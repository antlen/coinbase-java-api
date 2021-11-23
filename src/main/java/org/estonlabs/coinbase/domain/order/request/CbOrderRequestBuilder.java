package org.estonlabs.coinbase.domain.order.request;

import static org.estonlabs.coinbase.util.ValidationUtils.*;
import org.estonlabs.coinbase.domain.price.request.CbAmountRequestBuilder;
import org.estonlabs.coinbase.domain.trade.Side;

/**
 * Builds an order request.  commit defaults to false.
 */
public class CbOrderRequestBuilder extends CbAmountRequestBuilder<CbOrderRequestBuilder,CbOrderRequest> {

    private Side side;
    private String paymentMethod;
    private Boolean allowQueuing;
    private Boolean commit;
    private Boolean quote;

    public Side getSide() {
        return side;
    }

    public CbOrderRequestBuilder setSide(Side side) {
        this.side = side;
        return this;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public CbOrderRequestBuilder setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    public Boolean getAllowQueuing() {
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
        validateNotNull(side, "Side");
        validateNotNull(paymentMethod, "Payment Method");
    }

    @Override
    public CbOrderRequest doBuild() {
        return new CbOrderRequest(from, side, amount, currency, paymentMethod, allowQueuing, commit, quote);
    }

}
