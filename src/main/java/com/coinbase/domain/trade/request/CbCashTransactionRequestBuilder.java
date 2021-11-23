package com.coinbase.domain.trade.request;

import com.coinbase.domain.price.request.CbAmountRequestBuilder;
import com.coinbase.domain.trade.CashTransactionType;
import com.coinbase.util.ValidationUtils;
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
 * Builds a cash transaction request.
 *
 * @author antlen
 */
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
