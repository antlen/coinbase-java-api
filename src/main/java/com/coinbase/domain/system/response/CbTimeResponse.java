package com.coinbase.domain.system.response;

import com.coinbase.domain.general.response.CbResponse;
import com.coinbase.domain.system.CbTime;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CbTimeResponse extends CbResponse<CbTime> {
}
