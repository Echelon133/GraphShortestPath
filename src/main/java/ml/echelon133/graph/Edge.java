package ml.echelon133.graph;


import java.math.BigDecimal;
import java.math.BigInteger;

public class Edge<T extends Number & Comparable<T>> {
    private Vertex<T> source;
    private Vertex<T> destination;
    private T weight;

    public Edge(Vertex<T> source, Vertex<T> destination, T weight) throws IllegalArgumentException {

        // For now converting every T to double to check for negative value works fine
        if (weight.doubleValue() < 0) {
            throw new IllegalArgumentException("Edge weight cannot be negative");
        }

        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

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
