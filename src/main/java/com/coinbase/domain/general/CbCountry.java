package com.coinbase.domain.general;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CbCountry {
    @JsonProperty("code")
    private String code;
    @JsonProperty("name")
    private String name;
    @JsonProperty("is_in_europe")
    private String isInEurope;

    public String getCode() {
        return code;
    }
    public String getName() {
        return name;
    }

    public String getIsInEurope() {
        return isInEurope;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CbCountry cbCountry = (CbCountry) o;
        return code !=null && cbCountry.code != null && code.equals(cbCountry.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return "CbCountry{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", isInEurope='" + isInEurope + '\'' +
                '}';
    }
}
