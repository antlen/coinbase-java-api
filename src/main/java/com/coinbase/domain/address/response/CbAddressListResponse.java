package com.coinbase.domain.address.response;

import com.coinbase.domain.address.CbAddress;
import com.coinbase.domain.pagination.response.CbPaginatedResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CbAddressListResponse extends CbPaginatedResponse<CbAddress> {
}