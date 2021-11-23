package com.coinbase.client;

import com.coinbase.builder.Builder;
import com.coinbase.client.connection.CoinbaseRestConnection;
import com.coinbase.client.connection.auth.CoinbaseApiV2SecuredEndpoint;

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
 * This is the starting point to create a CoinbaseClient.
 *
 * @author antlen
 */
public class CoinbaseClientBuilder implements Builder<CoinbaseClient> {

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

    @Override
    public CoinbaseClient build() {
        CoinbaseApiV2SecuredEndpoint endPoint = new CoinbaseApiV2SecuredEndpoint(apiKey, secretKey);
        CoinbaseRestConnection connection = new CoinbaseRestConnection(endPoint);
        return new CoinbaseRestClient(connection, paginationLimit);
    }
}
