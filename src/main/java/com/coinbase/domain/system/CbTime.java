package com.coinbase.domain.system;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "iso",
        "epoch"
})
@Generated("jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CbTime {

    @JsonProperty("iso")
    private String iso;
    @JsonProperty("epoch")
    private Integer epoch;

    @JsonProperty("iso")
    public String getIso() {
        return iso;
    }

    @JsonProperty("epoch")
    public Integer getEpoch() {
        return epoch;
    }

    @Override
    public String toString() {
        return "CbTime{" +
                "iso='" + iso + '\'' +
                ", epoch=" + epoch +
                '}';
    }
}
