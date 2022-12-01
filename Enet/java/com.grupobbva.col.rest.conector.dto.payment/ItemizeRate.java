
package com.grupobbva.col.rest.conector.dto.payment;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class ItemizeRate {

    @SerializedName("itemizeRateUnits")
    @Expose
    private Object itemizeRateUnits;
    @SerializedName("calculationDate")
    @Expose
    private String calculationDate;
    @SerializedName("description")
    @Expose
    private Object description;
    @SerializedName("rateType")
    @Expose
    private Object rateType;

    public Object getItemizeRateUnits() {
        return itemizeRateUnits;
    }

    public void setItemizeRateUnits(Object itemizeRateUnits) {
        this.itemizeRateUnits = itemizeRateUnits;
    }

    public String getCalculationDate() {
        return calculationDate;
    }

    public void setCalculationDate(String calculationDate) {
        this.calculationDate = calculationDate;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public Object getRateType() {
        return rateType;
    }

    public void setRateType(Object rateType) {
        this.rateType = rateType;
    }

}
