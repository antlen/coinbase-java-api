package org.estonlabs.coinbase.client.auth;

import org.apache.commons.codec.binary.Hex;
import org.estonlabs.coinbase.client.CbApiException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.core.MultivaluedMap;
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

    private String generateSign(String timestamp, String path, String method, String body) {
        try {
            String sign = timestamp + method.toUpperCase() + path + (body == null ? "" : body);
            Mac mac = Mac.getInstance(AuthInfo.ALGO);
            mac.init(keySpec);
            return Hex.encodeHexString(mac.doFinal(sign.getBytes()));
        }
        catch (NoSuchAlgorithmException | InvalidKeyException e){
            throw new CbApiException(e.getMessage(), e);
        }
    }

    @Override
    public void addAuthHeaders(MultivaluedMap<String, Object> headers, String path, String method, String body) {
        String timestamp = Long.toString(Instant.now().getEpochSecond());
        headers.add("accept", "application/json");
        headers.add("content-type", "application/json");
        headers.add("CB-ACCESS-KEY", apiKey);
        headers.add("CB-ACCESS-TIMESTAMP", timestamp);
        headers.add("CB-VERSION", API_DATE);
        headers.add("CB-ACCESS-SIGN", generateSign(timestamp, path, method, body));
    }
}