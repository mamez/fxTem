
package com.grupobbva.col.rest.conector.dto.dynamicCurrency;

import java.util.List;
import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class ItemizeRate {

    private String rateType;
    private String description;
    private List<ItemizeRatesUnit> itemizeRatesUnit = null;

    public String getRateType() {
        return rateType;
    }

    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ItemizeRatesUnit> getItemizeRatesUnit() {
        return itemizeRatesUnit;
    }

    public void setItemizeRatesUnit(List<ItemizeRatesUnit> itemizeRatesUnit) {
        this.itemizeRatesUnit = itemizeRatesUnit;
    }

}
