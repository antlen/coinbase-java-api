package org.estonlabs.coinbase.domain.transaction;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.estonlabs.coinbase.domain.price.CbAmount;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "status",
        "name"
})
@Generated("jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CbCryptoNetwork {

    @JsonProperty("status")
    private String status;
    @JsonProperty("hash")
    private String hash;
    @JsonProperty("status_description")
    private String statusDescription;
    @JsonProperty("transaction_fee")
    private CbAmount transactionFee;
    @JsonProperty("transaction_amount")
    private CbAmount transactionAmount;
    @JsonProperty("transaction_url")
    private String transactionUrl;
    @JsonProperty("confirmations")
    private Integer confirmations;

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status_description")
    public String getStatusDescription() {
        return statusDescription;
    }

    @JsonProperty("transaction_fee")
    public CbAmount getTransactionFee() {
        return transactionFee;
    }

    @JsonProperty("transaction_amount")
    public CbAmount getTransactionAmount() {
        return transactionAmount;
    }

    @JsonProperty("transaction_url")
    public String getTransactionUrl() {
        return transactionUrl;
    }

    @JsonProperty("confirmations")
    public Integer getConfirmations() {
        return confirmations;
    }

    @JsonProperty("hash")
    public String getHash() {
        return hash;
    }

    @Override
    public String toString() {
        return "CbCryptoNetwork{" +
                "status='" + status + '\'' +
                ", hash='" + hash + '\'' +
                ", statusDescription='" + statusDescription + '\'' +
                ", transactionFee=" + transactionFee +
                ", transactionAmount=" + transactionAmount +
                ", transactionUrl='" + transactionUrl + '\'' +
                ", confirmations=" + confirmations +
                '}';
    }
}