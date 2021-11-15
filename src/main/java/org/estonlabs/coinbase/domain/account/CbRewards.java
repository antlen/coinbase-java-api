package org.estonlabs.coinbase.domain.account;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;
import java.util.Objects;

@Generated("jsonschema2pojo")
public class CbRewards {

    private String apy;
    private String label;

    @JsonProperty("formatted_apy")
    private String formattedApy;

    public String getApy() {
        return apy;
    }

    public String getFormattedApy() {
        return formattedApy;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return "CbRewards{" +
                "apy='" + Objects.toString(apy) + '\'' +
                ", label='" + Objects.toString(label) + '\'' +
                ", formattedApy='" + Objects.toString(formattedApy) + '\'' +
                '}';
    }
}