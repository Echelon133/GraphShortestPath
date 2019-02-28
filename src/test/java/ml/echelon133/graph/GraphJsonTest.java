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
}
