
package com.grupobbva.col.rest.conector.dto.comisionService;

import java.math.BigDecimal;
import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class ReimbursementAccount {

    private String account;
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
