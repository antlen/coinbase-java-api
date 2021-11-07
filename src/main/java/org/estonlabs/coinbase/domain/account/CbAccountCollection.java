package org.estonlabs.coinbase.domain.account;

import java.util.List;
import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class CbAccountCollection {

    private CbAccountPagination pagination;
    private List<CbAccount> data = null;

    public CbAccountPagination getPagination() {
        return pagination;
    }

    public void setPagination(CbAccountPagination pagination) {
        this.pagination = pagination;
    }

    public List<CbAccount> getData() {
        return data;
    }

    public void setData(List<CbAccount> data) {
        this.data = data;
    }

}