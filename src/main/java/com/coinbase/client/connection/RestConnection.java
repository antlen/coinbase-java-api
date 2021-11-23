package com.coinbase.client.connection;

import java.util.Map;

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
     * makes a get request to the URI and decodes the json response into
     * an object of type responseType.
     *
     * @param responseType
     * @param uri
     * @param <O>
     * @return
     */
    <O> O get(Class<O> responseType, String uri);

    /**
     * makes a get request to the URI along with the parameters and decodes the json response into
     * an object of type responseType.
     * @param responseType
     * @param uri
     * @param params
     * @param <O>
     * @return
     */
    <O> O get(Class<O> responseType, String uri, Map<String, String> params);

    /**
     * makes a Put request and adds the object o to the request then ecodes the json response into
     * an object of type responseType.
     *
     * @param responseType
     * @param uri
     * @param o
     * @param <O>
     * @param <I>
     * @return
     */
    <O, I> O put(Class<O> responseType, String uri, I o);

    /**
     * makes a Post  request and adds the object o to the request then ecodes the json response into
     * an object of type responseType.
     * @param responseType
     * @param uri
     * @param jsonObj
     * @param <I>
     * @param <O>
     * @return
     */
    <I,O> O post(Class<O> responseType, String uri, I jsonObj);

    /**
     * makes a post request to the URI and decodes the json response into
     * an object of type responseType.
     * @param responseType
     * @param uri
     * @param <O>
     * @return
     */
    <O> O post(Class<O> responseType, String uri);

    /**
     * makes a http Delete call to the uri
     * @param uri
     * @return
     */
    boolean delete(String uri);

    /**
     * reestablish the session to the server
     */
    void reconnect();

    /**
     * Makes a clone of the connection.
     *
     * @return
     */
    RestConnection clone();
}
