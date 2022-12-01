package com.grupobbva.col.rest.conector;

import java.util.Map;

import com.grupobbva.col.rest.conector.util.ResponseCode;

public class ResponseEntity<T> {
    private Map<String, String> headers;
    private T entity;
    private ResponseCode responseCode;

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}

	public ResponseCode getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(ResponseCode responseCode) {
		this.responseCode = responseCode;
	}
}
