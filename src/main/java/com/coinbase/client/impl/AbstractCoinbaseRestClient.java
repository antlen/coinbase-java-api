package com.coinbase.client.impl;

import com.coinbase.client.security.RequestAuthenticationFilter;
import com.coinbase.client.service.CoinbaseAPIv2Service;
import com.coinbase.client.service.ServiceFactory;
import com.coinbase.domain.pagination.response.CbPaginatedResponse;
import java.time.format.DateTimeFormatter;

/**
 * The MIT License (MIT)
 *
 *	Copyright (c) 2021 antlen
 *
 *	Permission is hereby granted, free of charge, to any person obtaining a copy
 *	of this software and associated documentation files (the "Software"), to deal
 *	in the Software without restriction, including without limitation the rights
 *	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *	copies of the Software, and to permit persons to whom the Software is
 *	furnished to do so, subject to the following conditions:
 *
 *	The above copyright notice and this permission notice shall be included in all
 *	copies or substantial portions of the Software.
 *
 *	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *	SOFTWARE.
 *
 * ------------------------------------------------
 * Abstract base class for the RestClients that handles any duplication between clients.
 *
 * @author antlen
 */
public abstract class AbstractCoinbaseRestClient{
    protected static DateTimeFormatter PRICE_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final ServiceFactory factory;
    private final RequestAuthenticationFilter filter;
    protected CoinbaseAPIv2Service service;
    protected int pageSize;

    public AbstractCoinbaseRestClient(RequestAuthenticationFilter filter,  int pageSize) {
        this.factory = new ServiceFactory(filter);
        this.filter=filter;
        this.pageSize=pageSize;
        reconnect();
    }

    public final void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    protected final String getNext(CbPaginatedResponse o){
        if(o.getPagination()!=null && o.getPagination().getNextUri()!=null) {
            return o.getPagination().getNextStartingAfter();
        }
        return null;
    }

    public final void reconnect() {
        service = factory.buildRestService();
    }
    public void setLogResponsesEnabled(boolean b) {
        filter.setLoggingEnabled(b);
    }
}
