
package com.grupobbva.col.rest.conector.dto.listBanc;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Description {

    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("value")
    @Expose
    private String value;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
