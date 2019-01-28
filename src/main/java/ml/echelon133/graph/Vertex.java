package ml.echelon133.graph;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


public class Vertex<T extends Number & Comparable<T>> {
    private String name;
    private List<Edge<T>> edges;

    public Vertex(String name) {
        this.name = name;
        this.edges = new LinkedList<>();
    }

    public String getName() {
        return name;
    }

    public List<Edge<T>> getEdges() {
        return edges;
    }

    public T getWeightTo(Vertex<T> dest) throws IllegalArgumentException {
        // find edge from this vertex to dest vertex with lowest weight
        Optional<Edge<T>> lowestWeightEdge = edges
                .stream()
                .filter(e -> e.getDestination().equals(dest))
                .min(Comparator.comparing(Edge<T>::getWeight));

        if (lowestWeightEdge.isPresent()) {
            return lowestWeightEdge.get().getWeight();
        } else {
            String msg = String.format("There is no edge between %s and %s vertexes", this.getName(), dest.getName());
            throw new IllegalArgumentException(msg);
        }
    }

    public void addEdge(Edge<T> e) {
        edges.add(e);
    }

    public void addEdge(Vertex<T> dest, T weight) {
        Edge<T> e = new Edge<>(this, dest, weight);
        edges.add(e);
    }

    public void removeEdge(Edge<T> e) {
        edges.remove(e);
    }


}
