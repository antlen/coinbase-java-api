package org.estonlabs.coinbase.domain.system.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.estonlabs.coinbase.domain.general.response.CbResponse;
import org.estonlabs.coinbase.domain.system.CbTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CbTimeResponse extends CbResponse<CbTime> {
}
