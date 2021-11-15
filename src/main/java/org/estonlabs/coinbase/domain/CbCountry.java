package org.estonlabs.coinbase.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class CbCountry {

    private String code;
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
        return code.equals(cbCountry.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return "CbCountry{" +
                "code='" + Objects.toString(code) + '\'' +
                ", name='" + Objects.toString(name) + '\'' +
                ", isInEurope='" + Objects.toString(isInEurope) + '\'' +
                '}';
    }
}
