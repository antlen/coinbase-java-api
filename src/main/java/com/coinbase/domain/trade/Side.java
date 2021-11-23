package com.coinbase.domain.trade;

/**
 * The side for orders and trades.
 */
public enum Side {
    BUY("buys"),
    SELL("sells");

    private final String uri;
    Side(final String uri){
        this.uri=uri;
    }

    public String getUri() {
        return uri;
    }
}
