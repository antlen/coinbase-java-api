package com.coinbase.domain.user.response;

import com.coinbase.domain.account.CbAuthInfo;
import com.coinbase.domain.general.response.CbResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CbAuthInfoResponse extends CbResponse<CbAuthInfo> {
}
