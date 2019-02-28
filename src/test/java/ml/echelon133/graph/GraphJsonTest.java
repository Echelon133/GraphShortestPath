package ml.echelon133.graph;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import ml.echelon133.graph.json.EdgeSerializer;
import ml.echelon133.graph.json.GraphDeserializer;
import ml.echelon133.graph.json.GraphSerializer;
import ml.echelon133.graph.json.VertexSerializer;
import org.junit.jupiter.api.BeforeAll;

import java.math.BigDecimal;


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
}
