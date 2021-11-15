package org.estonlabs.coinbase.domain.address;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "address"
})
@Generated("jsonschema2pojo")
public class CbAddressInfo {

    @JsonProperty("address")
    private String address;

    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

}