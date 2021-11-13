package org.estonlabs.coinbase.client.auth;

import javax.ws.rs.core.MultivaluedMap;

public interface AuthFilter {
    void addAuthHeaders(MultivaluedMap<String, Object> headers, String path, String method, String body);
}
