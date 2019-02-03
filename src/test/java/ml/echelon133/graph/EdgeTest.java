package ml.echelon133.graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class EdgeTest {

    private Vertex<Double> v1;
    private Vertex<Double> v2;

    @BeforeEach
    public void setup() {
        v1 = new Vertex<>("v1");
        v2 = new Vertex<>("v2");
    }

    @Test
    public void edgeConstructorThrowsExceptionWhenNegativeWeight() {
        String expectedMessage = "Edge weight cannot be negative";
        String receivedMessage = "";

        try {
            Edge<Double> e1 = new Edge<>(v1, v2, -0.01);
        } catch (IllegalArgumentException ex) {
            receivedMessage = ex.getMessage();
        }

        assertEquals(expectedMessage, receivedMessage);
    }

    @Test
    public void getWeightAsBigDecimalConvertsEdgesWithWeightOfTypeByte() {
        Edge<Byte> e1 = new Edge<>(null, null, Byte.MAX_VALUE);
        Edge<Byte> e2 = new Edge<>(null, null, (byte)0);

        BigDecimal e1ExpectedWeight = new BigDecimal(127);
        BigDecimal e2ExpectedWeight = new BigDecimal(0);

        assertEquals(e1ExpectedWeight, e1.getWeightAsBigDecimal());
        assertEquals(e2ExpectedWeight, e2.getWeightAsBigDecimal());
    }
}
