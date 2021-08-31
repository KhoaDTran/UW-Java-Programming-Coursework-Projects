package huskymaps.routing;

import graphpathfinding.AStarGraph;
import graphpathfinding.AStarPathFinder;
import graphpathfinding.ShortestPathFinder;
import huskymaps.graph.Coordinate;
import huskymaps.graph.Node;
import huskymaps.graph.StreetMapGraph;
import pointsets.KDTreePointSet;
import pointsets.Point;
import pointsets.PointSet;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static huskymaps.utils.Spatial.projectToPoint;

/**
 * @see Router
 */
public class DefaultRouter extends Router {
    private StreetMapGraph graph;
    private List<NodePoint> point;
    private PointSet<NodePoint> tree;
    private List<Node> nodes;

    private Map<Node, NodePoint> nodeToPoint = new HashMap<>();

    public DefaultRouter(StreetMapGraph graph) {
        this.graph = graph;
        nodes = graph.allNodes();
        point = new ArrayList<>();
        for (Node node: nodes) {
            if (!graph.neighbors(node).isEmpty()) {
                point.add(createNodePoint(node));
            }

        }
        tree = createPointSet(point);
    }

    @Override
    protected <T extends Point> PointSet<T> createPointSet(List<T> points) {
        // uncomment (and import) if you want to use WeirdPointSet instead of your own KDTreePointSet:
        // return new WeirdPointSet<>(points);
        return KDTreePointSet.createAfterShuffling(points);
    }

    @Override
    protected <VERTEX> ShortestPathFinder<VERTEX> createPathFinder(AStarGraph<VERTEX> g) {
        return new AStarPathFinder<>(g);
    }

    @Override
    protected NodePoint createNodePoint(Node node) {
        return projectToPoint(Coordinate.fromNode(node), (x, y) -> new NodePoint(x, y, node));
    }

    @Override
    protected Node closest(Coordinate c) {
        // Project to x and y coordinates instead of using raw lat and lon for finding closest points:
        Point p = projectToPoint(c, Point::new);
        return tree.nearest(p).node();
    }

    @Override
    public List<Node> shortestPath(Coordinate start, Coordinate end) {
        Node src = closest(start);
        Node dest = closest(end);
        return createPathFinder(graph).findShortestPath(src, dest, Duration.ofSeconds(20)).solution();

    }

    @Override
    public List<NavigationDirection> routeDirections(List<Node> route) {
        // Optional
        return null;
    }
}
