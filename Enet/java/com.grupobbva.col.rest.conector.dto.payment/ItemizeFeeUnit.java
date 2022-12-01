
package com.grupobbva.col.rest.conector.dto.payment;

import java.math.BigDecimal;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class ItemizeFeeUnit {

    @SerializedName("unitType")
    @Expose
    private String unitType;
    @SerializedName("amount")
    @Expose
    private BigDecimal amount;
    @SerializedName("currency")
    @Expose
    private String currency;

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

}
