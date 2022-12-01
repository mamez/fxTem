package com.grupobbva.col.rest.conector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.grupobbva.col.rest.conector.contract.LogConector;
import com.grupobbva.col.rest.conector.util.HttpClientException;
import com.grupobbva.col.rest.conector.util.HttpMethod;
import com.grupobbva.col.rest.conector.util.ResponseCode;

public class HttpClient {
	
	private static final String UTF_8 = "UTF-8";
	private String url;
	private HttpMethod metodo;
	private Map<String, String> headers;
	private LogConector log;
	private String body;
	private int timeOut;
	private int readtimeOut;
	private boolean disableSSL;

	private Response response;
	private HttpURLConnection conn;

	public HttpClient(ClientHttpFactory factory) {
		this.url = factory.getUrl();
		this.metodo = factory.getMetodo();
		this.headers = factory.getHeaders();
		this.log = factory.getLog();
		this.body = factory.getBody();
		this.readtimeOut = factory.getTimeOut();
		this.timeOut = factory.getReadtimeOut();
		this.disableSSL = factory.isDisableSSL();
	}

	public void executeConection() throws HttpClientException {
		URL urlObj;
		try {
			urlObj = new URL(url);
			this.conn = (HttpURLConnection) urlObj.openConnection();
			this.conn.setRequestMethod(metodo.name());
			this.conn.setConnectTimeout(timeOut);
			this.conn.setReadTimeout(readtimeOut);
			this.conn.setRequestProperty("Charset", UTF_8);
			setAditionalHeaders();
			switch (metodo) {
			case GET:
				doGet();
				break;
			case POST:
				doPost();
				break;
			default:
				doPost();
			}
		} catch (MalformedURLException e) {
			log.error("HttpClient -> executeConection " + e.getMessage());
			throw new HttpClientException("HttpClient -> executeConection: error al procesar url " + e.getMessage());
		} catch (IOException e) {
			log.error("HttpClient -> executeConection " + e.getMessage());
			throw new HttpClientException("HttpClient -> executeConection: error al ejecutar accion " + e.getMessage());
		} catch (HttpClientException e) {
			throw e;
		}
	}

	private void doGet() throws HttpClientException {
		try {
			conn.setRequestMethod(metodo.name());
			if (!headers.containsKey("Accept")) {
				conn.setRequestProperty("Accept", "application/json");
			}
			response = new Response();
			ResponseCode responseCode = ResponseCode.valueOfResponse(conn.getResponseCode());
			response.setResponse(responseCode);

			if (responseCode == ResponseCode.OK) {
				response.setBodyResponse(getResponseBody("OK"));
			}

			if (responseCode.getCode() > 299) {
				response.setBodyResponse(getResponseBody("ERROR"));
			}
			response.setHeders(getAllHeaders());
		} catch (ProtocolException e) {
			log.error("HttpClient -> doPost " + e.getMessage());
			throw new HttpClientException("HttpClient -> doPost: error al ejecutar protocolo " + e.getMessage());
		} catch (IOException e) {
			log.error("HttpClient -> doPost " + e.getMessage());
			throw new HttpClientException("HttpClient -> doPost: error al ejecutar accion " + e.getMessage());
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
	}

	private void doPost() throws HttpClientException {
		try {
			conn.setRequestMethod(metodo.name());
			conn.setDoOutput(true);
			if (!headers.containsKey("Content-Type")) {
				conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
			}
			OutputStream os = conn.getOutputStream();
			os.write(body.getBytes(UTF_8));
			os.flush();
			os.close();

			response = new Response();
			ResponseCode responseCode = ResponseCode.valueOfResponse(conn.getResponseCode());
			response.setResponse(responseCode);

			if (responseCode == ResponseCode.OK || responseCode == ResponseCode.CREATED) {
				response.setBodyResponse(getResponseBody("OK"));
			}

			if (responseCode.getCode() > 299) {
				response.setBodyResponse(getResponseBody("ERROR"));
			}
			response.setHeders(getAllHeaders());

		} catch (ProtocolException e) {
			log.error("HttpClient -> doget " + e.getMessage());
			throw new HttpClientException("HttpClient -> doget: error al ejecutar protocolo " + e.getMessage());
		} catch (IOException e) {
			log.error("HttpClient -> doget " + e.getMessage());
			throw new HttpClientException("HttpClient -> doget: error al ejecutar accion " + e.getMessage());
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
	}

	private String getResponseBody(String errorType) throws IOException {
		BufferedReader br = null;
		String bodyResponse = "";
		if (errorType.equals("OK")) {
			br = new BufferedReader(new InputStreamReader((conn.getInputStream()),UTF_8));

		} else {
			br = new BufferedReader(new InputStreamReader((conn.getErrorStream()),UTF_8));
		}
		String output;
		while ((output = br.readLine()) != null) {
			bodyResponse = bodyResponse.concat(output);
		}
		log.debug("Respuesta del servicio " + errorType + ": " + bodyResponse);
		return bodyResponse;
	}

	private void setAditionalHeaders() {
		for (Map.Entry<String, String> in : headers.entrySet()) {
			log.debug("Agregando heder a request " + in + " : " + in.getValue());
			conn.setRequestProperty(in.getKey(), in.getValue());
		}
	}

	private Map<String, String> getAllHeaders() {
		Map<String, String> response = new HashMap<String, String>();
		Map<String, List<String>> headers = this.conn.getHeaderFields();
		for (Map.Entry<String, List<String>> in : headers.entrySet()) {
			response.put(in.getKey(), combertList(in.getValue()));
		}
		return response;
	}

	private String combertList(List<String> headers) {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		while (i < headers.size() - 1) {
			sb.append(headers.get(i));
			sb.append(",");
			i++;
		}
		sb.append(headers.get(i));
		String res = sb.toString();
		return res;
	}

	public Response getResponse() {
		return response;
	}
}
