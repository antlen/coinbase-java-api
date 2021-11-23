package com.coinbase.domain.trade;

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
