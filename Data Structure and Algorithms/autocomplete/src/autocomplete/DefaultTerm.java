package autocomplete;

import java.util.Objects;

/**
 * This is currently the only implementation of the {@link Term} interface, which is why it's named
 * "default." (Having an interface with a single implementation is a little redundant, but we need
 * it to keep you from accidentally renaming things.)
 *
 * Make sure to check out the interface for method specifications.
 * @see Term
 */
public class DefaultTerm implements Term {
    private String query;
    private long weight;

    @Override
    public String toString() {
        return "DefaultTerm{" +
            "query='" + query + '\'' +
            ", weight=" + weight +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DefaultTerm that = (DefaultTerm) o;
        return weight == that.weight &&
            Objects.equals(query, that.query);
    }

    @Override
    public int hashCode() {
        return Objects.hash(query, weight);
    }

    /**
     * Initializes a term with the given query string and weight.
     *
     * @throws IllegalArgumentException if query is null or weight is negative
     */
    public DefaultTerm(String query, long weight) {
        if (weight < 0 || query == null) {
            throw new IllegalArgumentException();
        }
        this.query = query;
        this.weight = weight;
    }

    @Override
    public String query() {
        return this.query;
    }

    @Override
    public long weight() {
        return this.weight;
    }

    @Override
    public int queryOrder(Term that) {
        if (that == null) {
            throw new NullPointerException();
        }
        return this.query.compareTo(that.query());
    }

    @Override
    public int reverseWeightOrder(Term that) {
        return Long.compare(that.weight(), weight);
    }

    @Override
    public int matchesPrefix(String prefix) {
        if (prefix.length() <= this.query.length()) {
            return this.query.substring(0, prefix.length()).compareTo(prefix);
        }
        return this.query.compareTo(prefix);
    }
}
