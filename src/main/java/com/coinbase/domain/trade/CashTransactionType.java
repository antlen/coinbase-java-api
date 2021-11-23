package com.coinbase.domain.trade;

/**
 * The type of cash transaction.
 */
public enum CashTransactionType {
    DEPOSIT("deposits"),
    WITHDRAWAL("withdrawals");

    private final String uri;
    CashTransactionType(final String uri){
        this.uri=uri;
    }

    public String getUri() {
        return uri;
    }
}
