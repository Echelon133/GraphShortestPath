package ml.echelon133.graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}
