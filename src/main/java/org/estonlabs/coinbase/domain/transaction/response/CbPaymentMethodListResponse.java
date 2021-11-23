package org.estonlabs.coinbase.domain.transaction.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.estonlabs.coinbase.domain.pagination.response.CbPaginatedResponse;
import org.estonlabs.coinbase.domain.transaction.payment.CbPaymentMethod;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CbPaymentMethodListResponse extends CbPaginatedResponse<CbPaymentMethod> {
}