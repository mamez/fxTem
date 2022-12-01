package com.grupobbva.col.rest.conector.om;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.grupobbva.col.rest.conector.ResponseEntity;
import com.grupobbva.col.rest.conector.RestConector;
import com.grupobbva.col.rest.conector.dto.payment.ConciliateRequest;
import com.grupobbva.col.rest.conector.dto.payment.ConciliateResponse;
import com.grupobbva.col.rest.conector.dto.payment.PaymentsServiceRequest;
import com.grupobbva.col.rest.conector.dto.payment.PaymentsServiceResponse;
import com.grupobbva.col.rest.conector.dto.payment.Reason;
import com.grupobbva.col.rest.conector.dto.payment.Reference;
import com.grupobbva.col.rest.conector.dto.payment.Status;
import com.grupobbva.col.rest.conector.util.HttpClientException;
import com.grupobbva.col.rest.conector.util.ResponseCode;
import com.grupobbva.ii.sf.base.BbvaException;
import com.ibm.dse.base.DSEInvalidArgumentException;
import com.ibm.dse.base.DSEObjectNotFoundException;
import com.ibm.dse.base.IndexedCollection;
import com.ibm.dse.base.KeyedCollection;
import com.ibm.dse.base.Trace;

public class OMPaymentService extends OMTrisConector {
	
	private static final String CONECTOR_CIRI = "ConectorCiri.RestClinet.endpoint";
	private static final String SALIDA_OM = "Salida.paymentObject";
	
	
	public OMPaymentService() {
		super();
	}

	public OMPaymentService(String anOperationName) throws java.io.IOException {
		super(anOperationName);
	}

	public OMPaymentService(String anOperationName, com.ibm.dse.base.Context aParentContext)
			throws java.io.IOException, com.ibm.dse.base.DSEInvalidRequestException {
		super(anOperationName, aParentContext);
	}

	public OMPaymentService(String anOperationName, String aParentContext) throws java.io.IOException,
			com.ibm.dse.base.DSEObjectNotFoundException, com.ibm.dse.base.DSEInvalidRequestException {
		super(anOperationName, aParentContext);
	}

	public void execute() throws BbvaException {

		try {
			String metodoParameter=(String) getValueAt("Entrada.metodo");
			MetodosPayment metodo = MetodosPayment.valueOf(metodoParameter);
			
			switch (metodo) {
			case GET_ID_PAYMENT:
				getPaymenById();
				break;
			case POST_PAYMENT_CONCILIATE:
				postPaymentConciliate();
				break;
			case POST_PAYMENT:
				postPayment();
				break;
			default:
				getPaymenById();
			}
			
		} catch (DSEObjectNotFoundException e) {
			Trace.trace(Trace.VTF, "", "Error al leer propiedad : " + e.getMessage());
		} catch (HttpClientException e) {
			Trace.trace(Trace.VTF, "", "Error al realizar consumo rest : " + e.getMessage());
		} catch (DSEInvalidArgumentException e) {
			Trace.trace(Trace.VTF, "", "Error al conectar propiedad : " + e.getMessage());
    	} 
		
	}
	
	private void getPaymenById() throws DSEObjectNotFoundException, HttpClientException, DSEInvalidArgumentException{
		String URL = (String) getValueAt(CONECTOR_CIRI);
		String endPoint = URL + "/services/V1/PaymentsService/achPayments/{paymenId}";
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("paymenId", (String) getValueAt("Entrada.getPaymenById.id"));
		RestConector conector = getRestConector();
		ResponseEntity<PaymentsServiceResponse> response = conector.getForEntity(endPoint, PaymentsServiceResponse.class,
				parameters);
		if (response.getResponseCode() == ResponseCode.OK) {
			PaymentsServiceResponse paymentResponseDto = response.getEntity();
			setValueAt(SALIDA_OM, paymentResponseDto);
		}
	}
	
	private void postPaymentConciliate() throws DSEObjectNotFoundException, HttpClientException, DSEInvalidArgumentException {
		String URL = (String) getValueAt(CONECTOR_CIRI);
		String endPoint = URL + "/services/V1/PaymentsService/achPayments/{paymenId}/conciliate";
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("paymenId", (String) getValueAt("Entrada.postPaymentConciliate.id"));
		RestConector conector = getRestConector();
		ConciliateRequest conciliateRequest = new ConciliateRequest();		
		Status statusDto = new Status();
		statusDto.setId((String)getValueAt("Entrada.postPaymentConciliate.status.id"));		
		Reason reasonDto= new Reason();
		reasonDto.setId((String)getValueAt("Entrada.postPaymentConciliate.status.reasonId"));		
		statusDto.setReason(reasonDto);		
		conciliateRequest.setStatus(statusDto);		
		IndexedCollection references = (IndexedCollection) getElementAt("Entrada.postPaymentConciliate.references");
		List<Reference> referencesDto= new ArrayList<Reference>();
		for (int i = 0; i < references.size(); i++) {
			Reference referenceDto= new Reference();
			KeyedCollection reference = (KeyedCollection)references.getElementAt(i);
			referenceDto.setId((String)reference.getValueAt("id"));
			referenceDto.setValue((String)reference.getValueAt("value"));
			referencesDto.add(referenceDto);
		}
		conciliateRequest.setReferences(referencesDto);
		
		ResponseEntity<ConciliateResponse> response=conector.postForEntity(endPoint, ConciliateResponse.class, parameters, conciliateRequest);
		
		if (response.getResponseCode() == ResponseCode.OK) {
			ConciliateResponse paymentResponseDto = response.getEntity();
			setValueAt(SALIDA_OM, paymentResponseDto);
		}
	}
	
	private void postPayment() throws DSEObjectNotFoundException, HttpClientException, DSEInvalidArgumentException {
		
		String URL = (String) getValueAt(CONECTOR_CIRI);
		String endPoint = URL + "/services/V1/PaymentsService/achPayments";
		RestConector conector = getRestConector();
		PaymentsServiceRequest paymentDto = new PaymentsServiceRequest();
		paymentDto =(PaymentsServiceRequest)getValueAt("Entrada.postPayment.paymentObject");
		ResponseEntity<PaymentsServiceResponse> response = conector.postForEntity(endPoint, PaymentsServiceResponse.class,paymentDto);
		if (response.getResponseCode() == ResponseCode.OK ||response.getResponseCode() == ResponseCode.CREATED) {
			PaymentsServiceResponse paymentResponseDto = response.getEntity();
			setValueAt(SALIDA_OM, paymentResponseDto);
		}
	}

}
