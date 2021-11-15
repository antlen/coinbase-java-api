package org.estonlabs.coinbase.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CbNameUpdateRequest {

    @JsonProperty("name")
    private String name;

    public CbNameUpdateRequest(){}

    public CbNameUpdateRequest(String name) {
        this.name = name;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }
}
