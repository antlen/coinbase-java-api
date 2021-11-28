package com.coinbase.client;

import com.coinbase.client.connection.RestConnection;
import com.coinbase.domain.account.CbAccount;
import com.coinbase.domain.account.request.CbAccountUpdateRequest;
import com.coinbase.domain.account.response.CbAccountListResponse;
import com.coinbase.domain.account.response.CbAccountResponse;
import com.coinbase.domain.general.response.CbResponse;
import com.coinbase.domain.order.request.CbOrderRequest;
import com.coinbase.domain.pagination.response.CbPaginatedResponse;
import com.coinbase.domain.trade.*;
import com.coinbase.domain.trade.response.CbTradeListResponse;
import com.coinbase.domain.transaction.payment.CbPaymentMethod;
import com.coinbase.domain.transaction.response.CbPaymentMethodListResponse;
import com.coinbase.domain.transaction.response.CbPaymentMethodResponse;
import com.coinbase.domain.address.request.CbCreateAddressRequest;
import com.coinbase.domain.trade.request.CbCashTransactionRequest;
import com.coinbase.domain.trade.response.CbCashTransactionListResponse;
import com.coinbase.domain.trade.response.CbCashTransactionResponse;
import com.coinbase.exception.CbApiException;
import com.coinbase.domain.address.response.CbAddressTransactionResponse;
import com.coinbase.domain.address.CbAddress;
import com.coinbase.domain.address.CbAddressTransaction;
import com.coinbase.domain.address.response.CbAddressTransactionsResponse;
import com.coinbase.domain.address.response.CbAddressListResponse;
import com.coinbase.domain.address.response.CbAddressResponse;
import com.coinbase.domain.price.CbCurrencyCode;
import com.coinbase.domain.price.CbExchangeRate;
import com.coinbase.domain.price.CbPrice;
import com.coinbase.domain.price.response.CbCurrencyCodeListResponse;
import com.coinbase.domain.price.response.CbExchangeRateResponse;
import com.coinbase.domain.price.response.CbPriceResponse;
import com.coinbase.domain.price.PriceType;
import com.coinbase.domain.system.CbTime;
import com.coinbase.domain.system.response.CbTimeResponse;
import com.coinbase.domain.trade.response.CbTradeResponse;
import com.coinbase.domain.transaction.TransactionType;
import com.coinbase.domain.transaction.request.CbMoneyRequest;
import com.coinbase.domain.user.CbUser;
import com.coinbase.domain.user.response.CbUserResponse;
import com.coinbase.domain.user.request.CbUserUpdateRequest;
import com.coinbase.exception.CbApiHttpException;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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
 * The main implementation of CoinbaseClient
 *
 * @author antlen
 */
public class CoinbaseSyncRestClient implements CoinbaseSyncClient {
    public static final String LIMIT = "limit";
    private static DateTimeFormatter PRICE_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

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
    public static final String EXCHANGE_RATES = "/exchange-rates";
    public static final String COMMIT = "commit";

    private final RestConnection restApi;
    private final String pageSizeStr;
    private final int pageSize;

    public CoinbaseSyncRestClient(RestConnection restApi, int paginationSize) {
        this.restApi = restApi;
        this.pageSizeStr =Integer.toString(paginationSize);
        this.pageSize = paginationSize;
    }

    private final <X, T extends CbPaginatedResponse<X>> List<X> getPaginated(Class<T> responseType,
                                                                             final String uri, int maxRecords){
        ArrayList<X> results = new ArrayList<>();
        T response = block(restApi.get(responseType, uri, Map.of(LIMIT,pageSizeStr)));
        results.addAll(response.getData());
        populatePaginated(responseType,results,response,maxRecords, uri);
        return results;
    }

    private <X, T extends CbPaginatedResponse<X>> void populatePaginated(Class<T> responseType,ArrayList<X> results,
                                                                         T response, int maxRecords, String uri){
        while((response.getPagination()!=null && response.getPagination().getNextUri()!=null)
                && results.size()< maxRecords) {
            int size = Math.min((maxRecords - results.size()), pageSize);
            response = block(restApi.get(responseType, uri,
                    Map.of(LIMIT,  Integer.toString(size), STARTING_AFTER,
                            response.getPagination().getNextStartingAfter())));
            results.addAll(response.getData());
        }
    }

    private final <X, T extends CbResponse<X>> X get(Class<T> responseType, String path){
        return get(responseType, path, null);
    }

    private final <X, T extends CbResponse<X>> X get(Class<T> responseType, String path, Map<String, String> params){
        return block(restApi.get(responseType, path, params)).getData();
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
    public CoinbaseSyncClient clone() {
        return new CoinbaseSyncRestClient(restApi.clone(),  Integer.parseInt(pageSizeStr));
    }

    @Override
    public CbUser getUser(String userId) {
        try{
            return get(CbUserResponse.class, toUri(USERS, userId));
        }catch(Exception e){
            throw new CbApiException("Something went wrong, it's likely user "
                    + userId+" does not exist.", e);
        }
    }

    @Override
    @Path("")
    public List<CbAccount> getAccounts() {
        return getPaginated(CbAccountListResponse.class, ACCOUNTS, Integer.MAX_VALUE);
    }

    @Override
    public CbAccount getAccount(String id) {

        return get(CbAccountResponse.class, toUri(ACCOUNTS,id));
    }

    @Override
    public List<CbPaymentMethod> getPaymentMethods() {
        return getPaginated(CbPaymentMethodListResponse.class, PAYMENT_METHODS, Integer.MAX_VALUE);
    }

    @Override
    public CbPaymentMethod getPaymentMethod(String id) {
        return get(CbPaymentMethodResponse.class, toUri(PAYMENT_METHODS,id));
    }

    public void setLogResponsesEnabled(boolean b){
        restApi.setLogJsonMessages(b);
    }

    @Override
    public CbUser updateUser(CbUserUpdateRequest u) {
        return block(restApi.put(CbUserResponse.class, USER, u )).getData();
    }

    @Override
    public CbAccount updateAccountName(CbAccountUpdateRequest req) {
        return block(restApi.put(CbAccountResponse.class, toUri(ACCOUNTS,req.getAccount()), req)).getData();
    }

    @Override
    public boolean deleteAccount(String id) {
        try{
            return restApi.delete(toUri(ACCOUNTS,id)).get().getStatusInfo().toEnum() == Response.Status.NO_CONTENT;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<CbAddress> getAddresses(String id) {
        return getPaginated(CbAddressListResponse.class, toUri(ACCOUNTS,id, ADDRESSES), Integer.MAX_VALUE);
    }

    @Override
    public CbAddress getAddress(String account, String addressId) {
        return get(CbAddressResponse.class, toUri(ACCOUNTS,account, ADDRESSES, addressId));
    }

    @Override
    public CbAddress createAddress(CbCreateAddressRequest request) {
        return block(restApi.post(CbAddressResponse.class,
                toUri(ACCOUNTS,request.getAccount(), ADDRESSES), request)).getData();
    }

    @Override
    public List<CbAddressTransaction> getTransactions(String accountId, String address) {
        return getTransactions(accountId, address, Integer.MAX_VALUE);
    }

    @Override
    public List<CbAddressTransaction> getTransactions(String accountId, String address, int maxRecords) {
        return getPaginated(CbAddressTransactionsResponse.class,
                toUri(ACCOUNTS,accountId, ADDRESSES, address, TRANSACTIONS), maxRecords);
    }

    @Override
    public CbAddressTransaction sendMoneyRequest(CbMoneyRequest req) {
        return block(restApi.post(CbAddressTransactionResponse.class,
                toUri(ACCOUNTS,req.getFrom(), TRANSACTIONS), req)).getData();
    }

    @Override
    public CbAddressTransaction sendMoney(CbMoneyRequest req) {
        if(!req.getType().equals(TransactionType.SEND.toString().toLowerCase())){
            throw new CbApiException("Incorrect type '"+req.getType()+"' for sendMoney request.");
        }
        return sendMoneyRequest(req);
    }

    @Override
    public CbAddressTransaction requestMoney(CbMoneyRequest req) {
        if(!req.getType().equals(TransactionType.REQUEST.toString().toLowerCase())){
            throw new CbApiException("Incorrect type '"+req.getType()+"' for requestMoney request.");
        }
        return sendMoneyRequest(req);
    }

    @Override
    public CbAddressTransaction transferMoney(CbMoneyRequest req) {
        if(!req.getType().equals(TransactionType.TRANSFER.toString().toLowerCase())){
            throw new CbApiException("Incorrect type '"+req.getType()+"' for transferMoney request.");
        }
        return sendMoneyRequest(req);
    }

    @Override
    public Collection<CbTrade> getTrades(String account) {
        return new CbTradeCollection().add(getTrades(account, Side.BUY))
                .add(getTrades(account, Side.SELL)).toCollection();
    }

    @Override
    public Collection<CbTrade> getTrades(String account, Side side, int maxRecords) {
        Collection<CbTrade> res = getPaginated(CbTradeListResponse.class, toUri(ACCOUNTS,account, side.getUri()), maxRecords);
        res.stream().forEach(t ->t.setSide(side));
        return res;
    }

    public Collection<CbTrade> getTrades(String account, Side side) {
        return getTrades(account, side, Integer.MAX_VALUE);
    }

    @Override
    public CbTime getServerTime() {
        return get(CbTimeResponse.class, TIME);
    }

    @Override
    public CbPrice getPrice(PriceType t, String pair){
        String uri = toUri(PRICES, pair, t.toString().toLowerCase());
        CbPrice res = get(CbPriceResponse.class, uri);
        res.setType(t);
        return res;
    }

    @Override
    public CbPrice getSpotPrice(String pair, LocalDate date){
        String uri = toUri(PRICES, pair, PriceType.SPOT.toString().toLowerCase());
        Map<String, String> params = null;
        if(date !=null){
            params = new HashMap<>();
            params.put(DATE, date.format(PRICE_DATE_FORMAT));
        }
        return get(CbPriceResponse.class, uri, params).setType(PriceType.SPOT);
    }

    @Override
    public List<CbCurrencyCode> getCurrencyCodes() {
        return getPaginated(CbCurrencyCodeListResponse.class, CURRENCIES, Integer.MAX_VALUE);
    }

    @Override
    public CbTrade getTrade(String account, String id, Side side) {
        return get(CbTradeResponse.class, toUri(ACCOUNTS,account, side.getUri(), id)).setSide(side);
    }

    @Override
    public Collection<CbCashTransaction> getCashTransaction(String account, CashTransactionType type) {
        return getCashTransaction(account, type, Integer.MAX_VALUE);
    }

    @Override
    public Collection<CbCashTransaction> getCashTransaction(String account, CashTransactionType type, int maxRecords) {
        return getPaginated(CbCashTransactionListResponse.class,
                toUri(ACCOUNTS,account, type.getUri()), maxRecords);
    }

    @Override
    public CbCashTransaction getCashTransaction(String account, String depositId, CashTransactionType type) {
        return get(CbCashTransactionResponse.class,
                toUri(ACCOUNTS,account, type.getUri(), depositId));
    }

    @Override
    public CbCashTransaction executeCashTransaction(CbCashTransactionRequest req) {
        return block(restApi.post(CbCashTransactionResponse.class,
                toUri(ACCOUNTS, req.getFrom(),req.getType().getUri()), req)).getData();
    }

    @Override
    public CbCashTransaction commitCashTransaction(String account, String id, CashTransactionType type) {
        String path = toUri(ACCOUNTS, account, type.getUri(), id, COMMIT);
        return block(restApi.post(CbCashTransactionResponse.class, path, null)).getData();
    }

    @Override
    public CbCashTransaction commitCashTransaction(CbCashTransaction t) {
        if(t.getCommitted() !=null && !t.getCommitted()){
            return block(restApi.post(CbCashTransactionResponse.class, toUri(t.getResourcePath(), COMMIT), null)).getData();
        }
        throw new CbApiException("The cash transaction is already committed.");
    }

    @Override
    public CbTrade placeOrder(CbOrderRequest req) {
        String path = toUri(ACCOUNTS, req.getFrom(),req.getSide().getUri());
        return block(restApi.post(CbTradeResponse.class, path, req)).getData().setSide(req.getSide());
    }

    @Override
    public CbTrade commitOrder(String account, String orderId, Side side) {
        String path = toUri(ACCOUNTS, account, side.getUri(), orderId, COMMIT);
        return block(restApi.post(CbTradeResponse.class, path, null)).getData().setSide(side);
    }

    @Override
    public CbTrade commitOrder(CbTrade t) {
        if(t.getCommitted() !=null && !t.getCommitted()){
            return block(restApi.post(CbTradeResponse.class, toUri(t.getResourcePath(), COMMIT), null)).getData().setSide(t.getSide());
        }
        throw new CbApiException("The trade is already committed.");
    }

    @Override
    public CbExchangeRate getExchangeRate() {
        return get(CbExchangeRateResponse.class, EXCHANGE_RATES);
    }

    @Override
    public CbExchangeRate getExchangeRate(String base) {
        Map<String, String> m = new HashMap<>();
        m.put("currency", base);
        return block(restApi.get(CbExchangeRateResponse.class, EXCHANGE_RATES, m)).getData();
    }

    public <T extends CbResponse> T block(Future<T> t){
        try {
            return t.get();
        } catch (InterruptedException e) {
            throw exception(e);
        } catch (ExecutionException e) {
            throw exception(e.getCause());
        }
    }

    CbApiException exception(Throwable e){
        if(e instanceof ClientErrorException && ((ClientErrorException)e).getResponse().hasEntity()){
            return new CbApiHttpException(((ClientErrorException)e).getResponse().readEntity(CbResponse.class));
        }
        else{
            return new CbApiException("Error interacting with the server", e);
        }
    }

    private String toUri(String ... s){
        String path = s[0];
        for(int i=1; i<s.length; i++){
            path = path+"/"+s[i];
        }
        return path;
    }
}
