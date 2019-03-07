package ml.echelon133.graph;

import java.util.*;

public class WeightedGraph<T extends Number & Comparable<T>> implements Graph<T> {

    private List<Vertex<T>> vertexes;
    private Map<String, Vertex<T>> vertexHelperMap;
    private List<Edge<T>> edges;
    private boolean enableVertexSearchPerformance;

    public WeightedGraph() {
        vertexes = new ArrayList<>();
        edges = new ArrayList<>();
    }

    public WeightedGraph(Boolean enableVertexSearchPerformance) {
        this();
        this.enableVertexSearchPerformance = enableVertexSearchPerformance;
    }

    private void initVertexHelperMapIfNeeded() {
        // if our graph contains more than 200 vertexes, instantiate a map that helps with
        // changing linear time vertex search to constant time search (but using more memory)
        if (vertexes.size() > 200 && vertexHelperMap == null) {
            vertexHelperMap = new HashMap<>();

            // this will be performed only once, when size passes the threshold
            for (Vertex<T> v : vertexes) {
                vertexHelperMap.put(v.getName(), v);
            }
        }
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
        if (enableVertexSearchPerformance) {
            initVertexHelperMapIfNeeded();
        }

        boolean isVertexInGraph;

        // when vertexHelperMap is null, it means that only linear search is available to us
        // otherwise we can use our map to find vertexes by name in constant time
        if (vertexHelperMap != null) {
            isVertexInGraph = vertexHelperMap.containsKey(v.getName());
        } else {
            Optional<Vertex<T>> nameVertex = vertexes.stream().filter(vert -> vert.getName().equals(v.getName())).findFirst();
            isVertexInGraph = nameVertex.isPresent();
        }

        if (!isVertexInGraph && vertexHelperMap == null) {
            vertexes.add(v);
        } else if (!isVertexInGraph) {
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

        if (vertexHelperMap != null) {
            vertexHelperMap.remove(v.getName());
        }
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
