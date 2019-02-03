package ml.echelon133.graph;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class TestGraphStore {

    public static Graph<Byte> getByteTestGraph() {
        Graph<Byte> graph = new WeightedGraph<>();

        Vertex<Byte> v1 = new Vertex<>("byteVertex1");
        Vertex<Byte> v2 = new Vertex<>("byteVertex2");
        Vertex<Byte> v3 = new Vertex<>("byteVertex3");
        Vertex<Byte> v4 = new Vertex<>("byteVertex4");
        Vertex<Byte> v5 = new Vertex<>("byteVertex5");

        List<Vertex<Byte>> allVertexes = List.of(v1, v2, v3, v4, v5);
        allVertexes.forEach(graph::addVertex);

        graph.addEdge(v1, v2, (byte)20);
        graph.addEdge(v2, v3, (byte)10);
        graph.addEdge(v3, v4, (byte)50);
        graph.addEdge(v1, v5, (byte)5);
        graph.addEdge(v2, v3, (byte)3);
        graph.addEdge(v4, v5, (byte)12);
        graph.addEdge(v5, v2, (byte)9);
        graph.addEdge(v5, v3, (byte)6);
        graph.addEdge(v1, v2, (byte)127);
        graph.addEdge(v2, v4, (byte)33);

        return graph;
    }

    public static Graph<Short> getShortTestGraph() {
        return new WeightedGraph<>();
    }

    public static Graph<Integer> getIntegerTestGraph() {
        return new WeightedGraph<>();
    }

    public static Graph<Long> getLongTestGraph() {
        return new WeightedGraph<>();
    }

    public static Graph<Float> getFloatTestGraph() {
        return new WeightedGraph<>();
    }

    public static Graph<Double> getDoubleTestGraph() {
        return new WeightedGraph<>();
    }

    public static Graph<BigInteger> getBigIntegerTestGraph() {
        return new WeightedGraph<>();
    }

    public static Graph<BigDecimal> getBigDecimalTestGraph() {
        return new WeightedGraph<>();
    }
}
