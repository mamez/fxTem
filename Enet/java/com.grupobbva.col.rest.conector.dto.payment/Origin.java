
package com.grupobbva.col.rest.conector.dto.payment;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Origin {

    @SerializedName("isExternal")
    @Expose
    private Boolean isExternal;
    @SerializedName("bank")
    @Expose
    private Bank bank;

    public Boolean getIsExternal() {
        return isExternal;
    }

    public void setIsExternal(Boolean isExternal) {
        this.isExternal = isExternal;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

}
