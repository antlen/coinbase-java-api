package org.estonlabs.coinbase.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;
import java.util.Objects;

@Generated("jsonschema2pojo")
public class CbTiers {

    @JsonProperty("completed_description")
    private String completedDescription;

    @JsonProperty("upgrade_button_text")
    private String upgradeButtonText;

    private Object header;
    private Object body;

    public String getCompletedDescription() {
        return completedDescription;
    }

    public String getUpgradeButtonText() {
        return upgradeButtonText;
    }

    public Object getHeader() {
        return header;
    }

    public Object getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "CbTiers{" +
                "completedDescription='" + Objects.toString(completedDescription) + '\'' +
                ", upgradeButtonText='" + Objects.toString(upgradeButtonText) + '\'' +
                ", header=" + Objects.toString(header) +
                ", body=" + Objects.toString(body) +
                '}';
    }
}