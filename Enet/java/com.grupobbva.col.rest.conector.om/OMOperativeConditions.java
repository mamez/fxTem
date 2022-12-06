package com.grupobbva.col.rest.conector.om;

import java.math.BigDecimal;

import com.grupobbva.col.rest.conector.ResponseEntity;
import com.grupobbva.col.rest.conector.RestConector;
import com.grupobbva.col.rest.conector.dto.comisionService.ComisionServiceDto;
import com.grupobbva.col.rest.conector.dto.operativeConditions.AmountNegotiated;
import com.grupobbva.col.rest.conector.dto.operativeConditions.OperativeConditionsRequest;
import com.grupobbva.col.rest.conector.dto.operativeConditions.OperativeConditionsResponse;
import com.grupobbva.col.rest.conector.util.HttpClientException;
import com.grupobbva.col.rest.conector.util.ResponseCode;
import com.grupobbva.ii.sf.base.BbvaException;
import com.ibm.dse.base.DSEInvalidArgumentException;
import com.ibm.dse.base.DSEObjectNotFoundException;
import com.ibm.dse.base.Trace;

public class OMOperativeConditions extends OMTrisConector {

	public OMOperativeConditions() {
		super();
	}

	public OMOperativeConditions(String anOperationName) throws java.io.IOException {
		super(anOperationName);
	}

	public OMOperativeConditions(String anOperationName, com.ibm.dse.base.Context aParentContext)
			throws java.io.IOException, com.ibm.dse.base.DSEInvalidRequestException {
		super(anOperationName, aParentContext);
	}

	public OMOperativeConditions(String anOperationName, String aParentContext) throws java.io.IOException,
			com.ibm.dse.base.DSEObjectNotFoundException, com.ibm.dse.base.DSEInvalidRequestException {
		super(anOperationName, aParentContext);
	}

	public void execute() throws BbvaException {
		try {
			String metodoParameter=(String) getValueAt("Entrada.metodo");
			MetodosConditions metodo = MetodosConditions.valueOf(metodoParameter);
			
			switch (metodo) {
			case POST_OPERATIVE_CONDITIONS:
				postOperativeCondit();
				break;
			case GET_COMISION_SERVE:
				getComisionServe();
				break;
			default:	
				postOperativeCondit();
			}

		} catch (DSEObjectNotFoundException e) {
			Trace.trace(Trace.VTF, "", "Error al leer propiedad : " + e.getMessage());
		} catch (HttpClientException e) {
			Trace.trace(Trace.VTF, "", "Error al realizar consumo rest : " + e.getMessage());
		} catch (DSEInvalidArgumentException e) {
			Trace.trace(Trace.VTF, "", "Error al conectar propiedad : " + e.getMessage());
		}

	}

	private void postOperativeCondit() throws DSEObjectNotFoundException, HttpClientException, DSEInvalidArgumentException {

		String URL = (String) getValueAt("ConectorCiri.RestClinet.endpoint");
		String endPoint = URL + "/services/V1/ComisionService/operativeConditions";
		RestConector conector = getRestConector();
		OperativeConditionsRequest operativeConditionsDto = new OperativeConditionsRequest();
		operativeConditionsDto.setOperationType((String) getValueAt("Entrada.postOperativeCondit.operationType"));
		operativeConditionsDto.setBusinessId((String) getValueAt("Entrada.postOperativeCondit.businessId"));
		operativeConditionsDto.setOperationNumber((String) getValueAt("Entrada.postOperativeCondit.operationNumber"));
		AmountNegotiated amountNegotiated = new AmountNegotiated();
		amountNegotiated.setAmount((BigDecimal)getValueAt("Entrada.postOperativeCondit.amountNegotiated.amount"));
		amountNegotiated.setCurrency((String) getValueAt("Entrada.postOperativeCondit.amountNegotiated.currency"));
        operativeConditionsDto.setAmountNegotiated(amountNegotiated);

		ResponseEntity<OperativeConditionsResponse> response = conector.postForEntity(endPoint, OperativeConditionsResponse.class,
				operativeConditionsDto);

		if (response.getResponseCode() == ResponseCode.OK || response.getResponseCode() == ResponseCode.CREATED) {
			OperativeConditionsResponse operativeConditions = response.getEntity();
			setValueAt("Salida.operativeConditionsObject", operativeConditions);
		}

	}
	
	private void getComisionServe() throws DSEObjectNotFoundException, HttpClientException, DSEInvalidArgumentException {
		Trace.trace(Trace.VTF, "", "inicio getComisionServe (): " );
		String URL = (String) getValueAt("ConectorCiri.RestClinet.endpoint");
		String nit=(String)getValueAt("Entrada.comisionService.nit");
		String page=(String)getValueAt("Entrada.comisionService.page");
		String fromDate=(String)getValueAt("Entrada.comisionService.fromDate");
		String toDate=(String)getValueAt("Entrada.comisionService.toDate");
		String endPoint = URL + "/services/V1/ComisionService/operations?pageSize=10";
		               
		StringBuilder urlBuild =new StringBuilder(endPoint)
				.append("&paginationKey=").append(page);
		if(!nit.equalsIgnoreCase("NA")) {
			urlBuild.append("&customerId=").append(nit);
		}
	    
		if(!fromDate.equalsIgnoreCase("NA")) {
			urlBuild.append("&fromOperationDate=").append(fromDate)
			.append("&toOperationDate=").append(toDate);
		}
			
		RestConector conector = getRestConector();
		ResponseEntity<ComisionServiceDto> response = conector.getForEntity(urlBuild.toString(), ComisionServiceDto.class);

		if (response.getResponseCode() == ResponseCode.OK) {
			ComisionServiceDto comisionService = response.getEntity();
			setValueAt("Salida.comisionServiceObject", comisionService);
		}else if(response.getResponseCode() == ResponseCode.NO_CONTENT || response.getResponseCode() == ResponseCode.SERVER_ERROR || response.getResponseCode() == ResponseCode.CONFICT ) {
			setValueAt("Salida.comisionServiceObject", new ComisionServiceDto());
		}

	}
	
	
}
