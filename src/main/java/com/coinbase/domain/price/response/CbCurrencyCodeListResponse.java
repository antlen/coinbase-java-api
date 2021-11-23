package com.coinbase.domain.price.response;

import com.coinbase.domain.pagination.response.CbPaginatedResponse;
import com.coinbase.domain.price.CbCurrencyCode;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CbCurrencyCodeListResponse extends CbPaginatedResponse<CbCurrencyCode> {
}