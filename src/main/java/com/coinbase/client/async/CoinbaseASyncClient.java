package com.coinbase.client.async;

import com.coinbase.callback.PaginatedCollectionCallback;
import com.coinbase.callback.ResponseCallback;
import com.coinbase.domain.account.CbAccount;
import com.coinbase.domain.account.request.CbAccountUpdateRequest;
import com.coinbase.domain.address.CbAddress;
import com.coinbase.domain.address.CbAddressTransaction;
import com.coinbase.domain.address.request.CbCreateAddressRequest;
import com.coinbase.domain.order.request.CbOrderRequest;
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
import com.coinbase.domain.transaction.payment.CbPaymentMethod;
import com.coinbase.domain.transaction.request.CbMoneyRequest;
import com.coinbase.domain.user.CbUser;
import com.coinbase.domain.user.request.CbUserUpdateRequest;
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
 * Coinbase client interface for all async api calls
 *
 * @author antlen
 */
public interface CoinbaseASyncClient {

    /**
     * reestablish the connection.
     */
    void reconnect();

    /**
     * Ping the service to see if it is still aliver or to keep it alive.
     */
    void ping(ResponseCallback<Boolean> cb);

    /**
     * Turns on logginf og the raw JSON for every response.
     *
     * @param b
     */
    void setLogResponsesEnabled(boolean b);

    /**
     * Get current user’s public information. To get user’s email or private information,
     * use permissions wallet:user:email and wallet:user:read. If current request has a wallet:transactions:send scope,
     * then the response will contain a boolean sends_disabled field that indicates if the user’s send
     * functionality has been disabled.
     * @param cb
     */
    void fetchUser(ResponseCallback<CbUser> cb);

    /**
     * Get any user’s public information with their ID.
     *
     * @param cb
     * @param userId
     */
    void fetchUser(ResponseCallback<CbUser> cb, String userId);

    /**
     * Lists current user’s payment methods.
     * @param cb
     */
    void fetchPaymentMethods(PaginatedCollectionCallback<CbPaymentMethod> cb);

    /**
     * Show current user’s payment method.
     *
     * @param cb
     * @param id
     */
    void fetchPaymentMethod(ResponseCallback<CbPaymentMethod> cb, String id);

    /**
     * Modify current user and their preferences.
     *
     * @param cb
     * @param u
     */
    void updateUser(ResponseCallback<CbUser> cb, CbUserUpdateRequest u);

    /**
     * Lists current user’s accounts to which the authentication method has access to.
     *
     * @param cb
     */
    void fetchAccounts(PaginatedCollectionCallback<CbAccount> cb);

    /**
     * Show current user’s account. To access the primary account for a given currency,
     * a currency string (BTC or ETH) can be used instead of the account id in the URL.
     * @param cb
     * @param id
     */
    void fetchAccount(ResponseCallback<CbAccount> cb, String id);

    /**
     * Modifies user’s account name.
     * @param cb
     * @param req
     */
    void updateAccountName(ResponseCallback<CbAccount> cb, CbAccountUpdateRequest req);

    /**
     * Removes user’s account. In order to remove an account it can’t be:
     * <p>
     * Primary account
     * Account with non-zero balance
     * Fiat account
     * Vault with a pending withdrawal
     * @param cb
     * @param id
     */
    void deleteAccount(ResponseCallback<Boolean> cb, String id);

    /**
     *Lists addresses for an account.
     * <p>
     * Important: Addresses should be considered one time use only.
     * Please see createAddress() to create a new address.
     * @param cb
     * @param id
     */
    void fetchAddresses(PaginatedCollectionCallback<CbAddress> cb, String id);

    /**
     * Show an individual address for an account. A regular bitcoin, bitcoin cash, litecoin or ethereum address
     * can be used in place of addressId but the address has to be associated to the correct account.
     * @param cb
     * @param account
     * @param addressId
     */
    void fetchAddress(ResponseCallback<CbAddress> cb, String account, String addressId);


    /**
     * Creates a new address for an account.
     *
     * Addresses can be created for wallet account types.
     * @param cb
     * @param request
     */
    void createAddress(ResponseCallback<CbAddress> cb, CbCreateAddressRequest request);

    /**
     * List transactions that have been sent to a specific address.
     * A regular bitcoin, bitcoin cash, litecoin or ethereum address can be used in place of address_id but
     * the address has to be associated to the correct account.
     * @param cb
     * @param accountId
     * @param address
     */
    void fetchTransactions(PaginatedCollectionCallback<CbAddressTransaction> cb, String accountId, String address);

    /**
     * fetch the API server time.
     * <p>
     * This endpoint doesn’t require authentication.
     * @param cb
     */
    void fetchServerTime(ResponseCallback<CbTime> cb);

    /**
     * fetch the current price from the exchange
     * @param cb
     * @param t
     * @param pair
     */
    void fetchPrice(ResponseCallback<CbPrice> cb, PriceType t, String pair);

    /**
     * fetch the spot price at 'date' from the exchange
     *
     * @param cb
     * @param pair
     * @param date
     */
    void fetchSpotPrice(ResponseCallback<CbPrice> cb, String pair, LocalDate date);

    /**
     * List known currencies. Currency codes will conform to the ISO 4217 standard where possible.
     * Currencies which have or had no representation in ISO 4217 may use a custom code (e.g. BTC).
     * <p>
     * This endpoint doesn’t require authentication.
     * @param cb
     */
    void fetchCurrencyCodes(ResponseCallback<Collection<CbCurrencyCode>> cb);

    /**
     * fetch the exchange rates
     * @param cb
     * @param base
     */
    void fetchExchangeRate(ResponseCallback<CbExchangeRate> cb, String base);

    /**
     * fetch the exchange rates, defaults to USD.
     * @param cb
     */
    void fetchExchangeRate(ResponseCallback<CbExchangeRate> cb);

    /**
     * Send funds to a bitcoin address, bitcoin cash address, litecoin address, ethereum address,
     * or email address. No transaction fees are required for off blockchain bitcoin transactions.
     *
     * It’s recommended to always supply a unique idem field for each transaction.
     * This prevents you from sending the same transaction twice if there has been an unexpected network outage
     * or other issue.
     *
     * When used with OAuth2 authentication, this endpoint requires two factor authentication.
     * @param cb
     * @param req
     */
    void sendMoney(ResponseCallback<CbAddressTransaction> cb, CbMoneyRequest req);

    /**
     * Requests money from an email address.
     *
     * HTTP REQUEST
     * POST https://api.coinbase.com/v2/accounts/:account_id/transactions
     * @param cb
     * @param req
     */
    void requestMoney(ResponseCallback<CbAddressTransaction> cb, CbMoneyRequest req);

    /**
     * Transfer bitcoin, bitcoin cash, litecoin or ethereum between two of a user’s accounts. Following transfers are allowed:
     *
     * wallet to wallet
     * wallet to vault
     *
     * @param cb
     * @param req
     */
    void transferMoney(ResponseCallback<CbAddressTransaction> cb, CbMoneyRequest req);

    /**
     * Send any type of Money Request, the axction will depend on the type.Sprint 4
     *
     * @param cb
     * @param req
     */
    void sendMoneyRequest(ResponseCallback<CbAddressTransaction> cb, CbMoneyRequest req);

    /**
     * fetch a list of all buy or sell trades for the account
     * @param cb
     * @param account
     * @param side
     */
    void fetchTrades(PaginatedCollectionCallback<CbTrade> cb, String account, Side side);

    /**
     * loads an individual trade
     * @param cb
     * @param account
     * @param id
     * @param side
     */
    void fetchTrade(ResponseCallback<CbTrade> cb, String account, String id, Side side);

    /**
     * Lists cash transaction (deposit / withdrawal)  for an account.
     * @param cb
     * @param account
     * @param type
     */
    void fetchCashTransactions(PaginatedCollectionCallback<CbCashTransaction> cb,
                               String account, CashTransactionType type);


    /**
     * fetch a single cash transaction (deposit / withdrawal)
     * @param cb
     * @param account
     * @param id
     * @param type
     */
    void fetchCashTransaction(ResponseCallback<CbCashTransaction> cb,
                              String account, String id, CashTransactionType type);

    /**
     * cash transaction (deposit / withdrawal) of user-defined amount of funds to a fiat account.
     * @param req
     * @return
     */
    void executeCashTransaction(ResponseCallback<CbCashTransaction> cb, CbCashTransactionRequest req, CashTransactionType type);

    /**
     * Completes a cash transaction (deposit / withdrawal) that is created in commit: false state.
     *
     * @param cb
     * @param account
     * @param id
     * @param type
     */
    void commitCashTransaction(ResponseCallback<CbCashTransaction> cb,String account, String id, CashTransactionType type);

    /**
     * Completes a cash transaction (deposit / withdrawal) that is created in commit: false state.
     * @param cb
     * @param t
     */
    void commitCashTransaction(ResponseCallback<CbCashTransaction> cb, CbCashTransaction t);

    /**
     * Places a buy order to the exchange
     * @param request
     * @return
     */
    void placeBuyOrder(ResponseCallback<CbTrade> cb, CbOrderRequest request);

    /**
     * Places a sell order to the exchange
     * @param request
     * @return
     */
    void placeSellOrder(ResponseCallback<CbTrade> cb, CbOrderRequest request);

    /**
     * commits a previously uncommitted trade
     * @param cb
     * @param t
     */
    void commitOrder(ResponseCallback<CbTrade> cb, CbTrade t);


    /**
     * commits a previously uncommitted trade
     * @param cb
     * @param account
     * @param orderId
     * @param side
     */
    void commitOrder(ResponseCallback<CbTrade> cb, String account, String orderId, Side side);
}