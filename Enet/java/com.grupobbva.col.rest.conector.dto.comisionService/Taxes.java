
package com.grupobbva.col.rest.conector.dto.comisionService;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Taxes {

    private List<ItemizeTax> itemizeTaxes = new ArrayList<ItemizeTax>();
    private TotalTaxes totalTaxes;

    public List<ItemizeTax> getItemizeTaxes() {
        return itemizeTaxes;
    }

    public void setItemizeTaxes(List<ItemizeTax> itemizeTaxes) {
        this.itemizeTaxes = itemizeTaxes;
    }

    public TotalTaxes getTotalTaxes() {
        return totalTaxes;
    }

    public void setTotalTaxes(TotalTaxes totalTaxes) {
        this.totalTaxes = totalTaxes;
    }

}
