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
}
