package com.coinbase.domain.price.request;

import com.coinbase.domain.account.CbAccount;
import com.coinbase.util.ValidationUtils;
import com.coinbase.builder.AbstractValidatingBuilder;

/**
 * Abstract builder for building subclasses of CbAmountRequest
 * @param <B>
 * @param <O>
 */
public abstract class CbAmountRequestBuilder<B extends CbAmountRequestBuilder,
        O extends CbAmountRequest> extends AbstractValidatingBuilder<O> {
    protected String from;
    protected String amount;
    protected String currency;

    @Override
     public final void validate(){
        ValidationUtils.validateNotNull(from, "Account");
        ValidationUtils.validateNotNull(amount, "Amount");
        ValidationUtils.validateNotNull(currency, "Currency");

        doValidate();
    }

    protected abstract void doValidate();
    protected abstract B getThis();

    public B setAmount(String amount) {
        this.amount = amount;
        return getThis();
    }

    public B setCurrency(String currency) {
        this.currency = currency;
        return getThis();
    }

    public String getFrom() {
        return from;
    }

    public String getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public CbAmountRequestBuilder setFrom(CbAccount account) {
        return setFrom(account.getId());
    }

    public CbAmountRequestBuilder setFrom(String from) {
        this.from = from;
        return this;
    }
}
