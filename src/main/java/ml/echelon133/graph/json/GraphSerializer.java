package ml.echelon133.graph.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import ml.echelon133.graph.Edge;
import ml.echelon133.graph.Graph;
import ml.echelon133.graph.Vertex;

import java.io.IOException;

/**
 * Serializer of {@link Graph} objects.
 */
public class GraphSerializer extends StdSerializer<Graph<?>> {

    public GraphSerializer(JavaType type) {
        super(type);
    }

    @Override
    public void serialize(Graph<?> value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        JavaType vertexType = provider.constructType(Vertex.class);
        JavaType edgeType = provider.constructType(Edge.class);

        JsonSerializer<Object> vertexSerializer = provider.findValueSerializer(vertexType);
        JsonSerializer<Object> edgeSerializer = provider.findValueSerializer(edgeType);

        gen.writeStartObject();

        gen.writeArrayFieldStart("vertexes");
        for (Vertex v : value.getVertexes()) {
            vertexSerializer.serialize(v, gen, provider);
        }
        gen.writeEndArray();

        gen.writeArrayFieldStart("edges");
        for (Edge e : value.getEdges()) {
            edgeSerializer.serialize(e, gen, provider);
        }
        gen.writeEndArray();

        gen.writeEndObject();
    }
}
