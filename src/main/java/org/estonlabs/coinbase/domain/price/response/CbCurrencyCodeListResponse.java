package org.estonlabs.coinbase.domain.price.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.estonlabs.coinbase.domain.pagination.response.CbPaginatedResponse;
import org.estonlabs.coinbase.domain.price.CbCurrencyCode;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CbCurrencyCodeListResponse extends CbPaginatedResponse<CbCurrencyCode> {
}