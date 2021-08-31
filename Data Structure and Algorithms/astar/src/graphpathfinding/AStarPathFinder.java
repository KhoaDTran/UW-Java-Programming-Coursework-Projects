package graphpathfinding;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import priorityqueues.DoubleMapMinPQ;
import timing.Timer;


/**
 * @see ShortestPathFinder for more method documentation
 */
public class AStarPathFinder<VERTEX> extends ShortestPathFinder<VERTEX> {
    private DoubleMapMinPQ<VERTEX> minPq;
    private HashMap<VERTEX, Double> distTo;
    private HashMap<VERTEX, VERTEX> edgeTo;
    private AStarGraph<VERTEX> graph;
    private List<VERTEX> solution;
    private int totalState;

    /**
     * Creates a new AStarPathFinder that works on the provided graph.
     */
    public AStarPathFinder(AStarGraph<VERTEX> graph) {
        this.graph = graph;
        minPq = new DoubleMapMinPQ<>();
        solution = new ArrayList<>();
        distTo = new HashMap<>();
        edgeTo = new HashMap<>();
        totalState = 0;
    }

    @Override
    public ShortestPathResult<VERTEX> findShortestPath(VERTEX start, VERTEX end, Duration timeout) {
        Timer watch = new Timer(timeout);
        distTo.put(start, 0.0);
        minPq.add(start, 0);
        while (!minPq.peekMin().equals(end) && !watch.isTimeUp()) {
            totalState += 1;
            VERTEX value = minPq.removeMin();
            for (WeightedEdge<VERTEX> edge: this.graph.neighbors(value)) {
                relax(this.graph, end, edge);
            }
            if (minPq.size() == 0) {
                return new ShortestPathResult.Unsolvable<>(totalState, watch.elapsedDuration());
            }
        }
        if (watch.isTimeUp()) {
            return new ShortestPathResult.Timeout<>(totalState, watch.elapsedDuration());
        } else {
            addList(start, end, solution, edgeTo);
            double weight = distTo.get(minPq.peekMin());
            return new ShortestPathResult.Solved<>(solution, weight,
                totalState, watch.elapsedDuration());
        }
    }

    private void relax(AStarGraph<VERTEX> graphs, VERTEX end, WeightedEdge<VERTEX> edge) {
        VERTEX vFrom = edge.from();
        VERTEX vNext = edge.to();
        if (!distTo.containsKey(vNext) || edge.weight() + distTo.get(vFrom) < distTo.get(vNext)) {
            distTo.put(vNext, edge.weight() + distTo.get(vFrom));
            edgeTo.put(vNext, vFrom);
            if (minPq.contains(vNext)) {
                minPq.changePriority(vNext, distTo.get(vNext) + graphs.estimatedDistanceToGoal(vNext, end));
            } else {
                minPq.add(vNext, distTo.get(vNext) + graphs.estimatedDistanceToGoal(vNext, end));
            }
        }
    }

    private void addList(VERTEX start, VERTEX end, List<VERTEX> solutions, HashMap<VERTEX, VERTEX> edgeTos) {
        if (!start.equals(end)) {
            addList(start, edgeTos.get(end), solutions, edgeTos);
        }
        solutions.add(end);
    }

    @Override
    protected AStarGraph<VERTEX> graph() {
        return this.graph;
    }
}
