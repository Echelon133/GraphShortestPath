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
import ml.echelon133.graph.json.exception.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GraphDeserializer extends StdDeserializer<Graph<BigDecimal>> {

    public GraphDeserializer(JavaType valueType) {
        super(valueType);
    }

    private void checkIfNodeExists(JsonNode node, String exceptionMessage) throws JsonProcessingException {
        if (node == null || node.isMissingNode()) {
            throw new MissingNodeException(exceptionMessage);
        }
    }

    private void checkIfNodeIsArray(JsonNode node, String exceptionMessage) throws JsonProcessingException {
        if (!node.isArray()) {
            throw new NodeIsNotArrayException(exceptionMessage);
        }
    }

    private void checkIfNodeIsText(JsonNode node, String exceptionMessage) throws JsonProcessingException {
        if (!node.isTextual()) {
            throw new NodeIsNotTextualException(exceptionMessage);
        }
    }

    private void checkIfNodeIsObject(JsonNode node, String exceptionMessage) throws JsonProcessingException {
        if (!node.isObject()) {
            throw new NodeIsNotObjectException(exceptionMessage);
        }
    }

    private void checkIfNodeIsNumber(JsonNode node, String exceptionMessage) throws JsonProcessingException {
        if (!node.isNumber()) {
            throw new NodeIsNotNumberException(exceptionMessage);
        }
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

        checkIfNodeExists(vertexes, "Missing 'vertexes' JSON node.");
        checkIfNodeExists(edges, "Missing 'edges' JSON node.");

        checkIfNodeIsArray(vertexes, "'vertexes' is not an array node.");
        checkIfNodeIsArray(edges, "'edges' is not an array node.");

        // reconstructing vertexes
        Iterator<JsonNode> vertexIter = vertexes.elements();
        while (vertexIter.hasNext()) {
            JsonNode vertexElem = vertexIter.next();
            checkIfNodeIsText(vertexElem, "Vertex element in 'vertexes' is not textual");

            Vertex<BigDecimal> v = new Vertex<>(vertexElem.textValue());

            outputGraph.addVertex(v);
            // add every vertex to the map, this map will make reconstructing edges faster
            vertexHelperMap.put(vertexElem.textValue(), v);
        }

        // reconstructing edges
        Iterator<JsonNode> edgesIter = edges.elements();
        while (edgesIter.hasNext()) {
            JsonNode edgeElem = edgesIter.next();
            checkIfNodeIsObject(edgeElem, "Edge element in 'edges' is not an object");

            JsonNode sourceVertexElem = edgeElem.get("source");
            JsonNode destinationVertexElem = edgeElem.get("destination");
            JsonNode edgeWeightElem = edgeElem.get("weight");

            checkIfNodeExists(sourceVertexElem, "Edge object does not contain 'source' field");
            checkIfNodeExists(destinationVertexElem, "Edge object does not contain 'destination' field");
            checkIfNodeExists(edgeWeightElem, "Edge object does not contain 'weight' field");

            checkIfNodeIsText(sourceVertexElem, "Source vertex in Edge is not textual");
            checkIfNodeIsText(destinationVertexElem, "Destination vertex in Edge is not textual");

            // if a node can be represented as a number, then BigDecimal representation is also possible
            checkIfNodeIsNumber(edgeWeightElem, "Weight cannot be deserialized as BigDecimal");

            String sourceVertexName = sourceVertexElem.textValue();
            String destinationVertexName = destinationVertexElem.textValue();
            BigDecimal weight = edgeWeightElem.decimalValue();

            Vertex<BigDecimal> sourceVertex = vertexHelperMap.get(sourceVertexName);
            Vertex<BigDecimal> destinationVertex = vertexHelperMap.get(destinationVertexName);

            if (sourceVertex == null || destinationVertex == null) {
                String msg = String.format("Edge '%s' references a vertex that is not present in 'vertexes'", edgeElem.toString());
                throw new EdgeNullVertexException(msg);
            }

            Edge<BigDecimal> e = new Edge<>(sourceVertex, destinationVertex, weight);
            outputGraph.addEdge(e);
        }

        return outputGraph;
    }
}
