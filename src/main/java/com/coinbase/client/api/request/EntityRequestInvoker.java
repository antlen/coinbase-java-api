package com.coinbase.client.api.request;

public interface EntityRequestInvoker<I,O> extends RequestInvoker<O>{
    EntityRequestInvoker<I, O> with(I input);
}
