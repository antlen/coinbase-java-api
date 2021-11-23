package com.coinbase.domain.transaction;


import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "title",
        "subtitle",
        "header",
        "health"
})
@Generated("jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CbTransactionDetails {

    @JsonProperty("title")
    private String title;
    @JsonProperty("subtitle")
    private String subtitle;
    @JsonProperty("header")
    private String header;
    @JsonProperty("health")
    private String health;
    @JsonProperty("payment_method_name")
    private String paymentMethodName;

    public String getTitle() {
        return title;
    }

    @JsonProperty("subtitle")
    public String getSubtitle() {
        return subtitle;
    }

    @JsonProperty("header")
    public String getHeader() {
        return header;
    }

    @JsonProperty("health")
    public String getHealth() {
        return health;
    }

    @JsonProperty("payment_method_name")
    public String getPaymentMethodName() {
        return paymentMethodName;
    }

    @Override
    public String toString() {
        return "CbTransactionDetails{" +
                "title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", header='" + header + '\'' +
                ", health='" + health + '\'' +
                ", paymentMethodName='" + paymentMethodName + '\'' +
                '}';
    }
}