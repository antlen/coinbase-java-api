package org.estonlabs.coinbase.client;

public enum EndPoint {
    V2("https://api.coinbase.com","/v2"),
    PRO_SANDBOX("https://api-public.sandbox.pro.coinbase.com"),
    PRO(null);

    String endPoint;
    String prefix;
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

    public void setEndPoint(final String endPoint) {
        this.endPoint = endPoint;
    }

    public String adaptPath(final String path, final String parameter){
        String adapted = path;
        if(prefix!=null){
            adapted = prefix+adapted;
        }
        if(parameter!=null) {
            adapted = adapted+"/"+parameter;
        }
        return adapted;
    }
}
