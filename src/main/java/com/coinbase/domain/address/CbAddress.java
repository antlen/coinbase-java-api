package com.coinbase.domain.address;
import java.util.List;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

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
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "address",
        "address_info",
        "name",
        "created_at",
        "updated_at",
        "network",
        "uri_scheme",
        "resource",
        "resource_path",
        "warnings",
        "deposit_uri",
        "callback_url"
})
@Generated("jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CbAddress {

    @JsonProperty("id")
    private String id;
    @JsonProperty("address")
    private String address;
    @JsonProperty("address_info")
    private CbAddressInfo addressInfo;
    @JsonProperty("name")
    private String name;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("network")
    private String network;
    @JsonProperty("uri_scheme")
    private String uriScheme;
    @JsonProperty("resource")
    private String resource;
    @JsonProperty("resource_path")
    private String resourcePath;
    @JsonProperty("warnings")
    private List<CbAddressWarning> warnings = null;
    @JsonProperty("deposit_uri")
    private String depositUri;
    @JsonProperty("callback_url")
    private Object callbackUrl;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    @JsonProperty("address_info")
    public CbAddressInfo getAddressInfo() {
        return addressInfo;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("created_at")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("updated_at")
    public String getUpdatedAt() {
        return updatedAt;
    }

    @JsonProperty("network")
    public String getNetwork() {
        return network;
    }

    @JsonProperty("uri_scheme")
    public String getUriScheme() {
        return uriScheme;
    }

    @JsonProperty("resource")
    public String getResource() {
        return resource;
    }

    @JsonProperty("resource_path")
    public String getResourcePath() {
        return resourcePath;
    }

    @JsonProperty("warnings")
    public List<CbAddressWarning> getWarnings() {
        return warnings;
    }

    @JsonProperty("deposit_uri")
    public String getDepositUri() {
        return depositUri;
    }

    @JsonProperty("callback_url")
    public Object getCallbackUrl() {
        return callbackUrl;
    }

    @Override
    public String toString() {
        return "CbAddress{" +
                "id='" + id + '\'' +
                ", address='" + address + '\'' +
                ", addressInfo=" + addressInfo +
                ", name='" + name + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", network='" + network + '\'' +
                ", uriScheme='" + uriScheme + '\'' +
                ", resource='" + resource + '\'' +
                ", resourcePath='" + resourcePath + '\'' +
                ", warnings=" + warnings +
                ", depositUri='" + depositUri + '\'' +
                ", callbackUrl=" + callbackUrl +
                '}';
    }
}
