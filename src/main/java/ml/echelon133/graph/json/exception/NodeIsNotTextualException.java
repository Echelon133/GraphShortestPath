package ml.echelon133.graph.json.exception;

import com.fasterxml.jackson.core.JsonProcessingException;

public class NodeIsNotTextualException extends JsonProcessingException {

    public NodeIsNotTextualException(String msg) {
        super(msg);
    }
}
