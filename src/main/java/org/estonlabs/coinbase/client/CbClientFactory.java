package org.estonlabs.coinbase.client;

import org.estonlabs.coinbase.client.auth.CbApiV2KeyFilter;
import org.estonlabs.coinbase.client.auth.AuthInfo;
import org.estonlabs.coinbase.client.auth.CbProApiKeyFilter;

import javax.crypto.spec.SecretKeySpec;

public class CbClientFactory {

    public static CbClient newCbRestApiClient(String apiKey, byte[] secretKey) {
        return newCbRestApiClient(apiKey, secretKey, false, null);
    }

    public static CbClient newCbRestApiClient(String apiKey, byte[] secretKey, boolean useSandbox, String passphrase) {
        RestfulConnection c = null;
        if(useSandbox){
            CbProApiKeyFilter f = new CbProApiKeyFilter(apiKey, new String(secretKey), passphrase);
            c = new CbRestApiConnection(f,EndPoint.PRO_SANDBOX , true);
        }else{
            CbApiV2KeyFilter f = new CbApiV2KeyFilter(apiKey, new SecretKeySpec(secretKey, AuthInfo.ALGO));
            c = new CbRestApiConnection(f, EndPoint.V2, false);
        }
        return new CbRestApiClientImpl(c);
    }
}
