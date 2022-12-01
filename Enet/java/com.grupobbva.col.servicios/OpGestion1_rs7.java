package com.grupobbva.col.servicios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Random;
/*VYGTS GP12319 INICIO*/
import abad.atae.test.om.PreejecucionServicio;
import com.grupobbva.multidioma.CatalogoMultidioma;
import com.ibm.dse.base.DSEInvalidArgumentException;
import com.ibm.dse.base.DSEObjectNotFoundException;
import com.ibm.dse.base.DataElement;
import com.ibm.dse.base.Settings;
import com.grupobbva.bc.col.web.ofertas.OfertaConstantesEnum;
/*VYGTS GP12319 FIN*/
import com.grupobbva.ii.sf.base.BbvaARQException;
import com.grupobbva.ii.sf.base.BbvaException;
import com.grupobbva.ii.sf.base.BbvaNGException;
import com.grupobbva.ii.sf.modelo.ConsultaBD;
import com.grupobbva.ii.sf.operacion.BbvaOperacion;
import com.grupobbva.ii.sf.operacion.OpGestion;
import com.grupobbva.ii.sf.operacion.OperacionMulticanal;
import com.grupobbva.ii.sf.operacion.OperacionPresentacion;
import com.ibm.dse.base.IndexedCollection;
import com.ibm.dse.base.KeyedCollection;
import com.ibm.dse.base.Trace;
//import com.grupobbva.bc.col.operacion.*;

//import com.grupobbva.bc.col.modelo.*;
 
public class OpGestion1_rs7 extends OpGestion{
	ResultSet 			rsId	 		= null;
	
	public OperacionMulticanal om=null;
public OpGestion1_rs7() {
	super();
}
public OpGestion1_rs7(String anOperationName) throws java.io.IOException {
	super(anOperationName);
}
public OpGestion1_rs7(String anOperationName, com.ibm.dse.base.Context aParentContext) throws java.io.IOException, com.ibm.dse.base.DSEInvalidRequestException {
	super(anOperationName, aParentContext);
}
public OpGestion1_rs7(String anOperationName, String aParentContext) throws java.io.IOException, com.ibm.dse.base.DSEObjectNotFoundException, com.ibm.dse.base.DSEInvalidRequestException {
	super(anOperationName, aParentContext);
}
/*VYGTS GP12319 INICIO*/
public void comprobarPass2() throws Exception {
	
	String passwordOpe = "", tokenOpe = "", alias = "";
	
	try {
		// Se fijan los valores de algunas de las variables de la llamada al Policy
		fijarValores();
		// Se recoge el valor introducido por pantalla de Clave de Operaciones
		passwordOpe = (String) getValueAt("ClaveOperacion");
		tokenOpe=(String) getValueAt("token");
		// Se recoge el alias que es el numero de identificacion del cliente (num. tarj.)
		alias = (String) getValueAt("s_IdCliente");
		// Se realiza la llamada a la OMIdentificacionOperativa
		om=creaOM("identificacion_operativa_om");
		om.setValueAt(getNombreOM() + "-data.Alias", alias);
		om.setValueAt(getNombreOM() + "-data.PasswordOper", passwordOpe);
		om.setValueAt(getNombreOM() + "-data.SerialToken", getValueAt("s_serial_token"));
		om.setValueAt(getNombreOM() + "-data.TokenOper", tokenOpe);
		om.setValueAt(getNombreOM() + "-data.Country", getValueAt("Country"));
		om.setValueAt(getNombreOM() + "-data.Bank", getValueAt("Bank"));
		om.setValueAt(getNombreOM() + "-data.MaxErrors", getValueAt("MaxErrors"));
		//NGE  JCAG INICIO
		ejecutarOM(om);		
		
	}catch(Exception e)
	{
	}		
}
/*VYGTS GP12319 FIN*/
	public void comprobarPass() throws Exception {
	
	String passwordOpe = "", tokenOpe = "", alias = "";
	
	int res;
	try {
		// Se fijan los valores de algunas de las variables de la llamada al Policy
		int flag =0;
		fijarValores();
		// Se recoge el valor introducido por pantalla de Clave de Operaciones
		passwordOpe = (String) getValueAt("ClaveOperacion");
		tokenOpe=(String) getValueAt("token");
		// Se recoge el alias que es el numero de identificacion del cliente (num. tarj.)
		alias = (String) getValueAt("s_IdCliente");
		// Se realiza la llamada a la OMIdentificacionOperativa
		om=creaOM("identificacion_operativa_om");
		om.setValueAt(getNombreOM() + "-data.Alias", alias);
		om.setValueAt(getNombreOM() + "-data.PasswordOper", passwordOpe);
		om.setValueAt(getNombreOM() + "-data.SerialToken", getValueAt("s_serial_token"));
		om.setValueAt(getNombreOM() + "-data.TokenOper", tokenOpe);
		om.setValueAt(getNombreOM() + "-data.Country", getValueAt("Country"));
		om.setValueAt(getNombreOM() + "-data.Bank", getValueAt("Bank"));
		om.setValueAt(getNombreOM() + "-data.MaxErrors", getValueAt("MaxErrors"));
		//NGE  JCAG INICIO
		try{
			ejecutarOM(om);
		}catch(Exception e)
		{
			BbvaARQException Barq=ManejarExcepcion(5,"","","",e,"",this,"","");
			return;
		
		}
		int a=((Integer) getValueAt("Resultado")).intValue();
		if(a!=0)
		{
			//
			BbvaARQException e=construirARQE("ARQ0500080","");
			BbvaARQException Barq=ManejarExcepcion(5,String.valueOf(a),"","",e,"",this,"","");
			return;
		}
		//NGE  JCAG FIN

		} catch (Exception e) {
		
		BbvaARQException Barq=ManejarExcepcion(1,"","","",e,"",this,"","");
		throw Barq;
	}
		
		return;
				
}
	public void comprobarPass1() throws Exception {
		
		String passwordOpe = "", tokenOpe = "", alias = "";
		
		int res;
		try {
			// Se fijan los valores de algunas de las variables de la llamada al Policy
			int flag =0;
			fijarValores();
			// Se recoge el valor introducido por pantalla de Clave de Operaciones
			passwordOpe = (String) getValueAt("ClaveOperacion");
			tokenOpe=(String) getValueAt("token");
			// Se recoge el alias que es el numero de identificacion del cliente (num. tarj.)
			alias = (String) getValueAt("s_IdCliente");
			// Se realiza la llamada a la OMIdentificacionOperativa
			om=creaOM("identificacion_operativa_om");
			om.setValueAt(getNombreOM() + "-data.Alias", alias);
			om.setValueAt(getNombreOM() + "-data.PasswordOper", passwordOpe);
			om.setValueAt(getNombreOM() + "-data.SerialToken", getValueAt("s_serial_token"));
			om.setValueAt(getNombreOM() + "-data.TokenOper", tokenOpe);
			om.setValueAt(getNombreOM() + "-data.Country", getValueAt("Country"));
			om.setValueAt(getNombreOM() + "-data.Bank", getValueAt("Bank"));
			om.setValueAt(getNombreOM() + "-data.MaxErrors", getValueAt("MaxErrors"));
			//NGE  JCAG INICIO
			try{
				ejecutarOM(om);
			}catch(Exception e)
			{
				BbvaARQException Barq=ManejarExcepcion(5,"","","",e,"",this,"","");
				return;
			
			}
			
			} catch (Exception e) {
			
			BbvaARQException Barq=ManejarExcepcion(1,"","","",e,"",this,"","");
			throw Barq;
		}
			
			return;
					
	}

	public void consultarfirma(String tipo,String asunto)throws Exception
	{
		IndexedCollection icCuentas = null;
		KeyedCollection kcCuenta = null;
		Enumeration enCuentas = null;
		String moneda="";
		boolean bEncontrado = false;
		
		String servi ="6041";
		if (!servi.equals((String)getValueAt("datosAPP.pb_cod_serv"))){

		try {
			if(asunto.substring(10,12).equals("70"))
			{
				asunto=asunto.substring(0,20);
				icCuentas = (IndexedCollection) getElementAt("s_salida_fondos.s_lista_fondos");
				enCuentas = icCuentas.getEnumeration();
				while (enCuentas.hasMoreElements() && !bEncontrado) {
				kcCuenta = (KeyedCollection) enCuentas.nextElement();
				String service=(String)kcCuenta.getValueAt("s_permiso");
					if (((String) kcCuenta.getValueAt("s_clave_asunto")).equals(asunto)&&service.equals((String)getValueAt("datosAPP.pb_cod_serv"))) {
					// Si el asunto coincide 
						Object firma = ( kcCuenta.getValueAt("s_LimDiario"));
					
						setValueAt("firma",new Double(firma.toString()));
						bEncontrado = true;
					} 
				}
				
			}
			if(asunto.substring(10,12).equals("96"))
			{
				asunto=asunto.substring(0,20);
				icCuentas = (IndexedCollection) getElementAt("s_salida_prestamos.s_lista_prestamos");
				enCuentas = icCuentas.getEnumeration();
				while (enCuentas.hasMoreElements() && !bEncontrado) {
				kcCuenta = (KeyedCollection) enCuentas.nextElement();
				String service=(String)kcCuenta.getValueAt("s_permisop");
					if (((String) kcCuenta.getValueAt("s_clave_asunto")).equals(asunto)&&service.equals((String)getValueAt("datosAPP.pb_cod_serv"))) {
					// Si el asunto coincide 
						Object firma = ( kcCuenta.getValueAt("s_LimDiario"));
					
						setValueAt("firma",new Double(firma.toString()));
						bEncontrado = true;
					} 
				}
				
			}
			if(asunto.length()==16)
			{
				icCuentas = (IndexedCollection) getElementAt("s_salida_tarjetas.s_lista_tarjetas");
				enCuentas = icCuentas.getEnumeration();
				while (enCuentas.hasMoreElements() && !bEncontrado) {
				kcCuenta = (KeyedCollection) enCuentas.nextElement();
				String service=(String)kcCuenta.getValueAt("s_permiso");
					if (((String) kcCuenta.getValueAt("s_clave_asunto")).equals(asunto)&&service.equals((String)getValueAt("datosAPP.pb_cod_serv"))) {
					// Si el asunto coincide 
						Object firma = ( kcCuenta.getValueAt("s_LimDiario"));
					
						setValueAt("firma",new Double(firma.toString()));
						bEncontrado = true;
					} 
				}
				
			}
			if(asunto.substring(10,12).equals("01")||asunto.substring(10,12).equals("02"))
			{
				asunto=asunto.substring(0,20);
				icCuentas = (IndexedCollection) getElementAt("s_salida_cuentas.s_lista_cuentas");
				enCuentas = icCuentas.getEnumeration();
				while (enCuentas.hasMoreElements() && !bEncontrado) {
				kcCuenta = (KeyedCollection) enCuentas.nextElement();
				String service=(String)kcCuenta.getValueAt("s_permiso");
					if (((String) kcCuenta.getValueAt("s_clave_asunto")).equals(asunto)&&service.equals((String)getValueAt("datosAPP.pb_cod_serv"))) {
					// Si el asunto coincide 
						Object firma = ( kcCuenta.getValueAt("s_LimDiario"));
						Double a=new Double(firma.toString());
						double f=a.doubleValue();
						if(f>999999999)
						{
							f=999999999;
						}
						
						setValueAt("firma",new Double(f));
						bEncontrado = true;
					} 
				}
			}
			
		} catch (Exception e) {
			Trace.trace(Trace.VTF, "Error en consultarfirma() de opgestion. " + e.toString());
			//BbvaARQException Barq =ManejarExcepcion(1,"","","",e,"",this,"","");
			//ManejarExcepcion(4,"","","",Barq,"",this,"","");
			//throw Barq;
			setValueAt("firma",new Double(0));
		}
	}else{
		if(asunto.substring(10,12).equals("01")||asunto.substring(10,12).equals("02")||asunto.substring(10,12).equals("19"))
		{
			asunto=asunto.substring(0,20);
			icCuentas = (IndexedCollection) getElementAt("s_salida_cuentas.s_lista_cuentas");
			enCuentas = icCuentas.getEnumeration();
			while (enCuentas.hasMoreElements() && !bEncontrado) {
			kcCuenta = (KeyedCollection) enCuentas.nextElement();
			String service=(String)kcCuenta.getValueAt("s_permiso");
				if (((String) kcCuenta.getValueAt("s_clave_asunto")).equals(asunto)&&service.equals((String)getValueAt("datosAPP.pb_cod_serv"))) {
				// Si el asunto coincide 
					Object firma = ( kcCuenta.getValueAt("s_LimDiario"));
					Double a=new Double(firma.toString());
					double f=a.doubleValue();
					setValueAt("firma",new Double(f));
					bEncontrado = true;
				} 
			}
		}
		if(asunto.substring(10,12).equals("96"))
		{
			asunto=asunto.substring(0,20);
			icCuentas = (IndexedCollection) getElementAt("s_salida_prestamos.s_lista_prestamos");
			enCuentas = icCuentas.getEnumeration();
			while (enCuentas.hasMoreElements() && !bEncontrado) {
			kcCuenta = (KeyedCollection) enCuentas.nextElement();
			String service=(String)kcCuenta.getValueAt("s_permisop");
				if (((String) kcCuenta.getValueAt("s_clave_asunto")).equals(asunto)&&service.equals((String)getValueAt("datosAPP.pb_cod_serv"))) {
				// Si el asunto coincide 
					Object firma = ( kcCuenta.getValueAt("s_LimDiario"));
				
					setValueAt("firma",new Double(firma.toString()));
					bEncontrado = true;
				} 
			}
			
		}
		
	}
	
}
	public void validaman(String servicio) throws Exception
	{
		IndexedCollection icContextoMan;
		KeyedCollection kcMan,kcMan2;
		icContextoMan=(IndexedCollection) getElementAt("MancomunadaFlag");
		Enumeration enCuentas = icContextoMan.getEnumeration();
			String serial=(String)getValueAt("s_serial_token");
		String poderfir=(String)getValueAt("s_poder_firma");
		String validafir=(String)getValueAt("s_poder_valida");
		serial=serial.trim();
		if(serial!=null &&poderfir!=null&&validafir!=null)
		{
			
			
			if(!serial.equals("")&&poderfir.equals("S")&&validafir.equals("S"))
			{
				setValueAt("s_mancomunada","NO");
			}
			else
			{
				setValueAt("s_mancomunada","SI");
			}
		}
		int aux=0;
		
		while (enCuentas.hasMoreElements()) {
			
			kcMan2 =(KeyedCollection) enCuentas.nextElement();
			String service=(String)kcMan2.getValueAt("codServicio");
			String man=(String)kcMan2.getValueAt("mancomunado");
			if(man.equals("SI")&&service.equals((String)getValueAt("datosAPP.pb_cod_serv")))
			{
				if(!service.equals("6008")&&!service.equals("6009"))
				{
					setValueAt("s_mancomunada","SI");
				}
			}
			aux++;
			if(aux>30)
			{
				break;
			}
			
		}
		
		String serviceman=((String)getValueAt("datosAPP.pb_cod_serv"));
		if(serviceman.equals("6008"))
		{ 
			setValueAt("s_mancomunada","NO");
		}
	}
	public void sesion() throws Exception
	{
		try{
			Trace.trace(64, getClass().getName() + " generar sesion ");
		if(getValueAt("s_hay_sesion").equals("no"))
		{
			Trace.trace(64, getClass().getName() + " *** No esta generando sesion ");
			
			om=creaOM("sign_on_om"); 
			String service=(String)getValueAt("datosAPP.pb_cod_serv");
			if(service.equals("6041"))
			{
				 om.setValueAt("Entrada.BCODACCC", (String)getValueAt("s_cod_logon")+(String)getValueAt("s_cod_usuarisc"));
		    	  om.setValueAt("Entrada.BASUNPRO", (String)getValueAt("s_cod_usuarisc"));
			}
			else
			{
	    	  om.setValueAt("Entrada.BCODACCC", (String)getValueAt("datosAPP.iv-cod_emp")+(String)getValueAt("datosAPP.iv-cod_usu"));
	    	  om.setValueAt("Entrada.BASUNPRO", (String)getValueAt("datosAPP.iv-cod_usu"));
			}
	    	  //om.setValueAt("Entrada.BASUNASO", getValueAt("s_ip"));	        
	       	//NGE  JCAG INICIO
				try{
					ejecutarOM(om);
				}catch(Exception e)
				{
					throw new BbvaException("0102");
				}
				setValueAt("s_cod_nomconta", om.getValueAt("Salida.BNOMBREE"));
				setValueAt("s_cod_nomempre", om.getValueAt("Salida.BNOMBREM"));
				//setValueAt("s_ind_firma", om.getValueAt("Salida.BCODOCUP"));
				setValueAt("s_hay_sesion", "si");
				
		
			
		}
		}
		catch(Exception e)
		{
			Trace.trace(64, getClass().getName() + " ejecutarPrecargaUnica() " + e.toString());
			throw new BbvaException("0102");
		}	
	}
	// GP 11311 VYGTS Inicio
	public boolean autogestionFirmas(
			String FECHA,
			String DESCRIPCION,
			String IPCLIENTE,
			String USUARIO,
			String REFERENCIA,
			String EJECUTAROM,
			String claseord,
			String tipo_Asunto,
			IndexedCollection VectorSalida,
			String CuentaOrdenante,
			String descripcioncab,
			String codServicio,
			String DES_SERVICIO,
			String PRODUCTO,
			String TIPOPRODUCTO,
			int estadoFUA,
			String estadoCAB
	) throws Exception {
		FECHA = FECHA.replaceAll("/", "-");
		String insertaTTLNEMAN =
			"INSERT INTO TLNE.TTLNEMAN (TLNE_IDORDEN, TLNE_REFERENCIA, TLNE_BCODACCC,TLNE_BCODCTAA,TLNE_BPALACCE,TLNE_BPALACC2,TLNE_BASUNPRO,TLNE_BASUNASO,TLNE_BFECHA01,TLNE_BFECHA02,TLNE_BIMPORTE,TLNE_BIMPOAUX,TLNE_BINDPAGI,TLNE_BINDAUX1,TLNE_BINDAUX2,TLNE_BCODAUX1,TLNE_BCODAUX2,TLNE_BNUMAUX1,TLNE_BNUMAUX2,TLNE_BPAGINAC,TLNE_NOMOM, TLNE_DESCRIP) VALUES(?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
		String insertaTTLBHCAB =
			"INSERT INTO TLCL.TTLBHCAB (COD_CLIECASH, COD_CLASEORD, COD_IDORDEN,COD_CDNIFTR,COD_SUFPRESE,COD_DIISOALF,FEC_PROCESCA,DES_REFICHER,DES_NOMFICH,XSN_MEDCREAC,XSN_MODCREAC,FEC_BORRCASH,FEC_ESTACASH,XSN_ACTAUTDE,QTY_TOTIMPOR,QNU_TOTREGIS,XSN_FORMPAGO,COD_ESTACASH,QNU_PESOFIR,QNU_SIZE,DES_NOMFICHE,QNU_NUMITEM,DES_PATH,DES_FICHEROH,AUD_FMODIFIC,AUD_USUARIO,COD_DETSEROR,XTI_DETSEROR,COD_PRODCART,XSN_SUCURSAL,DES_PATHXML,DES_PATHHTML,DES_FXMLHTML,XTI_VALPERT,COD_LTIPO,DES_LDESC,XSN_BORRADO,QTY_IMPFINA,QNU_NUMREME,TIM_ORDEN) VALUES(?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, current timestamp)";
		String insertaTTLBHCOR =
			"INSERT INTO TLCL.TTLBHCOR (COD_IDORDEN, COD_CLIECASH, COD_CLASEORD,COD_CABECORI,COD_CDNITR,COD_SUFPRESE,COD_IDORIGEN,DES_CUENORIG,COD_BANCINTE,COD_BANCSB,COD_DIISOALF,COD_FORMASUN,XTI_CLASEASU,COD_SWIFT,COD_BANABA,DES_FICHEROH,AUD_FMODIFIC,AUD_USUARIO,XTI_EMISOR,DES_ORDPAGO,XTI_INDMODAL) VALUES(?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		String insertaTTLBHAUT =
			"INSERT INTO TLCL.TTLBHAUT (ID_ORDEN, REFERENCIA, SERVICIO, DESC_SERV, ASUNTO, TIPO_ASUNTO, SOLICITUD, TIPO_GESTION, CLASE_ORDEN, LIM_OPER, LIM_DIA, LIM_MEN, LIMITES, AUD_FMODIFIC, AUD_USUARIO,ID_GRUPO, LIT_GRUPO) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		String insertaTTLBHFUA =
			"INSERT INTO TLCL.TTLBHFUA (COD_CLIECASH, COD_CLASEORD, COD_IDORDEN, COD_ACCION, COD_IDACCION, COD_USUARIO, FEC_ACCIO, COD_USUFIRMA, COD_USUPODER, COD_ESTADFIC, FEC_ACCION, AUD_FMODIFIC, AUD_USUARIO, COD_IPCLIENT) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		boolean correcto = false;
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		KeyedCollection datos = null;
		String idorden = "99999999";
		idorden = aleatorio(); 
		setValueAt("idOrden", idorden);
		Trace.trace(Trace.Information, getClass().getName() + " IDORDEN:() " +idorden);
		Trace.trace(Trace.Information, getClass().getName() + " IDORDEN:() " +(String)getValueAt("idOrden"));
		Enumeration VectorSalidaEnumeration = VectorSalida.getEnumeration(); 
		KeyedCollection kcVectorEntrada ;
		String RefUnica = "00260082";
		if(claseord.equals("RAS") || claseord.equals("RAR")|| claseord.equals("DEP")|| claseord.equals("APP") || claseord.equals("RDS")){
			if(tipo_Asunto.equals("ASUNTO") || tipo_Asunto.equals("ASUNTO_SER")){
				conn = ConsultaBD.getConexion("BDMexico");
				while (VectorSalidaEnumeration.hasMoreElements()) {
					kcVectorEntrada = (KeyedCollection) VectorSalidaEnumeration.nextElement();
					String sTitulo= (String)kcVectorEntrada.getValueAt("TITULO");
					String sAsunto= (String)kcVectorEntrada.getValueAt("PRODUCTO");
					String tAsunto=(String)kcVectorEntrada.getValueAt("TIPO-PRODUCTO");
					try {
						 stm = conn.prepareStatement(insertaTTLBHAUT);
						 stm.setString(1,idorden);
						 stm.setString(2,RefUnica+REFERENCIA);
						 stm.setString(3, codServicio);
						 stm.setString(4, DES_SERVICIO); 
						 stm.setString(5, sAsunto);
						 stm.setString(6, tAsunto);
						 stm.setString(7, DESCRIPCION);
						 stm.setString(8, tipo_Asunto);
						 stm.setString(9, claseord );
						 stm.setString(10, "");
						 stm.setString(11, "");
						 stm.setString(12, "");
						 stm.setString(13, "");
						 stm.setString(14, FECHA);
						 stm.setString(15, USUARIO);
						 stm.setString(16, tAsunto);
						 stm.setString(17, sTitulo);
						 stm.execute();
					}catch (SQLException e) {
						Trace.trace(Trace.Error, getClass().getName() + e);
					}					
				}
			}
			else if( tipo_Asunto.equals("SER_ASUNTO")){
				conn = ConsultaBD.getConexion("BDMexico");
				while (VectorSalidaEnumeration.hasMoreElements()) {
					kcVectorEntrada = (KeyedCollection) VectorSalidaEnumeration.nextElement();
					String Productos ="";
					String CodProducto = "";
					String sTitulo = "";
					String sAsunto = "";
					String CodGrupo = "";
					String Title = "";
					if(claseord.equals("RAS")){
						sTitulo= (String)kcVectorEntrada.getValueAt("SERVICIO");
						sAsunto= (String)kcVectorEntrada.getValueAt("DES-SERVICIO");
						CodGrupo = (String)kcVectorEntrada.getValueAt("COD-GRUPO");
						Title=(String)kcVectorEntrada.getValueAt("TITULO");
					}else{
						sTitulo = codServicio;
						sAsunto = DES_SERVICIO;		
						Productos = (String)kcVectorEntrada.getValueAt("PRODUCTO");
						CodProducto = (String)kcVectorEntrada.getValueAt("TIPO-PRODUCTO");
						CodGrupo = (String)kcVectorEntrada.getValueAt("CODIGO-GRUPO");
						Title=(String)kcVectorEntrada.getValueAt("TITULO");						
					}
					try {
						 stm = conn.prepareStatement(insertaTTLBHAUT);
						 stm.setString(1,idorden);
						 stm.setString(2,RefUnica+REFERENCIA);
						 stm.setString(3, sTitulo);
						 stm.setString(4, sAsunto); 
						 stm.setString(5, Productos);
						 stm.setString(6, CodProducto);
						 stm.setString(7, DESCRIPCION);
						 stm.setString(8, tipo_Asunto);
						 stm.setString(9, claseord );
						 stm.setString(10, "");
						 stm.setString(11, "");
						 stm.setString(12, "");
						 stm.setString(13, "");
						 stm.setString(14, FECHA);
						 stm.setString(15, USUARIO);
						 stm.setString(16, CodGrupo);
						 stm.setString(17, Title);
						 stm.execute();
					}catch (SQLException e) {
						Trace.trace(Trace.Error, getClass().getName() + e);
					}					
				}
			}
			else{
				conn = ConsultaBD.getConexion("BDMexico");
				while (VectorSalidaEnumeration.hasMoreElements()) {
					kcVectorEntrada = (KeyedCollection) VectorSalidaEnumeration.nextElement();
					String servicio= (String)kcVectorEntrada.getValueAt("PRODUCTO");//Codigo del Servicio
					String dservicio=(String)kcVectorEntrada.getValueAt("TIPO-PRODUCTO");//Descripcion del Servicio
						String sTitulo= (String)kcVectorEntrada.getValueAt("TITULO");//Literal del grupo
						String tAsunto=(String)kcVectorEntrada.getValueAt("CODIGO-GRUPO");//ID GRupo
					 try {
							stm = conn.prepareStatement(insertaTTLBHAUT);
							stm.setString(1,idorden);
							stm.setString(2,"00260082"+REFERENCIA);
							stm.setString(3, codServicio);
							stm.setString(4, DES_SERVICIO); 
							stm.setString(5, servicio);
							stm.setString(6, dservicio);
							stm.setString(7, DESCRIPCION);
							stm.setString(8, tipo_Asunto);
							stm.setString(9, claseord );
							stm.setString(10, "");
							stm.setString(11, "");
							stm.setString(12, "");
							stm.setString(13, "");
							stm.setString(14, FECHA);
							stm.setString(15, USUARIO);
								stm.setString(16, tAsunto);
								stm.setString(17, sTitulo);
							stm.execute();
					 }catch (SQLException e) {
						 Trace.trace(Trace.Error, getClass().getName() + e);
					 }
				}
			}
			
		}else if(claseord.equals("ASP") || claseord.equals("DSP")){
			conn = ConsultaBD.getConexion("BDMexico");
			stm = conn.prepareStatement(insertaTTLBHAUT);
			if(claseord.equals("ASP")){
				while (VectorSalidaEnumeration.hasMoreElements()) {
					Trace.trace(Trace.Information, "va contando!!");
					try{
						kcVectorEntrada = (KeyedCollection) VectorSalidaEnumeration.nextElement();            
						stm.setString(1, idorden );
						stm.setString(2, "00260082"+REFERENCIA);
						stm.setString(3, (String) kcVectorEntrada.getValueAt("codigoServicio"));
						stm.setString(4, (String) kcVectorEntrada.getValueAt("nombreServicio"));
						stm.setString(5, "N/A");
						stm.setString(6, "N/A");
						stm.setString(7, "ALTA");
						stm.setString(8, "SERVICIO");
						stm.setString(9, claseord);
						stm.setString(10, (String) kcVectorEntrada.getValueAt("loperacion"));
						stm.setString(11, (String) kcVectorEntrada.getValueAt("ldiario"));
						stm.setString(12, (String) kcVectorEntrada.getValueAt("lmensual"));
						stm.setString(13, (String) kcVectorEntrada.getValueAt("limite"));
						stm.setString(14, FECHA);
						stm.setString(15, USUARIO);
						stm.setString(16, (String)kcVectorEntrada.getValueAt("nGrupo")	);
						stm.setString(17, (String)kcVectorEntrada.getValueAt("nombreGrupo")	);
						stm.execute();
					}catch(SQLException e) {
						 Trace.trace(Trace.Error, getClass().getName() + e);
					}
				}
			}else{			
				while (VectorSalidaEnumeration.hasMoreElements()) {
					try{
						kcVectorEntrada = (KeyedCollection) VectorSalidaEnumeration.nextElement();            
						stm.setString(1, idorden );
						stm.setString(2, "00260082"+REFERENCIA);
						stm.setString(3, (String) kcVectorEntrada.getValueAt("codigoServicio"));
						stm.setString(4, (String) kcVectorEntrada.getValueAt("nombreServicio"));
						stm.setString(5, "N/A");
						stm.setString(6, "N/A");
						stm.setString(7, "BAJA");							
						stm.setString(8, "SERVICIO");
						stm.setString(9, claseord);
						stm.setString(10, "");
						stm.setString(11, "");
						stm.setString(12, "");
						stm.setString(13, "");
						stm.setString(14, FECHA);
						stm.setString(15, USUARIO);
						stm.setString(16, (String)kcVectorEntrada.getValueAt("nGrupo")	);
						stm.setString(17, (String)kcVectorEntrada.getValueAt("nombreGrupo")	);
						stm.execute();
					}catch(SQLException e) {
						 Trace.trace(Trace.Error, getClass().getName() + e);
					}
				}
			}
		}
		try{
			String fechaSinGuiones = FECHA.replaceAll("-", "");
			correcto=true;	
			if(EJECUTAROM!=null){
				conn = ConsultaBD.getConexionBBVNet();
				stm = conn.prepareStatement(insertaTTLNEMAN);
				stm.setString(1, idorden);
				stm.setString(2, REFERENCIA+USUARIO);
				stm.setString(3, "");
				stm.setString(4, DESCRIPCION);
				stm.setString(5, "");
				stm.setString(6, "");
				stm.setString(7, "");
				stm.setString(8, "");
				stm.setString(9, fechaSinGuiones);
				stm.setString(10, "");
				stm.setDouble(11, 0);
				stm.setDouble(12, 0);
				stm.setString(13, "");
				stm.setString(14, "");
				stm.setString(15, "");
				stm.setString(16, "");
				stm.setString(17, "");
				stm.setDouble(18, 0);
				stm.setDouble(19, 0);
				stm.setString(20, "");
				stm.setString(21, EJECUTAROM);
				stm.setString(22, descripcioncab);
				stm.execute();
			}
				
			String tipo = "";
			conn = ConsultaBD.getConexion("BDMexico");
			stm = conn.prepareStatement(insertaTTLBHCOR);
			stm.setString(1, idorden);
			stm.setString(2, RefUnica+REFERENCIA);
			stm.setString(3, claseord);
			stm.setInt(4, 0);
			stm.setString(5, "*********");
			stm.setString(6, "***");
			stm.setString(7, "");
			stm.setString(8, CuentaOrdenante);
			stm.setString(9, "");
			stm.setInt(10, 82);
			stm.setString(11, "COP");
			stm.setInt(12, 0);
			stm.setString(13, tipo);
			stm.setString(14, "");
			stm.setString(15, "");
			stm.setString(16, "");
			stm.setString(17, FECHA);
			stm.setString(18, USUARIO);
			stm.setString(19, "");
			stm.setString(20, "");
			stm.setString(21, "");
			stm.execute();

			stm = conn.prepareStatement(insertaTTLBHCAB);
			stm.setString(1, RefUnica+REFERENCIA);
			stm.setString(2, claseord);
			stm.setString(3, idorden);
			stm.setString(4, "*********");
			stm.setString(5, "***");
			stm.setString(6, "COP");
			stm.setString(7, fechaSinGuiones);
			stm.setString(8, "");
			stm.setString(9, descripcioncab);
			stm.setString(10, "");
			stm.setString(11, "O");
			stm.setString(12, "");
			stm.setString(13, fechaSinGuiones);
			stm.setString(14, "");
			stm.setDouble(15, 0);
			stm.setInt(16, 0);
			stm.setString(17, "1");
			stm.setString(18, estadoCAB);
			stm.setDouble(19, 0.0);
			stm.setDouble(20, 0);
			stm.setString(21, "");
			stm.setDouble(22, 0.0);
			stm.setString(23, "");
			stm.setString(24, "");
			stm.setString(25, FECHA);
			stm.setString(26, USUARIO);
			stm.setString(27, "");
			stm.setString(28, "");
			stm.setString(29, "");
			stm.setString(30, "");
			stm.setString(31, "");
			stm.setString(32, "");
			stm.setString(33, "");
			stm.setString(34, "");
			stm.setString(35, "");
			stm.setString(36, "");
			stm.setString(37, "");
			stm.setDouble(38, 0.0);
			stm.setDouble(39, 0.0);
			stm.execute();
			
			conn = ConsultaBD.getConexion("BDMexico");
			stm = conn.prepareStatement(insertaTTLBHFUA);
	        stm.setString(1,RefUnica+REFERENCIA);
	        stm.setString(2, claseord);
	        stm.setString(3, idorden);
	        stm.setInt(4, estadoFUA);
	        stm.setInt(5, 0);
			stm.setString(6, USUARIO);
			stm.setString(7, fechaSinGuiones);
			if(((String)getValueAt("s_poder_firma")).equals("S")){
				stm.setString(8, USUARIO);
				stm.setString(9, (String)getValueAt("s_poder_firma"));
			}else{
				stm.setString(8, "");
				stm.setString(9, "");
			}			
			stm.setString(10, estadoCAB);
			SimpleDateFormat hora = new SimpleDateFormat ("hhmmss");
			stm.setString(11, (String) hora.format(new Date()));
			stm.setString(12, FECHA);
			stm.setString(13, "");
			stm.setString(14, IPCLIENTE);
			stm.execute();
			if(((String)getValueAt("s_poder_firma")).equals("S")||EJECUTAROM==null){
				conn = ConsultaBD.getConexion("BDMexico");
				stm = conn.prepareStatement(insertaTTLBHFUA);
		        stm.setString(1,RefUnica+REFERENCIA);
		        stm.setString(2, claseord);
		        stm.setString(3, idorden);
		        stm.setInt(4, 1);
		        stm.setInt(5, 0);
				stm.setString(6, USUARIO);
				stm.setString(7, fechaSinGuiones);
				stm.setString(8, "");
				stm.setString(9, "");
				stm.setString(10, "024");				
				stm.setString(11, (String) hora.format(new Date()));
				stm.setString(12, FECHA);
				stm.setString(13, "");
				stm.setString(14, IPCLIENTE);
				stm.execute();
			}
		} catch (SQLException aSQLExc) {
			Trace.trace(Trace.Error, "", aSQLExc.toString());
			return false;
		} catch (Exception aExc) {
			Trace.trace(Trace.Error, "", aExc.toString());
			aExc.printStackTrace();
			return false;
		}
		finally {
			try {
				if (stm != null)
					stm.close();
			} catch (SQLException aSQLExcII) {
				 throw new BbvaARQException( "ARQ0300700",
				 BbvaARQException.DB2, aSQLExcII.getSQLState(), aSQLExcII);
			}try {
				if (conn != null)
					if (!conn.isClosed())
						conn.close();
			} catch (SQLException aSQLExcIII) {
				throw new BbvaARQException( "ARQ0300710",
				 BbvaARQException.DB2, aSQLExcIII.getSQLState(), aSQLExcIII);
				
			}
		}
		return correcto;

	}
	// GP 11311 VYGTS FIN

	/**
	 * inicio metodo que actualiza los campos en la base de datos
	 *  cuando el usuario realiza la firma dentro del flujo**/
	
	public void firmarAutoGestion(String referencia,String usuario,String poderFirma,String idOrden, String claseOrd) throws BbvaARQException, SQLException, DSEObjectNotFoundException{
		Connection 			conn 		= null;
		PreparedStatement	stm		= null;
		double porcentajeFirma=0;
		String estadoCab="019";
		if(poderFirma.equals("M2")){
			porcentajeFirma=0.5;
		}else if(poderFirma.equals("M3")){
			porcentajeFirma=0.33;
		}else if(poderFirma.equals("M4")){
			porcentajeFirma=0.25;
		}//GP-10686 INICIO	en firmarAutogestin en OPgestion1
		else if(poderFirma.equals("S")){
			porcentajeFirma=1.0;
			estadoCab="022";
		}
		//GP-10686 FIN
		 String referen="00260082"+referencia;
		String updateTTLBHCAB =	"UPDATE TLCL.TTLBHCAB set COD_ESTACASH = '"+estadoCab+"',QNU_PESOFIR='"+porcentajeFirma+"' WHERE COD_IDORDEN = '"+idOrden+"'";
		String insertaTTLBHFUA =
				"INSERT INTO TLCL.TTLBHFUA (COD_CLIECASH, COD_CLASEORD, COD_IDORDEN, COD_ACCION, COD_IDACCION, COD_USUARIO, FEC_ACCIO, COD_USUFIRMA, COD_USUPODER, COD_ESTADFIC, FEC_ACCION, AUD_FMODIFIC, AUD_USUARIO, COD_IPCLIENT) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			/*Se actualiza el estado en la tabla TTLBHCAB al estado 019 (Firmado Parcialmente)*/
			conn=ConsultaBD.getConexion("BDMexico");
			stm=conn.prepareStatement(updateTTLBHCAB);
			stm.execute();
			
			/*Se inserta en la tabla TTLBHFUA el registro de la accion de firma*/
			java.util.Calendar fecha = java.util.Calendar.getInstance();
			int mes=fecha.get(java.util.Calendar.MONTH);
			mes=mes+1;
			String mr=Integer.toString(mes);
			if(mr.length()<2)
			{
				mr="0"+mr;
			}
			int day=fecha.get(java.util.Calendar.DATE);
			String dia=Integer.toString(day);
			if(dia.length()<2)
			{
				dia="0"+dia;
			}
			String f=fecha.get(java.util.Calendar.YEAR) + "-"  + mr    + "-"  +dia;
			String f1=fecha.get(java.util.Calendar.YEAR)   + mr    +dia;
			SimpleDateFormat hora = new SimpleDateFormat ("hhmmss");
			stm = conn.prepareStatement(insertaTTLBHFUA);		
			stm.setString(1, referen);
			stm.setString(2, claseOrd);
			stm.setString(3, idOrden);
			stm.setInt(4, 10);
			stm.setInt(5, 0);
			stm.setString(6, usuario);
			stm.setString(7, f1);
			stm.setString(8, usuario);
			stm.setString(9, poderFirma);
			stm.setString(10, "019");
			stm.setString(11, (String) hora.format(new Date()));
			stm.setString(12, f);
			stm.setString(13, usuario);
			stm.setString(14, (String)getValueAt("s_ip"));		
			stm.execute();
			
		} catch (BbvaARQException e) {
			Trace.trace(Trace.Error, getClass().getName()+"enviarAutoGestion", e.getMessage());
			throw e;
		} catch (SQLException e) {		
			Trace.trace(Trace.Error, getClass().getName()+"enviarAutoGestion", e.getMessage());
			throw e;
		} catch (DSEObjectNotFoundException e) {
			Trace.trace(Trace.Error, getClass().getName()+"enviarAutoGestion", e.getMessage());
			throw e;
		}
		
	}
	/**
	 * fin metodo que actualiza los campos en la base de datos
	 *  cuando el usuario realiza la firma dentro del flujo**/
	
	
	// GP 11311 VYGTS FIN
	public boolean  mancomunadas(String BCODACCC, String BCODCTAA, String BPALACCE, String BPALACC2,
			String BASUNPRO, String BASUNASO, String BFECHA01, String BFECHA02, double BIMPORTE,
			double BIMPOAUX, String BINDPAGI, String BINDAUX1, String BINDAUX2, String BCODAUX1,
			String BCODAUX2, double BNUMAUX1, double BNUMAUX2, String BPAGINAC, String nomom,String descripcion	,String claseord
	) throws Exception {
		String insertaoper =
			"INSERT INTO TLNE.TTLNEMAN (TLNE_IDORDEN, TLNE_REFERENCIA, TLNE_BCODACCC,TLNE_BCODCTAA,TLNE_BPALACCE,TLNE_BPALACC2,TLNE_BASUNPRO,TLNE_BASUNASO,TLNE_BFECHA01,TLNE_BFECHA02,TLNE_BIMPORTE,TLNE_BIMPOAUX,TLNE_BINDPAGI,TLNE_BINDAUX1,TLNE_BINDAUX2,TLNE_BCODAUX1,TLNE_BCODAUX2,TLNE_BNUMAUX1,TLNE_BNUMAUX2,TLNE_BPAGINAC,TLNE_NOMOM, TLNE_DESCRIP) VALUES(?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
		String insertaTTLBHCAB =
			"INSERT INTO TLCL.TTLBHCAB (COD_CLIECASH, COD_CLASEORD, COD_IDORDEN,COD_CDNIFTR,COD_SUFPRESE,COD_DIISOALF,FEC_PROCESCA,DES_REFICHER,DES_NOMFICH,XSN_MEDCREAC,XSN_MODCREAC,FEC_BORRCASH,FEC_ESTACASH,XSN_ACTAUTDE,QTY_TOTIMPOR,QNU_TOTREGIS,XSN_FORMPAGO,COD_ESTACASH,QNU_PESOFIR,QNU_SIZE,DES_NOMFICHE,QNU_NUMITEM,DES_PATH,DES_FICHEROH,AUD_FMODIFIC,AUD_USUARIO,COD_DETSEROR,XTI_DETSEROR,COD_PRODCART,XSN_SUCURSAL,DES_PATHXML,DES_PATHHTML,DES_FXMLHTML,XTI_VALPERT,COD_LTIPO,DES_LDESC,XSN_BORRADO,QTY_IMPFINA,QNU_NUMREME,TIM_ORDEN) VALUES(?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, current timestamp)";
		String insertaTTLBHCOR =
			"INSERT INTO TLCL.TTLBHCOR (COD_IDORDEN, COD_CLIECASH, COD_CLASEORD,COD_CABECORI,COD_CDNITR,COD_SUFPRESE,COD_IDORIGEN,DES_CUENORIG,COD_BANCINTE,COD_BANCSB,COD_DIISOALF,COD_FORMASUN,XTI_CLASEASU,COD_SWIFT,COD_BANABA,DES_FICHEROH,AUD_FMODIFIC,AUD_USUARIO,XTI_EMISOR,DES_ORDPAGO,XTI_INDMODAL) VALUES(?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		// GP11988 - Entelgy - 13-04-2015 - INI
		String insertTTLBHFUA = 
			"INSERT INTO TLCL.TTLBHFUA (COD_CLIECASH, COD_CLASEORD, COD_IDORDEN, COD_IDACCION, COD_USUARIO, FEC_ACCIO, FEC_ACCION, COD_USUFIRMA, COD_USUPODER, COD_ESTADFIC, COD_ACCION, COD_IPCLIENT, AUD_FMODIFIC, AUD_USUARIO) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		// GP11988 - Entelgy - 13-04-2015 - FIN
		
		boolean 			correcto 	= false;	
		//String				usuario		= null;
		Connection 			conn 		= null;
		PreparedStatement	stm 		= null;
		ResultSet 			rs	 		= null;
		KeyedCollection		datos 		= null;

			
		try
		{
			String pathTml = claseord.equals("GUD")?"GUD#00000000000000000000":"";
			// Obtenemos el usuario de la BBDD del contexto de sesi�n(DSEDATA)
			datos = (KeyedCollection) KeyedCollection.readObject("GlobalComun");
			//usuario = (String) datos.getValueAt("UserBD");
			
			// Se crea una conexi�n a BBDD
			conn = ConsultaBD.getConexionBBVNet();
			String REFERENCIA = null;
			String USUARIO = null;
			if(claseord.equals("ASP")){
				
				REFERENCIA =BPAGINAC;				
			}else{
			REFERENCIA =(String)getValueAt("datosAPP.iv-cod_emp")+(String)getValueAt("datosAPP.iv-cod_usu");
			}
			USUARIO =(String)getValueAt("datosAPP.iv-cod_usu");
			String idorden="99999999";
			idorden=aleatorio();
	        // Se crea la sentencia SQL
	        stm = conn.prepareStatement(insertaoper);
	        stm.setString(1, idorden);
	        stm.setString(2, REFERENCIA);
	        stm.setString(3, BCODACCC);
	        stm.setString(4, BCODCTAA);
			stm.setString(5, BPALACCE);
			stm.setString(6, BPALACC2);
			stm.setString(7, BASUNPRO);
			stm.setString(8, BASUNASO);
			stm.setString(9, BFECHA01);
			stm.setString(10, BFECHA02);
			stm.setDouble(11, BIMPORTE);
			stm.setDouble(12, BIMPOAUX);
			if(claseord.equals("ASP")){
				stm.setString(13, "");
				
			}else{
			stm.setString(13, BINDPAGI);
			}
			stm.setString(14, BINDAUX1);
			stm.setString(15, BINDAUX2);
			stm.setString(16, BCODAUX1);
			stm.setString(17, BCODAUX2);
			stm.setDouble(18, BNUMAUX1);
			stm.setDouble(19, BNUMAUX2);
			stm.setString(20, BPAGINAC);
			stm.setString(21, nomom);
			stm.setString(22, descripcion);

			// Se ejecuta la sentencia SQL
			stm.execute();
			if(nomom.equals("retorno_trasbbvafam_om")||nomom.equals("fondo_plazo_reem1_om")||nomom.equals("retorno_trasbd_om")||nomom.equals("retorno_trasfe_om"))
			{
				String tipo1="";
				try{
					 tipo1=BASUNPRO.substring(0,2);
				
				if((tipo1.equals("CC")||tipo1.equals("AH")))
				{
					if(nomom.equals("fondo_plazo_reem1_om"))
					{
						BASUNPRO=BASUNASO.substring(0,22);
						
					}
					else{
						BASUNPRO=BASUNASO;
					}
				}
				}catch(Exception e)
				{}
			}
			String tipo="";
			try{
				 tipo=BASUNPRO.substring(0,2);
			}catch(Exception e)
			{}
			if(nomom.equals("retorno_pagotarje_om")||nomom.equals("retorno_pagotarje_otros_om")||nomom.equals("retorno_pagotcter_om")||nomom.equals("retorno_avancetarje_om"))
			{
				
					BASUNPRO=BASUNASO;
				
			}
			if(nomom.equals("fondo_plazo_sucr_om"))
			{
				
					BASUNPRO="  "+BASUNPRO;
				
			}
	//Inicio GP11152 - VyG 08/04/2014
			if(nomom.equals("inscribir_om")||nomom.equals("borrar_om"))
			{

					BASUNPRO="  "+BCODCTAA;

			}
			if(nomom.equals("transferir_om"))
			{

					BASUNPRO="  "+BCODACCC;
					BIMPORTE=Double.parseDouble(BPALACCE);

			}
			if(nomom.equals("pago_om"))
			{

					BASUNPRO=BASUNASO;
					

			}
			//FIN GP11152 - VyG 08/04/2014
			tipo="";
			try{
				 tipo=BASUNPRO.substring(0,2);
			}catch(Exception e)
			{}
			
			
			correcto = true;
			ConsultaBD ConsultaB=new ConsultaBD();
			//ConsultaB.setRecursoDefecto("BDMexico");
			//conn = ConsultaBD.getConexionBBVNet();
			conn = ConsultaBD.getConexion("BDMexico");
			stm = conn.prepareStatement(insertaTTLBHCOR);
			REFERENCIA="00260082"+REFERENCIA.substring(0,8);
			stm.setString(1, idorden);
	        stm.setString(2, REFERENCIA);
	        stm.setString(3, claseord);
	        stm.setInt(4, 0);
			stm.setString(5, "*********");
			stm.setString(6, "***");
			stm.setString(7, "");
			if(claseord.equals("ASP")){
				stm.setString(8, "");
			// INICIO - Gp13427 Reingenieria de Divisas	
			//}else if (claseord.equals("NDV") && nomom.equals("neg_divisas_envio_om")){
		//		stm.setString(8, BASUNPRO);
			// FIN - Gp13427 Reingenieria de Divisas
			}else{
				stm.setString(8, BASUNPRO.substring(2));
			}
			stm.setString(9, "");
			stm.setInt(10, 82);
			stm.setString(11, "COP");
			stm.setInt(12, 0);
			stm.setString(13, tipo);
			stm.setString(14, "");
			stm.setString(15, "");
			stm.setString(16, "");
			java.util.Calendar fecha = java.util.Calendar.getInstance();
			int mes=fecha.get(java.util.Calendar.MONTH);
			mes=mes+1;
			String mr=Integer.toString(mes);
			if(mr.length()<2)
			{
				mr="0"+mr;
			}
			int day=fecha.get(java.util.Calendar.DATE);
			String dia=Integer.toString(day);
			if(dia.length()<2)
			{
				dia="0"+dia;
			}
			String f=fecha.get(java.util.Calendar.YEAR) + "-"  + mr    + "-"  +dia;
			String f1=fecha.get(java.util.Calendar.YEAR)   + mr    +dia;
			stm.setString(17, f);
			if(claseord.equals("ASP")){
			stm.setString(18, USUARIO);

			}
			else{
				stm.setString(18, "");
			}
			stm.setString(19, "");
			stm.setString(20, "");
			stm.setString(21, "");
			stm.execute();
			
			stm = conn.prepareStatement(insertaTTLBHCAB);
	        stm.setString(1, REFERENCIA);
	        stm.setString(2, claseord);
	        stm.setString(3, idorden);
	        stm.setString(4, "*********");
			stm.setString(5, "***");
			stm.setString(6, "COP");
			stm.setString(7, f1);
			stm.setString(8, "");
			stm.setString(9, descripcion);
			stm.setString(10, "");
			stm.setString(11, "O");
			stm.setString(12, "");
			stm.setString(13, "");
			stm.setString(14, "");
			stm.setDouble(15, BIMPORTE);
			stm.setInt(16, 0);
			stm.setString(17, "1");
			stm.setString(18, "024");
			stm.setDouble(19, 0.0);
			stm.setDouble(20, 0);
			
			// I GP9542 01/04/2014
			if(claseord.equals("PDS")){
				stm.setString(21, BCODCTAA);
			}else{
				stm.setString(21, "");
			}
			// I GP9542 01/04/2014
			stm.setDouble(22, 0.0);
			stm.setString(23, "");
			stm.setString(24, "");
			stm.setString(25, f);
			if(claseord.equals("ASP")){
			
				stm.setString(26, USUARIO);
			}
			else
			{
				stm.setString(26, "");
			}
			stm.setString(27, "");
			stm.setString(28, "");
			stm.setString(29, "");
			stm.setString(30, "");
			stm.setString(31, "");
			stm.setString(32, pathTml);
		    System.out.println(pathTml);
			stm.setString(33, BCODACCC);
			stm.setString(34, "");
			stm.setString(35, "");
			stm.setString(36, "");
			stm.setString(37, "");
			stm.setDouble(38, 0.0);
			stm.setDouble(39, 0.0);
			//stm.setString(40,CURRENT TIMESTAMP);
			stm.execute();
			
//			 GP11988 - Entelgy - 13-04-2015 - INI
			//Inserci�n en TTLBHFUA
			stm = conn.prepareStatement(insertTTLBHFUA);

			// 1. Obtenemos la referencia de la empresa
			stm.setString(1, REFERENCIA);

			// 2. y 3. Insertamos el tipo de orden y el id orden
			stm.setString(2, claseord);
			stm.setString(3, idorden);

			// 4. TLCLGnAcciones.iCREACION
			stm.setInt(4, 1);

			// 5. Obtenemos el codigo de usuario
			stm.setString(5, USUARIO);

			// 6. Obtenemos la fecha actual. En TLCL: (new
			// TLCLGnFecha()).fechaSistemaString()
			GregorianCalendar fechaSistema = new GregorianCalendar();
			Date date = new Date();
			SimpleDateFormat dt1 = new SimpleDateFormat("yyyyMMdd");
			String fechaAccion = dt1.format(date).toString();

			stm.setString(6, fechaAccion);

			// 7. Obtenemos la hora. En TLCL:
			// newTLCLGnHora().cadenaHoraHHMMSScc()

			SimpleDateFormat dt2 = new SimpleDateFormat("hhmmssSS");
			String horaMinSegMil = dt2.format(date).toString();
			horaMinSegMil = horaMinSegMil.substring(0, 8);
			stm.setString(7, horaMinSegMil);

			// 8. y 9. Usuario firma y poder de firma. Vacios ya que es
			// creacion.
			stm.setString(8, "");
			stm.setString(9, "");

			// 10. Estado del fichero. COD_ESTADFIC
			stm.setString(10, "001");

			// 11. COD_ACCION
			stm.setInt(11, 1);

			// 12. Ip del cliente
			String ipCliente = (String) getValueAt("s_ip");
			stm.setString(12, ipCliente);

			// 13. Fecha de auditoria
			java.sql.Date fechaAud = new java.sql.Date(fechaSistema.getTime().getTime());
			stm.setDate(13, fechaAud);

			// 14. Usuario auditoria
			stm.setString(14, USUARIO);
			
			stm.execute();
			// GP11988 - Entelgy - 13-04-2015 - FIN
			
			
			//ConsultaBD.setRecursoDefecto("BDColombia");
		} catch (SQLException aSQLExc) {
			Trace.trace(Trace.Error, "", aSQLExc.toString());
			// (ARQ0300527) Error producido al ejecutar la sentencia de inserci�n..			
			//throw new BbvaARQException("ARQ0300527", BbvaARQException.DB2, aSQLExc.getSQLState(), aSQLExc);
			//ConsultaBD.setRecursoDefecto("BDColombia");
			return false;
	            
		} catch (Exception aExc) {
			Trace.trace(Trace.Error, "", aExc.toString());
			// (ARQ0300527) Error producido al ejecutar la sentencia de inserci�n..
	    	aExc.printStackTrace();
			//throw new BbvaARQException("ARQ0300527", BbvaARQException.DB2, aExc.toString(), aExc);
	    	//ConsultaBD.setRecursoDefecto("BDColombia");
	    	return false;
		}
		
	
		finally {
			//--- Close Statement.
			try {
				if (stm != null)
					stm.close();
			} catch (SQLException aSQLExcII) {
				// (ARQ0300700) ConsultaBD: Error al cerrar el Statement.
				//throw new BbvaARQException( "ARQ0300700", BbvaARQException.DB2, aSQLExcII.getSQLState(), aSQLExcII);
				//ConsultaBD.setRecursoDefecto("BDColombia");
				return false;
			}
	
			//--- Close Connection.			
			try {
				if (conn != null)
					if (!conn.isClosed())
						conn.close();
			} catch (SQLException aSQLExcIII) {
				// (ARQ0300710) ConsultaBD: Error al cerrar la conexi�n con la BD.
				//throw new BbvaARQException( "ARQ0300710", BbvaARQException.DB2, aSQLExcIII.getSQLState(), aSQLExcIII);
				//ConsultaBD.setRecursoDefecto("BDColombia");
				return false;
			}
		}
		
		return correcto;
	
	}
	public boolean  mancomunadas1(String BCODACCC, String BCODCTAA, String BPALACCE, String BPALACC2,
			String BASUNPRO, String BASUNASO, String BFECHA01, String BFECHA02, double BIMPORTE,
			double BIMPOAUX, String BINDPAGI, String BINDAUX1, String BINDAUX2, String BCODAUX1,
			String BCODAUX2, double BNUMAUX1, double BNUMAUX2, String BPAGINAC, String nomom,String descripcion	,String claseord,String idorden
	) throws Exception {
		String insertaoper =
			"INSERT INTO TLNE.TTLNEMAN (TLNE_IDORDEN, TLNE_REFERENCIA, TLNE_BCODACCC,TLNE_BCODCTAA,TLNE_BPALACCE,TLNE_BPALACC2,TLNE_BASUNPRO,TLNE_BASUNASO,TLNE_BFECHA01,TLNE_BFECHA02,TLNE_BIMPORTE,TLNE_BIMPOAUX,TLNE_BINDPAGI,TLNE_BINDAUX1,TLNE_BINDAUX2,TLNE_BCODAUX1,TLNE_BCODAUX2,TLNE_BNUMAUX1,TLNE_BNUMAUX2,TLNE_BPAGINAC,TLNE_NOMOM, TLNE_DESCRIP) VALUES(?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
		String insertaTTLBHCAB =
			"INSERT INTO TLCL.TTLBHCAB (COD_CLIECASH, COD_CLASEORD, COD_IDORDEN,COD_CDNIFTR,COD_SUFPRESE,COD_DIISOALF,FEC_PROCESCA,DES_REFICHER,DES_NOMFICH,XSN_MEDCREAC,XSN_MODCREAC,FEC_BORRCASH,FEC_ESTACASH,XSN_ACTAUTDE,QTY_TOTIMPOR,QNU_TOTREGIS,XSN_FORMPAGO,COD_ESTACASH,QNU_PESOFIR,QNU_SIZE,DES_NOMFICHE,QNU_NUMITEM,DES_PATH,DES_FICHEROH,AUD_FMODIFIC,AUD_USUARIO,COD_DETSEROR,XTI_DETSEROR,COD_PRODCART,XSN_SUCURSAL,DES_PATHXML,DES_PATHHTML,DES_FXMLHTML,XTI_VALPERT,COD_LTIPO,DES_LDESC,XSN_BORRADO,QTY_IMPFINA,QNU_NUMREME,TIM_ORDEN) VALUES(?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, current timestamp)";
		String insertaTTLBHCOR =
			"INSERT INTO TLCL.TTLBHCOR (COD_IDORDEN, COD_CLIECASH, COD_CLASEORD,COD_CABECORI,COD_CDNITR,COD_SUFPRESE,COD_IDORIGEN,DES_CUENORIG,COD_BANCINTE,COD_BANCSB,COD_DIISOALF,COD_FORMASUN,XTI_CLASEASU,COD_SWIFT,COD_BANABA,DES_FICHEROH,AUD_FMODIFIC,AUD_USUARIO,XTI_EMISOR,DES_ORDPAGO,XTI_INDMODAL) VALUES(?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		boolean 			correcto 	= false;	
		//String				usuario		= null;
		Connection 			conn 		= null;
		PreparedStatement	stm 		= null;
		ResultSet 			rs	 		= null;
		KeyedCollection		datos 		= null;

			
		try
		{
			// Obtenemos el usuario de la BBDD del contexto de sesi�n(DSEDATA)
			datos = (KeyedCollection) KeyedCollection.readObject("GlobalComun");
			//usuario = (String) datos.getValueAt("UserBD");
			
			// Se crea una conexi�n a BBDD
			conn = ConsultaBD.getConexionBBVNet();
			String REFERENCIA =(String)getValueAt("datosAPP.iv-cod_emp")+(String)getValueAt("datosAPP.iv-cod_usu");
			
	        // Se crea la sentencia SQL
	        stm = conn.prepareStatement(insertaoper);
	        stm.setString(1, idorden);
	        stm.setString(2, REFERENCIA);
	        stm.setString(3, BCODACCC);
	        stm.setString(4, BCODCTAA);
			stm.setString(5, BPALACCE);
			stm.setString(6, BPALACC2);
			stm.setString(7, BASUNPRO);
			stm.setString(8, BASUNASO);
			stm.setString(9, BFECHA01);
			stm.setString(10, BFECHA02);
			stm.setDouble(11, BIMPORTE);
			stm.setDouble(12, BIMPOAUX);
			stm.setString(13, BINDPAGI);
			stm.setString(14, BINDAUX1);
			stm.setString(15, BINDAUX2);
			stm.setString(16, BCODAUX1);
			stm.setString(17, BCODAUX2);
			stm.setDouble(18, BNUMAUX1);
			stm.setDouble(19, BNUMAUX2);
			stm.setString(20, BPAGINAC);
			stm.setString(21, nomom);
			stm.setString(22, descripcion);

			// Se ejecuta la sentencia SQL
			stm.execute();
			if(nomom.equals("retorno_trasbbvafam_om")||nomom.equals("fondo_plazo_reem1_om")||nomom.equals("retorno_trasbd_om")||nomom.equals("retorno_trasfe_om"))
			{
				String tipo1="";
				try{
					 tipo1=BASUNPRO.substring(0,2);
				
				if((tipo1.equals("CC")||tipo1.equals("AH")))
				{
					if(nomom.equals("fondo_plazo_reem1_om"))
					{
						BASUNPRO=BASUNASO.substring(0,22);
						
					}
					else{
						BASUNPRO=BASUNASO;
					}
				}
				}catch(Exception e)
				{}
			}
			String tipo="";
			try{
				 tipo=BASUNPRO.substring(0,2);
			}catch(Exception e)
			{}
			if(nomom.equals("retorno_pagotarje_om")||nomom.equals("retorno_pagotarje_otros_om")||nomom.equals("retorno_pagotcter_om")||nomom.equals("retorno_avancetarje_om"))
			{
				
					BASUNPRO=BASUNASO;
				
			}
			if(nomom.equals("fondo_plazo_sucr_om"))
			{
				
					BASUNPRO="  "+BASUNPRO;
				
			}
			tipo="";
			try{
				 tipo=BASUNPRO.substring(0,2);
			}catch(Exception e)
			{}
			
			
			correcto = true;
			ConsultaBD ConsultaB=new ConsultaBD();
			//ConsultaB.setRecursoDefecto("BDMexico");
			//conn = ConsultaBD.getConexionBBVNet();
			conn = ConsultaBD.getConexion("BDMexico");
			stm = conn.prepareStatement(insertaTTLBHCOR);
			REFERENCIA="00260082"+REFERENCIA.substring(0,8);
			stm.setString(1, idorden);
	        stm.setString(2, REFERENCIA);
	        stm.setString(3, claseord);
	        stm.setInt(4, 0);
			stm.setString(5, "*********");
			stm.setString(6, "***");
			stm.setString(7, "");
			stm.setString(8, BASUNPRO.substring(2));
			stm.setString(9, "");
			stm.setInt(10, 82);
			stm.setString(11, "COP");
			stm.setInt(12, 0);
			stm.setString(13, tipo);
			stm.setString(14, "");
			stm.setString(15, "");
			stm.setString(16, "");
			java.util.Calendar fecha = java.util.Calendar.getInstance();
			int mes=fecha.get(java.util.Calendar.MONTH);
			mes=mes+1;
			String mr=Integer.toString(mes);
			if(mr.length()<2)
			{
				mr="0"+mr;
			}
			int day=fecha.get(java.util.Calendar.DATE);
			String dia=Integer.toString(day);
			if(dia.length()<2)
			{
				dia="0"+dia;
			}
			String f=fecha.get(java.util.Calendar.YEAR) + "-"  + mr    + "-"  +dia;
			String f1=fecha.get(java.util.Calendar.YEAR)   + mr    +dia;
			stm.setString(17, f);
			stm.setString(18, (String)getValueAt("datosAPP.iv-cod_usu"));
			stm.setString(19, "");
			stm.setString(20, "");
			stm.setString(21, "");
			stm.execute();
			
			stm = conn.prepareStatement(insertaTTLBHCAB);
	        stm.setString(1, REFERENCIA);
	        stm.setString(2, claseord);
	        stm.setString(3, idorden);
	        stm.setString(4, "*********");
			stm.setString(5, "***");
			stm.setString(6, "COP");
			stm.setString(7, f1);
			stm.setString(8, "");
			stm.setString(9, descripcion);
			stm.setString(10, "");
			stm.setString(11, "O");
			stm.setString(12, "");
			stm.setString(13, "");
			stm.setString(14, "");
			stm.setDouble(15, BIMPORTE);
			stm.setInt(16, 0);
			stm.setString(17, "1");
			stm.setString(18, "024");
			stm.setDouble(19, 0.0);
			stm.setDouble(20, 0);
			stm.setString(21, "");
			stm.setDouble(22, 0.0);
			stm.setString(23, "");
			stm.setString(24, "");
			stm.setString(25, f);
			stm.setString(26, (String)getValueAt("datosAPP.iv-cod_usu"));
			stm.setString(27, "");
			stm.setString(28, "");
			stm.setString(29, "");
			stm.setString(30, "");
			stm.setString(31, "");
			stm.setString(32, "");
			stm.setString(33, "");
			stm.setString(34, "");
			stm.setString(35, "");
			stm.setString(36, "");
			stm.setString(37, "");
			stm.setDouble(38, 0.0);
			stm.setDouble(39, 0.0);
			//stm.setString(40,CURRENT TIMESTAMP);
			stm.execute();
			
			
			
			//ConsultaBD.setRecursoDefecto("BDColombia");
		} catch (SQLException aSQLExc) {
			Trace.trace(Trace.Error, "", aSQLExc.toString());
			// (ARQ0300527) Error producido al ejecutar la sentencia de inserci�n..			
			//throw new BbvaARQException("ARQ0300527", BbvaARQException.DB2, aSQLExc.getSQLState(), aSQLExc);
			//ConsultaBD.setRecursoDefecto("BDColombia");
			return false;
	            
		} catch (Exception aExc) {
			Trace.trace(Trace.Error, "", aExc.toString());
			// (ARQ0300527) Error producido al ejecutar la sentencia de inserci�n..
	    	aExc.printStackTrace();
			//throw new BbvaARQException("ARQ0300527", BbvaARQException.DB2, aExc.toString(), aExc);
	    	//ConsultaBD.setRecursoDefecto("BDColombia");
	    	return false;
		}
		

		finally {
			//--- Close Statement.
			try {
				if (stm != null)
					stm.close();
			} catch (SQLException aSQLExcII) {
				// (ARQ0300700) ConsultaBD: Error al cerrar el Statement.
				//throw new BbvaARQException( "ARQ0300700", BbvaARQException.DB2, aSQLExcII.getSQLState(), aSQLExcII);
				//ConsultaBD.setRecursoDefecto("BDColombia");
				return false;
			}

			//--- Close Connection.			
			try {
				if (conn != null)
					if (!conn.isClosed())
						conn.close();
			} catch (SQLException aSQLExcIII) {
				// (ARQ0300710) ConsultaBD: Error al cerrar la conexi�n con la BD.
				//throw new BbvaARQException( "ARQ0300710", BbvaARQException.DB2, aSQLExcIII.getSQLState(), aSQLExcIII);
				//ConsultaBD.setRecursoDefecto("BDColombia");
				return false;
			}
		}
		
		return correcto;
	
	}
	public String aleatorio()
	{
		
		String aleatorio="N";
		Trace.trace(Trace.VTF, "OpGestion1.aleatorio() - Generando el IDORDEN ");
		Random rnd = new Random();
		for (int i = 0; i < 7; i++) {
	        aleatorio=aleatorio+ (int)(rnd.nextDouble() * 10.0);
	    }
		try{
			//INI INC idOrden duplicado 23-05-2019
			aleatorio = validaID(aleatorio);
			// FIN INC idOrden duplicado 
		}catch(Exception e){
			Trace.trace(Trace.VTF, "OpGestion1.aleatorio() - Error validando el IDORDEN " + e.toString());
		}
		return aleatorio;
	}
	
	// INI INC idOrden duplicado CMC idOrden 23-05-2019
	private String  validaID(String aleatorio) throws BbvaARQException {
	// FIN INC idOrden duplicado CMC idOrden
		
		Trace.trace(Trace.VTF, "OpGestion1.validaID() - estableciendo la conexion y consulta IDORDEN=" + aleatorio);		
		String resultado = aleatorio; //INC idOrden duplicado CMC idOrden 23-05-2019
		
		Connection 			conn 		= null;
		PreparedStatement	stm 		= null;
		
		String QueryIdorden = "SELECT * FROM TLNE.TTLNEMAN where TLNE_IDORDEN='" + aleatorio + "'"; 
		
		//INI INC PAGO FACTURA CMC 02-10-2018
		String QueryIdorden2 = "SELECT COD_IDORDEN FROM TLCL.TTLBHCAB where COD_IDORDEN='" + aleatorio + "'"; 
		//FIN INC PAGO FACTURA CMC 02-10-2018
		conn = ConsultaBD.getConexionBBVNet();
		//conn = ConsultaBD.getConexion("BDMexico");
		
		try{
			stm = conn.prepareStatement(QueryIdorden);
			stm.executeQuery();
			
			rsId = stm.executeQuery();
			 
			if(rsId.next()){
				String idOrd=((String)rsId.getString("TLNE_IDORDEN"));
				Trace.trace(Trace.VTF, "OpGestion1.validaID() - ID duplicado = " + idOrd);
				rsId.close();
				resultado = aleatorio(); //INC idOrden duplicado CMC idOrden 23-05-2019
				
			}			
			//INI INC PAGO FACTURA CMC 02-10-2018
			conn = ConsultaBD.getConexion("BDMexico");
			stm = conn.prepareStatement(QueryIdorden2);
			stm.executeQuery();
			rsId = stm.executeQuery();
			
			if(rsId.next()){
				String idOrd=((String)rsId.getString("COD_IDORDEN"));
				Trace.trace(Trace.VTF, "OpGestion1.validaID() - ID duplicado CAB = " + idOrd);
				rsId.close();
				resultado = aleatorio(); //INC idOrden duplicado CMC idOrden 23-05-2019			
			}			
			//FIN INC PAGO FACTURA CMC 02-10-2018					
			else {
				Trace.trace(Trace.VTF, "OpGestion1.validaID() - ID correcto");
				rsId.close();
				stm.close();
			}		
			
			
			
		}catch(Exception e){
			Trace.trace(Trace.VTF, "OpGestion1.validaID() - Error en ejecucion QueryIdorden " + e.toString());
			resultado = aleatorio(); //INC idOrden duplicado CMC idOrden 23-05-2019
		}
		return resultado; //INC idOrden duplicado CMC idOrden 23-05-2019
	}
	
	public void comprobarPass(int i) throws Exception {
	
	String passwordOpe = "", alias = "";
	
	int res;
	try {
		// Se fijan los valores de algunas de las variables de la llamada al Policy

		fijarValores();
		// Se recoge el valor introducido por pantalla de Clave de Operaciones
		passwordOpe = (String) getValueAt("ClaveOperacion");
		// Se recoge el alias que es el numero de identificacion del cliente (num. tarj.)
		alias = (String) getValueAt("s_IdCliente");
		// Se realiza la llamada a la OMIdentificacionOperativa
		setNombreOM("identificacion_operativa_om");
		setValueAt(getNombreOM() + "-data.Alias", alias);
		setValueAt(getNombreOM() + "-data.PasswordOper", passwordOpe);
		setValueAt(getNombreOM() + "-data.Country", getValueAt("Country"));
		setValueAt(getNombreOM() + "-data.Bank", getValueAt("Bank"));
		setValueAt(getNombreOM() + "-data.MaxErrors", getValueAt("MaxErrors"));
		execute();
		res = ((Integer) getValueAt("Resultado")).intValue();
		if(res!=0) //se verifica que el resultado sea diferente a 0
		{
			
			
			String codigo="ARQ05"; //se cosntruye el codigo del error
			int resul1=res*-10;
			if(resul1<100)
			{
				codigo=codigo+"000"+resul1;
			}
			else
			{
				codigo=codigo+"00"+resul1;
			}
			BbvaARQException Arq=construirARQE(codigo,"");
			
			if(i!=1)
			{
									
				return;
			}
			else
			{
				throw Arq;
			}
		
		}
		
		
	} catch (Exception e) {
		
		BbvaARQException Barq=ManejarExcepcion(1,"","","",e,"",this,"","");
		throw Barq;
	}
		
}
	public void comprobarPass(String h) throws Exception {
	
	String passwordOpe = "", alias = "";
	
	int res;
	try {
		// Se fijan los valores de algunas de las variables de la llamada al Policy
		int flag =0;
		fijarValores();
		// Se recoge el valor introducido por pantalla de Clave de Operaciones
		passwordOpe = (String) getValueAt("inpStrClaveOperacion");
		// Se recoge el alias que es el numero de identificacion del cliente (num. tarj.)
		alias = (String) getValueAt("s_IdCliente");
		// Se realiza la llamada a la OMIdentificacionOperativa
		om=creaOM("identificacion_operativa_om");
		om.setValueAt(getNombreOM() + "-data.Alias", alias);
		om.setValueAt(getNombreOM() + "-data.PasswordOper", passwordOpe);
		om.setValueAt(getNombreOM() + "-data.Country", getValueAt("Country"));
		om.setValueAt(getNombreOM() + "-data.Bank", getValueAt("Bank"));
		om.setValueAt(getNombreOM() + "-data.MaxErrors", getValueAt("MaxErrors"));
		//NGE  JCAG INICIO
		try{
			ejecutarOM(om);
		}catch(Exception e)
		{
			BbvaARQException Barq=ManejarExcepcion(3,"","","",e,"",this,"","");
			return;
		
		}
		//NGE  JCAG FIN

			} catch (Exception e) {
								
		BbvaARQException Barq=ManejarExcepcion(1,"","","",e,"",this,"","");
		throw Barq;
	}
		
}
/**
 * Insert the method's description here.
 * Creation date: (14/09/2005 02:04:32 p.m.)
 * @return com.grupobbva.ii.sf.base.BbvaARQException
 */
public BbvaARQException construirARQE(String codigo, String subtipo) {
	String tipo=null;
	if(subtipo.equals(""))
	{
		subtipo=codigo.substring(6,9);
	}
	tipo=codigo.substring(3,5);
	BbvaARQException BArq = new BbvaARQException(codigo,tipo,subtipo);
	return BArq;
}
/**
 * Insert the method's description here.
 * Creation date: (14/09/2005 02:04:32 p.m.)
 * @return com.grupobbva.ii.sf.base.BbvaARQException
 */
public BbvaARQException construirARQE(String codigo,String subtipo, Exception e) {
		String tipo=null;
	if (subtipo.equals(""))
	{
		subtipo=codigo.substring(6,9);
	}
	tipo=codigo.substring(3,5);
	
	BbvaARQException BArq = new BbvaARQException(codigo,tipo,subtipo,e);
	return BArq;
}
	/**
 * Insert the method's description here.
 * Creation date: (14/09/2005 02:08:09 p.m.)
 * @return com.grupobbva.ii.sf.base.BbvaNGException
 * @param param java.lang.String
 */
public BbvaNGException construirNGE(String codigo,String subtipo,Exception e) {
	String tipo=null;
	if( subtipo.equals(""))
	{
		subtipo=codigo.substring(6,9);
	}
	tipo=codigo.substring(3,5);
	
	
	BbvaNGException BNge = new BbvaNGException(tipo,subtipo,codigo,e);
	return BNge;
}
	public void fijarValores() throws Exception {

	try {
		setValueAt("Country", "CO");
		setValueAt("Bank", "ou=bbvacolombia,o=igrupobbva");
		setValueAt("MaxErrors", "3");
	} catch (Exception e) {
		
		if((e instanceof BbvaNGException))
			{
					throw e;
			}
			BbvaARQException Barq=ManejarExcepcion(1,"","","",e,"",this,"","");
			throw Barq;
	}
	
}
/**
 * Insert the method's description here.
 * Creation date: (12/09/2005 03:25:25 p.m.)
 */
    public BbvaARQException ManejarExcepcion(
	    int tipo,
							String msg,
							String titulo,
							String tipoError,
							Exception excepcion,
							String mensajeProgramador,
							BbvaOperacion origen,
							String codigo,String subtipo
							
							
	) throws Exception{
		
	 int g=0;
	/***************************************************************************************
	 * Glokal 09-03-2012 - INICIO 1
	 ***************************************************************************************/
 	 boolean traducir = false;
	 String idiomaUsu = "";
	 try{		 
		 idiomaUsu = (String)getValueAt("s_idioma");
		 if(!idiomaUsu.equalsIgnoreCase("es")){
			 traducir = true;
		}
	 }
	 catch (Exception e) {
		 traducir = false;
	 }
	 /**************************************************************************************
	 * Glokal 09-03-2012 - FIN
	 ***************************************************************************************/

	if(tipo==1)
	{
		
		if(!(excepcion instanceof BbvaARQException))
		{
			BbvaARQException BARQ = BbvaARQException.tipificarExcepcion(codigo,excepcion);
			BARQ.setOrigen(origen);
			BARQ.setDescriptionMsg(mensajeProgramador);
			/***************************************************************************************
			* Glokal 09-03-2012 - INICIO 2
			***************************************************************************************/
			if((traducir)&&(BARQ!=null)){
				String msg1="";
				if((BARQ.getCodigo()!=null)&&(!BARQ.getCodigo().equalsIgnoreCase(""))){
					msg1 = CatalogoMultidioma.obtenerLiteral("PNET_ERROR", idiomaUsu, BARQ.getCodigo());
				}
				else{
					msg1 = CatalogoMultidioma.obtenerLiteral("PNET_ERROR", idiomaUsu, BARQ.getCodeError());
				}				
				
				if((msg1!=null)&&(!msg1.equalsIgnoreCase(""))){
					msg = msg1;
				}
				else{
					msg = "SERVICE TEMPORARILY UNAVAILABLE";
				}
			}
			
			//Entelgy Entelgy Modificaci�n vulnerabilidad GP-11801
			String cod=(String)om.getValueAt("OMComun.CodigoError");
			msg=cod+"  "+(String)om.getValueAt("OMComun.DescripcionError");
			if(BARQ.codigo.indexOf("BGE") != -1 &&(BARQ.codigo.equalsIgnoreCase("BGE0026") || BARQ.codigo.equalsIgnoreCase("BGE0002") || BARQ.codigo.equalsIgnoreCase("BGE00178"))){
			    Trace.trace(32, "<__________________________________Mensaje de error tipo 1 codigo"+BARQ.codigo);
			    msg = "Servicio temporalmente no disponible";
			}
			if (cod.indexOf("BGE") != -1
					&& (cod.equalsIgnoreCase("BGE0026")
							|| (cod.equalsIgnoreCase("BGE0002")) || (cod
							.equalsIgnoreCase("BGE0178")))) {

			    msg = "Servicio temporalmente no disponible";
			}
			//Entelgy Fin Entelgy Modificaci�n vulnerabilidad GP-11801
			
			/**************************************************************************************
			* Glokal 09-03-2012 - FIN
			***************************************************************************************/
			if(!msg.equals(""))
			{
				BARQ.setMsg(msg);
			}
			return BARQ	;
		}
		return (BbvaARQException)excepcion;
	
	}
	BbvaARQException excepcion1;
	if(!(excepcion instanceof BbvaARQException))
	{
		excepcion1=BbvaARQException.tipificarExcepcion(codigo,excepcion);
		
	}
	else
	{
		excepcion1=(BbvaARQException)excepcion;
	}
	
	/***************************************************************************************
	* Glokal 09-03-2012 - INICIO 3
	***************************************************************************************/
	if((traducir)&&(excepcion1!=null)){
		String msg1="";
		if((excepcion1.getCodigo()!=null)&&(!excepcion1.getCodigo().equalsIgnoreCase(""))){
			msg1 = CatalogoMultidioma.obtenerLiteral("PNET_ERROR", idiomaUsu, excepcion1.getCodigo());
		}
		else{
			msg1 = CatalogoMultidioma.obtenerLiteral("PNET_ERROR", idiomaUsu, excepcion1.getCodeError());
		}
		if((msg1!=null)&&(!msg1.equalsIgnoreCase(""))){
			msg = msg1;
		}else{	
			msg = "SERVICE TEMPORARILY UNAVAILABLE";
		}
	}
	/**************************************************************************************
	 * Glokal 09-03-2012 - FIN
	 ***************************************************************************************/		

	if(tipo==2){
		if (excepcion1.getCodigo().equals("0204")) {
				/***************************************************************
				 * Glokal 09-03-2012 - INICIO 4
				 **************************************************************/
				if (traducir) {
					msg = "Please check your extract and verify if your operation was performed  correctly.";					
				} else {
					msg = "Por Favor Revise su extracto y verifique si su operacion fue realizada Correctamente.";
				}
				/***************************************************************
				 * Glokal 09-03-2012 - FIN
				 **************************************************************/
		}
		if(excepcion1.getCodigo().equals("0205"))
		{msg=(String)om.getValueAt("OMComun.CodigoError")+"  "+(String)om.getValueAt("OMComun.DescripcionError");g=1;}
		gestionarExcepcion(excepcion1.getCodigo(),"s","",false,msg,titulo,tipoError,excepcion1,mensajeProgramador,this,"");

	}
	if(tipo==5)
	{
		Trace.trace(Trace.VTF, "","Error enviado de host" + excepcion1.getCodigo());
		Trace.trace(Trace.VTF, "","Error enviado de host" + excepcion1.getCodeError());
		Trace.trace(Trace.VTF, "","Error enviado de host" + excepcion1.getDescriptionMsg());
		Trace.trace(Trace.VTF, "","Error enviado de host" + excepcion1.getDescriptionMsgBD());
		Trace.trace(Trace.VTF, "","Error enviado de host" + excepcion1.getMsg());
		Trace.trace(Trace.VTF, "","Error enviado de host" + excepcion1.getCodigo());
		/*if(!titulo.equals(""))
		{
			setValueAt("forward","error_apl1");
			
		}
		else
		{
			setValueAt("forward","pruebaerror");
		}*/
		if(excepcion1.getCodigo().equals("0204"))
		{
			if(excepcion1.getCodeError()!=null)
			{
				if(!excepcion1.getCodeError().equals("ARQ0200550"))
				{
					/***************************************************************
					 * Glokal 09-03-2012 - INICIO 5
					 **************************************************************/
					if (traducir) {
						msg = "Please check your extract and verify if your operation was performed  correctly.";						
					} else {
						msg = "Por Favor Revise su extracto y verifique si su operacion fue realizada Correctamente.";
					}
					/***************************************************************
					 * Glokal 09-03-2012 - FIN
					 **************************************************************/
					
				}
			}else
			{
				/***************************************************************
				 * Glokal 09-03-2012 - INICIO 6
				 **************************************************************/
				if (traducir) {
					msg = "Please check your extract and verify if your operation was performed  correctly.";
				} else {
					msg = "Por Favor Revise su extracto y verifique si su operacion fue realizada Correctamente.";
				}
				/***************************************************************
				 * Glokal 09-03-2012 - FIN
				 **************************************************************/
					
			}
				
				
		}
		if(excepcion1.getCodigo().equals("0205"))
		{
			String cod=(String)om.getValueAt("OMComun.CodigoError");
			/***************************************************************************************
			* Glokal 09-03-2012 - INICIO 7
			***************************************************************************************/
			if(traducir){
				String msg1 = CatalogoMultidioma.obtenerLiteral("PNET_ERROR",idiomaUsu,cod);
				Trace.trace(Trace.Information, "7 Metodo:ManejarExcepcion  Clase:OPGestion1 msg1(\""+cod+"\"): "+msg1);
				if((msg1!=null)&&(!msg1.equalsIgnoreCase(""))){
					msg = cod+"  "+msg1;
				}
				else{
					msg=cod+" SYSTEM ERROR";
				}
			}
			else{
				msg=cod+"  "+(String)om.getValueAt("OMComun.DescripcionError");
			}
			Trace.trace(Trace.Information, "7-2 Metodo:ManejarExcepcion  Clase:OPGestion1 msg(\""+cod+"\"): "+msg);
			/*
			if(msg.contains("PDN")){
				msg = "NO EXISTEN PINES DISPONIBLES PARA GENERAR EL RECAUDO, POR FAVOR COMUNIQUESE CON LA LINEA BBVA. ";
			}else if(msg.contains("PABDR")){
				msg = "AGOTADO PIN PARA LA BASE DE DATOS DEL RECAUDO, POR FAVOR COMUNIQUESE CON LA LINEA BBVA.";
			}*/
			/**************************************************************************************
			 * Glokal 09-03-2012 - FIN
			 ***************************************************************************************/			
			
			if((cod.indexOf("CNE9")==-1)&&(cod.indexOf("S00")==-1)&&(cod.indexOf("MPE")==-1)&&(cod.indexOf("UG")==-1))
			{
				
				g=1;
			}
			if((msg.indexOf("SQL")!=-1))
			{

				/***************************************************************************************
				* Glokal 09-03-2012 - INICIO 8
				***************************************************************************************/
				if(traducir){
					msg = "SYSTEM ERROR." + cod;					
				}
				else{
					msg = "Error en el sistema." + cod;
				}
				/**************************************************************************************
				 * Glokal 09-03-2012 - FIN
				 ***************************************************************************************/
			}
			
		}
		if(excepcion1.getCodeError().equals("ARQ0500080"))
		{
			/***************************************************************************************
			* Glokal 09-03-2012 - INICIO 9
			***************************************************************************************/
			if(traducir){
				msg="Data entered are not  correct "+msg;
			}
			else{
				msg="Los Datos Introducidos no son correctos "+msg;
			}
			/**************************************************************************************
			 * Glokal 09-03-2012 - FIN
			 ***************************************************************************************/
			
			g=1;
		}
		
		setEstado("ERROR");			
		setValueAt("forward","gn001_error");
		if(g==0)
		{
			if(msg!=null)
			{
				if(!msg.equals(""))
				{
					excepcion1.setMsg(msg);
				}
			}
		
			throw excepcion1;

		}
		setValueAt("Error",msg);
		//setValueAt("mensaje",msg);
		//setValueAt("ApliError","si");
		
		
	}
	if(tipo==3)
	{
		
		if(excepcion1.getCodigo().equals("0204"))
		{
			if(excepcion1.getCodeError()!=null)
			{
				if(!excepcion1.getCodeError().equals("ARQ0200550"))
				{
					/***************************************************************************************
					* Glokal 09-03-2012 - INICIO 10
					***************************************************************************************/
					if(traducir){
						msg = "Please check your extract and verify if your operation was performed  correctly.";
					}
					else{
						msg = "Por Favor Revise su extracto y verifique si su operacion fue realizada Correctamente.";
					}
					/**************************************************************************************
					* Glokal 09-03-2012 - FIN
					***************************************************************************************/
					
				}
			}
			else
			{
				/***************************************************************************************
				* Glokal 09-03-2012 - INICIO 11
				***************************************************************************************/
				if(traducir){
					msg = "Please check your extract and verify if your operation was performed  correctly.";
				}
				else{
					msg = "Por Favor Revise su extracto y verifique si su operacion fue realizada Correctamente.";
				}
				/**************************************************************************************
				* Glokal 09-03-2012 - FIN
				***************************************************************************************/
					
			}
			
				
				
		}
		
		if(excepcion1.getCodigo().equals("0205"))
		{
			String cod=(String)om.getValueAt("OMComun.CodigoError");
			msg=cod+"  "+(String)om.getValueAt("OMComun.DescripcionError");
			
			if((cod.indexOf("CNE9")==-1)&&(cod.indexOf("S00")==-1)&&(cod.indexOf("MPE")==-1)&&(cod.indexOf("BGE")==-1)&&(cod.indexOf("UG")==-1)&&(cod.indexOf("TPE")==-1))
			{
				
				g=1;
			}
			//Entelgy Entelgy Modificaci�n vulnerabilidad GP-11801
			if (cod.indexOf("BGE") != -1
					&& (cod.equalsIgnoreCase("BGE0026")
							|| (cod.equalsIgnoreCase("BGE0002")) || (cod
							.equalsIgnoreCase("BGE0178")))) {

				msg = "Servicio temporalmente no disponible";
			}
			//Entelgy Fin Entelgy Modificaci�n vulnerabilidad GP-11801
			
			if((msg.indexOf("SQL")!=-1))
			{

				/***************************************************************************************
				* Glokal 09-03-2012 - INICIO 12
				***************************************************************************************/
				if(traducir){
					msg = "SYSTEM ERROR. " + cod;
				}
				else{
					msg = "Error en el sistema." + cod;
				}
				/**************************************************************************************
				 * Glokal 09-03-2012 - FIN
				 ***************************************************************************************/ 
			}
			
		}
		
		if(msg!=null)
		{
			if(!msg.equals(""))
			{
				excepcion1.setMsg(msg);
			}
		}
		setEstado("ERROR");
		excepcion1.setParametroTitulo("ERROR");
		excepcion1.setDescriptionMsg(mensajeProgramador);
		if(mensajeProgramador.equals("1"))
		{
			g=1;
		}

		
		if(g==0)
		{
			
			throw excepcion1;

		}
		setValueAt("Error",msg);

		
		
	}
	if(tipo==4)
	{
		//excepcion1.setMsg(msg);
		if((!this.getEstado().equals("TRANSGRESION"))&&(!this.getEstado().equals("MENSAJE")))
		{
			setEstado("ERROR");
		}
		
		//setEstado("ERROR");
		excepcion1.setParametroTitulo("ERROR");
		excepcion1.setDescriptionMsg(mensajeProgramador);
		
		if(msg!=null)
		{
			if(!msg.equals(""))
			{
				excepcion1.setMsg(msg);
			}
		}
	}
	excepcion1.setOrigen(origen);
	
	return  excepcion1;
	
}
/**
 * Metodo para guardar con firmas mancomunadas y Auditoria
 * 
 * 
 * 
 * 
 * 
 * */
public boolean  mancomunadasAud(String BCODACCC, String BCODCTAA, String BPALACCE, String BPALACC2,
		String BASUNPRO, String BASUNASO, String BFECHA01, String BFECHA02, double BIMPORTE,
		double BIMPOAUX, String BINDPAGI, String BINDAUX1, String BINDAUX2, String BCODAUX1,
		String BCODAUX2, double BNUMAUX1, double BNUMAUX2, String BPAGINAC, String nomom,String descripcion	,String claseord
) throws Exception {
	String insertaoper =
		"INSERT INTO TLNE.TTLNEMAN (TLNE_IDORDEN, TLNE_REFERENCIA, TLNE_BCODACCC,TLNE_BCODCTAA,TLNE_BPALACCE,TLNE_BPALACC2,TLNE_BASUNPRO,TLNE_BASUNASO,TLNE_BFECHA01,TLNE_BFECHA02,TLNE_BIMPORTE,TLNE_BIMPOAUX,TLNE_BINDPAGI,TLNE_BINDAUX1,TLNE_BINDAUX2,TLNE_BCODAUX1,TLNE_BCODAUX2,TLNE_BNUMAUX1,TLNE_BNUMAUX2,TLNE_BPAGINAC,TLNE_NOMOM, TLNE_DESCRIP) VALUES(?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
	String insertaTTLBHCAB =
		"INSERT INTO TLCL.TTLBHCAB (COD_CLIECASH, COD_CLASEORD, COD_IDORDEN,COD_CDNIFTR,COD_SUFPRESE,COD_DIISOALF,FEC_PROCESCA,DES_REFICHER,DES_NOMFICH,XSN_MEDCREAC,XSN_MODCREAC,FEC_BORRCASH,FEC_ESTACASH,XSN_ACTAUTDE,QTY_TOTIMPOR,QNU_TOTREGIS,XSN_FORMPAGO,COD_ESTACASH,QNU_PESOFIR,QNU_SIZE,DES_NOMFICHE,QNU_NUMITEM,DES_PATH,DES_FICHEROH,AUD_FMODIFIC,AUD_USUARIO,COD_DETSEROR,XTI_DETSEROR,COD_PRODCART,XSN_SUCURSAL,DES_PATHXML,DES_PATHHTML,DES_FXMLHTML,XTI_VALPERT,COD_LTIPO,DES_LDESC,XSN_BORRADO,QTY_IMPFINA,QNU_NUMREME,TIM_ORDEN) VALUES(?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, current timestamp)";
	String insertaTTLBHCOR =
		"INSERT INTO TLCL.TTLBHCOR (COD_IDORDEN, COD_CLIECASH, COD_CLASEORD,COD_CABECORI,COD_CDNITR,COD_SUFPRESE,COD_IDORIGEN,DES_CUENORIG,COD_BANCINTE,COD_BANCSB,COD_DIISOALF,COD_FORMASUN,XTI_CLASEASU,COD_SWIFT,COD_BANABA,DES_FICHEROH,AUD_FMODIFIC,AUD_USUARIO,XTI_EMISOR,DES_ORDPAGO,XTI_INDMODAL) VALUES(?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	String insertaTTLBHFUA =
		"INSERT INTO TLCL.TTLBHFUA (COD_CLIECASH, COD_CLASEORD, COD_IDORDEN, COD_ACCION, COD_IDACCION, COD_USUARIO, FEC_ACCIO, COD_USUFIRMA, COD_USUPODER, COD_ESTADFIC, FEC_ACCION, AUD_FMODIFIC, AUD_USUARIO, COD_IPCLIENT) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	boolean 			correcto 	= false;	
	Connection 			conn 		= null;
	PreparedStatement	stm 		= null;
	ResultSet 			rs	 		= null;
	KeyedCollection		datos 		= null;
	try
	{
		// Obtenemos el usuario de la BBDD del contexto de sesi�n(DSEDATA)
		datos = (KeyedCollection) KeyedCollection.readObject("GlobalComun");
		//usuario = (String) datos.getValueAt("UserBD");
		
		// Se crea una conexi�n a BBDD
		Trace.trace(Trace.Debug, "TLCLOpGestionLimitesModificacion", "Traza ***************A1************************ ");
		conn = ConsultaBD.getConexionBBVNet();
		String REFERENCIA =(String)getValueAt("datosAPP.iv-cod_emp")+(String)getValueAt("datosAPP.iv-cod_usu");
		String idorden="99999999";
		idorden=aleatorio();
		Trace.trace(Trace.Debug, "TLCLOpGestionLimitesModificacion", "Traza ***************A2************************ ");
        // Se crea la sentencia SQL
        stm = conn.prepareStatement(insertaoper);
        stm.setString(1, idorden);
        stm.setString(2, REFERENCIA);
        stm.setString(3, BCODACCC);
        stm.setString(4, BCODCTAA);
		stm.setString(5, BPALACCE);
		stm.setString(6, BPALACC2);
		stm.setString(7, BASUNPRO);
		stm.setString(8, BASUNASO);
		stm.setString(9, BFECHA01);
		stm.setString(10, BFECHA02);
		stm.setDouble(11, BIMPORTE);
		stm.setDouble(12, BIMPOAUX);
		stm.setString(13, BINDPAGI);
		stm.setString(14, BINDAUX1);
		stm.setString(15, BINDAUX2);
		stm.setString(16, BCODAUX1);
		stm.setString(17, BCODAUX2);
		stm.setDouble(18, BNUMAUX1);
		stm.setDouble(19, BNUMAUX2);
		stm.setString(20, BPAGINAC);
		stm.setString(21, nomom);
		stm.setString(22, descripcion);
		Trace.trace(Trace.Debug, "TLCLOpGestionLimitesModificacion", "Traza ***************A3************************ ");
		// Se ejecuta la sentencia SQL
		stm.execute();
		Trace.trace(Trace.Debug, "TLCLOpGestionLimitesModificacion", "Traza ***************B1************************ ");
		if(nomom.equals("retorno_trasbbvafam_om")||nomom.equals("fondo_plazo_reem1_om")||nomom.equals("retorno_trasbd_om")||nomom.equals("retorno_trasfe_om"))
		{
			String tipo1="";
			try{
				 tipo1=BASUNPRO.substring(0,2);
			
			if((tipo1.equals("CC")||tipo1.equals("AH")))
			{
				if(nomom.equals("fondo_plazo_reem1_om"))
				{
					BASUNPRO=BASUNASO.substring(0,22);
					
				}
				else{
					BASUNPRO=BASUNASO;
				}
			}
			}catch(Exception e)
			{}
		}
		Trace.trace(Trace.Debug, "TLCLOpGestionLimitesModificacion", "Traza ***************B2************************ ");
		String tipo="";
		try{
			 tipo=BASUNPRO.substring(0,2);
		}catch(Exception e)
		{}
		Trace.trace(Trace.Debug, "TLCLOpGestionLimitesModificacion", "Traza ***************B3************************ ");
		if(nomom.equals("retorno_pagotarje_om")||nomom.equals("retorno_pagotarje_otros_om")||nomom.equals("retorno_pagotcter_om")||nomom.equals("retorno_avancetarje_om"))
		{
			
				BASUNPRO=BASUNASO;
			
		}
		if(nomom.equals("fondo_plazo_sucr_om"))
		{
			
				BASUNPRO="  "+BASUNPRO;
			
		}
		Trace.trace(Trace.Debug, "TLCLOpGestionLimitesModificacion", "Traza ***************B4************************ ");
		tipo="";
		try{
			 tipo=BASUNPRO.substring(0,2);
		}catch(Exception e)
		{}
		
		Trace.trace(Trace.Debug, "TLCLOpGestionLimitesModificacion", "Traza ***************C1************************ ");
		correcto = true;
		ConsultaBD ConsultaB=new ConsultaBD();
		//ConsultaB.setRecursoDefecto("BDMexico");
		//conn = ConsultaBD.getConexionBBVNet();
		conn = ConsultaBD.getConexion("BDMexico");
		stm = conn.prepareStatement(insertaTTLBHCOR);
		REFERENCIA="00260082"+REFERENCIA.substring(0,8);
		Trace.trace(Trace.Debug, "TLCLOpGestionLimitesModificacion", "Traza ***************C2************************ ");
		stm.setString(1, idorden);
        stm.setString(2, REFERENCIA);
        stm.setString(3, claseord);
        stm.setInt(4, 0);
		stm.setString(5, "*********");
		stm.setString(6, "***");
		stm.setString(7, "");
		stm.setString(8, BASUNPRO.substring(2));
		stm.setString(9, "");
		stm.setInt(10, 82);
		stm.setString(11, "COP");
		stm.setInt(12, 0);
		stm.setString(13, tipo);
		stm.setString(14, "");
		stm.setString(15, "");
		stm.setString(16, "");
		java.util.Calendar fecha = java.util.Calendar.getInstance();
		int mes=fecha.get(java.util.Calendar.MONTH);
		mes=mes+1;
		String mr=Integer.toString(mes);
		if(mr.length()<2)
		{
			mr="0"+mr;
		}
		int day=fecha.get(java.util.Calendar.DATE);
		String dia=Integer.toString(day);
		if(dia.length()<2)
		{
			dia="0"+dia;
		}
		String f=fecha.get(java.util.Calendar.YEAR) + "-"  + mr    + "-"  +dia;
		String f1=fecha.get(java.util.Calendar.YEAR)   + mr    +dia;
		stm.setString(17, f);
		stm.setString(18, (String)getValueAt("datosAPP.iv-cod_usu"));
		stm.setString(19, "");
		stm.setString(20, "");
		stm.setString(21, "");
		Trace.trace(Trace.Debug, "TLCLOpGestionLimitesModificacion", "Traza ***************C3************************ ");
		stm.execute();
		
		Trace.trace(Trace.Debug, "TLCLOpGestionLimitesModificacion", "Traza ***************D1************************ ");
		stm = conn.prepareStatement(insertaTTLBHCAB);
        stm.setString(1, REFERENCIA);
        stm.setString(2, claseord);
        stm.setString(3, idorden);
        stm.setString(4, "*********");
		stm.setString(5, "***");
		stm.setString(6, "COP");
		stm.setString(7, f1);
		stm.setString(8, "");
		stm.setString(9, descripcion);
		stm.setString(10, "");
		stm.setString(11, "O");
		stm.setString(12, "");
		stm.setString(13, "");
		stm.setString(14, "");
		stm.setDouble(15, BIMPORTE);
		stm.setInt(16, 0);
		stm.setString(17, "1");
		if(claseord.equals("PAI"))
		{
			stm.setString(18, "024");
		}else{
			stm.setString(18, "022");
		}
		stm.setDouble(19, 0.0);
		stm.setDouble(20, 0);
		stm.setString(21, "");
		stm.setDouble(22, 0.0);
		stm.setString(23, "");
		stm.setString(24, "");
		stm.setString(25, f);
		stm.setString(26, (String)getValueAt("datosAPP.iv-cod_usu"));
		stm.setString(27, "");
		stm.setString(28, "");
		stm.setString(29, "");
		stm.setString(30, "");
		stm.setString(31, "");
		stm.setString(32, "");
		stm.setString(33, "");
		stm.setString(34, "");
		stm.setString(35, "");
		stm.setString(36, "");
		stm.setString(37, "");
		stm.setDouble(38, 0.0);
		stm.setDouble(39, 0.0);
		Trace.trace(Trace.Debug, "TLCLOpGestionLimitesModificacion", "Traza ***************D2************************ ");
		//stm.setString(40,CURRENT TIMESTAMP);
		stm.execute();	
		
//		Hora		
		Date date = new Date();
        DateFormat hourFormat = new SimpleDateFormat("HHmmssSS");
        String hora = hourFormat.format(date);
        hora = hora.substring(0, hora.length()-1);
        
		
		//Modificacion TTLBHFUA 
		stm = conn.prepareStatement(insertaTTLBHFUA);
        stm.setString(1, REFERENCIA);
        stm.setString(2, claseord);
        stm.setString(3, idorden);
        stm.setInt(4, 1);
        stm.setInt(5, 0);
		stm.setString(6, (String)getValueAt("s_cod_usuarisc"));
		stm.setString(7, f1);
		stm.setString(8, (String)getValueAt("s_cod_usuarisc"));
		stm.setString(9, "");
		stm.setString(10, "024");
		stm.setString(11, hora);
		stm.setString(12, f);
		stm.setString(13, "");
		stm.setString(14, (String) getValueAt("s_IPCliente"));
		Trace.trace(Trace.Debug, "Auditoria()********", "insertaTTLBHFUA: "+insertaTTLBHFUA+"   1: "+REFERENCIA+"   2: "+claseord+"   3: "+idorden+"   4: "+"1"+"   5: "+"0"+"   6: "+(String)getValueAt("s_cod_usuarisc")+"   7: "+f1+"   8: "+(String)getValueAt("s_cod_usuarisc")+"   9: "+""+"   10: "+"024"+"   11: "+hora+"   12: "+f+"   13: "+""+"   14: "+""+(String) getValueAt("s_IPCliente"));
			stm.execute();
		//TTLBHFUA
		
		
		
		
		//ConsultaBD.setRecursoDefecto("BDColombia");
	} catch (SQLException aSQLExc) {
		Trace.trace(Trace.Error, "", aSQLExc.toString());
		// (ARQ0300527) Error producido al ejecutar la sentencia de inserci�n..			
		//throw new BbvaARQException("ARQ0300527", BbvaARQException.DB2, aSQLExc.getSQLState(), aSQLExc);
		//ConsultaBD.setRecursoDefecto("BDColombia");
		return false;
            
	} catch (Exception aExc) {
		Trace.trace(Trace.Error, "", aExc.toString());
		// (ARQ0300527) Error producido al ejecutar la sentencia de inserci�n..
    	aExc.printStackTrace();
		//throw new BbvaARQException("ARQ0300527", BbvaARQException.DB2, aExc.toString(), aExc);
    	//ConsultaBD.setRecursoDefecto("BDColombia");
    	return false;
	}
	

	finally {
		//--- Close Statement.
		try {
			if (stm != null)
				stm.close();
		} catch (SQLException aSQLExcII) {
			// (ARQ0300700) ConsultaBD: Error al cerrar el Statement.
			//throw new BbvaARQException( "ARQ0300700", BbvaARQException.DB2, aSQLExcII.getSQLState(), aSQLExcII);
			//ConsultaBD.setRecursoDefecto("BDColombia");
			return false;
		}

		//--- Close Connection.			
		try {
			if (conn != null)
				if (!conn.isClosed())
					conn.close();
		} catch (SQLException aSQLExcIII) {
			// (ARQ0300710) ConsultaBD: Error al cerrar la conexi�n con la BD.
			//throw new BbvaARQException( "ARQ0300710", BbvaARQException.DB2, aSQLExcIII.getSQLState(), aSQLExcIII);
			//ConsultaBD.setRecursoDefecto("BDColombia");
			return false;
		}
	}
	
	return correcto;

}
/**
 * Metodo para guardar con firmas mancomunadas y Auditoria
 * 
 * 
 * 
 * */

/**
 * Metodo para extraer datos de auditoria de ordenes de Limites
 */
/**
 * Metodo para guardar con firmas mancomunadas y Auditoria
 * 
 * 
 * 
 * 
 * 
 * */
public String  Aud() throws Exception {
	String insertaoper = "Select * from TLCL.TTLBHFUA where COD_CLIECASH='00260082"+(String)getValueAt("datosAPP.iv-cod_emp")+"' and COD_CLASEORD='MDL'";
	Connection 			conn 		= null;
	PreparedStatement	stm 		= null;
	ResultSet 			rs	 		= null;
	KeyedCollection		datos 		= null;
	String cad="";
	try
	{
		Trace.trace(Trace.Debug, "OpGestion1", "Traza preparando Conn with GC ");
		conn = ConsultaBD.getConexion("BDMexico");
		String REFERENCIA =(String)getValueAt("datosAPP.iv-cod_emp")+(String)getValueAt("datosAPP.iv-cod_usu");
		Trace.trace(Trace.Debug, "OpGestion1", "Prueba referencia: "+REFERENCIA);
		Trace.trace(Trace.Debug, "OpGestion1", "Traza select DB: "+insertaoper);
        stm = conn.prepareStatement(insertaoper);
		Trace.trace(Trace.Debug, "OpGestion1", "Traza execute STM ");
		rs=stm.executeQuery();
		while (rs.next()){
			Trace.trace(Trace.Debug, "OpGestion1", "Traza ***************si existen elementos************************ ");
			cad=cad+rs.getString("COD_USUARIO")+",";
		//	Trace.trace(Trace.Debug, "OpGestion1", "Usuario: "+cad);
			cad=cad+rs.getString("COD_IDORDEN")+",";
		//	Trace.trace(Trace.Debug, "OpGestion1", "Fecha: "+cad);
			cad=cad+rs.getString("FEC_ACCIO")+",";
		//	Trace.trace(Trace.Debug, "OpGestion1", "Fecha: "+cad);
			String estFichero=rs.getString("COD_ESTADFIC");
			if(estFichero.equals("022")){
				cad=cad+"Enviado Firmado;";
			}else if (estFichero.equals("025")){
				cad=cad+"Fdo. Pdte Env�o.;";    
			}else if(estFichero.equals("024")){
				cad=cad+"Pendiente de Firma;";  
			}else{
				cad=cad+"Enviado Firmado;";  
			}
		//	Trace.trace(Trace.Debug, "OpGestion1", "Fecha: "+cad);
		}
		Trace.trace(Trace.Debug, "OpGestion1", "Traza saliendo");
	} catch (SQLException aSQLExc) {
		Trace.trace(Trace.Error, "", aSQLExc.toString());
		return cad;
            
	} catch (Exception aExc) {
		Trace.trace(Trace.Error, "", aExc.toString());
    	aExc.printStackTrace();
		return cad;
	}
	finally {
		try {
			if (stm != null)
				stm.close();
		} catch (SQLException aSQLExcII) {
			return cad;
		}
		try {
			if (conn != null)
				if (!conn.isClosed())
					conn.close();
		} catch (SQLException aSQLExcIII) {
			return cad;
		}
	}
	return cad;
}
/**
 * Fin del metodo para extraer datos de auditoria de ordenes de limites
 */
public boolean  Auditoria(String BCODACCC, String BCODCTAA, String BPALACCE, String BPALACC2,
		String BASUNPRO, String BASUNASO, String BFECHA01, String BFECHA02, double BIMPORTE,
		double BIMPOAUX, String BINDPAGI, String BINDAUX1, String BINDAUX2, String BCODAUX1,
		String BCODAUX2, double BNUMAUX1, double BNUMAUX2, String BPAGINAC, String nomom,String descripcion	,String claseord
) throws Exception {
	String insertaTTLBHFUA ="INSERT INTO TLCL.TTLBHFUA (COD_CLIECASH, COD_CLASEORD, COD_IDORDEN, COD_ACCION, COD_IDACCION, COD_USUARIO, FEC_ACCIO, COD_USUFIRMA, COD_USUPODER, COD_ESTADFIC, FEC_ACCION, AUD_FMODIFIC, AUD_USUARIO, COD_IPCLIENT) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	boolean 			correcto 	= false;	
	//String				usuario		= null;
	Connection 			conn 		= null;
	PreparedStatement	stm 		= null;
	ResultSet 			rs	 		= null;
	KeyedCollection		datos 		= null;

		
	try
	{
		String REFERENCIA =(String)getValueAt("datosAPP.iv-cod_emp")+(String)getValueAt("datosAPP.iv-cod_usu");
		REFERENCIA="00260082"+REFERENCIA.substring(0,8);
		// Obtenemos el usuario de la BBDD del contexto de sesi�n(DSEDATA)
		datos = (KeyedCollection) KeyedCollection.readObject("GlobalComun");

		correcto = true;
		ConsultaBD ConsultaB=new ConsultaBD();
		//ConsultaB.setRecursoDefecto("BDMexico");
		//conn = ConsultaBD.getConexionBBVNet();
		conn = ConsultaBD.getConexion("BDMexico");
		java.util.Calendar fecha = java.util.Calendar.getInstance();
		int mes=fecha.get(java.util.Calendar.MONTH);
		mes=mes+1;
		String mr=Integer.toString(mes);
		if(mr.length()<2)
		{
			mr="0"+mr;
		}
		int day=fecha.get(java.util.Calendar.DATE);
		String dia=Integer.toString(day);
		if(dia.length()<2)
		{
			dia="0"+dia;
		}
		String f=fecha.get(java.util.Calendar.YEAR) + "-"  + mr    + "-"  +dia;
		String f1=fecha.get(java.util.Calendar.YEAR)   + mr    +dia;
		String idorden="99999999";
		idorden=aleatorio();
		
		//Hora		
		Date date = new Date();
        DateFormat hourFormat = new SimpleDateFormat("HHmmssSS");
        String hora = hourFormat.format(date);
        hora = hora.substring(0, hora.length()-1);
        
		
		//Modificacion TTLBHFUA 
		stm = conn.prepareStatement(insertaTTLBHFUA);
        stm.setString(1, REFERENCIA);
        stm.setString(2, claseord);
        stm.setString(3, idorden);
        stm.setInt(4, 1);
        stm.setInt(5, 0);
		stm.setString(6, (String)getValueAt("s_cod_usuarisc"));
		stm.setString(7, f1);
		stm.setString(8, (String)getValueAt("s_cod_usuarisc"));
		stm.setString(9, "");
		stm.setString(10, "022");
		stm.setString(11, hora);
		stm.setString(12, f);
		stm.setString(13, "");
		stm.setString(14, (String) getValueAt("s_IPCliente"));
		Trace.trace(Trace.Debug, "Auditoria()********", "insertaTTLBHFUA: "+insertaTTLBHFUA+"   1: "+REFERENCIA+"   2: "+claseord+"   3: "+idorden+"   4: "+"1"+"   5: "+"0"+"   6: "+(String)getValueAt("s_cod_usuarisc")+"   7: "+f1+"   8: "+""+"   9: "+""+"   10: "+"022"+"   11: "+""+"   12: "+f+"   13: "+""+"   14: "+""+"*******");
			stm.execute();
		//TTLBHFUA
		
		//ConsultaBD.setRecursoDefecto("BDColombia");
	} catch (SQLException aSQLExc) {
		Trace.trace(Trace.Error, "", aSQLExc.toString());
		// (ARQ0300527) Error producido al ejecutar la sentencia de inserci�n..			
		//throw new BbvaARQException("ARQ0300527", BbvaARQException.DB2, aSQLExc.getSQLState(), aSQLExc);
		//ConsultaBD.setRecursoDefecto("BDColombia");
		return false;
            
	} catch (Exception aExc) {
		Trace.trace(Trace.Error, "", aExc.toString());
		// (ARQ0300527) Error producido al ejecutar la sentencia de inserci�n..
    	aExc.printStackTrace();
		//throw new BbvaARQException("ARQ0300527", BbvaARQException.DB2, aExc.toString(), aExc);
    	//ConsultaBD.setRecursoDefecto("BDColombia");
    	return false;
	}
	

	finally {
		//--- Close Statement.
		try {
			if (stm != null)
				stm.close();
		} catch (SQLException aSQLExcII) {
			// (ARQ0300700) ConsultaBD: Error al cerrar el Statement.
			//throw new BbvaARQException( "ARQ0300700", BbvaARQException.DB2, aSQLExcII.getSQLState(), aSQLExcII);
			//ConsultaBD.setRecursoDefecto("BDColombia");
			return false;
		}

		//--- Close Connection.			
		try {
			if (conn != null)
				if (!conn.isClosed())
					conn.close();
		} catch (SQLException aSQLExcIII) {
			// (ARQ0300710) ConsultaBD: Error al cerrar la conexi�n con la BD.
			//throw new BbvaARQException( "ARQ0300710", BbvaARQException.DB2, aSQLExcIII.getSQLState(), aSQLExcIII);
			//ConsultaBD.setRecursoDefecto("BDColombia");
			return false;
		}
	}
	
	return correcto;

}

/**
 * Insert the method's description here.
 * Creation date: (08/03/2006 05:06:40 p.m.)
 * @param ordenante java.lang.String
 * @param beneficiaria java.lang.String
 * @param canal java.lang.String
 * @param funcion java.lang.String
 */
public void validar(String ordenante, String beneficiaria, String canal, String funcion, String OperMulti,String aux1) throws Exception{
		KeyedCollection kcContextoOM;
	IndexedCollection icContextoOM;

	KeyedCollection kcContextoSesion,kcSalidaCuentasBen;
	IndexedCollection icContextoSesion,icSalidaCuentasBen;
	Enumeration enContextoSesion;
	IndexedCollection icParaJSP,icDelaOM;
	KeyedCollection KcParaJSP,KcDelaOM;
	Enumeration en = null;
	int aux=1;
	
	if(canal.equals("ENET"))
	{
		try {
		om = creaOM(OperMulti);
		om.setValueAt("Entrada.BINDAUX1",aux1);
		om.setValueAt("Entrada.BASUNPRO",ordenante);
		om.setValueAt("Entrada.BCODAUX1",funcion);
		//NGE  JCAG INICIO
			try{
				ejecutarOM(om);
			}catch(Exception e)
			{
				BbvaARQException Barq=ManejarExcepcion(3,"","","",e,"",this,"","");
				return;
		
			}
		
		if(OperMulti.equals("trans_petdatos_om"))
		{
			icContextoSesion = (IndexedCollection) this.getElementAt("trans_petdatos_om-data.SalidaCtasBen.LstCtasBen");
			enContextoSesion = icContextoSesion.getEnumeration();
			
			while(enContextoSesion.hasMoreElements()){
			 	 kcContextoSesion = (KeyedCollection) enContextoSesion.nextElement();
		         String cuenta=(String)kcContextoSesion.getValueAt("NumCuenta");
 			     String clave= (String)kcContextoSesion.getValueAt("ClaveAsunto");
 			     if(clave.length()<22)
 			     {
					clave=clave.substring(7);
 			     }
 			     else
 			     {
 			     	clave=clave.substring(2,22);
 			     }
 			     if(beneficiaria.indexOf(clave)!=-1)
 			     {
					aux=0;
 			     }
			}
		  	    
 	   	    
		}
		if(OperMulti.equals("oper_interbanca_ctas_ben_om"))
		{
			icContextoSesion = (IndexedCollection) this.getElementAt(OperMulti+"-data.Salida.ListaAsuntosAsociados");
			enContextoSesion = icContextoSesion.getEnumeration();
			
			while(enContextoSesion.hasMoreElements()){
			 	 kcContextoSesion = (KeyedCollection) enContextoSesion.nextElement();
		         String cuenta=(String)kcContextoSesion.getValueAt("ASUNTOAS");
 			     String clave= (String)kcContextoSesion.getValueAt("TIPOASUN");
 			     clave="$"+cuenta;
 			     if(beneficiaria.indexOf(clave)!=-1)
 			     {
					aux=0;
 			     }
			}
		  	    
 	   	    
		}
		if(aux==1)
		{

			BbvaARQException e=construirARQE("APP1700109","");
			throw e;
		}

		}catch (Exception e) {
		Trace.trace(Trace.VTF, "", "      INTENTO DE FRAUDE     . " + e.toString());
		//NGE  JCAG INICIO
		BbvaARQException Barq=ManejarExcepcion(1,"","","",e,"",this,"","");
		 Barq=ManejarExcepcion(4,"","","",Barq,"INTENTO DE FRAUDE",this,"","");
		throw Barq;
		//NGE  JCAG FIN
	}
		


		

	}
	
	
	
	
	
	
	}



///GP 8438 I Se adiciona el nuevo metodo para poder cambiar el estado 022 (Firmado enviado) para auditoria 
public boolean  enviadoAuditorias(String BCODACCC, String BCODCTAA, String BPALACCE, String BPALACC2,
		String BASUNPRO, String BASUNASO, String BFECHA01, String BFECHA02, double BIMPORTE,
		double BIMPOAUX, String BINDPAGI, String BINDAUX1, String BINDAUX2, String BCODAUX1,
		String BCODAUX2, double BNUMAUX1, double BNUMAUX2, String BPAGINAC, String nomom,String descripcion	,String claseord
) throws Exception {
	String insertaoper =
		"INSERT INTO TLNE.TTLNEMAN (TLNE_IDORDEN, TLNE_REFERENCIA, TLNE_BCODACCC,TLNE_BCODCTAA,TLNE_BPALACCE,TLNE_BPALACC2,TLNE_BASUNPRO,TLNE_BASUNASO,TLNE_BFECHA01,TLNE_BFECHA02,TLNE_BIMPORTE,TLNE_BIMPOAUX,TLNE_BINDPAGI,TLNE_BINDAUX1,TLNE_BINDAUX2,TLNE_BCODAUX1,TLNE_BCODAUX2,TLNE_BNUMAUX1,TLNE_BNUMAUX2,TLNE_BPAGINAC,TLNE_NOMOM, TLNE_DESCRIP) VALUES(?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
	String insertaTTLBHCAB =
		"INSERT INTO TLCL.TTLBHCAB (COD_CLIECASH, COD_CLASEORD, COD_IDORDEN,COD_CDNIFTR,COD_SUFPRESE,COD_DIISOALF,FEC_PROCESCA,DES_REFICHER,DES_NOMFICH,XSN_MEDCREAC,XSN_MODCREAC,FEC_BORRCASH,FEC_ESTACASH,XSN_ACTAUTDE,QTY_TOTIMPOR,QNU_TOTREGIS,XSN_FORMPAGO,COD_ESTACASH,QNU_PESOFIR,QNU_SIZE,DES_NOMFICHE,QNU_NUMITEM,DES_PATH,DES_FICHEROH,AUD_FMODIFIC,AUD_USUARIO,COD_DETSEROR,XTI_DETSEROR,COD_PRODCART,XSN_SUCURSAL,DES_PATHXML,DES_PATHHTML,DES_FXMLHTML,XTI_VALPERT,COD_LTIPO,DES_LDESC,XSN_BORRADO,QTY_IMPFINA,QNU_NUMREME,TIM_ORDEN) VALUES(?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, current timestamp)";
	String insertaTTLBHCOR =
		"INSERT INTO TLCL.TTLBHCOR (COD_IDORDEN, COD_CLIECASH, COD_CLASEORD,COD_CABECORI,COD_CDNITR,COD_SUFPRESE,COD_IDORIGEN,DES_CUENORIG,COD_BANCINTE,COD_BANCSB,COD_DIISOALF,COD_FORMASUN,XTI_CLASEASU,COD_SWIFT,COD_BANABA,DES_FICHEROH,AUD_FMODIFIC,AUD_USUARIO,XTI_EMISOR,DES_ORDPAGO,XTI_INDMODAL) VALUES(?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	String insertaTTLBHFUA =
		"INSERT INTO TLCL.TTLBHFUA (COD_CLIECASH, COD_CLASEORD, COD_IDORDEN, COD_ACCION, COD_IDACCION, COD_USUARIO, FEC_ACCIO, COD_USUFIRMA, COD_USUPODER, COD_ESTADFIC, FEC_ACCION, AUD_FMODIFIC, AUD_USUARIO, COD_IPCLIENT) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	boolean 			correcto 	= false;	
	//String				usuario		= null;
	Connection 			conn 		= null;
	PreparedStatement	stm 		= null;
	ResultSet 			rs	 		= null;
	KeyedCollection		datos 		= null;

		
	try
	{
		// Obtenemos el usuario de la BBDD del contexto de sesi�n(DSEDATA)
		datos = (KeyedCollection) KeyedCollection.readObject("GlobalComun");
		//usuario = (String) datos.getValueAt("UserBD");
		
		// Se crea una conexi�n a BBDD
		Trace.trace(Trace.Debug, "TLCLOpGestionLimitesModificacion", "Traza ***************A1************************ ");
		conn = ConsultaBD.getConexionBBVNet();
		String REFERENCIA =(String)getValueAt("datosAPP.iv-cod_emp")+(String)getValueAt("datosAPP.iv-cod_usu");
		String idorden="99999999";
		idorden=aleatorio();
		Trace.trace(Trace.Debug, "TLCLOpGestionLimitesModificacion", "Traza ***************A2************************ ");
       // Se crea la sentencia SQL
       stm = conn.prepareStatement(insertaoper);
       stm.setString(1, idorden);
       stm.setString(2, REFERENCIA);
       stm.setString(3, BCODACCC);
       stm.setString(4, BCODCTAA);
		stm.setString(5, BPALACCE);
		stm.setString(6, BPALACC2);
		stm.setString(7, BASUNPRO);
		stm.setString(8, BASUNASO);
		stm.setString(9, BFECHA01);
		stm.setString(10, BFECHA02);
		stm.setDouble(11, BIMPORTE);
		stm.setDouble(12, BIMPOAUX);
		stm.setString(13, BINDPAGI);
		stm.setString(14, BINDAUX1);
		stm.setString(15, BINDAUX2);
		stm.setString(16, BCODAUX1);
		stm.setString(17, BCODAUX2);
		stm.setDouble(18, BNUMAUX1);
		stm.setDouble(19, BNUMAUX2);
		stm.setString(20, BPAGINAC);
		stm.setString(21, nomom);
		stm.setString(22, descripcion);
		Trace.trace(Trace.Debug, "TLCLOpGestionLimitesModificacion", "Traza ***************A3************************ ");
		// Se ejecuta la sentencia SQL
		stm.execute();
		Trace.trace(Trace.Debug, "TLCLOpGestionLimitesModificacion", "Traza ***************B1************************ ");
		if(nomom.equals("retorno_trasbbvafam_om")||nomom.equals("fondo_plazo_reem1_om")||nomom.equals("retorno_trasbd_om")||nomom.equals("retorno_trasfe_om"))
		{
			String tipo1="";
			try{
				 tipo1=BASUNPRO.substring(0,2);
			
			if((tipo1.equals("CC")||tipo1.equals("AH")))
			{
				if(nomom.equals("fondo_plazo_reem1_om"))
				{
					BASUNPRO=BASUNASO.substring(0,22);
					
				}
				else{
					BASUNPRO=BASUNASO;
				}
			}
			}catch(Exception e)
			{}
		}
		Trace.trace(Trace.Debug, "TLCLOpGestionLimitesModificacion", "Traza ***************B2************************ ");
		String tipo="";
		try{
			 tipo=BASUNPRO.substring(0,2);
		}catch(Exception e)
		{}
		Trace.trace(Trace.Debug, "TLCLOpGestionLimitesModificacion", "Traza ***************B3************************ ");
		if(nomom.equals("retorno_pagotarje_om")||nomom.equals("retorno_pagotarje_otros_om")||nomom.equals("retorno_pagotcter_om")||nomom.equals("retorno_avancetarje_om"))
		{
			
				BASUNPRO=BASUNASO;
			
		}
		if(nomom.equals("fondo_plazo_sucr_om"))
		{
			
				BASUNPRO="  "+BASUNPRO;
			
		}
		Trace.trace(Trace.Debug, "TLCLOpGestionLimitesModificacion", "Traza ***************B4************************ ");
		tipo="";
		try{
			 tipo=BASUNPRO.substring(0,2);
		}catch(Exception e)
		{}
		
		Trace.trace(Trace.Debug, "TLCLOpGestionLimitesModificacion", "Traza ***************C1************************ ");
		correcto = true;
		ConsultaBD ConsultaB=new ConsultaBD();
		//ConsultaB.setRecursoDefecto("BDMexico");
		//conn = ConsultaBD.getConexionBBVNet();
		conn = ConsultaBD.getConexion("BDMexico");
		stm = conn.prepareStatement(insertaTTLBHCOR);
		REFERENCIA="00260082"+REFERENCIA.substring(0,8);
		Trace.trace(Trace.Debug, "TLCLOpGestionLimitesModificacion", "Traza ***************C2************************ ");
		stm.setString(1, idorden);
       stm.setString(2, REFERENCIA);
       stm.setString(3, claseord);
       stm.setInt(4, 0);
		stm.setString(5, "*********");
		stm.setString(6, "***");
		stm.setString(7, "");
		stm.setString(8, BASUNPRO.substring(2));
		stm.setString(9, "");
		stm.setInt(10, 82);
		stm.setString(11, "COP");
		stm.setInt(12, 0);
		stm.setString(13, tipo);
		stm.setString(14, "");
		stm.setString(15, "");
		stm.setString(16, "");
		java.util.Calendar fecha = java.util.Calendar.getInstance();
		int mes=fecha.get(java.util.Calendar.MONTH);
		mes=mes+1;
		String mr=Integer.toString(mes);
		if(mr.length()<2)
		{
			mr="0"+mr;
		}
		int day=fecha.get(java.util.Calendar.DATE);
		String dia=Integer.toString(day);
		if(dia.length()<2)
		{
			dia="0"+dia;
		}
		String f=fecha.get(java.util.Calendar.YEAR) + "-"  + mr    + "-"  +dia;
		String f1=fecha.get(java.util.Calendar.YEAR)   + mr    +dia;
		stm.setString(17, f);
		stm.setString(18, (String)getValueAt("datosAPP.iv-cod_usu"));
		stm.setString(19, "");
		stm.setString(20, "");
		stm.setString(21, "");
		Trace.trace(Trace.Debug, "TLCLOpGestionLimitesModificacion", "Traza ***************C3************************ ");
		stm.execute();
		
		Trace.trace(Trace.Debug, "TLCLOpGestionLimitesModificacion", "Traza ***************D1************************ ");
		stm = conn.prepareStatement(insertaTTLBHCAB);
       stm.setString(1, REFERENCIA);
       stm.setString(2, claseord);
       stm.setString(3, idorden);
       stm.setString(4, "*********");
		stm.setString(5, "***");
		stm.setString(6, "COP");
		stm.setString(7, f1);
		stm.setString(8, "");
		stm.setString(9, descripcion);
		stm.setString(10, "");
		stm.setString(11, "O");
		stm.setString(12, "");
		stm.setString(13, "");
		stm.setString(14, "");
		stm.setDouble(15, BIMPORTE);
		stm.setInt(16, 0);
		stm.setString(17, "1");
		stm.setString(18, "022");
		stm.setDouble(19, 0.0);
		stm.setDouble(20, 0);
		stm.setString(21, "");
		stm.setDouble(22, 0.0);
		stm.setString(23, "");
		stm.setString(24, "");
		stm.setString(25, f);
		stm.setString(26, (String)getValueAt("datosAPP.iv-cod_usu"));
		stm.setString(27, "");
		stm.setString(28, "");
		stm.setString(29, "");
		stm.setString(30, "");
		stm.setString(31, "");
		stm.setString(32, "");
		stm.setString(33, "");
		stm.setString(34, "");
		stm.setString(35, "");
		stm.setString(36, "");
		stm.setString(37, "");
		stm.setDouble(38, 0.0);
		stm.setDouble(39, 0.0);
		Trace.trace(Trace.Debug, "TLCLOpGestionLimitesModificacion", "Traza ***************D2************************ ");
		//stm.setString(40,CURRENT TIMESTAMP);
		stm.execute();
		
		//Modificacion TTLBHFUA
		stm = conn.prepareStatement(insertaTTLBHFUA);
		Trace.trace(Trace.Debug, "TLCLOpGestionLimitesModificacion", "Traza ***************E1************************ ");
       stm.setString(1, REFERENCIA);
       stm.setString(2, claseord);
       stm.setString(3, idorden);
       stm.setInt(4, 1);
       stm.setInt(5, 0);
		stm.setString(6, (String)getValueAt("s_cod_usuarisc"));
		stm.setString(7, f1);
		stm.setString(8, "");
		stm.setString(9, "");
		stm.setString(10, "001");
		stm.setString(11, "");
		stm.setString(12, f);
		stm.setString(13, "");
		stm.setString(14, "");
		Trace.trace(Trace.Debug, "TLCLOpGestionLimitesModificacion", "Traza ***************E2************************ ");
		stm.execute();
		//TTLBHFUA
		
		//ConsultaBD.setRecursoDefecto("BDColombia");
	} catch (SQLException aSQLExc) {
		Trace.trace(Trace.Error, "", aSQLExc.toString());
		// (ARQ0300527) Error producido al ejecutar la sentencia de inserci�n..			
		//throw new BbvaARQException("ARQ0300527", BbvaARQException.DB2, aSQLExc.getSQLState(), aSQLExc);
		//ConsultaBD.setRecursoDefecto("BDColombia");
		return false;
           
	} catch (Exception aExc) {
		Trace.trace(Trace.Error, "", aExc.toString());
		// (ARQ0300527) Error producido al ejecutar la sentencia de inserci�n..
   	aExc.printStackTrace();
		//throw new BbvaARQException("ARQ0300527", BbvaARQException.DB2, aExc.toString(), aExc);
   	//ConsultaBD.setRecursoDefecto("BDColombia");
   	return false;
	}
	

	finally {
		//--- Close Statement.
		try {
			if (stm != null)
				stm.close();
		} catch (SQLException aSQLExcII) {
			// (ARQ0300700) ConsultaBD: Error al cerrar el Statement.
			//throw new BbvaARQException( "ARQ0300700", BbvaARQException.DB2, aSQLExcII.getSQLState(), aSQLExcII);
			//ConsultaBD.setRecursoDefecto("BDColombia");
			return false;
		}

		//--- Close Connection.			
		try {
			if (conn != null)
				if (!conn.isClosed())
					conn.close();
		} catch (SQLException aSQLExcIII) {
			// (ARQ0300710) ConsultaBD: Error al cerrar la conexi�n con la BD.
			//throw new BbvaARQException( "ARQ0300710", BbvaARQException.DB2, aSQLExcIII.getSQLState(), aSQLExcIII);
			//ConsultaBD.setRecursoDefecto("BDColombia");
			return false;
		}
	}
	
	return correcto;

}
//GP-11311 AutoGestion INICIO
/**
 * inicio metodos destinados a la ejecucion del webservice lanzado para traer el
 *  dato de la cuenta ordenante y el poder de firma paa cada usuario**/
public String getIdSession() {
    String resultado = null;
    try {
        KeyedCollection kC = (KeyedCollection) Settings.getSettings()
            .getElementAt("WEBSERVICES.CORE.CABECERAASTA");
        String refIdentificadorAsta = (String) kC.getValueAt("CONTENIDO");
        resultado = (String) getElementAt(refIdentificadorAsta).getValue();
    } catch (DSEObjectNotFoundException dseobjectnotfoundexception) {
    }
    return resultado;
}
public void CargarCuentaOrdenante() {
	OperacionMulticanal oM;
	 String strCodUsuario = "";
	try {
		strCodUsuario = getValueAt("s_cod_usuarisc").toString();
		Trace.trace(64, getClass().getName()+ " Cargando del Web Services la Cuenta Ordenante ...\n");
		Trace.trace(64, getClass().getName()+ " s_cod_logon ...  "+getValueAt("s_cod_logon"));
		Trace.trace(64, getClass().getName()+ "strCodUsuario "+strCodUsuario);
		PreejecucionServicio pr = new PreejecucionServicio();
		Trace.trace(64, getClass().getName() + "**********************COMENZANDO CREACION OPERACION MULTICANAL CUENTA ORDENANTE********************");
		oM = pr.crearOM("InformacionUsuarios_om");
		oM.setValueAt("Metodos.listarServiciosAsuntos.Ejecutar", "true");
		oM.setValueAt("Metodos.listarServiciosAsuntos.EntradaMetodo.codCanal", "0026");
	    oM.setValueAt("Metodos.listarServiciosAsuntos.EntradaMetodo.codEmpresa", getValueAt("s_cod_logon"));
	    oM.setValueAt("Metodos.listarServiciosAsuntos.EntradaMetodo.codBancoInterno", "0082");
	    oM.setValueAt("Metodos.listarServiciosAsuntos.EntradaMetodo.codUsuario", strCodUsuario);   
	    oM.setValueAt("Metodos.listarServiciosAsuntos.EntradaMetodo.codProducto","1380" );
	    oM.setValueAt("Metodos.listarServiciosAsuntos.EntradaMetodo.bancoAsunto","82" );
	    oM.setValueAt("Metodos.listarServiciosAsuntos.EntradaMetodo.codServicio","3496");
	    oM.setValueAt("Metodos.listarServiciosAsuntos.EntradaMetodo.idioma","");
	    oM.setValueAt("Metodos.listarServiciosAsuntos.EntradaMetodo.tipoAsunto","");
	    oM.setValueAt("Metodos.listarServiciosAsuntos.EntradaMetodo.asunto","");
	    oM.setValueAt("Metodos.listarServiciosAsuntos.EntradaMetodo.indOrdenanteBeneficiario","");
	    oM.setValueAt("Metodos.listarServiciosAsuntos.EntradaMetodo.codSubproducto","0"); 
	    oM.setValueAt("Metodos.listarServiciosAsuntos.EntradaMetodo.bloqueFirma","");
	    oM.setValueAt("Metodos.listarServiciosAsuntos.EntradaMetodo.indObtenerAsuntos","");
	    oM.setValueAt("Metodos.listarServiciosAsuntos.EntradaMetodo.codBancoProd","0082");
	    oM.setValueAt("Metodos.listarServiciosAsuntos.EntradaMetodo.codUsuarioAdmin","");
	    oM.setValueAt("datosAPP.iv-id_sesion_ast",getIdSession()); 
	    IndexedCollection listaServiciosLocales;
	    IndexedCollection listaServiciosGlobales;
	    KeyedCollection serviciosLocales;
	    KeyedCollection serviciosGlobales;
	    listaServiciosLocales = (IndexedCollection) oM.getElementAt("Metodos.listarServiciosAsuntos.EntradaMetodo.Lista_ServicioL");
	    listaServiciosLocales.removeAll();
	    listaServiciosGlobales = (IndexedCollection) oM.getElementAt("Metodos.listarServiciosAsuntos.EntradaMetodo.Lista_ServicioG");
	    listaServiciosGlobales.removeAll();
	    serviciosLocales = (KeyedCollection) DataElement.getExternalizer().convertTagToObject(listaServiciosLocales.getElementSubTag());
	    serviciosLocales.setValueAt("Item","");
	    serviciosGlobales = (KeyedCollection) DataElement.getExternalizer().convertTagToObject(listaServiciosGlobales.getElementSubTag());
	    serviciosGlobales.setValueAt("Item","");
	    listaServiciosLocales.addElement(serviciosLocales);
	    listaServiciosGlobales.addElement(serviciosGlobales);
	    Trace.trace(64, getClass().getName() + "**********************FINALIZANDO CREACION OPERACION MULTICANAL CUENTA ORDENANTE********************");
	    Trace.trace(64, getClass().getName() + "**********************EJECUTANDO OPERACION MULTICANAL CUENTA ORDENANTE********************");
	    oM.execute();
	    Trace.trace(64, getClass().getName()+ "Termino la Carga en el WebServices de la Cuenta Ordenante al usuario "+strCodUsuario +" \nIniciando Lectura de los datos Capturados");
	    IndexedCollection 	VectorSalidaGeneral;
	    VectorSalidaGeneral = (IndexedCollection) oM.getElementAt("Metodos.listarServiciosAsuntos.SalidaMetodo.RespuestaServicioCtx.Lista_ServicioCtx");
	   

	    KeyedCollection  kcVectorEntrada2 , kcListaAsunto, kcpoder, kcEntradaPoder;
	    IndexedCollection vectorSalidaNuevo= null;
	    String poderFirmante=""; 
	    IndexedCollection VectorSalidaInterno = null;
	    String cuentaOrdenate = "ERROR EN LECTURA DE CUENTA";
	  
	    Enumeration VectorSalidaGeneralEnumeration = VectorSalidaGeneral.getEnumeration();
	    
	   
	    while(VectorSalidaGeneralEnumeration.hasMoreElements()){
	    	kcListaAsunto = (KeyedCollection) VectorSalidaGeneralEnumeration .nextElement();
	    	VectorSalidaInterno = (IndexedCollection) kcListaAsunto.getElementAt("Lista_Asunto");
	    	poderFirmante=(String)kcListaAsunto.getValueAt("poderFirma");
	    	Enumeration VectorSalidaInternoEnumeration = VectorSalidaInterno.getEnumeration();
	    	while(VectorSalidaInternoEnumeration.hasMoreElements()){
	    		kcVectorEntrada2 = (KeyedCollection) VectorSalidaInternoEnumeration .nextElement();
	    		cuentaOrdenate = kcVectorEntrada2.getValueAt("nuAsunto").toString();
	    		
	    		Trace.trace(64, getClass().getName() + "******************************************CUENTA ORDENANTE CAPTURADA********************");
	    		Trace.trace(64, getClass().getName() + ""+cuentaOrdenate);
	    		Trace.trace(64, getClass().getName() + "******************************************CUENTA ORDENANTE CAPTURADA********************");
	    		Trace.trace(64, getClass().getName() + "******************************************CUENTA ORDENANTE CAPTURADA********************");
	    		Trace.trace(64, getClass().getName() + "11111"+poderFirmante);
	    		Trace.trace(64, getClass().getName() + "******************************************CUENTA ORDENANTE CAPTURADA********************");
	    		break;
	    	}
	    }
	    Trace.trace(Trace.All, getClass().getName() + " Poder Firmante "+poderFirmante);
	    setValueAt("s_poder_firma", poderFirmante);
	    if((poderFirmante.equals("S"))){
	    	setValueAt("s_mancomunada", "NO");
	    	  setValueAt("CuentaOrdenante",cuentaOrdenate);
	    }if(poderFirmante.equals("")){
	    	Trace.trace(Trace.All, getClass().getName() + "Se ingreso al if donde esta vacio");
			setValueAt("CuentaOrdenante","");
			setValueAt("Mensaje","El servicio de Autogesti&oacute;n Net Cash requiere contar con la configuraci&oacute;n adecuada");
	    }if(!(poderFirmante.equals("S"))){
			setValueAt("s_mancomunada", "SI");
			setValueAt("CuentaOrdenante",cuentaOrdenate);
	    }
	  
	} catch (DSEObjectNotFoundException e) {
		Trace.trace(64, getClass().getName() + " falla ejecucion--DSEObjectNotFoundException--\n"+e.getMessage());
		try {
			setValueAt("ChangeAcepted","0");
			setValueAt("Mensaje","El servicio no tiene una cuenta ordenante asociada, Por favor consulte a su Mesa de Ayuda");
		} catch (DSEObjectNotFoundException e1) {
			Trace.trace(64, getClass().getName() + " falla ejecucion--DSEObjectNotFoundException--\n"+e1.getMessage());
		} catch (DSEInvalidArgumentException e1) {
			Trace.trace(64, getClass().getName() + " falla ejecucion--DSEObjectNotFoundException--\n"+e1.getMessage());
		}
	} catch (DSEInvalidArgumentException e) {
		Trace.trace(64, getClass().getName() + " falla ejecucion--DSEInvalidArgumentException--\n"+e.getMessage());
		try {
			setValueAt("ChangeAcepted","0");
			setValueAt("Mensaje","El servicio no tiene una cuenta ordenante asociada, Por favor consulte a su Mesa de Ayuda");
		} catch (DSEObjectNotFoundException e1) {
			Trace.trace(64, getClass().getName() + " falla ejecucion--DSEObjectNotFoundException--\n"+e1.getMessage());
		} catch (DSEInvalidArgumentException e1) {
			Trace.trace(64, getClass().getName() + " falla ejecucion--DSEObjectNotFoundException--\n"+e1.getMessage());
		}
	} catch (BbvaException e) {
		Trace.trace(64, getClass().getName() + " falla ejecucion--BbvaException--\n"+e.getMessage());
		try {
			setValueAt("ChangeAcepted","0");
			setValueAt("Mensaje","El servicio no tiene una cuenta ordenante asociada, Por favor consulte a su Mesa de Ayuda");
		} catch (DSEObjectNotFoundException e1) {
			Trace.trace(64, getClass().getName() + " falla ejecucion--DSEObjectNotFoundException--\n"+e1.getMessage());
		} catch (DSEInvalidArgumentException e1) {
			Trace.trace(64, getClass().getName() + " falla ejecucion--DSEObjectNotFoundException--\n"+e1.getMessage());
		}
	 }catch(Exception e){
    	Trace.trace(64, getClass().getName() + " falla ejecucion--Exception-- "+e.getMessage());
    	try {
			setValueAt("ChangeAcepted","0");
			setValueAt("Mensaje","El servicio no tiene una cuenta ordenante asociada, Por favor consulte a su Mesa de Ayuda");
		} catch (DSEObjectNotFoundException e1) {
			Trace.trace(64, getClass().getName() + " falla ejecucion--DSEObjectNotFoundException--\n"+e1.getMessage());
		} catch (DSEInvalidArgumentException e1) {
			Trace.trace(64, getClass().getName() + " falla ejecucion--DSEObjectNotFoundException--\n"+e1.getMessage());
		}
	}
}
/********************************************************************************************************************
 * 								FIN METODO CARGAR CUENTA ORDENANTE													*
 ********************************************************************************************************************/


//GP-11311 AutoGestion FIN


	/**
	 * <h1>GP16295 Ofertas</h1> Metodo encargado de dar de alta las firmas de
	 * las ofertas, para usuarios solidario y mancomunados. Hasta la fecha el
	 * inico producto ofertado es Credito Virtual.
	 *  
	 * @author <a href="mailto:hernancamilo.rojas.contractor@bbva.com">CMC Camilo Rojas Balanta</a>
	 * @version 05/10/2018 Last modification : 07-03-2019 v0.0.8
	 * 
	 * @param cuentaOrdenante
	 * @param estadoCAB (tabla TTLBHCAB - campo TTLBHCAB.COD_ESTACASH)
	 * @param ejecutarOM 
	 * @param nroContrato
	 * @param tipoProducto
	 * @param porcentajeFirmaCAB (S = 1, M2 = 0.5, M3 = 0.34, M4 = 0.25)
	 * @param pesoFirmante (S, M2, M3, M4)
	 * @param valorOferta
	 * 
	 * @throws Exception
	 */
	public void firmarOferta(String cuentaOrdenante, String estadoCAB,
			String ejecutarOM, double nroContrato, String tipoProducto,
			double porcentajeFirmaCAB, String pesoFirmante, double valorOferta,
			String cliente, String tipoId, String numeroId)
			throws Exception {
		
		Trace.trace(64, getClass().getName() + " ******* INICIO OpGestion1 - firmarOferta()");
						
		String idOrden = "99999999";
		String inicioReferencia = "00260082";		
		Connection connection = null;		
		PreparedStatement preparedStm = null;
		Connection connectionMan = null;
		PreparedStatement preparedStmMan = null;
		boolean resultado;	
		java.util.Date fechaActual;
		java.text.SimpleDateFormat sDFFecha;
		String fechaProceso;
		int resultadoMan = 0;
		
		String queryTTLNEMAN = "INSERT INTO TLNE.TTLNEMAN (TLNE_IDORDEN, TLNE_REFERENCIA, TLNE_BCODACCC,TLNE_BCODCTAA,TLNE_BPALACCE,TLNE_BPALACC2,TLNE_BASUNPRO,TLNE_BASUNASO,TLNE_BFECHA01,TLNE_BFECHA02,TLNE_BIMPORTE,TLNE_BIMPOAUX,TLNE_BINDPAGI,TLNE_BINDAUX1,TLNE_BINDAUX2,TLNE_BCODAUX1,TLNE_BCODAUX2,TLNE_BNUMAUX1,TLNE_BNUMAUX2,TLNE_BPAGINAC,TLNE_NOMOM, TLNE_DESCRIP) VALUES(?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
		String queryTTLBHCOR = "INSERT INTO TLCL.TTLBHCOR (COD_IDORDEN, COD_CLIECASH, COD_CLASEORD,COD_CABECORI,COD_CDNITR,COD_SUFPRESE,COD_IDORIGEN,DES_CUENORIG,COD_BANCINTE,COD_BANCSB,COD_DIISOALF,COD_FORMASUN,XTI_CLASEASU,COD_SWIFT,COD_BANABA,DES_FICHEROH,AUD_FMODIFIC,AUD_USUARIO,XTI_EMISOR,DES_ORDPAGO,XTI_INDMODAL) VALUES(?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		String queryTTLBHCAB = "INSERT INTO TLCL.TTLBHCAB (COD_CLIECASH, COD_CLASEORD, COD_IDORDEN,COD_CDNIFTR,COD_SUFPRESE,COD_DIISOALF,FEC_PROCESCA,DES_REFICHER,DES_NOMFICH,XSN_MEDCREAC,XSN_MODCREAC,FEC_BORRCASH,FEC_ESTACASH,XSN_ACTAUTDE,QTY_TOTIMPOR,QNU_TOTREGIS,XSN_FORMPAGO,COD_ESTACASH,QNU_PESOFIR,QNU_SIZE,DES_NOMFICHE,QNU_NUMITEM,DES_PATH,DES_FICHEROH,AUD_FMODIFIC,AUD_USUARIO,COD_DETSEROR,XTI_DETSEROR,COD_PRODCART,XSN_SUCURSAL,DES_PATHXML,DES_PATHHTML,DES_FXMLHTML,XTI_VALPERT,COD_LTIPO,DES_LDESC,XSN_BORRADO,QTY_IMPFINA,QNU_NUMREME,TIM_ORDEN) VALUES(?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, current timestamp)";				
		String queryTTLBHFUA = "INSERT INTO TLCL.TTLBHFUA (COD_CLIECASH, COD_CLASEORD, COD_IDORDEN, COD_ACCION, COD_IDACCION, COD_USUARIO, FEC_ACCIO, COD_USUFIRMA, COD_USUPODER, COD_ESTADFIC, FEC_ACCION, AUD_FMODIFIC, AUD_USUARIO, COD_IPCLIENT) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";				
		String queryTTLCLFFU = "INSERT INTO TLCL.TTLCLFFU (COD_CLIECASH, COD_CLASEORD, COD_IDORDEN, COD_USUARIO, COD_NUMALEAT, XSN_FIRMADO, AUD_FMODIFIC, AUD_USUARIO) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
													
		try {			
			fechaActual = new java.util.Date();
			sDFFecha = new java.text.SimpleDateFormat("yyyy-MM-dd");
			fechaProceso = sDFFecha.format(fechaActual);				
			SimpleDateFormat sHora = new SimpleDateFormat ("hhmmss");
			String fechaProcesoLimpia = fechaProceso.replaceAll("-", "");
			
			idOrden = aleatorio();		
			
			Trace.trace(64, getClass().getName() + " ******* idOrden = " + idOrden); 
			Trace.trace(64, getClass().getName() + " ******* s_mancomunada = " + getValueAt("s_mancomunada"));						
			Trace.trace(64, getClass().getName() + " ******* referencia = " + (String) getValueAt("s_cod_logon"));
			Trace.trace(64, getClass().getName() + " ******* usuario = " + (String) getValueAt("s_cod_usuarisc"));					
			Trace.trace(64, getClass().getName() + " ******* fechaProcesoLimpia = " + fechaProcesoLimpia);
			Trace.trace(64, getClass().getName() + " ******* tipoProducto = " + tipoProducto);
			Trace.trace(64, getClass().getName() + " ******* ejecutarOM = " + ejecutarOM);
			Trace.trace(64, getClass().getName() + " ******* nroContrato = " + nroContrato);								
			Trace.trace(64, getClass().getName() + " ******* valorCredito = " + valorOferta);				
			Trace.trace(64, getClass().getName() + " ******* cliente = " + cliente);
			Trace.trace(64, getClass().getName() + " ******* tipoId = " + tipoId);								
			Trace.trace(64, getClass().getName() + " ******* numeroId = " + numeroId);
						
			connection = ConsultaBD.getConexion("BDMexico");
			
			// BTLCLCO1 TLCL.TTLBHCOR
			Trace.trace(64, getClass().getName() + " ******* INICIO TABLA: TLCL.TTLBHCOR");
			preparedStm = connection.prepareStatement(queryTTLBHCOR);
			preparedStm.setString(1, idOrden);
			preparedStm.setString(2, inicioReferencia + (String) getValueAt("s_cod_logon"));
			preparedStm.setString(3, OfertaConstantesEnum.CREDITO_VIRTUAL_CLASE_ORDEN.getConstante());
			preparedStm.setInt(4, 0);
			preparedStm.setString(5, "*********");
			preparedStm.setString(6, "***");
			preparedStm.setString(7, "");
			preparedStm.setString(8, cuentaOrdenante);
			preparedStm.setString(9, "");
			preparedStm.setInt(10, 82);
			preparedStm.setString(11, "COP");
			preparedStm.setInt(12, 0);
			preparedStm.setString(13, tipoProducto);
			preparedStm.setString(14, "");
			preparedStm.setString(15, "");
			preparedStm.setString(16, "");
			preparedStm.setString(17, fechaProceso);
			preparedStm.setString(18, (String) getValueAt("s_cod_usuarisc"));
			preparedStm.setString(19, "");
			preparedStm.setString(20, "");
			preparedStm.setString(21, "");			
			resultado = preparedStm.execute();
			Trace.trace(64, getClass().getName() + " ******* resultado TTLBHCOR = " + resultado);
			
			// BTLCLCO1 TLCL.TTLBHCAB
			Trace.trace(64, getClass().getName() + " ******* INICIO TABLA: TLCL.TTLBHCAB");
			preparedStm = connection.prepareStatement(queryTTLBHCAB);
			preparedStm.setString(1, inicioReferencia + (String) getValueAt("s_cod_logon"));
			preparedStm.setString(2, OfertaConstantesEnum.CREDITO_VIRTUAL_CLASE_ORDEN.getConstante());
			preparedStm.setString(3, idOrden);
			preparedStm.setString(4, "*********");
			preparedStm.setString(5, "***");
			preparedStm.setString(6, "COP");
			preparedStm.setString(7, fechaProcesoLimpia);
			preparedStm.setString(8, "");
			preparedStm.setString(9, "ALTA OFERTAS DE C.V.");
			preparedStm.setString(10, "");
			preparedStm.setString(11, "O");
			preparedStm.setString(12, "");
			preparedStm.setString(13, fechaProcesoLimpia);
			preparedStm.setString(14, "");
			preparedStm.setDouble(15, 0.0);
			preparedStm.setInt(16, 1);
			preparedStm.setString(17, "1");
			preparedStm.setString(18, estadoCAB);
			preparedStm.setDouble(19, porcentajeFirmaCAB);
			preparedStm.setDouble(20, 0);
			preparedStm.setString(21, "");
			preparedStm.setDouble(22, 0.0);
			preparedStm.setString(23, "");
			preparedStm.setString(24, "");
			preparedStm.setString(25, fechaProceso);
			preparedStm.setString(26, (String) getValueAt("s_cod_usuarisc"));
			preparedStm.setString(27, "");
			preparedStm.setString(28, "");
			preparedStm.setString(29, "");
			preparedStm.setString(30, "");
			preparedStm.setString(31, "");
			preparedStm.setString(32, "");
			preparedStm.setString(33, "");
			preparedStm.setString(34, "");
			preparedStm.setString(35, "");
			preparedStm.setString(36, "");
			preparedStm.setString(37, "");
			preparedStm.setDouble(38, 0.0);
			preparedStm.setDouble(39, 0.0);
			resultado = preparedStm.execute();
			Trace.trace(64, getClass().getName() + " ******* resultado TTLBHCAB = " + resultado);
									
			// BTLCLCO1 TLCL.TTLCLFFU
			Trace.trace(64, getClass().getName() + " ******* INICIO TABLA: TLCL.TTLCLFFU");
			preparedStm = connection.prepareStatement(queryTTLCLFFU);
			preparedStm.setString(1, inicioReferencia + (String) getValueAt("s_cod_logon"));
			preparedStm.setString(2, OfertaConstantesEnum.CREDITO_VIRTUAL_CLASE_ORDEN.getConstante());
			preparedStm.setString(3, idOrden);
			preparedStm.setString(4, (String) getValueAt("s_cod_usuarisc"));
			preparedStm.setString(5, (String) sHora.format(new Date()));
			preparedStm.setString(6, "S");
			preparedStm.setString(7, fechaProceso);
			preparedStm.setString(8, (String) getValueAt("s_cod_usuarisc"));		
			resultado = preparedStm.execute();
			Trace.trace(64, getClass().getName() + " ******* resultado TTLCLFFU = " + resultado);
														
			// BTLCLCO1 TLCL.TTLBHFUA
			Trace.trace(64, getClass().getName() + " ******* INICIO (1) TABLA: TLCL.TTLBHFUA");
			preparedStm = connection.prepareStatement(queryTTLBHFUA);
			preparedStm.setString(1, inicioReferencia + (String) getValueAt("s_cod_logon"));
			preparedStm.setString(2, OfertaConstantesEnum.CREDITO_VIRTUAL_CLASE_ORDEN.getConstante());
			preparedStm.setString(3, idOrden);
			preparedStm.setInt(4, 1);
			preparedStm.setInt(5, 0);
			preparedStm.setString(6, (String) getValueAt("s_cod_usuarisc"));
			preparedStm.setString(7, fechaProcesoLimpia);
			preparedStm.setString(8, "");
			preparedStm.setString(9, "");
			preparedStm.setString(10, "024");
			preparedStm.setString(11, (String) sHora.format(new Date()));
			preparedStm.setString(12, fechaProceso);
			preparedStm.setString(13, (String) getValueAt("s_cod_usuarisc"));
			preparedStm.setString(14, (String) getValueAt("s_IPCliente"));				
			resultado = preparedStm.execute();
			Trace.trace(64, getClass().getName() + " ******* resultado TTLBHFUA = " + resultado);	
			
			// solidario
			if (((String) getValueAt("s_mancomunada")).equals("NO")) {	

				// BTLCLCO1 TLCL.TTLBHFUA				
				Trace.trace(64, getClass().getName() + " ******* INICIO (2) TABLA: TLCL.TTLBHFUA");
				preparedStm = connection.prepareStatement(queryTTLBHFUA);
				preparedStm.setString(1, inicioReferencia + (String) getValueAt("s_cod_logon"));
				preparedStm.setString(2, OfertaConstantesEnum.CREDITO_VIRTUAL_CLASE_ORDEN.getConstante());
				preparedStm.setString(3, idOrden);
				preparedStm.setInt(4, 11);
				preparedStm.setInt(5, 0);
				preparedStm.setString(6, (String) getValueAt("s_cod_usuarisc"));
				preparedStm.setString(7, fechaProcesoLimpia);
				preparedStm.setString(8, (String) getValueAt("s_cod_usuarisc"));
				preparedStm.setString(9, pesoFirmante);
				preparedStm.setString(10, "022"); 
				preparedStm.setString(11, (String) sHora.format(new Date()));
				preparedStm.setString(12, fechaProceso);
				preparedStm.setString(13, (String) getValueAt("s_cod_usuarisc"));
				preparedStm.setString(14, (String) getValueAt("s_IPCliente"));			
				resultado = preparedStm.execute();
				Trace.trace(64, getClass().getName() + " ******* resultado (1) TTLBHFUA = " + resultado);													
			} 
			
			// mancomunado
			if (((String) getValueAt("s_mancomunada")).equals("SI")) {
				
				// BTLCLCO1 TLCL.TTLBHFUA
				Trace.trace(64, getClass().getName() + " ******* INICIO (3) TABLA: TLCL.TTLBHFUA");
				preparedStm = connection.prepareStatement(queryTTLBHFUA);
				preparedStm.setString(1, inicioReferencia + (String) getValueAt("s_cod_logon"));
				preparedStm.setString(2, OfertaConstantesEnum.CREDITO_VIRTUAL_CLASE_ORDEN.getConstante());
				preparedStm.setString(3, idOrden);
				preparedStm.setInt(4, 4);
				preparedStm.setInt(5, 1);
				preparedStm.setString(6, (String) getValueAt("s_cod_usuarisc"));
				preparedStm.setString(7, fechaProcesoLimpia);
				preparedStm.setString(8, (String) getValueAt("s_cod_usuarisc"));
				preparedStm.setString(9, pesoFirmante);
				preparedStm.setString(10, "019"); 
				preparedStm.setString(11, (String) sHora.format(new Date()));
				preparedStm.setString(12, fechaProceso);
				preparedStm.setString(13, (String) getValueAt("s_cod_usuarisc"));
				preparedStm.setString(14, (String) getValueAt("s_IPCliente"));			
				resultado = preparedStm.execute();
				Trace.trace(64, getClass().getName() + " ******* resultado TTLBHFUA = " + resultado);
			}			
			
			// BCNCO001 TLNE.TTLNEMAN			
			if (((String) getValueAt("s_mancomunada")).equals("SI")) {						
				Trace.trace(64, getClass().getName() + " ******* INICIO TABLA: TLNE.TTLNEMAN");				
												
				connectionMan = ConsultaBD.getConexionBBVNet();			
				
				preparedStmMan = connectionMan.prepareStatement(queryTTLNEMAN);																		
				preparedStmMan.setString(1, idOrden);
				preparedStmMan.setString(2, (String) getValueAt("s_cod_logon") + (String) getValueAt("s_cod_usuarisc"));
				preparedStmMan.setString(3, cliente);
				preparedStmMan.setString(4, "OFE CREDITO VIRTUAL");
				preparedStmMan.setString(5, numeroId);
				preparedStmMan.setString(6, "");
				preparedStmMan.setString(7, "");
				preparedStmMan.setString(8, "");
				preparedStmMan.setString(9, fechaProcesoLimpia);
				preparedStmMan.setString(10, "");
				preparedStmMan.setDouble(11, 0);
				preparedStmMan.setDouble(12, 0);
				preparedStmMan.setString(13, "");
				preparedStmMan.setString(14, tipoId);
				preparedStmMan.setString(15, "");
				preparedStmMan.setString(16, "0026");
				preparedStmMan.setString(17, tipoProducto);
				preparedStmMan.setDouble(18, nroContrato);
				preparedStmMan.setDouble(19, valorOferta);
				preparedStmMan.setString(20, "");
				preparedStmMan.setString(21, ejecutarOM);
				preparedStmMan.setString(22, "ALTA OFERTAS DE C.V.");								
				resultadoMan = preparedStmMan.executeUpdate();
				Trace.trace(64, getClass().getName() + " ******* resultadoMan TTLNEMAN = " + resultadoMan);	
			}
			
			Trace.trace(64, getClass().getName() + " ******* FIN OpGestion1 (1) - firmarOferta()");
			
		} catch (SQLException eSQL) {			
			Trace.trace(64, getClass().getName()
					+ " ******* ERROR OpGestion1 (1) - Metodo: firmarOferta(): " + eSQL.getMessage());
			
			throw new BbvaARQException("ARQ0300680", BbvaARQException.DB2,
					eSQL.getSQLState(), eSQL);
			
		} catch (Exception e) {
			Trace.trace(64, getClass().getName()
					+ " ******* ERROR OpGestion1 (2) - Metodo: firmarOferta(): " + e.getMessage());
			
			throw new BbvaARQException("ARQ0300690", BbvaARQException.DB2,
					e.getMessage(), e);
		}

		finally {
			Trace.trace(64, getClass().getName() + " ******* FIN OpGestion1 (2) - firmarOferta()");
			
			try {
				if (preparedStm != null) {
					preparedStm.close();
				}
				
				if (((String) getValueAt("s_mancomunada")).equals("SI")
						&& preparedStmMan != null) {
					preparedStmMan.close();
				}
			} catch (SQLException eSQLI) {
				Trace.trace(64, getClass().getName()
						+ " ******* ERROR OpGestion1 (3) - Metodo: firmarOferta(): " + eSQLI.getMessage());
				
				throw new BbvaARQException("ARQ0300700", BbvaARQException.DB2,
						eSQLI.getSQLState(), eSQLI);
			}
			
			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
				
				if (((String) getValueAt("s_mancomunada")).equals("SI")
						&& connectionMan != null && !connectionMan.isClosed()) {
					connectionMan.close();
				}						
			} catch (SQLException eSQLII) {
				Trace.trace(64, getClass().getName()
						+ " ******* ERROR OpGestion1 (4) - Metodo: firmarOferta(): " + eSQLII.getMessage());
				
				throw new BbvaARQException("ARQ0300710", BbvaARQException.DB2,
						eSQLII.getSQLState(), eSQLII);
			}
			
			Trace.trace(64, getClass().getName() + " ******* FIN OpGestion1 (3) - firmarOferta()");
		}
		
		Trace.trace(64, getClass().getName() + " ******* FIN OpGestion1 (4) - firmarOferta()");
	}

	
	/**
	 * <h1>GP16295 Ofertas</h1> Metodo de proposito general encargado de cargar
	 * la cuenta ordenante para el servicio que sea pasado por parametro al
	 * metodo, y retornar el poder de firma del usuario sobre el servicio pasado
	 * por parametro al metodo. consume el webservice InformacionUsuarios_om
	 * 
	 * @author <a href="mailto:hernancamilo.rojas.contractor@bbva.com">CMC Camilo Rojas Balanta</a>
	 * @version 06/10/2018 Last modification : 21-12-2018 v0.0.4
	 * 
	 * @param codServicio (Codigo de servicio)
	 * @return poderFirmanteServicio (Retorna el poder de firma sobre el
	 *         servicio: S, M2, M3, M4.)
	 * @throws Exception
	 * @see com.grupobbva.ii.sf.operacion.OMWebServices
	 * 
	 */
	public String cargarCTAOrdenanteYPoderFirma(String codServicio) throws Exception {
		
		Trace.trace(64, getClass().getName() + " ******* INICIO OpGestion1_rs7 - cargarCTAOrdenanteYPoderFirma()");
						
		OperacionMulticanal oMInformacionUsuarios;
		PreejecucionServicio preEjecucionServicio = new PreejecucionServicio();
		String strCodUsuario = "";
		String strEmpresa = "";
		String cuentaOrdenate = "";
		String poderFirmanteServicio = ""; 
		
		IndexedCollection listaServiciosLocales;
	    IndexedCollection listaServiciosGlobales;
	    IndexedCollection listaSalidaInterno;	    
	    IndexedCollection listaSalidaGeneral;
	    
	    KeyedCollection itemServiciosLocales;
	    KeyedCollection itemServiciosGlobales;
	    KeyedCollection itemEntrada; 
	    KeyedCollection itemServicio;
	    	    	    				 
		try {				
			strCodUsuario = getValueAt("s_cod_usuarisc").toString();
			strEmpresa = getValueAt("s_cod_logon").toString();
			
			Trace.trace(64, getClass().getName()+ " ******* strCodUsuario = " + strCodUsuario);
			Trace.trace(64, getClass().getName()+ " ******* strEmpresa = " + strEmpresa);
			Trace.trace(64, getClass().getName()+ " ******* codServicio = " + codServicio);									
			
			oMInformacionUsuarios = preEjecucionServicio.crearOM("InformacionUsuarios_om");
			oMInformacionUsuarios.setValueAt("Metodos.listarServiciosAsuntos.Ejecutar", "true");
			oMInformacionUsuarios.setValueAt("Metodos.listarServiciosAsuntos.EntradaMetodo.codCanal", "0026");
		    oMInformacionUsuarios.setValueAt("Metodos.listarServiciosAsuntos.EntradaMetodo.codEmpresa", strEmpresa);
		    oMInformacionUsuarios.setValueAt("Metodos.listarServiciosAsuntos.EntradaMetodo.codBancoInterno", "0082");
		    oMInformacionUsuarios.setValueAt("Metodos.listarServiciosAsuntos.EntradaMetodo.codUsuario", strCodUsuario);   
		    oMInformacionUsuarios.setValueAt("Metodos.listarServiciosAsuntos.EntradaMetodo.codProducto", "1380");
		    oMInformacionUsuarios.setValueAt("Metodos.listarServiciosAsuntos.EntradaMetodo.bancoAsunto", "82");
		    oMInformacionUsuarios.setValueAt("Metodos.listarServiciosAsuntos.EntradaMetodo.codServicio", codServicio);
		    oMInformacionUsuarios.setValueAt("Metodos.listarServiciosAsuntos.EntradaMetodo.idioma", "");
		    oMInformacionUsuarios.setValueAt("Metodos.listarServiciosAsuntos.EntradaMetodo.tipoAsunto", "");
		    oMInformacionUsuarios.setValueAt("Metodos.listarServiciosAsuntos.EntradaMetodo.asunto", "");
		    oMInformacionUsuarios.setValueAt("Metodos.listarServiciosAsuntos.EntradaMetodo.indOrdenanteBeneficiario", "");
		    oMInformacionUsuarios.setValueAt("Metodos.listarServiciosAsuntos.EntradaMetodo.codSubproducto", "0"); 
		    oMInformacionUsuarios.setValueAt("Metodos.listarServiciosAsuntos.EntradaMetodo.bloqueFirma", "");
		    oMInformacionUsuarios.setValueAt("Metodos.listarServiciosAsuntos.EntradaMetodo.indObtenerAsuntos", "");
		    oMInformacionUsuarios.setValueAt("Metodos.listarServiciosAsuntos.EntradaMetodo.codBancoProd", "0082");
		    oMInformacionUsuarios.setValueAt("Metodos.listarServiciosAsuntos.EntradaMetodo.codUsuarioAdmin", "");
			oMInformacionUsuarios.setValueAt("datosAPP.iv-id_sesion_ast", getIdSession());
		    		    		    
		    listaServiciosLocales = (IndexedCollection) oMInformacionUsuarios.getElementAt("Metodos.listarServiciosAsuntos.EntradaMetodo.Lista_ServicioL");
		    listaServiciosGlobales = (IndexedCollection) oMInformacionUsuarios.getElementAt("Metodos.listarServiciosAsuntos.EntradaMetodo.Lista_ServicioG");		    
		    listaServiciosLocales.removeAll();		    
		    listaServiciosGlobales.removeAll();
		    
		    itemServiciosLocales = (KeyedCollection) DataElement.getExternalizer().convertTagToObject(listaServiciosLocales.getElementSubTag());
		    itemServiciosGlobales = (KeyedCollection) DataElement.getExternalizer().convertTagToObject(listaServiciosGlobales.getElementSubTag());
		    itemServiciosLocales.setValueAt("Item","");		    
		    itemServiciosGlobales.setValueAt("Item","");
		    		    
		    listaServiciosLocales.addElement(itemServiciosLocales);
		    listaServiciosGlobales.addElement(itemServiciosGlobales);
		    		    
		    Trace.trace(64, getClass().getName() + " ******* INICIO ejecutando operacion multicanal cuenta ordenante ");		    		    
		    oMInformacionUsuarios.execute();
		    Trace.trace(64, getClass().getName() + " ******* FIN ejecutando operacion multicanal cuenta ordenante");
		    		    
		    listaSalidaGeneral = (IndexedCollection) oMInformacionUsuarios.getElementAt("Metodos.listarServiciosAsuntos.SalidaMetodo.RespuestaServicioCtx.Lista_ServicioCtx");		   		    		   		  
		    Trace.trace(64, getClass().getName() + " ******* listaSalidaGeneral = " + listaSalidaGeneral);
		    
		    Enumeration<?> VectorSalidaGeneralEnumeration = listaSalidaGeneral.getEnumeration();		    		   
			while (VectorSalidaGeneralEnumeration.hasMoreElements()) {
				itemServicio = (KeyedCollection) VectorSalidaGeneralEnumeration.nextElement();
				
				listaSalidaInterno = (IndexedCollection) itemServicio.getElementAt("Lista_Asunto");
				poderFirmanteServicio = (String) itemServicio.getValueAt("poderFirma");
								
				// Poder de firma sobre el servicio				
				if(poderFirmanteServicio.equalsIgnoreCase("S")){
					setValueAt("s_mancomunada", "NO");
				} else {
					setValueAt("s_mancomunada", "SI");
				}
								
				Enumeration<?> VectorSalidaInternoEnumeration = listaSalidaInterno.getEnumeration();
				while (VectorSalidaInternoEnumeration.hasMoreElements()) {
					itemEntrada = (KeyedCollection) VectorSalidaInternoEnumeration.nextElement();
					
					cuentaOrdenate = itemEntrada.getValueAt("nuAsunto").toString();
					setValueAt("CuentaOrdenante", cuentaOrdenate);
	
					Trace.trace(64, getClass().getName() + " ******* cuentaOrdenate = " + cuentaOrdenate);
					Trace.trace(64, getClass().getName() + " ******* poderFirmanteGlobal = " + getValueAt("s_poder_firma").toString());
					Trace.trace(64, getClass().getName() + " ******* poderFirmanteServicio = " + poderFirmanteServicio);							    		
		    		break;
		    	}
		    }
		    			
		} catch (DSEObjectNotFoundException e) {
			Trace.trace(64, getClass().getName()
					+ " ******* ERROR OpGestion1_rs7 (1) - Metodo: cargarCTAOrdenanteYPoderFirma(): " + e.getMessage());		
			throw e;
			
		} catch (DSEInvalidArgumentException e) {
			Trace.trace(64, getClass().getName()
					+ " ******* ERROR OpGestion1_rs7 (2) - Metodo: cargarCTAOrdenanteYPoderFirma(): " + e.getMessage());		
			throw e;
			
		} catch (BbvaException e) {
			Trace.trace(64, getClass().getName()
					+ " ******* ERROR OpGestion1_rs7 (3) - Metodo: cargarCTAOrdenanteYPoderFirma(): " + e.getMessage());		
			throw e;
				
		} catch (Exception e) {
			Trace.trace(64, getClass().getName()
					+ " ******* ERROR OpGestion1_rs7 (4) - Metodo: cargarCTAOrdenanteYPoderFirma(): " + e.getMessage());		
			throw e;		
		}
		
		Trace.trace(64, getClass().getName() + " ******* FIN OpGestion1_rs7 - cargarCTAOrdenanteYPoderFirma()");		
		return poderFirmanteServicio;
	}
	
	
	/**
	 * <h1>GP16880 Ofertas</h1> Metodo encargado de dar de alta las firmas de
	 * las ofertas, para usuarios solidario y mancomunados. producto ofertado es Credito Comercial.
	 *  
	 * @author <a href="mailto:angelicaalejandra.carvajal@bbva.com">Angelica Carvajal</a>
	 * @version 13/11/2019 Last modification :  v0.0.1
	 * 
	 * @param cuentaOrdenante
	 * @param estadoCAB (tabla TTLBHCAB - campo TTLBHCAB.COD_ESTACASH)
	 * @param ejecutarOM 
	 * @param nroContrato
	 * @param tipoProducto
	 * @param porcentajeFirmaCAB (S = 1, M2 = 0.5, M3 = 0.34, M4 = 0.25)
	 * @param pesoFirmante (S, M2, M3, M4)
	 * @param valorOferta
	 * @param cuentaSel 
	 * @param tipoUsu 
	 * 
	 * @throws Exception
	 */
	public void firmarOfertaCm(String cuentaOrdenante, String estadoCAB,
			String ejecutarOM, double nroContrato, String tipoProducto,
			double porcentajeFirmaCAB, String pesoFirmante, double valorOferta,
			String cliente, String tipoId, String numeroId, String cuentaSel, String tipoUsu)
			throws Exception {
		
		Trace.trace(64, getClass().getName() + " ******* INICIO OpGestion1 - firmarOfertaCm()");
						
		String idOrden = "99999999";
		String inicioReferencia = "00260082";		
		Connection connection = null;		
		PreparedStatement preparedStm = null;
		Connection connectionMan = null;
		PreparedStatement preparedStmMan = null;
		boolean resultado;	
		java.util.Date fechaActual;
		java.text.SimpleDateFormat sDFFecha;
		String fechaProceso;
		int resultadoMan = 0;
		
		String queryTTLNEMAN = "INSERT INTO TLNE.TTLNEMAN (TLNE_IDORDEN, TLNE_REFERENCIA, TLNE_BCODACCC,TLNE_BCODCTAA,TLNE_BPALACCE,TLNE_BPALACC2,TLNE_BASUNPRO,TLNE_BASUNASO,TLNE_BFECHA01,TLNE_BFECHA02,TLNE_BIMPORTE,TLNE_BIMPOAUX,TLNE_BINDPAGI,TLNE_BINDAUX1,TLNE_BINDAUX2,TLNE_BCODAUX1,TLNE_BCODAUX2,TLNE_BNUMAUX1,TLNE_BNUMAUX2,TLNE_BPAGINAC,TLNE_NOMOM, TLNE_DESCRIP) VALUES(?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
		String queryTTLBHCOR = "INSERT INTO TLCL.TTLBHCOR (COD_IDORDEN, COD_CLIECASH, COD_CLASEORD,COD_CABECORI,COD_CDNITR,COD_SUFPRESE,COD_IDORIGEN,DES_CUENORIG,COD_BANCINTE,COD_BANCSB,COD_DIISOALF,COD_FORMASUN,XTI_CLASEASU,COD_SWIFT,COD_BANABA,DES_FICHEROH,AUD_FMODIFIC,AUD_USUARIO,XTI_EMISOR,DES_ORDPAGO,XTI_INDMODAL) VALUES(?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		String queryTTLBHCAB = "INSERT INTO TLCL.TTLBHCAB (COD_CLIECASH, COD_CLASEORD, COD_IDORDEN,COD_CDNIFTR,COD_SUFPRESE,COD_DIISOALF,FEC_PROCESCA,DES_REFICHER,DES_NOMFICH,XSN_MEDCREAC,XSN_MODCREAC,FEC_BORRCASH,FEC_ESTACASH,XSN_ACTAUTDE,QTY_TOTIMPOR,QNU_TOTREGIS,XSN_FORMPAGO,COD_ESTACASH,QNU_PESOFIR,QNU_SIZE,DES_NOMFICHE,QNU_NUMITEM,DES_PATH,DES_FICHEROH,AUD_FMODIFIC,AUD_USUARIO,COD_DETSEROR,XTI_DETSEROR,COD_PRODCART,XSN_SUCURSAL,DES_PATHXML,DES_PATHHTML,DES_FXMLHTML,XTI_VALPERT,COD_LTIPO,DES_LDESC,XSN_BORRADO,QTY_IMPFINA,QNU_NUMREME,TIM_ORDEN) VALUES(?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, current timestamp)";				
		String queryTTLBHFUA = "INSERT INTO TLCL.TTLBHFUA (COD_CLIECASH, COD_CLASEORD, COD_IDORDEN, COD_ACCION, COD_IDACCION, COD_USUARIO, FEC_ACCIO, COD_USUFIRMA, COD_USUPODER, COD_ESTADFIC, FEC_ACCION, AUD_FMODIFIC, AUD_USUARIO, COD_IPCLIENT) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";				
		String queryTTLCLFFU = "INSERT INTO TLCL.TTLCLFFU (COD_CLIECASH, COD_CLASEORD, COD_IDORDEN, COD_USUARIO, COD_NUMALEAT, XSN_FIRMADO, AUD_FMODIFIC, AUD_USUARIO) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
													
		try {			
			fechaActual = new java.util.Date();
			sDFFecha = new java.text.SimpleDateFormat("yyyy-MM-dd");
			fechaProceso = sDFFecha.format(fechaActual);				
			SimpleDateFormat sHora = new SimpleDateFormat ("hhmmss");
			String fechaProcesoLimpia = fechaProceso.replaceAll("-", "");
			
			idOrden = aleatorio();		
			
			Trace.trace(64, getClass().getName() + " ******* idOrden = " + idOrden+
												"s_mancomunada = " + getValueAt("s_mancomunada")+
												"referencia = " + (String) getValueAt("s_cod_logon")+
												"usuario = " + (String) getValueAt("s_cod_usuarisc")+
												"fechaProcesoLimpia = " + fechaProcesoLimpia+
												"tipoProducto = " + tipoProducto+
												"ejecutarOM = " + ejecutarOM+
												"nroContrato = " + nroContrato+	
												"valorCredito = " + valorOferta+
												"cliente = " + cliente+
												"tipoId = " + tipoId+			
												"numeroId = " + numeroId+
												"cuentaSel = " + cuentaSel); 
						
			connection = ConsultaBD.getConexion("BDMexico");
			
			// BTLCLCO1 TLCL.TTLBHCOR
			Trace.trace(Trace.VTF, "" + " ******* INICIO TABLA: TLCL.TTLBHCOR");
			preparedStm = connection.prepareStatement(queryTTLBHCOR);
			preparedStm.setString(1, idOrden);
			preparedStm.setString(2, inicioReferencia + (String) getValueAt("s_cod_logon"));
			preparedStm.setString(3, OfertaConstantesEnum.CREDITO_VIRTUAL_CLASE_ORDEN.getConstante());
			preparedStm.setInt(4, 0);
			preparedStm.setString(5, "*********");
			preparedStm.setString(6, "***");
			preparedStm.setString(7, "");
			preparedStm.setString(8, cuentaOrdenante);
			preparedStm.setString(9, "");
			preparedStm.setInt(10, 82);
			preparedStm.setString(11, "COP");
			preparedStm.setInt(12, 0);
			preparedStm.setString(13, tipoProducto);
			preparedStm.setString(14, "");
			preparedStm.setString(15, "");
			preparedStm.setString(16, "");
			preparedStm.setString(17, fechaProceso);
			preparedStm.setString(18, (String) getValueAt("s_cod_usuarisc"));
			preparedStm.setString(19, "");
			preparedStm.setString(20, "");
			preparedStm.setString(21, "");			
			resultado = preparedStm.execute();
			Trace.trace(Trace.VTF, "" + " ******* resultado TTLBHCOR = " + resultado);
			
			// BTLCLCO1 TLCL.TTLBHCAB
			Trace.trace(Trace.VTF, "" + " ******* INICIO TABLA: TLCL.TTLBHCAB");
			preparedStm = connection.prepareStatement(queryTTLBHCAB);
			preparedStm.setString(1, inicioReferencia + (String) getValueAt("s_cod_logon"));
			preparedStm.setString(2, OfertaConstantesEnum.CREDITO_VIRTUAL_CLASE_ORDEN.getConstante());
			preparedStm.setString(3, idOrden);
			preparedStm.setString(4, "*********");
			preparedStm.setString(5, "***");
			preparedStm.setString(6, "COP");
			preparedStm.setString(7, fechaProcesoLimpia);
			preparedStm.setString(8, "");
			preparedStm.setString(9, "ALTA OFE RENOVA CRE.");//pendiente
			preparedStm.setString(10, "");
			preparedStm.setString(11, "O");
			preparedStm.setString(12, "");
			preparedStm.setString(13, fechaProcesoLimpia);
			preparedStm.setString(14, "");
			preparedStm.setDouble(15, 0.0);
			preparedStm.setInt(16, 1);
			preparedStm.setString(17, "1");
			preparedStm.setString(18, estadoCAB);
			preparedStm.setDouble(19, porcentajeFirmaCAB);
			preparedStm.setDouble(20, 0);
			preparedStm.setString(21, "");
			preparedStm.setDouble(22, 0.0);
			preparedStm.setString(23, "");
			preparedStm.setString(24, "");
			preparedStm.setString(25, fechaProceso);
			preparedStm.setString(26, (String) getValueAt("s_cod_usuarisc"));
			preparedStm.setString(27, "");
			preparedStm.setString(28, "");
			preparedStm.setString(29, "");
			preparedStm.setString(30, "");
			preparedStm.setString(31, "");
			preparedStm.setString(32, "");
			preparedStm.setString(33, "");
			preparedStm.setString(34, "");
			preparedStm.setString(35, "");
			preparedStm.setString(36, "");
			preparedStm.setString(37, "");
			preparedStm.setDouble(38, 0.0);
			preparedStm.setDouble(39, 0.0);
			resultado = preparedStm.execute();
			Trace.trace(Trace.VTF, "" + " ******* resultado TTLBHCAB = " + resultado);
									
			// BTLCLCO1 TLCL.TTLCLFFU
			Trace.trace(Trace.VTF, "" + " ******* INICIO TABLA: TLCL.TTLCLFFU");
			preparedStm = connection.prepareStatement(queryTTLCLFFU);
			preparedStm.setString(1, inicioReferencia + (String) getValueAt("s_cod_logon"));
			preparedStm.setString(2, OfertaConstantesEnum.CREDITO_VIRTUAL_CLASE_ORDEN.getConstante());
			preparedStm.setString(3, idOrden);
			preparedStm.setString(4, (String) getValueAt("s_cod_usuarisc"));
			preparedStm.setString(5, (String) sHora.format(new Date()));
			preparedStm.setString(6, "S");
			preparedStm.setString(7, fechaProceso);
			preparedStm.setString(8, (String) getValueAt("s_cod_usuarisc"));		
			resultado = preparedStm.execute();
			Trace.trace(Trace.VTF, "" + " ******* resultado TTLCLFFU = " + resultado);
														
			// BTLCLCO1 TLCL.TTLBHFUA
			Trace.trace(Trace.VTF, "" +" ******* INICIO (1) TABLA: TLCL.TTLBHFUA");
			preparedStm = connection.prepareStatement(queryTTLBHFUA);
			preparedStm.setString(1, inicioReferencia + (String) getValueAt("s_cod_logon"));
			preparedStm.setString(2, OfertaConstantesEnum.CREDITO_VIRTUAL_CLASE_ORDEN.getConstante());
			preparedStm.setString(3, idOrden);
			preparedStm.setInt(4, 1);
			preparedStm.setInt(5, 0);
			preparedStm.setString(6, (String) getValueAt("s_cod_usuarisc"));
			preparedStm.setString(7, fechaProcesoLimpia);
			preparedStm.setString(8, "");
			preparedStm.setString(9, "");
			preparedStm.setString(10, "024");
			preparedStm.setString(11, (String) sHora.format(new Date()));
			preparedStm.setString(12, fechaProceso);
			preparedStm.setString(13, (String) getValueAt("s_cod_usuarisc"));
			preparedStm.setString(14, (String) getValueAt("s_IPCliente"));				
			resultado = preparedStm.execute();
			Trace.trace(Trace.VTF, "" +" ******* resultado TTLBHFUA = " + resultado);	
			
			// solidario
			if (((String) getValueAt("s_mancomunada")).equals("NO")) {	
	
				// BTLCLCO1 TLCL.TTLBHFUA				
				Trace.trace(Trace.VTF, "" +" ******* INICIO (2) TABLA: TLCL.TTLBHFUA");
				preparedStm = connection.prepareStatement(queryTTLBHFUA);
				preparedStm.setString(1, inicioReferencia + (String) getValueAt("s_cod_logon"));
				preparedStm.setString(2, OfertaConstantesEnum.CREDITO_VIRTUAL_CLASE_ORDEN.getConstante());
				preparedStm.setString(3, idOrden);
				preparedStm.setInt(4, 11);
				preparedStm.setInt(5, 0);
				preparedStm.setString(6, (String) getValueAt("s_cod_usuarisc"));
				preparedStm.setString(7, fechaProcesoLimpia);
				preparedStm.setString(8, (String) getValueAt("s_cod_usuarisc"));
				preparedStm.setString(9, pesoFirmante);
				preparedStm.setString(10, "022"); 
				preparedStm.setString(11, (String) sHora.format(new Date()));
				preparedStm.setString(12, fechaProceso);
				preparedStm.setString(13, (String) getValueAt("s_cod_usuarisc"));
				preparedStm.setString(14, (String) getValueAt("s_IPCliente"));			
				resultado = preparedStm.execute();
				Trace.trace(Trace.VTF, "" +" ******* resultado (1) TTLBHFUA = " + resultado);													
			} 
			
			// mancomunado
			if (((String) getValueAt("s_mancomunada")).equals("SI")) {
				
				// BTLCLCO1 TLCL.TTLBHFUA
				Trace.trace(Trace.VTF, "" +" ******* INICIO (3) TABLA: TLCL.TTLBHFUA");
				preparedStm = connection.prepareStatement(queryTTLBHFUA);
				preparedStm.setString(1, inicioReferencia + (String) getValueAt("s_cod_logon"));
				preparedStm.setString(2, OfertaConstantesEnum.CREDITO_VIRTUAL_CLASE_ORDEN.getConstante());
				preparedStm.setString(3, idOrden);
				preparedStm.setInt(4, 4);
				preparedStm.setInt(5, 1);
				preparedStm.setString(6, (String) getValueAt("s_cod_usuarisc"));
				preparedStm.setString(7, fechaProcesoLimpia);
				preparedStm.setString(8, (String) getValueAt("s_cod_usuarisc"));
				preparedStm.setString(9, pesoFirmante);
				preparedStm.setString(10, "019"); 
				preparedStm.setString(11, (String) sHora.format(new Date()));
				preparedStm.setString(12, fechaProceso);
				preparedStm.setString(13, (String) getValueAt("s_cod_usuarisc"));
				preparedStm.setString(14, (String) getValueAt("s_IPCliente"));			
				resultado = preparedStm.execute();
				Trace.trace(Trace.VTF, "" + " ******* resultado TTLBHFUA = " + resultado);
			}			
			
			// BCNCO001 TLNE.TTLNEMAN			
			if (((String) getValueAt("s_mancomunada")).equals("SI")) {						
				Trace.trace(Trace.VTF, "" +" ******* INICIO TABLA: TLNE.TTLNEMAN");				
												
				connectionMan = ConsultaBD.getConexionBBVNet();		
				
				preparedStmMan = connectionMan.prepareStatement(queryTTLNEMAN);																		
				preparedStmMan.setString(1, idOrden);
				preparedStmMan.setString(2, (String) getValueAt("s_cod_logon") + (String) getValueAt("s_cod_usuarisc"));
				preparedStmMan.setString(3, cliente);
				preparedStmMan.setString(4, "ALTA OFE RENOV CRE.");//pendiente
				preparedStmMan.setString(5, numeroId);
				preparedStmMan.setString(6, "");
				preparedStmMan.setString(7, cuentaSel);//nuevo campo cuantasel TLNE_BASUNPRO
				preparedStmMan.setString(8, "");
				preparedStmMan.setString(9, fechaProcesoLimpia);
				preparedStmMan.setString(10, "");
				preparedStmMan.setDouble(11, 0);
				preparedStmMan.setDouble(12, 0);
				preparedStmMan.setString(13, "");
				preparedStmMan.setString(14, tipoId);
				preparedStmMan.setString(15, "");
				preparedStmMan.setString(16, "0026");
				preparedStmMan.setString(17, tipoProducto);
				preparedStmMan.setDouble(18, nroContrato);
				preparedStmMan.setDouble(19, valorOferta);
				preparedStmMan.setString(20, "");
				preparedStmMan.setString(21, ejecutarOM);
				preparedStmMan.setString(22, "ALTA OFE RENOVA CRE.");//pendiente								
				resultadoMan = preparedStmMan.executeUpdate();
				Trace.trace(Trace.VTF, "" + " ******* resultadoMan TTLNEMAN = " + resultadoMan);	
			}
			
			Trace.trace(Trace.VTF, "" + " ******* FIN OpGestion1 (1) - firmarOfertaCm()");
			
		} catch (SQLException eSQL) {			
			Trace.trace(Trace.Error, "" 
					+ " ******* ERROR OpGestion1 (1) - Metodo: firmarOfertaCm(): " + eSQL.getMessage());
			
			throw new BbvaARQException("ARQ0300680", BbvaARQException.DB2,
					eSQL.getSQLState(), eSQL);
			
		} catch (Exception e) {
			Trace.trace(Trace.Error, ""
					+ " ******* ERROR OpGestion1 (2) - Metodo: firmarOfertaCm(): " + e.getMessage());
			
			throw new BbvaARQException("ARQ0300690", BbvaARQException.DB2,
					e.getMessage(), e);
		}
	
		finally {
			Trace.trace(Trace.VTF, "" + " ******* FIN OpGestion1 (2) - firmarOfertaCm()");
			
			try {
				if (preparedStm != null) {
					preparedStm.close();
				}
				
				if (((String) getValueAt("s_mancomunada")).equals("SI")
						&& preparedStmMan != null) {
					preparedStmMan.close();
				}
			} catch (SQLException eSQLI) {
				Trace.trace(Trace.Error, ""
						+ " ******* ERROR OpGestion1 (3) - Metodo: firmarOfertaCm(): " + eSQLI.getMessage());
				
				throw new BbvaARQException("ARQ0300700", BbvaARQException.DB2,
						eSQLI.getSQLState(), eSQLI);
			}
			
			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
				
				if (((String) getValueAt("s_mancomunada")).equals("SI")
						&& connectionMan != null && !connectionMan.isClosed()) {
					connectionMan.close();
				}						
			} catch (SQLException eSQLII) {
				Trace.trace(Trace.Error, ""
						+ " ******* ERROR OpGestion1 (4) - Metodo: firmarOfertaCm(): " + eSQLII.getMessage());
				
				throw new BbvaARQException("ARQ0300710", BbvaARQException.DB2,
						eSQLII.getSQLState(), eSQLII);
			}
			
			Trace.trace(Trace.VTF, "" + " ******* FIN OpGestion1 (3) - firmarOfertaCm()");
		}
		
		Trace.trace(Trace.VTF, "" + " ******* FIN OpGestion1 (4) - firmarOfertaCm()");
	}
		
	
}