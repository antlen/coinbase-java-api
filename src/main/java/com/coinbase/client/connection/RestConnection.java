package com.coinbase.client.connection;

import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.concurrent.Future;

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
     * makes a get request to the URI along with the parameters and decodes the json response into
     * an object of type responseType.
     * @param responseType
     * @param uri
     * @param params - can be null
     * @param <O>
     * @return
     */
    <O> Future<O> get(Class<O> responseType, String uri, Map<String, String> params);

    <O> Future<O> get(InvocationCallback<O> callback, String uri, Map<String, String> params);

    /**
     * makes a Put request and adds the object o to the request then ecodes the json response into
     * an object of type responseType returned by a Future
     * @param responseType
     * @param uri
     * @param o - can be null
     * @param <O>
     * @param <I>
     * @return
     */
    <O, I> Future<O> put(Class<O> responseType, String uri, I o);

    /**
     * makes a Put request and adds the object o to the request then ecodes the json response into
     * an object of type responseType. The response can be obtained from the Future or the callback will be called
     * with the result.
     * @param callback
     * @param uri
     * @param o - can be null
     * @param <O>
     * @param <I>
     * @return
     */
    <O, I> Future<O> put(InvocationCallback<O> callback, String uri, I o);

    /**
     * makes a Post  request and adds the object o to the request then ecodes the json response into
     * an object of type responseType.
     * @param responseType
     * @param uri
     * @param jsonObj
     * @param <I> a object to encore - can be null.
     * @param <O>
     * @return
     */
    <I,O> Future<O> post(Class<O> responseType, String uri, I jsonObj);

    <I,O> Future<O> post(InvocationCallback<O> callback, String uri, I jsonObj);

    /**
     * makes a post request to the URI and decodes the json response into
     * an object of type responseType.
     * @param responseType
     * @param uri
     * @param <O>
     * @return
     */
  //  <O> Future<O> post(Class<O> responseType, String uri);

 //   <O> Future<O> post(InvocationCallback<O> callback, String uri);

    /**
     * makes a http Delete call to the uri
     * @param uri
     * @return
     */
    Future<Response> delete(String uri);

    Future<Response> delete(InvocationCallback<Response> callback, String uri);

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
