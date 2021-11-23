package com.coinbase.domain.price.response;

import com.coinbase.domain.general.response.CbResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.coinbase.domain.price.CbExchangeRate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CbExchangeRateResponse extends CbResponse<CbExchangeRate> {
}
