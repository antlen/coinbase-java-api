package com.coinbase.domain.price;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "amount",
        "currency",
        "base"
})
@Generated("jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CbPrice extends CbAmount{

    @JsonIgnore
    private PriceType type;

    @JsonProperty("base")
    private String base;

    @JsonProperty("base")
    public String getBase() {
        return base;
    }

    public PriceType getType() {
        return type;
    }

    public CbPrice setType(PriceType type) {
        this.type = type;
        return this;
    }
}
