package com.coinbase.domain.price;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Collections;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "currency",
        "rates"
})
@Generated("jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CbExchangeRate {

    @JsonProperty("currency")
    private String currency;
    @JsonProperty("rates")
    private Map<String,Double> rates;

    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

    @JsonProperty("currency")
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @JsonProperty("rates")
    public Map<String,Double>  getRates() {
        return Collections.unmodifiableMap(rates);
    }

    @Override
    public String toString() {
        return "CbExchangeRate{" +
                "currency='" + currency + '\'' +
                ", rates=" + rates +
                '}';
    }
}
