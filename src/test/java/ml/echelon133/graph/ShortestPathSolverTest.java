package ml.echelon133.graph;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ShortestPathSolverTest {

    @Test
    public void solveStartingFromThrowsExceptionWhenVertexDoesNotBelongToGraph() {
        String expectedMsg = "Graph does not contain vertex given as an argument";
        String receivedMsg = "";

        try {
            Graph<Byte> byteGraph = TestGraphStore.getByteTestGraph();
            Vertex<Byte> otherVertex = new Vertex<>("otherVertex");
            ShortestPathSolver<Byte> sps = new ShortestPathSolver<>(byteGraph);
            sps.solveStartingFrom(otherVertex);
        } catch (IllegalArgumentException ex) {
            receivedMsg = ex.getMessage();
        }

        assertEquals(expectedMsg, receivedMsg);
    }

    @Test
    public void byteGraphShortestPathTest() {
        Graph<Byte> byteGraph = TestGraphStore.getByteTestGraph();
        ShortestPathSolver<Byte> sps = new ShortestPathSolver<>(byteGraph);

        Vertex<Byte> startVertex = byteGraph
                .getVertexes().stream().filter(v -> v.getName().equals("byteVertex1")).findFirst().get();

        Map<Vertex<Byte>, VertexResult<Byte>> resultMap = sps.solveStartingFrom(startVertex);

        for (Map.Entry<Vertex<Byte>, VertexResult<Byte>> entry : resultMap.entrySet()) {

            Vertex<Byte> testedVertex = entry.getKey();
            VertexResult<Byte> testedVertexResult = entry.getValue();

            // Expected values are written in a comment of TestGraphStore.getByteTestGraph() method
            switch (testedVertex.getName()) {
                case "byteVertex1":
                    assertNull(testedVertexResult.getPreviousVertex());
                    assertEquals(new BigDecimal("0"), testedVertexResult.getSumOfWeights());
                    break;
                case "byteVertex2":
                    assertEquals("byteVertex5", testedVertexResult.getPreviousVertex().getName());
                    assertEquals(new BigDecimal("14"), testedVertexResult.getSumOfWeights());
                    break;
                case "byteVertex3":
                    assertEquals("byteVertex5", testedVertexResult.getPreviousVertex().getName());
                    assertEquals(new BigDecimal("11"), testedVertexResult.getSumOfWeights());
                    break;
                case "byteVertex4":
                    assertEquals("byteVertex2", testedVertexResult.getPreviousVertex().getName());
                    assertEquals(new BigDecimal("47"), testedVertexResult.getSumOfWeights());
                    break;
                case "byteVertex5":
                    assertEquals("byteVertex1", testedVertexResult.getPreviousVertex().getName());
                    assertEquals(new BigDecimal("5"), testedVertexResult.getSumOfWeights());
                    break;
                default:
                    // this should never execute
                    fail("Unexpected vertex in the tested graph");
                    break;
            }
        }
    }

    @Test
    public void shortGraphShortestPathTest() {
        Graph<Short> shortGraph = TestGraphStore.getShortTestGraph();
        ShortestPathSolver<Short> sps = new ShortestPathSolver<>(shortGraph);

        Vertex<Short> startVertex = shortGraph
                .getVertexes().stream().filter(v -> v.getName().equals("shortVertex1")).findFirst().get();

        Map<Vertex<Short>, VertexResult<Short>> resultMap = sps.solveStartingFrom(startVertex);

        for (Map.Entry<Vertex<Short>, VertexResult<Short>> entry : resultMap.entrySet()) {

            Vertex<Short> testedVertex = entry.getKey();
            VertexResult<Short> testedVertexResult = entry.getValue();

            // Expected values are written in a comment of TestGraphStore.getShortTestGraph() method
            switch (testedVertex.getName()) {
                case "shortVertex1":
                    assertNull(testedVertexResult.getPreviousVertex());
                    assertEquals(new BigDecimal("0"), testedVertexResult.getSumOfWeights());
                    break;
                case "shortVertex2":
                    assertEquals("shortVertex3", testedVertexResult.getPreviousVertex().getName());
                    assertEquals(new BigDecimal("9"), testedVertexResult.getSumOfWeights());
                    break;
                case "shortVertex3":
                    assertEquals("shortVertex1", testedVertexResult.getPreviousVertex().getName());
                    assertEquals(new BigDecimal("5"), testedVertexResult.getSumOfWeights());
                    break;
                case "shortVertex4":
                    assertEquals("shortVertex3", testedVertexResult.getPreviousVertex().getName());
                    assertEquals(new BigDecimal("32772"), testedVertexResult.getSumOfWeights());
                    break;
                case "shortVertex5":
                    assertEquals("shortVertex1", testedVertexResult.getPreviousVertex().getName());
                    assertEquals(new BigDecimal("32767"), testedVertexResult.getSumOfWeights());
                    break;
                default:
                    fail("Unexpected vertex in the tested graph");
                    break;
            }
        }
    }
}
