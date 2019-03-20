package ml.echelon133.graph.json.exception;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Exception thrown when a node that should be an array is not an array.
 */
public class NodeIsNotArrayException extends JsonProcessingException {

    public NodeIsNotArrayException(String msg) {
        super(msg);
    }
}
