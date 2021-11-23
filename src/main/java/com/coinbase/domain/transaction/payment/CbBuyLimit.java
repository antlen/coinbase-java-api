package com.coinbase.domain.transaction.payment;



import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.coinbase.domain.price.CbAmount;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "period_in_days",
        "total",
        "remaining",
        "description",
        "label",
        "next_requirement"
})
@Generated("jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CbBuyLimit {

    @JsonProperty("period_in_days")
    private Integer periodInDays;
    @JsonProperty("total")
    private CbAmount total;
    @JsonProperty("remaining")
    private CbAmount remaining;
    @JsonProperty("description")
    private String description;
    @JsonProperty("label")
    private String label;
    @JsonProperty("next_requirement")
    private Object nextRequirement;

    @JsonProperty("period_in_days")
    public Integer getPeriodInDays() {
        return periodInDays;
    }

    @JsonProperty("total")
    public CbAmount getTotal() {
        return total;
    }

    @JsonProperty("remaining")
    public CbAmount getRemaining() {
        return remaining;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("label")
    public String getLabel() {
        return label;
    }

    @JsonProperty("next_requirement")
    public Object getNextRequirement() {
        return nextRequirement;
    }

    @Override
    public String toString() {
        return "CbBuyLimit{" +
                "periodInDays=" + periodInDays +
                ", total=" + total +
                ", remaining=" + remaining +
                ", description='" + description + '\'' +
                ", label='" + label + '\'' +
                ", nextRequirement=" + nextRequirement +
                '}';
    }
}