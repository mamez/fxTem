
package com.grupobbva.col.rest.conector.dto.payment;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Fees {

    @SerializedName("totalFees")
    @Expose
    private TotalFees totalFees;
    @SerializedName("itemizeFees")
    @Expose
    private List<ItemizeFee> itemizeFees = new ArrayList<ItemizeFee>();

    public TotalFees getTotalFees() {
        return totalFees;
    }

    public void setTotalFees(TotalFees totalFees) {
        this.totalFees = totalFees;
    }

    public List<ItemizeFee> getItemizeFees() {
        return itemizeFees;
    }

    public void setItemizeFees(List<ItemizeFee> itemizeFees) {
        this.itemizeFees = itemizeFees;
    }

}
