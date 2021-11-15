package org.estonlabs.coinbase.domain;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "message",
        "url"
})
@Generated("jsonschema2pojo")
public class CbWarning {

    @JsonProperty("id")
    private String id;
    @JsonProperty("message")
    private String message;
    @JsonProperty("url")
    private String url;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "CbWarning{" +
                "id='" + id + '\'' +
                ", message='" + message + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}