package ml.echelon133.graph.json.exception;

import com.fasterxml.jackson.core.JsonProcessingException;

public class NodeIsNotArrayException extends JsonProcessingException {

    public NodeIsNotArrayException(String msg) {
        super(msg);
    }
}
