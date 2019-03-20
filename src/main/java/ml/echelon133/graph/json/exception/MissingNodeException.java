package ml.echelon133.graph.json.exception;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Exception thrown when graph JSON does not contain a required node that is needed to correctly deserialize a graph.
 */
public class MissingNodeException extends JsonProcessingException {

    public MissingNodeException(String msg) {
        super(msg);
    }
}
