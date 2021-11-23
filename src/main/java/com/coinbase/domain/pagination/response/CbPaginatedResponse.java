package com.coinbase.domain.pagination.response;

import com.coinbase.domain.general.response.CbResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.coinbase.domain.pagination.CbPagination;

import javax.annotation.Generated;
import java.util.List;

/**
 * A paginated response.
 * @param <T>
 */
@Generated("jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CbPaginatedResponse<T> extends CbResponse<List<T>> {

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