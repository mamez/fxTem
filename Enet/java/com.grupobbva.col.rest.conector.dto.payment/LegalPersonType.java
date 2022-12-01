
package com.grupobbva.col.rest.conector.dto.payment;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class LegalPersonType {

    @SerializedName("participantType")
    @Expose
    private String participantType;

    public String getParticipantType() {
        return participantType;
    }

    public void setParticipantType(String participantType) {
        this.participantType = participantType;
    }

}
