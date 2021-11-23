package com.coinbase.domain.address.response;

import com.coinbase.domain.general.response.CbResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.coinbase.domain.address.CbAddress;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CbAddressResponse extends CbResponse<CbAddress> {
}
