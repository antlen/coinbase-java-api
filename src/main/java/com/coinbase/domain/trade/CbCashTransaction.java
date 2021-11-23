package com.coinbase.domain.trade;

import javax.annotation.Generated;

import com.coinbase.domain.general.CbResource;
import com.coinbase.domain.price.CbAmount;
import com.coinbase.domain.transaction.payment.CbPaymentMethod;
import com.fasterxml.jackson.annotation.*;
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
