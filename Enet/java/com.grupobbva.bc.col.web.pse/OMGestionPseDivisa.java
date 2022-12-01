package com.grupobbva.bc.col.web.pse;

import java.io.IOException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.grupobbva.ii.sf.base.BbvaARQException;
import com.grupobbva.ii.sf.base.BbvaException;
import com.grupobbva.ii.sf.modelo.ConsultaBD;
import com.grupobbva.ii.sf.operacion.OmGestion;
import com.ibm.dse.base.DSEInvalidArgumentException;
import com.ibm.dse.base.DSEObjectNotFoundException;
import com.ibm.dse.base.DataElement;
import com.ibm.dse.base.IndexedCollection;
import com.ibm.dse.base.KeyedCollection;
import com.ibm.dse.base.Trace;

public class OMGestionPseDivisa extends OmGestion {
	private Connection conn = null;
	private PreparedStatement stm = null;
	private int limInferior = 0;
	private int limiteSuperior = 0;
	private ResultSet rs = null;
	private static final String PATTER_HORA = "HH:mm";
	
	private static String MENS_ERROR_PRO = "Error al leer propiedad :";
	private static String MENS_ERROR_CONEC_BD = "Error al conectar a la base de datos:";
	private static String MENS_ERROR_QUERY = "Error al ejecutar query:";
	private static String MENS_ERROR_CONEC = "Error al cerrar conexion";
	private static String COD_ARQUI_BBVA = "APP1700108";
	private static String COD_ARQ_BBVA = "ARQ0300600";
	private static String LISTA_USUA_PAG = "Salida.listarUsuario.pagina";
	private static String REFERENCIA = "referencia :";
	

	private static String TLNE_TRANSACI_EJEC = "TLNE_TRANSACI_EJEC";
	private static String TLNE_VALOR_UTILIZ = "TLNE_VALOR_UTILIZ";

	private static String COUNT_REGISTERS = "SELECT count(*) as total FROM TLNE.TTLNEPDP ";
	private static String ALL_USER_CONFIGURATION = "SELECT * FROM TLNE.TTLNEPDP ";
	private static String FILTRO_FECHA_TRANSACCION = " WHERE TLNE_FECHA_TRANS BETWEEN ? AND ? ";
	private static String FILTRO_REFERENCIA_EMPRESA = " AND TLNE_REFERENCIA = ?";
	private static String FILTRO_NUMERO_TRANSACCION = " AND TLNE_NUM_TRANS = ?";
	private static String ORDEN_BY_FECHA = " ORDER BY TLNE_FECHA_AUDI DESC";
	
	private static String ALL_USER_ORDEN = "SELECT * FROM TLNE.TTLNEPDP WHERE TLNE_FECHA_TRANS = CURRENT DATE  AND TLNE_USUARIO_AUDI = ?  AND TLNE_ESTADO = 'AUTORIZADO' ";

	private static String ORDEN_DETAIL = "SELECT TLNE_TRANSACI_EJEC, TLNE_VALOR_UTILIZ FROM TLNE.TTLNEPDP WHERE  TLNE_NUM_TRANS = ? ";
	private static String UPDATE_ORDEN_DETAIL = "UPDATE TLNE.TTLNEPDP SET TLNE_TRANSACI_EJEC = ?, TLNE_VALOR_UTILIZ = ?, TLNE_CODIGO_BANKTRADE= ?, TLNE_ESTADO = 'EJECUTADO', TLNE_FECHA_EJECUC = CURRENT DATE WHERE TLNE_NUM_TRANS = ?";

	private static String CREATE_USER_CONFIGURATION = "INSERT INTO TLNE.TTLNEPDP(TLNE_REFERENCIA, TLNE_USUARIO, TLNE_NUM_TRANS, TLNE_VALOR_DESDE, TLNE_VALOR_HASTA, TLNE_FECHA_TRANS, TLNE_ESTADO, TLNE_TIPO_PAGO, TLNE_USUARIO_AUDI, TLNE_IP_USUARIO_AUDI,TLNE_CANTI_PAG_AUTO,TLNE_TRANSACI_EJEC,TLNE_VALOR_UTILIZ,TLNE_CODIGO_BANKTRADE,TLNE_FECHA_AUDI,TLNE_FECHA_EJECUC)"
			+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,CURRENT TIMESTAMP,CURRENT DATE)";

	private static String DELETE_USER_CONFIGURATION = "DELETE FROM TLNE.TTLNEPDP WHERE TLNE_NUM_TRANS= ?";

	private static String UPDATE_ESTADO_USER = "UPDATE TLNE.TTLNEPDP SET TLNE_ESTADO = 'AUTORIZADO',TLNE_FECHA_AUDI = CURRENT TIMESTAMP WHERE  TLNE_REFERENCIA = ? "
			+ " AND  TLNE_NUM_TRANS = ( SELECT man.TLNE_BPALACC2 FROM TLNE.TTLNEMAN man WHERE man.TLNE_IDORDEN = ? )";

	private static String SEARCH_IDORDEN = "SELECT TLNE_IDORDEN FROM TLNE.TTLNEMAN WHERE TLNE_BPALACC2  = ?";
	
	private static String SEARCH_OPERATION= "SELECT * FROM TLNE.TTLNEPDP WHERE  TLNE_CODIGO_BANKTRADE = ? ";

	private static String UPDATE_INTENTO= "UPDATE TLNE.TTLNEPDP SET TLNE_TRANSACI_EJEC = ? WHERE  TLNE_CODIGO_BANKTRADE = ? ";
	
	public OMGestionPseDivisa() {
		super();
	}

	public OMGestionPseDivisa(String anOperationName) throws java.io.IOException {
		super(anOperationName);
	}

	public OMGestionPseDivisa(String anOperationName, com.ibm.dse.base.Context aParentContext)
			throws java.io.IOException, com.ibm.dse.base.DSEInvalidRequestException {
		super(anOperationName, aParentContext);
	}

	public OMGestionPseDivisa(String anOperationName, String aParentContext) throws java.io.IOException,
			com.ibm.dse.base.DSEObjectNotFoundException, com.ibm.dse.base.DSEInvalidRequestException {
		super(anOperationName, aParentContext);
	}

	public void execute() throws BbvaException {
		try {
			String metodoParameter = (String) getValueAt("Entrada.metodo");
			MetodoEnum metodo = MetodoEnum.valueOf(metodoParameter);
			switch (metodo) {
			case LISTAR_USUARIOS:
				listUsersByFilter();
				break;
			case CONFIGURAR_USUARIO:
				configureUser();
				break;
			case ELIMINAR_USUARIO:
				deleteUserConfig();
				break;
			case ACTUALIZAR_ORDEN:
				updateOrdenDetail();
				break;
			case AUTORIZAR_ORDEN:
				autorizar();
				break;
			case LISTAR_USUARIOS_ORDEN:
				listUserByOrder();
				break;
			case LISTAR_OPERACION:
				searchOperation();
				break;
			case ACTUALIZAR_REINTENTO:
				actualizarReintento();
				break;
			default:
				autorizar();
				break;
			}
		} catch (DSEObjectNotFoundException e) {
			e.printStackTrace();
		}
	}
	private void actualizarReintento() throws BbvaARQException {
		Trace.trace(Trace.VTF, "", "ejecutando actualizarReintento() :");
		try {                                       
			String numOperacion=(String)getValueAt("Entrada.actualizarReintento.bankTrade");
			String strReintento=(String) getValueAt("Entrada.actualizarReintento.reintento");
			int reintento=Integer.parseInt(strReintento); 
			conn = getConnection();         
			stm = conn.prepareStatement(UPDATE_INTENTO);
			stm.setInt(1, reintento);			
			stm.setString(2,numOperacion);			
			stm.executeUpdate();
			setValueAt("Salida.actualizarTransacci.status", "OK");			
		}catch (Exception e) {
			Trace.trace(Trace.VTF, "", MENS_ERROR_PRO + e.getMessage());
			BbvaARQException Nge = construirARQE(COD_ARQUI_BBVA, e, "");
			throw Nge;
		} finally {
			// INI 503 CMC 04-05-2022
			try {
				stm.close();
				if (stm != null) {
					stm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException aSQLExcII) {
				Trace.trace(Trace.VTF, "", MENS_ERROR_CONEC + aSQLExcII.getMessage());
			}
		}
		Trace.trace(Trace.VTF, "", "finalizando actualizarReintento() :");
	}
	
	private void searchOperation() throws BbvaARQException {
		// TODO Auto-generated method stub		
		Trace.trace(Trace.VTF, "", "ejecutando searchOperation() :");
		
		try{
			String numOperacion=(String) getValueAt("Entrada.filtroConsulta.banckTrade");
			Trace.trace(Trace.VTF, "", "numOperacuion : " + numOperacion);
			IndexedCollection icUsuariosConfig = (IndexedCollection) getElementAt("Salida.listarUsuarioOrden.usuarios");
			icUsuariosConfig.removeAll();
			conn = getConnection();
			stm = conn.prepareStatement(SEARCH_OPERATION);
			stm.setString(1, numOperacion);
			rs = stm.executeQuery();			
			while (rs.next()) {
			   mapAutorization(rs, icUsuariosConfig);
			}
		}catch (Exception e) {
			Trace.trace(Trace.VTF, "", MENS_ERROR_PRO + e.getMessage());
			BbvaARQException Nge = construirARQE(COD_ARQUI_BBVA, e, "");
			throw Nge;
		} finally {
			// INI 503 CMC 04-05-2022
			try {
				rs.close();
				if (stm != null) {
					stm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException aSQLExcII) {
				Trace.trace(Trace.VTF, "", MENS_ERROR_CONEC + aSQLExcII.getMessage());
			}
		}
		Trace.trace(Trace.VTF, "", "finalizando searchOperation() :");
	}

	private void listUsersByFilter() throws BbvaARQException {
		Trace.trace(Trace.VTF, "", "ejecutando listUsersByFilter() :");
		try {
			List<Object> parameters = new ArrayList<Object>();
			String fechaDesde = (String) getValueAt("Entrada.listarUsuario.fechaDesde");
			String fechaHasta = (String) getValueAt("Entrada.listarUsuario.fechahasta");
			String numTransaccion = (String) getValueAt("Entrada.listarUsuario.numTransaccion");
			String referencia = getCodEmpre();

			Trace.trace(Trace.VTF, "", "parameters : " + parameters);
			Trace.trace(Trace.VTF, "", "fechaDesde : " + fechaDesde);
			Trace.trace(Trace.VTF, "", "fechaHasta : " + fechaHasta);
			Trace.trace(Trace.VTF, "", "numTransaccion : " + numTransaccion);
			Trace.trace(Trace.VTF, "", REFERENCIA + referencia);
		

			StringBuilder queryCount = new StringBuilder(COUNT_REGISTERS);
			StringBuilder query = new StringBuilder(ALL_USER_CONFIGURATION);

			if (fechaDesde != null && fechaHasta != null) {
				query.append(FILTRO_FECHA_TRANSACCION);
				queryCount.append(FILTRO_FECHA_TRANSACCION);
				parameters.add(fechaDesde);
				parameters.add(fechaHasta);
			}
			if (referencia != null) {
				query.append(FILTRO_REFERENCIA_EMPRESA);
				queryCount.append(FILTRO_REFERENCIA_EMPRESA);
				parameters.add(referencia);
			}
			if (numTransaccion != null) {
				query.append(FILTRO_NUMERO_TRANSACCION);
				queryCount.append(FILTRO_NUMERO_TRANSACCION);
				parameters.add(numTransaccion);
			}
			
			query.append(ORDEN_BY_FECHA);
		
			
			buildPaginacion(parameters, queryCount.toString());
			conn = getConnection();
			stm = conn.prepareStatement(query.toString());
			int i = 1;
			for (Object parameter : parameters) {
				stm.setObject(i++, parameter);
			}
			rs = stm.executeQuery();
			IndexedCollection icUsuariosConfig = (IndexedCollection) getElementAt("Salida.listarUsuario.usuarios");
			icUsuariosConfig.removeAll();
			while (rs.next()) {
				int row = rs.getRow();
				if (row >= limInferior && row <= limiteSuperior) {
					mapAutorization(rs, icUsuariosConfig);
				}
			}
			// System.out.println(icUsuariosConfig);
		} catch (Exception e) {
			Trace.trace(Trace.VTF, "", MENS_ERROR_PRO + e.getMessage());
			BbvaARQException Nge = construirARQE(COD_ARQUI_BBVA, e, "");
			throw Nge;
		} finally {
			// INI 503 CMC 04-05-2022
			try {
				rs.close();
				if (stm != null) {
					stm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException aSQLExcII) {
				Trace.trace(Trace.VTF, "", MENS_ERROR_CONEC + aSQLExcII.getMessage());
			}
		}
		// FIN 503 CMC 04-05-2022
	}

	private void listUserByOrder() {
		Trace.trace(Trace.VTF, "", "ejecutando listUserOrden() :");
		try {
			String usuarioAudi = (String) getValueAt("datosAPP.iv-cod_usu");
			conn = getConnection();
			stm = conn.prepareStatement(ALL_USER_ORDEN);
			stm.setString(1, usuarioAudi);
			rs = stm.executeQuery();
			IndexedCollection icUsuariosConfig = (IndexedCollection) getElementAt("Salida.listarUsuarioOrden.usuarios");
			icUsuariosConfig.removeAll();
			while (rs.next()) {
               mapAutorization(rs, icUsuariosConfig);
			}
		} catch (Exception e) {
			Trace.trace(Trace.VTF, "", MENS_ERROR_PRO + e.getMessage());
		}finally {
			// INI 503 CMC 04-05-2022
			try {
				rs.close();
				if (stm != null) {
					stm.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException aSQLExcII) {
				Trace.trace(Trace.VTF, "", MENS_ERROR_CONEC + aSQLExcII.getMessage());
			}
			// FIN 503 CMC 04-05-2022
		}

	}

	private void configureUser() throws BbvaARQException {
		try {
			String usuario = (String) getValueAt("Entrada.configurarUsuario.usuario");
			double valorDesde = new Double((String) getValueAt("Entrada.configurarUsuario.valorDesde"));
			double valorHasta = new Double((String) getValueAt("Entrada.configurarUsuario.valorHasta"));
			String fechaTransa = (String) getValueAt("Entrada.configurarUsuario.fechaTrans");
			String tipoPago = (String) getValueAt("Entrada.configurarUsuario.tipoPago");
			String usuarioAudi = (String) getValueAt("datosAPP.iv-cod_usu");
			String ipUsuarioAudi = (String) getValueAt("datosAPP.iv-remote-address");
			String referencia = getCodEmpre();

			Trace.trace(Trace.VTF, "", "ejecutando configureUser() :");
			Trace.trace(Trace.VTF, "", "usuario : " + usuario);
			Trace.trace(Trace.VTF, "", "valorDesde : " + valorDesde);
			Trace.trace(Trace.VTF, "", "valorHasta : " + valorHasta);
			Trace.trace(Trace.VTF, "", "fechaTrans : " + fechaTransa);
			Trace.trace(Trace.VTF, "", "tipoPago : " + tipoPago);
			Trace.trace(Trace.VTF, "", "usuarioAudi : " + usuarioAudi);
			Trace.trace(Trace.VTF, "", "ipUsuarioAudi : " + ipUsuarioAudi);
			Trace.trace(Trace.VTF, "", REFERENCIA + referencia);
			String numeroTran = getNumTransa();
			conn = getConnection();
			stm = conn.prepareStatement(CREATE_USER_CONFIGURATION);

			stm.setString(1, referencia);
			stm.setString(2, usuario);
			stm.setString(3, numeroTran);
			stm.setDouble(4, valorDesde);
			stm.setDouble(5, valorHasta);
			stm.setString(6, fechaTransa);
			stm.setString(7, "CREADO");
			stm.setString(8, tipoPago);
			stm.setString(9, usuarioAudi);
			stm.setString(10, ipUsuarioAudi);
			stm.setInt(11, 1);
			stm.setInt(12, 0);
			stm.setDouble(13, 0);
			stm.setString(14, "");
			stm.executeUpdate();
			Trace.trace(Trace.VTF, "", "Query : " + CREATE_USER_CONFIGURATION);
			setValueAt("Salida.configurarUsuario.status", "OK");
			setValueAt("Salida.configurarUsuario.numTransaccion", numeroTran);
		} catch (DSEObjectNotFoundException e) {
			Trace.trace(Trace.VTF, "", MENS_ERROR_PRO + e.getMessage());
			BbvaARQException Nge = construirARQE(COD_ARQUI_BBVA, e, "");
			throw Nge;
		} catch (BbvaARQException e) {
			Trace.trace(Trace.VTF, "", MENS_ERROR_CONEC_BD + e.getMessage());
			BbvaARQException Nge = construirARQE(COD_ARQUI_BBVA, e, "");
			throw Nge;
		} catch (SQLException e) {
			Trace.trace(Trace.VTF, "", MENS_ERROR_QUERY + e.getMessage());
			throw new BbvaARQException(COD_ARQ_BBVA, BbvaARQException.DB2, e.getSQLState(), e);
		} catch (DSEInvalidArgumentException e) {
			Trace.trace(Trace.VTF, "", "Error al conectar  propiedad : " + e.getMessage());
			BbvaARQException Nge = construirARQE(COD_ARQUI_BBVA, e, "");
			throw Nge;
		} finally {
			// INI 503 CMC 04-05-2022
			try {
				if (stm != null && (!stm.isClosed())) {
					stm.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}

			} catch (SQLException aSQLExcII) {
				Trace.trace(Trace.VTF, "", MENS_ERROR_CONEC + aSQLExcII.getMessage());
			}
			// FIN 503 CMC 04-05-2022
		}
	}

	private void deleteUserConfig() throws BbvaARQException {
		try {
			Trace.trace(Trace.VTF, "", "ejecutando deleteUserConfig() :");
			String numTransaccion = (String) getValueAt("Entrada.eliminarUsuario.numTransaccion");
			Trace.trace(Trace.VTF, "", "Numero tran: " + numTransaccion);
			conn = getConnection();
			stm = conn.prepareStatement(DELETE_USER_CONFIGURATION);
			stm.setString(1, numTransaccion);
			stm.executeUpdate();

			setValueAt("Salida.eliminarUsuario.idOrden", getIdOrden());
			setValueAt("Salida.eliminarUsuario.status", "OK");
			Trace.trace(Trace.VTF, "", "ejecutando deleteUserConfig() : OK");
		} catch (BbvaARQException e) {
			Trace.trace(Trace.VTF, "", MENS_ERROR_CONEC_BD + e.getMessage());
			BbvaARQException Nge = construirARQE(COD_ARQUI_BBVA, e, "");
			throw Nge;
		} catch (SQLException e) {
			Trace.trace(Trace.VTF, "", MENS_ERROR_QUERY + e.getMessage());
			throw new BbvaARQException(COD_ARQ_BBVA, BbvaARQException.DB2, e.getSQLState(), e);
		} catch (DSEObjectNotFoundException e) {
			Trace.trace(Trace.VTF, "", MENS_ERROR_PRO + e.getMessage());
			BbvaARQException Nge = construirARQE(COD_ARQUI_BBVA, e, "");
			throw Nge;
		} catch (DSEInvalidArgumentException e) {
			Trace.trace(Trace.VTF, "", MENS_ERROR_PRO + e.getMessage());
			BbvaARQException Nge = construirARQE(COD_ARQUI_BBVA, e, "");
			throw Nge;
		} finally {
			// INI 503 CMC 04-05-2022
			try {
				if (stm != null && (!stm.isClosed())) {
					stm.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}

			} catch (SQLException aSQLExcII) {
				Trace.trace(Trace.VTF, "", MENS_ERROR_CONEC + aSQLExcII.getMessage());
			}
			// FIN 503 CMC 04-05-2022
		}
	}

	private String getIdOrden() {
		String idOrden = "";
		Trace.trace(Trace.VTF, "", "ejecutando getIdOrden() :");
		try {
			String numTransaccion = (String) getValueAt("Entrada.eliminarUsuario.numTransaccion");
			conn = getConnection();
			stm = conn.prepareStatement(SEARCH_IDORDEN);
			stm.setString(1, numTransaccion);
			rs = stm.executeQuery();
			while (rs.next()) {
				idOrden = rs.getString("TLNE_IDORDEN");

			}

		} catch (DSEObjectNotFoundException e) {
			Trace.trace(Trace.VTF, "", MENS_ERROR_PRO + e.getMessage());
		} catch (BbvaARQException e) {
			Trace.trace(Trace.VTF, "", MENS_ERROR_CONEC_BD + e.getMessage());
		} catch (SQLException e) {
			Trace.trace(Trace.VTF, "", MENS_ERROR_QUERY + e.getMessage());
		} finally {
			// INI 503 CMC 04-05-2022
			try {
				rs.close();
				if (stm != null && (!stm.isClosed())) {
					stm.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException aSQLExcII) {
				Trace.trace(Trace.VTF, "", MENS_ERROR_CONEC + aSQLExcII.getMessage());
			}
			// FIN 503 CMC 04-05-2022
		}
		return idOrden;
	}

	private void updateOrdenDetail() throws BbvaARQException {

		try {
			double valorUtil = new Double((String) getValueAt("Entrada.actualizarTransacci.valorUtilizado"));
			String numTransa = (String) getValueAt("Entrada.actualizarTransacci.numTransaccion");
			String bankTrade = (String) getValueAt("Entrada.actualizarTransacci.banckTrade");
			
			OrdenDetailDto ordenDetail = getOrdenDetail(valorUtil, numTransa,bankTrade );

			conn = getConnection();
			stm = conn.prepareStatement(UPDATE_ORDEN_DETAIL);
			stm.setInt(1, ordenDetail.getTransaccionEjecutadas());
			stm.setDouble(2, ordenDetail.getValorUtilizado());
			stm.setString(4, ordenDetail.getNumeroTran());
			stm.setString(3, bankTrade);
			stm.executeUpdate();

			setValueAt("Salida.actualizarTransacci.status", "OK");
		} catch (BbvaARQException e) {
			Trace.trace(Trace.VTF, "", "Error alconectar a la base de datos: " + e.getMessage());
		} catch (SQLException e) {
			Trace.trace(Trace.VTF, "", "Error al al ejecutar query: " + e.getMessage());
		} catch (DSEObjectNotFoundException e) {
			Trace.trace(Trace.VTF, "", MENS_ERROR_PRO + e.getMessage());
		} catch (DSEInvalidArgumentException e) {
			Trace.trace(Trace.VTF, "", MENS_ERROR_PRO + e.getMessage());
			BbvaARQException Nge = construirARQE(COD_ARQUI_BBVA, e, "");
			throw Nge;
		} finally {
			try {
				if (stm != null && (!stm.isClosed())) {
					stm.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException aSQLExcII) {
				Trace.trace(Trace.VTF, "", MENS_ERROR_CONEC + aSQLExcII.getMessage());
			}
		}

	}

	public void autorizar() throws BbvaARQException {
		try {
			String idOrden = (String) getValueAt("Entrada.autorizar.idOrden");
			String referencia = getCodEmpre();

			Trace.trace(Trace.VTF, "", "ejecutando autorizar() :");
			Trace.trace(Trace.VTF, "", "idOrden : " + idOrden);
			Trace.trace(Trace.VTF, "", REFERENCIA + referencia);

			conn = getConnection();
			stm = conn.prepareStatement(UPDATE_ESTADO_USER);
			stm.setString(1, referencia);
			stm.setString(2, idOrden);
			stm.executeUpdate();
			setValueAt("Salida.autorizar.status", "OK");
		} catch (DSEObjectNotFoundException e) {
			Trace.trace(Trace.VTF, "", MENS_ERROR_PRO + e.getMessage());
		} catch (DSEInvalidArgumentException e) {
			Trace.trace(Trace.VTF, "", "Error al ingresar propiedad : " + e.getMessage());
			BbvaARQException Nge = construirARQE(COD_ARQUI_BBVA, e, "");
			throw Nge;
		} catch (BbvaARQException e) {
			Trace.trace(Trace.VTF, "", MENS_ERROR_CONEC_BD + e.getMessage());
		} catch (SQLException e) {
			Trace.trace(Trace.VTF, "", MENS_ERROR_QUERY + e.getMessage());
		} finally {
			// INI 503 CMC 04-05-2022
			try {
				if (stm != null && (!stm.isClosed())) {
					stm.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException aSQLExcII) {
				Trace.trace(Trace.VTF, "", MENS_ERROR_CONEC + aSQLExcII.getMessage());
			}
			// FIN 503 CMC 04-05-2022
		}
	}

	private OrdenDetailDto getOrdenDetail(double valorUtil, String numTransa, String bankTrade) {
		OrdenDetailDto orden = new OrdenDetailDto(numTransa, bankTrade, valorUtil);
		try {
			conn = getConnection();
			stm = conn.prepareStatement(ORDEN_DETAIL);
			stm.setString(1, numTransa);
			rs = stm.executeQuery();
			while (rs.next()) {
				orden.setTransaccionEjecutadas(rs.getInt(TLNE_TRANSACI_EJEC));
				orden.setValorUtilizado(rs.getDouble(TLNE_VALOR_UTILIZ));
			}
		} catch (BbvaARQException e) {
			Trace.trace(Trace.VTF, "", "Error alconectar a la base de datos: " + e.getMessage());
		} catch (SQLException e) {
			Trace.trace(Trace.VTF, "", "Error al al ejecutar query: " + e.getMessage());
		} finally {
			try {
				// INI 503 CMC 04-05-2022
				rs.close();

				if (stm != null && (!stm.isClosed())) {
					stm.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException aSQLExcII) {
				Trace.trace(Trace.VTF, "", MENS_ERROR_CONEC + aSQLExcII.getMessage());
			}
			// FIN 503 CMC 04-05-2022
		}

		return orden;

	}

	private void buildPaginacion(List<Object> parameters, String queryCount) throws Exception {
		int total = 0;
		int tamano = 10;
		try {
			int page = Integer.parseInt((String) getValueAt("Entrada.listarUsuario.pagina"));
			conn = getConnection();
			stm = conn.prepareStatement(queryCount);
			int i = 1;
			for (Object parameter : parameters) {
				stm.setObject(i++, parameter);
			}
			rs = stm.executeQuery();
			while (rs.next()) {
				total = rs.getInt("total");
			}
			if (total <= tamano) {
				this.limInferior = 1;
				this.limiteSuperior = 10;
				setValueAt(LISTA_USUA_PAG, 1);
			} else {
				this.limInferior = ((page - 1) * 10) + (1);
				this.limiteSuperior = (limInferior - 1) + tamano;
				setValueAt(LISTA_USUA_PAG, page + 1);
				if (limiteSuperior >= total) {
					this.limiteSuperior = total;
					setValueAt(LISTA_USUA_PAG, page);
				}
			}
		} catch (Exception e) {
			Trace.trace(Trace.VTF, "", "Error al calcular total registros : " + e.getMessage());
			throw e;
		} finally {
			// INI 503 CMC 04-05-2022
			try {

				rs.close();
				if (stm != null && (!stm.isClosed())) {
					stm.close();
				}
				if (conn != null && (!conn.isClosed())) {
					conn.close();
				}

			} catch (SQLException aSQLExcII) {
				Trace.trace(Trace.VTF, "", MENS_ERROR_CONEC + aSQLExcII.getMessage());
			}
			// FIN 503 CMC 04-05-2022
		}
	}

	private String getNumTransa() {
		 SecureRandom random = new SecureRandom();
		int aleatorio = random.nextInt(10);
		String format = "ddMMyyhhmmss";
		SimpleDateFormat dateHourFormat = new SimpleDateFormat(format);
		String date = dateHourFormat.format(new Date());
		return aleatorio + date;
	}

	private String getCodEmpre() {
		try {
			String referenciaEmp = (String) getValueAt("datosAPP.iv-cod_emp");
			return referenciaEmp;

		} catch (DSEObjectNotFoundException e) {
			Trace.trace(Trace.VTF, "", "Error al obtener referencia de empresa: " + e.getMessage());
		}
		return null;
	}
	
	private void mapAutorization(ResultSet rs, IndexedCollection icUsuariosConfig) throws SQLException, IOException, DSEInvalidArgumentException, DSEObjectNotFoundException {
		Trace.trace(Trace.VTF, "", "ejecutando mapAutorization() :");
		KeyedCollection kcusuarioConfig = (KeyedCollection) DataElement.getExternalizer()
				.convertTagToObject(icUsuariosConfig.getElementSubTag());
		String estado =rs.getString("TLNE_ESTADO");
		kcusuarioConfig.setValueAt("estado", rs.getString("TLNE_ESTADO").trim());
		
		kcusuarioConfig.setValueAt("referencia", rs.getString("TLNE_REFERENCIA").trim());
		kcusuarioConfig.setValueAt("usuarioAut", rs.getString("TLNE_USUARIO").trim());
		kcusuarioConfig.setValueAt("numTransaccion", rs.getString("TLNE_NUM_TRANS").trim());
		kcusuarioConfig.setValueAt("valorDesde", rs.getString("TLNE_VALOR_DESDE").trim());
		kcusuarioConfig.setValueAt("valorHasta", rs.getString("TLNE_VALOR_HASTA").trim());
		kcusuarioConfig.setValueAt("fechaTrans", rs.getString("TLNE_FECHA_TRANS").trim());
		Timestamp dbSqlTimestamp = rs.getTimestamp("TLNE_FECHA_AUDI");
		Date fecha = new Date(dbSqlTimestamp.getTime());
		SimpleDateFormat simpleDateFormat= new SimpleDateFormat(PATTER_HORA);
		kcusuarioConfig.setValueAt("hora", simpleDateFormat.format(fecha));
		kcusuarioConfig.setValueAt("tipoPago", rs.getString("TLNE_TIPO_PAGO").trim());
		kcusuarioConfig.setValueAt("usuarioAudi", rs.getString("TLNE_USUARIO_AUDI").trim());
		kcusuarioConfig.setValueAt("ipUsuarioAudi", rs.getString("TLNE_IP_USUARIO_AUDI").trim());
		if (!estado.equals("EJECUTADO")){
			kcusuarioConfig.setValueAt("transaciEject", "0");
			kcusuarioConfig.setValueAt("valorUtilizado","0");
			kcusuarioConfig.setValueAt("fechaEjecu", "");

		}else {
			kcusuarioConfig.setValueAt("transaciEject", rs.getString("TLNE_TRANSACI_EJEC") );
			kcusuarioConfig.setValueAt("valorUtilizado", rs.getString("TLNE_VALOR_UTILIZ"));
			kcusuarioConfig.setValueAt("fechaEjecu", rs.getString("TLNE_FECHA_EJECUC"));
		}
		icUsuariosConfig.addElement(kcusuarioConfig);
	}
	
	 private Connection getConnection() throws BbvaARQException {
	 return ConsultaBD.getConexionBBVNet();
	 }
	 
}
