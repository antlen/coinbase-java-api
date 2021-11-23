package com.coinbase.domain.transaction.request;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.coinbase.domain.price.request.CbAmountRequest;

import javax.annotation.Generated;

/**
 * A request for requesting / sending / transfering money.  Instantiate using a builder.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "type",
        "to",
        "amount",
        "currency",
        "description",
        "skip_notifications",
        "fee",
        "idem",
        "to_financial_institution",
        "financial_institution_website"
})
@Generated("jsonschema2pojo")
public class CbMoneyRequest extends CbAmountRequest {

    @JsonProperty("type")
    private final String type;

    @JsonProperty("to")
    private final String to;

    @JsonProperty("description")
    private final String description;

    @JsonProperty("skip_notifications")
    private final Boolean skipNotifications;

    @JsonProperty("fee")
    private final String fee;

    @JsonProperty("idem")
    private final String idem;

    @JsonProperty("to_financial_institution")
    private final Boolean isToFinancialInstitution;

    @JsonProperty("financial_institution_website")
    private final String financialInstitutionWebsite;

    /**
     *
     * @param from - the account to send from
     * @param to - A bitcoin address, bitcoin cash address, litecoin address, ethereum address, or an email of the recipient
     * @param amount - Amount to be sent
     * @param currency - Currency for the amount
     */
    CbMoneyRequest(String type, String from, String to, String amount, String currency){
        this(type, from,to, amount, currency, null,false,null,null,
                false,null);
    }
    /**
     *
     * @param from - the account to send from
     * @param to - A bitcoin address, bitcoin cash address, litecoin address, ethereum address, or an email of the recipient
     * @param amount - Amount to be sent
     * @param currency - Currency for the amount
     * @param description - Optional	Notes to be included in the email that the recipient receives
     * @param skipNotifications - Optional	Don’t send notification emails for small amounts (e.g. tips)
     * @param fee - Transaction fee in BTC/ETH/LTC if you would like to pay it. Fees can be added as a string, such as 0.0005
     * @param idem - Optional	[Recommended] A token to ensure idempotence. If a previous transaction with the same idem parameter already exists for this sender, that previous transaction will be returned and a new one will not be created. Max length 100 characters
     * @param isToFinancialInstitution - Optional	Whether this send is to another financial institution or exchange. Required if this send is to an address and is valued at over USD$3000.
     * @param financialInstitutionWebsite - The website of the financial institution or exchange. Required if to_financial_institution is true.
     */
    CbMoneyRequest(String type, String from, String to, String amount, String currency, String description,
                          Boolean skipNotifications, String fee, String idem, Boolean isToFinancialInstitution,
                          String financialInstitutionWebsite) {
       super(from,amount, currency);
        this.type= type;
        this.to = to;
        this.description = description;
        this.skipNotifications = skipNotifications;
        this.fee = fee;
        this.idem = idem;
        this.isToFinancialInstitution = isToFinancialInstitution;
        this.financialInstitutionWebsite = financialInstitutionWebsite;
    }

    @JsonProperty("to")
    public String getTo() {
        return to;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    public Boolean isSkipNotifications() {
        return skipNotifications;
    }

    public String getFee() {
        return fee;
    }

    public String getIdem() {
        return idem;
    }

    public Boolean isToFinancialInstitution() {
        return isToFinancialInstitution;
    }

    public String getFinancialInstitutionWebsite() {
        return financialInstitutionWebsite;
    }
}
