package com.coinbase;

import com.coinbase.client.CoinbaseAsyncRestClient;
import com.coinbase.client.CoinbaseRestClient;
import com.coinbase.client.impl.CoinbaseAsyncRestClientImpl;
import com.coinbase.client.impl.CoinbaseRestClientImpl;
import com.coinbase.client.security.CoinbaseApiV2AuthHeaderGenerator;
import com.coinbase.client.security.RequestAuthenticationFilter;

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

    private final RequestAuthenticationFilter filter;
    private int pageSize = 100;

    public CoinbaseClientBuilder setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    /**
     * Avoid ever containing the secret key in a String if you can help it and use byte[] directly.
     * @param apiKey
     * @param secretKey
     */
    public CoinbaseClientBuilder(String apiKey, String secretKey){
        filter = new RequestAuthenticationFilter(new CoinbaseApiV2AuthHeaderGenerator(apiKey, secretKey.getBytes()));
    }

    public CoinbaseClientBuilder(String apiKey, byte[] secretKey){
        filter = new RequestAuthenticationFilter(new CoinbaseApiV2AuthHeaderGenerator(apiKey, secretKey));
    }

    /**
     * Returns a rest client that will execute the requests synchronously and block waiting for the reply.
     *
     * @return
     */
    public CoinbaseRestClient buildRestClient() {
        return new CoinbaseRestClientImpl(filter, pageSize);
    }

    /**
     * The requests will be processed asynchronously by the http engine and
     * the response will be handed off to the response ExecutorService if provided.
     *
     * @param responseService
     * @return
     */
    public CoinbaseAsyncRestClient buildAsyncRestClient(ExecutorService responseService) {
        return new CoinbaseAsyncRestClientImpl(filter, responseService, pageSize);
    }

    public CoinbaseAsyncRestClient buildAsyncRestClient() {
        return new CoinbaseAsyncRestClientImpl(filter, pageSize);
    }
}
