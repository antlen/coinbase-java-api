package org.estonlabs.coinbase.domain.transaction;


import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "amount",
        "currency"
})
@Generated("jsonschema2pojo")
public class CbMinimumPurchaseAmount {

    @JsonProperty("amount")
    private String amount;
    @JsonProperty("currency")
    private String currency;

    @JsonProperty("amount")
    public String getAmount() {
        return amount;
    }

    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return "CbMinimumPurchaseAmount{" +
                "amount='" + amount + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }
}