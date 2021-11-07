package org.estonlabs.coinbase.client;

import org.estonlabs.coinbase.domain.account.CbAccount;
import org.estonlabs.coinbase.domain.account.CbAccountCollection;
import org.estonlabs.coinbase.domain.user.CbUser;
import org.estonlabs.coinbase.domain.user.CbUserWrapper;

import java.util.List;

public class CbRestApiClientImpl implements CbClient {

    private final CbRestApiConnection restApi;

    public CbRestApiClientImpl(CbRestApiConnection restApi) {
        this.restApi = restApi;
    }

    @Override
    public void ping() {
        getUser();
    }

    @Override
    public CbUser getUser() {
        return restApi.request("/v2/user").get(CbUserWrapper.class).getData();
    }

    @Override
    public List<CbAccount> getAccounts() {
        return restApi.request("/v2/accounts").get(CbAccountCollection.class).getData();
    }

    public void setLogResponsesEnabled(boolean b){
        restApi.setLogResponsesEnabled(b);
    }
}
