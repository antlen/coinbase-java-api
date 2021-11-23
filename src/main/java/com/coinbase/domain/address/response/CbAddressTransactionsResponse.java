package com.coinbase.domain.address.response;

import com.coinbase.domain.address.CbAddressTransaction;
import com.coinbase.domain.pagination.response.CbPaginatedResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CbAddressTransactionsResponse extends CbPaginatedResponse<CbAddressTransaction> {
}