package org.estonlabs.coinbase.domain;


import java.util.List;

public class CbDataProvider<T> implements DataProvider<T>{

    T data;
    List<CbWarning> warnings;

    @Override
    public T getData() {
        return data;
    }

    public List<CbWarning> getWarnings() {
        return warnings;
    }
}
