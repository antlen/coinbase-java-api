package com.coinbase.client.async;

import com.coinbase.callback.PaginatedCollectionCallback;
import com.coinbase.client.api.CoinbaseRequestApi;
import com.coinbase.callback.ResponseCallback;
import com.coinbase.client.async.callback.PaginatedResponseTransformerCallback;
import com.coinbase.client.async.callback.ResponseTransformerCallback;
import com.coinbase.client.api.request.PaginatedGetRequest;
import com.coinbase.client.api.request.RequestInvoker;
import com.coinbase.domain.pagination.response.CbPaginatedResponse;

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
 * An implementation of a CoinbaseASyncClient that relies on the internal async method of the RequestInvoker.
 *
 * @author antlen
 */
public class CoinbaseASyncRestClient extends AbstractCoinbaseASyncRestClient implements CoinbaseASyncClient {

    public CoinbaseASyncRestClient(CoinbaseRequestApi api) {
        super(api);
    }

    @Override
    public void reconnect() {
        api.reconnect();
    }

    @Override
    public void setLogResponsesEnabled(boolean b) {
        api.setLogResponsesEnabled(b);
    }

    protected <R> void invoke(RequestInvoker i, ResponseCallback<R> cb){
        i.async(new ResponseTransformerCallback(cb));
    }

    protected <R> void invoke(PaginatedGetRequest<? extends CbPaginatedResponse<R>> i, PaginatedCollectionCallback<R> cb){
        i.async(new PaginatedResponseTransformerCallback(cb){
            @Override
            public void handleNext(PaginatedGetRequest next) {
                invoke(next, cb);
            }
        });
    }
}
