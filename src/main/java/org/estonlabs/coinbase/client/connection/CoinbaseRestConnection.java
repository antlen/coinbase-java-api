package org.estonlabs.coinbase.client.connection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.estonlabs.coinbase.domain.general.response.CbResponse;
import org.estonlabs.coinbase.exception.CbApiException;
import org.estonlabs.coinbase.client.connection.auth.SecuredEndpoint;
import org.estonlabs.coinbase.exception.CbApiHttpException;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.function.Supplier;

public class CoinbaseRestConnection implements RestConnection {

    private final SecuredEndpoint endPoint;
    private volatile LoggingClientResponseFilter responseLogger;
    private Client client;
    private boolean loggingEnabled = false;

    public CoinbaseRestConnection(SecuredEndpoint endPoint){
        this.endPoint=endPoint;
        reconnect();
    }

    @Override
    public RestConnection clone() {
        return new CoinbaseRestConnection(endPoint);
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
        responseLogger = null;
        this.client = ClientBuilder.newClient();
        client.register(new ClientRequestAuthFilter(endPoint));
        setLogJsonMessages(loggingEnabled);
    }

    private WebTarget createWebTarget(String path) {
        return client.target(endPoint.getEndpoint().getName())
                .path(endPoint.getEndpoint().adaptUri(path));
    }

    @Override
    public <T> T get(Class<T> responseType, String path) {
        return get(responseType,path,null);
    }

    @Override
    public <T> T get(Class<T> responseType, String path, Map<String, String> params){
        return run(() -> {
            WebTarget target = createWebTarget(path);
            if (params != null) {
                for (Map.Entry<String, String> e : params.entrySet()) {
                    target = target.queryParam(e.getKey(), e.getValue());
                }
            }
            return target.request(MediaType.APPLICATION_JSON).get(responseType);
        });
    }

    @Override
    public <O, I> O put(Class<O> responseType, String path, I jsonObj) {
        return run(() -> {
            Invocation.Builder request = createWebTarget(path)
                    .request(MediaType.APPLICATION_JSON);

            return request.put(Entity.json(jsonObj), responseType);
        });
    }

    @Override
    public boolean delete(String path){
        return run(() -> {
            Invocation.Builder request = createWebTarget(path)
                    .request(MediaType.APPLICATION_JSON);
            return request.delete().getStatusInfo().toEnum() == Response.Status.NO_CONTENT;
        });
    }

    @Override
    public <O> O post(Class<O> responseType, String path) {
        return run(() -> {
            Invocation.Builder request = createWebTarget(path)
                    .request(MediaType.APPLICATION_JSON);

            return request.post(null, responseType);
        });
    }

    public <I,O> O post(Class<O> responseType, String path, I jsonObj) {
       return run(() -> {
           Entity<I> entity = Entity.json(jsonObj);
           Invocation.Builder request = createWebTarget(path)
                   .request(MediaType.APPLICATION_JSON);

           return request.post(entity, responseType);
       });
    }

    public synchronized void setLogJsonMessages(boolean b){
        if(b && responseLogger == null){
            responseLogger = new LoggingClientResponseFilter();
            client.register(responseLogger);
        }
       loggingEnabled = b;
    }

    public void close(){
        client.close();
    }

    public <T> T run(Supplier<T> t){
        try {
            return t.get();
        }catch (ClientErrorException e){
            if(e.getResponse().hasEntity()){
                throw new CbApiHttpException(e.getResponse().readEntity(CbResponse.class));
            }else{
                throw new CbApiException("Error interacting with the server", e);
            }
        }
    }

    private class ClientRequestAuthFilter implements ClientRequestFilter{
        private final SecuredEndpoint securedEndpoint;

        public ClientRequestAuthFilter(SecuredEndpoint securedEndpoint) {
            this.securedEndpoint = securedEndpoint;
        }

        @Override
        public void filter(ClientRequestContext ctx){
            try {
                String body = null;

                if(loggingEnabled){
                    System.out.println(ctx.getUri().toString());
                }

                if(ctx.hasEntity()){
                    ObjectMapper om = new ObjectMapper();
                    body = om.writeValueAsString(ctx.getEntity());
                    if(loggingEnabled){
                        System.out.println(body);
                    }
                }
                MultivaluedMap<String, Object> headers = ctx.getHeaders();
                for(Map.Entry<String,String> e :securedEndpoint.generateHeaders(ctx.getUri(), ctx.getMethod(), body).entrySet()){
                    headers.add(e.getKey(), e.getValue());
                }
            } catch (JsonProcessingException e) {
                throw new CbApiException(e.getMessage(), e);
            }
        }
    }

    private class LoggingClientResponseFilter implements ClientResponseFilter {
        @Override
        public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {
            if(!loggingEnabled) return;

            if (responseContext.hasEntity()) {
                InputStream stream = responseContext.getEntityStream();
                final StringBuilder b = new StringBuilder();
                if (!stream.markSupported()) {
                    stream = new BufferedInputStream(stream);
                }

                stream.mark(Integer.MAX_VALUE);
                b.append(new String(stream.readAllBytes(), StandardCharsets.UTF_8));

                b.append('\n');
                stream.reset();
                System.out.println(b.toString());
                responseContext.setEntityStream(stream);
            }
        }
    }
}
