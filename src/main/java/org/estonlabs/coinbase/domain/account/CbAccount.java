package org.estonlabs.coinbase.domain.account;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.estonlabs.coinbase.domain.Named;
import org.estonlabs.coinbase.domain.price.CbCurrency;

import javax.annotation.Generated;
import java.util.Date;

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

    @Override
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

    public boolean isAlloWithdrawals() {
        return alloWithdrawals;
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
                ", alloWithdrawals=" + alloWithdrawals +
                ", rewardsApy='" + rewardsApy + '\'' +
                '}';
    }
}