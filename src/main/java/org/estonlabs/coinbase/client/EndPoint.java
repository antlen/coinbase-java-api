package org.estonlabs.coinbase.client;


public enum EndPoint {
    V2("https://api.coinbase.com","/v2"),
    PRO_SANDBOX("https://api-public.sandbox.pro.coinbase.com"),
    PRO(null);

    private final String endPoint;
    private final String prefix;
    EndPoint(final String endPoint, final String prefix){
        this.endPoint=endPoint;
        this.prefix=prefix;
    }
    EndPoint(String endPoint){
        this(endPoint, null);
    }

    public String getEndPoint() {
        return endPoint;
    }

    public String adaptPath(final String path){
        String adapted = path;
        if(prefix!=null && !adapted.startsWith(prefix)){
            adapted = prefix+adapted;
        }
        return adapted;
    }
}
