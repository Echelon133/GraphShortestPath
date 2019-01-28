package ml.echelon133.graph;


public class Edge<T extends Number & Comparable<T>> {
    private Vertex<T> source;
    private Vertex<T> destination;
    private T weight;

    public Edge(Vertex<T> source, Vertex<T> destination, T weight) {
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
