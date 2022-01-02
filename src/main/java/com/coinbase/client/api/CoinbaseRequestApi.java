package com.coinbase.client.api;

import com.coinbase.client.api.request.*;
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

import java.time.LocalDate;

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
 * Coinbase interface for all api calls
 *
 * @author antlen
 */
public interface CoinbaseRequestApi {

    /**
     * reestablish the connection.
     */
    void reconnect();

    /**
     * Ping the service to see if it is still aliver or to keep it alive.
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
    GetRequest<CbUserResponse> userRequest();

    /**
     * Get any user’s public information with their ID.
     *
     * @param userId
     * @return
     */
    GetRequest<CbUserResponse> userRequest(String userId);

    /**
     * Lists current user’s payment methods.
     *
     * @return
     */
    PaginatedGetRequest<CbPaymentMethodListResponse> paymentMethodsRequest();

    /**
     * Show current user’s payment method.
     *
     * @return
     */
    GetRequest<CbPaymentMethodResponse> paymentMethodRequest(String id);

    /**
     * Modify current user and their preferences.
     *
     * @return
     */
    PutRequest<CbUserUpdateRequest,CbUserResponse> updateUserRequest();

    /***
     * Lists current user’s accounts to which the authentication method has access to.
     * @return
     */
    PaginatedGetRequest<CbAccountListResponse> accountsRequest();

    /**
     * Show current user’s account. To access the primary account for a given currency,
     * a currency string (BTC or ETH) can be used instead of the account id in the URL.
     *
     * @return
     */
    GetRequest<CbAccountResponse> accountRequest(String id);

    /**
     * Modifies user’s account name.
     *
     * @return
     */
    PutRequest<CbAccountUpdateRequest,CbAccountResponse> updateAccountNameRequest(String id);

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
    DeleteRequest deleteAccount(String id);

    /**
     * Lists addresses for an account.
     * <p>
     * Important: Addresses should be considered one time use only.
     * Please see createAddress() to create a new address.
     *
     * @param id
     * @return
     */
    PaginatedGetRequest<CbAddressListResponse> addressesRequest(String id);

    /**
     * Show an individual address for an account. A regular bitcoin, bitcoin cash, litecoin or ethereum address
     * can be used in place of addressId but the address has to be associated to the correct account.
     *
     * @param account
     * @param addressId
     * @return
     */
    GetRequest<CbAddressResponse> addressRequest(String account, String addressId);


    /**
     * Creates a new address for an account.
     *
     * Addresses can be created for wallet account types.
     * @param account
     * @return
     */
    PostRequest<CbCreateAddressRequest,CbAddressResponse> createAddressRequest(String account);

    /**
     * List transactions that have been sent to a specific address.
     * A regular bitcoin, bitcoin cash, litecoin or ethereum address can be used in place of address_id but
     * the address has to be associated to the correct account.
     *
     * @return
     */
    PaginatedGetRequest<CbAddressTransactionListResponse> transactionsRequest(String accountId, String address);


    /**
     * Get the API server time.
     * <p>
     * This endpoint doesn’t require authentication.
     *
     * @return
     */
    GetRequest<CbTimeResponse> serverTimeRequest();

    /**
     * gets the current price from the exchange
     *
     * @param t
     * @param pair
     * @return
     */
    GetRequest<CbPriceResponse> priceRequest(PriceType t, String pair);

    /**
     * gets the spot price at 'date' from the exchange
     *
     * @param pair
     * @param date
     * @return
     */
    GetRequest<CbPriceResponse> spotPriceRequest(String pair, LocalDate date);

    /**
     * List known currencies. Currency codes will conform to the ISO 4217 standard where possible.
     * Currencies which have or had no representation in ISO 4217 may use a custom code (e.g. BTC).
     * <p>
     * This endpoint doesn’t require authentication.
     *
     * @return
     */
    GetRequest<CbCurrencyCodeListResponse> currencyCodesRequest();

    /**
     * get the exchange rates
     * @return
     */
    GetRequest<CbExchangeRateResponse> exchangeRateRequest(String base);

    /**
     * get the exchange rates, defaults to USD.
     * @return
     */
    GetRequest<CbExchangeRateResponse> exchangeRateRequest();

    /**
     * Send any type of Money Request, the action will depend on the type.
     * @param account
     * @return
     */
    PostRequest<CbMoneyRequest,CbAddressTransactionResponse> createSendMoneyRequest(String account);

    /**
     * Gets a list of all buy or sell trades for the account
     * @param account
     * @param side
     * @return
     */
    PaginatedGetRequest<CbTradeListResponse> tradesRequest(String account, Side side);

    /**
     * loads an individual trade
     * @param id
     * @param side
     * @return
     */
    GetRequest<CbTradeResponse> tradeRequest(String account, String id, Side side);

    /**
     * Lists cash transaction (deposit / withdrawal)  for an account.
     * @param account
     * @param type
     * @return
     */
    PaginatedGetRequest<CbCashTransactionListResponse> cashTransactionsRequest(String account, CashTransactionType type);

    /**
     *  Gets a single cash transaction (deposit / withdrawal) .
     * @param account
     * @param id
     * @param type
     * @return
     */
    GetRequest<CbCashTransactionResponse> cashTransactionRequest(String account, String id, CashTransactionType type);

    /**
     * cash transaction (deposit / withdrawal) of user-defined amount of funds to a fiat account.
     * @param account
     * @param type
     * @return
     */
    PostRequest<CbCashTransactionRequest,CbCashTransactionResponse> executeCashTransaction(String account, CashTransactionType type);

    /**
     * Completes a cash transaction (deposit / withdrawal) that is created in commit: false state.
     * @param account
     * @param id
     * @param type
     * @return
     */
    PostRequest<?,CbCashTransactionResponse> commitCashTransaction(String account, String id, CashTransactionType type);

    /**
     * Completes a cash transaction (deposit / withdrawal) that is created in commit: false state.
     * @param t
     * @return
     */
    PostRequest<?,CbCashTransactionResponse> commitCashTransaction(CbCashTransaction t);

    /**
     * Places a buy order to the exchange
     * @param account
     * @return
     */
    PostRequest<CbOrderRequest,CbTradeResponse> createBuyOrderRequest(String account);

    /**
     * Places a sell order to the exchange
     * @param account
     * @return
     */
    PostRequest<CbOrderRequest,CbTradeResponse> createSellOrderRequest(String account);
    /**
     * commits a previously uncommitted trade
     * @param t
     * @return
     */
    PostRequest<CbTrade,CbTradeResponse> commitOrderRequest(CbTrade t);


    /**
     * commits a previously uncommitted trade
     * @param account
     * @param orderId
     * @return
     */
    PostRequest<?,CbTradeResponse> commitOrderRequest(String account, String orderId, Side side);
}