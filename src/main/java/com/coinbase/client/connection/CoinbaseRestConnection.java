package com.coinbase.client.connection;

import com.coinbase.client.api.request.*;
import com.coinbase.domain.general.response.CbResponse;
import com.coinbase.domain.pagination.response.CbPaginatedResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.coinbase.exception.CbApiException;
import com.coinbase.client.connection.auth.SecuredEndpoint;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MultivaluedMap;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
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
 * Implementation of a RestConnection using javax.ws.rs.client.Client
 *
 * @author antlen
 */
public class CoinbaseRestConnection implements RestConnection {

    private final SecuredEndpoint endPoint;
    private volatile LoggingClientResponseFilter responseLogger;
    private Client client;
    private boolean loggingEnabled = false;
    private int pageSize;

    public CoinbaseRestConnection(SecuredEndpoint endPoint, int pageSize){
        this.endPoint=endPoint;
        this.pageSize = pageSize;
        reconnect();
    }

    @Override
    public RestConnection clone() {
        return new CoinbaseRestConnection(endPoint, pageSize);
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

    @Override
    public <I,O extends CbResponse> PostRequest<I,O> post(Class<O> c, String uri) {
        return new PostRequest<I,O>(c, buildWebTarget(client, endPoint, uri));
    }

    @Override
    public <I,O extends CbResponse> PutRequest<I,O> put(Class<O> c, String uri) {
        return new PutRequest<I,O>(c, buildWebTarget(client, endPoint, uri));
    }

    @Override
    public <O extends CbResponse> GetRequest<O> get(Class<O> c, String uri) {
        return new GetRequest<O>(c, buildWebTarget(client, endPoint, uri));
    }

    @Override
    public <O extends CbPaginatedResponse<?>> PaginatedGetRequest<O> paginatedGet(Class<O> c, String uri) {
        return new PaginatedGetRequest<O>(c, buildWebTarget(client, endPoint, uri), pageSize);
    }

    @Override
    public DeleteRequest delete(String uri) {
        return new DeleteRequest(buildWebTarget(client, endPoint, uri));
    }

    private WebTarget buildWebTarget(Client client, SecuredEndpoint endPoint, String uri) {
        return client.target(endPoint.getEndpoint().getHost())
                .path(endPoint.getEndpoint().adaptUri(uri));
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
