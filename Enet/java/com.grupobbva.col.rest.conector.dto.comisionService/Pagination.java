
package com.grupobbva.col.rest.conector.dto.comisionService;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Pagination {

    private Links links;
    private Long page;
    private Long totalPages;
    private Long totalElements;
    private Long pageSize;

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Long totalPages) {
        this.totalPages = totalPages;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

}
