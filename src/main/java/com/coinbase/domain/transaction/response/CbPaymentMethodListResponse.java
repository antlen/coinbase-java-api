package com.coinbase.domain.transaction.response;

import com.coinbase.domain.pagination.response.CbPaginatedResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.coinbase.domain.transaction.payment.CbPaymentMethod;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CbPaymentMethodListResponse extends CbPaginatedResponse<CbPaymentMethod> {
}