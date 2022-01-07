package com.coinbase.client;

import com.coinbase.domain.account.CbAccount;
import com.coinbase.domain.account.request.CbAccountUpdateRequest;
import com.coinbase.domain.account.response.CbAccountResponse;
import com.coinbase.domain.address.CbAddress;
import com.coinbase.domain.address.CbAddressTransaction;
import com.coinbase.domain.address.response.CbAddressResponse;
import com.coinbase.domain.address.response.CbAddressTransactionResponse;
import com.coinbase.domain.general.response.ResponseBody;
import com.coinbase.domain.order.request.CbOrderRequest;
import com.coinbase.domain.price.response.CbCurrencyCodeListResponse;
import com.coinbase.domain.price.response.CbExchangeRateResponse;
import com.coinbase.domain.price.response.CbPriceResponse;
import com.coinbase.domain.system.response.CbTimeResponse;
import com.coinbase.domain.trade.CbCashTransaction;
import com.coinbase.domain.trade.CbTrade;
import com.coinbase.domain.trade.response.CbCashTransactionResponse;
import com.coinbase.domain.trade.response.CbTradeResponse;
import com.coinbase.domain.address.request.CbCreateAddressRequest;
import com.coinbase.domain.price.PriceType;
import com.coinbase.domain.trade.CashTransactionType;
import com.coinbase.domain.trade.Side;
import com.coinbase.domain.trade.request.CbCashTransactionRequest;
import com.coinbase.domain.transaction.payment.CbPaymentMethod;
import com.coinbase.domain.transaction.request.CbMoneyRequest;
import com.coinbase.domain.transaction.response.CbPaymentMethodResponse;
import com.coinbase.domain.user.request.CbUserUpdateRequest;
import com.coinbase.domain.user.response.CbUserResponse;

import java.time.LocalDate;
import java.util.List;

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
 * Coinbase client implementation for all api calls
 *
 * @author antlen
 */
public interface CoinbaseRestClient {

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
     * Ping the service to see if it is still alive or to keep it alive.
     */
    void ping();

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
     *
     * @return
     */
    CbUserResponse getUser();

    /**
     * Get any user’s public information with their ID.
     *
     * @param userId
     * @return
     */
    CbUserResponse getUser(String userId);

    /**
     * Lists current user’s payment methods.
     *
     * @return
     */
    ResponseBody<List<CbPaymentMethod>>  getPaymentMethods();

    /**
     * Lists current user’s payment methods.
     *
     * @param maxRecords
     * @return
     */
    ResponseBody<List<CbPaymentMethod>>  getPaymentMethods(int maxRecords);

    /**
     * Show current user’s payment method.
     *
     * @param id
     * @return
     */
    CbPaymentMethodResponse getPaymentMethod(String id);

    /**
     * Modify current user and their preferences.
     *
     * @param u
     * @return
     */
    CbUserResponse updateUser(CbUserUpdateRequest u);

    /***
     * Lists current user’s accounts to which the authentication method has access to.
     * @return
     */
    ResponseBody<List<CbAccount>> getAccounts();

    /***
     * Lists current user’s accounts to which the authentication method has access to.
     * @param maxRecords
     * @return
     */
    ResponseBody<List<CbAccount>> getAccounts(int maxRecords);

    /**
     * Show current user’s account. To access the primary account for a given currency,
     * a currency string (BTC or ETH) can be used instead of the account id in the URL.
     *
     * @return
     */
    CbAccountResponse getAccount(String id);

    /**
     * Modifies user’s account name.
     *
     * @param req
     * @return
     */
    CbAccountResponse updateAccountName(String account, CbAccountUpdateRequest req);

    /**
     * Removes user’s account. In order to remove an account it can’t be:
     * <p>
     * Primary account
     * Account with non-zero balance
     * Fiat account
     * Vault with a pending withdrawal
     *
     * @param id
     * @return
     */
    boolean deleteAccount(String id);

    /**
     * Lists addresses for an account.
     * <p>
     * Important: Addresses should be considered one time use only.
     * Please see createAddress() to create a new address.
     *
     * @param id
     * @return
     */
    ResponseBody<List<CbAddress>>  getAddresses(String id);

    /**
     * Lists addresses for an account.
     * <p>
     * Important: Addresses should be considered one time use only.
     * Please see createAddress() to create a new address.
     *
     * @param id
     * @param maxRecords
     * @return
     */
    ResponseBody<List<CbAddress>>  getAddresses(String id, int maxRecords);

    /**
     * Show an individual address for an account. A regular bitcoin, bitcoin cash, litecoin or ethereum address
     * can be used in place of addressId but the address has to be associated to the correct account.
     *
     * @param account
     * @param addressId
     * @return
     */
    CbAddressResponse getAddress(String account, String addressId);


    /**
     * Creates a new address for an account.
     *
     * Addresses can be created for wallet account types.
     * @param account
     * @param request
     * @return
     */
    CbAddressResponse createAddress(String account, CbCreateAddressRequest request);

    /**
     * List transactions that have been sent to a specific address.
     * A regular bitcoin, bitcoin cash, litecoin or ethereum address can be used in place of address_id but
     * the address has to be associated to the correct account.
     *
     * @param accountId
     * @param address
     *
     * @return
     */
    ResponseBody<List<CbAddressTransaction>>  getTransactions(String accountId, String address);

    /**
     * List transactions that have been sent to a specific address.
     * A regular bitcoin, bitcoin cash, litecoin or ethereum address can be used in place of address_id but
     * the address has to be associated to the correct account.
     * @param accountId
     * @param address
     * @param maxRecords
     * @return
     */
    ResponseBody<List<CbAddressTransaction>>  getTransactions(String accountId, String address, int maxRecords);

    /**
     * Get the API server time.
     * <p>
     * This endpoint doesn’t require authentication.
     *
     * @return
     */
    CbTimeResponse getServerTime();

    /**
     * gets the current price from the exchange
     *
     * @param t
     * @param ticker
     * @return
     */
    CbPriceResponse getPrice(String ticker, PriceType t);

    /**
     * gets the spot price at 'date' from the exchange
     *
     * @param ticker
     * @param date
     * @return
     */
    CbPriceResponse getSpotPrice(String ticker, LocalDate date);

    /**
     * List known currencies. Currency codes will conform to the ISO 4217 standard where possible.
     * Currencies which have or had no representation in ISO 4217 may use a custom code (e.g. BTC).
     * <p>
     * This endpoint doesn’t require authentication.
     *
     * @return
     */
    CbCurrencyCodeListResponse getCurrencyCodes();

    /**
     * get the exchange rates
     * @return
     */
    CbExchangeRateResponse getExchangeRate(String base);

    /**
     * get the exchange rates, defaults to USD.
     * @return
     */
    CbExchangeRateResponse getExchangeRate();

    /**
     * Send funds to a bitcoin address, bitcoin cash address, litecoin address, ethereum address,
     * or email address. No transaction fees are required for off blockchain bitcoin transactions.
     *
     * It’s recommended to always supply a unique idem field for each transaction.
     * This prevents you from sending the same transaction twice if there has been an unexpected network outage
     * or other issue.
     *
     * When used with OAuth2 authentication, this endpoint requires two factor authentication.
     * @param req
     * @return
     */
    CbAddressTransactionResponse sendMoney(String account, CbMoneyRequest req);

    /**
     * Requests money from an email address.
     *
     * HTTP REQUEST
     * POST https://api.coinbase.com/v2/accounts/:account_id/transactions
     * @param account
     * @param req
     * @return
     */
    CbAddressTransactionResponse requestMoney(String account, CbMoneyRequest req);

    /**
     * Transfer bitcoin, bitcoin cash, litecoin or ethereum between two of a user’s accounts. Following transfers are allowed:
     *
     * wallet to wallet
     * wallet to vault
     * @param account
     * @param req
     * @return
     */
    CbAddressTransactionResponse transferMoney(String account, CbMoneyRequest req);

    /**
     * Send any type of Money Request, the axction will depend on the type.
     * @param account
     * @param req
     * @return
     */
    CbAddressTransactionResponse sendMoneyRequest(String account, CbMoneyRequest req);

    /**
     * Gets a list of all buy or sell trades for the account
     * @param account
     * @param side
     * @return
     */
    ResponseBody<List<CbTrade>>  getTrades(String account, Side side);

    /**
     * Gets a list of all buy or sell trades for the account
     * @param account
     * @param side
     * @param maxRecords
     *
     * @return
     */
    ResponseBody<List<CbTrade>>  getTrades(String account, Side side, int maxRecords);

    /**
     * loads an individual trade
     * @param id
     * @param side
     * @return
     */
    CbTradeResponse getTrade(String account, String id, Side side);

    /**
     * Lists cash transaction (deposit / withdrawal)  for an account.
     * @param account
     * @param type
     * @return
     */
    ResponseBody<List<CbCashTransaction>>  getCashTransactions(String account, CashTransactionType type);

    /**
     * Lists cash transaction (deposit / withdrawal)  for an account.
     * @param account
     * @param type
     * @param maxRecords
     * @return
     */
    ResponseBody<List<CbCashTransaction>>  getCashTransactions(String account, CashTransactionType type, int maxRecords);

    /**
     *  Gets a single cash transaction (deposit / withdrawal) .
     * @param account
     * @param id
     * @param type
     * @return
     */
    CbCashTransactionResponse getCashTransaction(String account, String id, CashTransactionType type);

    /**
     * cash transaction (deposit / withdrawal) of user-defined amount of funds to a fiat account.
     * @param req
     * @param type
     * @return
     */
    CbCashTransactionResponse executeCashTransaction(CbCashTransactionRequest req, CashTransactionType type);

    /**
     * Completes a cash transaction (deposit / withdrawal) that is created in commit: false state.
     * @param account
     * @param id
     * @param type
     * @return
     */
    CbCashTransactionResponse commitCashTransaction(String account, String id, CashTransactionType type);

    /**
     * Places an order to the exchange
     * @param request
     * @return
     */
    CbTradeResponse placeBuyOrder(String account, CbOrderRequest request);

    /**
     * Places an order to the exchange
     * @param request
     * @return
     */
    CbTradeResponse placeSellOrder(String account, CbOrderRequest request);


    /**
     * commits a previously uncommitted trade
     * @param account
     * @param orderId
     * @return
     */
    CbTradeResponse commitOrder(String account, String orderId, Side side);
}