package org.estonlabs.coinbase.client;

public class CbApiException extends RuntimeException{
    public CbApiException(String message, Throwable cause) {
        super(message, cause);
    }
    public CbApiException(String message){
        super(message);
    }
}
