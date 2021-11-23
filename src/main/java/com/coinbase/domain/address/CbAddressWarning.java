package com.coinbase.domain.address;

import java.util.List;
import javax.annotation.Generated;

import com.coinbase.domain.general.CbOption;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "type",
        "title",
        "details",
        "image_url",
        "options"
})
@Generated("jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CbAddressWarning {

    @JsonProperty("type")
    private String type;
    @JsonProperty("title")
    private String title;
    @JsonProperty("details")
    private String details;
    @JsonProperty("image_url")
    private String imageUrl;
    @JsonProperty("options")
    private List<CbOption> options = null;

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("details")
    public String getDetails() {
        return details;
    }

    @JsonProperty("image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    @JsonProperty("options")
    public List<CbOption> getOptions() {
        return options;
    }

    @Override
    public String toString() {
        return "CbAddressWarning{" +
                "type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", details='" + details + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", options=" + options +
                '}';
    }
}
