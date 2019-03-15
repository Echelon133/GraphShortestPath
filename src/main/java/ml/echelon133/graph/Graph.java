package ml.echelon133.graph;

import java.util.List;

/**
 * Base interface for Graphs.
 * @param <T> type that is going to be used to represent weight values of edges in the graph
 */
public interface Graph<T extends Number & Comparable<T>> {

    /**
     * A getter method that returns a list of all vertexes that belong to the graph.
     * @return The list of all vertexes
     */
    List<Vertex<T>> getVertexes();

    /**
     * A getter method that returns a list of all edges that belong to the graph.
     * @return The list of all edges
     */
    List<Edge<T>> getEdges();

    /**
     * A method that adds a vertex to the graph. Graph's edges can only contain vertexes that were previously
     * added to the graph using this method.
     * @param v The vertex to add
     * @throws IllegalArgumentException if vertex already belongs to the graph
     */
    void addVertex(Vertex<T> v) throws IllegalArgumentException;

    /**
     * A method that removes a vertex from the graph. Removing a vertex from the graph also removes all of the
     * edges that start or end on that vertex.
     * @param v The vertex to remove
     */
    void removeVertex(Vertex<T> v);

    /**
     * A method that adds an edge to the graph.
     * @param e The edge to add
     * @throws IllegalArgumentException if an edge starting or ending vertex does not belong to the graph
     */
    void addEdge(Edge<T> e) throws IllegalArgumentException;

    /**
     * A method that constructs a new edge from parameters passed in and adds it to the graph.
     * @param source The source vertex of the edge
     * @param destination The destination vertex of the edge
     * @param weight The weight value of the new edge
     * @throws IllegalArgumentException if an edge source or destination vertex does not belong to the graph
     */
    void addEdge(Vertex<T> source, Vertex<T> destination, T weight) throws IllegalArgumentException;

    /**
     * A method that removes the edge from the graph.
     * @param e The edge to remove
     */
    void removeEdge(Edge<T> e);

    /**
     * A method that does quick vertex lookup in the graph based on the vertex name.
     * @param vName The name of the vertex to find
     * @return The found vertex or null value if searched vertex not found
     */
    Vertex<T> findVertex(String vName);
}
