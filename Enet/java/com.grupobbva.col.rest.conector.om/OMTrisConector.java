package com.grupobbva.col.rest.conector.om;

import java.util.HashMap;
import java.util.Map;

import com.grupobbva.col.rest.conector.RestConector;
import com.grupobbva.col.rest.conector.util.HttpClientException;
import com.grupobbva.ii.sf.operacion.OmGestion;
import com.ibm.dse.base.DSEObjectNotFoundException;
import com.ibm.dse.base.Trace;

public class OMTrisConector extends OmGestion {
	
	public OMTrisConector() {
		super();
	}

	public OMTrisConector(String anOperationName) throws java.io.IOException {
		super(anOperationName);
	}

	public OMTrisConector(String anOperationName, com.ibm.dse.base.Context aParentContext)
			throws java.io.IOException, com.ibm.dse.base.DSEInvalidRequestException {
		super(anOperationName, aParentContext);
	}

	public OMTrisConector(String anOperationName, String aParentContext) throws java.io.IOException,
			com.ibm.dse.base.DSEObjectNotFoundException, com.ibm.dse.base.DSEInvalidRequestException {
		super(anOperationName, aParentContext);
	}
	

	
	public RestConector getRestConector() throws HttpClientException {
		return new RestConector(buildHeaders());
	}

	

	private Map<String, String> buildHeaders() {
	   Map<String, String> header = new HashMap<String, String>();
		try {                                            
			String ticket =  (String)getValueAt("s_ivTicket");
			String sesion = (String)getValueAt("datosAPP.iv-id_sesion_ast");
			String usuario = (String)getValueAt("datosAPP.iv-cod_usu");
			String referencia = (String)getValueAt("datosAPP.iv-cod_emp");
			String user = "00260082" + referencia + usuario ;
			String ipUser = (String)getValueAt("datosAPP.iv-remote-address");
			
			header.put("iv_ticketservice", ticket);
			header.put("iv-id_sesion_ast", rellenar(sesion));
			header.put("iv-cod_emp", referencia);
			header.put("iv-cod_usu",usuario );
			header.put("iv-cod_ban_int","0082" );
			header.put("iv-cod_canal", "0026");
			header.put("iv-user", user);
			header.put("iv-origen", "WEB");
			header.put("iv-remote-address", ipUser);
			header.put("Accept", "application/json");
			header.put("origen", "WEB");

			
		} catch (DSEObjectNotFoundException e) {
			Trace.trace(Trace.VTF, "", "Error al leer propiedad : " + e.getMessage());
		}
		return header;
	}
	
	@Override
	public Long getSessionId() {
		return super.getSessionId();
	}
	
	private String rellenar(String ast) {
		for(int i=ast.length();i<8;i++) {
			ast="0"+ast;
		}
		return ast;
	}

}
