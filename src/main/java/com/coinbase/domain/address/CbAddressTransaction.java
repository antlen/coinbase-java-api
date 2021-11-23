package com.coinbase.domain.address;

import javax.annotation.Generated;

import com.coinbase.domain.account.CbBalance;
import com.coinbase.domain.general.CbResource;
import com.coinbase.domain.transaction.CbTransactionDetails;
import com.fasterxml.jackson.annotation.*;
import com.coinbase.domain.transaction.CbCounterparty;
import com.coinbase.domain.transaction.CbCryptoNetwork;

/**
 * The MIT License (MIT)
 *
 *	Copyright (c) 2021 antlen
 *
 *	Permission is hereby granted, free of charge, to any person obtaining a copy
 *	of this software and associated documentation files (the "Software"), to deal
 *	in the Software without restriction, including without limitation the rights
 *	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *	copies of the Software, and to permit persons to whom the Software is
 *	furnished to do so, subject to the following conditions:
 *
 *	The above copyright notice and this permission notice shall be included in all
 *	copies or substantial portions of the Software.
 *
 *	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *	SOFTWARE.
 *
 * ------------------------------------------------
 *
 * @author antlen
 */
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
