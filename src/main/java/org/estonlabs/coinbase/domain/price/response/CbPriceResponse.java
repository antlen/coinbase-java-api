package org.estonlabs.coinbase.domain.price.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.estonlabs.coinbase.domain.general.response.CbResponse;
import org.estonlabs.coinbase.domain.price.CbPrice;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CbPriceResponse extends CbResponse<CbPrice> {
}
