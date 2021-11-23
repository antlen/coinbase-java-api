package org.estonlabs.coinbase.domain.user.request;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.estonlabs.coinbase.domain.account.request.CbAccountUpdateRequest;

/**
 * A request to update some of the user details.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "time_zone",
        "native_currency"
})
@Generated("jsonschema2pojo")
public class CbUserUpdateRequest{

    @JsonProperty("time_zone")
    private final String timeZone;
    @JsonProperty("native_currency")
    private final String nativeCurrency;
    @JsonProperty("name")
    private final String name;

    CbUserUpdateRequest(String name, String timeZone, String nativeCurrency) {
        this.name=name;
        this.timeZone = timeZone;
        this.nativeCurrency = nativeCurrency;
    }

    @JsonProperty("time_zone")
    public String getTimeZone() {
        return timeZone;
    }

    @JsonProperty("native_currency")
    public String getNativeCurrency() {
        return nativeCurrency;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }
}