
package com.grupobbva.col.rest.conector.dto.dynamicCurrency;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Data {

    private Customer customer;
    private LocalAmount localAmount;
    private EquivalentDollars equivalentDollars;
    private Rates rates;
    private Operation operation;
    private TransactionSummary transactionSummary;
    private String creationDate;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalAmount getLocalAmount() {
        return localAmount;
    }

    public void setLocalAmount(LocalAmount localAmount) {
        this.localAmount = localAmount;
    }

    public EquivalentDollars getEquivalentDollars() {
        return equivalentDollars;
    }

    public void setEquivalentDollars(EquivalentDollars equivalentDollars) {
        this.equivalentDollars = equivalentDollars;
    }

    public Rates getRates() {
        return rates;
    }

    public void setRates(Rates rates) {
        this.rates = rates;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public TransactionSummary getTransactionSummary() {
        return transactionSummary;
    }

    public void setTransactionSummary(TransactionSummary transactionSummary) {
        this.transactionSummary = transactionSummary;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

}
