package org.estonlabs.coinbase.domain.user;

import org.estonlabs.coinbase.domain.DataProvider;
import org.estonlabs.coinbase.domain.account.CbAuthInfo;

public class CbAuthInfoWrapper implements DataProvider<CbAuthInfo> {

    private CbAuthInfo data;

    @Override
    public CbAuthInfo getData(){
       return data;
    }

    public void setData(CbAuthInfo data) {
        this.data = data;
    }
}
