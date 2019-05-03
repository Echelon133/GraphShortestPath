package ml.echelon133.graph;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import ml.echelon133.graph.json.GraphDeserializer;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;


public class GraphDeserializerTest {

    private static JavaType graphBigDecimalType;
    private static ObjectMapper mapper;
    private static Integer maxEdgesCount = 3;

    @BeforeAll
    public static void setup() {
        SimpleModule module = new SimpleModule();
        mapper = new ObjectMapper();

        JavaType graphType = mapper.constructType(Graph.class);

        // this type is needed globally (all deserialized graphs type is Graph<BigDecimal>)
        graphBigDecimalType = mapper.getTypeFactory().constructParametricType(Graph.class, BigDecimal.class);

        // use the GraphDeserializer constructor that takes max accepted number of edges in a graph
        module.addDeserializer(Graph.class, new GraphDeserializer(graphType, maxEdgesCount));

        mapper.registerModule(module);
    }

    @Test
    public void vertexesNodeMissingCausesMissingNodeException() {
        String expectedMessage = "Missing 'vertexes' JSON node.";
        String receivedMessage = "";

        String json = "{\"edges\":[]}";

        try {
            Graph<BigDecimal> graph = mapper.readValue(json, graphBigDecimalType);
        } catch (IOException ex) {
            receivedMessage = ex.getMessage();
        }

        assertEquals(expectedMessage, receivedMessage);
    }

    @Test
    public void edgesNodeMissingCausesMissingNodeException() {
        String expectedMessage = "Missing 'edges' JSON node.";
        String receivedMessage = "";

        String json = "{\"vertexes\":[]}";

        try {
            Graph<BigDecimal> graph = mapper.readValue(json, graphBigDecimalType);
        } catch (IOException ex) {
            receivedMessage = ex.getMessage();
        }

        assertEquals(expectedMessage, receivedMessage);
    }

    @Test
    public void vertexIncorrectTypeCausesNodeIsNotArrayException() {
        String expectedMessage = "'vertexes' is not an array node.";
        String receivedMessage = "";

        String json = "{\"vertexes\": \"array expected here\", \"edges\": []}";

        try {
            Graph<BigDecimal> graph = mapper.readValue(json, graphBigDecimalType);
        } catch (IOException ex) {
            receivedMessage = ex.getMessage();
        }

        assertEquals(expectedMessage, receivedMessage);
    }

    @Test
    public void edgesIncorrectTypeCausesNodeIsNotArrayException() {
        String expectedMessage = "'edges' is not an array node.";
        String receivedMessage = "";

        String json = "{\"vertexes\": [], \"edges\": \"array expected here\"}";

        try {
            Graph<BigDecimal> graph = mapper.readValue(json, graphBigDecimalType);
        } catch (IOException ex) {
            receivedMessage = ex.getMessage();
        }

        assertEquals(expectedMessage, receivedMessage);
    }

    @Test
    public void vertexElementIncorrectTypeCausesNodeIsNotTextualException() {
        String expectedMessage = "Vertex element in 'vertexes' is not textual";
        String receivedMessage = "";

        String json = "{\"vertexes\": [\"v1\", \"v2\", \"v3\", 4], \"edges\": []}";

        try {
            Graph<BigDecimal> graph = mapper.readValue(json, graphBigDecimalType);
        } catch (IOException ex) {
            receivedMessage = ex.getMessage();
        }

        assertEquals(expectedMessage, receivedMessage);
    }

    @Test
    public void edgeElementIncorrectTypeCausesNodeIsNotObjectException() {
        String expectedMessage = "Edge element in 'edges' is not an object";
        String receivedMessage = "";

        String json1 = "{\"vertexes\": [\"v1\", \"v2\"], \"edges\": [1]}";
        String json2 = "{\"vertexes\": [\"v1\", \"v2\"], \"edges\": [\"test\"]}";

        try {
            Graph<BigDecimal> graph = mapper.readValue(json1, graphBigDecimalType);
        } catch (IOException ex) {
            receivedMessage = ex.getMessage();
        }

        assertEquals(expectedMessage, receivedMessage);

        receivedMessage = "";

        try {
            Graph<BigDecimal> graph = mapper.readValue(json2, graphBigDecimalType);
        } catch (IOException ex) {
            receivedMessage = ex.getMessage();
        }

        assertEquals(expectedMessage, receivedMessage);
    }

    @Test
    public void edgeObjectNegativeEdgeWeightCausesNegativeEdgeWeightException() {
        String expectedMessage = "Edge weight cannot be negative";
        String receivedMessage = "";

        String json = "{\"vertexes\": [\"v1\", \"v2\"], \"edges\": [{\"source\" : \"v1\", \"destination\" : \"v2\", \"weight\" : -20}]}";

        try {
            Graph<BigDecimal> graph = mapper.readValue(json, graphBigDecimalType);
        } catch (IOException ex) {
            receivedMessage = ex.getMessage();
        }

        assertEquals(expectedMessage, receivedMessage);
    }

    @Test
    public void edgeObjectMissingSourceVertexCausesMissingNodeException() {
        String expectedMessage = "Edge object does not contain 'source' field";
        String receivedMessage = "";

        String json = "{\"vertexes\": [\"v1\", \"v2\"], \"edges\": [{\"destination\" : \"v2\", \"weight\" : 20}]}";

        try {
            Graph<BigDecimal> graph = mapper.readValue(json, graphBigDecimalType);
        } catch (IOException ex) {
            receivedMessage = ex.getMessage();
        }

        assertEquals(expectedMessage, receivedMessage);
    }

    @Test
    public void edgeObjectMissingDestinationVertexCausesMissingNodeException() {
        String expectedMessage = "Edge object does not contain 'destination' field";
        String receivedMessage = "";

        String json = "{\"vertexes\": [\"v1\", \"v2\"], \"edges\": [{\"source\" : \"v1\", \"weight\" : 20}]}";

        try {
            Graph<BigDecimal> graph = mapper.readValue(json, graphBigDecimalType);
        } catch (IOException ex) {
            receivedMessage = ex.getMessage();
        }

        assertEquals(expectedMessage, receivedMessage);
    }

    @Test
    public void edgeObjectMissingWeightCausesMissingNodeException() {
        String expectedMessage = "Edge object does not contain 'weight' field";
        String receivedMessage = "";

        String json = "{\"vertexes\": [\"v1\", \"v2\"], \"edges\": [{\"source\" : \"v1\", \"destination\" : \"v2\"}]}";

        try {
            Graph<BigDecimal> graph = mapper.readValue(json, graphBigDecimalType);
        } catch (IOException ex) {
            receivedMessage = ex.getMessage();
        }

        assertEquals(expectedMessage, receivedMessage);
    }

    @Test
    public void edgeObjectSourceVertexNotTextualCausesNodeIsNotTextualException() {
        String expectedMessage = "Source vertex in Edge is not textual";
        String receivedMessage = "";

        String json = "{\"vertexes\": [\"v1\", \"v2\"], \"edges\": [{\"source\" : 1010, \"destination\" : \"v2\", \"weight\" : 20}]}";

        try {
            Graph<BigDecimal> graph = mapper.readValue(json, graphBigDecimalType);
        } catch (IOException ex) {
            receivedMessage = ex.getMessage();
        }

        assertEquals(expectedMessage, receivedMessage);
    }

    @Test
    public void edgeObjectDestinationVertexNotTextualCausesNodeIsNotTextualException() {
        String expectedMessage = "Destination vertex in Edge is not textual";
        String receivedMessage = "";

        String json = "{\"vertexes\": [\"v1\", \"v2\"], \"edges\": [{\"source\" : \"v1\", \"destination\" : 1010, \"weight\" : 20}]}";

        try {
            Graph<BigDecimal> graph = mapper.readValue(json, graphBigDecimalType);
        } catch (IOException ex) {
            receivedMessage = ex.getMessage();
        }

        assertEquals(expectedMessage, receivedMessage);
    }

    @Test
    public void edgeObjectVertexWeightNotNumberCausesNodeIsNotNumberException() {
        String expectedMessage = "Weight cannot be deserialized as BigDecimal";
        String receivedMessage = "";

        String json1 = "{\"vertexes\": [\"v1\", \"v2\"], \"edges\": [{\"source\" : \"v1\", \"destination\" : \"v2\", \"weight\" : \"asdf\"}]}";
        String json2 = "{\"vertexes\": [\"v1\", \"v2\"], \"edges\": [{\"source\" : \"v1\", \"destination\" : \"v2\", \"weight\" : {}}]}";

        try {
            Graph<BigDecimal> graph = mapper.readValue(json1, graphBigDecimalType);
        } catch (IOException ex) {
            receivedMessage = ex.getMessage();
        }

        assertEquals(expectedMessage, receivedMessage);

        receivedMessage = "";

        try {
            Graph<BigDecimal> graph = mapper.readValue(json2, graphBigDecimalType);
        } catch (IOException ex) {
            receivedMessage = ex.getMessage();
        }

        assertEquals(expectedMessage, receivedMessage);
    }

    @Test
    public void edgeObjectReferringToNotExistingVertexCausesEdgeNullVertexException() {
        String receivedMessage = "";

        String edgeContent = "{\"source\":\"v1\",\"destination\":\"v3\",\"weight\":20}";
        String json = "{\"vertexes\": [\"v1\", \"v2\"], \"edges\": [" + edgeContent + "]}";

        String expectedMessage = String.format("Edge '%s' references a vertex that is not present in 'vertexes'", edgeContent);

        try {
            Graph<BigDecimal> graph = mapper.readValue(json, graphBigDecimalType);
        } catch (IOException ex) {
            receivedMessage = ex.getMessage();
        }

        assertEquals(expectedMessage, receivedMessage);
    }

    @Test
    public void graphContainingNumberOfEdgesEqualToMaxEdgesCountDeserializesCorrectly() {
        String edgeContent = "{\"source\":\"v1\",\"destination\":\"v2\",\"weight\":20}," +
                             "{\"source\":\"v2\",\"destination\":\"v1\",\"weight\":20}," +
                             "{\"source\":\"v1\",\"destination\":\"v2\",\"weight\":20}";
        String json = "{\"vertexes\": [\"v1\", \"v2\"], \"edges\": [" + edgeContent + "]}";

        Graph<BigDecimal> graph = null;
        try {
            graph = mapper.readValue(json, graphBigDecimalType);
        } catch (IOException ex) {
            fail();
        }
        assertNotNull(graph);
    }

    @Test
    public void graphContainingNumberOfEdgesAboveMaxEdgesCountCausesMaxEdgeCountReachedException() {
        String receivedMessage = "";

        String edgeContent = "{\"source\":\"v1\",\"destination\":\"v2\",\"weight\":20}," +
                             "{\"source\":\"v2\",\"destination\":\"v1\",\"weight\":20}," +
                             "{\"source\":\"v1\",\"destination\":\"v2\",\"weight\":20}," +
                             "{\"source\":\"v2\",\"destination\":\"v1\",\"weight\":20}";
        String json = "{\"vertexes\": [\"v1\", \"v2\"], \"edges\": [" + edgeContent + "]}";

        String expectedMessage = String.format("Cannot accept graphs that contain more than %d edges", maxEdgesCount);

        try {
            Graph<BigDecimal> graph = mapper.readValue(json, graphBigDecimalType);
        } catch (IOException ex) {
            receivedMessage = ex.getMessage();
        }

        assertEquals(expectedMessage, receivedMessage);
    }
}
