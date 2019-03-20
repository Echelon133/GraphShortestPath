package ml.echelon133.graph.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import ml.echelon133.graph.Vertex;
import ml.echelon133.graph.VertexResult;

import java.io.IOException;

/**
 * Serializer of {@link VertexResult} objects. Not intended for standalone use.
 * It is used by {@link ResultMapSerializer}.
 */
public class VertexResultSerializer extends StdSerializer<VertexResult<?>> {

    public VertexResultSerializer(JavaType type) {
        super(type);
    }

    @Override
    public void serialize(VertexResult<?> value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        JavaType vertexType = provider.constructType(Vertex.class);
        JsonSerializer<Object> vertexSerializer = provider.findValueSerializer(vertexType);

        gen.writeStartObject();

        // if we serialize Map<Vertex<T>, VertexResult<T>> as a whole, sourceVertex field is redundant
        // sourceVertex is always equal to the value used as a key in that map
        // gen.writeStringField("sourceVertex", value.getSourceVertex().getName());

        if (value.getPreviousVertex() == null) {
            gen.writeNullField("previousVertex");
        } else {
            gen.writeStringField("previousVertex", value.getPreviousVertex().getName());
        }

        gen.writeNumberField("sumOfWeights", value.getSumOfWeights());

        gen.writeArrayFieldStart("pathToVertex");
        for (Object v : value.getPathToVertex()) {
            vertexSerializer.serialize(v, gen, provider);
        }
        gen.writeEndArray();

        gen.writeEndObject();
    }
}
