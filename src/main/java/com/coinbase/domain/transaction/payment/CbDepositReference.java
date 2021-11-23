package com.coinbase.domain.transaction.payment;
import javax.annotation.Generated;

import com.coinbase.domain.price.CbAmount;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "period_in_days",
        "total",
        "remaining",
        "description",
        "label"
})
@Generated("jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CbDepositReference {

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

    @Override
    public String toString() {
        return "CbDepositReference{" +
                "periodInDays=" + periodInDays +
                ", total=" + total +
                ", remaining=" + remaining +
                ", description='" + description + '\'' +
                ", label='" + label + '\'' +
                '}';
    }
}