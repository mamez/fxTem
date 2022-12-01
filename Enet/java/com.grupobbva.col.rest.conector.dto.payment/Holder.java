
package com.grupobbva.col.rest.conector.dto.payment;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Holder {

    @SerializedName("holderName")
    @Expose
    private String holderName;
    @SerializedName("identityDocuments")
    @Expose
    private List<IdentityDocument> identityDocuments = new ArrayList<IdentityDocument>();
    @SerializedName("legalPersonType")
    @Expose
    private LegalPersonType legalPersonType;

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public List<IdentityDocument> getIdentityDocuments() {
        return identityDocuments;
    }

    public void setIdentityDocuments(List<IdentityDocument> identityDocuments) {
        this.identityDocuments = identityDocuments;
    }

    public LegalPersonType getLegalPersonType() {
        return legalPersonType;
    }

    public void setLegalPersonType(LegalPersonType legalPersonType) {
        this.legalPersonType = legalPersonType;
    }

}
