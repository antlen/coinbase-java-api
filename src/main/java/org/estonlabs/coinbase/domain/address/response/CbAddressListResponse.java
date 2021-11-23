package org.estonlabs.coinbase.domain.address.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.estonlabs.coinbase.domain.pagination.response.CbPaginatedResponse;
import org.estonlabs.coinbase.domain.address.CbAddress;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CbAddressListResponse extends CbPaginatedResponse<CbAddress> {
}