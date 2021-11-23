package com.coinbase.client.connection;

/**
 * The MIT License (MIT)
 *
 *	Copyright (c) 2016 antlen
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
 * An end point to the server.
 * If coinbase provide a testing endpoint then this class will be more useful
 *
 * @author antlen
 */
public enum EndPoint {
    V2("https://api.coinbase.com","/v2");

    private final String endPoint;
    private final String prefix;

    EndPoint(final String endPoint, final String prefix){
        this.endPoint=endPoint;
        this.prefix=prefix;
    }

    /**
     * The hostname for the end point
     * @return
     */
    public String getHost() {
        return endPoint;
    }

    public String adaptUri(final String uri){
        String adapted = uri;
        if(prefix!=null && !adapted.startsWith(prefix)){
            adapted = prefix+adapted;
        }
        return adapted;
    }
}
