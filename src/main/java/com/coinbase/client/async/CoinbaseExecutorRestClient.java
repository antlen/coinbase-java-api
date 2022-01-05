package com.coinbase.client.async;

import com.coinbase.callback.PaginatedCollectionCallback;
import com.coinbase.callback.ResponseCallback;
import com.coinbase.client.api.request.PaginatedRequest;
import com.coinbase.client.async.callback.PaginatedResponseTransformerCallback;
import com.coinbase.client.async.callback.ResponseTransformerCallback;
import com.coinbase.client.api.CoinbaseRequestApi;
import com.coinbase.client.api.request.RequestInvoker;
import com.coinbase.domain.general.response.CbResponse;
import com.coinbase.domain.pagination.response.CbPaginatedResponse;

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
 * An implementation of CoinbaseASyncClient that uses inbound and outbound executors for finer threading control.
 *
 * @author antlen
 */
public class CoinbaseExecutorRestClient extends AbstractCoinbaseASyncRestClient{
    private final ExecutorService requestService;
    private final ExecutorService responseService;

    public CoinbaseExecutorRestClient(CoinbaseRequestApi api, ExecutorService requestService,
                                      ExecutorService responseService ) {
        super(api);
        this.requestService =requestService;
        this.responseService =responseService;
    }

    @Override
    protected <R> void invoke(RequestInvoker i, ResponseCallback<R> cb) {
        requestService.submit(i.prepare(new MyResponseTransformerCallback(cb)));
    }

    @Override
    protected <R> void invoke(PaginatedRequest<? extends CbPaginatedResponse<R>> i, PaginatedCollectionCallback<R> cb) {
        requestService.submit(i.prepare(new MyPaginatedResponseTransformerCallback(cb)));
    }

    private class MyPaginatedResponseTransformerCallback<DATA, RESPONSE extends CbPaginatedResponse<DATA>>
            extends PaginatedResponseTransformerCallback<DATA,RESPONSE>{

        public MyPaginatedResponseTransformerCallback(PaginatedCollectionCallback<DATA> cb) {
            super(cb);
        }

        @Override
        public void handleNext(PaginatedRequest<RESPONSE> next) {
            invoke(next, cb);
        }

        @Override
        public void failed(Throwable throwable) {
            responseService.submit(() -> super.failed(throwable));
        }

        @Override
        protected void handlerResults(RESPONSE response, boolean more) {
            responseService.submit(() -> super.handlerResults(response, more));
        }
    }

    private class MyResponseTransformerCallback<DATA, RESPONSE extends CbResponse<DATA>>
            extends ResponseTransformerCallback<DATA,RESPONSE> {

        public MyResponseTransformerCallback(ResponseCallback<DATA> cb) {
            super(cb);
        }

        @Override
        protected void callCompleted(DATA d) {
            responseService.submit(() -> MyResponseTransformerCallback.super.callCompleted(d));
        }

        @Override
        public void failed(Throwable throwable) {
            responseService.submit(() -> super.failed(throwable));
        }
    }
}
