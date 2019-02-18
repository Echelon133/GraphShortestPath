package ml.echelon133.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WeightedGraph<T extends Number & Comparable<T>> implements Graph<T> {

    private List<Vertex<T>> vertexes;
    private List<Edge<T>> edges;

    public WeightedGraph() {
        vertexes = new ArrayList<>();
        edges = new ArrayList<>();
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
        Optional<Vertex<T>> nameVertex = vertexes.stream().filter(vert -> vert.getName().equals(v.getName())).findFirst();

        if (!nameVertex.isPresent()) {
            vertexes.add(v);
        } else {
            throw new IllegalArgumentException("Vertex with that name already belongs to this graph");
        }
    }

    @Override
    public void removeVertex(Vertex<T> v) {
        vertexes.remove(v);
    }

    @Override
    public void addEdge(Edge<T> e) {
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

        // WeightedGraph needs to have a reference to every edge in the graph
        edges.add(e);
        // The actual shortest path algorithm takes info about nearest vertexes from the source vertex.
        // Without the line below our algorithm will not 'see' any edges.
        source.addEdge(e);
    }

    @Override
    public void removeEdge(Edge<T> e) {
        edges.remove(e);

        Vertex<T> source = e.getSource();
        source.removeEdge(e);
    }
}
