package org.estonlabs.coinbase.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.estonlabs.coinbase.domain.price.CbAmount;

import javax.annotation.Generated;
import java.util.Objects;

@Generated("jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CbReferralMoney extends CbAmount {

    @JsonProperty("currency_symbol")
    private String currencySymbol;

    @JsonProperty("referral_threshold")
    private String referralThreshold;

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public String getReferralThreshold() {
        return referralThreshold;
    }

    @Override
    public String toString() {
        return "CbReferralMoney{" +
                "currencySymbol='" + currencySymbol + '\'' +
                ", referralThreshold='" + referralThreshold + '\'' +
                '}';
    }
}
