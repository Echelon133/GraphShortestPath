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

    /*
        If we start from intVertex1, expected solution is:

        intVertex1 | Prev vertex: ---        | sumOfWeights to intVertex1: 0
        intVertex2 | Prev vertex: intVertex5 | sumOfWeights to intVertex2: 2147485677
        intVertex3 | Prev vertex: intVertex1 | sumOfWeights to intVertex3: 1000
        intVertex4 | Prev vertex: intVertex3 | sumOfWeights to intVertex4: 1200
        intVertex5 | Prev vertex: intVertex6 | sumOfWeights to intVertex5: 2147485647
        intVertex6 | Prev vertex: intVertex3 | sumOfWeights to intVertex6: 2000

         */
    public static Graph<Integer> getIntegerTestGraph() {
        Graph<Integer> graph = new WeightedGraph<>();

        Vertex<Integer> v1 = new Vertex<>("intVertex1");
        Vertex<Integer> v2 = new Vertex<>("intVertex2");
        Vertex<Integer> v3 = new Vertex<>("intVertex3");
        Vertex<Integer> v4 = new Vertex<>("intVertex4");
        Vertex<Integer> v5 = new Vertex<>("intVertex5");
        Vertex<Integer> v6 = new Vertex<>("intVertex6");

        List<Vertex<Integer>> allVertexes = List.of(v1, v2, v3, v4, v5, v6);
        allVertexes.forEach(graph::addVertex);

        graph.addEdge(v1, v3, 1000);
        graph.addEdge(v3, v6, 1000);
        graph.addEdge(v3, v4, 200);
        graph.addEdge(v4, v3, 800);
        graph.addEdge(v6, v5, Integer.MAX_VALUE);
        graph.addEdge(v5, v2, 30);
        graph.addEdge(v2, v4, 15);
        graph.addEdge(v2, v1, 80);
        graph.addEdge(v4, v1, 30);

        return graph;
    }

    /*
        If we start from longVertex1, expected solution is:

        longVertex1 | Prev vertex: ---         | sumOfWeights to longVertex1: 0
        longVertex2 | Prev vertex: longVertex1 | sumOfWeights to longVertex2: 9223372036854775807
        longVertex3 | Prev vertex: longVertex2 | sumOfWeights to longVertex3: 18446744073709551614
        longVertex4 | Prev vertex: longVertex2 | sumOfWeights to longVertex4: 9223372036854776110
        longVertex5 | Prev vertex: longVertex4 | sumOfWeights to longVertex5: 9223372036854781110
        longVertex6 | Prev vertex: longVertex4 | sumOfWeights to longVertex6: 9223372036854776410

         */
    public static Graph<Long> getLongTestGraph() {
        Graph<Long> graph = new WeightedGraph<>();

        Vertex<Long> v1 = new Vertex<>("longVertex1");
        Vertex<Long> v2 = new Vertex<>("longVertex2");
        Vertex<Long> v3 = new Vertex<>("longVertex3");
        Vertex<Long> v4 = new Vertex<>("longVertex4");
        Vertex<Long> v5 = new Vertex<>("longVertex5");
        Vertex<Long> v6 = new Vertex<>("longVertex6");

        List<Vertex<Long>> allVertexes = List.of(v1, v2, v3, v4, v5, v6);
        allVertexes.forEach(graph::addVertex);

        graph.addEdge(v1, v2, Long.MAX_VALUE);
        graph.addEdge(v2, v3, Long.MAX_VALUE);
        graph.addEdge(v3, v4, 1000L);
        graph.addEdge(v4, v2, 80L);
        graph.addEdge(v2, v4, 303L);
        graph.addEdge(v4, v5, 5000L);
        graph.addEdge(v5, v6, 3L);
        graph.addEdge(v4, v6, 5005L);
        graph.addEdge(v4, v6, 300L);

        return graph;
    }

    /*
        If we start from floatVertex1, expected solution is:

        floatVertex1 | Prev vertex: ---          | sumOfWeights to floatVertex1: 0
        floatVertex2 | Prev vertex: floatVertex1 | sumOfWeights to floatVertex2: 13.856
        floatVertex3 | Prev vertex: floatVertex1 | sumOfWeights to floatVertex3: 13.855
        floatVertex4 | Prev vertex: floatVertex2 | sumOfWeights to floatVertex4: 7820.389186
        floatVertex5 | Prev vertex: floatVertex3 | sumOfWeights to floatVertex5: 13.856
        floatVertex6 | Prev vertex: floatVertex3 | sumOfWeights to floatVertex6: 14.39179

         */
    public static Graph<Float> getFloatTestGraph() {
        Graph<Float> graph = new WeightedGraph<>();

        Vertex<Float> v1 = new Vertex<>("floatVertex1");
        Vertex<Float> v2 = new Vertex<>("floatVertex2");
        Vertex<Float> v3 = new Vertex<>("floatVertex3");
        Vertex<Float> v4 = new Vertex<>("floatVertex4");
        Vertex<Float> v5 = new Vertex<>("floatVertex5");
        Vertex<Float> v6 = new Vertex<>("floatVertex6");

        List<Vertex<Float>> allVertexes = List.of(v1, v2, v3, v4, v5, v6);
        allVertexes.forEach(graph::addVertex);

        graph.addEdge(v1, v3, 13.855f);
        graph.addEdge(v1, v2, 13.856f);
        graph.addEdge(v3, v5, 0.001f);
        graph.addEdge(v5, v6, 0.5368f);
        graph.addEdge(v3, v6, 0.53679f);
        graph.addEdge(v2, v4, 7806.53320f);
        graph.addEdge(v2, v4, 7806.533201f);
        graph.addEdge(v2, v4, 7806.533186f);

        return graph;
    }


    /*
        If we start from doubleVertex1, expected solution is:

        doubleVertex1 | Prev vertex: ---           | sumOfWeights to doubleVertex1: 0
        doubleVertex2 | Prev vertex: doubleVertex4 | sumOfWeights to doubleVertex2: 1008.667
        doubleVertex3 | Prev vertex: doubleVertex1 | sumOfWeights to doubleVertex3: 1.8776
        doubleVertex4 | Prev vertex: doubleVertex5 | sumOfWeights to doubleVertex4: 1008.666
        doubleVertex5 | Prev vertex: doubleVertex1 | sumOfWeights to doubleVertex5: 7.1

         */
    public static Graph<Double> getDoubleTestGraph() {
        Graph<Double> graph = new WeightedGraph<>();

        Vertex<Double> v1 = new Vertex<>("doubleVertex1");
        Vertex<Double> v2 = new Vertex<>("doubleVertex2");
        Vertex<Double> v3 = new Vertex<>("doubleVertex3");
        Vertex<Double> v4 = new Vertex<>("doubleVertex4");
        Vertex<Double> v5 = new Vertex<>("doubleVertex5");

        List<Vertex<Double>> allVertexes = List.of(v1, v2, v3, v4, v5);
        allVertexes.forEach(graph::addVertex);

        graph.addEdge(v1, v2, 1100.5);
        graph.addEdge(v2, v1, 0.0001);
        graph.addEdge(v1, v3, 1.8776);
        graph.addEdge(v3, v5, 5.7986);
        graph.addEdge(v5, v4, 1001.566);
        graph.addEdge(v4, v5, 1000000.11);
        graph.addEdge(v1, v5, 7.1);
        graph.addEdge(v4, v2, 0.001);
        graph.addEdge(v4, v3, 23.999);
        graph.addEdge(v2, v4, 93.2);

        return graph;
    }

    /*
        If we start from bigIntVertex1, expected solution is:

        bigIntVertex1 | Prev vertex: ---           | sumOfWeights to bigIntVertex1: 0
        bigIntVertex2 | Prev vertex: bigIntVertex1 | sumOfWeights to bigIntVertex2: 9223372036854775807
        bigIntVertex3 | Prev vertex: bigIntVertex2 | sumOfWeights to bigIntVertex3: 18446744073709551614
        bigIntVertex4 | Prev vertex: bigIntVertex3 | sumOfWeights to bigIntVertex4: 27670116110564327421
        bigIntVertex5 | Prev vertex: bigIntVertex3 | sumOfWeights to bigIntVertex5: 27670116110564327421
        bigIntVertex6 | Prev vertex: bigIntVertex5 | sumOfWeights to bigIntVertex6: 36893488147419103228

         */
    public static Graph<BigInteger> getBigIntegerTestGraph() {
        Graph<BigInteger> graph = new WeightedGraph<>();

        Vertex<BigInteger> v1 = new Vertex<>("bigIntVertex1");
        Vertex<BigInteger> v2 = new Vertex<>("bigIntVertex2");
        Vertex<BigInteger> v3 = new Vertex<>("bigIntVertex3");
        Vertex<BigInteger> v4 = new Vertex<>("bigIntVertex4");
        Vertex<BigInteger> v5 = new Vertex<>("bigIntVertex5");
        Vertex<BigInteger> v6 = new Vertex<>("bigIntVertex6");

        List<Vertex<BigInteger>> allVertexes = List.of(v1, v2, v3, v4, v5, v6);
        allVertexes.forEach(graph::addVertex);

        graph.addEdge(v1, v2, new BigInteger("9223372036854775807"));
        graph.addEdge(v2, v3, new BigInteger("9223372036854775807"));
        graph.addEdge(v3, v5, new BigInteger("9223372036854775807"));
        graph.addEdge(v2, v5, new BigInteger("27670116110564327421"));
        graph.addEdge(v5, v6, new BigInteger("9223372036854775807"));
        graph.addEdge(v3, v4, new BigInteger("9223372036854775807"));

        return graph;
    }

    public static Graph<BigDecimal> getBigDecimalTestGraph() {
        return new WeightedGraph<>();
    }
}
