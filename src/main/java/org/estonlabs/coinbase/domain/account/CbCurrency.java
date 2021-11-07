package org.estonlabs.coinbase.domain.account;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class CbCurrency {
    private String code;
    private String name;
    private String type;
    private String color;
    private Integer exponent;
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

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getExponent() {
        return exponent;
    }

    public void setExponent(Integer exponent) {
        this.exponent = exponent;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Integer getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(Integer sortIndex) {
        this.sortIndex = sortIndex;
    }

    public String getAddressRegex() {
        return addressRegex;
    }

    public void setAddressRegex(String addressRegex) {
        this.addressRegex = addressRegex;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getDestinationTagName() {
        return destinationTagName;
    }

    public void setDestinationTagName(String destinationTagName) {
        this.destinationTagName = destinationTagName;
    }

    public String getDestinationTagRegex() {
        return destinationTagRegex;
    }

    public void setDestinationTagRegex(String destinationTagRegex) {
        this.destinationTagRegex = destinationTagRegex;
    }

    @Override
    public String toString() {
        return "CbCurrency{" +
                "code='" + Objects.toString(code) + '\'' +
                ", name='" + Objects.toString(name) + '\'' +
                ", type='" + Objects.toString(type) + '\'' +
                ", color='" + Objects.toString(color) + '\'' +
                ", exponent=" + Objects.toString(exponent) +
                ", slug='" + Objects.toString(slug) + '\'' +
                ", sortIndex=" + Objects.toString(sortIndex) +
                ", addressRegex='" + Objects.toString(addressRegex) + '\'' +
                ", assetId='" + Objects.toString(assetId) + '\'' +
                ", destinationTagName='" + Objects.toString(destinationTagName) + '\'' +
                ", destinationTagRegex='" + Objects.toString(destinationTagRegex) + '\'' +
                '}';
    }
}
