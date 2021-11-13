package org.estonlabs.coinbase.client;

import javax.ws.rs.client.Client;

public interface RestfulConnection {
    void setLogJsonMessages(boolean b);

    boolean isSandbox();

    <T> T get(Class<T> responseType, String path);

    <T> T get(Class<T> responseType, String path, String parameter);

    <T, I> T put(Class<T> responseType, String path,  I jsonObj);

    <T, I> T put(Class<T> responseType, String path,  String parameter, I jsonObj);
}
