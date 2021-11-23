package com.coinbase.domain.price.response;

import com.coinbase.domain.general.response.CbResponse;
import com.coinbase.domain.price.CbPrice;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CbPriceResponse extends CbResponse<CbPrice> {
}
