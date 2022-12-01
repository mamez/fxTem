package com.grupobbva.bc.col.web.pse;

import com.grupobbva.ii.sf.operacion.Proceso;
import com.ibm.dse.base.Trace;

public class PrGestionPagosPseDivisa extends Proceso {

	public PrGestionPagosPseDivisa() {
		super();
	}

	/**
	 *
	 * @param anOperationName
	 * @throws java.io.IOException
	 */
	public PrGestionPagosPseDivisa(String anOperationName) throws java.io.IOException {
		super(anOperationName);
	}

	/**
	 *
	 * @param anOperationName
	 * @param aParentContext
	 * @throws java.io.IOException
	 * @throws com.ibm.dse.base.DSEInvalidRequestException
	 */
	public PrGestionPagosPseDivisa(String anOperationName, com.ibm.dse.base.Context aParentContext)
			throws java.io.IOException, com.ibm.dse.base.DSEInvalidRequestException {
		super(anOperationName, aParentContext);
	}

	public void actualizarEstado() {
		setEstado("0");
	}

	/* Metodo para acceder a Url segun acción */
	public void actualizarUrlData() {
		Trace.trace(Trace.VTF, "", "Metodo actualizarData: " );
	}
}