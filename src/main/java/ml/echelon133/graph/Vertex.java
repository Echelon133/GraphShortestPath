package ml.echelon133.graph;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Concrete Vertex class. Instances of this class represent a vertex that can be added to a graph and then connected
 * with other vertexes using edges.
 * @param <T> type that is going to be used to represent weight values of edges in the graph
 */
public class Vertex<T extends Number & Comparable<T>> {
    private String name;
    private List<Edge<T>> edges;

    /**
     * @param name Name identifier of the vertex. If we want to have many vertexes in our graph we need to make sure
     *             that the name is unique, otherwise we won't be able to add that vertex to the graph
     */
    public Vertex(String name) {
        this.name = name;
        this.edges = new LinkedList<>();
    }

    /**
     * @return this vertex's name
     */
    public String getName() {
        return name;
    }

    /**
     * A method that returns a list of all of the edges in which this vertex is the source vertex
     * @return a list of the edges in which this vertex is the source vertex
     */
    public List<Edge<T>> getEdges() {
        return edges;
    }

    /**
     * A method that returns the lowest weight from this vertex to the {@code dest} vertex.
     * @param dest The destination vertex. The vertex that we call this method on is our source vertex
     * @return The weight from {@code this} to {@code dest}
     * @throws IllegalArgumentException if there is no edge between this vertex and {@code dest} vertex
     */
    public BigDecimal getWeightTo(Vertex<T> dest) throws IllegalArgumentException {
        // find edge from this vertex to dest vertex with lowest weight
        Optional<Edge<T>> lowestWeightEdge = edges
                .stream()
                .filter(e -> e.getDestination().equals(dest))
                .min(Comparator.comparing(Edge<T>::getWeight));

        if (lowestWeightEdge.isPresent()) {
            return lowestWeightEdge.get().getWeightAsBigDecimal();
        } else {
            String msg = String.format("There is no edge between %s and %s vertexes", this.getName(), dest.getName());
            throw new IllegalArgumentException(msg);
        }
    }

    /** A method that adds an edge to this vertex's list of edges. That list should store only the edges in which our vertex is
     * the source vertex.
     * @param e A standalone edge that has this vertex as its source (i.e. starts at this vertex)
     */
    public void addEdge(Edge<T> e) {
        edges.add(e);
    }

    /**
     *  A method that adds an edge between this vertex and {@code dest} vertex to this vertex's list of edges.
     * @param dest The destination vertex
     * @param weight The weight value of the new edge
     */
    public void addEdge(Vertex<T> dest, T weight) {
        Edge<T> e = new Edge<>(this, dest, weight);
        edges.add(e);
    }

    /**
     * A method that removes a specific edge from the list of edges that start at this vertex.
     * @param e The edge to remove
     */
    public void removeEdge(Edge<T> e) {
        edges.remove(e);
    }
}
