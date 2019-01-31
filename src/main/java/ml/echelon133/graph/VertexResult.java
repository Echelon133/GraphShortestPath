package ml.echelon133.graph;

public class VertexResult<T extends Number & Comparable<T>> {
    private Vertex<T> sourceVertex;
    private Vertex<T> previousVertex;
    private T sumOfWeights;

    public VertexResult(Vertex<T> sourceVertex) {
        this.sourceVertex = sourceVertex;
        this.previousVertex = null;
        // indicate infinite value with null, because it is impossible to get BigInteger or BigDecimal MAX_VALUE
        this.sumOfWeights = null;
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

    public T getSumOfWeights() {
        return sumOfWeights;
    }

    public void setSumOfWeights(T sumOfWeights) {
        this.sumOfWeights = sumOfWeights;
    }
}
