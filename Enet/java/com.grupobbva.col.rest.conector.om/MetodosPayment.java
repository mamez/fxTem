package com.grupobbva.col.rest.conector.om;

public enum MetodosPayment {

	GET_ID_PAYMENT("getId"),
	POST_PAYMENT_CONCILIATE("createPaymentConci"),
	POST_PAYMENT("createPayment");
	
	private String metodos;

	private MetodosPayment(String metodos) {
		this.metodos = metodos;
	}

	public String getmetodName() {
		return metodos;
	}

	
}
