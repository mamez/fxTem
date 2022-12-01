
package com.grupobbva.col.rest.conector.dto.dynamicCurrency;

public class ExchangeRate {

    private Double amount;
    private String baseCurrency;
    private String targetCurrency;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

}
