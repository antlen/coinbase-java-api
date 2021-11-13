package org.estonlabs.coinbase.domain;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;
import java.util.Date;

@Generated("jsonschema2pojo")
public class CbPagination {
    private Integer limit;
    private String order;

    @JsonProperty("ending_before")
    private Date endingBefore;

    @JsonProperty("starting_after")
    private Date startingAfter;

    @JsonProperty("previous_ending_before")
    private Date previousEndingBefore;

    @JsonProperty("next_starting_after")
    private Date previousStartingAfter;

    @JsonProperty("next_uri")
    private Object nextUri;

    @JsonProperty("previous_uri")
    private String previousUri;

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Date getEndingBefore() {
        return endingBefore;
    }

    public void setEndingBefore(Date endingBefore) {
        this.endingBefore = endingBefore;
    }

    public Date getStartingAfter() {
        return startingAfter;
    }

    public void setStartingAfter(Date startingAfter) {
        this.startingAfter = startingAfter;
    }

    public Date getPreviousEndingBefore() {
        return previousEndingBefore;
    }

    public void setPreviousEndingBefore(Date previousEndingBefore) {
        this.previousEndingBefore = previousEndingBefore;
    }

    public Date getPreviousStartingAfter() {
        return previousStartingAfter;
    }

    public void setPreviousStartingAfter(Date previousStartingAfter) {
        this.previousStartingAfter = previousStartingAfter;
    }

    public Object getNextUri() {
        return nextUri;
    }

    public void setNextUri(Object nextUri) {
        this.nextUri = nextUri;
    }

    public String getPreviousUri() {
        return previousUri;
    }

    public void setPreviousUri(String previousUri) {
        this.previousUri = previousUri;
    }
}