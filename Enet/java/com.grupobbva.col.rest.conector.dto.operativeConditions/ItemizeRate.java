
package com.grupobbva.col.rest.conector.dto.operativeConditions;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class ItemizeRate {

    @SerializedName("rateType")
    @Expose
    private String rateType;
    @SerializedName("itemizeRateUnits")
    @Expose
    private ItemizeRateUnits itemizeRateUnits;
    @SerializedName("description")
    @Expose
    private String description;

    public String getRateType() {
        return rateType;
    }

    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    public ItemizeRateUnits getItemizeRateUnits() {
        return itemizeRateUnits;
    }

    public void setItemizeRateUnits(ItemizeRateUnits itemizeRateUnits) {
        this.itemizeRateUnits = itemizeRateUnits;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
