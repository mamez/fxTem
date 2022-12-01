
package com.grupobbva.col.rest.conector.dto.dynamicCurrency;

public class IdentityDocument {

	private DocumentType documentType;
	private String documentNumber;

	public IdentityDocument() {
	}

	public IdentityDocument(String documentType, String documentNumber) {
		this.documentType = new DocumentType(documentType);
		this.documentNumber = documentNumber;
	}

	public DocumentType getDocumentType() {
		return documentType;
	}

	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

}
