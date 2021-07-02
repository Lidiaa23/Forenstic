package datos;

import java.sql.*;
import java.util.*;
import java.util.Date;

import domain.Caso;
import domain.Dispositivo;
import domain.Evidencia;
import domain.Persona;

public class CasoDaoJDBC {

	private static final String SQL_SELECT = "SELECT id_caso, nombre, descripcion, num_dispositivos, num_evidencias, fecha_creacion_reg, fecha_ultima_modif FROM caso";
	private static final String SQL_SELECT_EVIDENCIAS_CASO = "SELECT id_evidencia_digital, nombre, descripcion, tipo, id_caso, id_dispositivo, fecha_creacion_reg, fecha_ultima_modif FROM evidencia_digital WHERE id_caso = ?";
	private static final String SQL_SELECT_PERSONAS_CASO =	"SELECT CP.id_caso, CP.id_persona, P.nombre FROM forensticdb.caso_persona CP, forensticdb.persona P WHERE CP.id_caso = ? AND CP.id_persona = P.id_persona";
	private static final String SQL_INSERT = "INSERT INTO caso(nombre, descripcion, num_dispositivos, num_evidencias, fecha_creacion_reg, fecha_ultima_modif) VALUES(?, ?, ?, ?, ?, ?)";
	private static final String SQL_INSERT_CASO_PERSONA = "INSERT INTO caso_persona(id_caso, id_persona) VALUES(?, ?)";
	private static final String SQL_DELETE = "DELETE FROM caso WHERE id_caso = ?";
	private static final String SQL_UPDATE = "UPDATE caso SET nombre = ?, descripcion = ?, fecha_ultima_modif = ? WHERE id_caso = ?";
	private static final String SQL_SELECT_BY_ID = "SELECT id_caso, nombre, descripcion, num_dispositivos, num_evidencias, fecha_creacion_reg, fecha_ultima_modif FROM caso WHERE id_caso = ?";
	private static final String SQL_SELECT_DISPOSITIVOS_CASO = "SELECT id_dispositivo, nombre, descripcion, fotografia, hash, cifrado, id_cadena_custodia, id_ciclo_vida, tipo, es_clonado_forense, num_evidencias, fecha_creacion_reg, fecha_ultima_modif FROM dispositivo WHERE id_caso = ?";	
	private static final String SQL_SELECT_MIS_CASOS = "SELECT caso.id_caso, caso.nombre, caso.descripcion, caso.num_dispositivos, caso.num_evidencias, caso.fecha_creacion_reg, caso.fecha_ultima_modif FROM caso INNER JOIN caso_persona ON caso.id_caso=caso_persona.id_caso WHERE caso_persona.id_persona = ?";
	private static final String SQL_SELECT_PERMISO_USUARIO_CASO = "SELECT id_caso FROM caso_persona WHERE id_persona = ?";
	private static final String SQL_DELETE_DISPOSITIVOS = "DELETE FROM dispositivo WHERE id_caso = ?";
	private static final String SQL_DELETE_EVIDENCIAS = "DELETE FROM evidencia_digital WHERE id_caso = ?";
	private static final String SQL_SELECT_NOMBRE_DISPOSITIVO = "SELECT nombre FROM dispositivo WHERE id_dispositivo = ?";
	private static final String SQL_DELETE_TABLA_CASO_PERSONA = "DELETE FROM caso_persona WHERE id_caso = ?";

	public CasoDaoJDBC() {	
	}
	
	public List<Caso> listar() {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Caso caso = null;
		List<Caso> listaCasos = new ArrayList<Caso>();
		
		try {
			conn = Conexion.getConnection();			
			stmt = conn.prepareStatement(SQL_SELECT);
			rs = stmt.executeQuery(); // Consulta
			while (rs.next()) {
				int idCaso = rs.getInt("id_caso");
				String nombre = rs.getString("nombre");
				String descripcion = rs.getString("descripcion");
				int numDispositivos = rs.getInt("num_dispositivos");
				int numEvidencias = rs.getInt("num_evidencias");
				Date fechaCreacionReg = rs.getTimestamp("fecha_creacion_reg");
				Date fechaUltimaModif = rs.getTimestamp("fecha_ultima_modif");
				caso = new Caso(idCaso, nombre, descripcion, numDispositivos, numEvidencias, null, null, null, fechaCreacionReg, fechaUltimaModif);
				listaCasos.add(caso);
			}
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);	
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace(System.out);	
		} finally {
			Conexion.close(rs);
			Conexion.close(stmt);
			Conexion.close(conn);
		}
		
		return listaCasos;
	}
	
	public List<Caso> listarMisCasos(int idUsuario) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		PreparedStatement stmtDispositivos = null;
		ResultSet rsDispositivos = null;
		Caso caso = null;
		List<Caso> listaMisCasos = new ArrayList<Caso>();
		
		try {
			conn = Conexion.getConnection();			
			stmt = conn.prepareStatement(SQL_SELECT_MIS_CASOS);
			stmt.setInt(1, idUsuario);		
			rs = stmt.executeQuery(); // Consulta
			
			while (rs.next()) {
				int idCaso = rs.getInt("id_caso");
				String nombre = rs.getString("nombre");
				String descripcion = rs.getString("descripcion");
				int numDispositivos = rs.getInt("num_dispositivos");
				int numEvidencias = rs.getInt("num_evidencias");
				Date fechaCreacionReg = rs.getTimestamp("fecha_creacion_reg");
				Date fechaUltimaModif = rs.getTimestamp("fecha_ultima_modif");
				caso = new Caso(idCaso, nombre, descripcion, numDispositivos, numEvidencias, null, null, null, fechaCreacionReg, fechaUltimaModif);
				
				// Buscar dispositivos del caso
				
				stmtDispositivos = conn.prepareStatement(SQL_SELECT_DISPOSITIVOS_CASO);
				stmtDispositivos.setInt(1, idCaso);		
				rsDispositivos = stmtDispositivos.executeQuery(); // Consulta
								
				List<Dispositivo> listaDispositivosCaso = new ArrayList<Dispositivo>();
				
				while (rsDispositivos.next()) {				
					Dispositivo dispositivo = new Dispositivo();					
					dispositivo.setIdDispositivo(rsDispositivos.getInt("id_dispositivo"));
					dispositivo.setNombre(rsDispositivos.getString("nombre"));
					listaDispositivosCaso.add(dispositivo);
				}
				caso.setListaDispositivos(listaDispositivosCaso);
				listaMisCasos.add(caso);
			}
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);	
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace(System.out);	
		} finally {
			Conexion.close(rs);
			Conexion.close(stmt);
			Conexion.close(rsDispositivos);
			Conexion.close(stmtDispositivos);
			Conexion.close(conn);
		}
		
		return listaMisCasos;
	}
	
	
	public boolean permisosUsuario(int idCaso, int idUsuario) {
		
		boolean tienePermisos = false;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = Conexion.getConnection();	
			stmt = conn.prepareStatement(SQL_SELECT_PERMISO_USUARIO_CASO);
			stmt.setInt(1, idUsuario);
			rs = stmt.executeQuery(); // Consulta	
			while (rs.next()) {	
				if (rs.getInt("id_caso") == idCaso) {
					tienePermisos = true;
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);	
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace(System.out);	
		} finally {
			Conexion.close(rs);
			Conexion.close(stmt);
			Conexion.close(conn);
		}
		System.out.println("[CasoDaoJDBC - permisosUsuario] Tiene permisos: "+tienePermisos);
		return tienePermisos;
			
	}
	
	public Caso encontrar(Caso caso) {
		
		Connection conn = null;
		
		PreparedStatement stmtSelectCaso = null;
		ResultSet rsSelectCaso = null;
		
		PreparedStatement stmtSelectEvidencias = null;
		ResultSet rsSelectEvidencias = null;
		
		PreparedStatement stmtSelectPersonas = null;
		ResultSet rsSelectPersonas = null;
		
		PreparedStatement stmtSelectDispositivos = null;
		ResultSet rsSelectDispositivos = null;
		
		PreparedStatement stmtSelectNombreDispositivo = null;
		ResultSet rsSelectNombreDispositivo = null;
		
		try {
			conn = Conexion.getConnection();
			
			// Obtener información básica del caso
			
			stmtSelectCaso = conn.prepareStatement(SQL_SELECT_BY_ID);
			stmtSelectCaso.setInt(1, caso.getIdCaso());
			rsSelectCaso = stmtSelectCaso.executeQuery(); // Consulta
			
			if (rsSelectCaso.next()) {
				caso.setNombre(rsSelectCaso.getString("nombre"));
				caso.setDescripcion(rsSelectCaso.getString("descripcion"));
				caso.setNumDispositivos(rsSelectCaso.getInt("num_dispositivos"));
				caso.setNumEvidencias(rsSelectCaso.getInt("num_evidencias"));
				caso.setFechaCreacionReg(rsSelectCaso.getTimestamp("fecha_creacion_reg"));
				caso.setFechaUltimaModif(rsSelectCaso.getTimestamp("fecha_ultima_modif"));
			}
			
			// Obtener evidencias del caso
			
			stmtSelectEvidencias = conn.prepareStatement(SQL_SELECT_EVIDENCIAS_CASO);
			stmtSelectEvidencias.setInt(1, caso.getIdCaso());
			rsSelectEvidencias = stmtSelectEvidencias.executeQuery(); // Consulta
			
			List<Evidencia> listaEvidencias = new ArrayList<Evidencia>();
			
			System.out.println("[CasoDaoJDBC - encontrar] Lista evidencias: ");
			while (rsSelectEvidencias.next()) {
				Evidencia evidencia = new Evidencia();
				evidencia.setIdEvidencia(rsSelectEvidencias.getInt("id_evidencia_digital"));
				evidencia.setIdCaso(rsSelectEvidencias.getInt("id_caso"));
				evidencia.setIdDispositivo(rsSelectEvidencias.getInt("id_dispositivo"));
				evidencia.setNombre(rsSelectEvidencias.getString("nombre"));
				evidencia.setDescripcion(rsSelectEvidencias.getString("descripcion"));
				evidencia.setTipo(rsSelectEvidencias.getString("tipo"));
				//evidencia.setTamano(rsSelectEvidencias.getDouble("tamano"));
				evidencia.setFechaCreacionReg(rsSelectEvidencias.getTimestamp("fecha_creacion_reg"));
				evidencia.setFechaUltimaModif(rsSelectEvidencias.getTimestamp("fecha_ultima_modif"));
				
				try {
					// Consultar nombre dispositivo de la evidencia				
					stmtSelectNombreDispositivo = conn.prepareStatement(SQL_SELECT_NOMBRE_DISPOSITIVO);
					stmtSelectNombreDispositivo.setInt(1, evidencia.getIdDispositivo());
					rsSelectNombreDispositivo = stmtSelectNombreDispositivo.executeQuery(); // Consulta				
					if (rsSelectNombreDispositivo.next()) {		 
						evidencia.setNombreDispositivo(rsSelectNombreDispositivo.getString("nombre"));				
					}
				} catch (SQLException ex) {
					ex.printStackTrace(System.out);	
				} finally {
					Conexion.close(rsSelectNombreDispositivo);
					Conexion.close(stmtSelectNombreDispositivo);
				}

				listaEvidencias.add(evidencia);	
				System.out.println("\t"+evidencia.toString());
			}
			caso.setListaEvidencias(listaEvidencias);
			
			// Obtener personas involucradas en el caso

			stmtSelectPersonas = conn.prepareStatement(SQL_SELECT_PERSONAS_CASO);
			stmtSelectPersonas.setInt(1, caso.getIdCaso());
			rsSelectPersonas = stmtSelectPersonas.executeQuery(); // Consulta
			
			List<Persona> listaPersonas = new ArrayList<Persona>();
			
			System.out.println("[CasoDaoJDBC - encontrar] Lista personas: ");
			while (rsSelectPersonas.next()) {		

				Persona persona = new Persona();
				persona.setIdPersona(rsSelectPersonas.getInt("id_persona"));
				persona.setNombre(rsSelectPersonas.getString("nombre"));
				listaPersonas.add(persona);	
				System.out.println("\t"+persona.toString());
			}
			caso.setListaPersonas(listaPersonas);
			
			// Obtener dispositivos del caso
			
			stmtSelectDispositivos = conn.prepareStatement(SQL_SELECT_DISPOSITIVOS_CASO);
			stmtSelectDispositivos.setInt(1, caso.getIdCaso());
			rsSelectDispositivos = stmtSelectDispositivos.executeQuery(); // Consulta
			
			List<Dispositivo> listaDispositivos = new ArrayList<Dispositivo>();

			System.out.println("[CasoDaoJDBC - encontrar] Lista dispositivos: ");
			while (rsSelectDispositivos.next()) {

				Dispositivo dispositivo = new Dispositivo();
				dispositivo.setIdDispositivo(rsSelectDispositivos.getInt("id_dispositivo"));
				dispositivo.setIdCadenaCustodia(rsSelectDispositivos.getInt("id_cadena_custodia"));
				dispositivo.setIdCaso(caso.getIdCaso());
				dispositivo.setNombre(rsSelectDispositivos.getString("nombre"));
				dispositivo.setDescripcion(rsSelectDispositivos.getString("descripcion"));
				dispositivo.setTipo(rsSelectDispositivos.getString("tipo"));
				dispositivo.setHash(rsSelectDispositivos.getString("hash"));
				dispositivo.setEstaCifrado(rsSelectDispositivos.getString("cifrado"));
				dispositivo.setEsClonadoForense(rsSelectDispositivos.getString("es_clonado_forense"));
				dispositivo.setFotografia(rsSelectDispositivos.getString("fotografia"));
				dispositivo.setNumEvidenciasDigitales(rsSelectDispositivos.getInt("num_evidencias"));
				dispositivo.setFechaCreacionReg(rsSelectDispositivos.getTimestamp("fecha_creacion_reg"));
				dispositivo.setFechaUltimaModif(rsSelectDispositivos.getTimestamp("fecha_ultima_modif"));
				listaDispositivos.add(dispositivo);
				System.out.println("\t"+dispositivo.toString());				
			}
			caso.setListaDispositivos(listaDispositivos);
			
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);	
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace(System.out);	
		} finally {
			Conexion.close(rsSelectCaso);
			Conexion.close(stmtSelectCaso);
			Conexion.close(rsSelectEvidencias);
			Conexion.close(stmtSelectEvidencias);
			Conexion.close(rsSelectDispositivos);
			Conexion.close(stmtSelectDispositivos);
			Conexion.close(rsSelectPersonas);
			Conexion.close(stmtSelectPersonas);
			Conexion.close(conn);
		}
		
		return caso;
		
	}
	
	public int insertar(Caso caso) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmtCasoPersona = null;
		int registros = 0;
		int registrosCasoPersona = 0;
		int lastId = 0;
		
		try {
			conn = Conexion.getConnection();
			
			// Actualizar tabla 'casos'
			stmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, caso.getNombre());
			stmt.setString(2, caso.getDescripcion());		
			stmt.setInt(3, 0);
			stmt.setInt(4, 0);
			java.sql.Timestamp dateFechaActual = new java.sql.Timestamp(System.currentTimeMillis());
			stmt.setTimestamp(5, dateFechaActual);
			stmt.setTimestamp(6, dateFechaActual);
			registros = stmt.executeUpdate(); // Altera el estado de la BDD
			System.out.println("[CasoDaoJDBC - insertar] Registros insertados: "+registros);
			
            ResultSet rs= stmt.getGeneratedKeys();
            if (rs.next()) {
            	lastId = rs.getInt(1);         	
            }
            
            // Actualizar tabla 'caso_persona'
            
            stmtCasoPersona = conn.prepareStatement(SQL_INSERT_CASO_PERSONA);
            stmtCasoPersona.setInt(1, lastId);
			if (!caso.getListaPersonas().isEmpty()) {
				stmtCasoPersona.setInt(2, caso.getListaPersonas().get(0).getIdPersona());
			}
			registrosCasoPersona = stmtCasoPersona.executeUpdate(); // Altera el estado de la BDD
			System.out.println("[CasoDaoJDBC - insertar] RegistrosCasoPersona insertados: "+registrosCasoPersona);
            
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace(System.out);	
		} finally {
			System.out.println("[CasoDaoJDBC - insertar] Last Inserted ID = "+lastId);
			Conexion.close(stmt);
			Conexion.close(stmtCasoPersona);
			Conexion.close(conn);
			
		}

		return lastId;
	}
	
	public int eliminar(Caso caso) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmtDispositivos = null;
		PreparedStatement stmtEvidencias = null;
		PreparedStatement stmtCasoPersona = null;
		int registros = 0;
		int registrosDispositivos = 0;
		int registrosEvidencias = 0;
		int registrosCasoPersona = 0;
		
		try {
			conn = Conexion.getConnection();
			stmt = conn.prepareStatement(SQL_DELETE);
			stmt.setInt(1, caso.getIdCaso());
			registros = stmt.executeUpdate(); // Altera el estado de la BDD
			System.out.println("[CasoDaoJDBC - eliminar] Registros eliminados: "+registros);
			
			// Eliminar dispositivos y evidencias asociadas
			stmtDispositivos = conn.prepareStatement(SQL_DELETE_DISPOSITIVOS);
			stmtDispositivos.setInt(1, caso.getIdCaso());
			registrosDispositivos = stmtDispositivos.executeUpdate(); // Altera el estado de la BDD
			System.out.println("[CasoDaoJDBC - eliminar] Registros de dispositivos eliminados: "+registrosDispositivos);
			
			stmtEvidencias = conn.prepareStatement(SQL_DELETE_EVIDENCIAS);
			stmtEvidencias.setInt(1, caso.getIdCaso());
			registrosEvidencias = stmtEvidencias.executeUpdate(); // Altera el estado de la BDD
			System.out.println("[CasoDaoJDBC - eliminar] Registros de dispositivos eliminados: "+registrosEvidencias);
			
			// Eliminar de la tabla de 'caso_persona'
			stmtCasoPersona = conn.prepareStatement(SQL_DELETE_TABLA_CASO_PERSONA);
			stmtCasoPersona.setInt(1, caso.getIdCaso());
			registrosCasoPersona = stmtCasoPersona.executeUpdate(); // Altera el estado de la BDD
			System.out.println("[CasoDaoJDBC - eliminar] Registros de caso_persona eliminados: "+registrosCasoPersona);
			
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace(System.out);	
		} finally {
			Conexion.close(stmt);
			Conexion.close(stmtDispositivos);
			Conexion.close(stmtEvidencias);
			Conexion.close(conn);
		}
		return registros;
	}
	
	public int actualizar(Caso caso) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		int registros = 0;

		try {
			conn = Conexion.getConnection();
			stmt = conn.prepareStatement(SQL_UPDATE);
			stmt.setString(1, caso.getNombre());
			stmt.setString(2, caso.getDescripcion());
			java.sql.Timestamp dateFechaActual = new java.sql.Timestamp(System.currentTimeMillis());			
			stmt.setTimestamp(3, dateFechaActual);
			stmt.setInt(4, caso.getIdCaso());
			registros = stmt.executeUpdate(); // Altera el estado de la BDD
			System.out.println("[CasoDaoJDBC - actualizar] Registros actualizados: "+registros);

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
	
	
	
	public List<Caso> buscarCaso(int idCaso, String nombreCaso, String inicioFechaCreacion, String finFechaCreacion, String inicioFechaUltimaModif, String finFechaUltimaModif) {
		
		// TODO en la pantalla hacer obligatorio informar mínimo 1 campo para buscar
		
		List<Caso> listaCasosEncontrados = new ArrayList<Caso>();
		
		Connection conn = null;
		
		// ********** campos individuales (idCaso, nombreCaso, fechas) **********
		List<Integer> listaIdCasosCamposIndividuales = new ArrayList<Integer>();
		if (idCaso != 0 || !nombreCaso.isEmpty() || !inicioFechaCreacion.isEmpty() || !finFechaCreacion.isEmpty() || !inicioFechaUltimaModif.isEmpty() || !finFechaUltimaModif.isEmpty()) {
			listaIdCasosCamposIndividuales = buscarIdCasosPorCamposIndividuales(conn, idCaso, nombreCaso, inicioFechaCreacion, finFechaCreacion, inicioFechaUltimaModif, finFechaUltimaModif);
		}

		System.out.println("[CasoDaoJDBC - buscarCaso] Caso/s a devolver: ");
		for (Integer idCasoEncontrado: listaIdCasosCamposIndividuales) {
			System.out.println("\t"+idCasoEncontrado);
			Caso casoAencontrar = new Caso(idCasoEncontrado);
			Caso casoEncontrado = encontrar(casoAencontrar);
			listaCasosEncontrados.add(casoEncontrado);
		}

		return listaCasosEncontrados;
	
	}

	public List<Integer> buscarIdCasosPorCamposIndividuales(Connection conn, int idCaso, String nombreCaso, String inicioFechaCreacion, String finFechaCreacion, String inicioFechaUltimaModif, String finFechaUltimaModif) {
		
		List<Integer> listaIdCasosEncontrados = new ArrayList<Integer>();
		boolean existePrimerWhere = false;
		String sqlQuery = "SELECT id_caso FROM caso WHERE ";
		
		// ********** idCaso **********
		if (idCaso != 0) {	
			existePrimerWhere = true;			
			sqlQuery = sqlQuery+"id_caso = "+idCaso+" ";		
		}
		
		// ********** nombreCaso **********
		if (!nombreCaso.isEmpty()) {			
			if (existePrimerWhere) {				
				sqlQuery = sqlQuery+"AND nombre LIKE \'%%"+nombreCaso+"%%\' ";				
			} else {			
				existePrimerWhere = true;				
				sqlQuery = sqlQuery+"nombre LIKE \'%%"+nombreCaso+"%%\' ";				
			}	
		}

		// ********** inicioFechaCreacion **********
		if (!inicioFechaCreacion.isEmpty()) {			
			if (existePrimerWhere) {				
				sqlQuery = sqlQuery+"AND fecha_creacion_reg >= \'"+inicioFechaCreacion+" 00:00:00\' ";				
			} else {			
				existePrimerWhere = true;				
				sqlQuery = sqlQuery+"fecha_creacion_reg >= \'"+inicioFechaCreacion+" 00:00:00\' ";				
			}
		}

		// ********** finFechaCreacion **********
		if (!finFechaCreacion.isEmpty()) {
			if (existePrimerWhere) {				
				sqlQuery = sqlQuery+"AND fecha_creacion_reg <= \'"+finFechaCreacion+" 23:59:59\' ";				
			} else {			
				existePrimerWhere = true;				
				sqlQuery = sqlQuery+"fecha_creacion_reg <= \'"+finFechaCreacion+" 23:59:59\' ";				
			}	
		}
		
		// ********** inicioFechaUltimaModif **********
		if (!inicioFechaUltimaModif.isEmpty()) {
			if (existePrimerWhere) {				
				sqlQuery = sqlQuery+"AND fecha_ultima_modif >= \'"+inicioFechaUltimaModif+" 00:00:00\' ";				
			} else {			
				existePrimerWhere = true;				
				sqlQuery = sqlQuery+"fecha_ultima_modif >= \'"+inicioFechaUltimaModif+" 00:00:00\' ";				
			}	
		}
		
		// ********** finFechaUltimaModif **********
		if (!finFechaUltimaModif.isEmpty()) {
			if (existePrimerWhere) {				
				sqlQuery = sqlQuery+"AND fecha_ultima_modif <= \'"+finFechaUltimaModif+" 23:59:59\' ";				
			} else {			
				existePrimerWhere = true;				
				sqlQuery = sqlQuery+"fecha_ultima_modif <= \'"+finFechaUltimaModif+" 23:59:59\' ";				
			}	
		}
		
		// Buscar en la tabla de casos con esta información
		
		PreparedStatement stmtSelectCaso = null;
		ResultSet rsSelectCaso = null;
				
		if (existePrimerWhere) {
			try {
				System.out.println("[CasoDaoJDBC - buscarIdCasosPorCamposIndividuales] sqlQuery: "+sqlQuery);
				
				conn = Conexion.getConnection();	
				stmtSelectCaso = conn.prepareStatement(sqlQuery);
				rsSelectCaso = stmtSelectCaso.executeQuery(); // Consulta
				
				while (rsSelectCaso.next()) {

					int idCasoEncontrado = rsSelectCaso.getInt("id_caso");
					listaIdCasosEncontrados.add(idCasoEncontrado);
				}

			} catch (SQLException ex) {
				ex.printStackTrace(System.out);	
			} catch (ClassNotFoundException ex) {
				ex.printStackTrace(System.out);	
			} finally {
				Conexion.close(rsSelectCaso);
				Conexion.close(stmtSelectCaso);
				Conexion.close(conn);
			}
			
			if (!listaIdCasosEncontrados.isEmpty()) {
				System.out.println("[CasoDaoJDBC - buscarIdCasosPorCamposIndividuales] ID casos encontrados:");
				for (Integer cas: listaIdCasosEncontrados) {
					System.out.println("\t"+cas);
				}
			}
		}
		return listaIdCasosEncontrados;
	}

}
