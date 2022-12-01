
package com.grupobbva.col.rest.conector.dto.operativeConditions;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class ItemizeFee {

    @SerializedName("feeAmount")
    @Expose
    private FeeAmount feeAmount;

    public FeeAmount getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(FeeAmount feeAmount) {
        this.feeAmount = feeAmount;
    }

}
