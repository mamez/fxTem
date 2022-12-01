
package com.grupobbva.col.rest.conector.dto.operativeConditions;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class OperativeConditionsRequest {

    @SerializedName("operationType")
    @Expose
    private String operationType;
    
    @SerializedName("businessId")
    @Expose
    private String businessId;
    
    @SerializedName("amountNegotiated")
    @Expose
    private AmountNegotiated amountNegotiated;
    
    @SerializedName("operationNumber")
    @Expose
    private String operationNumber;

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public AmountNegotiated getAmountNegotiated() {
        return amountNegotiated;
    }

    public void setAmountNegotiated(AmountNegotiated amountNegotiated) {
        this.amountNegotiated = amountNegotiated;
    }

    public String getOperationNumber() {
        return operationNumber;
    }

    public void setOperationNumber(String operationNumber) {
        this.operationNumber = operationNumber;
    }

}
