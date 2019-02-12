# Weighted Directed Graphs

## Example Graph

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

|       Vertex       | Previous Vertex | Sum of weights to vertex |
|:------------------:|:---------------:|:------------------------:|
| intVertex1 (start) |       ---       |             0            |
|     intVertex2     |    intVertex5   |        2147485677        |
|     intVertex3     |    intVertex1   |           1000           |
|     intVertex4     |    intVertex3   |           1200           |
|     intVertex5     |    intVertex6   |        2147485647        |
|     intVertex6     |    intVertex3   |           2000           |


Only vertexes that are reachable from the start vertex will be placed in the result map.
Ex. if our graph has a vertex without any outgoing edges, and we will call **solveStartingFrom()** method passing that 
vertex as an argument, we will receive a map that has only one key (starting vertex). That key's value (**VertexResult<>**) will always have *sumOfWeights* equal to 0 and *previousVertex* set to *null*.
