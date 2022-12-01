
package com.grupobbva.col.rest.conector.dto.listBanc;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BanckAch {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("descriptions")
    @Expose
    private List<Description> descriptions = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Description> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<Description> descriptions) {
        this.descriptions = descriptions;
    }

}
