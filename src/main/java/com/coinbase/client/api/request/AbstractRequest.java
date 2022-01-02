package com.coinbase.client.api.request;

import com.coinbase.callback.ResponseCallback;
import com.coinbase.domain.general.response.CbResponse;
import com.coinbase.exception.CbApiHttpException;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.*;

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
 * Provides basic functionality for all type of requests.
 *
 * @author antlen
 * @param <O>
 */
public abstract class AbstractRequest<O extends CbResponse> implements RequestInvoker<O> {
    protected final Class<O> klass;
    protected final RequestType type;
    protected final WebTarget base;
    protected WebTarget target;

    public AbstractRequest(Class<O> klass, RequestType type, WebTarget target) {
        this.klass = klass;
        this.type = type;
        this.base = target;
        this.target = target;
    }

    @Override
    public final Callable<O> prepare(ResponseCallback<O> delegate) {
        return () -> async(delegate).get();
    }

    public AbstractRequest<O> queryParam(String key, Object v){
        target = target.queryParam(key, v);
        return this;
    }

    protected Invocation.Builder jsonRequest() {
        return target.request(MediaType.APPLICATION_JSON);
    }

    protected O getAndCloseEntity(Response response){
        O o = response.readEntity(klass);
        response.close();
        return o;
    }

    protected boolean hasFailed(O o){
        return (o != null && o.getData() == null);
    }

    protected AsyncInvoker asyncJson() {
        return jsonRequest().async();
    }

    protected class FutureWrapper implements Future<O>{
        private final Future<Response> delegate;

        public  FutureWrapper(Future<Response> delegate) {
            this.delegate = delegate;
        }

        @Override
        public boolean cancel(boolean mayInterruptIfRunning) {
            return delegate.cancel(mayInterruptIfRunning);
        }

        @Override
        public boolean isCancelled() {
            return delegate.isCancelled();
        }

        @Override
        public boolean isDone() {
            return delegate.isDone();
        }

        @Override
        public O get() throws InterruptedException, ExecutionException{
            return validate(getAndCloseEntity(delegate.get()));
        }

        public O validate (O o) throws ExecutionException{
            if(hasFailed(o)){
                throw new ExecutionException(new CbApiHttpException(o));
            }
            return o;
        }

        @Override
        public O get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
            return validate(getAndCloseEntity(delegate.get(timeout, unit)));
        }
    }

    protected class CallBack implements InvocationCallback<Response> {
        protected final ResponseCallback<O> delegate;

        public CallBack(ResponseCallback<O> delegate) {
            this.delegate = delegate;
        }

        @Override
        public final void completed(Response r) {
            O entity = getAndCloseEntity(r);
            if(hasFailed(entity)){
                failed(new CbApiHttpException(entity));
            }else{
                delegate.completed(entity);
            }
        }

        @Override
        public void failed(Throwable t) {
            delegate.failed(t);
        }
    }
}
