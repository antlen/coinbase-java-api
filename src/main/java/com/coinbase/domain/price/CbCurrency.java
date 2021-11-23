package com.coinbase.domain.price;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
 *
 * @author antlen
 */
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
