package ml.echelon133.graph.json.exception;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Exception thrown during deserialization process when the graph JSON contains an edge that has a negative weight value.
 */
public class NegativeEdgeWeightException extends JsonProcessingException {

    public NegativeEdgeWeightException(String msg) {
        super(msg);
    }
}
