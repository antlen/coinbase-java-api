package org.estonlabs.coinbase.client;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class LoggingClientResponseFilter implements ClientResponseFilter {
    private boolean enabled = false;

    public void setEnabled(boolean b){
        enabled=b;
    }

    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {
        if(!enabled) return;

        if (responseContext.hasEntity()) {
            InputStream stream = responseContext.getEntityStream();
            if (!stream.markSupported()) {
                stream = new BufferedInputStream(stream);
            }
            final StringBuilder b = new StringBuilder();
            stream.mark(Integer.MAX_VALUE);
            b.append(new String(stream.readAllBytes(), StandardCharsets.UTF_8));

            b.append('\n');
            stream.reset();
            System.out.println(b.toString());
            responseContext.setEntityStream(stream);
        }
    }
}