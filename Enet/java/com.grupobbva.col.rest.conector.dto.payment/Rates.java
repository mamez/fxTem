
package com.grupobbva.col.rest.conector.dto.payment;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Rates {

    @SerializedName("itemizeRates")
    @Expose
    private List<ItemizeRate> itemizeRates = new ArrayList<ItemizeRate>();
    @SerializedName("totalRates")
    @Expose
    private TotalRates totalRates;

    public List<ItemizeRate> getItemizeRates() {
        return itemizeRates;
    }

    public void setItemizeRates(List<ItemizeRate> itemizeRates) {
        this.itemizeRates = itemizeRates;
    }

    public TotalRates getTotalRates() {
        return totalRates;
    }

    public void setTotalRates(TotalRates totalRates) {
        this.totalRates = totalRates;
    }

}
