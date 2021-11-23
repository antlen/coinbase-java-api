package com.coinbase.domain.account;


import com.coinbase.domain.price.CbCurrency;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;
import java.util.Date;

@Generated("jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CbAccount {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("primary")
    private Boolean primary;
    @JsonProperty("type")
    private String type;
    @JsonProperty("currency")
    private CbCurrency currency;
    @JsonProperty("balance")
    private CbBalance balance;
    @JsonProperty("resource")
    private String resource;
    @JsonProperty("rewards")
    private CbRewards rewards;
    @JsonProperty("created_at")
    private Date createdAt;
    @JsonProperty("updated_at")
    private Date updatedAt;
    @JsonProperty("resource_path")
    private String resourcePath;
    @JsonProperty("allow_deposits")
    private boolean allowDeposits;
    @JsonProperty("allow_withdrawals")
    private boolean allowWithdrawals;
    @JsonProperty("rewards_apy")
    private String rewardsApy;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean getPrimary() {
        return primary;
    }

    public String getType() {
        return type;
    }

    public CbCurrency getCurrency() {
        return currency;
    }

    public CbBalance getBalance() {
        return balance;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public String getResource() {
        return resource;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public CbRewards getRewards() {
        return rewards;
    }

    public boolean isAllowDeposits() {
        return allowDeposits;
    }

    public boolean isAllowWithdrawals() {
        return allowWithdrawals;
    }

    public String isRewardsApy() {
        return rewardsApy;
    }

    @Override
    public String toString() {
        return "CbAccount{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", primary=" + primary +
                ", type='" + type + '\'' +
                ", currency=" + currency +
                ", balance=" + balance +
                ", resource='" + resource + '\'' +
                ", rewards=" + rewards +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", resourcePath='" + resourcePath + '\'' +
                ", allowDeposits=" + allowDeposits +
                ", alloWithdrawals=" + allowWithdrawals +
                ", rewardsApy='" + rewardsApy + '\'' +
                '}';
    }
}