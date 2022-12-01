package com.grupobbva.bc.col.web.pse;

public class OrdenDetailDto {

	private String numeroTran;
	private double valorUtilizado;
	private int transaccionEjecutadas;
	private String bankTrade;
	
	public OrdenDetailDto(String numeroTran, String bankTrade, double valorUtilizado) {
		this.numeroTran=numeroTran;
		this.valorUtilizado=valorUtilizado;
		this.transaccionEjecutadas=1;
		this.bankTrade = bankTrade;
	}

	public String getBankTrade() {
		return bankTrade;
	}

	public String getNumeroTran() {
		return numeroTran;
	}

	public double getValorUtilizado() {
		return valorUtilizado;
	}

	public int getTransaccionEjecutadas() {
		return transaccionEjecutadas;
	}

	public void setValorUtilizado(double valorUtilizado) {
		
		this.valorUtilizado+= valorUtilizado;
	}

	public void setTransaccionEjecutadas(int transaccionEjecutadas) {
		this.transaccionEjecutadas= transaccionEjecutadas;
	}

}
