package com.coinbase.domain.transaction.response;

import com.coinbase.domain.general.response.CbResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.coinbase.domain.transaction.payment.CbPaymentMethod;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CbPaymentMethodResponse extends CbResponse<CbPaymentMethod> {
}
