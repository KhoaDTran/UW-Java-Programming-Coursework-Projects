package pointsets;

import java.text.DecimalFormat;
import java.util.Objects;

/**
 * A 2-d point class with some methods for calculating distances between points.
 */
public class Point {
    private static final DecimalFormat NUMBER_FORMATTER = new DecimalFormat("#.######");

    private final double x;
    private final double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the Euclidean distance (L2 norm) squared between two points.
     * Note: This is the square of the Euclidean distance, i.e. there's no
     * square root.
     */
    public double distanceSquaredTo(Point p) {
        return this.distanceSquaredTo(p.x, p.y);
    }

    /**
     * Returns the Euclidean distance (L2 norm) squared between two points.
     * Note: This is the square of the Euclidean distance, i.e. there's no
     * square root.
     */
    public double distanceSquaredTo(double px, double py) {
        return distanceSquaredBetween(this.x, px, this.y, py);
    }

    /**
     * Returns the Euclidean distance (L2 norm) squared between two points
     * (x1, y1) and (x2, y2).
     */
    public static double distanceSquaredBetween(double x1, double x2, double y1, double y2) {
        return Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2);
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Point point = (Point) o;
        return Double.compare(point.x, x) == 0 &&
                Double.compare(point.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return String.format("(x: %s, y: %s)", NUMBER_FORMATTER.format(x), NUMBER_FORMATTER.format(y));
    }
}
