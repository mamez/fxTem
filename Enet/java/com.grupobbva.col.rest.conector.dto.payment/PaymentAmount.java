
package com.grupobbva.col.rest.conector.dto.payment;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class PaymentAmount {

    @SerializedName("paymentType")
    @Expose
    private PaymentType paymentType;
    @SerializedName("amount")
    @Expose
    private Amount amount;

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

}
