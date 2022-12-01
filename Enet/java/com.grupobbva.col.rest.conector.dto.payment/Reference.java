
package com.grupobbva.col.rest.conector.dto.payment;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Reference {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("transactionCycle")
    @Expose
    private Long transactionCycle;
    @SerializedName("value")
    @Expose
    private String value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getTransactionCycle() {
        return transactionCycle;
    }

    public void setTransactionCycle(Long transactionCycle) {
        this.transactionCycle = transactionCycle;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
