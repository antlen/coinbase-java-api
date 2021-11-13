package org.estonlabs.coinbase.domain.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.estonlabs.coinbase.domain.Builder;

public class CbUserUpdateBuilder implements Builder<CbUser, CbUserUpdateRequest> {

    private CbUserUpdateRequest userUpdate;

    public CbUserUpdateBuilder(CbUser u){
        init(u);
    }

    @Override
    public Builder<CbUser, CbUserUpdateRequest> init(CbUser u) {
        userUpdate = new CbUserUpdateRequest(u.getName(), u.getTimeZone(), u.getNativeCurrency());
        return this;
    }

    @Override
    public CbUserUpdateRequest build() {
        return userUpdate;
    }
    @JsonProperty("name")
    public Builder<CbUser, CbUserUpdateRequest>  setName(String name) {
        userUpdate.setName(name);
        return this;
    }
    @JsonProperty("time_zone")
    public Builder<CbUser, CbUserUpdateRequest>  setTimeZone(String timeZone) {
        userUpdate.setTimeZone(timeZone);
        return this;
    }
    @JsonProperty("native_currency")
    public Builder<CbUser, CbUserUpdateRequest>  setNativeCurrency(String nativeCurrency) {
        userUpdate.setNativeCurrency(nativeCurrency);
        return this;
    }
}
