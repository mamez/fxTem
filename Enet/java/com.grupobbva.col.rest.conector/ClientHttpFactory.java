package com.grupobbva.col.rest.conector;

import java.util.HashMap;
import java.util.Map;

import com.grupobbva.col.rest.conector.contract.LogConector;
import com.grupobbva.col.rest.conector.contract.LogImpl;
import com.grupobbva.col.rest.conector.util.HttpMethod;

public class ClientHttpFactory {
	private String url;
	private HttpMethod metodo;
	private Map<String, String> headers;
	private LogConector log;
	private String body;
	private int timeOut = 5000;
	private int readtimeOut = 5000;
	private boolean disableSSL = false;

	public ClientHttpFactory(String url, HttpMethod metodo) {
		this.url = url;
		this.metodo = metodo;
	}

	public ClientHttpFactory headers(Map<String, String> headers) {
		this.headers = headers;
		return this;
	}

	public ClientHttpFactory log(LogConector log) {
		this.log = log;
		return this;
	}

	public ClientHttpFactory body(String body) {
		this.body = body;
		return this;
	}

	public ClientHttpFactory timeOut(int timeOut) {
		this.timeOut = timeOut;
		return this;
	}

	public ClientHttpFactory readtimeOut(int readtimeOut) {
		this.readtimeOut = readtimeOut;
		return this;
	}

	public ClientHttpFactory disableSSL(boolean disable) {
		this.disableSSL = disable;
		return this;
	}

	public HttpClient build() {
		return new HttpClient(this);
	}

	public String getUrl() {
		return url;
	}

	public HttpMethod getMetodo() {
		return metodo;
	}

	public Map<String, String> getHeaders() {
		if (headers == null) {
			headers = new HashMap<String, String>();
		}
		return headers;
	}

	public LogConector getLog() {
		if (log == null) {
			log = new LogImpl();
		}
		return log;
	}

	public String getBody() {
		if (body == null) {
			body = "";
		}
		return body;
	}

	public int getTimeOut() {
		return timeOut;
	}

	public int getReadtimeOut() {
		return readtimeOut;
	}

	public boolean isDisableSSL() {
		return disableSSL;
	}
}
