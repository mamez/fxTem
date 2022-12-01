package com.grupobbva.bc.col.web.pse;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.grupobbva.col.servicios.OpGestion_xhr_rs7;
import com.grupobbva.ii.sf.base.BbvaException;
import com.grupobbva.ii.sf.operacion.OperacionMulticanal;
import com.ibm.dse.base.DSEInvalidArgumentException;
import com.ibm.dse.base.DSEObjectNotFoundException;
import com.ibm.dse.base.DataElement;
import com.ibm.dse.base.IndexedCollection;
import com.ibm.dse.base.KeyedCollection;
import com.ibm.dse.base.Trace;

public class OpGestionPagosPseDivisa extends OpGestion_xhr_rs7 {
	OperacionMulticanal oM = null;
	private static final String CODIGO_SERVICIO="3523";
	private static final String JSON_DATA="jsonData";
	private static final String MENSA_ERROR_OM="Error al obtener datos a om :";
	private static final String MENSA_ERROR_EJE_OM="Error al ejecutar om :";
	private static final String MENSA_ERROR_ENV_OM= "Error al enviar datos a om";
	private static final String STATUS= "status";
	private static final String CREAR_OM= "consulta_pagos_pse_divisa_om";
	private static final String METO_ENUM= "Entrada.metodo";
	
	/**
	*
	*/
	public OpGestionPagosPseDivisa() {
		super();
	}

	/**
	 *
	 * @param anOperationName
	 * @throws java.io.IOException
	 */
	public OpGestionPagosPseDivisa(String anOperationName) throws java.io.IOException {
		super(anOperationName);
	}

	/**
	 *
	 * @param anOperationName
	 * @param aParentContext
	 * @throws java.io.IOException
	 * @throws com.ibm.dse.base.DSEInvalidRequestException
	 */
	public OpGestionPagosPseDivisa(String anOperationName, com.ibm.dse.base.Context aParentContext)
			throws java.io.IOException, com.ibm.dse.base.DSEInvalidRequestException {
		super(anOperationName, aParentContext);
	}

	public void inicio() {
		try {
			
			listarUsuarios();
		} catch (Exception e) {
			Trace.trace(Trace.VTF, "", "Error al listar usuario : " + e.getMessage());
		}
		setEstado("1");
	}
	
	public void listarAutorizados() {
		try {
			String fechaDesde =(String)getValueAt("fechaDesde");
			String fechaHasta = (String)getValueAt("fechaHasta");
			String numDias = (String)getValueAt("numDias");
			String numTransa = null;
			String pagina = (String)getValueAt("pagina");
			if(!numDias.equals("NA")) {
				fechaDesde=getDateForDay(Integer.parseInt(numDias));
				fechaHasta=getFechaActual();
			}
			UsuarioResponse usuarioResp=listarUsuariosPSE(fechaDesde, fechaHasta, numTransa, pagina);
			setObjectValue(JSON_DATA, usuarioResp);
		}catch (Exception e) {
			Trace.trace(Trace.VTF, "", MENSA_ERROR_OM + e.getMessage());
		}
		setEstado("2");
	}
	

	public void configurarUsuario() {
		try {
			Map<String, Object> response = configurarUsuarioPse();
			setObjectValue(JSON_DATA, response);
			listarUsuarios();
		} catch (Exception e) {
			Trace.trace(Trace.VTF, "", "Error alconfigurar usuario : " + e.getMessage());
		}
		setEstado("3");
	}
	
	public void eliminarConfiguracion() {
		try {
			Map<String, Object> response= null;
			String numTransa = (String)getValueAt("numTransa");
			String [] numTransacciones = numTransa.split("&");
			for (String numTraccion : numTransacciones) {
				if(!numTraccion.trim().equals("")) {
					response = eliminarConfiguracionPse(numTraccion);
				}
			setObjectValue(JSON_DATA, response);
			}
		} catch (DSEObjectNotFoundException e) {
			Trace.trace(Trace.VTF, "", MENSA_ERROR_OM + e.getMessage());
		} catch (DSEInvalidArgumentException e) {
			Trace.trace(Trace.VTF, "", "Error al eliminar usuario : " + e.getMessage());
		}
			setEstado("4");
	}

	private UsuarioResponse listarUsuariosPSE(String fechaDesde,String fechaHasta,String numTransa,String pagina ) {
		IndexedCollection icOMUsuariosPse = null;
		UsuarioResponse usuarioResp = new UsuarioResponse();
		try {
			MetodoEnum metodo = MetodoEnum.LISTAR_USUARIOS;
			oM = creaOM(CREAR_OM);
			oM.setValueAt(METO_ENUM, metodo.name());
			oM.setValueAt("Entrada.listarUsuario.fechaDesde", fechaDesde);
			oM.setValueAt("Entrada.listarUsuario.fechahasta", fechaHasta);
			oM.setValueAt("Entrada.listarUsuario.numTransaccion", numTransa);
			oM.setValueAt("Entrada.listarUsuario.pagina", pagina);	
			oM.execute();
			
			List<UsuariosPseDto> usuarios = new ArrayList<UsuariosPseDto>();
			
			icOMUsuariosPse = (IndexedCollection) oM.getElementAt("Salida.listarUsuario.usuarios");
			Trace.trace(Trace.VTF, "", "El tamano de la lista es: " + icOMUsuariosPse.size());
			
			for (int i = 0; i < icOMUsuariosPse.size(); i++) {
				KeyedCollection listaUsuarioOm = (KeyedCollection) icOMUsuariosPse.getElementAt(i);
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
			usuarioResp.setUsuarios(usuarios);
			int page= (Integer)oM.getValueAt("Salida.listarUsuario.pagina");
			usuarioResp.setPage(page);
			usuarioResp.setStatus("OK");

		} catch (BbvaException e) {
			Trace.trace(Trace.VTF, "", MENSA_ERROR_EJE_OM + e.getMessage());
		} catch (DSEObjectNotFoundException e) {
			Trace.trace(Trace.VTF, "", MENSA_ERROR_OM + e.getMessage());
		} catch (DSEInvalidArgumentException e) {
			Trace.trace(Trace.VTF, "", MENSA_ERROR_ENV_OM + e.getMessage());
		}
		return usuarioResp;
	}
	
	
	private Map<String, Object> configurarUsuarioPse() {
		Map<String, Object> response= new HashMap<String, Object>();
		response.put(STATUS, "NOK");
		response.put("msg", "mensajeNok");
		String status = "NOK";
		String numTrans = null;
		try {
			String usuario=(String)getValueAt("usuarioAutoIn");
			String valorDesde = (String)getValueAt("valorTransacDesde");
			String valorHasta = (String)getValueAt("valorTransacHasta");
			String fechaTrans = (String)getValueAt("fechaTransac");
			String tipoPago = (String)getValueAt("tipoPago");
			double valorTransHasta = Double.parseDouble(valorHasta);

			MetodoEnum metodo = MetodoEnum.CONFIGURAR_USUARIO;
			oM = creaOM(CREAR_OM);
			oM.setValueAt(METO_ENUM, metodo.name());
			oM.setValueAt("Entrada.configurarUsuario.usuario", usuario);
			oM.setValueAt("Entrada.configurarUsuario.valorDesde", valorDesde);
			oM.setValueAt("Entrada.configurarUsuario.valorHasta", valorHasta);
			oM.setValueAt("Entrada.configurarUsuario.fechaTrans", fechaTrans);
			oM.setValueAt("Entrada.configurarUsuario.tipoPago", tipoPago);
			oM.execute();
			status = (String) oM.getValueAt("Salida.configurarUsuario.status");
			if(status.equals("OK")) {
				numTrans = (String) oM.getValueAt("Salida.configurarUsuario.numTransaccion");
				String detalleFactura= formatDetealle(numTrans,usuario);
				 String cuentaOrdente= "AH00000000000000000000";
				mancomunadas("", "Gest PSE Divisas", "", numTrans, 
						cuentaOrdente, "", "", "", valorTransHasta,0, "", "", "", "",
						"00", 0, 0, detalleFactura, CREAR_OM, "Gest PSE Divisas","GUD");
			    response.put(STATUS, status);
		        response.put("msg", "mensajeOk");
			}
		} catch (BbvaException e) {
			Trace.trace(Trace.VTF, "", MENSA_ERROR_EJE_OM + e.getMessage());
		} catch (DSEObjectNotFoundException e) {
			Trace.trace(Trace.VTF, "", MENSA_ERROR_OM + e.getMessage());
		} catch (DSEInvalidArgumentException e) {
			Trace.trace(Trace.VTF, "", MENSA_ERROR_ENV_OM + e.getMessage());
		} catch (Exception e) {
			Trace.trace(Trace.VTF, "", "Error al obtener datos a mancomunados : " + e.getMessage());
		}
		return response;
		
	}
	
	private Map<String, Object> eliminarConfiguracionPse(String numTransa) {
		Map<String, Object> response= new HashMap<String, Object>();
		response.put(STATUS, "NOK");
		response.put("msg", "Error al eliminar usuario");
		String status = "NOK";
		try {
			
			MetodoEnum metodo = MetodoEnum.ELIMINAR_USUARIO;
			oM = creaOM(CREAR_OM);
			oM.setValueAt(METO_ENUM, metodo.name());
			oM.setValueAt("Entrada.eliminarUsuario.numTransaccion", numTransa);
			oM.execute();
			 status = (String) oM.getValueAt(("Salida.eliminarUsuario.status"));
			 String idOrden = (String) oM.getValueAt(("Salida.eliminarUsuario.idOrden"));
			 deleteOrdenPse(idOrden);
			 response.put(STATUS, status);
		     response.put("msg", "Se ha eliminado exitosamente el usuario.");
		     
		} catch (BbvaException e) {
			Trace.trace(Trace.VTF, "", MENSA_ERROR_EJE_OM + e.getMessage());
		} catch (DSEObjectNotFoundException e) {
			Trace.trace(Trace.VTF, "", MENSA_ERROR_OM + e.getMessage());
		} catch (DSEInvalidArgumentException e) {
			Trace.trace(Trace.VTF, "", MENSA_ERROR_ENV_OM + e.getMessage());
		}
		return response;
	}
	
	
	
	
	private void deleteOrdenPse(String idOrden) {
		OperacionMulticanal oM = null;
		try {
			String codClient="00260082"+ getCodLogon(); 
			oM = creaOM("rollback_operation_creation_om");
			oM.setValueAt("Entrada.accion","deleteOrden");
			oM.setValueAt("Entrada.referencia",codClient);
			oM.setValueAt("Entrada.idOrden",idOrden);
			oM.execute();
		} catch (BbvaException e) {
			Trace.trace(Trace.VTF, "", MENSA_ERROR_EJE_OM + e.getMessage());
		} catch (DSEObjectNotFoundException e) {
			Trace.trace(Trace.VTF, "", MENSA_ERROR_OM + e.getMessage());
		} catch (DSEInvalidArgumentException e) {
			Trace.trace(Trace.VTF, "", MENSA_ERROR_ENV_OM + e.getMessage());
		}
	}
	
	private void listarUsuarios() throws IOException {
		OperacionMulticanal oM = null;
		IndexedCollection icOMUsuarios;
		try {
			oM = creaOM("InformacionUsuarios_om");
			oM.setValueAt("datosAPP.iv-id_sesion_ast", getIdSession());
			oM.setValueAt("Metodos.listarUsuariosPorTipoPerfil.Ejecutar", "true");
			oM.setValueAt("Metodos.listarUsuariosPorTipoPerfil.EntradaMetodo.codBancoInterno", "0082");
			oM.setValueAt("Metodos.listarUsuariosPorTipoPerfil.EntradaMetodo.codEmpresa", getCodLogon());
			oM.setValueAt("Metodos.listarUsuariosPorTipoPerfil.EntradaMetodo.codBancoProd", "82");
			oM.setValueAt("Metodos.listarUsuariosPorTipoPerfil.EntradaMetodo.codProducto", "1380");
			oM.setValueAt("Metodos.listarUsuariosPorTipoPerfil.EntradaMetodo.codSubproducto", "0");
			oM.setValueAt("Metodos.listarUsuariosPorTipoPerfil.EntradaMetodo.codCanal", "0026");
			oM.setValueAt("Metodos.listarUsuariosPorTipoPerfil.EntradaMetodo.mostrarFicticios", "");
			oM.setValueAt("Metodos.listarUsuariosPorTipoPerfil.EntradaMetodo.tipoUsuario", "");
			oM.setValueAt("Metodos.listarUsuariosPorTipoPerfil.EntradaMetodo.tipoFirmante", "");
			oM.setValueAt("Metodos.listarUsuariosPorTipoPerfil.EntradaMetodo.esUsuarioCMP", "");
			oM.setValueAt("Metodos.listarUsuariosPorTipoPerfil.EntradaMetodo.autFirmaOrdenes", "");
			oM.execute();
			icOMUsuarios = (IndexedCollection) oM
					.getElementAt("Metodos.listarUsuariosPorTipoPerfil.SalidaMetodo.RespuestaUsuario.Lista_Usuario");
			IndexedCollection icListaUsuarioOp = (IndexedCollection) getElementAt("listaUsuariosAuto");
			icListaUsuarioOp.removeAll();

			for (int i = 0; i < icOMUsuarios.size(); i++) {
				KeyedCollection listaUsuarioOm = (KeyedCollection) icOMUsuarios.getElementAt(i);
				KeyedCollection usuarioAutOp = (KeyedCollection) DataElement.getExternalizer()
						.convertTagToObject(icListaUsuarioOp.getElementSubTag());
				usuarioAutOp.setValueAt("id", listaUsuarioOm.getValueAt("codUsuario"));
				usuarioAutOp.setValueAt("name", listaUsuarioOm.getValueAt("nomUsuario"));
				icListaUsuarioOp.addElement(usuarioAutOp);
			}

		} catch (BbvaException e) {
			Trace.trace(Trace.VTF, "", MENSA_ERROR_EJE_OM + e.getMessage());
			
		} catch (DSEObjectNotFoundException e) {
			Trace.trace(Trace.VTF, "", MENSA_ERROR_OM + e.getMessage());
			
		} catch (DSEInvalidArgumentException e) {
			Trace.trace(Trace.VTF, "", MENSA_ERROR_ENV_OM + e.getMessage());
		}

	}
	
	
	private String cargarCuentaOrdenante() {
		 String cuentaOrdenate = "00";
		 OperacionMulticanal oM;
		 String strCodUsuario = "";
		try {
			strCodUsuario = getValueAt("s_cod_usuarisc").toString();
			//Creacion de OM webservices
			oM = creaOM("InformacionUsuarios_om");
			oM.setValueAt("Metodos.listarServiciosAsuntos.Ejecutar", "true");
			oM.setValueAt("Metodos.listarServiciosAsuntos.EntradaMetodo.codCanal", "0026");
		    oM.setValueAt("Metodos.listarServiciosAsuntos.EntradaMetodo.codEmpresa", getCodLogon());
		    oM.setValueAt("Metodos.listarServiciosAsuntos.EntradaMetodo.codBancoInterno", "0082");
		    oM.setValueAt("Metodos.listarServiciosAsuntos.EntradaMetodo.codUsuario", strCodUsuario);   
		    oM.setValueAt("Metodos.listarServiciosAsuntos.EntradaMetodo.codProducto","1380" );
		    oM.setValueAt("Metodos.listarServiciosAsuntos.EntradaMetodo.bancoAsunto","82" );
		    oM.setValueAt("Metodos.listarServiciosAsuntos.EntradaMetodo.codServicio",CODIGO_SERVICIO);
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
		    oM.execute();
		    IndexedCollection 	VectorSalidaGeneral;
		    VectorSalidaGeneral = (IndexedCollection) oM.getElementAt("Metodos.listarServiciosAsuntos.SalidaMetodo.RespuestaServicioCtx.Lista_ServicioCtx");
		    Enumeration VectorSalidaGeneralEnumeration = VectorSalidaGeneral.getEnumeration();
		    IndexedCollection VectorSalidaInterno = null;
		    KeyedCollection  kcVectorEntrada2 , kcListaAsunto; 
		    while(VectorSalidaGeneralEnumeration.hasMoreElements()){
		    	kcListaAsunto = (KeyedCollection) VectorSalidaGeneralEnumeration .nextElement();
		    	VectorSalidaInterno = (IndexedCollection) kcListaAsunto.getElementAt("Lista_Asunto");
		    	Enumeration VectorSalidaInternoEnumeration = VectorSalidaInterno.getEnumeration();
		    	while(VectorSalidaInternoEnumeration.hasMoreElements()){
		    		kcVectorEntrada2 = (KeyedCollection) VectorSalidaInternoEnumeration .nextElement();
		    		String tipoCuenta= kcVectorEntrada2.getValueAt("tipoAsunto").toString();
		    		cuentaOrdenate = tipoCuenta+kcVectorEntrada2.getValueAt("nuAsunto").toString();
		            break;
		    	}
		    }
		} catch (DSEObjectNotFoundException e) {
			Trace.trace(Trace.VTF, "", MENSA_ERROR_OM + e.getMessage());
		} catch (BbvaException e) {
			Trace.trace(Trace.VTF, "", MENSA_ERROR_EJE_OM + e.getMessage());
		 }catch(Exception e){
	    	Trace.trace(64, getClass().getName() + " falla ejecucion--Exception-- "+e.getMessage());
		}
		
		return cuentaOrdenate;
	}

	
	
  private String getDateForDay(int days) {
	  String format = "yyyy-MM-dd";
	  Calendar c = new GregorianCalendar();
	  c.add(Calendar.DAY_OF_MONTH,-days);
		SimpleDateFormat dateHourFormat = new SimpleDateFormat(format);
		String date = dateHourFormat.format( c.getTime());
	   return date;
  }
	
  private String getFechaActual(){
		Date date = Calendar.getInstance().getTime();
		String fechaFinal = "";
		DateFormat fechaFormatter = new SimpleDateFormat("yyyy-MM-dd");
		fechaFinal = fechaFormatter.format(date);
		return fechaFinal;	
	}
  
  private String formatDetealle(String numeroTransaccion, String usuario) {
	  StringBuilder detalle = new StringBuilder("  ")
			  .append(numeroTransaccion.trim())
			  .append("           ")
			  .append(usuario);
	        return detalle.toString();
  }
  

  
  private String getCodLogon() {
		try {
			String codLogon =  (String) getValueAt("s_cod_logon");
			return codLogon;
			
		} catch (DSEObjectNotFoundException e) {
			Trace.trace(Trace.VTF, "", "Error al obtener s_cod_logon " + e.getMessage());
		}
		return null;
	}


}