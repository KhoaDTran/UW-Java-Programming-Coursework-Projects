package pointsets;

import java.util.ArrayList;
import java.util.List;

/**
 * Naive nearest-neighbor implementation using a linear scan.
 */
public class NaivePointSet<T extends Point> implements PointSet<T> {
    private List<T> points;

    /**
     * Instantiates a new NaivePointSet with the given points.
     * @param points a non-null, non-empty list of points to include
     *               Assumes that the list will not be used externally afterwards (and thus may
     *               directly store and mutate the array).
     */
    public NaivePointSet(List<T> points) {
        this.points = points;
    }

    /**
     * Returns the point in this set closest to the given point in O(N) time, where N is the number
     * of points in this set.
     */
    @Override
    public T nearest(Point target) {
        if (points.isEmpty()) {
            return null;
        }
        List<Double> distances = new ArrayList<>();
        for (int i = 0; i <= points.size() - 1; i++) {
            distances.add(points.get(i).distanceSquaredTo(target));
        }
        double minDistance = distances.get(0);
        T pointNearest = points.get(0);
        for (int j = 1; j <= points.size() - 1; j++) {
            if (distances.get(j) < minDistance) {
                minDistance = distances.get(j);
                pointNearest = points.get(j);
            }
        }
        return pointNearest;
    }

    @Override
    public List<T> allPoints() {
        return points;
    }
}
