package com.coinbase.domain.general.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.coinbase.domain.general.CbMessage;

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
 * The wrapper for all responses from the server.
 * @param <T>
 *
 * @author antlen
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CbResponse<T> implements ResponseBody<T>{

    private T data;
    @JsonProperty("warnings")
    private List<CbMessage> warnings;
    @JsonProperty("errors")
    private List<CbMessage> errors;

    public CbResponse(){
    }

    public CbResponse(T data){
        this.data = data;
    }

    /**
     * The underlying json object
     * @return
     */
    @Override
    public T getData() {
        return data;
    }

    /**
     * A list of errors provided by the server
     * @return
     */
    @JsonProperty("errors")
    public List<CbMessage> getErrors() {
        return errors;
    }

    /**
     * A list of warnings provided by the server
     * @return
     */
    @JsonProperty("warnings")
    public List<CbMessage> getWarnings() {
        return warnings;
    }
}
