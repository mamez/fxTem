
package com.grupobbva.col.rest.conector.dto.dynamicCurrency;

import java.util.List;
import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class ItemizeFee {

    private String feeType;
    private String description;
    private List<ItemizeFeeUnit> itemizeFeeUnits = null;

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

    public List<ItemizeFeeUnit> getItemizeFeeUnits() {
        return itemizeFeeUnits;
    }

    public void setItemizeFeeUnits(List<ItemizeFeeUnit> itemizeFeeUnits) {
        this.itemizeFeeUnits = itemizeFeeUnits;
    }

}
