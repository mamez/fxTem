
package com.grupobbva.col.rest.conector.dto.payment;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Data {

    @SerializedName("additionalInformation")
    @Expose
    private Object additionalInformation;
    @SerializedName("fees")
    @Expose
    private Fees fees;
    @SerializedName("holder")
    @Expose
    private Holder holder;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("origin")
    @Expose
    private Origin origin;
    @SerializedName("paymentAmount")
    @Expose
    private List<PaymentAmount> paymentAmount = new ArrayList<PaymentAmount>();
    @SerializedName("processDate")
    @Expose
    private String processDate;
    @SerializedName("rates")
    @Expose
    private Rates rates;
    @SerializedName("receiver")
    @Expose
    private Receiver receiver;
    @SerializedName("reimbursementAccount")
    @Expose
    private ReimbursementAccount reimbursementAccount;
    @SerializedName("requestDate")
    @Expose
    private String requestDate;
    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("taxes")
    @Expose
    private Taxes taxes;
    @SerializedName("tradeOperationId")
    @Expose
    private String tradeOperationId;
    @SerializedName("references")
    @Expose
    private List<Reference> references = new ArrayList<Reference>();
    @SerializedName("link")
    @Expose
    private Link link;

    public Object getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(Object additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public Fees getFees() {
        return fees;
    }

    public void setFees(Fees fees) {
        this.fees = fees;
    }

    public Holder getHolder() {
        return holder;
    }

    public void setHolder(Holder holder) {
        this.holder = holder;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public List<PaymentAmount> getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(List<PaymentAmount> paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getProcessDate() {
        return processDate;
    }

    public void setProcessDate(String processDate) {
        this.processDate = processDate;
    }

    public Rates getRates() {
        return rates;
    }

    public void setRates(Rates rates) {
        this.rates = rates;
    }

    public Receiver getReceiver() {
        return receiver;
    }

    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    public ReimbursementAccount getReimbursementAccount() {
        return reimbursementAccount;
    }

    public void setReimbursementAccount(ReimbursementAccount reimbursementAccount) {
        this.reimbursementAccount = reimbursementAccount;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Taxes getTaxes() {
        return taxes;
    }

    public void setTaxes(Taxes taxes) {
        this.taxes = taxes;
    }

    public String getTradeOperationId() {
        return tradeOperationId;
    }

    public void setTradeOperationId(String tradeOperationId) {
        this.tradeOperationId = tradeOperationId;
    }

    public List<Reference> getReferences() {
        return references;
    }

    public void setReferences(List<Reference> references) {
        this.references = references;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

}
