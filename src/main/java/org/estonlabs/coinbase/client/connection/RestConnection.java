package org.estonlabs.coinbase.client.connection;

import java.util.Map;

/**
 * Rest connection for making calls to the server.
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
