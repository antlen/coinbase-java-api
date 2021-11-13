package org.estonlabs.coinbase.client;

import org.estonlabs.coinbase.domain.DataProvider;
import org.estonlabs.coinbase.domain.account.CbAccount;
import org.estonlabs.coinbase.domain.account.CbAccountCollection;
import org.estonlabs.coinbase.domain.account.CbAccountProvider;
import org.estonlabs.coinbase.domain.transaction.CbPaymentMethod;
import org.estonlabs.coinbase.domain.transaction.CbPaymentMethodCollection;
import org.estonlabs.coinbase.domain.account.CbAuthInfo;
import org.estonlabs.coinbase.domain.transaction.CbPaymentMethodProvider;
import org.estonlabs.coinbase.domain.user.CbAuthInfoWrapper;
import org.estonlabs.coinbase.domain.user.CbUser;
import org.estonlabs.coinbase.domain.user.CbUserProvider;
import org.estonlabs.coinbase.domain.user.CbUserUpdateRequest;

import java.util.List;

public class CbRestApiClientImpl implements CbClient {

    private final RestfulConnection restApi;

    public CbRestApiClientImpl(RestfulConnection restApi) {
        this.restApi = restApi;
    }

    @Override
    public boolean isLConnectedToProd() {
        return !restApi.isSandbox();
    }

    @Override
    public boolean isLConnectedToSandbox() {
        return restApi.isSandbox();
    }

    private final <X, T extends DataProvider<X>> X get(Class<T> responseType, String path){
        return get(responseType,path, null);
    }

    private final <X, T extends DataProvider<X>> X get(Class<T> responseType, String path, String params){
        T t = restApi.get(responseType, path, params);
        return t.getData();
    }

    @Override
    public void ping() {
        getUser();
    }

    @Override
    public CbUser getUser() {
        return get(CbUserProvider.class,"/user");
    }

    @Override
    public CbUser getUser(String userId) {
        try{
            return get(CbUserProvider.class,"/users", userId);
        }catch(Exception e){
            throw new CbApiException("Something went wrong, it's likely user "
                    + userId+" does not exist.", e);
        }
    }

    @Override
    public CbAuthInfo getUserAuthInfo() {
        try{
            return get(CbAuthInfoWrapper.class,"/auth");
        }catch(Exception e){
            throw new CbApiException("The AuthInfo is only available if logged in through Oath", e);
        }
    }

    @Override
    public List<CbAccount> getAccounts() {
        return get(CbAccountCollection.class,"/accounts");
    }

    @Override
    public CbAccount getAccount(String id) {

        return get(CbAccountProvider.class,"/accounts", id);
    }

    @Override
    public List<CbPaymentMethod> getPaymentMethods() {
        return get(CbPaymentMethodCollection.class,"/payment-methods");
    }

    @Override
    public CbPaymentMethod getPaymentMethod(String id) {
        return get(CbPaymentMethodProvider.class,"/payment-methods", id);
    }

    public void setLogResponsesEnabled(boolean b){
        restApi.setLogJsonMessages(b);
    }


    @Override
    public CbUser updateUser(CbUserUpdateRequest u) {
        return restApi.put(CbUserProvider.class,"/user", u ).getData();
    }

    @Override
    public CbAccount updateAccountName(String id, CbNameUpdateRequest req) {
        return restApi.put(CbAccountProvider.class,"/accounts", id, req).getData();
    }
}
