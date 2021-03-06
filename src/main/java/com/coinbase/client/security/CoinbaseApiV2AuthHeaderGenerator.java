package com.coinbase.client.security;

import com.coinbase.exception.CbApiException;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URI;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
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
 * Generated headers for "CB-ACCESS-KEY", "CB-ACCESS-TIMESTAMP", "CB-VERSION", "CB-ACCESS-SIGN".
 *
 * @author antlen
 */
public class CoinbaseApiV2AuthHeaderGenerator implements HeaderGenerator {
    public static final String ALGO = "HmacSHA256";
    private static final String API_DATE = "2021-11-05";

    private final String apiKey;
    private final SecretKeySpec keySpec;

    public CoinbaseApiV2AuthHeaderGenerator(String apiKey, byte[] secretKey) {
        this.apiKey = apiKey;
        this.keySpec = new SecretKeySpec(secretKey, ALGO);
    }

    private String generateSign(String timestamp, URI uri, String method, String body) {
        try {
            String uri1 = getURI(uri);
            String sign = timestamp + method.toUpperCase() + uri1 + (body == null ? "" : body);
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
}
