package org.estonlabs.coinbase.domain.pagination;

import org.estonlabs.coinbase.domain.Response;

import javax.annotation.Generated;
import java.util.List;

@Generated("jsonschema2pojo")
public class CbPaginatedResponse<T> implements Response<List<T>> {

    private CbPagination pagination;

    private List<T> data = null;

    public CbPagination getPagination() {
        return pagination;
    }

    @Override
    public List<T> getData() {
        return data;
    }

}