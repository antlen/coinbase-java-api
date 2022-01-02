package com.coinbase.client.api;

import com.coinbase.client.api.request.*;
import com.coinbase.client.connection.RestConnection;
import com.coinbase.domain.account.request.CbAccountUpdateRequest;
import com.coinbase.domain.account.response.CbAccountListResponse;
import com.coinbase.domain.account.response.CbAccountResponse;
import com.coinbase.domain.address.request.CbCreateAddressRequest;
import com.coinbase.domain.address.response.CbAddressListResponse;
import com.coinbase.domain.address.response.CbAddressResponse;
import com.coinbase.domain.address.response.CbAddressTransactionListResponse;
import com.coinbase.domain.address.response.CbAddressTransactionResponse;
import com.coinbase.domain.general.response.CbResponse;
import com.coinbase.domain.order.request.CbOrderRequest;
import com.coinbase.domain.pagination.response.CbPaginatedResponse;
import com.coinbase.domain.price.PriceType;
import com.coinbase.domain.price.response.CbCurrencyCodeListResponse;
import com.coinbase.domain.price.response.CbExchangeRateResponse;
import com.coinbase.domain.price.response.CbPriceResponse;
import com.coinbase.domain.system.response.CbTimeResponse;
import com.coinbase.domain.trade.CashTransactionType;
import com.coinbase.domain.trade.CbCashTransaction;
import com.coinbase.domain.trade.CbTrade;
import com.coinbase.domain.trade.Side;
import com.coinbase.domain.trade.request.CbCashTransactionRequest;
import com.coinbase.domain.trade.response.CbCashTransactionListResponse;
import com.coinbase.domain.trade.response.CbCashTransactionResponse;
import com.coinbase.domain.trade.response.CbTradeListResponse;
import com.coinbase.domain.trade.response.CbTradeResponse;
import com.coinbase.domain.transaction.request.CbMoneyRequest;
import com.coinbase.domain.transaction.response.CbPaymentMethodListResponse;
import com.coinbase.domain.transaction.response.CbPaymentMethodResponse;
import com.coinbase.domain.user.request.CbUserUpdateRequest;
import com.coinbase.domain.user.response.CbUserResponse;
import com.coinbase.exception.CbApiException;
import com.coinbase.exception.CbApiHttpException;

import javax.ws.rs.ClientErrorException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
 * Coinbase implementation for all api calls
 *
 * @author antlen
 */
public class CoinbaseDefaultRequestApi implements CoinbaseRequestApi {
    protected static DateTimeFormatter PRICE_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    protected static final String CURRENCY = "currency";
    protected static final String ACCOUNTS = "/accounts";
    protected static final String PAYMENT_METHODS = "/payment-methods";
    protected static final String USER = "/user";
    protected static final String USERS = "/users";
    protected static final String AUTH = "/auth";
    protected static final String ADDRESSES = "addresses";
    protected static final String TIME = "/time";
    protected static final String PRICES = "/prices";
    protected static final String TRANSACTIONS = "transactions";
    protected static final String DATE = "date";
    protected static final String CURRENCIES = "/currencies";
    protected static final String EXCHANGE_RATES = "/exchange-rates";
    protected static final String COMMIT = "commit";

    protected final RestConnection restApi;

    public CoinbaseDefaultRequestApi(RestConnection restApi) {
        this.restApi = restApi;
    }

    public void reconnect() {
        restApi.reconnect();
    }

    public void setLogResponsesEnabled(boolean b){
        restApi.setLogJsonMessages(b);
    }

    protected CbApiException exception(Throwable e){
        if(e instanceof ClientErrorException && ((ClientErrorException)e).getResponse().hasEntity()){
            return new CbApiHttpException(((ClientErrorException)e).getResponse().readEntity(CbResponse.class));
        }
        else{
            return new CbApiException("Error interacting with the server", e);
        }
    }

    protected String toUri(String ... s){
        String path = s[0];
        for(int i=1; i<s.length; i++){
            path = path+"/"+s[i];
        }
        return path;
    }

    @Override
    public void ping() {
        serverTimeRequest().sync();
    }

    private <O extends CbResponse> GetRequest<O> get(Class<O> c, String uri) {
        return restApi.get(c, uri);
    }

    private <O extends CbPaginatedResponse<?>> PaginatedGetRequest<O> paginate(Class<O> c, String uri) {
        return restApi.paginatedGet(c, uri);
    }

    private <I,O extends CbResponse> PostRequest<I, O> post(Class<O> c, String uri) {
        return restApi.post(c, uri);
    }

    private <I,O extends CbResponse> PutRequest<I,O> put(Class<O> c, String uri) {
        return restApi.put(c, uri);
    }

    @Override
    public GetRequest<CbUserResponse> userRequest() {
        return get(CbUserResponse.class, USER);
    }

    @Override
    public GetRequest<CbUserResponse> userRequest(String userId) {
        return get(CbUserResponse.class, toUri(USERS, userId));
    }

    @Override
    public PaginatedGetRequest<CbPaymentMethodListResponse> paymentMethodsRequest() {
        return paginate(CbPaymentMethodListResponse.class, PAYMENT_METHODS);
    }

    @Override
    public GetRequest<CbPaymentMethodResponse> paymentMethodRequest(String id) {
        return get(CbPaymentMethodResponse.class, toUri(PAYMENT_METHODS,id));
    }

    @Override
    public PutRequest<CbUserUpdateRequest,CbUserResponse> updateUserRequest() {
        return put(CbUserResponse.class, USER);
    }

    @Override
    public PaginatedGetRequest<CbAccountListResponse> accountsRequest() {
        return paginate(CbAccountListResponse.class, ACCOUNTS);
    }

    @Override
    public GetRequest<CbAccountResponse> accountRequest(String id)  {
        return get(CbAccountResponse.class, toUri(ACCOUNTS,id));
    }

    @Override
    public PutRequest<CbAccountUpdateRequest, CbAccountResponse> updateAccountNameRequest(String id) {
        return put(CbAccountResponse.class, toUri(ACCOUNTS,id));
    }

    @Override
    public DeleteRequest deleteAccount(String id) {
        return restApi.delete(toUri(ACCOUNTS,id));
    }

    @Override
    public PaginatedGetRequest<CbAddressListResponse> addressesRequest(String id) {
        return paginate(CbAddressListResponse.class, toUri(ACCOUNTS,id, ADDRESSES));
    }

    @Override
    public GetRequest<CbAddressResponse> addressRequest(String account, String addressId) {
        return get(CbAddressResponse.class, toUri(ACCOUNTS,account, ADDRESSES, addressId));
    }

    @Override
    public PostRequest<CbCreateAddressRequest, CbAddressResponse> createAddressRequest(String account) {
        return post(CbAddressResponse.class, toUri(ACCOUNTS,account, ADDRESSES));
    }

    @Override
    public PaginatedGetRequest<CbAddressTransactionListResponse> transactionsRequest(String accountId, String address) {
        return paginate(CbAddressTransactionListResponse.class, toUri(ACCOUNTS,accountId, ADDRESSES, address, TRANSACTIONS));
    }

    @Override
    public GetRequest<CbTimeResponse> serverTimeRequest() {
        return get(CbTimeResponse.class, TIME);
    }

    @Override
    public GetRequest<CbPriceResponse> priceRequest(PriceType t, String pair) {
        return get(CbPriceResponse.class, toUri(PRICES, pair, t.getName()));
    }

    @Override
    public GetRequest<CbPriceResponse> spotPriceRequest(String pair, LocalDate date) {
        GetRequest<CbPriceResponse> req = get(CbPriceResponse.class, toUri(PRICES, pair, PriceType.SPOT.getName()));
        if(date != null){
            req.queryParam(DATE,date.format(PRICE_DATE_FORMAT));
        }
        return req;
    }

    @Override
    public GetRequest<CbCurrencyCodeListResponse> currencyCodesRequest() {
        return get(CbCurrencyCodeListResponse.class,CURRENCIES);
    }

    @Override
    public GetRequest<CbExchangeRateResponse> exchangeRateRequest(String base) {
        GetRequest<CbExchangeRateResponse> req = exchangeRateRequest();
        if(base !=null){
            req.queryParam(CURRENCY, base);
        }
        return req;
    }

    @Override
    public GetRequest<CbExchangeRateResponse> exchangeRateRequest() {
        return get(CbExchangeRateResponse.class, EXCHANGE_RATES);
    }

    @Override
    public PostRequest<CbMoneyRequest, CbAddressTransactionResponse> createSendMoneyRequest(String account) {
        return post(CbAddressTransactionResponse.class, toUri(ACCOUNTS,account, TRANSACTIONS));
    }

    @Override
    public PaginatedGetRequest<CbTradeListResponse> tradesRequest(String account, Side side) {
        return paginate(CbTradeListResponse.class,toUri(ACCOUNTS,account, side.getUri()));
    }

    @Override
    public GetRequest<CbTradeResponse> tradeRequest(String account, String id, Side side) {
        return get(CbTradeResponse.class,  toUri(ACCOUNTS,account, side.getUri(), id));
    }

    @Override
    public PaginatedGetRequest<CbCashTransactionListResponse> cashTransactionsRequest(String account, CashTransactionType type) {
        return paginate(CbCashTransactionListResponse.class, toUri(ACCOUNTS,account, type.getUri()));
    }

    @Override
    public GetRequest<CbCashTransactionResponse> cashTransactionRequest(String account, String id, CashTransactionType type) {
        return get(CbCashTransactionResponse.class, toUri(ACCOUNTS,account,type.getUri(), id));
    }

    @Override
    public PostRequest<CbCashTransactionRequest, CbCashTransactionResponse> executeCashTransaction(String account, CashTransactionType type) {
        return post(CbCashTransactionResponse.class, toUri(ACCOUNTS, account,type.getUri()));
    }

    @Override
    public PostRequest<?, CbCashTransactionResponse> commitCashTransaction(String account, String id, CashTransactionType type) {
        return post(CbCashTransactionResponse.class, toUri(ACCOUNTS, account, type.getUri(), id, COMMIT));
    }

    @Override
    public PostRequest<?, CbCashTransactionResponse> commitCashTransaction(CbCashTransaction t) {
        if(t.getCommitted() !=null && t.getCommitted()) {
            throw new CbApiException("The cash transaction is already committed.");
        }
        return post(CbCashTransactionResponse.class,toUri(t.getResourcePath(), COMMIT));
    }

    @Override
    public PostRequest<CbOrderRequest, CbTradeResponse> createBuyOrderRequest(String account) {
        return post(CbTradeResponse.class, toUri(ACCOUNTS, account,Side.BUY.getUri()));
    }

    @Override
    public PostRequest<CbOrderRequest, CbTradeResponse> createSellOrderRequest(String account) {
        return post(CbTradeResponse.class, toUri(ACCOUNTS, account,Side.SELL.getUri()));
    }

    @Override
    public PostRequest<CbTrade, CbTradeResponse> commitOrderRequest(CbTrade t) {
        if(t.getCommitted() !=null && t.getCommitted()) {
            throw new CbApiException("The order is already committed.");
        }
        return post(CbTradeResponse.class, toUri(t.getResourcePath(), COMMIT));
    }

    @Override
    public PostRequest<?, CbTradeResponse> commitOrderRequest(String account, String orderId, Side side) {
        return post(CbTradeResponse.class, toUri(ACCOUNTS, account, side.getUri(), orderId, COMMIT));
    }
}
