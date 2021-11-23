package com.coinbase.domain.transaction.request;

import com.coinbase.domain.price.request.CbAmountRequestBuilder;
import com.coinbase.domain.transaction.TransactionType;

public abstract class CbTransactionTypeRequestBuilder <B extends CbTransactionTypeRequestBuilder> extends CbAmountRequestBuilder<B, CbMoneyRequest> {

    protected final String type;
    protected String description;

    public CbTransactionTypeRequestBuilder(TransactionType type) {
        this.type = type.toString().toLowerCase();
    }

    public String getType() {
        return type;
    }


    public String getDescription() {
        return description;
    }

    public B setDescription(String description) {
        this.description = description;
        return getThis();
    }
}
