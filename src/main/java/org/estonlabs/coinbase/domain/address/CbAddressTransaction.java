package org.estonlabs.coinbase.domain.address;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.estonlabs.coinbase.domain.account.CbBalance;
import org.estonlabs.coinbase.domain.transaction.CbCounterparty;
import org.estonlabs.coinbase.domain.transaction.CbCryptoNetwork;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "type",
        "status",
        "amount",
        "native_amount",
        "description",
        "created_at",
        "updated_at",
        "resource",
        "resource_path",
        "network",
        "from"
})
@Generated("jsonschema2pojo")
public class CbAddressTransaction {

    @JsonProperty("id")
    private String id;
    @JsonProperty("type")
    private String type;
    @JsonProperty("status")
    private String status;
    @JsonProperty("amount")
    private CbBalance amount;
    @JsonProperty("native_amount")
    private CbBalance nativeAmount;
    @JsonProperty("description")
    private Object description;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("resource")
    private String resource;
    @JsonProperty("resource_path")
    private String resourcePath;
    @JsonProperty("network")
    private CbCryptoNetwork network;
    @JsonProperty("from")
    private CbCounterparty from;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("amount")
    public CbBalance getAmount() {
        return amount;
    }

    @JsonProperty("native_amount")
    public CbBalance getNativeAmount() {
        return nativeAmount;
    }

    @JsonProperty("description")
    public Object getDescription() {
        return description;
    }

    @JsonProperty("created_at")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("updated_at")
    public String getUpdatedAt() {
        return updatedAt;
    }

    @JsonProperty("resource")
    public String getResource() {
        return resource;
    }

    @JsonProperty("resource_path")
    public String getResourcePath() {
        return resourcePath;
    }

    @JsonProperty("network")
    public CbCryptoNetwork getNetwork() {
        return network;
    }

    @JsonProperty("from")
    public CbCounterparty getFrom() {
        return from;
    }
}
