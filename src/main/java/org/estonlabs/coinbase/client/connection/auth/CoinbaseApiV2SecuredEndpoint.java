package org.estonlabs.coinbase.client.connection.auth;

import org.apache.commons.codec.binary.Hex;
import org.estonlabs.coinbase.client.connection.EndPoint;
import org.estonlabs.coinbase.exception.CbApiException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URI;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated headers for "CB-ACCESS-KEY", "CB-ACCESS-TIMESTAMP", "CB-VERSION", "CB-ACCESS-SIGN".
 */
public class CoinbaseApiV2SecuredEndpoint implements SecuredEndpoint {
    public static final String ALGO = "HmacSHA256";
    private static final String API_DATE = "2021-11-05";

    private final String apiKey;
    private final SecretKeySpec keySpec;

    public CoinbaseApiV2SecuredEndpoint(String apiKey, byte[] secretKey) {
        this.apiKey = apiKey;
        this.keySpec = new SecretKeySpec(secretKey, ALGO);
    }

    private String generateSign(String timestamp, URI uri, String method, String body) {
        try {
            String sign = timestamp + method.toUpperCase() + getURI(uri) + (body == null ? "" : body);
            Mac mac = Mac.getInstance(ALGO);
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
    public Map<String, String> generateHeaders(URI path, String method, String body) {
        Map<String, String> headers = new HashMap<>();
        String timestamp = Long.toString(Instant.now().getEpochSecond());
        headers.put("accept", "application/json");
        headers.put("content-type", "application/json");
        headers.put("CB-ACCESS-KEY", apiKey);
        headers.put("CB-ACCESS-TIMESTAMP", timestamp);
        headers.put("CB-VERSION", API_DATE);
        headers.put("CB-ACCESS-SIGN", generateSign(timestamp, path, method, body));
        return headers;
    }

    @Override
    public EndPoint getEndpoint() {
        return EndPoint.V2;
    }
}
