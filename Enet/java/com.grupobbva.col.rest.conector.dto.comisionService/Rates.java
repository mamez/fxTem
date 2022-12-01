
package com.grupobbva.col.rest.conector.dto.comisionService;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Rates {

    private List<ItemizeRate> itemizeRates = new ArrayList<ItemizeRate>();
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
