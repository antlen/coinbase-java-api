package org.estonlabs.coinbase.domain.address.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.estonlabs.coinbase.domain.address.CbAddressTransaction;
import org.estonlabs.coinbase.domain.general.response.CbResponse;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CbAddressTransactionResponse extends CbResponse<CbAddressTransaction> {
}
