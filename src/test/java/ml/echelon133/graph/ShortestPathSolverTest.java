package ml.echelon133.graph;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ShortestPathSolverTest {

    @Test
    public void solveStartingFromThrowsExceptionWhenVertexDoesNotBelongToGraph() {
        String expectedMsg = "Graph does not contain the vertex given as an argument";
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
                    assertEquals(0, testedVertexResult.getPathToVertex().size());
                    break;
                case "byteVertex2":
                    assertEquals("byteVertex5", testedVertexResult.getPreviousVertex().getName());
                    assertEquals(new BigDecimal("14"), testedVertexResult.getSumOfWeights());
                    assertEquals(2, testedVertexResult.getPathToVertex().size());
                    assertEquals(testedVertexResult.getPreviousVertex(), testedVertexResult.getPathToVertex().getLast());
                    break;
                case "byteVertex3":
                    assertEquals("byteVertex5", testedVertexResult.getPreviousVertex().getName());
                    assertEquals(new BigDecimal("11"), testedVertexResult.getSumOfWeights());
                    assertEquals(2, testedVertexResult.getPathToVertex().size());
                    assertEquals(testedVertexResult.getPreviousVertex(), testedVertexResult.getPathToVertex().getLast());
                    break;
                case "byteVertex4":
                    assertEquals("byteVertex2", testedVertexResult.getPreviousVertex().getName());
                    assertEquals(new BigDecimal("47"), testedVertexResult.getSumOfWeights());
                    assertEquals(3, testedVertexResult.getPathToVertex().size());
                    assertEquals(testedVertexResult.getPreviousVertex(), testedVertexResult.getPathToVertex().getLast());
                    break;
                case "byteVertex5":
                    assertEquals("byteVertex1", testedVertexResult.getPreviousVertex().getName());
                    assertEquals(new BigDecimal("5"), testedVertexResult.getSumOfWeights());
                    assertEquals(1, testedVertexResult.getPathToVertex().size());
                    assertEquals(testedVertexResult.getPreviousVertex(), testedVertexResult.getPathToVertex().getLast());
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
                    assertEquals(0, testedVertexResult.getPathToVertex().size());
                    break;
                case "shortVertex2":
                    assertEquals("shortVertex3", testedVertexResult.getPreviousVertex().getName());
                    assertEquals(new BigDecimal("9"), testedVertexResult.getSumOfWeights());
                    assertEquals(2, testedVertexResult.getPathToVertex().size());
                    assertEquals(testedVertexResult.getPreviousVertex(), testedVertexResult.getPathToVertex().getLast());
                    break;
                case "shortVertex3":
                    assertEquals("shortVertex1", testedVertexResult.getPreviousVertex().getName());
                    assertEquals(new BigDecimal("5"), testedVertexResult.getSumOfWeights());
                    assertEquals(1, testedVertexResult.getPathToVertex().size());
                    assertEquals(testedVertexResult.getPreviousVertex(), testedVertexResult.getPathToVertex().getLast());
                    break;
                case "shortVertex4":
                    assertEquals("shortVertex3", testedVertexResult.getPreviousVertex().getName());
                    assertEquals(new BigDecimal("32772"), testedVertexResult.getSumOfWeights());
                    assertEquals(2, testedVertexResult.getPathToVertex().size());
                    assertEquals(testedVertexResult.getPreviousVertex(), testedVertexResult.getPathToVertex().getLast());
                    break;
                case "shortVertex5":
                    assertEquals("shortVertex1", testedVertexResult.getPreviousVertex().getName());
                    assertEquals(new BigDecimal("32767"), testedVertexResult.getSumOfWeights());
                    assertEquals(1, testedVertexResult.getPathToVertex().size());
                    assertEquals(testedVertexResult.getPreviousVertex(), testedVertexResult.getPathToVertex().getLast());
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

    @Test
    public void floatGraphShortestPathTest() {
        Graph<Float> floatGraph = TestGraphStore.getFloatTestGraph();
        ShortestPathSolver<Float> sps = new ShortestPathSolver<>(floatGraph);

        Vertex<Float> startVertex = floatGraph
                .getVertexes().stream().filter(v -> v.getName().equals("floatVertex1")).findFirst().get();

        Map<Vertex<Float>, VertexResult<Float>> resultMap = sps.solveStartingFrom(startVertex);

        for (Map.Entry<Vertex<Float>, VertexResult<Float>> entry : resultMap.entrySet()) {

            Vertex<Float> testedVertex = entry.getKey();
            VertexResult<Float> testedVertexResult = entry.getValue();

            // needed for comparing floats with delta
            float vertexSumOfWeights = testedVertexResult.getSumOfWeights().floatValue();

            // Expected values are written in a comment of TestGraphStore.getFloatTestGraph() method
            switch (testedVertex.getName()) {
                case "floatVertex1":
                    assertNull(testedVertexResult.getPreviousVertex());
                    assertEquals(new BigDecimal("0").floatValue(), vertexSumOfWeights, 0.00001f);
                    break;
                case "floatVertex2":
                    assertEquals("floatVertex1", testedVertexResult.getPreviousVertex().getName());
                    assertEquals(new BigDecimal("13.856").floatValue(), vertexSumOfWeights, 0.00001f);
                    break;
                case "floatVertex3":
                    assertEquals("floatVertex1", testedVertexResult.getPreviousVertex().getName());
                    assertEquals(new BigDecimal("13.855").floatValue(), vertexSumOfWeights, 0.00001f);
                    break;
                case "floatVertex4":
                    assertEquals("floatVertex2", testedVertexResult.getPreviousVertex().getName());
                    assertEquals(new BigDecimal("7820.389186").floatValue(), vertexSumOfWeights, 0.00001f);
                    break;
                case "floatVertex5":
                    assertEquals("floatVertex3", testedVertexResult.getPreviousVertex().getName());
                    assertEquals(new BigDecimal("13.856").floatValue(), vertexSumOfWeights, 0.00001f);
                    break;
                case "floatVertex6":
                    assertEquals("floatVertex3", testedVertexResult.getPreviousVertex().getName());
                    assertEquals(new BigDecimal("14.39179").floatValue(), vertexSumOfWeights, 0.00001f);
                    break;
                default:
                    fail("Unexpected vertex in the tested graph");
                    break;
            }
        }
    }

    @Test
    public void doubleGraphShortestPathTest() {
        Graph<Double> doubleGraph = TestGraphStore.getDoubleTestGraph();
        ShortestPathSolver<Double> sps = new ShortestPathSolver<>(doubleGraph);

        Vertex<Double> startVertex = doubleGraph
                .getVertexes().stream().filter(v -> v.getName().equals("doubleVertex1")).findFirst().get();

        Map<Vertex<Double>, VertexResult<Double>> resultMap = sps.solveStartingFrom(startVertex);

        for (Map.Entry<Vertex<Double>, VertexResult<Double>> entry : resultMap.entrySet()) {

            Vertex<Double> testedVertex = entry.getKey();
            VertexResult<Double> testedVertexResult = entry.getValue();

            // needed for comparing doubles with delta
            double vertexSumOfWeights = testedVertexResult.getSumOfWeights().doubleValue();

            // Expected values are written in a comment of TestGraphStore.getDoubleTestGraph() method
            switch (testedVertex.getName()) {
                case "doubleVertex1":
                    assertNull(testedVertexResult.getPreviousVertex());
                    assertEquals(new BigDecimal("0").doubleValue(), vertexSumOfWeights, 0.00001);
                    break;
                case "doubleVertex2":
                    assertEquals("doubleVertex4", testedVertexResult.getPreviousVertex().getName());
                    assertEquals(new BigDecimal("1008.667").doubleValue(), vertexSumOfWeights, 0.00001);
                    break;
                case "doubleVertex3":
                    assertEquals("doubleVertex1", testedVertexResult.getPreviousVertex().getName());
                    assertEquals(new BigDecimal("1.8776").doubleValue(), vertexSumOfWeights, 0.00001);
                    break;
                case "doubleVertex4":
                    assertEquals("doubleVertex5", testedVertexResult.getPreviousVertex().getName());
                    assertEquals(new BigDecimal("1008.666").doubleValue(), vertexSumOfWeights, 0.00001);
                    break;
                case "doubleVertex5":
                    assertEquals("doubleVertex1", testedVertexResult.getPreviousVertex().getName());
                    assertEquals(new BigDecimal("7.1").doubleValue(), vertexSumOfWeights, 0.00001);
                    break;
                default:
                    fail("Unexpected vertex in the tested graph");
                    break;
            }
        }
    }

    @Test
    public void bigIntegerGraphShortestPathTest() {
        Graph<BigInteger> bigIntGraph = TestGraphStore.getBigIntegerTestGraph();
        ShortestPathSolver<BigInteger> sps = new ShortestPathSolver<>(bigIntGraph);

        Vertex<BigInteger> startVertex = bigIntGraph
                .getVertexes().stream().filter(v -> v.getName().equals("bigIntVertex1")).findFirst().get();

        Map<Vertex<BigInteger>, VertexResult<BigInteger>> resultMap = sps.solveStartingFrom(startVertex);

        for (Map.Entry<Vertex<BigInteger>, VertexResult<BigInteger>> entry : resultMap.entrySet()) {

            Vertex<BigInteger> testedVertex = entry.getKey();
            VertexResult<BigInteger> testedVertexResult = entry.getValue();

            // Expected values are written in a comment of TestGraphStore.getBigIntegerTestGraph() method
            switch (testedVertex.getName()) {
                case "bigIntVertex1":
                    assertNull(testedVertexResult.getPreviousVertex());
                    assertEquals(new BigDecimal("0"), testedVertexResult.getSumOfWeights());
                    break;
                case "bigIntVertex2":
                    assertEquals("bigIntVertex1", testedVertexResult.getPreviousVertex().getName());
                    assertEquals(new BigDecimal("9223372036854775807"), testedVertexResult.getSumOfWeights());
                    break;
                case "bigIntVertex3":
                    assertEquals("bigIntVertex2", testedVertexResult.getPreviousVertex().getName());
                    assertEquals(new BigDecimal("18446744073709551614"), testedVertexResult.getSumOfWeights());
                    break;
                case "bigIntVertex4":
                    assertEquals("bigIntVertex3", testedVertexResult.getPreviousVertex().getName());
                    assertEquals(new BigDecimal("27670116110564327421"), testedVertexResult.getSumOfWeights());
                    break;
                case "bigIntVertex5":
                    assertEquals("bigIntVertex3", testedVertexResult.getPreviousVertex().getName());
                    assertEquals(new BigDecimal("27670116110564327421"), testedVertexResult.getSumOfWeights());
                    break;
                case "bigIntVertex6":
                    assertEquals("bigIntVertex5", testedVertexResult.getPreviousVertex().getName());
                    assertEquals(new BigDecimal("36893488147419103228"), testedVertexResult.getSumOfWeights());
                    break;
                default:
                    fail("Unexpected vertex in the tested graph");
                    break;
            }
        }
    }

    @Test
    public void bigDecimalGraphShortestPathTest() {
        Graph<BigDecimal> bigDecGraph = TestGraphStore.getBigDecimalTestGraph();
        ShortestPathSolver<BigDecimal> sps = new ShortestPathSolver<>(bigDecGraph);

        Vertex<BigDecimal> startVertex = bigDecGraph
                .getVertexes().stream().filter(v -> v.getName().equals("bigDecVertex1")).findFirst().get();

        Map<Vertex<BigDecimal>, VertexResult<BigDecimal>> resultMap = sps.solveStartingFrom(startVertex);

        for (Map.Entry<Vertex<BigDecimal>, VertexResult<BigDecimal>> entry : resultMap.entrySet()) {

            Vertex<BigDecimal> testedVertex = entry.getKey();
            VertexResult<BigDecimal> testedVertexResult = entry.getValue();

            // Expected values are written in a comment of TestGraphStore.getBigDecimalTestGraph() method
            switch (testedVertex.getName()) {
                case "bigDecVertex1":
                    assertNull(testedVertexResult.getPreviousVertex());
                    assertEquals(new BigDecimal("0"), testedVertexResult.getSumOfWeights());
                    break;
                case "bigDecVertex2":
                    assertEquals("bigDecVertex1", testedVertexResult.getPreviousVertex().getName());
                    assertEquals(new BigDecimal("0.000001"), testedVertexResult.getSumOfWeights());
                    break;
                case "bigDecVertex3":
                    assertEquals("bigDecVertex7", testedVertexResult.getPreviousVertex().getName());
                    assertEquals(new BigDecimal("0.00000010001001"), testedVertexResult.getSumOfWeights());
                    break;
                case "bigDecVertex4":
                    assertEquals("bigDecVertex2", testedVertexResult.getPreviousVertex().getName());
                    assertEquals(new BigDecimal("0.000001001"), testedVertexResult.getSumOfWeights());
                    break;
                case "bigDecVertex5":
                    assertEquals("bigDecVertex1", testedVertexResult.getPreviousVertex().getName());
                    assertEquals(new BigDecimal("0.0000001"), testedVertexResult.getSumOfWeights());
                    break;
                case "bigDecVertex6":
                    assertEquals("bigDecVertex3", testedVertexResult.getPreviousVertex().getName());
                    assertEquals(new BigDecimal("0.0000001000100100001"), testedVertexResult.getSumOfWeights());
                    break;
                case "bigDecVertex7":
                    assertEquals("bigDecVertex5", testedVertexResult.getPreviousVertex().getName());
                    assertEquals(new BigDecimal("0.00000010001"), testedVertexResult.getSumOfWeights());
                    break;
                default:
                    fail("Unexpected vertex in the tested graph");
                    break;
            }
        }
    }
}
