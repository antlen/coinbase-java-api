package com.coinbase.domain.transaction;

import javax.annotation.Generated;

import com.coinbase.domain.price.CbAmount;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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