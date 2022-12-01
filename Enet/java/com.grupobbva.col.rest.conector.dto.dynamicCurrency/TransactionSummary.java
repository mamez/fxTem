
package com.grupobbva.col.rest.conector.dto.dynamicCurrency;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class TransactionSummary {

    private Double baseAmount;
    private String baseCurrency;
    private Double targetRate;
    private String targetCurrency;
    private Double marketRateValue;

    public Double getBaseAmount() {
        return baseAmount;
    }

    public void setBaseAmount(Double baseAmount) {
        this.baseAmount = baseAmount;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public Double getTargetRate() {
        return targetRate;
    }

    public void setTargetRate(Double targetRate) {
        this.targetRate = targetRate;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public Double getMarketRateValue() {
        return marketRateValue;
    }

    public void setMarketRateValue(Double marketRateValue) {
        this.marketRateValue = marketRateValue;
    }

}
