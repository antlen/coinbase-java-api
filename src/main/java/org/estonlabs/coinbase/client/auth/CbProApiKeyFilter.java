package org.estonlabs.coinbase.client.auth;

import org.estonlabs.coinbase.client.CbApiException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.core.MultivaluedMap;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Base64;

public class CbProApiKeyFilter implements AuthFilter {
    private static final String API_DATE = "2021-11-05";

    private final String apiKey;
    private final String passphrase;
    private final String secretKey;

    public CbProApiKeyFilter(String apiKey, String secretKey, String passphrase) {
        this.apiKey = apiKey;
        this.passphrase = passphrase;
        this.secretKey = secretKey;
    }

    private String generateSign(String timestamp, String requestPath, String method) {
        String body = "";
        try {
            String prehash = timestamp + method.toUpperCase() + requestPath + body;
            byte[] secretDecoded = Base64.getDecoder().decode(secretKey);
            Mac hmacSHA256 = Mac.getInstance("HmacSHA256");
            SecretKeySpec keyspec = new SecretKeySpec(secretDecoded, hmacSHA256.getAlgorithm());
            Mac sha256 = (Mac) hmacSHA256.clone();
            sha256.init(keyspec);
            return Base64.getEncoder().encodeToString(sha256.doFinal(prehash.getBytes()));
        } catch (CloneNotSupportedException | NoSuchAlgorithmException | InvalidKeyException e) {
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
        headers.add("CB-ACCESS-PASSPHRASE", passphrase);
        headers.add("CB-ACCESS-SIGN", generateSign(timestamp,path, method));
    }
}