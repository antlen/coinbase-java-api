package org.estonlabs.coinbase.domain.trade.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.estonlabs.coinbase.domain.pagination.response.CbPaginatedResponse;
import org.estonlabs.coinbase.domain.trade.CbTrade;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CbTradeListResponse extends CbPaginatedResponse<CbTrade>{
}