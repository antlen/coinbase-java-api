package org.estonlabs.coinbase.domain.transaction;

import org.estonlabs.coinbase.domain.CbPagination;
import org.estonlabs.coinbase.domain.DataProvider;
import org.estonlabs.coinbase.domain.account.CbAccount;

import javax.annotation.Generated;
import java.util.List;

@Generated("jsonschema2pojo")
public class CbPaymentMethodCollection implements DataProvider<List<CbPaymentMethod>> {

    private CbPagination pagination;
    private List<CbPaymentMethod> data = null;

    public CbPagination getPagination() {
        return pagination;
    }

    public void setPagination(CbPagination pagination) {
        this.pagination = pagination;
    }

    @Override
    public List<CbPaymentMethod> getData() {
        return data;
    }

    public void setData(List<CbPaymentMethod> data) {
        this.data = data;
    }

}