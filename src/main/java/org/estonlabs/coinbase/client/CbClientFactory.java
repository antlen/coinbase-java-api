package org.estonlabs.coinbase.client;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class CbClientFactory {

    public static CbClient newCbRestApiClientI(String apiKey, byte[] secretKey) {
        return new CbRestApiClientImpl(new CbRestApiConnection(apiKey, secretKey));
    }
}
