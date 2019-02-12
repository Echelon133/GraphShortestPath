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
        if (!resultMap.containsKey(v)) {
            VertexResult<T> vResult = new VertexResult<>(v);

            if (startVertex) {
                // vertex that we start from always has sumOfWeights equal to 0
                vResult.setSumOfWeights(new BigDecimal(0));
            }
            resultMap.put(v, vResult);
        }
    }

    private void relax(Vertex<T> v1, Vertex<T> v2) {
        // if v2 was not visited/relaxed yet, it is not present in resultMap
        putVertexInResultMapIfNotContains(v2, false);

        VertexResult<T> v1Result = resultMap.get(v1);
        VertexResult<T> v2Result = resultMap.get(v2);

        BigDecimal v1ToV2Weight = v1.getWeightTo(v2);
        BigDecimal weightToV1 = v1Result.getSumOfWeights(); // weight from start vertex to v1
        BigDecimal weightToV2 = v2Result.getSumOfWeights(); // weight from start vertex to v2
        BigDecimal potentialNewPathWeight = v1ToV2Weight.add(weightToV1);

        if (weightToV2 == null || weightToV2.compareTo(potentialNewPathWeight) > 0) {
            // null value of weightToV2 means that potentialNewPathWeight is guaranteed to be smaller than weightToV2
            // null value of getSumOfWeights is used to mark INFINITY weight value

            v2Result.setSumOfWeights(potentialNewPathWeight);
            v2Result.setPreviousVertex(v1);

            // update the priority of v2 after updating its sumOfWeights value
            // the simplest way to update priority in PriorityQueue is to remove an element and put it again
            resultMap.remove(v2);
            resultMap.put(v2, v2Result);
        }
    }

    public Map<Vertex<T>, VertexResult<T>> solveStartingFrom(Vertex<T> v) throws IllegalArgumentException {
        // Algorithm ref: https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm

        // Clear map of any info from previous method calls
        resultMap.clear();
        visitedVertexes.clear();

        if (!graph.getVertexes().contains(v)) {
            throw new IllegalArgumentException("Graph does not contain the vertex given as an argument");
        }

        workQueue.add(v);
        putVertexInResultMapIfNotContains(v, true);

        while (!workQueue.isEmpty()) {
            Vertex<T> minWeightVertex = workQueue.remove();
            visitedVertexes.add(minWeightVertex);
            putVertexInResultMapIfNotContains(minWeightVertex, false);

            for (Edge<T> e : minWeightVertex.getEdges()) {
                relax(e.getSource(), e.getDestination());

                if (!visitedVertexes.contains(e.getDestination())) {
                    workQueue.add(e.getDestination());
                }
            }
        }

        return resultMap;
    }
}
