
package com.grupobbva.col.rest.conector.dto.dynamicCurrency;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Operation {

    private TotalAmount totalAmount;
    private Fees fees;

    public TotalAmount getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(TotalAmount totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Fees getFees() {
        return fees;
    }

    public void setFees(Fees fees) {
        this.fees = fees;
    }

}
