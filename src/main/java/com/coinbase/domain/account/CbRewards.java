package com.coinbase.domain.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;
import java.util.Objects;

@Generated("jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CbRewards {

    @JsonProperty("apy")
    private String apy;
    @JsonProperty("label")
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