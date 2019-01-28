package ml.echelon133.graph;


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

    public Vertex<T> getSource() {
        return source;
    }

    public Vertex<T> getDestination() {
        return destination;
    }

    public T getWeight() {
        return weight;
    }
}
