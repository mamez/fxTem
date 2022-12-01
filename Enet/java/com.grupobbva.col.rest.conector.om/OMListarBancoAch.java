package com.grupobbva.col.rest.conector.om;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.grupobbva.col.rest.conector.ResponseEntity;
import com.grupobbva.col.rest.conector.RestConector;
import com.grupobbva.col.rest.conector.dto.listBanc.BanckAch;
import com.grupobbva.col.rest.conector.dto.listBanc.ListaBancosAch;
import com.grupobbva.col.rest.conector.util.HttpClientException;
import com.grupobbva.col.rest.conector.util.ResponseCode;
import com.grupobbva.ii.sf.base.BbvaException;
import com.ibm.dse.base.DSEInvalidArgumentException;
import com.ibm.dse.base.DSEObjectNotFoundException;
import com.ibm.dse.base.DataElement;
import com.ibm.dse.base.IndexedCollection;
import com.ibm.dse.base.KeyedCollection;
import com.ibm.dse.base.Trace;

public class OMListarBancoAch extends OMTrisConector {
	
	public OMListarBancoAch() {
		super();
	}

	public OMListarBancoAch(String anOperationName) throws java.io.IOException {
		super(anOperationName);
	}

	public OMListarBancoAch(String anOperationName, com.ibm.dse.base.Context aParentContext)
			throws java.io.IOException, com.ibm.dse.base.DSEInvalidRequestException {
		super(anOperationName, aParentContext);
	}

	public OMListarBancoAch(String anOperationName, String aParentContext) throws java.io.IOException,
			com.ibm.dse.base.DSEObjectNotFoundException, com.ibm.dse.base.DSEInvalidRequestException {
		super(anOperationName, aParentContext);
	}
	
	public void execute() throws BbvaException {
		try {
			String URL=(String)getValueAt("ConectorCiri.RestClinet.endpoint");
			String endPoint=URL+"/services/V1/Catalogs/{catalogId}/records";
			Map<String, String> parameters = new HashMap<String, String>();
			parameters.put("catalogId", "ACH_BANKS");
			RestConector conector=getRestConector();
			ResponseEntity<ListaBancosAch> response=   conector.getForEntity(endPoint,ListaBancosAch.class, parameters);
			if(response.getResponseCode() == ResponseCode.OK) {
				ListaBancosAch  data=response.getEntity();
				IndexedCollection iclistarBancosAch = (IndexedCollection) getElementAt("Salida.listaBancosAch");
				iclistarBancosAch.removeAll();
				for(BanckAch in: data.getData()) {
				KeyedCollection kclistarBancosAch = (KeyedCollection) DataElement.getExternalizer()
							.convertTagToObject(iclistarBancosAch.getElementSubTag());
				kclistarBancosAch.setValueAt("id", in.getId());
				kclistarBancosAch.setValueAt("descripcion", in.getDescriptions().get(0).getValue());
				iclistarBancosAch.addElement(kclistarBancosAch);
				}
			}
		} catch (DSEObjectNotFoundException e) {
			Trace.trace(Trace.VTF, "", "Error al leer propiedad : " + e.getMessage());
		} catch (HttpClientException e) {
			Trace.trace(Trace.VTF, "", "Error al realizar consumo rest : " + e.getMessage());
		} catch (IOException e) {
			Trace.trace(Trace.VTF, "", "Error al listar propiedad : " + e.getMessage());
		} catch (DSEInvalidArgumentException e) {
			Trace.trace(Trace.VTF, "", "Error al conectar propiedad : " + e.getMessage());
		}
			
		
	}
	
	
	

}
