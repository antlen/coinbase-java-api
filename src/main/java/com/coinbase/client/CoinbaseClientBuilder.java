package com.coinbase.client;

import com.coinbase.client.api.StandaloneCoinbaseApiExecutor;
import com.coinbase.client.async.CoinbaseASyncClient;
import com.coinbase.client.async.CoinbaseASyncRestClient;
import com.coinbase.client.async.CoinbaseExecutorRestClient;
import com.coinbase.client.connection.CoinbaseRestConnection;
import com.coinbase.client.connection.auth.CoinbaseApiV2SecuredEndpoint;
import com.coinbase.client.api.CoinbaseDefaultRequestApi;
import com.coinbase.client.api.CoinbaseRequestApi;
import com.coinbase.client.sync.CoinbaseSyncClient;
import com.coinbase.client.sync.CoinbaseSyncRestClient;

import java.util.concurrent.ExecutorService;

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
public class CoinbaseClientBuilder {

    private final CoinbaseApiV2SecuredEndpoint endPoint;
    private CoinbaseRestConnection connection;

    public CoinbaseClientBuilder(String apiKey, byte[] secretKey){
        endPoint = new CoinbaseApiV2SecuredEndpoint(apiKey, secretKey);
        connection = new CoinbaseRestConnection(endPoint, 25);
    }

    public CoinbaseClientBuilder setPaginationLimit(int paginationLimit){
        connection = new CoinbaseRestConnection(endPoint, paginationLimit);
        return this;
    }

    public CoinbaseASyncClient buildASyncClient() {
        return new CoinbaseASyncRestClient(buildApi());
    }

    public CoinbaseASyncClient buildASyncClient( ExecutorService outbound,ExecutorService inbound) {
        return new CoinbaseExecutorRestClient(buildApi(), outbound, inbound);
    }

    public CoinbaseSyncClient buildSyncClient() {
        return new CoinbaseSyncRestClient(buildApi());
    }

    public CoinbaseRequestApi buildApi() {
        return new CoinbaseDefaultRequestApi(connection);
    }

    public StandaloneCoinbaseApiExecutor buildExecutor(ExecutorService requestService,ExecutorService responseService){
        return new StandaloneCoinbaseApiExecutor(buildApi(),requestService, responseService);
    }
}
