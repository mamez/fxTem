
package com.grupobbva.col.rest.conector.dto.payment;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class ItemizeFee {

    @SerializedName("feeType")
    @Expose
    private String feeType;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("itemizeFeeUnit")
    @Expose
    private ItemizeFeeUnit itemizeFeeUnit;

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ItemizeFeeUnit getItemizeFeeUnit() {
        return itemizeFeeUnit;
    }

    public void setItemizeFeeUnit(ItemizeFeeUnit itemizeFeeUnit) {
        this.itemizeFeeUnit = itemizeFeeUnit;
    }

}
