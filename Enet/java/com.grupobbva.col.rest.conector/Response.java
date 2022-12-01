package com.grupobbva.col.rest.conector;

import java.util.Map;

import com.grupobbva.col.rest.conector.util.ResponseCode;

public class Response {
	private ResponseCode response;
	private Map<String, String> heders;
	private String bodyResponse;

	public ResponseCode getResponse() {
		return response;
	}

	public void setResponse(ResponseCode response) {
		this.response = response;
	}

	public String getBodyResponse() {
		return bodyResponse;
	}

	public void setBodyResponse(String bodyResponse) {
		this.bodyResponse = bodyResponse;
	}

	public Map<String, String> getHeders() {
		return heders;
	}

	public void setHeders(Map<String, String> heders) {
		this.heders = heders;
	}

}
