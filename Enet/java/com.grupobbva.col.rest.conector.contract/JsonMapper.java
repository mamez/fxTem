package com.grupobbva.col.rest.conector.contract;

import com.grupobbva.col.rest.conector.util.HttpClientException;

public interface JsonMapper {
    public <T> T mapToObject(String response, Class<T> clazz) throws HttpClientException;
    public String mapToRequest(Object clazz) throws HttpClientException;
}
