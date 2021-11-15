package org.estonlabs.coinbase.domain.transaction;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "resource"
})
@Generated("jsonschema2pojo")
public class CbCounterparty {

    @JsonProperty("id")
    private String id;
    @JsonProperty("resource")
    private String resource;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("resource")
    public String getResource() {
        return resource;
    }
}