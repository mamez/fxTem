
package com.grupobbva.col.rest.conector.dto.payment;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class PaymentsServiceRequest {

    @SerializedName("paymentAmount")
    @Expose
    private List<PaymentAmount> paymentAmount = new ArrayList<PaymentAmount>();
    @SerializedName("fees")
    @Expose
    private Fees fees;
    @SerializedName("taxes")
    @Expose
    private Taxes taxes;
    @SerializedName("reimbursementAccount")
    @Expose
    private ReimbursementAccount reimbursementAccount;
    @SerializedName("origin")
    @Expose
    private Origin origin;
    @SerializedName("holder")
    @Expose
    private Holder holder;
    @SerializedName("receiver")
    @Expose
    private Receiver receiver;
    @SerializedName("reference")
    @Expose
    private List<Reference> reference = new ArrayList<Reference>();
    @SerializedName("additionalInformation")
    @Expose
    private AdditionalInformation additionalInformation;

    public List<PaymentAmount> getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(List<PaymentAmount> paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Fees getFees() {
        return fees;
    }

    public void setFees(Fees fees) {
        this.fees = fees;
    }

    public Taxes getTaxes() {
        return taxes;
    }

    public void setTaxes(Taxes taxes) {
        this.taxes = taxes;
    }

    public ReimbursementAccount getReimbursementAccount() {
        return reimbursementAccount;
    }

    public void setReimbursementAccount(ReimbursementAccount reimbursementAccount) {
        this.reimbursementAccount = reimbursementAccount;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public Holder getHolder() {
        return holder;
    }

    public void setHolder(Holder holder) {
        this.holder = holder;
    }

    public Receiver getReceiver() {
        return receiver;
    }

    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    public List<Reference> getReference() {
        return reference;
    }

    public void setReference(List<Reference> reference) {
        this.reference = reference;
    }

    public AdditionalInformation getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(AdditionalInformation additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

}
