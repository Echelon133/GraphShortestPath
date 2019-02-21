package ml.echelon133.graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


public class WeightedGraphTest {

    private Graph<Long> testGraph;

    @BeforeEach
    public void setup() {
        testGraph = new WeightedGraph<>();
    }

    @Test
    public void initialStateIsCorrect() {
        assertEquals(List.of(), testGraph.getEdges());
        assertEquals(List.of(), testGraph.getVertexes());
    }

    @Test
    public void addingAndRemovingVertexesWorks() {
        List<Vertex<Long>> newVertexes;
        List<Vertex<Long>> savedVertexes;

        Vertex<Long> v1 = new Vertex<>("v1");
        Vertex<Long> v2 = new Vertex<>("v2");
        newVertexes = List.of(v1, v2);

        // Add all vertexes to the graph
        newVertexes.forEach(v -> testGraph.addVertex(v));

        savedVertexes = testGraph.getVertexes();

        // Check whether every vertex was added successfully
        assertEquals(newVertexes, savedVertexes);

        // Remove all previously added vertexes
        newVertexes.forEach(v -> testGraph.removeVertex(v));

        savedVertexes = testGraph.getVertexes();

        // Check whether every vertex was removed successfully
        assertEquals(List.of(), savedVertexes);
    }

    @Test
    public void addingVertexWithNameThatAlreadyBelongsToGraphThrowsException() {
        String expectedMsg = "Vertex with that name already belongs to this graph";
        String receivedMsg = "";

        Vertex<Long> v1 = new Vertex<>("test");
        Vertex<Long> v2 = new Vertex<>("test");

        try {
            testGraph.addVertex(v1);
            testGraph.addVertex(v2);
        } catch (IllegalArgumentException ex) {
            receivedMsg = ex.getMessage();
        }

        assertEquals(expectedMsg, receivedMsg);
    }

    @Test
    public void addingAndRemovingEdgesWorks() {
        Vertex<Long> v1 = new Vertex<>("v1");
        Vertex<Long> v2 = new Vertex<>("v2");
        Vertex<Long> v3 = new Vertex<>("v3");

        // Add all test vertexes to the graph
        List.of(v1, v2, v3).forEach(v -> testGraph.addVertex(v));

        // First way to create an edge
        Edge<Long> e1 = new Edge<>(v1, v2, 10L);
        testGraph.addEdge(e1);

        // Second way to create an edge
        testGraph.addEdge(v3, v2, 20L);
        testGraph.addEdge(v2, v3, 30L);

        // testGraph holds references to all edges created
        List<Edge<Long>> savedEdges = testGraph.getEdges();

        // Try to find references to the edges we created without using Edge directly
        Optional<Edge<Long>> e2 = savedEdges.stream().filter(e -> e.getWeight().equals(20L)).findFirst();
        Optional<Edge<Long>> e3 = savedEdges.stream().filter(e -> e.getWeight().equals(30L)).findFirst();

        // We expect exactly 3 edges
        assertEquals(3, savedEdges.size());

        // Check whether testGraph has a reference to every edge we created
        assertTrue(savedEdges.contains(e1));
        assertTrue(e2.isPresent());
        assertTrue(e3.isPresent());

        // Check whether each vertex has references to the edges that start from it
        assertTrue(v1.getEdges().contains(e1)); // edge v1 -> v2
        assertTrue(v2.getEdges().contains(e3.get())); // edge v2 -> v3
        assertTrue(v3.getEdges().contains(e2.get())); // edge v3 -> v2

        // Remove all edges
        testGraph.removeEdge(e1);
        testGraph.removeEdge(e2.get());
        testGraph.removeEdge(e3.get());

        // Check whether testGraph has no references to the edges
        assertEquals(0, testGraph.getEdges().size());

        // Check if references to the edges were removed from each vertex
        assertEquals(0, v1.getEdges().size());
        assertEquals(0, v2.getEdges().size());
        assertEquals(0, v3.getEdges().size());
    }

    @Test
    public void removingVertexRemovesEdgesThatContainThatVertex() {
        Graph<Integer> graph = new WeightedGraph<>();

        Vertex<Integer> v1 = new Vertex<>("v1");
        Vertex<Integer> v2 = new Vertex<>("v2");
        Vertex<Integer> v3 = new Vertex<>("v3");
        Vertex<Integer> v4 = new Vertex<>("v4");
        Vertex<Integer> v5 = new Vertex<>("v5");

        List<Vertex<Integer>> allVertexes = List.of(v1, v2, v3, v4, v5);
        allVertexes.forEach(graph::addVertex);

        Edge<Integer> e1 = new Edge<>(v1, v2, 1);
        Edge<Integer> e2 = new Edge<>(v1, v3, 3);
        Edge<Integer> e3 = new Edge<>(v1, v5, 5);
        Edge<Integer> e4 = new Edge<>(v3, v5, 6);
        Edge<Integer> e5 = new Edge<>(v5, v4, 7);
        Edge<Integer> e6 = new Edge<>(v3, v4, 4);
        Edge<Integer> e7 = new Edge<>(v2, v4, 2);

        List<Edge<Integer>> allEdges = List.of(e1, e2, e3, e4, e5, e6, e7);
        allEdges.forEach(graph::addEdge);

        graph.removeVertex(v3);

        // v3 is removed
        assertFalse(graph.getVertexes().contains(v3));

        // removing v3 should also remove 3 edges from the graph (7 edges before, 4 edges after)
        assertEquals(4, graph.getEdges().size());

        // e2, e4, e6 should no longer be in the graph (because they use a vertex that no longer belongs to the graph)
        assertFalse(graph.getEdges().contains(e2));
        assertFalse(graph.getEdges().contains(e4));
        assertFalse(graph.getEdges().contains(e6));
    }
}
