package org.estonlabs.coinbase.domain.transaction.request;

import org.estonlabs.coinbase.domain.account.CbAccount;
import org.estonlabs.coinbase.domain.transaction.TransactionType;

import static org.estonlabs.coinbase.util.ValidationUtils.validateNotNull;

/**
 * Builds a request for transfer money.  The coinbase API currently has a bug so this is not possible.
 */
public class CbTransferMoneyRequestBuilder extends CbTransactionTypeRequestBuilder<CbTransferMoneyRequestBuilder> {

    protected String toAccount;

    public CbTransferMoneyRequestBuilder() {
        super( TransactionType.TRANSFER);
    }

    public CbTransferMoneyRequestBuilder setToAccount(CbAccount account) {
        return setToAccount(account.getId());
    }

    public CbTransferMoneyRequestBuilder setToAccount(String to) {
        this.toAccount = to;
        return this;
    }

    public String getToAccount() {
        return toAccount;
    }

    @Override
    public CbMoneyRequest doBuild() {
        return new CbMoneyRequest(type, from,toAccount, amount,currency,description,null,
                null,null, null, null);
    }

    @Override
    public void doValidate(){
        validateNotNull(toAccount, "Recipient's Account");
    }


    @Override
    protected CbTransferMoneyRequestBuilder getThis() {
        return this;
    }
}
