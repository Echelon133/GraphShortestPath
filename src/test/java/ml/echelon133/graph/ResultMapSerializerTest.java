package ml.echelon133.graph;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import ml.echelon133.graph.json.ResultMapSerializer;
import ml.echelon133.graph.json.VertexResultSerializer;
import ml.echelon133.graph.json.VertexSerializer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ResultMapSerializerTest {

    private static ObjectMapper mapper;

    @BeforeAll
    public static void setup() {
        SimpleModule simpleModule = new SimpleModule();
        mapper = new ObjectMapper();

        JavaType vertexType = mapper.constructType(Vertex.class);
        JavaType vertexResultType = mapper.constructType(VertexResult.class);
        JavaType resultMapType = mapper.getTypeFactory().constructMapType(Map.class, Vertex.class, VertexResult.class);

        simpleModule.addSerializer(new VertexSerializer(vertexType));
        simpleModule.addSerializer(new VertexResultSerializer(vertexResultType));
        simpleModule.addSerializer(new ResultMapSerializer(resultMapType));
        mapper.registerModule(simpleModule);
    }

    @Test
    public void serializeByteGraphResultMap() throws Exception {
        Graph<Byte> byteGraph = TestGraphStore.getByteTestGraph();

        Vertex<Byte> startVertex = byteGraph
                .getVertexes().stream().filter(v -> v.getName().equals("byteVertex1")).findFirst().get();

        ShortestPathSolver<Byte> sps = new ShortestPathSolver<>(byteGraph);
        Map<Vertex<Byte>, VertexResult<Byte>> resultMap = sps.solveStartingFrom(startVertex);

        String serializedResultMap = mapper.writeValueAsString(resultMap);

        JsonNode mainNode = mapper.readTree(serializedResultMap);
        JsonNode results = mainNode.get("results");

        // Compare each VertexResult's values with serialized values
        for (Map.Entry<Vertex<Byte>, VertexResult<Byte>> v : resultMap.entrySet()) {
            VertexResult<Byte> vResult = v.getValue();
            JsonNode vertexObj = results.findValue(v.getKey().getName());

            JsonNode nodePreviousVertex = vertexObj.get("previousVertex");
            JsonNode nodeSumOfWeights = vertexObj.get("sumOfWeights");
            JsonNode nodePathToVertex = vertexObj.get("pathToVertex");

            // Check previousVertex name
            try {
                assertEquals(vResult.getPreviousVertex().getName(), nodePreviousVertex.asText());
            } catch (NullPointerException ex) {
                // if vResult.getPreviousVertex() is null, then check whether serialized field is also null
                assertTrue(nodePreviousVertex.isNull());
            }

            // Check sumOfWeights value
            assertEquals(vResult.getSumOfWeights(), nodeSumOfWeights.decimalValue());

            // Check pathToVertex elements (their order and values)
            LinkedList<Vertex<Byte>> vPath = vResult.getPathToVertex();
            // get elements by their indexes to make sure that the order remains the same after serialization
            for (int i = 0; i < vPath.size(); i++) {
                String expectedVertexName = vPath.get(i).getName();
                String serializedVertexName = nodePathToVertex.get(i).asText();

                assertEquals(expectedVertexName, serializedVertexName);
            }
        }
    }

    @Test
    public void serializeShortGraphResultMap() throws Exception {
        Graph<Short> shortGraph = TestGraphStore.getShortTestGraph();

        Vertex<Short> startVertex = shortGraph
                .getVertexes().stream().filter(v -> v.getName().equals("shortVertex1")).findFirst().get();

        ShortestPathSolver<Short> sps = new ShortestPathSolver<>(shortGraph);
        Map<Vertex<Short>, VertexResult<Short>> resultMap = sps.solveStartingFrom(startVertex);

        String serializedResultMap = mapper.writeValueAsString(resultMap);

        JsonNode mainNode = mapper.readTree(serializedResultMap);
        JsonNode results = mainNode.get("results");

        // Compare each VertexResult's values with serialized values
        for (Map.Entry<Vertex<Short>, VertexResult<Short>> v : resultMap.entrySet()) {
            VertexResult<Short> vResult = v.getValue();
            JsonNode vertexObj = results.findValue(v.getKey().getName());

            JsonNode nodePreviousVertex = vertexObj.get("previousVertex");
            JsonNode nodeSumOfWeights = vertexObj.get("sumOfWeights");
            JsonNode nodePathToVertex = vertexObj.get("pathToVertex");

            // Check previousVertex name
            try {
                assertEquals(vResult.getPreviousVertex().getName(), nodePreviousVertex.asText());
            } catch (NullPointerException ex) {
                // if vResult.getPreviousVertex() is null, then check whether serialized field is also null
                assertTrue(nodePreviousVertex.isNull());
            }

            // Check sumOfWeights value
            assertEquals(vResult.getSumOfWeights(), nodeSumOfWeights.decimalValue());

            // Check pathToVertex elements (their order and values)
            LinkedList<Vertex<Short>> vPath = vResult.getPathToVertex();
            // get elements by their indexes to make sure that the order remains the same after serialization
            for (int i = 0; i < vPath.size(); i++) {
                String expectedVertexName = vPath.get(i).getName();
                String serializedVertexName = nodePathToVertex.get(i).asText();

                assertEquals(expectedVertexName, serializedVertexName);
            }
        }
    }

    @Test
    public void serializeIntegerGraphResultMap() throws Exception {
        Graph<Integer> intGraph = TestGraphStore.getIntegerTestGraph();

        Vertex<Integer> startVertex = intGraph
                .getVertexes().stream().filter(v -> v.getName().equals("intVertex1")).findFirst().get();

        ShortestPathSolver<Integer> sps = new ShortestPathSolver<>(intGraph);
        Map<Vertex<Integer>, VertexResult<Integer>> resultMap = sps.solveStartingFrom(startVertex);

        String serializedResultMap = mapper.writeValueAsString(resultMap);

        JsonNode mainNode = mapper.readTree(serializedResultMap);
        JsonNode results = mainNode.get("results");

        // Compare each VertexResult's values with serialized values
        for (Map.Entry<Vertex<Integer>, VertexResult<Integer>> v : resultMap.entrySet()) {
            VertexResult<Integer> vResult = v.getValue();
            JsonNode vertexObj = results.findValue(v.getKey().getName());

            JsonNode nodePreviousVertex = vertexObj.get("previousVertex");
            JsonNode nodeSumOfWeights = vertexObj.get("sumOfWeights");
            JsonNode nodePathToVertex = vertexObj.get("pathToVertex");

            // Check previousVertex name
            try {
                assertEquals(vResult.getPreviousVertex().getName(), nodePreviousVertex.asText());
            } catch (NullPointerException ex) {
                // if vResult.getPreviousVertex() is null, then check whether serialized field is also null
                assertTrue(nodePreviousVertex.isNull());
            }

            // Check sumOfWeights value
            assertEquals(vResult.getSumOfWeights(), nodeSumOfWeights.decimalValue());

            // Check pathToVertex elements (their order and values)
            LinkedList<Vertex<Integer>> vPath = vResult.getPathToVertex();
            // get elements by their indexes to make sure that the order remains the same after serialization
            for (int i = 0; i < vPath.size(); i++) {
                String expectedVertexName = vPath.get(i).getName();
                String serializedVertexName = nodePathToVertex.get(i).asText();

                assertEquals(expectedVertexName, serializedVertexName);
            }
        }
    }

    @Test
    public void serializeLongGraphResultMap() throws Exception {
        Graph<Long> longGraph = TestGraphStore.getLongTestGraph();

        Vertex<Long> startVertex = longGraph
                .getVertexes().stream().filter(v -> v.getName().equals("longVertex1")).findFirst().get();

        ShortestPathSolver<Long> sps = new ShortestPathSolver<>(longGraph);
        Map<Vertex<Long>, VertexResult<Long>> resultMap = sps.solveStartingFrom(startVertex);

        String serializedResultMap = mapper.writeValueAsString(resultMap);

        JsonNode mainNode = mapper.readTree(serializedResultMap);
        JsonNode results = mainNode.get("results");

        // Compare each VertexResult's values with serialized values
        for (Map.Entry<Vertex<Long>, VertexResult<Long>> v : resultMap.entrySet()) {
            VertexResult<Long> vResult = v.getValue();
            JsonNode vertexObj = results.findValue(v.getKey().getName());

            JsonNode nodePreviousVertex = vertexObj.get("previousVertex");
            JsonNode nodeSumOfWeights = vertexObj.get("sumOfWeights");
            JsonNode nodePathToVertex = vertexObj.get("pathToVertex");

            // Check previousVertex name
            try {
                assertEquals(vResult.getPreviousVertex().getName(), nodePreviousVertex.asText());
            } catch (NullPointerException ex) {
                // if vResult.getPreviousVertex() is null, then check whether serialized field is also null
                assertTrue(nodePreviousVertex.isNull());
            }

            // Check sumOfWeights value
            assertEquals(vResult.getSumOfWeights(), nodeSumOfWeights.decimalValue());

            // Check pathToVertex elements (their order and values)
            LinkedList<Vertex<Long>> vPath = vResult.getPathToVertex();
            // get elements by their indexes to make sure that the order remains the same after serialization
            for (int i = 0; i < vPath.size(); i++) {
                String expectedVertexName = vPath.get(i).getName();
                String serializedVertexName = nodePathToVertex.get(i).asText();

                assertEquals(expectedVertexName, serializedVertexName);
            }
        }
    }

    @Test
    public void serializeFloatGraphResultMap() throws Exception {
        Graph<Float> floatGraph = TestGraphStore.getFloatTestGraph();

        Vertex<Float> startVertex = floatGraph
                .getVertexes().stream().filter(v -> v.getName().equals("floatVertex1")).findFirst().get();

        ShortestPathSolver<Float> sps = new ShortestPathSolver<>(floatGraph);
        Map<Vertex<Float>, VertexResult<Float>> resultMap = sps.solveStartingFrom(startVertex);

        String serializedResultMap = mapper.writeValueAsString(resultMap);

        JsonNode mainNode = mapper.readTree(serializedResultMap);
        JsonNode results = mainNode.get("results");

        // Compare each VertexResult's values with serialized values
        for (Map.Entry<Vertex<Float>, VertexResult<Float>> v : resultMap.entrySet()) {
            VertexResult<Float> vResult = v.getValue();
            JsonNode vertexObj = results.findValue(v.getKey().getName());

            JsonNode nodePreviousVertex = vertexObj.get("previousVertex");
            JsonNode nodeSumOfWeights = vertexObj.get("sumOfWeights");
            JsonNode nodePathToVertex = vertexObj.get("pathToVertex");

            // Check previousVertex name
            try {
                assertEquals(vResult.getPreviousVertex().getName(), nodePreviousVertex.asText());
            } catch (NullPointerException ex) {
                // if vResult.getPreviousVertex() is null, then check whether serialized field is also null
                assertTrue(nodePreviousVertex.isNull());
            }

            // Check sumOfWeights value (compare as floats, because of the precision loss)
            assertEquals(vResult.getSumOfWeights().floatValue(), nodeSumOfWeights.floatValue());

            // Check pathToVertex elements (their order and values)
            LinkedList<Vertex<Float>> vPath = vResult.getPathToVertex();
            // get elements by their indexes to make sure that the order remains the same after serialization
            for (int i = 0; i < vPath.size(); i++) {
                String expectedVertexName = vPath.get(i).getName();
                String serializedVertexName = nodePathToVertex.get(i).asText();

                assertEquals(expectedVertexName, serializedVertexName);
            }
        }
    }
}
