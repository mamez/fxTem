
package com.grupobbva.col.rest.conector.dto.operativeConditions;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class ItemizeTax {

    @SerializedName("taxType")
    @Expose
    private String taxType;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("amountTax")
    @Expose
    private AmountTax amountTax;

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AmountTax getAmountTax() {
        return amountTax;
    }

    public void setAmountTax(AmountTax amountTax) {
        this.amountTax = amountTax;
    }

}
