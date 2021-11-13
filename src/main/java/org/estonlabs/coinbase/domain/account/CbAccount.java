package org.estonlabs.coinbase.domain.account;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.estonlabs.coinbase.domain.DataProvider;
import org.estonlabs.coinbase.domain.Named;

import javax.annotation.Generated;
import java.util.Date;
import java.util.Objects;

@Generated("jsonschema2pojo")
public class CbAccount implements Named {

    private String id;
    private String name;
    private Boolean primary;
    private String type;
    private CbCurrency currency;
    private CbBalance balance;
    private String resource;
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
    private boolean alloWithdrawals;

    @JsonProperty("rewards_apy")
    private String rewardsApy;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getPrimary() {
        return primary;
    }

    public void setPrimary(Boolean primary) {
        this.primary = primary;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CbCurrency getCurrency() {
        return currency;
    }

    public void setCurrency(CbCurrency currency) {
        this.currency = currency;
    }

    public CbBalance getBalance() {
        return balance;
    }

    public void setBalance(CbBalance balance) {
        this.balance = balance;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public CbRewards getRewards() {
        return rewards;
    }

    public void setRewards(CbRewards rewards) {
        this.rewards = rewards;
    }

    public boolean isAllowDeposits() {
        return allowDeposits;
    }

    public void setAllowDeposits(boolean allowDeposits) {
        this.allowDeposits = allowDeposits;
    }

    public boolean isAlloWithdrawals() {
        return alloWithdrawals;
    }

    public void setAlloWithdrawals(boolean alloWithdrawals) {
        this.alloWithdrawals = alloWithdrawals;
    }

    public String isRewardsApy() {
        return rewardsApy;
    }

    public void setRewardsApy(String rewardsApy) {
        this.rewardsApy = rewardsApy;
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
                ", alloWithdrawals=" + alloWithdrawals +
                ", rewardsApy='" + rewardsApy + '\'' +
                '}';
    }
}