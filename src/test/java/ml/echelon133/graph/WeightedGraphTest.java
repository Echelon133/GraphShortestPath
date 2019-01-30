package ml.echelon133.graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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
}
