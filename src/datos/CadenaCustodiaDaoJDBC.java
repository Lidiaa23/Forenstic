package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import domain.CadenaCustodia;
import domain.Caso;
import domain.Evidencia;
import domain.RegistroCadenaCustodia;

public class CadenaCustodiaDaoJDBC {
	
	private static final String SQL_SELECT_BY_ID = "SELECT id_cadena_custodia, id_caso, id_dispositivo, id_ciclo_vida, localizacion, fecha_hora, razon, agencia_institucion, persona FROM cadena_custodia WHERE id_cadena_custodia = ?";
	private static final String SQL_SELECT_BY_ID_DISPOSITIVO = "SELECT id_cadena_custodia, id_caso, id_dispositivo, id_ciclo_vida, localizacion, fecha_hora, razon, agencia_institucion, persona FROM cadena_custodia WHERE id_dispositivo = ?";
	private static final String SQL_SELECT_REGISTROS_CADENA_CUSTODIA = "SELECT id_registro_cadena_custodia, id_cadena_custodia, fecha_hora, persona_entrega, agencia_entrega, persona_recibe, agencia_recibe, orden, actividad_proposito, observaciones FROM registro_cadena_custodia WHERE id_cadena_custodia = ?";
	private static final String SQL_INSERT = "INSERT INTO cadena_custodia(id_caso, id_dispositivo, localizacion, fecha_hora, razon, agencia_institucion, persona) VALUES(?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_SELECT_ID_CASO = "SELECT id_caso FROM dispositivo WHERE id_dispositivo = ?";
	private static final String SQL_SELECT_INFO_DISPOSITIVO = "SELECT nombre, marca, descripcion, es_clonado_forense, hash from dispositivo WHERE id_dispositivo = ?";
	private static final String SQL_UPDATE_DISPOSITIVO_CADENA_CUSTODIA = "UPDATE dispositivo SET id_cadena_custodia = ? WHERE id_dispositivo = ?";
	private static final String SQL_SELECT_PERMISO_USUARIO_CASO = "SELECT id_caso FROM caso_persona WHERE id_persona = ?";
	private static final String SQL_SELECT_ID_CASO_DEL_DISPOSITIVO = "SELECT id_caso FROM dispositivo WHERE id_dispositivo = ?";
	private static final String SQL_UPDATE = "UPDATE cadena_custodia SET localizacion = ?, fecha_hora = ?, razon = ?, agencia_institucion = ?, persona = ? WHERE id_cadena_custodia = ?";

	public CadenaCustodiaDaoJDBC() {	
	}
	
	public int actualizar(CadenaCustodia cadenaCustodia) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		int registros = 0;
		try {
			conn = Conexion.getConnection();
			stmt = conn.prepareStatement(SQL_UPDATE);
			stmt.setString(1, cadenaCustodia.getLocalizacionOrigen());
			Timestamp ts = new Timestamp(cadenaCustodia.getFechaHoraOrigen().getTime()); 
			stmt.setTimestamp(2, ts);
			stmt.setString(3, cadenaCustodia.getRazon());
			stmt.setString(4, cadenaCustodia.getAgenciaInstitucion());
			stmt.setString(5, cadenaCustodia.getPersona());
			stmt.setInt(6, cadenaCustodia.getIdCadenaCustodia());
			registros = stmt.executeUpdate(); // Altera el estado de la BDD
			System.out.println("[CadenaCustodiaDaoJDBC - actualizar] Registros actualizados: "+registros);

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
	
	public boolean permisosUsuario(int idCadenaCustodia, int idDispositivo, int idUsuario) {
		
		boolean tienePermisos = false;
		Connection conn = null;
		PreparedStatement stmtCasosConPermiso = null;
		ResultSet rsCasosConPermiso = null;
		PreparedStatement stmtCasoCadena = null;
		ResultSet rsCasoCadena = null;

		try {
			conn = Conexion.getConnection();	
			
			stmtCasosConPermiso = conn.prepareStatement(SQL_SELECT_PERMISO_USUARIO_CASO);
			stmtCasosConPermiso.setInt(1, idUsuario);
			System.out.println("[CadenaCustodiaDaoJDBC - permisosUsuario] SQL_SELECT_PERMISO_USUARIO_CASO: "+stmtCasosConPermiso);
			rsCasosConPermiso = stmtCasosConPermiso.executeQuery(); // Consulta	
			
			// Consultar id_caso del dispositivo de la cadena de custodia
			stmtCasoCadena = conn.prepareStatement(SQL_SELECT_ID_CASO_DEL_DISPOSITIVO);
			stmtCasoCadena.setInt(1, idDispositivo);
			System.out.println("[CadenaCustodiaDaoJDBC - permisosUsuario] SQL_SELECT_ID_CASO_DEL_DISPOSITIVO: "+stmtCasoCadena);
			rsCasoCadena = stmtCasoCadena.executeQuery(); // Consulta	
			
			// Mirar si coincide el id_caso del dispositivo con uno de los de la lista anterior
			int casoCadena = 0;
			if (rsCasoCadena.next()) {
				casoCadena = rsCasoCadena.getInt("id_caso");
			}			
			System.out.println("[CadenaCustodiaDaoJDBC - permisosUsuario] Caso de la cadena de custodia: "+casoCadena);
			
			System.out.println("[CadenaCustodiaDaoJDBC - permisosUsuario] Casos con los que el usuario tiene permisos:");
			while (rsCasosConPermiso.next()) {	
				System.out.println("\t"+rsCasosConPermiso.getInt("id_caso"));
				if (rsCasosConPermiso.getInt("id_caso") == rsCasoCadena.getInt("id_caso")) {
					tienePermisos = true;
				}
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);	
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace(System.out);	
		} finally {
			Conexion.close(rsCasosConPermiso);
			Conexion.close(stmtCasosConPermiso);
			Conexion.close(rsCasoCadena);
			Conexion.close(stmtCasoCadena);
			Conexion.close(conn);
		}
		System.out.println("[CadenaCustodiaDaoJDBC - permisosUsuario] Tiene permisos: "+tienePermisos);
		return tienePermisos;
			
	}
	
	public int insertar(CadenaCustodia cadenaCustodia) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmtIdCaso = null;
		ResultSet rsIdCaso = null;
		PreparedStatement stmtDispositivo = null;
		int registros = 0;
		int lastId = 0;
		int idCaso = 0;
		int registrosDispositivos = 0;
		
		try {
			conn = Conexion.getConnection();			
			// Coger el idCaso del idDispositivo	
			stmtIdCaso = conn.prepareStatement(SQL_SELECT_ID_CASO);
			stmtIdCaso.setInt(1, cadenaCustodia.getIdDispositivo());
			rsIdCaso = stmtIdCaso.executeQuery();
			if (rsIdCaso.next()) {
				idCaso = rsIdCaso.getInt("id_caso");
			}	
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);	
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace(System.out);				
		} finally {
			Conexion.close(stmtIdCaso);
			Conexion.close(rsIdCaso);
		}
			
		try {
			// Actualizar tabla 'cadena_custodia'
			stmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, idCaso);
			stmt.setInt(2, cadenaCustodia.getIdDispositivo());
			stmt.setString(3, cadenaCustodia.getLocalizacionOrigen());
			Timestamp ts = new Timestamp(cadenaCustodia.getFechaHoraOrigen().getTime());  
			stmt.setTimestamp(4, ts);
			stmt.setString(5, cadenaCustodia.getRazon());
			stmt.setString(6, cadenaCustodia.getAgenciaInstitucion());
			stmt.setString(7, cadenaCustodia.getPersona());
			System.out.println("[CadenaCustodiaDaoJDBC - insertar] SQL_INSERT; "+stmt);
			registros = stmt.executeUpdate(); // Altera el estado de la BDD
			System.out.println("[CadenaCustodiaDaoJDBC - insertar] Registros insertados: "+registros);
			
            ResultSet rs= stmt.getGeneratedKeys();
            if (rs.next()) {
            	lastId = rs.getInt(1);         	
            }

    		// Actualizar la tabla de 'dispositivo'
    		
    		try {
    			stmtDispositivo = conn.prepareStatement(SQL_UPDATE_DISPOSITIVO_CADENA_CUSTODIA);
    			stmtDispositivo.setInt(1, lastId);
    			stmtDispositivo.setInt(2, cadenaCustodia.getIdDispositivo());		
    			registrosDispositivos = stmtDispositivo.executeUpdate(); // Altera el estado de la BDD
    			System.out.println("[CasoDaoJDBC - insertar] Registros dispositivos actualizados: "+registrosDispositivos);

    		} catch (SQLException ex) {
    			ex.printStackTrace(System.out);
    		} finally {
    			Conexion.close(stmtDispositivo);			
    		}

		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		} finally {
			System.out.println("[CadenaCustodiaDaoJDBC - insertar] Last Inserted ID = "+lastId);
			Conexion.close(stmt);
			Conexion.close(conn);
		}

		return lastId;
	}
	
	
	public CadenaCustodia encontrarPorIdDispositivo(CadenaCustodia cadenaCustodia) {
		
		Connection conn = null;
		
		PreparedStatement stmtSelectCadenaCustodia = null;
		ResultSet rsSelectCadenaCustodia = null;
		
		PreparedStatement stmtSelectDispositivo = null;
		ResultSet rsSelectDispositivo = null;
		
		PreparedStatement stmtSelectRegistros = null;
		ResultSet rsSelectRegistros = null;
		
		System.out.println("[CadenaCustodiaDaoJDBC - encontrarPorIdDispositivo] Buscando cadena de custodia...");
		
		try {
			conn = Conexion.getConnection();
			
			// Obtener información básica del caso
			
			stmtSelectCadenaCustodia = conn.prepareStatement(SQL_SELECT_BY_ID_DISPOSITIVO);
			stmtSelectCadenaCustodia.setInt(1, cadenaCustodia.getIdDispositivo());
			System.out.println("SQL_SELECT_BY_ID: " + stmtSelectCadenaCustodia);
			rsSelectCadenaCustodia = stmtSelectCadenaCustodia.executeQuery(); // Consulta
			
			if (rsSelectCadenaCustodia.next()) {				
				cadenaCustodia.setIdCadenaCustodia(rsSelectCadenaCustodia.getInt("id_cadena_custodia"));
				cadenaCustodia.setIdCaso(rsSelectCadenaCustodia.getInt("id_caso"));
				cadenaCustodia.setIdDispositivo(rsSelectCadenaCustodia.getInt("id_dispositivo"));
				cadenaCustodia.setIdCicloVida(rsSelectCadenaCustodia.getInt("id_ciclo_vida"));
				cadenaCustodia.setLocalizacionOrigen(rsSelectCadenaCustodia.getString("localizacion"));
				cadenaCustodia.setFechaHoraOrigen(rsSelectCadenaCustodia.getTimestamp("fecha_hora"));
				cadenaCustodia.setRazon(rsSelectCadenaCustodia.getString("razon"));
				cadenaCustodia.setAgenciaInstitucion(rsSelectCadenaCustodia.getString("agencia_institucion"));
				cadenaCustodia.setPersona(rsSelectCadenaCustodia.getString("persona"));	
			}
			
			// Obtener información del dispositivo
			
			stmtSelectDispositivo = conn.prepareStatement(SQL_SELECT_INFO_DISPOSITIVO);
			stmtSelectDispositivo.setInt(1, cadenaCustodia.getIdDispositivo());
			System.out.println("SQL_SELECT_INFO_DISPOSITIVO: " + stmtSelectDispositivo);
			rsSelectDispositivo = stmtSelectDispositivo.executeQuery();
			
			if (rsSelectDispositivo.next()) {
				cadenaCustodia.setNombreDispositivo(rsSelectDispositivo.getString("nombre"));
				cadenaCustodia.setMarcaModeloDispositivo(rsSelectDispositivo.getString("marca"));
				cadenaCustodia.setDescripcionDispositivo(rsSelectDispositivo.getString("descripcion"));
				cadenaCustodia.setColeccionAdquisicionDispositivo(rsSelectDispositivo.getString("es_clonado_forense"));
				cadenaCustodia.setHashDispositivo(rsSelectDispositivo.getString("hash"));
			}
			
			stmtSelectDispositivo.setInt(1, cadenaCustodia.getIdDispositivo());
			rsSelectDispositivo = stmtSelectDispositivo.executeQuery(); // Consulta
			
			if (rsSelectCadenaCustodia.next()) {				
				cadenaCustodia.setIdCadenaCustodia(rsSelectCadenaCustodia.getInt("id_cadena_custodia"));
				cadenaCustodia.setIdCaso(rsSelectCadenaCustodia.getInt("id_caso"));
				cadenaCustodia.setIdDispositivo(rsSelectCadenaCustodia.getInt("id_dispositivo"));
				cadenaCustodia.setIdCicloVida(rsSelectCadenaCustodia.getInt("id_ciclo_vida"));
				cadenaCustodia.setLocalizacionOrigen(rsSelectCadenaCustodia.getString("localizacion"));
				cadenaCustodia.setFechaHoraOrigen(rsSelectCadenaCustodia.getTimestamp("fecha_hora"));
				cadenaCustodia.setRazon(rsSelectCadenaCustodia.getString("razon"));
				cadenaCustodia.setAgenciaInstitucion(rsSelectCadenaCustodia.getString("agencia_institucion"));
				cadenaCustodia.setPersona(rsSelectCadenaCustodia.getString("persona"));	
			}
			
			// Obtener registros de la cadena de custodia
			
			stmtSelectRegistros = conn.prepareStatement(SQL_SELECT_REGISTROS_CADENA_CUSTODIA);
			stmtSelectRegistros.setInt(1, cadenaCustodia.getIdCadenaCustodia());
			rsSelectRegistros = stmtSelectRegistros.executeQuery(); // Consulta
			
			List<RegistroCadenaCustodia> listaRegistroCadenaCustodia = new ArrayList<RegistroCadenaCustodia>();

			System.out.println("Lista registros de cadena de custodia: ");
			while (rsSelectRegistros.next()) {
				RegistroCadenaCustodia registroCadenaCustodia = new RegistroCadenaCustodia();			
				registroCadenaCustodia.setIdRegistroCadenaCustodia(rsSelectRegistros.getInt("id_registro_cadena_custodia"));
				registroCadenaCustodia.setIdCadenaCustodia(rsSelectRegistros.getInt("id_cadena_custodia"));
				registroCadenaCustodia.setFechaHora(rsSelectRegistros.getTimestamp("fecha_hora"));
				registroCadenaCustodia.setPersonaEntrega(rsSelectRegistros.getString("persona_entrega"));
				registroCadenaCustodia.setAgenciaInstitucionEntrega(rsSelectRegistros.getString("agencia_entrega"));
				registroCadenaCustodia.setPersonaRecibe(rsSelectRegistros.getString("persona_recibe"));
				registroCadenaCustodia.setAgenciaInstitucionRecibe(rsSelectRegistros.getString("agencia_recibe"));
				registroCadenaCustodia.setOrden(rsSelectRegistros.getInt("orden"));
				registroCadenaCustodia.setActividadProposito(rsSelectRegistros.getString("actividad_proposito"));
				registroCadenaCustodia.setObservaciones(rsSelectRegistros.getString("observaciones"));
				listaRegistroCadenaCustodia.add(registroCadenaCustodia);	
				System.out.println("\t"+registroCadenaCustodia.toString());
			}
			cadenaCustodia.setListaRegistros(listaRegistroCadenaCustodia);

		} catch (SQLException ex) {
			ex.printStackTrace(System.out);	
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace(System.out);	
		} finally {
			Conexion.close(rsSelectCadenaCustodia);
			Conexion.close(stmtSelectCadenaCustodia);
			Conexion.close(conn);
		}
		
		return cadenaCustodia;
		
	}
	
	public CadenaCustodia encontrar(CadenaCustodia cadenaCustodia) {
		
		Connection conn = null;
		
		PreparedStatement stmtSelectCadenaCustodia = null;
		ResultSet rsSelectCadenaCustodia = null;
		
		PreparedStatement stmtSelectDispositivo = null;
		ResultSet rsSelectDispositivo = null;
		
		PreparedStatement stmtSelectRegistros = null;
		ResultSet rsSelectRegistros = null;
		
		System.out.println("[CadenaCustodiaDaoJDBC - encontrar] Buscando cadena de custodia...");
		
		try {
			conn = Conexion.getConnection();
			
			// Obtener información básica del caso
			
			stmtSelectCadenaCustodia = conn.prepareStatement(SQL_SELECT_BY_ID);
			stmtSelectCadenaCustodia.setInt(1, cadenaCustodia.getIdCadenaCustodia());
			System.out.println("SQL_SELECT_BY_ID: " + stmtSelectCadenaCustodia);
			rsSelectCadenaCustodia = stmtSelectCadenaCustodia.executeQuery(); // Consulta
			
			if (rsSelectCadenaCustodia.next()) {				
				cadenaCustodia.setIdCadenaCustodia(rsSelectCadenaCustodia.getInt("id_cadena_custodia"));
				cadenaCustodia.setIdCaso(rsSelectCadenaCustodia.getInt("id_caso"));
				cadenaCustodia.setIdDispositivo(rsSelectCadenaCustodia.getInt("id_dispositivo"));
				cadenaCustodia.setIdCicloVida(rsSelectCadenaCustodia.getInt("id_ciclo_vida"));
				cadenaCustodia.setLocalizacionOrigen(rsSelectCadenaCustodia.getString("localizacion"));
				cadenaCustodia.setFechaHoraOrigen(rsSelectCadenaCustodia.getTimestamp("fecha_hora"));
				cadenaCustodia.setRazon(rsSelectCadenaCustodia.getString("razon"));
				cadenaCustodia.setAgenciaInstitucion(rsSelectCadenaCustodia.getString("agencia_institucion"));
				cadenaCustodia.setPersona(rsSelectCadenaCustodia.getString("persona"));	
			}
			
			// Obtener información del dispositivo
			
			stmtSelectDispositivo = conn.prepareStatement(SQL_SELECT_INFO_DISPOSITIVO);
			stmtSelectDispositivo.setInt(1, cadenaCustodia.getIdDispositivo());
			System.out.println("SQL_SELECT_INFO_DISPOSITIVO: " + stmtSelectDispositivo);
			rsSelectDispositivo = stmtSelectDispositivo.executeQuery();
			
			if (rsSelectDispositivo.next()) {
				cadenaCustodia.setNombreDispositivo(rsSelectDispositivo.getString("nombre"));
				cadenaCustodia.setMarcaModeloDispositivo(rsSelectDispositivo.getString("marca"));
				cadenaCustodia.setDescripcionDispositivo(rsSelectDispositivo.getString("descripcion"));
				cadenaCustodia.setColeccionAdquisicionDispositivo(rsSelectDispositivo.getString("es_clonado_forense"));
				cadenaCustodia.setHashDispositivo(rsSelectDispositivo.getString("hash"));
			}
			
			stmtSelectDispositivo.setInt(1, cadenaCustodia.getIdDispositivo());
			rsSelectDispositivo = stmtSelectDispositivo.executeQuery(); // Consulta
			
			if (rsSelectCadenaCustodia.next()) {				
				cadenaCustodia.setIdCadenaCustodia(rsSelectCadenaCustodia.getInt("id_cadena_custodia"));
				cadenaCustodia.setIdCaso(rsSelectCadenaCustodia.getInt("id_caso"));
				cadenaCustodia.setIdDispositivo(rsSelectCadenaCustodia.getInt("id_dispositivo"));
				cadenaCustodia.setIdCicloVida(rsSelectCadenaCustodia.getInt("id_ciclo_vida"));
				cadenaCustodia.setLocalizacionOrigen(rsSelectCadenaCustodia.getString("localizacion"));
				cadenaCustodia.setFechaHoraOrigen(rsSelectCadenaCustodia.getTimestamp("fecha_hora"));
				cadenaCustodia.setRazon(rsSelectCadenaCustodia.getString("razon"));
				cadenaCustodia.setAgenciaInstitucion(rsSelectCadenaCustodia.getString("agencia_institucion"));
				cadenaCustodia.setPersona(rsSelectCadenaCustodia.getString("persona"));	
			}
			
			// Obtener registros de la cadena de custodia
			
			stmtSelectRegistros = conn.prepareStatement(SQL_SELECT_REGISTROS_CADENA_CUSTODIA);
			stmtSelectRegistros.setInt(1, cadenaCustodia.getIdCadenaCustodia());
			rsSelectRegistros = stmtSelectRegistros.executeQuery(); // Consulta
			
			List<RegistroCadenaCustodia> listaRegistroCadenaCustodia = new ArrayList<RegistroCadenaCustodia>();

			System.out.println("Lista registros de cadena de custodia: ");
			while (rsSelectRegistros.next()) {
				RegistroCadenaCustodia registroCadenaCustodia = new RegistroCadenaCustodia();			
				registroCadenaCustodia.setIdRegistroCadenaCustodia(rsSelectRegistros.getInt("id_registro_cadena_custodia"));
				registroCadenaCustodia.setIdCadenaCustodia(rsSelectRegistros.getInt("id_cadena_custodia"));
				registroCadenaCustodia.setFechaHora(rsSelectRegistros.getTimestamp("fecha_hora"));
				registroCadenaCustodia.setPersonaEntrega(rsSelectRegistros.getString("persona_entrega"));
				registroCadenaCustodia.setAgenciaInstitucionEntrega(rsSelectRegistros.getString("agencia_entrega"));
				registroCadenaCustodia.setPersonaRecibe(rsSelectRegistros.getString("persona_recibe"));
				registroCadenaCustodia.setAgenciaInstitucionRecibe(rsSelectRegistros.getString("agencia_recibe"));
				registroCadenaCustodia.setOrden(rsSelectRegistros.getInt("orden"));
				registroCadenaCustodia.setActividadProposito(rsSelectRegistros.getString("actividad_proposito"));
				registroCadenaCustodia.setObservaciones(rsSelectRegistros.getString("observaciones"));
				listaRegistroCadenaCustodia.add(registroCadenaCustodia);	
				System.out.println("\t"+registroCadenaCustodia.toString());
			}
			cadenaCustodia.setListaRegistros(listaRegistroCadenaCustodia);

		} catch (SQLException ex) {
			ex.printStackTrace(System.out);	
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace(System.out);	
		} finally {
			Conexion.close(rsSelectCadenaCustodia);
			Conexion.close(stmtSelectCadenaCustodia);
			Conexion.close(conn);
		}
		
		return cadenaCustodia;
		
	}

}
