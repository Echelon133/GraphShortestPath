package ml.echelon133.graph.json.exception;

import com.fasterxml.jackson.core.JsonProcessingException;

public class MissingNodeException extends JsonProcessingException {

    public MissingNodeException(String msg) {
        super(msg);
    }
}
