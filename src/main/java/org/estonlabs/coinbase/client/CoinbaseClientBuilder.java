package org.estonlabs.coinbase.client;

import org.estonlabs.coinbase.client.connection.CoinbaseRestConnection;
import org.estonlabs.coinbase.client.connection.auth.CoinbaseApiV2SecuredEndpoint;

/**
 * This is the starting point to create a CoinbaseClient.
 */
public class CoinbaseClientBuilder {

    private final String apiKey;
    private final byte[] secretKey;

    private int paginationLimit = 25;

    public CoinbaseClientBuilder(String apiKey, byte[] secretKey){
        this.apiKey=apiKey;
        this.secretKey= secretKey;
    }

    public CoinbaseClientBuilder setPaginationLimit(int paginationLimit){
        this.paginationLimit=paginationLimit;
        return this;
    }

    public CoinbaseClient create() {
        CoinbaseApiV2SecuredEndpoint endPoint = new CoinbaseApiV2SecuredEndpoint(apiKey, secretKey);
        CoinbaseRestConnection connection = new CoinbaseRestConnection(endPoint);
        return new CoinbaseRestClient(connection, paginationLimit);
    }

}
