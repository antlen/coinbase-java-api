package org.estonlabs.coinbase.domain.account.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.estonlabs.coinbase.domain.general.response.CbResponse;
import org.estonlabs.coinbase.domain.account.CbAccount;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CbAccountResponse extends CbResponse<CbAccount> {
}
