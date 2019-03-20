package ml.echelon133.graph.json.exception;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Exception thrown when a node that should be an object is not an object.
 */
public class NodeIsNotObjectException extends JsonProcessingException {

    public NodeIsNotObjectException(String msg) {
        super(msg);
    }
}
