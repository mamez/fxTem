
package com.grupobbva.col.rest.conector.dto.payment;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class ItemizeTax {

    @SerializedName("taxType")
    @Expose
    private String taxType;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("itemizeTaxUnit")
    @Expose
    private ItemizeTaxUnit itemizeTaxUnit;

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ItemizeTaxUnit getItemizeTaxUnit() {
        return itemizeTaxUnit;
    }

    public void setItemizeTaxUnit(ItemizeTaxUnit itemizeTaxUnit) {
        this.itemizeTaxUnit = itemizeTaxUnit;
    }

}
