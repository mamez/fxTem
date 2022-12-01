package com.grupobbva.bc.col.web.comercioext.clientews;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RequestBankTradeService {
	private final SimpleDateFormat FORMATO_FECHA = new SimpleDateFormat("yyyy-MM-dd");
	private final SimpleDateFormat FORMATO_HORA = new SimpleDateFormat("HH:mm:ss");
	private String TyOperac;
	private String IdSucursal;
	private String DeOperac;
	private String IdCliente;
	private String IdCampo72;
	private String MonNegoc;
	private String CurNegoc;
	private String NoOperac;
	private Double TasaDivi;
	private Double TasaUsd;
	private Double TasaLinea;
	private String Canal;
	private String Subcanal;
	private String Campana;
	private String TyVended;
	private String NoVended;
	private String DigVended;
	private String TyNegoci;
	private String NoAvance;
	private String MonAvance;
	private String TasaAvance;
	private String NuCambiario;
	private String TyTransa;
	private String NoOperac1;
	private Date FeValor;
	private String TyUsuario;
	private Date FechaHoraNegociacion;
	private String SpreadMinimo;
	private String spreadMaximo;
	private String IndMoneda;
	private String ID_BCOOrdenante;
	private String NomBcoOrd1;
	private String NomBcoOrd2;
	private String NomBcoOrd3;
	private String NomBcoOrd4;
	private String IdSwTpBcoOrd;
	private String SwBcoOrd;
	private String DeComm;
	private String NoCtaComm;
	private String CurDbComm;
	private String TpCambioComm;
	private String DbPrincipal;
	private String NoCtaPrincipal;
	private String NoCtaPrincipalCre;
	private String IdCampo59;
	private String NomCam59Bene1;
	private String NomCam59Bene2;
	private String NomCam59Bene3;
	private String NomCam59Bene4;
	private String Infcam70Sw1;
	private String Infcam70Sw2;
	private String Infcam70Sw3;
	private String Infcam70Sw4;
	private String IDcampo56;
	private String NomCam56InfBene1;
	private String NomCam56InfBene2;
	private String NomCam56InfBene3;
	private String NomCam56InfBene4;
	private String IdTyCampo56Sw;
	private String Swiftcampo56A;
	private String IDcampo57;
	private String NomCam57BanBene1;
	private String NomCam57BanBene2;
	private String NomCam57BanBene3;
	private String NomCam57BanBene4;
	private String IdTyCampo57Sw;
	private String Swiftcampo57A;
	private String CodOpecampo23b;
	private String UsdRateComm;
	private String MontoNetoPse;

	public String getCampana() {
		return this.Campana;
	}

	public void setCampana(String campana) {
		this.Campana = campana;
	}

	public String getCanal() {
		return this.Canal;
	}

	public void setCanal(String canal) {
		this.Canal = canal;
	}

	public String getCodOpecampo23b() {
		return this.CodOpecampo23b;
	}

	public void setCodOpecampo23b(String codOpecampo23b) {
		this.CodOpecampo23b = codOpecampo23b;
	}

	public String getCurDbComm() {
		return this.CurDbComm;
	}

	public void setCurDbComm(String curDbComm) {
		this.CurDbComm = curDbComm;
	}

	public String getCurNegoc() {
		return this.CurNegoc;
	}

	public void setCurNegoc(String curNegoc) {
		this.CurNegoc = curNegoc;
	}

	public String getDbPrincipal() {
		return this.DbPrincipal;
	}

	public void setDbPrincipal(String dbPrincipal) {
		this.DbPrincipal = dbPrincipal;
	}

	public String getDeOperac() {
		return this.DeOperac;
	}

	public void setDeOperac(String deOperac) {
		this.DeOperac = deOperac;
	}

	public String getDigVended() {
		return this.DigVended;
	}

	public void setDigVended(String digVended) {
		this.DigVended = digVended;
	}

	public Date getFechaHoraNegociacion() {
		return this.FechaHoraNegociacion;
	}

	public void setFechaHoraNegociacion(Date fechaHoraNegociacion) {
		this.FechaHoraNegociacion = fechaHoraNegociacion;
	}

	public Date getFeValor() {
		return this.FeValor;
	}

	public void setFeValor(Date feValor) {
		this.FeValor = feValor;
	}

	public String getID_BCOOrdenante() {
		return this.ID_BCOOrdenante;
	}

	public void setID_BCOOrdenante(String id) {
		this.ID_BCOOrdenante = id;
	}

	public String getIDcampo56() {
		return this.IDcampo56;
	}

	public void setIDcampo56(String dcampo56) {
		this.IDcampo56 = dcampo56;
	}

	public String getIDcampo57() {
		return this.IDcampo57;
	}

	public void setIDcampo57(String dcampo57) {
		this.IDcampo57 = dcampo57;
	}

	public String getIdCampo59() {
		return this.IdCampo59;
	}

	public void setIdCampo59(String idCampo59) {
		this.IdCampo59 = idCampo59;
	}

	public String getIdCliente() {
		return this.IdCliente;
	}

	public void setIdCliente(String idCliente) {
		this.IdCliente = idCliente;
	}
	public String getIdCampo72() {
		return this.IdCampo72;
	}
	public void setIdCampo72(String idcamp72) {
		this.IdCampo72 = idcamp72;
	}

	public String getIdSucursal() {
		return this.IdSucursal;
	}

	public void setIdSucursal(String idSucursal) {
		this.IdSucursal = idSucursal;
	}

	public String getIdSwTpBcoOrd() {
		return this.IdSwTpBcoOrd;
	}

	public void setIdSwTpBcoOrd(String idSwTpBcoOrd) {
		this.IdSwTpBcoOrd = idSwTpBcoOrd;
	}

	public String getIdTyCampo56Sw() {
		return this.IdTyCampo56Sw;
	}

	public void setIdTyCampo56Sw(String idTyCampo56Sw) {
		this.IdTyCampo56Sw = idTyCampo56Sw;
	}

	public String getIdTyCampo57Sw() {
		return this.IdTyCampo57Sw;
	}

	public void setIdTyCampo57Sw(String idTyCampo57Sw) {
		this.IdTyCampo57Sw = idTyCampo57Sw;
	}

	public String getIndMoneda() {
		return this.IndMoneda;
	}

	public void setIndMoneda(String indMoneda) {
		this.IndMoneda = indMoneda;
	}

	public String getInfcam70Sw1() {
		return this.Infcam70Sw1;
	}

	public void setInfcam70Sw1(String infcam70Sw1) {
		this.Infcam70Sw1 = infcam70Sw1;
	}

	public String getInfcam70Sw2() {
		return this.Infcam70Sw2;
	}

	public void setInfcam70Sw2(String infcam70Sw2) {
		this.Infcam70Sw2 = infcam70Sw2;
	}

	public String getInfcam70Sw3() {
		return this.Infcam70Sw3;
	}

	public void setInfcam70Sw3(String infcam70Sw3) {
		this.Infcam70Sw3 = infcam70Sw3;
	}

	public String getInfcam70Sw4() {
		return this.Infcam70Sw4;
	}

	public void setInfcam70Sw4(String infcam70Sw4) {
		this.Infcam70Sw4 = infcam70Sw4;
	}

	public String getMonAvance() {
		return this.MonAvance;
	}

	public void setMonAvance(String monAvance) {
		this.MonAvance = monAvance;
	}

	public String getMonNegoc() {
		return this.MonNegoc;
	}

	public void setMonNegoc(String monNegoc) {
		this.MonNegoc = monNegoc;
	}

	public String getNoAvance() {
		return this.NoAvance;
	}

	public void setNoAvance(String noAvance) {
		this.NoAvance = noAvance;
	}

	public String getNoCtaComm() {
		return this.NoCtaComm;
	}

	public void setNoCtaComm(String noCtaComm) {
		this.NoCtaComm = noCtaComm;
	}

	public String getNoCtaPrincipal() {
		return this.NoCtaPrincipal;
	}

	public void setNoCtaPrincipal(String noCtaPrincipal) {
		this.NoCtaPrincipal = noCtaPrincipal;
	}

	public String getNoCtaPrincipalCre() {
		return this.NoCtaPrincipalCre;
	}

	public void setNoCtaPrincipalCre(String noCtaPrincipalCre) {
		this.NoCtaPrincipalCre = noCtaPrincipalCre;
	}

	public String getNomBcoOrd1() {
		return this.NomBcoOrd1;
	}

	public void setNomBcoOrd1(String nomBcoOrd1) {
		this.NomBcoOrd1 = nomBcoOrd1;
	}

	public String getNomBcoOrd2() {
		return this.NomBcoOrd2;
	}

	public void setNomBcoOrd2(String nomBcoOrd2) {
		this.NomBcoOrd2 = nomBcoOrd2;
	}

	public String getNomBcoOrd3() {
		return this.NomBcoOrd3;
	}

	public void setNomBcoOrd3(String nomBcoOrd3) {
		this.NomBcoOrd3 = nomBcoOrd3;
	}

	public String getNomBcoOrd4() {
		return this.NomBcoOrd4;
	}

	public void setNomBcoOrd4(String nomBcoOrd4) {
		this.NomBcoOrd4 = nomBcoOrd4;
	}

	public String getNomCam56InfBene1() {
		return this.NomCam56InfBene1;
	}

	public void setNomCam56InfBene1(String nomCam56InfBene1) {
		this.NomCam56InfBene1 = nomCam56InfBene1;
	}

	public String getNomCam56InfBene2() {
		return this.NomCam56InfBene2;
	}

	public void setNomCam56InfBene2(String nomCam56InfBene2) {
		this.NomCam56InfBene2 = nomCam56InfBene2;
	}

	public String getNomCam56InfBene3() {
		return this.NomCam56InfBene3;
	}

	public void setNomCam56InfBene3(String nomCam56InfBene3) {
		this.NomCam56InfBene3 = nomCam56InfBene3;
	}

	public String getNomCam56InfBene4() {
		return this.NomCam56InfBene4;
	}

	public void setNomCam56InfBene4(String nomCam56InfBene4) {
		this.NomCam56InfBene4 = nomCam56InfBene4;
	}

	public String getNomCam57BanBene1() {
		return this.NomCam57BanBene1;
	}

	public void setNomCam57BanBene1(String nomCam57BanBene1) {
		this.NomCam57BanBene1 = nomCam57BanBene1;
	}

	public String getNomCam57BanBene2() {
		return this.NomCam57BanBene2;
	}

	public void setNomCam57BanBene2(String nomCam57BanBene2) {
		this.NomCam57BanBene2 = nomCam57BanBene2;
	}

	public String getNomCam57BanBene3() {
		return this.NomCam57BanBene3;
	}

	public void setNomCam57BanBene3(String nomCam57BanBene3) {
		this.NomCam57BanBene3 = nomCam57BanBene3;
	}

	public String getNomCam57BanBene4() {
		return this.NomCam57BanBene4;
	}

	public void setNomCam57BanBene4(String nomCam57BanBene4) {
		this.NomCam57BanBene4 = nomCam57BanBene4;
	}

	public String getNomCam59Bene1() {
		return this.NomCam59Bene1;
	}

	public void setNomCam59Bene1(String nomCam59Bene1) {
		this.NomCam59Bene1 = nomCam59Bene1;
	}

	public String getNomCam59Bene2() {
		return this.NomCam59Bene2;
	}

	public void setNomCam59Bene2(String nomCam59Bene2) {
		this.NomCam59Bene2 = nomCam59Bene2;
	}

	public String getNomCam59Bene3() {
		return this.NomCam59Bene3;
	}

	public void setNomCam59Bene3(String nomCam59Bene3) {
		this.NomCam59Bene3 = nomCam59Bene3;
	}

	public String getNomCam59Bene4() {
		return this.NomCam59Bene4;
	}

	public void setNomCam59Bene4(String nomCam59Bene4) {
		this.NomCam59Bene4 = nomCam59Bene4;
	}

	public String getNoOperac() {
		return this.NoOperac;
	}

	public void setNoOperac(String noOperac) {
		this.NoOperac = noOperac;
	}

	public String getNoOperac1() {
		return this.NoOperac1;
	}

	public void setNoOperac1(String noOperac1) {
		this.NoOperac1 = noOperac1;
	}

	public String getNoVended() {
		return this.NoVended;
	}

	public void setNoVended(String noVended) {
		this.NoVended = noVended;
	}

	public String getNuCambiario() {
		return this.NuCambiario;
	}

	public void setNuCambiario(String nuCambiario) {
		this.NuCambiario = nuCambiario;
	}

	public String getSpreadMaximo() {
		return this.spreadMaximo;
	}

	public void setSpreadMaximo(String spreadMaximo) {
		this.spreadMaximo = spreadMaximo;
	}

	public String getSpreadMinimo() {
		return this.SpreadMinimo;
	}

	public void setSpreadMinimo(String spreadMinimo) {
		this.SpreadMinimo = spreadMinimo;
	}

	public String getSubcanal() {
		return this.Subcanal;
	}

	public void setSubcanal(String subcanal) {
		this.Subcanal = subcanal;
	}

	public String getSwBcoOrd() {
		return this.SwBcoOrd;
	}

	public void setSwBcoOrd(String swBcoOrd) {
		this.SwBcoOrd = swBcoOrd;
	}

	public String getSwiftcampo56A() {
		return this.Swiftcampo56A;
	}

	public void setSwiftcampo56A(String swiftcampo56A) {
		this.Swiftcampo56A = swiftcampo56A;
	}

	public String getSwiftcampo57A() {
		return this.Swiftcampo57A;
	}

	public void setSwiftcampo57A(String swiftcampo57A) {
		this.Swiftcampo57A = swiftcampo57A;
	}

	public String getTasaAvance() {
		return this.TasaAvance;
	}

	public void setTasaAvance(String tasaAvance) {
		this.TasaAvance = tasaAvance;
	}

	public Double getTasaDivi() {
		return this.TasaDivi;
	}

	public void setTasaDivi(Double tasaDivi) {
		this.TasaDivi = tasaDivi;
	}

	public Double getTasaLinea() {
		return this.TasaLinea;
	}

	public void setTasaLinea(Double tasaLinea) {
		this.TasaLinea = tasaLinea;
	}

	public Double getTasaUsd() {
		return this.TasaUsd;
	}

	public void setTasaUsd(Double tasaUsd) {
		this.TasaUsd = tasaUsd;
	}

	public String getTpCambioComm() {
		return this.TpCambioComm;
	}

	public void setTpCambioComm(String tpCambioComm) {
		this.TpCambioComm = tpCambioComm;
	}

	public String getTyNegoci() {
		return this.TyNegoci;
	}

	public void setTyNegoci(String tyNegoci) {
		this.TyNegoci = tyNegoci;
	}

	public String getTyOperac() {
		return this.TyOperac;
	}

	public void setTyOperac(String tyOperac) {
		this.TyOperac = tyOperac;
	}

	public String getTyTransa() {
		return this.TyTransa;
	}

	public void setTyTransa(String tyTransa) {
		this.TyTransa = tyTransa;
	}

	public String getTyUsuario() {
		return this.TyUsuario;
	}

	public void setTyUsuario(String tyUsuario) {
		this.TyUsuario = tyUsuario;
	}

	public String getTyVended() {
		return this.TyVended;
	}

	public void setTyVended(String tyVended) {
		this.TyVended = tyVended;
	}

	public String getDeComm() {
		return this.DeComm;
	}

	public void setDeComm(String deComm) {
		this.DeComm = deComm;
	}
	
	public String getUsdRateComm() {
		return UsdRateComm;
	}

	public void setUsdRateComm(String usdRateComm) {
		UsdRateComm = usdRateComm;
	}

	public String getMontoNetoPse() {
		return MontoNetoPse;
	}

	public void setMontoNetoPse(String montoNetoPse) {
		MontoNetoPse = montoNetoPse;
	}

	public String toString() {
		String respuestaXML = "<Message><Header><TyOperac>"
				+ (this.getTyOperac() != null ? this.getTyOperac().toString() : "") + "</TyOperac>" + "</Header>"
				+ "<Body>" + "<DeOperac>" + (this.getDeOperac() != null ? this.getDeOperac().toString() : "")
				+ "</DeOperac>" + "<IdCliente>" + (this.getIdCliente() != null ? this.getIdCliente().toString() : "")
				+ "</IdCliente>" + "<MonNegoc>" + (this.getMonNegoc() != null ? this.getMonNegoc().toString() : "")
				+ "</MonNegoc>" + "<CurNegoc>" + (this.getCurNegoc() != null ? this.getCurNegoc().toString() : "")
				+ "</CurNegoc>" + "<NoOperac>" + (this.getNoOperac() != null ? this.getNoOperac().toString() : "")
				+ "</NoOperac>" + "<TasaDivi>" + (this.getTasaDivi() != null ? this.getTasaDivi().toString() : "")
				+ "</TasaDivi>" + "<TasaUsd>" + (this.getTasaUsd() != null ? this.getTasaUsd().toString() : "")
				+ "</TasaUsd>" + "<TasaLinea>" + (this.getTasaLinea() != null ? this.getTasaLinea().toString() : "")
				+ "</TasaLinea>" + "<Canal>" + (this.getCanal() != null ? this.getCanal().toString() : "") + "</Canal>"
				+ "<Subcanal>" + (this.getSubcanal() != null ? this.getSubcanal().toString() : "") + "</Subcanal>"
				+ "<Campana>" + (this.getCampana() != null ? this.getCampana().toString() : "") + "</Campana>"
				+ "<TyVended>" + (this.getTyVended() != null ? this.getTyVended().toString() : "") + "</TyVended>"
				+ "<NoVended>" + (this.getNoVended() != null ? this.getNoVended().toString() : "") + "</NoVended>"
				+ "<DigVended>" + (this.getDigVended() != null ? this.getDigVended().toString() : "") + "</DigVended>"
				+ "<TyNegoci>" + (this.getTyNegoci() != null ? this.getTyNegoci().toString() : "") + "</TyNegoci>"
				+ "<NoAvance>" + (this.getNoAvance() != null ? this.getNoAvance().toString() : "") + "</NoAvance>"
				+ "<MonAvance>" + (this.getMonAvance() != null ? this.getMonAvance().toString() : "") + "</MonAvance>"
				+ "<TasaAvance>" + (this.getTasaAvance() != null ? this.getTasaAvance().toString() : "")
				+ "</TasaAvance>" + "<NuCambiario>"
				+ (this.getNuCambiario() != null ? this.getNuCambiario().toString() : "") + "</NuCambiario>"
				+ "<TyTransa>" + (this.getTyTransa() != null ? this.getTyTransa().toString() : "") + "</TyTransa>"
				+ "<NoOperac1>" + (this.getNoOperac1() != null ? this.getNoOperac1().toString() : "") + "</NoOperac1>"
				+ "<IdSucursal>" + (this.getIdSucursal() != null ? this.getIdSucursal().toString() : "")
				+ "</IdSucursal>" + "<FeValor>"
				+ (this.getFeValor() != null ? this.FORMATO_FECHA.format(this.getFeValor()) : "") + "</FeValor>"
				+ "<TyUsuario>" + (this.getTyUsuario() != null ? this.getTyUsuario().toString() : "") + "</TyUsuario>"
				+ "<FeNegoci>"
				+ (this.getFechaHoraNegociacion() != null ? this.FORMATO_FECHA.format(this.getFechaHoraNegociacion())
						: "")
				+ "</FeNegoci>" + "<HoraNegoci>"
				+ (this.getFechaHoraNegociacion() != null ? this.FORMATO_HORA.format(this.getFechaHoraNegociacion())
						: "")
				+ "</HoraNegoci>" + "<SpreadMinimo>"
				+ (this.getSpreadMinimo() != null ? this.getSpreadMinimo().toString() : "") + "</SpreadMinimo>"
				+ "<spreadMaximo>" + (this.getSpreadMaximo() != null ? this.getSpreadMaximo().toString() : "")
				+ "</spreadMaximo>" + "<IndMoneda>"
				+ (this.getIndMoneda() != null ? this.getIndMoneda().toString() : "") + "</IndMoneda>"
				+ "<ID.BCOOrdenante>" + (this.getID_BCOOrdenante() != null ? this.getID_BCOOrdenante().toString() : "")
				+ "</ID.BCOOrdenante>" + "<NomBcoOrd1>"
				+ (this.getNomBcoOrd1() != null ? this.getNomBcoOrd1().toString() : "") + "</NomBcoOrd1>"
				+ "<NomBcoOrd2>" + (this.getNomBcoOrd2() != null ? this.getNomBcoOrd2().toString() : "")
				+ "</NomBcoOrd2>" + "<NomBcoOrd3>"
				+ (this.getNomBcoOrd3() != null ? this.getNomBcoOrd3().toString() : "") + "</NomBcoOrd3>"
				+ "<NomBcoOrd4>" + (this.getNomBcoOrd4() != null ? this.getNomBcoOrd4().toString() : "")
				+ "</NomBcoOrd4>" + "<IdSwTpBcoOrd>"
				+ (this.getIdSwTpBcoOrd() != null ? this.getIdSwTpBcoOrd().toString() : "") + "</IdSwTpBcoOrd>"
				+ "<SwBcoOrd>" + (this.getSwBcoOrd() != null ? this.getSwBcoOrd().toString() : "") + "</SwBcoOrd>"
				+ "<DeComm>" + (this.getDeComm() != null ? this.getDeComm().toString() : "") + "</DeComm>"
				+ "<NoCtaComm>" + (this.getNoCtaComm() != null ? this.getNoCtaComm().toString() : "") + "</NoCtaComm>"
				+ "<CurDbComm>" + (this.getCurDbComm() != null ? this.getCurDbComm().toString() : "") + "</CurDbComm>"
				+ "<TpCambioComm>" + (this.getTpCambioComm() != null ? this.getTpCambioComm().toString() : "")
				+ "</TpCambioComm>" + "<DbPrincipal>"
				+ (this.getDbPrincipal() != null ? this.getDbPrincipal().toString() : "") + "</DbPrincipal>"
				+ "<NoCtaPrincipal>" + (this.getNoCtaPrincipal() != null ? this.getNoCtaPrincipal().toString() : "")
				+ "</NoCtaPrincipal>" + "<NoCtaPrincipalCre>"
				+ (this.getNoCtaPrincipalCre() != null ? this.getNoCtaPrincipalCre().toString() : "")
				+ "</NoCtaPrincipalCre>" + "<IdCampo59>"
				+ (this.getIdCampo59() != null ? this.getIdCampo59().toString() : "") + "</IdCampo59>"
				+"<IdCampo72>"
				+ (this.getIdCampo72() != null ? this.getIdCampo72().toString() : "") + "</IdCampo72>"
				+ "<NomCam59Bene1>" + (this.getNomCam59Bene1() != null ? this.getNomCam59Bene1().toString() : "")
				+ "</NomCam59Bene1>" + "<NomCam59Bene2>"
				+ (this.getNomCam59Bene2() != null ? this.getNomCam59Bene2().toString() : "") + "</NomCam59Bene2>"
				+ "<NomCam59Bene3>" + (this.getNomCam59Bene3() != null ? this.getNomCam59Bene3().toString() : "")
				+ "</NomCam59Bene3>" + "<NomCam59Bene4>"
				+ (this.getNomCam59Bene4() != null ? this.getNomCam59Bene4().toString() : "") + "</NomCam59Bene4>"
				+ "<Infcam70Sw1>" + (this.getInfcam70Sw1() != null ? this.getInfcam70Sw1().toString() : "")
				+ "</Infcam70Sw1>" + "<Infcam70Sw2>"
				+ (this.getInfcam70Sw2() != null ? this.getInfcam70Sw2().toString() : "") + "</Infcam70Sw2>"
				+ "<Infcam70Sw3>" + (this.getInfcam70Sw3() != null ? this.getInfcam70Sw3().toString() : "")
				+ "</Infcam70Sw3>" + "<Infcam70Sw4>"
				+ (this.getInfcam70Sw4() != null ? this.getInfcam70Sw4().toString() : "") + "</Infcam70Sw4>"
				+ "<IDcampo56>" + (this.getIDcampo56() != null ? this.getIDcampo56().toString() : "") + "</IDcampo56>"
				+ "<NomCam56InfBene1>"
				+ (this.getNomCam56InfBene1() != null ? this.getNomCam56InfBene1().toString() : "")
				+ "</NomCam56InfBene1>" + "<NomCam56InfBene2>"
				+ (this.getNomCam56InfBene2() != null ? this.getNomCam56InfBene2().toString() : "")
				+ "</NomCam56InfBene2>" + "<NomCam56InfBene3>"
				+ (this.getNomCam56InfBene3() != null ? this.getNomCam56InfBene3().toString() : "")
				+ "</NomCam56InfBene3>" + "<NomCam56InfBene4>"
				+ (this.getNomCam56InfBene4() != null ? this.getNomCam56InfBene4().toString() : "")
				+ "</NomCam56InfBene4>" + "<IdTyCampo56Sw>"
				+ (this.getIdTyCampo56Sw() != null ? this.getIdTyCampo56Sw().toString() : "") + "</IdTyCampo56Sw>"
				+ "<Swiftcampo56A>" + (this.getSwiftcampo56A() != null ? this.getSwiftcampo56A().toString() : "")
				+ "</Swiftcampo56A>" + "<IDcampo57>"
				+ (this.getIDcampo57() != null ? this.getIDcampo57().toString() : "") + "</IDcampo57>"
				+ "<NomCam57BanBene1>"
				+ (this.getNomCam57BanBene1() != null ? this.getNomCam57BanBene1().toString() : "")
				+ "</NomCam57BanBene1>" + "<NomCam57BanBene2>"
				+ (this.getNomCam57BanBene2() != null ? this.getNomCam57BanBene2().toString() : "")
				+ "</NomCam57BanBene2>" + "<NomCam57BanBene3>"
				+ (this.getNomCam57BanBene3() != null ? this.getNomCam57BanBene3().toString() : "")
				+ "</NomCam57BanBene3>" + "<NomCam57BanBene4>"
				+ (this.getNomCam57BanBene4() != null ? this.getNomCam57BanBene4().toString() : "")
				+ "</NomCam57BanBene4>" + "<IdTyCampo57Sw>"
				+ (this.getIdTyCampo57Sw() != null ? this.getIdTyCampo57Sw().toString() : "") + "</IdTyCampo57Sw>"
				+ "<Swiftcampo57A>" + (this.getSwiftcampo57A() != null ? this.getSwiftcampo57A().toString() : "")
				+ "</Swiftcampo57A>" + "<CodOpecampo23b>"
				+ (this.getCodOpecampo23b() != null ? this.getCodOpecampo23b().toString() : "") + "</CodOpecampo23b>"
				+ "<UsdRateComm>" + (this.getUsdRateComm() != null ? this.getUsdRateComm().toString() : "") + "</UsdRateComm>"
				+ "<MontoNetoPse>" + (this.getMontoNetoPse() != null ? this.getMontoNetoPse().toString() : "") + "</MontoNetoPse>"
				+ "</Body>" + "</Message>";
		return respuestaXML;
	}
	
	

	public String usuarioNetADocBT(String userNet) {
		int longitudUser = userNet.length();
		String tipoDoc = userNet.substring(longitudUser - 17, longitudUser - 15);
		if (tipoDoc.equalsIgnoreCase("CC")) {
			tipoDoc = "1";
		} else if (tipoDoc.equalsIgnoreCase("CE")) {
			tipoDoc = "2";
		} else if (tipoDoc.equalsIgnoreCase("TI")) {
			tipoDoc = "4";
		} else if (tipoDoc.equalsIgnoreCase("PA")) {
			tipoDoc = "5";
		} else if (tipoDoc.equalsIgnoreCase("NIP")) {
			tipoDoc = "0";
		} else {
			tipoDoc = "_";
		}

		String documento = "" + Long.parseLong(userNet.substring(longitudUser - 15));
		String digVerificacion = "0";
		return tipoDoc + documento + digVerificacion;
	}
}