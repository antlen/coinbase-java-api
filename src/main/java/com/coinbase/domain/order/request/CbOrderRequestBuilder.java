package com.coinbase.domain.order.request;

import com.coinbase.util.ValidationUtils;
import com.coinbase.domain.price.request.CbAmountRequestBuilder;
import com.coinbase.domain.trade.Side;

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
 * builds an order request. The default value for 'commit' is false.
 *
 * @author antlen
 */
public class CbOrderRequestBuilder extends CbAmountRequestBuilder<CbOrderRequestBuilder,CbOrderRequest> {

    private String paymentMethod;
    private Boolean allowQueuing;
    private Boolean commit;
    private Boolean quote;

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public CbOrderRequestBuilder setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    public Boolean isAllowQueuing() {
        return allowQueuing;
    }

    public CbOrderRequestBuilder setAllowQueuing(Boolean allowQueuing) {
        this.allowQueuing = allowQueuing;
        return this;
    }

    public Boolean getCommit() {
        return commit;
    }

    public CbOrderRequestBuilder setCommit(Boolean commit) {
        this.commit = commit;
        return this;
    }

    public Boolean getQuote() {
        return quote;
    }

    public CbOrderRequestBuilder setQuote(Boolean quote) {
        this.quote = quote;
        return this;
    }

    @Override
    protected CbOrderRequestBuilder getThis() {
        return this;
    }

    @Override
    public void doValidate() {
        ValidationUtils.validateNotNull(paymentMethod, "Payment Method");
    }

    @Override
    public CbOrderRequest doBuild() {
        return new CbOrderRequest(amount, currency, paymentMethod, allowQueuing, commit, quote);
    }

}
