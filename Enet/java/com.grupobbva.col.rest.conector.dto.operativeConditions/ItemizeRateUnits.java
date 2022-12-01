
package com.grupobbva.col.rest.conector.dto.operativeConditions;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class ItemizeRateUnits {

    @SerializedName("amount")
    @Expose
    private Double amount;
    @SerializedName("currency")
    @Expose
    private String currency;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

}
