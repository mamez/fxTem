package com.grupobbva.bc.col.web.pse;

public class UsuariosPseDto {

	private String tipoPago;
	private String usuarioAuto;
	private String numeroTrans;
	private String valorDesde;
	private String valorHasta;
	private String hora;
	private String estado;
	private String fechaTrans;
	private String usuarioAudi;
	private String ipUsuarioAudi;
	private String valorUtilizado;
	private String transaccioEject;
	private String fechaEjecu;
	
	public String getFechaTrans() {
		return fechaTrans;
	}
	public void setFechaTrans(String fechaTrans) {
		this.fechaTrans = fechaTrans;
	}
	public String getTipoPago() {
		return tipoPago;
	}
	public String getUsuarioAuto() {
		return usuarioAuto;
	}
	public String getNumeroTrans() {
		return numeroTrans;
	}

	public String getEstado() {
		return estado;
	}
	public String getUsuarioAudi() {
		return usuarioAudi;
	}
	public String getIpUsuarioAudi() {
		return ipUsuarioAudi;
	}
	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}
	public void setUsuarioAuto(String usuarioAuto) {
		this.usuarioAuto = usuarioAuto;
	}
	public void setNumeroTrans(String numeroTrans) {
		this.numeroTrans = numeroTrans;
	}
	public String getValorDesde() {
		return valorDesde;
	}
	public String getValorHasta() {
		return valorHasta;
	}
	public void setValorDesde(String valorDesde) {
		this.valorDesde = valorDesde;
	}
	public void setValorHasta(String valorHasta) {
		this.valorHasta = valorHasta;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public void setUsuarioAudi(String usuarioAudi) {
		this.usuarioAudi = usuarioAudi;
	}
	public void setIpUsuarioAudi(String ipUsuarioAudi) {
		this.ipUsuarioAudi = ipUsuarioAudi;
	}
	public String getValorUtilizado() {
		return valorUtilizado;
	}
	public String getTransaccioEject() {
		return transaccioEject;
	}
	public String getFechaEjecu() {
		return fechaEjecu;
	}
	public void setValorUtilizado(String valorUtilizado) {
		this.valorUtilizado = valorUtilizado;
	}
	public void setTransaccioEject(String transaccioEject) {
		this.transaccioEject = transaccioEject;
	}
	public void setFechaEjecu(String fechaEjecu) {
		this.fechaEjecu = fechaEjecu;
	}
	
	
}
