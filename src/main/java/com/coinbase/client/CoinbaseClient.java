package com.coinbase.client;

import com.coinbase.domain.account.CbAccount;
import com.coinbase.domain.account.request.CbAccountUpdateRequest;
import com.coinbase.domain.order.request.CbOrderRequest;
import com.coinbase.domain.transaction.payment.CbPaymentMethod;
import com.coinbase.domain.address.CbAddress;
import com.coinbase.domain.address.CbAddressTransaction;
import com.coinbase.domain.address.request.CbCreateAddressRequest;
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
import com.coinbase.domain.transaction.request.CbMoneyRequest;
import com.coinbase.domain.user.CbUser;
import com.coinbase.domain.user.request.CbUserUpdateRequest;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

/**
 * Coinbase client implementation for all api calls
 */
public interface CoinbaseClient {

    /**
     * reestablish the connection.
     */
    void reconnect();

    /**
     * Ping the service to see if it is still aliver or to keep it alive.
     */
    void ping();

    /**
     * Clones the client
     */
    CoinbaseClient clone();

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
    CbUser getUser();

    /**
     * Get any user’s public information with their ID.
     *
     * @param userId
     * @return
     */
    CbUser getUser(String userId);

    /**
     * Lists current user’s payment methods.
     *
     * @return
     */
    List<CbPaymentMethod> getPaymentMethods();

    /**
     * Show current user’s payment method.
     *
     * @return
     */
    CbPaymentMethod getPaymentMethod(String id);

    /**
     * Modify current user and their preferences.
     *
     * @param u
     * @return
     */
    CbUser updateUser(CbUserUpdateRequest u);

    /***
     * Lists current user’s accounts to which the authentication method has access to.
     * @return
     */
    List<CbAccount> getAccounts();

    /**
     * Show current user’s account. To access the primary account for a given currency,
     * a currency string (BTC or ETH) can be used instead of the account id in the URL.
     *
     * @return
     */
    CbAccount getAccount(String id);

    /**
     * Modifies user’s account name.
     *
     * @param req
     * @return
     */
    CbAccount updateAccountName(CbAccountUpdateRequest req);

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
    List<CbAddress> getAddresses(String id);

    /**
     * Show an individual address for an account. A regular bitcoin, bitcoin cash, litecoin or ethereum address
     * can be used in place of addressId but the address has to be associated to the correct account.
     *
     * @param account
     * @param addressId
     * @return
     */
    CbAddress getAddress(String account, String addressId);


    /**
     * Creates a new address for an account.
     *
     * Addresses can be created for wallet account types.
     * @param request
     * @return
     */
    CbAddress createAddress(CbCreateAddressRequest request);

    /**
     * List transactions that have been sent to a specific address.
     * A regular bitcoin, bitcoin cash, litecoin or ethereum address can be used in place of address_id but
     * the address has to be associated to the correct account.
     *
     * @return
     */
    List<CbAddressTransaction> getTransactions(String accountId, String address);

    /**
     * Get the API server time.
     * <p>
     * This endpoint doesn’t require authentication.
     *
     * @return
     */
    CbTime getServerTime();

    /**
     * gets the current price from the exchange
     *
     * @param t
     * @param pair
     * @return
     */
    CbPrice getPrice(PriceType t, String pair);

    /**
     * gets the spot price at 'date' from the exchange
     *
     * @param pair
     * @param date
     * @return
     */
    CbPrice getSpotPrice(String pair, LocalDate date);

    /**
     * List known currencies. Currency codes will conform to the ISO 4217 standard where possible.
     * Currencies which have or had no representation in ISO 4217 may use a custom code (e.g. BTC).
     * <p>
     * This endpoint doesn’t require authentication.
     *
     * @return
     */
    List<CbCurrencyCode> getCurrencyCodes();

    /**
     * get the echange rates for BTC/
     * @return
     */
    CbExchangeRate getBTCExchangeRate();

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
    CbAddressTransaction sendMoney(CbMoneyRequest req);

    /**
     * Requests money from an email address.
     *
     * HTTP REQUEST
     * POST https://api.coinbase.com/v2/accounts/:account_id/transactions
     * @param req
     * @return
     */
    CbAddressTransaction requestMoney(CbMoneyRequest req);

    /**
     * Transfer bitcoin, bitcoin cash, litecoin or ethereum between two of a user’s accounts. Following transfers are allowed:
     *
     * wallet to wallet
     * wallet to vault
     * @param req
     * @return
     */
    CbAddressTransaction transferMoney(CbMoneyRequest req);

    /**
     * Gets a list of all of the trades made for the account
     * @param account
     * @return
     */
    Collection<CbTrade> getTrades(String account);

    /**
     * Gets a list of all buy or sell trades for the account
     * @param account
     * @param side
     * @return
     */
    Collection<CbTrade> getTrades(String account, Side side);


    /**
     * loads an individual trade
     * @param id
     * @param side
     * @return
     */
    CbTrade getTrade(String account, String id, Side side);

    /**
     * Lists cash transaction (deposit / withdrawal)  for an account.
     * @param account
     * @param type
     * @return
     */
    Collection<CbCashTransaction> getCashTransaction(String account, CashTransactionType type);

    /**
     *  Gets a single cash transaction (deposit / withdrawal) .
     * @param account
     * @param id
     * @param type
     * @return
     */
    CbCashTransaction getCashTransaction(String account, String id, CashTransactionType type);

    /**
     * cash transaction (deposit / withdrawal) of user-defined amount of funds to a fiat account.
     * @param depositRequest
     * @return
     */
    CbCashTransaction executeCashTransaction(CbCashTransactionRequest depositRequest);

    /**
     * Completes a cash transaction (deposit / withdrawal) that is created in commit: false state.
     * @param account
     * @param id
     * @param type
     * @return
     */
    CbCashTransaction commitCashTransaction(String account, String id, CashTransactionType type);

    /**
     * Completes a cash transaction (deposit / withdrawal) that is created in commit: false state.
     * @param t
     * @return
     */
    CbCashTransaction commitCashTransaction(CbCashTransaction t);

    /**
     * Places an order to the exchange
     * @param request
     * @return
     */
    CbTrade placeOrder(CbOrderRequest request);


    /**
     * commits a previously uncommitted trade
     * @param t
     * @return
     */
    CbTrade commitOrder(CbTrade t);


    /**
     * commits a previously uncommitted trade
     * @param account
     * @param orderId
     * @return
     */
    CbTrade commitOrder(String account, String orderId, Side side);
}