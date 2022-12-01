
package com.grupobbva.col.rest.conector.dto.payment;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class IdentityDocument {

    @SerializedName("documentNumber")
    @Expose
    private String documentNumber;
    @SerializedName("documentType")
    @Expose
    private DocumentType documentType;

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

}
