package org.estonlabs.coinbase.client.connection;

/**
 * An end point to the server.
 * If coinbase provide a testing endpoint then this class will be more useful
 */
public enum EndPoint {
    V2("https://api.coinbase.com","/v2");

    private final String endPoint;
    private final String prefix;

    EndPoint(final String endPoint, final String prefix){
        this.endPoint=endPoint;
        this.prefix=prefix;
    }

    public String getName() {
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
