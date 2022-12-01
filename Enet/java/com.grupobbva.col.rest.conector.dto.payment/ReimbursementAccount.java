
package com.grupobbva.col.rest.conector.dto.payment;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class ReimbursementAccount {

    @SerializedName("account")
    @Expose
    private String account;
    @SerializedName("bank")
    @Expose
    private Bank bank;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

}
