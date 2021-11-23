package org.estonlabs.coinbase.domain.price.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.estonlabs.coinbase.domain.general.response.CbResponse;
import org.estonlabs.coinbase.domain.price.CbExchangeRate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CbExchangeRateResponse extends CbResponse<CbExchangeRate> {
}
