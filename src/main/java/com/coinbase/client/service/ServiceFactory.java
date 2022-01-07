package com.coinbase.client.service;

import com.coinbase.client.security.RequestAuthenticationFilter;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.ws.rs.client.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * The MIT License (MIT)
 *
 *	Copyright (c) 2021 antlen
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
 * The main service factory that will generate the Client and WebTarget then generate the CoinbaseAPIv2Service for
 * handling the rest api requests.
 *
 * @author antlen
 */
public class ServiceFactory {

    private final RequestAuthenticationFilter filter;
    private final LoggingClientResponseFilter loggingFilter = new LoggingClientResponseFilter();

    public ServiceFactory(RequestAuthenticationFilter filter) {
        this.filter = filter;
    }

    public CoinbaseAPIv2Service buildRestService(){
        return buildWebTarget().proxy(CoinbaseAPIv2Service.class);
    }

    private ResteasyWebTarget buildWebTarget() {
        Client client = ClientBuilder.newClient();
        client.register(loggingFilter);
        client.register(filter);
        WebTarget target = client.target("https://api.coinbase.com");
        return (ResteasyWebTarget)target;
    }

    private class LoggingClientResponseFilter implements ClientResponseFilter {
        @Override
        public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {
            if(!filter.isLoggingEnabled()) return;

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
                System.out.println(Thread.currentThread().getName() +" : " +b);
                responseContext.setEntityStream(stream);
            }
        }
    }
}
