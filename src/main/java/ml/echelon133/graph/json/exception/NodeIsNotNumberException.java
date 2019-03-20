package ml.echelon133.graph.json.exception;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Exception thrown when a node that should be a number is not a number.
 */
public class NodeIsNotNumberException extends JsonProcessingException {

    public NodeIsNotNumberException(String msg) {
        super(msg);
    }
}
