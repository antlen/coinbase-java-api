package org.estonlabs.coinbase.domain.address.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A request to create an address.
 */
public class CbCreateAddressRequest {

    @JsonIgnore
    private final String account;

    @JsonProperty("name")
    private final String name;

    public CbCreateAddressRequest(String account, String name) {
        this.account= account;
        this.name = name;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public String getAccount() {
        return account;
    }
}
