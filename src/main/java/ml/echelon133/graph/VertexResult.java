package ml.echelon133.graph;

import java.math.BigDecimal;
import java.util.LinkedList;

/**
 * This class stores all information about a single vertex (computed during the execution of the shortest path algorithm).
 * @param <T> type that represent weight values of edges in the graph that we executed the shortest path algorithm on
 */
public class VertexResult<T extends Number & Comparable<T>> {
    private Vertex<T> sourceVertex;
    private Vertex<T> previousVertex;
    private BigDecimal sumOfWeights;
    private LinkedList<Vertex<T>> pathToVertex;

    /**
     * @param sourceVertex The vertex that this class instance is going to describe
     */
    public VertexResult(Vertex<T> sourceVertex) {
        this.sourceVertex = sourceVertex;
        this.previousVertex = null;
        // indicate infinite value with null, because it is impossible to get BigInteger or BigDecimal MAX_VALUE
        this.sumOfWeights = null;
        this.pathToVertex = new LinkedList<>();
    }

    /** Get the vertex that this {@link VertexResult} describes
     * @return The {@code sourceVertex}
     */
    public Vertex<T> getSourceVertex() {
        return sourceVertex;
    }

    public void setSourceVertex(Vertex<T> sourceVertex) {
        this.sourceVertex = sourceVertex;
    }

    /** Get the vertex that comes just before the source vertex in the computed shortest path.
     *
     * @return The previous vertex that leads to the source vertex in the shortest path ({@code null} if there was no
     * previous vertex, because the source vertex of this {@link VertexResult} was used as the starting vertex of
     * the shortest path algorithm)
     */
    public Vertex<T> getPreviousVertex() {
        return previousVertex;
    }

    public void setPreviousVertex(Vertex<T> previousVertex) {
        this.previousVertex = previousVertex;
    }

    /**
     * A method that returns the sum computed by adding together all of the weights of edges that create the path to this
     * {@link VertexResult}'s source vertex
     * @return Sum of weights to the source vertex of this {@link VertexResult} instance
     */
    public BigDecimal getSumOfWeights() {
        return sumOfWeights;
    }

    public void setSumOfWeights(BigDecimal sumOfWeights) {
        this.sumOfWeights = sumOfWeights;
    }

    /**
     * Get all of the vertexes that create the path to this {@link VertexResult}'s source vertex (without source vertex itself)
     * in the order they were visited in.
     *
     * The first vertex in that list is the vertex that was used as a starting point in {@link ShortestPathSolver#solveStartingFrom(Vertex)}
     * (exception - {@link VertexResult} of the starting point always has an empty path, because there weren't any vertexes visited before getting to it).
     * The last vertex of that list should <b>always</b> be equal to the vertex that is returned by {@link #getPreviousVertex()}.
     *
     * @return A list of all vertexes visited before reaching the source vertex (in order they were visited in)
     */
    public LinkedList<Vertex<T>> getPathToVertex() {
        return pathToVertex;
    }


    /**
     * A method that copies the {@code pathToVertex} of a {@link VertexResult} given as an argument to this
     * {@link VertexResult}'s {@code pathToVertex} and then updates that copied {@code pathToVertex} with the source vertex
     * taken from {@code prevVertexResult}.
     *
     * E.g. if we visit vertexes ["v1", "v2", "v3"] in sequential order, then the path to "v2" is just ["v1"].
     * The path to "v3" is ["v1", "v2"], meaning that it is the path of "v2" with "v2" itself added to it.
     *
     * @param prevVertexResult The {@link VertexResult} of the vertex that comes before this {@link VertexResult}'s source vertex in a path
     */
    public void copyAndUpdatePathToVertexFrom(VertexResult<T> prevVertexResult) {
        // Path to this (sourceVertex) vertex is a copy of a path to the previousVertex...
        this.pathToVertex = new LinkedList<>(prevVertexResult.getPathToVertex());

        // With the previous vertex itself added to it. Last element of pathToVertex should always be equal to previousVertex
        this.pathToVertex.add(prevVertexResult.getSourceVertex());
    }
}
