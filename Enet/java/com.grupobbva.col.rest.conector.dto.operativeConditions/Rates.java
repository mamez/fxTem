
package com.grupobbva.col.rest.conector.dto.operativeConditions;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Rates {

    @SerializedName("totalRates")
    @Expose
    private TotalRates totalRates;
    @SerializedName("itemizeRates")
    @Expose
    private List<ItemizeRate> itemizeRates = new ArrayList<ItemizeRate>();

    public TotalRates getTotalRates() {
        return totalRates;
    }

    public void setTotalRates(TotalRates totalRates) {
        this.totalRates = totalRates;
    }

    public List<ItemizeRate> getItemizeRates() {
        return itemizeRates;
    }

    public void setItemizeRates(List<ItemizeRate> itemizeRates) {
        this.itemizeRates = itemizeRates;
    }

}
