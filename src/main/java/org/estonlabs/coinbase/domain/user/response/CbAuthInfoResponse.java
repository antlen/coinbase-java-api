package org.estonlabs.coinbase.domain.user.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.estonlabs.coinbase.domain.general.response.CbResponse;
import org.estonlabs.coinbase.domain.account.CbAuthInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CbAuthInfoResponse extends CbResponse<CbAuthInfo> {
}
