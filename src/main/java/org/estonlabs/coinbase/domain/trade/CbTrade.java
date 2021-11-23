package org.estonlabs.coinbase.domain.trade;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.*;
import org.estonlabs.coinbase.domain.general.CbResource;
import org.estonlabs.coinbase.exception.CbApiException;
import org.estonlabs.coinbase.domain.price.CbAmount;
import org.estonlabs.coinbase.domain.transaction.payment.CbPaymentMethod;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "status",
        "payment_method",
        "transaction",
        "amount",
        "total",
        "subtotal",
        "created_at",
        "updated_at",
        "resource",
        "resource_path",
        "committed",
        "instant",
        "fee",
        "payout_at"
})
@Generated("jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CbTrade {
    private final static DateTimeFormatter CREATION_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

    @JsonProperty("id")
    private String id;
    @JsonProperty("status")
    private String status;
    @JsonProperty("payment_method")
    private CbPaymentMethod paymentMethod;
    @JsonProperty("user_reference")
    private String userIdReference;
    @JsonProperty("transaction")
    private CbResource transaction;
    @JsonProperty("amount")
    private CbAmount amount;
    @JsonProperty("total")
    private CbAmount total;
    @JsonProperty("subtotal")
    private CbAmount subtotal;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("resource")
    private String resource;
    @JsonProperty("resource_path")
    private String resourcePath;
    @JsonProperty("committed")
    private Boolean committed;
    @JsonProperty("instant")
    private Boolean instant;
    @JsonProperty("fee")
    private CbAmount fee;
    @JsonProperty("payout_at")
    private String payoutAt;
    @JsonProperty("unit_price")
    private CbAmount unitPrice;
    @JsonProperty("hold_until")
    private String holdUntil;
    @JsonProperty("idem")
    private String idem;
    @JsonProperty("hold_days")
    private Integer holdDays;
    @JsonProperty("next_step")
    private CbNextStep nextStep;
    @JsonProperty("is_first_buy")
    private Boolean isFirstBuy;
    @JsonProperty("requires_completion_step")
    private Boolean requiresCompletionStep;
    @JsonIgnore
    private Side side;

    private LocalDateTime creationDateTime;

    @JsonIgnore
    public Side getSide() {
        return side;
    }

    @JsonIgnore
    public CbTrade setSide(Side side) {
        if(this.side != null) throw new CbApiException("Side is already set");
        this.side = side;
        return this;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("payment_method")
    public CbPaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    @JsonProperty("transaction")
    public CbResource getTransaction() {
        return transaction;
    }

    @JsonProperty("amount")
    public CbAmount getAmount() {
        return amount;
    }

    @JsonProperty("total")
    public CbAmount getTotal() {
        return total;
    }

    @JsonProperty("subtotal")
    public CbAmount getSubtotal() {
        return subtotal;
    }

    @JsonProperty("created_at")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("updated_at")
    public String getUpdatedAt() {
        return updatedAt;
    }

    @JsonProperty("resource")
    public String getResource() {
        return resource;
    }

    @JsonProperty("resource_path")
    public String getResourcePath() {
        return resourcePath;
    }

    @JsonProperty("committed")
    public Boolean getCommitted() {
        return committed;
    }

    @JsonProperty("instant")
    public Boolean getInstant() {
        return instant;
    }

    @JsonProperty("fee")
    public CbAmount getFee() {
        return fee;
    }

    @JsonProperty("payout_at")
    public String getPayoutAt() {
        return payoutAt;
    }

    public String getUserIdReference() {
        return userIdReference;
    }

    @JsonProperty("unit_price")
    public CbAmount getUnitPrice() {
        return unitPrice;
    }

    @JsonProperty("hold_until")
    public String getHoldUntil() {
        return holdUntil;
    }

    @JsonProperty("hold_days")
    public Integer getHoldDays() {
        return holdDays;
    }

    @JsonProperty("next_step")
    public CbNextStep getNextStep() {
        return nextStep;
    }

    @JsonProperty("is_first_buy")
    public Boolean getIsFirstBuy() {
        return isFirstBuy;
    }

    @JsonProperty("requires_completion_step")
    public Boolean getRequiresCompletionStep() {
        return requiresCompletionStep;
    }

    @JsonProperty("idem")
    public String getIdem() {
        return idem;
    }

    public LocalDateTime getCreationDateTime() {
        if(creationDateTime == null){
            creationDateTime = LocalDateTime.parse(createdAt,CREATION_TIME_FORMAT);
        }
        return creationDateTime;
    }

    @Override
    public String toString() {
        return "CbTrade{" +
                "id='" + id + '\'' +
                ", status='" + status + '\'' +
                ", paymentMethod=" + paymentMethod +
                ", userIdReference='" + userIdReference + '\'' +
                ", transaction=" + transaction +
                ", amount=" + amount +
                ", total=" + total +
                ", subtotal=" + subtotal +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", resource='" + resource + '\'' +
                ", resourcePath='" + resourcePath + '\'' +
                ", committed=" + committed +
                ", instant=" + instant +
                ", fee=" + fee +
                ", payoutAt='" + payoutAt + '\'' +
                ", unitPrice=" + unitPrice +
                ", holdUntil='" + holdUntil + '\'' +
                ", idem='" + idem + '\'' +
                ", holdDays=" + holdDays +
                ", nextStep=" + nextStep +
                ", isFirstBuy=" + isFirstBuy +
                ", requiresCompletionStep=" + requiresCompletionStep +
                ", side=" + side +
                ", creationDateTime=" + creationDateTime +
                '}';
    }
}