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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * The main implementation of CoinbaseClient
 */
public class CoinbaseRestClient implements CoinbaseClient {
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
    private final String paginationSize;

    public CoinbaseRestClient(RestConnection restApi, int paginationSize) {
        this.restApi = restApi;
        this.paginationSize=Integer.toString(paginationSize);
    }

    private final <X, T extends CbResponse<X>> X get(Class<T> responseType, String path){
        return get(responseType, path, null);
    }

    private final <X, T extends CbResponse<X>> X get(Class<T> responseType, String path, Map<String, String> params){
        return restApi.get(responseType, path, params).getData();
    }

    private final <X, T extends CbPaginatedResponse<X>> List<X> getPaginated(Class<T> responseType, final String uri){
        ArrayList<X> results = new ArrayList<>();
        boolean loop = true;
        Map<String, String> from = new HashMap<>();
        from.put("limit",paginationSize);
        while(loop){
            T response = restApi.get(responseType, uri, from);
            from.clear();
            results.addAll(response.getData());
            loop =  (response.getPagination()!=null && response.getPagination().getNextUri()!=null);
            if(loop){
                from.put("limit",paginationSize);
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
    public CoinbaseClient clone() {
        return new CoinbaseRestClient(restApi.clone(),  Integer.parseInt(paginationSize));
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
    public List<CbAccount> getAccounts() {
        return getPaginated(CbAccountListResponse.class, ACCOUNTS);
    }

    @Override
    public CbAccount getAccount(String id) {

        return get(CbAccountResponse.class, toUri(ACCOUNTS,id));
    }

    @Override
    public List<CbPaymentMethod> getPaymentMethods() {
        return getPaginated(CbPaymentMethodListResponse.class, PAYMENT_METHODS);
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
        return restApi.put(CbUserResponse.class, USER, u ).getData();
    }

    @Override
    public CbAccount updateAccountName(CbAccountUpdateRequest req) {
        return restApi.put(CbAccountResponse.class, toUri(ACCOUNTS,req.getAccount()), req).getData();
    }

    @Override
    public boolean deleteAccount(String id) {
        return restApi.delete(toUri(ACCOUNTS,id));
    }

    @Override
    public List<CbAddress> getAddresses(String id) {
        return getPaginated(CbAddressListResponse.class, toUri(ACCOUNTS,id, ADDRESSES));
    }

    @Override
    public CbAddress getAddress(String account, String addressId) {
        return get(CbAddressResponse.class, toUri(ACCOUNTS,account, ADDRESSES, addressId));
    }

    @Override
    public CbAddress createAddress(CbCreateAddressRequest request) {
        return restApi.post(CbAddressResponse.class,
                toUri(ACCOUNTS,request.getAccount(), ADDRESSES), request).getData();
    }

    @Override
    public List<CbAddressTransaction> getTransactions(String accountId, String address) {
        return getPaginated(CbAddressTransactionsResponse.class,
                toUri(ACCOUNTS,accountId, ADDRESSES, address, TRANSACTIONS));
    }

    private CbAddressTransaction sendMoneyRequest(CbMoneyRequest req) {
        return restApi.post(CbAddressTransactionResponse.class,
                toUri(ACCOUNTS,req.getFrom(), TRANSACTIONS), req).getData();
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
    public Collection<CbTrade> getTrades(String account, Side side) {
        Collection<CbTrade> res = getPaginated(CbTradeListResponse.class, toUri(ACCOUNTS,account, side.getUri()));
        res.stream().forEach(t ->t.setSide(side));
        return res;
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
        return getPaginated(CbCurrencyCodeListResponse.class, CURRENCIES);
    }

    @Override
    public CbTrade getTrade(String account, String id, Side side) {
        return get(CbTradeResponse.class, toUri(ACCOUNTS,account, side.getUri(), id)).setSide(side);
    }

    @Override
    public Collection<CbCashTransaction> getCashTransaction(String account, CashTransactionType type) {
        return getPaginated(CbCashTransactionListResponse.class,
                toUri(ACCOUNTS,account, type.getUri()));
    }

    @Override
    public CbCashTransaction getCashTransaction(String account, String depositId, CashTransactionType type) {
        return get(CbCashTransactionResponse.class,
                toUri(ACCOUNTS,account, type.getUri(), depositId));
    }

    @Override
    public CbCashTransaction executeCashTransaction(CbCashTransactionRequest req) {
        return restApi.post(CbCashTransactionResponse.class,
                toUri(ACCOUNTS, req.getFrom(),req.getType().getUri()), req).getData();
    }

    @Override
    public CbCashTransaction commitCashTransaction(String account, String id, CashTransactionType type) {
        String path = toUri(ACCOUNTS, account, type.getUri(), id, COMMIT);
        return restApi.post(CbCashTransactionResponse.class, path).getData();
    }

    @Override
    public CbCashTransaction commitCashTransaction(CbCashTransaction t) {
        if(t.getCommitted() !=null && !t.getCommitted()){
            return restApi.post(CbCashTransactionResponse.class, toUri(t.getResourcePath(), COMMIT)).getData();
        }
        throw new CbApiException("The cash transaction is already committed.");
    }

    @Override
    public CbTrade placeOrder(CbOrderRequest req) {
        String path = toUri(ACCOUNTS, req.getFrom(),req.getSide().getUri());
        return restApi.post(CbTradeResponse.class, path, req).getData().setSide(req.getSide());
    }

    @Override
    public CbTrade commitOrder(String account, String orderId, Side side) {
        String path = toUri(ACCOUNTS, account, side.getUri(), orderId, COMMIT);
        return restApi.post(CbTradeResponse.class, path).getData().setSide(side);
    }

    @Override
    public CbTrade commitOrder(CbTrade t) {
        if(t.getCommitted() !=null && !t.getCommitted()){
            return restApi.post(CbTradeResponse.class, toUri(t.getResourcePath(), COMMIT)).getData().setSide(t.getSide());
        }
        throw new CbApiException("The trade is already committed.");
    }

    @Override
    public CbExchangeRate getBTCExchangeRate() {
        return get(CbExchangeRateResponse.class, EXCHANGE_RATES);
    }

    private String toUri(String ... s){
        String path = s[0];
        for(int i=1; i<s.length; i++){
            path = path+"/"+s[i];
        }
        return path;
    }
}
