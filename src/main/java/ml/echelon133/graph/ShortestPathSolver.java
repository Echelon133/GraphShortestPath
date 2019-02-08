package ml.echelon133.graph;

import java.math.BigDecimal;
import java.util.*;

public class ShortestPathSolver<T extends Number & Comparable<T>> {

    private class VertexComparator implements Comparator<Vertex<T>> {

        @Override
        public int compare(Vertex<T> o1, Vertex<T> o2) {
            // Comparator needs to always sort Vertex<T> elements in the PriorityQueue
            // based on their sumOfWeights that is stored in VertexResult (value of resultMap)
            BigDecimal o1SumOfWeights = resultMap.get(o1).getSumOfWeights();
            BigDecimal o2SumOfWeights = resultMap.get(o2).getSumOfWeights();
            return o1SumOfWeights.compareTo(o2SumOfWeights);
        }
    }

    private Graph<T> graph;
    private Set<Vertex<T>> visitedVertexes;
    private PriorityQueue<Vertex<T>> workQueue;
    private Map<Vertex<T>, VertexResult<T>> resultMap;

    public ShortestPathSolver(Graph<T> graph) {
        this.graph = graph;
        this.visitedVertexes = new HashSet<>();
        this.workQueue = new PriorityQueue<>(new VertexComparator());
        this.resultMap = new HashMap<>();
    }

    private void putVertexInResultMapIfNotContains(Vertex<T> v, boolean startVertex) {

    }

    private void relax(Vertex<T> v1, Vertex<T> v2) {

    }

    public Map<Vertex<T>, VertexResult<T>> solveStartingFrom(Vertex<T> v) throws IllegalArgumentException {
        // Algorithm ref: https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm

        // Clear map of any info from previous method calls
        resultMap.clear();

        if (!graph.getVertexes().contains(v)) {
            throw new IllegalArgumentException("Graph does not contain the vertex given as an argument");
        }

        return resultMap;
    }
}
