package ml.echelon133.graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;


public class VertexTest {

    private Vertex<Integer> testVertex;


    @BeforeEach
    public void setup() {
        testVertex = new Vertex<>("v1");
    }

    @Test
    public void getNameValueAsExpected() {
        assertTrue(testVertex.getName().equals("v1"));
    }

    @Test
    public void getEdgesOfNewVertexIsEmpty() {
        assertTrue(testVertex.getEdges().size() == 0);
    }

    @Test
    public void getWeightToThrowsExceptionWhenNoEdgeBetweenVertexes() {
        Vertex<Integer> newVertex = new Vertex<>("v2");

        String expectedMessage = "There is no edge between v1 and v2 vertexes";
        String receivedMessage = "";

        try {
            testVertex.getWeightTo(newVertex);
        } catch (IllegalArgumentException ex) {
            receivedMessage = ex.getMessage();
        }
        assertEquals(expectedMessage, receivedMessage);
    }

    @Test
    public void getWeightToReturnsLowestWeight() {
        Vertex<Integer> newVertex = new Vertex<>("v2");

        testVertex.addEdge(newVertex, 30);
        testVertex.addEdge(newVertex, 7);
        testVertex.addEdge(newVertex, 50);
        testVertex.addEdge(newVertex, 15);

        BigDecimal weight = testVertex.getWeightTo(newVertex);
        assertEquals(7, weight.intValue());
    }

    @Test
    public void addEdgeDirectlyWorks() {
        Vertex<Integer> destVertex = new Vertex<>("v2");
        Edge<Integer> e1 = new Edge<>(testVertex, destVertex, 30);
        Edge<Integer> e2 = new Edge<>(testVertex, destVertex, 15);

        testVertex.addEdge(e1);
        testVertex.addEdge(e2);

        assertTrue(testVertex.getEdges().contains(e1));
        assertTrue(testVertex.getEdges().contains(e2));
    }

    @Test
    public void removeEdgeWorks() {
        Vertex<Integer> destVertex = new Vertex<>("v2");
        Edge<Integer> e1 = new Edge<>(testVertex, destVertex, 10);

        testVertex.addEdge(e1);
        assertTrue(testVertex.getEdges().contains(e1));
        testVertex.removeEdge(e1);
        assertTrue(testVertex.getEdges().isEmpty());
    }
}
