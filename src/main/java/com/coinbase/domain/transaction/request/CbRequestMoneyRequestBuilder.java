package com.coinbase.domain.transaction.request;

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
 * Builds a request for requesting money
 *
 * @author antlen
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
        ValidationUtils.validateNotNull(toEmail, "Recipient's Email");
    }

    @Override
    protected CbRequestMoneyRequestBuilder getThis() {
        return this;
    }
}
