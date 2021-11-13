package org.estonlabs.coinbase.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.estonlabs.coinbase.client.auth.AuthFilter;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.io.*;

public class CbRestApiConnection implements RestfulConnection {

    private final EndPoint endPoint;
    private volatile LoggingClientResponseFilter responseLogger;
    private final Client client;
    private final boolean isSandbox;

    CbRestApiConnection(AuthFilter auth, EndPoint endPoint, boolean isSandbox){
        this.endPoint=endPoint;
        this.client = ClientBuilder.newClient();;
        client.register(new ClientRequestAuthFilter(auth));
        this.isSandbox=isSandbox;
    }

    @Override
    public <T> T get(Class<T> responseType, String path, String parameter){
        Invocation.Builder request = client.target(endPoint.getEndPoint())
                .path(endPoint.adaptPath(path, parameter))
                .request(MediaType.APPLICATION_JSON);
        return request.get(responseType);
    }

    @Override
    public <T, I> T put(Class<T> responseType, String path, String parameter, I jsonObj) {
        Entity<Object> entity = Entity.json(jsonObj);
        WebTarget target = client.target(endPoint.getEndPoint());
        Invocation.Builder request = target
                .path(endPoint.adaptPath(path, parameter))
                .request(MediaType.APPLICATION_JSON);

        return request.put(entity, responseType);
    }

    @Override
    public <T, I> T put(Class<T> responseType, String path, I jsonObj) {
        return put(responseType, path, null, jsonObj);
    }

/**
    public <T> T post(Class<T> responseType, String path, Object jsonObj) {
        Entity<Object> entity = Entity.json(jsonObj);
        WebTarget target = getClient().target(endPoint.getEndPoint());
        Invocation.Builder request = target
                .path(endPoint.adaptPath(path, null))
                .request(MediaType.APPLICATION_JSON);

        return request.post(entity, responseType);
    }
**/

    public synchronized void setLogJsonMessages(boolean b){
        if(b && responseLogger == null){
            responseLogger = new LoggingClientResponseFilter();
            client.register(responseLogger);
        }
        responseLogger.setEnabled(b);
    }

    public boolean isSandbox() {
        return isSandbox;
    }

    public <T> T get(Class<T> responseType, String path){
        return get(responseType, path, null);
    }

    public void close(){
        client.close();
    }

    private class ClientRequestAuthFilter implements ClientRequestFilter{
        private final AuthFilter authFilter;

        public ClientRequestAuthFilter(AuthFilter authFilter) {
            this.authFilter = authFilter;
        }

        @Override
        public void filter(ClientRequestContext ctx){
            try {
            MultivaluedMap<String, Object> headers = ctx.getHeaders();
            String body = null;
                if(CbRestApiConnection.this.responseLogger.isEnabled()){
                    System.out.println(ctx.getUri().toString());
                }
                if(ctx.hasEntity()){
                    ObjectMapper om = new ObjectMapper();
                    body = om.writeValueAsString(ctx.getEntity());
                    if(CbRestApiConnection.this.responseLogger.isEnabled()){
                        System.out.println(body);
                    }
                }
                authFilter.addAuthHeaders(headers, ctx.getUri().getPath(), ctx.getMethod(), body);
            } catch (JsonProcessingException e) {
                throw new CbApiException(e.getMessage(), e);
            }
        }
    }
}
