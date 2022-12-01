package com.grupobbva.bc.col.web.pse;

public enum MetodoEnum {

	LISTAR_USUARIOS("listar"),
	LISTAR_USUARIOS_ORDEN ("listarUsuarioOrden"),
	ELIMINAR_USUARIO("eliminar"), 
	CONFIGURAR_USUARIO("configurar"),
	ACTUALIZAR_ORDEN("actualizar"),
	AUTORIZAR_ORDEN("autorizar"),
	LISTAR_OPERACION("listarOperacion"),
	ACTUALIZAR_REINTENTO("actualizarReintento");
	

	private String metodos;

	private MetodoEnum(String metodos) {
		this.metodos = metodos;
	}

	public String getmetodName() {
		return metodos;
	}

}
