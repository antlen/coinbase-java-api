package org.estonlabs.coinbase.domain.transaction.request;

import org.estonlabs.coinbase.domain.transaction.TransactionType;

import static org.estonlabs.coinbase.util.ValidationUtils.validateNotNull;

/**
 * Builds a request for requesting money
 */
public class CbRequestMoneyRequestBuilder extends CbTransactionTypeRequestBuilder<CbRequestMoneyRequestBuilder> {

    protected String toEmail;

    public CbRequestMoneyRequestBuilder() {
        super( TransactionType.REQUEST);
    }

    public CbRequestMoneyRequestBuilder setToEmail(String toEmail) {
        this.toEmail = toEmail;
        return getThis();
    }

    public String getToEmail() {
        return toEmail;
    }

    @Override
    public CbMoneyRequest doBuild() {
        return new CbMoneyRequest(type, from,toEmail, amount,currency,description,null,
                null,null, null, null);
    }

    @Override
    public void doValidate(){
        validateNotNull(toEmail, "Recipient's Email");
    }

    @Override
    protected CbRequestMoneyRequestBuilder getThis() {
        return this;
    }
}
