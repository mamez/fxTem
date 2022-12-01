
package com.grupobbva.col.rest.conector.dto.comisionService;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Datum {

    private String abaCode;
    private Advance advance;
    private List<Beneficiary> beneficiaries = new ArrayList<Beneficiary>();
    private ChargeAmount chargeAmount;
    private String description;
    private String id;
    private NetAmountSecureOnlinePayments netAmountSecureOnlinePayments;
    private String operationDate;
    private String pseOperationId;
    private String pseStatus;
    private Rates rates;
    private ReimbursementAccount reimbursementAccount;
    private String status;
    private String swiftCode;
    private Taxes taxes;
    private TransferedTotalAmount transferedTotalAmount;
	private String operationDateMsl;

    public String getAbaCode() {
        return abaCode;
    }

    public void setAbaCode(String abaCode) {
        this.abaCode = abaCode;
    }

    public Advance getAdvance() {
        return advance;
    }

    public void setAdvance(Advance advance) {
        this.advance = advance;
    }

    public List<Beneficiary> getBeneficiaries() {
        return beneficiaries;
    }

    public void setBeneficiaries(List<Beneficiary> beneficiaries) {
        this.beneficiaries = beneficiaries;
    }

    public ChargeAmount getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(ChargeAmount chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public NetAmountSecureOnlinePayments getNetAmountSecureOnlinePayments() {
        return netAmountSecureOnlinePayments;
    }

    public void setNetAmountSecureOnlinePayments(NetAmountSecureOnlinePayments netAmountSecureOnlinePayments) {
        this.netAmountSecureOnlinePayments = netAmountSecureOnlinePayments;
    }

    public String getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(String operationDate) {
        this.operationDate = operationDate;
    }

    public String getPseOperationId() {
        return pseOperationId;
    }

    public void setPseOperationId(String pseOperationId) {
        this.pseOperationId = pseOperationId;
    }

    public String getPseStatus() {
        return pseStatus;
    }

    public void setPseStatus(String pseStatus) {
        this.pseStatus = pseStatus;
    }

    public Rates getRates() {
        return rates;
    }

    public void setRates(Rates rates) {
        this.rates = rates;
    }

    public ReimbursementAccount getReimbursementAccount() {
        return reimbursementAccount;
    }

    public void setReimbursementAccount(ReimbursementAccount reimbursementAccount) {
        this.reimbursementAccount = reimbursementAccount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSwiftCode() {
        return swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }

    public Taxes getTaxes() {
        return taxes;
    }

    public void setTaxes(Taxes taxes) {
        this.taxes = taxes;
    }

    public TransferedTotalAmount getTransferedTotalAmount() {
        return transferedTotalAmount;
    }

    public void setTransferedTotalAmount(TransferedTotalAmount transferedTotalAmount) {
        this.transferedTotalAmount = transferedTotalAmount;
    }

	public String getOperationDateMsl() {
		return operationDateMsl;
	}

	public void setOperationDateMsl(String operationDateMsl) {
		this.operationDateMsl = operationDateMsl;
	}

}
