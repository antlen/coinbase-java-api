package com.coinbase.domain.general.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.coinbase.domain.general.CbMessage;

import java.util.List;

/**
 * The wrapper for all responses from the server.
 * @param <T>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CbResponse<T> {

    private T data;
    @JsonProperty("warnings")
    private List<CbMessage> warnings;
    @JsonProperty("errors")
    private List<CbMessage> errors;

    public T getData() {
        return data;
    }

    @JsonProperty("errors")
    public List<CbMessage> getErrors() {
        return errors;
    }

    @JsonProperty("warnings")
    public List<CbMessage> getWarnings() {
        return warnings;
    }
}
