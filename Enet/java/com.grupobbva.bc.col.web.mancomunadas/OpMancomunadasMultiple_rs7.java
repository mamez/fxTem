package com.grupobbva.bc.col.web.mancomunadas;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.StringTokenizer;

import com.grupobbva.ii.sf.base.BbvaARQException;
import com.grupobbva.ii.sf.base.BbvaException;
import com.grupobbva.ii.sf.modelo.ConsultaBD;
import com.grupobbva.ii.sf.operacion.OperacionMulticanal;
import com.ibm.dse.base.DSEInvalidArgumentException;
import com.ibm.dse.base.DSEObjectNotFoundException;
import com.ibm.dse.base.DataElement;
import com.ibm.dse.base.IndexedCollection;
import com.ibm.dse.base.KeyedCollection;
import com.ibm.dse.base.Trace;
import com.grupobbva.bc.col.web.comercioext.clientews.RequestBankTradeService;
import com.grupobbva.bc.col.web.comercioext.clientews.ResponseBankTradeService;
import com.grupobbva.bc.col.web.comercioext.clientews.WrapperBanktradeService;
import com.grupobbva.bc.col.web.ofertas.OfertaConstantesEnum;
import com.grupobbva.bc.col.web.pse.MetodoEnum;
import com.grupobbva.bc.col.web.utilidades.OpControl;//CMC - FUNCION DE NEGOCIO FNGU - PRUEBA - 19/09/2019
import org.apache.axis.AxisFault;//VARIOS NITS 2 TOUT CMC 17-02-2020 

public class OpMancomunadasMultiple_rs7 extends com.grupobbva.ii.sf.operacion.OpGestion {

	private static final long serialVersionUID = 4381610155630166802L;

	String selectman = "SELECT TLNE_IDORDEN, TLNE_REFERENCIA, TLNE_BCODACCC,TLNE_BCODCTAA,TLNE_BPALACCE,TLNE_BPALACC2,TLNE_BASUNPRO,TLNE_BASUNASO,TLNE_BFECHA01,TLNE_BFECHA02,TLNE_BIMPORTE,TLNE_BIMPOAUX,TLNE_BINDPAGI,TLNE_BINDAUX1,TLNE_BINDAUX2,TLNE_BCODAUX1,TLNE_BCODAUX2,TLNE_BNUMAUX1,TLNE_BNUMAUX2,TLNE_BPAGINAC,TLNE_NOMOM,TLNE_DESCRIP FROM TLNE.TTLNEMAN WHERE TLNE_IDORDEN = ?";
	String selectefi = "SELECT TLNE_IDORDEN, TLNE_TIPREGIST, TLNE_RESTOARCH FROM TLNE.TTLNEEFI WHERE TLNE_IDORDEN = ? ";
	String SELECTCAB = "SELECT QTY_TOTIMPOR  FROM TLCL.TTLBHCAB WHERE COD_IDORDEN = ? "; //Correcion Pago Prestamos Importe
	String deleteman = "DELETE FROM TLNE.TTLNEMAN WHERE TLNE_IDORDEN = ?";
	
	/*GP8438 I Modificacion en la sentencia */
	String updatecab = "UPDATE TLCL.TTLBHCAB set COD_ESTACASH = '022' WHERE COD_IDORDEN = ? ";
	//INI INC Mapeo error ENET 13-07-2018
	String updatecab24 = "UPDATE TLCL.TTLBHCAB set COD_ESTACASH = '023' WHERE COD_IDORDEN = ? ";
	String updatecabIA = "UPDATE TLCL.TTLBHCAB set DES_FXMLHTML = ? WHERE COD_IDORDEN = ? ";
	//FIN INC Mapeo error ENET 13-07-2018
	String deletecab = "DELETE FROM TLCL.TTLBHCAB WHERE COD_IDORDEN = ? ";

	String updateman = "UPDATE TLNE.TTLNEMAN SET  TLNE_IDORDEN = CONCAT('*',?) WHERE TLNE_IDORDEN = ?";
	String updateman2 = "UPDATE TLNE.TTLNEMAN SET  TLNE_IDORDEN = ? WHERE TLNE_IDORDEN = CONCAT('*',?)";
	String deleteman2 = "DELETE FROM TLNE.TTLNEMAN WHERE TLNE_IDORDEN = CONCAT(?,'*')";

	/*GP8438 F */
	String deletecor = "DELETE FROM TLCL.TTLBHCOR WHERE COD_IDORDEN = ?";
	String selectcab23 = "SELECT COD_ESTACASH FROM TLCL.TTLBHCAB WHERE COD_ESTACASH = '023' AND COD_IDORDEN = ? ";//IN-411 PETICION MOD FIRMAS CMC 12-03-2020
	
	// INI - GP13427 Reingenieria de Divisas
	private String tipoOperacion;
	private String subTipoOperacion;
	private String numeroCuenta;
	private String numeroUsuario;
	private String numeroCliente;
	private String numeroOperacion;
	private KeyedCollection kGiro,kEntradaGiros ;
	private String sOmConsultaUnicaOperaciones = "divisas_consulta_unica_operaciones_om";
	RequestBankTradeService peticion;
	private final String ERROR_WS_BANKTRADE = "Fallo llamado Banktrade - ";
	// FIN - GP13427 Reingenieria de Divisass
	private OpControl controlOM;//CMC - FUNCION DE NEGOCIO FNGU - PRUEBA - 19/09/2019
	
	public OpMancomunadasMultiple_rs7() {
        super();
    }

    /**
     * Comentario de constructor OPMexCtasOrd.
     * @param anOperationName java.lang.String
     * @exception java.io.IOException La descripción de excepción.
     */
    public OpMancomunadasMultiple_rs7(String anOperationName) throws java.io.IOException {
        super(anOperationName);
    }

    /**
     * Comentario de constructor OPMexCtasOrd.
     * @param anOperationName java.lang.String
     * @param aParentContext com.ibm.dse.base.Context
     * @exception java.io.IOException La descripción de excepción.
     * @exception com.ibm.dse.base.DSEInvalidRequestException La descripción de excepción.
     */
    public OpMancomunadasMultiple_rs7(String anOperationName, com.ibm.dse.base.Context aParentContext) throws java.io.IOException, com.ibm.dse.base.DSEInvalidRequestException {
        super(anOperationName, aParentContext);
    }

    /**
     * Comentario de constructor OPMexCtasOrd.
     *
     * @param anOperationName java.lang.String
     * @param aParentContext java.lang.String
     * @exception java.io.IOException La descripción de excepción.
     * @exception com.ibm.dse.base.DSEObjectNotFoundException La descripción de excepción.
     * @exception com.ibm.dse.base.DSEInvalidRequestException La descripción de excepción.
     */
    public OpMancomunadasMultiple_rs7(String anOperationName, String aParentContext) throws java.io.IOException, com.ibm.dse.base.DSEObjectNotFoundException, com.ibm.dse.base.DSEInvalidRequestException {
        super(anOperationName, aParentContext);
    }

    public void jsonMultienvio () {
    	try {
			multiEnvioEnet();
		} catch (Exception e) {
			Trace.trace(Trace.Debug, "Error general al obtener detalle orden ENET: " + e.getMessage());
		}
		setEstado("1");
    }
    

    /**
     * Envío Multiple Ordenes ENET
     * @throws Exception
     */
	public void multiEnvioEnet() throws Exception {

		Trace.trace(Trace.Debug , "ENET" , getClass().getName() + " : multiEnvioEnet() -> INICIO");
		
		String[] idOrdenes = ((String) getValueAt("id_orden")).split("_");
		final int tamOrden = idOrdenes.length;
		String id_ordenPR;
		String id_orden2;
		String haySesion="";//INC 222 FX CMC 11/06/2019
		
		//INI INC Mapeo error ENET
		Connection conn = null;
		Connection conn1 = null; //Caso 0078 CMC 05/03/2019
		Connection conn2 = null;
		PreparedStatement stm24 = null;
		//FIN INC Mapeo error ENET
		PreparedStatement stm23 = null;//IN-411 PETICION MOD FIRMAS CMC 12-03-2020
		
		IndexedCollection iCordenes = (IndexedCollection) getElementAt("ordenes");
		iCordenes.removeAll();

		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
		
		for (int h = 0 ; h < tamOrden ; ++h) {
		
			id_ordenPR = idOrdenes[h];
			id_orden2 = id_ordenPR.substring(0 , 7);
			
			//INI INC Mapeo error ENET
			//INI Caso 0078 CMC 06/03/2019
			PreparedStatement stm_p = null;
			PreparedStatement stm_cab= null;
			ResultSet rs_p = null;
			ResultSet rsCAB = null;
			ResultSet rsCAB23 = null;//IN-411 PETICION MOD FIRMAS CMC 12-03-2020
			boolean ejecutarPeticion=true;//IN-411 PETICION MOD FIRMAS CMC 12-03-2020
			try {
				conn1 = ConsultaBD.getConexionBBVNet();

				stm_p = conn1.prepareStatement(selectman);
				stm_p.setString(1, id_ordenPR);
				rs_p = stm_p.executeQuery();
				
				conn = ConsultaBD.getConexion("BDMexico");
				
				//INI IN-411 PETICION MOD FIRMAS CMC 18-03-2020
				stm23 = conn.prepareStatement(selectcab23);
				stm23.setString(1 , id_ordenPR);
				try {
					rsCAB23=stm23.executeQuery();
					Trace.trace(Trace.Information , "-----------> Se realiza consulta select de rechazo:" + id_ordenPR);
					if(rsCAB23.next()) {
						ejecutarPeticion=false;
						Trace.trace(Trace.Information , "-----------> Ya se ejecuto o esta en proceso no se va a enviar a proceso, ID_Orden: " + id_ordenPR);
					}
				} catch (Exception e) {
					Trace.trace(Trace.Error , "-----------> Excepcion al lanzar SELECT CAB23");
				}
				Trace.trace(Trace.Information , "----------->ejecutarPeticion:" + ejecutarPeticion);
				//FIN IN-411 PETICION MOD FIRMAS CMC 18-03-2020

				if(rs_p.next() && ejecutarPeticion){ //IN-411 PETICION MOD FIRMAS CMC 12-03-2020
					
					stm24 = conn.prepareStatement(updatecab24);
					stm24.setString(1 , id_ordenPR);
					
					try {
						stm24.execute();
						Trace.trace(Trace.Debug , "-----------> Update CAB1 idOrden: " + id_ordenPR);
					} catch (Exception e) {
						Trace.trace(Trace.Debug , "-----------> Excepcion al lanzar Update CAB1");
					}
				//FIN INC Mapeo error ENET
				}
				if (stm_p != null) {
					stm_p.close();
				}
				if (conn1 != null && !conn1.isClosed()) {
					conn1.close();
				}
				//INI IN-411 PETICION MOD FIRMAS CMC 12-03-2020
				if (stm23 != null) {
					stm23.close();
				}
				//FIN IN-411 PETICION MOD FIRMAS CMC 12-03-2020
				
			} catch (SQLException aSQLExcV) {
				Trace.trace(Trace.Debug, "Error en Base de Datos: " + aSQLExcV.getSQLState() , aSQLExcV.getMessage());
			}
			//FIN Caso 0078 CMC 06/03/2019
			PreparedStatement stm = null;
			PreparedStatement stm1 = null;
			PreparedStatement stm2 = null;
			PreparedStatement stmIA = null;
			ResultSet rs = null;
			ResultSet rs1 = null;
			
			
			String nomom	= "";
			String modif	= "";
			String paginac =  "";				
			
			String descripcion = "";
			double tlne_importe = 0.0;
			
			conn = ConsultaBD.getConexionBBVNet();
			KeyedCollection kcOrden = (KeyedCollection) DataElement.getExternalizer().convertTagToObject(iCordenes.getElementSubTag());
			
			try {

				stm = conn.prepareStatement(selectman);
				stm.setString(1, id_ordenPR);
				rs = stm.executeQuery();
				
				kcOrden.setValueAt("idOrden", id_ordenPR);
				kcOrden.setValueAt("indLanzador", String.valueOf(h));

				if (rs.next() && ejecutarPeticion) { //IN-411 PETICION MOD FIRMAS CMC 12-03-2020
					
					//INI incidencia 222 FX CMC 11/06/2019
					haySesion="";
//INI CMC - FUNCION DE NEGOCIO FNGU - PRUEBA - 17/09/2019
					String referencia2 	= ((String) rs.getString("TLNE_REFERENCIA")).substring(0, 8);
					String usuario2 	= ((String) rs.getString("TLNE_REFERENCIA")).substring(8);
//INI CMC - FUNCION DE NEGOCIO FNGU - PRUEBA - 17/09/2019
					try{
						haySesion=getValueAt("s_hay_sesion").toString();
						
					} catch(Exception f){
						Trace.trace(Trace.Error, getClass().getName()+ "***No hay sesion"+ f);
					}					
					//FIN incidencia 222 FX CMC 11/06/2019
								
						if (null == haySesion || "no".equals(haySesion) || "" == haySesion){//INC 222 FX CMC 11/06/2019

							String referencia 	= ((String) rs.getString("TLNE_REFERENCIA")).substring(0, 8);
							String usuario 		= ((String) rs.getString("TLNE_REFERENCIA")).substring(8);

							om = creaOM("sign_on_om");
							om.setValueAt("Entrada.BCODACCC", referencia + usuario);
							om.setValueAt("Entrada.BASUNPRO", usuario);

							try {
								ejecutarOM(om);
							} catch (Exception e) {
								kcOrden.setValueAt("error" , "No Sesion en F100");
								Trace.trace(Trace.Debug , "No Sesion en F100");
								continue;
								// throw new BbvaException("0102");
							}
							
							setValueAt("s_cod_nomconta"	, om.getValueAt("Salida.BNOMBREE"));
							setValueAt("s_cod_nomempre"	, om.getValueAt("Salida.BNOMBREM"));
							setValueAt("s_hay_sesion"	, "si");
						}//INC 222 FX CMC 11/06/2019
					
					
					nomom = rs.getString("TLNE_NOMOM");
					modif = rs.getString("TLNE_BINDAUX1");
					paginac = rs.getString("TLNE_BPAGINAC");
					String Conve= rs.getString("TLNE_BPALACC2");
					
					kcOrden.setValueAt("factura", paginac);
					
					String opcion = "";
					
					// @author CMC Camilo Rojas (hernancamilo.rojas.contractor@bbva.com)
		    		// @version 03/12/2018 Last modification : 20-02-2019 v0.0.7
		    		// GP16295 - firmas mancomunadas ofertas.
					if (nomom.equals("ofertas_alta_om")) {						
						altaOfertasCV(nomom, id_ordenPR, rs, kcOrden);
					}
					
					// @version 12/02/2020 Last modification : 12/02/2020 v0.0.1
		    		// GP16880- firmas mancomunadas ofertas.
					if (nomom.equals("ofertascm_alta_om")) {						
						altaOfertasCM(nomom, id_ordenPR, rs, kcOrden);
					}
					
					// @author Hector Siso
		    		// @version 09/04/2019 Last modification : 09/04/2019 v0.0.1
		    		// GP16543 - freemium.
					if (nomom.equals("freemium_contratar_om")) {						
						contratarFreemium(nomom, id_ordenPR, rs, kcOrden);
					}
					
					//	GP-11311 AutoGestion INICIO
			    	if(nomom.equals("autogestion_serv_alta_om")||nomom.equals("autogestion_asocxserv_alta_om")||nomom.equals("autogestion_prod_baja_om")||nomom.equals("autogestion_serv_baja_om")||nomom.equals("autogestion_asocxserv_baja_om")||nomom.equals("autogestion_prod_alta_om")){
			    		om = creaOM(nomom);
						Trace.trace(Trace.Debug,"INGRESO A MANCOMUNADAS -- AUTOGESTION");
			    		String idOrden=(String)getValueAt("id_orden");
			    		String referencia=((String)rs.getString("TLNE_REFERENCIA")).substring(0,8);
						String usuario=((String)rs.getString("TLNE_REFERENCIA")).substring(8);				
						om.setValueAt("Entrada.REFERENCIA",referencia);
					    om.setValueAt("Entrada.USUARIO",usuario);
					    descripcion=(String)rs.getString("TLNE_DESCRIP");
			    		/*
			    		 * SE REALIZA EL SELECT EN LA TABLA TTLBHAUT PARA TRAER LOS CONCEPTOS
			    		 * */
			    		String selectAUT = "SELECT ID_ORDEN, REFERENCIA, SERVICIO, DESC_SERV, ASUNTO, TIPO_ASUNTO, SOLICITUD, TIPO_GESTION, CLASE_ORDEN, LIM_OPER, LIM_DIA, LIM_MEN, LIMITES, AUD_FMODIFIC, AUD_USUARIO FROM TLCL.TTLBHAUT WHERE ID_ORDEN = ?";
			    		Connection connAut = ConsultaBD.getConexion("BDMexico");
			    		PreparedStatement stmAut = connAut.prepareStatement(selectAUT, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);	    		
			    		//PreparedStatement stmAut = connAut.prepareStatement(selectAUT);
						stmAut.setString(1, id_ordenPR);
			    		ResultSet rsAut=stmAut.executeQuery();	    		
			    		/*
			    		 * VALORES DE LA TABLA TLCL.TTLBHAUT QUE SE NECESITAN PARA LANZAR LA OM
			    		 * CORRESPONDIENTE
			    		 * */	    		
			    		String codClaseOrd="";
			    		String codServicio="";
			    		String limite="";
			    		String limiteOperacion="";
			    		String limiteDiario="";
			    		String limiteMensual="";
			    		String asunto="";
			    		String tipoAsunto="";	    		
			    		/*
			    		 * Se controla en envio de datos HOST para el alta de servicios, ya que la trama puede llegar a ser muy grande,
			    		 * por lo tanto se utiliza un indicador maxEnvio=95 para el alta de servicios, maxEnvio=1000 para los demas servicios.
			    		 * */
			    		int maxEnvio=95;	    		
			    		IndexedCollection icListaServicios = (IndexedCollection) om.getElementAt("Entrada.LISTASAUTOGES");
			    		icListaServicios.removeAll();		
			    		int ciclos=0;
						int conServicios=0;	
						int indPag=0;
						Trace.trace(Trace.Debug,"INGRESO A MANCOMUNADAS -- AUTOGESTION -- ANTES DEL WHILE");
			    		while(rsAut.next()){
			    				KeyedCollection kcCon = (KeyedCollection) DataElement.getExternalizer().convertTagToObject(icListaServicios.getElementSubTag());	
				    			codServicio=rsAut.getString("SERVICIO");
				    			Trace.trace(Trace.Debug,"INGRESO A MANCOMUNADAS -- AUTOGESTION-- COD SERVICIO:"+codServicio);
				    			kcCon.setValueAt("COD-SERVICIO", codServicio);
				    			limite=rsAut.getString("LIMITES");
				    			kcCon.setValueAt("IND-LIMITES", limite);		    			
				    			if(limite.trim().equals("S")){
				    				limiteOperacion=rsAut.getString("LIM_OPER");
				    				kcCon.setValueAt("LIMITE-OPERA", new Long(limiteOperacion));
				    				limiteDiario=rsAut.getString("LIM_DIA");
				    				kcCon.setValueAt("LIMITE-DIARIO", new Long(limiteDiario));
				    				limiteMensual=rsAut.getString("LIM_MEN");
				    				kcCon.setValueAt("LIMITE-MENSUAL", new Long(limiteMensual));
				    			}
				    			asunto=rsAut.getString("ASUNTO");
				    			Trace.trace(Trace.Debug,"INGRESO A MANCOMUNADAS -- AUTOGESTION-- COD ASUNTO:"+asunto);
				    			kcCon.setValueAt("ASUNTO", asunto);
				    			tipoAsunto=rsAut.getString("TIPO_ASUNTO");
				    			kcCon.setValueAt("TIPO-ASUNTO", tipoAsunto);
				    			icListaServicios.addElement(kcCon);
				    			conServicios++;
				    			if(conServicios==maxEnvio){	
				    				    indPag=conServicios;
				    					om.setValueAt("Entrada.IND-PAGINACION", new Integer(indPag));
				    					Trace.trace(Trace.Debug,"INGRESO A MANCOMUNADAS -- AUTOGESTION-- IND - PAG - "+indPag);			    					
				    					ejecutarOM(om);
				    					ciclos++;
				    					icListaServicios.removeAll();
				    					/*
				    					 * Volvemos a crear la om para la siguiente ejecucion.
				    					 * 
				    					 * */
				    					om = creaOM(nomom);
				    					om.setValueAt("Entrada.REFERENCIA",referencia);
				    				    om.setValueAt("Entrada.USUARIO",usuario);
				    				    icListaServicios = (IndexedCollection) om.getElementAt("Entrada.LISTASAUTOGES");
				    					icListaServicios.removeAll();
				    					conServicios=0;
				    			}else if(rsAut.isLast()){
				    				codClaseOrd=rsAut.getString("CLASE_ORDEN");
				    				indPag=conServicios;
				    				Trace.trace(Trace.Debug,"INGRESO A MANCOMUNADAS -- AUTOGESTION-- IND - PAG - "+indPag);	
				    				om.setValueAt("Entrada.IND-PAGINACION", new Integer(indPag));		    				
			    					ejecutarOM(om);
				    			}
			    		}
			    		stmAut.close();
			    		connAut.close();
			    		
			    		/*
			    		 * Insertamos en la FUA
			    		 * 
			    		 */
			    		
			    		String insertaTTLBHFUA =
								"INSERT INTO TLCL.TTLBHFUA (COD_CLIECASH, COD_CLASEORD, COD_IDORDEN, COD_ACCION, COD_IDACCION, COD_USUARIO, FEC_ACCIO, COD_USUFIRMA, COD_USUPODER, COD_ESTADFIC, FEC_ACCION, AUD_FMODIFIC, AUD_USUARIO, COD_IPCLIENT) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";				
						Connection 			connFUA 		= null;
						PreparedStatement	stmFUA 			= null;
						
						connFUA = ConsultaBD.getConexion("BDMexico");
						stmFUA = connFUA.prepareStatement(insertaTTLBHFUA);
						
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
						
						Trace.trace(Trace.Debug, getClass().getName()+"", "Traza ***************E1************************ ");
						stmFUA.setString(1, "00260082"+referencia);
						stmFUA.setString(2, codClaseOrd);
						stmFUA.setString(3, id_ordenPR);
						stmFUA.setInt(4, 11);  //--ACCION ENVIADO
						stmFUA.setInt(5, 0);  //--ID ACCION
						stmFUA.setString(6, (String)getValueAt("s_cod_usuarisc"));
						stmFUA.setString(7, f1);
						stmFUA.setString(8, "");
						stmFUA.setString(9, "");
						stmFUA.setString(10, "001");
						stmFUA.setString(11, (String) hora.format(new Date()));
						stmFUA.setString(12, f);
						stmFUA.setString(13, "");
						stmFUA.setString(14, (String)getValueAt("s_ip"));
						Trace.trace(Trace.Debug, getClass().getName()+"", "Traza ***************E2************************ ");
						stmFUA.execute();
						
						stmFUA.close();
						connFUA.close();
						
						
		               
			    		
						setValueAt("codClaseOrd", codClaseOrd);
			    		
			    	}//GP-11311 AutoGestion FIN
					
					if(!nomom.equals("neg_divisas_envio_om")){
						om = creaOM(nomom);
					}
					
					Trace.trace(Trace.Debug , "Orden: " + id_ordenPR + " *** OM Invocar: " + nomom);
					
			    	// INI - GP13427 Reingenieria de Divisas
			    	if(nomom.equals("neg_divisas_envio_om")){
			    		String referenciaDIV 	= "";
			    			String usuarioDIV 	= "";
			    		try {
			    			Trace.trace(Trace.Debug, "", "### Enviando neg_divisas_envio_om -> OpEnvioWsDivisas **** " + rs.getString("TLNE_IDORDEN") );
			    				
			    			try{
			    				referenciaDIV 	= ((String) rs.getString("TLNE_REFERENCIA")).substring(0, 8);
								usuarioDIV 		= ((String) rs.getString("TLNE_REFERENCIA")).substring(8);
			    				if(getValueAt("s_cod_logon")!=null && getValueAt("s_cod_usuarisc")!=null){
			    					referenciaDIV 	= (String) getValueAt("s_cod_logon");
									usuarioDIV 		= (String) getValueAt("s_cod_usuarisc");
			    				}
			    			}catch(Exception e){
			    				referenciaDIV 	= ((String) rs.getString("TLNE_REFERENCIA")).substring(0, 8);
								usuarioDIV 		= ((String) rs.getString("TLNE_REFERENCIA")).substring(8);
			    			}
			    			Trace.trace(Trace.Debug, "", "### OpEnvioWsDivisas.obtenerDatosWs() ref + usu " + referenciaDIV + usuarioDIV);
			    			boolean estadoObtenerDatos = false;
			    			String  numOperacionTMP = "", tipoOpe = (String) rs.getString("TLNE_BCODAUX1"), 
			    					numOpe = (String) rs.getString("TLNE_BASUNASO"), 
			    					numCuenta = (String) rs.getString("TLNE_BASUNPRO"), 
			    					numCliente = (String) rs.getString("TLNE_BPALACC2"); 
			    					
			    			Trace.trace(Trace.Debug, "", "### neg_divisas_envio_om Datos de entrada OK! - A " + numCuenta);
			    			numCuenta = numCuenta.substring(2);
			    			
			    			om = creaOM(sOmConsultaUnicaOperaciones);
							
			    			setTipoOperacion(tipoOpe.substring(0,1));
							//setNumeroCuenta(numCuenta);
			    			setNumeroCuenta(numCuenta);
							setNumeroUsuario(obtenerUsuario(referenciaDIV, usuarioDIV));
							numOperacionTMP = numOpe.replace("T", "");
							numOperacionTMP = numOperacionTMP.replace("H", "");
							setNumeroOperacion(numOperacionTMP);
							setSubTipoOperacion(tipoOpe.substring(2, 4));
							setNumeroCliente(numCliente);
							Trace.trace(Trace.Debug, "", "### neg_divisas_envio_om Datos de entrada OK! - B");
							
			    			kEntradaGiros = (KeyedCollection)getElementAt("divisas_consulta_unica_operaciones_om-data.entrada");
			    			kEntradaGiros.setValueAt("FECHIN", "");
			    			kEntradaGiros.setValueAt("FECHFN", "");
			    			kEntradaGiros.setValueAt("TIPOPER", getTipoOperacion());
			    			kEntradaGiros.setValueAt("NCUENT",  getNumeroCuenta());
			    			kEntradaGiros.setValueAt("NUMCLIE", getNumeroUsuario());
			    			
			    			Double idPaginaHost = new Double(getNumeroOperacion());
			    			kEntradaGiros.setValueAt("MOVIMT", idPaginaHost);
			    			Trace.trace(Trace.Debug, "", "### Om sOmConsultaUnicaOperaciones precargada");
			    			
			    			try {
//INI CMC - FUNCION DE NEGOCIO FNGU - PRUEBA - 17/09/2019
								controlOM = new OpControl();
								controlOM.setConfig("Divisas Firma",
										referencia2 + usuario2,
										usuario2,
			    						creaOM("sign_on_om"),
			    						creaOM("sign_on_om"),
			    						om, 
			    						creaOM(om.getName()));
			    				controlOM.control_f100();
//FIN CMC - FUNCION DE NEGOCIO FNGU - PRUEBA - 17/09/2019
								Trace.trace(Trace.Debug, "", "### Om sOmConsultaUnicaOperaciones ejecutada");
								copiarDeOMConsultaUnica();
								Trace.trace(Trace.Debug, "", "### Om sOmConsultaUnicaOperaciones Retorno formateado");
								copiarDeOpListaGirosOP();
								Trace.trace(Trace.Debug, "", "### copiarDeOpListaGirosOP cargados a PeticionWs");
								estadoObtenerDatos = peticionEnvioWs();
							} catch (Exception e) {
								ManejarExcepcion(3, "", "", "", e, "", this, "", "");
								kcOrden.setValueAt("error" , getValueAt("Error"));
								break;
//INI CMC - FUNCION DE NEGOCIO - PRUEBA - 19/09/2019
							} finally {
								controlOM = null;
							}
//FIN CMC - FUNCION DE NEGOCIO - PRUEBA - 19/09/2019
			    			
				    		if (estadoObtenerDatos){
				    			Trace.trace(Trace.Debug, "", "### Operacion " + rs.getString("TLNE_BASUNASO") + " enviada.");
				    		}else{
				    			//ejecutarOmRollback(rs, "NDV");
				    			Trace.trace(Trace.Debug, "", "### Operacion " + rs.getString("TLNE_BASUNASO") + " NO enviada.");
				    			kcOrden.setValueAt("error" , getValueAt("Error"));
				    			//INI incidencia 201 FX CMC 15/03/2019
				    			//break;
				    			continue;
				    			//FIN incidencia 201 FX CMC 15/03/2019
				    		}
						} catch (Exception e) {
							Trace.trace(Trace.Debug, "", "### Operacion " + rs.getString("TLNE_BASUNASO") + " NO enviada - Error Envio divisas OpMancomunadasMultiple_rs7. " + e);
							ManejarExcepcion(3, "", "", "", e, "", this, "", "");
							kcOrden.setValueAt("error" , getValueAt("Error"));
							break;
						} // FIN - GP13427 Reingenieria de Divisas
			    	} else if ("preinscrip_factura_pagatiempo_om".equals(nomom)) {

						if (modif.equals("V")) {
							opcion = "M";
						} else {
							opcion = "A";
						}

						String estado = rs.getString("TLNE_BINDAUX2");

						om.setValueAt("Entrada.BCODCONV", rs.getString("TLNE_BPALACC2"));
						om.setValueAt("Entrada.BCODFACT", rs.getString("TLNE_BPAGINAC"));
						om.setValueAt("Entrada.BDESFACT", rs.getString("TLNE_BCODCTAA"));
						om.setValueAt("Entrada.BFECINIC", rs.getString("TLNE_BFECHA01"));
						om.setValueAt("Entrada.BFECFINA", rs.getString("TLNE_BFECHA02"));
						om.setValueAt("Entrada.BDEEMAIL", rs.getString("TLNE_BASUNASO"));
						om.setValueAt("Entrada.BASUPRO1", rs.getString("TLNE_BASUNPRO"));
						//om.setValueAt("Entrada.BASUCUO1", cuotas1);
						om.setValueAt("Entrada.BASUPRO2", rs.getString("TLNE_BPALACCE"));
						//om.setValueAt("Entrada.BASUCUO2", cuotas2);
						om.setValueAt("Entrada.BASUPRO3", rs.getString("TLNE_BINDPAGI"));
						//om.setValueAt("Entrada.BASUCUO3", cuotas3);
						om.setValueAt("Entrada.BDIASANT", new Integer(rs.getInt("TLNE_BIMPOAUX")));
						om.setValueAt("Entrada.BMONTOMA", new Integer(rs.getInt("TLNE_BIMPORTE")));
						om.setValueAt("Entrada.BESTADOF", estado);
						om.setValueAt("Entrada.BOPCIONP", opcion);

						try {
							ejecutarOM(om);
						} catch (Exception e) {
							ManejarExcepcion(3, "", "", "", e, "", this, "", "");
							kcOrden.setValueAt("error" , getValueAt("Error"));
							continue;
						}
					} else if ("inscribir_om".equals(nomom) || "borrar_om".equals(nomom) || "transferir_om".equals(nomom)) {
						//ACH VYGTS 26/06/2014 
						om.setValueAt("Entrada.BCODACCC", rs.getString("TLNE_BCODACCC"));
						om.setValueAt("Entrada.BCODCTAA", rs.getString("TLNE_BCODCTAA"));
						om.setValueAt("Entrada.BASUNPRO", rs.getString("TLNE_BASUNPRO"));
						om.setValueAt("Entrada.BPALACCE", rs.getString("TLNE_BPALACCE"));
						om.setValueAt("Entrada.BASUNASO", rs.getString("TLNE_BASUNASO"));
						om.setValueAt("Entrada.BCODAUX1", rs.getString("TLNE_BCODAUX1"));
						om.setValueAt("Entrada.BPALACC2", rs.getString("TLNE_BPALACC2"));
						om.setValueAt("Entrada.BPAGINAC", rs.getString("TLNE_BPAGINAC"));
						om.setValueAt("Entrada.BCODAUX2", rs.getString("TLNE_BCODAUX2"));
						
						descripcion = (String) rs.getString("TLNE_DESCRIP");
						
						try {														
							ejecutarOM(om);
							stm2 = conn.prepareStatement(updateman);
							stm2.setString(1 , id_orden2);
							stm2.setString(2 , id_ordenPR);
							stm2.execute();
							Trace.trace(Trace.Debug, "************** EJECUTO LA OM 1 ACH ****************** ");
							
						} catch (Exception e) {
							ManejarExcepcion(3, "", "", "", e, "", this, "", "");
							kcOrden.setValueAt("error" , getValueAt("Error"));
							continue;
						}

					} 
			    	// CMC INICIO FX PSE
					else if ("consulta_pagos_pse_divisa_om".equals(nomom)) {
						EjecutarOmGestionPagosPse(rs.getString("TLNE_IDORDEN"));
						descripcion = (String) rs.getString("TLNE_DESCRIP");
						tlne_importe = rs.getDouble("TLNE_BIMPORTE");;
					}
			    	//CMC FIN FX PSE
					//CMC INICIO recaudo corporativo 24/03/2020
					else if ("recaudo_corporativo_om".equals(nomom)) {
						EjecutarOmRecaudoCoporativo(rs);
						descripcion = (String) rs.getString("TLNE_DESCRIP");
						tlne_importe = rs.getDouble("TLNE_BIMPORTE");;
					}
					//CMC FIN recaudo corporativo 24/03/2020
					else if (!"envio_fichero_om".equals(nomom)) {
						//ACH VYGTS 26/06/2014
						if (nomom.equals("inscribirm_om")) {

							descripcion = "Ins. Masiva";

							/*
							String nombreColaHost = "CN000000";
							String nomarchivo = "", cantidad = "", observaciones = "";
							ValidarAsuntosForm1 validar;
							Object importe;
							Double impo = new Double("0.00");
							int enviosHost = 0;
							boolean fla = true;
							boolean fla1 = true;
							int contador = 0;
							*/
							
							conn = ConsultaBD.getConexionBBVNet();
							stm1 = conn.prepareStatement(selectefi);
							stm1.setString(1, id_ordenPR);
							rs1 = stm1.executeQuery();
							
							om = creaOM(nomom);

							IndexedCollection icRegistrosFile = (IndexedCollection) getElementAt("inscribirm_om-data.Entrada.CUENTAS");
							int contRegs = 0;

							while (rs1.next()) {

								String valorLinea = rs1.getString("TLNE_RESTOARCH");
								String tipo = rs1.getString("TLNE_TIPREGIST");

								if ("1".equals(tipo)) {

									StringTokenizer a = new StringTokenizer(valorLinea, "$");
									KeyedCollection kcRegistroFile = (KeyedCollection) DataElement.getExternalizer().convertTagToObject(icRegistrosFile.getElementSubTag());
									kcRegistroFile.setValueAt("TIPBBVA", a.nextToken());
									kcRegistroFile.setValueAt("CUEBBVA", a.nextToken());
									kcRegistroFile.setValueAt("CODBANCO", a.nextToken());
									kcRegistroFile.setValueAt("TIPIDBEN", a.nextToken());
									kcRegistroFile.setValueAt("NUMID", a.nextToken());
									kcRegistroFile.setValueAt("DIGITOID", a.nextToken());
									kcRegistroFile.setValueAt("TIPCUNETA", a.nextToken());
									kcRegistroFile.setValueAt("CUENTA", a.nextToken());
									kcRegistroFile.setValueAt("NOMCTA", a.nextToken());
									kcRegistroFile.setValueAt("ACCION", a.nextToken());
									icRegistrosFile.addElement(kcRegistroFile);

									Trace.trace(Trace.VTF, "", "PRUEBAS 111 IF ");
									contRegs++;

								} else {
									om.setValueAt("Entrada.USUARIO", valorLinea.substring(0, 8));
									om.setValueAt("Entrada.NUM-REG", new Integer(contRegs));
									om.setValueAt("Entrada.REFERENCIA", valorLinea.substring(8));
									Trace.trace(Trace.VTF, "", "PRUEBAS 111 ELSE ");
								}
							}

							// NGE JCAG INICIO
							try {
								om.execute();
								Trace.trace(Trace.VTF, "", "EJECUTO LA OM");
							} catch (Exception e) {
								ManejarExcepcion(3, "", "", "", e, "", this, "", "");
								kcOrden.setValueAt("error" , getValueAt("Error"));
								continue;
							}
							// setValueAt("REGNK", om.getValueAt("Salida.REGNK"));
							// setValueAt("REGOK", om.getValueAt("Salida.REGOK"));

						} else {
							//INI Inc 114 Prestamos pendiente de envío CMC - 27/03/2018
							String TLNE_BCODACCC = "", TLNE_BCODCTAA = "", TLNE_BPALACCE = "", TLNE_BPALACC2 = "", TLNE_BASUNPRO = "", TLNE_BASUNASO = "", TLNE_BINDPAGI = "", TLNE_BINDAUX2 = "", TLNE_BCODAUX1 = "", TLNE_BCODAUX2 = "", TLNE_BPAGINAC = "";
							Double TLNE_BIMPORTE = 0.0, TLNE_BIMPOAUX = 0.0, TLNE_BNUMAUX1 = 0.0,TLNE_BNUMAUX2 = 0.0;
							//FIN Inc 114 Prestamos pendiente de envío CMC - 27/03/2018

							om.setValueAt("Entrada.BCODACCC", rs.getString("TLNE_BCODACCC"));
							om.setValueAt("Entrada.BCODCTAA", rs.getString("TLNE_BCODCTAA"));
							om.setValueAt("Entrada.BPALACCE", rs.getString("TLNE_BPALACCE"));
							om.setValueAt("Entrada.BPACACC2", rs.getString("TLNE_BPALACC2"));

							//GP7353 Envío Automático de Archivo de Adquirencia - GLOKAL - NBC - Octubre 11 de 2013 - INICIO
							if (nomom.equals("correos_adquirencia_om")) {
								String BASUNPRO = rs.getString("TLNE_BASUNPRO");
								om.setValueAt("Entrada.BASUNPRO", BASUNPRO.length() > 20 ? BASUNPRO.substring(2) : BASUNPRO);
							} else {
								om.setValueAt("Entrada.BASUNPRO", rs.getString("TLNE_BASUNPRO"));
								Trace.trace(Trace.Debug, "----------->1111111 *****");
								//INI Inc 66 - Credito Virtual CMC - 15/01/2018
								Trace.trace(Trace.Debug, "----------->Entro al else de la linea 413 *****");
								//FIN Inc 66 - Credito Virtual CMC - 15/01/2018
							}

							if (nomom.equals("cuprot_pagos_om")) {
								//INI Inc 66 - Credito Virtual CMC - 15/01/2018
								Trace.trace(Trace.Debug, "----------->Entro al if de la linea 421 *****");
								//FIN Inc 66 - Credito Virtual CMC - 15/01/2018
								String BASUNPRO = rs.getString("TLNE_BASUNASO");
								om.setValueAt("Entrada.BASUNASO", BASUNPRO.length() > 20 ? BASUNPRO.substring(2) : BASUNPRO);
								om.setValueAt("Entrada.BASUNPRO", rs.getString("TLNE_BASUNPRO"));
								Trace.trace(Trace.Debug, "----------->1111111 *****");
							}
							//INI Inc 66 - Credito Virtual CMC - 15/01/2018
							Trace.trace(Trace.Debug, "----------->dato del ResultSet TLNE_BASUNASO = " + rs.getString("TLNE_BASUNASO") + " *****");
							//FIN Inc 66 - Credito Virtual CMC - 15/01/2018
							
							//GP7353 Envío Automático de Archivo de Adquirencia - GLOKAL - NBC - Octubre 11 de 2013 - FIN		
							om.setValueAt("Entrada.BASUNASO", rs.getString("TLNE_BASUNASO"));
							//INI CAMBIOS  PSE - CMC 22/04/2019
							String fecha1 = rs.getString("TLNE_BFECHA01");
							String fecha2 = rs.getString("TLNE_BFECHA02");
							if(nomom.equals("gest_pgs_pse_crea_pago_om")){
								SimpleDateFormat sdfPSE = new SimpleDateFormat("yyyyMMdd");
								if (!"".equals(fecha1)) {
									Date fecha_1 = sdfPSE.parse(fecha1);
									om.setValueAt("Entrada.BFECHA01", fecha_1);
								}		
								if (!"".equals(fecha2)) {
									Date fecha_2 = sdfPSE.parse(fecha2);
									om.setValueAt("Entrada.BFECHA02", fecha_2);
								}
							} else {
								if (!"".equals(fecha1)) {
									om.setValueAt("Entrada.BFECHA01", fecha1);
								}		
								if (!"".equals(fecha2)) {
									om.setValueAt("Entrada.BFECHA02", fecha2);
								}
							}
							//FIN CAMBIOS  PSE - CMC 22/04/2019
							
							//INI Inc 66 - Credito Virtual CMC - 15/01/2018
							Trace.trace(Trace.Debug, "----------->Despues del If de fechas = " + nomom + " *****");
							/* Incidencia 278 Pago prestamos Importe */
						   
							conn2 = ConsultaBD.getConexion("BDMexico");
							stm_cab = conn2.prepareStatement(SELECTCAB);
							stm_cab.setString(1, id_ordenPR);
							double valorImporte=0.0;
							try{
								rsCAB = stm_cab.executeQuery();
								if(rsCAB.next()) {
									 valorImporte = rsCAB.getDouble("QTY_TOTIMPOR");
								}
							Trace.trace(Trace.Information, "----------->Valor Importe= "+ valorImporte + " *****");
							}
							catch(Exception e){
								Trace.trace(Trace.Information, "----------->Ingresa al catch, Error = "+ e.getMessage() + " *****");
							}
							om.setValueAt("Entrada.BIMPORTE", valorImporte);
							/* Incidencia 278 Pago prestamos Importe */
							om.setValueAt("Entrada.BIMPOAUX", new Double(rs.getDouble("TLNE_BIMPOAUX")));
							Trace.trace(Trace.Debug, "----------->Despues del importe y el importe AUX - nombreOM = " + nomom + " *****");

							//INI Inc 66 - CreditoVirtual CMC - 01/02/2018
							/*if (nomom.equals("retorno_trasbbvafam_om") || nomom.equals("pago_prestamos_propios_realizar_cl_om") || nomom.equals("pago_prestamos_propios_realizar_om") || nomom.equals("pago_prestamos_terceros_realizar_om") || nomom.equals("retorno_trasbd_om") || nomom.equals("retorno_trasfe_om") || nomom.equals("prestamos_disposicion_credito_liquido2_om") || nomom.equals("prestamos_disposicion_credito_liquido_om") || nomom.equals("prestamos_disposicion_credito_liquido1_om")
									|| nomom.equals("retorno_cdts_om") || nomom.equals("fondo_plazo_sucr_om") || nomom.equals("lib_retorno_tras_om") || nomom.equals("correos_adquirencia_om") || nomom.equals("fondo_plazo_reem1_om") || nomom.equals("cuprot_disposiciones_om") || nomom.equals("prestamos_disposicion_credito_virtual2_om")) {*/
							if (nomom.equals("retorno_trasbbvafam_om") || nomom.equals("pago_prestamos_propios_realizar_cl_om") || nomom.equals("pago_prestamos_propios_realizar_om") || nomom.equals("pago_prestamos_terceros_realizar_om") || nomom.equals("retorno_trasbd_om") || nomom.equals("retorno_trasfe_om") || nomom.equals("prestamos_disposicion_credito_liquido2_om") || nomom.equals("prestamos_disposicion_credito_liquido_om") || nomom.equals("prestamos_disposicion_credito_liquido1_om") || 
								nomom.equals("retorno_cdts_om") || nomom.equals("fondo_plazo_sucr_om") || nomom.equals("lib_retorno_tras_om") || nomom.equals("correos_adquirencia_om") || nomom.equals("fondo_plazo_reem1_om") || nomom.equals("cuprot_disposiciones_om") || nomom.equals("prestamos_disposicion_credito_virtual2_om")  || nomom.equals("prestamos_disposicion_credito_virtual1_om") || nomom.equals("prestamos_disposicion_credito_virtual_om") || nomom.equals("prestamos_disposicion_credito_virtual2_om_rs7") || 
								nomom.equals("prestamos_disposicion_credito_virtual1_om_rs7") || nomom.equals("prestamos_disposicion_credito_virtual_om_rs7") || nomom.equals("gest_pgs_pse_crea_pago_om")) // CAMBIOS GESTION DE PAGOS PSE - CMC 22/04/2019
							{
							//FIN Inc 66 - CreditoVirtual CMC - 01/02/2018	
								om.setValueAt("Entrada.BINDPAGI", rs.getString("TLNE_BINDPAGI"));
								Trace.trace(Trace.Debug, "----------->Entro al if - nombreOM = " + nomom + " *****");
							} else {
								om.setValueAt("Entrada.BINDPAGE", rs.getString("TLNE_BINDPAGI"));
								Trace.trace(Trace.Debug, "-----------> 222222 *****");
							}
							//FIN Inc 66 - Credito Virtual CMC - 15/01/2018

							int flag = 0;
							om.setValueAt("Entrada.BINDAUX1", rs.getString("TLNE_BINDAUX1"));

							if (nomom.equals("pago_prestamos_propios_realizar_cl_om") || nomom.equals("pago_prestamos_propios_realizar_om") || nomom.equals("pago_prestamos_terceros_realizar_om")) {
								om.setValueAt("Entrada.BINDAUX1", "S");
								flag = 1;
							}

							om.setValueAt("Entrada.BINDAUX2", rs.getString("TLNE_BINDAUX2"));
							om.setValueAt("Entrada.BCODAUX1", rs.getString("TLNE_BCODAUX1"));
							om.setValueAt("Entrada.BCODAUX2", rs.getString("TLNE_BCODAUX2"));
							om.setValueAt("Entrada.BNUMAUX1", new Double(rs.getDouble("TLNE_BNUMAUX1")));
							om.setValueAt("Entrada.BNUMAUX2", new Double(rs.getDouble("TLNE_BNUMAUX2")));
							om.setValueAt("Entrada.BPAGINAC", rs.getString("TLNE_BPAGINAC"));

							if (nomom.equals("cuprot_pagos_om") || nomom.equals("cuprot_disposiciones_om")) {

								om.setValueAt("Entrada.BASUNPRO", rs.getString("TLNE_BCODCTAA"));
								Trace.trace(Trace.Debug, "----------->Resutado Entrada.BASUNPRO: " + om.getValueAt("Entrada.BASUNPRO"));

								om.setValueAt("Entrada.BASUNASO", rs.getString("TLNE_BASUNPRO"));
								Trace.trace(Trace.Debug, "----------->Resutado TLNE_BASUNASO: " + om.getValueAt("Entrada.BASUNASO"));

								om.setValueAt("Entrada.BCODAUX1", "P");
								Trace.trace(Trace.Debug, "----------->Resutado TLNE_BCODAUX1: " + om.getValueAt("Entrada.BCODAUX1"));

								SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

								if (!"".equals(fecha1)) {
									Date envioFecha = sdf.parse(fecha1);
									om.setValueAt("Entrada.BFECHA01", envioFecha);
								}
								Trace.trace(Trace.Debug, "----------->Resutado TLNE_BFECHA01: " + om.getValueAt("Entrada.BFECHA01"));
							}
							//INI Inc 114 Prestamos pendiente de envío CMC - 27/03/2018
							try
							{
								nomom = rs.getString("TLNE_NOMOM");
								Trace.trace(Trace.Debug, "----------->Resutado 114 nomom: " + nomom);
								TLNE_BCODACCC = (String) rs.getString("TLNE_BCODACCC");
								Trace.trace(Trace.Debug, "----------->Resutado 114 TLNE_BCODACCC: " + TLNE_BCODACCC);
								TLNE_BCODCTAA = (String) rs.getString("TLNE_BCODCTAA");
								Trace.trace(Trace.Debug, "----------->Resutado 114 TLNE_BCODCTAA: " + TLNE_BCODCTAA);
								TLNE_BPALACCE = (String) rs.getString("TLNE_BPALACCE");
								Trace.trace(Trace.Debug, "----------->Resutado 114 TLNE_BPALACCE: " + TLNE_BPALACCE);
								TLNE_BPALACC2 = (String) rs.getString("TLNE_BPALACC2");
								Trace.trace(Trace.Debug, "----------->Resutado 114 TLNE_BPALACC2: " + TLNE_BPALACC2);
								TLNE_BASUNPRO = (String) rs.getString("TLNE_BASUNPRO");
								Trace.trace(Trace.Debug, "----------->Resutado 114 TLNE_BASUNPRO: " + TLNE_BASUNPRO);
								TLNE_BASUNASO = (String) rs.getString("TLNE_BASUNASO");
								Trace.trace(Trace.Debug, "----------->Resutado 114 TLNE_BASUNASO: " + TLNE_BASUNASO);
								TLNE_BIMPORTE = valorImporte; //Correcion Incidencia 278 Pago Prestamos
								Trace.trace(Trace.Information, "----------->Resutado 114 TLNE_BIMPORTE decimales: " + TLNE_BIMPORTE);
								TLNE_BIMPOAUX = new Double(rs.getDouble("TLNE_BIMPOAUX"));
								Trace.trace(Trace.Debug, "----------->Resutado 114 TLNE_BIMPOAUX: " + TLNE_BIMPOAUX);
								TLNE_BINDPAGI = (String) rs.getString("TLNE_BINDPAGI");
								Trace.trace(Trace.Debug, "----------->Resutado 114 TLNE_BINDPAGI: " + TLNE_BINDPAGI);
								TLNE_BINDAUX2 = (String) rs.getString("TLNE_BINDAUX2");
								Trace.trace(Trace.Debug, "----------->Resutado 114 TLNE_BINDAUX2: " + TLNE_BINDAUX2);
								TLNE_BCODAUX1 = (String) rs.getString("TLNE_BCODAUX1");
								Trace.trace(Trace.Debug, "----------->Resutado 114 TLNE_BCODAUX1: " + TLNE_BCODAUX1);
								TLNE_BCODAUX2 = (String) rs.getString("TLNE_BCODAUX2");
								Trace.trace(Trace.Debug, "----------->Resutado 114 TLNE_BCODAUX2: " + TLNE_BCODAUX2);
								TLNE_BNUMAUX1 = new Double(rs.getDouble("TLNE_BNUMAUX1"));
								Trace.trace(Trace.Debug, "----------->Resutado 114 TLNE_BNUMAUX1: " + TLNE_BNUMAUX1);
								TLNE_BNUMAUX2 = new Double(rs.getDouble("TLNE_BNUMAUX2"));
								Trace.trace(Trace.Debug, "----------->Resutado 114 TLNE_BNUMAUX2: " + TLNE_BNUMAUX2);
								TLNE_BPAGINAC = (String) rs.getString("TLNE_BPAGINAC");
								Trace.trace(Trace.Debug, "----------->Resutado 114 TLNE_BPAGINAC: " + TLNE_BPAGINAC);
								
								fecha1 = rs.getString("TLNE_BFECHA01");
								Trace.trace(Trace.Debug, "----------->Resutado 114 fecha1: " + fecha1);
								fecha2 = rs.getString("TLNE_BFECHA02");
								Trace.trace(Trace.Debug, "----------->Resutado 114 fecha2: " + fecha2);
								//INI IN-688 FONFOD PLAZO 30 TORRE 30-04-2020
								if (null!= fecha1 && !"".equals(fecha1) && null!=nomom && nomom.equals("fondo_plazo_reem1_om")) {
									Date fechaFondo30 = convertDateFechaFondo(fecha1);
									Trace.trace(Trace.Debug, "----------->Resutado 114 fechaFondo30: " + fechaFondo30);
									om.setValueAt("Entrada.BFECHA01", fechaFondo30);
								}
								//FIN IN-688 FONFOD PLAZO 30 TORRE 30-04-2020								

								//INI IN-838 RECAUDO UNE TORRE 30-06-2020 Se realiza validacion si el pago es de la Web o Movil
								if (null!=nomom && nomom.equals("retorno_pserv_om_rs7")) {
									String idOrdenConsulta = (String) rs.getString("TLNE_IDORDEN");
									Trace.trace(Trace.Debug, "----------->Entra a consulta de Factura, idOrdenConsulta: " + idOrdenConsulta);
									boolean existeEnMovil= existeFUAMovil(idOrdenConsulta);
									if(existeEnMovil){
										om.setValueAt("Entrada.BINDAUX2", "M");
									}
									Trace.trace(Trace.Debug, "----------->resultado consulta existeEnMovil: " + existeEnMovil);
								}
								//FIN IN-838 RECAUDO UNE TORRE 30-06-2020
							}
							catch(Exception ex114)
							{
								flag = 0;
								Trace.trace(Trace.Debug, "Error al obtener nombre de OM para prestamos e: " + ex114 + " Message: " + ex114.getMessage());
							}
							//FIN Inc 114 Prestamos pendiente de envío CMC - 27/03/2018

							descripcion = (String) rs.getString("TLNE_DESCRIP");
							tlne_importe = TLNE_BIMPORTE;

							try {
								//INI IN-462 LIBRANZA CMC 07-02-2020
								controlOM = new OpControl();
								OperacionMulticanal opMulOm=creaOM("sign_on_om");
								controlOM.setConfig("Pago Libranza",referencia2 + usuario2,usuario2,opMulOm,opMulOm,om,creaOM(om.getName()));
								controlOM.control_f100();
								//ejecutarOM(om);
								//FIN IN-462 LIBRANZA CMC 07-02-2020
								
								stm2 = conn.prepareStatement(updateman);
								stm2.setString(1 , id_orden2);
								stm2.setString(2 , id_ordenPR);
								stm2.execute();
								Trace.trace(Trace.Debug, "************** EJECUTO LA OM 1  ****************** ");
							} catch (Exception e) {
								stm = conn.prepareStatement(updateman2);
								stm.setString(1 , id_ordenPR);
								stm.setString(2 , id_orden2);
								stm.execute();
								Trace.trace(Trace.Debug, "************** QUERYYY 2  ****************** " + updateman2);
								ManejarExcepcion(3, "", "", "", e, "", this, "", "");
								kcOrden.setValueAt("error" , getValueAt("Error"));
								continue;
							}//INI IN-462 LIBRANZA CMC 07-02-2020
							finally {
								controlOM = null;
							}
							//FIN IN-462 LIBRANZA CMC 07-02-2020

							if (flag == 1) {
								//INI Inc 114 Prestamos pendiente de envío CMC - 27/03/2018
								//nomom = rs.getString("TLNE_NOMOM");
								om = creaOM(nomom);
								om.setValueAt("Entrada.BCODACCC", TLNE_BCODACCC);
								om.setValueAt("Entrada.BCODCTAA", TLNE_BCODCTAA);
								om.setValueAt("Entrada.BPALACCE", TLNE_BPALACCE);
								om.setValueAt("Entrada.BPACACC2", TLNE_BPALACC2);
								om.setValueAt("Entrada.BASUNPRO", TLNE_BASUNPRO);
								om.setValueAt("Entrada.BASUNASO", TLNE_BASUNASO);
								
								//fecha1 = rs.getString("TLNE_BFECHA01");

								if (!"".equals(fecha1)) {
									om.setValueAt("Entrada.BFECHA01", fecha1);
								}

								//fecha2 = rs.getString("TLNE_BFECHA02");

								if (!"".equals(fecha2)) {
									om.setValueAt("Entrada.BFECHA02", fecha2);
								}
								
								om.setValueAt("Entrada.BIMPORTE", TLNE_BIMPORTE);
								om.setValueAt("Entrada.BIMPOAUX", TLNE_BIMPOAUX);

								if (nomom.equals("retorno_trasbbvafam_om") || nomom.equals("pago_prestamos_propios_realizar_cl_om") || nomom.equals("pago_prestamos_propios_realizar_om") || nomom.equals("pago_prestamos_terceros_realizar_om") || nomom.equals("retorno_trasbd_om") || nomom.equals("retorno_trasfe_om")) {
									om.setValueAt("Entrada.BINDPAGI", TLNE_BINDPAGI);
								} else {
									om.setValueAt("Entrada.BINDPAGE", TLNE_BINDPAGI);
								}

								om.setValueAt("Entrada.BINDAUX1", "P");
								om.setValueAt("Entrada.BINDAUX2", TLNE_BINDAUX2);
								om.setValueAt("Entrada.BCODAUX1", TLNE_BCODAUX1);
								om.setValueAt("Entrada.BCODAUX2", TLNE_BCODAUX2);
								om.setValueAt("Entrada.BNUMAUX1", TLNE_BNUMAUX1);
								om.setValueAt("Entrada.BNUMAUX2", TLNE_BNUMAUX2);
								om.setValueAt("Entrada.BPAGINAC", TLNE_BPAGINAC);

								//FIN Inc 114 Prestamos pendiente de envío CMC - 27/03/2018
								try {
									ejecutarOM(om);
								} catch (Exception e) {
									ManejarExcepcion(3, "", "", "", e, "", this, "", "");
									kcOrden.setValueAt("error" , getValueAt("Error"));
									continue;
								}
							}
						}
					} else {

						tlne_importe = rs.getDouble("TLNE_BIMPORTE");
						descripcion = "EFIPAGOS";

						String nombreColaHost = "CN000000";
						
						int enviosHost = 0;
						conn = ConsultaBD.getConexionBBVNet();

						stm1 = conn.prepareStatement(selectefi);
						stm1.setString(1, id_ordenPR);

						rs1 = stm1.executeQuery();
						IndexedCollection icRegistrosFile = (IndexedCollection) getElementAt("envio_fichero_om-data.Entrada.ESTARCHIV");

						int numRegsReales = 0;
						boolean fla = true;
						int contador = 0;

						while (fla) {
							int contRegs = 0;
							icRegistrosFile.removeAll();
							fla = rs1.next();
							while (contRegs < 80 && fla) {

								// String valorLinea = rs1.getString("TLNE_RESTOARCH");
								// String valorCodigo = valorLinea.substring(19, 22);

								KeyedCollection kcRegistroFile = (KeyedCollection) DataElement.getExternalizer().convertTagToObject(icRegistrosFile.getElementSubTag());
								kcRegistroFile.setValueAt("TIPREGIST", rs1.getString("TLNE_TIPREGIST"));
								kcRegistroFile.setValueAt("RESTOARCH", rs1.getString("TLNE_RESTOARCH"));

								if (rs1.getString("TLNE_TIPREGIST") != null && !rs1.getString("TLNE_TIPREGIST").equals("")) {
									icRegistrosFile.addElement(kcRegistroFile);
								}

								contRegs++;
								numRegsReales++;

								if (contRegs < 80) {
									fla = rs1.next();
								}
							}

							if (!fla) {
								om.setValueAt("Entrada.INDPAGINA", "N");
							} else {
								om.setValueAt("Entrada.INDPAGINA", "S");
							}

							String strNumRegsReales = Integer.toString(numRegsReales++);

							while (strNumRegsReales.length() < 3) {
								strNumRegsReales = "0" + strNumRegsReales;
							}
							enviosHost += 1;
							nombreColaHost = (String) getValueAt("id_sesion");
							String datosLibres = Integer.toString(enviosHost) + strNumRegsReales + nombreColaHost;
							om.setValueAt("Entrada.CAMPLIBRE", datosLibres);
							om.setValueAt("Entrada.IMPORTE TOTAL", new Double(0));

							try {
								om.execute();
								contador++;
								if (contador > 5) {
									fla = false;
								}
							} catch (Exception e) {
								ManejarExcepcion(3, "", "", "", e, "", this, "", "");
								kcOrden.setValueAt("error" , getValueAt("Error"));
								continue;

							}
							//NGE  JCAG FIN
							Trace.trace(Trace.Debug, "----------->llegamos al final");
							nombreColaHost = (String) om.getValueAt("Salida.FECOPER");
							Trace.trace(Trace.Debug, "----------->llegamos al final222");
						}
					}

					kcOrden.setValueAt("fechaOperacion" , formatoFecha.format(Calendar.getInstance().getTime()));
					kcOrden.setValueAt("descripcion"	, descripcion);
					kcOrden.setValueAt("importePago"	, new Double(tlne_importe));
					kcOrden.setValueAt("error" , "");
					
					
					try {
						if (stm != null) {
							stm.close();
						}
					} catch (SQLException aSQLExcII) {
						String msjError = aSQLExcII.getSQLState();
						Trace.trace(Trace.Debug, "Error en Base de Datos: " + msjError, aSQLExcII.toString());
					}
					//DESARROLLO IATA
					if (Conve.equals("0010032393000")){
						Trace.trace(Trace.Debug, "----------->ENTRO REF IATA");
						long NumMov = (Long) om.getValueAt("Salida.REFCARGO");
						String Nummov2= String.valueOf(NumMov);
						Trace.trace(Trace.Debug, "----------->NUMOV"+Nummov2);
						stmIA = conn2.prepareStatement(updatecabIA);
						stmIA.setString(1 , Nummov2);
						stmIA.setString(2 , id_ordenPR);
						Trace.trace(Trace.Debug, "----------->EXECUTE"+stmIA);
						stmIA.execute();
						stmIA.close();
					}
							
					Trace.trace(Trace.Debug, "----------->llegamos al final33333");
					
					stm = conn.prepareStatement(deleteman);
					stm.setString(1, id_ordenPR);
					stm.execute();
					Trace.trace(Trace.Debug, "Se borró orden de TLNE.TTLNEMAN " + id_ordenPR);
					
					Trace.trace(Trace.Debug, "-----------> deleteman2 inicio");
					stm2 = conn.prepareStatement(deleteman2);
					stm2.setString(1 , id_orden2);
					stm2.execute();
					Trace.trace(Trace.Debug, "-----------> deleteman2 finale");
					
					try {
						if (stm != null) {
							stm.close();
						}
					} catch (SQLException aSQLExcII) {
						Trace.trace(Trace.Debug, "Error en Base de Datos: " + aSQLExcII.getSQLState() , aSQLExcII.getMessage());
					}
					
					try {
						Trace.trace(Trace.Debug, "----------->llegamos al final44444444");
						if	(conn != null && !conn.isClosed()) {
							conn.close();
						}
					} catch (SQLException aSQLExcIII) {
						Trace.trace(Trace.Debug, "Error en Base de Datos: " + aSQLExcIII.getSQLState() , aSQLExcIII.getMessage());
					}
					
					Trace.trace(Trace.Debug, "----------->llegamos al final555555555");
					
					conn = ConsultaBD.getConexion("BDMexico");
					stm = conn.prepareStatement(updatecab);
					stm.setString(1 , id_ordenPR);
					
					try {
						stm.execute();
						Trace.trace(Trace.Debug , "-----------> Update CAB idOrden: " + id_ordenPR);
					} catch (Exception e3) {
						Trace.trace(Trace.Debug , "-----------> Excepcion al lanzar Update CAB");
						stm = conn.prepareStatement(deletecab);
						stm.setString(1 , id_ordenPR);
						stm.execute();
					}

					try {
						
						//INI INC 325 CMC 10-01-2019
						String vClaseOrden= (String) getValueAt("codClaseOrd");
						Trace.trace(Trace.Debug , "-----------> INC 325 vClaseOrden: " + vClaseOrden);
						if(null==vClaseOrden){
							stm = conn.prepareStatement(deletecor);
							stm.setString(1 , id_ordenPR);
							stm.execute();
							Trace.trace(Trace.Debug , "-----------> Delete COR idOrden: " + id_ordenPR);
						}
						//FIN INC 325 CMC 10-01-2019
						
					} catch (Exception e4) {
						Trace.trace(Trace.Debug , "-----------> Excepcion al lanzar Delete COR");
					}
				}

			} catch (SQLException aSQLExc) {
				Trace.trace(Trace.Debug , "Error en Base de Datos: " + aSQLExc.getSQLState() , aSQLExc.getMessage());
			} catch (Exception e) {
				Trace.trace(Trace.Debug , "Error General: " + e.getMessage());
			} finally {

				iCordenes.addElement(kcOrden);
				
				try {
					// INI IN-69 CCMC 06/11/2019 Se retira ajuste de la incidencia INI INC CMC COL567819I00114
					Trace.trace(Trace.Debug , "Bloque Final");
					if (stm != null) {
						stm.close();
					}
					if (stm1 != null) {
						stm1.close();
					}
					if (stm2 != null) {
						stm2.close();
					}
					if (stm_cab != null) {
						stm_cab.close();
					}
					if (conn != null && !conn.isClosed()) {
						conn.close();
					}
					if(conn2 != null && !conn2.isClosed()){
						conn2.close();
					}
				} catch (SQLException aSQLExcII) {
					Trace.trace(Trace.Debug, "Error en Base de Datos: " + aSQLExcII.getSQLState() , aSQLExcII.getMessage());
				}
			}
		}
    }

	/**
	 * @param fecha1
	 * @return
	 * @throws NumberFormatException
	 */
	
	private static final Date convertDateFechaFondo(String fecha1){
		Date dateRepresentation =new Date();
		try {
            int anyoInt = Integer.parseInt(fecha1.substring(0, 4));
            int mesInt = Integer.parseInt(fecha1.substring(4, 6));
            int diaInt = Integer.parseInt(fecha1.substring(6, 8));
            
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, anyoInt);
            cal.set(Calendar.MONTH, mesInt-1);
            cal.set(Calendar.DAY_OF_MONTH, diaInt);
            dateRepresentation = cal.getTime();
		}
		catch(Exception ex) {
			Trace.trace(Trace.Error,"Error transformar string a n\u00FAmerico en"+fecha1);
		}
		return dateRepresentation;
	}
	
	// INI - GP13427 Reingenieria de Divisass
	private String getNumeroUsuario() {
		return numeroUsuario;
	}

	private void setNumeroUsuario(String numeroUsuario) {
		this.numeroUsuario = numeroUsuario;
	}
	
	private String getNumeroCliente() {
		return numeroCliente;
	}

	private void setNumeroCliente(String numeroCliente) {
		this.numeroCliente = numeroCliente;
	}

	private String getNumeroCuenta() {
		return numeroCuenta;
	}


	private void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	private String getNumeroOperacion() {
		return numeroOperacion;
	}

	private void setNumeroOperacion(String numeroOperacion) {
		this.numeroOperacion = numeroOperacion;
	}

	private String getTipoOperacion() {
		return tipoOperacion;
	}

	private void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}
	
	private String getSubTipoOperacion() {
		return subTipoOperacion;
	}

	private void setSubTipoOperacion(String subTipoOperacion) {
		this.subTipoOperacion = subTipoOperacion;
	}
	
	public String obtenerUsuario (String referencia, String codUsuario){
		
		Trace.trace(Trace.Debug, "", "Inicio obtenerUsuario()  WS Divisas");
		String nCliente = "";
		try {
			Trace.trace(Trace.Debug, "", "### OPEnvioWSDivisas " + referencia + codUsuario);
			
			for (int i = codUsuario.length(); i<8; i++){ 
				codUsuario = codUsuario + "X"; 
			}
			nCliente = referencia + codUsuario + "0000000NC";
			
		} catch (Exception e) {
			Trace.trace(Trace.Debug, "", "### Error al formatear código de usuario/referencia  OPEnvioWSDivisas.java ****" + e);
		}
		Trace.trace(Trace.Debug, "", "Fin obtenerUsuario() WS Divisas ****");
		return nCliente;
	}
	
	private void copiarDeOMConsultaUnica() throws Exception {
		IndexedCollection listaGirosOM;
		int idEstado ;
		
		try {
			Trace.trace(Trace.Debug, "", " Inicio copiarDeOMConsultaUnica().");
			
			listaGirosOM = (IndexedCollection) getElementAt("divisas_consulta_unica_operaciones_om-data.salida.listaGiros");
			
			Trace.trace(Trace.Debug, "", "### copiarDeOMConsultaUnica() - registros eliminados / Cant Registros OM: " + listaGirosOM.size());
			
			//Se eliminan registros que vienen vacios
				kGiro =(KeyedCollection) listaGirosOM.getElementAt(0);
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
					//INI INC FX 182A 16/04/2019
					String numAvance= String.valueOf(kGiro.getValueAt("NUMAVA"));
					int longitudAvance = numAvance.length();
					if(longitudAvance > 10 && !numAvance.trim().equalsIgnoreCase("STD1")){
						numAvance = numAvance.substring(longitudAvance - 10);
						kGiro.setValueAt("NUMAVA", numAvance);						
					}
					//FIN INC FX 182A 16/04/2019
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
					if(getTipoOperacion().equals("T")){
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
								kGiro.setValueAt("ESTADO", "Proceso Banco");
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
					}else if(getTipoOperacion().equals("H")){
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
								kGiro.setValueAt("ESTADO", "Proceso Banco");
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
					
					if(monto.compareTo("null")!=0)
					kGiro.setValueAt("MONTO", monto);//INC FX IN 318 AMALFI 17-12-2019 Control para envio de monto a Banktrade String
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
					Trace.trace(Trace.Debug, "", "### ORDENA   =" + kGiro.getValueAt("ORDENA"));
					Trace.trace(Trace.Debug, "", "### BENEFIC  =" + kGiro.getValueAt("BENEFIC"));
					Trace.trace(Trace.Debug, "", "### BANBENE  =" + kGiro.getValueAt("BANBENE"));
					Trace.trace(Trace.Debug, "", "### PAIS     =" + kGiro.getValueAt("PAIS"));
					Trace.trace(Trace.Debug, "", "### CODSWIFT =" + kGiro.getValueAt("CODSWIFT"));
					Trace.trace(Trace.Debug, "", "### CODABBA  =" + kGiro.getValueAt("CODABBA"));
					Trace.trace(Trace.Debug, "", "### ESTADO   =" + kGiro.getValueAt("ESTADO"));
					Trace.trace(Trace.Debug, "", "### BENEFI   =" + kGiro.getValueAt("BENEFI"));
					Trace.trace(Trace.Debug, "", "### ORDEN    =" + kGiro.getValueAt("ORDEN"));
					Trace.trace(Trace.Debug, "", "### MOVIMT   =" + kGiro.getValueAt("MOVIMT"));
					Trace.trace(Trace.Debug, "", "### CUENTABON=" + kGiro.getValueAt("CUENTABON"));
					Trace.trace(Trace.Debug, "", "### BANCOINT =" + kGiro.getValueAt("BANCOINT"));
					Trace.trace(Trace.Debug, "", "############# ----- #############");
					
					Trace.trace(Trace.Debug, "", "### copiarDeOMConsultaUnica() **** Datos cargados al Ws");
					
			Trace.trace(Trace.Debug, "", " Fin copiarDeOMConsultaUnica()");
		} catch (Exception e) {;
			Trace.trace(Trace.Debug, "", "### ERROR copiarDeOMConsultaUnica() - copiando registros " + e);
			e.printStackTrace();
		}
		
	}
	
	public void copiarDeOpListaGirosOP() throws Exception {
		Trace.trace(Trace.Debug, "", " Inicio copiarDeOpListaGirosOP() ++ Cargar variables de operacion");
		peticion = new RequestBankTradeService();
		
		Trace.trace(Trace.Debug, "", "### copiarDeOpListaGirosOP() ++ PeticionWebService loaded ");
		
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
		Trace.trace(Trace.Debug, "", "### copiarDeOpListaGirosOP() - Asignacion de valores fijos Peticion Ws ");

		peticion.setIndMoneda("");//Pendiente por definir
		//peticion.setID_BCOOrdenante("SYSTEM");
		peticion.setID_BCOOrdenante(null);
		//peticion.setNomBcoOrd1("SYSTEM");
		peticion.setNomBcoOrd1(null);
		peticion.setIdSwTpBcoOrd("SW");
		//peticion.setCampana("CANALES");
		peticion.setCampana(null);
		//peticion.setTyVended("9");
		peticion.setTyVended(null);
		//peticion.setNoVended("777777777777777");
		//peticion.setDigVended("9");
		peticion.setNoVended(null);
		peticion.setDigVended(null);
		
		peticion.setNoCtaComm(getNumeroCuenta());
		peticion.setDeComm(tipoCuentaBT(getNumeroCuenta()));
		peticion.setNoCtaPrincipal(getNumeroCuenta());
		peticion.setNoCtaPrincipalCre(getNumeroCuenta());//INC BT15.1 PRD FX 05-07-2019
		
		//peticion.setIdSucursal(BT_IDSUCURSAL); 
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
			Trace.trace(Trace.Debug, "", "### setNuCambiario: NULL/NO TRAE: valor no asignado");
		}
		peticion.setSubcanal(null);
		
		peticion.setMonNegoc(kGiro.getValueAt("MONTO").toString());
		Trace.trace(Trace.Debug, "", "### setMonNegoc: " + kGiro.getValueAt("MONTO").toString());
		
		if(kGiro.getValueAt("DESTRAN")!=null && kGiro.getValueAt("DESTRAN")!=""){
			peticion.setDeOperac(kGiro.getValueAt("DESTRAN").toString());
			peticion.setInfcam70Sw1(kGiro.getValueAt("DESTRAN").toString());
			Trace.trace(Trace.Debug, "", "### setInfcam70Sw1: " + kGiro.getValueAt("DESTRAN").toString());
			Trace.trace(Trace.Debug, "", "### setDeOperac: " + kGiro.getValueAt("DESTRAN").toString());
		}else{
			Trace.trace(Trace.Debug, "", "### setDeOperac: NULL/NO TRAE: valor no asignado");
		}
		peticion.setIdCliente(getNumeroCliente());
		Trace.trace(Trace.Debug, "", "### setIdCliente: " + getNumeroCliente());
		peticion.setNoOperac1(getNumeroOperacion());
		Trace.trace(Trace.Debug, "", "### setNoOperac1: " + getNumeroOperacion());
		
		String selectMoneda = kGiro.getValueAt("MONEDA").toString();
		if(selectMoneda!= null && selectMoneda!= ""){
			selectMoneda= selectMoneda.substring(0,3);
			}
		peticion.setCurNegoc(selectMoneda);
		Trace.trace(Trace.Debug, "", "### setCurNegoc: " + selectMoneda);
		
		if(getSubTipoOperacion().equals("T1") || getSubTipoOperacion().equals("T2")){
			peticion.setTyOperac("TFOU");
			peticion.setTyTransa("T");
			Trace.trace(Trace.Debug, "", "### setTyTransa: T");
			peticion.setSubcanal(null);
		}else if(getSubTipoOperacion().equals("H1") || getSubTipoOperacion().equals("H2")){
			peticion.setSubcanal(null);
			peticion.setTyOperac("TFIU");
			peticion.setTyTransa("H");
			Trace.trace(Trace.Debug, "", "### setTyTransa: H");
		}
		
		Trace.trace(Trace.Debug, "", "*** copiarDeOpListaGirosOP() - Asignacion de valores de TASA Peticion Ws");
		peticion.setMonAvance(kGiro.getValueAt("MONTO").toString());
		Trace.trace(Trace.Debug, "", "### setMonAvance: " + kGiro.getValueAt("MONTO").toString());
		if(getSubTipoOperacion().equals("T1") || getSubTipoOperacion().equals("H1")){
			
			Trace.trace(Trace.Debug, "", "### copiarDeOpListaGirosOP() TASAPE: " + kGiro.getValueAt("TASAPE").toString());
			peticion.setTasaDivi(Double.valueOf(kGiro.getValueAt("TASAPE").toString()));
			Trace.trace(Trace.Debug, "", "### setTasaDivi: " + Double.valueOf(kGiro.getValueAt("TASAPE").toString()));
			peticion.setTasaUsd(Double.valueOf(kGiro.getValueAt("TASAUSD").toString()));
			Trace.trace(Trace.Debug, "", "### setTasaUsd: " + Double.valueOf(kGiro.getValueAt("TASAUSD").toString()));
			peticion.setTasaLinea(Double.valueOf(kGiro.getValueAt("TASADIV").toString()));
			Trace.trace(Trace.Debug, "", "### setTasaLinea: " + Double.valueOf(kGiro.getValueAt("TASADIV").toString()));
			peticion.setTasaAvance(kGiro.getValueAt("TASAPE").toString());
			Trace.trace(Trace.Debug, "", "### setTasaAvance: " + Double.valueOf(kGiro.getValueAt("TASAPE").toString()));
			peticion.setNoAvance("STD1");
			Trace.trace(Trace.Debug, "", "### setTyNegoci: NL" );
			
		}else if(getSubTipoOperacion().equals("T2") || getSubTipoOperacion().equals("H2")){
			
			Trace.trace(Trace.Debug, "", "*** copiarDeOpListaGirosOP() " + kGiro.getValueAt("TASADIV").toString() + " / " + kGiro.getValueAt("MONTO").toString());
			if(kGiro.getValueAt("NUMAVA")!=null){
				//INI INC FX 182A 24/04/2019
				peticion.setTasaDivi(Double.valueOf(kGiro.getValueAt("TASAPE").toString()));
				peticion.setTasaUsd(null);
				peticion.setTasaLinea(null);
				peticion.setTasaAvance(kGiro.getValueAt("TASAPE").toString());
				//FIN INC FX 182A 24/04/2019
				peticion.setNoAvance(kGiro.getValueAt("NUMAVA").toString());
				Trace.trace(Trace.Debug, "", "### setNoAvance: " + kGiro.getValueAt("NUMAVA").toString());
			}else{
				peticion.setNoAvance(null);
				//INI INC FX 182A 23/04/2019
				Trace.trace(Trace.Debug, "", "### setTyNegoci: NM" );
				peticion.setTasaDivi(null);		
				peticion.setTasaUsd(null);
				peticion.setTasaLinea(null);
				peticion.setTasaAvance(null);
				//FIN INC FX 182A 23/04/2019
			}
		}
		//FX INI INC 124 03-12-2018
		if(getSubTipoOperacion().equals("T1") || getSubTipoOperacion().equals("T2")){
			String codABA=(String)kGiro.getValueAt("CODABBA");
			String coSwift=(String)kGiro.getValueAt("CODSWIFT");
			if(null!=codABA && !codABA.trim().equalsIgnoreCase("null") && !codABA.trim().equalsIgnoreCase("")){
				peticion.setIDcampo57(codABA);
				peticion.setIdTyCampo57Sw("FW");
			}
			else if(null!=coSwift && !coSwift.trim().equalsIgnoreCase("null") && !coSwift.trim().equalsIgnoreCase("")){
				peticion.setSwiftcampo57A(coSwift);
			}
		}
		//FX FIN INC 124 03-12-2018		
		Trace.trace(Trace.Debug, "", " Fin copiarDeOpListaGirosOP()");
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
	
	/*
	 * - METODO INICIAL 
	 * - Envio de operaciones de negociacion de Divisas
	 */
	public boolean peticionEnvioWs(){
		
		Trace.trace(Trace.Debug, "", "Inicio peticionEnvioWs()  WS Divisas -");
		boolean estadoPeticionWs = false;
		if(peticion != null){
			 try{	
				ResponseBankTradeService response = WrapperBanktradeService.execute(peticion);
				if(response.getCodError() != null){
					if(!response.getCodError().equals("")){
						String mensaje = ERROR_WS_BANKTRADE.concat(response.getSequence()+" "+response.getCodError());
						Trace.trace(Trace.Debug, "", "### peticionEnvioWs() - error de web service: " + mensaje);
						estadoPeticionWs = false;
					}else{
						Trace.trace(Trace.Debug, "", "### peticionEnvioWs() - response.getNumOpera " + response.getNumOpera());
						estadoPeticionWs = true;
					}
				}else{
					Trace.trace(Trace.Debug, "", "### peticionEnvioWs() - error de web service: RTA null");
					estadoPeticionWs = false;
				}
			}
			catch (Exception e) {
				if (e instanceof AxisFault) { //INI VARIOS NITS 2 TOUT CMC 17-02-2020
				AxisFault ex = (AxisFault) e;
					if(ex.detail.getMessage().contains("connect timed out") ||ex.getFaultString().contains("SocketTimeoutException")) {
						Trace.trace(Trace.Error, "", "### peticionEnvioWs() Control TIME OUT  - Falla lanzando web service: " + e);
						estadoPeticionWs = true;
						return estadoPeticionWs;
					}
				} //FIN VARIOS NITS 2 TOUT CMC 17-02-2020
				estadoPeticionWs = false;
				Trace.trace(Trace.Debug, "", "### peticionEnvioWs() - Falla lanzando web service: " + e);
			}
		}
		
		return estadoPeticionWs;
	}
	// FIN - GP13427 Reingenieria de Divisass 
	
	/**
	 * <h1>GP16295 Ofertas CV</h1> 
	 * Metodo que da de alta la oferta de credito virtual para  usuarios mancomunados.
	 * 
	 * @author <a href="mailto:hernancamilo.rojas.contractor@bbva.com">CMC Camilo Rojas Balanta</a>
	 * @version 03/12/2018 Last modification : 20-02-2019 v0.0.6
	 * @throws Exception
	 * 
	 * @param nombreOM
	 * @param idOrden
	 * @param result
	 * @throws Exception
	 */
	private void altaOfertasCV(String nombreOM, String idOrden, ResultSet result, KeyedCollection kcOrden) throws Exception{
		
		try {
			Trace.trace(64, getClass().getName() + " ******* INICIO OpMancomunadasMultiple_rs7 (Ofertas) - jsonMultienvio()");
			
			om = creaOM(nombreOM);			
			String referencia = ((String) result.getString("TLNE_REFERENCIA")).substring(0, 8);
			String usuario = ((String) result.getString("TLNE_REFERENCIA")).substring(8);
			long lNroContrato = (new Double(result.getDouble("TLNE_BNUMAUX1"))).longValue(); 
			String contrato = Long.toString(lNroContrato); 
			String cliente = result.getString("TLNE_BCODACCC");
			String tipoId = result.getString("TLNE_BINDAUX1");
			String numeroId = result.getString("TLNE_BPALACCE");
			
			String queryTTLBHFUA = "INSERT INTO TLCL.TTLBHFUA (COD_CLIECASH, COD_CLASEORD, COD_IDORDEN, COD_ACCION, COD_IDACCION, COD_USUARIO, FEC_ACCIO, COD_USUFIRMA, COD_USUPODER, COD_ESTADFIC, FEC_ACCION, AUD_FMODIFIC, AUD_USUARIO, COD_IPCLIENT) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			Connection connFUA = ConsultaBD.getConexion("BDMexico");
			PreparedStatement stmFUA = connFUA.prepareStatement(queryTTLBHFUA);
			
			java.util.Calendar fechaActual = java.util.Calendar.getInstance();
			int mes0 = fechaActual.get(java.util.Calendar.MONTH);
			mes0 = mes0 + 1;
			String mes00 = Integer.toString(mes0);
			if (mes00.length() < 2) {
				mes00 = "0" + mes00;
			}
			
			int dia0 = fechaActual.get(java.util.Calendar.DATE);
			String dia00 = Integer.toString(dia0);
			if (dia00.length() < 2) {
				dia00 = "0" + dia00;
			}
			
			String fechaFormato = fechaActual.get(java.util.Calendar.YEAR) + "-" + mes00 + "-" + dia00;
			String fecha = fechaActual.get(java.util.Calendar.YEAR) + mes00 + dia00;
			SimpleDateFormat hora = new SimpleDateFormat ("hhmmss");
										
			Trace.trace(64, getClass().getName() + " ******* idOrdenMan = " + result.getString("TLNE_IDORDEN"));
			Trace.trace(64, getClass().getName() + " ******* referencia = " + referencia);
			Trace.trace(64, getClass().getName() + " ******* usuario = " + usuario);
			Trace.trace(64, getClass().getName() + " ******* canal = " + result.getString("TLNE_BCODAUX1"));
			Trace.trace(64, getClass().getName() + " ******* tipo producto = " + result.getString("TLNE_BCODAUX2"));
			Trace.trace(64, getClass().getName() + " ******* contrato = " + contrato);
			Trace.trace(64, getClass().getName() + " ******* cliente = " + cliente);
			Trace.trace(64, getClass().getName() + " ******* tipoId = " + tipoId);
			Trace.trace(64, getClass().getName() + " ******* numeroId = " + numeroId);
																	
			om.setValueAt("Entrada.REFERENCIA", referencia);
			om.setValueAt("Entrada.CANAL", result.getString("TLNE_BCODAUX1"));
			om.setValueAt("Entrada.USUARIO", usuario);
			om.setValueAt("Entrada.CLIENTE", cliente);	
			om.setValueAt("Entrada.TIPO-ID", tipoId);		
			om.setValueAt("Entrada.NUMERO-ID", numeroId);	
			om.setValueAt("Entrada.TIPO-PRODUCTO", result.getString("TLNE_BCODAUX2"));
			om.setValueAt("Entrada.CONTRATO", contrato);	
						
			Trace.trace(64, getClass().getName() + " ******* INICIO TXFG19");
			ejecutarOM(om);							
			Trace.trace(64, getClass().getName() + " ******* FIN TXFG19");
			
			String codRetorno = getValueAt("ofertas_alta_om-data.Salida.COD-RETORNO").toString();
			String desRetorno = getValueAt("ofertas_alta_om-data.Salida.DES-RETORNO").toString();
			
			switch (Integer.parseInt(codRetorno)) {
			case 0:
				Trace.trace(64, getClass().getName() + " ******* OK TXFG19 OpMancomunadasMultiple_rs7 (Ofertas) - jsonMultienvio() ");
				Trace.trace(64, getClass().getName() + " ******* COD-RETORNO = " + codRetorno);
				Trace.trace(64, getClass().getName() + " ******* DES-RETORNO = " + desRetorno);
				
				stmFUA.setString(1, "00260082" + referencia);
				stmFUA.setString(2,  OfertaConstantesEnum.CREDITO_VIRTUAL_CLASE_ORDEN.getConstante());
				stmFUA.setString(3, idOrden);
				stmFUA.setInt(4, 11);
				stmFUA.setInt(5, 0); 
				stmFUA.setString(6, (String) getValueAt("s_cod_usuarisc"));
				stmFUA.setString(7, fecha);
				stmFUA.setString(8, (String) getValueAt("s_cod_usuarisc"));
				stmFUA.setString(9, "");
				stmFUA.setString(10, "022");
				stmFUA.setString(11, (String) hora.format(new Date()));
				stmFUA.setString(12, fechaFormato);
				stmFUA.setString(13, "");
				stmFUA.setString(14, (String)getValueAt("s_ip"));		
				
				stmFUA.execute();																					
				stmFUA.close();
				connFUA.close();
				
				break;

			default:
				Trace.trace(64, getClass().getName() + " ******* ERROR TXFG19 OpMancomunadasMultiple_rs7 (Ofertas) - jsonMultienvio() ");
				Trace.trace(64, getClass().getName() + " ******* COD-RETORNO = " + codRetorno);
				Trace.trace(64, getClass().getName() + " ******* DES-RETORNO = " + desRetorno);
											
				stmFUA.setString(1, "00260082" + referencia);
				stmFUA.setString(2, OfertaConstantesEnum.CREDITO_VIRTUAL_CLASE_ORDEN.getConstante());
				stmFUA.setString(3, idOrden);
				stmFUA.setInt(4, 1);  
				stmFUA.setInt(5, 0);  
				stmFUA.setString(6, (String) getValueAt("s_cod_usuarisc"));
				stmFUA.setString(7, fecha);
				stmFUA.setString(8, (String) getValueAt("s_cod_usuarisc"));
				stmFUA.setString(9, "");
				stmFUA.setString(10, "023");
				stmFUA.setString(11, (String) hora.format(new Date()));
				stmFUA.setString(12, fechaFormato);
				stmFUA.setString(13, "");
				stmFUA.setString(14, (String)getValueAt("s_ip"));								
				
				stmFUA.execute();																					
				stmFUA.close();
				connFUA.close();
				
				ManejarExcepcion(3, "", "", "", null, "", this, "", "");
				kcOrden.setValueAt("error" , "ERROR TXFG19 OpMancomunadasMultiple_rs7 (Ofertas)");		
				Trace.trace(Trace.Debug , "******* ERROR TXFG19 OpMancomunadasMultiple_rs7 (Ofertas) - jsonMultienvio()");
				break;
			}
																	
			Trace.trace(64, getClass().getName() + " ******* FIN OpMancomunadasMultiple_rs7 (Ofertas) - jsonMultienvio()");
		} catch (Exception e) {							
			Trace.trace(64, getClass().getName() + " ******* ERROR OpMancomunadasMultiple_rs7 (Ofertas) - inicio() = " + e.getMessage());
			ManejarExcepcion(3, "", "", "", e, "", this, "", "");
			kcOrden.setValueAt("error" , getValueAt("Error"));		
		}		
	}
	
	/**
	 * <h1>GP-16543 Freemium</h1> 
	 * Metodo ejecuta om para contratar portafolio freemium
	 * 
	 * @author <a href="mailto:">Hector Siso</a>
	 * @version 09/04/2019 Last modification : 09-04-2019 v0.0.1
	 * @throws Exception
	 * 
	 * @param nombreOM
	 * @param idOrden
	 * @param result
	 * @throws Exception
	 */
	private void  contratarFreemium(String nombreOM, String idOrden, ResultSet result, KeyedCollection kcOrden) throws Exception{
		
		try {
			Trace.trace(64, getClass().getName() + " ******* INICIO OpMancomunadasMultiple_rs7 (Ofertas) - contratarFreemium()");
			
			om = creaOM(nombreOM);	
			OperacionMulticanal omSrvs = null;
			String referencia = ((String) result.getString("TLNE_REFERENCIA")).substring(0, 8);
			String usuario = ((String) result.getString("TLNE_REFERENCIA")).substring(8);
			String portafolio = (String) result.getString("TLNE_BCODACCC");
			
			String nomPortafolio = (String) result.getString("TLNE_BCODCTAA"); 
			String tipoProducto = (String) result.getString("TLNE_BPALACCE"); 
						
			String tipoOperacion = (String) result.getString("TLNE_BCODAUX2");
			
			String lDiario = (String) result.getString("TLNE_BPALACC2");
			String lMensual = (String) result.getString("TLNE_BASUNPRO");
			String lOperacion = (String) result.getString("TLNE_BASUNASO");
			
			HashMap<String, String> hmLimites = new HashMap<String, String>();
			hmLimites.put("lOperacion", lOperacion);
			hmLimites.put("lDiario", lDiario);
			hmLimites.put("lOperacion", lOperacion);
					
			long valorOferta = (new Double(result.getDouble("TLNE_BNUMAUX2"))).longValue(); 
			

    		Connection conn = ConsultaBD.getConexion("BDMexico");
    
    		

    		
    		//ARMA OM SRVS
    		String nombreOmSrv = "";
    		String codServicio, tipoAsunto, asunto =""; 
			

 
			String queryTTLBHFUA = "INSERT INTO TLCL.TTLBHFUA (COD_CLIECASH, COD_CLASEORD, COD_IDORDEN, COD_ACCION, COD_IDACCION, COD_USUARIO, FEC_ACCIO, COD_USUFIRMA, COD_USUPODER, COD_ESTADFIC, FEC_ACCION, AUD_FMODIFIC, AUD_USUARIO, COD_IPCLIENT) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			 
			PreparedStatement stmFUA = conn.prepareStatement(queryTTLBHFUA);
			
			java.util.Calendar fechaActual = java.util.Calendar.getInstance();
			int mes0 = fechaActual.get(java.util.Calendar.MONTH);
			mes0 = mes0 + 1;
			String mes00 = Integer.toString(mes0);
			if (mes00.length() < 2) {
				mes00 = "0" + mes00;
			}
			
			int dia0 = fechaActual.get(java.util.Calendar.DATE);
			String dia00 = Integer.toString(dia0);
			if (dia00.length() < 2) {
				dia00 = "0" + dia00;
			}
			
			String fechaFormato = fechaActual.get(java.util.Calendar.YEAR) + "-" + mes00 + "-" + dia00;
			String fecha = fechaActual.get(java.util.Calendar.YEAR) + mes00 + dia00;
			SimpleDateFormat hora = new SimpleDateFormat ("hhmmss");
										
			Trace.trace(64, getClass().getName() + " ******* idOrdenMan = " + result.getString("TLNE_IDORDEN"));
			Trace.trace(64, getClass().getName() + " ******* referencia = " + referencia);
			Trace.trace(64, getClass().getName() + " ******* usuario = " + usuario);
			Trace.trace(64, getClass().getName() + " ******* tipoProducto = " + tipoProducto);
			Trace.trace(64, getClass().getName() + " ******* nroPortafolio = " + portafolio);								
			Trace.trace(64, getClass().getName() + " ******* valorCredito = " + valorOferta);
																	
			om.setValueAt("Entrada.REFERENCIA", referencia);
			om.setValueAt("Entrada.COD-PAQUETE", portafolio);
					
			Trace.trace(64, getClass().getName() + " ******* INICIO TXFNAP");
			ejecutarOM(om);							
			Trace.trace(64, getClass().getName() + " ******* FIN TXFNAP");
			
			String codRetorno = getValueAt("ofertas_alta_om-data.Salida.CODIGO-RETORNO").toString();
			String desRetorno = getValueAt("ofertas_alta_om-data.Salida.DESCRIP-RETORNO").toString();
			
			switch (Integer.parseInt(codRetorno)) {
			case 0:
				Trace.trace(64, getClass().getName() + " ******* OK TXFNAP OpMancomunadasMultiple_rs7 (Freemium) - contratarFreemium() ");
				Trace.trace(64, getClass().getName() + " ******* CODIGO-RETORNO = " + codRetorno);
				Trace.trace(64, getClass().getName() + " ******* DESCRIP-RETORNO = " + desRetorno);
				
				stmFUA.setString(1, "00260082" + referencia);
				stmFUA.setString(2, "PTF");
				stmFUA.setString(3, idOrden);
				stmFUA.setInt(4, 11);
				stmFUA.setInt(5, 0); 
				stmFUA.setString(6, (String) getValueAt("s_cod_usuarisc"));
				stmFUA.setString(7, fecha);
				stmFUA.setString(8, (String) getValueAt("s_cod_usuarisc"));
				stmFUA.setString(9, "");
				stmFUA.setString(10, "022");
				stmFUA.setString(11, (String) hora.format(new Date()));
				stmFUA.setString(12, fechaFormato);
				stmFUA.setString(13, "");
				stmFUA.setString(14, (String)getValueAt("s_ip"));		
				
				stmFUA.execute();																					
				stmFUA.close();
				conn.close();
				
				//LANZA OM A/B SRVs
				if(tipoOperacion.equalsIgnoreCase("SUBE_PLAN")){
					 //alta servicios
					cargalistaServiciosAltaOM(hmLimites);
					
				}else{
					bajaServicios();

				}
				
				break;

			default:
				Trace.trace(64, getClass().getName() + " ******* OK TXFNAP OpMancomunadasMultiple_rs7 (Freemium) - contratarFreemium() ");
				Trace.trace(64, getClass().getName() + " ******* CODIGO-RETORNO = " + codRetorno);
				Trace.trace(64, getClass().getName() + " ******* DESCRIP-RETORNO = " + desRetorno);
											
				stmFUA.setString(1, "00260082" + referencia);
				stmFUA.setString(2, "PTF");
				stmFUA.setString(3, idOrden);
				stmFUA.setInt(4, 1);  
				stmFUA.setInt(5, 0);  
				stmFUA.setString(6, (String) getValueAt("s_cod_usuarisc"));
				stmFUA.setString(7, fecha);
				stmFUA.setString(8, (String) getValueAt("s_cod_usuarisc"));
				stmFUA.setString(9, "");
				stmFUA.setString(10, "023");
				stmFUA.setString(11, (String) hora.format(new Date()));
				stmFUA.setString(12, fechaFormato);
				stmFUA.setString(13, "");
				stmFUA.setString(14, (String)getValueAt("s_ip"));								
				
				stmFUA.execute();																					
				stmFUA.close();
				conn.close();
				
				ManejarExcepcion(3, "", "", "", null, "", this, "", "");
				kcOrden.setValueAt("error" , "ERROR TXFNAP OpMancomunadasMultiple_rs7 (Freemium)");		
				Trace.trace(Trace.Debug , "******* ERROR TXFNAP OpMancomunadasMultiple_rs7 (Freemium) - contratarFreemium()");
				break;
			}
																	
			Trace.trace(64, getClass().getName() + " ******* FIN OpMancomunadasMultiple_rs7 (Freemium) - contratarFreemium()");
		} catch (Exception e) {							
			Trace.trace(64, getClass().getName() + " ******* ERROR OpMancomunadasMultiple_rs7 (Freemium) - inicio() = " + e.getMessage());
			ManejarExcepcion(3, "", "", "", e, "", this, "", "");
			kcOrden.setValueAt("error" , getValueAt("Error"));		
		}		
	}
	
	private boolean cargalistaServiciosAltaOM(HashMap<String, String> hmLimites) throws Exception {
		Trace.trace(Trace.Debug, "OpMancomunadasMultiple_rs7", 
				"***** Inicio Metodo cargalistaServiciosAltaOM *****");
		String usuario = "", referencia = "";
		boolean lanzar = false;
		IndexedCollection icListaServicios;
		Integer contador;
		try {
			usuario = (String) getValueAt("s_cod_usuarisc");// sebas 90
			referencia = (String) getValueAt("s_cod_logon");// 40003009

			int cont = 1;
			contador = new Integer(cont);
			do {
				om = creaOM("autogestion_serv_alta_listador_om");
				om.setValueAt("Entrada.REFERENCIA", referencia);
				om.setValueAt("Entrada.USUARIO", usuario);
				om.setValueAt("Entrada.IND-PAGINACION", contador);
				ejecutarOM(om);
				String respuesta = (String) om
						.getValueAt("Salida.IND-PAGINACION");
				icListaServicios = (IndexedCollection) om
						.getElementAt("Salida.LISTADOR-SERV");
				envioHostAlta(icListaServicios, hmLimites);
				if (respuesta.indexOf('S') != -1) {
					lanzar = true;
					cont += icListaServicios.size();
					contador = new Integer(cont);
				} else {
					lanzar = false;
				}

			} while (lanzar);

			
			Trace.trace(Trace.Debug, "OpAltaServicios",
					"***** FIN Metodo listaServiciosOM *****");
			return true;
			
		} catch (Exception ex) {
			Trace.trace(
					Trace.Error,
					"Error en OpAltaServicios de listaServiciosOM."
							+ ex.toString());
			return false;
			//setEstado("ERROR");
			//throw BbvaARQException.tipificarExcepcion(null, ex);
		}

	}
	
	
	
	public void envioHostAlta(IndexedCollection icContextoSesion, HashMap<String, String> hmLimites) throws BbvaARQException {
		Trace.trace(Trace.Debug, "OpAutogestionServAlta",
				"***** Inicio Metodo envioHost *****");
		IndexedCollection icListaServicios;
		KeyedCollection kcOutListaSrv2;


		int numServEnviar;
		 
		try {

			// Datos con los que se Ejecuta la OM autogestion_serv_alta_om
			String usuario = (String) getValueAt("s_cod_usuarisc");// sebas 90
			String referencia = (String) getValueAt("s_cod_logon");// 40003009
			
			
			numServEnviar = icContextoSesion.size();

			om = creaOM("autogestion_serv_alta_om");
			om.setValueAt("Entrada.REFERENCIA", referencia);
			om.setValueAt("Entrada.USUARIO", usuario);


			om.setValueAt("Entrada.IND-PAGINACION", numServEnviar);
			icListaServicios = (IndexedCollection) om
					.getElementAt("Entrada.LISTASAUTOGES");
			icListaServicios.removeAll();

			String lDiario = (String) hmLimites.get("lDiario");
			String lMensual = (String)  hmLimites.get("lMensual");
			String lOperacion = (String) hmLimites.get("lOperacion");
			
			
			Long lngOperacion = new Long(lOperacion);
			Long lngDiario = new Long(lDiario);
			Long lngMensual = new Long(lMensual);
			
				for (int i = 0; i < numServEnviar; i++) {
					KeyedCollection kcContextoSesion2 = (KeyedCollection) icContextoSesion
							.getElementAt(i);
					kcOutListaSrv2 = (KeyedCollection) DataElement
							.getExternalizer().convertTagToObject(
									icListaServicios.getElementSubTag());
					kcOutListaSrv2.setValueAt("COD-SERVICIO", kcContextoSesion2
							.getValueAt("COD-SERVICIO").toString());
					if (kcContextoSesion2.getValueAt("IND-LIMITES").toString()
							.equals("S")) {
						kcOutListaSrv2.setValueAt("IND-LIMITES","S");
						
						kcOutListaSrv2.setValueAt("LIMITE-OPERA", lngOperacion);
 
						kcOutListaSrv2.setValueAt("LIMITE-DIARIO", lngDiario);
						
						kcOutListaSrv2.setValueAt("LIMITE-MENSUAL", lngMensual);

					} else {
						kcOutListaSrv2.setValueAt("IND-LIMITES","N");
					}

					icListaServicios.addElement(kcOutListaSrv2);
				}
	 
				
				
				ejecutarOM(om);

			

		
			Trace.trace(Trace.Debug, "OpFreemium",
					"***** FIN Metodo envioHost *****");

		} catch (Exception ex) {
			Trace.trace(
					Trace.Error,
					"Error en OpFreemium Metodo envioHostAlta."
							+ ex.toString());
			setEstado("ERROR");
			throw BbvaARQException.tipificarExcepcion(null, ex);
		}
	}
	
	private void bajaServicios() throws Exception {

		IndexedCollection iCListHost, icListaServicios;
		Enumeration<?> enumerationHost;
		KeyedCollection kCItemServicioBaja;

		
		
		try { 
			
		
			iCListHost = (IndexedCollection) getElementAt("freemium_contratar_om-data.Salida.LISTADOR-SERVICIOS");
		
			if(iCListaIsNotEmpty(iCListHost, "COD-SERVICIO")) {		    
				
				String usuario = (String) getValueAt("s_cod_usuarisc");
				String referencia = (String) getValueAt("s_cod_logon");
				OperacionMulticanal oMFG13 = null;
				oMFG13 = creaOM("autogestion_serv_baja_om");		    
				oMFG13.setValueAt("Entrada.REFERENCIA",referencia);
				oMFG13.setValueAt("Entrada.USUARIO",usuario);				
				oMFG13.setValueAt("Entrada.IND-PAGINACION",iCListHost.size());
				
			    icListaServicios = (IndexedCollection) oMFG13.getElementAt("Entrada.LISTASAUTOGES");
				icListaServicios.removeAll();
			
				enumerationHost = iCListHost.getEnumeration();
				
				
	
				while (enumerationHost.hasMoreElements()) {
					KeyedCollection kCItemHost = (KeyedCollection) enumerationHost
							.nextElement();
			
					kCItemServicioBaja = (KeyedCollection) DataElement.getExternalizer().convertTagToObject(icListaServicios.getElementSubTag());
	 

					kCItemServicioBaja.setValueAt("COD-SERVICIO",  (String) kCItemHost.getValueAt("COD-SERVICIO"));
					icListaServicios.addElement(kCItemServicioBaja);
		
				}
				
					
				
				ejecutarOM(oMFG13);
				
			}
		
		} catch (Exception e) {
			Trace.trace(
					64,
					getClass().getName()
							+ " ******* ERROR - Metodo: bajaServicios(): "
							+ e.getMessage());

			BbvaARQException Barq = ManejarExcepcion(1, "", "", "", e, "",
					this, "", "");
			ManejarExcepcion(4, "", "", "", Barq, "", this, "", "");
			throw (Barq);
		}
			//setEstado("2");

	}
	
	private boolean iCListaIsNotEmpty(IndexedCollection iCLista, String fieldCheck)
			throws DSEObjectNotFoundException {

		boolean result = true;
		Enumeration<?> enumeration;
		KeyedCollection kCOfertas = null;
		String val;

		enumeration = iCLista.getEnumeration();
		if (enumeration.hasMoreElements()) {
			kCOfertas = (KeyedCollection) enumeration.nextElement();
			val = (String) kCOfertas.getValueAt(fieldCheck);
			if (val == null || val.equals("")) {
				result = false;
			}
		} else {
			result = false;
		}

		return result;
	}
	
	/**
	 * <h1>GP16880 Ofertas CM</h1> 
	 * Metodo que da de alta la oferta de credito comercia para  usuarios mancomunados.
	 * 
	 * @version 12/02/2020 Last modification : 12/02/2020 v0.0.1
	 * @throws Exception
	 * 
	 * @param nombreOM
	 * @param idOrden
	 * @param result
	 * @throws Exception
	 */
	private void altaOfertasCM(String nombreOM, String idOrden, ResultSet result, KeyedCollection kcOrden) throws Exception{
		
		Connection connFUA = null;
		try {
			Trace.trace(Trace.VTF, "" + " ******* INICIO OpMancomunadasMultiple_rs7 (OfertasCm) - jsonMultienvio()");
			nombreOM = "ofertas_alta_om";
			om = creaOM(nombreOM);			
			String referencia = ((String) result.getString("TLNE_REFERENCIA")).substring(0, 8);
			String usuario = ((String) result.getString("TLNE_REFERENCIA")).substring(8);
			long lNroContrato = (new Double(result.getDouble("TLNE_BNUMAUX1"))).longValue(); 
			String contrato = Long.toString(lNroContrato); 
			String cliente = result.getString("TLNE_BCODACCC");
			String tipoId = result.getString("TLNE_BINDAUX1");
			String numeroId = result.getString("TLNE_BPALACCE");
			String cuentaSel = (String) result.getString("TLNE_BASUNPRO");
			
			String queryTTLBHFUA = "INSERT INTO TLCL.TTLBHFUA (COD_CLIECASH, COD_CLASEORD, COD_IDORDEN, COD_ACCION, COD_IDACCION, COD_USUARIO, FEC_ACCIO, COD_USUFIRMA, COD_USUPODER, COD_ESTADFIC, FEC_ACCION, AUD_FMODIFIC, AUD_USUARIO, COD_IPCLIENT) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			connFUA = ConsultaBD.getConexion("BDMexico");
			PreparedStatement stmFUA = connFUA.prepareStatement(queryTTLBHFUA);
			
			Calendar fechaActual = Calendar.getInstance();
			int mes0 = fechaActual.get(Calendar.MONTH);
			mes0 = mes0 + 1;
			String mes00 = Integer.toString(mes0);
			if (mes00.length() < 2) {
				mes00 = "0" + mes00;
			}
			
			int dia0 = fechaActual.get(Calendar.DATE);
			String dia00 = Integer.toString(dia0);
			if (dia00.length() < 2) {
				dia00 = "0" + dia00;
			}
			
			String fechaFormato = fechaActual.get(Calendar.YEAR) + "-" + mes00 + "-" + dia00;
			String fecha = fechaActual.get(Calendar.YEAR) + mes00 + dia00;
			SimpleDateFormat hora = new SimpleDateFormat ("hhmmss");
										
			Trace.trace(Trace.VTF, "" + " ******* idOrdenMan = " + result.getString("TLNE_IDORDEN")
			+ " ******* referencia = " + referencia
			+ " ******* usuario = " + usuario
			+ " ******* canal = " + result.getString("TLNE_BCODAUX1")
			+ " ******* tipo producto = " + result.getString("TLNE_BCODAUX2")
			+ " ******* contrato = " + contrato
			+ " ******* cliente = " + cliente
			+ " ******* tipoId = " + tipoId
			+ " ******* numeroId = " + numeroId
			+ " ******* cuentaSel = " + cuentaSel);
			
			om.setValueAt("Entrada.REFERENCIA", referencia);
			om.setValueAt("Entrada.CANAL", result.getString("TLNE_BCODAUX1"));
			om.setValueAt("Entrada.USUARIO", usuario);
			om.setValueAt("Entrada.CLIENTE", cliente);	
			om.setValueAt("Entrada.TIPO-ID", tipoId);		
			om.setValueAt("Entrada.NUMERO-ID", numeroId);	
			om.setValueAt("Entrada.TIPO-PRODUCTO", result.getString("TLNE_BCODAUX2"));
			om.setValueAt("Entrada.CONTRATO", contrato);
			om.setValueAt("Entrada.CUENTA-SEL", cuentaSel);
						
			Trace.trace(Trace.VTF, "" + " ******* INICIO TXFG19");
			ejecutarOM(om);							
			Trace.trace(Trace.VTF, "" + " ******* FIN TXFG19");
			
			String codRetorno = getValueAt("ofertas_alta_om-data.Salida.COD-RETORNO").toString();
			String desRetorno = getValueAt("ofertas_alta_om-data.Salida.DES-RETORNO").toString();
			
			switch (Integer.parseInt(codRetorno)) {
			case 0:
				Trace.trace(Trace.VTF, "" + " ******* OK TXFG19 OpMancomunadasMultiple_rs7 (OfertasCm) - jsonMultienvio() \n"+
											"******* COD-RETORNO = " + codRetorno + "\n"+
											" ******* DES-RETORNO = " + desRetorno);
				
				stmFUA.setString(1, "00260082" + referencia);
				stmFUA.setString(2,  OfertaConstantesEnum.CREDITO_VIRTUAL_CLASE_ORDEN.getConstante());
				stmFUA.setString(3, idOrden);
				stmFUA.setInt(4, 11);
				stmFUA.setInt(5, 0); 
				stmFUA.setString(6, (String) getValueAt("s_cod_usuarisc"));
				stmFUA.setString(7, fecha);
				stmFUA.setString(8, (String) getValueAt("s_cod_usuarisc"));
				stmFUA.setString(9, "");
				stmFUA.setString(10, "022");
				stmFUA.setString(11, (String) hora.format(new Date()));
				stmFUA.setString(12, fechaFormato);
				stmFUA.setString(13, "");
				stmFUA.setString(14, (String)getValueAt("s_ip"));		
				
				stmFUA.execute();																					
				stmFUA.close();
				connFUA.close();
				
				break;

			default:
				Trace.trace(Trace.VTF, "" + " ******* ERROR TXFG19 OpMancomunadasMultiple_rs7 (OfertasCm) - jsonMultienvio() \n"+
											"******* COD-RETORNO = " + codRetorno + "\n"+
											" ******* DES-RETORNO = " + desRetorno);
											
				stmFUA.setString(1, "00260082" + referencia);
				stmFUA.setString(2, OfertaConstantesEnum.CREDITO_VIRTUAL_CLASE_ORDEN.getConstante());
				stmFUA.setString(3, idOrden);
				stmFUA.setInt(4, 1);  
				stmFUA.setInt(5, 0);  
				stmFUA.setString(6, (String) getValueAt("s_cod_usuarisc"));
				stmFUA.setString(7, fecha);
				stmFUA.setString(8, (String) getValueAt("s_cod_usuarisc"));
				stmFUA.setString(9, "");
				stmFUA.setString(10, "023");
				stmFUA.setString(11, (String) hora.format(new Date()));
				stmFUA.setString(12, fechaFormato);
				stmFUA.setString(13, "");
				stmFUA.setString(14, (String)getValueAt("s_ip"));								
				
				stmFUA.execute();																					
				stmFUA.close();
				connFUA.close();
				
				ManejarExcepcion(3, "", "", "", null, "", this, "", "");
				kcOrden.setValueAt("error" , "ERROR TXFG19 OpMancomunadasMultiple_rs7 (OfertasCm)");		
				Trace.trace(Trace.Debug , "******* ERROR TXFG19 OpMancomunadasMultiple_rs7 (OfertasCm) - jsonMultienvio()");
				break;
			}
																	
			Trace.trace(Trace.VTF, "" + " ******* FIN OpMancomunadasMultiple_rs7 (OfertasCm) - jsonMultienvio()");
		} catch (Exception e) {							
			Trace.trace(Trace.Error, "" +  " ******* ERROR OpMancomunadasMultiple_rs7 (OfertasCm) - inicio() = " + e.getMessage());
			connFUA.close();
			ManejarExcepcion(3, "", "", "", e, "", this, "", "");
			kcOrden.setValueAt("error" , getValueAt("Error"));		
		}		
	}
	//CMC INICIO recaudo corporativo 24/03/2020
	public void EjecutarOmRecaudoCoporativo(ResultSet rs){
		try {
			om = creaOM("recaudo_corporativo_om");
			om.setValueAt("Entrada.generarCargo.ejecutar", "true");
			om.setValueAt("Entrada.generarCargo.convenioReferencia", rs.getString("TLNE_BPALACC2"));
			om.setValueAt("Entrada.generarCargo.valor", rs.getString("TLNE_BIMPORTE"));
			String referencia= rs.getString("TLNE_REFERENCIA");
			om.setValueAt("Entrada.generarCargo.referenciaEmpresa",referencia.substring(0, 8));
			om.setValueAt("Entrada.generarCargo.usuario",referencia.substring(8, 16));
			om.execute();
			System.out.println("om execyte OK");
		} catch (BbvaException e) {
			e.printStackTrace();
		}catch (DSEObjectNotFoundException e) {
			e.printStackTrace();
		}catch (DSEInvalidArgumentException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	//CMC FIN recaudo corporativo 24/03/2020
	
	//INI IN-838 RECAUDO UNE TORRE 30-06-2020 Se realiza validacion si el pago es de la Web o Movil
	private boolean existeFUAMovil(String idOrden){

		Trace.trace(Trace.Information, "" +  " ******* existeFUAMovil(a) consultar idOrden:" + idOrden);
		boolean respuesta = false;
		String selectMovilFUA = "SELECT count(*) AS CONTADOR FROM TLCL.TTLBHFUA WHERE COD_APPORIG='APP' AND COD_ACCION=1 AND COD_CLASEORD = 'PFA' AND COD_IDORDEN = ? AND COD_CLIECASH = ?";
		PreparedStatement stmMovilFua = null;
		ResultSet rsMovilFua = null;
		Connection connMovilFua = null;
		try{
			
			connMovilFua = ConsultaBD.getConexion("BDMexico");
			stmMovilFua = connMovilFua.prepareStatement(selectMovilFUA);
			stmMovilFua.setString(1, idOrden);
			String cod_cliecash = (String) getValueAt("datosAPP.iv-user");
			cod_cliecash = cod_cliecash.substring(0,16);
			Trace.trace(Trace.Information, "" +  " ******* existeFUAMovil(a) valor mapeado a COD_CLIECASH:" + cod_cliecash);
			stmMovilFua.setString(2, cod_cliecash);
			rsMovilFua = stmMovilFua.executeQuery();
			Trace.trace(Trace.Information, "" +  " ******* existeFUAMovil(a) luego de ejecutar consulta:" + idOrden);
			if ((rsMovilFua!=null) && rsMovilFua.next()) //Fue creada en la movil porque tiene la marcacion en campo COD_APPORIG de APP
			{
				respuesta=(rsMovilFua.getInt("CONTADOR")>0);
				Trace.trace(Trace.Information, "" +  " ******* existeFUAMovil(a) tiene marca de movil:" + rsMovilFua.getInt("CONTADOR"));
			}

		}
		catch(Exception ex){
			Trace.trace(Trace.Error, "" +  " ******* ERROR existeFUAMovil " + ex.getMessage());
		}
		finally
		{	
			try {
				if (stmMovilFua!=null)
					stmMovilFua.close();
				if (rsMovilFua != null)
					rsMovilFua.close();	
				if	(connMovilFua != null && !connMovilFua.isClosed())
					connMovilFua.close();	
			} catch (SQLException e) {
				Trace.trace(Trace.Error, "" +  " ******* ERROR SQLException existeFUAMovil " + e.getMessage());		
			}
		}		
		return respuesta;
	}
	//FIN IN-838 RECAUDO UNE TORRE 30-06-2020
	public void ejecutarOmRollback(ResultSet rs, String claseOrden) {
		Trace.trace(Trace.Debug, "" +  " Ejecutando metodo de  Rollback");	
		try {
			String referencia= rs.getString("TLNE_REFERENCIA");
			String idOrden=rs.getString("TLNE_IDORDEN");
			Trace.trace(Trace.Debug, "" +  " IdOrden : "+idOrden);	
			om= creaOM("rollback_operation_creation_om");
			om.setValueAt("Entrada.idOrden", idOrden);
			om.setValueAt("Entrada.referencia", refGeneric +referencia.substring(0, 8));
			om.setValueAt("Entrada.claseOrden", claseOrden);
			om.execute();
		} catch (BbvaException e) {
			Trace.trace(Trace.Error, "" +  " Error al ejecutar om  "+e.getMessage());
		} catch (DSEObjectNotFoundException e) {
			Trace.trace(Trace.Error, "" +  " Error al configurar om  "+e.getMessage());
		} catch (DSEInvalidArgumentException e) {
			Trace.trace(Trace.Error, "" +  " Error al configurar om  "+e.getMessage());
		} catch (SQLException e) {
			Trace.trace(Trace.Error, "" +  " Error al ejecutar SQL "+e.getMessage());
		}
	}
	//INICIO FX gestion
	public void EjecutarOmGestionPagosPse(String idOrden){
		try {
			MetodoEnum metodo = MetodoEnum.AUTORIZAR_ORDEN;
			om = creaOM("consulta_pagos_pse_divisa_om");
			om.setValueAt("Entrada.autorizar.idOrden", idOrden);
			om.setValueAt("Entrada.metodo", metodo.name());
			om.execute();

		} catch (BbvaException e) {
			e.printStackTrace();
		}catch (DSEObjectNotFoundException e) {
			e.printStackTrace();
		}catch (DSEInvalidArgumentException e) {
			e.printStackTrace();
		}
	}
	
}