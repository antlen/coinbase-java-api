package com.coinbase.domain.transaction.request;

import com.coinbase.util.ValidationUtils;
import com.coinbase.domain.transaction.TransactionType;

/**
 * Builds a request for sending money
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
