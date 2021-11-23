package com.coinbase.domain.trade.response;

import com.coinbase.domain.general.response.CbResponse;
import com.coinbase.domain.trade.CbTrade;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CbTradeResponse extends CbResponse<CbTrade> {
}
