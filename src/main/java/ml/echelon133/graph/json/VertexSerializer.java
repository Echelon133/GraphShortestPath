package ml.echelon133.graph.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import ml.echelon133.graph.Vertex;

import java.io.IOException;

/**
 * Serializer of {@link Vertex} objects.
 */
public class VertexSerializer extends StdSerializer<Vertex<?>> {

    public VertexSerializer(JavaType type) {
        super(type);
    }

    @Override
    public void serialize(Vertex<?> value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(value.getName());
    }
}
