package com.coinbase.client.connection;

import com.coinbase.domain.general.response.CbResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.coinbase.exception.CbApiException;
import com.coinbase.client.connection.auth.SecuredEndpoint;
import com.coinbase.exception.CbApiHttpException;

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
import java.util.concurrent.Future;
import java.util.function.Supplier;

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
 * Implementation of a RestConnection using javax.ws.rs.client.Client
 *
 * @author antlen
 */
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

    private AsyncInvoker buildAsync(String uri, Map<String, String> params) {
        WebTarget target = client.target(endPoint.getEndpoint().getHost())
                .path(endPoint.getEndpoint().adaptUri(uri));
        if (params != null) {
            for (Map.Entry<String, String> e : params.entrySet()) {
                target = target.queryParam(e.getKey(), e.getValue());
            }
        }
        return target.request(MediaType.APPLICATION_JSON).async();
    }

    public <T> Future<T> get(Class<T> responseType, String path, Map<String, String> params){
        return run(() -> buildAsync(path, params).get(responseType));
    }

    @Override
    public <T> Future<T> get(InvocationCallback<T> callback, String path, Map<String, String> params){
        return run(() -> buildAsync(path, params).get(callback));
    }

    @Override
    public <O, I> Future<O> put(InvocationCallback<O> callback, String uri, I o) {
        return run(() -> buildAsync(uri, null).put(Entity.json(o), callback));
    }

    @Override
    public <O, I> Future<O> put(Class<O> responseType, String uri, I o) {
        return run(() -> buildAsync(uri, null).put(Entity.json(o), responseType));
    }

    @Override
    public <I, O> Future<O> post(InvocationCallback<O> callback, String uri, I o) {
        return run(() -> {
            Entity<I> json = o==null?null:Entity.json(o);
            return buildAsync(uri, null).put(json, callback);
        });
    }

    @Override
    public <I,O> Future<O> post(Class<O> responseType, String uri, I o) {
        return run(() -> {
            Entity<I> json = o==null?null:Entity.json(o);
            return buildAsync(uri, null).put(json, responseType);
        });
    }

    @Override
    public Future<Response> delete(InvocationCallback<Response> callback, String uri) {
        return run(() -> buildAsync(uri, null).delete(callback));
    }

    @Override
    public Future<Response> delete(String uri){
        return run(() -> buildAsync(uri, null).delete());
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

    public <T> Future<T> run(Supplier<Future<T>> t){
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
