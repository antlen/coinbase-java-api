package org.estonlabs.coinbase.domain.account.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A request object for updating the name of the account.
 */
public class CbAccountUpdateRequest {

    @JsonIgnore
    private final String account;

    @JsonProperty("name")
    private final String name;

    public CbAccountUpdateRequest(String account, String name) {
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
