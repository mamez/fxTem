package com.grupobbva.col.servicios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.grupobbva.ii.sf.base.BbvaARQException;
import com.grupobbva.ii.sf.base.BbvaException;
import com.grupobbva.ii.sf.operacion.OmGestion;
import com.grupobbva.ii.sf.modelo.ConsultaBD;
import com.ibm.dse.base.DSEInvalidArgumentException;
import com.ibm.dse.base.DSEObjectNotFoundException;
import com.ibm.dse.base.Trace;

public class OmRollbackOpCreation extends OmGestion {
	
	
	private static final String ROLLBACK_CAB = "UPDATE  TLCL.TTLBHCAB SET COD_ESTACASH = '024', QNU_PESOFIR=0  WHERE COD_CLIECASH = ? AND COD_CLASEORD = ? AND COD_IDORDEN =?";
	private static final String ROLLBACK_FUA = "DELETE FROM TLCL.TTLBHFUA WHERE COD_CLIECASH = ? AND COD_CLASEORD = ? AND COD_IDORDEN = ? AND COD_ACCION != 1";
	
	private static final String DELETE_FUA = "DELETE FROM TLCL.TTLBHFUA WHERE COD_IDORDEN= ? AND COD_CLIECASH= ? ";
	private static final String DELETE_COR = "DELETE FROM TLCL.TTLBHCOR WHERE COD_IDORDEN= ? AND COD_CLIECASH= ? ";
	private static final String DELETE_CAB = "DELETE FROM TLCL.TTLBHCAB WHERE COD_IDORDEN= ? AND COD_CLIECASH= ? ";
	private static final String DELETE_MAN = "DELETE FROM TLNE.TTLNEMAN WHERE  TLNE_IDORDEN= ? AND TLNE_REFERENCIA= ? ";
	
	private Connection conn = null;
	private PreparedStatement stm = null;

	public OmRollbackOpCreation() {
		super();
	}

	public OmRollbackOpCreation(String anOperationName) throws java.io.IOException {
		super(anOperationName);
	}

	public OmRollbackOpCreation(String anOperationName, com.ibm.dse.base.Context aParentContext)
			throws java.io.IOException, com.ibm.dse.base.DSEInvalidRequestException {
		super(anOperationName, aParentContext);
	}

	public OmRollbackOpCreation(String anOperationName, String aParentContext) throws java.io.IOException,
			com.ibm.dse.base.DSEObjectNotFoundException, com.ibm.dse.base.DSEInvalidRequestException {
		super(anOperationName, aParentContext);
	}

	public void execute() throws BbvaException {
		try {
			String accion = (String) getValueAt("Entrada.accion");
			if(accion.equals("rollback")) {
				rollback();
			}else {
				deleteOrden();
			}
			
		} catch (DSEObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	/**
	 * Metodos de rollback
	 * @throws BbvaException
	 */
	private void rollback() throws BbvaException{
		String statusResponse = "NOK";
		try {
			Trace.trace(Trace.Debug, "", "Ejecutando OmRollbackOpCreation ...");
			String idOrden = (String) getValueAt("Entrada.idOrden");
			String referencia = (String) getValueAt("Entrada.referencia");
			String claseOrden = (String) getValueAt("Entrada.claseOrden");
			Trace.trace(Trace.Debug, "", "referencia: " + referencia);
			Trace.trace(Trace.Debug, "", "idOrden: " + idOrden);
			executeFua(referencia, claseOrden, idOrden);
			executeCab(referencia, claseOrden, idOrden);
			statusResponse = "OK";
		} catch (DSEObjectNotFoundException e) {
			Trace.trace(Trace.Error, "", "Error al obtener datos: " + e.getMessage());
		} catch (SQLException e) {
			Trace.trace(Trace.Error, "", "Error al ejecutar query: " + e.getMessage());
		}

		try {
			setValueAt("Salida.status", statusResponse);
		} catch (Exception e) {
			Trace.trace(Trace.Error, "", "Error al obtener datos: " + e.getMessage());
		}
	}

	private void executeFua(String referencia, String claseOrden, String idOrden)
			throws BbvaARQException, SQLException {
		Trace.trace(Trace.Debug, "", "Ejecutando Rollback FUA");
		conn = ConsultaBD.getConexion("BDMexico");
		stm = conn.prepareStatement(ROLLBACK_FUA);
		stm.setString(1, referencia);
		stm.setString(2, claseOrden);
		stm.setString(3, idOrden);
		stm.execute();
		if (stm != null) {
			stm.close();
		}
		if (conn != null) {
			conn.close();
		}
		Trace.trace(Trace.Debug, "", "Ejecutando Rollback FUA ..... OK");
	}

	private void executeCab(String referencia, String claseOrden, String idOrden)
			throws BbvaARQException, SQLException {
		Trace.trace(Trace.Debug, "", "Ejecutando Rollback CAB");
		conn = ConsultaBD.getConexion("BDMexico");
		stm = conn.prepareStatement(ROLLBACK_CAB);
		stm.setString(1, referencia);
		stm.setString(2, claseOrden);
		stm.setString(3, idOrden);
		stm.execute();
		if (stm != null) {
			stm.close();
		}
		if (conn != null) {
			conn.close();
		}
		Trace.trace(Trace.Debug, "", "Ejecutando Rollback CAB ..... OK");
	}
	
	
	/**
	 * Metodos de Eliminar ornden
	 */
	private void deleteOrden() {
		try {
			String idOrden = (String) getValueAt("Entrada.idOrden");
			String referencia = (String) getValueAt("Entrada.referencia");
			Trace.trace(Trace.Debug, "", "referencia: " + referencia);
			Trace.trace(Trace.Debug, "", "idOrden: " + idOrden);
			String codClient="00260082"+getValueAt("s_cod_logon").toString(); 
			String refMan=getValueAt("s_cod_logon").toString()+getValueAt("s_cod_usuarisc").toString();
			
			deleteFua(idOrden,codClient);
			deleteCor(idOrden,codClient);
			deleteCab(idOrden, codClient);
			deleteMan(idOrden, refMan);
			
			setValueAt("Salida.status", "OK");
			
		} catch (DSEObjectNotFoundException e) {
			Trace.trace(Trace.VTF, "", "Error al leer propiedad : " + e.getMessage());
		} catch (DSEInvalidArgumentException e) {
			Trace.trace(Trace.Error, "", "Error al obtener datos: " + e.getMessage());
		}
		
	}
	
	
	private void deleteFua( String idOrden, String referencia) {
		Trace.trace(Trace.VTF, "", "ejecutando deleteUserConfig() :");
		try {
			conn = ConsultaBD.getConexion("BDMexico");
			stm = conn.prepareStatement(DELETE_FUA);
			stm.setString(1, idOrden);
			stm.setString(2, referencia);
			stm.execute();
		} catch (BbvaARQException e) {
			Trace.trace(Trace.VTF, "", "Error al conectar a la base de datos: " + e.getMessage());
		} catch (SQLException e) {
			Trace.trace(Trace.VTF, "", "Error al  ejecutar query: " + e.getMessage());
		}finally {
			closeConection();
		}
		
	}
	
	private void deleteCor( String idOrden, String referencia) {
		Trace.trace(Trace.VTF, "", "ejecutando deleteCor() :");
		try {
			conn = ConsultaBD.getConexion("BDMexico");
			stm = conn.prepareStatement(DELETE_COR);
			stm.setString(1, idOrden);
			stm.setString(2, referencia);
			stm.execute();
		} catch (BbvaARQException e) {
			Trace.trace(Trace.VTF, "", "Error al conectar a la base de datos: " + e.getMessage());
		} catch (SQLException e) {
			Trace.trace(Trace.VTF, "", "Error al  ejecutar query: " + e.getMessage());
		}finally {
			closeConection();
		}
		
	}
	
	private void deleteCab( String idOrden, String codClient) {
		Trace.trace(Trace.VTF, "", "ejecutando deleteCab() :");
		try {
			conn = ConsultaBD.getConexion("BDMexico");
			stm = conn.prepareStatement(DELETE_CAB);
			
			stm.setString(1, idOrden);
			stm.setString(2, codClient);
			stm.execute();
		} catch (BbvaARQException e) {
			Trace.trace(Trace.VTF, "", "Error al conectar a la base de datos: " + e.getMessage());
		} catch (SQLException e) {
			Trace.trace(Trace.VTF, "", "Error al  ejecutar query: " + e.getMessage());
		}finally {
			closeConection();
		}
		
	}
	
	private void deleteMan( String idOrden, String refMan) {
		Trace.trace(Trace.VTF, "", "ejecutando deleteMan() :");
		try {
			
			conn = ConsultaBD.getConexionBBVNet();
			stm = conn.prepareStatement(DELETE_MAN);
			stm.setString(1, idOrden);
			stm.setString(2, refMan);
			stm.execute();
		} catch (BbvaARQException e) {
			Trace.trace(Trace.VTF, "", "Error al conectar a la base de datos: " + e.getMessage());
		} catch (SQLException e) {
			Trace.trace(Trace.VTF, "", "Error al  ejecutar query: " + e.getMessage());
		}finally {
			closeConection();
		}
		
	}
	
	/**
	 * Metodos genericos
	 */
	private void closeConection() {
		try {
			if (stm != null)
				stm.close();
		} catch (SQLException aSQLExcII) {
			Trace.trace(Trace.VTF, "", "Error al cerrar stm : " + aSQLExcII.getMessage());
		}

		try {
			if (conn != null)
				if (!conn.isClosed())
					conn.close();
		} catch (SQLException aSQLExcIII) {
			Trace.trace(Trace.VTF, "", "Error al cerrar conn : " + aSQLExcIII.getMessage());
		}
	}

	
}
