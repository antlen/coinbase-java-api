package org.estonlabs.coinbase;

public class CbApiException extends RuntimeException{
    public CbApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
