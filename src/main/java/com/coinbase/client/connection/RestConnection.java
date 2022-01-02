package com.coinbase.client.connection;

import com.coinbase.client.api.request.*;
import com.coinbase.domain.general.response.CbResponse;
import com.coinbase.domain.pagination.response.CbPaginatedResponse;

/**
 * The MIT License (MIT)
 *
 *	Copyright (c) 2016 antlen
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
 * Rest connection for making calls to the server.
 *
 * @author antlen
 */
public interface RestConnection {
    /**
     * turns on / off logging the raw json messages from the requests and responses
     * @param b
     */
    void setLogJsonMessages(boolean b);

    /**
     * reestablish the session to the server
     */
    void reconnect();

    <I,O  extends CbResponse> PostRequest<I,O> post(Class<O> c, String uri);

    <I,O extends CbResponse> PutRequest<I,O> put(Class<O> c, String uri);

    <O extends CbResponse> GetRequest<O> get(Class<O> c, String uri);

    <O extends CbPaginatedResponse<?>> PaginatedGetRequest<O> paginatedGet(Class<O> c, String uri);

    DeleteRequest delete(String uri);
}
