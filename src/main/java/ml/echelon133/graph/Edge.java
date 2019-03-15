package ml.echelon133.graph;


import java.math.BigDecimal;
import java.math.BigInteger;

/**
 *  Concrete Edge class.
 * @param <T> type of the value that represents weights of edges in the graph
 */
public class Edge<T extends Number & Comparable<T>> {
    private Vertex<T> source;
    private Vertex<T> destination;
    private T weight;

    /**
     * @param source The source vertex (base of the arrow in a directed graph)
     * @param destination The destination vertex (tip of the arrow in a directed graph)
     * @param weight The weight value of the edge (only non-negative values are accepted)
     * @throws IllegalArgumentException if weight value is negative
     */
    public Edge(Vertex<T> source, Vertex<T> destination, T weight) throws IllegalArgumentException {

        // For now converting every T to double to check for negative value works fine
        if (weight.doubleValue() < 0) {
            throw new IllegalArgumentException("Edge weight cannot be negative");
        }

        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    /**
     * A method that checks whether the given vertex is either a source vertex or a destination vertex in this edge.
     * @param v The vertex to check
     * @return {@code true} if the edge source or destination is equal to v, {@code false} otherwise
     */
    public Boolean isVertexInEdge(Vertex<T> v) {
        return (v.equals(source) || v.equals(destination));
    }

    public Vertex<T> getSource() {
        return source;
    }

    public Vertex<T> getDestination() {
        return destination;
    }

    public T getWeight() {
        return weight;
    }

    /** A method that returns this edge value in {@code BigDecimal} representation.
     * @return {@code BigDecimal} with a value that is equal to this edge {@link #getWeight()}
     */
    public BigDecimal getWeightAsBigDecimal() {
        BigDecimal retWeight;

        if (weight instanceof Float) {
            retWeight = new BigDecimal(weight.floatValue());
        } else if (weight instanceof Double) {
            retWeight = new BigDecimal(weight.doubleValue());
        } else if (weight instanceof BigDecimal) {
            retWeight = (BigDecimal)weight;
        } else if (weight instanceof BigInteger) {
            retWeight = new BigDecimal((BigInteger)weight);
        } else {
            // take byte/short/int/long values as long, because there is no precision there
            retWeight = new BigDecimal(weight.longValue());
        }
        return retWeight;
    }
}
