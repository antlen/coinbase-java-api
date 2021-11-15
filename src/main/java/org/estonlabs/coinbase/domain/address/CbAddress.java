package org.estonlabs.coinbase.domain.address;
import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "address",
        "address_info",
        "name",
        "created_at",
        "updated_at",
        "network",
        "uri_scheme",
        "resource",
        "resource_path",
        "warnings",
        "deposit_uri",
        "callback_url"
})
@Generated("jsonschema2pojo")
public class CbAddress {

    @JsonProperty("id")
    private String id;
    @JsonProperty("address")
    private String address;
    @JsonProperty("address_info")
    private CbAddressInfo addressInfo;
    @JsonProperty("name")
    private String name;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("network")
    private String network;
    @JsonProperty("uri_scheme")
    private String uriScheme;
    @JsonProperty("resource")
    private String resource;
    @JsonProperty("resource_path")
    private String resourcePath;
    @JsonProperty("warnings")
    private List<CbAddressWarning> warnings = null;
    @JsonProperty("deposit_uri")
    private String depositUri;
    @JsonProperty("callback_url")
    private Object callbackUrl;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    @JsonProperty("address_info")
    public CbAddressInfo getAddressInfo() {
        return addressInfo;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("created_at")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("updated_at")
    public String getUpdatedAt() {
        return updatedAt;
    }

    @JsonProperty("network")
    public String getNetwork() {
        return network;
    }

    @JsonProperty("uri_scheme")
    public String getUriScheme() {
        return uriScheme;
    }

    @JsonProperty("resource")
    public String getResource() {
        return resource;
    }

    @JsonProperty("resource_path")
    public String getResourcePath() {
        return resourcePath;
    }

    @JsonProperty("warnings")
    public List<CbAddressWarning> getWarnings() {
        return warnings;
    }

    @JsonProperty("deposit_uri")
    public String getDepositUri() {
        return depositUri;
    }

    @JsonProperty("callback_url")
    public Object getCallbackUrl() {
        return callbackUrl;
    }
}
