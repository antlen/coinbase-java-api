package com.coinbase.client.sync;

import com.coinbase.client.api.CoinbaseRequestApi;
import com.coinbase.client.api.request.PaginatedGetRequest;
import com.coinbase.domain.account.CbAccount;
import com.coinbase.domain.account.request.CbAccountUpdateRequest;
import com.coinbase.domain.order.request.CbOrderRequest;
import com.coinbase.domain.pagination.response.CbPaginatedResponse;
import com.coinbase.domain.trade.*;
import com.coinbase.domain.transaction.payment.CbPaymentMethod;
import com.coinbase.domain.address.request.CbCreateAddressRequest;
import com.coinbase.domain.trade.request.CbCashTransactionRequest;
import com.coinbase.exception.CbApiException;
import com.coinbase.domain.address.CbAddress;
import com.coinbase.domain.address.CbAddressTransaction;
import com.coinbase.domain.price.CbCurrencyCode;
import com.coinbase.domain.price.CbExchangeRate;
import com.coinbase.domain.price.CbPrice;
import com.coinbase.domain.price.PriceType;
import com.coinbase.domain.system.CbTime;
import com.coinbase.domain.transaction.TransactionType;
import com.coinbase.domain.transaction.request.CbMoneyRequest;
import com.coinbase.domain.user.CbUser;
import com.coinbase.domain.user.request.CbUserUpdateRequest;

import java.time.LocalDate;
import java.util.*;

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
 * The main implementation of CoinbaseSyncClient
 *
 * @author antlen
 */
public class CoinbaseSyncRestClient implements CoinbaseSyncClient {

    private final CoinbaseRequestApi api;

    public CoinbaseSyncRestClient(CoinbaseRequestApi api) {
        this.api = api;
    }

    private <T> List<T> page(PaginatedGetRequest<? extends CbPaginatedResponse<T>> req, int maxRecords){
        List<T> res = new ArrayList<>();
        while(req != null && (maxRecords < 0 || res.size() < maxRecords)){
            int rec = maxRecords - res.size();
            res.addAll(req.sync().getData());
            req = req.next();
        }
        return res;
    }

    @Override
    public void reconnect() {
        api.reconnect();
    }

    @Override
    public void ping() {
        api.ping();
    }

    @Override
    public void setLogResponsesEnabled(boolean b) {
        api.setLogResponsesEnabled(b);
    }

    @Override
    public CbUser getUser() {
        return api.userRequest().sync().getData();
    }

    @Override
    public CbUser getUser(String userId) {
        return api.userRequest(userId).sync().getData();
    }

    @Override
    public List<CbPaymentMethod> getPaymentMethods() {
        return page(api.paymentMethodsRequest(), -1);
    }

    @Override
    public CbPaymentMethod getPaymentMethod(String id) {
        return api.paymentMethodRequest(id).sync().getData();
    }

    @Override
    public CbUser updateUser(CbUserUpdateRequest u) {
        return api.updateUserRequest().with(u).sync().getData();
    }

    @Override
    public List<CbAccount> getAccounts() {
        return page(api.accountsRequest(), -1);
    }

    @Override
    public CbAccount getAccount(String id) {
        return api.accountRequest(id).sync().getData();
    }

    @Override
    public CbAccount updateAccountName(CbAccountUpdateRequest req) {
        return api.updateAccountNameRequest(req.getAccount()).with(req).sync().getData();
    }

    @Override
    public boolean deleteAccount(String id) {
        api.deleteAccount(id).sync();
        return true;
    }

    @Override
    public List<CbAddress> getAddresses(String id) {
        return page(api.addressesRequest(id), -1);
    }

    @Override
    public CbAddress getAddress(String account, String addressId) {
        return api.addressRequest(account, addressId).sync().getData();
    }

    @Override
    public CbAddress createAddress(CbCreateAddressRequest request) {
        return api.createAddressRequest(request.getAccount()).with(request).sync().getData();
    }

    @Override
    public List<CbAddressTransaction> getTransactions(String accountId, String address) {
        return getTransactions(accountId, address, -1);
    }

    @Override
    public List<CbAddressTransaction> getTransactions(String accountId, String address, int maxRecords) {
        return page(api.transactionsRequest(accountId,address), maxRecords);
    }

    @Override
    public CbTime getServerTime() {
        return api.serverTimeRequest().sync().getData();
    }

    @Override
    public CbPrice getPrice(PriceType t, String pair) {
        return api.priceRequest(t, pair).sync().getData();
    }

    @Override
    public CbPrice getSpotPrice(String pair, LocalDate date) {
        return api.spotPriceRequest(pair, date).sync().getData();
    }

    @Override
    public Collection<CbCurrencyCode> getCurrencyCodes() {
        return api.currencyCodesRequest().sync().getData();
    }

    @Override
    public CbExchangeRate getExchangeRate(String base) {
        return api.exchangeRateRequest(base).sync().getData();
    }

    @Override
    public CbExchangeRate getExchangeRate() {
        return api.exchangeRateRequest().sync().getData();
    }

    @Override
    public CbAddressTransaction sendMoney(CbMoneyRequest req) {
        if(!req.getType().equals(TransactionType.SEND.getName())){
            throw new CbApiException("Incorrect type '"+req.getType()+"' for sendMoney request.");
        }
        return sendMoneyRequest(req);
    }

    @Override
    public CbAddressTransaction requestMoney(CbMoneyRequest req) {
        if(!req.getType().equals(TransactionType.REQUEST.getName())){
            throw new CbApiException("Incorrect type '"+req.getType()+"' for requestMoney request.");
        }
        return sendMoneyRequest(req);
    }

    @Override
    public CbAddressTransaction transferMoney(CbMoneyRequest req) {
        if(!req.getType().equals(TransactionType.TRANSFER.getName())){
            throw new CbApiException("Incorrect type '"+req.getType()+"' for transferMoney request.");
        }
        return sendMoneyRequest(req);
    }

    @Override
    public CbAddressTransaction sendMoneyRequest(CbMoneyRequest req) {
        return api.createSendMoneyRequest(req.getFrom()).with(req).sync().getData();
    }

    @Override
    public Collection<CbTrade> getTrades(String account, Side side) {
        return page(api.tradesRequest(account, side), -1);
    }

    @Override
    public Collection<CbTrade> getTrades(String account, Side side, int maxRecords) {
        return page(api.tradesRequest(account, side), maxRecords);
    }

    @Override
    public CbTrade getTrade(String account, String id, Side side) {
        return api.tradeRequest(account, id, side).sync().getData();
    }

    @Override
    public Collection<CbCashTransaction> getCashTransactions(String account, CashTransactionType type) {
        return getCashTransactions(account, type, -1);
    }

    @Override
    public Collection<CbCashTransaction> getCashTransactions(String account, CashTransactionType type, int maxRecords) {
        return page(api.cashTransactionsRequest(account, type), maxRecords);
    }

    @Override
    public CbCashTransaction getCashTransaction(String account, String id, CashTransactionType type) {
        return api.cashTransactionRequest(account, id, type).sync().getData();
    }

    @Override
    public CbCashTransaction executeCashTransaction(CbCashTransactionRequest req, CashTransactionType type) {
        return api.executeCashTransaction(req.getFrom(), type).with(req).sync().getData();
    }

    @Override
    public CbCashTransaction commitCashTransaction(String account, String id, CashTransactionType type) {
        return api.commitCashTransaction(account,id, type).sync().getData();
    }

    @Override
    public CbCashTransaction commitCashTransaction(CbCashTransaction t) {
        return api.commitCashTransaction(t).sync().getData();
    }

    @Override
    public CbTrade placeBuyOrder(CbOrderRequest request) {
        return api.createBuyOrderRequest(request.getFrom()).with(request).sync().getData();
    }

    @Override
    public CbTrade placeSellOrder(CbOrderRequest request) {
        return api.createSellOrderRequest(request.getFrom()).with(request).sync().getData();
    }

    @Override
    public CbTrade commitOrder(CbTrade t) {
        return api.commitOrderRequest(t).sync().getData();
    }

    @Override
    public CbTrade commitOrder(String account, String orderId, Side side) {
        return api.commitOrderRequest(account, orderId, side).sync().getData();
    }
}
