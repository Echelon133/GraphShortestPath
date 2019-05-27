package ml.echelon133.graph.json.exception;

import com.fasterxml.jackson.core.JsonProcessingException;

public class VertexAlreadyInGraphException extends JsonProcessingException {

    public VertexAlreadyInGraphException(String message) {
        super(message);
    }
}
