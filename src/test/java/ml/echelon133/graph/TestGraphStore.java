package ml.echelon133.graph;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class TestGraphStore {

    /*
    If we start from byteVertex1, expected solution is:

    byteVertex1 | Prev vertex: ---         | sumOfWeights to byteVertex1: 0
    byteVertex2 | Prev vertex: byteVertex5 | sumOfWeights to byteVertex2: 14
    byteVertex3 | Prev vertex: byteVertex5 | sumOfWeights to byteVertex3: 11
    byteVertex4 | Prev vertex: byteVertex2 | sumOfWeights to byteVertex4: 47
    byteVertex5 | Prev vertex: byteVertex1 | sumOfWeights to byteVertex5: 5

     */
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

    /*
    If we start from shortVertex1, expected solution is:

    shortVertex1 | Prev vertex: ---          | sumOfWeights to shortVertex1: 0
    shortVertex2 | Prev vertex: shortVertex5 | sumOfWeights to shortVertex2: 9
    shortVertex3 | Prev vertex: shortVertex5 | sumOfWeights to shortVertex3: 5
    shortVertex4 | Prev vertex: shortVertex2 | sumOfWeights to shortVertex4: 32772
    shortVertex5 | Prev vertex: shortVertex1 | sumOfWeights to shortVertex5: 32767

     */
    public static Graph<Short> getShortTestGraph() {
        Graph<Short> graph = new WeightedGraph<>();

        Vertex<Short> v1 = new Vertex<>("shortVertex1");
        Vertex<Short> v2 = new Vertex<>("shortVertex2");
        Vertex<Short> v3 = new Vertex<>("shortVertex3");
        Vertex<Short> v4 = new Vertex<>("shortVertex4");
        Vertex<Short> v5 = new Vertex<>("shortVertex5");

        List<Vertex<Short>> allVertexes = List.of(v1, v2, v3, v4, v5);
        allVertexes.forEach(graph::addVertex);

        graph.addEdge(v1, v2, (short)10);
        graph.addEdge(v1, v2, (short)12);
        graph.addEdge(v1, v5, Short.MAX_VALUE);
        graph.addEdge(v1, v3, (short)5);
        graph.addEdge(v3, v4, Short.MAX_VALUE);
        graph.addEdge(v4, v3, (short)1);
        graph.addEdge(v4, v5, (short)18);
        graph.addEdge(v3, v2, (short)4);

        return graph;
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
