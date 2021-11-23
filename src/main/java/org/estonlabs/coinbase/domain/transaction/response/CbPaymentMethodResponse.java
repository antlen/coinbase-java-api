package org.estonlabs.coinbase.domain.transaction.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.estonlabs.coinbase.domain.general.response.CbResponse;
import org.estonlabs.coinbase.domain.transaction.payment.CbPaymentMethod;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CbPaymentMethodResponse extends CbResponse<CbPaymentMethod> {
}
