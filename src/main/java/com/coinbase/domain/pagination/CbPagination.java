package com.coinbase.domain.pagination;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CbPagination {
    @JsonProperty("limit")
    private Integer limit;
    @JsonProperty("order")
    private String order;
    @JsonProperty("ending_before")
    private String endingBefore;
    @JsonProperty("starting_after")
    private String startingAfter;
    @JsonProperty("previous_ending_before")
    private String previousEndingBefore;
    @JsonProperty("next_starting_after")
    private String nextStartingAfter;
    @JsonProperty("next_uri")
    private String nextUri;
    @JsonProperty("previous_uri")
    private String previousUri;

    public Integer getLimit() {
        return limit;
    }

    public String getOrder() {
        return order;
    }

    public String getEndingBefore() {
        return endingBefore;
    }

    public String getStartingAfter() {
        return startingAfter;
    }

    public String getPreviousEndingBefore() {
        return previousEndingBefore;
    }

    public String getNextStartingAfter() {
        return nextStartingAfter;
    }

    public String getNextUri() {
        return nextUri;
    }

    public String getPreviousUri() {
        return previousUri;
    }
}