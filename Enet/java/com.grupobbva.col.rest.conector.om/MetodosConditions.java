package com.grupobbva.col.rest.conector.om;

public enum MetodosConditions {
	
	GET_COMISION_SERVE("listarComisionSer"),
	POST_OPERATIVE_CONDITIONS("crearOperativeCondit");
	
	private String metodos;

	private MetodosConditions(String metodos) {
		this.metodos = metodos;
	}

	public String getmetodName() {
		return metodos;
	}


}
