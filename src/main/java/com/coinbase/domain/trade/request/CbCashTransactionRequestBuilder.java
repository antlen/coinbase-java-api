package com.coinbase.domain.trade.request;

import com.coinbase.domain.price.request.CbAmountRequestBuilder;
import com.coinbase.domain.trade.CashTransactionType;
import com.coinbase.util.ValidationUtils;

public class CbCashTransactionRequestBuilder extends CbAmountRequestBuilder<CbCashTransactionRequestBuilder, CbCashTransactionRequest> {

    private String paymentMethod;
    private Boolean commit;
    private final CashTransactionType type;

    public CbCashTransactionRequestBuilder(CashTransactionType type) {
        this.type = type;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public CbCashTransactionRequestBuilder setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    public Boolean getCommit() {
        return commit;
    }

    public CbCashTransactionRequestBuilder setCommit(Boolean commit) {
        this.commit = commit;
        return this;
    }

    @Override
    protected CbCashTransactionRequestBuilder getThis() {
        return this;
    }

    @Override
    public void doValidate() {
        ValidationUtils.validateNotNull(paymentMethod, "Payment Method");
    }

    @Override
    public CbCashTransactionRequest doBuild() {
        return new CbCashTransactionRequest(type,from, amount, currency, paymentMethod, commit);
    }

}
