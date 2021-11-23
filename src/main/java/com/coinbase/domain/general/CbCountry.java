package com.coinbase.domain.general;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * The MIT License (MIT)
 *
 *	Copyright (c) 2021 antlen
 *
 *	Permission is hereby granted, free of charge, to any person obtaining a copy
 *	of this software and associated documentation files (the "Software"), to deal
 *	in the Software without restriction, including without limitation the rights
 *	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *	copies of the Software, and to permit persons to whom the Software is
 *	furnished to do so, subject to the following conditions:
 *
 *	The above copyright notice and this permission notice shall be included in all
 *	copies or substantial portions of the Software.
 *
 *	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *	SOFTWARE.
 *
 * ------------------------------------------------
 *
 * @author antlen
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CbCountry {
    @JsonProperty("code")
    private String code;
    @JsonProperty("name")
    private String name;
    @JsonProperty("is_in_europe")
    private String isInEurope;

    /**
     * The country code
     * @return
     */
    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    /**
     * The name of the country
     * @return
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * Whether the country is in europe
     * @return
     */
    @JsonProperty("is_in_europe")
    public String isInEurope() {
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
