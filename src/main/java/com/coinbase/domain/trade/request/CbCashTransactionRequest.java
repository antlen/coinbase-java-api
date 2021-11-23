package com.coinbase.domain.trade.request;

import com.coinbase.domain.price.request.CbAmountRequest;
import com.coinbase.domain.trade.CashTransactionType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A cash transaction request for a withdrawal or deposit.
 */
public class CbCashTransactionRequest extends CbAmountRequest {
    @JsonProperty("payment_method")
    private final String paymentMethod;
    @JsonProperty("commit")
    private final Boolean commit;

    @JsonIgnore
    private final CashTransactionType type;

    CbCashTransactionRequest(CashTransactionType type, String account, String amount, String currency, String paymentMethod, Boolean commit) {
        super(account, amount, currency);
        this.type = type;
        this.paymentMethod = paymentMethod;
        this.commit = commit;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public Boolean getCommit() {
        return commit;
    }

    public CashTransactionType getType() {
        return type;
    }
}
