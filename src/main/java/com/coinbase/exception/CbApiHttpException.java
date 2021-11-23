package com.coinbase.exception;

import com.coinbase.domain.general.CbMessage;
import com.coinbase.domain.general.response.CbResponse;

import java.util.List;

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
 * An exception produced from a Http response.
 *
 * @author antlen
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

    /**
     * Get the response that caused this exception.
     * @return
     */
    public CbResponse<?> getResponse() {
        return response;
    }
}
