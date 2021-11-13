package org.estonlabs.coinbase.domain.transaction;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.estonlabs.coinbase.domain.account.CbAccountRef;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "type",
        "name",
        "currency",
        "primary_buy",
        "primary_sell",
        "instant_buy",
        "instant_sell",
        "created_at",
        "updated_at",
        "resource",
        "resource_path",
        "allow_buy",
        "allow_sell",
        "allow_deposit",
        "allow_withdraw",
        "fiat_account",
        "verified",
        "minimum_purchase_amount"
})
@Generated("jsonschema2pojo")
public class CbPaymentMethod {

    @JsonProperty("id")
    private String id;
    @JsonProperty("type")
    private String type;
    @JsonProperty("name")
    private String name;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("primary_buy")
    private Boolean primaryBuy;
    @JsonProperty("primary_sell")
    private Boolean primarySell;
    @JsonProperty("instant_buy")
    private Boolean instantBuy;
    @JsonProperty("instant_sell")
    private Boolean instantSell;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("resource")
    private String resource;
    @JsonProperty("resource_path")
    private String resourcePath;
    @JsonProperty("allow_buy")
    private Boolean allowBuy;
    @JsonProperty("allow_sell")
    private Boolean allowSell;
    @JsonProperty("allow_deposit")
    private Boolean allowDeposit;
    @JsonProperty("allow_withdraw")
    private Boolean allowWithdraw;
    @JsonProperty("fiat_account")
    private CbAccountRef fiatAccount;
    @JsonProperty("verified")
    private Boolean verified;
    @JsonProperty("minimum_purchase_amount")
    private CbMinimumPurchaseAmount minimumPurchaseAmount;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

    @JsonProperty("currency")
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @JsonProperty("primary_buy")
    public Boolean getPrimaryBuy() {
        return primaryBuy;
    }

    @JsonProperty("primary_buy")
    public void setPrimaryBuy(Boolean primaryBuy) {
        this.primaryBuy = primaryBuy;
    }

    @JsonProperty("primary_sell")
    public Boolean getPrimarySell() {
        return primarySell;
    }

    @JsonProperty("primary_sell")
    public void setPrimarySell(Boolean primarySell) {
        this.primarySell = primarySell;
    }

    @JsonProperty("instant_buy")
    public Boolean getInstantBuy() {
        return instantBuy;
    }

    @JsonProperty("instant_buy")
    public void setInstantBuy(Boolean instantBuy) {
        this.instantBuy = instantBuy;
    }

    @JsonProperty("instant_sell")
    public Boolean getInstantSell() {
        return instantSell;
    }

    @JsonProperty("instant_sell")
    public void setInstantSell(Boolean instantSell) {
        this.instantSell = instantSell;
    }

    @JsonProperty("created_at")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("updated_at")
    public String getUpdatedAt() {
        return updatedAt;
    }

    @JsonProperty("updated_at")
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @JsonProperty("resource")
    public String getResource() {
        return resource;
    }

    @JsonProperty("resource")
    public void setResource(String resource) {
        this.resource = resource;
    }

    @JsonProperty("resource_path")
    public String getResourcePath() {
        return resourcePath;
    }

    @JsonProperty("resource_path")
    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    @JsonProperty("allow_buy")
    public Boolean getAllowBuy() {
        return allowBuy;
    }

    @JsonProperty("allow_buy")
    public void setAllowBuy(Boolean allowBuy) {
        this.allowBuy = allowBuy;
    }

    @JsonProperty("allow_sell")
    public Boolean getAllowSell() {
        return allowSell;
    }

    @JsonProperty("allow_sell")
    public void setAllowSell(Boolean allowSell) {
        this.allowSell = allowSell;
    }

    @JsonProperty("allow_deposit")
    public Boolean getAllowDeposit() {
        return allowDeposit;
    }

    @JsonProperty("allow_deposit")
    public void setAllowDeposit(Boolean allowDeposit) {
        this.allowDeposit = allowDeposit;
    }

    @JsonProperty("allow_withdraw")
    public Boolean getAllowWithdraw() {
        return allowWithdraw;
    }

    @JsonProperty("allow_withdraw")
    public void setAllowWithdraw(Boolean allowWithdraw) {
        this.allowWithdraw = allowWithdraw;
    }

    @JsonProperty("fiat_account")
    public CbAccountRef getFiatAccount() {
        return fiatAccount;
    }

    @JsonProperty("fiat_account")
    public void setFiatAccount(CbAccountRef fiatAccount) {
        this.fiatAccount = fiatAccount;
    }

    @JsonProperty("verified")
    public Boolean getVerified() {
        return verified;
    }

    @JsonProperty("verified")
    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    @JsonProperty("minimum_purchase_amount")
    public CbMinimumPurchaseAmount getMinimumPurchaseAmount() {
        return minimumPurchaseAmount;
    }

    @JsonProperty("minimum_purchase_amount")
    public void setMinimumPurchaseAmount(CbMinimumPurchaseAmount minimumPurchaseAmount) {
        this.minimumPurchaseAmount = minimumPurchaseAmount;
    }

    @Override
    public String toString() {
        return "CbPaymentMethod{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", currency='" + currency + '\'' +
                ", primaryBuy=" + primaryBuy +
                ", primarySell=" + primarySell +
                ", instantBuy=" + instantBuy +
                ", instantSell=" + instantSell +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", resource='" + resource + '\'' +
                ", resourcePath='" + resourcePath + '\'' +
                ", allowBuy=" + allowBuy +
                ", allowSell=" + allowSell +
                ", allowDeposit=" + allowDeposit +
                ", allowWithdraw=" + allowWithdraw +
                ", fiatAccount=" + fiatAccount +
                ", verified=" + verified +
                ", minimumPurchaseAmount=" + minimumPurchaseAmount +
                '}';
    }
}