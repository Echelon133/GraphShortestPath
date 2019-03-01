package ml.echelon133.graph.json.exception;

import com.fasterxml.jackson.core.JsonProcessingException;

public class NodeIsNotBigDecimalException extends JsonProcessingException {

    public NodeIsNotBigDecimalException(String msg) {
        super(msg);
    }
}
