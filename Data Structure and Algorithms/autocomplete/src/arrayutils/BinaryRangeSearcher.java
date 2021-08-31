package arrayutils;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Make sure to check out the interface for more method details:
 * @see ArraySearcher
 */
public class BinaryRangeSearcher<T, U> implements ArraySearcher<T, U> {
    private Matcher<T, U> matcher;
    private T[] array;

    /**
     * Creates a BinaryRangeSearcher for the given array of items that matches items using the
     * Matcher matchUsing.
     *
     * First sorts the array in place using the Comparator sortUsing. (Assumes that the given array
     * will not be used externally afterwards.)
     *
     * Requires that sortUsing sorts the array such that for any possible reference item U,
     * calling matchUsing.match(T, U) on each T in the sorted array will result in all negative
     * values first, then all 0 values, then all positive.
     *
     * For example:
     * sortUsing lexicographic string sort: [  aaa,  abc,   ba,  bzb, cdef ]
     * matchUsing T is prefixed by U
     * matchUsing.match for prefix "b":     [   -1,   -1,    0,    0,    1 ]
     *
     * @throws IllegalArgumentException if array is null or contains null
     * @throws IllegalArgumentException if sortUsing or matchUsing is null
     */

    public static <T, U> BinaryRangeSearcher<T, U> forUnsortedArray(T[] array,
                                                                    Comparator<T> sortUsing,
                                                                    Matcher<T, U> matchUsing) {
        if (sortUsing == null || matchUsing == null) {
            throw new IllegalArgumentException();
        }
        Arrays.sort(array, sortUsing);
        return new BinaryRangeSearcher<>(array, matchUsing);
    }

    /**
     * Requires that array is sorted such that for any possible reference item U,
     * calling matchUsing.match(T, U) on each T in the sorted array will result in all negative
     * values first, then all 0 values, then all positive.
     *
     * Assumes that the given array will not be used externally afterwards (and thus may directly
     * store and mutate the array).
     * @throws IllegalArgumentException if array is null or contains null
     * @throws IllegalArgumentException if matcher is null
     */
    public BinaryRangeSearcher(T[] array, Matcher<T, U> matcher) {
        if (array == null || matcher == null) {
            throw new IllegalArgumentException();
        }
        for (T item : array) {
            if (item == null) {
                throw new IllegalArgumentException();
            }
        }
        this.array = array;
        this.matcher = matcher;
    }

    public MatchResult<T> findAllMatches(U target) {
        if (target == null) {
            throw new IllegalArgumentException();
        }
        int beindex = findMatchLow(target, 0, this.array.length - 1);
        int laindex = findMatchHigh(target, 0, this.array.length - 1);
        if (beindex == -1 || laindex == -1) {
            return new MatchResult<>(this.array);
        } else {
            return new MatchResult<>(this.array, beindex,  laindex + 1);
        }
    }

    private int findMatchHigh(U target, int low, int high) {
        int mid = (low + high)/2;
        if (this.matcher.match(this.array[mid], target) == 0) {
            if (mid == this.array.length - 1) {
                return mid;
            } else if (this.matcher.match(this.array[mid + 1], target) == 0) {
                return findMatchHigh(target, mid + 1, high);
            } else {
                return mid;
            }
        } else if (high <= low) {
            if (this.matcher.match(this.array[high], target) == 0 && low > 0) {
                return high;
            }
            if (this.matcher.match(this.array[low], target) == 0 && high < this.array.length) {
                return low;
            } else {
                return -1;
            }
        } else if (this.matcher.match(this.array[mid], target) > 0) {
            if (mid == 0) {
                return -1;
            } else {
                return findMatchHigh(target, low, mid - 1);
            }
        } else {
            if (mid == this.array.length -1) {
                return -1;
            } else {
                return findMatchHigh(target, mid + 1, high);
            }
        }
    }

    private int findMatchLow(U target, int low, int high) {
        int mid = (low + high)/2;
        if (this.matcher.match(this.array[mid], target) == 0) {
            if (mid == 0) {
                return mid;
            } else if (this.matcher.match(this.array[mid - 1], target) == 0) {
                return findMatchLow(target, low, mid - 1);
            } else {
                return mid;
            }
        } else if (high <= low) {
            if (this.matcher.match(this.array[high], target) == 0 && low > 0) {
                return high;
            }
            if (this.matcher.match(this.array[low], target) == 0 && high < this.array.length) {
                return low;
            } else {
                return -1;
            }
        } else if (this.matcher.match(this.array[mid], target) > 0) {
            if (mid == 0) {
                return -1;
            } else {
                return findMatchLow(target, low, mid - 1);
            }
        } else {
            if (mid == this.array.length - 1) {
                return -1;
            } else {
                return findMatchLow(target, mid + 1, high);
            }
        }
    }

    public static class MatchResult<T> extends AbstractMatchResult<T> {
        final T[] array;
        final int start;
        final int end;

        /**
         * Use this constructor if there are no matching results.
         * (This lets us use Arrays.copyOfRange to make a new T[], which can be difficult to
         * acquire otherwise due to the way Java handles generics.)
         */
        protected MatchResult(T[] array) {
            this(array, 0, 0);
        }

        protected MatchResult(T[] array, int startInclusive, int endExclusive) {
            this.array = array;
            this.start = startInclusive;
            this.end = endExclusive;
        }

        @Override
        public int count() {
            return this.end - this.start;
        }

        @Override
        public T[] unsorted() {
            return Arrays.copyOfRange(this.array, this.start, this.end);
        }
    }
}

