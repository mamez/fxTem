package com.grupobbva.bc.col.web.pse;

import java.util.List;

public class UsuarioResponse {
	
	private String status;
	private int page;
	private List<UsuariosPseDto> usuarios;
	
	public String getStatus() {
		return status;
	}
	public List<UsuariosPseDto> getUsuarios() {
		return usuarios;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setUsuarios(List<UsuariosPseDto> usuarios) {
		this.usuarios = usuarios;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
	

}
