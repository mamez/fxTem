package com.grupobbva.col.rest.conector.contract;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.grupobbva.col.rest.conector.util.HttpClientException;

public class DefaultMapper implements JsonMapper {

	@Override
	public <T> T mapToObject(String response, Class<T> clazz) throws HttpClientException {
		try {
			Gson gson = new GsonBuilder().create();
			T classRespnse = gson.fromJson(response, clazz);
			return classRespnse;
		} catch (Exception e) {
			throw new HttpClientException("Error al mapear " + e.getMessage());
		}
	}

	@Override
	public String mapToRequest(Object obj) throws HttpClientException {
		try {
			Gson gson = new GsonBuilder().create();
			return gson.toJson(obj);
		} catch (Exception e) {
			throw new HttpClientException("Error al mapear " + e.getMessage());
		}
	}

}
