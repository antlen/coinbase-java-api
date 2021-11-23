package org.estonlabs.coinbase.domain.order.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.estonlabs.coinbase.domain.price.request.CbAmountRequest;
import org.estonlabs.coinbase.domain.trade.Side;

/**
 * A order request.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CbOrderRequest extends CbAmountRequest {

    @JsonIgnore
    private final Side side;

    @JsonProperty("payment_method")
    private final String paymentMethod;
    @JsonProperty("agree_btc_amount_varies")
    private final Boolean allowQueuing;
    @JsonProperty("commit")
    private final Boolean commit;
    @JsonProperty("quote")
    private final Boolean quote;

    CbOrderRequest(String account, Side side, String amount, String currency, String paymentMethod, Boolean allowQueuing, Boolean commit, Boolean quote) {
        super(account, amount, currency);
        this.side = side;
        this.paymentMethod = paymentMethod;
        this.allowQueuing = allowQueuing;
        this.commit = commit;
        this.quote = quote;
    }

    public Side getSide() {
        return side;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public Boolean getAllowQueuing() {
        return allowQueuing;
    }

    public Boolean getCommit() {
        return commit;
    }

    public Boolean getQuote() {
        return quote;
    }
}
