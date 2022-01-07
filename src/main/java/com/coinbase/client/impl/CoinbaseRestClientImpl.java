package com.coinbase.client.impl;

import com.coinbase.client.CoinbaseRestClient;
import com.coinbase.client.security.RequestAuthenticationFilter;
import com.coinbase.domain.account.CbAccount;
import com.coinbase.domain.account.request.CbAccountUpdateRequest;
import com.coinbase.domain.account.response.CbAccountResponse;
import com.coinbase.domain.address.CbAddress;
import com.coinbase.domain.address.CbAddressTransaction;
import com.coinbase.domain.address.request.CbCreateAddressRequest;
import com.coinbase.domain.address.response.CbAddressResponse;
import com.coinbase.domain.address.response.CbAddressTransactionResponse;
import com.coinbase.domain.general.response.ResponseBody;
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
import com.coinbase.domain.trade.response.CbCashTransactionResponse;
import com.coinbase.domain.trade.response.CbTradeResponse;
import com.coinbase.domain.transaction.TransactionType;
import com.coinbase.domain.transaction.payment.CbPaymentMethod;
import com.coinbase.domain.transaction.request.CbMoneyRequest;
import com.coinbase.domain.transaction.response.CbPaymentMethodResponse;
import com.coinbase.domain.user.request.CbUserUpdateRequest;
import com.coinbase.domain.user.response.CbUserResponse;
import com.coinbase.exception.CbApiException;

import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import java.util.function.Supplier;
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
 * The main implementation of the blocking CoinbaseRestClient.
 *
 * @author antlen
 */
public class CoinbaseRestClientImpl extends AbstractCoinbaseRestClient implements CoinbaseRestClient {

    public CoinbaseRestClientImpl(RequestAuthenticationFilter filter, int pageSize) {
        super(filter,pageSize);
    }

    private <V, T extends CbPaginatedResponse<V>> List<V> paginate(List<V> results, int max,
                                                                   Function<String, CompletionStage<T>> t, String value){
        CbPaginatedResponse<V> res = t.apply(value).toCompletableFuture().join();
        results.addAll(res.getData());
        String next = getNext(res);
        int nextMax = max - res.getData().size();
        if(nextMax> 0 && next != null){
            paginate(results,nextMax, t, next);
        }
        return results;
    }

    private <T> T invoke(Supplier<CompletionStage<T>> t){
        return t.get().toCompletableFuture().join();
    }


    @Override
    public void ping() {
        getServerTime();
    }

    @Override
    public CbUserResponse getUser() {
        return invoke(()->service.getUser());
    }

    @Override
    public CbUserResponse getUser(String userId) {
        return invoke(()->service.getUser(userId));
    }

    @Override
    public ResponseBody<List<CbPaymentMethod>> getPaymentMethods() {
        return getPaymentMethods(Integer.MAX_VALUE);
    }

    @Override
    public ResponseBody<List<CbPaymentMethod>> getPaymentMethods(int maxRecords) {
        return () -> paginate(new ArrayList<>(),maxRecords, next -> service.getPaymentMethods(pageSize,next), null);
    }

    @Override
    public CbPaymentMethodResponse getPaymentMethod(String id) {
        return invoke(()-> service.getPaymentMethod(id));
    }

    @Override
    public CbUserResponse updateUser(CbUserUpdateRequest u) {
        return invoke(()->service.updateUser(u));
    }

    @Override
    public ResponseBody<List<CbAccount>> getAccounts() {
        return getAccounts(Integer.MAX_VALUE);
    }

    @Override
    public ResponseBody<List<CbAccount>> getAccounts(int maxRecords) {
        return () -> paginate(new ArrayList<>(),maxRecords,
                next -> service.getAccounts(pageSize, next), null);
    }

    @Override
    public CbAccountResponse getAccount(String id) {
        return invoke(()->service.getAccount(id));
    }

    @Override
    public CbAccountResponse updateAccountName(String account, CbAccountUpdateRequest req) {
        return invoke(()->service.updateAccountName(account, req));
    }

    @Override
    public boolean deleteAccount(String id) {
        return invoke(()->service.deleteAccount(id)).getStatusInfo().toEnum() == Response.Status.OK;
    }

    @Override
    public ResponseBody<List<CbAddress>> getAddresses(String id) {
        return getAddresses(id, Integer.MAX_VALUE);
    }

    @Override
    public ResponseBody<List<CbAddress>> getAddresses(String id, int maxRecords) {
        return () -> paginate(new ArrayList<>(), maxRecords,
                next -> service.getAddresses(id, pageSize, next), null);
    }

    @Override
    public CbAddressResponse getAddress(String account, String addressId) {
        return invoke(()->service.getAddress(account, addressId));
    }

    @Override
    public CbAddressResponse createAddress(String account, CbCreateAddressRequest request) {
        return invoke(()->service.createAddress(account,request));
    }

    @Override
    public ResponseBody<List<CbAddressTransaction>> getTransactions(String accountId, String address) {
        return getTransactions(accountId, address, Integer.MAX_VALUE);
    }

    @Override
    public ResponseBody<List<CbAddressTransaction>> getTransactions(String accountId, String address, int maxRecords) {
        return () -> paginate(new ArrayList<>(), maxRecords,
                next -> service.getTransactions(accountId, address, pageSize, next), null);
    }

    @Override
    public CbTimeResponse getServerTime() {
        return invoke(()->service.getServerTime());
    }

    @Override
    public CbPriceResponse getPrice( String ticker, PriceType t) {
        return invoke(()->service.getPrice(ticker, t.getName()));
    }

    @Override
    public CbPriceResponse getSpotPrice(String ticker, LocalDate date) {
        return invoke(()->service.getSpotPrice(ticker, date.format(PRICE_DATE_FORMAT)));
    }

    @Override
    public CbCurrencyCodeListResponse getCurrencyCodes() {
        return invoke(()->service.getCurrencyCodes());
    }

    @Override
    public CbExchangeRateResponse getExchangeRate(String base) {
        return invoke(()->service.getExchangeRate(base));
    }

    @Override
    public CbExchangeRateResponse getExchangeRate() {
        return invoke(()->service.getExchangeRate());
    }

    private CbAddressTransactionResponse sendMoneyRequest(String account, CbMoneyRequest req, TransactionType type) {
        if( type != null&& !req.getType().equals(type.getName())){
            throw new CbApiException("Incorrect type '"+req.getType()+"' for this request.");
        }
        return invoke(()->service.sendMoneyRequest(account, req));
    }

    @Override
    public CbAddressTransactionResponse sendMoney(String account, CbMoneyRequest req) {
        return sendMoneyRequest(account, req, null);
    }

    @Override
    public CbAddressTransactionResponse requestMoney(String account, CbMoneyRequest req) {
        return sendMoneyRequest(account, req, TransactionType.REQUEST);
    }

    @Override
    public CbAddressTransactionResponse transferMoney(String account, CbMoneyRequest req) {
        return sendMoneyRequest(account, req, TransactionType.TRANSFER);
    }

    @Override
    public CbAddressTransactionResponse sendMoneyRequest(String account, CbMoneyRequest req) {
        return sendMoneyRequest(account, req, TransactionType.SEND);
    }

    @Override
    public ResponseBody<List<CbTrade>> getTrades(String account, Side side) {
        return getTrades(account, side, Integer.MAX_VALUE);
    }

    @Override
    public ResponseBody<List<CbTrade>> getTrades(String account, Side side, int maxRecords) {
        return () -> paginate(new ArrayList<>(), maxRecords,
                next -> service.getTrades(account, side.getUri(),pageSize, next), null);
    }

    @Override
    public CbTradeResponse getTrade(String account, String id, Side side) {
        return invoke(()->service.getTrade(account, side.getUri(), id));
    }

    @Override
    public ResponseBody<List<CbCashTransaction>> getCashTransactions(String account, CashTransactionType type) {
        return getCashTransactions(account, type, Integer.MAX_VALUE);
    }

    @Override
    public ResponseBody<List<CbCashTransaction>> getCashTransactions(String account, CashTransactionType type, int maxRecords) {
        return () -> paginate(new ArrayList<>(), maxRecords,
                next -> service.getCashTransactions(account, type.getUri(),pageSize,next), null);
    }

    @Override
    public CbCashTransactionResponse getCashTransaction(String account, String id, CashTransactionType type) {
        return invoke(()->service.getCashTransaction(account, type.getUri(), id));
    }

    @Override
    public CbCashTransactionResponse executeCashTransaction(CbCashTransactionRequest req, CashTransactionType type) {
        return invoke(()->service.executeCashTransaction(type.getUri(), req));
    }

    @Override
    public CbCashTransactionResponse commitCashTransaction(String account, String id, CashTransactionType type) {
        return invoke(()->service.commitCashTransaction(account, type.getUri(), id));
    }

    private CbTradeResponse placeOrder(String account, Side side, CbOrderRequest request) {
        return invoke(()->service.placeOrder(account,side.getUri(), request));
    }

    @Override
    public CbTradeResponse placeBuyOrder(String account, CbOrderRequest request) {
        return placeOrder(account,Side.BUY, request);
    }

    @Override
    public CbTradeResponse placeSellOrder(String account, CbOrderRequest request) {
        return placeOrder(account,Side.SELL, request);
    }

    @Override
    public CbTradeResponse commitOrder(String account, String orderId, Side side) {
        return invoke(()->service.commitOrder(account, side.getUri(), orderId));
    }
}
