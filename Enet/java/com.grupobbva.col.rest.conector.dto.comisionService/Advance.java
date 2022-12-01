
package com.grupobbva.col.rest.conector.dto.comisionService;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Advance {

    private String id;
    private NegotiatedAmount negotiatedAmount;


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public NegotiatedAmount getNegotiatedAmount() {
        return negotiatedAmount;
    }


    public void setNegotiatedAmount(NegotiatedAmount negotiatedAmount) {
        this.negotiatedAmount = negotiatedAmount;
    }

}
