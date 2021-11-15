package org.estonlabs.coinbase.client;

import javax.ws.rs.client.Client;
import java.util.Map;

public interface RestfulConnection {
    void setLogJsonMessages(boolean b);

    boolean isSandbox();

    <T> T get(Class<T> responseType, String path);

    public <T> T get(Class<T> responseType, String path, Map<String, String> params);

    <T, I> T put(Class<T> responseType, String path,  I jsonObj);

     boolean delete(String path);

     void reconnect();
}
