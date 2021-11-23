package com.coinbase.domain.user.request;

import com.coinbase.builder.Builder;
import com.coinbase.domain.user.CbUser;
import com.fasterxml.jackson.annotation.JsonProperty;

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
public class CbUserUpdateBuilder implements Builder<CbUserUpdateRequest> {
    private String name;
    private String timeZone;
    private String nativeCurrency;

    public CbUserUpdateBuilder(CbUser u){
        name = u.getName();
        timeZone=u.getTimeZone();
        nativeCurrency=u.getNativeCurrency();
    }

    @Override
    public CbUserUpdateRequest build() {
        return new CbUserUpdateRequest(name, timeZone, nativeCurrency);
    }

    @JsonProperty("name")
    public Builder<CbUserUpdateRequest>  setName(String name) {
        this.name=name;
        return this;
    }
    @JsonProperty("time_zone")
    public Builder<CbUserUpdateRequest>  setTimeZone(String timeZone) {
        this.timeZone=timeZone;
        return this;
    }
    @JsonProperty("native_currency")
    public Builder<CbUserUpdateRequest>  setNativeCurrency(String nativeCurrency) {
        this.nativeCurrency=nativeCurrency;
        return this;
    }
}
