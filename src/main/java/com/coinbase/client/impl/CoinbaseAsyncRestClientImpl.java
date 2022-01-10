package com.coinbase.client.impl;

import com.coinbase.callback.CoinbaseCallback;
import com.coinbase.callback.SafeDelegatingCallback;
import com.coinbase.client.CoinbaseAsyncRestClient;
import com.coinbase.client.security.RequestAuthenticationFilter;
import com.coinbase.domain.account.request.CbAccountUpdateRequest;
import com.coinbase.domain.account.response.CbAccountListResponse;
import com.coinbase.domain.account.response.CbAccountResponse;
import com.coinbase.domain.address.request.CbCreateAddressRequest;
import com.coinbase.domain.address.response.CbAddressListResponse;
import com.coinbase.domain.address.response.CbAddressResponse;
import com.coinbase.domain.address.response.CbAddressTransactionListResponse;
import com.coinbase.domain.address.response.CbAddressTransactionResponse;
import com.coinbase.domain.order.request.CbOrderRequest;
import com.coinbase.domain.pagination.response.CbPaginatedResponse;
import com.coinbase.domain.price.PriceType;
import com.coinbase.domain.price.response.CbCurrencyCodeListResponse;
import com.coinbase.domain.price.response.CbExchangeRateResponse;
import com.coinbase.domain.price.response.CbPriceResponse;
import com.coinbase.domain.system.response.CbTimeResponse;
import com.coinbase.domain.trade.CashTransactionType;
import com.coinbase.domain.trade.Side;
import com.coinbase.domain.trade.request.CbCashTransactionRequest;
import com.coinbase.domain.trade.response.CbCashTransactionListResponse;
import com.coinbase.domain.trade.response.CbCashTransactionResponse;
import com.coinbase.domain.trade.response.CbTradeListResponse;
import com.coinbase.domain.trade.response.CbTradeResponse;
import com.coinbase.domain.transaction.TransactionType;
import com.coinbase.domain.transaction.request.CbMoneyRequest;
import com.coinbase.domain.transaction.response.CbPaymentMethodListResponse;
import com.coinbase.domain.transaction.response.CbPaymentMethodResponse;
import com.coinbase.domain.user.request.CbUserUpdateRequest;
import com.coinbase.domain.user.response.CbUserResponse;
import com.coinbase.exception.CbApiException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.concurrent.*;
import java.util.function.Function;
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
 * The main implementation of CoinbaseAsyncRestClient.  The requests are processed asynchronously by the http engine and
 * the response will either be processed on a http engine thread or handed off to the ExecutorService if provided.
 *
 * @author antlen
 */
public class CoinbaseAsyncRestClientImpl extends AbstractCoinbaseRestClient implements CoinbaseAsyncRestClient {
    private final ExecutorService responseService;

    public CoinbaseAsyncRestClientImpl(RequestAuthenticationFilter filter, int pageSize) {
        this(filter, null, pageSize);
    }

    public CoinbaseAsyncRestClientImpl(RequestAuthenticationFilter filter, ExecutorService responseService, int pageSize) {
       super(filter, pageSize);
        this.responseService = responseService;
    }

    private <T> CompletableFuture<T> exceptionally(CompletableFuture<T> f, CoinbaseCallback<?> cb){
        return f.exceptionally(throwable -> {
            if(cb != null){
                if(responseService != null){
                    //send to the thread pool for execution
                    responseService.submit(()->cb.failed(throwable));
                }else{
                    //run on this thread
                    cb.failed(throwable);
                }
            }else{
                throwable.printStackTrace();
            }
            return null;
        });
    }

    private <T> CompletionStage<T> invoke(CoinbaseCallback<T> callback, Function<String, CompletionStage<T>>  t){
        CompletableFuture<T>  s  =t.apply(null).toCompletableFuture();
        return exceptionally( s.thenApplyAsync(t1 -> {
            if(callback!=null) {
                callback.onResponse(t1, false);
            }
            return t1;
        }, responseService != null? responseService : s.defaultExecutor()), callback);
    }

    public <T extends CbPaginatedResponse> CompletableFuture<List<T>> paginate(CoinbaseCallback<T> cb, int max,
                                                                               Function<String, CompletionStage<T>> func) {
        CompletableFuture<T>  s  =func.apply(null).toCompletableFuture();

        //run the pagination on the request thread then delegate to the response thread once the results are in.
        return exceptionally( s.thenApplyAsync(v ->
                handlePagination(cb, new ArrayList<>(), v, max, func)), cb)
                .thenApplyAsync(t -> t, responseService != null? responseService : s.defaultExecutor());
    }

    private <T extends CbPaginatedResponse> List<T> handlePagination(CoinbaseCallback<T> cb,
                                                                     List<T> results, T v, int max, Function<String, CompletionStage<T>> func){
        results.add(v);
        int nextMax = max - v.getData().size();
        final String next = getNext(v);
        if(cb != null) {
            responseService.submit( ()->  cb.onResponse(v, next != null));
        }
        if (nextMax > 0 && next != null){
            return handlePagination(cb, results, func.apply(next).toCompletableFuture().join(), nextMax, func);
        }
        return results;
    }

    @Override
    public void ping(CoinbaseCallback<Boolean> cb) {
        invoke(new SafeDelegatingCallback<>(cb) {
            @Override
            protected void onFailsafeResponse(CbTimeResponse aBoolean, boolean moreToCome) {
                cb.onResponse(true, false);
            }
        }, t -> service.getServerTime());
    }

    @Override
    public CompletableFuture<CbUserResponse> fetchUser(CoinbaseCallback<CbUserResponse> cb) {
        return invoke(cb, result -> service.getUser()).toCompletableFuture();
    }

    @Override
    public CompletableFuture<CbUserResponse> fetchUser(CoinbaseCallback<CbUserResponse> cb, String userId) {
        return invoke(cb, result -> service.getUser(userId)).toCompletableFuture();
    }

    @Override
    public CompletableFuture<List<CbPaymentMethodListResponse>> fetchPaymentMethods(CoinbaseCallback<CbPaymentMethodListResponse> cb) {
        return fetchPaymentMethods(Integer.MAX_VALUE, cb);
    }

    @Override
    public CompletableFuture<List<CbPaymentMethodListResponse>> fetchPaymentMethods(int maxRecords, CoinbaseCallback<CbPaymentMethodListResponse> cb) {
        return paginate(cb, maxRecords,
                before -> service.getPaymentMethods(pageSize,before));
    }

    @Override
    public CompletableFuture<CbPaymentMethodResponse> fetchPaymentMethod(String id, CoinbaseCallback<CbPaymentMethodResponse> cb) {
        return invoke(cb, result -> service.getPaymentMethod(id)).toCompletableFuture();
    }

    @Override
    public CompletableFuture<CbUserResponse> updateUser(CbUserUpdateRequest req, CoinbaseCallback<CbUserResponse> cb) {
        return invoke(cb, result -> service.updateUser(req)).toCompletableFuture();
    }

    @Override
    public CompletableFuture<List<CbAccountListResponse>> fetchAccounts(int maxRecords, CoinbaseCallback<CbAccountListResponse> cb) {
        return paginate(cb, maxRecords,
                before -> service.getAccounts(pageSize,before));
    }

    @Override
    public CompletableFuture<List<CbAccountListResponse>> fetchAccounts(CoinbaseCallback<CbAccountListResponse> cb) {
        return fetchAccounts(Integer.MAX_VALUE, cb);
    }


    @Override
    public CompletableFuture<CbAccountResponse> fetchAccount(String id, CoinbaseCallback<CbAccountResponse> cb) {
        return invoke(cb, result -> service.getAccount(id)).toCompletableFuture();
    }

    @Override
    public CompletableFuture<CbAccountResponse> updateAccountName(String account, CbAccountUpdateRequest req, CoinbaseCallback<CbAccountResponse> cb) {
        return invoke(cb, result -> service.updateAccountName(account, req)).toCompletableFuture();
    }

    @Override
    public CompletableFuture<Response> deleteAccount(String id, CoinbaseCallback<Response> cb) {
        return invoke(cb, result -> service.deleteAccount(id)).toCompletableFuture();
    }

    @Override
    public CompletableFuture<List<CbAddressListResponse>> fetchAddresses(String id, CoinbaseCallback<CbAddressListResponse> cb) {
        return fetchAddresses(id, Integer.MAX_VALUE, cb);
    }

    @Override
    public CompletableFuture<List<CbAddressListResponse>> fetchAddresses(String id, int maxRecords, CoinbaseCallback<CbAddressListResponse> cb) {
        return paginate(cb,maxRecords,
                next -> service.getAddresses(id, pageSize, next));
    }

    @Override
    public CompletableFuture<CbAddressResponse> fetchAddress(String account, String addressId, CoinbaseCallback<CbAddressResponse> cb) {
        return invoke(cb, result -> service.getAddress(account, addressId)).toCompletableFuture();
    }

    @Override
    public CompletableFuture<CbAddressResponse> createAddress(String accountId, CbCreateAddressRequest request, CoinbaseCallback<CbAddressResponse> cb) {
        return invoke(cb, result -> service.createAddress(accountId, request)).toCompletableFuture();
    }

    @Override
    public CompletableFuture<List<CbAddressTransactionListResponse>> fetchTransactions(String accountId, String address, CoinbaseCallback<CbAddressTransactionListResponse> cb) {
       return fetchTransactions(accountId, address,Integer.MAX_VALUE, cb);
    }

    @Override
    public CompletableFuture<List<CbAddressTransactionListResponse>> fetchTransactions(String accountId, String address, int maxRecords, CoinbaseCallback<CbAddressTransactionListResponse> cb) {
        return paginate(cb,maxRecords,
                s -> service.getTransactions(accountId, address, pageSize, s));
    }

    @Override
    public CompletableFuture<CbTimeResponse> fetchServerTime(CoinbaseCallback<CbTimeResponse> cb) {
        return invoke(cb, result -> service.getServerTime()).toCompletableFuture();
    }

    @Override
    public CompletableFuture<CbPriceResponse> fetchPrice(PriceType t, String ticker, CoinbaseCallback<CbPriceResponse> cb) {
        return invoke(cb, result -> service.getPrice(ticker, t.getName())).toCompletableFuture();
    }

    @Override
    public CompletableFuture<CbPriceResponse> fetchSpotPrice(String ticker, LocalDate date, CoinbaseCallback<CbPriceResponse> cb) {
        return invoke(cb,
                result -> service.getSpotPrice(ticker, date.format(PRICE_DATE_FORMAT))).toCompletableFuture();
    }

    @Override
    public CompletableFuture<CbCurrencyCodeListResponse> fetchCurrencyCodes(CoinbaseCallback<CbCurrencyCodeListResponse> cb) {
        return invoke(cb, result -> service.getCurrencyCodes()).toCompletableFuture();
    }

    @Override
    public CompletableFuture<CbExchangeRateResponse> fetchExchangeRate(String base, CoinbaseCallback<CbExchangeRateResponse> cb) {
        return invoke(cb, result -> service.getExchangeRate(base)).toCompletableFuture();
    }

    @Override
    public CompletableFuture<CbExchangeRateResponse> fetchExchangeRate(CoinbaseCallback<CbExchangeRateResponse> cb) {
        return invoke(cb, result -> service.getExchangeRate()).toCompletableFuture();
    }

    @Override
    public CompletableFuture<CbAddressTransactionResponse> sendMoney(String account, CbMoneyRequest req, CoinbaseCallback<CbAddressTransactionResponse> cb) {
        return sendMoneyRequest(account, req, TransactionType.SEND, cb).toCompletableFuture();
    }

    @Override
    public CompletableFuture<CbAddressTransactionResponse> requestMoney(String account, CbMoneyRequest req, CoinbaseCallback<CbAddressTransactionResponse> cb) {
        return sendMoneyRequest(account, req, TransactionType.REQUEST, cb).toCompletableFuture();
    }

    @Override
    public CompletableFuture<CbAddressTransactionResponse> transferMoney(String account, CbMoneyRequest req, CoinbaseCallback<CbAddressTransactionResponse> cb) {
        return sendMoneyRequest(account, req, TransactionType.TRANSFER, cb).toCompletableFuture();
    }

    @Override
    public CompletableFuture<CbAddressTransactionResponse> sendMoneyRequest(String account, CbMoneyRequest req, CoinbaseCallback<CbAddressTransactionResponse> cb) {
        return sendMoneyRequest(account, req, null, cb).toCompletableFuture();
    }

    private CompletableFuture<CbAddressTransactionResponse>  sendMoneyRequest(String account, CbMoneyRequest req,
                                                                   TransactionType type, CoinbaseCallback<CbAddressTransactionResponse> cb) {
        if( type != null&& !req.getType().equals(type.getName())){
            throw new CbApiException("Incorrect type '"+req.getType()+"' for this request.");
        }
        return  invoke(cb, result -> service.sendMoneyRequest(account, req)).toCompletableFuture();
    }

    @Override
    public CompletableFuture<List<CbTradeListResponse>> fetchTrades(String account, Side side, CoinbaseCallback<CbTradeListResponse> cb) {
        return fetchTrades(account, side, Integer.MAX_VALUE, cb);
    }

    @Override
    public CompletableFuture<List<CbTradeListResponse>> fetchTrades(String account, Side side, int maxRecords, CoinbaseCallback<CbTradeListResponse> cb) {
        return paginate(cb, maxRecords,
                next -> service.getTrades(account, side.getUri(), pageSize, next));
    }

    @Override
    public CompletableFuture<CbTradeResponse> fetchTrade(String account, String id, Side side, CoinbaseCallback<CbTradeResponse> cb) {
        return invoke(cb, result -> service.getTrade(account, id, side.getUri())).toCompletableFuture();
    }

    @Override
    public CompletableFuture<List<CbCashTransactionListResponse>> fetchCashTransactions(String account, CashTransactionType type, CoinbaseCallback<CbCashTransactionListResponse> cb) {
        return fetchCashTransactions(account, type, Integer.MAX_VALUE, cb);
    }

    @Override
    public CompletableFuture<List<CbCashTransactionListResponse>> fetchCashTransactions(String account, CashTransactionType type, int maxRecords, CoinbaseCallback<CbCashTransactionListResponse> cb) {
        return paginate(cb,maxRecords,
                next -> service.getCashTransactions(account, type.getUri(), pageSize, next));
    }

    @Override
    public CompletableFuture<CbCashTransactionResponse> fetchCashTransaction(String account, String id, CashTransactionType type, CoinbaseCallback<CbCashTransactionResponse> cb) {
        return invoke(cb, result -> service.getCashTransaction(account, id, type.getUri())).toCompletableFuture();
    }

    @Override
    public CompletableFuture<CbCashTransactionResponse> executeCashTransaction(String account, CbCashTransactionRequest req, CashTransactionType type, CoinbaseCallback<CbCashTransactionResponse> cb) {
        return invoke(cb, result -> service.executeCashTransaction(account, req)).toCompletableFuture();
    }

    @Override
    public CompletableFuture<CbCashTransactionResponse> commitCashTransaction(String account, String id, CashTransactionType type, CoinbaseCallback<CbCashTransactionResponse> cb) {
        return invoke(cb, result -> service.commitCashTransaction(account, id, type.getUri())).toCompletableFuture();
    }

    @Override
    public CompletableFuture<CbTradeResponse> placeBuyOrder(String account,CbOrderRequest request, CoinbaseCallback<CbTradeResponse> cb) {
        return invoke(cb, result -> service.placeOrder(account, Side.BUY.getUri(), request)).toCompletableFuture();
    }

    @Override
    public CompletableFuture<CbTradeResponse> placeSellOrder(String account,CbOrderRequest request, CoinbaseCallback<CbTradeResponse> cb) {
        return invoke(cb, result -> service.placeOrder(account, Side.SELL.getUri(), request)).toCompletableFuture();
    }

    @Override
    public CompletableFuture<CbTradeResponse> commitOrder(String account, String orderId, Side side, CoinbaseCallback<CbTradeResponse> cb) {
        return invoke(cb, result -> service.commitOrder(account, orderId, side.getUri())).toCompletableFuture();
    }
}
