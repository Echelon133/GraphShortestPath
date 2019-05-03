package ml.echelon133.graph.json.exception;

import com.fasterxml.jackson.core.JsonProcessingException;

public class MaxEdgeCountReachedException extends JsonProcessingException {

    public MaxEdgeCountReachedException(String msg) {
        super(msg);
    }
}
