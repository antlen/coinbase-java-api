package org.estonlabs.coinbase.domain.transaction.payment;
import java.util.List;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "type",
        "name",
        "buy",
        "deposit"
})
@Generated("jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CbPaymentMethodLimits {

    @JsonProperty("type")
    private String type;
    @JsonProperty("name")
    private String name;
    @JsonProperty("buy")
    private List<CbBuyLimit> buy = null;
    @JsonProperty("deposit")
    private List<CbDepositReference> deposit = null;

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

    @JsonProperty("buy")
    public List<CbBuyLimit> getBuy() {
        return buy;
    }

    @JsonProperty("deposit")
    public List<CbDepositReference> getDeposit() {
        return deposit;
    }

    @Override
    public String toString() {
        return "CbPaymentMethodLimits{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", buy=" + buy +
                ", deposit=" + deposit +
                '}';
    }
}
