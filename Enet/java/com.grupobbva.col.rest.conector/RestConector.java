package com.grupobbva.col.rest.conector;

import java.util.HashMap;
import java.util.Map;

import com.grupobbva.col.rest.conector.contract.DefaultMapper;
import com.grupobbva.col.rest.conector.contract.JsonMapper;
import com.grupobbva.col.rest.conector.contract.LogConector;
import com.grupobbva.col.rest.conector.util.HttpClientException;
import com.grupobbva.col.rest.conector.util.HttpMethod;
import com.grupobbva.col.rest.conector.util.ResponseCode;

public class RestConector {
	private HttpClient client;
	private JsonMapper mapper;
	private int timeOut = 10000;
	private int readtimeOut = 10000;
	private LogConector log;
	private boolean disableSSL = false;
	private Map<String, String> headers=null;

	public RestConector() {
		this.mapper = new DefaultMapper();
		this.headers= new HashMap<String, String>();
	}
	
	public RestConector(Map<String, String> headers) throws HttpClientException{
		this.mapper = new DefaultMapper();
		this.headers=headers;
		if(headers==null) {
			throw new HttpClientException("Error heders null");
		}
	}

	public RestConector(JsonMapper mapper ) {
		this.mapper = mapper;
		this.headers= new HashMap<String, String>();
	}

	public RestConector(JsonMapper mapper,Map<String, String> headers) throws HttpClientException{
		this.mapper = mapper;
		this.headers=headers;
		if(headers==null) {
			throw new HttpClientException("Error heders null");
		}
	}

	/**
	 * <p>
	 * Peticion GET de servicios RestConector
	 * </p>
	 * 
	 * @param <T>
	 * @param url          Url de consulta del servicio
	 * @param responseType clase a la cual sera convertida la peticion
	 * @return Responde clase generica convertida
	 * @throws HttpClientException Exepcion de peticion
	 */
	public <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType) throws HttpClientException {
		return blindClinetHttp(url, HttpMethod.GET, responseType, headers, null);
	}

	/**
	 * 
	 * @param url          Url de consulta del servicio
	 * @param responseType clase a la cual sera convertida la peticion
	 * @param uriVariables Parametro de consulta de la peticion
	 * @return Responde clase generica convertida
	 * @throws HttpClientException Exepcion de peticion
	 */
	public <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType, Map<String, String> uriVariables)
			throws HttpClientException {
		url = buildUrl(url, uriVariables);
		return blindClinetHttp(url, HttpMethod.GET, responseType, headers, null);
	}

	/**
	 * 
	 * @param <T>
	 * @param url          Url de consulta del servicio
	 * @param headers      permite enviar informacin adicional junto a una peticion
	 *                     o respuesta
	 * @param responseType clase a la cual sera convertida la peticion
	 * @return Responde clase generica convertida
	 * @throws HttpClientException Exepcion de peticion
	 */
	public <T> ResponseEntity<T> getForEntity(String url, Map<String, String> headers, Class<T> responseType)
			throws HttpClientException {
		return blindClinetHttp(url, HttpMethod.GET, responseType, headers, null);
	}

	/**
	 * <p>
	 * Peticion GET de servicios RestConector
	 * </p>
	 * 
	 * @param url          Url de consulta del servicio
	 * @param headers      Permite enviar informacion adicional frente a una
	 *                     peticion o respuesta
	 * @param uriVariables Parametro de consulta de la peticion
	 * @param responseType clase a la cual sera convertida la peticion
	 * @return Responde clase generica convertida
	 * @throws HttpClientException Exepcion de peticion
	 */

	public <T> ResponseEntity<T> getForEntity(String url, Map<String, String> headers, Map<String, String> uriVariables,
			Class<T> responseType) throws HttpClientException {
		url = buildUrl(url, uriVariables);
		return blindClinetHttp(url, HttpMethod.GET, responseType, headers, null);
	}

	/**
	 * 
	 * @param <T>
	 * @param url          Url de consulta del servicio
	 * @param responseType clase a la cual sera convertida la peticion
	 * @param body         Objeto de envio al servicio
	 * @return Responde clase generica convertida
	 * @throws HttpClientException Exepcion de peticion
	 */
	public <T> ResponseEntity<T> postForEntity(String url, Class<T> responseType, Object body)
			throws HttpClientException {
		String bodyStr = null;
		if (body != null) {
			bodyStr = this.mapper.mapToRequest(body);
		}
		return blindClinetHttp(url, HttpMethod.POST, responseType, headers, bodyStr);
	}

	/**
	 * 
	 * @param url          Url de consulta del servicio
	 * @param responseType clase a la cual sera convertida la peticion
	 * @param uriVariables Parametro de consulta de la peticion
	 * @param body         Objeto de envio al servicio
	 * @return Responde clase generica convertida
	 * @throws HttpClientException Exepcion de peticion
	 */
	public <T> ResponseEntity<T> postForEntity(String url, Class<T> responseType, Map<String, String> uriVariables,
			Object body) throws HttpClientException {

		String bodyStr = null;
		if (body != null) {
			bodyStr = this.mapper.mapToRequest(body);
		}
		url = buildUrl(url, uriVariables);
		return blindClinetHttp(url, HttpMethod.POST, responseType, headers, bodyStr);

	}

	/**
	 * 
	 * @param <T>
	 * @param url          Url de consulta del servicio
	 * @param headers      Permite enviar informacion adicional frente a una
	 *                     peticion o respuesta
	 * @param responseType clase a la cual sera convertida la peticion
	 * @param body         Objeto de envio al servicio
	 * @return Responde clase generica convertida
	 * @throws HttpClientException Exepcion de peticion
	 */
	public <T> ResponseEntity<T> postForEntity(String url, Map<String, String> headers, Class<T> responseType,
			Object body) throws HttpClientException {
		String bodyStr = null;
		if (body != null) {
			bodyStr = this.mapper.mapToRequest(body);
		}
		return blindClinetHttp(url, HttpMethod.POST, responseType, headers, bodyStr);
	}

	/**
	 * 
	 * @param url          Url de consulta del servicio
	 * @param headers      Permite enviar informacion adicional frente a una
	 *                     peticion o respuesta
	 * @param uriVariables Parametro de consulta de la peticion
	 * @param responseType clase a la cual sera convertida la peticion
	 * @param body         Objeto de envio al servicio
	 * @return Responde clase generica convertida
	 * @throws HttpClientException Exepcion de peticion
	 */
	public <T> ResponseEntity<T> postForEntity(String url, Map<String, String> headers,
			Map<String, String> uriVariables, Class<T> responseType, Object body) throws HttpClientException {
		String bodyStr = null;
		if (body != null) {
			bodyStr = this.mapper.mapToRequest(body);
		}
		url = buildUrl(url, uriVariables);
		return blindClinetHttp(url, HttpMethod.POST, responseType, headers, bodyStr);
	}

	@SuppressWarnings("unchecked")
	private <T> ResponseEntity<T> blindClinetHttp(String url, HttpMethod metodo, Class<T> responseType,
			Map<String, String> headers, String body) throws HttpClientException {
		try {
			if (headers == null) {
				headers = new HashMap<String, String>();
			}
			for(Map.Entry<String, String> in : headers.entrySet()) {
				this.headers.put(in.getKey(), in.getValue());
			}
			
			this.client = new ClientHttpFactory(url, metodo).timeOut(this.timeOut).readtimeOut(this.readtimeOut)
					.log(this.log).disableSSL(isDisableSSL()).headers(headers).body(body).build();
			this.client.executeConection();
			Response responseService = this.client.getResponse();
			ResponseCode responseCode=responseService.getResponse();
			ResponseEntity<T> responseEntity = ResponseEntity.class.newInstance();
			responseEntity.setHeaders(responseService.getHeders());
			responseEntity.setResponseCode(responseCode);
			switch (responseCode) {
			case OK:
				responseEntity.setEntity(this.mapper.mapToObject(responseService.getBodyResponse(), responseType));
				break;
			case NO_CONTENT:
				responseEntity.setEntity(responseType.newInstance());
				break;
			case ACEPTED:
				responseEntity.setEntity(responseType.newInstance());
				break;
			case CREATED:
				if(responseService.getBodyResponse() != null && (!responseService.getBodyResponse().trim().equals(""))) {
					responseEntity.setEntity(this.mapper.mapToObject(responseService.getBodyResponse(), responseType));
				}else {
					responseEntity.setEntity(responseType.newInstance());
				}
				break;
			default:
				throw new HttpClientException("Error:"+responseCode.getCode()+"@"+"MSG:"+responseService.getBodyResponse());
			}
			
			
			
			
			return responseEntity;
		} catch (InstantiationException e) {
			throw new HttpClientException("Error al iniciar clase : " + e.getMessage());
		} catch (IllegalAccessException e) {
			throw new HttpClientException("Error al acceder clase : " + e.getMessage());
		} catch (HttpClientException e) {
			throw e;
		} catch (Exception e) {
			throw new HttpClientException("Error no inesperado : " + e.getMessage());
		}
	}

	private String buildUrl(String url, Map<String, String> params) throws HttpClientException {
		try {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				url = url.replace("{" + entry.getKey() + "}", entry.getValue());
			}
			return url;
		} catch (Exception e) {
			throw new HttpClientException("Error al formatear las variables " + e.getMessage());
		}
	}

	public int getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
	}

	public int getReadtimeOut() {
		return readtimeOut;
	}

	public void setReadtimeOut(int readtimeOut) {
		this.readtimeOut = readtimeOut;
	}

	public LogConector getLog() {
		return log;
	}

	public void setLog(LogConector log) {
		this.log = log;
	}

	public boolean isDisableSSL() {
		return disableSSL;
	}

	public void setDisableSSL(boolean disableSSL) {
		this.disableSSL = disableSSL;
	}

}
