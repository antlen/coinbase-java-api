package org.estonlabs.coinbase.domain.general;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "text",
        "style",
        "id"
})
@Generated("jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CbOption {

    @JsonProperty("text")
    private String text;
    @JsonProperty("style")
    private String style;
    @JsonProperty("id")
    private String id;

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    @JsonProperty("style")
    public String getStyle() {
        return style;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "CbOption{" +
                "text='" + text + '\'' +
                ", style='" + style + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
