package org.estonlabs.coinbase.client.auth;

import org.apache.commons.codec.binary.Hex;
import org.estonlabs.coinbase.CbApiException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.core.MultivaluedMap;
import java.net.URI;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;

public class CbApiV2KeyFilter implements AuthFilter {
    private static final String API_DATE = "2021-11-05";

    private final String apiKey;
    private final SecretKeySpec keySpec;

    public CbApiV2KeyFilter(String apiKey, SecretKeySpec keySpec) {
        this.apiKey = apiKey;
        this.keySpec = keySpec;
    }

    private String generateSign(String timestamp, URI uri, String method, String body) {
        try {
            String sign = timestamp + method.toUpperCase() + getURI(uri) + (body == null ? "" : body);
            Mac mac = Mac.getInstance(AuthInfo.ALGO);
            mac.init(keySpec);
            return Hex.encodeHexString(mac.doFinal(sign.getBytes()));
        }
        catch (NoSuchAlgorithmException | InvalidKeyException e){
            throw new CbApiException(e.getMessage(), e);
        }
    }


    private String getURI(URI uri){
        /*
         * The sign needs the full path, including the query strings but without the host.
         * uri.getPath() does not include the host but it also does not include the query string.
         * Uri.toString() includes the query string but also includes host..
         * So this logic removes everything before the path from the toString.
         * e.g.
         * uri.toString() = "https://api.coinbase.com/v2/accounts?starting_after=1961d899-xxxx-50d4-9771-xxxxx"
         * uri.getPath() = "/v2/accounts"
         * uriStr.substring(uriStr.indexOf(uri.getPath())) = "/v2/accounts?starting_after=1961d899-xxxx-50d4-9771-xxxxx"
         */
        String uriStr = uri.toString();
        return uriStr.substring(uriStr.indexOf(uri.getPath()));
    }

    @Override
    public void addAuthHeaders(MultivaluedMap<String, Object> headers, URI path, String method, String body) {
        String timestamp = Long.toString(Instant.now().getEpochSecond());
        headers.add("accept", "application/json");
        headers.add("content-type", "application/json");
        headers.add("CB-ACCESS-KEY", apiKey);
        headers.add("CB-ACCESS-TIMESTAMP", timestamp);
        headers.add("CB-VERSION", API_DATE);
        headers.add("CB-ACCESS-SIGN", generateSign(timestamp, path, method, body));
    }
}