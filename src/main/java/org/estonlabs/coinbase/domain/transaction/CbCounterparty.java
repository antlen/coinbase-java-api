package org.estonlabs.coinbase.domain.transaction;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.estonlabs.coinbase.domain.address.CbAddressInfo;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "resource"
})
@Generated("jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CbCounterparty {

    @JsonProperty("id")
    private String id;
    @JsonProperty("resource")
    private String resource;
    @JsonProperty("address")
    private String address;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("address_info")
    private CbAddressInfo addressInfo;
    @JsonProperty("address_url")
    private String addressUrl;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("resource")
    public String getResource() {
        return resource;
    }

    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

    @JsonProperty("address_info")
    public CbAddressInfo getAddressInfo() {
        return addressInfo;
    }

    @JsonProperty("address_url")
    public String getAddressUrl() {
        return addressUrl;
    }

    @Override
    public String toString() {
        return "CbCounterparty{" +
                "id='" + id + '\'' +
                ", resource='" + resource + '\'' +
                ", address='" + address + '\'' +
                ", currency='" + currency + '\'' +
                ", addressInfo=" + addressInfo +
                ", addressUrl='" + addressUrl + '\'' +
                '}';
    }
}