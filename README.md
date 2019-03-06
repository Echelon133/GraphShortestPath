# Weighted Directed Graphs

## Example Graph

![GRAPH](https://github.com/Echelon133/WeightedGraphShortestPath/blob/master/graph_example/int_graph.png)

```java
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
```

## Calculating Shortest Path Starting From Specific Vertex

```java
ShortestPathSolver<Integer> sps = new ShortestPathSolver<>(graph);
Map<Vertex<Integer>, VertexResult<Integer>> result = sps.solveStartingFrom(v1);
```
### Result

|       Vertex       | Previous Vertex | Sum of weights to vertex | pathToVertex                                   |
|:------------------:|:---------------:|:------------------------:|:----------------------------------------------:|
| intVertex1 (start) |       ---       |             0            | ---                                            |
|     intVertex2     |    intVertex5   |        2147485677        | intVertex1, intVertex3, intVertex6, intVertex5 |
|     intVertex3     |    intVertex1   |           1000           | intVertex1                                     |
|     intVertex4     |    intVertex3   |           1200           | intVertex1, intVertex3                         |
|     intVertex5     |    intVertex6   |        2147485647        | intVertex1, intVertex3, intVertex6             |
|     intVertex6     |    intVertex3   |           2000           | intVertex1, intVertex3                         |


Only vertexes that are reachable from the start vertex will be placed in the result map.
For example, if our graph has a vertex without any outgoing edges, and we will call **solveStartingFrom()** method passing that 
vertex as an argument, we will receive a map that has only one key (starting vertex). That key's value (**VertexResult<>**) will always have *sumOfWeights* equal to 0 and *previousVertex* set to *null*.

**VertexResult<>** *pathToVertex* is a linked list that stores vertexes in order in which they were visited before 
reaching the vertex that is a key of **VertexResult<>**. The table above shows, that the last element of *pathToVertex*
is always equal to the element that is stored in *previousVertex* of **VertexResult<>**. 

## Graph Serialization/Deserialization

### Serialization 

Source graph:

```java
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
```

Serialization output:

```json
{
"vertexes":["byteVertex1","byteVertex2","byteVertex3","byteVertex4","byteVertex5"],
"edges":[
  {"source": "byteVertex1", "destination": "byteVertex2", "weight": 20},
  {"source": "byteVertex2", "destination": "byteVertex3", "weight": 10},
  {"source": "byteVertex3", "destination": "byteVertex4", "weight": 50},
  {"source": "byteVertex1", "destination": "byteVertex5", "weight": 5},
  {"source": "byteVertex2", "destination": "byteVertex3", "weight": 3},
  {"source": "byteVertex4", "destination": "byteVertex5", "weight": 12},
  {"source": "byteVertex5", "destination": "byteVertex2", "weight": 9},
  {"source": "byteVertex5", "destination": "byteVertex3", "weight": 6},
  {"source": "byteVertex1", "destination": "byteVertex2", "weight": 127},
  {"source": "byteVertex2", "destination": "byteVertex4", "weight": 33}]
 }
```

Every *weight* value is the **Edge** weight value converted to **BigDecimal**. It simplifies serialization and deserialization of graphs, 
because there is no need for storing original type information, and deserialization method always returns **Graph\<BigDecimal>**.

### Deserialization

All graphs are deserialized to **Graph\<BigDecimal\>** because:
* serialization process serializes each edge *weight* as **BigDecimal**
* simplicity - deserializer always returns **Graph\<BigDecimal>**
* deserialization to a **Graph** with parametric type determined dynamically would be slower, and harder to implement
* type preserving code would be significantly larger, and the gain is negligible

Example deserialization code:

```java
SimpleModule module = new SimpleModule();
ObjectMapper mapper = new ObjectMapper();

JavaType graphType = mapper.constructType(Graph.class);
JavaType graphBigDecimalType = mapper.getTypeFactory().constructParametricType(Graph.class, BigDecimal.class);

module.addDeserializer(Graph.class, new GraphDeserializer(graphType));
mapper.registerModule(module);

// serialized is a String that contains the serialized graph
Graph<BigDecimal> deserializedGraph = mapper.readValue(serialized, graphBigDecimalType);
```