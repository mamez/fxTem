package com.grupobbva.bc.col.web.comercioext;

//INI INC 158 FX CMC 19/01/2019
import java.io.BufferedReader;//INC 158 FX CMC 21/01/2019
import java.io.File;
import java.io.FileReader;
import java.io.IOException; // LISTA DE NIT/COMERCIO EXTERIOR - OSNEIDER ACEVEDO - CMC - 14-05-2019
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
//fx Fin inc 6.1 12/06/2018
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
//fx Ini inc 6.1 12/06/2018
import java.util.HashMap;
import java.util.List;
import java.util.Locale; //VARIOS NIT F2 - 16-01-2019 - MONTOS
import java.util.Map;
import java.util.StringTokenizer;
//FIN INC 158 FX CMC 19/01/2019
//INI INC 202 FX CMC 19/03/2019
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.grupobbva.bc.col.web.comercioext.clientews.RequestBankTradeService;
import com.grupobbva.bc.col.web.comercioext.clientews.ResponseBankTradeService;
import com.grupobbva.bc.col.web.comercioext.clientews.WrapperBanktradeService;
import com.grupobbva.bc.col.web.pse.MetodoEnum;
import com.grupobbva.bc.col.web.pse.UsuariosPseDto;
import com.grupobbva.bc.col.web.utilidades.OpControl;//CMC - FUNCION DE NEGOCIO FNGU - PRUEBA - 19/09/2019
import com.grupobbva.col.rest.conector.dto.comisionService.ComisionServiceDto;
import com.grupobbva.col.rest.conector.dto.dynamicCurrency.Customer;
import com.grupobbva.col.rest.conector.dto.dynamicCurrency.Data;
import com.grupobbva.col.rest.conector.dto.dynamicCurrency.DinamicCurrencyRequest;
import com.grupobbva.col.rest.conector.dto.dynamicCurrency.DinamicCurrencyResponse;
import com.grupobbva.col.rest.conector.dto.dynamicCurrency.ExchangeRate;
import com.grupobbva.col.rest.conector.dto.dynamicCurrency.ItemizeRate;
import com.grupobbva.col.rest.conector.dto.operativeConditions.OperativeConditionsResponse;
import com.grupobbva.col.rest.conector.dto.payment.ConciliateResponse;
import com.grupobbva.col.rest.conector.dto.payment.PaymentsServiceResponse;
import com.grupobbva.col.rest.conector.om.MetodosConditions;
import com.grupobbva.col.rest.conector.om.MetodosPayment;
import com.grupobbva.col.servicios.OpGestion_xhr_rs7;
import com.grupobbva.ii.sf.base.BbvaARQException;
import com.grupobbva.ii.sf.base.BbvaException;
import com.grupobbva.ii.sf.base.BbvaNGException;
import com.grupobbva.ii.sf.operacion.OperacionMulticanal;
import com.grupobbva.multidioma.CatalogoMultidioma;
import com.ibm.dse.base.DSEInvalidArgumentException;
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
 * Ventana - Preferencias - Java - Estilo de codigo - Plantillas de codigo
 */
public class OpConsultaOperaciones extends OpGestion_xhr_rs7 {
	
	String sOmCotizador ="consulta_tasas_operaciones_negociacion_om";
	String sOmGiros ="consulta_operaciones_negociacion_om";
	String sOmParametria ="consulta_detalle_operaciones_negociacion_om";
	
	String sOmAltaBeneficiarios = "alta_beneficiarios_om";
	String sOmConsultaBeneficiarios = "compra_divisas_consulta_beneficiarios_om";
	
	String sOmEstadoCta= "compra_divisas_consulta_estado_cuenta_om";
	String sOmConsultaAvance = "compra_divisas_consulta_avances_om";
	String sOmOficinaTitular= "compra_divisas_consulta_centro_oficina_om";
	String sOmConsultaUnicaOperaciones = "divisas_consulta_unica_operaciones_om";
	//INI- LISTA DE NIT/COMERCIO EXTERIOR - OSNEIDER ACEVEDO - CMC - 14-05-2019 
	String sOmNit="consulta_nit_om";
	String sOmCuenta="consulta_cuenta_om";
	//FIN LISTA DE NIT/COMERCIO EXTERIOR - OSNEIDER ACEVEDO - CMC - 14-05-2019 
	/// Variables Ws ///
	private String BT_IDSUCURSAL = "0073";
	private String documentoCliente = "";
	
	private Date HINI = new Date();
	private Date HFIN =new Date();
	private Date HNINI = new Date();
	private Date HNFIN =new Date();
	
	private final String ERROR_WS_BANKTRADE = "Fallo llamado Banktrade - ";	
	private static final String TIPO_FONDO_GIRO = "tipoFondoGiro";
	private static final String FONDO_PSE = "fondoPSE";
	private static final String FONDO_BBVA = "fondoBBVA";
	private static final String ENTRADA_TIPO_OPE = "Entrada.E_TipoOperacion";
	private static final String ABONO = "ABONO";
	private static final String NIT_NOMBRE = "Nit_Nombre";
	private static final String CONSULTA_OM_PSE = "consulta_pagos_pse_divisa_om";
	private static final String OM_OPERATIVE_CONDITIONS = "om_operative_conditions_service";
	private static final String OM_PAYMENTS_SERVICE = "om_payments_service";
	private static final String ENTRADA_METODO = "Entrada.metodo";
	private static final String SALIDA_LISTAR_USURORDE = "Salida.listarUsuarioOrden.usuarios";
	private static final String TOTAL_DEBITO = "totalDebito";
	private static final String NUM_TRANS = "numTrans";
	private static final String SHOW_BLOTTER = "showBlotter";
	private static final String AMOUNT_EQUIV = "amountEquiv";
	private static final String NOMBRE_CLIENTE = "NOMBRE_CLIENTE";
	private static final String FLAG_OPEN_MODAL = "isOpenModal";
	private static final String VALUE_FALSE = "false"; 
	private static final String EQUIPES = "EQUIPES"; 
	private static final String VALUE_BENEFI = "beneficiario"; 
	private static final String VALUE_STATUS = "status"; 
	private static final String VALUE_ORDEDANTE = "ordenante";
	private static final String STATUS_OK = "OK";
	private static final String STATUS_NOK = "NOK";
	private static final String STATUS_PENDING="PENDING";
	private static final String VALUE_BUSINESS_ID="businessId";
	private static final String EQUIV_PESO_2="equivPesos2";
	private static final String SHOW_BLOTTER_PAGE="showBlotterPage";
	private static final String CONTIGENCIA="contingencia";
	private static final String DATA_SALIDA="-data.salida";
	private static final String EQUIV_PESO="equivPesos";
	private static final String LIT_PETICIO_WEB_SERV="PeticionWebService";
	private static final String SALIDA_COD_ERROR="Salida.COD-ERROR";
	private static final String SALIDA_COD_AVISO="Salida.CODAVISO";
	private static final String PME1147="PME1147";
	private Map<String, Object> responseAjax;
	private static final DecimalFormat FORMAT = new DecimalFormat("###,###.##");
	private static final String DINAMIC_PRICING_DATE_FORMAT= "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	private static final String ERROR= "ERROR";
			
	private OpControl controlOM;//CMC - FUNCION DE NEGOCIO FNGU - PRUEBA - 19/09/2019
	//	/ Variables Ws ///
	
	
	public OpConsultaOperaciones() {
	    super();
	}

	public OpConsultaOperaciones(String anOperationName)
	    throws java.io.IOException {
	    super(anOperationName);
	}

	public OpConsultaOperaciones(
	    String anOperationName,
	    com.ibm.dse.base.Context aParentContext)
	    throws java.io.IOException, com.ibm.dse.base.DSEInvalidRequestException {
	    super(anOperationName, aParentContext);
	}
	
	public void tipoGiros() throws Exception{
		sesion();
		Trace.trace(Trace.Debug, "", " Inicio tipoGiros().");
		setEstado("0");
		Trace.trace(Trace.Debug, "", " Fin tipoGiros()");
        String origen=(String) getValueAt("origen");
		try {
		   boolean redirectBlotter=new Boolean((String)getValueAt(SHOW_BLOTTER));	
		   if(redirectBlotter) {
		       // Confirmacion de pagos getpaymenId,postPaymenId
			   PaymentsServiceResponse paymentsServiceResponse = getPaymentById((String) getValueAt("cuspse"));
			   String statuspseId= ""+paymentsServiceResponse.getData().getStatus().getId();
			   if(!statuspseId.equalsIgnoreCase(STATUS_PENDING)) {				   
				   String paymentIdpse=""+paymentsServiceResponse.getData().getId();
				   String idStatuspse=""+paymentsServiceResponse.getData().getStatus().getId(); 
				   String reasonIdpse=""+paymentsServiceResponse.getData().getStatus().getId();			   
				   String referenceIdpse=""+paymentsServiceResponse.getData().getReferences().get(0).getId(); 
				   String referenceValuepse=""+paymentsServiceResponse.getData().getReferences().get(0).getValue();
				   
				   postPaymentConciliate(paymentIdpse,idStatuspse,referenceIdpse,referenceValuepse);
			   }
			   girosHacia();
			   setValueAt(SHOW_BLOTTER, VALUE_FALSE);
			   setValueAt(SHOW_BLOTTER_PAGE, "true");
		   }
		   if(!origen.equalsIgnoreCase("menu")) {
			   setObjectValue("operationObj", "");
			   setValueAt(LIT_PETICIO_WEB_SERV,"");
			   girosHacia();
			   setValueAt(SHOW_BLOTTER, VALUE_FALSE);
			   setValueAt(SHOW_BLOTTER_PAGE, "true");
		   }
		}catch (Exception e) {
			System.out.println("Error al redireecionar");
		}
	}
		
	public void traerGiros() throws Exception{
		Trace.trace(Trace.Debug, "", " Inicio traerGiros()");
		//INI INC CMC OFICINA GESTORA 27/09/2019
		try {
			setValueAt("codigoSucursal", (String) getValueAt("oficinaGestoraOP"));
		} catch (Exception e) {
			Trace.trace(Trace.Error, getClass().getName(), "####### traerGiros() - ERROR ####### " + e);
		}
		//FIN INC CMC OFICINA GESTORA 27/09/2019
		
		obtenerMontoMaximoCotizacion();//GP17663 MONTO 25-05-2021 CMC
		
		Trace.trace(Trace.Debug, "", " Inicio traerGiros() ClienteCons: " + getValueAt("ClienteCons"));
		if(getValueAt("ClienteCons")!=null) {
			String[] obj=getValueAt("ClienteCons").toString().split("-");
			if(obj.length>1){
				String NumCliente=obj[0];
				String documento=obj[1];
				setValueAt("Num_Nit", documento);
				ConsultarCuentasPorNit2(NumCliente);	
				setValueAt("s_ClienteCons", NumCliente);

			}
		}
		
		if(!validaLista().equalsIgnoreCase("NN")) {
			setEstado("6");
			return;
		}
		
		KeyedCollection kEntradaParametria;
		
		String clave= (String) getValueAt("selectCuentaOrden");
		String tipo= (String) getValueAt("selectOpe");
		setValueAt("pestana", "1");
		setValueAt("pagina", "1");
		setValueAt("selectMoneda", "");
		
		kEntradaParametria = (KeyedCollection) getElementAt(sOmParametria+"-data.entrada");
		kEntradaParametria.setValueAt("NCUENTA",  clave);
		OperacionMulticanal OmParametria = creaOM(sOmParametria);
//INI CMC - FUNCION DE NEGOCIO FNDO - PRUEBA - 19/09/2019
		controlOM = new OpControl();
		controlOM.setConfig((String)getValueAt("datosAPP.pb_cod_serv"),
							(String)getValueAt("datosAPP.iv-cod_emp")+(String)getValueAt("datosAPP.iv-cod_usu"),
							(String)getValueAt("datosAPP.iv-cod_usu"),
							creaOM("sign_on_om"), 
							creaOM("sign_on_om"),
							OmParametria, 
							creaOM(OmParametria.getName()));
		controlOM.control_f100();
		controlOM = null;
//FIN CMC - FUNCION DE NEGOCIO FNDO - PRUEBA - 19/09/2019
		copiarDeOMParametria();
		Trace.trace(Trace.Debug, "", "### traerGiros() - Parametria cargada - monedas");
		
		setValueAt("tipoOperacion", tipo);
		setValueAt("selectCuentaOrden", clave);
		setValueAt("cotizacion.tasaDivisa", new Double("0"));
		setValueAt("cotizacion.tasaDivisaPeso", new Double("0"));
		setValueAt("cotizacion.tasaUsdPeso", new Double("0"));
		setValueAt("cotizacion.equivPesos", new Double("0"));
		Trace.trace(Trace.Debug, "", "Inicio traerGiros() - Parametria inicializada ");
				
		try{
			
			Trace.trace(Trace.Debug, "", "### traerGiros() - cargar  sOmGiros: " + clave);
			copiarAOMGiros(1);// INC 185 PAGINACION FNGE CMC 19-06-2019
			Trace.trace(Trace.Debug, "", "### giros Hacia() --- OM sOmGiros cargada");
			OperacionMulticanal OMConsultaOper = creaOM(sOmGiros);
			//INI - VARIOS NIT F2 - OSNEIDER A. CMC - 02-10-2019
			String tipoOp=(String)getValueAt("tipoOperacion");
			if (null!=tipoOp && tipoOp.trim().equalsIgnoreCase("H")) {
				Trace.trace(Trace.Information, getClass().getName(), "INICIO: traerGiros() - Se agrega Nit como dato de entrada a la FNGE");
				String NitCompleto=getValueAt("Nit_Mod").toString().trim();
				NitCompleto=NitCompleto.replace("-", "");
				String Nit=NitCompleto.substring(1);
				String tipoDoc=NitCompleto.substring(0,1);
				for(int i=NitCompleto.length();i<17;i++) {
					Nit="0"+Nit;
				}
				
				OMConsultaOper.setValueAt("entrada.NIT_COMPLETO",tipoDoc+Nit);
			}
			//FIN - VARIOS NIT F2 -    A. CMC - 02-10-2019
			try{
				ejecutarOM(OMConsultaOper);
			}catch(Exception e1){
				Trace.trace(Trace.Debug, "", "### Falla en traerGiros() *** Lanzando la transaccion" + e1);
			}
			copiarDeOMGiros();
			Trace.trace(Trace.Debug, "", "### girosHacia() *** Paginar H");
			paginar(1);
			
		}catch(Exception e){
			setEstado(ERROR);	
			Trace.trace(Trace.Debug, "", "### Falla en traerGiros() Global *** Lanzando la transaccion" + e);
		}
		validarHora();
		
		setEstado("2");
		setValueAt("S_Nit_Solo", (String)getValueAt("Nit_Solo")); //LISTA DE NIT/COMERCIO EXTERIOR - OSNEIDER ACEVEDO - CMC - 14-05-2019
		asignaNitNombrePr();//VARIOS NIT F2 - OSNEIDER A. CMC - 02-10-2019		
		Trace.trace(Trace.Debug, "", " Fin traerGiros()");
	}
	
	public void girosHacia() throws Exception{
		KeyedCollection kCuenta,kEntradaParametria;
		IndexedCollection icContextoSesion,listaCuentasOP = null;
		Enumeration enContextoSesion1;
		
		String clave = "";
		
		Trace.trace(Trace.Debug, "", " Inicio girosHacia()");
		try {
			setValueAt(SHOW_BLOTTER_PAGE, VALUE_FALSE);
			//INI incidencia 125 FX CMC 11/01/2019
			setValueAt("indNITPrinHacia", "S"); //NITS 1.2 CMC FX 20/08/2019
			setValueAt("indiList", "NN");
			clave = cargarCuentas();			
			String tipo="T";						
			kEntradaParametria = (KeyedCollection) getElementAt(sOmParametria+"-data.entrada");
			kEntradaParametria.setValueAt("NCUENTA",  clave);
			OperacionMulticanal OmParametria = creaOM(sOmParametria);
//INI CMC - FUNCION DE NEGOCIO FNDO - PRUEBA - 19/09/2019
			controlOM = new OpControl();
			controlOM.setConfig((String)getValueAt("datosAPP.pb_cod_serv"),
								(String)getValueAt("datosAPP.iv-cod_emp")+(String)getValueAt("datosAPP.iv-cod_usu"),
								(String)getValueAt("datosAPP.iv-cod_usu"),
								creaOM("sign_on_om"), 
								creaOM("sign_on_om"),
								OmParametria, 
								creaOM(OmParametria.getName()));
			controlOM.control_f100();
//FIN CMC - FUNCION DE NEGOCIO FNDO - PRUEBA - 19/09/2019
			copiarDeOMParametria();
			Trace.trace(Trace.Debug, "", "### girosHacia() - Parametria cargada - monedas");
			obtenerMontoMaximoCotizacion();// GP17663 MONTO 25-05-2021 CMC
			
			setValueAt("tipoOperacion", tipo);
			setValueAt("selectCuentaOrden", clave);
			setValueAt("cotizacion.tasaDivisa", new Double("0"));
			setValueAt("cotizacion.tasaDivisaPeso", new Double("0"));
			setValueAt("cotizacion.tasaUsdPeso", new Double("0"));
			setValueAt("cotizacion.equivPesos", new Double("0"));
			Trace.trace(Trace.Debug, "", "### girosHacia() - Parametria inicializada ");
			setValueAt("selectMoneda", "");
			setValueAt("pestana", "1");
			setValueAt("pagina", "1");
			
			copiarAOMGiros(1);// INC 185 PAGINACION FNGE CMC 19-06-2019
			Trace.trace(Trace.Debug, "", "### giros Hacia() --- OM sOmGiros cargada");
			OperacionMulticanal OMConsultaOper = creaOM(sOmGiros);
			// INI - FILTRO NITS/COMERCIO EXTERIOR - OSNEIDER ACEVEDO - CMC - 11-07-2019 -->
			setValueAt("Nit_Mod", "");	//Se inicializa
			if(getValueAt("selectNit")!=null && !getValueAt("selectNit").toString().trim().equals("")) {
				Trace.trace(Trace.Debug, "", "### giros Hacia() --- OM sOmGiros selectNit"+getValueAt("selectNit").toString());
				String[] obj=getValueAt("selectNit").toString().split("@");
				if(obj.length>1){
					setValueAt("Nit_Mod", obj[0]);		
					OMConsultaOper.setValueAt("entrada.NIT", obj[1]);	
				}						
				//INI NITS 1.2 CMC FX 20/08/2019
				setValueAt("indNITPrinHacia", "N"); 
				String IndicadorNIT= (String)getValueAt("IndicadorNIT");
				
				if(null!=IndicadorNIT && IndicadorNIT.trim().length()>0) {
					int indicadorN= Integer.parseInt(IndicadorNIT);
					IndexedCollection IcOp = (IndexedCollection) getElementAt("IcListaNit");
					KeyedCollection List = (KeyedCollection)IcOp.getElementAt(indicadorN-1);
					List.getValueAt("ClienteAltamira");
					List.getValueAt("TipoDocumento");
					String numDoc=(String)List.getValueAt("NumeroDocumento");
					numDoc=numDoc.replaceFirst ("^0*", "");
					String digVeri=(String)List.getValueAt("DigitoVerificacion");
					String nomClient=(String)List.getValueAt("NombreCliente");
					List.getValueAt("Oficina");
					setValueAt("NitNombre", numDoc+"-"+digVeri+"    -"+nomClient); 
					
				}				
				//FIN NITS 1.2 CMC FX 20/08/2019		
			}else{
			  OMConsultaOper.setValueAt("entrada.NIT","");
			}
			//INI-VARIOS NIT F2 - OSNEIDER A. CMC - 02-10-2019
			Trace.trace(Trace.Information, getClass().getName(), "girosHacia() - Se limpia variable de entrada entrada.NIT_COMPLETO");
			OMConsultaOper.setValueAt("entrada.NIT_COMPLETO",""); 
			//FIN-VARIOS NIT F2 - OSNEIDER A. CMC - 02-10-2019
			setValueAt("selectNit", "");
			// FIN - FILTRO NITS/COMERCIO EXTERIOR - OSNEIDER ACEVEDO - CMC - 12-07-2019 -->
			//INI NITS 1.2 CMC FX 20/08/2019
			String indNITPrinHaciaV=(String)getValueAt("indNITPrinHacia");
			if(null!=indNITPrinHaciaV && indNITPrinHaciaV.equalsIgnoreCase("S")) {
				setValueAt("NitNombre", ""); 
			}
			//FIN NITS 1.2 CMC FX 20/08/2019
			try{
				ejecutarOM(OMConsultaOper);
			}catch(Exception e1){
				Trace.trace(Trace.Debug, "", "### Falla en girosHacia() *** Lanzando la transaccion");
			}
			copiarDeOMGiros();
			Trace.trace(Trace.Debug, "", "### girosHacia() *** Paginar T");
			paginar(1);
			
			validarHora();
			
			setEstado("1");
			Trace.trace(Trace.Debug, "", " Fin girosHacia()");
			
			// INI - FILTRO NITS/COMERCIO EXTERIOR - OSNEIDER ACEVEDO - CMC - 26-06-2019 -->
			try {
				ConsultaNit(); 			
			} catch (Exception e) {
				Trace.trace(Trace.Error, "", " Inicio girosDesde(). Error ConsultaNit()");
			}
			// FIN - FILTRO NITS/COMERCIO EXTERIOR - OSNEIDER ACEVEDO - CMC - 26-06-2019 -->
			//INI-VARIOS NIT F2 - OSNEIDER A. CMC - 02-10-2019
			Trace.trace(Trace.Information, getClass().getName(), "OMAN - girosHacia() - Se limpia variable Fijo_Nit y Fijo_Nombre");
			setValueAt("Fijo_Nit","");
            setValueAt("Fijo_Nombre","");
            setValueAt("num_cuenta","");
            //FIN-VARIOS NIT F2 - OSNEIDER A. CMC - 02-10-2019
			
		} catch (Exception e) {
			setEstado(ERROR);		
			Trace.trace(Trace.Debug, "", "### ERROR - - - Fin girosHacia()" + e.getMessage());
			e.printStackTrace();
//INI CMC - FUNCION DE NEGOCIO - PRUEBA - 19/09/2019
		} finally {
			controlOM = null;
		}
//FIN CMC - FUNCION DE NEGOCIO - PRUEBA - 19/09/2019
	}
	
	public void girosDesde() throws Exception{
		try{
			setValueAt("Nit_Mod", "");//Se inicializa el NIT - NITS CMC 11-07-2019
			setValueAt("indiList","NN");
			cargarCuentas();//Incidencia 125 FX CMC 11/01/2019		
			setEstado("6");
			Trace.trace(Trace.Debug, "", " Fin girosDesde()");
			// INI- LISTA DE NIT/COMERCIO EXTERIOR - OSNEIDER ACEVEDO - CMC - 14-05-2019	
			try {
					
				ConsultaNit(); 
							
			} catch (Exception e) {
				Trace.trace(Trace.Error, "", " Inicio girosDesde(). Error ConsultaNit()");
			}
			//FIN- LISTA DE NIT/COMERCIO EXTERIOR - OSNEIDER ACEVEDO - CMC - 14-05-2019 
			//INI - VARIOS NIT F2 - OSNEIDER A. CMC - 02-10-2019
			Trace.trace(Trace.Information, getClass().getName(), "OMAN-girosHacia() - Se limpia variable Fijo_Nit y Fijo_Nombre");
			setValueAt("Fijo_Nit","");
            setValueAt("Fijo_Nombre","");
           //FIN - VARIOS NIT F2 - OSNEIDER A. CMC - 02-10-2019
		} catch (Exception e) {
			setEstado(ERROR);	
			Trace.trace(Trace.Debug, "", "### ERROR - - - Fin girosDesde()" + e.getMessage());
			e.printStackTrace();
		}

	}
	public void filtrarGirosDesde() throws Exception{
		KeyedCollection kEntradaParametria;
		/*INI INC 125 CMC 11-01-2019
		IndexedCollection icContextoSesion,listaCuentasOP = null;
		Enumeration enContextoSesion1;
		FIN INC 125 CMC 11-01-2019 */
				
		String clave = "";
		
		Trace.trace(Trace.Debug, "", " Inicio filtrarGirosDesde().");
		try {
			/*INI INC 125 CMC 11-01-2019
			icContextoSesion = (IndexedCollection) getElementAt("s_salida_cuentas.s_lista_cuentas");
			listaCuentasOP = (IndexedCollection) getElementAt("lista_cuentas");
			copiarIndexedCollection(icContextoSesion, listaCuentasOP);
			enContextoSesion1 = listaCuentasOP.getEnumeration();
			Trace.trace(Trace.Debug, "", "### filtrarGirosDesde() - copiar icolls .. " );
			FIN INC 125 CMC 11-01-2019 */
			cargarCuentas();
			
			clave=(String) getValueAt("selectCuentaOrden");
			
			String tipo="H";
			
			kEntradaParametria = (KeyedCollection) getElementAt(sOmParametria+"-data.entrada");
			kEntradaParametria.setValueAt("NCUENTA",  clave);
			OperacionMulticanal OmParametria = creaOM(sOmParametria);
//INI CMC - FUNCION DE NEGOCIO FNDO - PRUEBA - 20/09/2019
			controlOM = new OpControl();
			controlOM.setConfig((String)getValueAt("datosAPP.pb_cod_serv"),
								(String)getValueAt("datosAPP.iv-cod_emp")+(String)getValueAt("datosAPP.iv-cod_usu"),
								(String)getValueAt("datosAPP.iv-cod_usu"),
								creaOM("sign_on_om"), 
								creaOM("sign_on_om"),
								OmParametria, 
								creaOM(OmParametria.getName()));
			controlOM.control_f100();
//FIN CMC - FUNCION DE NEGOCIO FNDO - PRUEBA - 20/09/2019
			copiarDeOMParametria();
			Trace.trace(Trace.Debug, "", "### filtrarGirosDesde() - Parametria cargada - monedas");
			
			setValueAt("tipoOperacion", tipo);
			setValueAt("selectCuentaOrden", clave);
			setValueAt("cotizacion.tasaDivisa", new Double("0"));
			setValueAt("cotizacion.tasaDivisaPeso", new Double("0"));
			setValueAt("cotizacion.tasaUsdPeso", new Double("0"));
			setValueAt("cotizacion.equivPesos", new Double("0"));
			Trace.trace(Trace.Debug, "", "### filtrarGirosDesde() - Parametria inicializada ");
			setValueAt("selectMoneda", "");
			setValueAt("pestana", "1");
			setValueAt("pagina", "1");
			
			copiarAOMGiros(1);// INC 185 PAGINACION FNGE CMC 19-06-2019
			Trace.trace(Trace.Debug, "", "### filtrarGirosDesde() --- OM sOmGiros cargada");
			OperacionMulticanal OMConsultaOper = creaOM(sOmGiros);
			try{
				ejecutarOM(OMConsultaOper);
			}catch(Exception e1){
				Trace.trace(Trace.Debug, "", "### Falla en filtrarGirosDesde() *** Lanzando la transaccion");
			}
			copiarDeOMGiros();
			paginar(1);
			
			setEstado("2");
			
			Trace.trace(Trace.Debug, "", " Fin filtrarGirosDesde()");
			
		} catch (Exception e) {
			setEstado(ERROR);	
			Trace.trace(Trace.Debug, "", "### ERROR - Fin filtrarGirosDesde()" + e.getMessage());
			e.printStackTrace();
//INI CMC - FUNCION DE NEGOCIO - PRUEBA - 19/09/2019
		} finally {
			controlOM = null;
		}
//FIN CMC - FUNCION DE NEGOCIO - PRUEBA - 19/09/2019

	}
	
	public void negociarLinea() {
		KeyedCollection kCuenta,kEntradaParametria;
		IndexedCollection icContextoSesion,listaCuentasOP = null;
		Enumeration enContextoSesion1;
				
		String clave = "",ctaBenef="", selectMoneda="";
		
		 
		Trace.trace(Trace.Debug, "", " Inicio negociarLinea()");
		try {
			setValueAt("pagina_actual", "");//INC 102 FX 06/12/2018
			setValueAt("indiList", "NN");
			//INI incidencia 125 FX CMC 11/01/2019
			clave=cargarCuentas();
			/*icContextoSesion = (IndexedCollection) getElementAt("s_salida_cuentas.s_lista_cuentas");
			listaCuentasOP = (IndexedCollection) getElementAt("lista_cuentas");
			copiarIndexedCollection(icContextoSesion, listaCuentasOP);
			enContextoSesion1 = listaCuentasOP.getEnumeration();
			Trace.trace(Trace.Debug, "", "### negociarLinea() - copiar icolls " );*/
			//FIN incidencia 125 FX CMC 11/01/2019
			//INI INC 202 FX CMC 20/03/2019
			String descripHLinea = (String)getValueAt("descripNegociacion");
			String tipoFondoGiro = "";
			setValueAt("validaDes","");
			setValueAt("errorBeneficiario","");
			try{
				if(!validaDescrip(descripHLinea)) {
					setValueAt("validaDes","VD");
				}
			}
			catch (Exception e) {
				Trace.trace(Trace.Debug, "", "### negociarLinea() - descripcion de la operacion no valida" + e);
			}
			//FIN INC 202 FX CMC 20/03/2019
			
			if (getValueAt("selectCuentaOrden")!=null && getValueAt("selectCuentaOrden")!=""){
				Trace.trace(Trace.Debug, "", "### negociarLinea() - Cotizador lanzado ");
				validaLista();
				cotizador();
				tipoFondoGiro = (String) getValueAt(TIPO_FONDO_GIRO);
				List<UsuariosPseDto> autorizaciones = new ArrayList<UsuariosPseDto>();
				autorizaciones=listUserOrden();
				validacionRangoAutorizado(tipoFondoGiro);
				setValueAt("cMonto",getValueAt("monto"));
				selectMoneda = (String) getValueAt("selectMoneda");
				if(selectMoneda!= null && selectMoneda!= ""){
					selectMoneda= selectMoneda.substring(0,3);
					}
				setValueAt("selectMoneda", selectMoneda);
				//INI INC 213 FX CMC 06/05/2019
				validaMonto();
				//setValueAt("monto", getValueAt("monto"));
				//FIN INC 213 FX CMC 06/05/2019
				setValueAt("descripNegociacion", getValueAt("descripNegociacion"));
				Trace.trace(Trace.Debug, "", "### negociarLinea() - Cotizacion cargada ");
				
				try{
					ctaBenef =(String)getValueAt("selectCuentaOrden");
					validarCuenta();
				}catch(Exception e){
					Trace.trace(Trace.Debug, "", "### negociarLinea() - falla en la validacion de la cuenta");
					setValueAt("validaCta","N");
					setValueAt("msjError", "falla en la validacion de la cuenta");//INC 6 FX 11/05/2018
				}
				
				
			}else{
				Trace.trace(Trace.Debug, "", "negociarLinea() - Inicializar cotizador ");
				//Incidencia 213 FX CMC 22/05/2019
				setValueAt("montoBandera", "primera");
				//Incidencia 213 FX CMC 22/05/2019
				
				//INI incidencia 125 FX CMC 11/01/2019
				
				/*while(enContextoSesion1.hasMoreElements()){
					kCuenta = (KeyedCollection) enContextoSesion1.nextElement();
					clave = (String) kCuenta.getValueAt("clave_asunto");
				}*/				
				//FIN incidencia 125 FX CMC 11/01/2019
				setValueAt("indBenef", "");
				String tipo=(String)getValueAt("tipoOperacion");
						
				kEntradaParametria = (KeyedCollection) getElementAt(sOmParametria+"-data.entrada");
				kEntradaParametria.setValueAt("NCUENTA",  clave);
				OperacionMulticanal OmParametria = creaOM(sOmParametria);
//INI CMC - FUNCION DE NEGOCIO FNDO - PRUEBA - 20/09/2019
				controlOM = new OpControl();
				controlOM.setConfig((String)getValueAt("datosAPP.pb_cod_serv"),
									(String)getValueAt("datosAPP.iv-cod_emp")+(String)getValueAt("datosAPP.iv-cod_usu"),
									(String)getValueAt("datosAPP.iv-cod_usu"),
									creaOM("sign_on_om"), 
									creaOM("sign_on_om"),
									OmParametria, 
									creaOM(OmParametria.getName()));
				controlOM.control_f100();
//FIN CMC - FUNCION DE NEGOCIO FNDO - PRUEBA - 20/09/2019
				copiarDeOMParametria();
				Trace.trace(Trace.Debug, "", "### negociarLinea() - Parametria cargada - monedas");
			
				setValueAt("tipoOperacion", tipo);
				setValueAt("selectCuentaOrden", " ");
				setValueAt("cotizacion.tasaDivisa", new Double("0"));
				setValueAt("cotizacion.tasaDivisaPeso", new Double("0"));
				setValueAt("cotizacion.tasaUsdPeso", new Double("0"));
				setValueAt("cotizacion.equivPesos", new Double("0"));
			
				setValueAt("selectMoneda", "");
				setValueAt("monto", "");
				setValueAt("descripNegociacion", "");
				setValueAt("validaCta","");
				setValueAt("msjError", "");
				setValueAt("descErrorMonto", ""); // INC 214 FX - CMC 13/06/2019  
				actualizaEstadoFondo(TIPO_FONDO_GIRO);				
				Trace.trace(Trace.Debug, "", "### negociarLinea() - Parametria y cotizador inicializados ");
			}
			validateOpenModal();
			validarAutorizacion(listUserOrden());
			String businessId = callFormatNit(validateElement(VALUE_BUSINESS_ID));
			getCommission(businessId, getValueAt("businessOp"), getValueAt(AMOUNT_EQUIV),
					getValueAt(NUM_TRANS), getValueAt("selectCuentaOrden"), "");
			setEstado("7");
			
			//INI- LISTA DE NIT/COMERCIO EXTERIOR - OSNEIDER ACEVEDO - CMC - 14-05-2019
	
			
			ConsultaNit(); 
			if(getValueAt("ClienteCons")!=null) {
				String[] obj=getValueAt("ClienteCons").toString().split("-");
				if(obj.length>1){
					String NumCliente=obj[0];
					String documento=obj[1];
					setValueAt("Num_Nit", documento);
					ConsultarCuentasPorNit2(NumCliente);	
					setValueAt("s_ClienteCons", NumCliente);
					
				}
				setValueAt("selectOpe", "T1");
				
			}else {
				setValueAt("s_ClienteCons", "NA");
				inicializacionCuentas();
			}
			
			//FIN- LISTA DE NIT/COMERCIO EXTERIOR - OSNEIDER ACEVEDO - CMC - 14-05-2019
		} catch (Exception e) {
			setEstado(ERROR);
			Trace.trace(Trace.Debug, "", "### ERROR - Fin negociarLinea() -- " + e);
//INI CMC - FUNCION DE NEGOCIO - PRUEBA - 19/09/2019
		} finally {
			controlOM = null;
		}
//FIN CMC - FUNCION DE NEGOCIO - PRUEBA - 19/09/2019

	}

	// INI- LISTA DE NIT/COMERCIO EXTERIOR - OSNEIDER ACEVEDO - CMC - 11-07-2019
	public void ConsultaNit() throws Exception {
		// INI- LISTA DE NIT/COMERCIO EXTERIOR - OSNEIDER ACEVEDO - CMC - 22-07-2019
		try {
			IndexedCollection IcOm, IcOp = null;
			OperacionMulticanal OmNit = creaOM(sOmNit);
			OmNit.setValueAt("Entrada.E_Referencia",(String)getValueAt("s_cod_logon") );
			OmNit.setValueAt("Entrada.E_Usuario", (String)getValueAt("s_cod_usuarisc"));
			OmNit.setValueAt("Entrada.E_TipoDocumento", "3");
			OmNit.setValueAt("Entrada.E_Pagina","1" );	
			OmNit.setValueAt("Entrada.E_NumRegistros", "20");
			Trace.trace(Trace.Information, getClass().getName(), "#CONSULTA DE NIT: consulta_nit_om");
			Trace.trace(Trace.Information, getClass().getName(),
					"#ENTRADA: " + (KeyedCollection) OmNit.getElementAt("Entrada"));
			
			IcOp = (IndexedCollection) getElementAt("IcListaNit");
			IcOp.removeAll();
			//System.out.println((KeyedCollection) OmNit.getElementAt("Salida"));
			int primera_Pagina=1;
			int cant_nit=0;
			while(primera_Pagina==1 || OmNit.getValueAt("Salida.S_ListarMas").toString().trim().equalsIgnoreCase("S")) {  
				    if(primera_Pagina!=1) { 
				    	OmNit = creaOM(sOmNit);	
				    	OmNit.setValueAt("Entrada.E_Pagina",Integer.toString(primera_Pagina) );	
				    }
				    primera_Pagina++;			   
				ejecutarOMControl(OmNit,sOmNit);//INI PERDIDA SESION HOST 10-12-2019
				    IcOm = (IndexedCollection) OmNit.getElementAt("Salida.S_IcListaNit");
					if (OmNit.getValueAt("Salida.S_CodigoError").toString().trim().equalsIgnoreCase("")) {
						
						int lista=IcOm.size();
						if(lista>0) {
							for (int i =0; i<lista;  i++) {
								
								KeyedCollection kNit = (KeyedCollection) IcOm.getElementAt(i);
								KeyedCollection List = (KeyedCollection) DataElement.getExternalizer().convertTagToObject(IcOp.getElementSubTag());
								
								if(kNit.getValueAt("S_ClienteAltamira")==null && lista==1) {
									inicializacionNit();// lista vacia para evitar errores.
								}else {
									cant_nit++;
									List.setValueAt("ClienteAltamira", kNit.getValueAt("S_ClienteAltamira"));
									List.setValueAt("TipoDocumento", kNit.getValueAt("S_TipoDocumento"));
									List.setValueAt("NumeroDocumento", kNit.getValueAt("S_NumeroDocumento"));
									List.setValueAt("DigitoVerificacion", kNit.getValueAt("S_DigitoVerificacion"));
									List.setValueAt("NombreCliente", kNit.getValueAt("S_NombreCliente"));
									List.setValueAt("Oficina", kNit.getValueAt("S_Oficina"));
									
									IcOp.addElement(List);
									
								}
							}
						}else {
							inicializacionNit();// lista vacia para evitar errores.
						}
						
						
					} else {
						inicializacionNit();// lista vacia para evitar errores.
						Trace.trace(Trace.Error, getClass().getName(), "#ERROR AL EJECUTAR LA FUNCION DE NEGOCIO TxFNINPNET");
						Trace.trace(Trace.Error, getClass().getName(),
								"#CODIGO DE ERROR: " + (String) OmNit.getValueAt("Salida.S_CodigoError"));
						Trace.trace(Trace.Error, getClass().getName(),
								"#DESCRIPCION DE ERROR 1: " + (String) OmNit.getValueAt("Salida.S_DescripcionErr1"));
						Trace.trace(Trace.Error, getClass().getName(),
								"#DESCRIPCION DE ERROR 2: " + (String) OmNit.getValueAt("Salida.S_DescripcionErr2"));
					}
					OmNit.close();
			}
			Trace.trace(Trace.Information, getClass().getName(), "#CANTIDAD DE EJECUCIONES FNNI:"+(primera_Pagina-1));
			Trace.trace(Trace.Information, getClass().getName(), "#CANTIDAD DE NIT:"+cant_nit);

		} catch (Exception e) {
			Trace.trace(Trace.Error, getClass().getName(), "#ERROR CONSULTA DE NIT: " + e);
			inicializacionNit();// lista vacia para evitar errores.
		}
		// INI- LISTA DE NIT/COMERCIO EXTERIOR - OSNEIDER ACEVEDO - CMC - 22-07-2019
	}

	public String validaLista(){
		String indic ="";
		try {
			Trace.trace(Trace.Debug, "", " Inicio validaLista(): ");
			String[] Num_Nit = getValueAt("Num_Nit").toString().split("@");
			String s_ClienteCons = (String) getValueAt("s_ClienteCons");
			String numIdent = Num_Nit[0].substring(1, Num_Nit[0].length()-1);
			String digVerif = Num_Nit[0].substring(Num_Nit[0].length()-1,Num_Nit[0].length());
			String tipoDoc = Num_Nit[0].substring(0, 1);
			String flagNit[] = transgresionNit(numIdent, s_ClienteCons, digVerif, tipoDoc);
			
			if(flagNit[0].equalsIgnoreCase("OK")) {
				om = creaOM("validacion_baep_om");
				om.setValueAt("Entrada.COD_IDEN", tipoDoc);
				om.setValueAt("Entrada.NUM_IDEN", flagNit[1]);
				om.setValueAt("Entrada.DIG_IDEN", digVerif);
				ejecutarOM(om);

				setValueAt("indiList", om.getValueAt("Salida.IND_LIST_BAEP") != null ?
						(om.getValueAt("Salida.IND_LIST_BAEP")).toString() : "");
				setValueAt("codError", om.getValueAt("Salida.COD_RROR") != null ?
						(om.getValueAt("Salida.COD_RROR")).toString() : "");
				setValueAt("valError", om.getValueAt("Salida.VALOR_ERROR") != null ?
						(om.getValueAt("Salida.VALOR_ERROR")).toString() : "");
				indic = om.getValueAt("Salida.IND_LIST_BAEP") != null ?
						om.getValueAt("Salida.IND_LIST_BAEP").toString() : "";
			}else {
				Trace.trace(Trace.Error, "", " validaLista(): " + " El nit a consultar no esta asociado a la referencia");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Trace.trace(Trace.Error, "", " validaLista(): " + "Error al validar lista baep debido a: " + e.getMessage());
		}
		Trace.trace(Trace.Debug, "", " fin validaLista(): ");
		return indic;	
	}
	
	public void ConsultarCuentasPorNit() throws DSEInvalidArgumentException, IOException {
	
		try {
			setValueAt("Nit_Mod", "");//VARIOS NIT F2 - OSNEIDER A. CMC - 02-10-2019
			String datos=(String)getValueAt("ClienteCons");
			String[] num_client_nit=datos.split("@");
			String nitMod=(String)getValueAt("Nit_Mod");
			if(null!=nitMod && nitMod.trim().length()==0){
				setValueAt("Nit_Mod", (String)getValueAt("Nit_Completo"));
			}			
			if(num_client_nit.length==2) {
				setValueAt("Nit_Solo", num_client_nit[1]);
				setValueAt("NumCliente", num_client_nit[0]);
			}else {
				setValueAt("NumCliente", (String)getValueAt("ClienteCons"));
			}
			
			
			IndexedCollection IcOm, IcOp = null;
			KeyedCollection List,kCuentas;
			OperacionMulticanal OmCuenta = creaOM(sOmCuenta);
			OmCuenta.setValueAt("Entrada.E_Referencia",(String)getValueAt("s_cod_logon") );
			OmCuenta.setValueAt("Entrada.E_Usuario", (String)getValueAt("s_cod_usuarisc"));
			OmCuenta.setValueAt("Entrada.E_TipoDocumento", "");
			OmCuenta.setValueAt("Entrada.E_NumeroDocumento", "");
			OmCuenta.setValueAt("Entrada.E_DigitoVerificacion", "");
			OmCuenta.setValueAt("Entrada.E_ClienteCons", (String)getValueAt("ClienteCons"));
			OmCuenta.setValueAt("Entrada.E_Servicio", "3523");
			OmCuenta.setValueAt("Entrada.E_Pagina", "");
			OmCuenta.setValueAt("Entrada.E_NumRegistros", "");
			OmCuenta.setValueAt(ENTRADA_TIPO_OPE, ABONO);


			Trace.trace(Trace.Information, getClass().getName(), "#CONSULTA DE CUENTAS: consulta_nit_om");
			Trace.trace(Trace.Information, getClass().getName(),
					"#ENTRADA: " + (KeyedCollection) OmCuenta.getElementAt("Entrada"));
            ejecutarOMControl(OmCuenta,sOmCuenta);//INI PERDIDA SESION HOST 10-12-2019
			if (OmCuenta.getValueAt("Salida.S_CodigoError") == null ) {
				IcOm = (IndexedCollection) OmCuenta.getElementAt("Salida.S_IcListaCuenta");
				IcOp = (IndexedCollection) getElementAt("IcListaCuenta");
				
				IcOp.removeAll();
				int hay=0;
				int lista=IcOm.size();
				if(lista>0) {
					for (int i =0; i<lista;  i++) {
						kCuentas = (KeyedCollection) IcOm.getElementAt(i);
						List = (KeyedCollection) DataElement.getExternalizer().convertTagToObject(IcOp.getElementSubTag());
						if(kCuentas.getValueAt("S_Cuenta")!=null && !kCuentas.getValueAt("S_Cuenta").toString().trim().equals("")) {
							List.setValueAt("Cuenta", (String)kCuentas.getValueAt("S_Cuenta"));
//INI INC CMC OFICINA GESTORA 27/09/2019
							if (((String)kCuentas.getValueAt("SS0_CENTROC"))!=null && ((String)kCuentas.getValueAt("SS0_CENTROC")).length()==4) {
								List.setValueAt("oficinaGestora", (String)kCuentas.getValueAt("SS0_CENTROC"));
								Trace.trace(Trace.Information, getClass().getName(), "####### OK PRUEBA Oficina Gestora : "+ (String)kCuentas.getValueAt("SS0_CENTROC"));	
							} else {
								List.setValueAt("oficinaGestora", "0073");
								Trace.trace(Trace.Information, getClass().getName(), "####### KO PRUEBA Oficina Gestora : " + (String)kCuentas.getValueAt("SS0_CENTROC") + " ####### OFICINA POR DEFECTO ####### ");
							}
//FIN INC CMC OFICINA GESTORA 27/09/2019
							IcOp.addElement(List);	
							hay++;
					     }
					}
					if(hay==0) {
						inicializacionCuentas();// lista vacia para evitar errores.
					}
					
				}else {
					inicializacionCuentas();// lista vacia para evitar errores.
				}
				
			} else {
				
				inicializacionCuentas();// lista vacia para evitar errores.
				Trace.trace(Trace.Error, getClass().getName(), "#ERROR AL EJECUTAR LA FUNCION DE NEGOCIO TxFNINPNET");
				Trace.trace(Trace.Error, getClass().getName(),
						"#CODIGO DE ERROR: " + (String) OmCuenta.getValueAt("Salida.S_CodigoError"));
				Trace.trace(Trace.Error, getClass().getName(),
						"#DESCRIPCION DE ERROR 1: " + (String) OmCuenta.getValueAt("Salida.S_DescripcionErr1"));
				Trace.trace(Trace.Error, getClass().getName(),
						"#DESCRIPCION DE ERROR 2: " + (String) OmCuenta.getValueAt("Salida.S_DescripcionErr2"));
			}
			setEstado("11");

		} catch (Exception e) {
			
			Trace.trace(Trace.Error, getClass().getName(), "#ERROR CONSULTA DE CUENTAS: " + e);
			inicializacionCuentas();// lista vacia para evitar errores.
		}
		
	}
	public void ConsultarCuentasPorNit2(String ncli) throws DSEInvalidArgumentException, IOException {

		try {
			IndexedCollection IcOm, IcOp = null;
			KeyedCollection List,kCuentas;
			OperacionMulticanal OmCuenta = creaOM(sOmCuenta);
			OmCuenta.setValueAt("Entrada.E_Referencia",(String)getValueAt("s_cod_logon") );
			OmCuenta.setValueAt("Entrada.E_Usuario", (String)getValueAt("s_cod_usuarisc"));
			
			OmCuenta.setValueAt("Entrada.E_TipoDocumento", "");
			OmCuenta.setValueAt("Entrada.E_NumeroDocumento", "");
			OmCuenta.setValueAt("Entrada.E_DigitoVerificacion", "");
			OmCuenta.setValueAt("Entrada.E_ClienteCons", ncli);
			OmCuenta.setValueAt("Entrada.E_Servicio", "3523");
			OmCuenta.setValueAt("Entrada.E_Pagina", "");
			OmCuenta.setValueAt("Entrada.E_NumRegistros", "");
			if(getValueAt(TIPO_FONDO_GIRO) != null && getValueAt(TIPO_FONDO_GIRO).toString().equalsIgnoreCase(FONDO_PSE))
				OmCuenta.setValueAt(ENTRADA_TIPO_OPE, ABONO);

			Trace.trace(Trace.Information, getClass().getName(), "#CONSULTA DE CUENTAS: consulta_nit_om");
			Trace.trace(Trace.Information, getClass().getName(),
					"#ENTRADA: " + (KeyedCollection) OmCuenta.getElementAt("Entrada"));
            ejecutarOMControl(OmCuenta,sOmCuenta);//INI PERDIDA SESION HOST 10-12-2019
			if (OmCuenta.getValueAt("Salida.S_CodigoError") == null ) {
				IcOm = (IndexedCollection) OmCuenta.getElementAt("Salida.S_IcListaCuenta");
				IcOp = (IndexedCollection) getElementAt("IcListaCuenta");
				
				IcOp.removeAll();
				int lista=IcOm.size();
				int hay=0;
				if(lista>0) {
					for (int i =0; i<lista;  i++) {
						kCuentas = (KeyedCollection) IcOm.getElementAt(i);
						List = (KeyedCollection) DataElement.getExternalizer().convertTagToObject(IcOp.getElementSubTag());
						if(kCuentas.getValueAt("S_Cuenta")!=null && !kCuentas.getValueAt("S_Cuenta").toString().trim().equals("")) {
							List.setValueAt("Cuenta", (String)kCuentas.getValueAt("S_Cuenta"));
//INI INC CMC OFICINA GESTORA 27/09/2019
							if (((String)kCuentas.getValueAt("SS0_CENTROC"))!=null && ((String)kCuentas.getValueAt("SS0_CENTROC")).length()==4) {
								List.setValueAt("oficinaGestora", (String)kCuentas.getValueAt("SS0_CENTROC"));
								Trace.trace(Trace.Information, getClass().getName(), "####### OK2 Oficina Gestora: "+ (String)kCuentas.getValueAt("SS0_CENTROC"));	
							} else {
								List.setValueAt("oficinaGestora", "0073");
								Trace.trace(Trace.Information, getClass().getName(), "####### KO2 Oficina Gestora:: " + (String)kCuentas.getValueAt("SS0_CENTROC") + " ####### OFICINA POR DEFECTO ####### ");
							}
//FIN INC CMC OFICINA GESTORA 27/09/2019
							IcOp.addElement(List);	
							hay++;
					     }
					}
					if(hay==0) {
						inicializacionCuentas();// lista vacia para evitar errores.
					}
				}else {
					inicializacionCuentas();// lista vacia para evitar errores.
				}
				
			} else {
				
				inicializacionCuentas();// lista vacia para evitar errores.
				Trace.trace(Trace.Error, getClass().getName(), "#ERROR AL EJECUTAR LA FUNCION DE NEGOCIO TxFNINPNET");
				Trace.trace(Trace.Error, getClass().getName(),
						"#CODIGO DE ERROR: " + (String) OmCuenta.getValueAt("Salida.S_CodigoError"));
				Trace.trace(Trace.Error, getClass().getName(),
						"#DESCRIPCION DE ERROR 1: " + (String) OmCuenta.getValueAt("Salida.S_DescripcionErr1"));
				Trace.trace(Trace.Error, getClass().getName(),
						"#DESCRIPCION DE ERROR 2: " + (String) OmCuenta.getValueAt("Salida.S_DescripcionErr2"));
			}
			

		} catch (Exception e) {
			
			Trace.trace(Trace.Error, getClass().getName(), "#ERROR CONSULTA DE CUENTAS: " + e);
			inicializacionCuentas();// lista vacia para evitar errores.
		}
	}
	public void inicializacionCuentas() throws IOException, DSEInvalidArgumentException {
		IndexedCollection IcOp;
		try {
			IcOp = (IndexedCollection) getElementAt("IcListaCuenta");
			IcOp.removeAll();
			KeyedCollection List = (KeyedCollection) DataElement.getExternalizer().convertTagToObject(IcOp.getElementSubTag());
			List.setValueAt("Cuenta", "");
			IcOp.addElement(List);
		} catch (DSEObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void inicializacionNit() throws IOException, DSEInvalidArgumentException {
		IndexedCollection IcOp;
		try {
			IcOp = (IndexedCollection) getElementAt("IcListaNit");
			int tam=IcOp.size();// LISTA DE NIT/COMERCIO EXTERIOR - OSNEIDER ACEVEDO - CMC - 22-07-2019
			if(tam==0) {// LISTA DE NIT/COMERCIO EXTERIOR - OSNEIDER ACEVEDO - CMC - 22-07-2019
			IcOp.removeAll();
			KeyedCollection List = (KeyedCollection) DataElement.getExternalizer().convertTagToObject(IcOp.getElementSubTag());
			List.setValueAt("ClienteAltamira", "");
			List.setValueAt("TipoDocumento", "");
			List.setValueAt("NumeroDocumento", "");
			List.setValueAt("DigitoVerificacion", "");
			List.setValueAt("NombreCliente", "");
			List.setValueAt("Oficina", "");
			IcOp.addElement(List);
			}// LISTA DE NIT/COMERCIO EXTERIOR - OSNEIDER ACEVEDO - CMC - 22-07-2019
		} catch (DSEObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	// FIN- LISTA DE NIT/COMERCIO EXTERIOR - OSNEIDER ACEVEDO - CMC - 11-07-2019
	public void negociarMesaDinero() throws DSEObjectNotFoundException { //VARIOS NIT F2 - OSNEIDER A. CMC - 16-09-2019
		Trace.trace(Trace.Debug, "", " Inicio negociarMesaDinero()");
		//INI-VARIOS NIT F2 - OSNEIDER A. CMC - 16-09-2019
		    try {
		    	actualizaEstadoFondo(TIPO_FONDO_GIRO);		    	
				setValueAt("S_SALIDA_NIT", "");
				setValueAt("S_NOMBRE_CLIENTE", "");
				setValueAt("S_DIVISA_COP", "");
				setValueAt("S_DIVISA_DOLAR", "");
				setValueAt("S_DIVISA_PESO", "");
				setValueAt("S_DIVISA_MONEDA", "");
				setValueAt("S_MONTO", "");
				setValueAt("indiList", "NN");
			} catch (DSEInvalidArgumentException e1) {
				Trace.trace(Trace.Error, "", "### negociarMesaDinero() - error: "+e1);
			}
			
		if(getValueAt("ValidaDisplay")!=null && getValueAt("ValidaDisplay").toString().trim().equalsIgnoreCase("Primero")) {
			try {
				consultarAvance2();
				List<UsuariosPseDto> autorizaciones = new ArrayList<UsuariosPseDto>();
				autorizaciones=listUserOrden();
				String infopesos=String.valueOf(getValueAt(EQUIV_PESO_2));
				validaAutorizaciones(infopesos);
			} catch (Exception e) {
				Trace.trace(Trace.Error, "", "### negociarMesaDinero() - error al ejecutar :consultarAvance2() "+e);
			}
		}else { //FIN-VARIOS NIT F2 - OSNEIDER A. CMC - 16-09-2019
		KeyedCollection kCuenta,kEntradaParametria;
		IndexedCollection icContextoSesion,listaCuentasOP = null;
		Enumeration enContextoSesion1;
				
		String clave = "",ctaBenef="",indicador="",selectMoneda="";
		
		
		try {
				//INI-VARIOS NIT F2 - OSNEIDER A. CMC - 16-09-2019
				if(getValueAt("ValidaDisplay")==null) {
					setValueAt("S_ValidaDisplay", "Primero");
				}else if(getValueAt("ValidaDisplay").toString().trim().equalsIgnoreCase("N")){
					setValueAt("S_ValidaDisplay", "Segundo");
				}
				//FIN-VARIOS NIT F2 - OSNEIDER A. CMC - 16-09-2019
			Trace.trace(Trace.Debug, "", "### negociarMesaDinero() - avanceOpe: " + getValueAt("avanceOpe"));
			Trace.trace(Trace.Debug, "", "### negociarMesaDinero() - selectCuentaOrden: " + getValueAt("selectCuentaOrden"));
			Trace.trace(Trace.Debug, "", "### negociarMesaDinero() - selectMoneda: " + getValueAt("selectMoneda"));
			Trace.trace(Trace.Debug, "", "### negociarMesaDinero() - descripNegociacion: " + getValueAt("descripNegociacion"));
			
			setValueAt("pagina_actual", "");//INC 102 FX 06/12/2018
			//INI incidencia 125 FX CMC 11/01/2019
			//clave=cargarCuentas();	//VARIOS NIT F2 - OSNEIDER A. CMC - 16-09-2019
			/*
			icContextoSesion = (IndexedCollection) getElementAt("s_salida_cuentas.s_lista_cuentas");
			listaCuentasOP = (IndexedCollection) getElementAt("lista_cuentas");
			copiarIndexedCollection(icContextoSesion, listaCuentasOP);
			enContextoSesion1 = listaCuentasOP.getEnumeration();
			Trace.trace(Trace.Debug, "", " Inicio negociarMesaDinero() - copiar icolls " );
			
				Trace.trace(Trace.Debug, "", "### negociarMesaDinero() - Inicializar variables ");
				while(enContextoSesion1.hasMoreElements())
				{
					kCuenta = (KeyedCollection) enContextoSesion1.nextElement();
					clave = (String) kCuenta.getValueAt("clave_asunto");
				}*/
			//FIN incidencia 125 FX CMC 11/01/2019
				
			//INI INC 202 FX CMC 20/03/2019
			String descripHMesa = (String)getValueAt("descripNegociacion");
			setValueAt("validaDes","");
			try{
				if(!validaDescrip(descripHMesa)) {  
					setValueAt("validaDes","VD");
				}
			}
			catch (Exception e) {
				Trace.trace(Trace.Debug, "", "### negociarMesaDinero() - descripcion de la operacion no valida" + e);
			}
			//FIN INC 202 FX CMC 20/03/2019
				
				setValueAt("indBenef", "");
				String tipo=(String)getValueAt("tipoOperacion");
				Trace.trace(Trace.Debug, "", "### negociarMesaDinero() - tipo: " + tipo);
				//	INI-VARIOS NIT F2 - OSNEIDER A. CMC - 16-09-2019
				//	kEntradaParametria = (KeyedCollection) getElementAt(sOmParametria+"-data.entrada");
				//	kEntradaParametria.setValueAt("NCUENTA",  clave);
				//	OperacionMulticanal OmParametria = creaOM(sOmParametria);
	            //INI CMC - FUNCION DE NEGOCIO FNDO - PRUEBA - 19/09/2019
				//	controlOM.setConfig((String)getValueAt("datosAPP.pb_cod_serv"),
				//						(String)getValueAt("datosAPP.iv-cod_emp")+(String)getValueAt("datosAPP.iv-cod_usu"),
				//						(String)getValueAt("datosAPP.iv-cod_usu"),
				//						creaOM("sign_on_om"), 
				//						creaOM("sign_on_om"),
				//						OmParametria, 
				//						creaOM(OmParametria.getName()));
				//	controlOM.control_f100();
	            //FIN CMC - FUNCION DE NEGOCIO FNDO - PRUEBA - 19/09/2019
				//	copiarDeOMParametria();
				//FIN-VARIOS NIT F2 - OSNEIDER A. CMC - 16-09-2019
				Trace.trace(Trace.Debug, "", "### negociarMesaDinero() - Parametria cargada - monedas");
				setValueAt("tipoOperacion", tipo);
				setValueAt("selectOpe","T2");
				
				
				if(getValueAt("avanceOpe")!=null && getValueAt("selectCuentaOrden")!=null){
					Trace.trace(Trace.Debug, "", "### negociarMesaDinero() - a consultar ");
					try{
						indicador =(String)getValueAt("avanceOpe");
						Trace.trace(Trace.Debug, "", "### negociarMesaDinero() - validar avance " + indicador);
							//INI-VARIOS NIT F2 - OSNEIDER A. CMC - 16-09-2019
							if(getValueAt("ValidaDisplay").toString().equalsIgnoreCase("S")) {
								
								String cuenta=getValueAt("selectCuentaOrden").toString().trim();
								String[] cuent_ofi=cuenta.split("@");
								setValueAt("selectCuentaOrden", cuent_ofi[0]);
								consultarAvance(); 
								String alerta=getValueAt("indAvance").toString().trim();
								setValueAt("S_ValidaDisplay", alerta.equals("N")?"Segundo":"S");
								String nit= getValueAt("SALIDA_NIT").toString().trim();
								setValueAt("S_SALIDA_NIT", nit);
		 		 				String tipoD=nit.substring(0,1);
		 						nit=nit.substring(2);
		 						String digitoVerifi=nit.substring(nit.length()-1);
		 						nit=nit.replace("-", "");
		 						String nitSolo=nit.substring(0,nit.length()-1);
								setValueAt("S_NOMBRE_CLIENTE", getValueAt(NOMBRE_CLIENTE).toString().trim());
								setValueAt("S_DIVISA_COP", getValueAt("DIVISA_COP").toString().trim());
								setValueAt("S_DIVISA_DOLAR", getValueAt("DIVISA_DOLAR").toString().trim());
								setValueAt("S_DIVISA_PESO", getValueAt("DIVISA_PESO").toString().trim());
								setValueAt("S_DIVISA_MONEDA", getValueAt("DIVISA_MONEDA").toString().trim());
								setValueAt("S_MONTO", getValueAt("E_MONTO").toString().trim());
								IndexedCollection Icuentas=ConsultarCuentasPorDocumento(tipoD, nitSolo,digitoVerifi,"","");
		 		 				CargarCuentasOp(Icuentas);
							}
							//FIN-VARIOS NIT F2 - OSNEIDER A. CMC - 16-09-2019	
					}catch(Exception e){
						Trace.trace(Trace.Debug, "", "### negociarMesaDinero() - falla en la validacion del avance");
						setValueAt("indAvance","N");
					}
					
					
					setValueAt("selectCuentaOrden", getValueAt("selectCuentaOrden"));
					selectMoneda = (String) getValueAt("selectMoneda");
					if(selectMoneda!= null && selectMoneda!= ""){
						selectMoneda= selectMoneda.substring(0,3);
						}
					setValueAt("selectMoneda", selectMoneda);
					setValueAt("avanceOpe", getValueAt("avanceOpe"));
					//INI INC 213 FX CMC 06/05/2019
					validaMonto();
					//setValueAt("monto", getValueAt("monto"));
					//FIN INC 213 FX CMC 06/05/2019
					setValueAt("descripNegociacion", getValueAt("descripNegociacion"));
					
					
					try{
						ctaBenef =(String)getValueAt("selectCuentaOrden");
						Trace.trace(Trace.Debug, "", "### negociarMesaDinero() - validar cuenta " + ctaBenef + " / monto: " + getValueAt("monto"));
						setValueAt("tasaDivisa", new Double("0"));
						setValueAt("tasaDivisaPeso", new Double("0"));
						setValueAt("tasaUsdPeso", new Double("0"));
						//INI INC 73 04-07-2018
						String numeroR=getValueAt("monto").toString();
				        numeroR= numeroR.replaceAll(",", "");
				        // INI INC 210.A CMC FX 8-04-2019
				        Double equivAvance = new Double(getValueAt(EQUIV_PESO_2).toString());
				        setValueAt(EQUIV_PESO, equivAvance);
				        Trace.trace(Trace.Debug, "", "*** negociarMesaDinero()- EquivPesos: " + equivAvance);
				        // FIN INC 210.A CMC FX 8-04-2019
						//setValueAt(EQUIV_PESO, new Double(getValueAt("monto").toString()));
						//FIN INC 73 04-07-2018
						validarCuenta();
						
					}catch(Exception e){
						Trace.trace(Trace.Debug, "", "### negociarMesaDinero() - falla en la validacion de la cuenta");
						setValueAt("validaCta","N");
						setValueAt("msjError", "falla en la validacion de la cuenta");
					}
										
				}else{
					Trace.trace(Trace.Debug, "", "### negociarMesaDinero() - Parametria y cotizador inicializados ");
					//Incidencia 213 FX CMC 23/05/2019
					setValueAt("montoBandera", "primera");
					//Incidencia 213 FX CMC 23/05/2019
					setValueAt("selectCuentaOrden", "");
					setValueAt("indAvance", "");
					setValueAt("selectMoneda", "");
					setValueAt("monto", "");
					setValueAt("descripNegociacion", "");
					setValueAt("validaCta","");
					setValueAt("avanceOpe","");
					setValueAt("msjError", "");
					
					setValueAt("tasaDivisa", new Double("0"));
					setValueAt("tasaDivisaPeso", new Double("0"));
					setValueAt("tasaUsdPeso", new Double("0"));
					setValueAt(EQUIV_PESO, new Double("0"));
					
					//ini 85
					setValueAt("tasaDivisaPeso2", new Double("0"));
					setValueAt(EQUIV_PESO_2, new Double("0"));
					//fin 85
				}
				setValueAt(EQUIV_PESO, new Double("0"));
				
				Trace.trace(Trace.Debug, "", "### negociarMesaDinero() - FIN");
			
			setEstado("10");
			List<UsuariosPseDto> autorizaciones = new ArrayList<UsuariosPseDto>();
			autorizaciones=listUserOrden();
			String infopesos=String.valueOf(getValueAt(EQUIV_PESO_2));
			validaAutorizaciones(infopesos);			
		}catch (Exception e) {
			setEstado(ERROR);
			Trace.trace(Trace.Debug, "", "### ERROR - Fin negociarMesaDinero() - " + e);
//INI CMC - FUNCION DE NEGOCIO - PRUEBA - 19/09/2019
		} finally {
			controlOM = null;
		}
//FIN CMC - FUNCION DE NEGOCIO - PRUEBA - 19/09/2019
		Trace.trace(Trace.Debug, "", " FIN negociarMesaDinero()");
		} //VARIOS NIT F2 - OSNEIDER A. CMC - 16-09-2019
	}
	
	public void selecBeneficiarios() throws Exception{
		Trace.trace(Trace.Debug, "", "### selecBeneficiarios() - INICIO " + getEstado());
//INI INC CMC OFICINA GESTORA 27/09/2019
		try {
			setValueAt("codigoSucursal", (String) getValueAt("oficinaGestoraOP"));
		} catch (Exception e) {
			Trace.trace(Trace.Error, getClass().getName(), "####### selecBeneficiarios() - ERROR ####### " + e);
		}
//FIN INC CMC OFICINA GESTORA 27/09/2019
		String indBenef="",selectMoneda="";
		//INI incidencia 225B FX CMC 17/09/2019
		setValueAt("errorSwift", getValueAt("errorCodigo"));
			if(getValueAt("errorSwift").equals("S")) {
				setValueAt("pagina_actual", "");
			}
		//FIN incidencia 225B FX CMC 17/09/2019
		if(getValueAt("indBenef")!=null){
			indBenef = (String)getValueAt("indBenef");
			Trace.trace(Trace.Debug, "", "### selecBeneficiarios() - indBenef " + indBenef);
		}
		
		if(indBenef.equals("A")){
			setValueAt("pagina_actual", ""); //INC 102 FX 14/11/2018
			altaBeneficiario();
		}else if (indBenef.equals("M")){
			setValueAt("pagina_actual", ""); //INC 102 FX 14/11/2018
			modificarBeneficiario();
		}
		
		//INI INC 76 AVANCE 0 FX 16-07-2018
		String resultadoAvance =(String) getValueAt("avanceOpe");
		Trace.trace(Trace.Debug, "", "### selecBeneficiarios() - resultadoAvance ANTES:::::: " + resultadoAvance);
		resultadoAvance = formateoAvance(resultadoAvance);
		//setValueAt("avanceOpe", getValueAt("avanceOpe"));
		Trace.trace(Trace.Debug, "", "### selecBeneficiarios() - resultadoAvance DESPUES::::: " + resultadoAvance);
		setValueAt("avanceOpe", resultadoAvance);
		//FIN INC 76 AVANCE 0 FX 16-07-2018

		
		Trace.trace(Trace.Debug, "", "### avanceOpe " + getValueAt("avanceOpe"));
		setValueAt("selectOpe", getValueAt("selectOpe"));
		Trace.trace(Trace.Debug, "", "### selectOpe " + getValueAt("selectOpe"));
		setValueAt("selectCuentaOrden", getValueAt("selectCuentaOrden"));
		Trace.trace(Trace.Debug, "", "### selectCuentaOrden " + getValueAt("selectCuentaOrden"));
		selectMoneda = (String) getValueAt("selectMoneda");
		if(selectMoneda!= null && selectMoneda!= ""){
			selectMoneda= selectMoneda.substring(0,3);
			}
		setValueAt("selectMoneda", selectMoneda);
		Trace.trace(Trace.Debug, "", "### selectMoneda " + getValueAt("selectMoneda"));
		setValueAt("monto", getValueAt("monto"));
		Trace.trace(Trace.Debug, "", "### monto " + getValueAt("monto"));
		setValueAt("descripNegociacion", getValueAt("descripNegociacion"));
		Trace.trace(Trace.Debug, "", "### descripNegociacion " + getValueAt("descripNegociacion"));
		
		setValueAt("tasaDivisa", getValueAt("tasaDivisa"));
		Trace.trace(Trace.Debug, "", "### tasaDivisa " + getValueAt("tasaDivisa"));
		setValueAt("tasaDivisaPeso", getValueAt("tasaDivisaPeso"));
		Trace.trace(Trace.Debug, "", "### tasaDivisaPeso " + getValueAt("tasaDivisaPeso"));
		setValueAt("tasaUsdPeso", getValueAt("tasaUsdPeso"));
		Trace.trace(Trace.Debug, "", "### tasaUsdPeso " + getValueAt("tasaUsdPeso"));
		setValueAt(EQUIV_PESO, getValueAt(EQUIV_PESO));
		Trace.trace(Trace.Debug, "", "### equivPesos " + getValueAt(EQUIV_PESO));
		setValueAt(TIPO_FONDO_GIRO, getValueAt(TIPO_FONDO_GIRO));
		Trace.trace(Trace.Debug, "", "### tipoFondoGiro " + getValueAt(TIPO_FONDO_GIRO));
		
		setValueAt("indBenef", indBenef);
		//ini 85
		setValueAt("tasaDivisaPeso2", getValueAt("tasaDivisaPeso2"));
		setValueAt(EQUIV_PESO_2, getValueAt(EQUIV_PESO_2));
		//fin 85
		
		try{
			Trace.trace(Trace.Debug, "", "### consulta Beneficiarios - consultaBeneficiarios()");
			consultaBeneficiarios();
			
			// INI incidencia 102 FX 30-10-2018
			if (!(getValueAt("pagina_actual").equals("1"))) {
				setValueAt("referenciaOpe", getValueAt("referenciaOpe"));
				//Trace.trace(Trace.Debug, "", "### tipoOperacion " + getValueAt("tipoOperacion"));//Depuracion trazas
				//Trace.trace(Trace.Debug, "", "### numOperacion " + getValueAt("numOperacion"));//Depuracion trazas
			}
			// FIN incidencia 102 FX 30-10-2018
			//Trace.trace(Trace.Debug, "", "*** consulta Beneficiarios - seguimiento111 " + indBenef);
			if(indBenef.equals("")){
				generarReferenciaOperacion();
			}
			setValueAt("indBenef", ""); //INC 102 FX 15/11/2018
			//Trace.trace(Trace.Debug, "", "*** consulta Beneficiarios - seguimiento222 " + getValueAt("msjError"));
			if(getValueAt("msjError").equals("")){
				setEstado("8");
			}
			
			if(getValueAt("msjError").equals("T") || getValueAt("msjError").equals("H")){
				Trace.trace(Trace.Debug, "", "### Entra SELECBENEFICIARIOS " + getValueAt("msjError"));					
				setValueAt("Error", getValueAt("msjError"));
				Trace.trace(Trace.Debug, "", "### Entra SELECBENEFICIARIOS ERROR" + getValueAt("Error"));	
				setEstado("ERRORWS");
			}
			
			asignaNitNombrePr();// VARIOS NIT F2 - OSNEIDER A. CMC - 02-10-2019
		}catch(Exception ec){
			setValueAt("indBenef", ""); //INC 102 FX 15/11/2018
			Trace.trace(Trace.Debug, "", "### falla en  - selecBeneficiarios()" + ec.toString());
			ec.printStackTrace();
		}
	}

	public void validarCuenta() throws Exception{	
		String Aviso= "";
		String Flag ="S";
		String codError = "";
		String tipoFondoGiro = validateElement(TIPO_FONDO_GIRO);
		Trace.trace(Trace.Debug, "", " Tipo fondo giro " + getValueAt(TIPO_FONDO_GIRO));
		if (!tipoFondoGiro.equalsIgnoreCase(FONDO_PSE)) {
			
		
			Trace.trace(Trace.Debug, "", " Tipo fondo giro " + getValueAt(TIPO_FONDO_GIRO));
			Trace.trace(Trace.Debug, "", " Inicio validarCuenta(): " + getValueAt("selectCuentaOrden") + getValueAt(EQUIV_PESO));
			om = creaOM(sOmEstadoCta);
			om.setValueAt("Entrada.BINDAUX1", "S");
			om.setValueAt("Entrada.BASUNPRO", "XX"+getValueAt("selectCuentaOrden"));
				Double importe = new Double(getValueAt(EQUIV_PESO).toString());
	
			Trace.trace(Trace.Debug, "", "*** Inicio validarCuenta()- IMPORTE: " + importe);
			om.setValueAt("Entrada.BIMPORTE", importe);
			om.setValueAt("Entrada.BCODACCC", getValueAt("s_cod_logon").toString());
			
			ejecutarOM(om);
			 Flag = (String) om.getValueAt(SALIDA_COD_AVISO);
			 Aviso = (String) om.getValueAt("Salida.VALOR-ERROR-UNO") + (String) om.getValueAt("Salida.VALOR-ERROR-DOS");//INI INC 35 FX 28/03/2018
			//INI INC 134 FX CMC 30/11/2018
			 codError = (String) om.getValueAt(SALIDA_COD_ERROR);
			//INC 134.2 CMC INI 26/12/2018
			if(codError!= null && codError!="" && (codError.trim().equalsIgnoreCase("BGE0515")||codError.trim().equalsIgnoreCase("BGE1320"))){
				Aviso = "Fondos Insuficientes";
			} else if(codError!= null && codError!="" && codError.trim().startsWith("BGE")){
				Aviso = "Su cuenta presenta inconvenientes, por favor comun\u00edquese con nuestra l\u00ednea 018000 935 777 o en Bogot\u00e1 al 3078071";
			}
			
			//INC 134.2 CMC FIN 26/12/2018
			//FIN INC 134 FX CMC 30/11/2018
		}
			
			Trace.trace(Trace.Debug, "", "FIN validarCuenta(): "+ Aviso);
			setValueAt("validaCta", Flag);
			Trace.trace(Trace.Debug, "", " FIN validarCuenta(): " + getValueAt("validaCta"));
			Aviso.replaceAll("null","");//INC 6 FX 03/05/2018
			setValueAt("msjError", Aviso);//FIN INC 35 FX 28/03/2018
		
	}
	
	public void consultaNombreBanco() throws Exception{
		Trace.trace(Trace.Debug, "", " Inicio consultaNombreBanco(): " + getValueAt("codigoConsBanco") + " " + getValueAt("element").toString());
		
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
		om.setValueAt("Entrada.CODIGO_BAN", getValueAt("codigoConsBanco").toString());
		String element = getValueAt("element").toString();
		ejecutarOM(om);
		try {
			if(om.getValueAt(SALIDA_COD_ERROR) != null && om.getValueAt(SALIDA_COD_ERROR) != "") {
				setValueAt("codError", (om.getValueAt(SALIDA_COD_ERROR)).toString());
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
		setEstado("9");
	}
			
	public void gestionBeneficiarios() throws Exception{
		Trace.trace(Trace.Debug, "", " Inicio gestionBeneficiarios()");
		String indBenef = (String)getValueAt("indBenef");
		Trace.trace(Trace.Debug, "", "### gestionBeneficiarios() - indBenef " + indBenef);
		
		setValueAt("avanceOpe", getValueAt("avanceOpe"));
		Trace.trace(Trace.Debug, "", "### GB avanceOpe " + getValueAt("avanceOpe"));
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
		setValueAt(TIPO_FONDO_GIRO, getValueAt(TIPO_FONDO_GIRO));
		Trace.trace(Trace.Debug, "", "### tipoFondoGiro " + getValueAt(TIPO_FONDO_GIRO));
		
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
		setValueAt("nombreOperacion", "consulta_operaciones_negociacion_op");
		Trace.trace(Trace.Debug, "", " fin gestionBeneficiarios()");
		
		setEstado("9");
	}
	
	public void verUnico() throws Exception {
		Trace.trace(Trace.Debug, "", " Inicio verUnico()");
		
		try {
			 	
			String documentofull = (String) getValueAt("docBenefi");
			Trace.trace(Trace.Debug, "", "### verUnico - documentofull=" + documentofull);
			String[] parts = documentofull.split("\\|@\\|");//INC 39 FX CMC 02/03/2018
			String idioma = (String) getValueAt("s_idioma");
			
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
			om.setValueAt("Entrada.BCODACCC", getValueAt("s_cod_logon").toString());
			
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
//INI CMC - FUNCION DE NEGOCIO FSTE - PRUEBA - 19/09/2019
				
			Trace.trace(Trace.Debug, "", "### verUnico - ejecutada om sOmConsultaBeneficiarios");
			IndexedCollection iccuentas = (IndexedCollection) om.getElementAt("Salida.ListaCUENTAS-BENEFIC");

			Trace.trace(Trace.Debug, "", "### verUnico - cargada la lista");
			int tamano = iccuentas.size() - 1;
			IndexedCollection listaCuentas;
			for (int i = tamano; i > 0; i--) {
				KeyedCollection kCuentas = (KeyedCollection) iccuentas.getElementAt(i);

				String cuenta = (String) kCuentas.getValueAt("ID-BENEFICIARIO");
				if (cuenta == null) {
					iccuentas.removeElementAt(i);
				} else if (cuenta == "") {
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
				KeyedCollection kCuentas = (KeyedCollection) listaCuentas3.getElementAt(i);
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
			
			
		}catch (Exception e) {
			e.printStackTrace();
			Trace.trace(Trace.Debug, "", "### ERROR verUnico()" + e);
//INI CMC - FUNCION DE NEGOCIO - PRUEBA - 19/09/2019
		} finally {
			controlOM = null;
		}
//FIN CMC - FUNCION DE NEGOCIO - PRUEBA - 19/09/2019
	}
	
	
	public void altaBeneficiario() throws Exception{
		Trace.trace(Trace.Debug, "", "### INICIO altaBeneficiario()");
		setValueAt("pagina_actual", "");//INC 102 FX 13/11/2018
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
			om.setValueAt("entrada.REFENC", getValueAt("s_cod_logon").toString());
			Trace.trace(Trace.Debug, "", "### altaBeneficiario() / Codigo Unitario: " + getValueAt("codigoUnitario"));
			om.setValueAt("entrada.CODIGO", getValueAt("codigoUnitario"));
			
			
			Trace.trace(Trace.Debug, "", "### altaBeneficiario() / lanzando sOmAltaBeneficiarios");
			ejecutarOM(om);
			Trace.trace(Trace.Debug, "", "### altaBeneficiario() / sOmAltaBeneficiarios Ok!");
			setValueAt("msjError", "");
			
		} catch (Exception e) {
			setValueAt("msjError", "");
			Trace.trace(Trace.Debug, "", "### ERROR altaBeneficiario()" + e);
			e.printStackTrace();
			
		}
	}
	
	public void modificarBeneficiario() throws Exception{
		Trace.trace(Trace.Debug, "", " Inicio modificarBeneficiario()");
		setValueAt("pagina_actual", "");//INC 102 FX 13/11/2018
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
			om.setValueAt("entrada.REFENC", getValueAt("s_cod_logon").toString());
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
			setValueAt("msjError", "");
			Trace.trace(Trace.Debug, "", "### ERROR modificarBeneficiario()" + e);
			e.printStackTrace();
			return;
		}
		
	}
	
	public void eliminarBeneficiario() throws Exception{
		Trace.trace(Trace.Debug, "", " Inicio eliminarBeneficiario()");
		
		String documentofull = (String) getValueAt("docBenefi");
		Trace.trace(Trace.Debug, "", "### verUnico - documentofull=" + documentofull);
		String[] parts = documentofull.split("\\|@\\|");//INC 39 CMC FX 02/03/2018
		
		String tipoId = parts[0];
		String numId = parts[1];
		String digVer = parts[2];
		String ctaBenef = parts[3];
		
		String numReferencia = getValueAt("s_cod_logon").toString();
		
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
			e.printStackTrace();
			this.setEstado(ERROR);
			return;
		}
		
		setValueAt("indBenef", "B");
		setValueAt("pagina_actual", "");//INC 102 FX 13/11/2018
		selecBeneficiarios();
	}
	
	
	public void consultaBeneficiarios() throws Exception {

		try {
			// INI Incidencia 102 FX 19/11/2018
			String tamanoPag = "20";//Incidencia 102 FX 8/11/2018
			IndexedCollection listaCuentas;
			int paginaOP = 0;
			Trace.trace(Trace.Debug, "", "### consultaBeneficiarios() - inicio beneficiarios");
			om = creaOM(sOmConsultaBeneficiarios);
			///// Se debe asignar el valor correspondientes a la FN
			om.setValueAt("Entrada.BINDAUX1", "T");
			om.setValueAt("Entrada.BCODACCC", getValueAt("s_cod_logon").toString());
			om.setValueAt("Entrada.BNUMAUX2", tamanoPag);//Incidencia 102 FX 8/11/2018
			
			//INICIO Incidencia 102 FX 14/11/2018	
			Trace.trace(Trace.Debug, "", "### IF ANTES DE SI ES NULO " + (String)getValueAt("pagina_actual"));
			if(getValueAt("pagina_actual") == null)
			{
				Trace.trace(Trace.Debug, "", "### IF SI ES NULO " + (String)getValueAt("pagina_actual"));
				setValueAt("pagina_actual","");
			}
							
			if(!getValueAt("pagina_actual").equals("")) 
			{
				Trace.trace(Trace.Debug, "", "### IF SI ES OTRA PAGINA " + (String)getValueAt("pagina_actual"));
				paginaOP = Integer.parseInt(String.valueOf(getValueAt("pagina_actual")));				
				om.setValueAt("Entrada.BNUMAUX1", String.valueOf(paginaOP + 1));//Se solicita la pag siguiente
				setValueAt("pagina_actual",  String.valueOf(paginaOP + 1));
			}else
			{				
				Trace.trace(Trace.Debug, "", "### CUALQUIER OPERACION DIFERENTE DE VER MAS" + (String)getValueAt("pagina_actual"));
				om.setValueAt("Entrada.BNUMAUX1", "1");//Se solicita la pag 1	
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
//INI CMC - FUNCION DE NEGOCIO FSTE - PRUEBA - 19/09/2019
			Trace.trace(Trace.Debug, "", "### consultaBeneficiarios() - sOmConsultaBeneficiarios ejecutada ");

			Trace.trace(Trace.Debug, "", "### BANDERA PAGINACION " + om.getValueAt("Salida.PAGINA-ACTUAL"));
			Trace.trace(Trace.Debug, "", "###8 PAGINA ACTUAL " + (String) getValueAt("pagina_actual"));
			setValueAt("bandera_paginacion", (om.getValueAt("Salida.PAGINA-ACTUAL")).toString());// Bandera
			IndexedCollection iccuentas = (IndexedCollection) om.getElementAt("Salida.ListaCUENTAS-BENEFIC");

			int tamano = iccuentas.size() - 1;
			
			listaCuentas = (IndexedCollection) getElementAt("listaCuentasB");
			
			if (Integer.parseInt(String.valueOf(getValueAt("pagina_actual"))) <= 1) {
				Trace.trace(Trace.Debug, "", "### ENTRA SI PAGINA ACTUAL ES MENOR A 1 ");
				listaCuentas.removeAll();
			}
			Trace.trace(Trace.Debug, "", "### consultaBeneficiarios() - lista: tamano" + tamano);//Depuracion trazas
			for (int i = tamano; i >= 0; i--) {
				KeyedCollection kCuentas = (KeyedCollection) iccuentas.getElementAt(i);

				String cuenta = (String) kCuentas.getValueAt("NOMBRES-BENEFIC");
				//Trace.trace(Trace.Debug, "", "### consultaBeneficiarios() - lista: " + cuenta);//Depuracion trazas
				
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
			// FIN Incidencia 102 FX 19/11/2018
		} catch (Exception e) {
			e.printStackTrace();
			this.setEstado(ERROR);
			Trace.trace(Trace.Debug, "", "### consultaBeneficiarios() - Falla en el metodo " + e);
			return;
//INI CMC - FUNCION DE NEGOCIO - PRUEBA - 19/09/2019
		} finally {
			controlOM = null;
		}
//FIN CMC - FUNCION DE NEGOCIO - PRUEBA - 19/09/2019
	}
	
	
	public void generarReferenciaOperacion() throws Exception{
		Trace.trace(Trace.Debug, "", "### generarReferenciaOperacion() PASO_1 - inicio ");
		setValueAt("errorCodigo", "N");//INC 225B FX CMC 17/09/2019
		try{
			//INI- LISTA DE NIT/COMERCIO EXTERIOR - OSNEIDER ACEVEDO - CMC - 14-05-2019 
			
			if(getValueAt("selectOpe")!=null && getValueAt("selectOpe").toString().equalsIgnoreCase("T1")) {
				if(getValueAt("Num_Nit")!=null && !getValueAt("Num_Nit").toString().trim().equals("")) {
					String trama=(String)getValueAt("Num_Nit");
					String[] datos=trama.split("@");
					setValueAt("num_cuenta", datos[0]);
//INI INC CMC OFICINA GESTORA 27/09/2019
					try {
						BT_IDSUCURSAL = (String) getValueAt("oficinaGestoraOP");
					} catch (Exception e) {
						Trace.trace(Trace.Error, getClass().getName(), "####### generarReferenciaOperacion() - ERROR ####### " + e);
					}
//FIN INC CMC OFICINA GESTORA 27/09/2019
					setValueAt("msjError", "");
				}
			}else if (getValueAt("selectOpe").toString().equalsIgnoreCase("T2")) { //INI-VARIOS NIT F2 - OSNEIDER A. CMC - 29-01-2020 FASE2
				try {
					if(getValueAt("NitUnido")!=null && !getValueAt("NitUnido").toString().trim().equals("")) {
					String nit=getValueAt("NitUnido").toString().trim();
					nit=nit.replace("-", "");
					System.out.println(getValueAt("NitUnido").toString().trim());
					setValueAt("num_cuenta", nit);
					BT_IDSUCURSAL = (String) getValueAt("oficinaGestoraOP");
					}
					setValueAt("msjError", "");
					Trace.trace(Trace.Debug, "", "OMAN####### GIROS HACIA #######  OFICINA: " + BT_IDSUCURSAL);
				} catch (Exception e) {
					Trace.trace(Trace.Error, getClass().getName(), "####### generarReferenciaOperacion() - ERROR ####### " + e);
				}
			}else { //FIN-VARIOS NIT F2 - OSNEIDER A. CMC - 29-01-2020 FASE2
				consultarOficinaTitular();
			}
			//FIN- LISTA DE NIT/COMERCIO EXTERIOR - OSNEIDER ACEVEDO - CMC - 14-05-2019 
			
			//INI INC 225B FX CMC 25/09/2019
			if(getValueAt("errorSwift").equals("N")) {
				// INI incidencia 102 FX 30-10-2018
				if (getValueAt("pagina_actual").equals("1")) {
					//INI incidencia 153 FX CMC 27/12/2018
					//aceptarInicial();
					generarOperacionT();
					//FIN incidencia 153 FX CMC 27/12/2018
				}
				// FIN incidencia 102 FX 30-10-2018
			}
			//FIN INC 225B FX CMC 25/09/2019
		}catch (Exception e) {
			Trace.trace(Trace.Debug, "", "### generarReferenciaOperacion() PASO_1 - ERROR " + e.toString());
			setValueAt("referenciaOpe", "_");
			setValueAt("msjError", "SISTEMA TEMPORALMENTE NO DISPONIBLE");
			
		}
		
	}
	
	public void consultarOficinaTitular() throws Exception{
		String idSucursalOK = "";//Incidencia 153 FX CMC 08/01/2019
		Trace.trace(Trace.Debug, "", "### consultarOficinaTitular() - inicio ");
		om = creaOM(sOmOficinaTitular);
		om.setValueAt("Entrada.BCODACCC", getValueAt("s_cod_logon").toString());
//		
		ejecutarOM(om);
		
		//INI incidencia 183 FX CMC 08/03/2019
		String banderaCodError = "";
		if(om.getValueAt(SALIDA_COD_ERROR) == null) {
			banderaCodError = "1";
		}else {
			if(om.getValueAt(SALIDA_COD_ERROR).equals(PME1147)) {
				banderaCodError = PME1147;
			}
		}
		if(om.getValueAt(SALIDA_COD_AVISO)==null || om.getValueAt(SALIDA_COD_AVISO)=="XXXX" || om.getValueAt(SALIDA_COD_AVISO)=="" || om.getValueAt(SALIDA_COD_AVISO)==" "  || banderaCodError.equals(PME1147)){
			BT_IDSUCURSAL = "0073";
		//FIN incidencia 183 FX CMC 08/03/2019
		}else{
			BT_IDSUCURSAL = (String) om.getValueAt(SALIDA_COD_AVISO);			
			Trace.trace(Trace.Debug, "", "### Oficina OK: " + BT_IDSUCURSAL);
			//INI incidencia 153 FX CMC 08/01/2019
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
			//FIN incidencia 153 FX CMC 08/01/2019
		}
		String docuTmp = (String) om.getValueAt("Salida.VALOR-ERROR-DOS");
		
		try{
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
			Trace.trace(Trace.Debug, "", "### ERROR:documentoCliente no formateado: " + docuTmp);
			Trace.trace(Trace.Debug, "", "### ERROR: " + e);
		}
		
		Trace.trace(Trace.Debug, "", "### Oficina: " + BT_IDSUCURSAL);
		Trace.trace(Trace.Debug, "", "### documentoCliente: " + documentoCliente);
		setValueAt("num_cuenta", documentoCliente);
		setValueAt("msjError", "");
		
		Trace.trace(Trace.Debug, "", "### consultarOficinaTitular() - fin ");
	}
	
	public void aceptarInicial() throws Exception {
		
		String descripNegociacion = (String) getValueAt("descripNegociacion");
		String selectMoneda = (String) getValueAt("selectMoneda");
		Trace.trace(Trace.Debug, "", "### aceptarInicial() - inicio PASO_1: " + descripNegociacion);
				
		RequestBankTradeService peticion = new RequestBankTradeService();
		
		Date fechaActual = new Date();
		peticion.setFechaHoraNegociacion(fechaActual);
		peticion.setFeValor(fechaActual);
		peticion.setMonAvance(getValueAt("monto").toString());
		
		// Cuenta Ordenante - INI//
		String sSelectCuentaOrden = (String) getValueAt("selectCuentaOrden");
		String deCTA = sSelectCuentaOrden.substring(10,12);
		
		if(deCTA.equals("01")){
			deCTA = "CTE";
		}else if(deCTA.equals("02")){
			deCTA = "AHO";
		}
		Trace.trace(Trace.Debug, "", "### aceptarInicial() - deCTA " + deCTA);
		
		peticion.setNoCtaComm(sSelectCuentaOrden);
		peticion.setDeComm(deCTA);
		peticion.setNoCtaPrincipal(sSelectCuentaOrden);
		peticion.setNoCtaPrincipalCre(sSelectCuentaOrden);//INC BT15.1 PRD FX 05-07-2019
		// Cuenta Ordenante - FIN//
		
		if(getValueAt("selectOpe").equals("T1") ){
			Trace.trace(Trace.Debug, "", "### aceptarInicial() PASO_1 - T1 ");
			peticion.setTasaDivi(Double.valueOf(getValueAt("cotizacion.tasaDivisaPeso").toString()));
			peticion.setTasaAvance(getValueAt("tasaDivisaPeso").toString());
			peticion.setTasaUsd(Double.valueOf(getValueAt("cotizacion.tasaUsdPeso").toString()));
			peticion.setTasaLinea(Double.valueOf(getValueAt("cotizacion.tasaDivisa").toString()));
			peticion.setTyNegoci("NL");
			peticion.setNoAvance("STD1");
		}else if (getValueAt("selectOpe").equals("T2") ){
			Trace.trace(Trace.Debug, "", "### aceptarInicial() PASO_1 - T2 " + getValueAt("avanceOpe").toString() + " / " + getValueAt("monto").toString());
			peticion.setNoAvance(getValueAt("avanceOpe").toString());
			peticion.setTyNegoci("NM");
			
			peticion.setTasaAvance(null);
			peticion.setTasaDivi(null);		
			peticion.setTasaUsd(null);
			peticion.setTasaLinea(null);
			
		}
		//INI INC 8 FX CMC 26-02-2018
		String texto = "                                                                                                                                            ";
		peticion.setDeOperac(descripNegociacion);
		if (descripNegociacion.length() < 140) {
		    descripNegociacion = descripNegociacion + texto;
		}
		texto = descripNegociacion.substring(0,35);
		peticion.setInfcam70Sw1(texto);
		texto = descripNegociacion.substring(36,70);
		peticion.setInfcam70Sw2(texto);
		texto = descripNegociacion.substring(71,105);
		peticion.setInfcam70Sw3(texto);
		texto = descripNegociacion.substring(106,140);
		peticion.setInfcam70Sw4(texto);
		//FIN INC 8 FX CMC 26-02-2018
		//3 000000800067737 - 1 ::: 38000677371 
		String documentoCliente = (String) getValueAt("num_cuenta");
		peticion.setIdCliente(documentoCliente);
		peticion.setTyUsuario("JR");
		peticion.setMonNegoc(getValueAt("monto").toString());
		
		if(selectMoneda!= null && selectMoneda!= ""){
			selectMoneda= selectMoneda.substring(0,3);
			}
		setValueAt("selectMoneda", selectMoneda);
		peticion.setCurNegoc(selectMoneda);
		
		Trace.trace(Trace.Debug, "", "### aceptarInicial() PASO_1 - variables fijas ");
		peticion.setTyOperac("TFOL");			
		peticion.setIdSucursal(BT_IDSUCURSAL);
		peticion.setCanal("80");
		peticion.setSubcanal("01");
		peticion.setCampana("CANALES");
		peticion.setTyVended("9");
		peticion.setNoVended("777777777777777");
		peticion.setDigVended("9");
		peticion.setTyTransa("T");
		peticion.setIndMoneda("");//Pendiente por definir
		peticion.setID_BCOOrdenante("SYSTEM");
		peticion.setNomBcoOrd1("SYSTEM");
		
		Trace.trace(Trace.Debug, "", "### aceptarInicial() PASO_1 - a lanzar ws-...  ");
		try{
			ResponseBankTradeService response = WrapperBanktradeService.execute(peticion);
			if(response.getCodError() != null){
				if(!response.getCodError().equals("")){					
					this.setEstado(ERROR);//Incidencia FX 98 CMC 19/10/2018
					String mensaje = ERROR_WS_BANKTRADE.concat(response.getSequence()+" "+response.getCodError());
					Trace.trace(Trace.Debug, "", "### aceptarInicial() PASO_1 - error de web service: " + mensaje);
					setValueAt("msjError", mensaje);
					BbvaARQException Barq = ManejarExcepcion(3, mensaje, "", "", new Exception(), mensaje, this, "", "");//Incidencia FX 98 CMC 19/10/2018
					return;
				}else{
					Trace.trace(Trace.Debug, "", "### aceptarInicial() PASO_1 - response.getNumOpera " + response.getNumOpera());
					setValueAt("referenciaOpe", response.getNumOpera().toString());
					peticion.setNoOperac1(response.getNumOpera().toString());
					//peticion.setNoOperac1(response.getNumOpera().toString());
					setValueAt("msjError", "");
					setValueAt(LIT_PETICIO_WEB_SERV, peticion);
				}
			}else{
				Trace.trace(Trace.Debug, "", "### aceptarInicial() PASO_1 - error de web service: RTA null");
				setValueAt("msjError", "RTA null");
			}
		}catch (Exception e) {
			//INI incidencia FX 98 CMC 19/10/2018
			e.printStackTrace();
			Trace.trace(Trace.Debug, "", "### ENTRA AL CATCH DEL SEGUNDO LLAMADO ");		
			Trace.trace(Trace.Debug, "", "### aceptarInicial() PASO_1 - Falla lanzando web service: " + e);
			//setValueAt("msjError", "SISTEMA TEMPORALMENTE NO DISPONIBLE");
			String errorWS = "T";
			setValueAt("Error", errorWS);
			setValueAt("msjError", errorWS);
			this.setEstado("ERRORWS");
			//BbvaARQException Barq = ManejarExcepcion(3,errorWS, "", "", e, "", this, "", "");
			return;	
			//FIN incidencia FX 98 CMC 19/10/2018			
		}
		//2do envio a BTConnector 
		updatePaso2TMP(peticion, sSelectCuentaOrden);
		
	}
	
	public void updatePaso2TMP(RequestBankTradeService peticion, String sSelectCuentaOrden) throws Exception{
		Trace.trace(Trace.Debug, "", " Inicio updatePaso2TMP() PASO_TMP" );
		
		peticion = cargarInfoBeneficiarioTMP(peticion, sSelectCuentaOrden);
		peticion.setTyOperac("TFOUS");
		//peticion.setNoCtaPrincipalCre("SYSTEM");INC BT15.1 PRD FX 05-07-2019 Se está seteando en el metodo de cargarInfoBeneficiarioTMP
		
		peticion.setCurDbComm("COP");
		peticion.setTpCambioComm("1.000000000");												
		peticion.setCodOpecampo23b("CRED");
		peticion.setTyNegoci("**");
		// se ingresa un Numeral Cambiario temporal
		peticion.setNuCambiario("5E02904");
		
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
				
		Trace.trace(Trace.Debug, "", "### updatePaso2TMP() PASO_TMP - a lanzar ws-...  ");
		
		if(peticion!=null){
		 try{	
			ResponseBankTradeService response = WrapperBanktradeService.execute(peticion);
			if(response.getCodError() != null){
				if(!response.getCodError().equals("")){
					//INI incidencia FX 98 CMC 19/10/2018
					this.setEstado(ERROR);
					String mensaje = ERROR_WS_BANKTRADE.concat(response.getSequence()+" "+response.getCodError());
					setValueAt("msjError", mensaje);
					Trace.trace(Trace.Debug, "", "### updatePaso2TMP() PASO_TMP - error de web service: " + mensaje);
					return;
					//FIN incidencia FX 98 CMC 19/10/2018
				}else{
					Trace.trace(Trace.Debug, "", "### updatePaso2TMP() PASO_TMP - response.getNumOpera " + response.getNumOpera());
					setValueAt(LIT_PETICIO_WEB_SERV, peticion);
				}
			}else{
				Trace.trace(Trace.Debug, "", "### updatePaso2TMP() PASO_1 - error de web service: RTA null");
			}
		 }catch (Exception e) {
			e.printStackTrace();
			//INI incidencia FX 98 CMC 19/10/2018
			Trace.trace(Trace.Debug, "", "### ENTRA AL CATCH DEL SEGUNDO LLAMADO ");	
			String errorWS = "T";
			setValueAt("msjError", errorWS);			
			setValueAt("Error", errorWS);
			//FIN incidencia FX 98 CMC 19/10/2018
			Trace.trace(Trace.Debug, "", "### updatePaso2TMP() PASO_TMP - Falla lanzando web service: " + e);
			this.setEstado("ERRORWS");
			return;
		 }
		}
		Trace.trace(Trace.Debug, "", " Fin updatePaso2TMP(). PASO_TMP");
	}
	
	
	public void detalleGiro() throws Exception{
		
		String monto;
		double montoNum; //VARIOS NIT F2 - 16-01-2019 - MONTOS	
		String montoCompleto; //VARIOS NIT F2 - 16-01-2019 - MONTOS
		int indice;
		KeyedCollection kGiro, kDetalleOP;
		IndexedCollection listaGirosOP;
		Trace.trace(Trace.Debug, "", " Inicio detalleGiro() Indice: " + getValueAt("indiceGiro"));
		try {
			
		indice = Integer.parseInt((String)getValueAt("indiceGiro"));
		
		listaGirosOP= (IndexedCollection) getElementAt("listaGiros");
		kGiro= (KeyedCollection) listaGirosOP.getElementAt(indice);
		kDetalleOP = (KeyedCollection) getElementAt("detalleGiro");
		copiarKeyedCollection(kGiro, kDetalleOP);
		monto=  kDetalleOP.getValueAt("monto").toString();
		Numero numeroLetras = new Numero();
		String montoEnLetras =numeroLetras.convertir(monto,true);
		kDetalleOP.setValueAt("montoLetras", montoEnLetras);
		
		//FX ini INC 6.1 12/06/2018		
		String fechaOper = String.valueOf(kDetalleOP.getValueAt("fechaOperacion"));
		Trace.trace(Trace.Debug, "", " detalleGiro() fechaOperacion: ---->" +fechaOper);
		String mostrarFech=FormateaFecha(fechaOper);
		setValueAt("mostrarFechaOpeDetalle", mostrarFech);
		Trace.trace(Trace.Debug, "", " detalleGiro() monto: ---->" +monto);
		Trace.trace(Trace.Debug, "", " detalleGiro() mostrarfechaopeDeta: ---->" +getValueAt("mostrarFechaOpeDetalle"));
		
	//INI VARIOS NIT F2 - 16-01-2019 - MONTOS
		//Inicio tratamiento monto sin notacion cientifica
		montoNum = Double.parseDouble(monto);
		Locale.setDefault(Locale.US);
	    DecimalFormat num = new DecimalFormat("###.0#");
	    montoCompleto = num.format(montoNum);
	    //Fin tratamiento monto sin notacion cientifica
	    
		String[] res = montoCompleto.trim().split("\\.");
	//FIN VARIOS NIT F2 - 16-01-2019 - MONTOS	
		int decimal = Integer.parseInt(res[1]);
		String auxMonto="";
		String muestraMonto="";
		//Inicio formateador miles y decimales
		DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
	    simbolo.setDecimalSeparator('.');
	    simbolo.setGroupingSeparator(',');
	    DecimalFormat formateador = new DecimalFormat("###,###.##",simbolo);
		//fin formateador
		

		if (decimal == 0) {
		//si parte decimal igual a cero, mostrar entero
		muestraMonto = formateador.format (Long.valueOf(res[0]));
		setValueAt("mostrarMonto", muestraMonto);					
		} else {
		//sino mostrar decimal
			if (res[1].length()==1) {
			auxMonto = monto;
			muestraMonto=formateador.format (Double.valueOf(auxMonto));
			setValueAt("mostrarMonto", muestraMonto.concat("0"));					
			Trace.trace(Trace.Debug, "", " detalleGiro() montoDecimalAddDig: ---->" +auxMonto);			
			} else {
			muestraMonto=formateador.format (Double.valueOf(monto));
			setValueAt("mostrarMonto", muestraMonto);					
			Trace.trace(Trace.Debug, "", " detalleGiro() montoDecimalDosDig: ---->" +auxMonto);									
			}		
			
		}
		
			//inicio tratamiento concepto
		String numeralAux = String.valueOf(kDetalleOP.getValueAt("formNumeral"));
		String numeral=numeralAux.trim();
		String numeralCambiario = "";
		int digitoNumCambiario = 0;
		
		if (numeral == null || numeral.equalsIgnoreCase("") || numeral.equalsIgnoreCase("null")) {
		setValueAt("mostrarNumCambiario", "");
		}else{
		Trace.trace(Trace.Debug, "", " detalleGiro() numeral: ---->" +numeral);		
		digitoNumCambiario = Integer.valueOf(numeral.trim().substring(0, 1));
		numeralCambiario = numeral.trim().substring(1);
		setValueAt("mostrarNumCambiario", numeralCambiario);
		Trace.trace(Trace.Debug, "", " detalleGiro() numeralCamb: ---->" +getValueAt("mostrarNumCambiario"));		
		}
		
		Map<Integer, String> concepto = new HashMap<Integer, String>();
		concepto.put(1, "Importaci\u00f3n de bienes");
		concepto.put(2, "Exportaci\u00f3n de Bienes");
		concepto.put(3, "Endeudamiento Externo");
		concepto.put(4, "Inversiones Internacionales");
		concepto.put(5, "Servicios y otros conceptos");		
		
		String obtener = concepto.get(digitoNumCambiario);		
		if (obtener == null) {
			Trace.trace(Trace.Debug, "", " detalleGiro() getMostrarconcep: ---->" + "numeral no corresponde a concepto");
			setValueAt("mostrarConcepto", "");
		}else{
			setValueAt("mostrarConcepto", obtener);
			Trace.trace(Trace.Debug, "", " detalleGiro() getMostrarconcep: ---->" +getValueAt("mostrarConcepto"));
			Trace.trace(Trace.Debug, "", " detalleGiro() digito, concepto, numeralCambiario: ---->"+":"+obtener+":"+numeralCambiario);
		}
				
			//Fin tratamiento concepto
			
			//inicio tratamiento estado
			int numEstado=0;			
			String estadoOperacion = String.valueOf(kDetalleOP.getValueAt("estado"));
			if (estadoOperacion == null || estadoOperacion.equalsIgnoreCase("") || estadoOperacion.equalsIgnoreCase("null")) {
				numEstado=0;
			}
			Trace.trace(Trace.Debug, "", " detalleGiro() estadoOperacion: ---->" + estadoOperacion);
			numEstado = Integer.valueOf(estadoOperacion);
			Trace.trace(Trace.Debug, "", " detalleGiro() estadoValorNum: ---->" + numEstado);
			
				
				switch (numEstado) {
				case 1:
				case 7:
					setValueAt("mostrarEstado", "En Proceso Cliente");
					break;
				case 2:
				case 11:
					setValueAt("mostrarEstado", "Pendiente de firma");
					break;
				case 3:
					setValueAt("mostrarEstado", "Proceso Banco");					
					break;
				case 4:
				case 8:
					setValueAt("mostrarEstado", "Rechazada");					
					break;
				case 5:
				case 9:
					setValueAt("mostrarEstado", "Devuelta");					
					break;
				case 6:
					setValueAt("mostrarEstado", "Enviada al Exterior");					
					break;
				case 10:
					setValueAt("mostrarEstado", "Enviada al Extranjero");					
					break;

				default:
					setValueAt("mostrarEstado", "N/A");
				}
				Trace.trace(Trace.Debug, "", " detalleGiro() estadovsNum: ---->" + getValueAt("mostrarEstado"));
					//fin tratamiento estado
			
		//FX fin INC 6.1 12/06/2018 
		setEstado("5");
        //INI-VARIOS NIT F2 - OSNEIDER A. CMC - 02-10-2019
		if(kGiro.getValueAt("nOperacion") != null && !kGiro.getValueAt("nOperacion").toString().equalsIgnoreCase("")) {
			Trace.trace(Trace.Information, getClass().getName(), "OMAN- detalleGiro() - consumo de la FNGU para el detalle de la operacion");
			setValueAt("s_Fijo_Nit","" );
            setValueAt("s_Fijo_Nombre","");
			String ref=(String)getValueAt("s_cod_logon");
			String usu=(String)getValueAt("s_cod_usuarisc");
			String usuario=obtenerUsuario(ref, usu);
			String oper=kGiro.getValueAt("nOperacion").toString();
			String tipoO=oper.substring(0,1);
			String numeroOper=oper.substring(1,oper.length());
			String cuenta="AH0013"+kGiro.getValueAt("cuentaAbono").toString().trim();
			Trace.trace(Trace.Information, getClass().getName(), "OMAN- detalleGiro() - consumo de la FNGU numeroOperacion:"+numeroOper);
			KeyedCollection detallesOperacion=om_FNGU("","",tipoO,cuenta ,usuario,new Double(numeroOper));
			if(detallesOperacion!=null) {
				if(detallesOperacion.getValueAt("NIT_COMPLETO")!=null && !detallesOperacion.getValueAt("NIT_COMPLETO").toString().equals("")) {
				Trace.trace(Trace.Information, getClass().getName(), "OMAN- detalleGiro() - Formatea datos Nit y nombre");
				String nit=(String)detallesOperacion.getValueAt("NIT_COMPLETO");
				nit=nit.substring(1);
				
				nit=nit.replaceFirst("^0+(?!$)", "");
				String digitoVerifi=nit.substring(nit.length()-1);
				nit=nit.substring(0,nit.length()-1)+"-"+digitoVerifi;
				setValueAt("s_Fijo_Nit",nit );
	            setValueAt("s_Fijo_Nombre",(String)detallesOperacion.getValueAt(NOMBRE_CLIENTE));
				}
			}
			
		}
		TraerNit_NombreContextoOp();
		//FIN-VARIOS NIT F2 - OSNEIDER A. CMC - 02-10-2019
		Trace.trace(Trace.Debug, "", " Fin detalleGiro()");
		}catch (Exception e) {
			setEstado(ERROR);
			Trace.trace(Trace.Debug, "", "### ERROR - Fin detalleGiro() - " + e);
		}
	}
	
	public void cotizador2() throws Exception{
		KeyedCollection kEntradaCotizador,kCotizadorOM,kCotizadorOP,kEntradaParametria;
		try {	
		Trace.trace(Trace.Debug, "", " Inicio cotizador()");
		kEntradaCotizador = (KeyedCollection) getElementAt(sOmCotizador+"-data.entrada");
		kEntradaParametria = (KeyedCollection) getElementAt(sOmParametria+"-data.entrada");
		String sMonto=(String)getValueAt("monto");
		String sSelectMoneda= (String)getValueAt("selectMoneda");
		String sSelectCuentaOrden= (String) getValueAt("selectCuentaOrden");
		String selectOpe= (String)getValueAt("selectOpe");
		//String sTipoIdCliente= getValueAt("sTipoIdCliente");
		String sTipoIdCliente= "03";
		Trace.trace(Trace.Debug, "", "### cotizador()- sMonto" + sMonto);
		Trace.trace(Trace.Debug, "", "### cotizador()- sSelectMoneda" + sSelectMoneda);
		Trace.trace(Trace.Debug, "", "### cotizador()- selectOpe" + selectOpe);
		
		if (sMonto==null || sSelectMoneda==null || sSelectCuentaOrden==null){
			Trace.trace(Trace.Debug, "", "### cotizador()- recibe nulos");
		}else {
			try{
			Double monto =formatearImporteEntrada(sMonto);
			Trace.trace(Trace.Debug, "", "### cotizador()- datos numericos OK");
			IndexedCollection listaMonedas =(IndexedCollection) getElementAt("parametria.listaMonedas");
			
			kEntradaCotizador.setValueAt("TOPER",selectOpe);
			kEntradaCotizador.setValueAt("MONTO", monto);
			kEntradaCotizador.setValueAt("CODIV", sSelectMoneda);
			kEntradaCotizador.setValueAt("TIPER", sTipoIdCliente);
			//kEntradaCotizador.setValueAt("NCUENT", ((String)kCuenta.getValueAt("s_banco")).trim() +((String)kCuenta.getValueAt("s_oficina")).trim()+ ((String)kCuenta.getValueAt("s_dcontrol")).trim() +((String)kCuenta.getValueAt("s_num_cuenta")).trim());
			kEntradaCotizador.setValueAt("NCUENT", sSelectCuentaOrden);
			OperacionMulticanal OmCotizador = creaOM(sOmCotizador);
			Trace.trace(Trace.Debug, "", "### cotizador()- sOmCotizador precargada");
			try{
				ejecutarOM(OmCotizador);
				kCotizadorOM = (KeyedCollection) getElementAt(sOmCotizador+DATA_SALIDA);
				kCotizadorOP = (KeyedCollection) getElementAt("cotizacion");
				copiarKeyedCollection(kCotizadorOM, kCotizadorOP);
				Trace.trace(Trace.Debug, "", "### cotizador()- sOmCotizador ejecutada");
				//INI INC 214 FX - CMC 09/05/2019
				String codMontoMax ="";
                String montoMax ="";
                String descErrorMonto ="";
                montoMax= (String)kCotizadorOM.getValueAt("MONTO-MAX");
                codMontoMax= (String)kCotizadorOM.getValueAt("COD-RET");
                descErrorMonto= (String)kCotizadorOM.getValueAt("DESC-ERROR");
                if(codMontoMax != null && codMontoMax.equalsIgnoreCase("CNE9999")){
				    descErrorMonto = descErrorMonto.substring(1,2).toUpperCase() + descErrorMonto.substring(2, descErrorMonto.length()-1).toLowerCase();
				    setValueAt("descErrorMonto", descErrorMonto);
				    kCotizadorOP.setValueAt("tasaDivisa", new Double("0"));
				    kCotizadorOP.setValueAt("tasaDivisaPeso", new Double("0"));
				    kCotizadorOP.setValueAt("tasaUsdPeso", new Double("0"));
				    kCotizadorOP.setValueAt(EQUIV_PESO, new Double("0"));
				    setValueAt("tasaDivisaPeso2", new Double("0"));
				    setValueAt(EQUIV_PESO_2, new Double("0"));
                } 
                //INI INC 137 y 147 FX - CMC 26/06/2019
                else if(codMontoMax != null && codMontoMax.equalsIgnoreCase("CNE0019")) {
                	descErrorMonto = "Monto no valido";
                	setValueAt("descErrorMonto", descErrorMonto);
                    kCotizadorOP.setValueAt("tasaDivisa", new Double("0"));
                    kCotizadorOP.setValueAt("tasaDivisaPeso", new Double("0"));
                    kCotizadorOP.setValueAt("tasaUsdPeso", new Double("0"));
                    kCotizadorOP.setValueAt(EQUIV_PESO, new Double("0"));
                    setValueAt("tasaDivisaPeso2", new Double("0"));
                    setValueAt(EQUIV_PESO_2, new Double("0"));
                } else if(codMontoMax != null && (codMontoMax.equalsIgnoreCase("GPE1998") || codMontoMax.equalsIgnoreCase("GPE3001") 
                		|| codMontoMax.equalsIgnoreCase("GPE3002") || codMontoMax.equalsIgnoreCase("GPE3003") || codMontoMax.equalsIgnoreCase("GPE3040")
                		|| codMontoMax.equalsIgnoreCase("GPE3004") || codMontoMax.equalsIgnoreCase("GPE3005") || codMontoMax.equalsIgnoreCase("GPE3006") 
                		|| codMontoMax.equalsIgnoreCase("GPE3007") || codMontoMax.equalsIgnoreCase("GPE3009") || codMontoMax.equalsIgnoreCase("GPE3010")
                		|| codMontoMax.equalsIgnoreCase("GPE3011") || codMontoMax.equalsIgnoreCase("GPE3012") || codMontoMax.equalsIgnoreCase("GPE3013")
                		|| codMontoMax.equalsIgnoreCase("GPE3014") || codMontoMax.equalsIgnoreCase("GPE3015") || codMontoMax.equalsIgnoreCase("GPE3016")
                		|| codMontoMax.equalsIgnoreCase("GPE3017") || codMontoMax.equalsIgnoreCase("GPE3018") || codMontoMax.equalsIgnoreCase("GPE3019")
                		|| codMontoMax.equalsIgnoreCase("GPE3022") || codMontoMax.equalsIgnoreCase("GPE3023") || codMontoMax.equalsIgnoreCase("GPE3024")
                		|| codMontoMax.equalsIgnoreCase("GPE3028") || codMontoMax.equalsIgnoreCase("GPE3029") || codMontoMax.equalsIgnoreCase("GPE3030") 
                		|| codMontoMax.equalsIgnoreCase("GPE3031") || codMontoMax.equalsIgnoreCase("GPE3032") || codMontoMax.equalsIgnoreCase("GPE3033")
                		|| codMontoMax.equalsIgnoreCase("GPE3034") || codMontoMax.equalsIgnoreCase("GPE3039") )) {
                	descErrorMonto = descErrorMonto.substring(0,1).toUpperCase() + descErrorMonto.substring(1, descErrorMonto.length()-1).toLowerCase();
	       		   	setValueAt("descErrorMonto", descErrorMonto);
	       		   	kCotizadorOP.setValueAt("tasaDivisa", new Double("0"));
	       		   	kCotizadorOP.setValueAt("tasaDivisaPeso", new Double("0"));
	       		   	kCotizadorOP.setValueAt("tasaUsdPeso", new Double("0"));
	       		   	kCotizadorOP.setValueAt(EQUIV_PESO, new Double("0"));
	       		   	setValueAt("tasaDivisaPeso2", new Double("0"));
	       		   	setValueAt(EQUIV_PESO_2, new Double("0"));
                }
                //FIN INC 137 y 147 FX - CMC 26/06/2019
                else{
                	descErrorMonto = "";
                	setValueAt("descErrorMonto", descErrorMonto);
                }
                //FIN INC 214 FX - CMC 09/05/2019
				Trace.trace(Trace.Debug, "", "### cotizador()- sOmCotizador2 - EQVPES: " + kCotizadorOM.getValueAt("EQVPES").toString() );
				setValueAt(EQUIV_PESO, kCotizadorOM.getValueAt("EQVPES").toString());
				
				//INC GP 12886 MOISES LUNA FX 06-03-2018
				Trace.trace(Trace.Debug, "", "### cotizador()- sOmCotizador2 - TIMER: " + kCotizadorOM.getValueAt("TIMER").toString() );
				setValueAt("timerS", kCotizadorOM.getValueAt("TIMER").toString());
				Trace.trace(Trace.Debug, "", "### cotizador()- sOmCotizador2 - PILOT: " + kCotizadorOM.getValueAt("PILOT").toString() );
				setValueAt("piloto", kCotizadorOM.getValueAt("PILOT").toString());				
				//FIN GP 12886 MOISES LUNA FX 06-03-2018
			}catch(Exception eom){
				Trace.trace(Trace.Debug, "", "### cotizador()- sOmCotizador Fallo la cotizacion " + eom);
				kCotizadorOP = (KeyedCollection) getElementAt("cotizacion");
				kCotizadorOP.setValueAt("tasaDivisa", new Double("0"));
				kCotizadorOP.setValueAt("tasaDivisaPeso", new Double("0"));
				kCotizadorOP.setValueAt("tasaUsdPeso", new Double("0"));
				kCotizadorOP.setValueAt(EQUIV_PESO, new Double("0"));
				//INI INC 85 FX CMC 07-11-2018
				setValueAt("tasaDivisaPeso2", new Double("0"));
				setValueAt(EQUIV_PESO_2, new Double("0"));
				//fin INC 85 FX CMC 07-11-2018
			}
			}catch(Exception ec){
				setEstado("ERROR");	
				Trace.trace(Trace.Debug, "", "### cotizador()- ERRORR sOmCotizador ejecutada");
				Trace.trace(Trace.Debug, "", "### cotizador()- "+  ec);
			}
		}
		setValueAt("pestana", "2");
		setEstado("3");
		
		Trace.trace(Trace.Debug, "", " Fin cotizador()");
		} catch (Exception e) {
			Trace.trace(Trace.Debug, "", "### cotizador()- ERRORR PREVIO sOmCotizador ejecutada");
			Trace.trace(Trace.Debug, "", "### cotizador()- "+  e);
		}
	}
	
	public void cotizador() throws Exception{
		KeyedCollection kCotizadorOP;
		try {	
		Trace.trace(Trace.Debug, "", " Inicio cotizador()");
		String sMonto=(String)getValueAt("monto");
		String sSelectMoneda= (String)getValueAt("selectMoneda");
		String sSelectCuentaOrden= (String) getValueAt("selectCuentaOrden");
		//String selectOpe= (String)getValueAt("selectOpe");
		//String sTipoIdCliente= "03";
		
		if (sMonto==null || sSelectMoneda==null || sSelectCuentaOrden==null){
			Trace.trace(Trace.Debug, "", "### cotizador()- recibe nulos");
		}else {
			try{
				
		    OperacionMulticanal OmCotizador = creaOM("om_dynamic_currency_service");	
			Double monto =formatearImporteEntrada(sMonto);
			Trace.trace(Trace.Debug, "", "### cotizador()- datos numericos OK");
			SimpleDateFormat dateFor = new SimpleDateFormat(DINAMIC_PRICING_DATE_FORMAT);
			DinamicCurrencyRequest request = new DinamicCurrencyRequest();
			request.setOperationType("PURCHASE");
			request.setIsSimulation(true);
			request.setCreationDate(dateFor.format(new Date()));
			Customer customer = new Customer("NIT", "12345");
			request.setCustomer(customer);
			ExchangeRate exchangeRate = new ExchangeRate();
			exchangeRate.setAmount(monto);
			exchangeRate.setBaseCurrency("COP");
			exchangeRate.setTargetCurrency(sSelectMoneda);
			request.setExchangeRate(exchangeRate);
			OmCotizador.setValueAt("Entrada.postDynamic.dynamicObject", request);
			OmCotizador.execute();
			DinamicCurrencyResponse dinacmicResponseDto =(DinamicCurrencyResponse)OmCotizador.getValueAt("Salida.dynamicObject");
			Data data= dinacmicResponseDto.getData();
			double tasaDivisa=data.getEquivalentDollars().getAmount();
			double tasaDivisaPeso=0.0;
			for(ItemizeRate in : data.getRates().getItemizeRates()) {
				if(in.getRateType().equalsIgnoreCase("dollarRate")) {
					tasaDivisaPeso=in.getItemizeRatesUnit().get(0).getAmount();
				}
			}
			double tasaUsdPeso= data.getTransactionSummary().getTargetRate();
			double equivPesos = data.getOperation().getTotalAmount().getAmount();
			
			//double tasaDivisa= new Double("1");
			//double tasaDivisaPeso=  new Double("3951");
			//double tasaUsdPeso= new Double("3951");
			//double equivPesos = new Double("39510");
			
			String timer = "0030";
			String piloto="S";
			String codMontoMax ="NA";
            String descErrorMonto ="";
            
            kCotizadorOP = (KeyedCollection) getElementAt("cotizacion");
            kCotizadorOP.setValueAt("tasaDivisa", tasaDivisa);
			kCotizadorOP.setValueAt("tasaDivisaPeso",tasaDivisaPeso);
			kCotizadorOP.setValueAt("tasaUsdPeso", tasaUsdPeso);
		    kCotizadorOP.setValueAt(EQUIV_PESO, equivPesos);
			kCotizadorOP.setValueAt("timerS", timer);
			kCotizadorOP.setValueAt("piloto", piloto);
			    
            if(codMontoMax.equalsIgnoreCase("")){
				   throw new Exception();
            } else{
                	Trace.trace(Trace.Debug, "", "### cotizador()- sOmCotizador2 - EQVPES: " + equivPesos);
                	Trace.trace(Trace.Debug, "", "### cotizador()- sOmCotizador2 - TIMER: " + timer);
                	Trace.trace(Trace.Debug, "", "### cotizador()- sOmCotizador2 - PILOT: " + piloto );
    				setValueAt(EQUIV_PESO, equivPesos);
    				setValueAt("timerS", timer);
    				setValueAt("piloto", piloto);	
    				setValueAt("descErrorMonto", descErrorMonto);
                }
			}catch(Exception eom){
				Trace.trace(Trace.Debug, "", "### cotizador()- sOmCotizador Fallo la cotizacion " + eom);
				kCotizadorOP = (KeyedCollection) getElementAt("cotizacion");
				kCotizadorOP.setValueAt("tasaDivisa", new Double("0"));
				kCotizadorOP.setValueAt("tasaDivisaPeso", new Double("0"));
				kCotizadorOP.setValueAt("tasaUsdPeso", new Double("0"));
				kCotizadorOP.setValueAt(EQUIV_PESO, new Double("0"));
				setValueAt("tasaDivisaPeso2", new Double("0"));
				setValueAt(EQUIV_PESO_2, new Double("0"));
			}
		}
		setValueAt("pestana", "2");
		setEstado("3");
		
		Trace.trace(Trace.Debug, "", " Fin cotizador()");
		} catch (Exception e) {
			Trace.trace(Trace.Debug, "", "### cotizador()- ERRORR PREVIO sOmCotizador ejecutada");
			Trace.trace(Trace.Debug, "", "### cotizador()- "+  e);
		}
	}
	
	
	
	public void consultarAvance() throws Exception{

		//INI INC 76 AVANCE 0 FX 16-07-2018
		String resultadoAvance =(String) getValueAt("avanceOpe");
		resultadoAvance = formateoAvance(resultadoAvance);
		Trace.trace(Trace.Debug, "", " Inicio consultarAvance() Formateado: " + getValueAt("selectMoneda") + " | " + resultadoAvance);
		//FIN INC 76 AVANCE 0 FX 16-07-2018
		
			Trace.trace(Trace.Debug, "", " Inicio consultarAvance(): " + getValueAt("selectMoneda") + " | " + getValueAt("avanceOpe"));

		om = creaOM(sOmConsultaAvance);
		om.setValueAt("Entrada.BPALACCE", "V");
		om.setValueAt("Entrada.BPALACC2", getValueAt("selectMoneda"));
		// INI INC 76 AVANCE 0 FX 16-07-2018
		// om.setValueAt("Entrada.BCODACCC", getValueAt("avanceOpe"));
		om.setValueAt("Entrada.BCODACCC", resultadoAvance);
		// FIN INC 76 AVANCE 0 FX 16-07-2018
		om.setValueAt("Entrada.BNITCOMP", ""); //VARIOS NIT F2 - OSNEIDER A. CMC - 16-09-2019
		// INI INC 104 CMC FX 18-10-2018
		String sMonto = (String) getValueAt("monto");
		Trace.trace(Trace.Debug, "", "### consultarAvance()- sMonto" + sMonto);
		Double monto = formatearImporteEntrada(sMonto);
		Trace.trace(Trace.Debug, "", "### consultarAvance()- FORMATEADO monto" + monto);
		om.setValueAt("Entrada.BIMPORTE", monto);
		// FIN INC 104 CMC FX 18-10-2018
		ejecutarOM(om);
		Trace.trace(Trace.Debug, "", "### Flag Flag Flag Flag ");
		try {
			String Flag2 = (String) om.getValueAt(SALIDA_COD_AVISO);
				String Flag3Error = (String) om.getValueAt(SALIDA_COD_ERROR);
				Trace.trace(Trace.Debug, "", "### Fin consultarAvance() Flag3Error " + Flag3Error);
				if(null!=Flag3Error && (Flag3Error.equalsIgnoreCase("CNE0019") || Flag3Error.equalsIgnoreCase("CNE0020")))//INCIDENCIA 154 FC CMC 22/01/2019
				{
					Flag2=Flag3Error;
				}
			Trace.trace(Trace.Debug, "", "### Fin consultarAvance() Flag " + Flag2);
			setValueAt("indAvance", Flag2);			
			
			//INI INC 85 FX CMC 09-11-2018
				String tasaDivisaV = (String) om.getValueAt("Salida.TASA-DIVISA");
				String equivalentePessoV = (String) om.getValueAt("Salida.EQUIVALENTE-PESOS");
				String divisCopV = (String) om.getValueAt("Salida.DIVISA-COP");
				

				//INI incidencia 156 FC CMC 25/01/2019
				Double tasaAvance = 0.0;
				Double equivPesosMonto = 0.0;
				tasaAvance = Double.parseDouble(tasaDivisaV);
				equivPesosMonto = monto*tasaAvance;
				
				Trace.trace(Trace.Debug, "", "***Fin consultarAvance() tasaDivisaPeso: " + tasaDivisaV);
				Trace.trace(Trace.Debug, "", "***Fin consultarAvance() equivalentePessoV: " + equivPesosMonto);
				Trace.trace(Trace.Debug, "", "***Fin consultarAvance() divisCopV: " + divisCopV);
									
				setValueAt("tasaDivisaPeso2", Double.valueOf(tasaDivisaV.trim()));
				setValueAt(EQUIV_PESO_2, equivPesosMonto);
				
				/*
				Trace.trace(Trace.Debug, "", "***Fin consultarAvance() tasaDivisaPeso: " + tasaDivisaV);
				Trace.trace(Trace.Debug, "", "***Fin consultarAvance() equivalentePessoV: " + equivalentePessoV);
				Trace.trace(Trace.Debug, "", "***Fin consultarAvance() divisCopV: " + divisCopV);
									
				setValueAt("tasaDivisaPeso2", Double.valueOf(tasaDivisaV.trim()));
				setValueAt("equivPesos2", Double.valueOf(equivalentePessoV.trim()));
				*/
				//FIN indicencia 156 FC CMC 25/01/2019
			//FIN INC 85 FX CMC 09-11-2018
		} catch (Exception e) {
			Trace.trace(Trace.Debug, "", "### ERROR consultarAvance() e " + e.toString());
		}

	}
	
	public void mostrarMas() throws Exception {
		Trace.trace(Trace.Debug, "", " Inicio mostrarMas()- " + getValueAt("selectOpe"));
		int pagina = Integer.parseInt(getValueAt("pagina").toString())+1;
		setValueAt("pagina", String.valueOf(pagina));
		Trace.trace(Trace.Debug, "", "### mostrarMas()- Pagina: " + pagina);
		PaginarHost(pagina);
		String selectOpe= (String)getValueAt("selectOpe"); // o tipoOperacion
		if(selectOpe.equals("T")){
			setEstado("1");
		}else if(selectOpe.equals("H")){
			setEstado("2");
		}
		Trace.trace(Trace.Debug, "", " Fin mostrarMas()- ");
	}
	
public void mostrarAtras() throws Exception {
		Trace.trace(Trace.Debug, "", " Inicio mostrarAtras()- " + getValueAt("selectOpe"));
		int pagina=Integer.parseInt((String) getValueAt("pagina"));
			if(pagina>1){
				int tamPagina = Integer.parseInt((String) getValueAt("tamPagina"));
				Trace.trace(Trace.Debug, "", "### mostrarAtras()- tamPagina: " + tamPagina);
				int regInicio = (pagina - 1) * tamPagina;
				int regFin = ((pagina) * tamPagina);

				IndexedCollection listaFicherosTotal = (IndexedCollection) getElementAt("listaGirosTotal");
				Trace.trace(Trace.Debug, "", "### mostrarAtras()- listaFicherosTotal: OK!" );
		
				if (regFin > listaFicherosTotal.size())
					regFin = listaFicherosTotal.size();

				for (int i = regFin-1; i > regInicio-1; i--) {
					listaFicherosTotal.removeElementAt(i);
				}
				Trace.trace(Trace.Debug, "", "### mostrarAtras()- Pagina: " + pagina);
				paginar(pagina-1);
				String selectOpe= (String)getValueAt("selectOpe"); // o tipoOperacion
				if(selectOpe.equals("T")){
					setEstado("1");
				}else if(selectOpe.equals("H")){
					setEstado("6");//Incidencia 125 FX CMC 23/2018
				}
				
				setValueAt("hayMas", "S");
				Trace.trace(Trace.Debug, "", " Fin mostrarAtras()- ");
			}else{
				setEstado(getEstado());
				Trace.trace(Trace.Debug, "", " Fin mostrarAtras()- Pagina inicial, no hay elementos por recorrer.");
			}
	}
	
	protected void paginar(int paginaActual) throws BbvaNGException {

		Trace.trace(Trace.Information, getClass().getName(), "### paginar () Inicio");
		int pagina = 1;
		try {
//			if (esPrimeraVez)  
//				setValueAt("hayMas", "S");
//			else
				pagina = paginaActual ;

			int tamPagina = Integer.parseInt((String) getValueAt("tamPagina"));
			
			//TEMPORAL INI
			Trace.trace(Trace.Error, getClass().getName()+ "### paginar() ", "tamPagina " + tamPagina);
			//TEMPORAL FIN
			int regInicio = (pagina - 1) * tamPagina;
			int regFin = ((pagina) * tamPagina);

			IndexedCollection listaFicherosTotal = (IndexedCollection) getElementAt("listaGirosTotal");
			IndexedCollection salida = (IndexedCollection) getElementAt("listaGiros");
//			Acumular las consultas 
			if(pagina<=1){
				salida.removeAll();
			}
			if (regFin > listaFicherosTotal.size())
				regFin = listaFicherosTotal.size();

			for (int i = regInicio; i < regFin; i++) {
				KeyedCollection kSalida = (KeyedCollection) listaFicherosTotal.getElementAt(i);
				salida.addElement(kSalida);
			}
			setValueAt("pagina", String.valueOf(pagina));
			setValueAt("registro", new Integer(regInicio));

		} catch (Exception e) {
			setEstado("ERROR");	
			Trace.trace(Trace.Error, getClass().getName()+ "### paginar() ", "Error al generar el nuevo listado " + e.getMessage());
			BbvaNGException eNGE = new BbvaNGException(null, null, null, e);
			throw eNGE;
		}
		Trace.trace(Trace.Information, getClass().getName(), "### paginar ()------> Fin");
	}
	
	protected void PaginarHost(int pagina) throws Exception {
		Trace.trace(Trace.Information, getClass().getName(), "### PaginarHost () Inicio");
		try {
			
				/*INI INC 185 PAGINACION FNGE CMC 19-06-2019 
				IndexedCollection listaFicherosTotal = (IndexedCollection) getElementAt("listaGirosTotal");

				KeyedCollection kSalida = (KeyedCollection) listaFicherosTotal.getElementAt(listaFicherosTotal.size()-1);
				Double idHost = new Double(kSalida.getValueAt("idMovimiento").toString()); */
				copiarAOMGiros(pagina); //FIN INC 185 PAGINACION FNGE CMC 19-06-2019
				OperacionMulticanal OMConsultaOper = creaOM(sOmGiros);
				ejecutarOM(OMConsultaOper);
				copiarDeOMGiros();
				paginar(pagina);
			

		} catch (BbvaNGException e) {
			Trace.trace(Trace.Error, getClass().getName() + "### PaginarHost () ",
					"Error al generar el nuevo listado " + e.getMessage());

			if (((BbvaException) e).getCodigo().equals("0205")) {
				String codError = (String) getValueAt(getNombreOM() + "-data.OMComun.CodigoError");
				String descripc = (String) getValueAt(getNombreOM() + "-data.OMComun.DescripcionError");

				((BbvaException) e).setCodigo(codError);
				if (descripc != null && descripc.indexOf("EYE0001") != -1) {
					descripc = "La consulta no obtuvo resultados";
				}
				((BbvaException) e).setMsg(descripc);
			}

			Trace.trace(Trace.Information, getClass().getName(), "### PaginarHost ()------> Fin");
			
			setEstado("ERROR");	
			BbvaARQException Barq = ManejarExcepcion(1, "", "", "", e, "",this, "", "");
			ManejarExcepcion(4, "", "", "", Barq, "", this, "", "");
			throw Barq;
		}

	}
	
	protected void copiarAOMGiros(int idPaginaHost) throws Exception{ //INC 185 PAGINACION FNGE CMC 19-06-2019
		KeyedCollection kEntradaGiros ;
		String fechaDesde = "", fechaHasta = "";
		try {
			if( getValueAt("filtro")!=null){
				fechaDesde= (String) getValueAt("fechaDesde");
				fechaHasta= (String) getValueAt("fechaHasta");
				try{
					fechaDesde= fechaDesde.substring(6, 10) + fechaDesde.substring(2, 5) + "-" + fechaDesde.substring(0, 2);
					fechaHasta= fechaHasta.substring(6, 10) + fechaHasta.substring(2, 5) + "-" + fechaHasta.substring(0, 2);
				}catch(Exception e){
					fechaDesde ="";
					fechaHasta =""; 
				}
			}
			
			Trace.trace(Trace.Debug, "", "Inicio copiarAOMGiros()... fechaDesde :" + fechaDesde);
			Trace.trace(Trace.Debug, "", "### copiarAOMGiros()... fechaHasta :" + fechaHasta);
			kEntradaGiros = (KeyedCollection)getElementAt("consulta_operaciones_negociacion_om-data.entrada");
			kEntradaGiros.setValueAt("FECHIN", fechaDesde);
			kEntradaGiros.setValueAt("FECHFN", fechaHasta);
			kEntradaGiros.setValueAt("TIPOPER", (String)getValueAt("tipoOperacion"));
			//INI NITS CMC 11-07-2019
			String tipoOp=(String)getValueAt("tipoOperacion");
			if (null!=tipoOp && tipoOp.trim().equalsIgnoreCase("H")) {
				kEntradaGiros.setValueAt("NIT","");
			}
			//FIN NITS CMC 11-07-2019
			kEntradaGiros.setValueAt("NCUENT",  getValueAt("selectCuentaOrden"));
			Trace.trace(Trace.Debug, "", "Inicio copiarAOMGiros()... Cta " + getValueAt("tipoOperacion") + " / " + getValueAt("selectCuentaOrden"));
			String user = getValueAt("s_cod_usuarisc").toString();
			Trace.trace(Trace.Debug, "", "### copiarAOMGiros()... Usuario " + user);
			for (int i=user.length();i<8;i++)	{ 
				user=user + "X"; 
			}
			String numclieTMP = getValueAt("s_cod_logon").toString() + user + "0000000NC";
			Trace.trace(Trace.Debug, "", "### copiarAOMGiros()... numclieTMP :" + numclieTMP);
			kEntradaGiros.setValueAt("NUMCLIE", numclieTMP);
			//INI INC 185 PAGINACION FNGE CMC 19-06-2019
			//kEntradaGiros.setValueAt("MOVIMT", idPaginaHost);	
			kEntradaGiros.setValueAt("MOVIMT", new Double("0"));
			kEntradaGiros.setValueAt("NUMPAGN", ""+idPaginaHost); 
			kEntradaGiros.setValueAt("TAMPAGN",  "10");
			//FIN 185 PAGINACION FNGE CMC 19-06-2019

			Trace.trace(Trace.Debug, "", " Fin copiarAOMGiros()");
			Trace.trace(Trace.Debug, "", "### copiarAOMGiros() + REFERENCIA " + numclieTMP);
			Trace.trace(Trace.Debug, "", "### copiarAOMGiros() + CODUSUARIO " + getValueAt("s_cod_usuarisc").toString());
			
			} catch (Exception e) {
				setEstado("ERROR");	
				Trace.trace(Trace.Debug, "", "### ERROR en copiarAOMGiros()");
				e.printStackTrace();
			}
	}
	
	private void copiarDeOMGiros() throws Exception {
		IndexedCollection listaGirosOP, listaGirosOM;
		int idEstado ;
		String CODSWIFT="", BENEFIC="", BANBENE="", FNUMCA="", ESTADO ="";
		try {
			Trace.trace(Trace.Debug, "", " Inicio copiarDeOMGiros().");
			//INI INC 185 PAGINACION FNGE CMC 19-06-2019
			String hayMasPag =(String) getValueAt("consulta_operaciones_negociacion_om-data.salida.IDPAG");
						
			if (null!=hayMasPag && hayMasPag.trim().equalsIgnoreCase("S")){
				setValueAt("hayMas", "S");
			}else{
				setValueAt("hayMas", "N");
			}
			//FIN  INC 185 PAGINACION FNGE CMC 19-06-2019
			
			listaGirosOM = (IndexedCollection) getElementAt("consulta_operaciones_negociacion_om-data.salida.listaGiros");
			listaGirosOP= (IndexedCollection) getElementAt("listaGirosTotal");
			if(getValueAt("pagina").toString().equals("1"))
			listaGirosOP.removeAll();
			Trace.trace(Trace.Debug, "", "### copiarDeOMGiros() - registros eliminados / Cant Registros OM: " + listaGirosOM.size());
			
			//Se eliminan registros que vienen vacios
			for (int i = 0; i <=listaGirosOM.size()-1 ; i++) {
				KeyedCollection kGiroOp =(KeyedCollection) DataElement.getExternalizer().convertTagToObject(listaGirosOP.getElementSubTag());
				KeyedCollection kGiro =(KeyedCollection) listaGirosOM.getElementAt(i);
				
				if (String.valueOf(kGiro.getValueAt("MOVIMT")).trim().compareTo("")==0 || String.valueOf(kGiro.getValueAt("MOVIMT")).trim().toLowerCase().compareTo("null")==0){
					listaGirosOM.removeElementAt(i);
					i=i-1;
				}else{
					//Tratamiento de fechas y horas
					SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat fmt2 = new SimpleDateFormat("dd/MM/yyyy");
					//fx ini inc 31 13/02/2018
					SimpleDateFormat fmt3 = new SimpleDateFormat("yyyy/MM/dd");
					//fx fin inc 31 13/02/2018
					SimpleDateFormat fmtH = new SimpleDateFormat("HH.mm");
					SimpleDateFormat fmtH2 = new SimpleDateFormat("hh:mm aa");
					//Trace.trace(Trace.Debug, "", "### copiarDeOMGiros() - controlando 1");//Depuracion de trazas
					//Limpiar Operaciones genericas INI
					if (kGiro.getValueAt("CODSWIFT")!=null){
						//Trace.trace(Trace.Debug, "", "### copiarDeOMGiros() - controlando 1a");//Depuracion de trazas
						 CODSWIFT = (String) kGiro.getValueAt("CODSWIFT");
					}

					//Trace.trace(Trace.Debug, "", "### copiarDeOMGiros() - controlando 2");//Depuracion de trazas
					if (kGiro.getValueAt("BENEFIC")!=null){
						//Trace.trace(Trace.Debug, "", "### copiarDeOMGiros() - controlando 1b");//Depuracion de trazas
						BENEFIC = (String) kGiro.getValueAt("BENEFIC");
					}

					//Trace.trace(Trace.Debug, "", "### copiarDeOMGiros() - controlando 3");//Depuracion de trazas
					if (kGiro.getValueAt("BANBENE")!=null){
						//Trace.trace(Trace.Debug, "", "### copiarDeOMGiros() - controlando 1c");//Depuracion de trazas
						BANBENE = (String) kGiro.getValueAt("BANBENE");
					}

					//Trace.trace(Trace.Debug, "", "### copiarDeOMGiros() - controlando 4");//Depuracion de trazas
					if (kGiro.getValueAt("FNUMCA")!=null){
						//Trace.trace(Trace.Debug, "", "### copiarDeOMGiros() - controlando 1d");//Depuracion de trazas
						FNUMCA = (String) kGiro.getValueAt("FNUMCA");
					}

					//Trace.trace(Trace.Debug, "", "### copiarDeOMGiros() - controlando 5");//Depuracion de trazas
					if (kGiro.getValueAt("ESTADO")!=null){
						//Trace.trace(Trace.Debug, "", "### copiarDeOMGiros() - controlando 1d");//Depuracion de trazas
						ESTADO = (String) kGiro.getValueAt("ESTADO");
					}

					//Trace.trace(Trace.Debug, "", "### copiarDeOMGiros() - controlando 5");//Depuracion de trazas
					

					if(FNUMCA.equals("5E02904") && CODSWIFT.equals("GEROCOBB") && BENEFIC.equals("SYSTEM") && BANBENE.equals("BBVA")){
						//Trace.trace(Trace.Debug, "", "### copiarDeOMGiros() - controlando 6 - data beneficiario vacia");//INC 211 FX CMC 10/04/2019 Depuracion de trazas
						kGiro.setValueAt("BENEFIC", "  ");
						kGiro.setValueAt("FNUMCA", "  ");
						kGiro.setValueAt("PAIS", "  ");
						kGiro.setValueAt("BANBENE", "  ");
						kGiro.setValueAt("CODSWIFT", "  ");
						//INI INC 211 FX CMC 10/04/2019
						if (ESTADO==null || ESTADO.trim().length()==0){
						kGiro.setValueAt("ESTADO", "1");
						//Trace.trace(Trace.Debug, "", "### copiarDeOMGiros() - Estado de operacion 1");//Depuracion de trazas
						}
						//FIN INC 211 FX CMC 19/04/2019
					}
					


					if(FNUMCA.equals("5E02918") && getValueAt("tipoOperacion").equals("H") && (ESTADO.equals("2")||ESTADO.equals("11"))){
						kGiro.setValueAt("FNUMCA", "  ");
						kGiro.setValueAt("ESTADO", "1");
					}

					//Limpiar Operaciones genericas FIN
					
					String fechaOpe= String.valueOf(kGiro.getValueAt("FCHOPE"));
					String fechaVal= String.valueOf(kGiro.getValueAt("FCHVAL"));
					String horaOpe= String.valueOf(kGiro.getValueAt("HOPERA"));
					String estado= String.valueOf(kGiro.getValueAt("ESTADO")).trim();
					String monto= String.valueOf(kGiro.getValueAt("MONTO")).trim();			
					String tasaDiv= String.valueOf(kGiro.getValueAt("TASADIV")).trim();	
					String tasaPeso= String.valueOf(kGiro.getValueAt("TASAPE")).trim();	
					String tasaUsd= String.valueOf(kGiro.getValueAt("TASAUSD")).trim();	
					String equivPeso= String.valueOf(kGiro.getValueAt(EQUIPES)).trim();
					if(fechaOpe.compareTo("null")!=0)
					kGiro.setValueAt("FCHOPE", fmt3.format(fmt.parse(fechaOpe)));
					
					//fx ini INC 31 13/02/2018
					setValueAt("mostrarFechaOpera", fmt2.format(fmt.parse(fechaOpe)));
					//FX fin INC 31 13/02/2018
					
					if(fechaVal.compareTo("null")!=0)
					kGiro.setValueAt("FCHVAL", fmt2.format(fmt.parse(fechaVal)));
					if(horaOpe.compareTo("null")!=0)
					kGiro.setValueAt("HOPERA", fmtH2.format(fmtH.parse(horaOpe)));
					//Tratamiento de estado
					kGiro.setValueAt("ESTADO", "");
					/*INI INC 61 - ESTADO - FX - 05-06-2015*/
					if(getValueAt("tipoOperacion").equals("T") || getValueAt("tipoOperacion").equals("H")){
						kGiro.setValueAt("ESTADO", estado);
					}
					
					//Tratamiento de Importes
					if(monto.compareTo("null")!=0)
					kGiro.setValueAt("MONTO", new Double(monto));
					if(tasaDiv.compareTo("null")!=0)
					kGiro.setValueAt("TASADIV", new Double(tasaDiv));
					if(tasaPeso.compareTo("null")!=0)
					kGiro.setValueAt("TASAPE", new Double(tasaPeso));
					if(tasaUsd.compareTo("null")!=0)
					kGiro.setValueAt("TASAUSD", new Double(tasaUsd));
					if(equivPeso.compareTo("null")!=0)
					kGiro.setValueAt(EQUIPES, new Double(equivPeso));
					
					Trace.trace(Trace.Debug, "", "### copiando copiarDeOMGiros()");
					copiarKeyedCollection(kGiro, kGiroOp);
						
						//INI INC 132 FX 12-12-2018
						Trace.trace(Trace.Debug, "", "### TERMINO DE COPIAR INFO GIRO()");
						try{
							String vfechaValor = (String) kGiroOp.getValueAt("fechaValor");//INC 217 FX CMC 26/06/2019
							String vfechaOpe = (String) kGiroOp.getValueAt("fechaOperacion");
							String vnumeOpe = (String) kGiroOp.getValueAt("nOperacion");
							String smonedaX = (String) kGiroOp.getValueAt("moneda");
							String vbenef = (String) kGiroOp.getValueAt(VALUE_BENEFI);
							String vordenanteX = (String) kGiroOp.getValueAt(VALUE_ORDEDANTE);
							String vidOrdenanteX = (String) kGiroOp.getValueAt("idOrdenante");	
							String vdescripTrans = (String) kGiroOp.getValueAt("descTransaccion");	
							String vrefExtranjero = (String) kGiroOp.getValueAt("idMovimiento");	
							String vestadoOp = (String) kGiroOp.getValueAt("estado");
							
							vfechaValor =replaceCaracteres(vfechaValor);//INC 217 FX CMC 26/06/2019
							vfechaOpe =replaceCaracteres(vfechaOpe);
							vnumeOpe =replaceCaracteres(vnumeOpe);
							smonedaX =replaceCaracteres(smonedaX);
							vbenef =replaceCaracteres(vbenef);
							vordenanteX =replaceCaracteres(vordenanteX);
							vidOrdenanteX =replaceCaracteres(vidOrdenanteX);
							vdescripTrans =replaceCaracteres(vdescripTrans);
							vrefExtranjero =replaceCaracteres(vrefExtranjero);
							vestadoOp =replaceCaracteres(vestadoOp);

							kGiroOp.setValueAt("fechaValor", vfechaValor);//INC 217 FX CMC 26/06/2019
							kGiroOp.setValueAt("fechaOperacion", vfechaOpe);	
							kGiroOp.setValueAt("nOperacion", vnumeOpe);
							kGiroOp.setValueAt("moneda", smonedaX);
							kGiroOp.setValueAt(VALUE_BENEFI, vbenef);
							kGiroOp.setValueAt(VALUE_ORDEDANTE, vordenanteX);
							kGiroOp.setValueAt("idOrdenante", vidOrdenanteX);
							kGiroOp.setValueAt("descTransaccion", vdescripTrans);
							kGiroOp.setValueAt("idMovimiento", vrefExtranjero);
							kGiroOp.setValueAt("estado", vestadoOp);						
						}catch(Exception ed){
							Trace.trace(Trace.Debug, "", "### ERROR CATCH()ed:"+ed);
						}
						//FIN INC 132 FX 12-12-2018
						listaGirosOP.addElement(kGiroOp);
				}
				
			}
			
			Trace.trace(Trace.Debug, "", " Fin copiarDeOMGiros()");
		} catch (Exception e) {
			setEstado("ERROR");	
			Trace.trace(Trace.Debug, "", "### ERROR copiarDeOMGiros() - copiando registros " + e);
			e.printStackTrace();
		}
		
	}
	
	private void copiarDeOMParametria() throws Exception {
		KeyedCollection kParametriaOM, kParametriaOP, kMoneda;
		String codigoMoneda;
		int tamano;
		try {
			kParametriaOM = (KeyedCollection) getElementAt(sOmParametria+DATA_SALIDA);
			IndexedCollection listaMonedas =(IndexedCollection) kParametriaOM.getElementAt("listaMonedas");
			//Se eliminan registros que vienen vacios
			tamano =listaMonedas.size();
			Trace.trace(Trace.Debug, "", " Inicio copiarDeOMParametria() MOneda.. tamano" + tamano);
			for (int i = 0; i < tamano ; i++) {
				kMoneda =(KeyedCollection) listaMonedas.getElementAt(i);
				codigoMoneda =(String) kMoneda.getValueAt("DESMON");
				//Trace.trace(Trace.Debug, "", "### copiarDeOMParametria() MOneda.." + i +" " + codigoMoneda);//Depuracion de trazas
				
			}
			//Formateo de Horas
			//Trace.trace(Trace.Debug, "", "Inicio copiarDeOMParametria() MOneda.. SETEADO" );
			SimpleDateFormat fmt = new SimpleDateFormat("HHmm");
			SimpleDateFormat fmtX = new SimpleDateFormat("hh:mm aa");
			
			HINI =  fmt.parse((String)kParametriaOM.getValueAt("HINI"));//INI incidencia 119 FX 14/11/2018
			HFIN =  fmt.parse((String)kParametriaOM.getValueAt("HFIN"));//INI incidencia 119 FX 14/11/2018
			HNINI =  fmt.parse((String)kParametriaOM.getValueAt("HNINI"));
			HNFIN =  fmt.parse((String)kParametriaOM.getValueAt("HNFIN"));
			
			kParametriaOM.setValueAt("HINI", fmtX.format(fmt.parse((String)kParametriaOM.getValueAt("HINI"))));
			kParametriaOM.setValueAt("HFIN", fmtX.format(fmt.parse((String)kParametriaOM.getValueAt("HFIN"))));
			kParametriaOM.setValueAt("HNINI",fmtX.format(fmt.parse((String)kParametriaOM.getValueAt("HNINI"))));
			kParametriaOM.setValueAt("HNFIN",fmtX.format(fmt.parse((String)kParametriaOM.getValueAt("HNFIN"))));
			String prueba = (String) kParametriaOM.getValueAt("HABIPSE");
			setValueAt(CONTIGENCIA, prueba.substring(0, 6));
			
			Trace.trace(Trace.Debug, "", "### copiarDeOMParametria() Moneda.. seteado OK" );
			
			kParametriaOP = (KeyedCollection) getElementAt("parametria");
			copiarKeyedCollection(kParametriaOM, kParametriaOP);
			Trace.trace(Trace.Debug, "", " FIN copiarDeOMParametria() MOneda.." );
		} catch (Exception e) {
			setEstado("ERROR");	
			Trace.trace(Trace.Debug, "", "### ERROR copiarDeOMParametria()   MOneda: " +  e);
			e.printStackTrace();
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
	
	private void validarHora() throws Exception{
		SimpleDateFormat formatoEntradaHora = new SimpleDateFormat("HHmm");
		Trace.trace(Trace.Debug, "", " Inicio validarHora()");
		Date fecha = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		int dia_semana = cal.get(Calendar.DAY_OF_WEEK);
		Trace.trace(Trace.Debug, "", "### validarHora actual - HINI/HFIN: " + HINI + "/" + HFIN);
		Trace.trace(Trace.Debug, "", "### validarHora actual - HNINI/HNFIN: " + HNINI + "/" + HNFIN);
		
		//INI incidencia 217 FX CMC 03/07/2019
		int diaActual = cal.get(Calendar.DAY_OF_MONTH);
		int mesActual = cal.get(Calendar.MONTH)+1;//nero = 0 Diciembre = 11
		int anoActual = cal.get(Calendar.YEAR);
		setValueAt("fechaHoy", anoActual +"-"+mesActual+"-"+diaActual);
		String fechaActual = (String)getValueAt("fechaHoy");
		//FIN incidencia 217 FX CMC 03/07/2019
		
		int actual = Integer.parseInt(formatoEntradaHora.format(fecha));
		int min = Integer.parseInt(formatoEntradaHora.format(HNINI));
		int max = Integer.parseInt(formatoEntradaHora.format(HNFIN));
		int minN = Integer.parseInt(formatoEntradaHora.format(HINI));
		int maxN = Integer.parseInt(formatoEntradaHora.format(HFIN));
		
		Trace.trace(Trace.Debug, "", "### validarHora Negociacion min/max/dia_semana: " +  min + "/" + max + "/" + dia_semana);
		Trace.trace(Trace.Debug, "", "### validarHora Modificacion min/max/dia_semana: " +  minN + "/" + maxN + "/" + dia_semana);
		
		if((actual<min || actual >= max)||dia_semana==1||dia_semana==7){
			setValueAt("indHorario", "No");
		}else{
			setValueAt("indHorario", "Si");
		}
		if((actual<minN || actual >= maxN)||dia_semana==1||dia_semana==7){
			setValueAt("indHorarioModif", "No");
		}else{
			setValueAt("indHorarioModif", "Si");
		}
		
		//INI INC 158 FX CMC 21/01/2019
		if(validarFestivos()) {
			setValueAt("indHorario", "No");
			setValueAt("indHorarioModif", "No");
		}
		//FIN INC 158 FX CMC 21/01/2019	
	}
	
	public void crearSimilar() {
		KeyedCollection kCuenta,kEntradaParametria;
		IndexedCollection icContextoSesion,listaCuentasOP = null;
		Enumeration enContextoSesion1;
				
		String clave = "";
		
		Trace.trace(Trace.Debug, "", " Inicio crearSimilar()");
		try {
			//INI INC 125 CMC 11-01-2019
			clave=cargarCuentas(); 
			/*
			icContextoSesion = (IndexedCollection) getElementAt("s_salida_cuentas.s_lista_cuentas");
			listaCuentasOP = (IndexedCollection) getElementAt("lista_cuentas");
			copiarIndexedCollection(icContextoSesion, listaCuentasOP);
			enContextoSesion1 = listaCuentasOP.getEnumeration();
			FIN INC 125 CMC 11-01-2019	*/
			Trace.trace(Trace.Debug, "", "### crearSimilar() - copiar icolls " );
			
			setValueAt("indBenef", "");
			String tipo=(String)getValueAt("tipoOperacion");
			
			Trace.trace(Trace.Debug, "", " crearSimilar() - Inicializar cotizador tipo " + tipo);
			/*INI INC 125 CMC 11-01-2019
			while(enContextoSesion1.hasMoreElements()){
					kCuenta = (KeyedCollection) enContextoSesion1.nextElement();
					clave = (String) kCuenta.getValueAt("clave_asunto");
				}				
			FIN INC 125 CMC 11-01-2019	*/
			kEntradaParametria = (KeyedCollection) getElementAt(sOmParametria+"-data.entrada");
			kEntradaParametria.setValueAt("NCUENTA",  clave);
			OperacionMulticanal OmParametria = creaOM(sOmParametria);
//INI CMC - FUNCION DE NEGOCIO FNDO - PRUEBA - 19/09/2019
			controlOM = new OpControl();
			controlOM.setConfig((String)getValueAt("datosAPP.pb_cod_serv"),
								(String)getValueAt("datosAPP.iv-cod_emp")+(String)getValueAt("datosAPP.iv-cod_usu"),
								(String)getValueAt("datosAPP.iv-cod_usu"),
								creaOM("sign_on_om"), 
								creaOM("sign_on_om"),
								OmParametria, 
								creaOM(OmParametria.getName()));
			controlOM.control_f100();
//FIN CMC - FUNCION DE NEGOCIO FNDO - PRUEBA - 19/09/2019
			copiarDeOMParametria();
			
			String msjErrorCS=(String)getValueAt("msjError");
			String[] parts = msjErrorCS.split("-");
			Trace.trace(Trace.Debug, "", "### crearSimilar() - Parametria cargada - monedas / msjError: " + msjErrorCS);
			String montoCS = parts[0];
			String monedaCS = parts[1];
			
			setValueAt("selectOpe", tipo);
			setValueAt("selectCuentaOrden", clave);
			setValueAt("selectMoneda", monedaCS);
			setValueAt("monto", montoCS);
			setValueAt("tipoOperacion", tipo);
			
			cotizador();
			validarCuenta();
			
			setValueAt("descripNegociacion", "");
			setValueAt("validaCta","");
			setValueAt("msjError", "");
				
			Trace.trace(Trace.Debug, "", "### negociarLinea() - Parametria y cotizador inicializados ");
			
			setEstado("7");
		
		}catch (Exception e) {
			setEstado("ERROR");
			Trace.trace(Trace.Debug, "", "### ERROR - Fin negociarLinea() " + e);
//INI CMC - FUNCION DE NEGOCIO - PRUEBA - 19/09/2019
		} finally {
			controlOM = null;
		}
//FIN CMC - FUNCION DE NEGOCIO - PRUEBA - 19/09/2019
		
	}
	
	private RequestBankTradeService cargarInfoBeneficiarioTMP(RequestBankTradeService peticion, String ctaOrdenante) throws Exception{
		
		String tipoFondoGiro = getValueAt(TIPO_FONDO_GIRO) != null ? (String) getValueAt(TIPO_FONDO_GIRO) : "";

		String nitSelect =  getValueAt("num_cuenta")!= null ? (String) getValueAt("num_cuenta") : "";	
		peticion.setIdSwTpBcoOrd(null);
		peticion.setNomCam56InfBene1(null);
		peticion.setNomCam56InfBene2(null);
		peticion.setNomCam56InfBene3(null);
		peticion.setNomCam56InfBene4("");
		
		peticion.setNomCam57BanBene1("BBVA");
		peticion.setNomCam57BanBene2("SYSTEM");
		peticion.setNomCam57BanBene3("SYSTEM");
		peticion.setSwiftcampo57A("GEROCOBB");
	
		peticion.setIdCampo59("0013000000001");
		peticion.setNomCam59Bene1("SYSTEM");
		peticion.setNomCam59Bene2("SYSTEM");
		peticion.setNomCam59Bene3("BO");
		peticion.setNomCam59Bene4("SYSTEM");
		if(tipoFondoGiro.equalsIgnoreCase(FONDO_PSE)) {
			peticion.setDbPrincipal("PSE");
			peticion.setDeComm("PSE");
			peticion.setNoCtaComm(nitSelect);
		}
		else {
			peticion.setDbPrincipal(tipoCuentaBT(ctaOrdenante));
			peticion.setDeComm(tipoCuentaBT(ctaOrdenante));
			peticion.setNoCtaComm(ctaOrdenante);
		}
		peticion.setNoCtaPrincipal(ctaOrdenante);	
		peticion.setNoCtaPrincipalCre(ctaOrdenante);//INC BT15.1 PRD FX 05-07-2019
	
		return peticion;
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
	
	//	fx ini inc 6,1 12/06/2018
	public static String FormateaFecha(String vFecha){
		String vDia, vMes, vAno;
		StringTokenizer tokens = new StringTokenizer(vFecha,"/"); 
		vDia = tokens.nextToken(); 
		vMes = tokens.nextToken();
		vAno = tokens.nextToken(); 
		return vAno+"/"+vMes+"/"+vDia; 
		}
	//	fx fin inc 6.1 12/06/2018
	//INI INC 76 AVANCE 0 FX 16-07-2018
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
	//FIN INC 76 AVANCE 0 FX 16-07-2018

	// INI CMC 08/11/2018 INC 102 FX
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
			;
		}

		return iDestino;
	}
	// FIN CMC 08/11/2018 INC 102 FX
	
	//INI INC 132 FX 12-12-2018
	private String replaceCaracteres(String valor){
		valor = valor.replaceAll("\"", "");
		valor = valor.replaceAll("\'", "");
		valor = valor.replaceAll("|@|", "");

		return valor;		
	}
	//FIN INC 132 FX 12-12-2018

	
    //INI incidencia 125 FX CMC 14/01/2019
	public String cargarCuentas()
	{
		KeyedCollection kCuenta, kcSalidaCuentasSrv, kcContextoSesion;
		IndexedCollection icContextoSesion,listaCuentasOP = null;
		Enumeration enContextoSesion1;
		String tipoPermiso = "";		
		String clave = "";
		
		Trace.trace(Trace.Debug, "", " Inicio OpConsultaOperaciones cargarCuentas().");
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
			Trace.trace(Trace.Debug, "", "### FIN OpConsultaOperaciones cargarCuentas().:" + clave);
			return clave;
		}catch(Exception ee){
				Trace.trace(Trace.Debug, "", "### Falla en carga de cuentas() *** :" + ee);
				return clave;
		}
	}//FIN incidencia 125 FX CMC 14/01/2019
	
	//INI incidencia 153 FX CMC 27/12/2018
	//Genera la operacion llamando solo una vez el servicio
	public void generarOperacionT() throws Exception
	{
		String descripNegociacion = (String) getValueAt("descripNegociacion");
		String selectMoneda = (String) getValueAt("selectMoneda");
		Date fechaActual = new Date();
		String sSelectCuentaOrden = (String) getValueAt("selectCuentaOrden");
		String deCTA = sSelectCuentaOrden.substring(10,12);
		String texto = "                                                                                                                                            ";
		String documentoCliente = (String) getValueAt("num_cuenta");	
        String codigoU ="";
		String banbene ="";
		String nitSelect = "";
		String tipoFondoGiro = getValueAt(TIPO_FONDO_GIRO) != null ? (String) getValueAt(TIPO_FONDO_GIRO) : "";
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
			Trace.trace(Trace.Debug, "", "### CODIGOUTRATADO "+codigoU );
		}
			if(codigoU.equalsIgnoreCase("")) {
			codigoU="' '";
		}
		Trace.trace(Trace.Debug, "", "### generarOperacionT() - inicio preparando datos: " + descripNegociacion);
		
		RequestBankTradeService peticion = new RequestBankTradeService();
		peticion.setFechaHoraNegociacion(fechaActual);
		peticion.setFeValor(fechaActual);
		peticion.setMonAvance(getValueAt("monto").toString());
		
		if(tipoFondoGiro.equalsIgnoreCase(FONDO_PSE)) {
			deCTA = "PSE";
			nitSelect = documentoCliente;
			sSelectCuentaOrden = documentoCliente;
			String rate = getValueAt("rate") != null ? eliminarNotacion(Double.valueOf((String) getValueAt("rate"))) : "";
			Trace.trace(Trace.Debug, "", "### generarOperacionT() - rate: " + rate);
			String totalDebito = getValueAt(TOTAL_DEBITO) != null ? eliminarNotacion(Double.valueOf((String) getValueAt(TOTAL_DEBITO))) : "";
			Trace.trace(Trace.Debug, "", "### generarOperacionT() - totalDebito: " + totalDebito);
			peticion.setUsdRateComm(rate);
			peticion.setMontoNetoPse(totalDebito);
		}else {
			if(deCTA.equals("01"))
			{
				deCTA = "CTE";
			}else if(deCTA.equals("02"))
			{
				deCTA = "AHO";	
			}
		}
		
		Trace.trace(Trace.Debug, "", "### generarOperacionT() - sSelectCuentaOrden: " + sSelectCuentaOrden);
		peticion.setNoCtaComm(nitSelect);
		Trace.trace(Trace.Debug, "", "### generarOperacionT() - deCTA: " + deCTA);
		peticion.setDeComm(deCTA);
		peticion.setNoCtaPrincipal(sSelectCuentaOrden);
		peticion.setNoCtaPrincipalCre(sSelectCuentaOrden);//INC BT15.1 PRD FX 05-07-2019
		
		if(getValueAt("selectOpe").equals("T1"))
		{
			Trace.trace(Trace.Debug, "", "### generarOperacionT() tipo T1 ");
			peticion.setTasaDivi(Double.valueOf(getValueAt("cotizacion.tasaDivisaPeso").toString()));
			peticion.setTasaAvance(getValueAt("tasaDivisaPeso").toString());
			peticion.setTasaUsd(Double.valueOf(getValueAt("cotizacion.tasaUsdPeso").toString()));
			peticion.setTasaLinea(Double.valueOf(getValueAt("cotizacion.tasaDivisa").toString()));
			peticion.setNoAvance("STD1");
		}else if (getValueAt("selectOpe").equals("T2") )
		{
			Trace.trace(Trace.Debug, "", "### generarOperacionT() tipo T2 " + getValueAt("avanceOpe").toString() + " / " + getValueAt("monto").toString());
			peticion.setNoAvance(getValueAt("avanceOpe").toString());
			peticion.setTasaDivi(Double.valueOf(getValueAt("cotizacion.tasaDivisaPeso").toString()));
			peticion.setTasaAvance(getValueAt("tasaDivisaPeso").toString());
			peticion.setTasaUsd(Double.valueOf(getValueAt("cotizacion.tasaUsdPeso").toString()));
			peticion.setTasaLinea(Double.valueOf(getValueAt("cotizacion.tasaDivisa").toString()));
		}
		
		peticion.setDeOperac(descripNegociacion);
		if (descripNegociacion.length() < 140) 
		{
			descripNegociacion = descripNegociacion + texto;
		}
		texto = descripNegociacion.substring(0,35);
		peticion.setInfcam70Sw1(texto);
		texto = descripNegociacion.substring(36,70);
		peticion.setInfcam70Sw2(texto);
		texto = descripNegociacion.substring(71,105);
		peticion.setInfcam70Sw3(texto);
		texto = descripNegociacion.substring(106,140);
		peticion.setInfcam70Sw4(texto);
		peticion.setIdCliente(documentoCliente);
		if(!codigoU.equalsIgnoreCase("")) {
			peticion.setIdCampo72(codigoU);	
		}
		peticion.setTyUsuario("JR");
		peticion.setMonNegoc(getValueAt("monto").toString());
				
		if(selectMoneda!= null && selectMoneda!= "")
		{
			selectMoneda= selectMoneda.substring(0,3);
		}
		
		setValueAt("selectMoneda", selectMoneda);
		peticion.setCurNegoc(selectMoneda);
		
		Trace.trace(Trace.Debug, "", "### generarOperacionT() Seteando - variables fijas ");
		peticion.setTyNegoci("**");
		peticion.setTyOperac("TFOC");
		peticion.setIdSucursal(BT_IDSUCURSAL);
		peticion.setCanal("80");
		peticion.setSubcanal("01");
		peticion.setCampana("CANALES");
		peticion.setTyVended("9");
		peticion.setNoVended("777777777777777");
		peticion.setDigVended("9");
		peticion.setTyTransa("T");
		peticion.setIndMoneda("");
		peticion.setID_BCOOrdenante("SYSTEM");
		peticion.setNomBcoOrd1("SYSTEM");
		
		//parte del 180
		peticion = cargarInfoBeneficiarioTMP(peticion, sSelectCuentaOrden);
		//peticion.setNoCtaPrincipalCre("SYSTEM");()); INC BT15.1 PRD FX 05-07-2019 Se está seteando en el metodo de cargarInfoBeneficiarioTMP
		peticion.setCurDbComm("COP");
		peticion.setTpCambioComm("1.000000000");												
		peticion.setCodOpecampo23b("CRED");
		peticion.setNuCambiario("5E02904");// se ingresa un Numeral Cambiario temporal
		peticion.setIdSwTpBcoOrd("SW");				
		setValueAt("msjError", "");
		setValueAt(LIT_PETICIO_WEB_SERV, peticion);
		setValueAt("referenciaOpe", "T2351");
		Trace.trace(Trace.Debug, "", "### generarOperacionT() Data xml:  " + peticion.toString());
		Trace.trace(Trace.Debug, "", "### generarOperacionT() Lanzando ws-...  ");
		try
		{
			ResponseBankTradeService response = WrapperBanktradeService.execute(peticion);
			if(response.getCodError() != null)
			{
				if(!response.getCodError().equals(""))
					{					
						this.setEstado("ERROR");
						String mensaje = ERROR_WS_BANKTRADE.concat(response.getSequence()+" "+response.getCodError());
						Trace.trace(Trace.Debug, "", "### generarOperacionT() error de web service: " + mensaje);
						//setValueAt("msjError", mensaje);
						setValueAt("msjError", mensaje);
						BbvaARQException Barq = ManejarExcepcion(3, mensaje, "", "", new Exception(), mensaje, this, "", "");
						return;
				}else
				{
					Trace.trace(Trace.Debug, "", "### generarOperacionT() respuesta OK ");
					Trace.trace(Trace.Debug, "", "### generarOperacionT() response.getNumOpera " + response.getNumOpera().toString());
					setValueAt("msjError", "");
					setValueAt(LIT_PETICIO_WEB_SERV, peticion);
					setValueAt("referenciaOpe", response.getNumOpera());
					if(getValueAt(TIPO_FONDO_GIRO) != null && getValueAt(TIPO_FONDO_GIRO).toString().equalsIgnoreCase(FONDO_PSE)) {
						getCommission(formatNit(getValueAt(VALUE_BUSINESS_ID).toString()), getValueAt(TIPO_FONDO_GIRO), getValueAt(AMOUNT_EQUIV),
								getValueAt(NUM_TRANS), getValueAt("selectCuentaOrden"), response.getNumOpera());
						updateOrden(getValueAt(NUM_TRANS), getValueAt(AMOUNT_EQUIV), response.getNumOpera());
					}
				}
			}else{
				Trace.trace(Trace.Debug, "", "### generarOperacionT() error de web service: RTA null");
				setValueAt("msjError", "RTA null");
			}
		}catch (Exception e)
		{
			//e.printStackTrace();	
			Trace.trace(Trace.Debug, "", "### generarOperacionT() Falla lanzando web service: " + e);
			String errorWS = "T";
			setValueAt("Error", errorWS);
			setValueAt("msjError", errorWS);
			this.setEstado("ERRORWS");
			return;				
		}	
		Trace.trace(Trace.Debug, "", " Fin generarOperacionT() ");	
	}
	//FIN incidencia 153 FX CMC 27/12/2018
	
	//INI INC 158 FX CMC 21/01/2019
	private boolean validarFestivos() {
	
	boolean respuesta=false;
	String rutaArchivo = "tlcl/batch/co/dat/di/ttlclfhf.txt";
		try {
			//INI INC 158 FX CMC 25/01/2019
			KeyedCollection sessionData3 = (KeyedCollection) DataElement.getExternalizer().readObject("SesionDatosGenerales");
			String entorno = (String)sessionData3.getValueAt("entorno");
			Trace.trace(Trace.Debug, "", "### entorno: " + entorno);
			//FIN INC 158 FX CMC 25/01/2019
			FileReader fr = new FileReader(entorno+File.separator+rutaArchivo);//ruta lectura
			Trace.trace(Trace.Debug, "", "### ruta archivo festivos " + entorno+File.separator+rutaArchivo);
			
			if (fr != null) {
				BufferedReader bf = new BufferedReader(fr);
				String linea = "";

				SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
				Date fechaHoy = new Date();
				Trace.trace(Trace.Debug, "", "### Empieza validacion dias archivo... ");
				
				while ((linea = bf.readLine()) != null) {
					String paisArchivo=linea.substring(2,4);
					if(paisArchivo.equals("82")) {
						String fechaFestivo = linea.substring(4);

						if (fechaFestivo.equals(formato.format(fechaHoy))) {
							respuesta=true;
							Trace.trace(Trace.Debug, "", "### Encuentra dia festivo:::...");
							break;
						}
					}					
				}
				Trace.trace(Trace.Debug, "", "### Fin Validacion dias festivos");
				fr.close();
			}						
			return respuesta;
		}
		catch (Exception e) {
			Trace.trace(Trace.Debug, "", "### Entro al catch de entorno" + e);
			return respuesta;
		}
	}
	//FIN INC 158 FX CMC 21/01/2019
	//INI INC 202 FX CMC 19/03/2019
	private boolean validaDescrip(String descrip)  {
		boolean validar= false;
		Pattern patron = Pattern.compile("[^A-Za-z0-9 ]");//INC 202 FX CMC 14/05/2019
		Matcher encaja = patron.matcher(descrip);
		try{
			if(!encaja.find()) {
				validar=true;
			}
			return validar;   
		}catch (Exception e) {
			Trace.trace(Trace.Debug, "", "### Entro al catch de validaDescrip" + e);
			return validar;
		}
	}
	//FIN INC 202 FX CMC 19/03/2019		
	//INI INC 213 FX CMC 07/05/2019
		private void validaMonto() throws Exception{
			int decimal = 0;
			String montoDecimal = "";
			String monto = "";
			try{				
				if(getValueAt("monto") != null && getValueAt("monto") != ""){
					monto = (String) getValueAt("monto");
					decimal = monto.indexOf(".");
					if(decimal >= 0) {
						montoDecimal = monto.substring(decimal,monto.length());
						if(montoDecimal.length() > 3){
							setValueAt("montoBandera", "s");	
						}else{
							setValueAt("montoBandera", "n");
						}
					}else {
						setValueAt("montoBandera", "n");
					}			
				}else{
					setValueAt("montoBandera", "s");
				}
				setValueAt("monto", getValueAt("monto"));
			}catch(Exception e){
				Trace.trace(Trace.Debug, "", "### validaMonto() falla en la validaci�n del monto "+e);
			}
		}
		//FIN INC 213 FX CMC 07/05/2019
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
				oM.setValueAt("Entrada.BCODACCC",referencia1);
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
	public void asignaNitNombrePr() {
		
		try {
			Trace.trace(Trace.Information, getClass().getName(), "OMAN-INICIO: asignaNitNombrePr()");
			if(getValueAt(NIT_NOMBRE)!=null && !getValueAt(NIT_NOMBRE).toString().equalsIgnoreCase("null")) {
					String nit_nombre=getValueAt(NIT_NOMBRE).toString();
					String[] datos=nit_nombre.split("@");
					if(datos.length==2) {
						setValueAt("Fijo_Nit", datos[0]);
						setValueAt("Fijo_Nombre", datos[1]);
					}else {
						setValueAt("Fijo_Nit", "");
						setValueAt("Fijo_Nombre", "");
					}
				
			}
			Trace.trace(Trace.Information, getClass().getName(), "OMAN-FIN: asignaNitNombrePr()");
		} catch (DSEObjectNotFoundException e) {
			Trace.trace(Trace.Error, getClass().getName(), "OMAN-ERROR: asignaNitNombrePr(): "+e);
		} catch (DSEInvalidArgumentException e) {
			Trace.trace(Trace.Error, getClass().getName(), "OMAN-ERROR: asignaNitNombrePr(): "+e);
		}
		
}
public void TraerNit_NombreContextoOp() {
  try {
	Trace.trace(Trace.Information, getClass().getName(), "OMAN-INICIO: TraerNit_NombreContextoOp()");
	if( (getValueAt("Fijo_Nit")!=null && getValueAt("Fijo_Nombre")!=null) && 
		(!getValueAt("Fijo_Nit").toString().trim().equalsIgnoreCase("") && !getValueAt("Fijo_Nombre").toString().trim().equalsIgnoreCase("") ) 
	   ) {
    	setValueAt("s_Fijo_Nit", getValueAt("Fijo_Nit").toString().trim());
        setValueAt("s_Fijo_Nombre", getValueAt("Fijo_Nombre").toString().trim());
    }
	Trace.trace(Trace.Information, getClass().getName(), "OMAN-FIN: TraerNit_NombreContextoOp()");
  } catch (DSEObjectNotFoundException e) {
	Trace.trace(Trace.Error, getClass().getName(), "OMAN-ERROR: TraerNit_NombreContextoOp() : "+e);
  } catch (DSEInvalidArgumentException e) {
	Trace.trace(Trace.Error, getClass().getName(), "OMAN-ERROR: TraerNit_NombreContextoOp() : "+e);
  }
}
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
	Trace.trace(Trace.Information, getClass().getName(), "OMAN-FIN: om_FNGU()");
	return salida;
	} catch (BbvaException e) {
		Trace.trace(Trace.Error, getClass().getName(), "OMAN-ERROR: om_FNGU() Salida null: "+e);
		return null;
		
	}
}
public String obtenerUsuario (String referencia, String codUsuario){

	Trace.trace(Trace.Information, "", "OMAN-Inicio obtenerUsuario()");
	String nCliente = "";
	try {
		Trace.trace(Trace.Information, "", "OMAN-### obtenerUsuario " + referencia + codUsuario);
		
		for (int i = codUsuario.length(); i<8; i++){ 
			codUsuario = codUsuario + "X"; 
		}
		nCliente = referencia + codUsuario + "0000000NC";
		
	} catch (Exception e) {
		Trace.trace(Trace.Error, "", "OMAN-### Error al formatear codigo de usuario/referencia  obtenerUsuario.java ****" + e);
	}
	Trace.trace(Trace.Information, "", "OMAN-Fin obtenerUsuario() WS Divisas ****");
	return nCliente;
}
public void consultarAvance2() throws Exception{

		setValueAt("montoBandera", "primera");
		setValueAt("selectCuentaOrden", "");
		setValueAt("indAvance", "");
		setValueAt("selectMoneda", "");
		setValueAt("monto", "");
		setValueAt("descripNegociacion", "");
		setValueAt("validaCta","S");
		
		setValueAt("msjError", "");
		setValueAt("tasaDivisa", new Double("0"));
		setValueAt("tasaDivisaPeso", new Double("0"));
		setValueAt("tasaUsdPeso", new Double("0"));
		setValueAt(EQUIV_PESO, new Double("0"));
		setValueAt("tasaDivisaPeso2", new Double("0"));
		setValueAt(EQUIV_PESO_2, new Double("0"));
		String tipo=(String)getValueAt("tipoOperacion");
		Trace.trace(Trace.Debug, "", "### negociarMesaDinero() - tipo: " + tipo);
		setValueAt("tipoOperacion", tipo);
		setValueAt("selectOpe","T2");


		String resultadoAvance =(String) getValueAt("avanceOpe");
		resultadoAvance = formateoAvance(resultadoAvance);
		Trace.trace(Trace.Debug, "", " Inicio consultarAvance() Formateado: " + getValueAt("selectMoneda") + " | " + resultadoAvance);
	
		
			Trace.trace(Trace.Debug, "", " Inicio consultarAvance(): " + getValueAt("selectMoneda") + " | " + getValueAt("avanceOpe"));

		om = creaOM(sOmConsultaAvance);
		om.setValueAt("Entrada.BPALACCE", "V");
		om.setValueAt("Entrada.BPALACC2", "");
		om.setValueAt("Entrada.BCODACCC", resultadoAvance);
		String refer=getValueAt("s_cod_logon").toString().trim();
		for (int i = refer.length(); i < 17; i++) {
		refer="0"+refer;
	}
		
		om.setValueAt("Entrada.BNITCOMP", refer);
		om.setValueAt("Entrada.BIMPORTE", null);
		KeyedCollection KC2=(KeyedCollection)om.getElementAt("Entrada");
		
		ejecutarOM(om);
		KeyedCollection KC=(KeyedCollection)om.getElementAt("Salida");
	
		Trace.trace(Trace.Debug, "", "### Flag Flag Flag Flag ");
		try {
			    String Flag2 =(String) om.getValueAt(SALIDA_COD_AVISO);
				String Flag3Error = (String) om.getValueAt(SALIDA_COD_ERROR);
				Trace.trace(Trace.Debug, "", "### Fin consultarAvance() Flag3Error " + Flag3Error);
				if(null!=Flag3Error && (Flag3Error.equalsIgnoreCase("CNE0008") || Flag3Error.equalsIgnoreCase("CNE0019") || Flag3Error.equalsIgnoreCase("CNE0020") || Flag3Error.equalsIgnoreCase("CNE0030")))
				{
					if(Flag3Error.equalsIgnoreCase("CNE0008")) {
						setValueAt("indiList", "SI");
					}
					setValueAt("S_ValidaDisplay", "Primero");
					Flag2=Flag3Error;
				}else { 	
					    setValueAt("S_ValidaDisplay", Flag2.trim());
					    Trace.trace(Trace.Debug, "", "### Fin consultarAvance() Flag " + Flag2);
					    if(Flag2.trim().equalsIgnoreCase("S")) {
 		 			    		
 		 				String tasaDivisaV = (String) om.getValueAt("Salida.TASA-DIVISA");
 		 				String equivalentePessoV =(String) om.getValueAt("Salida.EQUIVALENTE-PESOS");
 		 				String divisCopV = (String) om.getValueAt("Salida.DIVISA-COP");
 		 				String SALIDA_NIT=(String) om.getValueAt("Salida.SALIDA_NIT");
 		 				String NOMBRE_CLIENTE=(String) om.getValueAt("Salida.NOMBRE_CLIENTE");
 		 				String DIVISA_COP=(String) om.getValueAt("Salida.DIVISA_COP");
 		 				String DIVISA_DOLAR=(String) om.getValueAt("Salida.DIVISA_DOLAR");
 		 				String DIVISA_PESO=(String) om.getValueAt("Salida.DIVISA_PESO");
 		 				String Monto =(String) om.getValueAt("Salida.MONTO");
 		 				
 		 				String nit= SALIDA_NIT.trim();
 		 				String tipoD=nit.substring(0,1);
 						nit=nit.substring(1);
 						
 						nit=nit.replaceFirst("^0+(?!$)", "");
 						String digitoVerifi=nit.substring(nit.length()-1);
 						String nitSolo=nit.substring(0,nit.length()-1);
 						nit=tipoD+"-"+nitSolo+"-"+digitoVerifi;
 		 				setValueAt("S_SALIDA_NIT", nit);
 		 				setValueAt("S_NOMBRE_CLIENTE", NOMBRE_CLIENTE.trim());
 		 				DIVISA_COP=DIVISA_COP.trim();
 		 				DIVISA_COP=DIVISA_COP.substring(0,(DIVISA_COP.length()-2));
 		 				setValueAt("S_DIVISA_COP", DIVISA_COP.trim());
 		 				setValueAt("S_DIVISA_DOLAR", DIVISA_DOLAR.trim());
 		 				DIVISA_PESO=DIVISA_PESO.trim();
 		 				DIVISA_PESO=DIVISA_PESO.substring(0,(DIVISA_PESO.length()-2));
 		 				setValueAt("S_DIVISA_PESO", DIVISA_PESO.trim());
 		 				setValueAt("S_DIVISA_MONEDA", (String)om.getValueAt("Salida.DIVISA-COP"));
 		 				
 		 				String s_monto= om.getValueAt("Salida.MONTO").toString();
 		 				s_monto=s_monto.replaceFirst("^0+(?!$)", "");
 		 				s_monto=s_monto.substring(0,s_monto.length()-1);
 		 				setValueAt("S_MONTO", s_monto.trim());
 		 				setValueAt("monto", s_monto.trim());
 		 				Double tasaAvance = 0.0;
 		 				Double equivPesosMonto = 0.0;
 		 				tasaAvance = Double.parseDouble(tasaDivisaV);
 		 				equivPesosMonto = formatearImporteEntrada(s_monto.trim())*tasaAvance;
 		 				
 		 				Trace.trace(Trace.Debug, "", "***Fin consultarAvance() tasaDivisaPeso: " + tasaDivisaV);
 		 				Trace.trace(Trace.Debug, "", "***Fin consultarAvance() equivalentePessoV: " + equivPesosMonto);
 		 				Trace.trace(Trace.Debug, "", "***Fin consultarAvance() divisCopV: " + divisCopV);
 		 									
 		 				setValueAt("tasaDivisaPeso2", Double.valueOf(tasaDivisaV.trim()));
 		 				setValueAt(EQUIV_PESO_2, equivPesosMonto);
 		 				
 		 				IndexedCollection Icuentas=ConsultarCuentasPorDocumento(tipoD, nitSolo,digitoVerifi,"","");
 		 				CargarCuentasOp(Icuentas);

					    }
				}
				setValueAt("indAvance", Flag2);
				
				
		} catch (Exception e) {
			Trace.trace(Trace.Debug, "", "### ERROR consultarAvance2() e " + e.toString());
		}


}
public IndexedCollection  ConsultarCuentasPorDocumento(String tipoDocumento, String numDocumento,String digitoVerficacion, String pagina, String cant)  {

try {

	for (int i = numDocumento.length(); i < 15; i++) {
		numDocumento="0"+numDocumento;
	}
	
	
	IndexedCollection IcOm, IcOp = null;
	KeyedCollection List,kCuentas;
	OperacionMulticanal OmCuenta = creaOM(sOmCuenta);
	OmCuenta.setValueAt("Entrada.E_Referencia",(String)getValueAt("s_cod_logon") );
	OmCuenta.setValueAt("Entrada.E_Usuario", (String)getValueAt("s_cod_usuarisc"));
	OmCuenta.setValueAt("Entrada.E_TipoDocumento", tipoDocumento);
	OmCuenta.setValueAt("Entrada.E_NumeroDocumento", numDocumento);
	OmCuenta.setValueAt("Entrada.E_DigitoVerificacion", digitoVerficacion);
	OmCuenta.setValueAt("Entrada.E_ClienteCons", "");
	OmCuenta.setValueAt("Entrada.E_Servicio", "3523");
	OmCuenta.setValueAt("Entrada.E_Pagina", pagina);
	OmCuenta.setValueAt("Entrada.E_NumRegistros",  cant);
	String tipoFondo=""+getValueAt(TIPO_FONDO_GIRO);
	if(getValueAt(TIPO_FONDO_GIRO) != null && getValueAt(TIPO_FONDO_GIRO).toString().equalsIgnoreCase(FONDO_PSE)) {
		OmCuenta.setValueAt(ENTRADA_TIPO_OPE, ABONO);
	}
	Trace.trace(Trace.Information, getClass().getName(), "#CONSULTA DE CUENTAS: consulta_nit_om");
	Trace.trace(Trace.Information, getClass().getName(),
			"#ENTRADA: " + (KeyedCollection) OmCuenta.getElementAt("Entrada"));
	ejecutarOM(OmCuenta);	
    IcOm = (IndexedCollection) OmCuenta.getElementAt("Salida.S_IcListaCuenta");
	
    return IcOm;	

	} catch (Exception e) {
		Trace.trace(Trace.Error, getClass().getName(), "#ERROR ConsultarCuentasPorDocumento() " + e);
	}
	return null;
}
private void CargarCuentasOp(IndexedCollection listaCuenta) throws IOException, DSEInvalidArgumentException {
	
	int cant=listaCuenta.size();
	IndexedCollection IcOp;
	try {
	IcOp = (IndexedCollection)getElementAt("lista_cuentas");
	IcOp.removeAll();
	for (int i =0; i<cant;  i++) {
		KeyedCollection kCuentas = (KeyedCollection) listaCuenta.getElementAt(i);
		KeyedCollection OpCuenta = (KeyedCollection) DataElement.getExternalizer().convertTagToObject(IcOp.getElementSubTag());
		
		if(kCuentas.getValueAt("S_Cuenta") !=null && !kCuentas.getValueAt("S_Cuenta").toString().trim().equalsIgnoreCase("")) {
			String oficina=kCuentas.getValueAt("SS0_CENTROC")==null?"":kCuentas.getValueAt("SS0_CENTROC").toString();
			OpCuenta.setValueAt("clave_asunto", kCuentas.getValueAt("S_Cuenta").toString().trim());
			OpCuenta.setValueAt("oficina", oficina.toString().trim());
			OpCuenta.setValueAt("divisa", "COP");
			IcOp.addElement(OpCuenta);
		}	
	}
	} catch (DSEObjectNotFoundException e) {
		Trace.trace(Trace.Error, "", "### CargarCuentasOp() - Error al listar cuentas" + e);
	}
}

public void validarContinuar() throws DSEObjectNotFoundException {
			
	KeyedCollection kCuenta,kEntradaParametria;
	IndexedCollection icContextoSesion,listaCuentasOP = null;
	Enumeration enContextoSesion1;		
	String clave = "",ctaBenef="",indicador="",selectMoneda="";

	String descripHMesa = (String)getValueAt("descripNegociacion");
	String tipoFondoGiro = validateElement(TIPO_FONDO_GIRO);
	
	try{
		setValueAt("validaDes","");
		if(!validaDescrip(descripHMesa)) {  
			setValueAt("validaDes","VD");
		}
		if(tipoFondoGiro.equalsIgnoreCase(FONDO_PSE)) {
			String businessId = "3000000"+getValueAt(VALUE_BUSINESS_ID).toString().replace("-", "");
			getCommission(businessId, getValueAt(TIPO_FONDO_GIRO), getValueAt(EQUIV_PESO),
					getValueAt(NUM_TRANS), getValueAt("selectCuentaOrden"), "");
		}
	}
	catch (Exception e) {
		Trace.trace(Trace.Error, "", "### validarContinuar() - Error al validar descripcion" + e);
	}
    try {
			validaMonto();
    } catch (Exception e) {
    	Trace.trace(Trace.Error, "", "### validarContinuar() - Error al validar monto" + e);
	}
	try {
		Double equivPesosMonto = 0.0;	
			equivPesosMonto = formatearImporteEntrada(getValueAt(EQUIV_PESO).toString());
			setValueAt(EQUIV_PESO, equivPesosMonto);
		validarCuenta();
	} catch (Exception e) {
		Trace.trace(Trace.Error, "", "### validarContinuar() - Error al validar cuenta" + e);
	}
	setEstado("12");
}
	// FIN -VARIOS NIT F2 - OSNEIDER A. CMC - 02-10-2019

	public String[] transgresionNit(String numIdent, String codAltamira, String digVerif, String tipoDoc) {
		Trace.trace(Trace.Debug, "", " Inicio transgresionNit(): ");
		Trace.trace(Trace.Debug, "", " Inicio transgresionNit(): codAltamira: " + codAltamira);
		String[] nitConfirmado = new String[2];
		nitConfirmado[0] = "KO";
		try {
			IndexedCollection icListaNit = (IndexedCollection) getElementAt("IcListaNit");
			if(icListaNit.size()>0){
				for (int i =0; i<icListaNit.size();  i++) {
					KeyedCollection kcNit = (KeyedCollection) icListaNit.getElementAt(i);
					String numeroDocumento = kcNit.getValueAt("NumeroDocumento").toString().replaceFirst("^0*", "");
					if(kcNit.getValueAt("ClienteAltamira").toString().equalsIgnoreCase(codAltamira)&&
							numeroDocumento.equalsIgnoreCase(numIdent)&&
							kcNit.getValueAt("DigitoVerificacion").toString().equalsIgnoreCase(digVerif)&&
							kcNit.getValueAt("TipoDocumento").toString().equalsIgnoreCase(tipoDoc)){
						nitConfirmado[0] = "OK";
						nitConfirmado[1] = kcNit.getValueAt("NumeroDocumento").toString();
						return nitConfirmado;
					}
				}
			}
		}catch(Exception e) {
			Trace.trace(Trace.Error, "", " transgresionNit(): " + "Se ha producido un Error al validar la autenticidad del nit");
		}
		Trace.trace(Trace.Debug, "", " Fin transgresionNit(): ");
		return nitConfirmado;
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
	//GP17663 MONTO 25-05-2021 CMC	
	private void obtenerMontoMaximoCotizacion() throws DSEObjectNotFoundException, DSEInvalidArgumentException {
			
		OperacionMulticanal ciriRest = null; 
		try {
			ciriRest = creaOM("ciri_generic_rest_client_om");
			ciriRest.setValueAt("Entrada.targetCurrency", "USD");
			ciriRest.setValueAt("Entrada.exchangePaymentCard", "CASH");
			ejecutarOM(ciriRest);
			String maximumLimitAmount =ciriRest.getValueAt("Salida.range.maximumLimitAmount").toString();
			Trace.trace(Trace.Debug, "", "### girosHacia() - Parametria maximumLimitAmount "+maximumLimitAmount);
			setValueAt("montoMaximoCotizar", maximumLimitAmount);
		}catch (Exception e) {
			Trace.trace(Trace.Error, "", " Inicio giroHacia(). Error ciri_generic_rest_client_om "+e);
			setValueAt("montoMaximoCotizar", "200.000");
		}		
	}
	//GP17663 MONTO FIN 25-05-2021 CMC
  	public void getblotter(){
  		this.responseAjax= new HashMap<String, Object>();
  		this.responseAjax.put(VALUE_STATUS, STATUS_NOK);
  		ComisionServiceDto comisionServiceDto = null;
  			 try {
  				String customerIdPse=(String)getValueAt("customerIdPse");
  				String nit="";
  				if (!customerIdPse.isEmpty()) {
  					nit=customerIdPse;
  					setValueAt("customerIdPse","");		
  				}else {
  					nit=(String)getValueAt("nit");
  				}
  				
  				MetodosConditions metodo = MetodosConditions.GET_COMISION_SERVE;
  				OperacionMulticanal oM = creaOM(OM_OPERATIVE_CONDITIONS);
  				oM.setValueAt(ENTRADA_METODO, metodo.name());
  				String page=(String)getValueAt("page");
  				String toDate=(String)getValueAt("toDate");
  				String fromDate=(String)getValueAt("fromDate");
  				oM.setValueAt("Entrada.comisionService.page", page);
  				if(!nit.equals("NA")) {
  					oM.setValueAt("Entrada.comisionService.nit", nit);
  				}
  				//if(!fromDate.equalsIgnoreCase("NA")) {
  					oM.setValueAt("Entrada.comisionService.toDate", toDate);
  					oM.setValueAt("Entrada.comisionService.fromDate", fromDate);
  				//}
  				oM.execute();
  				comisionServiceDto = (ComisionServiceDto) oM.getValueAt("Salida.comisionServiceObject");
  				responseAjax.put(VALUE_STATUS, "OK");
  				String pattern = "MM/dd/yyyy HH:mm";
  				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
  				String actualDate = simpleDateFormat.format(new Date());
				responseAjax.put("fecha_actual", actualDate);
				if(comisionServiceDto.getData().size() == 0 || comisionServiceDto.getData() == null  ) {
					responseAjax.put(VALUE_STATUS, "NO_DATA");
				}
  				responseAjax.put("data", comisionServiceDto);
  			} catch (BbvaException e) {
  				responseAjax.put(VALUE_STATUS, "ERROR");
		  		  Trace.trace(Trace.VTF, "", "Error al ejecutar om: " + e.getMessage());

  			} catch (DSEObjectNotFoundException e) {
  				responseAjax.put(VALUE_STATUS, "ERROR");
		  		  Trace.trace(Trace.VTF, "", "Error al obtener la propiedad: " + e.getMessage());

  			} catch (DSEInvalidArgumentException e) {
  				responseAjax.put(VALUE_STATUS, "ERROR");
  				Trace.trace(Trace.VTF, "", "Error al settear los valores: " + e.getMessage());

  			}finally {
  				try {
  					setObjectValue("jsonData", responseAjax);
  					setEstado("16");
  				}catch (Exception e) {
  		  		  Trace.trace(Trace.VTF, "", "Error al obtener el objeto: " + e.getMessage());

				}	
			}
  	}
  	
  	public void confirmarBlotter() {
  		ComisionServiceDto comisionServiceDto = null;
  		try {
  			comisionServiceDto = getObjectValue("orden", ComisionServiceDto.class);
  			System.out.println(comisionServiceDto);
  		}catch (Exception e) {
			// TODO: handle exception
		}
  	}
  	
  	public void getBeneficiario() {
  		Map<String, String> resp = new HashMap<String, String>();
  		resp.put(VALUE_STATUS, "OK");
  		try {
  			String numOperacion= (String)getValueAt("numOperacion");
  			String cuenta = (String)getValueAt("cuentabenefica");
  			String ref=(String)getValueAt("s_cod_logon");
  			String usu=(String)getValueAt("s_cod_usuarisc");
  			String usuario=obtenerUsuario(ref, usu);
  	  		KeyedCollection detallesOperacion=om_FNGU("","","T",cuenta ,usuario,new Double(numOperacion.substring(1,numOperacion.length())));
  			if(detallesOperacion!=null) {
  				if(detallesOperacion.getValueAt("NIT_COMPLETO")!=null && !detallesOperacion.getValueAt("NIT_COMPLETO").toString().equals("")) {
  				String monto = (String)detallesOperacion.getValueAt(EQUIPES);
  			    String beneficiario=(String)detallesOperacion.getValueAt("BENEFIC");
  				String banco=(String)detallesOperacion.getValueAt("BANBENE");
  				String ordenante=(String)detallesOperacion.getValueAt("ORDENA");
  				String ordenanteId=(String)detallesOperacion.getValueAt("ORDEN");
  				String concepto=(String)detallesOperacion.getValueAt("DESTRAN");
  				resp.put("monto", monto.trim());
  				resp.put(VALUE_BENEFI, beneficiario.trim());
  				resp.put("banco", banco.trim());
  				resp.put(VALUE_ORDEDANTE, ordenante.trim());
  				resp.put("ordenanteId", ordenanteId.trim());
  				resp.put("concepto", concepto.trim());
  				}
  			}
  		}catch (Exception e) {
  			resp.put(VALUE_STATUS, STATUS_NOK);
		}
  		try {
  			setObjectValue("jsonData", resp);
  	  		setEstado("18");
  		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
  	}
  	
  	
    private List<UsuariosPseDto> listUserOrden() {
    	IndexedCollection icOMUsuariosPseOrden = null;
    	List<UsuariosPseDto> usuarios = new ArrayList<UsuariosPseDto>();
    	try {
    		MetodoEnum metodo = MetodoEnum.LISTAR_USUARIOS_ORDEN;
    		OperacionMulticanal om = creaOM(CONSULTA_OM_PSE);
    		om.setValueAt(ENTRADA_METODO, metodo.name());
    		om.execute();
    		icOMUsuariosPseOrden = (IndexedCollection) om.getElementAt(SALIDA_LISTAR_USURORDE);
    		usuarios = mapUsers(icOMUsuariosPseOrden);

    	} catch (Exception e) {
    		Trace.trace(Trace.Error, "", "Fallo en la ejecucion del metodo listUserOrden() " + e.toString());
    	}
    	return usuarios;

    }
    
    private List<UsuariosPseDto> mapUsers(IndexedCollection icOMUsuariosPseOrden) throws DSEObjectNotFoundException, DSEInvalidArgumentException {
    	
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
		setValueAt("usuariosPseDto", usuarios);
		return usuarios;
    }

    private void getCommission (String businessId, Object businessOp, Object amount, 
    		Object operationNumber, Object selectCuentaOrden, String numOperacion) {
    	try {
    		Trace.trace(Trace.Debug, "", "getCommission Ingresa");
    		setValueAt("equivaPesos", "");
    		setValueAt("comision", "");
    		setValueAt("iva", "");
    		setValueAt(TOTAL_DEBITO, "");
    		if(businessId != null && businessOp != null) {
    			if(businessOp.toString().equalsIgnoreCase(FONDO_PSE)) {
    				String amountEquiv = amount!=null ? (String) amount : "";
    				amountEquiv = amountEquiv.replace(",", "");
    				String numTrans = operationNumber!=null ? (String) operationNumber : "";
    				String selectCuenta = getValueAtOfdefault("selectCuentaOrden", "");
    				String[] temporal = selectCuenta.split("_");
    				if(!numOperacion.equalsIgnoreCase(""))
    					numTrans = numTrans+"-T"+numOperacion+"-"+temporal[0];
    				else
    					numTrans = numTrans+"-XXXXXXX-"+temporal[0];
    				setValueAt(TIPO_FONDO_GIRO, businessOp);
    				MetodosConditions metodosPayment = MetodosConditions.POST_OPERATIVE_CONDITIONS;
    	    		OperacionMulticanal om = creaOM(OM_OPERATIVE_CONDITIONS);
    	    		om.setValueAt(ENTRADA_METODO, metodosPayment.name());
    	    		om.setValueAt("Entrada.postOperativeCondit.operationType", "MONEY_TRANSFER");
    	    		Trace.trace(Trace.Debug, "", "getCommission businessId: "+businessId);
    	    		om.setValueAt("Entrada.postOperativeCondit.businessId", businessId.replace("-", "").trim());
    	    		Trace.trace(Trace.Debug, "", "getCommission operationNumber: "+numTrans);
    	    		om.setValueAt("Entrada.postOperativeCondit.operationNumber", numTrans);
    	    		Trace.trace(Trace.Debug, "", "getCommission amount: "+amountEquiv);
    	    		BigDecimal b = new BigDecimal(amountEquiv).setScale(2, RoundingMode.HALF_UP);
    	    		om.setValueAt("Entrada.postOperativeCondit.amountNegotiated.amount", b);
    	    		om.setValueAt("Entrada.postOperativeCondit.amountNegotiated.currency", "COP");
    	    		om.execute();
    	    		OperativeConditionsResponse operativeDto = (OperativeConditionsResponse) om.getValueAt("Salida.operativeConditionsObject");
    	    		String equivPesos = String.valueOf(operativeDto.getData().getAmountNegotiated().getAmount());
    	    		String iva = String.valueOf(operativeDto.getData().getTaxes().getTotalTaxes().getAmount());
    	    		String comision = String.valueOf(operativeDto.getData().getFees().getTotalFees().getAmount());
    	    		String totalDebito = String.valueOf(operativeDto.getData().getTotalCharged().getAmount());
    	    		String rate = String.valueOf(operativeDto.getData().getRates().getTotalRates().getAmount());
    	    		setValueAt("equivaPesos", equivPesos);
    	    		setValueAt("comision", comision);
    	    		setValueAt("iva", iva);
    	    		setValueAt(TOTAL_DEBITO, totalDebito);
    	    		setValueAt("rate", rate);
    	    		setValueAt("tempor1", FORMAT.format(Double.valueOf(equivPesos)));
    	    		setValueAt("tempor2", FORMAT.format(Double.valueOf(comision)));
    	    		setValueAt("tempor3", FORMAT.format(Double.valueOf(iva)));
    	    		setValueAt("tempor4", FORMAT.format(Double.valueOf(totalDebito)));
    	    		setValueAt("tempor5", FORMAT.format(Double.valueOf(rate)));
    			}
			}
    		
    	} catch (Exception e) {
    		Trace.trace(Trace.Error, "", "Fallo en la ejecucion del metodo getCommission() " + e.toString());
    	}

    }
    
    private String formatNit(String nit) {
    	if(nit.contains("@")) {
    		String[] temporal = nit.split("-");
        	nit = temporal[1].replace("@", "");
    	}
    	nit = nit.substring(0, 1) + "000000" + nit.substring(1, nit.length()-1);
    	return nit;
    }
    
	private void validarAutorizacion(List<UsuariosPseDto> listUsuariosPseDto) {
		try {
			if (listUsuariosPseDto.size() > 0) {
				setValueAt("pseAutorizado", "true");
			} else {
				setValueAt("pseAutorizado", VALUE_FALSE);
			}
		} catch (Exception e) {
			Trace.trace(Trace.Error, "", "Fallo en la ejecucion del metodo validarAutorizacion(List<UsuariosPseDto> listUsuariosPseDto) " + e.toString());
		}
	}
	
	private void updateOrden(Object numTrans, Object amountEquiv, String numOpera) {
    	try {
    		MetodoEnum metodo = MetodoEnum.ACTUALIZAR_ORDEN;
    		OperacionMulticanal om = creaOM(CONSULTA_OM_PSE);
    		om.setValueAt(ENTRADA_METODO, metodo.name());
    		om.setValueAt("Entrada.actualizarTransacci.valorUtilizado", amountEquiv.toString().replace(",", ""));
			om.setValueAt("Entrada.actualizarTransacci.numTransaccion", numTrans);
			om.setValueAt("Entrada.actualizarTransacci.banckTrade", "T"+numOpera);
    		om.execute();
    		Trace.trace(Trace.Information, "", "Actualizacion de la orden " + numTrans + " con numero de operacion bankTrade "
    				+ numOpera + " finalizada con exito");
    	} catch (Exception e) {
    		Trace.trace(Trace.Error, "", "Fallo en la ejecucion del metodo updateOrden() " + e.toString());
    	}
    }
	
	private void validateOpenModal() {
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HHmm");
			Date date = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			int currentTime = Integer.parseInt(simpleDateFormat.format(date));
			date = simpleDateFormat.parse("1400");
			int deadLine = Integer.parseInt(simpleDateFormat.format(date));
			if (currentTime > deadLine) {
				setValueAt(FLAG_OPEN_MODAL, "true");
			}else {
				setValueAt(FLAG_OPEN_MODAL, VALUE_FALSE);
			}
		} catch (Exception e) {
			Trace.trace(Trace.Error, "", "Fallo en la ejecucion del metodo validateOpenModal() " + e.toString());
		}
	}
	
	private String eliminarNotacion(double numero) {
        String d = "####################################";
        return new DecimalFormat("#." + d + d + d).format(numero).replace(",", ".");
    }
	
	private String validarRangoAutorizacion(String equivPesos) throws DSEInvalidArgumentException {
		List<UsuariosPseDto> listUsuariosPseDto = new ArrayList<UsuariosPseDto>();
		String canContinue = "NO";
		try {
			System.out.println("Importe en pesos antes de validar contra autorizaciones: " + equivPesos);
			equivPesos = equivPesos.replaceAll(",","");
			Double equivalentInPesos = Double.valueOf(equivPesos);
			listUsuariosPseDto = (List<UsuariosPseDto>) getValueAt("usuariosPseDto");
			System.out.println("Tamano lista de autorizaciones: " + listUsuariosPseDto.size());
			for(UsuariosPseDto usuariosPseDto: listUsuariosPseDto) {
				if(equivalentInPesos>=Double.valueOf(usuariosPseDto.getValorDesde()) &&
						equivalentInPesos<=Double.valueOf(usuariosPseDto.getValorHasta())) {
					canContinue = "SI";
					setValueAt(NUM_TRANS, usuariosPseDto.getNumeroTrans());
				}
			}
		} catch (DSEObjectNotFoundException e) {
			Trace.trace(Trace.Error, "", "Fallo en la ejecucion del metodo validarRangoAutorizacion() " + e.toString());
		}
		return canContinue;
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
	
	private void postPaymentConciliate(String paymentId, String idStatus,  
			String referenceId, String referenceValue) {
			String reasonId="";
			IndexedCollection icPaymentReferences = null;
	        try {
	            MetodosPayment metodosPayment = MetodosPayment.POST_PAYMENT_CONCILIATE;
	            OperacionMulticanal om = creaOM(OM_PAYMENTS_SERVICE);
	            if(idStatus.equalsIgnoreCase(STATUS_OK)) {
	            	reasonId="COMPLETED";
	            }else {
	            	reasonId="REJECTED";
	            }
	            om.setValueAt(ENTRADA_METODO , metodosPayment.name());
	            om.setValueAt("Entrada.postPaymentConciliate.id", paymentId);
	            om.setValueAt("Entrada.postPaymentConciliate.status.id", idStatus);
	            om.setValueAt("Entrada.postPaymentConciliate.status.reasonId", reasonId);
	            icPaymentReferences = (IndexedCollection) om.getElementAt("Entrada.postPaymentConciliate.references");
	            icPaymentReferences.removeAll();
	            for (int i = 0; i < 1; i++) {
	                KeyedCollection kcReferences = (KeyedCollection) DataElement.getExternalizer().convertTagToObject(icPaymentReferences.getElementSubTag());
	                kcReferences.setValueAt("id", referenceId);
	                kcReferences.setValueAt("value", referenceValue);
	                icPaymentReferences.addElement(kcReferences);
	            }
	            //om.setValueAt("Entrada.postPaymentConciliate.references", icPaymentReferences);
	            om.execute();
	            ConciliateResponse conciliateResponse = (ConciliateResponse) om.getValueAt("Salida.paymentObject");
	            System.out.println(conciliateResponse.getData().toString());
	        } catch (Exception e) {
	            Trace.trace(Trace.Error, "", "Fallo en la ejecucion del metodo postPaymentConciliate() " + e.toString());
	        }
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
	 
	 private String callFormatNit(String nit) {
		 if(!nit.equalsIgnoreCase(""))
			 return formatNit(nit);
		 return "";
	 }
	 private void actualizaEstadoFondo(String nameElement) throws DSEInvalidArgumentException{
		 String retorno="";
		 try {
			if (getValueAt(nameElement) == null) {
				setValueAt(nameElement,FONDO_BBVA);
			}			
			
		} catch (DSEObjectNotFoundException e) {
			Trace.trace(Trace.Error, "", "Fallo en la actualizacion de tipoFondo " + e.toString());
		}
	 }
	 
	 	 private void validarDisponibilidadPse(String clave) throws DSEObjectNotFoundException, DSEInvalidArgumentException, BbvaException {
		 	KeyedCollection kEntradaParametria;
			kEntradaParametria = (KeyedCollection) getElementAt(sOmParametria+"-data.entrada");
			kEntradaParametria.setValueAt("NCUENTA",  clave);
			OperacionMulticanal OmParametria = creaOM(sOmParametria);
			controlOM = new OpControl();
			controlOM.setConfig((String)getValueAt("datosAPP.pb_cod_serv"),
								(String)getValueAt("datosAPP.iv-cod_emp")+(String)getValueAt("datosAPP.iv-cod_usu"),
								(String)getValueAt("datosAPP.iv-cod_usu"),
								creaOM("sign_on_om"), 
								creaOM("sign_on_om"),
								OmParametria, 
								creaOM(OmParametria.getName()));
			controlOM.control_f100();
			KeyedCollection kParametriaOM = (KeyedCollection) getElementAt(sOmParametria+DATA_SALIDA);
			String contingencia=(String) kParametriaOM.getValueAt("HABIPSE");
			setValueAt(CONTIGENCIA,contingencia.substring(0,6));
	 }
	 private void validaAutorizaciones(String monto) throws BbvaException {
		 Trace.trace(Trace.Error, "", "Inicio validaAutorizaciones() " + monto );
			try {
				validarDisponibilidadPse("0013");
				String contingencia = (String)getValueAt(CONTIGENCIA);
				setValueAt("operatePse", "NO");
				
				String activaPse=validarRangoAutorizacion(monto);
				if(contingencia.equalsIgnoreCase("NOACTI") && activaPse.equalsIgnoreCase("SI")) {
					setValueAt("operatePse", "SI");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Trace.trace(Trace.Error, "", "Fallo en la metodo validaAutorizaciones() " + e.toString());
			}
			
			
	 }
	 
	 private void validacionRangoAutorizado(String tipoFondoGiro){
		 try {
			if(validarRangoAutorizacion(getValueAt(EQUIV_PESO).toString()).equalsIgnoreCase("SI")) {
					setValueAt("rangoAutorizado", "SI");
					setValueAt(TIPO_FONDO_GIRO, tipoFondoGiro);
				}else {
					setValueAt("rangoAutorizado", "NO");
					setValueAt(TIPO_FONDO_GIRO, FONDO_BBVA);
				}
		} catch (DSEInvalidArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DSEObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 public void actualizarPse() throws DSEObjectNotFoundException, DSEInvalidArgumentException {
		 this.responseAjax= new HashMap<String, Object>();
		 responseAjax.put(VALUE_STATUS, STATUS_OK);
		 try {
			 PaymentsServiceResponse paymentsServiceResponse = getPaymentById((String) getValueAt("idcuspse"));
			   String statuspseId= ""+paymentsServiceResponse.getData().getStatus().getId();	   
			   if(!statuspseId.equalsIgnoreCase(STATUS_PENDING)) {				   
				   String paymentIdpse=""+paymentsServiceResponse.getData().getId();
				   String idStatuspse=""+paymentsServiceResponse.getData().getStatus().getId(); 		   
				   String referenceIdpse=""+paymentsServiceResponse.getData().getReferences().get(0).getId(); 
				   String referenceValuepse=""+paymentsServiceResponse.getData().getReferences().get(0).getValue();
				   postPaymentConciliate(paymentIdpse,idStatuspse,referenceIdpse,referenceValuepse);
			   }
			  
		 } catch (Exception e) {
			 responseAjax.put(VALUE_STATUS, STATUS_NOK);
		}
		 setObjectValue("jsonData", responseAjax);
		 setEstado("19");
	}
}
