package ml.echelon133.graph;

import java.util.*;

/**
 * Implementation of {@link Graph} interface. This class represents a graph that has weighted edges.
 * @param <T> type of the value that represents weights of edges in the graph
 */
public class WeightedGraph<T extends Number & Comparable<T>> implements Graph<T> {

    private List<Vertex<T>> vertexes;
    private Map<String, Vertex<T>> vertexHelperMap;
    private List<Edge<T>> edges;

    public WeightedGraph() {
        vertexes = new ArrayList<>();
        edges = new ArrayList<>();
        vertexHelperMap = new HashMap<>();
    }

    @Override
    public List<Vertex<T>> getVertexes() {
        return vertexes;
    }

    @Override
    public List<Edge<T>> getEdges() {
        return edges;
    }

    @Override
    public void addVertex(Vertex<T> v) throws IllegalArgumentException {

        // vertexHelperMap is used internally to make lookup of vertexes faster
        // external objects can only access graph's vertexes through getVertexes()
        if (!vertexHelperMap.containsKey(v.getName())) {
            vertexes.add(v);
            vertexHelperMap.put(v.getName(), v);
        } else {
            throw new IllegalArgumentException("Vertex with that name already belongs to this graph");
        }
    }

    @Override
    public void removeVertex(Vertex<T> v) {
        // Remove every edge from the graph that contains removed vertex
        this.edges.removeIf(edge -> edge.isVertexInEdge(v));

        vertexes.remove(v);
        vertexHelperMap.remove(v.getName());
    }

    @Override
    public void addEdge(Edge<T> e) throws IllegalArgumentException {

        if (!vertexHelperMap.containsKey(e.getSource().getName()) || !vertexHelperMap.containsKey(e.getDestination().getName())) {
            throw new IllegalArgumentException("Edge contains a vertex that does not belong to the graph");
        }

        // WeightedGraph needs to have a reference to every edge in the graph
        edges.add(e);

        Vertex<T> source = e.getSource();
        // The actual shortest path algorithm takes info about nearest vertexes from the source vertex.
        // Without the line below our algorithm will not 'see' any edges.
        source.addEdge(e);
    }

    @Override
    public void addEdge(Vertex<T> source, Vertex<T> destination, T weight) throws IllegalArgumentException {
        Edge<T> e = new Edge<>(source, destination, weight);

        // use addEdge(Edge<T>) for simplicity
        this.addEdge(e);
    }

    @Override
    public void removeEdge(Edge<T> e) {
        edges.remove(e);

        Vertex<T> source = e.getSource();
        source.removeEdge(e);
    }

    @Override
    public Vertex<T> findVertex(String vName) {
        return vertexHelperMap.get(vName);
    }
}
