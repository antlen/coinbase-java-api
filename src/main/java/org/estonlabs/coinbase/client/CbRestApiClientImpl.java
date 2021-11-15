package org.estonlabs.coinbase.client;

import org.estonlabs.coinbase.CbApiException;
import org.estonlabs.coinbase.domain.CbNameUpdateRequest;
import org.estonlabs.coinbase.domain.address.CbAddress;
import org.estonlabs.coinbase.domain.address.CbAddressTransaction;
import org.estonlabs.coinbase.domain.address.response.CbAddressTransactionsResponse;
import org.estonlabs.coinbase.domain.pagination.CbPaginatedResponse;
import org.estonlabs.coinbase.domain.Response;
import org.estonlabs.coinbase.domain.account.*;
import org.estonlabs.coinbase.domain.account.response.CbAccountListResponse;
import org.estonlabs.coinbase.domain.account.response.CbAccountResponse;
import org.estonlabs.coinbase.domain.address.response.CbAddressListResponse;
import org.estonlabs.coinbase.domain.address.response.CbAddressResponse;
import org.estonlabs.coinbase.domain.price.CbCurrencyCode;
import org.estonlabs.coinbase.domain.price.CbExchangeRate;
import org.estonlabs.coinbase.domain.price.CbPrice;
import org.estonlabs.coinbase.domain.price.response.CbCurrencyCodeListResponse;
import org.estonlabs.coinbase.domain.price.response.CbExchangeRateResponse;
import org.estonlabs.coinbase.domain.price.response.CbPriceResponse;
import org.estonlabs.coinbase.domain.price.PriceType;
import org.estonlabs.coinbase.domain.system.CbTime;
import org.estonlabs.coinbase.domain.system.response.CbTimeResponse;
import org.estonlabs.coinbase.domain.transaction.CbPaymentMethod;
import org.estonlabs.coinbase.domain.transaction.response.CbPaymentMethodListResponse;
import org.estonlabs.coinbase.domain.transaction.response.CbPaymentMethodResponse;
import org.estonlabs.coinbase.domain.user.response.CbAuthInfoResponse;
import org.estonlabs.coinbase.domain.user.CbUser;
import org.estonlabs.coinbase.domain.user.response.CbUserResponse;
import org.estonlabs.coinbase.domain.user.CbUserUpdateRequest;

import javax.ws.rs.client.Client;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CbRestApiClientImpl implements CbClient {

    public static final String ACCOUNTS = "/accounts";
    public static final String PAYMENT_METHODS = "/payment-methods";
    public static final String USER = "/user";
    public static final String USERS = "/users";
    public static final String AUTH = "/auth";
    public static final String ADDRESSES = "addresses";
    public static final String TIME = "/time";
    public static final String PRICES = "/prices";
    public static final String STARTING_AFTER = "starting_after";
    public static final String TRANSACTIONS = "transactions";
    public static final String DATE = "date";
    public static final String CURRENCIES = "/currencies";

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

    private final <X, T extends Response<X>> X get(Class<T> responseType, String path){
        return get(responseType, path, null);
    }

    private final <X, T extends Response<X>> X get(Class<T> responseType, String path, Map<String, String> params){
        return restApi.get(responseType, path, params).getData();
    }

    private final <X, T extends CbPaginatedResponse<X>> List<X> getPaginated(Class<T> responseType, final String uri){
        ArrayList<X> results = new ArrayList<>();
        boolean loop = true;
        Map<String, String> from = new HashMap<>();
        while(loop){
            T response = restApi.get(responseType, uri, from);
            from.clear();
            results.addAll(response.getData());
            loop =  (response.getPagination()!=null && response.getPagination().getNextUri()!=null);
            if(loop){
                from.put(STARTING_AFTER,response.getPagination().getNextStartingAfter());
            }
        }
        return results;
    }

    @Override
    public void ping() {
        getServerTime();
    }

    @Override
    public void reconnect() {
        restApi.reconnect();
    }

    @Override
    public CbUser getUser() {
        return get(CbUserResponse.class, USER);
    }

    @Override
    public CbUser getUser(String userId) {
        try{
            return get(CbUserResponse.class, toPath(USERS, userId));
        }catch(Exception e){
            throw new CbApiException("Something went wrong, it's likely user "
                    + userId+" does not exist.", e);
        }
    }

    @Override
    public CbAuthInfo getUserAuthInfo() {
        try{
            return get(CbAuthInfoResponse.class, AUTH);
        }catch(Exception e){
            throw new CbApiException("The AuthInfo is only available if logged in through Oath", e);
        }
    }

    @Override
    public List<CbAccount> getAccounts() {
        return getPaginated(CbAccountListResponse.class, ACCOUNTS);
    }

    @Override
    public CbAccount getAccount(String id) {

        return get(CbAccountResponse.class, toPath(ACCOUNTS,id));
    }

    @Override
    public List<CbPaymentMethod> getPaymentMethods() {
        return getPaginated(CbPaymentMethodListResponse.class, PAYMENT_METHODS);
    }

    @Override
    public CbPaymentMethod getPaymentMethod(String id) {
        return get(CbPaymentMethodResponse.class, toPath(PAYMENT_METHODS,id));
    }

    public void setLogResponsesEnabled(boolean b){
        restApi.setLogJsonMessages(b);
    }


    @Override
    public CbUser updateUser(CbUserUpdateRequest u) {
        return restApi.put(CbUserResponse.class, USER, u ).getData();
    }

    @Override
    public CbAccount updateAccountName(String id, CbNameUpdateRequest req) {
        return restApi.put(CbAccountResponse.class, toPath(ACCOUNTS,id), req).getData();
    }

    @Override
    public boolean deleteAccount(String id) {
        return restApi.delete(toPath(ACCOUNTS,id));
    }

    @Override
    public List<CbAddress> getAddresses(String id) {
        return getPaginated(CbAddressListResponse.class, toPath(ACCOUNTS,id, ADDRESSES));
    }

    @Override
    public CbAddress getAddress(String account, String addressId) {
        return get(CbAddressResponse.class, toPath(ACCOUNTS,account, ADDRESSES, addressId));
    }

    @Override
    public List<CbAddressTransaction> getTransactions(String accountId, String address) {
        //     * GET https://api.coinbase.com/v2/accounts/:account_id/addresses/:address_id/transactions
        return getPaginated(CbAddressTransactionsResponse.class,
                toPath(ACCOUNTS,accountId, ADDRESSES, address, TRANSACTIONS));
    }

    @Override
    public CbTime getServerTime() {
        return get(CbTimeResponse.class, TIME);
    }

    @Override
    public CbPrice getPrice(PriceType t, String pair){
        String uri = toPath(PRICES, pair, t.toString().toLowerCase());
        CbPrice res = get(CbPriceResponse.class, uri);
        res.setType(t);
        return res;
    }

    @Override
    public CbPrice getSpotPrice(String pair, LocalDate date){
        String uri = toPath(PRICES, pair, PriceType.SPOT.toString().toLowerCase());
        Map<String, String> params = null;
        if(date !=null){
            params = new HashMap<>();
            params.put(DATE, CbDateTimeUtils.toPriceDateString(date));
        }
        CbPrice res = get(CbPriceResponse.class, uri, params);
        res.setType(PriceType.SPOT);
        return res;
    }

    @Override
    public List<CbCurrencyCode> getCurrencyCodes() {
        return getPaginated(CbCurrencyCodeListResponse.class, CURRENCIES);
    }

    @Override
    public CbExchangeRate getBTCExchangeRate() {
        return get(CbExchangeRateResponse.class, "/exchange-rates");
    }

    private String toPath(String ... s){
        String path = s[0];
        for(int i=1; i<s.length; i++){
            path = path+"/"+s[i];
        }
        return path;
    }
}
