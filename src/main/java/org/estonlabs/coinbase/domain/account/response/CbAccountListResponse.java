package org.estonlabs.coinbase.domain.account.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.estonlabs.coinbase.domain.pagination.response.CbPaginatedResponse;
import org.estonlabs.coinbase.domain.account.CbAccount;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CbAccountListResponse extends CbPaginatedResponse<CbAccount> {
}