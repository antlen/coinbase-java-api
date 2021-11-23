package org.estonlabs.coinbase.domain.address;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.*;
import org.estonlabs.coinbase.domain.account.CbBalance;
import org.estonlabs.coinbase.domain.account.CbRewards;
import org.estonlabs.coinbase.domain.general.CbResource;
import org.estonlabs.coinbase.domain.trade.Side;
import org.estonlabs.coinbase.domain.transaction.CbCounterparty;
import org.estonlabs.coinbase.domain.transaction.CbCryptoNetwork;
import org.estonlabs.coinbase.domain.transaction.CbTransactionDetails;

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
        "from",
        "to",
        "idem",
        "details",
        "hide_native_amount"
})
@Generated("jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown = true)
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
    @JsonProperty("instant_exchange")
    private boolean instantExchange;
    @JsonProperty("off_chain_status")
    private String offChainStatus;
    @JsonProperty("network")
    private CbCryptoNetwork network;
    @JsonProperty("from")
    private CbCounterparty from;
    @JsonProperty("to")
    private CbCounterparty to;
    @JsonProperty("idem")
    private String idem;
    @JsonProperty("details")
    private CbTransactionDetails details;
    @JsonProperty("hide_native_amount")
    private Boolean hideNativeAmount;
    @JsonProperty("buy")
    private CbResource buy;
    @JsonProperty("sell")
    private CbResource sell;

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

    @JsonProperty("to")
    public CbCounterparty getTo() {
        return to;
    }

    @JsonProperty("idem")
    public String getIdem() {
        return idem;
    }

    @JsonProperty("details")
    public CbTransactionDetails getDetails() {
        return details;
    }

    @JsonProperty("hide_native_amount")
    public Boolean getHideNativeAmount() {
        return hideNativeAmount;
    }

    @JsonProperty("instant_exchange")
    public boolean isInstantExchange() {
        return instantExchange;
    }
    @JsonProperty("off_chain_status")
    public String getOffChainStatus() {
        return offChainStatus;
    }

    public CbResource getOrderDetails(){
        return buy!=null?buy:sell;
    }

    @Override
    public String toString() {
        return "CbAddressTransaction{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", amount=" + amount +
                ", nativeAmount=" + nativeAmount +
                ", description=" + description +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", resource='" + resource + '\'' +
                ", resourcePath='" + resourcePath + '\'' +
                ", instantExchange=" + instantExchange +
                ", offChainStatus='" + offChainStatus + '\'' +
                ", network=" + network +
                ", from=" + from +
                ", to=" + to +
                ", idem='" + idem + '\'' +
                ", details=" + details +
                ", hideNativeAmount=" + hideNativeAmount +
                ", buy=" + buy +
                ", sell=" + sell +
                '}';
    }
}
