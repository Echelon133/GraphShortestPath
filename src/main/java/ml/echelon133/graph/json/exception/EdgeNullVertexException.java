package ml.echelon133.graph.json.exception;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Exception thrown during the graph deserialization process when "edges" array contains an edge object that
 * references a vertex that is not present in the "vertexes" array.
 */
public class EdgeNullVertexException extends JsonProcessingException {

    public EdgeNullVertexException(String msg) {
        super(msg);
    }
}
