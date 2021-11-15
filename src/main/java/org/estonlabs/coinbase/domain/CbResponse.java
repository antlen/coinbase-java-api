package org.estonlabs.coinbase.domain;


import java.util.List;

public class CbResponse<T> implements Response<T> {

    private T data;
    private List<CbWarning> warnings;

    @Override
    public T getData() {
        return data;
    }

    public List<CbWarning> getWarnings() {
        return warnings;
    }
}
