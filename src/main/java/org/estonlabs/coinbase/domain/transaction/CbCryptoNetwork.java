package org.estonlabs.coinbase.domain.transaction;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "status",
        "name"
})
@Generated("jsonschema2pojo")
public class CbCryptoNetwork {

    @JsonProperty("status")
    private String status;
    @JsonProperty("name")
    private String name;

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }
    @JsonProperty("name")
    public String getName() {
        return name;
    }
}