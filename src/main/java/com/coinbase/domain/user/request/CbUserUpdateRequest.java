package com.coinbase.domain.user.request;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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
 * A request to update some of the user details.
 *
 * @author antlen
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "time_zone",
        "native_currency"
})
@Generated("jsonschema2pojo")
public class CbUserUpdateRequest{

    @JsonProperty("time_zone")
    private final String timeZone;
    @JsonProperty("native_currency")
    private final String nativeCurrency;
    @JsonProperty("name")
    private final String name;

    CbUserUpdateRequest(String name, String timeZone, String nativeCurrency) {
        this.name=name;
        this.timeZone = timeZone;
        this.nativeCurrency = nativeCurrency;
    }

    @JsonProperty("time_zone")
    public String getTimeZone() {
        return timeZone;
    }

    @JsonProperty("native_currency")
    public String getNativeCurrency() {
        return nativeCurrency;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }
}