
package com.grupobbva.col.rest.conector.dto.comisionService;

import java.math.BigDecimal;
import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class NetAmountSecureOnlinePayments {

    private BigDecimal amount;
    private String currency;

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

}
