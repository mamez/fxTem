
package com.grupobbva.col.rest.conector.dto.dynamicCurrency;

public class DinamicCurrencyRequest {

    private Customer customer;
    private String operationType;
    private ExchangeRate exchangeRate;
    private Boolean isSimulation;
    private String creationDate;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public ExchangeRate getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(ExchangeRate exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public Boolean getIsSimulation() {
        return isSimulation;
    }

    public void setIsSimulation(Boolean isSimulation) {
        this.isSimulation = isSimulation;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

}
