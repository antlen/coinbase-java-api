package org.estonlabs.coinbase.client.connection.auth;

import org.estonlabs.coinbase.client.connection.EndPoint;
import java.net.URI;
import java.util.Map;

/**
 * An abstraction of an endpoint that can generate the authorization headers for the endpoint.
 */
public interface SecuredEndpoint {
    /**
     * Generates a Map of heads to be added into the request.
     * @param uri
     * @param method
     * @param body
     * @return
     */
    Map<String, String> generateHeaders(URI uri, String method, String body);

    /**
     * Gets the endpoint of the server.
     *
     * @return
     */
    EndPoint getEndpoint();
}
