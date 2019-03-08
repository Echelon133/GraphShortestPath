package ml.echelon133.graph;

import java.util.List;

public interface Graph<T extends Number & Comparable<T>> {
    List<Vertex<T>> getVertexes();
    List<Edge<T>> getEdges();

    void addVertex(Vertex<T> v);
    void removeVertex(Vertex<T> v);
    void addEdge(Edge<T> e) throws IllegalArgumentException;
    void addEdge(Vertex<T> source, Vertex<T> destination, T weight) throws IllegalArgumentException;
    void removeEdge(Edge<T> e);
}
