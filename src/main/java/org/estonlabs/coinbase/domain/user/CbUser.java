package org.estonlabs.coinbase.domain.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.estonlabs.coinbase.domain.CbCountry;
import org.estonlabs.coinbase.domain.CbTiers;
import org.estonlabs.coinbase.domain.Named;

import java.util.Date;
import java.util.Objects;
import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class CbUser implements Named {

    private String id;
    private String name;
    private String username;
    private String resource;
    private String state;
    private Object nationality;
    private CbCountry country;
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

    @Override
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
                "id='" + Objects.toString(id) + '\'' +
                ", name='" + Objects.toString(name) + '\'' +
                ", username='" + Objects.toString(username) + '\'' +
                ", resource='" + Objects.toString(resource) + '\'' +
                ", state='" + Objects.toString(state) + '\'' +
                ", nationality=" + Objects.toString(nationality) +
                ", country=" + Objects.toString(country) +
                ", tiers=" + Objects.toString(tiers) +
                ", profileLocation=" + Objects.toString(profileLocation) +
                ", profileBio='" + Objects.toString(profileBio) + '\'' +
                ", profileUrl='" + Objects.toString(profileUrl) + '\'' +
                ", avatarUrl='" + Objects.toString(avatarUrl) + '\'' +
                ", legacy_id='" + Objects.toString(legacy_id) + '\'' +
                ", resourcePath='" + Objects.toString(resourcePath) + '\'' +
                ", timeZone='" + Objects.toString(timeZone) + '\'' +
                ", nativeCurrency='" + Objects.toString(nativeCurrency) + '\'' +
                ", bitcoinUnit='" + Objects.toString(bitcoinUnit) + '\'' +
                ", regionSupportsFiatTransfers=" + Objects.toString(regionSupportsFiatTransfers) +
                ", regionSupportsCrypto2CryptoTransfers=" + Objects.toString(regionSupportsCrypto2CryptoTransfers) +
                ", createdAt=" + Objects.toString(createdAt) +
                ", updatedAt=" + Objects.toString(updatedAt) +
                ", supportsRewards=" + Objects.toString(supportsRewards) +
                ", hasBlockingBuyRestrictions=" + Objects.toString(hasBlockingBuyRestrictions) +
                ", hasMadePurchase=" + Objects.toString(hasMadePurchase) +
                ", hasBuyDepositPaymentMethods=" + Objects.toString(hasBuyDepositPaymentMethods) +
                ", hasVerifiedBuyDepositPaymentMethods=" + Objects.toString(hasVerifiedBuyDepositPaymentMethods) +
                ", needsKycRemediation=" + Objects.toString(needsKycRemediation) +
                ", showInstantAchUx=" + Objects.toString(showInstantAchUx) +
                ", userType='" + Objects.toString(userType) + '\'' +
                ", referralMoney=" + Objects.toString(referralMoney) +
                '}';
    }
}