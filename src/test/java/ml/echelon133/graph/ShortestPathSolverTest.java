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

    @Test
    public void intGraphShortestPathTest() {
        Graph<Integer> intGraph = TestGraphStore.getIntegerTestGraph();
        ShortestPathSolver<Integer> sps = new ShortestPathSolver<>(intGraph);

        Vertex<Integer> startVertex = intGraph
                .getVertexes().stream().filter(v -> v.getName().equals("intVertex1")).findFirst().get();

        Map<Vertex<Integer>, VertexResult<Integer>> resultMap = sps.solveStartingFrom(startVertex);

        for (Map.Entry<Vertex<Integer>, VertexResult<Integer>> entry : resultMap.entrySet()) {

            Vertex<Integer> testedVertex = entry.getKey();
            VertexResult<Integer> testedVertexResult = entry.getValue();

            // Expected values are written in a comment of TestGraphStore.getIntegerTestGraph() method
            switch (testedVertex.getName()) {
                case "intVertex1":
                    assertNull(testedVertexResult.getPreviousVertex());
                    assertEquals(new BigDecimal("0"), testedVertexResult.getSumOfWeights());
                    break;
                case "intVertex2":
                    assertEquals("intVertex5", testedVertexResult.getPreviousVertex().getName());
                    assertEquals(new BigDecimal("2147485677"), testedVertexResult.getSumOfWeights());
                    break;
                case "intVertex3":
                    assertEquals("intVertex1", testedVertexResult.getPreviousVertex().getName());
                    assertEquals(new BigDecimal("1000"), testedVertexResult.getSumOfWeights());
                    break;
                case "intVertex4":
                    assertEquals("intVertex3", testedVertexResult.getPreviousVertex().getName());
                    assertEquals(new BigDecimal("1200"), testedVertexResult.getSumOfWeights());
                    break;
                case "intVertex5":
                    assertEquals("intVertex6", testedVertexResult.getPreviousVertex().getName());
                    assertEquals(new BigDecimal("2147485647"), testedVertexResult.getSumOfWeights());
                    break;
                case "intVertex6":
                    assertEquals("intVertex3", testedVertexResult.getPreviousVertex().getName());
                    assertEquals(new BigDecimal("2000"), testedVertexResult.getSumOfWeights());
                    break;
                default:
                    fail("Unexpected vertex in the tested graph");
                    break;
            }
        }
    }

    @Test
    public void longGraphShortestPathTest() {
        Graph<Long> longGraph = TestGraphStore.getLongTestGraph();
        ShortestPathSolver<Long> sps = new ShortestPathSolver<>(longGraph);

        Vertex<Long> startVertex = longGraph
                .getVertexes().stream().filter(v -> v.getName().equals("longVertex1")).findFirst().get();

        Map<Vertex<Long>, VertexResult<Long>> resultMap = sps.solveStartingFrom(startVertex);

        for (Map.Entry<Vertex<Long>, VertexResult<Long>> entry : resultMap.entrySet()) {

            Vertex<Long> testedVertex = entry.getKey();
            VertexResult<Long> testedVertexResult = entry.getValue();

            // Expected values are written in a comment of TestGraphStore.getLongTestGraph() method
            switch (testedVertex.getName()) {
                case "longVertex1":
                    assertNull(testedVertexResult.getPreviousVertex());
                    assertEquals(new BigDecimal("0"), testedVertexResult.getSumOfWeights());
                    break;
                case "longVertex2":
                    assertEquals("longVertex1", testedVertexResult.getPreviousVertex().getName());
                    assertEquals(new BigDecimal("9223372036854775807"), testedVertexResult.getSumOfWeights());
                    break;
                case "longVertex3":
                    assertEquals("longVertex2", testedVertexResult.getPreviousVertex().getName());
                    assertEquals(new BigDecimal("18446744073709551614"), testedVertexResult.getSumOfWeights());
                    break;
                case "longVertex4":
                    assertEquals("longVertex2", testedVertexResult.getPreviousVertex().getName());
                    assertEquals(new BigDecimal("9223372036854776110"), testedVertexResult.getSumOfWeights());
                    break;
                case "longVertex5":
                    assertEquals("longVertex4", testedVertexResult.getPreviousVertex().getName());
                    assertEquals(new BigDecimal("9223372036854781110"), testedVertexResult.getSumOfWeights());
                    break;
                case "longVertex6":
                    assertEquals("longVertex4", testedVertexResult.getPreviousVertex().getName());
                    assertEquals(new BigDecimal("9223372036854776410"), testedVertexResult.getSumOfWeights());
                    break;
                default:
                    fail("Unexpected vertex in the tested graph");
                    break;
            }
        }
    }
}
