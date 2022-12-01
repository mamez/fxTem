
package com.grupobbva.col.rest.conector.dto.comisionService;

import java.math.BigDecimal;
import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class ItemizeTaxUnit {

    private BigDecimal amount;
    private String currency;
    private String unitType;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

}
