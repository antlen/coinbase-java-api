package org.estonlabs.coinbase.client;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicBoolean;

public class CbRestApiConnection {
    public static final String ALGO = "HmacSHA256";
    private static final String API_DATE = "2021-11-05";

    private final SecretKeySpec keySpec;
    private final String apiKey;
    private final Client client;
    private final LoggingClientResponseFilter responseLogger = new LoggingClientResponseFilter();

    CbRestApiConnection(String apiKey, byte[] secretKey){
        this.apiKey = apiKey;
        keySpec =  new SecretKeySpec(secretKey, ALGO);
        //eagerly test the sign generation
        try {
            generateSign(Instant.now().getEpochSecond(),"","");
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }finally{
            for(int i=0; i<secretKey.length; i++){
                secretKey[i]=(byte)0;
            }
        }
        client = ClientBuilder.newClient();
    }

    public void close(){
        client.close();
    }

    public void setLogResponsesEnabled(boolean b){
        responseLogger.setEnabled(b);
    }

    public Invocation.Builder request(String path){
        Invocation.Builder b = client.target("https://api.coinbase.com")
                .path(path).request(MediaType.APPLICATION_JSON);
        addHeaders(b, path, "GET");
        return b;
    }

    private void addHeaders(Invocation.Builder ib, String path, String method) {
        Long timestamp = Instant.now().getEpochSecond();

        ib.header("CB-ACCESS-KEY", apiKey);
        ib.header("CB-ACCESS-TIMESTAMP", timestamp.toString());
        ib.header("CB-VERSION", API_DATE);
        try{
            ib.header("CB-ACCESS-SIGN", generateSign(timestamp,path, method));
        }catch (Exception e){
            //should never get here as already tested the sign generation in the constructor.
            e.printStackTrace();
        }
    }

    private String generateSign( Long timestamp,String path, String method) throws NoSuchAlgorithmException, InvalidKeyException{
        String sign = timestamp.toString() + method + path ;
        Mac mac = Mac.getInstance(ALGO);
        mac.init(keySpec);
        return Hex.encodeHexString(mac.doFinal(sign.getBytes()));
    }

    private class LoggingClientResponseFilter implements ClientResponseFilter {
        private AtomicBoolean enabled = new AtomicBoolean(false);
        private AtomicBoolean initialized = new AtomicBoolean(false);

        public synchronized void setEnabled(boolean b){
            if(b && !initialized.get()){
                CbRestApiConnection.this.client.register(this);
            }
            enabled.set(b);
        }

        @Override
        public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {
            if(!enabled.get()) return;

            final StringBuilder b = new StringBuilder();
            if (responseContext.hasEntity()) {
                InputStream stream = responseContext.getEntityStream();
                if (!stream.markSupported()) {
                    stream = new BufferedInputStream(stream);
                }
                stream.mark(Integer.MAX_VALUE);
                b.append(new String(stream.readAllBytes(), StandardCharsets.UTF_8));

                b.append('\n');
                stream.reset();
                responseContext.setEntityStream(stream);
                System.out.println(b.toString());
            }
        }
    }
}
