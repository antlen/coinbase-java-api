package org.estonlabs.coinbase.domain.account;


import javax.annotation.Generated;
import java.util.Objects;

@Generated("jsonschema2pojo")
public class CbBalance {

    private double amount;
    private String currency;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "CbBalance{" +
                "amount='" + Objects.toString(amount) + '\'' +
                ", currency=" + Objects.toString(currency) +
                '}';
    }
}