package com.coinbase.domain.price;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CbCurrency {
    @JsonProperty("code")
    private String code;
    @JsonProperty("name")
    private String name;
    @JsonProperty("type")
    private String type;
    @JsonProperty("color")
    private String color;
    @JsonProperty("exponent")
    private Integer exponent;
    @JsonProperty("slug")
    private String slug;
    @JsonProperty("sort_index")
    private Integer sortIndex;
    @JsonProperty("address_regex")
    private String addressRegex;
    @JsonProperty("asset_id")
    private String assetId;
    @JsonProperty("destination_tag_name")
    private String destinationTagName;
    @JsonProperty("destination_tag_regex")
    private String destinationTagRegex;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getColor() {
        return color;
    }

    public Integer getExponent() {
        return exponent;
    }

    public String getSlug() {
        return slug;
    }

    public Integer getSortIndex() {
        return sortIndex;
    }

    public String getAddressRegex() {
        return addressRegex;
    }

    public String getAssetId() {
        return assetId;
    }

    public String getDestinationTagName() {
        return destinationTagName;
    }

    public String getDestinationTagRegex() {
        return destinationTagRegex;
    }

    @Override
    public String toString() {
        return "CbCurrency{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", color='" + color + '\'' +
                ", exponent=" + exponent +
                ", slug='" + slug + '\'' +
                ", sortIndex=" + sortIndex +
                ", addressRegex='" + addressRegex + '\'' +
                ", assetId='" + assetId + '\'' +
                ", destinationTagName='" + destinationTagName + '\'' +
                ", destinationTagRegex='" + destinationTagRegex + '\'' +
                '}';
    }
}
