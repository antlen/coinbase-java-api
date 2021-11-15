package org.estonlabs.coinbase.domain.user;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.estonlabs.coinbase.domain.CbNameUpdateRequest;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "time_zone",
        "native_currency"
})
@Generated("jsonschema2pojo")
public class CbUserUpdateRequest extends CbNameUpdateRequest {


    @JsonProperty("time_zone")
    private String timeZone;
    @JsonProperty("native_currency")
    private String nativeCurrency;

    public CbUserUpdateRequest(){
        super();
    }

    public CbUserUpdateRequest(String name, String timeZone, String nativeCurrency) {
        super(name);
        this.timeZone = timeZone;
        this.nativeCurrency = nativeCurrency;
    }

    @JsonProperty("time_zone")
    public String getTimeZone() {
        return timeZone;
    }

    @JsonProperty("time_zone")
    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    @JsonProperty("native_currency")
    public String getNativeCurrency() {
        return nativeCurrency;
    }

    @JsonProperty("native_currency")
    public void setNativeCurrency(String nativeCurrency) {
        this.nativeCurrency = nativeCurrency;
    }

}