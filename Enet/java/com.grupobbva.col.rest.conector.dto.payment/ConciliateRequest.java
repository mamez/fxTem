
package com.grupobbva.col.rest.conector.dto.payment;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class ConciliateRequest {

    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("references")
    @Expose
    private List<Reference> references = null;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Reference> getReferences() {
        return references;
    }

    public void setReferences(List<Reference> references) {
        this.references = references;
    }

}
