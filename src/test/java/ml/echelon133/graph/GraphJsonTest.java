package ml.echelon133.graph;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import ml.echelon133.graph.json.EdgeSerializer;
import ml.echelon133.graph.json.GraphDeserializer;
import ml.echelon133.graph.json.GraphSerializer;
import ml.echelon133.graph.json.VertexSerializer;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class GraphJsonTest {

    private static JavaType graphBigDecimalType;
    private static ObjectMapper mapper;

    @BeforeAll
    public static void setup() {
        SimpleModule module = new SimpleModule();
        mapper = new ObjectMapper();

        JavaType vertexType = mapper.constructType(Vertex.class);
        JavaType edgeType = mapper.constructType(Edge.class);
        JavaType graphType = mapper.constructType(Graph.class);

        // this type is needed globally (all deserialized graphs type is Graph<BigDecimal>)
        graphBigDecimalType = mapper.getTypeFactory().constructParametricType(Graph.class, BigDecimal.class);

        module.addSerializer(new VertexSerializer(vertexType));
        module.addSerializer(new EdgeSerializer(edgeType));
        module.addSerializer(new GraphSerializer(graphType));
        module.addDeserializer(Graph.class, new GraphDeserializer(graphType));

        mapper.registerModule(module);
    }

    @Test
    public void serializeAndDeserializeByteGraphTest() throws Exception {
        Graph<Byte> byteGraph = TestGraphStore.getByteTestGraph();
        String serialized = mapper.writeValueAsString(byteGraph);

        Graph<BigDecimal> deserializedGraph = mapper.readValue(serialized, graphBigDecimalType);

        // both graphs have the same number of vertexes and edges
        assertEquals(byteGraph.getVertexes().size(), deserializedGraph.getVertexes().size());
        assertEquals(byteGraph.getEdges().size(), deserializedGraph.getEdges().size());

        int vertexesSize = byteGraph.getVertexes().size();
        int edgesSize = byteGraph.getEdges().size();

        List<Vertex<Byte>> byteGraphVertexes = byteGraph.getVertexes();
        List<Vertex<BigDecimal>> bDecimalGraphVertexes = deserializedGraph.getVertexes();

        // both graphs have identical vertexes
        for (int i = 0; i < vertexesSize; i++) {
            String byteGraphVertexName = byteGraphVertexes.get(i).getName();
            String deserializedGraphVertexName = bDecimalGraphVertexes.get(i).getName();
            assertEquals(byteGraphVertexName, deserializedGraphVertexName);
        }

        List<Edge<Byte>> byteGraphEdges = byteGraph.getEdges();
        List<Edge<BigDecimal>> deserializedGraphEdges = deserializedGraph.getEdges();

        // both graphs have identical edges
        for (int j = 0; j < edgesSize; j++) {
            Edge<Byte> byteEdge = byteGraphEdges.get(j);
            Edge<BigDecimal> bDecimalEdge = deserializedGraphEdges.get(j);

            String expectedSourceVertexName = byteEdge.getSource().getName();
            String receivedSourceVertexName = bDecimalEdge.getSource().getName();
            assertEquals(expectedSourceVertexName, receivedSourceVertexName);

            String expectedDestinationVertexName = byteEdge.getDestination().getName();
            String receivedDestinationVertexName = bDecimalEdge.getDestination().getName();
            assertEquals(expectedDestinationVertexName, receivedDestinationVertexName);

            assertEquals(byteEdge.getWeightAsBigDecimal(), bDecimalEdge.getWeightAsBigDecimal());
        }
    }

    @Test
    public void serializeAndDeserializeShortGraphTest() throws Exception {
        Graph<Short> shortGraph = TestGraphStore.getShortTestGraph();
        String serialized = mapper.writeValueAsString(shortGraph);

        Graph<BigDecimal> deserializedGraph = mapper.readValue(serialized, graphBigDecimalType);

        // both graphs have the same number of vertexes and edges
        assertEquals(shortGraph.getVertexes().size(), deserializedGraph.getVertexes().size());
        assertEquals(shortGraph.getEdges().size(), deserializedGraph.getEdges().size());

        int vertexesSize = shortGraph.getVertexes().size();
        int edgesSize = shortGraph.getEdges().size();

        List<Vertex<Short>> shortGraphVertexes = shortGraph.getVertexes();
        List<Vertex<BigDecimal>> bDecimalGraphVertexes = deserializedGraph.getVertexes();

        // both graphs have identical vertexes
        for (int i = 0; i < vertexesSize; i++) {
            String shortGraphVertexName = shortGraphVertexes.get(i).getName();
            String deserializedGraphVertexName = bDecimalGraphVertexes.get(i).getName();
            assertEquals(shortGraphVertexName, deserializedGraphVertexName);
        }

        List<Edge<Short>> shortGraphEdges = shortGraph.getEdges();
        List<Edge<BigDecimal>> deserializedGraphEdges = deserializedGraph.getEdges();

        // both graphs have identical edges
        for (int j = 0; j < edgesSize; j++) {
            Edge<Short> shortEdge = shortGraphEdges.get(j);
            Edge<BigDecimal> bDecimalEdge = deserializedGraphEdges.get(j);

            String expectedSourceVertexName = shortEdge.getSource().getName();
            String receivedSourceVertexName = bDecimalEdge.getSource().getName();
            assertEquals(expectedSourceVertexName, receivedSourceVertexName);

            String expectedDestinationVertexName = shortEdge.getDestination().getName();
            String receivedDestinationVertexName = bDecimalEdge.getDestination().getName();
            assertEquals(expectedDestinationVertexName, receivedDestinationVertexName);

            assertEquals(shortEdge.getWeightAsBigDecimal(), bDecimalEdge.getWeightAsBigDecimal());
        }
    }

    @Test
    public void serializeAndDeserializeIntegerGraphTest() throws Exception {
        Graph<Integer> integerGraph = TestGraphStore.getIntegerTestGraph();
        String serialized = mapper.writeValueAsString(integerGraph);

        Graph<BigDecimal> deserializedGraph = mapper.readValue(serialized, graphBigDecimalType);

        // both graphs have the same number of vertexes and edges
        assertEquals(integerGraph.getVertexes().size(), deserializedGraph.getVertexes().size());
        assertEquals(integerGraph.getEdges().size(), deserializedGraph.getEdges().size());

        int vertexesSize = integerGraph.getVertexes().size();
        int edgesSize = integerGraph.getEdges().size();

        List<Vertex<Integer>> integerGraphVertexes = integerGraph.getVertexes();
        List<Vertex<BigDecimal>> bDecimalGraphVertexes = deserializedGraph.getVertexes();

        // both graphs have identical vertexes
        for (int i = 0; i < vertexesSize; i++) {
            String integerGraphVertexName = integerGraphVertexes.get(i).getName();
            String deserializedGraphVertexName = bDecimalGraphVertexes.get(i).getName();
            assertEquals(integerGraphVertexName, deserializedGraphVertexName);
        }

        List<Edge<Integer>> integerGraphEdges = integerGraph.getEdges();
        List<Edge<BigDecimal>> deserializedGraphEdges = deserializedGraph.getEdges();

        // both graphs have identical edges
        for (int j = 0; j < edgesSize; j++) {
            Edge<Integer> integerEdge = integerGraphEdges.get(j);
            Edge<BigDecimal> bDecimalEdge = deserializedGraphEdges.get(j);

            String expectedSourceVertexName = integerEdge.getSource().getName();
            String receivedSourceVertexName = bDecimalEdge.getSource().getName();
            assertEquals(expectedSourceVertexName, receivedSourceVertexName);

            String expectedDestinationVertexName = integerEdge.getDestination().getName();
            String receivedDestinationVertexName = bDecimalEdge.getDestination().getName();
            assertEquals(expectedDestinationVertexName, receivedDestinationVertexName);

            assertEquals(integerEdge.getWeightAsBigDecimal(), bDecimalEdge.getWeightAsBigDecimal());
        }
    }

    @Test
    public void serializeAndDeserializeLongGraphTest() throws Exception {
        Graph<Long> longGraph = TestGraphStore.getLongTestGraph();
        String serialized = mapper.writeValueAsString(longGraph);

        Graph<BigDecimal> deserializedGraph = mapper.readValue(serialized, graphBigDecimalType);

        // both graphs have the same number of vertexes and edges
        assertEquals(longGraph.getVertexes().size(), deserializedGraph.getVertexes().size());
        assertEquals(longGraph.getEdges().size(), deserializedGraph.getEdges().size());

        int vertexesSize = longGraph.getVertexes().size();
        int edgesSize = longGraph.getEdges().size();

        List<Vertex<Long>> longGraphVertexes = longGraph.getVertexes();
        List<Vertex<BigDecimal>> bDecimalGraphVertexes = deserializedGraph.getVertexes();

        // both graphs have identical vertexes
        for (int i = 0; i < vertexesSize; i++) {
            String longGraphVertexName = longGraphVertexes.get(i).getName();
            String deserializedGraphVertexName = bDecimalGraphVertexes.get(i).getName();
            assertEquals(longGraphVertexName, deserializedGraphVertexName);
        }

        List<Edge<Long>> longGraphEdges = longGraph.getEdges();
        List<Edge<BigDecimal>> deserializedGraphEdges = deserializedGraph.getEdges();

        // both graphs have identical edges
        for (int j = 0; j < edgesSize; j++) {
            Edge<Long> longEdge = longGraphEdges.get(j);
            Edge<BigDecimal> bDecimalEdge = deserializedGraphEdges.get(j);

            String expectedSourceVertexName = longEdge.getSource().getName();
            String receivedSourceVertexName = bDecimalEdge.getSource().getName();
            assertEquals(expectedSourceVertexName, receivedSourceVertexName);

            String expectedDestinationVertexName = longEdge.getDestination().getName();
            String receivedDestinationVertexName = bDecimalEdge.getDestination().getName();
            assertEquals(expectedDestinationVertexName, receivedDestinationVertexName);

            assertEquals(longEdge.getWeightAsBigDecimal(), bDecimalEdge.getWeightAsBigDecimal());
        }
    }

    @Test
    public void serializeAndDeserializeFloatGraphTest() throws Exception {
        Graph<Float> floatGraph = TestGraphStore.getFloatTestGraph();
        String serialized = mapper.writeValueAsString(floatGraph);

        Graph<BigDecimal> deserializedGraph = mapper.readValue(serialized, graphBigDecimalType);

        // both graphs have the same number of vertexes and edges
        assertEquals(floatGraph.getVertexes().size(), deserializedGraph.getVertexes().size());
        assertEquals(floatGraph.getEdges().size(), deserializedGraph.getEdges().size());

        int vertexesSize = floatGraph.getVertexes().size();
        int edgesSize = floatGraph.getEdges().size();

        List<Vertex<Float>> floatGraphVertexes = floatGraph.getVertexes();
        List<Vertex<BigDecimal>> bDecimalGraphVertexes = deserializedGraph.getVertexes();

        // both graphs have identical vertexes
        for (int i = 0; i < vertexesSize; i++) {
            String floatGraphVertexName = floatGraphVertexes.get(i).getName();
            String deserializedGraphVertexName = bDecimalGraphVertexes.get(i).getName();
            assertEquals(floatGraphVertexName, deserializedGraphVertexName);
        }

        List<Edge<Float>> floatGraphEdges = floatGraph.getEdges();
        List<Edge<BigDecimal>> deserializedGraphEdges = deserializedGraph.getEdges();

        // both graphs have identical edges
        for (int j = 0; j < edgesSize; j++) {
            Edge<Float> floatEdge = floatGraphEdges.get(j);
            Edge<BigDecimal> bDecimalEdge = deserializedGraphEdges.get(j);

            String expectedSourceVertexName = floatEdge.getSource().getName();
            String receivedSourceVertexName = bDecimalEdge.getSource().getName();
            assertEquals(expectedSourceVertexName, receivedSourceVertexName);

            String expectedDestinationVertexName = floatEdge.getDestination().getName();
            String receivedDestinationVertexName = bDecimalEdge.getDestination().getName();
            assertEquals(expectedDestinationVertexName, receivedDestinationVertexName);

            assertEquals(floatEdge.getWeight(), bDecimalEdge.getWeightAsBigDecimal().floatValue(), 0.00000000001f);
        }
    }
}
