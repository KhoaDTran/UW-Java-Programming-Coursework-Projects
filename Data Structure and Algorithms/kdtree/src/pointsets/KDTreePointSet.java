package pointsets;

import java.util.Collections;
import java.util.List;

/**
 * Fast nearest-neighbor implementation using a k-d tree.
 */
public class KDTreePointSet<T extends Point> implements PointSet<T> {
    private Node overallRoot;
    private List<T> points;

    private class Node {
        private T point;
        private Node left;
        private Node right;

        public Node(T point) {
            this.point = point;
            this.left = null;
            this.right = null;
        }

        public Node(T point, Node left, Node right) {
            this.point = point;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * Instantiates a new KDTreePointSet with a shuffled version of the given points.
     *
     * Randomizing the point order decreases likeliness of ending up with a spindly tree if the
     * points are sorted somehow.
     *
     * @param points a non-null, non-empty list of points to include.
     *               Assumes that the list will not be used externally afterwards (and thus may
     *               directly store and mutate the array).
     */
    public static <T extends Point> KDTreePointSet<T> createAfterShuffling(List<T> points) {
        Collections.shuffle(points);
        return new KDTreePointSet<T>(points);
    }

    /**
     * Instantiates a new KDTreePointSet with the given points.
     *
     * @param points a non-null, non-empty list of points to include.
     *               Assumes that the list will not be used externally afterwards (and thus may
     *               directly store and mutate the array).
     */
    KDTreePointSet(List<T> points) {
        this.points = points;
        for (int i = 0; i < this.points.size(); i++) {
            overallRoot = addTree(overallRoot, points.get(i), true);
        }
    }

    private Node addTree(Node root, T point, boolean checkLevel) {
        if (root == null) {
            return new Node(point);
        } else if (root.point.equals(point)) {
            root.point = point;
        }
        if (checkLevel) {
            if (root.point.x() < point.x()) {
                root.right = addTree(root.right, point, false);
            } else {
                root.left = addTree(root.left, point, false);
            }
        } else if (!checkLevel) {
            if (root.point.y() < point.y()) {
                root.right = addTree(root.right, point, true);
            } else {
                root.left = addTree(root.left, point, true);
            }
        }
        return root;
    }

    /**
     * Returns the point in this set closest to the given point in (usually) O(log N) time, where
     * N is the number of points in this set.
     */
    @Override
    public T nearest(Point target) {
        return nearestHelp(overallRoot, target, overallRoot.point, true);
    }

    private T nearestHelp(Node root, Point target, T nearestPoint, boolean levelChecker) {
        if (root == null) {
            return nearestPoint;
        }
        if (root.point.equals(target)) {
            return root.point;
        }
        if (root.point.distanceSquaredTo(target) < nearestPoint.distanceSquaredTo(target)) {
            nearestPoint = root.point;
        }
        double distToLine = 0;   //get the distance to the partition line
        if (levelChecker) {
            distToLine = root.point.x() - target.x();
        } else if (!levelChecker) {
            distToLine = root.point.y() - target.y();
        }
        if (distToLine <= 0) {
            nearestPoint = nearestHelp(root.right, target, nearestPoint, !levelChecker);
            if (target.distanceSquaredTo(nearestPoint) >= (Math.pow(distToLine, 2))) {
                nearestPoint = nearestHelp(root.left, target, nearestPoint, !levelChecker);
            }
        } else {
            nearestPoint = nearestHelp(root.left, target, nearestPoint, !levelChecker);
            if (target.distanceSquaredTo(nearestPoint) >= (Math.pow(distToLine, 2))) {
                nearestPoint = nearestHelp(root.right, target, nearestPoint, !levelChecker);
            }
        }
        return nearestPoint;
    }

    @Override
    public List<T> allPoints() {
        return this.points;
    }
}
