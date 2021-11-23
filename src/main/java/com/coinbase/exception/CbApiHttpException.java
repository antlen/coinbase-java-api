package com.coinbase.exception;

import com.coinbase.domain.general.CbMessage;
import com.coinbase.domain.general.response.CbResponse;

import java.util.List;

/**
 * An exception produced from a Http response.
 */
public class CbApiHttpException extends CbApiException{
    private final CbResponse<?> response;

    public CbApiHttpException(CbResponse<?> response) {
        super(buildMessage(response));
        this.response = response;
    }

    private static String buildMessage(CbResponse<?> response){
        StringBuilder b = new StringBuilder();
        print( response.getErrors(), b, "ERRORS");
        print( response.getWarnings(), b, "WARNS");
        return b.toString();
    }

    private static void print(List<CbMessage> messages, StringBuilder b, String type) {
        if(messages== null || messages.isEmpty()) return;

        b.append(type).append(": [");
        int i = 1;
        for(CbMessage er : messages){
            b.append(i++).append(") ");
            b.append(er.getMessage()).append(" ");
        }
        b.append("] ");
    }

    public CbResponse<?> getResponse() {
        return response;
    }
}
