package com.coinbase.client.security;

import com.coinbase.exception.CbApiException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
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
 *  ClientRequest filter that stamps header on requests.
 *
 * @author antlen
 */
public class RequestAuthenticationFilter implements ClientRequestFilter {
    private final HeaderGenerator headerGenerator;
    private boolean loggingEnabled = false;

    public RequestAuthenticationFilter(HeaderGenerator headerGenerator) {
        this.headerGenerator = headerGenerator;
    }

    public void setLoggingEnabled(boolean loggingEnabled) {
        this.loggingEnabled = loggingEnabled;
    }

    public boolean isLoggingEnabled() {
        return loggingEnabled;
    }

    @Override
    public void filter(ClientRequestContext ctx){
        try {
            String body = null;

            if(loggingEnabled){
                System.out.println(Thread.currentThread().getName() +" : " + ctx.getUri().toString());
            }

            if(ctx.hasEntity()){
                ObjectMapper om = new ObjectMapper();
                body = om.writeValueAsString(ctx.getEntity());
                if(loggingEnabled){
                    System.out.println(body);
                }
            }
            MultivaluedMap<String, Object> headers = ctx.getHeaders();
            for(Map.Entry<String,String> e : headerGenerator.generateHeaders(ctx.getUri(), ctx.getMethod(), body).entrySet()){
                headers.add(e.getKey(), e.getValue());
            }
        } catch (JsonProcessingException e) {
            throw new CbApiException(e.getMessage(), e);
        }
    }
}
