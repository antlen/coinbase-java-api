package org.estonlabs.coinbase.domain.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;
import java.util.Objects;

@Generated("jsonschema2pojo")
public class CbReferralMoney {

    private String amount;
    private String currency;

    @JsonProperty("currency_symbol")
    private String currencySymbol;

    @JsonProperty("referral_threshold")
    private String referralThreshold;

    public String getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public String getReferralThreshold() {
        return referralThreshold;
    }

    @Override
    public String toString() {
        return "CbReferralMoney{" +
                "amount='" + Objects.toString(amount) + '\'' +
                ", currency='" + Objects.toString(currency) + '\'' +
                ", currencySymbol='" + Objects.toString(currencySymbol) + '\'' +
                ", referralThreshold='" + Objects.toString(referralThreshold) + '\'' +
                '}';
    }
}
