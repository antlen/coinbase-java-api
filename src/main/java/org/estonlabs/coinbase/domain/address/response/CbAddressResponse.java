package org.estonlabs.coinbase.domain.address.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.estonlabs.coinbase.domain.general.response.CbResponse;
import org.estonlabs.coinbase.domain.address.CbAddress;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CbAddressResponse extends CbResponse<CbAddress> {
}
