package com.coinbase.client.async;

import com.coinbase.callback.PaginatedCollectionCallback;
import com.coinbase.callback.ResponseCallback;
import com.coinbase.client.api.request.PaginatedRequest;
import com.coinbase.client.async.callback.AnyToBooleanResponse;
import com.coinbase.client.api.CoinbaseRequestApi;
import com.coinbase.client.api.request.RequestInvoker;
import com.coinbase.domain.account.CbAccount;
import com.coinbase.domain.account.request.CbAccountUpdateRequest;
import com.coinbase.domain.address.CbAddress;
import com.coinbase.domain.address.CbAddressTransaction;
import com.coinbase.domain.address.request.CbCreateAddressRequest;
import com.coinbase.domain.order.request.CbOrderRequest;
import com.coinbase.domain.pagination.response.CbPaginatedResponse;
import com.coinbase.domain.price.CbCurrencyCode;
import com.coinbase.domain.price.CbExchangeRate;
import com.coinbase.domain.price.CbPrice;
import com.coinbase.domain.price.PriceType;
import com.coinbase.domain.system.CbTime;
import com.coinbase.domain.trade.CashTransactionType;
import com.coinbase.domain.trade.CbCashTransaction;
import com.coinbase.domain.trade.CbTrade;
import com.coinbase.domain.trade.Side;
import com.coinbase.domain.trade.request.CbCashTransactionRequest;
import com.coinbase.domain.transaction.TransactionType;
import com.coinbase.domain.transaction.payment.CbPaymentMethod;
import com.coinbase.domain.transaction.request.CbMoneyRequest;
import com.coinbase.domain.user.CbUser;
import com.coinbase.domain.user.request.CbUserUpdateRequest;
import com.coinbase.exception.CbApiException;

import java.time.LocalDate;
import java.util.Collection;

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
 * Abstract CoinbaseASyncClient. This has most of the implementation but the logic for the
 * asynchronous invocation will be handled by child implementations.
 *
 * @author antlen
 */
public abstract class AbstractCoinbaseASyncRestClient implements CoinbaseASyncClient {

    protected final CoinbaseRequestApi api;

    public AbstractCoinbaseASyncRestClient(CoinbaseRequestApi api) {
        this.api = api;
    }

    @Override
    public void reconnect() {
        api.reconnect();
    }

    @Override
    public void setLogResponsesEnabled(boolean b) {
        api.setLogResponsesEnabled(b);
    }

    protected abstract <R> void invoke(RequestInvoker i, ResponseCallback<R> cb);

    protected abstract  <R> void invoke(PaginatedRequest<? extends CbPaginatedResponse<R>> i,
                                        PaginatedCollectionCallback<R> cb);

    @Override
    public void ping(ResponseCallback<Boolean> cb) {
        fetchServerTime(new AnyToBooleanResponse(cb));
    }

    @Override
    public void fetchUser(ResponseCallback<CbUser> cb) {
        invoke(api.userRequest(), cb);
    }

    @Override
    public void fetchUser(ResponseCallback<CbUser> cb, String userId) {
        invoke(api.userRequest(userId), cb);
    }

    @Override
    public void fetchPaymentMethods(PaginatedCollectionCallback<CbPaymentMethod> cb) {
        invoke(api.paymentMethodsRequest(), cb);
    }

    @Override
    public void fetchPaymentMethod(ResponseCallback<CbPaymentMethod> cb, String id) {
        invoke(api.paymentMethodRequest(id), cb);
    }

    @Override
    public void updateUser(ResponseCallback<CbUser> cb, CbUserUpdateRequest u) {
        invoke(api.updateUserRequest().with(u), cb);
    }

    @Override
    public void fetchAccounts(PaginatedCollectionCallback<CbAccount> cb) {
        invoke(api.accountsRequest(), cb);
    }

    @Override
    public void fetchAccount(ResponseCallback<CbAccount> cb, String id) {
        invoke(api.accountRequest(id), cb);
    }

    @Override
    public void updateAccountName(ResponseCallback<CbAccount> cb, CbAccountUpdateRequest req) {
        invoke(api.updateAccountNameRequest(req.getAccount()).with(req), cb);
    }

    @Override
    public void deleteAccount(ResponseCallback<Boolean> cb, String id) {
        invoke(api.deleteAccount(id), cb);
    }

    @Override
    public void fetchAddresses(PaginatedCollectionCallback<CbAddress> cb, String id) {
        invoke(api.addressesRequest(id), cb);
    }

    @Override
    public void fetchAddress(ResponseCallback<CbAddress> cb, String account, String addressId) {
        invoke(api.addressRequest(account, addressId), cb);
    }

    @Override
    public void createAddress(ResponseCallback<CbAddress> cb, CbCreateAddressRequest request) {
        invoke(api.createAddressRequest(request.getAccount()).with(request), cb);
    }

    @Override
    public void fetchTransactions(PaginatedCollectionCallback<CbAddressTransaction> cb, String accountId, String address) {
        invoke(api.transactionsRequest(accountId, address), cb);
    }

    @Override
    public void fetchServerTime(ResponseCallback<CbTime> cb) {
        invoke(api.serverTimeRequest(), cb);
    }

    @Override
    public void fetchPrice(ResponseCallback<CbPrice> cb, PriceType t, String pair) {
        invoke(api.priceRequest(t, pair), cb);
    }

    @Override
    public void fetchSpotPrice(ResponseCallback<CbPrice> cb, String pair, LocalDate date) {
        invoke(api.spotPriceRequest(pair, date), cb);
    }

    @Override
    public void fetchCurrencyCodes(ResponseCallback<Collection<CbCurrencyCode>> cb) {
        invoke(api.currencyCodesRequest(), cb);
    }

    @Override
    public void fetchExchangeRate(ResponseCallback<CbExchangeRate> cb, String base) {
        invoke(api.exchangeRateRequest(base), cb);
    }

    @Override
    public void fetchExchangeRate(ResponseCallback<CbExchangeRate> cb) {
        invoke(api.exchangeRateRequest(), cb);
    }

    @Override
    public void sendMoney(ResponseCallback<CbAddressTransaction> cb, CbMoneyRequest req) {
        sendMoneyRequest(cb, req, TransactionType.SEND);
    }

    @Override
    public void requestMoney(ResponseCallback<CbAddressTransaction> cb, CbMoneyRequest req) {
        sendMoneyRequest(cb, req, TransactionType.REQUEST);
    }

    @Override
    public void transferMoney(ResponseCallback<CbAddressTransaction> cb, CbMoneyRequest req) {
        sendMoneyRequest(cb, req, TransactionType.TRANSFER);
    }

    @Override
    public void sendMoneyRequest(ResponseCallback<CbAddressTransaction> cb, CbMoneyRequest req) {
        sendMoneyRequest(cb, req, null);
    }
    private void sendMoneyRequest(ResponseCallback<CbAddressTransaction> cb, CbMoneyRequest req, TransactionType type) {

        if(type !=null && !req.getType().equals(type.getName())){
            throw new CbApiException("Incorrect type '"+type+"' for sendMoney request.");
        }

        invoke(api.createSendMoneyRequest(req.getFrom()).with(req), cb);
    }

    @Override
    public void fetchTrades(PaginatedCollectionCallback<CbTrade> cb, String account, Side side) {
        invoke(api.tradesRequest(account, side), cb);
    }

    @Override
    public void fetchTrade(ResponseCallback<CbTrade> cb, String account, String id, Side side) {
        invoke(api.tradeRequest(account, id, side), cb);
    }

    @Override
    public void fetchCashTransactions(PaginatedCollectionCallback<CbCashTransaction> cb, String account,
                                      CashTransactionType type) {
        invoke(api.cashTransactionsRequest(account, type), cb);
    }

    @Override
    public void fetchCashTransaction(ResponseCallback<CbCashTransaction> cb, String account, String id, CashTransactionType type) {
        invoke(api.cashTransactionRequest(account, id, type), cb);
    }

    @Override
    public void executeCashTransaction(ResponseCallback<CbCashTransaction> cb, CbCashTransactionRequest req,
                                       CashTransactionType type) {
        invoke(api.executeCashTransaction(req.getFrom(), type).with(req), cb);
    }

    @Override
    public void commitCashTransaction(ResponseCallback<CbCashTransaction> cb, String account, String id,
                                      CashTransactionType type) {
        invoke(api.commitCashTransaction(account, id, type), cb);
    }

    @Override
    public void commitCashTransaction(ResponseCallback<CbCashTransaction> cb, CbCashTransaction t) {
        invoke(api.commitCashTransaction(t), cb);
    }

    @Override
    public void placeBuyOrder(ResponseCallback<CbTrade> cb, CbOrderRequest request) {
        invoke(api.createBuyOrderRequest(request.getFrom()).with(request), cb);
    }

    @Override
    public void placeSellOrder(ResponseCallback<CbTrade> cb, CbOrderRequest request) {
        invoke(api.createSellOrderRequest(request.getFrom()).with(request), cb);
    }

    @Override
    public void commitOrder(ResponseCallback<CbTrade> cb, CbTrade t) {
        invoke(api.commitOrderRequest(t), cb);
    }

    @Override
    public void commitOrder(ResponseCallback<CbTrade> cb, String account, String orderId, Side side) {
        invoke(api.commitOrderRequest(account, orderId, side), cb);
    }
}
