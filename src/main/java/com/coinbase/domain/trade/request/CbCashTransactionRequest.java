package com.coinbase.domain.trade.request;

import com.coinbase.domain.price.request.CbAmountRequest;
import com.coinbase.domain.trade.CashTransactionType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
 * A cash transaction request for a withdrawal or deposit.
 *
 * @author antlen
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

    @JsonProperty("payment_method")
    public String getPaymentMethod() {
        return paymentMethod;
    }

    @JsonProperty("commit")
    public Boolean isCommit() {
        return commit;
    }

    public CashTransactionType getType() {
        return type;
    }
}
