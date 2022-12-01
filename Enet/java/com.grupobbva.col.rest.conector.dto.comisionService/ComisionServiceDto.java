
package com.grupobbva.col.rest.conector.dto.comisionService;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class ComisionServiceDto {

    private List<Datum> data = new ArrayList<Datum>();
    private Pagination pagination;
    
    public List<Datum> getData() {
        return data;
    }
    
    public void setData(List<Datum> data) {
        this.data = data;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

}
