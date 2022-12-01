package com.grupobbva.bc.col.web.comercioext;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat; //INI INC AVANCE 0 FX 16-07-2018
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.axis.AxisFault;//VARIOS NITS 2 TOUT CMC 17-02-2020

import com.bbva.client.banktrade.dacollector.DacollectorService;
import com.bbva.client.banktrade.dacollector.types.UploadFilePet;
import com.bbva.client.banktrade.dacollector.types.UploadFileResp;
import com.grupobbva.bc.col.web.comercioext.clientews.RequestBankTradeService;
import com.grupobbva.bc.col.web.comercioext.clientews.ResponseBankTradeService;
import com.grupobbva.bc.col.web.comercioext.clientews.WrapperBanktradeService;
import com.grupobbva.bc.col.web.pse.MetodoEnum;
import com.grupobbva.bc.col.web.pse.UsuariosPseDto;
import com.grupobbva.bc.col.web.utilidades.OpControl;//CMC - FUNCION DE NEGOCIO FNGU - PRUEBA - 27/09/2019
import com.grupobbva.col.rest.conector.dto.comisionService.Beneficiary;
import com.grupobbva.col.rest.conector.dto.comisionService.Datum;
import com.grupobbva.col.rest.conector.dto.payment.MapPayment;
import com.grupobbva.col.rest.conector.dto.payment.PaymentsServiceRequest;
import com.grupobbva.col.rest.conector.dto.payment.PaymentsServiceResponse;
import com.grupobbva.col.rest.conector.om.MetodosPayment;
import com.grupobbva.col.servicios.OpGestion_xhr_rs7;
import com.grupobbva.ii.sf.base.BbvaARQException;
import com.grupobbva.ii.sf.base.BbvaException;
import com.grupobbva.ii.sf.operacion.OperacionMulticanal;
import com.grupobbva.multidioma.CatalogoMultidioma;
import com.ibm.dse.base.DSEInvalidArgumentException; //INI -VARIOS NIT F2 - OSNEIDER A. CMC - 02-10-2019
import com.ibm.dse.base.DSEObjectNotFoundException;
import com.ibm.dse.base.DataElement;
import com.ibm.dse.base.IndexedCollection;
import com.ibm.dse.base.KeyedCollection;
import com.ibm.dse.base.Trace;
import com.ibm.dse.base.Vector;
/**
 * @author C342172
 * PROYECTO PLATAFORMA FX
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class OpFinalizarOperaciones extends OpGestion_xhr_rs7 {
	
	String sOmCotizador ="consulta_tasas_operaciones_negociacion_om";
	String sOmGiros ="consulta_operaciones_negociacion_om";
	String sOmParametria ="consulta_detalle_operaciones_negociacion_om";
	
	String sOmAltaBeneficiarios = "alta_beneficiarios_om";
	String sOmConsultaBeneficiarios = "compra_divisas_consulta_beneficiarios_om";
	
	String sOmEstadoCta= "compra_divisas_consulta_estado_cuenta_om";
	String sOmConsultaAvance = "compra_divisas_consulta_avances_om";
	String sOmConsultaUnicaOperaciones = "divisas_consulta_unica_operaciones_om";
	String sOmOficinaTitular= "compra_divisas_consulta_centro_oficina_om";
	
	private String BT_IDSUCURSAL = "0073";
	private String documentoCliente = "";
	private String refExtranjero = "";
	private String numOperacion = "";


	private final String ERROR_WS_BANKTRADE = "Fallo llamado Banktrade - ";
	private OpControl controlOM;//CMC - FUNCION DE NEGOCIO FNGU - PRUEBA - 27/09/2019
	private static final String CURRENCY_COP="COP";
	private static final String TIPO_FONDO_GIRO="tipoFondoGiro";
	private static final String FONDO_PSE = "fondoPSE";
	private static final String PSE = "PSE";
	private static final String MENSAJE_ERROR = "### generarReferenciaOperacion() PASO_1 - error de web service: RTA null";
	private static final String TASA_DIVISA_PESO="tasaDivisaPeso";
	private static final String TASA_USD_PESO="tasaUsdPeso";
	private static final String TASA_DIVISA="tasaDivisa";
	private static final String AVANCE_OPE="avanceOpe";
	private static final String CONSULTA_OM_PSE = "consulta_pagos_pse_divisa_om";
	private static final String ENTRADA_METODO = "Entrada.metodo";
	private static final String SALIDA_LISTAR_USURORDE = "Salida.listarUsuarioOrden.usuarios";
	private static final String ENTRADA_ACTUALREINTENTO = "Entrada.actualizarReintento";
	private static final String VALUE_STATUS = "status";
	private static final String EQUIPES = "EQUIPES";
	private static final String VALUE_ORDEDANTE = "ordenante";
	private static final String VALUE_BENEFI = "beneficiario";
	private static final String VALUE_COD_USUA = "s_cod_usuarisc";
	private static final String VALUE_RESULTADO = "Resultado";
	private static final String SOCKET_TIMEOUT_EXCE = "SocketTimeoutException";
	private static final String CONNECT_TIMED_OUT = "connect timed out";
	private static final String CODIGO_CONS_BANCO = "codigoConsBanco";
	private static final String OPERATION_OBJ = "operationObj";
	private static final String PROCESO_BANCO = "Proceso Banco";
	private static final String VALUE_COMISION = "comision";
	private static final String NUM_TRANS = "numTrans";
	private static final String VALUE_ORIGIN = "origin";
	private static final String VALUE_TOTAL_DEBI = "totalDebito";
	private static final String VALUE_EQUIV_PESOS = "equivaPesos";
	private static final String ORDENA = "ORDENA";
	private static final String CUENTABON = "CUENTABON";
	private static final String VALUE_SIGNO = "^0+(?!$)";
	private static final String VALUE_FIRMA = "firma";
	private static final String NIT_COMPLETO = "NIT_COMPLETO";
	private static final String NOMBRE_CLIENTE = "NOMBRE_CLIENTE";
	private static final String VALUE_FIJO_NIT = "Fijo_Nit";
	private static final String VALUE_FIJO_NOMBRE = "Fijo_Nombre";
	private static final String ENTRADA_BCODACCC = "Entrada.BCODACCC";
	private static final String VALUE_LOGON = "s_cod_logon";
	private static final String OM_PAYMENTS_SERVICE = "om_payments_service";
	
	public OpFinalizarOperaciones() {
	    super();
	}

	public OpFinalizarOperaciones(String anOperationName)
	    throws java.io.IOException {
	    super(anOperationName);
	}

	public OpFinalizarOperaciones(
	    String anOperationName,
	    com.ibm.dse.base.Context aParentContext)
	    throws java.io.IOException, com.ibm.dse.base.DSEInvalidRequestException {
	    super(anOperationName, aParentContext);
	}
	
	public void inicio() throws Exception{
		Trace.trace(Trace.Debug, "", " Inicio OpFinalizarOperaciones(). PASO_2");
		String selectMoneda = (String) getValueAt("selectMoneda");
				
		setValueAt("selectOpe", getValueAt("selectOpe"));
		Trace.trace(Trace.Debug, "", "###  selectOpe " + getValueAt("selectOpe"));
		setValueAt("selectCuentaOrden", getValueAt("selectCuentaOrden"));
		Trace.trace(Trace.Debug, "", "###  selectCuentaOrden " + getValueAt("selectCuentaOrden"));
		if(selectMoneda!= null && selectMoneda!= ""){
		    if (selectMoneda.length() > 2) {//INI incidencia CMC FX 02/02/2018
			selectMoneda= selectMoneda.substring(0,3);
		    }//FIN incidencia CMC FX 02/02/2018
			}
		setValueAt("selectMoneda", selectMoneda);
		Trace.trace(Trace.Debug, "", "###  selectMoneda " + getValueAt("selectMoneda"));
		setValueAt("monto", getValueAt("monto"));
		Trace.trace(Trace.Debug, "", "###  monto " + getValueAt("monto"));
		setValueAt("descripNegociacion", getValueAt("descripNegociacion"));
		Trace.trace(Trace.Debug, "", "###  descripNegociacion " + getValueAt("descripNegociacion"));
		
		setValueAt("referenciaOpe", getValueAt("referenciaOpe"));
		Trace.trace(Trace.Debug, "", "###  referenciaOpe " + getValueAt("referenciaOpe"));
		setValueAt("docBenefi", getValueAt("docBenefi"));
		Trace.trace(Trace.Debug, "", "###  docBenefi " + getValueAt("docBenefi"));
		setValueAt("errorBeneficiario","");
		String tipoFondoGiro =(String) getValueAt(TIPO_FONDO_GIRO);
		Trace.trace(Trace.Debug, "", "###  tipoFondoGiro " + tipoFondoGiro);
		setValueAt(TIPO_FONDO_GIRO,tipoFondoGiro);
		
		setValueAt("errorBeneficiario","");
		String campos = "nombresbenefic@direccionebenef@paisbeneficiar@ciudadbenefcia@"
				+ "telefonobenefi@tiposabanco1@codigosabanco1@cuentabenefica@codigoUnitario";
		int numCampos = verificarBeneficiarioSeleccionado(campos);
		Trace.trace(Trace.Debug, "", "### numCampos " + numCampos);
		if(numCampos>0) {
			setValueAt("errorBeneficiario","S");
			setEstado("9");
			return;
		}
		
		updatePaso2(""); /*INC 61 Fx - 30-05-2018*/


		//INI incidencia 225B FX CMC 30/10/2019
		try{
			if(getValueAt("errorCodigo").equals("N")) {
				if(getValueAt("msjError").equals("")){
					setEstado("0");
				}else{
					this.setEstado("ERROR");
					ManejarExcepcion(3, "ERROR EN BENEFICIARIO - BANKTRADE", "SISTEMA TEMPORALMENTE NO DISPONIBLE", "", null, "", this, "", "");
					return;
				}
			}else {
				setEstado("12");
			}			
		}catch(Exception e){
			Trace.trace(Trace.Error, "", "******************** ERROR inicio()"+ e);
		}
		//FIN incidencia 225B FX CMC 30/10/2019
		Trace.trace(Trace.Debug, "", " Fin OpFinalizarOperaciones(). PASO_2");
	}
	
	public int updatePaso2(String flujo) throws Exception{/*INC 61 Fx - 30-05-2018*/
		Trace.trace(Trace.Debug, "", " Inicio updatePaso2(). PASO_2" );
		Date fechaActual = new Date();				
		
		String documentofull = (String) getValueAt("docBenefi");
		String[] parts = documentofull.split("\\|@\\|");//Incidencia 16 CMC FX 06/02/2018
		int valor=0;/*INC 61 Fx - 30-05-2018*/
		String tipoId = parts[0];
		String numId = parts[1];
		String digVer = parts[2];
		String ctaBenef = parts[3];
				
		String sSelectBeneficiario = tipoId + numId + digVer;
		String sSelectCuentaB = ctaBenef;	
		String sSelectCuentaOrden = (String) getValueAt("selectCuentaOrden");
		String deCTA = tipoCuentaBT(sSelectCuentaOrden);
		String tipoFondoGiro = getValueAtOfdefault(TIPO_FONDO_GIRO, "");
		String nitSelect = "";

		RequestBankTradeService peticion = null;
		
		// ojo al flujo de moficicar // 
		peticion = (RequestBankTradeService)getValueAt("PeticionWebService");
		Trace.trace(Trace.Debug, "", "### updatePaso2(). imprimiendo deCTA " + deCTA);
		Trace.trace(Trace.Debug, "", "### updatePaso2(). peticion.getDeOperac() " + peticion.getDeOperac().toString() );
		Trace.trace(Trace.Debug, "", "### updatePaso2(). peticion.referenciaOpe() " + getValueAt("referenciaOpe").toString() );
		if(tipoFondoGiro.equalsIgnoreCase(FONDO_PSE)) {
			deCTA = PSE;
			nitSelect = peticion.getIdCliente();
			sSelectCuentaOrden = peticion.getIdCliente();
		}
		peticion=setOpenType(peticion,sSelectBeneficiario,sSelectCuentaB,sSelectCuentaOrden);
		//FIN incidencia 181 FX CMC 12/02/2019
		if(!nitSelect.equalsIgnoreCase(""))
			peticion.setNoCtaComm(nitSelect);
		else
			peticion.setNoCtaComm(sSelectCuentaOrden);
		peticion.setDeComm(deCTA);
		peticion.setNoCtaPrincipal(sSelectCuentaOrden);
		peticion.setNoCtaPrincipalCre(sSelectCuentaOrden);//INC BT15.1 PRD FX 05-07-2019
		if(getValueAt("referenciaOpe").toString().equals("")){
			setValueAt("referenciaOpe",peticion.getNoOperac1());
		}
		
		peticion.setNoOperac1(getValueAt("referenciaOpe").toString());//Incidencia 153 FX CMC 21/01/2019
		peticion.setCanal("80");
		peticion.setTyUsuario("JR");
		peticion.setFechaHoraNegociacion(fechaActual);
		peticion.setCurDbComm("COP");
		peticion.setTpCambioComm("1.000000000");												
		peticion.setCodOpecampo23b("CRED");
		peticion.setTyNegoci("**");
		// se ingresa un Numeral Cambiario temporal		
		
		/* INI INC 61 Fx - 30-05-2018*/
		if(flujo.equalsIgnoreCase("pte"))
		{
			Trace.trace(Trace.Debug, "", "### updatePaso2() entra a condicional para IndMoneda P : " + flujo);
			peticion.setIndMoneda("P");
		}
		else
		{			
			Trace.trace(Trace.Debug, "", "### updatePaso2() entra a condicional para IndMoneda + NuCambiario 5E02904 : " + flujo);
			peticion.setNuCambiario("5E02904"); /* INC 61 Fx - 06-06-2018 */
			peticion.setIndMoneda("N"); //INC 77 FX PRUEBA 31-08-2018
			/*FIN INC 61 Fx - 30-05-2018*/
		}
		
		peticion.setID_BCOOrdenante(null);
		peticion.setNomBcoOrd1(null);
		peticion.setIdSucursal(null);
		//peticion.setDeOperac(null);
		peticion.setSubcanal(null);
		peticion.setCampana(null);
		peticion.setTyVended(null);
		peticion.setNoVended(null);
		peticion.setDigVended(null);
		peticion.setIdSwTpBcoOrd("SW");
		
		Trace.trace(Trace.Debug, "", "### updatePaso2() Data xml:  " + peticion.toString());
				
		Trace.trace(Trace.Debug, "", "### updatePaso2() PASO_2 - a lanzar ws-...  ");
		
		valor=processErrorUpdatePaso2(peticion,flujo,valor);
		
		
		setValueAt("msjError", "");
		setValueAt("PeticionWebService", peticion);
		Trace.trace(Trace.Debug, "", " Fin updatePaso2(). PASO_2");
		return valor;/*INC 61 Fx - 30-05-2018*/
	}
	
	public void finalizar() throws Exception {
		Trace.trace(Trace.Debug, "", " iniciar finalizar()");
		try {
			String tipoFondoGiro = (String) getValueAt(TIPO_FONDO_GIRO);
			if(tipoFondoGiro.equalsIgnoreCase(FONDO_PSE)) {
				Trace.trace(Trace.Debug, "", " iniciar finalizar() PSE");
				Datum comisionService = getObjectValue(OPERATION_OBJ, Datum.class);
				String statusPse=comisionService.getPseStatus();
				if(statusPse.equalsIgnoreCase("COMPLETED")) {
					finalizarPse();
				}else {
					finalizarNegociacion();
				}
			}else {
				finalizarNegociacion();
			}
		}catch (Exception e) {
			finalizarNegociacion();
		}
		Trace.trace(Trace.Debug, "", " fin finalizar()");
	}
	
	
    public void finalizarNegociacion() throws Exception
    {
		Trace.trace(Trace.Debug, "", " Inicio finalizar().");
		
		String selectMoneda = (String) getValueAt("selectMoneda");
				
		Trace.trace(Trace.Debug, "", "### selectOpe " + getValueAt("selectOpe"));
		setValueAt("selectCuentaOrden", getValueAt("selectCuentaOrden"));
		String sSelectCuentaOrden = (String) getValueAt("selectCuentaOrden");
		String sTipoCtaOrden = tipoCuentaFirmas(sSelectCuentaOrden);
		Trace.trace(Trace.Debug, "", "### selectCuentaOrden " + sTipoCtaOrden+sSelectCuentaOrden);
        if(selectMoneda!= null && selectMoneda!= "")
        {
	    if(selectMoneda.length() > 2) {//INI incidencia CMC FX 02/02/2018
			selectMoneda= selectMoneda.substring(0,3);
	    }//FIN incidencia CMC FX 02/02/2018
			}
		setValueAt("selectMoneda", selectMoneda);
		Trace.trace(Trace.Debug, "", "### selectMoneda " + getValueAt("selectMoneda"));
		setValueAt("monto", getValueAt("monto"));
		Trace.trace(Trace.Debug, "", "### monto " + getValueAt("monto"));
		setValueAt("descripNegociacion", getValueAt("descripNegociacion"));
		Trace.trace(Trace.Debug, "", "### descripNegociacion " + getValueAt("descripNegociacion"));
		
		
		setValueAt("referenciaOpe", getValueAt("referenciaOpe"));
		Trace.trace(Trace.Debug, "", "### referenciaOpe " + getValueAt("referenciaOpe"));
		setValueAt("docBenefi", getValueAt("docBenefi"));
		Trace.trace(Trace.Debug, "", "### docBenefi " + getValueAt("docBenefi"));
		
		setValueAt("Formulario", getValueAt("Formulario"));
		Trace.trace(Trace.Debug, "", "### Formulario " + getValueAt("Formulario"));
		setValueAt("NumeralC", getValueAt("NumeralC"));
		Trace.trace(Trace.Debug, "", "### NumeralC " + getValueAt("NumeralC"));
		setValueAt("descNumeral", getValueAt("descNumeral"));
		Trace.trace(Trace.Debug, "", "### descNumeral " + getValueAt("descNumeral"));
		Trace.trace(Trace.Debug, "", "################### " + getValueAt("num_cuenta") + " ########################");
		mapModificarPse();
		updatePaso3();
        /* INI Alcance Divisas CMC Archivos 29/01/2018 */
		try
		{	/*INI incidencia 121 FX CMC 20/11/2018
	        Trace.trace(Trace.Debug, "", "### Archivo 1 " + getValueAt("file1"));
	        Trace.trace(Trace.Debug, "", "### Archivo 2 " + getValueAt("file2"));
	        Trace.trace(Trace.Debug, "", "### Archivo 3 " + getValueAt("file3"));
	        Trace.trace(Trace.Debug, "", "### Archivo 4 " + getValueAt("file4"));
	        Trace.trace(Trace.Debug, "", "### Archivo 5 " + getValueAt("file5"));
	        Trace.trace(Trace.Debug, "", "### Archivo 6 " + getValueAt("file6"));
	        Trace.trace(Trace.Debug, "", "### Archivo 7 " + getValueAt("file7"));
	        Trace.trace(Trace.Debug, "", "### Archivo 8 " + getValueAt("file8"));
	        Trace.trace(Trace.Debug, "", "### Archivo 9 " + getValueAt("file9"));
	        Trace.trace(Trace.Debug, "", "### Archivo 10 " + getValueAt("file10"));
			*/
			Trace.trace(Trace.Debug, "", "### Carga de archivos");
			//FIN incidencia 121 FX CMC 20/11/2018
	        Trace.trace(Trace.Debug, "", "### Tamaños y extension de archivos " + getValueAt("trazaExtAndSize"));
	        
		    String trama = (String) getValueAt("trazaExtAndSize");
			String nombreBase = "Archivo adjunto ", nombre="";
		    String datosArchivos[] = trama.split("@");
		    Trace.trace(Trace.Debug, "", "### Pasa el primer Split - Longitud array " + datosArchivos.length);
		    nombre =getNameByFile(datosArchivos,nombreBase);
		}
		catch (Exception e)
		{
			Trace.trace(Trace.Debug, "", "### enviarImagenesbas64() Declaracion - Falla envio parametros: " + e);
		}
		/* FIN Alcance Divisas CMC Archivos 29/01/2018 */
        
        if(getValueAt("selectOpe").equals("T2") || getValueAt("selectOpe").equals("H2"))
        {
            cargarEquivalentePesos((String)getValueAt("selectCuentaOrden"), (String) getValueAt("selectOpe"), (String) getValueAt("referenciaOpe"));
        }
        
        if(getValueAt("msjError").equals(""))
        {
            setEstado("1");
            consultarfirma(sTipoCtaOrden,sSelectCuentaOrden);
            Trace.trace(Trace.Debug, "", "### consultarfirma() " + getValueAt(VALUE_FIRMA) );
		// INI -VARIOS NIT F2 - OSNEIDER A. CMC - 02-10-2019
        Trace.trace(Trace.Information, getClass().getName(), "OMAN-INICIO: finalizar(): Se valida tipo de operacion: "+getValueAt("selectOpe").toString() );
        String TipoOpe="";
        if(getValueAt("selectOpe").equals("T2") || getValueAt("selectOpe").equals("T1"))
        {
        	TipoOpe="T";
        }else if(getValueAt("selectOpe").equals("H2") || getValueAt("selectOpe").equals("H1")) {
        	TipoOpe="H";
        }
           setPayeeInfo(TipoOpe,sSelectCuentaOrden);
		}
		// FIN -VARIOS NIT F2 - OSNEIDER A. CMC - 02-10-2019
		Trace.trace(Trace.Debug, "", " Fin finalizar()");
	}
	
	public void cargarEquivalentePesos(String selectCuentaOrden, String selectOpe, String referenciaOpe) throws Exception{
		
		try{
			Trace.trace(Trace.Information, getClass().getName(), "OMAN-cargarEquivalentePesos(): referenciaOpe:"+referenciaOpe);//VARIOS NIT F2 - OSNEIDER A. CMC - 02-10-2019
			IndexedCollection listaGirosOM;
		
			selectOpe.replaceAll("2", "");
			Trace.trace(Trace.Debug, "", " Inicio cargarEquivalentePesos() " + selectOpe + referenciaOpe);
		
			OperacionMulticanal OMConsultaOper = creaOM(sOmConsultaUnicaOperaciones);
			copiarAOMConsultaUnica(referenciaOpe, selectOpe);
			OMConsultaOper.setValueAt("entrada.NCUENT",  selectCuentaOrden); //VARIOS NIT F2 - OSNEIDER A. CMC - 02-10-2019
			Trace.trace(Trace.Debug, "", "### cargarEquivalentePesos() --- OM sOmConsultaUnicaOperaciones cargada");
		
//INI CMC - FUNCION DE NEGOCIO FNGU - PRUEBA - 19/09/2019
			controlOM = new OpControl();
			controlOM.setConfig((String)getValueAt("datosAPP.pb_cod_serv"),
								(String)getValueAt("datosAPP.iv-cod_emp")+(String)getValueAt("datosAPP.iv-cod_usu"),
								(String)getValueAt("datosAPP.iv-cod_usu"),
								creaOM("sign_on_om"), 
								creaOM("sign_on_om"),
								OMConsultaOper, 
								creaOM(OMConsultaOper.getName()));
			controlOM.control_f100();
//FIN CMC - FUNCION DE NEGOCIO FNGU - PRUEBA - 19/09/2019
			Trace.trace(Trace.Debug, "", "### cargarEquivalentePesos() **** Ejecutada OM OMConsultaOper");
		
			listaGirosOM = (IndexedCollection) getElementAt("divisas_consulta_unica_operaciones_om-data.salida.listaGiros");
			KeyedCollection kGiro =(KeyedCollection) listaGirosOM.getElementAt(0);
		
			String monto= String.valueOf(kGiro.getValueAt("MONTO")).trim();			
			String tasaDiv= String.valueOf(kGiro.getValueAt("TASADIV")).trim();	
			String tasaPeso= String.valueOf(kGiro.getValueAt("TASAPE")).trim();	
			String tasaUsd= String.valueOf(kGiro.getValueAt("TASAUSD")).trim();	
			String equivPeso= String.valueOf(kGiro.getValueAt("EQUIPES")).trim();
		
			Trace.trace(Trace.Debug, "", "### equivPeso: " + equivPeso);
			if(kGiro.getValueAt("EQUIPES")==null){
				Double equivPesoCalc= new Double(equivPeso);
				Double montoCalc= new Double(monto);
				Double tasaPesoCalc= new Double(tasaPeso);
				if (equivPeso.equals("0.0000")){
					equivPesoCalc = tasaPesoCalc * montoCalc;
					equivPeso = String.valueOf(equivPesoCalc);
					Trace.trace(Trace.Debug, "", "### equivPeso: " + equivPeso);
					Trace.trace(Trace.Debug, "", "### equivPesoCalc: " + equivPesoCalc);
				}
			}
		
			setValueAt(TASA_DIVISA, new Double(tasaDiv));
			setValueAt(TASA_DIVISA_PESO, new Double(tasaPeso));
			setValueAt(TASA_USD_PESO, new Double(tasaUsd));
			setValueAt("equivPesos", new Double(equivPeso));
			
			// INI -VARIOS NIT F2 - OSNEIDER A. CMC - 02-10-2019
			if(kGiro.getValueAt(NIT_COMPLETO)!=null && kGiro.getValueAt(NOMBRE_CLIENTE)!=null) {
				Trace.trace(Trace.Information, getClass().getName(), "OMAN-cargarEquivalentePesos(): Se formatean datos nuevos Nit y nombre");
				String nombreC=kGiro.getValueAt(NOMBRE_CLIENTE).toString().trim();
				String nit=kGiro.getValueAt(NIT_COMPLETO).toString().trim();
				nit=nit.substring(1);
				nit=nit.replaceFirst(VALUE_SIGNO, "");
				String digitoVerifi=nit.substring(nit.length()-1);
				nit=nit.substring(0,nit.length()-1)+"-"+digitoVerifi;
				setValueAt(VALUE_FIJO_NIT,nit);
				setValueAt(VALUE_FIJO_NOMBRE,nombreC);
			}
			// FIN -VARIOS NIT F2 - OSNEIDER A. CMC - 02-10-2019
			Trace.trace(Trace.Debug, "", "### Fin cargarEquivalentePesos() " + equivPeso);
		}catch(Exception e){
			setValueAt(TASA_DIVISA, new Double(0));
			setValueAt(TASA_DIVISA_PESO, new Double(0));
			setValueAt(TASA_USD_PESO, new Double(0));
			setValueAt("equivPesos", new Double(0));
//INI CMC - FUNCION DE NEGOCIO - PRUEBA - 19/09/2019
		} finally {
			controlOM = null;
		}
//FIN CMC - FUNCION DE NEGOCIO - PRUEBA - 19/09/2019
		
	}
	
    /* INI Alcance Divisas CMC Archivos 29/01/2018 */
	//public void enviarImagenesbas64(String Declaracion, String Factura) throws Exception
	public void enviarImagenesbas64(String file, String ext, String size, String name) throws Exception
	{
        Trace.trace(Trace.Debug, "", "### Inicio enviarImagenesbas64() - " + getValueAt("referenciaOpe").toString());
        String documentoCliente = (String) getValueAt("num_cuenta");
        
        String base64, contentType;
        String[] parts;
        
        String numOperation = getValueAt("selectOpe").toString().substring(0,1) + getValueAt("referenciaOpe");
        Trace.trace(Trace.Debug, "", "### enviarImagenesbas64() - numOperation: " + numOperation);
        
        try
        {
            if (!file.equals("_") && size != null && ext != "")
            {
                int sizeInt = Integer.parseInt(size);
                Trace.trace(Trace.Debug, "", "### sizeDeclaracion: " + sizeInt);
                DacollectorService serviceIMG = new DacollectorService();
                UploadFilePet uploadFilePetIMG = new UploadFilePet();
                
                Trace.trace(Trace.Debug, "", "### Declaracion Forzar Base64: ");
                
                parts = file.split(";base64,");
                contentType = parts[0];
                base64 = parts[1];
                
                Trace.trace(Trace.Debug, "", "### Declaracion Sepadado OK! - contentType " + contentType);
                Trace.trace(Trace.Debug, "", "### Declaracion Sepadado OK! - base64 " + base64.substring(0, 5));
                
                uploadFilePetIMG.setDoc_name(documentoCliente); 
                uploadFilePetIMG.setUid("800"); 
                uploadFilePetIMG.setLocation("CN01  REG1"); //CMC Migracion BT15 21/05/2019
                uploadFilePetIMG.setContent_type(contentType);
                uploadFilePetIMG.setOrig_len(sizeInt); 
                uploadFilePetIMG.setComments(name);
                uploadFilePetIMG.setPicture(base64);
                uploadFilePetIMG.setPicturename("Archivo." + ext);
                uploadFilePetIMG.setDoc_name("Archivo." + ext);

                UploadFileResp resp = serviceIMG.uploadFile(numOperation + "_80_001", uploadFilePetIMG);
                Trace.trace(Trace.Debug, "", "-Declaracion- INICIO uploadFile IMAGENES DIVISAS -Declaracion-");
                Trace.trace(Trace.Debug, "", "### enviarImagenesbas64() " + "--- INICIO uploadFile IMAGENES DIVISAS ---");
                Trace.trace(Trace.Debug, "", "" + resp.getHttp_status());
                Trace.trace(Trace.Debug, "", "### enviarImagenesbas64()-getHttp_status " + resp.getHttp_status());
                Trace.trace(Trace.Debug, "", "" + resp.getDoc_guid());
                Trace.trace(Trace.Debug, "", "### enviarImagenesbas64()-getDoc_guid " + resp.getDoc_guid());
                Trace.trace(Trace.Debug, "", "" + resp.getErr()); 
                Trace.trace(Trace.Debug, "", "### enviarImagenesbas64()-getErr " + resp.getErr());
                Trace.trace(Trace.Debug, "", "-Declaracion- FIN uploadFile IMAGENES DIVISAS -Declaracion-");
                Trace.trace(Trace.Debug, "", "### enviarImagenesbas64() " + "--- FIN uploadFile IMAGENES DIVISAS---");
            }
        }
        catch(Exception e)
        {
            Trace.trace(Trace.Debug, "", "### enviarImagenesbas64() Declaracion - Falla lanzando web service: " + e);
        }
        Trace.trace(Trace.Debug, "", " Fin  enviarImagenesbas64()");
    }
	/* FIN Alcance Divisas CMC Archivos 29/01/2018 */
	public void updatePaso3() throws Exception{
		Trace.trace(Trace.Debug, "", " Inicio updatePaso3(). PASO_3");
		Date fechaActual = new Date();				
		RequestBankTradeService peticion = null;
		
		String numeralC = getValueAt("Formulario").toString()+(String) getValueAt("NumeralC");
		
		String tipoFondoGiro = getValueAt(TIPO_FONDO_GIRO) != null ? (String) getValueAt(TIPO_FONDO_GIRO) : "";
		String sSelectCuentaOrden = (String) getValueAt("selectCuentaOrden");
		String deCTA = tipoCuentaBT(sSelectCuentaOrden);
		
		peticion = (RequestBankTradeService)getValueAt("PeticionWebService");
		if(tipoFondoGiro.equalsIgnoreCase(FONDO_PSE)) {
			deCTA = PSE;			
			sSelectCuentaOrden = peticion.getIdCliente();
		}
		if(getValueAt("referenciaOpe").toString().equals("")){
			setValueAt("referenciaOpe",peticion.getNoOperac1());
		}
		peticion.setNoOperac1(getValueAt("referenciaOpe").toString());//Incidencia 153 FX CMC 22/01/2019		
		peticion.setNoCtaComm(sSelectCuentaOrden);
		peticion.setDeComm(deCTA);
		peticion.setNoCtaPrincipal(sSelectCuentaOrden);
		peticion.setNoCtaPrincipalCre(sSelectCuentaOrden);//INC BT15.1 PRD FX 05-07-2019
		peticion.setTyUsuario("JR");
		peticion.setFechaHoraNegociacion(fechaActual);
		peticion.setCurDbComm("COP");
		peticion.setTpCambioComm("1.000000000");												
		peticion.setCodOpecampo23b("CRED");
		peticion.setInfcam70Sw1(peticion.getDeOperac());
		peticion.setNuCambiario(numeralC);
		peticion.setTyNegoci("**");
		peticion.setCanal("80");
		
		Trace.trace(Trace.Debug, "", "### updatePaso3(). PASO_3 - definiendo datos entrada ws-.. " + peticion.getNoOperac1());
		
		peticion.setID_BCOOrdenante(null);
		peticion.setNomBcoOrd1(null);
		peticion.setIdSucursal(null);
		//peticion.setDeOperac(null);
		peticion.setSubcanal(null);
		peticion.setCampana(null);
		peticion.setTyVended(null);
		peticion.setNoVended(null);
		peticion.setDigVended(null);
		
		if (getValueAt("selectOpe").equals("T1")  || getValueAt("selectOpe").equals("T2") ) {
			peticion.setTyOperac("TFOUS");
		}else if (getValueAt("selectOpe").equals("H1") || getValueAt("selectOpe").equals("H2") ) {
			peticion.setTyOperac("TFIUS");
		}
		//INI-VARIOS NIT F2 - OSNEIDER A. CMC - 02-10-2019
		if(getValueAt("selectOpe") !=null && getValueAt("selectOpe").equals("T1") )
	    {
			setValueAt("num_cuenta", peticion.getIdCliente());
	    }
		//FIN-VARIOS NIT F2 - OSNEIDER A. CMC - 02-10-2019
		Trace.trace(Trace.Debug, "", "### updatePaso2() Data xml:  " + peticion.toString());
		Trace.trace(Trace.Debug, "", "### updatePaso3() PASO_3 - a lanzar ws-...  ");
		
		if(peticion!=null){
			callBanktradeService(peticion);
		}
		setValueAt("msjError", "");
		setValueAt("PeticionWebService", peticion);
		Trace.trace(Trace.Debug, "", " Fin updatePaso3(). PASO_3");
	}
	
	public void verUnico() throws Exception {

		try {
			String documentofull = (String) getValueAt("docBenefi");
			Trace.trace(Trace.Debug, "", "### verUnico - documentofull=" + documentofull);
			String[] parts = documentofull.split("\\|@\\|");//INC 39 CMC FX 02/03/2018
			
			String tipoId = parts[0];
			String numId = parts[1];
			String digVer = parts[2];
			String ctaBenef = parts[3];
			
			om = creaOM(sOmConsultaBeneficiarios);
			
			// Se debe asignar el valor correspondientes a la FN
			om.setValueAt("Entrada.BINDAUX1", "U ");
			om.setValueAt("Entrada.BINDAUX2", tipoId);
			om.setValueAt("Entrada.BPALACC2", numId);
			om.setValueAt("Entrada.BPALACCE", digVer);
			om.setValueAt("Entrada.BCODCTAA", ctaBenef);

			om.setValueAt(ENTRADA_BCODACCC, getValueAt(VALUE_LOGON).toString());

            /*INI PERDIDA SESION HOST 10-12-2019*/
            ejecutarOMControl(om,sOmConsultaBeneficiarios);
            /*FIN PERDIDA SESION HOST 10-12-2019*/
				
			Trace.trace(Trace.Debug, "", "### verUnico - ejecutada om sOmConsultaBeneficiarios");
			IndexedCollection iccuentas = (IndexedCollection) om
					.getElementAt("Salida.ListaCUENTAS-BENEFIC");
			
			Trace.trace(Trace.Debug, "", "### verUnico - cargada la lista");
			int tamano = iccuentas.size() - 1;
			IndexedCollection listaCuentas;
			for (int i = tamano; i > 0; i--) {
				KeyedCollection kCuentas = (KeyedCollection) iccuentas
						.getElementAt(i);

				String cuenta = (String) kCuentas.getValueAt("ID-BENEFICIARIO");
				if (cuenta == null || cuenta.equals("")) {
					iccuentas.removeElementAt(i);
				}
			}
			
			Trace.trace(Trace.Debug, "", "### verUnico - lista depurarda");
			
			listaCuentas = (IndexedCollection) getElementAt("listaCuentasB");
			listaCuentas.removeAll();
			copiarIndexedCollection(iccuentas, listaCuentas);
			
			IndexedCollection listaCuentas3;
			listaCuentas3 = (IndexedCollection) getElementAt("listaCuentasB");
			int tamano2 = listaCuentas3.size() - 1;
			for (int i = tamano2; i >= 0; i--) {
				KeyedCollection kCuentas = (KeyedCollection) listaCuentas3
						.getElementAt(i);
				
				Vector pais = new Vector();
				setValueAt("tipobeneficiar", kCuentas.getValueAt("tipobeneficiar"));
				setValueAt("idbeneficiario", kCuentas.getValueAt("idbeneficiario"));
				setValueAt("digivbeneficia", kCuentas.getValueAt("digivbeneficia"));
				setValueAt("nombresbenefic", kCuentas.getValueAt("nombresbenefic"));
				setValueAt("apellidosbenef", kCuentas.getValueAt("apellidosbenef"));
				setValueAt("direccionebenef", kCuentas.getValueAt("direccionebenef"));
				setValueAt("telefonobenefi", kCuentas.getValueAt("telefonobenefi"));
				setValueAt("paisbeneficiar", kCuentas.getValueAt("paisbeneficiar"));
				setValueAt("ciudadbenefcia", kCuentas.getValueAt("ciudadbenefcia"));
				setValueAt("cuentabenefica", kCuentas.getValueAt("cuentabenefica"));
				setValueAt("tiposabanco1", kCuentas.getValueAt("tiposabanco1"));
				setValueAt("codigosabanco1", kCuentas.getValueAt("codigosabanco1"));
				setValueAt("nombrebancobe", kCuentas.getValueAt("nombrebancobe"));
				pais = obtenerLiteralPaisProperties(kCuentas.getValueAt("paisbancobene") != null ?
						kCuentas.getValueAt("paisbancobene").toString() : "",0);
				setValueAt("paisbancobene", pais.get(0));
				setValueAt("codePaisBene", pais.get(1));
				setValueAt("ciudadbancobe", kCuentas.getValueAt("ciudadbancobe"));
				setValueAt("direccbancobe", kCuentas.getValueAt("direccbancobe"));
				setValueAt("nombrebancoin", kCuentas.getValueAt("nombrebancoin"));
				setValueAt("cuentaintermed", kCuentas.getValueAt("cuentaintermed"));
				setValueAt("tiposabanco2", kCuentas.getValueAt("tiposabanco2"));
				setValueAt("codigosabanc2", kCuentas.getValueAt("codigosabanc2"));
				pais = obtenerLiteralPaisProperties(kCuentas.getValueAt("paisbancointe") != null ?
						kCuentas.getValueAt("paisbancointe").toString() : "",0);
				setValueAt("paisbancointe", pais.get(0));
				setValueAt("codePaisInte", pais.get(1));
				setValueAt("ciudadbancoin", kCuentas.getValueAt("ciudadbancoin"));
				setValueAt("direccbancoin", kCuentas.getValueAt("direccbancoin"));
				setValueAt("codigoUnitario", kCuentas.getValueAt("codigoUnitario"));

			}
			Trace.trace(Trace.Debug, "", "### verUnico - terminado");
			//INI incidencia 225B FX CMC 02/09/2019
				try {
					if(getValueAt("codigosabanco1") != null || getValueAt("codigosabanco1") != ""){
						
						validarCodigoBanco((String)getValueAt("codigosabanco1"), (String)getValueAt("tiposabanco1"));
						if(getValueAt("errorCodigo") == "S"){
							setEstado("8");//Seleccionar beneficiarios
						}
						if (getValueAt("codigosabanc2") != null || getValueAt("codigosabanc2") != null) {
							validarCodigoBanco((String)getValueAt("codigosabanc2"), (String)getValueAt("tiposabanco2"));
							if(getValueAt("errorCodigo") == "S"){
								setEstado("8");//Seleccionar beneficiarios
							}						
						}
					}
				}catch(Exception y){
					Trace.trace(Trace.Debug, "", "###  FALLA AJUSTE 225B: " + y);//TRY CATCH PARA NO BLOQUEAR FLUJO
				}
			//FIN incidencia 225B FX CMC 04/09/2019
			setEstado("2");
		}catch (Exception e) {
			Trace.trace(Trace.Debug, "", "###  ERROR verUnico(): " + e);
			e.printStackTrace();
			this.setEstado("ERROR");
			return;
//INI CMC - FUNCION DE NEGOCIO - PRUEBA - 19/09/2019
		} finally {
			controlOM = null;
		}
//FIN CMC - FUNCION DE NEGOCIO - PRUEBA - 19/09/2019
	}
	
	private RequestBankTradeService cargarInfoBeneficiario(RequestBankTradeService peticion, String docBeneficiario, String ctaBeneficiario,String ctaOrdenante) throws Exception{
		
		verUnico();
		String codigoU ="";
		String tipoFondoGiro = getValueAt(TIPO_FONDO_GIRO) != null ? (String) getValueAt(TIPO_FONDO_GIRO) : "";
		if(getValueAt("codigoUnitario")!=null&&getValueAt("paisbancobene")!=null) {
			codigoU = (String) getValueAt("codigoUnitario");
			String banbene = (String) getValueAt("paisbancobene");
			Trace.trace(Trace.Debug, "", "### CODIGOUNI "+codigoU );
			Trace.trace(Trace.Debug, "", "### BANBENEE "+banbene );
			if(banbene.equals("GBR"))
			{
				codigoU = "/ACC/SORT CODE "+codigoU;
			}else if(banbene.equals("CAN"))
			{
				codigoU = "/ACC/CODIGO TRANSIT "+codigoU;
			}
			Trace.trace(Trace.Debug, "", "### CODIGOUTRATADO "+codigoU );
		}
		if(codigoU.equalsIgnoreCase("")) {
			codigoU="' '";
		}
		
		//TEPMPORAL INI //
		Trace.trace(Trace.Debug, "", "##################################################################");
		peticion.setIdSwTpBcoOrd(null);
		peticion.setNomCam56InfBene1((String)getValueAt("nombrebancoin"));
		Trace.trace(Trace.Debug, "", "###  instanciarBeneficiario - setNomCam56InfBene1: " + getValueAt("nombrebancoin"));
		peticion.setNomCam56InfBene2((String)getValueAt("ciudadbancoin"));
		Trace.trace(Trace.Debug, "", "###  instanciarBeneficiario - setNomCam56InfBene2: " + getValueAt("ciudadbancoin"));
		peticion.setNomCam56InfBene3((String)getValueAt("paisbancointe"));
		Trace.trace(Trace.Debug, "", "###  instanciarBeneficiario - setNomCam56InfBene3: " + getValueAt("paisbancointe"));
		peticion.setNomCam56InfBene4("");
		Trace.trace(Trace.Debug, "", "###  instanciarBeneficiario - setNomCam56InfBene4: " );
		if (getValueAt("tiposabanco2")!=null){
			if(getValueAt("tiposabanco2").toString().contains("FW")){
				peticion.setIdTyCampo56Sw((String) getValueAt("tiposabanco2"));
				Trace.trace(Trace.Debug, "", "###  instanciarBeneficiario - (FW)setIdTyCampo56Sw: " + getValueAt("tiposabanco2"));
				peticion.setIDcampo56((String) getValueAt("codigosabanc2"));
				Trace.trace(Trace.Debug, "", "###  instanciarBeneficiario - (FW)setIDcampo56: " + getValueAt("codigosabanc2"));
			}else if(getValueAt("tiposabanco2").toString().contains("CT")){
				peticion.setIDcampo56((String) getValueAt("codigosabanc2"));
				Trace.trace(Trace.Debug, "", "###  instanciarBeneficiario - (CT)setIDcampo56: " + getValueAt("codigosabanc2"));
			}else{
				peticion.setSwiftcampo56A((String) getValueAt("codigosabanc2"));
				Trace.trace(Trace.Debug, "", "###  instanciarBeneficiario - (SW)setSwiftcampo56A: " + getValueAt("codigosabanc2"));
			}
		}	
		peticion.setNomCam57BanBene1((String)getValueAt("nombrebancobe"));
		Trace.trace(Trace.Debug, "", "###  instanciarBeneficiario - setNomCam57BanBene1: " + getValueAt("nombrebancobe"));
		peticion.setNomCam57BanBene2((String)getValueAt("ciudadbancobe"));
		Trace.trace(Trace.Debug, "", "###  instanciarBeneficiario - setNomCam57BanBene2: " + getValueAt("ciudadbancobe"));
		peticion.setNomCam57BanBene3((String)getValueAt("paisbancobene"));
		Trace.trace(Trace.Debug, "", "###  instanciarBeneficiario - setNomCam57BanBene4: " + getValueAt("direccionebenef"));
		if (getValueAt("tiposabanco1")!=null){
			if(getValueAt("tiposabanco1").toString().contains("FW")){
				peticion.setIdTyCampo57Sw((String) getValueAt("tiposabanco1"));
				Trace.trace(Trace.Debug, "", "###  instanciarBeneficiario - (FW)setIdTyCampo57Sw: " + getValueAt("tiposabanco1"));
				peticion.setIDcampo57((String) getValueAt("codigosabanco1"));
				Trace.trace(Trace.Debug, "", "###  instanciarBeneficiario - (FW)setIDcampo57: " + getValueAt("codigosabanco1"));
			}else if(getValueAt("tiposabanco1").toString().contains("CT")){
				peticion.setIDcampo57((String) getValueAt("codigosabanco1"));
				Trace.trace(Trace.Debug, "", "###  instanciarBeneficiario - (CT)setIDcampo57: " + getValueAt("codigosabanco1"));
			}else{
				peticion.setSwiftcampo57A((String) getValueAt("codigosabanco1"));
				Trace.trace(Trace.Debug, "", "###  instanciarBeneficiario - (SW)setSwiftcampo57A: " + getValueAt("codigosabanco1"));
			}
		}
		
		peticion.setIdCampo59(ctaBeneficiario);
		Trace.trace(Trace.Debug, "", "###  instanciarBeneficiario - setIdCampo59: " + ctaBeneficiario);
		//INI incidencia 103 FX CMC 09/11/2018
		peticion.setNomCam59Bene1((String) getValueAt("nombresbenefic") + (String) getValueAt("apellidosbenef"));
		Trace.trace(Trace.Debug, "", "###  instanciarBeneficiario - setNomCam59Bene1: " + getValueAt("nombresbenefic") + getValueAt("apellidosbenef"));
		//FIN incidencia 103 FX CMC 09/11/2018
		peticion.setNomCam59Bene2((String) getValueAt("ciudadbenefcia"));
		Trace.trace(Trace.Debug, "", "###  instanciarBeneficiario - setNomCam59Bene2: " + getValueAt("ciudadbenefcia"));
		peticion.setNomCam59Bene3((String) getValueAt("paisbeneficiar"));
		Trace.trace(Trace.Debug, "", "###  instanciarBeneficiario - setNomCam59Bene3: " + getValueAt("paisbeneficiar"));
		peticion.setNomCam59Bene4((String) getValueAt("direccionebenef"));
		Trace.trace(Trace.Debug, "", "###  instanciarBeneficiario - setNomCam59Bene4: " + getValueAt("direccionebenef"));
		peticion.setDeComm(tipoCuentaBT(ctaOrdenante));
		Trace.trace(Trace.Debug, "", "###  instanciarBeneficiario - setDeComm: " + tipoCuentaBT(ctaOrdenante));
		peticion.setNoCtaComm(ctaOrdenante);				
		Trace.trace(Trace.Debug, "", "###  instanciarBeneficiario - setNoCtaComm: " + ctaOrdenante);
		if(tipoFondoGiro.equalsIgnoreCase(FONDO_PSE))
			peticion.setDbPrincipal(PSE);
		else
			peticion.setDbPrincipal(tipoCuentaBT(ctaOrdenante));
		peticion.setNoCtaPrincipal(ctaOrdenante);	
		if(!codigoU.equalsIgnoreCase("")) {
			peticion.setIdCampo72(codigoU);	
			Trace.trace(Trace.Debug, "", "### ENTROOO TRATADOOO ");
		}
		
		peticion.setNoCtaPrincipalCre(ctaOrdenante);//INC BT15.1 PRD FX 05-07-2019
		Trace.trace(Trace.Debug, "", "##################################################################");
//		TEPMPORAL FIN //
		
		return peticion;
	}
	
	public void instanciarBeneficiario() throws Exception {
		Trace.trace(Trace.Debug, "", "###  instanciarBeneficiario - inicio");
		setValueAt("tipobeneficiar", "");
		setValueAt("idbeneficiario", "");
		setValueAt("digivbeneficia", "");
		setValueAt("nombresbenefic", "");
		setValueAt("apellidosbenef", "");
		setValueAt("direccionebenef", "");
		setValueAt("telefonobenefi", "");
		setValueAt("paisbeneficiar", "");
		setValueAt("ciudadbenefcia", "");
		setValueAt("cuentabenefica", "");
		
		setValueAt("nombrebancobe", "");
		setValueAt("tiposabanco1", "");
		setValueAt("codigosabanco1", "");
		setValueAt("paisbancobene", "");
		setValueAt("ciudadbancobe", "");
		
		setValueAt("nombrebancoin", "");
		setValueAt("cuentaintermed", "");
		setValueAt("tiposabanco2", "");
		setValueAt("codigosabanc2", "");
		setValueAt("paisbancointe", "");
		setValueAt("ciudadbancoin", "");
		Trace.trace(Trace.Debug, "", "###  instanciarBeneficiario - fin");
	}
	
	private String tipoCuentaBT(String cuenta){
		
		String resp = "";
		try{
			resp = cuenta.substring(10,12);
		}catch (Exception e) {
			Trace.trace(Trace.Debug, "", "### ERROR  falla tipoCuenteBT - ERROR");
		}
		
		if(resp.equals("01"))
			resp = "CTE";
		else if(resp.equals("02"))
			resp = "AHO";
		else
			resp = "_";
		
		return resp;
	}
	
private String tipoCuentaFirmas(String cuenta){
		
		String resp = "";
		try{
			resp = cuenta.substring(10,12);
		}catch (Exception e) {
			Trace.trace(Trace.Debug, "", "### ERROR  falla tipoCuentaFirmas - ERROR");
		}
		if(resp.equals("01"))
			resp = "CC";
		else if(resp.equals("02"))
			resp = "AH";
		else
			resp = "_";
		
		return resp;
	}
	
	public void generarReferenciaOperacionH() throws Exception{
		Trace.trace(Trace.Debug, "", "### generarReferenciaOperacionH() PASO_1 - inicio ");
		try{
			
			String dataFull = (String) getValueAt("dataFull");
			Trace.trace(Trace.Debug, "", ">>>>>>>>>>>DataFull: "+ dataFull);//INI FX Incidencia 16 CMC 02/02/2018
            String[] dtsOpe = dataFull.split("\\|@\\|");//FX CMC 06022018
	    Trace.trace(Trace.Debug, "",">>>>>>>>>>>Longitud "+dtsOpe.length);
            refExtranjero = dtsOpe[9];
	    Trace.trace(Trace.Debug, "",">>>>>>>>>>>refExtranjero ["+refExtranjero+"]");
            refExtranjero=refExtranjero.replaceAll(" ", "");
            for(int i=1;refExtranjero.length()<10;i++){
            	refExtranjero=0+refExtranjero;
		Trace.trace(Trace.Debug,"",">>>>>>>>>>>refExtranjero:["+refExtranjero+"] length:"+refExtranjero.length());//FIN FX Incidencia 16 CMC 02/02/2018
            }
            Trace.trace(Trace.Debug, "", " Inicio() //refExtranjero: " + refExtranjero);
            
          //INI- LISTA DE NIT/COMERCIO EXTERIOR - OSNEIDER ACEVEDO - CMC - 14-05-2019 
			
			if(getValueAt("selectOpe")!=null && getValueAt("selectOpe").toString().equalsIgnoreCase("H1")) {
				if(getValueAt("Num_Nit")!=null && !getValueAt("Num_Nit").toString().trim().equals("")) {
					String trama=(String)getValueAt("Num_Nit");
					String[] datos=trama.split("@");
					setValueAt("num_cuenta", datos[0]);
					//INI INC CMC OFICINA GESTORA 27/09/2019
					try {
						String sucursal = (String) getValueAt("codigoSucursal");
						BT_IDSUCURSAL = (sucursal!=null)?sucursal:"0073";
					} catch (Exception e) {
						Trace.trace(Trace.Error, "", "generarReferenciaOperacionH() - Oficina Gestora: " + e);
					}
					//FIN INC CMC OFICINA GESTORA 27/09/2019
					setValueAt("msjError", "");
				}
			}//INI VARIOS NITS FASE2 29 01 2020
			else if(getValueAt("selectOpe")!=null && getValueAt("selectOpe").toString().equalsIgnoreCase("H2")) {
				try {
					String sucursal = (String) getValueAt("codigoSucursal");
					BT_IDSUCURSAL = (sucursal!=null)?sucursal:"0073";
					if(getValueAt("Nit_Mod")!=null) {
						setValueAt("num_cuenta", getValueAt("Nit_Mod").toString().trim().replace("-", ""));
					}
					setValueAt("msjError", "");
				} catch (Exception e_gestora) {
					Trace.trace(Trace.Error, "", "consultarOficinaTitular() - Oficina Gestora: " + e_gestora);
					consultarOficinaTitular();
				}
				
			}
			//FIN VARIOS NITS FASE2 29 01 2020
			// FIN- LISTA DE NIT/COMERCIO EXTERIOR - OSNEIDER ACEVEDO - CMC - 14-05-2019

			// INI incidencia 153 FX CMC 21/01/2019
			// aceptarInicialH();
			generarOperacionH();
			//FIN incidencia 153 FX CMC 21/01/2019
			setValueAt("referenciaOpe", getValueAt("referenciaOpe"));
			
			//INI INC 68 FX 2018-06-26
			if(getValueAt("msjError").equals("")){				
				setValueAt("docBenefi", " |@| |@| |@| |@| |@| ");
			}
			//FIN INC 68 FX 2018-06-26
			
			//INI INC 98 FX 22/10/2018
			if(getValueAt("msjError").equals("T") || getValueAt("msjError").equals("H")){
				Trace.trace(Trace.Debug, "", "### Entra Generar operación MSJERROR" + getValueAt("msjError"));					
				setValueAt("Error", getValueAt("msjError"));
				Trace.trace(Trace.Debug, "", "### Entra Generar operación ERROR" + getValueAt("Error"));	
				setEstado("ERRORWS");
			}else 
			{
				setEstado("0");
			}
			//FIN INC 98 FX 22/10/2018
			
			Trace.trace(Trace.Debug, "", " Fin generarReferenciaOperacionH() PASO_1 - referenciaOpe: " + getValueAt("referenciaOpe"));
		}catch (Exception e) {			
			Trace.trace(Trace.Debug, "", "### generarReferenciaOperacionH() PASO_1 - ERROR " + e.toString());
			//INI incidencia 98 FX 16/10/2018
			//setValueAt("msjError", "SISTEMA TEMPORALMENTE NO DISPONIBLE");
			String errorWS = "H";			
			setValueAt("msjError", errorWS);			
			setValueAt("Error", errorWS);
			this.setEstado("ERRORWS");			
			//INI incidencia 98 FX 16/10/2018
			//ManejarExcepcion(3, "ERROR AL CREAR OPERACIÓN", "SISTEMA TEMPORALMENTE NO DISPONIBLE", "", e, "", this, "", "");
			//ManejarExcepcion(3, "11", errorWS, "", e, "", this, "", "");
			return;
		}
	}
	
	public void aceptarInicialH() throws Exception {
		//validarHora();
		String descripNegociacion = (String) getValueAt("descripNegociacion");
		Trace.trace(Trace.Debug,"","aceptarInicialH() descripNegociacion"+descripNegociacion);//Incidencia 16 CMC FX 02/02/2018
		String selectMoneda = (String) getValueAt("selectMoneda");
		Trace.trace(Trace.Debug,"","aceptarInicialH() selectMoneda"+selectMoneda);//Incidencia 16 CMC FX 02/02/2018
		if(selectMoneda!= null && selectMoneda!= ""){
		    if (selectMoneda.length() > 2){//INI Incidencia 16 CMC FX 02/02/2018
			selectMoneda= selectMoneda.substring(0,3);
		    }//FIN Incidencia 16 CMC FX 02/02/2018
			}
		setValueAt("selectMoneda", selectMoneda);
		
		//INI INC AVANCE 0 FX 16-07-2018
		String resultadoAvance =(String) getValueAt(AVANCE_OPE);
		resultadoAvance = formateoAvance(resultadoAvance);
		Trace.trace(Trace.Debug,"","aceptarInicialH() resultadoAvance:"+resultadoAvance);
		//setValueAt(AVANCE_OPE, getValueAt(AVANCE_OPE));
		setValueAt(AVANCE_OPE, resultadoAvance);
		//FIN INC AVANCE 0 FX 16-07-2018
		
		Trace.trace(Trace.Debug, "", "### avanceOpe #" + getValueAt(AVANCE_OPE));
		setValueAt("selectOpe", getValueAt("selectOpe"));
		Trace.trace(Trace.Debug, "", "### selectOpe " + getValueAt("selectOpe"));
		setValueAt("selectCuentaOrden", getValueAt("selectCuentaOrden"));
		Trace.trace(Trace.Debug, "", "### selectCuentaOrden " + getValueAt("selectCuentaOrden"));
		
		
		Trace.trace(Trace.Debug, "", "### selectMoneda " + getValueAt("selectMoneda"));
		setValueAt("monto", getValueAt("monto"));
		Trace.trace(Trace.Debug, "", "### monto " + getValueAt("monto"));
		setValueAt("descripNegociacion", descripNegociacion);
		Trace.trace(Trace.Debug, "", "### descripNegociacion " + getValueAt("descripNegociacion"));
		
		setValueAt(TASA_DIVISA, getValueAt(TASA_DIVISA));
		Trace.trace(Trace.Debug, "", "### tasaDivisa " + getValueAt(TASA_DIVISA));
		setValueAt(TASA_DIVISA_PESO, getValueAt(TASA_DIVISA_PESO));
		Trace.trace(Trace.Debug, "", "### tasaDivisaPeso: " + getValueAt(TASA_DIVISA_PESO));
		setValueAt(TASA_USD_PESO, getValueAt(TASA_USD_PESO));
		Trace.trace(Trace.Debug, "", "### tasaUsdPeso# " + getValueAt(TASA_USD_PESO));
		setValueAt("equivPesos", getValueAt("equivPesos"));
		Trace.trace(Trace.Debug, "", "### equivPesos " + getValueAt("equivPesos"));
		
		setValueAt("indBenef", "");
		
		Trace.trace(Trace.Debug, "", "### aceptarInicialH() - inicio PASO_1 " + descripNegociacion);
		
		RequestBankTradeService peticion = new RequestBankTradeService();
		
		Date fechaActual = new Date();
		peticion.setFechaHoraNegociacion(fechaActual);
		//peticion.setFeValor(fechaActual); - FECHA DE CREACION DE LA OPERACION H
		
		// Cuenta Ordenante - INI//
		String sSelectCuentaOrden = (String) getValueAt("selectCuentaOrden");
		String deCTA = tipoCuentaBT(sSelectCuentaOrden);
		
		Trace.trace(Trace.Debug, "", "### aceptarInicialH() - deCTA " + deCTA);
		
		peticion.setNoCtaComm(sSelectCuentaOrden);
		peticion.setDeComm(deCTA);
		peticion.setNoCtaPrincipal(sSelectCuentaOrden);
		peticion.setNoCtaPrincipalCre(sSelectCuentaOrden);
		peticion.setNoOperac(refExtranjero);
		// Cuenta Ordenante - FIN//
		
		peticion.setMonAvance(getValueAt("monto").toString());
		if(getValueAt("selectOpe").equals("H1") ){
			Trace.trace(Trace.Debug, "", "### aceptarInicialH() PASO_1 - H1 ");
			peticion.setTasaDivi(Double.valueOf(getValueAt(TASA_DIVISA_PESO).toString()));	
			peticion.setTasaAvance(getValueAt(TASA_DIVISA_PESO).toString());
			peticion.setTasaUsd(Double.valueOf(getValueAt(TASA_USD_PESO).toString()));
			peticion.setTasaLinea(Double.valueOf(getValueAt(TASA_DIVISA).toString()));
			peticion.setTyNegoci("NL");
			peticion.setNoAvance("STD1");
			
		}else if (getValueAt("selectOpe").equals("H2") ){
			Trace.trace(Trace.Debug, "", "### aceptarInicialH() PASO_1 - H2 " + getValueAt(AVANCE_OPE).toString() + " / " + getValueAt("monto").toString());
			peticion.setNoAvance(getValueAt(AVANCE_OPE).toString());
			peticion.setTyNegoci("NM");
			peticion.setTasaDivi(null);		
			peticion.setTasaUsd(null);
			peticion.setTasaLinea(null);
			peticion.setTasaAvance(null);
			
		}
		Trace.trace(Trace.Debug, "", "### aceptarInicialH() variables dinamicas ");
		String documentoCliente = (String) getValueAt("num_cuenta");
		peticion.setIdCliente(documentoCliente);
		peticion.setTyUsuario("JR");
		
		peticion.setDeOperac(descripNegociacion);
		peticion.setInfcam70Sw1(descripNegociacion);

		peticion.setMonNegoc(getValueAt("monto").toString());
		peticion.setCurNegoc(selectMoneda);
		
		Trace.trace(Trace.Debug, "", "### aceptarInicialH() PASO_1 - variables fijas ");
		peticion.setTyOperac("TFIL");			
		peticion.setIdSucursal(BT_IDSUCURSAL);
		peticion.setCanal("80");
		peticion.setSubcanal("02");
		peticion.setCampana("CANALES");
		peticion.setTyVended("9");
		peticion.setNoVended("777777777777777");
		peticion.setDigVended("9");
		peticion.setTyTransa("H");
		peticion.setIndMoneda("");//Pendiente por definir
		peticion.setID_BCOOrdenante("SYSTEM");
		peticion.setNomBcoOrd1("SYSTEM");
		peticion.setIdSwTpBcoOrd("SW");
			
		Trace.trace(Trace.Debug, "", "### aceptarInicialH() PASO_1 - a lanzar ws-...  ");
		try{
			ResponseBankTradeService response = WrapperBanktradeService.execute(peticion);
			if(response.getCodError() != null){
				if(!response.getCodError().equals("")){
					this.setEstado("ERROR");
					String mensaje = ERROR_WS_BANKTRADE.concat(response.getSequence()+" "+response.getCodError());
					Trace.trace(Trace.Debug, "", "### generarReferenciaOperacionH() PASO_1 - error de web service: " + mensaje);
					setValueAt("msjError", mensaje);
					ManejarExcepcion(3, "ERROR AL CREAR OPERACIÓN", "SISTEMA TEMPORALMENTE NO DISPONIBLE", "", new Exception(), "", this, "", "");
					return;
				}else{
					setValueAt("referenciaOpe", response.getNumOpera().toString());
					Trace.trace(Trace.Debug, "", "### aceptarInicialH() PASO_1 - response.getNumOpera " + response.getNumOpera());
					Trace.trace(Trace.Debug, "", "### aceptarInicialH() PASO_1 - response.getPrefix " + response.getPrefix());
					Trace.trace(Trace.Debug, "", "### aceptarInicialH() PASO_1 - response.sequence " + response.getSequence());
					setValueAt("msjError", "");
					peticion.setNoOperac1(response.getNumOpera().toString());
					Trace.trace(Trace.Debug, "", "### aceptarInicialH() referenciaOpe " + getValueAt("referenciaOpe"));
					setValueAt("PeticionWebService", peticion);
				}
			}else{
				Trace.trace(Trace.Debug, "", MENSAJE_ERROR);
				setValueAt("msjError", "RTA null");
			}
		}catch (Exception e) {
			e.printStackTrace();
			//INI incidencia FX 98 22/10/2018
			Trace.trace(Trace.Debug, "", "### ENTRA AL CATCH DEL FINALIZAR OPERACIONES ACEPTAR INICIAL");			
			Trace.trace(Trace.Debug, "", "### generarReferenciaOperacionH() PASO_1 - Falla lanzando web service: " + e);
			String errorWS = "H";			
			//setValueAt("msjError", "SISTEMA TEMPORALMENTE NO DISPONIBLE");
			setValueAt("msjError", errorWS);
			setValueAt("Error", errorWS);
			this.setEstado("ERRORWS");
			//ManejarExcepcion(3, errorWS, "", "", e, "", this, "", "");
			return;	
			//FIN incidencia FX 98 22/10/2018
		}
		
		//2do envio a BTConnector H
		updatePaso3TMP(peticion, sSelectCuentaOrden);
	}
	
	public void updatePaso3TMP(RequestBankTradeService peticion, String sSelectCuentaOrden) throws Exception{
		Trace.trace(Trace.Debug, "", " Inicio updatePaso3TMP() PASO_TMP" );
	
		peticion.setTyOperac("TFIUS");
	
		peticion.setTyUsuario("JR");
		peticion.setCurDbComm("COP");
		peticion.setTpCambioComm("1.000000000");												
		peticion.setCodOpecampo23b("CRED");
		peticion.setInfcam70Sw1(peticion.getDeOperac());
		peticion.setNuCambiario("5E02918");
		peticion.setTyNegoci("**");
		peticion.setCanal("80");
	
		Trace.trace(Trace.Debug, "", "### updatePaso3TMP(). PASO_3 - definiendo datos entrada ws-.. " + peticion.getNoOperac1());
	
		peticion.setID_BCOOrdenante(null);
		peticion.setNomBcoOrd1(null);
		peticion.setIdSucursal(null);
		//peticion.setDeOperac(null);
		peticion.setSubcanal(null);
		peticion.setCampana(null);
		peticion.setTyVended(null);
		peticion.setNoVended(null);
		peticion.setDigVended(null);
		
		Trace.trace(Trace.Debug, "", "### updatePaso3TMP() PASO_3 - a lanzar ws-...  ");
		
		if(peticion!=null){
			try{
				ResponseBankTradeService response = WrapperBanktradeService.execute(peticion);
				if(response.getCodError() != null){
					if(!response.getCodError().equals("")){
						String mensaje = ERROR_WS_BANKTRADE.concat(response.getSequence()+" "+response.getCodError());
						Trace.trace(Trace.Debug, "", "### updatePaso3TMP() PASO_3 - error de web service: " + mensaje);
					}else{
						Trace.trace(Trace.Debug, "", "### updatePaso3TMP() PASO_3 - response.getNumOpera " + response.getNumOpera());
						setValueAt("PeticionWebService", peticion);
					}
				}else{
					Trace.trace(Trace.Debug, "", "### updatePaso3TMP() PASO_1 - error de web service: RTA null");
				}
			}catch (Exception e) {
				//INI incidencia 98 FX 16/10/2018
				Trace.trace(Trace.Debug, "", "### ENTRA AL CATCH DEL SEGUNDO LLAMADO FINALIZAR OPERACIONES ");
				String errorWS = "H";
				setValueAt("msjError", errorWS);
				setValueAt("Error", errorWS);
				//BbvaARQException Barq = ManejarExcepcion(3,errorWS, "", "", e, "", this, "", "");
				//FIN incidencia 98 FX 16/10/2018
				Trace.trace(Trace.Debug, "", "### updatePaso3TMP() PASO_3 - Falla lanzando web service: " + e);
				this.setEstado("ERRORWS");
				return;
			}
		}
	}
	
	public void enviarOperacion() throws Exception{
		
		Trace.trace(Trace.Debug, "", " Inicio enviarOperacion(). PASO_4");
		String selectMoneda = (String) getValueAt("selectMoneda");

		try {
			Trace.trace(Trace.Debug, "", "### Variables comprobarPass() ClaveOperacion:" + getValueAt("ClaveOperacion") + " / token: " + getValueAt("token") + " / alias: " + getValueAt("s_IdCliente"));
			comprobarPass();
			int valor = 0;
			valor = ((Integer) getValueAt(VALUE_RESULTADO)).intValue();
			Trace.trace(Trace.Debug, "", "### Resultado luego de comprobarPass() : " + valor);
			Trace.trace(Trace.Debug, "", "### ComprobarPass Fin  ***");
			
		} catch (Exception e) {
			setValueAt("msjError", "Falla en la validacion de Token y Clave de operaciones***");
			Trace.trace(Trace.Debug, "", "### Falla en la validacion de Token y Clave de operaciones *** " + e);
			
		}
		
		if(selectMoneda!= null && selectMoneda!= ""){
		    if(selectMoneda.length() > 2){//INI incidencia CMC FX 02/02/2018
			selectMoneda= selectMoneda.substring(0,3);
		    }//FIN incidencia CMC FX 02/02/2018
		}
		setValueAt("selectMoneda", selectMoneda);
		
		setValueAt("selectMoneda", getValueAt("selectOpe"));
		Trace.trace(Trace.Debug, "", "### selectOpe " + getValueAt("selectOpe"));
		setValueAt("selectCuentaOrden", getValueAt("selectCuentaOrden"));
		Trace.trace(Trace.Debug, "", "### selectCuentaOrden " + getValueAt("selectCuentaOrden"));
		setValueAt("monto", getValueAt("monto"));
		Trace.trace(Trace.Debug, "", "### monto " + getValueAt("monto"));
		setValueAt("descripNegociacion", getValueAt("descripNegociacion"));
		Trace.trace(Trace.Debug, "", "### descripNegociacion " + getValueAt("descripNegociacion"));
		
		setValueAt("referenciaOpe", getValueAt("referenciaOpe"));
		Trace.trace(Trace.Debug, "", "### referenciaOpe " + getValueAt("referenciaOpe"));
		setValueAt("docBenefi", getValueAt("docBenefi"));
		Trace.trace(Trace.Debug, "", "### docBenefi " + getValueAt("docBenefi"));
		
		setValueAt("Formulario", getValueAt("Formulario"));
		Trace.trace(Trace.Debug, "", "### Formulario " + getValueAt("Formulario"));
		setValueAt("NumeralC", getValueAt("NumeralC"));
		Trace.trace(Trace.Debug, "", "### NumeralC " + getValueAt("NumeralC"));
		setValueAt("descNumeral", getValueAt("descNumeral"));
		Trace.trace(Trace.Debug, "", "### descNumeral " + getValueAt("descNumeral"));
		
		if (((Integer) getValueAt(VALUE_RESULTADO)).intValue() == 0) {
			Trace.trace(Trace.Debug, "", "### Credenciales correctas ***");
			updatePaso4();
			
			if(getValueAt("msjError").equals("E")){
				setEstado("3");
			}
		Trace.trace(Trace.Debug, "", " Fin enviarOperacion(). PASO_4");
		}else{
			setEstado("1");
			setValueAt("msjError", "TKN");
			Trace.trace(Trace.Debug, "", "### Token y Clave de Operaciones no son correctos ***");
			
		}
	}
	
	public void updatePaso4() throws Exception{
		Trace.trace(Trace.Debug, "", " Inicio updatePaso4(). PASO_4");
		Date fechaActual = new Date();				
		RequestBankTradeService peticion = null;
		
		peticion = (RequestBankTradeService)getValueAt("PeticionWebService");
				
		if(getValueAt("referenciaOpe").toString().equals("")){
			setValueAt("referenciaOpe",peticion.getNoOperac1());
		}
		peticion.setTyUsuario("JR");
		peticion.setFechaHoraNegociacion(fechaActual);
		peticion.setCurDbComm("COP");
		peticion.setTpCambioComm("1.000000000");												
		peticion.setCodOpecampo23b("CRED");
		
		Trace.trace(Trace.Debug, "", "### updatePaso4(). PASO_4 - definiendo datos entrada ws-..");
		
		peticion.setCanal("80");
		peticion.setSubcanal(null);
		peticion.setCampana(null);
		peticion.setTyVended(null);
		peticion.setNoVended(null);
		peticion.setDigVended(null);
		
		if (getValueAt("selectOpe").equals("T1")  || getValueAt("selectOpe").equals("T2") ) {
			peticion.setTyOperac("TFOU");
		}else if (getValueAt("selectOpe").equals("H1") || getValueAt("selectOpe").equals("H2") ) {
			peticion.setTyOperac("TFIU");
		}
		
		Trace.trace(Trace.Debug, "", "### updatePaso4() PASO_4 - a lanzar ws-...  ");
		
		if(peticion!=null){
			try{
			ResponseBankTradeService response = WrapperBanktradeService.execute(peticion);
			if(response.getCodError() != null){
				if(!response.getCodError().equals("")){
					
					String mensaje = ERROR_WS_BANKTRADE.concat(response.getSequence()+" "+response.getCodError());
					Trace.trace(Trace.Debug, "", "### updatePaso4() PASO_4 - error de web service: " + mensaje);
					setValueAt("msjError", mensaje);
					
				}else{
					Trace.trace(Trace.Debug, "", "### updatePaso4() PASO_4 - response.getNumOpera " + response.getNumOpera());
					
					setValueAt("msjError", "E");
				}
				} else {
					Trace.trace(Trace.Debug, "",
							MENSAJE_ERROR);
					setValueAt("msjError", "RTA null");
				}
			}
			catch (Exception e) {
				if (e instanceof AxisFault) { //INI VARIOS NITS 2 TOUT CMC 17-02-2020
				AxisFault ex = (AxisFault) e;
					if(ex.detail.getMessage().contains(CONNECT_TIMED_OUT) ||ex.getFaultString().contains(SOCKET_TIMEOUT_EXCE)) {
						Trace.trace(Trace.Error, "", "### peticionEnvioWs() Control TIME OUT  - Falla lanzando web service: " + e);
						setValueAt("msjError", "E");
						return;
					}
				} //FIN VARIOS NITS 2 TOUT CMC 17-02-2020
				Trace.trace(Trace.Debug, "", "### updatePaso4() PASO_4 - Falla lanzando web service: " + e);
				setValueAt("msjError", "SISTEMA TEMPORALMENTE NO DISPONIBLE");
				
			 }
		}
		
		
		Trace.trace(Trace.Debug, "", " Fin updatePaso4(). PASO_4");
	}

	public void pendienteEnvio() throws Exception {
		Trace.trace(Trace.Debug, "", " Inicio pendienteEnvio()");
		String selectMoneda = (String) getValueAt("selectMoneda");
		if(selectMoneda!= null && selectMoneda!= ""){
		    if (selectMoneda.length() > 2) {//INI incidencia CMC FX 02/02/2018
			selectMoneda= selectMoneda.substring(0,3);
		    }//FIN incidencia CMC FX 02/02/2018
			}
		setValueAt("selectMoneda", selectMoneda);
		Trace.trace(Trace.Debug, "", "### selectMoneda " + selectMoneda);
		setValueAt("selectMoneda", getValueAt("selectOpe"));
		Trace.trace(Trace.Debug, "", "### selectOpe " + getValueAt("selectOpe"));
		setValueAt("selectCuentaOrden", getValueAt("selectCuentaOrden"));
		Trace.trace(Trace.Debug, "", "### selectCuentaOrden " + getValueAt("selectCuentaOrden"));
		setValueAt("monto", getValueAt("monto"));
		Trace.trace(Trace.Debug, "", "### monto " + getValueAt("monto"));
		setValueAt("equivPesos", getValueAt("equivPesos"));
		Trace.trace(Trace.Debug, "", "### equivPesos " + getValueAt("equivPesos"));
		setValueAt("descripNegociacion", getValueAt("descripNegociacion"));
		Trace.trace(Trace.Debug, "", "### descripNegociacion " + getValueAt("descripNegociacion"));
		
		setValueAt("referenciaOpe", getValueAt("referenciaOpe"));
		Trace.trace(Trace.Debug, "", "### referenciaOpe " + getValueAt("referenciaOpe"));
		setValueAt(AVANCE_OPE, getValueAt(AVANCE_OPE));
		Trace.trace(Trace.Debug, "", "### avanceOpe ##" + getValueAt(AVANCE_OPE));
		setValueAt("docBenefi", getValueAt("docBenefi"));
		Trace.trace(Trace.Debug, "", "### docBenefi " + getValueAt("docBenefi"));
		
		setValueAt("Formulario", getValueAt("Formulario"));
		Trace.trace(Trace.Debug, "", "### Formulario " + getValueAt("Formulario"));
		setValueAt("NumeralC", getValueAt("NumeralC"));
		Trace.trace(Trace.Debug, "", "### NumeralC " + getValueAt("NumeralC"));
		setValueAt("descNumeral", getValueAt("descNumeral"));
		Trace.trace(Trace.Debug, "", "### descNumeral " + getValueAt("descNumeral"));
		
		enviarfirmas();		
		setEstado("3");/*INC 61 Fx - 30-05-2018*/
		Trace.trace(Trace.Debug, "", "setea estado 3()");		
		Trace.trace(Trace.Debug, "", " Fin pendienteEnvio()");
		
	}
	
	public void enviarfirmas() throws Exception {
		Trace.trace(Trace.Debug, "", " Inicio enviarfirmas()");
		
		String  observaciones="";
		Object importe;
		String codOperacion="",cuenta="",descripcion="Negociacion Divisas";
		
		String selectMoneda = (String) getValueAt("selectMoneda");
		
		if(getValueAt("descripNegociacion")!=null){
			descripcion = (String) getValueAt("descripNegociacion");
		}
		
		try {
			Trace.trace(Trace.Debug, "", "### enviarfirmas() _ Definicion de entradas");
			String selectOpe = (String) getValueAt("selectOpe");
			if(selectOpe.equals("T1")){
				observaciones="Neg. al exterior - en Línea";
				selectOpe="T " + selectMoneda;
			}else if(selectOpe.equals("H1")){
				observaciones="Neg. desde exterior - en Línea";
				selectOpe="H " + selectMoneda;
			}else if(selectOpe.equals("T2")){
				observaciones="Neg. al exterior - Mesa Dinero";
				selectOpe="T " + selectMoneda;
			}else if(selectOpe.equals("H2")){
				observaciones="Neg. desde exterior - Mesa Dinero";
				selectOpe="H " + selectMoneda;
			}
			
			codOperacion = (String) getValueAt("referenciaOpe");
			cuenta =(String) getValueAt("selectCuentaOrden");
			String tipo = tipoCuentaFirmas(cuenta);
			importe = getValueAt("equivPesos");
			Trace.trace(Trace.Debug, "", "### enviarfirmas() _ entradas OK");
			Double importeFinal = formatearImporteEntrada(importe.toString());
			String documentoClienteC = "";//FX PERDIDA SESION ZMR 12-12-2019 INI VARIOS NITS FASE 2 RECUPERAR NIT 18-02-2020
			RequestBankTradeService peticion2 = null;
			peticion2 = (RequestBankTradeService) getValueAt("PeticionWebService");
			documentoClienteC= peticion2.getIdCliente(); //FIN NI VARIOS NITS FASE 2 RECUPERAR NIT 18-02-2020
			/* INI INC 61 Fx - 30-05-2018 */
			if (updatePaso2("pte") == 1) {
				Trace.trace(Trace.Debug, "", "### enviarfirmas() entro condicional de respuesta updatePaso2()");
				mancomunadas("", observaciones, "", documentoClienteC, 
				tipo + cuenta, codOperacion, obtenerFechaActual(), obtenerFechaActual(), importeFinal,
				0, "", "", "", selectOpe,
				descripcion, 0, 0, "", "neg_divisas_envio_om", "Negociacion Divisas","NDV");
				setValueAt("msjError", "F");
			}
			/* FIN INC 61 Fx - 30-05-2018 - FX IN 316 PERDIDA SESION ZMR 12-12-2019*/

			Trace.trace(Trace.Debug, "", " Fin enviarfirmas()");
		}catch (Exception e) {
			Trace.trace(Trace.Debug, "", "###  ERROR enviarfirmas(): " + e);
  			BbvaARQException Barq =ManejarExcepcion(1,"","","",e,"",this,"","");
  			ManejarExcepcion(4,"","","",Barq,"",this,"","");
  			throw Barq;
		}
	}
	
	protected Double formatearImporteEntrada(String importe) {
		Double dImporte = null;
		if (importe!=null && !importe.trim().equals("") ) {
			importe=importe.replaceAll(",", "");
			 dImporte = new Double(importe);
		}
		
		return dImporte;
	}
	
	public String validaLista(){
		String indic ="";
		try {
			Trace.trace(Trace.Debug, "", " Inicio validaLista(): " + getValueAt("nitConsulta"));
			String nitConsulta = (String) getValueAt("nitConsulta");
			om = creaOM("validacion_baep_om");
			om.setValueAt("Entrada.COD_IDEN", nitConsulta.substring(0, 1));
			om.setValueAt("Entrada.NUM_IDEN", nitConsulta.substring(1,nitConsulta.length()-1));
			om.setValueAt("Entrada.DIG_IDEN", nitConsulta.substring(nitConsulta.length()-1, nitConsulta.length()));
			ejecutarOM(om);

			setValueAt("indiList", om.getValueAt("Salida.IND_LIST_BAEP") != null ?
					(om.getValueAt("Salida.IND_LIST_BAEP")).toString() : "");
			setValueAt("codError", om.getValueAt("Salida.COD_RROR") != null ?
					(om.getValueAt("Salida.COD_RROR")).toString() : "");
			setValueAt("valError", om.getValueAt("Salida.VALOR_ERROR") != null ?
					(om.getValueAt("Salida.VALOR_ERROR")).toString() : "");
			indic = om.getValueAt("Salida.IND_LIST_BAEP") != null ?
					om.getValueAt("Salida.IND_LIST_BAEP").toString() : "";
		} catch (Exception e) {
			e.printStackTrace();
			Trace.trace(Trace.Error, "", " validaLista(): " + "Error al validar lista baep debido a: " + e.getMessage());
		}
		Trace.trace(Trace.Debug, "", " Finaliza validaLista(): ");
		return indic;	
	}
	
	public void consultaNombreBanco() throws Exception{
		Trace.trace(Trace.Debug, "", " Inicio consultaNombreBanco(): " + getValueAt(CODIGO_CONS_BANCO) + " " + getValueAt("element").toString());

		//Limpiamos variables
		setValueAt("codError", "");
		setValueAt("codePaisBene","");
		setValueAt("codePaisInte","");
		setValueAt("nombrebancobe", "");
		setValueAt("paisbancobene", "");
		setValueAt("ciudadbancobe", "");
		setValueAt("nombrebancoin", "");
		setValueAt("paisbancointe", "");
		setValueAt("ciudadbancoin", "");

		om = creaOM("validacion_codigo_om");
		om.setValueAt("Entrada.CODIGO_BAN", getValueAt(CODIGO_CONS_BANCO).toString());
		String element = getValueAt("element").toString();
		ejecutarOM(om);
		try {
			if(om.getValueAt("Salida.COD-ERROR") != null && om.getValueAt("Salida.COD-ERROR") != "") {
				setValueAt("codError", (om.getValueAt("Salida.COD-ERROR")).toString());
				setValueAt("valError", (om.getValueAt("Salida.VALOR-ERROR-UNO")).toString());
			}else {
				Vector pais = obtenerLiteralPaisProperties((om.getValueAt("Salida.PAISBAN")).toString(),0);
				if(element.equalsIgnoreCase("codigosabanco1")) {
					setValueAt("nombrebancobe", (om.getValueAt("Salida.NOMBAN")).toString());
					setValueAt("paisbancobene", pais.get(0));
					setValueAt("ciudadbancobe", (om.getValueAt("Salida.CIUDBAN")).toString());
					setValueAt("codePaisBene",pais.get(1));
				}else if(element.equalsIgnoreCase("codigosabanc2")) {
					setValueAt("nombrebancoin", (om.getValueAt("Salida.NOMBAN")).toString());
					setValueAt("paisbancointe", pais.get(0));
					setValueAt("ciudadbancoin", (om.getValueAt("Salida.CIUDBAN")).toString());
					setValueAt("codePaisInte",pais.get(1));
				}
			}	
		} 
		catch (Exception e) {
			Trace.trace(Trace.Error, getClass().getName(), "####### consultaNombreBanco() - ERROR ####### " + e);
		}
		setEstado("7");
	}
	
	public void modificarOperaciones() throws Exception {
		IndexedCollection icContextoSesion,listaCuentasOP = null;
		Trace.trace(Trace.Debug, "", " Inicio modificarOperaciones() " + getValueAt("tipoOperacion") + " // " + getValueAt("selectOpe") + " - N.O: " + getValueAt("numOperacion") + " - R.E: " + getValueAt("refExtranjero"));
		
		setValueAt("errorSwift", "N");//INC 225B FX CMC 17/09/2019
		setValueAt("errorBeneficiario", "");
		
		numOperacion = (String) getValueAt("numOperacion");
		numOperacion = numOperacion.replace("T", "");
		numOperacion = numOperacion.replace("H", "");
		
		String selectOpe = getValueAt("selectOpe").toString();
		String indBenef = "";

		try {
			// INI INC NIT 11-07-2019
			String resultadoAvance1 = (String) getValueAt(AVANCE_OPE);
			String tipoFondoGiro = (String) getValueAt(TIPO_FONDO_GIRO);
			String nitMod="";
			if(tipoFondoGiro != null && tipoFondoGiro.equalsIgnoreCase(FONDO_PSE)) {
				Datum comisionService = getObjectValue(OPERATION_OBJ, Datum.class);
				setValueAt(OPERATION_OBJ, getValueAt(OPERATION_OBJ));
				setValueAt(TIPO_FONDO_GIRO, "PSE");
				for(Beneficiary in : comisionService.getBeneficiaries()) {
					if(in.getFullName().equalsIgnoreCase("BBVA")) {
						nitMod = in.getId();
						break;
					}
				} 
			}else {
				 setValueAt(TIPO_FONDO_GIRO, "");
				 nitMod = (String) getValueAt("Nit_Mod");
			}
			Trace.trace(Trace.Debug, "",
					" Inicio modificarOperaciones() " + "resultadoAvance1       : " + resultadoAvance1);
			if(null!=selectOpe && (selectOpe.equals("T1") || selectOpe.equals("T2") ||selectOpe.equals("H1") || selectOpe.equals("H2")) && (null!=nitMod && nitMod.trim().length()>0 && nitMod.trim()!="null")){ // VARIOS NITS FASE2 29 01 2020
				
				nitMod = nitMod.trim();
				nitMod = nitMod.replace("-", "");
				String nitConsulta = formateadorDeNit(nitMod.substring(1,nitMod.length()-1),15);
				nitConsulta = nitMod.substring(0,1)+nitConsulta+nitMod.substring(nitMod.length()-1,nitMod.length());
				Trace.trace(Trace.Debug, "",
						" Inicio modificarOperaciones() " + "nitConsulta       : " + nitConsulta);
				setValueAt("nitConsulta", nitConsulta);
				setValueAt("num_cuenta", nitMod);
				setValueAt("msjError", "");
			}
			else{
				Trace.trace(Trace.Debug, "", " OPERACION POR LA MESA SE CONSULTA NIT NUEVAMNETE PRINCIPAL DE LA RREF " );
				consultarOficinaTitular();
			}
			//FIN INC NIT 11-07-2019			
			// cargar id de la referencia
			
			if(!validaLista().equalsIgnoreCase("NN")) {
				setEstado("8");
				return;
			}
			
			if(getValueAt("indBenef")!=null){
				indBenef = (String)getValueAt("indBenef");
				Trace.trace(Trace.Debug, "", "selecBeneficiarios() - indBenef " + indBenef);
			}
			
			try{
				if(indBenef.equals("A")){
					setValueAt("ppagina", ""); //INC 102 FX 14/11/2018
					altaBeneficiario();					
					setValueAt("indBenef", ""); //INC 102 FX 14/11/2018
				}else if (indBenef.equals("M")){
					setValueAt("ppagina", ""); //INC 102 FX 14/11/2018
					modificarBeneficiario();	
					setValueAt("indBenef", ""); //INC 102 FX 14/11/2018
				}
			}catch(Exception e){
				Trace.trace(Trace.Debug, "", "### modificarOperaciones() --- Falla al dar de alta/modificar el beneficiario");
			}
				OperacionMulticanal OMConsultaOper = creaOM(sOmConsultaUnicaOperaciones);
				
				copiarAOMConsultaUnica(numOperacion, (String)getValueAt("tipoOperacion"));
				Trace.trace(Trace.Debug, "", "### modificarOperaciones() --- OM sOmConsultaUnicaOperaciones cargada");
				
//INI CMC - FUNCION DE NEGOCIO FNGU - PRUEBA - 19/09/2019
				controlOM = new OpControl();
				controlOM.setConfig((String)getValueAt("datosAPP.pb_cod_serv"),
						(String)getValueAt("datosAPP.iv-cod_emp")+(String)getValueAt("datosAPP.iv-cod_usu"),
						(String)getValueAt("datosAPP.iv-cod_usu"),
						creaOM("sign_on_om"),
						creaOM("sign_on_om"),
						OMConsultaOper, 
						creaOM(OMConsultaOper.getName()));
				controlOM.control_f100();
//FIN CMC - FUNCION DE NEGOCIO FNGU - PRUEBA - 19/09/2019
				Trace.trace(Trace.Debug, "", "### copiarDeOMConsultaUnica() **** Ejecutada OM OMConsultaOper");
				copiarDeOMConsultaUnica();
				Trace.trace(Trace.Debug, "", "### copiarDeOMConsultaUnica() **** Datos copiados");
				
			try{
				Trace.trace(Trace.Debug, "", "### consulta Beneficiarios - modificarOperaciones() ++ selectOpe " + selectOpe);
				if(selectOpe.equals("T1") || selectOpe.equals("T2")){
					consultaBeneficiarios();
				}
				
			}catch(Exception ec){
				Trace.trace(Trace.Debug, "", "### falla en  - consulta Beneficiarios - modificarOperaciones())" + ec.toString());
				ec.printStackTrace();
				this.setEstado("ERROR");
				return;
			}
			
			Trace.trace(Trace.Debug, "", "### consulta Beneficiarios - modificarOperaciones() 0%");
			Trace.trace(Trace.Debug, "", "### indBenef: " + getValueAt("indBenef"));
			
			setValueAt("selectCuentaOrden", getValueAt("selectCuentaOrden"));
			Trace.trace(Trace.Debug, "", "### selectCuentaOrden " + getValueAt("selectCuentaOrden"));
			setValueAt("numOperacion", getValueAt("numOperacion"));
			Trace.trace(Trace.Debug, "", "### numOperacion " + getValueAt("numOperacion"));
			setValueAt("refExtranjero", getValueAt("refExtranjero"));
			Trace.trace(Trace.Debug, "", "### refExtranjero " + getValueAt("refExtranjero"));
			setValueAt("selectMoneda", getValueAt("selectMoneda"));
			Trace.trace(Trace.Debug, "", "### selectMoneda " + getValueAt("selectMoneda"));
			setValueAt("monto", getValueAt("monto"));
			Trace.trace(Trace.Debug, "", "### monto " + getValueAt("monto"));
			setValueAt("descripNegociacion", getValueAt("descripNegociacion"));
			Trace.trace(Trace.Debug, "", "### descripNegociacion " + getValueAt("descripNegociacion"));
			setValueAt("referenciaOpe", numOperacion);
			Trace.trace(Trace.Debug, "", "### referenciaOpe " + getValueAt("referenciaOpe"));
			setValueAt(AVANCE_OPE, getValueAt(AVANCE_OPE));
			Trace.trace(Trace.Debug, "", "### avanceOpe ###" + getValueAt(AVANCE_OPE));
			setValueAt("validaCta", "");
			Trace.trace(Trace.Debug, "", "### validaCta " + getValueAt("validaCta"));
			setValueAt("selectOpe", getValueAt("selectOpe"));
			Trace.trace(Trace.Debug, "", "### selectOpe " + getValueAt("selectOpe"));
			
			//INI incidencia 181 FX CMC 13/02/2019
			if(selectOpe.equals("T2") || selectOpe.equals("H2")){
				consultarDataAvance();
			}
			//INI incidencia 181 FX CMC 13/02/2019
			
			setValueAt(TASA_DIVISA, getValueAt(TASA_DIVISA));
			Trace.trace(Trace.Debug, "", "### tasaDivisa# " + getValueAt(TASA_DIVISA));
			setValueAt(TASA_DIVISA_PESO, getValueAt(TASA_DIVISA_PESO));
			Trace.trace(Trace.Debug, "", "### tasaDivisaPeso:# " + getValueAt(TASA_DIVISA_PESO));
			setValueAt(TASA_USD_PESO, getValueAt(TASA_USD_PESO));
			Trace.trace(Trace.Debug, "", "### tasaUsdPeso ##" + getValueAt(TASA_USD_PESO));
			setValueAt("equivPesos", getValueAt("equivPesos"));
			Trace.trace(Trace.Debug, "", "### equivPesos " + getValueAt("equivPesos"));
			setValueAt("validaCta", "S");
			Trace.trace(Trace.Debug, "", "### validaCta " + getValueAt("validaCta"));
			
			if(getValueAt("msjError").equals("")){
				if(selectOpe.equals("T1") || selectOpe.equals("T2")){
					setEstado("4");
				}else if(selectOpe.equals("H1") || selectOpe.equals("H2")){
					//INI incidencia 125 FX CMC 11/01/2019
					/*icContextoSesion = (IndexedCollection) getElementAt("s_salida_cuentas.s_lista_cuentas");
					listaCuentasOP = (IndexedCollection) getElementAt("lista_cuentas");
					copiarIndexedCollection(icContextoSesion, listaCuentasOP);*/
					//FIN incidencia 125 FX CMC 11/01/2019
					cargarCuentas();
					setValueAt("docBenefi", " |@| |@| |@| |@| |@| ");//CMC Incidencia 16 FX 02/02/2018
					setEstado("5");
				}
				
			}
			Trace.trace(Trace.Debug, "", " FIN modificarOperaciones() ");
		}catch(Exception e){
			setValueAt("msjError", "SISTEMA TEMPORALMENTE NO DISPONIBLE");
			Trace.trace(Trace.Debug, "", "### Falla en modificarOperaciones() Global *** Lanzando la consulta" + e);
//INI CMC - FUNCION DE NEGOCIO - PRUEBA - 19/09/2019
		} finally {
			controlOM = null;
		}
//FIN CMC - FUNCION DE NEGOCIO - PRUEBA - 19/09/2019
		System.out.println("Estado "+getEstado());
		Trace.trace(Trace.Debug, "", " Fin modificarOperaciones()");
	}
	
	public void consultaBeneficiarios() throws Exception {

		try {
			//INI Incidencia 102 FX 19/11/2018		
			String tamanoPag = "20";//Incidencia 102 FX 8/11/2018
			Trace.trace(Trace.Debug, "", "### consultaBeneficiarios() - inicio beneficiarios");
			om = creaOM(sOmConsultaBeneficiarios);
			///// Se debe asignar el valor correspondientes a la FN
			om.setValueAt("Entrada.BINDAUX1", "T");
			om.setValueAt(ENTRADA_BCODACCC, getValueAt(VALUE_LOGON).toString());
			om.setValueAt("Entrada.BNUMAUX2", tamanoPag);//Incidencia 102 FX 8/11/2018
			IndexedCollection listaCuentas;
			int paginaOP = 0;
			//INICIO Incidencia 102 FX 14/11/2018	
			Trace.trace(Trace.Debug, "", "### IF ANTES DE SI ES NULO " + (String)getValueAt("pagina_actual"));
			if(getValueAt("ppagina") == null)
			{
				Trace.trace(Trace.Debug, "", "### IF SI ES NULO " + (String)getValueAt("pagina_actual"));
				setValueAt("ppagina","");
			}
							
			if(!getValueAt("ppagina").equals("")) 
			{
				Trace.trace(Trace.Debug, "", "### IF SI ES OTRA PAGINA " + (String)getValueAt("pagina_actual"));
				paginaOP = Integer.parseInt(String.valueOf(getValueAt("pagina_actual")));				
				om.setValueAt("Entrada.BNUMAUX1", String.valueOf(paginaOP + 1));//Se solicita la pág siguiente
				setValueAt("pagina_actual",  String.valueOf(paginaOP + 1));
			}else
			{				
				Trace.trace(Trace.Debug, "", "### CUALQUIER OPERACION DIFERENTE DE VER MAS" + (String)getValueAt("pagina_actual"));
				om.setValueAt("Entrada.BNUMAUX1", "1");//Se solicita la pág 1	
				setValueAt("pagina_actual", "1");
				Trace.trace(Trace.Debug, "", "### IF SI ES PRIMERA PAGINA SI SETEA op " + (String)getValueAt("pagina_actual"));	
			}

			//FIN 102 FX 14/11/2018
//INI CMC - FUNCION DE NEGOCIO FSTE - PRUEBA - 19/09/2019
			controlOM = new OpControl();
			controlOM.setConfig((String)getValueAt("datosAPP.pb_cod_serv"),
								(String)getValueAt("datosAPP.iv-cod_emp")+(String)getValueAt("datosAPP.iv-cod_usu"),
								(String)getValueAt("datosAPP.iv-cod_usu"),
								creaOM("sign_on_om"), 
								creaOM("sign_on_om"),
								om, 
								creaOM(om.getName()));
			controlOM.control_f100();
//FIN CMC - FUNCION DE NEGOCIO FSTE - PRUEBA - 19/09/2019
			Trace.trace(Trace.Debug, "", "### consultaBeneficiarios() - sOmConsultaBeneficiarios ejecutada ");
			
			Trace.trace(Trace.Debug, "", "### BANDERA PAGINACION " + om.getValueAt("Salida.PAGINA-ACTUAL"));
			Trace.trace(Trace.Debug, "", "###8 PAGINA ACTUAL " + (String)getValueAt("pagina_actual"));
			setValueAt("bandera_paginacion", (om.getValueAt("Salida.PAGINA-ACTUAL")).toString());//Bandera
			//setValueAt("pagina_actual", om.getValueAt("Salida."));//Página actual
			
			
			IndexedCollection iccuentas = (IndexedCollection) om.getElementAt("Salida.ListaCUENTAS-BENEFIC");

			int tamano = iccuentas.size() - 1;
			
			listaCuentas = (IndexedCollection) getElementAt("listaCuentasB");
		
			if(Integer.parseInt(String.valueOf(getValueAt("pagina_actual"))) <= 1) 
			{		
				Trace.trace(Trace.Debug, "", "### ENTRA SI PÁGINA ACTUAL ES MENOR A 1 ");
				listaCuentas.removeAll();
			}
			
			
			
				for (int i = tamano; i >= 0; i--) {	
				KeyedCollection kCuentas = (KeyedCollection) iccuentas.getElementAt(i);
				
				String cuenta = (String) kCuentas.getValueAt("NOMBRES-BENEFIC");
				Trace.trace(Trace.Debug, "", "### consultaBeneficiarios() - lista: " + cuenta);
				
				if (cuenta == null) {
					iccuentas.removeElementAt(i);
				} else if (cuenta == "") {
					iccuentas.removeElementAt(i);
				} else if (cuenta == " ") {
					iccuentas.removeElementAt(i);
				} else if (cuenta.trim().equals("")) {
					iccuentas.removeElementAt(i);
				}else if (cuenta.trim().equals(null)) {
					iccuentas.removeElementAt(i);
				}
			}
			Trace.trace(Trace.Debug, "", "### consultaBeneficiarios() - copiar datos a op");

			// listaCuentas = (IndexedCollection) getElementAt("listaCuentasB");
			// listaCuentas.removeAll();
			copiar2IndexedCollection(iccuentas, listaCuentas);
			Trace.trace(Trace.Debug, "", "### consultaBeneficiarios() - Finaliza el metodo ");
			setValueAt("msjError", "");
			//FIN Incidencia 102 FX 19/11/2018
		} catch (Exception e) {
			e.printStackTrace();
			setValueAt("msjError", "SISTEMA TEMPORALMENTE NO DISPONIBLE");
			Trace.trace(Trace.Debug, "", "### consultaBeneficiarios() - Falla en el metodo " + e);
			
//INI CMC - FUNCION DE NEGOCIO - PRUEBA - 19/09/2019
		} finally {
			controlOM = null;
		}
//FIN CMC - FUNCION DE NEGOCIO - PRUEBA - 19/09/2019
	}
	
	
	public void copiarDeOpListaGirosOP(KeyedCollection kGiro) throws Exception {
		Trace.trace(Trace.Debug, "", " Inicio copiarDeOpListaGirosOP() - Cargar variables de operacion");
		//INI incidencia 181 FX CMC 27/03/2019
		String avancePeticion = "";
		String tasaAvancePeticion = "";
		String tasaUSDPesoPeticion = "";
		String tasaLineaPeticion = "";
		String resultadoAvance ="";
		String codigoU ="";
		String banbene ="";
		//FIN incidencia 181 FX CMC 27/03/2019	
		RequestBankTradeService peticion = new RequestBankTradeService();
		Trace.trace(Trace.Debug, "", "### copiarDeOpListaGirosOP() - PeticionWebService loaded " + getValueAt("selectOpe"));
		if(getValueAt("codigoUnitario")!=null&&getValueAt("paisbancobene")!=null) {
			codigoU = (String) getValueAt("codigoUnitario");
			banbene = (String) getValueAt("paisbancobene");
			if(banbene.equals("GBR"))
			{
				codigoU = "/ACC/SORT CODE "+codigoU;
			}else if(banbene.equals("CAN"))
			{
				codigoU = "/ACC/CODIGO TRANSIT "+codigoU;
			}
		}
		if(kGiro.getValueAt("FCHOPE")!=null){
			String fechaOpeD =  kGiro.getValueAt("FCHOPE").toString().substring(0, 4);
			String fechaOpeM =  kGiro.getValueAt("FCHOPE").toString().substring(5, 7);
			String fechaOpeY =  kGiro.getValueAt("FCHOPE").toString().substring(8, 10);
			String fechaOpe = fechaOpeD + "/" + fechaOpeM + "/" + fechaOpeY;
			Trace.trace(Trace.Debug, "", "### copiarDeOpListaGirosOP() FCHOPE: " + fechaOpe);
		}
		
		if(kGiro.getValueAt("FCHVAL")!=null){
			String fechaValD =  kGiro.getValueAt("FCHVAL").toString().substring(0, 2);
			String fechaValM =  kGiro.getValueAt("FCHVAL").toString().substring(3, 5);
			String fechaValY =  kGiro.getValueAt("FCHVAL").toString().substring(6, 10);
			String fechaVal = fechaValD + "/" + fechaValM + "/" + fechaValY;
			Trace.trace(Trace.Debug, "", "### copiarDeOpListaGirosOP() FCHVAL: " + fechaVal);
		}
		
		Date fechaActual = new Date();
		Trace.trace(Trace.Debug, "", "### PeticionWebService fechaActual: " + fechaActual);
		
		peticion.setFechaHoraNegociacion(fechaActual);
		
		
		Trace.trace(Trace.Debug, "", "### copiarDeOpListaGirosOP() - Asignacion de valores fijos Peticion Ws");
		peticion.setIndMoneda("");
		peticion.setID_BCOOrdenante(null);
		peticion.setNomBcoOrd1(null);
		peticion.setIdSwTpBcoOrd("SW");
		peticion.setCampana(null);
		peticion.setTyVended(null);
		peticion.setNoVended(null);
		peticion.setDigVended(null);
		
		peticion.setNoCtaComm(null);
		peticion.setDeComm(null);
		peticion.setNoCtaPrincipal(null);
		peticion.setNoCtaPrincipalCre(null);//INC BT15.1 PRD FX 05-07-2019
		peticion.setIdSucursal(null);
		peticion.setCanal("80");
		peticion.setTyUsuario("JR");
		peticion.setCurDbComm("COP");
		peticion.setTpCambioComm("1.000000000");												
		peticion.setCodOpecampo23b("CRED");
		peticion.setTyNegoci("**");
		
		if(kGiro.getValueAt("FNUMCA")!=null && kGiro.getValueAt("FNUMCA")!=""){
			peticion.setNuCambiario(kGiro.getValueAt("FNUMCA").toString());
		}else{
			// se ingresa un Numeral Cambiario temporal
			peticion.setNuCambiario("5E02904");
		}
		
		
		peticion.setSubcanal(null);
		
		Trace.trace(Trace.Debug, "", "### copiarDeOpListaGirosOP() - Asignacion de valores Operacion Peticion Ws");
		
		setValueAt("monto", kGiro.getValueAt("MONTO").toString());
		peticion.setMonNegoc(kGiro.getValueAt("MONTO").toString());
		Trace.trace(Trace.Debug, "", "### setMonNegoc: " + kGiro.getValueAt("MONTO").toString());
		if(kGiro.getValueAt("DESTRAN")!=null){
			setValueAt("descripNegociacion", kGiro.getValueAt("DESTRAN").toString());
			peticion.setDeOperac(kGiro.getValueAt("DESTRAN").toString());
			peticion.setInfcam70Sw1(kGiro.getValueAt("DESTRAN").toString());
			Trace.trace(Trace.Debug, "", "### setInfcam70Sw1: " + kGiro.getValueAt("DESTRAN").toString());
			Trace.trace(Trace.Debug, "", "### setDeOperac: " + kGiro.getValueAt("DESTRAN").toString());
		}else{
			setValueAt("descripNegociacion", " ");
			Trace.trace(Trace.Debug, "", "### setDeOperac: NULL/NO TRAE: valor no asignado");
		}
		if(!codigoU.equalsIgnoreCase("")) {
			peticion.setIdCampo72(codigoU);	
		}
		peticion.setIdCliente(getValueAt("num_cuenta").toString());
		Trace.trace(Trace.Debug, "", "### setIdCliente: " + getValueAt("num_cuenta").toString());
		peticion.setNoOperac1(numOperacion);
		Trace.trace(Trace.Debug, "", "### setNoOperac1: " + numOperacion);
		
		String selectMoneda = kGiro.getValueAt("MONEDA").toString();
		if(selectMoneda!= null && selectMoneda!= ""){
			selectMoneda= selectMoneda.substring(0,3);
			}
		setValueAt("selectMoneda", selectMoneda);
		peticion.setCurNegoc(selectMoneda);
		Trace.trace(Trace.Debug, "", "### setCurNegoc: " + selectMoneda);
		
		String selectOpe = getValueAt("selectOpe").toString();
		
		if(selectOpe.equals("T1") || selectOpe.equals("T2")){
			peticion.setTyOperac("TFOUS");
			peticion.setTyTransa("T");
			Trace.trace(Trace.Debug, "", "### setTyTransa: T");
			peticion.setSubcanal(null);
		}else if(selectOpe.equals("H1") || selectOpe.equals("H2")){
			peticion.setSubcanal(null);
			peticion.setTyOperac("TFIUS");
			peticion.setTyTransa("H");
			Trace.trace(Trace.Debug, "", "### setTyTransa: H");
		}
		
		Trace.trace(Trace.Debug, "", "*** copiarDeOpListaGirosOP() - Asignacion de valores de TASA Peticion Ws");
		peticion.setMonAvance(kGiro.getValueAt("MONTO").toString());
		Trace.trace(Trace.Debug, "", "### setMonAvance: " + kGiro.getValueAt("MONTO").toString());
		if(getValueAt("selectOpe").toString().equals("T1") || getValueAt("selectOpe").toString().equals("H1")){
			Trace.trace(Trace.Debug, "", "### copiarDeOpListaGirosOP()+1 ");
			peticion.setTasaDivi(Double.valueOf(kGiro.getValueAt("TASAPE").toString()));
			Trace.trace(Trace.Debug, "", "### etTasaDivi: " + Double.valueOf(kGiro.getValueAt("TASAPE").toString()));
			peticion.setTasaUsd(Double.valueOf(kGiro.getValueAt("TASAUSD").toString()));
			Trace.trace(Trace.Debug, "", "### setTasaUsd: " + Double.valueOf(kGiro.getValueAt("TASAUSD").toString()));
			peticion.setTasaLinea(Double.valueOf(kGiro.getValueAt("TASADIV").toString()));
			Trace.trace(Trace.Debug, "", "### setTasaLinea: " + Double.valueOf(kGiro.getValueAt("TASADIV").toString()));
			peticion.setTasaAvance(kGiro.getValueAt("TASAPE").toString());
			Trace.trace(Trace.Debug, "", "### setTasaAvance: " + Double.valueOf(kGiro.getValueAt("TASAPE").toString()));
			peticion.setNoAvance("STD1");
			Trace.trace(Trace.Debug, "", "### setTyNegoci: NL" );
			////////////////////////
			setValueAt(AVANCE_OPE, "STD1");
			setValueAt(TASA_DIVISA, new Double(kGiro.getValueAt("TASADIV").toString()));
			setValueAt(TASA_DIVISA_PESO, new Double(kGiro.getValueAt("TASAPE").toString()));
			setValueAt(TASA_USD_PESO, new Double(kGiro.getValueAt("TASAUSD").toString()));
			setValueAt("equivPesos", new Double(kGiro.getValueAt("EQUIPES").toString()));
			
		}else if(getValueAt("selectOpe").toString().equals("T2") || getValueAt("selectOpe").toString().equals("H2")){
			Trace.trace(Trace.Debug, "", "### copiarDeOpListaGirosOP()+2 ");
			
			if(kGiro.getValueAt("NUMAVA")!=null){
				peticion.setNoAvance(kGiro.getValueAt("NUMAVA").toString());
				Trace.trace(Trace.Debug, "", "### setNoAvance: " + kGiro.getValueAt("NUMAVA").toString());
				setValueAt(AVANCE_OPE, kGiro.getValueAt("NUMAVA").toString());
			}else{
				peticion.setNoAvance(null);
				setValueAt(AVANCE_OPE, "");
			}
			//INI incidencia 181 FX CMC 27/03/2019
			Trace.trace(Trace.Debug, "", "### setTyNegoci: NM" );
			avancePeticion = getValueAtOfdefault(AVANCE_OPE, "0");			
			resultadoAvance = formateoAvance(avancePeticion);
			Trace.trace(Trace.Debug,"","Modificacion: resultadoAvance:"+resultadoAvance);
			setValueAt(AVANCE_OPE, resultadoAvance);
			tasaAvancePeticion = getValueAtOfdefault(TASA_DIVISA_PESO, "0");
			Trace.trace(Trace.Debug, "", "#### tasaDivisaPeso:"+tasaAvancePeticion);
			tasaUSDPesoPeticion = getValueAtOfdefault(TASA_USD_PESO, "0");
			Trace.trace(Trace.Debug, "", "### tasaUsdPeso:"+tasaUSDPesoPeticion);
			tasaLineaPeticion = getValueAtOfdefault(TASA_DIVISA, "0");
			Trace.trace(Trace.Debug, "", "### tasaDivisa:"+tasaLineaPeticion);
			peticion.setNoAvance(resultadoAvance);
			peticion.setTasaDivi( Double.parseDouble(tasaAvancePeticion));	
			peticion.setTasaAvance(tasaAvancePeticion);
			peticion.setTasaUsd(Double.parseDouble(tasaUSDPesoPeticion));
			peticion.setTasaLinea(Double.parseDouble(tasaLineaPeticion));			
			/*
			peticion.setTasaDivi(null);		
			peticion.setTasaUsd(null);
			peticion.setTasaLinea(null);
			peticion.setTasaAvance(null);
			setValueAt(TASA_DIVISA, new Double("0"));
			setValueAt(TASA_DIVISA_PESO, new Double("0"));
			setValueAt(TASA_USD_PESO, new Double("0"));
			setValueAt("equivPesos", new Double("0"));
			*/
			//FIN incidencia 181 FX CMC 27/03/2019
		}
		
		Trace.trace(Trace.Debug, "", "### copiarDeOpListaGirosOP() - PeticionWebService lista para setter");
		setValueAt("PeticionWebService", peticion);
		setValueAt("msjError", "");
		Trace.trace(Trace.Debug, "", " Fin copiarDeOpListaGirosOP()");
	}
	
	protected void copiarAOMConsultaUnica(String numOperacion, String tipoOperacion) throws Exception{
		
		KeyedCollection kCuenta,kEntradaGiros;
		IndexedCollection icContextoSesion,listaCuentasOP = null;
		Enumeration enContextoSesion1;
		String clave = "";
		Double idPaginaHost = new Double(0);
		
		try {
			Trace.trace(Trace.Debug, "", " Inicio copiarAOMConsultaUnica() - " + numOperacion);
			//INI INC 125 CMC 11-01-2019
			clave = cargarCuentas();
			/*
			icContextoSesion = (IndexedCollection) getElementAt("s_salida_cuentas.s_lista_cuentas");
			listaCuentasOP = (IndexedCollection) getElementAt("lista_cuentas");
			copiarIndexedCollection(icContextoSesion, listaCuentasOP);
			enContextoSesion1 = listaCuentasOP.getEnumeration();
			
			while(enContextoSesion1.hasMoreElements())
			{
				kCuenta = (KeyedCollection) enContextoSesion1.nextElement();
				clave = (String) kCuenta.getValueAt("clave_asunto");
			}
			FIN INC 125 CMC 11-01-2019*/
			
			Trace.trace(Trace.Debug, "", "### copiarAOMConsultaUnica() - Cuenta cargada " + clave);
			setValueAt("selectCuentaOrden", " ");
			
			kEntradaGiros = (KeyedCollection)getElementAt("divisas_consulta_unica_operaciones_om-data.entrada");
			kEntradaGiros.setValueAt("FECHIN", "");
			kEntradaGiros.setValueAt("FECHFN", "");
			kEntradaGiros.setValueAt("TIPOPER", tipoOperacion);
			kEntradaGiros.setValueAt("NCUENT",  clave);
			String user = getValueAt(VALUE_COD_USUA).toString();
			for (int i=user.length();i<8;i++)	
			{ 
				user=user + "X"; 
			}
			String numclieTMP = getValueAt(VALUE_LOGON).toString() + user + "0000000NC";
			Trace.trace(Trace.Debug, "", "### copiarAOMConsultaUnica()... numclieTMP :" + numclieTMP);
			kEntradaGiros.setValueAt("NUMCLIE", numclieTMP);
			setValueAt("referenciaOpe", numOperacion);
			Trace.trace(Trace.Debug, "", "### copiarAOMConsultaUnica() numOperacion: " + numOperacion);
			idPaginaHost = new Double(numOperacion);
			
			Trace.trace(Trace.Debug, "", "### copiarAOMConsultaUnica() idPaginaHost " + idPaginaHost);
			kEntradaGiros.setValueAt("MOVIMT", idPaginaHost);
			
			setValueAt("msjError", "");
			Trace.trace(Trace.Debug, "", " Fin copiarAOMConsultaUnica()");
			
			}catch (Exception e) {
				setValueAt("msjError", "SISTEMA TEMPORALMENTE NO DISPONIBLE");
				Trace.trace(Trace.Debug, "", "### ERROR en copiarAOMConsultaUnica() " + e);
				e.printStackTrace();
			}
	}
	
	private void copiarDeOMConsultaUnica() throws Exception {
		IndexedCollection listaGirosOP, listaGirosOM;
		int idEstado ;
		
		
		try {
			Trace.trace(Trace.Debug, "", " Inicio copiarDeOMConsultaUnica()");
			
			listaGirosOM = (IndexedCollection) getElementAt("divisas_consulta_unica_operaciones_om-data.salida.listaGiros");
			listaGirosOP= (IndexedCollection) getElementAt("listaGirosTotal");
			listaGirosOP.removeAll();
			
			//Se eliminan registros que vienen vacios
				KeyedCollection kGiroOp =(KeyedCollection) DataElement.getExternalizer().convertTagToObject(listaGirosOP.getElementSubTag());
				KeyedCollection kGiro =(KeyedCollection) listaGirosOM.getElementAt(0);
				Trace.trace(Trace.Debug, "", "### Iniciando formateo de registros.");
					//Tratamiento de fechas y horas
					SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat fmt2 = new SimpleDateFormat("dd/MM/yyyy");
					SimpleDateFormat fmt3 = new SimpleDateFormat("yyyy/MM/dd");
					SimpleDateFormat fmtH = new SimpleDateFormat("HH.mm");
					SimpleDateFormat fmtH2 = new SimpleDateFormat("hh:mm aa");
					String fechaOpe= String.valueOf(kGiro.getValueAt("FCHOPE"));
					String fechaVal= String.valueOf(kGiro.getValueAt("FCHVAL"));
					String horaOpe= String.valueOf(kGiro.getValueAt("HOPERA"));
					String estado= String.valueOf(kGiro.getValueAt("ESTADO")).trim();
					String monto= String.valueOf(kGiro.getValueAt("MONTO")).trim();			
					String tasaDiv= String.valueOf(kGiro.getValueAt("TASADIV")).trim();	
					String tasaPeso= String.valueOf(kGiro.getValueAt("TASAPE")).trim();	
					String tasaUsd= String.valueOf(kGiro.getValueAt("TASAUSD")).trim();	
					String equivPeso= String.valueOf(kGiro.getValueAt("EQUIPES")).trim();
					
					Trace.trace(Trace.Debug, "", "### equivPeso: " + equivPeso);
					if(kGiro.getValueAt("EQUIPES")!=null){
						Double equivPesoCalc= new Double(equivPeso);
						Double montoCalc= new Double(monto);
						Double tasaPesoCalc= new Double(tasaPeso);
						if (equivPeso.equals("0.0000")){
							equivPesoCalc = tasaPesoCalc * montoCalc;
							equivPeso = String.valueOf(equivPesoCalc);
							Trace.trace(Trace.Debug, "", "### equivPeso: " + equivPeso);
							Trace.trace(Trace.Debug, "", "### equivPesoCalc: " + equivPesoCalc);
						}
					}
					
					if(fechaOpe.compareTo("null")!=0)
					kGiro.setValueAt("FCHOPE", fmt3.format(fmt.parse(fechaOpe)));
					
					if(fechaVal.compareTo("null")!=0)
					kGiro.setValueAt("FCHVAL", fmt2.format(fmt.parse(fechaVal)));
					if(horaOpe.compareTo("null")!=0)
					kGiro.setValueAt("HOPERA", fmtH2.format(fmtH.parse(horaOpe)));
					//Tratamiento de estado
					kGiro.setValueAt("ESTADO", "");
					if(getValueAt("tipoOperacion").equals("T")){
						if(estado.compareTo("null")!=0){
							idEstado =Integer.parseInt(estado);
							switch (idEstado) {
							case 1:
								kGiro.setValueAt("ESTADO", "En proceso Cliente");
								break;
							case 2:
								kGiro.setValueAt("ESTADO", "Pendiente de firma");
								break;
							case 3:
								kGiro.setValueAt("ESTADO", PROCESO_BANCO);
								break;
							case 4:
								kGiro.setValueAt("ESTADO", "Rechazada");
								break;
							case 5:
								kGiro.setValueAt("ESTADO", "Devuelta");
								break;
							case 6:
								kGiro.setValueAt("ESTADO", "Enviada al Exterior");
								break;
							case 7:
								kGiro.setValueAt("ESTADO", "En Proceso Cliente");
								break;
							case 8:
								kGiro.setValueAt("ESTADO", "Rechazada");
								break;
							case 9:
								kGiro.setValueAt("ESTADO", "Devuelta");
								break;
							case 10:
								kGiro.setValueAt("ESTADO", "Enviada al Extranjero");
								break;
							case 11:
								kGiro.setValueAt("ESTADO", "Pendiente de firma");
								break;
							default:
								kGiro.setValueAt("ESTADO", "");
								break;
							}
						}
					}else if(getValueAt("tipoOperacion").equals("H")){
						if(estado.compareTo("null")!=0){
							idEstado =Integer.parseInt(estado);
							switch (idEstado) {
							case 1:
								kGiro.setValueAt("ESTADO", "En proceso Cliente");
								break;
							case 2:
								kGiro.setValueAt("ESTADO", "Pendiente de firma");
								break;
							case 3:
								kGiro.setValueAt("ESTADO", PROCESO_BANCO);
								break;
							case 4:
								kGiro.setValueAt("ESTADO", "Rechazada");
								break;
							case 5:
								kGiro.setValueAt("ESTADO", "Devuelta");
								break;
							case 6:
								kGiro.setValueAt("ESTADO", "Abonada en Cuenta");
								break;
							case 8:
								kGiro.setValueAt("ESTADO", "Rechazada");
								break;
							case 9:
								kGiro.setValueAt("ESTADO", "Devuelta");
								break;
							case 10:
								kGiro.setValueAt("ESTADO", "Abonada en Cuenta");
								break;
							case 200:
								kGiro.setValueAt("ESTADO", "Pendiente de negociar");
								break;
							case 350:
								kGiro.setValueAt("ESTADO", "Negociada Oficina");
								break;
							case 11:
								kGiro.setValueAt("ESTADO", "Pendiente de firma");
								break;
							default:
								kGiro.setValueAt("ESTADO", "");
								break;
							}
						}
					}
					
					//Tratamiento de Importes
					if(monto.compareTo("null")!=0)
					kGiro.setValueAt("MONTO", monto);//INC IN 318 FX AMALFI 17-12-2019 Control para envio de monto a Banktrade String
					if(tasaDiv.compareTo("null")!=0)
					kGiro.setValueAt("TASADIV", new Double(tasaDiv));
					if(tasaPeso.compareTo("null")!=0)
					kGiro.setValueAt("TASAPE", new Double(tasaPeso));
					if(tasaUsd.compareTo("null")!=0)
					kGiro.setValueAt("TASAUSD", new Double(tasaUsd));
					if(equivPeso.compareTo("null")!=0)
					kGiro.setValueAt("EQUIPES", new Double(equivPeso));

					Trace.trace(Trace.Debug, "", "############# Giro #############");
					Trace.trace(Trace.Debug, "", "### NCUENT   =" + kGiro.getValueAt("NCUENT"));
					Trace.trace(Trace.Debug, "", "### FCHOPE   =" + kGiro.getValueAt("FCHOPE"));
					Trace.trace(Trace.Debug, "", "### FCHVAL   =" + kGiro.getValueAt("FCHVAL"));
					Trace.trace(Trace.Debug, "", "### HOPERA   =" + kGiro.getValueAt("HOPERA"));
					Trace.trace(Trace.Debug, "", "### MONTO    =" + kGiro.getValueAt("MONTO"));
					Trace.trace(Trace.Debug, "", "### MONEDA   =" + kGiro.getValueAt("MONEDA"));
					Trace.trace(Trace.Debug, "", "### NUMAVA   =" + kGiro.getValueAt("NUMAVA"));
					Trace.trace(Trace.Debug, "", "### TIPNEGO  =" + kGiro.getValueAt("TIPNEGO"));
					Trace.trace(Trace.Debug, "", "### TASADIV  =" + kGiro.getValueAt("TASADIV"));
					Trace.trace(Trace.Debug, "", "### TASAPE   =" + kGiro.getValueAt("TASAPE"));
					Trace.trace(Trace.Debug, "", "### TASAUSD  =" + kGiro.getValueAt("TASAUSD"));
					Trace.trace(Trace.Debug, "", "### EQUIPES  =" + kGiro.getValueAt("EQUIPES"));
					Trace.trace(Trace.Debug, "", "### FNUMCA   =" + kGiro.getValueAt("FNUMCA"));
					Trace.trace(Trace.Debug, "", "### DESTRAN  =" + kGiro.getValueAt("DESTRAN"));
					Trace.trace(Trace.Debug, "", "### ORDENA   =" + kGiro.getValueAt(ORDENA));
					Trace.trace(Trace.Debug, "", "### BENEFIC  =" + kGiro.getValueAt("BENEFIC"));
					Trace.trace(Trace.Debug, "", "### BANBENE  =" + kGiro.getValueAt("BANBENE"));
					Trace.trace(Trace.Debug, "", "### PAIS     =" + kGiro.getValueAt("PAIS"));
					Trace.trace(Trace.Debug, "", "### CODSWIFT =" + kGiro.getValueAt("CODSWIFT"));
					Trace.trace(Trace.Debug, "", "### CODABBA  =" + kGiro.getValueAt("CODABBA"));
					Trace.trace(Trace.Debug, "", "### ESTADO   =" + kGiro.getValueAt("ESTADO"));
					Trace.trace(Trace.Debug, "", "### BENEFI   =" + kGiro.getValueAt("BENEFI"));
					Trace.trace(Trace.Debug, "", "### ORDEN    =" + kGiro.getValueAt("ORDEN"));
					Trace.trace(Trace.Debug, "", "### MOVIMT   =" + kGiro.getValueAt("MOVIMT"));
					Trace.trace(Trace.Debug, "", "### CUENTABON=" + kGiro.getValueAt(CUENTABON));
					Trace.trace(Trace.Debug, "", "### BANCOINT =" + kGiro.getValueAt("BANCOINT"));
					Trace.trace(Trace.Debug, "", "############# ----- #############");
					
					Trace.trace(Trace.Debug, "", "### copiando kGiroOp <--> kGiro");
					//INI incidencia 181 FX CMC 19/02/2019
					setValueAt(TASA_USD_PESO, kGiro.getValueAt("TASAUSD"));
					Trace.trace(Trace.Debug, "", "### tasaUsdPeso:#  " + getValueAt(TASA_USD_PESO));
					//INI incidencia 181 FX CMC 19/02/2019
					////INI incidencia 90 FX CMC 04/09/2018
					setValueAt("selectCuentaOrden", (String) kGiro.getValueAt(CUENTABON));
					Trace.trace(Trace.Debug, "", "### copiando kGiroOp CUENTA antigua modificar "+ getValueAt("selectCuentaOrden"));
					////FIN incidencia 90 FX CMC 04/09/2018
					
					copiarKeyedCollection(kGiro, kGiroOp);
					listaGirosOP.addElement(kGiroOp);
					
					// INI incidencia 102 FX 6-11-2018
										
					Trace.trace(Trace.Debug, "", "### copiarDeOMConsultaUnica() **** Dato pagina actual valor"+getValueAt("ppagina"));
					if (getValueAt("ppagina") == null || getValueAt("ppagina").equals("")) {
						copiarDeOpListaGirosOP(kGiro);
						Trace.trace(Trace.Debug, "", "### copiarDeOMConsultaUnica() **** Dato pagina actual"+getValueAt("ppagina"));
						Trace.trace(Trace.Debug, "", "### copiarDeOMConsultaUnica() **** Datos cargados al Ws");
					}
					// FIN incidencia 102 FX 6-11-2018
					
					setValueAt("msjError", "");
			Trace.trace(Trace.Debug, "", " Fin copiarDeOMConsultaUnica()");
		} catch (Exception e) {
			setValueAt("msjError", "SISTEMA TEMPORALMENTE NO DISPONIBLE");
			Trace.trace(Trace.Debug, "", "### ERROR copiarDeOMConsultaUnica() - copiando registros " + e);
			e.printStackTrace();
		}
		
	}
	
	public void modificarForms() throws Exception{
		Trace.trace(Trace.Debug, "", " Inicio modificarForms()");
		
		String selectMoneda = (String) getValueAt("selectMoneda");
		
		setValueAt("selectOpe", getValueAt("selectOpe"));
		Trace.trace(Trace.Debug, "", "### selectOpe " + getValueAt("selectOpe"));
		setValueAt("selectCuentaOrden", getValueAt("selectCuentaOrden"));
		Trace.trace(Trace.Debug, "", "### selectCuentaOrden " + getValueAt("selectCuentaOrden"));
		if(selectMoneda!= null && selectMoneda!= ""){
		    if (selectMoneda.length() > 2) { //INI incidencia CMC FX 02/02/2018
			selectMoneda= selectMoneda.substring(0,3);
		    }//FIN incidencia CMC FX 02/02/2018
			}
		setValueAt("selectMoneda", selectMoneda);
		Trace.trace(Trace.Debug, "", "### selectMoneda " + getValueAt("selectMoneda"));
		setValueAt("monto", getValueAt("monto"));
		Trace.trace(Trace.Debug, "", "### monto " + getValueAt("monto"));
		setValueAt("equivPesos", getValueAt("equivPesos"));
		Trace.trace(Trace.Debug, "", "### equivPesos " + getValueAt("equivPesos"));
		setValueAt("descripNegociacion", getValueAt("descripNegociacion"));
		Trace.trace(Trace.Debug, "", "### descripNegociacion " + getValueAt("descripNegociacion"));
		
		setValueAt("referenciaOpe", getValueAt("referenciaOpe"));
		Trace.trace(Trace.Debug, "", "### referenciaOpe " + getValueAt("referenciaOpe"));
		setValueAt("docBenefi", getValueAt("docBenefi"));
		Trace.trace(Trace.Debug, "", "### docBenefi " + getValueAt("docBenefi"));
		setValueAt("errorBeneficiario","");
		String campos = "nombresbenefic@direccionebenef@paisbeneficiar@ciudadbenefcia@"
				+ "telefonobenefi@tiposabanco1@codigosabanco1@cuentabenefica@codigoUnitario";
		int numCampos = verificarBeneficiarioSeleccionado(campos);
		Trace.trace(Trace.Debug, "", "### numCampos " + numCampos);
		setValueAt(TIPO_FONDO_GIRO,getValueAt(TIPO_FONDO_GIRO));
		if(numCampos>0) {
			setValueAt("errorBeneficiario","S");
			setEstado("4");
			return;
		}
		try{
			//INI incidencia 90 CMC FX 09/08/2018
			//if(getValueAt("selectOpe").equals("T1") || getValueAt("selectOpe").equals("H1")){
			if(getValueAt("selectOpe").equals("H1") ){
			//FIN incidencia 90 CMC FX 09/08/2018
				validarCuenta();
			}else{
				setValueAt("validaCta", "S");
				}
		}catch(Exception e){
			Trace.trace(Trace.Debug, "", "### negociarLinea() - falla en la validacion de la cuenta");
			setValueAt("validaCta","N");
			setValueAt("msjError", "falla en la validacion de la cuenta");//INI INC 6 FX 27/04/2018
		}
		//INI INC 44 FX 30/04/2018
		Trace.trace(Trace.Debug, "", "### msgError "+ getValueAt("msjError").toString());
		if(((getValueAt("msjError").toString()).equals("nullnull") || getValueAt("msjError").equals("")) && getValueAt("validaCta").toString().contains("S")){
			//INI incidencia 225B FX CMC 16/09/2019			
			updatePaso2("");/*INC 61 Fx - 30-05-2018*/
			if(getValueAt("errorCodigo").equals("N")) {
				if(getValueAt("msjError").equals("") || (getValueAt("msjError").toString()).equals("nullnull")){
					setEstado("5");
				}else{
					this.setEstado("ERROR");
					return;
				}	
			}else {
				modificarOperaciones();
				setValueAt("errorSwift", getValueAt("errorCodigo"));
				setValueAt("pagina_actual", "");
				setEstado("4");	
			}

		} else {
				this.setEstado("ERROR");
				return;
		}
		//FIN INC 44 FX 30/04/2018		
		//FIN incidencia 225B FX CMC 16/09/2019
		Trace.trace(Trace.Debug, "", " FIN modificarForms()");
	}
	
	public void consultarOficinaTitular() throws Exception{
		Trace.trace(Trace.Debug, "", "### consultarOficinaTitular() - inicio ");
		
		om = creaOM(sOmOficinaTitular);
		om.setValueAt(ENTRADA_BCODACCC, getValueAt(VALUE_LOGON).toString());
		ejecutarOM(om);
		
		//INI incidencia 183 FX CMC 08/03/2019
				String banderaCodError = "";
				if(om.getValueAt("Salida.COD-ERROR") == null) {
					banderaCodError = "1";
				}else {
					if(om.getValueAt("Salida.COD-ERROR").equals("PME1147")) {
						banderaCodError = "PME1147";
					}
				}
				if(om.getValueAt("Salida.CODAVISO")==null || om.getValueAt("Salida.CODAVISO")=="XXXX" || om.getValueAt("Salida.CODAVISO")=="" || om.getValueAt("Salida.CODAVISO")==" "  || banderaCodError.equals("PME1147")){
					BT_IDSUCURSAL = "0073";
				//FIN incidencia 183 FX CMC 08/03/2019
		}else{
			//INI INC CMC OFICINA GESTORA 27/09/2019
			try {
				String sucursal = (String) getValueAt("codigoSucursal");
				BT_IDSUCURSAL = sucursal;
			} catch (Exception e_gestora) {
				Trace.trace(Trace.Error, "", "consultarOficinaTitular() - Oficina Gestora: " + e_gestora);
			}
			//FIN INC CMC OFICINA GESTORA 27/09/2019
			Trace.trace(Trace.Debug, "", "### Oficina OK: " + BT_IDSUCURSAL);
			//INI incidencia 153 FX CMC 21/01/2019
			if (BT_IDSUCURSAL!=null) {//INI INC CMC OFICINA GESTORA 27/09/2019
			String idSucursalOK = "";
			char[] idSucursalDividido = BT_IDSUCURSAL.toCharArray();
			String n = "";
			for(int i=0; i<idSucursalDividido.length; i++) 
			{
				if(Character.isDigit(idSucursalDividido[i])) 
				{
					idSucursalOK+=idSucursalDividido[i];
				}
			}
			BT_IDSUCURSAL = idSucursalOK;
			Trace.trace(Trace.Debug, "", "### Oficina OK: " + BT_IDSUCURSAL);
			}//INC CMC OFICINA GESTORA 27/09/2019
			//FIN incidencia 153 FX CMC 21/01/2019
		}
		String docuTmp = (String) om.getValueAt("Salida.VALOR-ERROR-DOS");
		
		try{
			setValueAt("nitConsulta", docuTmp);
			Trace.trace(Trace.Debug, "", "### documentoCliente nit para enviar a la validacion baep: " + getValueAt("nitConsulta"));
			
			String tipoDoc = docuTmp.substring(0, 1);
			String numeroDoc = docuTmp.substring(1, 16);
			String digitoDoc = docuTmp.substring(16);
			Trace.trace(Trace.Debug, "", "### documentoCliente formateando 00: " + tipoDoc + " " + numeroDoc + " " + digitoDoc);
			int numEntero = Integer.parseInt(numeroDoc);
			Trace.trace(Trace.Debug, "", "### documentoCliente formateando 11: " + numEntero);
			Object finalNum = tipoDoc+numEntero+digitoDoc ;
			Trace.trace(Trace.Debug, "", "### documentoCliente formateando 22: " + finalNum);
			documentoCliente = (String) finalNum;
			Trace.trace(Trace.Debug, "", "### documentoCliente formateando 33: " + documentoCliente);
		}catch(Exception e){
			
			Trace.trace(Trace.Debug, "", "### documentoCliente no formateado: " + docuTmp);
			Trace.trace(Trace.Debug, "", "###  " + e);
		}
		
		setValueAt("num_cuenta", documentoCliente);
		setValueAt("msjError", "");
		
		Trace.trace(Trace.Debug, "", "### consultarOficinaTitular() - fin ");
	}
	
	public void eliminarBeneficiario() throws Exception{
		Trace.trace(Trace.Debug, "", " Inicio eliminarBeneficiario()");
		
		String documentofull = (String) getValueAt("docBenefi");
		Trace.trace(Trace.Debug, "", "### verUnico - documentofull=" + documentofull);
		String[] parts = documentofull.split("\\|@\\|");//Incidencia 16 CMC FX 06/02/2018
		
		String tipoId = parts[0];
		String numId = parts[1];
		String digVer = parts[2];
		String ctaBenef = parts[3];
		
		String numReferencia = getValueAt(VALUE_LOGON).toString();
		
		try{

			om = creaOM(sOmAltaBeneficiarios);
			
			om.setValueAt("entrada.OPCION", "D");
			om.setValueAt("entrada.TIPBEN", tipoId);
			om.setValueAt("entrada.IDBEN", numId);
			om.setValueAt("entrada.DIGBEN", digVer);
			om.setValueAt("entrada.CUEBEN", ctaBenef);
			om.setValueAt("entrada.REFENC", numReferencia);
			
			ejecutarOM(om);
			setValueAt("msjError", "");
			Trace.trace(Trace.Debug, "", "### eliminarBeneficiario() Se ejcuta la OM sOmAltaBeneficiarios para eliminar");
		}catch(Exception e){
			Trace.trace(Trace.Debug, "", "### ERROR en eliminarBeneficiario()" + e);
			setValueAt("msjError", "Error al intentar eliminar el beneficiario");
		}
		
		setValueAt("indBenef", "B");
		setValueAt("ppagina", "");//INC 102 FX 13/11/2018
		modificarOperaciones();
		setValueAt("indBenef", "");//INC 102 FX 19/11/2018
	}
	
	
	public String obtenerFechaActual(){
		
		Date date = Calendar.getInstance().getTime();
		
		String fechaFinal = "";
		DateFormat fechaFormatter = new SimpleDateFormat("ddMMyyyy");
		fechaFinal = fechaFormatter.format(date);
		
		return fechaFinal;	
	}
	
	public void validarCuenta() throws Exception{
		
		Double importe = new Double(0);
		
		Trace.trace(Trace.Debug, "", " Inicio validarCuenta(): " + getValueAt("selectCuentaOrden") + getValueAt("equivPesos"));
		om = creaOM(sOmEstadoCta);
		om.setValueAt("Entrada.BINDAUX1", "S");
		om.setValueAt("Entrada.BASUNPRO", "XX"+getValueAt("selectCuentaOrden"));
		if(!getValueAt("equivPesos").toString().equals("0.0")){
			importe = new Double(getValueAt("equivPesos").toString());
		}else{
			importe = new Double(1);
		}
			Trace.trace(Trace.Debug, "", "### validarCuenta()- IMPORTE: " + importe);
		om.setValueAt("Entrada.BIMPORTE", importe);
		om.setValueAt(ENTRADA_BCODACCC, getValueAt(VALUE_LOGON).toString());
		
		ejecutarOM(om);
		String Flag = (String) om.getValueAt("Salida.CODAVISO");
		String Aviso = (String) om.getValueAt("Salida.VALOR-ERROR-UNO") + (String) om.getValueAt("Salida.VALOR-ERROR-DOS");//INI INC 35 FX 28/03/2018
		//INI INC 134 FX CMC 30/11/2018
			String codError = (String) om.getValueAt("Salida.COD-ERROR");
			if(codError!= null && codError!="" && codError.trim().equalsIgnoreCase("BGE1320")){
				
			Aviso = "Fondos Insuficientes";	
			}
			//FIN INC 134 FX CMC 30/11/2018
		Trace.trace(Trace.Debug, "", "FIN validarCuenta(): "+ Aviso);
		setValueAt("validaCta", Flag);
		Trace.trace(Trace.Debug, "", " FIN validarCuenta(): " + getValueAt("validaCta"));
		setValueAt("msjError", Aviso);//INI INC 35 FX 28/03/2018
	
	}
	
	public void gestionBeneficiarios() throws Exception{
		Trace.trace(Trace.Debug, "", " Inicio gestionBeneficiarios()");
		String indBenef = (String)getValueAt("indBenef");
		Trace.trace(Trace.Debug, "", "### gestionBeneficiarios() - indBenef " + indBenef);
		
		setValueAt(AVANCE_OPE, getValueAt(AVANCE_OPE));
		Trace.trace(Trace.Debug, "", "### GB avanceOpe " + getValueAt(AVANCE_OPE));
		setValueAt("selectOpe", getValueAt("selectOpe"));
		Trace.trace(Trace.Debug, "", "### GB selectOpe " + getValueAt("selectOpe"));
		setValueAt("selectCuentaOrden", getValueAt("selectCuentaOrden"));
		Trace.trace(Trace.Debug, "", "### GB selectCuentaOrden " + getValueAt("selectCuentaOrden"));
		setValueAt("selectMoneda", getValueAt("selectMoneda"));
		Trace.trace(Trace.Debug, "", "### GB selectMoneda " + getValueAt("selectMoneda"));
		setValueAt("monto", getValueAt("monto"));
		Trace.trace(Trace.Debug, "", "### GB monto " + getValueAt("monto"));
		setValueAt("descripNegociacion", getValueAt("descripNegociacion"));
		Trace.trace(Trace.Debug, "", "### GB descripNegociacion " + getValueAt("descripNegociacion"));
		
		setValueAt("codError", "");
		setValueAt("codePaisBene","");
		setValueAt("codePaisInte","");
		setValueAt("codigoUnitario", "");
		setValueAt("literalCodigoUnitario", "");
		setValueAt("tipobeneficiar", "");
		setValueAt("idbeneficiario", "");
		setValueAt("digivbeneficia", "");
		setValueAt("nombresbenefic", "");
		setValueAt("apellidosbenef", "");
		setValueAt("direccionebenef", "");
		setValueAt("telefonobenefi", "");
		setValueAt("paisbeneficiar", "");
		setValueAt("ciudadbenefcia", "");
		setValueAt("cuentabenefica", "");
		
		setValueAt("nombrebancobe", "");
		setValueAt("tiposabanco1", "");
		setValueAt("codigosabanco1", "");
		setValueAt("paisbancobene", "");
		setValueAt("ciudadbancobe", "");
		
		setValueAt("nombrebancoin", "");
		setValueAt("cuentaintermed", "");
		setValueAt("tiposabanco2", "");
		setValueAt("codigosabanc2", "");
		setValueAt("paisbancointe", "");
		setValueAt("ciudadbancoin", "");
		
		if(indBenef.equals("M")){
			verUnico();
		}
		
		if(indBenef.equals("A")){
			setValueAt("indBenef", indBenef);
		}
		setValueAt("nombreOperacion", "finalizar_operaciones_negociacion_op");
		Trace.trace(Trace.Debug, "", " fin gestionBeneficiarios()");
		setEstado("7");
	}
	
	public void altaBeneficiario() throws Exception{
		Trace.trace(Trace.Debug, "", " INICIO altaBeneficiario()");
		try {
			om = creaOM(sOmAltaBeneficiarios);
			
			om.setValueAt("entrada.OPCION", "I");
			om.setValueAt("entrada.TIPBEN", getValueAt("tipobeneficiar"));
			om.setValueAt("entrada.IDBEN", getValueAt("idbeneficiario"));
			om.setValueAt("entrada.DIGBEN", getValueAt("digivbeneficia"));
			om.setValueAt("entrada.NOMBEN", getValueAt("nombresbenefic"));
			om.setValueAt("entrada.APEBEN", getValueAt("apellidosbenef"));
			om.setValueAt("entrada.DIRBEN", getValueAt("direccionebenef"));
			om.setValueAt("entrada.TELBEN", getValueAt("telefonobenefi"));
			om.setValueAt("entrada.PAIBEN", getValueAt("paisbeneficiar"));
			om.setValueAt("entrada.CIDBEN", getValueAt("ciudadbenefcia"));
			om.setValueAt("entrada.CUEBEN", getValueAt("cuentabenefica"));
			om.setValueAt("entrada.CBANBN", getValueAt("codigosabanco1"));
			om.setValueAt("entrada.TBNBEN", getValueAt("tiposabanco1"));
			om.setValueAt("entrada.BANBEN", getValueAt("nombrebancobe"));
			om.setValueAt("entrada.PBNBEN", getValueAt("paisbancobene"));
			om.setValueAt("entrada.CBNBEN", getValueAt("ciudadbancobe"));
			om.setValueAt("entrada.DIRBBN", getValueAt("direccbancobe"));
			om.setValueAt("entrada.CTAINT", getValueAt("cuentaintermed"));
			om.setValueAt("entrada.CODINT", getValueAt("codigosabanc2"));
			om.setValueAt("entrada.TIPINT", getValueAt("tiposabanco2"));
			om.setValueAt("entrada.NOMINT", getValueAt("nombrebancoin"));
			om.setValueAt("entrada.PAIINT", getValueAt("paisbancointe"));
			om.setValueAt("entrada.CIUINT", getValueAt("ciudadbancoin"));
			om.setValueAt("entrada.DIRINT", getValueAt("direccbancoin"));
			om.setValueAt("entrada.REFENC", getValueAt(VALUE_LOGON).toString());
			om.setValueAt("entrada.CODIGO", getValueAt("codigoUnitario"));
			
			Trace.trace(Trace.Debug, "", "### altaBeneficiario() / lanzando sOmAltaBeneficiarios");
			ejecutarOM(om);
			Trace.trace(Trace.Debug, "", "### altaBeneficiario() / sOmAltaBeneficiarios Ok!");
			setValueAt("msjError", "");
			
		} catch (Exception e) {
			setValueAt("msjError", "Error al dar de alta el beneficiario");
			Trace.trace(Trace.Debug, "", "### ERROR altaBeneficiario()" + e);
			e.printStackTrace();
		}
	}
	
	public void modificarBeneficiario() throws Exception{
		Trace.trace(Trace.Debug, "", " Inicio modificarBeneficiario()");
		try {
			om = creaOM(sOmAltaBeneficiarios);
			
			om.setValueAt("entrada.OPCION", "U");
			om.setValueAt("entrada.TIPBEN", getValueAt("tipobeneficiar"));
			om.setValueAt("entrada.IDBEN", getValueAt("idbeneficiario"));
			om.setValueAt("entrada.DIGBEN", getValueAt("digivbeneficia"));
			om.setValueAt("entrada.NOMBEN", getValueAt("nombresbenefic"));
			om.setValueAt("entrada.APEBEN", getValueAt("apellidosbenef"));
			om.setValueAt("entrada.DIRBEN", getValueAt("direccionebenef"));
			om.setValueAt("entrada.TELBEN", getValueAt("telefonobenefi"));
			om.setValueAt("entrada.PAIBEN", getValueAt("paisbeneficiar"));
			om.setValueAt("entrada.CIDBEN", getValueAt("ciudadbenefcia"));
			om.setValueAt("entrada.CUEBEN", getValueAt("cuentabenefica"));
			om.setValueAt("entrada.CBANBN", getValueAt("codigosabanco1"));
			om.setValueAt("entrada.TBNBEN", getValueAt("tiposabanco1"));
			om.setValueAt("entrada.BANBEN", getValueAt("nombrebancobe"));
			om.setValueAt("entrada.PBNBEN", getValueAt("paisbancobene"));
			om.setValueAt("entrada.CBNBEN", getValueAt("ciudadbancobe"));
			om.setValueAt("entrada.DIRBBN", getValueAt("direccbancobe"));
			om.setValueAt("entrada.CTAINT", getValueAt("cuentaintermed"));
			om.setValueAt("entrada.CODINT", getValueAt("codigosabanc2"));
			om.setValueAt("entrada.TIPINT", getValueAt("tiposabanco2"));
			om.setValueAt("entrada.NOMINT", getValueAt("nombrebancoin"));
			om.setValueAt("entrada.PAIINT", getValueAt("paisbancointe"));
			om.setValueAt("entrada.CIUINT", getValueAt("ciudadbancoin"));
			om.setValueAt("entrada.DIRINT", getValueAt("direccbancoin"));
			om.setValueAt("entrada.REFENC", getValueAt(VALUE_LOGON).toString());
			om.setValueAt("entrada.CODIGO", getValueAt("codigoUnitario"));
			
			Trace.trace(Trace.Debug, "", "### gestionBeneficiarios() - nombresbenefic " + getValueAt("nombresbenefic"));
			Trace.trace(Trace.Debug, "", "### gestionBeneficiarios() - apellidosbenef " + getValueAt("apellidosbenef"));
			Trace.trace(Trace.Debug, "", "### gestionBeneficiarios() - tipobeneficiar " + getValueAt("tipobeneficiar"));
			Trace.trace(Trace.Debug, "", "### gestionBeneficiarios() -  idbeneficiario " + getValueAt("idbeneficiario"));
			
			
			Trace.trace(Trace.Debug, "", "### modificarBeneficiario() / lanzando sOmAltaBeneficiarios");
			ejecutarOM(om);
			Trace.trace(Trace.Debug, "", "### modificarBeneficiario() / sOmAltaBeneficiarios Ok!");
			setValueAt("msjError", "");
		} catch (Exception e) {
			setValueAt("msjError", "Error al modificar el beneficiario");
			Trace.trace(Trace.Debug, "", "### ERROR modificarBeneficiario()" + e);
			e.printStackTrace();
		}
		
	}
	
		//INI INC AVANCE 0 FX 16-07-2018
		private String formateoAvance(String avanceOriginal){
			String resultadoAvance =avanceOriginal;
			try{
				DecimalFormat fmAvance = new DecimalFormat("0000000000");
		        resultadoAvance = fmAvance.format(Integer.parseInt(resultadoAvance));
	        } catch(Exception excRA){
	        	Trace.trace(Trace.Debug, "", " Inicio formateoAvance() error controlado: " + excRA);
	        }
	        return resultadoAvance;
	        
		}
		//FIN INC AVANCE 0 FX 16-07-2018
		private String formateoMoneda(String formatoMoneda){
			String resultadoAvance =formatoMoneda;
			try{
				DecimalFormat fmAvance = new DecimalFormat("###,###,###,###.##");
		        resultadoAvance = fmAvance.format(Integer.parseInt(formatoMoneda));
	        } catch(Exception excRA){
	        	Trace.trace(Trace.Debug, "", " Inicio formateoMoneda() error controlado: " + excRA);
	        }
	        return resultadoAvance;
		}		
		//INI INC 102 FX 13/11/2018	
		protected IndexedCollection copiar2IndexedCollection(IndexedCollection iOrigen, IndexedCollection iDestino) {
			Object campoDestino = null;

			try {
				for (int j = 0; j < iOrigen.size(); ++j) {
					DataElement campoOrigen = iOrigen.getElementAt(j);
					campoDestino = (DataElement) iDestino.getDataElement().clone();
					if (campoOrigen instanceof IndexedCollection) {
						campoDestino = this.copiar2IndexedCollection((IndexedCollection) campoOrigen,
								(IndexedCollection) campoDestino);
					} else if (campoOrigen instanceof KeyedCollection) {
						campoDestino = this.copiar2KeyedCollection((KeyedCollection) campoOrigen,
								(KeyedCollection) campoDestino);
					}

					iDestino.addElement((DataElement) campoDestino);
				}
			} catch (Exception var6) {
				Trace.trace("com.grupobbva.ii.sf.operacion.BbvaOperacion", 8, "", var6.toString());
			}

			return iDestino;
		}

		protected final KeyedCollection copiar2KeyedCollection(KeyedCollection iOrigen, KeyedCollection iDestino) {
			try {
				for (int j = 0; j < iOrigen.size(); ++j) {
					DataElement campoOrigen = iOrigen.getElementAt(j);
					DataElement campoDestino = iDestino.getElementAt(j);
					if (campoOrigen instanceof IndexedCollection) {
						this.copiar2IndexedCollection((IndexedCollection) campoOrigen, (IndexedCollection) campoDestino);
					} else if (campoOrigen instanceof KeyedCollection) {
						this.copiar2KeyedCollection((KeyedCollection) campoOrigen, (KeyedCollection) campoDestino);
					} else {
						iDestino.setValueAt(campoDestino.getName(), campoOrigen.getValue());
					}
				}
			} catch (Exception var6) {
				Trace.trace("com.grupobbva.ii.sf.operacion.BbvaOperacion", 8, "", var6.toString());
			}

			return iDestino;
		}
		//FIN INC 102 FX 13/11/2018	
		
//INI incidencia 125 FX CMC 14/01/2019
	public String cargarCuentas()
	{
		KeyedCollection kCuenta, kcSalidaCuentasSrv, kcContextoSesion;
		IndexedCollection icContextoSesion,listaCuentasOP = null;
		Enumeration enContextoSesion1;
		String tipoPermiso = "";		
		String clave = "";
		
		Trace.trace(Trace.Debug, "", " Inicio OpFinalizarOperaciones cargarCuentas().");
		try {
			icContextoSesion = (IndexedCollection) getElementAt("s_salida_cuentas.s_lista_cuentas");
			listaCuentasOP = (IndexedCollection) getElementAt("lista_cuentas");
			listaCuentasOP.removeAll();
			enContextoSesion1 = icContextoSesion.getEnumeration();
			while(enContextoSesion1.hasMoreElements()){
				kCuenta = (KeyedCollection) enContextoSesion1.nextElement();
				clave = (String) kCuenta.getValueAt("s_clave_asunto");
				tipoPermiso = (String) kCuenta.getValueAt("s_permiso");
				if(tipoPermiso==null)
				{
					tipoPermiso="";
				}
				if (tipoPermiso.equals(getValueAt("datosAPP.pb_cod_serv"))) 
				{
					kcSalidaCuentasSrv = (KeyedCollection) DataElement.getExternalizer().convertTagToObject( listaCuentasOP.getElementSubTag());
					kcSalidaCuentasSrv.setValueAt("banco", kCuenta.getValueAt("s_banco"));
					kcSalidaCuentasSrv.setValueAt("oficina", kCuenta.getValueAt("s_oficina"));
					kcSalidaCuentasSrv.setValueAt("dcontrol", kCuenta.getValueAt("s_dcontrol"));
					kcSalidaCuentasSrv.setValueAt("num_cuenta", kCuenta.getValueAt("s_num_cuenta"));
					kcSalidaCuentasSrv.setValueAt("clave_asunto", kCuenta.getValueAt("s_clave_asunto"));
					kcSalidaCuentasSrv.setValueAt("tipo", kCuenta.getValueAt("s_tipo"));
					kcSalidaCuentasSrv.setValueAt("divisa", kCuenta.getValueAt("s_divisa"));
					kcSalidaCuentasSrv.setValueAt("permiso", kCuenta.getValueAt("s_permiso"));
					kcSalidaCuentasSrv.setValueAt("estado", kCuenta.getValueAt("s_estado"));
					kcSalidaCuentasSrv.setValueAt("valaso", kCuenta.getValueAt("s_valaso"));
					kcSalidaCuentasSrv.setValueAt("LimDiario", kCuenta.getValueAt("s_LimDiario"));
					kcSalidaCuentasSrv.setValueAt("LimTran", kCuenta.getValueAt("s_LimDiario"));
					kcSalidaCuentasSrv.setValueAt("LimMes", kCuenta.getValueAt("s_LimMes"));
					listaCuentasOP.addElement(kcSalidaCuentasSrv);
				}
			}
			Trace.trace(Trace.Debug, "", "### FIN OpFinalizarOperaciones cargarCuentas().:" + clave);
			return clave;
		}catch(Exception ee){
				Trace.trace(Trace.Debug, "", "### Falla en carga de cuentas() *** :" + ee);
				return clave;
		}
	}//FIN incidencia 125 FX CMC 14/01/2019
	
	//Genera la operación llamando sólo una vez el servicio
		//INI incidencia 153 FX CMC 21/01/2019
		public void generarOperacionH() throws Exception
		{
			Trace.trace(Trace.Debug, "", "### generarOperacionH() inicio ");
			String descripNegociacion = (String) getValueAt("descripNegociacion");
			Trace.trace(Trace.Debug,"","generarOperacionH() descripNegociacion"+descripNegociacion);
			String selectMoneda = (String) getValueAt("selectMoneda");
			Trace.trace(Trace.Debug,"","generarOperacionH() selectMoneda"+selectMoneda);
			if(selectMoneda!= null && selectMoneda!= "")
			{
			    if (selectMoneda.length() > 2)
				{
					selectMoneda= selectMoneda.substring(0,3);
			    }
			}
			setValueAt("selectMoneda", selectMoneda);
			String resultadoAvance =(String) getValueAt(AVANCE_OPE);
			resultadoAvance = formateoAvance(resultadoAvance);
			Trace.trace(Trace.Debug,"","generarOperacionH() resultadoAvance:"+resultadoAvance);
			setValueAt(AVANCE_OPE, resultadoAvance);
			
			Trace.trace(Trace.Debug, "", "#### avanceOpe ##" + getValueAt(AVANCE_OPE));
			setValueAt("selectOpe", getValueAt("selectOpe"));
			Trace.trace(Trace.Debug, "", "### selectOpe " + getValueAt("selectOpe"));
			setValueAt("selectCuentaOrden", getValueAt("selectCuentaOrden"));
			Trace.trace(Trace.Debug, "", "### selectCuentaOrden " + getValueAt("selectCuentaOrden"));
			
			
			Trace.trace(Trace.Debug, "", "### selectMoneda " + getValueAt("selectMoneda"));
			setValueAt("monto", getValueAt("monto"));
			Trace.trace(Trace.Debug, "", "### monto " + getValueAt("monto"));
			setValueAt("descripNegociacion", descripNegociacion);
			Trace.trace(Trace.Debug, "", "### descripNegociacion " + getValueAt("descripNegociacion"));
			
			setValueAt(TASA_DIVISA, getValueAt(TASA_DIVISA));
			Trace.trace(Trace.Debug, "", "### tasaDivisa # " + getValueAt(TASA_DIVISA));
			setValueAt(TASA_DIVISA_PESO, getValueAt(TASA_DIVISA_PESO));
			Trace.trace(Trace.Debug, "", "### tasaDivisaPeso:## " + getValueAt(TASA_DIVISA_PESO));
			setValueAt(TASA_USD_PESO, getValueAt(TASA_USD_PESO));
			Trace.trace(Trace.Debug, "", "### tasaUsdPeso:## " + getValueAt(TASA_USD_PESO));
			setValueAt("equivPesos", getValueAt("equivPesos"));
			Trace.trace(Trace.Debug, "", "### equivPesos " + getValueAt("equivPesos"));
			
			setValueAt("indBenef", "");
			
			Trace.trace(Trace.Debug, "", "### generarOperacionH() - inicio PASO_1 " + descripNegociacion);
			
			RequestBankTradeService peticion = new RequestBankTradeService();
			
			Date fechaActual = new Date();
			String sSelectCuentaOrden = (String) getValueAt("selectCuentaOrden");
			String deCTA = tipoCuentaBT(sSelectCuentaOrden);
			
			peticion.setFechaHoraNegociacion(fechaActual);
			peticion.setNoCtaComm(sSelectCuentaOrden);
			peticion.setDeComm(deCTA);
			peticion.setNoCtaPrincipal(sSelectCuentaOrden);
			peticion.setNoCtaPrincipalCre(sSelectCuentaOrden);
			peticion.setNoOperac(refExtranjero);
			peticion.setMonAvance(getValueAt("monto").toString());
			
			if(getValueAt("selectOpe").equals("H1") )
			{
				Trace.trace(Trace.Debug, "", "### generarOperacionH() tipo H1 ");
				peticion.setTasaDivi(Double.valueOf(getValueAt(TASA_DIVISA_PESO).toString()));	
				peticion.setTasaAvance(getValueAt(TASA_DIVISA_PESO).toString());
				peticion.setTasaUsd(Double.valueOf(getValueAt(TASA_USD_PESO).toString()));
				peticion.setTasaLinea(Double.valueOf(getValueAt(TASA_DIVISA).toString()));			
				peticion.setNoAvance("STD1");
			
			}else if (getValueAt("selectOpe").equals("H2") )
			{
				Trace.trace(Trace.Debug, "", "### generarOperacionH() tipo H2 " + getValueAt(AVANCE_OPE).toString() + " / " + getValueAt("monto").toString());
				peticion.setNoAvance(getValueAt(AVANCE_OPE).toString());
				//INI incidencia 181 FX CMC 13/02/2019
				peticion.setTasaDivi(Double.valueOf(getValueAt(TASA_DIVISA_PESO).toString()));	
				peticion.setTasaAvance(getValueAt(TASA_DIVISA_PESO).toString());
				peticion.setTasaUsd(Double.valueOf(getValueAt(TASA_USD_PESO).toString()));
				peticion.setTasaLinea(Double.valueOf(getValueAt(TASA_DIVISA).toString()));	
				//FIN incidencia 181 FX CMC 13/02/2019
			}

			Trace.trace(Trace.Debug, "", "### generarOperacionH() variables dinamicas ");
			String documentoCliente = (String) getValueAt("num_cuenta");
			peticion.setIdCliente(documentoCliente);
			peticion.setTyUsuario("JR");
			
			peticion.setDeOperac(descripNegociacion);
			peticion.setInfcam70Sw1(descripNegociacion);

			peticion.setMonNegoc(getValueAt("monto").toString());
			peticion.setCurNegoc(selectMoneda);
			peticion.setTyNegoci("**");
			
			Trace.trace(Trace.Debug, "", "### generarOperacionH() seteo de variables fijas ");
			peticion.setTyOperac("TFIL");			
			peticion.setIdSucursal(BT_IDSUCURSAL);
			peticion.setCanal("80");
			peticion.setSubcanal("02");
			peticion.setCampana("CANALES");
			peticion.setTyVended("9");
			peticion.setNoVended("777777777777777");
			peticion.setDigVended("9");
			peticion.setTyTransa("H");
			peticion.setIndMoneda("");//Pendiente por definir
			peticion.setID_BCOOrdenante("SYSTEM");
			peticion.setNomBcoOrd1("SYSTEM");
			peticion.setIdSwTpBcoOrd("SW");
				
			//Empieza 180
			Trace.trace(Trace.Debug, "", " Inicio generarOperacionH() 180" );//Traza de prueba
			
			peticion.setTyOperac("TFIC");
			peticion.setTyUsuario("JR");
			peticion.setCurDbComm("COP");
			peticion.setTpCambioComm("1.000000000");												
			peticion.setCodOpecampo23b("CRED");
			peticion.setInfcam70Sw1(peticion.getDeOperac());
			peticion.setNuCambiario("5E02918");
			peticion.setCanal("80");		
				
			Trace.trace(Trace.Debug, "", "### generarOperacionH() lanzando ws-...  ");
			
			try
			{
				ResponseBankTradeService response = WrapperBanktradeService.execute(peticion);
				if(response.getCodError() != null)
				{
					if(!response.getCodError().equals(""))
					{
						this.setEstado("ERROR");
						String mensaje = ERROR_WS_BANKTRADE.concat(response.getSequence()+" "+response.getCodError());
						Trace.trace(Trace.Debug, "", "### generarOperacionH() PASO_1 - error de web service: " + mensaje);
						setValueAt("msjError", mensaje);
						ManejarExcepcion(3, "ERROR AL CREAR OPERACIÓN", "SISTEMA TEMPORALMENTE NO DISPONIBLE", "", new Exception(), "", this, "", "");
						return;
					}else
					{
						Trace.trace(Trace.Debug, "", "### generarOperacionH() response.getNumOpera " + response.getNumOpera());
						Trace.trace(Trace.Debug, "", "### generarOperacionH() response.getPrefix " + response.getPrefix());
						Trace.trace(Trace.Debug, "", "### generarOperacionH() response.sequence " + response.getSequence());
						setValueAt("msjError", "");
						setValueAt("PeticionWebService", peticion);
						setValueAt("referenciaOpe", response.getNumOpera());
					}
				}else
				{
					Trace.trace(Trace.Debug, "", "### generarOperacionH() PASO_1 - error de web service: RTA null");
					setValueAt("msjError", "RTA null");
				}
			}catch (Exception e) 
			{
				e.printStackTrace();			
				Trace.trace(Trace.Debug, "", "### generarOperacionH() PASO_1 - Falla lanzando web service: " + e);
				String errorWS = "H";			
				setValueAt("msjError", errorWS);
				setValueAt("Error", errorWS);
				this.setEstado("ERRORWS");
				return;	
			}		
		}
		//FIN incidencia 153 FX CMC 21/01/2019
	//INI incidencia 181 FX CMC 13/02/2019
	public void consultarDataAvance() throws Exception
	{
		String resultadoAvance =(String) getValueAt(AVANCE_OPE);
		resultadoAvance = formateoAvance(resultadoAvance);	
		setValueAt(AVANCE_OPE,resultadoAvance);
		Trace.trace(Trace.Debug, "", " Inicio consultarAvance(): " + getValueAt("selectMoneda") + " | " + getValueAt(AVANCE_OPE));	
		om = creaOM(sOmConsultaAvance);
		//INI-VARIOS NIT F2 - OSNEIDER A. CMC - 16-09-2019
		if(getValueAt("selectOpe")!=null && getValueAt("selectOpe").toString().trim().equalsIgnoreCase("T2")) {
			om.setValueAt("Entrada.BPALACCE", "V");
		}else if(getValueAt("selectOpe")!=null && getValueAt("selectOpe").toString().trim().equalsIgnoreCase("H2") ){
		    om.setValueAt("Entrada.BPALACCE", "C");
		}else {
			om.setValueAt("Entrada.BPALACCE", "V");
		}
		//FIN- VARIOS NIT F2 - OSNEIDER A. CMC - 16-09-2019
		om.setValueAt("Entrada.BPALACC2", getValueAt("selectMoneda"));
		om.setValueAt(ENTRADA_BCODACCC, resultadoAvance);
		String sMonto = (String) getValueAt("monto");
		Trace.trace(Trace.Debug, "", "### consultarAvance()- sMonto" + sMonto);
		Double monto = formatearImporteEntrada(sMonto);
		Trace.trace(Trace.Debug, "", "### consultarAvance()- FORMATEADO monto" + monto);
		om.setValueAt("Entrada.BIMPORTE", monto);
		ejecutarOM(om);
		Trace.trace(Trace.Debug, "", "### Flag Flag Flag Flag ");
		try {
			String Flag2 = (String) om.getValueAt("Salida.CODAVISO");
			String Flag3Error = (String) om.getValueAt("Salida.COD-ERROR");
			Trace.trace(Trace.Debug, "", "### Fin consultarAvance() Flag3Error " + Flag3Error);
			if(null!=Flag3Error && (Flag3Error.equalsIgnoreCase("CNE0019") || Flag3Error.equalsIgnoreCase("CNE0020")))
			{
				Flag2=Flag3Error;
			}
			Trace.trace(Trace.Debug, "", "### Fin consultarAvance() Flag " + Flag2);
			String tasaDivisaV = (String) om.getValueAt("Salida.TASA-DIVISA");
			String equivalentePessoV = (String) om.getValueAt("Salida.EQUIVALENTE-PESOS");
			String divisCopV = (String) om.getValueAt("Salida.DIVISA-COP");
			Double tasaAvance = 0.0;
			Double equivPesosMonto = 0.0;
			tasaAvance = Double.parseDouble(tasaDivisaV);
			equivPesosMonto = monto*tasaAvance;
			Trace.trace(Trace.Debug, "", "***Fin consultarAvance() tasaDivisaPeso: " + tasaDivisaV);
			Trace.trace(Trace.Debug, "", "***Fin consultarAvance() equivalentePessoV: " + equivPesosMonto);
			Trace.trace(Trace.Debug, "", "***Fin consultarAvance() divisCopV: " + divisCopV);									
			setValueAt(TASA_DIVISA, tasaAvance);
			setValueAt("equivPesos", equivPesosMonto);
			setValueAt(TASA_DIVISA_PESO, tasaAvance);
			setValueAt("validaCta", "S");
			Trace.trace(Trace.Debug, "", "### validaCta " + getValueAt("validaCta"));			
		} catch (Exception e) {
			Trace.trace(Trace.Debug, "", "### ERROR consultarAvance() e " + e.toString());
		}		
	}
	//FIN incidencia 181 FX CMC 13/02/2019

//INI incidencia 225B FX CMC 02/09/2019
	public void validarCodigoBanco(String codigo, String tipoCodigo) {
		try {
			String varcuentacod;
			int correctoSwift = 0;
			if (tipoCodigo.equalsIgnoreCase("SW")) {
				if ((codigo.length() == 8) || (codigo.length() == 11))
				{
					String letras="abcdefghijklmnopqrstuvwxyz";
					varcuentacod = codigo.toLowerCase();
					varcuentacod = varcuentacod.substring(0,6);
					for(int i=0; i<=varcuentacod.length()-1; i++){
						if ((letras.indexOf(varcuentacod.charAt(i),0)!=-1)){//Si contiene algo diferente a letras en los primeros 6 caracteres
							correctoSwift++;
							}
						}		
				}		
			}
			if(tipoCodigo.equalsIgnoreCase("SW")) {
				if (correctoSwift == 6){
					setValueAt("errorCodigo", "N");
				}else if(correctoSwift != 6){
					setValueAt("errorCodigo", "S");
				}
			}			
		}catch(Exception e) {
			Trace.trace(Trace.Information, "", "### ERROR INC 225B FALLA VALIDACION BANCO() e " + e.toString());
		}
	}
//FIN incidencia 225B FX CMC 04/09/2019
	//INI PERDIDA SESION HOST 10-12-2019
	private void ejecutarOMControl(OperacionMulticanal omEjecutar, String nombreOM) {
		try {
			ejecutarOM(omEjecutar);			
		} catch (BbvaARQException ex) {
			lanzarOMF100(ex);
			try {
				omEjecutar = creaOM(nombreOM);
				ejecutarOM(omEjecutar);
			}			
			catch (Exception ex2) {
				Trace.trace(Trace.Error, "", "--control_f100_1()-- MsgError ejecutarOMControl Exception: " + ex2);
			}
		}
		catch (Exception ex2) {
			Trace.trace(Trace.Error, "", "--control_f100_2()-- MsgError ejecutarOMControl Exception: " + ex2);
		}
		
	}
	
	private void lanzarOMF100(BbvaARQException arqEx){
		try {
			String referencia1=(String) getValueAt("datosAPP.iv-cod_emp") + (String) getValueAt("datosAPP.iv-cod_usu");
			String usuario1=(String) getValueAt("datosAPP.iv-cod_usu");
			String arqException = arqEx.getMsg();
			Trace.trace(Trace.Information, "", "--ejecutar_f100()-- BbvaARQException.MsgError :" + arqException + ":");
			if (arqException.equalsIgnoreCase("NO EXISTE EN TABLA USUARIO-EMPRESA (USU) -CNE0002- ") || arqException.equalsIgnoreCase("SISTEMA NO DISPONIBLE, INTENTE MAS TARDE                                99999 -S0001- ")){
				Trace.trace(64, getClass().getName() + "**********************COMENZANDO CREACION OPERACION MULTICANAL F100*******************");
				OperacionMulticanal oM = creaOM("sign_on_om");
				oM.setValueAt(ENTRADA_BCODACCC,referencia1);
				oM.setValueAt("Entrada.BASUNPRO", usuario1);   
				oM.setValueAt("Entrada.BINDAUX1", "");
				ejecutarOM(oM); 
			}	
		} catch (BbvaARQException ex){
			Trace.trace(Trace.Error, "", "--control_f100()-- MsgError lanzarOMF100: " + ex.getMsg());
		}
		catch(Exception exc) {
			Trace.trace(Trace.Error, "", "--control_f100()-- MsgError lanzarOMF100 Exception: " + exc);
		}
	}
	//FIN PERDIDA SESION HOST 10-12-2019
	// INI -VARIOS NIT F2 - OSNEIDER A. CMC - 02-10-2019
	 public KeyedCollection om_FNGU(String FECHIN,String FECHFN,String TIPOPER,String NCUENT, String NUMCLIE, double MOVIMT  ) throws DSEObjectNotFoundException, DSEInvalidArgumentException {		
			OperacionMulticanal omMjs;
			try {
				Trace.trace(Trace.Information, getClass().getName(), "OMAN-INICIO: om_FNGU()");
			omMjs = (OperacionMulticanal)creaOM("divisas_consulta_unica_operaciones_om");

			omMjs.setValueAt("entrada.FECHIN", FECHIN);
			omMjs.setValueAt("entrada.FECHFN", FECHFN);	
			omMjs.setValueAt("entrada.TIPOPER", TIPOPER);
			omMjs.setValueAt("entrada.NCUENT", NCUENT);
			omMjs.setValueAt("entrada.NUMCLIE", NUMCLIE);
			omMjs.setValueAt("entrada.MOVIMT",MOVIMT);
			Trace.trace(Trace.Information, getClass().getName(), "OMAN-ENTRADA- FECHIN :"+FECHIN + " FECHFN: "+FECHFN+" TIPOPER: "+TIPOPER+" NCUENT:"+NCUENT+" NUMCLIE: "+NUMCLIE+" MOVIMT:"+MOVIMT);
			omMjs.execute();	
			IndexedCollection lista =(IndexedCollection)omMjs.getElementAt("salida.listaGiros");
			KeyedCollection salida =(KeyedCollection)lista.getElementAt(0);
			return salida;
			} catch (BbvaException e) {
				Trace.trace(Trace.Error, getClass().getName(), "OMAN-ERROR: om_FNGU() Salida null: "+e);
				return null;
			}
		}
	 public String obtenerUsuario (String referencia, String codUsuario){
			
			Trace.trace(Trace.Debug, "", "OMAN-Inicio obtenerUsuario()  WS Divisas");
			String nCliente = "";
			try {
				Trace.trace(Trace.Debug, "", "OMAN-### OPEnvioWSDivisas " + referencia + codUsuario);
				
				for (int i = codUsuario.length(); i<8; i++){ 
					codUsuario = codUsuario + "X"; 
				}
				nCliente = referencia + codUsuario + "0000000NC";
				
			} catch (Exception e) {
				Trace.trace(Trace.Debug, "", "OMAN-### Error al formatear código de usuario/referencia  OPEnvioWSDivisas.java ****" + e);
			}
			Trace.trace(Trace.Debug, "", "OMAN-Fin obtenerUsuario() WS Divisas ****");
			return nCliente;
	    }
	// FIN -VARIOS NIT F2 - OSNEIDER A. CMC - 02-10-2019
	 
	 /***
	  * El nit enviado deber llegar
	  * sin tipo de documento y digito de verificacion
	  * @param String nit
	  * @param int posiciones
	  * @return String
	  */
	 public String formateadorDeNit(String nit, int posiciones) {
		 if(nit.length()==posiciones)
			 return nit;
		 else
			 nit="0"+nit;
			 return formateadorDeNit(nit,posiciones);
	 }
	 
	 public Vector obtenerLiteralPaisProperties(String codigoPais, int contador) {
		 String idioma;
		 Vector pais = new Vector(1);
		 pais.add(0,"");
		 pais.add(1,"");
		 try {
			 if(contador==249)
				 return pais;
			 idioma = (String) getValueAt("s_idioma");
			 String valor = (String) CatalogoMultidioma.obtenerLiteral("PNET_PAISES_DIVISAS",idioma,"PNET_value" + contador);
			 String[] parts1 = valor.split(";");
			 String valuePais = parts1[0];
			 String namePais = parts1[1];
			 if(valuePais.equalsIgnoreCase(codigoPais)) {
				 pais.add(0,valuePais);
				 pais.add(1,namePais);
				 return pais;
			 }else {
				 return obtenerLiteralPaisProperties(codigoPais, ++contador);
			 }
		 } catch (DSEObjectNotFoundException e) {
			 Trace.trace(Trace.Debug, "", " obtenerLiteralPaisProperties(): Falla al obtener literal del pais");
			 return pais;
		 }
	 }
	 
	 public int verificarBeneficiarioSeleccionado(String campos) {
		 Trace.trace(Trace.Debug, "OpFinalizarOperaciones", "verificarBeneficiarioSeleccionado() " + "Inicio");
		 String[] vecCampos = campos.split("@");
		 int cont = 0;
		 try{
			 String documentofull = (String) getValueAt("docBenefi");
			 Trace.trace(Trace.Debug, "OpFinalizarOperaciones", "verificarBeneficiarioSeleccionado() documentofull=" + documentofull);
			 String[] parts = documentofull.split("\\|@\\|");//INC 39 CMC FX 02/03/2018

			 String tipoId = parts[0];
			 String numId = parts[1];
			 String digVer = parts[2];
			 String ctaBenef = parts[3];

			 om = creaOM(sOmConsultaBeneficiarios);

			 om.setValueAt("Entrada.BINDAUX1", "U ");
			 om.setValueAt("Entrada.BINDAUX2", tipoId);
			 om.setValueAt("Entrada.BPALACC2", numId);
			 om.setValueAt("Entrada.BPALACCE", digVer);
			 om.setValueAt("Entrada.BCODCTAA", ctaBenef);

			 om.setValueAt(ENTRADA_BCODACCC, getValueAt(VALUE_LOGON).toString());

			 ejecutarOMControl(om,sOmConsultaBeneficiarios);
			 Trace.trace(Trace.Debug, "OpFinalizarOperaciones", "verificarBeneficiarioSeleccionado() - ejecutada om sOmConsultaBeneficiarios");

			 IndexedCollection icCuentas = (IndexedCollection) om.getElementAt("Salida.ListaCUENTAS-BENEFIC");
			 int tamano = icCuentas.size() - 1;
			 IndexedCollection listaCuentas;
			 for (int i = tamano; i > 0; i--) {
				 KeyedCollection kCuentas = (KeyedCollection) icCuentas.getElementAt(i);
				 String cuenta = (String) kCuentas.getValueAt("ID-BENEFICIARIO");
				 Trace.trace(Trace.Debug, "OpFinalizarOperaciones", "verificarBeneficiarioSeleccionado() cuenta: " + cuenta);
				 if (cuenta == null || cuenta.equals("")) {
					 icCuentas.removeElementAt(i);
				 }
			 }
			 listaCuentas = (IndexedCollection) getElementAt("listaCuentasC");
			 listaCuentas.removeAll();
			 copiarIndexedCollection(icCuentas, listaCuentas);

			 IndexedCollection listaCuentas3;
			 listaCuentas3 = (IndexedCollection) getElementAt("listaCuentasC");
			 int tamano2 = listaCuentas3.size() - 1;
			 for (int i = tamano2; i >= 0; i--) {
				 KeyedCollection kCuentas = (KeyedCollection) listaCuentas3.getElementAt(i);
				 String paisBanBenef =  kCuentas.getValueAt("paisbancobene") != null ? kCuentas.getValueAt("paisbancobene").toString() : "";
				 String cuentabenefica =  kCuentas.getValueAt("cuentabenefica") != null ? kCuentas.getValueAt("cuentabenefica").toString().trim() : "";
				 for(int x=0; x<vecCampos.length; x++) {
					 Vector pais = new Vector();
					 boolean hayCodigo = true;
					 String campo = vecCampos[x];
					 String valor = kCuentas.getValueAt(campo) != null ? kCuentas.getValueAt(campo).toString() : "";
					 if(campo.equalsIgnoreCase("nombresbenefic")||campo.equalsIgnoreCase("direccionebenef")) {
						 if(!valor.equalsIgnoreCase("")) {
							 if(valor.length()>35) {
								 Trace.trace(Trace.Debug, "OpFinalizarOperaciones", "verificarBeneficiarioSeleccionado() "
								 		+ "Campo " + campo + " supera la longitud maxima.");
								 cont++;
							 }
						 }else {
							 Trace.trace(Trace.Debug, "OpFinalizarOperaciones", "verificarBeneficiarioSeleccionado() "
							 		+ "Campo " + campo + " vacio.");
							 cont++;
						 }
					 }
					 if(campo.equalsIgnoreCase("ciudadbenefcia")) {
						 if(!valor.equalsIgnoreCase("")) {
							 if(valor.length()>12) {
								 Trace.trace(Trace.Debug, "OpFinalizarOperaciones", "verificarBeneficiarioSeleccionado() "
								 		+ "Campo " + campo + " supera la longitud maxima.");
								 cont++;
							 }
						 }else {
							 Trace.trace(Trace.Debug, "OpFinalizarOperaciones", "verificarBeneficiarioSeleccionado() "
							 		+ "Campo " + campo + " vacio.");
							 cont++;
						 }
					 }
					 if(campo.equalsIgnoreCase("telefonobenefi")) {
						 if(!valor.equalsIgnoreCase("")) {
							 if(valor.length()>15) {
								 Trace.trace(Trace.Debug, "OpFinalizarOperaciones", "verificarBeneficiarioSeleccionado() "
								 		+ "Campo " + campo + " supera la longitud maxima.");
								 cont++;
							 }
						 }else {
							 Trace.trace(Trace.Debug, "OpFinalizarOperaciones", "verificarBeneficiarioSeleccionado() "
							 		+ "Campo " + campo + " vacio.");
							 cont++;
						 }
					 }
					 if(campo.equalsIgnoreCase("paisbeneficiar")) {
						 if(!valor.equalsIgnoreCase("")) {
							 pais = obtenerLiteralPaisProperties(valor, 0);
							 String codPais = pais.get(0) != null ? pais.get(0).toString() : "";
							 if(codPais.equalsIgnoreCase("")) {
								 Trace.trace(Trace.Debug, "OpFinalizarOperaciones", "verificarBeneficiarioSeleccionado() "
								 		+ "Campo " + campo + " el pais no existe.");
								 cont++; 
							 }
						 }else {
							 Trace.trace(Trace.Debug, "OpFinalizarOperaciones", "verificarBeneficiarioSeleccionado() "
							 		+ "Campo " + campo + " vacio.");
							 cont++;
						 }
					 }
					 if(campo.equalsIgnoreCase("tiposabanco1")) {
						 if(!valor.equalsIgnoreCase("")) {
							 if(!valor.equalsIgnoreCase("SW")&&!valor.equalsIgnoreCase("FW")) {
								 Trace.trace(Trace.Debug, "OpFinalizarOperaciones", "verificarBeneficiarioSeleccionado() "
								 		+ "Campo " + campo + " el valor no es valido.");
								 hayCodigo = false;
								 cont++; 
							 }
						 }else {
							 Trace.trace(Trace.Debug, "OpFinalizarOperaciones", "verificarBeneficiarioSeleccionado() "
							 		+ "Campo " + campo + " vacio.");
							 cont++;
						 }
					 }
					 if(hayCodigo) {
						 if(campo.equalsIgnoreCase("codigosabanco1")) {
							 if(!valor.equalsIgnoreCase("")) {
								 if(!existeBanco(valor)) {
									 Trace.trace(Trace.Debug, "OpFinalizarOperaciones", "verificarBeneficiarioSeleccionado() "
									 		+ "Campo " + campo + " el codigo de banco beneficiario no existe.");
									 cont++;
								 }else {
									 String nombrebancobe = kCuentas.getValueAt("nombrebancobe") != null ? kCuentas.getValueAt("nombrebancobe").toString().trim() : "";
									 String ciudadbancobe = kCuentas.getValueAt("ciudadbancobe") != null ? kCuentas.getValueAt("ciudadbancobe").toString().trim() : "";
									 if(nombrebancobe.equalsIgnoreCase("")) {
										 Trace.trace(Trace.Debug, "OpFinalizarOperaciones", "verificarBeneficiarioSeleccionado() "
											 		+ "Campo " + "nombrebancobe" + " vacio.");
										 cont++;
									 }
									 if(paisBanBenef.equalsIgnoreCase("")) {
										 Trace.trace(Trace.Debug, "OpFinalizarOperaciones", "verificarBeneficiarioSeleccionado() "
											 		+ "Campo " + "paisbancobene" + " vacio.");
										 cont++;
									 }
									 if(ciudadbancobe.equalsIgnoreCase("")) {
										 Trace.trace(Trace.Debug, "OpFinalizarOperaciones", "verificarBeneficiarioSeleccionado() "
											 		+ "Campo " + "ciudadbancobe" + " vacio.");
										 cont++; 
									 }
									 if(!cuentabenefica.equalsIgnoreCase("")) {
										 if(paisBanBenef.equalsIgnoreCase("GBR")) {
											 if(cuentabenefica.length()>=14&&cuentabenefica.length()<=22) {
												 Trace.trace(Trace.Debug, "OpFinalizarOperaciones", "verificarBeneficiarioSeleccionado() "
												 		+ "Cuenta beneficiaria correcta para el pais " + paisBanBenef); 
											 }else {
												 cont++;
												 Trace.trace(Trace.Debug, "OpFinalizarOperaciones", "verificarBeneficiarioSeleccionado() "
												 		+ "Cuenta beneficiaria incorrecta para el pais " + paisBanBenef + " no cumple la longitud");
											 }
										 }else {
											 if(paisBanBenef.equalsIgnoreCase("MEX")) {
												 if(cuentabenefica.length()==18) {
													 Trace.trace(Trace.Debug, "OpFinalizarOperaciones", "verificarBeneficiarioSeleccionado() "
													 		+ "Cuenta beneficiaria correcta para el pais " + paisBanBenef); 
												 }else {
													 cont++;
													 Trace.trace(Trace.Debug, "OpFinalizarOperaciones", "verificarBeneficiarioSeleccionado() "
													 		+ "Cuenta beneficiaria incorrecta para el pais " + paisBanBenef + " no cumple la longitud");
												 }
											 }else {
												if(paisBanBenef.equalsIgnoreCase("CAN")) {
													 if(cuentabenefica.length()>=7&&cuentabenefica.length()<=12) {
														 Trace.trace(Trace.Debug, "OpFinalizarOperaciones", "verificarBeneficiarioSeleccionado() "
														 		+ "Cuenta beneficiaria correcta para el pais " + paisBanBenef); 
													 }else {
														 cont++;
														 Trace.trace(Trace.Debug, "OpFinalizarOperaciones", "verificarBeneficiarioSeleccionado() "
														 		+ "Cuenta beneficiaria incorrecta para el pais " + paisBanBenef + " no cumple la longitud");
													 }
												}else {
													if(cuentabenefica.length()>=5&&cuentabenefica.length()<=30) {
														Trace.trace(Trace.Debug, "OpFinalizarOperaciones", "verificarBeneficiarioSeleccionado() "
																+ "Cuenta beneficiaria correcta para el pais " + paisBanBenef);
													}else {
														cont++;
														Trace.trace(Trace.Debug, "OpFinalizarOperaciones", "verificarBeneficiarioSeleccionado() "
																+ "Cuenta beneficiaria incorrecta para el pais " + paisBanBenef + " no cumple la longitud");
													}
												} 
											 } 
										 }
									 }else {
										 Trace.trace(Trace.Debug, "OpFinalizarOperaciones", "verificarBeneficiarioSeleccionado() Campo " + "cuentabenefica" + " vacio.");
										 cont++; 
									 }
									 if(paisBanBenef.equalsIgnoreCase("GBR")||paisBanBenef.equalsIgnoreCase("CAN")) {
										 String codigoUnitario =  kCuentas.getValueAt("codigoUnitario") != null ? kCuentas.getValueAt("codigoUnitario").toString().trim() : "";
										 if(!codigoUnitario.equalsIgnoreCase("")) {
											 if(paisBanBenef.equalsIgnoreCase("GBR")) {
												 if(codigoUnitario.length()==6) {
													 Trace.trace(Trace.Debug, "OpFinalizarOperaciones", "verificarBeneficiarioSeleccionado() "
																+ "Sort Code correcto para el pais " + paisBanBenef + " valor: " + codigoUnitario);
												 }else {
													 cont++;
													 Trace.trace(Trace.Debug, "OpFinalizarOperaciones", "verificarBeneficiarioSeleccionado() "
																+ "Sort Code incorrecto para el pais " + paisBanBenef + " valor " + codigoUnitario + " no cumple la longitud");
												 }
											 }else {
												if(paisBanBenef.equalsIgnoreCase("CAN")) {
													 if(codigoUnitario.length()==5) {
														 Trace.trace(Trace.Debug, "OpFinalizarOperaciones", "verificarBeneficiarioSeleccionado() "
																	+ "Codigo Transitorio correcto para el pais " + paisBanBenef + " valor: " + codigoUnitario);
													 }else {
														 cont++;
														 Trace.trace(Trace.Debug, "OpFinalizarOperaciones", "verificarBeneficiarioSeleccionado() "
																	+ "Codigo Transitorio incorrecto para el pais " + paisBanBenef + " valor " + codigoUnitario + " no cumple la longitud");
													 }
												} 
											 }
										 }else {
											 Trace.trace(Trace.Debug, "OpFinalizarOperaciones", "verificarBeneficiarioSeleccionado() Campo " + "codigoUnitario" + " vacio.");
											 cont++; 
										 }
									 }
								 }
							 }else {
								 Trace.trace(Trace.Debug, "OpFinalizarOperaciones", "verificarBeneficiarioSeleccionado() Campo " + campo + " vacio.");
								 cont++;
							 }
						 }
					 }
				 }
			 }
		 }catch(Exception e){
			 Trace.trace(Trace.Error, "OpFinalizarOperaciones", "verificarBeneficiarioSeleccionado() ERROR al validar la autenticidad del beneficiario: " + e);
			 this.setEstado("ERROR");
			 return 0;
		 }finally{
			 controlOM = null;
		 }
		 return cont;
	 }
	 
	 public boolean existeBanco (String codigo) throws Exception {
		 boolean flag = false;
		 try {
			 om = creaOM("validacion_codigo_om");
			 om.setValueAt("Entrada.CODIGO_BAN", codigo);
			 ejecutarOM(om);
			 if(om.getValueAt("Salida.COD-ERROR") != null && om.getValueAt("Salida.COD-ERROR") != "") {
				 flag = false;
			 }else {
				 flag = true;	 
			 }	
		 }catch(Exception e) {

		 }
		 return flag;
	 }
	 
	public void finalizarPse() throws DSEInvalidArgumentException {
		Trace.trace(Trace.Debug, "", " iniciar finalizarPse()");
		try {
			Datum datum =getObjectValue(OPERATION_OBJ, Datum.class);
			RequestBankTradeService rbt=inputMapperFinishBankTrade(datum);
			finishBankTrade(rbt);
			setEstado("11");
		} catch (DSEObjectNotFoundException e) {
			e.printStackTrace();
		}
	}
	public String getBeneficiarioFinalizar(String operacion) {
  		String controlCambiario="";
  		try {
  			
  			String ref=(String)getValueAt(VALUE_LOGON);
  			String usu=(String)getValueAt(VALUE_COD_USUA);
  			String usuario=obtenerUsuario(ref, usu);
  	  		KeyedCollection detallesOperacion=om_FNGU("","","T","00130073000112345678" ,usuario,new Double(numOperacion.substring(1,numOperacion.length())));
  			if(detallesOperacion!=null) {
  				if(detallesOperacion.getValueAt(NIT_COMPLETO)!=null && !detallesOperacion.getValueAt(NIT_COMPLETO).toString().equals("")) {
  					controlCambiario= (String)detallesOperacion.getValueAt("FNUMCA");
  				}
  			}
  		}catch (Exception e) {
  			System.out.println(e.getMessage());	
		}
  		return controlCambiario;
  	}
	 
	 private void getBancosPse() {
		 IndexedCollection icOmLIstaBancoAch= null;
		 IndexedCollection listaBancoAch= null;
		 try {
			Trace.trace(Trace.Debug, "OpFinalizarOperaciones", "getBancosPse() Inicio Consulta Bancos ACH ");
			om= creaOM("om_listar_bancos_ach");
			om.execute();
			icOmLIstaBancoAch=(IndexedCollection)om.getElementAt("Salida.listaBancosAch");
			listaBancoAch=(IndexedCollection)getElementAt("listaBancoAch");
			listaBancoAch.removeAll();
			Trace.trace(Trace.Debug, "OpFinalizarOperaciones", "Total bancos ACH: "+icOmLIstaBancoAch.size());
			for(int i =0; i<icOmLIstaBancoAch.size(); i++) {
				KeyedCollection bancoLista = (KeyedCollection)icOmLIstaBancoAch.getElementAt(i);
				KeyedCollection banco =(KeyedCollection) DataElement.getExternalizer().convertTagToObject(listaBancoAch.getElementSubTag());
				banco.setValueAt("idBanco", (String)bancoLista.getValueAt("id"));
				banco.setValueAt("name", (String)bancoLista.getValueAt("descripcion"));
				listaBancoAch.addElement(banco);
			}
			Trace.trace(Trace.Debug, "OpFinalizarOperaciones", "getBancosPse() FIN ");
		} catch (BbvaException e) {
			Trace.trace(Trace.Error, "", "OpFinalizarOperaciones : getBancosPse() : Error Al ejecutar om " + e.getMessage());
		} catch (DSEObjectNotFoundException e) {
			Trace.trace(Trace.Error, "", "OpFinalizarOperaciones : getBancosPse() : Error dato no encontrado " + e.getMessage());
		} catch (IOException e) {
			Trace.trace(Trace.Error, "", "OpFinalizarOperaciones : getBancosPse() : Error map elemts " + e.getMessage());
		} catch (DSEInvalidArgumentException e) {
			Trace.trace(Trace.Error, "", "OpFinalizarOperaciones : getBancosPse() : Argumento no valido " + e.getMessage());
		}
	 }
	 
	 public void postPayment() throws Exception{
		 Trace.trace(Trace.Debug, "", "Entro a postpayment");
		 MapPayment mapPayment = new MapPayment();
		 mapPayment = maperData(mapPayment);
		 PaymentsServiceRequest paymentsServiceRequest = mapPayment.Map();
		 mapOutPut(createPayment(paymentsServiceRequest));
		 Trace.trace(Trace.Debug, "", "actualizando reintento:"+mapPayment.getReintento());
		 Trace.trace(Trace.Debug, "", "actualizando operacion:"+mapPayment.getOperationNumber());
		 /*Actualizacion del reintento*/
		 try {
			 MetodoEnum metodo = MetodoEnum.ACTUALIZAR_REINTENTO;
			 OperacionMulticanal om = creaOM(CONSULTA_OM_PSE);
			 om.setValueAt(ENTRADA_METODO, metodo.name());
			 om.setValueAt("Entrada.actualizarReintento.bankTrade",mapPayment.getOperationNumber());		
			 om.setValueAt("Entrada.actualizarReintento.reintento",mapPayment.getReintento());
			 om.execute();
			 Trace.trace(Trace.Debug, "", "actualizado reintento: "+mapPayment.getReintento());
			 Trace.trace(Trace.Debug, "", "actualizado operacion: "+mapPayment.getOperationNumber());
		 }catch (Exception e) {
			 Trace.trace(Trace.Debug, "", "fallo la acutualizacion reintento:"+e);
		 }
		 Trace.trace(Trace.Debug, "", "finaliza postPayment:");
		 setEstado("14");
	 }
	 
	 private PaymentsServiceResponse createPayment(PaymentsServiceRequest paymentsServiceRequest) {
		 try {
			Trace.trace(Trace.Debug, "OpFinalizarOperaciones", "createPayment() Inicio createPayment ");
			MetodosPayment metodo = MetodosPayment.POST_PAYMENT;
			om= creaOM(OM_PAYMENTS_SERVICE);
			om.setValueAt(ENTRADA_METODO, metodo.name());
			om.setValueAt("Entrada.postPayment.paymentObject", paymentsServiceRequest);
			om.execute();
			PaymentsServiceResponse paymentsServiceResponse=(PaymentsServiceResponse)om.getValueAt("Salida.paymentObject");
			Trace.trace(Trace.Debug, "OpFinalizarOperaciones", "createPayment() FIN ");
			return paymentsServiceResponse;
		} catch (BbvaException e) {
			Trace.trace(Trace.Error, "", "OpFinalizarOperaciones : createPayment() : Error Al ejecutar om " + e.getMessage());
		} catch (DSEObjectNotFoundException e) {
			Trace.trace(Trace.Error, "", "OpFinalizarOperaciones : createPayment() : Argumeto no valido " + e.getMessage());
		} catch (DSEInvalidArgumentException e) {
			Trace.trace(Trace.Error, "", "OpFinalizarOperaciones : createPayment() : Error dato no encontrado " + e.getMessage());
		}
		 return null; 
	 }
	 
	 private MapPayment maperData (MapPayment mapPayment) throws DSEObjectNotFoundException, BbvaException, DSEInvalidArgumentException {
		 Trace.trace(Trace.Debug, "", "Entro a maperData");
		 String account=(String) getValueAt("selectCuentaOrden");
		 if(!account.startsWith("0013")) {
			 Datum comisionService = getObjectValue(OPERATION_OBJ, Datum.class);
			 account=comisionService.getReimbursementAccount().getAccount();
		 }
		 mapPayment.setAccount(account);	
		 String comission=""+getValueAt(VALUE_COMISION);
		 BigDecimal b= new BigDecimal(comission).setScale(2, RoundingMode.HALF_UP);
		 String Taxess=""+getValueAt("iva");
		 BigDecimal c= new BigDecimal(Taxess).setScale(2, RoundingMode.HALF_UP);
		 String valorDebitar=""+(String) getValueAt(VALUE_TOTAL_DEBI);
		 BigDecimal d=new BigDecimal(valorDebitar);
		 mapPayment.setAmountTotalFees(b);
		 mapPayment.setAmountTotalTaxes(c);
		 mapPayment.setValorTotal(d);
		 mapPayment.setAutorizationNumber((String) getValueAt(NUM_TRANS));
		 String[] numberDocument = getValueAt("E_Fijo_Nit").toString().split("-");
		 mapPayment.setDocumentNumber(""+numberDocument[0]+""+numberDocument[1]);
		 String[] bank = getValueAt("selectBank").toString().split("@");
		 mapPayment.setIdBank(bank[0]);
		 mapPayment.setNameBank(bank[1]);
		 mapPayment.setNameDocumentType("NIT");
		 mapPayment.setOperationNumber("T"+(String) getValueAt("referenciaOpe"));
		 mapPayment.setRepresentativeName("USER GENERIC");
		 mapPayment.setTypeDocument("3");
		 mapPayment.setUrl((String) getValueAt(VALUE_ORIGIN)+(String) getValueAt("pathPse"));		 
		 String reintento = buscarReintento("T"+(String) getValueAt("referenciaOpe"),"");
		 mapPayment.setReintento(reintento);
		 Trace.trace(Trace.Debug, "", "Finaliza maperData "+mapPayment.toString());
		 return mapPayment;
	 }
	 
	 private RequestBankTradeService setOpenType(RequestBankTradeService peticion,String sSelectBeneficiario, String sSelectCuentaB, String sSelectCuentaOrden) throws DSEObjectNotFoundException, Exception {
		 if (getValueAt("selectOpe").equals("T1")  || getValueAt("selectOpe").equals("T2") ) {
				peticion.setInfcam70Sw1(peticion.getDeOperac());
				peticion.setTyOperac("TFOUS");
				peticion = cargarInfoBeneficiario(peticion, sSelectBeneficiario, sSelectCuentaB, sSelectCuentaOrden);
			}else if (getValueAt("selectOpe").equals("H1") || getValueAt("selectOpe").equals("H2") ) {
				peticion.setInfcam70Sw1(getValueAt("descripNegociacion").toString());
				peticion.setTyOperac("TFIUS");
			}
			//INI incidencia 181 FX CMC 12/02/2019
			if(getValueAt("selectOpe").equals("T2")) {
				peticion.setNoAvance(getValueAt(AVANCE_OPE).toString());
				peticion.setTasaDivi(Double.valueOf(getValueAt(TASA_DIVISA_PESO).toString()));	
				peticion.setTasaAvance(getValueAt(TASA_DIVISA_PESO).toString());
				peticion.setTasaUsd(Double.valueOf(getValueAt(TASA_USD_PESO).toString()));
				peticion.setTasaLinea(Double.valueOf(getValueAt(TASA_DIVISA).toString()));
			}
			return peticion;
	 }
	 
	 private int processErrorBtConector(Exception e, RequestBankTradeService peticion, String flujo, int valor) throws Exception {
		 
		 Trace.trace(Trace.Debug, "", "### updatePaso2() PASO_2_1_0 - Falla lanzando web service: " + e); // INC 182 Traza para revision cuando falle el WS
			if (flujo.equalsIgnoreCase("pte")) {
				if (e instanceof AxisFault) {//INI VARIOS NITS 2 TOUT CMC 17-02-2020
				AxisFault ex = (AxisFault) e;
					if(ex.detail.getMessage().contains(CONNECT_TIMED_OUT) ||ex.getFaultString().contains(SOCKET_TIMEOUT_EXCE)) {
						Trace.trace(Trace.Error, "", "### peticionEnvioWs() Control TIME OUT  - Falla lanzando web service: " + e);
						valor = 1;
						try {
							setValueAt("msjError", "");
							setValueAt("PeticionWebService", peticion);
						} catch (Exception j) {	Trace.trace(Trace.Error, "", "### Se genera error al setear variables TimeOut..." + j);}
						return valor;
					}
				} //FIN VARIOS NITS 2 TOUT CMC 17-02-2020
				Trace.trace(Trace.Debug, "", "### updatePaso2() PASO_2_1 - Falla lanzando web service: " + e); // INC 182 Traza para revision cuando falle el WS 
				setValueAt("msjError", "No responde el servicio");
				valor = 0;
			 }
			 else
			 {
					this.setEstado("ERROR");
					Trace.trace(Trace.Debug, "", "### updatePaso2() PASO_2 - Falla lanzando web service: " + e);
					setValueAt("msjError", "SISTEMA TEMPORALMENTE NO DISPONIBLE");
					ManejarExcepcion(3, "ERROR EN BENEFICIARIO - BANKTRADE", "SISTEMA TEMPORALMENTE NO DISPONIBLE", "", e, "", this, "", "");						
			 }
		   return valor;
	 }
	 
	 private int processErrorUpdatePaso2(RequestBankTradeService peticion,String flujo,int valor) throws DSEObjectNotFoundException, Exception {
		 if(!getValueAt("errorCodigo").equals("S")) { //Incidencia 225B FX CMC 06/09/2019	
				if(peticion!=null){
					 try{
						ResponseBankTradeService response = WrapperBanktradeService.execute(peticion);
						if(response.getCodError() != null){
							valor = evalErrorpaso2Empy(response,peticion,valor,flujo);
						}else{
							Trace.trace(Trace.Debug, "", MENSAJE_ERROR);
							setValueAt("msjError", "RTA null");
						}
					}catch (Exception e) {
						valor = processErrorBtConector(e,peticion,flujo,valor);					
					 }
				}				
			}
		 return valor;
	 }
	 
	 private int evalErrorpaso2Empy(ResponseBankTradeService response, RequestBankTradeService peticion, int valor, String flujo) throws Exception {
		 if(!response.getCodError().equals("")){
				
				String mensaje = ERROR_WS_BANKTRADE.concat(response.getSequence()+" "+response.getCodError());
				Trace.trace(Trace.Debug, "", "### updatePaso2() PASO_2 - error de web service: " + mensaje);
				setValueAt("msjError", mensaje);
				/* INI INC 61 Fx - 30-05-2018*/
				if(flujo.equalsIgnoreCase("pte"))
				{
					valor = 0;
				}
				/*FIN INC 61 Fx - 30-05-2018*/						
			}else{
				Trace.trace(Trace.Debug, "", "### updatePaso2() PASO_2 - response.getNumOpera " + response.getNumOpera());
				//INI incidencia 227 FX CMC 21/08/2019
				try{
					setValueAt("msjError", "");
					setValueAt("PeticionWebService", peticion);
				}catch(Exception j){
					Trace.trace(Trace.Debug, "", "### Se genera error al setear variables..."+ j);
				}
				valor = 1;/*INC 61 Fx - 30-05-2018*/
				Trace.trace(Trace.Debug, "", "### updatePaso2() PASO_2 valor: " + valor); //INC 182 Traza para revision cuando falle el WS
			}
		 return valor;
	 }
	 
	 private String validateElement(String nameElement) {
		 String retorno = "";
		 try {
			retorno = getValueAt(nameElement) != null  ? (String) getValueAt(nameElement) : "";
		} catch (DSEObjectNotFoundException e) {
			e.printStackTrace();
		} 
		 return retorno;
	 }
	 
	private void callBanktradeService(RequestBankTradeService peticion) throws DSEObjectNotFoundException, DSEInvalidArgumentException {
		try {
			ResponseBankTradeService response = WrapperBanktradeService.execute(peticion);
			if (response.getCodError() != null) {
				if (!response.getCodError().equals("")) {
					String mensaje = ERROR_WS_BANKTRADE.concat(response.getSequence() + " " + response.getCodError());
					Trace.trace(Trace.Debug, "", "### updatePaso3() PASO_3 - error de web service: " + mensaje);
					setValueAt("msjError", mensaje);
				} else {
					Trace.trace(Trace.Debug, "",
							"### updatePaso3() PASO_3 - response.getNumOpera " + response.getNumOpera());
					setValueAt("msjError", "");
					setValueAt("PeticionWebService", peticion);
				}
			} else {
				Trace.trace(Trace.Debug, "", MENSAJE_ERROR);
				setValueAt("msjError", "RTA null");
			}
		} catch (Exception e) {
			Trace.trace(Trace.Debug, "", "### updatePaso3() PASO_3 - Falla lanzando web service: " + e);
			setValueAt("msjError", "SISTEMA TEMPORALMENTE NO DISPONIBLE");

		}
	}
	
	private void mapOutPut(PaymentsServiceResponse paymentsServiceResponse) throws DSEObjectNotFoundException, DSEInvalidArgumentException {
		String customerID=""+paymentsServiceResponse.getData().getHolder().getIdentityDocuments().get(0).getDocumentType().getId()+paymentsServiceResponse.getData().getHolder().getIdentityDocuments().get(0).getDocumentNumber();
		setValueAt(NUM_TRANS, "");
		setValueAt(VALUE_TOTAL_DEBI, "");
		setValueAt("showBlotter", "true");
		setValueAt("cuspse", paymentsServiceResponse.getData().getId());
		setValueAt("customerIdPse", customerID);
		setValueAt("linkPse", paymentsServiceResponse.getData().getLink().getHref());
	}
	
	private String buscarReintento(String numeroOperacion, String tipoRetorno) throws BbvaException, DSEObjectNotFoundException, DSEInvalidArgumentException {
		Trace.trace(Trace.Debug, "", "Entro a buscarReintento");
		String numReintento="";		
		String reintento="0";
    	IndexedCollection icOMUsuariosPseOrden = null;
    	List<UsuariosPseDto> usuarios = null;
		MetodoEnum metodo = MetodoEnum.LISTAR_OPERACION;
		OperacionMulticanal om = creaOM(CONSULTA_OM_PSE);
		om.setValueAt(ENTRADA_METODO, metodo.name());
		om.setValueAt("Entrada.filtroConsulta.banckTrade",numeroOperacion);		
		om.execute();
		icOMUsuariosPseOrden = (IndexedCollection) om.getElementAt(SALIDA_LISTAR_USURORDE);
		if (icOMUsuariosPseOrden.size()>0) {
			usuarios = mapUsers(icOMUsuariosPseOrden);
			if(tipoRetorno.equals("NUM_TRAN")) {
				return usuarios.get(0).getNumeroTrans();
			}
			reintento= usuarios.get(0).getTransaccioEject();
			int reintentoInt = Integer.parseInt(reintento);
			switch (reintentoInt){
			case 0:
				numReintento="1";
				break;
			case 1:
				numReintento="2";
				break;
			case  2:
				numReintento="3";
				break;
			default :
				Trace.trace(Trace.Debug, "", "Supero el reintento"+reintento );    
			}
		}
		Trace.trace(Trace.Debug, "", "termino a buscarReintento"+reintento);
		return numReintento;
	}
	
	private List<UsuariosPseDto> mapUsers(IndexedCollection icOMUsuariosPseOrden) throws DSEObjectNotFoundException {
    	
    	List<UsuariosPseDto> usuarios = new ArrayList<UsuariosPseDto>();

		for (int i = 0; i < icOMUsuariosPseOrden.size(); i++) {

			KeyedCollection listaUsuarioOm = (KeyedCollection) icOMUsuariosPseOrden.getElementAt(i);
			UsuariosPseDto usuario = new UsuariosPseDto();
			usuario.setNumeroTrans(listaUsuarioOm.getValueAt("numTransaccion").toString());
			usuario.setUsuarioAuto(listaUsuarioOm.getValueAt("usuarioAut").toString());
			usuario.setTipoPago(listaUsuarioOm.getValueAt("tipoPago").toString());
			usuario.setValorDesde(listaUsuarioOm.getValueAt("valorDesde").toString());
			usuario.setValorHasta(listaUsuarioOm.getValueAt("valorHasta").toString());
			usuario.setFechaTrans(listaUsuarioOm.getValueAt("fechaTrans").toString());
			usuario.setHora(listaUsuarioOm.getValueAt("hora").toString());
			usuario.setEstado(listaUsuarioOm.getValueAt("estado").toString());
			usuario.setIpUsuarioAudi(listaUsuarioOm.getValueAt("ipUsuarioAudi").toString());
			usuario.setUsuarioAudi(listaUsuarioOm.getValueAt("usuarioAudi").toString());
			usuario.setFechaEjecu(listaUsuarioOm.getValueAt("fechaEjecu").toString());
			usuario.setValorUtilizado(listaUsuarioOm.getValueAt("valorUtilizado").toString());
			usuario.setTransaccioEject(listaUsuarioOm.getValueAt("transaciEject").toString());
			usuarios.add(usuario);

		}
		return usuarios;
    }
	
	private RequestBankTradeService inputMapperFinishBankTrade (Datum datum) throws DSEInvalidArgumentException, DSEObjectNotFoundException {
		RequestBankTradeService peticion = new RequestBankTradeService();
		Date fechaActual = new Date();
		Double rateOperationTemp;
		String usdRateComm;
		String swiftcampo57A;
		String numeralCambiario = "";
		String iDBCOOrdenante = datum.getPseOperationId();
		String tipotasaDivisa = datum.getTaxes().getItemizeTaxes().get(1).getDescription();
		String nombresbenefic="";
		String codigosabanco1="";
		String nombrebancobe="";
		String cuentabenefica="";
		String tiposabanco1="";
		String tiposabanco2="";
		peticion.setTyUsuario("JR");
		peticion.setFechaHoraNegociacion(fechaActual);
		peticion.setCurDbComm("COP");
		peticion.setTpCambioComm("1.000000000");												
		peticion.setCodOpecampo23b("CRED");
		peticion.setTyTransa("T");
		peticion.setDeComm(PSE);		
		peticion.setTyOperac("TFOU");
		peticion.setIdSwTpBcoOrd("SW");
		peticion.setFeValor(fechaActual);
		peticion.setMonAvance(datum.getAdvance().getNegotiatedAmount().getAmount().toString());
		peticion.setDeOperac(datum.getDescription());
		String documentocliente="";
		if (!datum.getBeneficiaries().get(0).getFullName().equalsIgnoreCase("BBVA")) {
			peticion.setIdCliente(datum.getBeneficiaries().get(1).getId());
			documentocliente=datum.getBeneficiaries().get(1).getId();
		}else {
			peticion.setIdCliente(datum.getBeneficiaries().get(0).getId());
			documentocliente=datum.getBeneficiaries().get(0).getId();
		}
		peticion.setMonNegoc(datum.getAdvance().getNegotiatedAmount().getAmount().toString());
		peticion.setCurNegoc(datum.getAdvance().getNegotiatedAmount().getCurrency());
		
		if (tipotasaDivisa.equalsIgnoreCase("TRM DIV")) {
			rateOperationTemp = datum.getTaxes().getItemizeTaxes().get(1).getItemizeTaxUnit().getAmount().doubleValue();
			usdRateComm = datum.getTaxes().getItemizeTaxes().get(0).getItemizeTaxUnit().getAmount().toString();				
		}else {
			rateOperationTemp = datum.getTaxes().getItemizeTaxes().get(0).getItemizeTaxUnit().getAmount().doubleValue();
			usdRateComm = datum.getTaxes().getItemizeTaxes().get(1).getItemizeTaxUnit().getAmount().toString();
		}
		peticion.setTasaDivi(rateOperationTemp);
		peticion.setTasaUsd(rateOperationTemp);
		peticion.setUsdRateComm(usdRateComm);
		peticion.setTasaAvance(rateOperationTemp.toString());
		peticion.setTasaLinea(1.0);
		peticion.setCanal("80");
		peticion.setTyNegoci("**");
		peticion.setNoAvance(datum.getAdvance().getId());
		peticion.setNoCtaComm(iDBCOOrdenante);
		String nombreCliente="";
		String equivalentePesos="";
		String descripcionoperacion="";
		try {
			KeyedCollection detallesOperacion = om_FNGU("","", "T", datum.getReimbursementAccount().getAccount().toString(), 
					obtenerUsuario((String) getValueAt(VALUE_LOGON), (String) getValueAt(VALUE_COD_USUA)), new Double(datum.getId().substring(1)));
			if(detallesOperacion!=null) {
				numeralCambiario = (String) detallesOperacion.getValueAt("FNUMCA");
				nombreCliente = (String) detallesOperacion.getValueAt(ORDENA);
				equivalentePesos=(String) detallesOperacion.getValueAt(EQUIPES);
				nombresbenefic=(String) detallesOperacion.getValueAt("BENEFIC");
				codigosabanco1=(String) detallesOperacion.getValueAt(ORDENA);
				nombrebancobe=(String) detallesOperacion.getValueAt("BANBENE");
				cuentabenefica=(String) detallesOperacion.getValueAt(CUENTABON);
				tiposabanco1=(String) detallesOperacion.getValueAt("CODSWIFT");
				tiposabanco2=(String) detallesOperacion.getValueAt("CODABBA");
			}
		} catch (NumberFormatException e) {
			Trace.trace(Trace.Debug, "", "Error para pasar de cadena de texto a double:  " + e.toString());
		}
		peticion.setNuCambiario(numeralCambiario);
		peticion.setNoOperac1(datum.getId().substring(1));
		peticion.setNoCtaPrincipal(iDBCOOrdenante);
		peticion.setNoCtaPrincipalCre(iDBCOOrdenante);
		peticion.setInfcam70Sw1(datum.getDescription());
		if(datum.getAbaCode()!= null) {
			swiftcampo57A = datum.getAbaCode();
		    setValueAt("cuentaintermed",tiposabanco2);		    
		    setValueAt("tiposabanco1","ABA");
		}else {
			swiftcampo57A = datum.getSwiftCode();
			setValueAt("cuentaintermed",tiposabanco1);
			setValueAt("tiposabanco1","Swift");
		}	
		//peticion.setSwiftcampo57A(swiftcampo57A);
		peticion.setMontoNetoPse(datum.getNetAmountSecureOnlinePayments().getAmount().toString());
		setValueAt("selectMoneda",datum.getAdvance().getNegotiatedAmount().getCurrency());
		setValueAt("descripNegociacion",datum.getDescription());		
		setValueAt(NUM_TRANS,datum.getId());
		setValueAt("codpseCus",datum.getPseOperationId());
		PaymentsServiceResponse paymentsServiceResponse = getPaymentById(datum.getPseOperationId());
		String bankName=paymentsServiceResponse.getData().getOrigin().getBank().getName();
		if(bankName==null) {
			setValueAt(CODIGO_CONS_BANCO,"");
		}else {
		setValueAt(CODIGO_CONS_BANCO,bankName);
		}
		setValueAt("tipoOperacion",datum.getAdvance().getId());
		//setValueAt("FmetodoDePago",metodoDePago);
		setValueAt("nitConsulta",documentocliente);
		setValueAt("nitNombre",nombreCliente);
		if(datum.getAdvance().getId().equalsIgnoreCase("STD1")) {
			descripcionoperacion="Negociaci&oacute;n en Linea - Giro Al exterior";
			setValueAt(AVANCE_OPE,"N.A");
		}else {
			descripcionoperacion="Negociaci&oacute;n en Mesa de Dinero - Giro Al exterior";
			setValueAt(AVANCE_OPE,datum.getAdvance().getId());
		}
		
		setValueAt("descripNegociacion",datum.getDescription());
		setValueAt("monto",datum.getAdvance().getNegotiatedAmount().getAmount().toString());
		setValueAt(VALUE_EQUIV_PESOS,formateoMoneda(equivalentePesos));
		setValueAt("descNumeral",numeralCambiario);
		setValueAt("NumeralC",numeralCambiario);
		setValueAt(VALUE_RESULTADO,PROCESO_BANCO);
		setValueAt(VALUE_COMISION,formateoMoneda(datum.getTransferedTotalAmount().getAmount().toString()));
		setValueAt("iva",formateoMoneda(datum.getTaxes().getTotalTaxes().getAmount().toString()));
		setValueAt(VALUE_TOTAL_DEBI,formateoMoneda(datum.getNetAmountSecureOnlinePayments().getAmount().toString()));
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String strDate= formatter.format(fechaActual);
		setValueAt("fechaActual",strDate);		
		setValueAt("descripcionoperPSE", descripcionoperacion);
		
		setValueAt("nombresbenefic",nombresbenefic);
		setValueAt("nombrebancobe",nombrebancobe);
		setValueAt("codigosabanco1",codigosabanco1);
		setValueAt("cuentabenefica",cuentabenefica);
		
		Trace.trace(Trace.Debug, "", "inputMapperFinishBankTrade() Data xml:  " + peticion.toString());
		return peticion;
	}
	
	private void finishBankTrade (RequestBankTradeService requestBankTradeService) throws DSEObjectNotFoundException, DSEInvalidArgumentException {
		
		if(requestBankTradeService!=null){
			try{
				ResponseBankTradeService response = WrapperBanktradeService.execute(requestBankTradeService);
				if(response.getCodError() != null){
					if(!response.getCodError().equals("")){
						String mensaje = ERROR_WS_BANKTRADE
								.concat(response.getSequence() + " " + response.getCodError());
						Trace.trace(Trace.Debug, "", "finishBankTrade() - error de web service: " + mensaje);
						setValueAt("msjError", mensaje);
					}else{
						Trace.trace(Trace.Debug, "",
								"finishBankTrade() - response.getNumOpera " + response.getNumOpera());
						setValueAt("msjError", "E");
					}
				}else{
					Trace.trace(Trace.Debug, "", MENSAJE_ERROR);
					setValueAt("msjError", "RTA null");
				}
			}catch(Exception e){
				buildErrorBt(e);
			}
		}
	}
	private void mapModificarPse() throws DSEObjectNotFoundException, DSEInvalidArgumentException, BbvaException {
		Trace.trace(Trace.Debug, "", "Entro mapModificarPse");
		String tipoFondoGiro = validateElement(TIPO_FONDO_GIRO);
		setValueAt(TIPO_FONDO_GIRO, tipoFondoGiro);
		if(tipoFondoGiro.equals(FONDO_PSE)) {
			getBancosPse();
			String numTrans =(String)getValueAt(NUM_TRANS);
			if(numTrans==null || numTrans.trim().equals("")) {
				Datum comisionService = getObjectValue(OPERATION_OBJ, Datum.class);
				setValueAt("rate", comisionService.getRates().getTotalRates().getAmount().toString());
				setValueAt(VALUE_TOTAL_DEBI, comisionService.getNetAmountSecureOnlinePayments().getAmount().toString());
				setValueAt(NUM_TRANS, buscarReintento(comisionService.getId(), "NUM_TRAN"));
				setValueAt(VALUE_ORIGIN, "pse");
				setValueAt(VALUE_EQUIV_PESOS, comisionService.getChargeAmount().getAmount().toString());
				setValueAt(VALUE_COMISION, comisionService.getTransferedTotalAmount().getAmount().toString());
				setValueAt("iva", comisionService.getTaxes().getTotalTaxes().getAmount().toString());
				setValueAt(VALUE_FIRMA, 0);
				KeyedCollection detallesOperacion = om_FNGU("","", "T", comisionService.getReimbursementAccount().getAccount().toString(), 
						obtenerUsuario((String) getValueAt(VALUE_LOGON), (String) getValueAt(VALUE_COD_USUA)), new Double(comisionService.getId().substring(1)));
				if(detallesOperacion!=null) {
					String nit=(String)detallesOperacion.getValueAt(NIT_COMPLETO);
					nit=nit.substring(1);
					nit=nit.replaceFirst(VALUE_SIGNO, "");
					String digitoVerifi=nit.substring(nit.length()-1);
					nit=nit.substring(0,nit.length()-1)+"-"+digitoVerifi;
					setValueAt(VALUE_FIJO_NIT, nit);
		            setValueAt(VALUE_FIJO_NOMBRE, (String)detallesOperacion.getValueAt(NOMBRE_CLIENTE));
				}
			}else {
				setValueAt("rate", getValueAt("rate"));
				setValueAt(VALUE_TOTAL_DEBI,getValueAt(VALUE_TOTAL_DEBI));
				setValueAt(NUM_TRANS, numTrans);
				setValueAt(VALUE_ORIGIN, "pse");
				setValueAt(VALUE_EQUIV_PESOS, getValueAt(VALUE_EQUIV_PESOS));
				setValueAt(VALUE_COMISION,getValueAt(VALUE_COMISION));
				setValueAt("iva",getValueAt("iva"));	
				setValueAt(VALUE_FIRMA, 0);
			}
		}else {
			setValueAt("rate", "0");
			setValueAt(VALUE_TOTAL_DEBI,"0");
			setValueAt(NUM_TRANS, "0");
			setValueAt(VALUE_ORIGIN, "NOPSE");
			setValueAt(VALUE_EQUIV_PESOS, "0");
			setValueAt(VALUE_COMISION, "0");
			setValueAt("iva","0");
		}
		Trace.trace(Trace.Debug, "", "Fin mapModificarPse");
	}
	
	private String getNameByFile(String[] datosArchivos, String nombreBase) throws DSEObjectNotFoundException, Exception {
		String nombre="";
		 for (int i = 0; i < datosArchivos.length; i++)
		    {
		    	Trace.trace(Trace.Debug, "", "### Entra al for en el indice = " + i + " con el dato = " + datosArchivos[i]);
		    	if(!"".equals(datosArchivos[i]))
		    	{
			    	String datosArchivo = datosArchivos[i];
				    String arrayDatos[] = datosArchivo.split("-");
				    Trace.trace(Trace.Debug, "", "### Pasa el segundo Split - Indice0 = " + arrayDatos[0] + " - Indice1 = " + arrayDatos[1]);				    
				    String file = "file" + (i + 1);
					if(i == 0)
						nombre = "Declaración de cambio, Formulario N°1";
					else if(i == 1)
						nombre = "Fotocopia de la factura proforma";
					else
						nombre = nombreBase;
					
					Trace.trace(Trace.Debug, "", "### Nombre archivo = " + nombre);

					enviarImagenesbas64((String) getValueAt(file), arrayDatos[1], arrayDatos[0], nombre);
		    	}
			}
		 return nombre;
	}
	
	private void setPayeeInfo(String TipoOpe, String sSelectCuentaOrden) throws DSEObjectNotFoundException, DSEInvalidArgumentException {

        String ConsultarFNGU="no";
        if(getValueAt(VALUE_FIJO_NIT)==null && getValueAt(VALUE_FIJO_NOMBRE)==null) {
        	ConsultarFNGU="si";
        }else if((getValueAt(VALUE_FIJO_NIT)!= null && getValueAt(VALUE_FIJO_NOMBRE)!= null) && 
        		  (getValueAt(VALUE_FIJO_NIT).toString().trim().equalsIgnoreCase("") && getValueAt(VALUE_FIJO_NOMBRE).toString().trim().equalsIgnoreCase("")
        		)) {
        	ConsultarFNGU="si";
        }
        Trace.trace(Trace.Information, getClass().getName(), "OMAN-INICIO: finalizar(): Valida si es requerido consultar la FNGU: "+ConsultarFNGU );
        if(ConsultarFNGU.equalsIgnoreCase("si") ) {
        	
        	setValueAt("s_Fijo_Nit","");
            setValueAt("s_Fijo_Nombre", "");
  
			String ref=(String)getValueAt(VALUE_LOGON);
			String usu=(String)getValueAt(VALUE_COD_USUA);
			String usuario=obtenerUsuario(ref, usu);
			String tipoO=TipoOpe;
			String numeroOper=getValueAt("referenciaOpe").toString().trim();
			String cuenta=sSelectCuentaOrden.trim();
			String PrimerCarecter=numeroOper.substring(0,1);
			if(PrimerCarecter.equalsIgnoreCase("T") || PrimerCarecter.equalsIgnoreCase("H")) {
				numeroOper=numeroOper.substring(1);
			}
			KeyedCollection detallesOperacion=om_FNGU("","",tipoO,cuenta ,usuario,new Double(numeroOper));
		
			if(detallesOperacion!=null) {
				if(detallesOperacion.getValueAt(NIT_COMPLETO)!=null && !detallesOperacion.getValueAt(NIT_COMPLETO).toString().equals("")) {
					 Trace.trace(Trace.Information, getClass().getName(), "OMAN-INICIO: finalizar(): Formateamos los datos nit y nombre" );
				String nit=(String)detallesOperacion.getValueAt(NIT_COMPLETO);
				nit=nit.substring(1);
				nit=nit.replaceFirst(VALUE_SIGNO, "");
				String digitoVerifi=nit.substring(nit.length()-1);
				nit=nit.substring(0,nit.length()-1)+"-"+digitoVerifi;
				setValueAt("s_Fijo_Nit",nit );
	            setValueAt("s_Fijo_Nombre",(String)detallesOperacion.getValueAt(NOMBRE_CLIENTE));
				}
			}
        }else {
        	 Trace.trace(Trace.Information, getClass().getName(), "OMAN-INICIO: finalizar(): Datos cargados desde Pr Nit y nombre " );
        	setValueAt("s_Fijo_Nit", getValueAt(VALUE_FIJO_NIT).toString().trim());
            setValueAt("s_Fijo_Nombre", getValueAt(VALUE_FIJO_NOMBRE).toString().trim());
        }
	}
	
	private void buildErrorBt(Exception e) throws DSEObjectNotFoundException, DSEInvalidArgumentException {
		if (e instanceof AxisFault){
			AxisFault ex = (AxisFault) e;
			if(ex.detail.getMessage().contains(CONNECT_TIMED_OUT)
					|| ex.getFaultString().contains(SOCKET_TIMEOUT_EXCE)){
				Trace.trace(Trace.Error, "",
						"finishBankTrade() Control TIME OUT  - Falla lanzando web service: " + e);
				setValueAt("msjError", "E");
				return;
			}
		}
		Trace.trace(Trace.Error, "", "finishBankTrade() - Falla lanzando web service: " + e);
		setValueAt("msjError", "SISTEMA TEMPORALMENTE NO DISPONIBLE");
	}
	private PaymentsServiceResponse getPaymentById(String cusPse) {
		PaymentsServiceResponse paymentsServiceResponse = new PaymentsServiceResponse();
		MetodosPayment metodosPayment = MetodosPayment.GET_ID_PAYMENT;
		try {
			OperacionMulticanal om = creaOM(OM_PAYMENTS_SERVICE);
			om.setValueAt(ENTRADA_METODO , metodosPayment.name());
			om.setValueAt("Entrada.getPaymenById.id", cusPse);
			om.execute();		
			paymentsServiceResponse = (PaymentsServiceResponse) om.getValueAt("Salida.paymentObject");
		} catch (Exception e) {
			Trace.trace(Trace.Error, "", "Fallo en la ejecucion del metodo getPaymentById() " + e.toString());
		}
		return paymentsServiceResponse;
	}
}
