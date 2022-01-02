package com.coinbase.client.api.request;

import com.coinbase.callback.PaginatedResponseCallback;
import com.coinbase.callback.ResponseCallback;
import com.coinbase.domain.pagination.response.CbPaginatedResponse;
import com.coinbase.exception.CbApiHttpException;

import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

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
 * Wrapper for a paginated get request
 *
 * @author antlen
 * @param <O>
 */
public class PaginatedGetRequest<O extends CbPaginatedResponse<?>> extends AbstractRequest<O> {
    protected static final String LIMIT = "limit";
    protected static final String STARTING_AFTER = "starting_after";

    private PaginatedGetRequest<O> next = null;
    private final int pageSize;
    private String after;

    public PaginatedGetRequest(Class<O> klass, WebTarget target, int pageSize) {
        super(klass, RequestType.GET, target);
        this.pageSize =pageSize;
    }

    public PaginatedGetRequest<O> next() {
        return next;
    }

    @Override
    public O sync() {
        setQueryParams();
        O res =  jsonRequest().get(klass);
        updateNext(res);
        return res;
    }

    @Override
    public Future<O> async(ResponseCallback<O> cb) {
        setQueryParams();
        return new FutureWrapper(asyncJson().get(new CallBack(cb)));
    }

    public Future<O> async(PaginatedResponseCallback<O> cb) {
        setQueryParams();
        return new FutureWrapper(asyncJson().get(new PgCallBack(cb)));
    }

    public final Callable<O> prepare(PaginatedResponseCallback<O> delegate) {
        return () -> async(delegate).get();
    }

    private void setQueryParams() {
        if(pageSize >0){
            queryParam(LIMIT, Integer.toString(pageSize));
        }
        if(after != null){
            queryParam(STARTING_AFTER, after);
        }
    }

    private PaginatedGetRequest<O> updateNext(O o){
        if(o.getPagination()!=null && o.getPagination().getNextUri()!=null){
            next = new PaginatedGetRequest<>(klass, base, pageSize);
            next.after = o.getPagination().getNextStartingAfter();
            return next;
        }
        return null;
    }

    protected class PgCallBack implements InvocationCallback<Response> {
        protected final PaginatedResponseCallback<O> delegate;

        public PgCallBack(PaginatedResponseCallback<O> delegate) {
            this.delegate = delegate;
        }

        @Override
        public final void completed(Response r) {
            O entity = getAndCloseEntity(r);
            if(hasFailed(entity)){
                failed(new CbApiHttpException(entity));
            }else{
                delegate.pagedResults(entity, updateNext(entity));
            }
        }

        @Override
        public void failed(Throwable t) {
            delegate.failed(t);
        }
    }
}
