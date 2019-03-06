package ml.echelon133.graph.json.exception;

import com.fasterxml.jackson.core.JsonProcessingException;

public class NodeIsNotNumberException extends JsonProcessingException {

    public NodeIsNotNumberException(String msg) {
        super(msg);
    }
}
