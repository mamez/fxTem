package com.grupobbva.col.servicios;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import com.ibm.dse.base.DSEInvalidArgumentException;
import com.ibm.dse.base.DSEObjectNotFoundException;

public class OpGestion_xhr_rs7 extends OpGestion1_rs7 {
	private ObjectMapper objectMapper;
	private static final String MENS_ERROR = "Error";
	private static final String MENS_ERROR_JSON = "JsonMappingException";
	private static final String MENS_DES_ERROR_JSON = "Error al mapear el json to class";
	private static final String MENS_ERROR_EXPE = "IOException";
	private static final String MENS_DES_FUE_ERROR_JSON = "Error a encontrar fuente json";
	private static final String MENS_ERROR_EXC_JSON = "JsonGenerationException";
	private static final String MENS_DES_ERROR_EXC_JSON = "Error al generar el json to class";
	
	
	

	public OpGestion_xhr_rs7() {
		super();
		initConfig();
	}

	public OpGestion_xhr_rs7(String anOperationName) throws java.io.IOException {
		super(anOperationName);
		initConfig();
	}

	public OpGestion_xhr_rs7(String anOperationName, com.ibm.dse.base.Context aParentContext)
			throws java.io.IOException, com.ibm.dse.base.DSEInvalidRequestException {
		super(anOperationName, aParentContext);
		initConfig();
	}

	public OpGestion_xhr_rs7(String anOperationName, String aParentContext) throws java.io.IOException,
			com.ibm.dse.base.DSEObjectNotFoundException, com.ibm.dse.base.DSEInvalidRequestException {
		super(anOperationName, aParentContext);
		initConfig();
	}

	private void initConfig() {
		objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(SerializationConfig.Feature.AUTO_DETECT_IS_GETTERS, true);
	}

	protected <T> T getObjectValue(String inputFormat, Class<T> entity) throws DSEObjectNotFoundException {
		try {
			String jsonValue = (String) getValueAt(inputFormat);
			return objectMapper.readValue(jsonValue, entity);
		} catch (JsonParseException e) {
			throw new DSEObjectNotFoundException(MENS_ERROR, "Error al trasformat el json to class", "JsonParseException");
		} catch (JsonMappingException e) {
			throw new DSEObjectNotFoundException(MENS_ERROR, MENS_DES_ERROR_JSON, MENS_ERROR_JSON);
		} catch (IOException e) {
			throw new DSEObjectNotFoundException(MENS_ERROR, MENS_DES_FUE_ERROR_JSON, MENS_ERROR_EXPE);
		}
	}

	protected void setObjectValue(String ouputFormat, Object entity)
			throws DSEObjectNotFoundException, DSEInvalidArgumentException {
		try {
			String jsonValue = objectMapper.writeValueAsString(entity);
			setValueAt(ouputFormat, jsonValue);
		} catch (JsonGenerationException e) {
			throw new DSEObjectNotFoundException(MENS_ERROR, MENS_DES_ERROR_EXC_JSON,MENS_ERROR_EXC_JSON);
		} catch (JsonMappingException e) {
			throw new DSEObjectNotFoundException(MENS_ERROR, MENS_DES_ERROR_JSON, MENS_ERROR_JSON);
		} catch (IOException e) {
			throw new DSEObjectNotFoundException(MENS_ERROR, MENS_DES_FUE_ERROR_JSON, MENS_ERROR_EXPE);
		}
	}	
	
		protected void setObjectValue(String ouputFormat, List<Object> entity) throws DSEObjectNotFoundException, DSEInvalidArgumentException {		
		try {			
		String jsonValue = objectMapper.writeValueAsString(entity);
	    setValueAt(ouputFormat, jsonValue);
	     }catch (JsonGenerationException e) {
		   throw new DSEObjectNotFoundException(MENS_ERROR, MENS_DES_ERROR_EXC_JSON, MENS_ERROR_EXC_JSON);
		    }catch (JsonMappingException e) {
		    throw new DSEObjectNotFoundException(MENS_ERROR, MENS_DES_ERROR_JSON, MENS_ERROR_JSON);
		   }catch (IOException e) {
		   			throw new DSEObjectNotFoundException(MENS_ERROR, MENS_DES_FUE_ERROR_JSON, MENS_ERROR_EXPE);
							}
								}	




	protected void setObjectValue(String ouputFormat, Map<String, Object> entity)
			throws DSEObjectNotFoundException, DSEInvalidArgumentException {
		try {
			String jsonValue = objectMapper.writeValueAsString(entity);
			setValueAt(ouputFormat, jsonValue);
		} catch (JsonGenerationException e) {
			throw new DSEObjectNotFoundException(MENS_ERROR, MENS_DES_ERROR_EXC_JSON,MENS_ERROR_EXC_JSON);
		} catch (JsonMappingException e) {
			throw new DSEObjectNotFoundException(MENS_ERROR, MENS_DES_ERROR_JSON, MENS_ERROR_JSON);
		} catch (IOException e) {
			throw new DSEObjectNotFoundException(MENS_ERROR, MENS_DES_FUE_ERROR_JSON, MENS_ERROR_EXPE);
		}
	}
	
	protected String getValueAtOfdefault(String literal, String defaultValue) throws DSEObjectNotFoundException {
		
		if (getValueAt(literal) != null) {
			Object value=getValueAt(literal);
			if(value instanceof Double) {
				double valorDouble=0;
				valorDouble= (Double) getValueAt(literal);
				return String.valueOf(valorDouble);
			}else {
				return (String) getValueAt(literal);
			}
				
			
		}else {
			return defaultValue;
		}
		
	}

}
