package com.coinbase.domain.trade;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "action",
        "poll_path",
        "max_poll_attempts",
        "poll_interval_in_ms",
        "status_on_max_attempts"
})
@Generated("jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CbNextStep {

    @JsonProperty("action")
    private String action;
    @JsonProperty("poll_path")
    private String pollPath;
    @JsonProperty("max_poll_attempts")
    private Integer maxPollAttempts;
    @JsonProperty("poll_interval_in_ms")
    private Integer pollIntervalInMs;
    @JsonProperty("status_on_max_attempts")
    private String statusOnMaxAttempts;

    @JsonProperty("action")
    public String getAction() {
        return action;
    }

    @JsonProperty("poll_path")
    public String getPollPath() {
        return pollPath;
    }

    @JsonProperty("max_poll_attempts")
    public Integer getMaxPollAttempts() {
        return maxPollAttempts;
    }

    @JsonProperty("poll_interval_in_ms")
    public Integer getPollIntervalInMs() {
        return pollIntervalInMs;
    }

    @JsonProperty("status_on_max_attempts")
    public String getStatusOnMaxAttempts() {
        return statusOnMaxAttempts;
    }

    @Override
    public String toString() {
        return "CbNextStep{" +
                "action='" + action + '\'' +
                ", pollPath='" + pollPath + '\'' +
                ", maxPollAttempts=" + maxPollAttempts +
                ", pollIntervalInMs=" + pollIntervalInMs +
                ", statusOnMaxAttempts='" + statusOnMaxAttempts + '\'' +
                '}';
    }
}
