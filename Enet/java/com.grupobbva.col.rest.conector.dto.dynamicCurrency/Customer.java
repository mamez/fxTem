
package com.grupobbva.col.rest.conector.dto.dynamicCurrency;

public class Customer {

	private IdentityDocument identityDocument;

	public Customer() {
	}

	public Customer(String documentType, String documentNumber) {
		this.identityDocument = new IdentityDocument(documentType, documentNumber);
	}

	public IdentityDocument getIdentityDocument() {
		return identityDocument;
	}

	public void setIdentityDocument(IdentityDocument identityDocument) {
		this.identityDocument = identityDocument;
	}

}
