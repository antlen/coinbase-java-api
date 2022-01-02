package com.coinbase.client.async.callback;

import com.coinbase.callback.ResponseCallback;
import com.coinbase.domain.general.response.CbResponse;

import javax.ws.rs.client.InvocationCallback;
import java.util.function.Function;

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
 * internal base callback for handling conversion into the underlying data.
 *
 * @author antlen
 * @param <DATA>
 * @param <RESPONSE>
 */
public class ResponseTransformerCallback<DATA, RESPONSE extends CbResponse<DATA>> implements ResponseCallback<RESPONSE> {
    private final Function<DATA,DATA> adapt;
    private final ResponseCallback<DATA> cb;

    public ResponseTransformerCallback(ResponseCallback<DATA> cb) {
        this(cb, null);
    }

    public ResponseTransformerCallback(ResponseCallback<DATA> cb, Function<DATA,DATA> adapt) {
        this.cb = cb;
        this.adapt=adapt;
    }

    @Override
    public void failed(Throwable throwable) {
        cb.failed(throwable);
    }

    @Override
    public final void completed(RESPONSE response) {
        DATA d = adapt==null?response.getData(): adapt.apply(response.getData());
        callCompleted(d);
    }

    protected void callCompleted(DATA d) {
        cb.completed(d);
    }
}
