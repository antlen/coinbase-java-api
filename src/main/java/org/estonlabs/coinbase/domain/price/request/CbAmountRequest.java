package org.estonlabs.coinbase.domain.price.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Base class for requests that have an amount and currency.
 */
public abstract class CbAmountRequest {

    @JsonIgnore
    private final String from;

    @JsonProperty("amount")
    private final String amount;

    @JsonProperty("currency")
    private final String currency;

    protected CbAmountRequest(String from, String amount, String currency) {
        this.from = from;
        this.amount = amount;
        this.currency = currency;
    }

    public String getFrom() {
        return from;
    }


    @JsonProperty("amount")
    public String getAmount() {
        return amount;
    }

    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }
}
