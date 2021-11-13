package org.estonlabs.coinbase.domain;

import org.estonlabs.coinbase.domain.account.CbAccount;

import javax.annotation.Generated;
import java.util.List;

@Generated("jsonschema2pojo")
public class CbPaginationCollection<T> implements DataProvider<List<T>> {

    private CbPagination pagination;

    private List<T> data = null;

    public CbPagination getPagination() {
        return pagination;
    }

    public void setPagination(CbPagination pagination) {
        this.pagination = pagination;
    }

    @Override
    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

}