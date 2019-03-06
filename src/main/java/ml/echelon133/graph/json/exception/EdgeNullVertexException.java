package ml.echelon133.graph.json.exception;

import com.fasterxml.jackson.core.JsonProcessingException;

public class EdgeNullVertexException extends JsonProcessingException {

    public EdgeNullVertexException(String msg) {
        super(msg);
    }
}
