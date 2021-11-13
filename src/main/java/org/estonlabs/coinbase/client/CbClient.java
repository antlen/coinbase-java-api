package org.estonlabs.coinbase.client;

import org.estonlabs.coinbase.domain.account.CbAccount;
import org.estonlabs.coinbase.domain.transaction.CbPaymentMethod;
import org.estonlabs.coinbase.domain.account.CbAuthInfo;
import org.estonlabs.coinbase.domain.user.CbUser;
import org.estonlabs.coinbase.domain.user.CbUserUpdateRequest;

import java.util.List;

public interface CbClient {

    void ping();

    /**
     * Whether this client is connected to the real prod API.
     * If this is true then isLConnectedToSandbox() will be false.
     * @return
     */
    boolean isLConnectedToProd();

    /**
     * Whether this client is connected to the sandbox (test api).
     * If this is true then isLConnectedToProd() will be false.
     * @return
     */
    boolean isLConnectedToSandbox();

    /**
     * Get current user’s public information. To get user’s email or private information,
     * use permissions wallet:user:email and wallet:user:read. If current request has a wallet:transactions:send scope,
     * then the response will contain a boolean sends_disabled field that indicates if the user’s send
     * functionality has been disabled.
     * @return
     */
    CbUser getUser();

    /**
     * Get any user’s public information with their ID.
     * @param userId
     * @return
     */
    CbUser getUser(String userId);

    /**
     * Get current user’s authorization information including granted scopes and send limits when using OAuth2 authentication.
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
     * @return
     */
     CbAccount getAccount(String id);

    /**
     * Lists current user’s payment methods.
     * @return
     */
    List<CbPaymentMethod> getPaymentMethods();

    /**
     * Show current user’s payment method.
     * @return
     */
    CbPaymentMethod getPaymentMethod(String id);

    /**
     * Turns on logginf og the raw JSON for every response.
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
     * @param id
     * @param req
     * @return
     */
    CbAccount updateAccountName(String id, CbNameUpdateRequest req);
}
