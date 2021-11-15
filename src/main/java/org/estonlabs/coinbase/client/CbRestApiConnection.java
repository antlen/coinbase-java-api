package org.estonlabs.coinbase.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.estonlabs.coinbase.CbApiException;
import org.estonlabs.coinbase.client.auth.AuthFilter;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Map;

public class CbRestApiConnection implements RestfulConnection {

    private final EndPoint endPoint;
    private volatile LoggingClientResponseFilter responseLogger;
    private Client client;
    private final boolean isSandbox;
    private final ClientRequestAuthFilter authFilter;

    CbRestApiConnection(AuthFilter auth, EndPoint endPoint, boolean isSandbox){
        this.endPoint=endPoint;
        this.authFilter = new ClientRequestAuthFilter(auth);
        this.isSandbox=isSandbox;
        reconnect();
    }

    @Override
    public void reconnect() {
        if(client!= null){
            try{
                client.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        boolean log = responseLogger==null?false: responseLogger.isEnabled();
        responseLogger = null;
        this.client = ClientBuilder.newClient();;
        client.register(authFilter);
        setLogJsonMessages(log);
    }

    @Override
    public <T> T get(Class<T> responseType, String path) {
        return get(responseType,path,null);
    }

    @Override
    public <T> T get(Class<T> responseType, String path, Map<String, String> params){
        WebTarget target = client.target(endPoint.getEndPoint());

        if(params !=null){
            for(Map.Entry<String, String> e : params.entrySet()){
                target = target.queryParam(e.getKey(), e.getValue());
            }
        }
        target = target.path(endPoint.adaptPath(path));
        return target.request(MediaType.APPLICATION_JSON).get(responseType);
    }


    @Override
    public <T, I> T put(Class<T> responseType, String path, I jsonObj) {
        Entity<Object> entity = Entity.json(jsonObj);
        WebTarget target = client.target(endPoint.getEndPoint());
        Invocation.Builder request = target
                .path(endPoint.adaptPath(path))
                .request(MediaType.APPLICATION_JSON);

        return request.put(entity, responseType);
    }

    @Override
    public boolean delete(String path){
        Invocation.Builder request = client.target(endPoint.getEndPoint())
                .path(endPoint.adaptPath(path))
                .request(MediaType.APPLICATION_JSON);
        return request.delete().getStatusInfo().toEnum() == Response.Status.NO_CONTENT;
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
        if(responseLogger !=null){
            responseLogger.setEnabled(b);
        }
    }

    public boolean isSandbox() {
        return isSandbox;
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
                authFilter.addAuthHeaders(headers, ctx.getUri(), ctx.getMethod(), body);
            } catch (JsonProcessingException e) {
                throw new CbApiException(e.getMessage(), e);
            }
        }

    }
}
