package org.estonlabs.coinbase.domain.trade.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.estonlabs.coinbase.domain.general.response.CbResponse;
import org.estonlabs.coinbase.domain.trade.CbTrade;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CbTradeResponse extends CbResponse<CbTrade> {
}
