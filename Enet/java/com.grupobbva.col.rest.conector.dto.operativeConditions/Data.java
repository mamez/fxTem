
package com.grupobbva.col.rest.conector.dto.operativeConditions;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Data {

    @SerializedName("operationType")
    @Expose
    private String operationType;
    @SerializedName("businessId")
    @Expose
    private String businessId;
    @SerializedName("amountNegotiated")
    @Expose
    private AmountNegotiated amountNegotiated;
    @SerializedName("rates")
    @Expose
    private Rates rates;
    @SerializedName("totalCharged")
    @Expose
    private TotalCharged totalCharged;
    @SerializedName("operationNumber")
    @Expose
    private String operationNumber;
    @SerializedName("channel")
    @Expose
    private String channel;
    @SerializedName("taxes")
    @Expose
    private Taxes taxes;
    @SerializedName("fees")
    @Expose
    private Fees fees;

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public AmountNegotiated getAmountNegotiated() {
        return amountNegotiated;
    }

    public void setAmountNegotiated(AmountNegotiated amountNegotiated) {
        this.amountNegotiated = amountNegotiated;
    }

    public Rates getRates() {
        return rates;
    }

    public void setRates(Rates rates) {
        this.rates = rates;
    }

    public TotalCharged getTotalCharged() {
        return totalCharged;
    }

    public void setTotalCharged(TotalCharged totalCharged) {
        this.totalCharged = totalCharged;
    }

    public String getOperationNumber() {
        return operationNumber;
    }

    public void setOperationNumber(String operationNumber) {
        this.operationNumber = operationNumber;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Taxes getTaxes() {
        return taxes;
    }

    public void setTaxes(Taxes taxes) {
        this.taxes = taxes;
    }

    public Fees getFees() {
        return fees;
    }

    public void setFees(Fees fees) {
        this.fees = fees;
    }

}
