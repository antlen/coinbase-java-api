package org.estonlabs.coinbase.client.auth;

import javax.ws.rs.core.MultivaluedMap;
import java.net.URI;

public interface AuthFilter {
    void addAuthHeaders(MultivaluedMap<String, Object> headers, URI uri, String method, String body);
}
