package org.estonlabs.coinbase.domain.user.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.estonlabs.coinbase.builder.Builder;
import org.estonlabs.coinbase.domain.user.CbUser;

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
