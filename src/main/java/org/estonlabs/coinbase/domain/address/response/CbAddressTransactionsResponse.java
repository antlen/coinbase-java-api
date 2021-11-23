package org.estonlabs.coinbase.domain.address.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.estonlabs.coinbase.domain.address.CbAddressTransaction;
import org.estonlabs.coinbase.domain.pagination.response.CbPaginatedResponse;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CbAddressTransactionsResponse extends CbPaginatedResponse<CbAddressTransaction> {
}