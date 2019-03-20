package ml.echelon133.graph.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import ml.echelon133.graph.Vertex;
import ml.echelon133.graph.VertexResult;

import java.io.IOException;
import java.util.Map;

/**
 * Serializer of the result {@link Map} that is returned by
 * {@link ml.echelon133.graph.ShortestPathSolver#solveStartingFrom(Vertex)} method.
 */
public class ResultMapSerializer extends StdSerializer<Map<Vertex<?>, VertexResult<?>>> {

    public ResultMapSerializer(JavaType type) {
        super(type);
    }

    @Override
    public void serialize(Map<Vertex<?>, VertexResult<?>> value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        JavaType vertexResultType = provider.constructType(VertexResult.class);
        JsonSerializer<Object> vertexResultSerializer = provider.findValueSerializer(vertexResultType);

        gen.writeStartObject();
        gen.writeArrayFieldStart("results");
        for (Map.Entry<Vertex<?>, VertexResult<?>> entry : value.entrySet()) {
            gen.writeStartObject();
            gen.writeFieldName(entry.getKey().getName());
            vertexResultSerializer.serialize(entry.getValue(), gen, provider);
            gen.writeEndObject();
        }
        gen.writeEndArray();
        gen.writeEndObject();
    }
}
