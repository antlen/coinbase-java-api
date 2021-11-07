package org.estonlabs.coinbase.client;

import org.estonlabs.coinbase.domain.account.CbAccount;
import org.estonlabs.coinbase.domain.user.CbUser;

import java.util.List;

public interface CbClient {

    void ping();

    CbUser getUser();

    List<CbAccount> getAccounts();

    void setLogResponsesEnabled(boolean b);
}
