package org.estonlabs.coinbase.domain.account;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.annotation.Generated;
import java.util.Objects;

@Generated("jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CbBalance {

    private double amount;
    private String currency;

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return "CbBalance{" +
                "amount='" + Objects.toString(amount) + '\'' +
                ", currency=" + Objects.toString(currency) +
                '}';
    }
}