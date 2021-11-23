package org.estonlabs.coinbase.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.estonlabs.coinbase.domain.general.CbCountry;
import org.estonlabs.coinbase.domain.general.CbTiers;

import java.util.Date;
import java.util.Objects;
import javax.annotation.Generated;

@Generated("jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CbUser{

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("username")
    private String username;
    @JsonProperty("resource")
    private String resource;
    @JsonProperty("state")
    private String state;
    @JsonProperty("nationality")
    private Object nationality;
    @JsonProperty("country")
    private CbCountry country;
    @JsonProperty("tiers")
    private CbTiers tiers;
    @JsonProperty("profile_location")
    private Object profileLocation;
    @JsonProperty("profile_bio")
    private String profileBio;
    @JsonProperty("profile_url")
    private String profileUrl;
    @JsonProperty("avatar_url")
    private String avatarUrl;
    @JsonProperty("legacy_id")
    private String legacy_id;
    @JsonProperty("resource_path")
    private String resourcePath;
    @JsonProperty("time_zone")
    private String timeZone;
    @JsonProperty("native_currency")
    private String nativeCurrency;
    @JsonProperty("bitcoin_unit")
    private String bitcoinUnit;
    @JsonProperty("region_supports_fiat_transfers")
    private boolean regionSupportsFiatTransfers;
    @JsonProperty("region_supports_crypto_to_crypto_transfers")
    private boolean regionSupportsCrypto2CryptoTransfers;
    @JsonProperty("created_at")
    protected Date createdAt;
    @JsonProperty("updated_at")
    protected Date updatedAt;
    @JsonProperty("supports_rewards")
    private boolean supportsRewards;
    @JsonProperty("has_blocking_buy_restrictions")
    private boolean hasBlockingBuyRestrictions;
    @JsonProperty("has_made_a_purchase")
    private boolean hasMadePurchase;
    @JsonProperty("has_buy_deposit_payment_methods")
    private boolean hasBuyDepositPaymentMethods;
    @JsonProperty("has_unverified_buy_deposit_payment_methods")
    private boolean hasVerifiedBuyDepositPaymentMethods;
    @JsonProperty("needs_kyc_remediation")
    private boolean needsKycRemediation;
    @JsonProperty("show_instant_ach_ux")
    private boolean showInstantAchUx;
    @JsonProperty("user_type")
    private String userType;
    @JsonProperty("referral_money")
    private CbReferralMoney referralMoney;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getResource() {
        return resource;
    }

    public String getState() {
        return state;
    }

    public Object getNationality() {
        return nationality;
    }

    public CbCountry getCountry() {
        return country;
    }

    public Object getProfileLocation() {
        return profileLocation;
    }

    public String getProfileBio() {
        return profileBio;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getLegacy_id() {
        return legacy_id;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public String getNativeCurrency() {
        return nativeCurrency;
    }

    public String getBitcoinUnit() {
        return bitcoinUnit;
    }

    public boolean isRegionSupportsFiatTransfers() {
        return regionSupportsFiatTransfers;
    }

    public boolean isRegionSupportsCrypto2CryptoTransfers() {
        return regionSupportsCrypto2CryptoTransfers;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public boolean isSupportsRewards() {
        return supportsRewards;
    }

    public CbTiers getTiers() {
        return tiers;
    }

    public boolean isHasBlockingBuyRestrictions() {
        return hasBlockingBuyRestrictions;
    }

    public boolean isHasMadePurchase() {
        return hasMadePurchase;
    }

    public boolean isHasBuyDepositPaymentMethods() {
        return hasBuyDepositPaymentMethods;
    }

    public boolean isHasVerifiedBuyDepositPaymentMethods() {
        return hasVerifiedBuyDepositPaymentMethods;
    }

    public boolean isNeedsKycRemediation() {
        return needsKycRemediation;
    }

    public boolean isShowInstantAchUx() {
        return showInstantAchUx;
    }

    public String getUserType() {
        return userType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CbUser cbUser = (CbUser) o;
        return Objects.equals(id, cbUser.id) &&
                Objects.equals(name, cbUser.name) &&
                Objects.equals(username, cbUser.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, username);
    }

    @Override
    public String toString() {
        return "CbUser{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", resource='" + resource + '\'' +
                ", state='" + state + '\'' +
                ", nationality=" + nationality +
                ", country=" + country +
                ", tiers=" + tiers +
                ", profileLocation=" + profileLocation +
                ", profileBio='" + profileBio + '\'' +
                ", profileUrl='" + profileUrl + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", legacy_id='" + legacy_id + '\'' +
                ", resourcePath='" + resourcePath + '\'' +
                ", timeZone='" + timeZone + '\'' +
                ", nativeCurrency='" + nativeCurrency + '\'' +
                ", bitcoinUnit='" + bitcoinUnit + '\'' +
                ", regionSupportsFiatTransfers=" + regionSupportsFiatTransfers +
                ", regionSupportsCrypto2CryptoTransfers=" + regionSupportsCrypto2CryptoTransfers +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", supportsRewards=" + supportsRewards +
                ", hasBlockingBuyRestrictions=" + hasBlockingBuyRestrictions +
                ", hasMadePurchase=" + hasMadePurchase +
                ", hasBuyDepositPaymentMethods=" + hasBuyDepositPaymentMethods +
                ", hasVerifiedBuyDepositPaymentMethods=" + hasVerifiedBuyDepositPaymentMethods +
                ", needsKycRemediation=" + needsKycRemediation +
                ", showInstantAchUx=" + showInstantAchUx +
                ", userType='" + userType + '\'' +
                ", referralMoney=" + referralMoney +
                '}';
    }
}