package com.grupobbva.col.rest.conector.om;

import com.grupobbva.col.rest.conector.ResponseEntity;
import com.grupobbva.col.rest.conector.RestConector;
import com.grupobbva.col.rest.conector.dto.dynamicCurrency.DinamicCurrencyRequest;
import com.grupobbva.col.rest.conector.dto.dynamicCurrency.DinamicCurrencyResponse;
import com.grupobbva.col.rest.conector.dto.payment.PaymentsServiceRequest;
import com.grupobbva.col.rest.conector.dto.payment.PaymentsServiceResponse;
import com.grupobbva.col.rest.conector.util.HttpClientException;
import com.grupobbva.col.rest.conector.util.ResponseCode;
import com.grupobbva.ii.sf.base.BbvaException;
import com.ibm.dse.base.DSEInvalidArgumentException;
import com.ibm.dse.base.DSEObjectNotFoundException;
import com.ibm.dse.base.Trace;

public class OMDynamicCurrency extends  OMTrisConector {
	public OMDynamicCurrency() {
		super();
	}

	public OMDynamicCurrency(String anOperationName) throws java.io.IOException {
		super(anOperationName);
	}

	public OMDynamicCurrency(String anOperationName, com.ibm.dse.base.Context aParentContext)
			throws java.io.IOException, com.ibm.dse.base.DSEInvalidRequestException {
		super(anOperationName, aParentContext);
	}

	public OMDynamicCurrency(String anOperationName, String aParentContext) throws java.io.IOException,
			com.ibm.dse.base.DSEObjectNotFoundException, com.ibm.dse.base.DSEInvalidRequestException {
		super(anOperationName, aParentContext);
	}
	
	public void execute() throws BbvaException {
		postDynamic();
	}
	
	private void postDynamic() {
		Trace.trace(Trace.Debug, "", " Inicio postDynamic()");
		String URL;
		try {
			URL = (String) getValueAt("ConectorCiri.RestClinet.endpoint");
			String endPoint = URL + "/services/V1/foreignCurrenciesExchange/dynamic-currency-pricing";
			RestConector conector = getRestConector();
			DinamicCurrencyRequest dinamicDto = new DinamicCurrencyRequest();
			dinamicDto =(DinamicCurrencyRequest)getValueAt("Entrada.postDynamic.dynamicObject");
			ResponseEntity<DinamicCurrencyResponse> response = conector.postForEntity(endPoint, DinamicCurrencyResponse.class,dinamicDto);
			if (response.getResponseCode() == ResponseCode.OK ||response.getResponseCode() == ResponseCode.CREATED) {
				DinamicCurrencyResponse dinacmicResponseDto = response.getEntity();
				setValueAt("Salida.dynamicObject", dinacmicResponseDto);
			}
			
		} catch (DSEObjectNotFoundException e) {
			Trace.trace(Trace.VTF, "", "Error al leer propiedad : " + e.getMessage());
		} catch (HttpClientException e) {
			Trace.trace(Trace.VTF, "", "Error al realizar consumo rest : " + e.getMessage());
		} catch (DSEInvalidArgumentException e) {
			Trace.trace(Trace.VTF, "", "Error al conectar propiedad : " + e.getMessage());
		}
		
		
	}

}
