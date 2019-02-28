package ml.echelon133.graph.json.exception;

import com.fasterxml.jackson.core.JsonProcessingException;

public class NodeIsNotObjectException extends JsonProcessingException {

    public NodeIsNotObjectException(String msg) {
        super(msg);
    }
}
