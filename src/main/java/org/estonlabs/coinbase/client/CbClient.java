package org.estonlabs.coinbase.client;

import org.estonlabs.coinbase.domain.CbNameUpdateRequest;
import org.estonlabs.coinbase.domain.account.CbAccount;
import org.estonlabs.coinbase.domain.address.CbAddress;
import org.estonlabs.coinbase.domain.address.CbAddressTransaction;
import org.estonlabs.coinbase.domain.price.CbCurrencyCode;
import org.estonlabs.coinbase.domain.price.CbExchangeRate;
import org.estonlabs.coinbase.domain.price.CbPrice;
import org.estonlabs.coinbase.domain.price.PriceType;
import org.estonlabs.coinbase.domain.system.CbTime;
import org.estonlabs.coinbase.domain.transaction.CbPaymentMethod;
import org.estonlabs.coinbase.domain.account.CbAuthInfo;
import org.estonlabs.coinbase.domain.user.CbUser;
import org.estonlabs.coinbase.domain.user.CbUserUpdateRequest;

import java.time.LocalDate;
import java.util.List;

public interface CbClient {

    void reconnect();

    void ping();

    /**
     * Whether this client is connected to the real prod API.
     * If this is true then isLConnectedToSandbox() will be false.
     *
     * @return
     */
    boolean isLConnectedToProd();

    /**
     * Whether this client is connected to the sandbox (test api).
     * If this is true then isLConnectedToProd() will be false.
     *
     * @return
     */
    boolean isLConnectedToSandbox();

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
     * Get current user’s authorization information including granted scopes and send limits when using OAuth2 authentication.
     *
     * @return
     */
    CbAuthInfo getUserAuthInfo();

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
     * Turns on logginf og the raw JSON for every response.
     *
     * @param b
     */
    void setLogResponsesEnabled(boolean b);

    /**
     * Modify current user and their preferences.
     *
     * @param u
     * @return
     */
    CbUser updateUser(CbUserUpdateRequest u);

    /**
     * Modifies user’s account name.
     *
     * @param id
     * @param req
     * @return
     */
    CbAccount updateAccountName(String id, CbNameUpdateRequest req);

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
}