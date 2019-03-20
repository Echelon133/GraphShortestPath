package ml.echelon133.graph.json.exception;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Exception thrown when a node that should contain text does not contain text.
 */
public class NodeIsNotTextualException extends JsonProcessingException {

    public NodeIsNotTextualException(String msg) {
        super(msg);
    }
}
