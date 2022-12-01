
package com.grupobbva.col.rest.conector.dto.operativeConditions;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Taxes {

    @SerializedName("totalTaxes")
    @Expose
    private TotalTaxes totalTaxes;
    @SerializedName("itemizeTaxes")
    @Expose
    private List<ItemizeTax> itemizeTaxes = new ArrayList<ItemizeTax>();

    public TotalTaxes getTotalTaxes() {
        return totalTaxes;
    }

    public void setTotalTaxes(TotalTaxes totalTaxes) {
        this.totalTaxes = totalTaxes;
    }

    public List<ItemizeTax> getItemizeTaxes() {
        return itemizeTaxes;
    }

    public void setItemizeTaxes(List<ItemizeTax> itemizeTaxes) {
        this.itemizeTaxes = itemizeTaxes;
    }

}
