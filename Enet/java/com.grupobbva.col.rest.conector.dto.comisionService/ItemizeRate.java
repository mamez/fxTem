
package com.grupobbva.col.rest.conector.dto.comisionService;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class ItemizeRate {

    private String description;
    private ItemizeRateUnit itemizeRateUnit;
    private String rateType;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ItemizeRateUnit getItemizeRateUnit() {
        return itemizeRateUnit;
    }

    public void setItemizeRateUnit(ItemizeRateUnit itemizeRateUnit) {
        this.itemizeRateUnit = itemizeRateUnit;
    }

    public String getRateType() {
        return rateType;
    }

    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

}
