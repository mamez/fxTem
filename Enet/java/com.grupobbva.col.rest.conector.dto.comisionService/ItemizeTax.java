
package com.grupobbva.col.rest.conector.dto.comisionService;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class ItemizeTax {

    private String description;
    private ItemizeTaxUnit itemizeTaxUnit;
    private String taxType;

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

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

}
