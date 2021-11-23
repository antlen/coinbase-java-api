package com.coinbase.domain.account.response;

import com.coinbase.domain.pagination.response.CbPaginatedResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.coinbase.domain.account.CbAccount;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CbAccountListResponse extends CbPaginatedResponse<CbAccount> {
}