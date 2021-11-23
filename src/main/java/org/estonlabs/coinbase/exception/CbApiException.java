package org.estonlabs.coinbase.exception;

/**
 * General exception for the framework.
 */
public class CbApiException extends RuntimeException{
    public CbApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public CbApiException(String message) {
        super(message);
    }
}
