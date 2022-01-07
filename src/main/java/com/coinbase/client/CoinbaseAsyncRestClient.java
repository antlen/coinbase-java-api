package com.coinbase.client;

import com.coinbase.callback.CoinbaseCallback;
import com.coinbase.domain.account.request.CbAccountUpdateRequest;
import com.coinbase.domain.account.response.CbAccountListResponse;
import com.coinbase.domain.account.response.CbAccountResponse;
import com.coinbase.domain.address.request.CbCreateAddressRequest;
import com.coinbase.domain.address.response.CbAddressListResponse;
import com.coinbase.domain.address.response.CbAddressResponse;
import com.coinbase.domain.address.response.CbAddressTransactionListResponse;
import com.coinbase.domain.address.response.CbAddressTransactionResponse;
import com.coinbase.domain.order.request.CbOrderRequest;
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
import com.coinbase.domain.transaction.request.CbMoneyRequest;
import com.coinbase.domain.transaction.response.CbPaymentMethodListResponse;
import com.coinbase.domain.transaction.response.CbPaymentMethodResponse;
import com.coinbase.domain.user.request.CbUserUpdateRequest;
import com.coinbase.domain.user.response.CbUserResponse;

import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

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
public interface CoinbaseAsyncRestClient {

    /**
     * Change the page size for paginated requests
     * @param pageSize
     */
    void setPageSize(int pageSize);

    /**
     * reestablish the connection.
     */
    void reconnect();

    /**
     * Ping the service to see if it is still aliver or to keep it alive.
     */
    void ping(CoinbaseCallback<Boolean> cb);

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
    CompletableFuture<CbUserResponse> fetchUser(CoinbaseCallback<CbUserResponse> cb);

    /**
     * Get any user’s public information with their ID.
     *
     * @param cb
     * @param userId
     */
    CompletableFuture<CbUserResponse> fetchUser(CoinbaseCallback<CbUserResponse> cb, String userId);

    /**
     * Lists current user’s payment methods.
     * @param cb
     */
    CompletableFuture<CbPaymentMethodListResponse> fetchPaymentMethods(CoinbaseCallback<CbPaymentMethodListResponse> cb);

    /**
     * Lists current user’s payment methods.
     * @param maxRecords
     * @param cb
     */
    CompletableFuture<CbPaymentMethodListResponse> fetchPaymentMethods(int maxRecords, CoinbaseCallback<CbPaymentMethodListResponse> cb);


    /**
     * Show current user’s payment method.
     *
     * @param cb
     * @param id
     */
    CompletableFuture<CbPaymentMethodResponse> fetchPaymentMethod(String id, CoinbaseCallback<CbPaymentMethodResponse> cb);

    /**
     * Modify current user and their preferences.
     *
     * @param cb
     * @param u
     */
    CompletableFuture<CbUserResponse> updateUser(CbUserUpdateRequest u, CoinbaseCallback<CbUserResponse> cb);

    /**
     * Lists current user’s accounts to which the authentication method has access to.
     *
     * @param cb
     * @return
     */
    CompletableFuture<CbAccountListResponse> fetchAccounts(CoinbaseCallback<CbAccountListResponse> cb);

    /**
     * Lists current user’s accounts to which the authentication method has access to.
     *
     * @param cb
     * @param maxRecords
     *
     * @return
     */
    CompletableFuture<CbAccountListResponse> fetchAccounts(int maxRecords, CoinbaseCallback<CbAccountListResponse> cb);

    /**
     * Show current user’s account. To access the primary account for a given currency,
     * a currency string (BTC or ETH) can be used instead of the account id in the URL.
     * @param cb
     * @param id
     */
    CompletableFuture<CbAccountResponse> fetchAccount(String id, CoinbaseCallback<CbAccountResponse> cb);

    /**
     * Modifies user’s account name.
     * @param cb
     * @param req
     */
    CompletableFuture<CbAccountResponse> updateAccountName(String account, CbAccountUpdateRequest req, CoinbaseCallback<CbAccountResponse> cb);

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
    CompletableFuture<Response> deleteAccount(String id, CoinbaseCallback<Response> cb);

    /**
     *Lists addresses for an account.
     * <p>
     * Important: Addresses should be considered one time use only.
     * Please see createAddress() to create a new address.
     * @param cb
     * @param id
     */
    CompletableFuture<CbAddressListResponse> fetchAddresses(String id, CoinbaseCallback<CbAddressListResponse> cb);

    /**
     *Lists addresses for an account.
     * <p>
     * Important: Addresses should be considered one time use only.
     * Please see createAddress() to create a new address.
     *
     * @param id
     * @param cb
     * @param maxRecords
     */
    CompletableFuture<CbAddressListResponse> fetchAddresses(String id, int maxRecords, CoinbaseCallback<CbAddressListResponse> cb);

    /**
     * Show an individual address for an account. A regular bitcoin, bitcoin cash, litecoin or ethereum address
     * can be used in place of addressId but the address has to be associated to the correct account.
     * @param cb
     * @param account
     * @param addressId
     */
    CompletableFuture<CbAddressResponse> fetchAddress(String account, String addressId, CoinbaseCallback<CbAddressResponse> cb);


    /**
     * Creates a new address for an account.
     *
     * Addresses can be created for wallet account types.
     * @param cb
     * @param request
     */
    CompletableFuture<CbAddressResponse> createAddress(String accountId, CbCreateAddressRequest request, CoinbaseCallback<CbAddressResponse> cb);

    /**
     * List transactions that have been sent to a specific address.
     * A regular bitcoin, bitcoin cash, litecoin or ethereum address can be used in place of address_id but
     * the address has to be associated to the correct account.
     * @param accountId
     * @param address
     * @param cb
     * @return
     */
    CompletableFuture<CbAddressTransactionListResponse> fetchTransactions(String accountId, String address, CoinbaseCallback<CbAddressTransactionListResponse> cb);

    /**
     * List transactions that have been sent to a specific address.
     * A regular bitcoin, bitcoin cash, litecoin or ethereum address can be used in place of address_id but
     * the address has to be associated to the correct account.
     * @param accountId
     * @param address
     * @param maxRecords
     * @param cb
     * @return
     */
    CompletableFuture<CbAddressTransactionListResponse> fetchTransactions(String accountId, String address, int maxRecords, CoinbaseCallback<CbAddressTransactionListResponse> cb);

    /**
     * fetch the API server time.
     * <p>
     * This endpoint doesn’t require authentication.
     * @param cb
     */
    CompletableFuture<CbTimeResponse> fetchServerTime(CoinbaseCallback<CbTimeResponse> cb);

    /**
     * fetch the current price from the exchange
     * @param cb
     * @param t
     * @param pair
     */
    CompletableFuture<CbPriceResponse> fetchPrice(PriceType t, String pair, CoinbaseCallback<CbPriceResponse> cb);

    /**
     * fetch the spot price at 'date' from the exchange
     *
     * @param cb
     * @param pair
     * @param date
     */
    CompletableFuture<CbPriceResponse> fetchSpotPrice(String pair, LocalDate date, CoinbaseCallback<CbPriceResponse> cb);

    /**
     * List known currencies. Currency codes will conform to the ISO 4217 standard where possible.
     * Currencies which have or had no representation in ISO 4217 may use a custom code (e.g. BTC).
     * <p>
     * This endpoint doesn’t require authentication.
     * @param cb
     */
    CompletableFuture<CbCurrencyCodeListResponse> fetchCurrencyCodes(CoinbaseCallback<CbCurrencyCodeListResponse> cb);

    /**
     * fetch the exchange rates
     * @param cb
     * @param base
     */
    CompletableFuture<CbExchangeRateResponse> fetchExchangeRate(String base, CoinbaseCallback<CbExchangeRateResponse> cb);

    /**
     * fetch the exchange rates, defaults to USD.
     * @param cb
     */
    CompletableFuture<CbExchangeRateResponse> fetchExchangeRate(CoinbaseCallback<CbExchangeRateResponse> cb);

    /**
     * Send funds to a bitcoin address, bitcoin cash address, litecoin address, ethereum address,
     * or email address. No transaction fees are required for off blockchain bitcoin transactions.
     *
     * It’s recommended to always supply a unique idem field for each transaction.
     * This prevents you from sending the same transaction twice if there has been an unexpected network outage
     * or other issue.
     *
     * When used with OAuth2 authentication, this endpoint requires two factor authentication.
     * @param account
     * @param cb
     * @param req
     */
    CompletableFuture<CbAddressTransactionResponse> sendMoney(String account, CbMoneyRequest req, CoinbaseCallback<CbAddressTransactionResponse> cb);

    /**
     * Requests money from an email address.
     *
     * HTTP REQUEST
     * POST https://api.coinbase.com/v2/accounts/:account_id/transactions
     * @param account
     * @param cb
     * @param req
     */
    CompletableFuture<CbAddressTransactionResponse> requestMoney(String account, CbMoneyRequest req, CoinbaseCallback<CbAddressTransactionResponse> cb);

    /**
     * Transfer bitcoin, bitcoin cash, litecoin or ethereum between two of a user’s accounts. Following transfers are allowed:
     *
     * wallet to wallet
     * wallet to vault
     * @param account
     * @param cb
     * @param req
     */
    CompletableFuture<CbAddressTransactionResponse> transferMoney(String account, CbMoneyRequest req, CoinbaseCallback<CbAddressTransactionResponse> cb);

    /**
     * Send any type of Money Request, the axction will depend on the type.Sprint 4
     * @param account
     * @param cb
     * @param req
     */
    CompletableFuture<CbAddressTransactionResponse> sendMoneyRequest(String account, CbMoneyRequest req, CoinbaseCallback<CbAddressTransactionResponse> cb);

    /**
     * fetch a list of all buy or sell trades for the account
     * @param cb
     * @param account
     * @param side
     */
    CompletableFuture<CbTradeListResponse> fetchTrades(String account, Side side, CoinbaseCallback<CbTradeListResponse> cb);

    /**
     * fetch a list of all buy or sell trades for the account
     * @param account
     * @param side
     * @param cb
     * @param maxRecords
     */
    CompletableFuture<CbTradeListResponse> fetchTrades(String account, Side side, int maxRecords, CoinbaseCallback<CbTradeListResponse> cb);

    /**
     * loads an individual trade
     * @param cb
     * @param account
     * @param id
     * @param side
     */
    CompletableFuture<CbTradeResponse> fetchTrade(String account, String id, Side side, CoinbaseCallback<CbTradeResponse> cb);

    /**
     * Lists cash transaction (deposit / withdrawal)  for an account.
     * @param cb
     * @param account
     * @param type
     */
    CompletableFuture<CbCashTransactionListResponse> fetchCashTransactions(String account, CashTransactionType type, CoinbaseCallback<CbCashTransactionListResponse> cb);

    /**
     * Lists cash transaction (deposit / withdrawal)  for an account.
     *
     * @param account
     * @param type
     * @param cb
     * @param maxRecords
     */
    CompletableFuture<CbCashTransactionListResponse> fetchCashTransactions(String account, CashTransactionType type, int maxRecords, CoinbaseCallback<CbCashTransactionListResponse> cb);

    /**
     * fetch a single cash transaction (deposit / withdrawal)
     * @param cb
     * @param account
     * @param id
     * @param type
     */
    CompletableFuture<CbCashTransactionResponse> fetchCashTransaction(String account, String id, CashTransactionType type, CoinbaseCallback<CbCashTransactionResponse> cb);

    /**
     * cash transaction (deposit / withdrawal) of user-defined amount of funds to a fiat account.
     * @param account
     * @param req
     * @return
     */
    CompletableFuture<CbCashTransactionResponse> executeCashTransaction(String account, CbCashTransactionRequest req, CashTransactionType type, CoinbaseCallback<CbCashTransactionResponse> cb);

    /**
     * Completes a cash transaction (deposit / withdrawal) that is created in commit: false state.
     *
     * @param cb
     * @param account
     * @param id
     * @param type
     */
    CompletableFuture<CbCashTransactionResponse> commitCashTransaction(String account, String id, CashTransactionType type, CoinbaseCallback<CbCashTransactionResponse> cb);

    /**
     * Places a buy order to the exchange
     * @param account
     * @param request
     * @return
     */
    CompletableFuture<CbTradeResponse> placeBuyOrder(String account,CbOrderRequest request, CoinbaseCallback<CbTradeResponse> cb);

    /**
     * Places a sell order to the exchange
     * @param account
     * @param request
     * @return
     */
    CompletableFuture<CbTradeResponse> placeSellOrder(String account,CbOrderRequest request, CoinbaseCallback<CbTradeResponse> cb);

    /**
     * commits a previously uncommitted trade
     * @param account
     * @param cb
     * @param account
     * @param orderId
     * @param side
     */
    CompletableFuture<CbTradeResponse> commitOrder(String account, String orderId, Side side, CoinbaseCallback<CbTradeResponse> cb);
}