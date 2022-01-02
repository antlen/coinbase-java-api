package com.coinbase.client.api.request;

import com.coinbase.callback.ResponseCallback;
import com.coinbase.domain.general.response.CbResponse;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

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
 * Wrapper for a Get request
 *
 * @author antlen
 * @param <O>
 */
public class GetRequest<O extends CbResponse> extends AbstractRequest<O> {
    private static AtomicInteger index = new AtomicInteger();
    Client c;
    public GetRequest(Class<O> klass, WebTarget target) {
        super(klass, RequestType.GET, target);
    }

    @Override
    public O sync() {
        return jsonRequest().get(klass);
    }

    @Override
    public Future<O> async(ResponseCallback<O> cb) {
        return new FutureWrapper(asyncJson().get(new CallBack(cb)));
    }
}
