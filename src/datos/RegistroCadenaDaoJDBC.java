package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import domain.Evidencia;
import domain.RegistroCadenaCustodia;

public class RegistroCadenaDaoJDBC {
	
	private static final String SQL_SELECT_BY_ID = "SELECT id_registro_cadena_custodia, id_cadena_custodia, fecha_hora, persona_entrega, agencia_entrega, persona_recibe, agencia_recibe, orden, actividad_proposito, observaciones FROM registro_cadena_custodia WHERE id_registro_cadena_custodia = ?";
	private static final String SQL_INSERT = "INSERT INTO registro_cadena_custodia(id_cadena_custodia, fecha_hora, persona_entrega, agencia_entrega, persona_recibe, agencia_recibe, orden, actividad_proposito, observaciones) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_SELECT_LAST_ORDER = "SELECT orden FROM registro_cadena_custodia WHERE id_cadena_custodia = ?";
	private static final String SQL_DELETE = "DELETE FROM registro_cadena_custodia WHERE id_registro_cadena_custodia = ?";
	private static final String SQL_UPDATE = "UPDATE registro_cadena_custodia SET fecha_hora = ?, persona_entrega = ?, agencia_entrega = ?, persona_recibe = ?, agencia_recibe = ?, actividad_proposito = ?, observaciones = ? WHERE id_registro_cadena_custodia = ?";

	
	public RegistroCadenaDaoJDBC() {
	}
	
	public int actualizar(RegistroCadenaCustodia registroCadenaCustodia) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		int registros = 0;
		try {
			conn = Conexion.getConnection();
			stmt = conn.prepareStatement(SQL_UPDATE);
			Timestamp ts = new Timestamp(registroCadenaCustodia.getFechaHora().getTime());  
			stmt.setTimestamp(1, ts);	
			stmt.setString(2, registroCadenaCustodia.getPersonaEntrega());
			stmt.setString(3, registroCadenaCustodia.getAgenciaInstitucionEntrega());
			stmt.setString(4,  registroCadenaCustodia.getPersonaRecibe());
			stmt.setString(5, registroCadenaCustodia.getAgenciaInstitucionRecibe());
			stmt.setString(6, registroCadenaCustodia.getActividadProposito());
			stmt.setString(7, registroCadenaCustodia.getObservaciones());
			stmt.setInt(8, registroCadenaCustodia.getIdRegistroCadenaCustodia());
			System.out.println("[RegistroCadenaDaoJDBC - actualizar] SQL_UPDATE: "+stmt);
			registros = stmt.executeUpdate(); // Altera el estado de la BDD
			System.out.println("[RegistroCadenaDaoJDBC - actualizar] Registros actualizados: "+registros);

		} catch (SQLException e) {
			e.printStackTrace(System.out);
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace(System.out);	
		} finally {
			Conexion.close(stmt);
			Conexion.close(conn);
		}
		
		return registros;
	}
	
	public int eliminar(RegistroCadenaCustodia registroCadena) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		int registros = 0;
		
		try {
			conn = Conexion.getConnection();
			stmt = conn.prepareStatement(SQL_DELETE);
			stmt.setInt(1, registroCadena.getIdRegistroCadenaCustodia());
			registros = stmt.executeUpdate(); // Altera el estado de la BDD
			System.out.println("[RegistroCadenaDaoJDBC - eliminar] Registros eliminados: "+registros);
			
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace(System.out);	
		} finally {
			Conexion.close(stmt);
			Conexion.close(conn);
		}
		return registros;
	}
	
	public int insertar(RegistroCadenaCustodia registroCadenaCustodia) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		int lastId = 0;
		int registros = 0;
		PreparedStatement stmtUltimoOrden = null;
		ResultSet rsUltimoOrden = null;
		
		try {
			conn = Conexion.getConnection();
			
			// 1. Recoger orden último registro de la cadena de custodia
			
			stmtUltimoOrden = conn.prepareStatement(SQL_SELECT_LAST_ORDER);
			stmtUltimoOrden.setInt(1, registroCadenaCustodia.getIdCadenaCustodia());
			rsUltimoOrden = stmtUltimoOrden.executeQuery(); // Consulta
			List<Integer> listaOrden = new ArrayList<Integer>();
			
			while (rsUltimoOrden.next()) {	
				listaOrden.add(rsUltimoOrden.getInt("orden"));			
			}

			int ultimoOrden = Collections.max(listaOrden);
			registroCadenaCustodia.setOrden(ultimoOrden);
			
			// 2. Insertar registro de cadena de custodia

			stmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

			stmt.setInt(1, registroCadenaCustodia.getIdCadenaCustodia());
			Timestamp ts = new Timestamp(registroCadenaCustodia.getFechaHora().getTime());  
			stmt.setTimestamp(2, ts);			
			stmt.setString(3, registroCadenaCustodia.getPersonaEntrega());
			stmt.setString(4, registroCadenaCustodia.getAgenciaInstitucionEntrega());			
			stmt.setString(5, registroCadenaCustodia.getPersonaRecibe());	
			stmt.setString(6, registroCadenaCustodia.getAgenciaInstitucionRecibe());			
			stmt.setInt(7, registroCadenaCustodia.getOrden());	
			stmt.setString(8, registroCadenaCustodia.getActividadProposito());	
			stmt.setString(9, registroCadenaCustodia.getObservaciones());	

			registros = stmt.executeUpdate(); // Altera el estado de la BDD
			System.out.println("[RegistroCadenaDaoJDBC - insertar] Registros insertados: "+registros);
			
            ResultSet rs= stmt.getGeneratedKeys();
            if (rs.next()) {
            	lastId = rs.getInt(1);         	
            }
			
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace(System.out);	
		} finally {
			System.out.println("[RegistroCadenaDaoJDBC - insertar] Last Inserted ID = "+lastId);
			Conexion.close(stmt);
			Conexion.close(rsUltimoOrden);
			Conexion.close(stmtUltimoOrden);
			Conexion.close(conn);
		}

		return lastId;
	}
	
	
	public RegistroCadenaCustodia encontrar(RegistroCadenaCustodia registroCadenaCustodia) {
		
		Connection conn = null;
		
		PreparedStatement stmtSelectRegistroCadena = null;
		ResultSet rsSelectRegistroCadena = null;
		
		try {
			conn = Conexion.getConnection();
			
			// Obtener información básica del registro de la cadena de custodia
			
			stmtSelectRegistroCadena = conn.prepareStatement(SQL_SELECT_BY_ID);
			stmtSelectRegistroCadena.setInt(1, registroCadenaCustodia.getIdRegistroCadenaCustodia());
			rsSelectRegistroCadena = stmtSelectRegistroCadena.executeQuery(); // Consulta
			
			if (rsSelectRegistroCadena.next()) {								
				registroCadenaCustodia.setIdRegistroCadenaCustodia(rsSelectRegistroCadena.getInt("id_registro_cadena_custodia"));
				registroCadenaCustodia.setIdCadenaCustodia(rsSelectRegistroCadena.getInt("id_cadena_custodia"));
				registroCadenaCustodia.setFechaHora(rsSelectRegistroCadena.getTimestamp("fecha_hora"));
				registroCadenaCustodia.setPersonaEntrega(rsSelectRegistroCadena.getString("persona_entrega"));
				registroCadenaCustodia.setAgenciaInstitucionEntrega(rsSelectRegistroCadena.getString("agencia_entrega"));
				registroCadenaCustodia.setPersonaRecibe(rsSelectRegistroCadena.getString("persona_recibe"));
				registroCadenaCustodia.setAgenciaInstitucionRecibe(rsSelectRegistroCadena.getString("agencia_recibe"));
				registroCadenaCustodia.setOrden(rsSelectRegistroCadena.getInt("orden"));
				registroCadenaCustodia.setActividadProposito(rsSelectRegistroCadena.getString("actividad_proposito"));
				registroCadenaCustodia.setObservaciones(rsSelectRegistroCadena.getString("observaciones"));
			}

		} catch (SQLException ex) {
			ex.printStackTrace(System.out);	
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace(System.out);	
		} finally {
			Conexion.close(rsSelectRegistroCadena);
			Conexion.close(stmtSelectRegistroCadena);
			Conexion.close(conn);
		}
		
		return registroCadenaCustodia;
	}

}
