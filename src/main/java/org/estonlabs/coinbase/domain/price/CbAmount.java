package org.estonlabs.coinbase.domain.price;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "amount",
        "currency"
})
@Generated("jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CbAmount {

    @JsonProperty("amount")
    private Double amount;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("scale")
    private Integer scale;

    @JsonProperty("amount")
    public Double getAmount() {
        return amount;
    }

    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

    @JsonProperty("scale")
    public Integer getScale() {
        return scale;
    }

    @Override
    public String toString() {
        return "CbAmount{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                ", scale=" + scale +
                '}';
    }
}
