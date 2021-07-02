package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import domain.Evidencia;

public class EvidenciaDaoJDBC {
	
	private static final String SQL_SELECT_BY_ID = "SELECT id_evidencia_digital, id_caso, descripcion, tipo, nombre, id_dispositivo, fecha_creacion_reg, fecha_ultima_modif FROM evidencia_digital WHERE id_evidencia_digital = ?";
	private static final String SQL_SELECT_NOMBRE_DISPOSITIVO = "SELECT nombre FROM dispositivo WHERE id_dispositivo = ?";
	private static final String SQL_SELECT_NOMBRE_CASO = "SELECT nombre FROM caso WHERE id_caso = ?";
	private static final String SQL_INSERT = "INSERT INTO evidencia_digital(id_caso, id_dispositivo, nombre, descripcion, tipo, fecha_creacion_reg, fecha_ultima_modif) VALUES(?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE = "UPDATE evidencia_digital SET nombre = ?, descripcion = ?, tipo = ?, fecha_ultima_modif = ? WHERE id_evidencia_digital = ?";
	private static final String SQL_DELETE = "DELETE FROM evidencia_digital WHERE id_evidencia_digital = ?";
	private static final String SQL_UPDATE_NUM_EVIDENCIAS_DISPOSITIVO = "UPDATE dispositivo SET num_evidencias = num_evidencias + 1 WHERE id_dispositivo = ?";	
	private static final String SQL_UPDATE_NUM_EVIDENCIAS_CASO = "UPDATE caso SET num_evidencias = num_evidencias + 1 WHERE id_caso = ?";	
	private static final String SQL_UPDATE_NUM_EVIDENCIAS_DISPOSITIVO_ELIMINAR = "UPDATE dispositivo SET num_evidencias = num_evidencias - 1 WHERE id_dispositivo = ?";	
	private static final String SQL_UPDATE_NUM_EVIDENCIAS_CASO_ELIMINAR = "UPDATE caso SET num_evidencias = num_evidencias - 1 WHERE id_caso = ?";
	private static final String SQL_SELECT = "SELECT id_evidencia_digital, id_caso, descripcion, tipo, nombre, id_dispositivo, fecha_creacion_reg, fecha_ultima_modif FROM evidencia_digital";
	private static final String SQL_SELECT_MIS_EVIDENCIAS = "SELECT evidencia_digital.id_evidencia_digital, evidencia_digital.id_caso, evidencia_digital.descripcion, evidencia_digital.tipo, evidencia_digital.nombre, evidencia_digital.id_dispositivo, evidencia_digital.fecha_creacion_reg, evidencia_digital.fecha_ultima_modif FROM evidencia_digital INNER JOIN caso_persona ON evidencia_digital.id_caso=caso_persona.id_caso WHERE caso_persona.id_persona = ?";

	public EvidenciaDaoJDBC() {
	}
	
	public List<Evidencia> listarMisEvidencias(int idUsuario) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		PreparedStatement stmtNombreCaso = null;
		ResultSet rsSelectNombreCaso = null;
		PreparedStatement stmtNombreDispositivo = null;
		ResultSet rsSelectNombreDispositivo = null;
		Evidencia evidencia = null;
		List<Evidencia> listaMisEvidencias = new ArrayList<Evidencia>();
		
		try {
			conn = Conexion.getConnection();			
			stmt = conn.prepareStatement(SQL_SELECT_MIS_EVIDENCIAS);
			stmt.setInt(1, idUsuario);		
			rs = stmt.executeQuery(); // Consulta
			while (rs.next()) {
				int idEvidencia = rs.getInt("id_evidencia_digital");
				int idCaso = rs.getInt("id_caso");
				int idDispositivo = rs.getInt("id_dispositivo");
				String descripcion = rs.getString("descripcion");
				String tipo = rs.getString("tipo");
				String nombre = rs.getString("nombre");
				//Double tamano = rs.getDouble("tamano");
				Date fechaCreacionReg = rs.getTimestamp("fecha_creacion_reg");
				Date fechaUltimaModif = rs.getTimestamp("fecha_ultima_modif");			
				evidencia = new Evidencia(idEvidencia, idCaso, idDispositivo, nombre, descripcion, tipo, fechaCreacionReg, fechaUltimaModif);	
				
				// Recoger el nombre del dispositivo asociado			
				stmtNombreDispositivo = conn.prepareStatement(SQL_SELECT_NOMBRE_DISPOSITIVO);
				stmtNombreDispositivo.setInt(1, evidencia.getIdDispositivo());
				rsSelectNombreDispositivo = stmtNombreDispositivo.executeQuery();
				if (rsSelectNombreDispositivo.next()) {
					evidencia.setNombreDispositivo(rsSelectNombreDispositivo.getString("nombre"));
				}
				
				// Recoger el nombre del caso asociado			
				stmtNombreCaso = conn.prepareStatement(SQL_SELECT_NOMBRE_CASO);
				stmtNombreCaso.setInt(1, evidencia.getIdCaso());
				rsSelectNombreCaso = stmtNombreCaso.executeQuery();
				if (rsSelectNombreCaso.next()) {
					evidencia.setNombreCaso(rsSelectNombreCaso.getString("nombre"));
				}
				
				listaMisEvidencias.add(evidencia);
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
		
		return listaMisEvidencias;
	}
	
	
	public List<Evidencia> listar() {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		PreparedStatement stmtNombreCaso = null;
		ResultSet rsNombreCaso = null;
		PreparedStatement stmtNombreDispositivo = null;
		ResultSet rsNombreDispositivo = null;
		Evidencia evidencia = null;
		List<Evidencia> listaEvidencias = new ArrayList<Evidencia>();
		
		try {
			conn = Conexion.getConnection();			
			stmt = conn.prepareStatement(SQL_SELECT);
			rs = stmt.executeQuery(); // Consulta
			listaEvidencias = new ArrayList<Evidencia>();
			while (rs.next()) {		
				int idEvidencia = rs.getInt("id_evidencia_digital");
				int idCaso = rs.getInt("id_caso");
				int idDispositivo = rs.getInt("id_dispositivo");
				String descripcion = rs.getString("descripcion");
				String tipo = rs.getString("tipo");
				String nombre = rs.getString("nombre");
				//Double tamano = rs.getDouble("tamano");
				Date fechaCreacionReg = rs.getTimestamp("fecha_creacion_reg");
				Date fechaUltimaModif = rs.getTimestamp("fecha_ultima_modif");
				evidencia = new Evidencia(idEvidencia, idCaso, idDispositivo, nombre, descripcion, tipo, fechaCreacionReg, fechaUltimaModif);

				// Consultar nombre caso
				try {
					stmtNombreCaso = conn.prepareStatement(SQL_SELECT_NOMBRE_CASO);
					stmtNombreCaso.setInt(1, evidencia.getIdCaso());
					rsNombreCaso = stmtNombreCaso.executeQuery(); // Consulta			
					if (rsNombreCaso.next()) {
						String nombreCaso = rsNombreCaso.getString("nombre");
						evidencia.setNombreCaso(nombreCaso);
					}
				} catch (SQLException ex) {
					ex.printStackTrace(System.out);
				} finally {
					Conexion.close(rsNombreCaso);
					Conexion.close(stmtNombreCaso);
				}
				
				// Consultar nombre dispositivo
				try {
					stmtNombreDispositivo = conn.prepareStatement(SQL_SELECT_NOMBRE_DISPOSITIVO);
					stmtNombreDispositivo.setInt(1, evidencia.getIdDispositivo());
					rsNombreDispositivo = stmtNombreDispositivo.executeQuery(); // Consulta			
					if (rsNombreDispositivo.next()) {
						String nombreDispositivo = rsNombreDispositivo.getString("nombre");
						evidencia.setNombreDispositivo(nombreDispositivo);
					}
				} catch (SQLException ex) {
					ex.printStackTrace(System.out);
				} finally {
					Conexion.close(rsNombreDispositivo);
					Conexion.close(stmtNombreDispositivo);
				}
				
				listaEvidencias.add(evidencia);
				
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
		
		return listaEvidencias;
	}

	public Evidencia encontrar(Evidencia evidencia) {
		
		Connection conn = null;
		
		PreparedStatement stmtSelectEvidencia = null;
		ResultSet rsSelectEvidencia = null;
		PreparedStatement stmtNombreDispositivo = null;
		ResultSet rsSelectNombreDispositivo = null;
		PreparedStatement stmtNombreCaso = null;
		ResultSet rsSelectNombreCaso = null;
		
		try {
			conn = Conexion.getConnection();
			
			// Obtener información básica de la evidencia

			stmtSelectEvidencia = conn.prepareStatement(SQL_SELECT_BY_ID);
			stmtSelectEvidencia.setInt(1, evidencia.getIdEvidencia());
			rsSelectEvidencia = stmtSelectEvidencia.executeQuery(); // Consulta
			
			if (rsSelectEvidencia.next()) {				
				evidencia.setDescripcion(rsSelectEvidencia.getString("descripcion"));
				evidencia.setFechaCreacionReg(rsSelectEvidencia.getTimestamp("fecha_creacion_reg"));
				evidencia.setFechaUltimaModif(rsSelectEvidencia.getTimestamp("fecha_ultima_modif"));
				evidencia.setIdCaso(rsSelectEvidencia.getInt("id_caso"));
				evidencia.setIdDispositivo(rsSelectEvidencia.getInt("id_dispositivo"));
				evidencia.setIdEvidencia(rsSelectEvidencia.getInt("id_evidencia_digital"));
				evidencia.setNombre(rsSelectEvidencia.getString("nombre"));
				//evidencia.setTamano(rsSelectEvidencia.getDouble("tamano"));
				evidencia.setTipo(rsSelectEvidencia.getString("tipo"));
			}
			
			// Recoger el nombre del dispositivo asociado			
			stmtNombreDispositivo = conn.prepareStatement(SQL_SELECT_NOMBRE_DISPOSITIVO);
			stmtNombreDispositivo.setInt(1, evidencia.getIdDispositivo());
			rsSelectNombreDispositivo = stmtNombreDispositivo.executeQuery();
			if (rsSelectNombreDispositivo.next()) {
				evidencia.setNombreDispositivo(rsSelectNombreDispositivo.getString("nombre"));
			}
			
			// Recoger el nombre del caso asociado			
			stmtNombreCaso = conn.prepareStatement(SQL_SELECT_NOMBRE_CASO);
			stmtNombreCaso.setInt(1, evidencia.getIdCaso());
			rsSelectNombreCaso = stmtNombreCaso.executeQuery();
			if (rsSelectNombreCaso.next()) {
				evidencia.setNombreCaso(rsSelectNombreCaso.getString("nombre"));
			}

		} catch (SQLException ex) {
			ex.printStackTrace(System.out);	
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace(System.out);	
		} finally {
			Conexion.close(rsSelectEvidencia);
			Conexion.close(stmtSelectEvidencia);
			Conexion.close(rsSelectNombreDispositivo);
			Conexion.close(stmtNombreDispositivo);
			Conexion.close(rsSelectNombreCaso);
			Conexion.close(stmtNombreCaso);
			Conexion.close(conn);
		}
		
		return evidencia;
	}
	
	public int insertar(Evidencia evidencia) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmtNumEvidenciasDispositivo = null;
		PreparedStatement stmtNumEvidenciasCaso = null;
		int lastId = 0;
		int registros = 0;
		int registrosUpdateNumEvidenciasDispositivo = 0;
		int registrosUpdateNumEvidenciasCaso = 0;
		
		try {
			conn = Conexion.getConnection();
			stmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

			stmt.setInt(1, evidencia.getIdCaso());
			stmt.setInt(2, evidencia.getIdDispositivo());
			stmt.setString(3, evidencia.getNombre());
			stmt.setString(4, evidencia.getDescripcion());			
			stmt.setString(5, evidencia.getTipo());	
			//stmt.setDouble(6, evidencia.getTamano());	
			java.sql.Timestamp dateFechaActual = new java.sql.Timestamp(System.currentTimeMillis());
			stmt.setTimestamp(6, dateFechaActual);
			stmt.setTimestamp(7, dateFechaActual);
			registros = stmt.executeUpdate(); // Altera el estado de la BDD
			System.out.println("[EvidenciaDaoJDBC - insertar] Registros insertados: "+registros);
			
            ResultSet rs= stmt.getGeneratedKeys();
            if (rs.next()) {
            	lastId = rs.getInt(1);         	
            }
            
            // Modificar la tabla de dispositivos para incrementar el número de evidencias            
            stmtNumEvidenciasDispositivo = conn.prepareStatement(SQL_UPDATE_NUM_EVIDENCIAS_DISPOSITIVO);
            stmtNumEvidenciasDispositivo.setInt(1, evidencia.getIdDispositivo());
            registrosUpdateNumEvidenciasDispositivo = stmtNumEvidenciasDispositivo.executeUpdate(); // Altera el estado de la BDD
			System.out.println("[EvidenciaDaoJDBC - insertar] Registros actualizados en el update de numEvidencias del dispositivo: "+registrosUpdateNumEvidenciasDispositivo);
			
            // Modificar la tabla de casos para incrementar el número de evidencias            
            stmtNumEvidenciasCaso = conn.prepareStatement(SQL_UPDATE_NUM_EVIDENCIAS_CASO);
            stmtNumEvidenciasCaso.setInt(1, evidencia.getIdCaso());
            registrosUpdateNumEvidenciasCaso = stmtNumEvidenciasCaso.executeUpdate(); // Altera el estado de la BDD
			System.out.println("[EvidenciaDaoJDBC - insertar] Registros actualizados en el update de numEvidencias del caso: "+registrosUpdateNumEvidenciasCaso);
			
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace(System.out);	
		} finally {
			System.out.println("[EvidenciaDaoJDBC - insertar] Last Inserted ID = "+lastId);
			Conexion.close(stmt);
			Conexion.close(stmtNumEvidenciasDispositivo);
			Conexion.close(stmtNumEvidenciasCaso);
			Conexion.close(conn);
		}

		return lastId;
	}
	
	public int actualizar(Evidencia evidencia) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		int registros = 0;
		try {
			conn = Conexion.getConnection();
			stmt = conn.prepareStatement(SQL_UPDATE);
			stmt.setString(1, evidencia.getNombre());
			stmt.setString(2, evidencia.getDescripcion());
			stmt.setString(3, evidencia.getTipo());
			//stmt.setDouble(4,  evidencia.getTamano());
			java.sql.Timestamp dateFechaActual = new java.sql.Timestamp(System.currentTimeMillis());			
			stmt.setTimestamp(4, dateFechaActual);
			stmt.setInt(5, evidencia.getIdEvidencia());
			registros = stmt.executeUpdate(); // Altera el estado de la BDD
			System.out.println("[EvidenciaDaoJDBC - actualizar] Registros actualizados: "+registros);

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
	
	public int eliminar(Evidencia evidencia) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmtNumEvidenciasDispositivo = null;
		PreparedStatement stmtNumEvidenciasCaso = null;
		int registros = 0;
		int registrosUpdateNumEvidenciasDispositivo = 0;
		int registrosUpdateNumEvidenciasCaso = 0;
		
		try {
			conn = Conexion.getConnection();
			stmt = conn.prepareStatement(SQL_DELETE);
			stmt.setInt(1, evidencia.getIdEvidencia());
			registros = stmt.executeUpdate(); // Altera el estado de la BDD
			System.out.println("[EvidenciaDaoJDBC - eliminar] Registros eliminados: "+registros);
			
            // Modificar la tabla de dispositivos para decrementar el número de evidencias            
            stmtNumEvidenciasDispositivo = conn.prepareStatement(SQL_UPDATE_NUM_EVIDENCIAS_DISPOSITIVO_ELIMINAR);
            stmtNumEvidenciasDispositivo.setInt(1, evidencia.getIdDispositivo());
            registrosUpdateNumEvidenciasDispositivo = stmtNumEvidenciasDispositivo.executeUpdate(); // Altera el estado de la BDD
			System.out.println("[EvidenciaDaoJDBC - eliminar] Registros actualizados en el update de numEvidencias del dispositivo: "+registrosUpdateNumEvidenciasDispositivo);
			
            // Modificar la tabla de casos para decrementar el número de evidencias            
            stmtNumEvidenciasCaso = conn.prepareStatement(SQL_UPDATE_NUM_EVIDENCIAS_CASO_ELIMINAR);
            stmtNumEvidenciasCaso.setInt(1, evidencia.getIdCaso());
            registrosUpdateNumEvidenciasCaso = stmtNumEvidenciasCaso.executeUpdate(); // Altera el estado de la BDD
			System.out.println("[EvidenciaDaoJDBC - eliminar] Registros actualizados en el update de numEvidencias del caso: "+registrosUpdateNumEvidenciasCaso);
			
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace(System.out);	
		} finally {
			Conexion.close(stmt);
			Conexion.close(stmtNumEvidenciasDispositivo);
			Conexion.close(stmtNumEvidenciasCaso);
			Conexion.close(conn);
		}
		return registros;
	}
	
	public List<Evidencia> buscarEvidencia(int idEvidencia, String nombreEvidencia, String inicioFechaCreacion, String finFechaCreacion, String inicioFechaUltimaModif, String finFechaUltimaModif) {
		
		// TODO en la pantalla hacer obligatorio informar mínimo 1 campo para buscar
		
		List<Evidencia> listaEvidenciasEncontradas = new ArrayList<Evidencia>();
		
		Connection conn = null;
		
		// ********** campos individuales (idEvidencia, nombreEvidencia, fechas) **********
		List<Integer> listaIdEvidenciasCamposIndividuales = new ArrayList<Integer>();
		if (idEvidencia != 0 || !nombreEvidencia.isEmpty() || !inicioFechaCreacion.isEmpty() || !finFechaCreacion.isEmpty() || !inicioFechaUltimaModif.isEmpty() || !finFechaUltimaModif.isEmpty()) {
			listaIdEvidenciasCamposIndividuales = buscarIdEvidenciasPorCamposIndividuales(conn, idEvidencia, nombreEvidencia, inicioFechaCreacion, finFechaCreacion, inicioFechaUltimaModif, finFechaUltimaModif);
		}

		System.out.println("[EvidenciaDaoJDBC - buscarEvidencia] Evidencia/s a devolver: ");
		for (Integer idEvidenciaEncontrada: listaIdEvidenciasCamposIndividuales) {
			System.out.println("\t"+idEvidenciaEncontrada);
			Evidencia evidenciaAencontrar = new Evidencia(idEvidenciaEncontrada);
			Evidencia evidenciaEncontrada = encontrar(evidenciaAencontrar);
			listaEvidenciasEncontradas.add(evidenciaEncontrada);
		}

		return listaEvidenciasEncontradas;
	
	}
	
	public List<Integer> buscarIdEvidenciasPorCamposIndividuales(Connection conn, int idEvidencia, String nombreEvidencia, String inicioFechaCreacion, String finFechaCreacion, String inicioFechaUltimaModif, String finFechaUltimaModif) {
		
		List<Integer> listaIdEvidenciasEncontradas = new ArrayList<Integer>();
		boolean existePrimerWhere = false;
		String sqlQuery = "SELECT id_evidencia_digital FROM evidencia_digital WHERE ";
		
		// ********** idDispositivo **********
		if (idEvidencia != 0) {
			existePrimerWhere = true;
			sqlQuery = sqlQuery+"id_evidencia_digital = "+idEvidencia+" ";
		}
		
		// ********** nombreDispositivo **********
		if (!nombreEvidencia.isEmpty()) {			
			if (existePrimerWhere) {				
				sqlQuery = sqlQuery+"AND nombre = \'"+nombreEvidencia+"\' ";				
			} else {			
				existePrimerWhere = true;				
				sqlQuery = sqlQuery+"nombre = \'"+nombreEvidencia+"\' ";				
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
		
		PreparedStatement stmtSelectEvidencia = null;
		ResultSet rsSelectEvidencia = null;
				
		if (existePrimerWhere) {
			try {
				System.out.println("[EvidenciaDaoJDBC - buscarIdEvidenciasPorCamposIndividuales] sqlQuery: "+sqlQuery);
				
				conn = Conexion.getConnection();	
				stmtSelectEvidencia = conn.prepareStatement(sqlQuery);
				rsSelectEvidencia = stmtSelectEvidencia.executeQuery(); // Consulta
				
				while (rsSelectEvidencia.next()) {

					int idEvidenciaEncontrada = rsSelectEvidencia.getInt("id_evidencia_digital");
					listaIdEvidenciasEncontradas.add(idEvidenciaEncontrada);
				}

			} catch (SQLException ex) {
				ex.printStackTrace(System.out);	
			} catch (ClassNotFoundException ex) {
				ex.printStackTrace(System.out);	
			} finally {
				Conexion.close(rsSelectEvidencia);
				Conexion.close(stmtSelectEvidencia);
				Conexion.close(conn);
			}
			
			if (!listaIdEvidenciasEncontradas.isEmpty()) {
				System.out.println("[EvidenciaDaoJDBC - buscarIdDispositivosPorCamposIndividuales] ID dispositivos encontrados:");
				for (Integer cas: listaIdEvidenciasEncontradas) {
					System.out.println("\t"+cas);
				}
			}
		}
		return listaIdEvidenciasEncontradas;
	}
	
}
