package com.coinbase.client.api;

import com.coinbase.callback.FailureCallback;
import com.coinbase.callback.PaginatedResponseCallback;
import com.coinbase.callback.ResponseCallback;
import com.coinbase.client.api.request.PaginatedRequest;
import com.coinbase.client.api.request.RequestInvoker;
import com.coinbase.domain.general.response.CbResponse;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class StandaloneCoinbaseApiExecutor {
    private final CoinbaseRequestApi api;
    private final ExecutorService requestService;
    private final ExecutorService responseService;

    public StandaloneCoinbaseApiExecutor(CoinbaseRequestApi api, ExecutorService requestService, ExecutorService responseService) {
        this.api = api;
        this.requestService = requestService;
        this.responseService = responseService;
    }

    public CoinbaseRequestApi getApi() {
        return api;
    }

    public <R> Future<R> invoke(RequestInvoker i, ResponseCallback<R> cb) {
        return requestService.submit(i.prepare(new Callback(cb)));
    }


    public <R extends CbResponse> Future<R> invoke(PaginatedRequest<R> i, PaginatedResponseCallback<R> cb) {
        return requestService.submit(i.prepare(new PgCallback(cb)));
    }

    private class Callback<R> extends Failed<ResponseCallback<R>> implements ResponseCallback<R>{
        public Callback(ResponseCallback<R> cb) {
            super(cb);
        }

        @Override
        public void completed(R response) {
            responseService.submit(() -> cb.completed(response));
        }
    }

    private class PgCallback<R extends CbResponse> extends Failed<PaginatedResponseCallback<R>> implements PaginatedResponseCallback<R>{
        public PgCallback(PaginatedResponseCallback<R> failureCallback) {
            super(failureCallback);
        }

        @Override
        public void pagedResults(R response, PaginatedRequest<R> next) {
            responseService.submit(() -> cb.pagedResults(response, next));
            if(next != null){
                invoke(next, cb);
            }
        }
    }

    private class Failed<CB extends FailureCallback> implements FailureCallback{
        protected CB cb;

        public Failed(CB cb) {
            this.cb = cb;
        }

        @Override
        public void failed(Throwable throwable) {
            responseService.submit(() -> cb.failed(throwable));
        }
    }
}
