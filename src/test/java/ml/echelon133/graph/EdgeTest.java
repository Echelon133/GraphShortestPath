package ml.echelon133.graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

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

    @Test
    public void getWeightAsBigDecimalConvertsEdgesWithWeightOfTypeShort() {
        Edge<Short> e1 = new Edge<>(null, null, Short.MAX_VALUE);
        Edge<Short> e2 = new Edge<>(null, null, (short)0);

        BigDecimal e1ExpectedWeight = new BigDecimal(32767);
        BigDecimal e2ExpectedWeight = new BigDecimal(0);

        assertEquals(e1ExpectedWeight, e1.getWeightAsBigDecimal());
        assertEquals(e2ExpectedWeight, e2.getWeightAsBigDecimal());
    }

    @Test
    public void getWeightAsBigDecimalConvertsEdgesWithWeightOfTypeInteger() {
        Edge<Integer> e1 = new Edge<>(null, null, Integer.MAX_VALUE);
        Edge<Integer> e2 = new Edge<>(null, null, 0);

        BigDecimal e1ExpectedWeight = new BigDecimal(0x7fffffff);
        BigDecimal e2ExpectedWeight = new BigDecimal(0);

        assertEquals(e1ExpectedWeight, e1.getWeightAsBigDecimal());
        assertEquals(e2ExpectedWeight, e2.getWeightAsBigDecimal());
    }

    @Test
    public void getWeightAsBigDecimalConvertsEdgesWithWeightOfTypeLong() {
        Edge<Long> e1 = new Edge<>(null, null, Long.MAX_VALUE);
        Edge<Long> e2 = new Edge<>(null, null, 0L);

        BigDecimal e1ExpectedWeight = new BigDecimal(0x7fffffffffffffffL);
        BigDecimal e2ExpectedWeight = new BigDecimal(0);

        assertEquals(e1ExpectedWeight, e1.getWeightAsBigDecimal());
        assertEquals(e2ExpectedWeight, e2.getWeightAsBigDecimal());
    }

    @Test
    public void getWeightAsBigDecimalConvertsEdgesWithWeightOfTypeBigInteger() {
        BigInteger bigTestValue = new BigInteger("1267650600228229401496703205376"); // 2^100

        Edge<BigInteger> e1 = new Edge<>(null, null, bigTestValue);
        Edge<BigInteger> e2 = new Edge<>(null, null, new BigInteger("0"));

        BigDecimal e1ExpectedWeight = new BigDecimal(bigTestValue);
        BigDecimal e2ExpectedWeight = new BigDecimal(0);

        assertEquals(e1ExpectedWeight, e1.getWeightAsBigDecimal());
        assertEquals(e2ExpectedWeight, e2.getWeightAsBigDecimal());
    }

    @Test
    public void getWeightAsBigDecimalConvertsEdgesWithWeightOfTypeBigDecimal() {
        BigDecimal bigTestValue = new BigDecimal("1267650600228229401496.703205376"); // 2^100

        Edge<BigDecimal> e1 = new Edge<>(null, null, bigTestValue);
        Edge<BigDecimal> e2 = new Edge<>(null, null, new BigDecimal("0"));

        BigDecimal e1ExpectedWeight = bigTestValue;
        BigDecimal e2ExpectedWeight = new BigDecimal(0);

        assertEquals(e1ExpectedWeight, e1.getWeightAsBigDecimal());
        assertEquals(e2ExpectedWeight, e2.getWeightAsBigDecimal());
    }

}
