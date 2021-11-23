package com.coinbase.domain.trade;

import javax.annotation.Generated;

import com.coinbase.domain.general.CbResource;
import com.coinbase.domain.price.CbAmount;
import com.coinbase.domain.transaction.payment.CbPaymentMethod;
import com.fasterxml.jackson.annotation.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "status",
        "payment_method",
        "transaction",
        "amount",
        "subtotal",
        "created_at",
        "updated_at",
        "resource",
        "resource_path",
        "committed",
        "fee",
        "payout_at"
})
@Generated("jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CbCashTransaction {

    @JsonProperty("id")
    private String id;
    @JsonProperty("status")
    private String status;
    @JsonProperty("payment_method")
    private CbPaymentMethod paymentMethod;
    @JsonProperty("transaction")
    private CbResource transaction;
    @JsonProperty("amount")
    private CbAmount amount;
    @JsonProperty("subtotal")
    private CbAmount subtotal;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("resource")
    private String resource;
    @JsonProperty("resource_path")
    private String resourcePath;
    @JsonProperty("committed")
    private Boolean committed;
    @JsonProperty("fee")
    private CbAmount fee;
    @JsonProperty("payout_at")
    private String payoutAt;
    @JsonProperty("user_reference")
    private String userReference;
    @JsonProperty("instant")
    private Boolean instant;
    @JsonProperty("fee_explanation_url")
    private String feeExplanationUrl;
    @JsonProperty("idem")
    private String idem;
    @JsonProperty("next_step")
    private CbNextStep nextStep;
    @JsonIgnore
    private CashTransactionType cashTransactionType;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("payment_method")
    public CbPaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    @JsonProperty("transaction")
    public CbResource getTransaction() {
        return transaction;
    }

    @JsonProperty("amount")
    public CbAmount getAmount() {
        return amount;
    }

    @JsonProperty("subtotal")
    public CbAmount getSubtotal() {
        return subtotal;
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

    @JsonProperty("committed")
    public Boolean getCommitted() {
        return committed;
    }

    @JsonProperty("fee")
    public CbAmount getFee() {
        return fee;
    }

    @JsonProperty("payout_at")
    public String getPayoutAt() {
        return payoutAt;
    }

    @JsonProperty("user_reference")
    public String getUserReference() {
        return userReference;
    }

    @JsonProperty("instant")
    public Boolean getInstant() {
        return instant;
    }

    @JsonProperty("fee_explanation_url")
    public String getFeeExplanationUrl() {
        return feeExplanationUrl;
    }

    @JsonProperty("idem")
    public String getIdem() {
        return idem;
    }

    @JsonProperty("next_step")
    public CbNextStep getNextStep() {
        return nextStep;
    }

    public CashTransactionType getCashTransactionType() {
        if (cashTransactionType == null && resource !=null){
            cashTransactionType = CashTransactionType.valueOf(resource.toUpperCase());
        }
        return cashTransactionType;
    }

    @Override
    public String toString() {
        return "CbCashTransaction{" +
                "id='" + id + '\'' +
                ", status='" + status + '\'' +
                ", paymentMethod=" + paymentMethod +
                ", transaction=" + transaction +
                ", amount=" + amount +
                ", subtotal=" + subtotal +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", resource='" + resource + '\'' +
                ", resourcePath='" + resourcePath + '\'' +
                ", committed=" + committed +
                ", fee=" + fee +
                ", payoutAt='" + payoutAt + '\'' +
                ", cashTransactionType=" + cashTransactionType +
                ", userReference='" + userReference + '\'' +
                ", instant=" + instant +
                ", feeExplanationUrl='" + feeExplanationUrl + '\'' +
                ", idem='" + idem + '\'' +
                ", nextStep=" + nextStep +
                '}';
    }
}
