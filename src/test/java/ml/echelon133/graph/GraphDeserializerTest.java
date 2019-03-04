package ml.echelon133.graph;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import ml.echelon133.graph.json.GraphDeserializer;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class GraphDeserializerTest {

    private static JavaType graphBigDecimalType;
    private static ObjectMapper mapper;

    @BeforeAll
    public static void setup() {
        SimpleModule module = new SimpleModule();
        mapper = new ObjectMapper();

        JavaType graphType = mapper.constructType(Graph.class);

        // this type is needed globally (all deserialized graphs type is Graph<BigDecimal>)
        graphBigDecimalType = mapper.getTypeFactory().constructParametricType(Graph.class, BigDecimal.class);

        module.addDeserializer(Graph.class, new GraphDeserializer(graphType));

        mapper.registerModule(module);
    }

    @Test
    public void vertexesNodeMissingThrowsMissingNodeException() {
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
    public void edgesNodeMissingThrowsMissingNodeException() {
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
    public void vertexIncorrectTypeThrowsNodeIsNotArrayException() {
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
    public void edgesIncorrectTypeThrowsNodeIsNotArrayException() {
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
}
