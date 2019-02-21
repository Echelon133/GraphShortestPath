package ml.echelon133.graph;

import java.math.BigDecimal;
import java.util.LinkedList;

public class VertexResult<T extends Number & Comparable<T>> {
    private Vertex<T> sourceVertex;
    private Vertex<T> previousVertex;
    private BigDecimal sumOfWeights;
    private LinkedList<Vertex<T>> pathToVertex;

    public VertexResult(Vertex<T> sourceVertex) {
        this.sourceVertex = sourceVertex;
        this.previousVertex = null;
        // indicate infinite value with null, because it is impossible to get BigInteger or BigDecimal MAX_VALUE
        this.sumOfWeights = null;
        this.pathToVertex = new LinkedList<>();
    }

    public Vertex<T> getSourceVertex() {
        return sourceVertex;
    }

    public void setSourceVertex(Vertex<T> sourceVertex) {
        this.sourceVertex = sourceVertex;
    }

    public Vertex<T> getPreviousVertex() {
        return previousVertex;
    }

    public void setPreviousVertex(Vertex<T> previousVertex) {
        this.previousVertex = previousVertex;
    }

    public BigDecimal getSumOfWeights() {
        return sumOfWeights;
    }

    public void setSumOfWeights(BigDecimal sumOfWeights) {
        this.sumOfWeights = sumOfWeights;
    }

    public LinkedList<Vertex<T>> getPathToVertex() {
        return pathToVertex;
    }

    public void copyAndUpdatePathToVertexFrom(VertexResult<T> prevVertexResult) {
        // Path to this (sourceVertex) vertex is a copy of a path to the previousVertex...
        this.pathToVertex = new LinkedList<>(prevVertexResult.getPathToVertex());

        // With the previous vertex itself added to it. Last element of pathToVertex should always be equal to previousVertex
        this.pathToVertex.add(prevVertexResult.getSourceVertex());
    }
}
