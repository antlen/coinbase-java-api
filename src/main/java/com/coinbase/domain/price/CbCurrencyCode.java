package com.coinbase.domain.price;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name",
        "min_size"
})
@Generated("jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CbCurrencyCode {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("min_size")
    private String minSize;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("min_size")
    public String getMinSize() {
        return minSize;
    }

    @Override
    public String toString() {
        return "CbCurrencyCode{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", minSize='" + minSize + '\'' +
                '}';
    }
}
