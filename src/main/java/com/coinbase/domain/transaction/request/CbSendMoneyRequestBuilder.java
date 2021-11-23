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
 * Builds a request for sending money
 *
 * @author antlen
 */
public class CbSendMoneyRequestBuilder extends CbTransactionTypeRequestBuilder<CbSendMoneyRequestBuilder> {

    private boolean skipNotifications;
    private String fee;
    private String idem;
    private boolean isToFinancialInstitution;
    private String financialInstitutionWebsite;
    protected String toAddress;

    public CbSendMoneyRequestBuilder() {
        super( TransactionType.SEND);
    }

    @Override
    public CbMoneyRequest doBuild() {
        return new CbMoneyRequest(type, from,toAddress, amount,currency,description,skipNotifications,
                fee,idem, isToFinancialInstitution, financialInstitutionWebsite);
    }

    @Override
    public void doValidate(){
        ValidationUtils.validateNotNull(toAddress, "Recipient's Address");
    }

    public CbSendMoneyRequestBuilder setToAddress(String toAddress) {
        this.toAddress = toAddress;
        return this;
    }

    public CbSendMoneyRequestBuilder setIdem(String idem) {
        this.idem = idem;
        return this;
    }

    public CbSendMoneyRequestBuilder setToFinancialInstitution(boolean toFinancialInstitution) {
        isToFinancialInstitution = toFinancialInstitution;
        return this;
    }

    public CbSendMoneyRequestBuilder setFinancialInstitutionWebsite(String financialInstitutionWebsite) {
        this.financialInstitutionWebsite = financialInstitutionWebsite;
        return this;
    }

    public CbSendMoneyRequestBuilder setSkipNotifications(boolean skipNotifications) {
        this.skipNotifications = skipNotifications;
        return this;
    }

    public CbSendMoneyRequestBuilder setFee(String fee) {
        this.fee = fee;
        return this;
    }

    @Override
    protected CbSendMoneyRequestBuilder getThis() {
        return this;
    }

    public boolean isSkipNotifications() {
        return skipNotifications;
    }

    public String getFee() {
        return fee;
    }

    public String getIdem() {
        return idem;
    }

    public boolean isToFinancialInstitution() {
        return isToFinancialInstitution;
    }

    public String getFinancialInstitutionWebsite() {
        return financialInstitutionWebsite;
    }

    public String getToAddress() {
        return toAddress;
    }
}
