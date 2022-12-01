package com.grupobbva.col.rest.conector.dto.payment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MapPayment {
	private static final String CURRENCY_COP = "COP";
	private static final String FIXED_VALUE = "xxx";
	private static final String AMOUNT = "AMOUNT"; 
	private BigDecimal amountTotalFees;
	private BigDecimal amountTotalTaxes;
	private String account;
	private String idBank;
	private String nameBank;
	private String typeDocument;
	private String nameDocumentType;
	private String documentNumber;
	private String representativeName;
	private String operationNumber;
	private String autorizationNumber;
	private BigDecimal valorTotal;
	private String url;
	private String reintento;

	

	public PaymentsServiceRequest Map() {
		PaymentsServiceRequest paymentsServiceRequest = new PaymentsServiceRequest();
		//Map paymentAmount
		List<PaymentAmount> listPaymentAmount = new ArrayList<PaymentAmount>();
		PaymentAmount paymentAmount = new PaymentAmount();
		PaymentType paymentType = new PaymentType();
		paymentType.setId("MINIMUM_AMOUNT_PAYMENT");
		paymentType.setValue("DV1");
		Amount amount = new Amount();
		amount.setAmount(valorTotal);
		amount.setCurrency(CURRENCY_COP);
		paymentAmount.setPaymentType(paymentType);
		paymentAmount.setAmount(amount);
		listPaymentAmount.add(paymentAmount);
		paymentsServiceRequest.setPaymentAmount(listPaymentAmount);
		//Map fees
		Fees fees = new Fees();
		List<ItemizeFee> listItemizeFee = new ArrayList<ItemizeFee>();
		TotalFees totalFees = new TotalFees();
		totalFees.setAmount(amountTotalFees);
		totalFees.setCurrency(CURRENCY_COP);
		fees.setTotalFees(totalFees);
		ItemizeFee itemizeFee = new ItemizeFee();
		itemizeFee.setFeeType(FIXED_VALUE);
		itemizeFee.setDescription(FIXED_VALUE);
		ItemizeFeeUnit itemizeFeeUnit = new ItemizeFeeUnit();
		itemizeFeeUnit.setUnitType(AMOUNT);
		itemizeFeeUnit.setAmount(BigDecimal.valueOf(23));
		itemizeFeeUnit.setCurrency(CURRENCY_COP);
		itemizeFee.setItemizeFeeUnit(itemizeFeeUnit);
		listItemizeFee.add(itemizeFee);
		fees.setItemizeFees(listItemizeFee);
		paymentsServiceRequest.setFees(fees);
		//Map taxes
		Taxes taxes = new Taxes();
		TotalTaxes totalTaxes = new TotalTaxes();
		totalTaxes.setAmount(amountTotalTaxes);
		totalTaxes.setCurrency(CURRENCY_COP);
		taxes.setTotalTaxes(totalTaxes);
		List<ItemizeTax> listItemizeTaxes = new ArrayList<ItemizeTax>();
		ItemizeTax itemizeTax = new ItemizeTax();
		itemizeTax.setTaxType(FIXED_VALUE);
		itemizeTax.setDescription(FIXED_VALUE);
		ItemizeTaxUnit itemizeTaxUnit = new ItemizeTaxUnit();
		itemizeTaxUnit.setUnitType(AMOUNT);
		itemizeTaxUnit.setAmount(Double.valueOf(23));
		itemizeTaxUnit.setCurrency(CURRENCY_COP);
		itemizeTax.setItemizeTaxUnit(itemizeTaxUnit);
		listItemizeTaxes.add(itemizeTax);
		taxes.setItemizeTaxes(listItemizeTaxes);
		paymentsServiceRequest.setTaxes(taxes);
		//Map reimbursementAccount
		ReimbursementAccount reimbursementAccount = new ReimbursementAccount();
		reimbursementAccount.setAccount(account);
		Bank bank = new Bank();	
		bank.setId(reintento);
		bank.setName("BBVA");
		reimbursementAccount.setBank(bank);
		paymentsServiceRequest.setReimbursementAccount(reimbursementAccount);
		//Map origin
		Origin origin = new Origin();
		Bank bankOrigin = new Bank();
		origin.setIsExternal(true);
		bankOrigin.setId(idBank);
		bankOrigin.setName(nameBank);
		origin.setBank(bankOrigin);
		paymentsServiceRequest.setOrigin(origin);
		//Map holder
		Holder holder = new Holder();
		holder.setHolderName(representativeName);
		List<IdentityDocument> identityDocuments = new ArrayList<IdentityDocument>();
		IdentityDocument identityDocument = new IdentityDocument();
		DocumentType documentType = new DocumentType();
		documentType.setId(typeDocument);
		documentType.setName(nameDocumentType);
		identityDocument.setDocumentType(documentType);
		identityDocument.setDocumentNumber(documentNumber);
		identityDocuments.add(identityDocument);
		holder.setIdentityDocuments(identityDocuments);
		LegalPersonType legalPersonType = new LegalPersonType();
		legalPersonType.setParticipantType("LEGAL");
		holder.setLegalPersonType(legalPersonType);
		paymentsServiceRequest.setHolder(holder);
		//Map receiver
		Receiver receiver = new Receiver();
		ProductType productType = new ProductType();
		productType.setId("CARD");
		receiver.setProductType(productType);
		receiver.setNumber(operationNumber);
		NumberType numberType = new NumberType();
		numberType.setId("PAN");
		receiver.setNumberType(numberType);
		paymentsServiceRequest.setReceiver(receiver);
		//Map reference
		List<Reference> listReference = new ArrayList<Reference>();
		Reference reference = new Reference();
		reference.setId("INTERNAL");
		reference.setValue(autorizationNumber);
		listReference.add(reference);
		paymentsServiceRequest.setReference(listReference);
		//Map additionalInformation
		AdditionalInformation additionalInformation = new AdditionalInformation();
		additionalInformation.setId("DEEPLINK");
		additionalInformation.setValue(url);
		paymentsServiceRequest.setAdditionalInformation(additionalInformation);
		return paymentsServiceRequest;
	}

	public BigDecimal getAmountTotalFees() {
		return amountTotalFees;
	}

	public void setAmountTotalFees(BigDecimal amountTotalFees) {
		this.amountTotalFees = amountTotalFees;
	}

	public BigDecimal getAmountTotalTaxes() {
		return amountTotalTaxes;
	}

	public void setAmountTotalTaxes(BigDecimal amountTotalTaxes) {
		this.amountTotalTaxes = amountTotalTaxes;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getIdBank() {
		return idBank;
	}

	public void setIdBank(String idBank) {
		this.idBank = idBank;
	}

	public String getNameBank() {
		return nameBank;
	}

	public void setNameBank(String nameBank) {
		this.nameBank = nameBank;
	}

	public String getTypeDocument() {
		return typeDocument;
	}

	public void setTypeDocument(String typeDocument) {
		this.typeDocument = typeDocument;
	}

	public String getNameDocumentType() {
		return nameDocumentType;
	}

	public void setNameDocumentType(String nameDocumentType) {
		this.nameDocumentType = nameDocumentType;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public String getRepresentativeName() {
		return representativeName;
	}

	public void setRepresentativeName(String representativeName) {
		this.representativeName = representativeName;
	}

	public String getOperationNumber() {
		return operationNumber;
	}

	public void setOperationNumber(String operationNumber) {
		this.operationNumber = operationNumber;
	}

	public String getAutorizationNumber() {
		return autorizationNumber;
	}

	public void setAutorizationNumber(String autorizationNumber) {
		this.autorizationNumber = autorizationNumber;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getReintento() {
		return reintento;
	}

	public void setReintento(String reintento) {
		this.reintento = reintento;
	}	
}
