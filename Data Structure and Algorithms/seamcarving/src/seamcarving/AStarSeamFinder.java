package seamcarving;

import graphpathfinding.AStarGraph;
import graphpathfinding.AStarPathFinder;
import graphpathfinding.ShortestPathFinder;
import graphpathfinding.WeightedEdge;

import java.awt.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AStarSeamFinder extends SeamFinder {
    /*
    Use this method to create your ShortestPathFinder.
    This will be overridden during grading to use our solution path finder, so you don't get
    penalized again for any bugs in code from previous assignments
    */
    @Override
    protected <VERTEX> ShortestPathFinder<VERTEX> createPathFinder(AStarGraph<VERTEX> graph) {
        return new AStarPathFinder<>(graph);
    }

    public class AStarSeamGraph<VERTEX extends Point> implements AStarGraph<VERTEX> {
        private double[][] energyArray;
        private boolean horizontalSeam;

        public AStarSeamGraph(double[][] energyArray, boolean horizontalSeam) {
            this.energyArray = energyArray;
            this.horizontalSeam = horizontalSeam;
        }

        public Collection<WeightedEdge<VERTEX>> neighbors(VERTEX v) {
            List<WeightedEdge<VERTEX>> edgeList = new ArrayList<>();
            if (horizontalSeam) {
                addHoriz(v, edgeList, energyArray[0].length - 1, energyArray.length - 1);
            } else {
                addVert(v, edgeList, energyArray.length - 1,  energyArray[0].length - 1);
            }
            return edgeList;
        }

        public double estimatedDistanceToGoal(VERTEX v, VERTEX goal) {
            return 0;
        }

        @SuppressWarnings("checkstyle:WhitespaceAfter")
        private void addHoriz(VERTEX vertex, List<WeightedEdge<VERTEX>> edgeNeighbors, int maxVal, int length) {
            Point curr;
            Point start;
            Point end;
            if (vertex.x >= 0 && vertex.x < length) {
                if (vertex.y == maxVal) {
                    start = new Point(vertex.x + 1, vertex.y - 1);
                    curr = new Point(vertex.x + 1, vertex.y);
                    edgeNeighbors.add(new WeightedEdge<>(vertex, (VERTEX) curr, energyArray[curr.x][curr.y]));
                    edgeNeighbors.add(new WeightedEdge<>(vertex, (VERTEX) start, energyArray[start.x][start.y]));
                } else if (vertex.y == 0) {
                    start = new Point(vertex.x + 1, vertex.y + 1);
                    curr = new Point(vertex.x + 1, vertex.y);
                    edgeNeighbors.add(new WeightedEdge<>(vertex, (VERTEX) curr, energyArray[curr.x][curr.y]));
                    edgeNeighbors.add(new WeightedEdge<>(vertex, (VERTEX) start, energyArray[start.x][start.y]));
                } else {
                    start = new Point(vertex.x + 1, vertex.y);
                    end = new Point(vertex.x + 1, vertex.y + 1);
                    curr = new Point(vertex.x + 1, vertex.y - 1);
                    edgeNeighbors.add(new WeightedEdge<>(vertex, (VERTEX) curr, energyArray[curr.x][curr.y]));
                    edgeNeighbors.add(new WeightedEdge<>(vertex, (VERTEX) start, energyArray[start.x][start.y]));
                    edgeNeighbors.add(new WeightedEdge<>(vertex, (VERTEX) end, energyArray[end.x][end.y]));
                }
            }
            else  if (vertex.x == -1) {
                for (int i = 0; i < energyArray[0].length; i++) {
                    edgeNeighbors.add(new WeightedEdge<>(vertex, (VERTEX) new Point(0, i), energyArray[0][i]));
                }
            } else if (vertex.x == length) {
                edgeNeighbors.add(new WeightedEdge<>(vertex, (VERTEX) new Point(energyArray.length, 0), 0));
            }
        }

        private void addVert(VERTEX vertex, List<WeightedEdge<VERTEX>> edgeNeighbors, int maxVal, int length) {
            Point curr;
            Point start;
            Point end;
            if (vertex.y >= 0 && vertex.y < length) {
                if (vertex.x == maxVal) {
                    start = new Point(vertex.x, vertex.y + 1);
                    curr = new Point(vertex.x - 1, vertex.y + 1);
                    edgeNeighbors.add(new WeightedEdge<>(vertex, (VERTEX) curr, energyArray[curr.x][curr.y]));
                    edgeNeighbors.add(new WeightedEdge<>(vertex, (VERTEX) start, energyArray[start.x][start.y]));
                } else if (vertex.x == 0) {
                    start = new Point(vertex.x + 1, vertex.y + 1);
                    curr = new Point(vertex.x, vertex.y + 1);
                    edgeNeighbors.add(new WeightedEdge<>(vertex, (VERTEX) curr, energyArray[curr.x][curr.y]));
                    edgeNeighbors.add(new WeightedEdge<>(vertex, (VERTEX) start, energyArray[start.x][start.y]));
                } else {
                    start = new Point(vertex.x, vertex.y + 1);
                    end = new Point(vertex.x + 1, vertex.y + 1);
                    curr = new Point(vertex.x - 1, vertex.y + 1);
                    edgeNeighbors.add(new WeightedEdge<>(vertex, (VERTEX) curr, energyArray[curr.x][curr.y]));
                    edgeNeighbors.add(new WeightedEdge<>(vertex, (VERTEX) start, energyArray[start.x][start.y]));
                    edgeNeighbors.add(new WeightedEdge<>(vertex, (VERTEX) end, energyArray[end.x][end.y]));
                }
            } else if (vertex.y == -1) {
                for (int i = 0; i < energyArray.length; i++) {
                    edgeNeighbors.add(new WeightedEdge<>(vertex, (VERTEX) new Point(i, 0), energyArray[i][0]));
                }
            } else if (vertex.y == length) {
                edgeNeighbors.add(new WeightedEdge<>(vertex, (VERTEX) new Point(0, energyArray[0].length), 0));
            }
        }
    }
    @Override
    public List<Integer> findHorizontalSeam(double[][] energies) {
        List<Integer> seamResult = new ArrayList<>();
        AStarSeamGraph<Point> graph = new AStarSeamGraph<>(energies, true);
        List<Point> result = createPathFinder(graph).findShortestPath(new Point(-1, 0),
            new Point(energies.length, 0), Duration.ofSeconds(20)).solution();
        for (int i = 1; i < result.size() - 1; i++) {
            seamResult.add(result.get(i).y);
        }
        return seamResult;
    }

    @Override
    public List<Integer> findVerticalSeam(double[][] energies) {
        List<Integer> seamResult = new ArrayList<>();
        AStarSeamGraph<Point> graph = new AStarSeamGraph<>(energies, false);
        List<Point> result = createPathFinder(graph).findShortestPath(new Point(0, -1),
            new Point(0, energies[0].length), Duration.ofSeconds(20)).solution();
        for (int i = 1; i < result.size() - 1; i++) {
            seamResult.add(result.get(i).x);
        }
        return seamResult;
    }
}
