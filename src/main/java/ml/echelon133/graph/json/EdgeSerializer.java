package ml.echelon133.graph.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import ml.echelon133.graph.Edge;

import java.io.IOException;

/**
 * Serializer of {@link Edge} objects.
 */
public class EdgeSerializer extends StdSerializer<Edge<?>> {

    public EdgeSerializer(JavaType type) {
        super(type);
    }

    @Override
    public void serialize(Edge<?> value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("source", value.getSource().getName());
        gen.writeStringField("destination", value.getDestination().getName());

        // serialize graph weight using BigDecimal representation
        // serializing actual weight with additional field that stores info about the type makes deserialization difficult
        // preserving the actual type does not seem crucial after considering the complexity of deserialization process
        gen.writeNumberField("weight", value.getWeightAsBigDecimal());
        gen.writeEndObject();
    }
}
