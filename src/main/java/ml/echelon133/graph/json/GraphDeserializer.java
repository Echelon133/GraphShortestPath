package ml.echelon133.graph.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import ml.echelon133.graph.Edge;
import ml.echelon133.graph.Graph;
import ml.echelon133.graph.Vertex;
import ml.echelon133.graph.WeightedGraph;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GraphDeserializer extends StdDeserializer<Graph<BigDecimal>> {

    public GraphDeserializer(JavaType valueType) {
        super(valueType);
    }

    private void checkNodeExistence(JsonNode node, String exceptionMessage) throws JsonProcessingException {
    }

    private void checkIfNodeIsArray(JsonNode node, String exceptionMessage) throws JsonProcessingException {
    }

    private void checkIfNodeIsText(JsonNode node, String exceptionMessage) throws JsonProcessingException {
    }

    private void checkIfNodeIsObject(JsonNode node, String exceptionMessage) throws JsonProcessingException {
    }

    @Override
    public Graph<BigDecimal> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        // we need this helper map to quickly find vertexes by their name (serialized edge refers to the vertex by its name)
        Map<String, Vertex<BigDecimal>> vertexHelperMap = new HashMap<>();

        Graph<BigDecimal> outputGraph = new WeightedGraph<>();

        // main node (all fields are reachable from it)
        JsonNode mainNode = p.getCodec().readTree(p);

        JsonNode vertexes = mainNode.get("vertexes");
        JsonNode edges = mainNode.get("edges");

        // reconstructing vertexes
        Iterator<JsonNode> vertexIter = vertexes.elements();
        while (vertexIter.hasNext()) {
            JsonNode vertexElem = vertexIter.next();
            Vertex<BigDecimal> v = new Vertex<>(vertexElem.textValue());

            outputGraph.addVertex(v);
            // add every vertex to the map, this map will make reconstructing edges faster
            vertexHelperMap.put(vertexElem.textValue(), v);
        }

        // reconstructing edges
        Iterator<JsonNode> edgesIter = edges.elements();
        while (edgesIter.hasNext()) {
            JsonNode edgeElem = edgesIter.next();

            JsonNode sourceVertexElem = edgeElem.get("source");
            JsonNode destinationVertexElem = edgeElem.get("destination");

            String sourceVertexName = sourceVertexElem.textValue();
            String destinationVertexName = destinationVertexElem.textValue();
            BigDecimal weight = edgeElem.get("weight").decimalValue();

            Vertex<BigDecimal> sourceVertex = vertexHelperMap.get(sourceVertexName);
            Vertex<BigDecimal> destinationVertex = vertexHelperMap.get(destinationVertexName);

            Edge<BigDecimal> e = new Edge<>(sourceVertex, destinationVertex, weight);
            outputGraph.addEdge(e);
        }

        return outputGraph;
    }
}
