package com.coinbase.domain.transaction.request;

import com.coinbase.domain.account.CbAccount;
import com.coinbase.util.ValidationUtils;
import com.coinbase.domain.transaction.TransactionType;

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
 * Builds a request for transfer money.  The coinbase API currently has a bug so this is not possible.
 *
 * @author antlen
 */
public class CbTransferMoneyRequestBuilder extends CbTransactionTypeRequestBuilder<CbTransferMoneyRequestBuilder> {

    protected String toAccount;

    public CbTransferMoneyRequestBuilder() {
        super(TransactionType.TRANSFER);
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
        return new CbMoneyRequest(type, toAccount, amount,currency,description,null,
                null,null, null, null);
    }

    @Override
    public void doValidate(){
        ValidationUtils.validateNotNull(toAccount, "Recipient's Account");
    }


    @Override
    protected CbTransferMoneyRequestBuilder getThis() {
        return this;
    }
}
