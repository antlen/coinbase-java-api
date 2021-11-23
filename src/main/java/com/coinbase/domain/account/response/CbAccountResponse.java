package com.coinbase.domain.account.response;

import com.coinbase.domain.general.response.CbResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.coinbase.domain.account.CbAccount;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CbAccountResponse extends CbResponse<CbAccount> {
}
