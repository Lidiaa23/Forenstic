package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import domain.Caso;
import domain.Dispositivo;
import domain.Evidencia;
import domain.Persona;

public class DispositivoDaoJDBC {
	
	private static final String SQL_SELECT_BY_ID = "SELECT id_dispositivo, nombre, descripcion, fotografia, hash, cifrado, id_caso, id_cadena_custodia, id_ciclo_vida, tipo, marca, es_clonado_forense, fecha_creacion_reg, fecha_ultima_modif FROM dispositivo WHERE id_dispositivo = ?";
	private static final String SQL_SELECT_EVIDENCIAS_DISPOSITIVO = "SELECT id_evidencia_digital, id_dispositivo, nombre, descripcion, tipo, id_caso, fecha_creacion_reg, fecha_ultima_modif FROM evidencia_digital WHERE id_dispositivo = ?";
	private static final String SQL_INSERT = "INSERT INTO dispositivo(id_caso, nombre, descripcion, fotografia, hash, cifrado, tipo, marca, es_clonado_forense, fecha_creacion_reg, fecha_ultima_modif) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_SELECT_MIS_DISPOSITIVOS = "SELECT dispositivo.id_caso, dispositivo.nombre, dispositivo.descripcion, dispositivo.num_evidencias, dispositivo.id_dispositivo, dispositivo.fotografia, dispositivo.hash, dispositivo.cifrado, dispositivo.id_cadena_custodia, dispositivo.id_ciclo_vida, dispositivo.tipo, dispositivo.marca, dispositivo.es_clonado_forense, dispositivo.fecha_creacion_reg, dispositivo.fecha_ultima_modif FROM dispositivo INNER JOIN caso_persona ON dispositivo.id_caso=caso_persona.id_caso WHERE caso_persona.id_persona = ?";
	private static final String SQL_SELECT_DISPOSITIVOS_DE_UN_CASO = "SELECT nombre, descripcion, id_dispositivo, num_evidencias, fotografia, hash, cifrado, id_cadena_custodia, id_ciclo_vida, tipo, marca, es_clonado_forense, fecha_creacion_reg, fecha_ultima_modif FROM dispositivo WHERE id_caso = ?";
	private static final String SQL_SELECT = "SELECT id_dispositivo, id_caso, nombre, descripcion, num_evidencias, fotografia, hash, cifrado, id_cadena_custodia, id_ciclo_vida, tipo, marca, es_clonado_forense,  fecha_creacion_reg, fecha_ultima_modif FROM dispositivo";
	private static final String SQL_UPDATE_NUM_DISPOSITIVOS_CASO = "UPDATE caso SET num_dispositivos = num_dispositivos + 1 WHERE id_caso = ?";	
	private static final String SQL_DELETE = "DELETE FROM dispositivo WHERE id_dispositivo = ?";
	private static final String SQL_UPDATE = "UPDATE dispositivo SET nombre = ?, descripcion = ?, fotografia = ?, hash = ?, cifrado = ?, tipo = ?, marca = ?, es_clonado_forense = ?, fecha_ultima_modif = ? WHERE id_dispositivo = ?";
	private static final String SQL_UPDATE_SIN_FOTOGRAFIA = "UPDATE dispositivo SET nombre = ?, descripcion = ?, hash = ?, cifrado = ?, tipo = ?, marca = ?, es_clonado_forense = ?, fecha_ultima_modif = ? WHERE id_dispositivo = ?";
	private static final String SQL_UPDATE_NUM_DISPOSITIVOS_CASO_ELIMINAR = "UPDATE caso SET num_dispositivos = num_dispositivos - 1 WHERE id_caso = ?";
	private static final String SQL_DELETE_EVIDENCIAS = "DELETE FROM evidencia_digital WHERE id_dispositivo = ?";
	private static final String SQL_SELECT_NOMBRE_CASO = "SELECT nombre from caso WHERE id_caso = ?";
	private static final String SQL_SELECT_MIS_DISPOSITIVOS_SIN_CADENA_CUSTODIA = "SELECT dispositivo.id_caso, dispositivo.nombre, dispositivo.descripcion, dispositivo.id_dispositivo, dispositivo.num_evidencias, dispositivo.fotografia, dispositivo.hash, dispositivo.cifrado, dispositivo.id_cadena_custodia, dispositivo.id_ciclo_vida, dispositivo.tipo, dispositivo.marca, dispositivo.es_clonado_forense, dispositivo.fecha_creacion_reg, dispositivo.fecha_ultima_modif FROM dispositivo INNER JOIN caso_persona ON dispositivo.id_caso=caso_persona.id_caso WHERE caso_persona.id_persona = ? AND dispositivo.id_cadena_custodia IS NULL";

	public DispositivoDaoJDBC() {	
	}
	
	public List<Dispositivo> listar() {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		PreparedStatement stmtNombreCaso = null;
		ResultSet rsNombreCaso = null;
		PreparedStatement stmtEvidencias = null;
		ResultSet rsEvidencias = null;
		Dispositivo dispositivo = null;
		Evidencia evidencia = null;
		List<Dispositivo> listaDispositivos = new ArrayList<Dispositivo>();
		List<Evidencia> listaEvidencias = new ArrayList<Evidencia>();
		
		try {
			conn = Conexion.getConnection();			
			stmt = conn.prepareStatement(SQL_SELECT);
			rs = stmt.executeQuery(); // Consulta
			while (rs.next()) {
				listaEvidencias = new ArrayList<Evidencia>();
				int idDispositivo = rs.getInt("id_dispositivo");
				int idCaso = rs.getInt("id_caso");
				String nombre = rs.getString("nombre");
				String descripcion = rs.getString("descripcion");				
				String fotografia = rs.getString("fotografia");
				String hash = rs.getString("hash");
				String estaCifrado = rs.getString("cifrado");
				int idCadenaCustodia = rs.getInt("id_cadena_custodia");
				int idCicloVida = rs.getInt("id_ciclo_vida");
				int numEvidencias = rs.getInt("num_evidencias");
				String tipo = rs.getString("tipo");
				String marca = rs.getString("marca");
				String esClonadoForense = rs.getString("es_clonado_forense");			
				Date fechaCreacionReg = rs.getTimestamp("fecha_creacion_reg");
				Date fechaUltimaModif = rs.getTimestamp("fecha_ultima_modif");
				dispositivo = new Dispositivo(idDispositivo, idCaso, nombre, descripcion, fotografia, hash, estaCifrado, tipo, marca, esClonadoForense, idCadenaCustodia, idCicloVida, numEvidencias, fechaCreacionReg, fechaUltimaModif);
				
				
				// Consultar todas las evidencias digitales de este dispositivo			
				stmtEvidencias = conn.prepareStatement(SQL_SELECT_EVIDENCIAS_DISPOSITIVO);
				stmtEvidencias.setInt(1, idDispositivo);		
				rsEvidencias = stmtEvidencias.executeQuery(); // Consulta
							
				while (rsEvidencias.next()) {
					int idCasoEvidencia = rsEvidencias.getInt("id_caso");
					int idEvidenciaDigital = rsEvidencias.getInt("id_evidencia_digital");
					int idDispositivoEvidencia = rsEvidencias.getInt("id_dispositivo");			
					String nombreEvidencia = rsEvidencias.getString("nombre");
					String descripcionEvidencia = rsEvidencias.getString("descripcion");
					String tipoEvidencia = rsEvidencias.getString("tipo");
					//Double tamano = rsEvidencias.getDouble("tamano");
					Date fechaCreacionRegEvidencia = rsEvidencias.getTimestamp("fecha_creacion_reg");
					Date fechaUltimaModifEvidencia = rsEvidencias.getTimestamp("fecha_ultima_modif");
					evidencia = new Evidencia(idEvidenciaDigital, idCasoEvidencia, idDispositivoEvidencia, nombreEvidencia, descripcionEvidencia, tipoEvidencia, fechaCreacionRegEvidencia, fechaUltimaModifEvidencia);
					listaEvidencias.add(evidencia);
					dispositivo.setListaEvidenciasDigitales(listaEvidencias);
				}
				
				// Consultar nombre caso
				try {
					stmtNombreCaso = conn.prepareStatement(SQL_SELECT_NOMBRE_CASO);
					stmtNombreCaso.setInt(1, dispositivo.getIdCaso());
					rsNombreCaso = stmtNombreCaso.executeQuery(); // Consulta			
					if (rsNombreCaso.next()) {
						String nombreCaso = rsNombreCaso.getString("nombre");
						dispositivo.setNombreCaso(nombreCaso);
					}
				} catch (SQLException ex) {
					ex.printStackTrace(System.out);
				} finally {
					Conexion.close(rsNombreCaso);
					Conexion.close(stmtNombreCaso);
				}
				
				listaDispositivos.add(dispositivo);

			}
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);	
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace(System.out);	
		} finally {
			Conexion.close(rsEvidencias);
			Conexion.close(stmtEvidencias);
			Conexion.close(rs);
			Conexion.close(stmt);
			Conexion.close(conn);
		}
		
		return listaDispositivos;
	}

	public List<Dispositivo> listarDispositivosDeUnCaso(int idCaso) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		PreparedStatement stmtEvidencias = null;
		ResultSet rsEvidencias = null;
		Dispositivo dispositivo = null;
		Evidencia evidencia = null;
		List<Dispositivo> listaMisDispositivos = new ArrayList<Dispositivo>();
		List<Evidencia> listaEvidencias = new ArrayList<Evidencia>();
		int idDispositivo = 0;
		
		try {
			conn = Conexion.getConnection();			
			stmt = conn.prepareStatement(SQL_SELECT_DISPOSITIVOS_DE_UN_CASO);
			stmt.setInt(1, idCaso);		
			rs = stmt.executeQuery(); // Consulta
			
			while (rs.next()) {
				listaEvidencias = new ArrayList<Evidencia>();
				idDispositivo = rs.getInt("id_dispositivo");
				String nombre = rs.getString("nombre");
				String descripcion = rs.getString("descripcion");
				String fotografia = rs.getString("fotografia");
				String hash = rs.getString("hash");
				String cifrado = rs.getString("cifrado");
				int idCadenaCustodia = rs.getInt("id_cadena_custodia");
				int idCicloVida = rs.getInt("id_ciclo_vida");
				int numEvidencias = rs.getInt("num_evidencias");
				String tipo = rs.getString("tipo");
				String marca = rs.getString("marca");
				String esClonadoForense = rs.getString("es_clonado_forense");				
				Date fechaCreacionReg = rs.getTimestamp("fecha_creacion_reg");
				Date fechaUltimaModif = rs.getTimestamp("fecha_ultima_modif");
				dispositivo = new Dispositivo(idDispositivo, idCaso, nombre, descripcion, fotografia, hash, cifrado, tipo, marca, esClonadoForense, idCadenaCustodia, idCicloVida, numEvidencias, fechaCreacionReg, fechaUltimaModif);
				listaMisDispositivos.add(dispositivo);
				
				try {
					// Consultar todas las evidencias digitales de este dispositivo			
					stmtEvidencias = conn.prepareStatement(SQL_SELECT_EVIDENCIAS_DISPOSITIVO);
					stmtEvidencias.setInt(1, idDispositivo);		
					rsEvidencias = stmtEvidencias.executeQuery(); // Consulta
								
					while (rsEvidencias.next()) {
						int idCasoEvidencia = rsEvidencias.getInt("id_caso");
						int idEvidenciaDigital = rsEvidencias.getInt("id_evidencia_digital");
						int idDispositivoEvidencia = rsEvidencias.getInt("id_dispositivo");			
						String nombreEvidencia = rsEvidencias.getString("nombre");
						String descripcionEvidencia = rsEvidencias.getString("descripcion");
						String tipoEvidencia = rsEvidencias.getString("tipo");
						//Double tamano = rsEvidencias.getDouble("tamano");
						Date fechaCreacionRegEvidencia = rsEvidencias.getTimestamp("fecha_creacion_reg");
						Date fechaUltimaModifEvidencia = rsEvidencias.getTimestamp("fecha_ultima_modif");
						evidencia = new Evidencia(idEvidenciaDigital, idCasoEvidencia, idDispositivoEvidencia, nombreEvidencia, descripcionEvidencia, tipoEvidencia, fechaCreacionRegEvidencia, fechaUltimaModifEvidencia);
						listaEvidencias.add(evidencia);
						dispositivo.setListaEvidenciasDigitales(listaEvidencias);
					}
				} catch (SQLException ex) {
					ex.printStackTrace(System.out);	
				} finally {
					Conexion.close(rsEvidencias);
					Conexion.close(stmtEvidencias);
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
		
		return listaMisDispositivos;

	}
	
	public int actualizar(Dispositivo dispositivo) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		int registros = 0;

		if (dispositivo.getFotografia().isEmpty()) {
			try {
				conn = Conexion.getConnection();
				stmt = conn.prepareStatement(SQL_UPDATE_SIN_FOTOGRAFIA);
				stmt.setString(1, dispositivo.getNombre());
				stmt.setString(2, dispositivo.getDescripcion());
				stmt.setString(3, dispositivo.getHash());
				stmt.setString(4, dispositivo.getEstaCifrado());
				stmt.setString(5, dispositivo.getTipo());
				stmt.setString(6, dispositivo.getMarca());
				stmt.setString(7, dispositivo.getEsClonadoForense());
				java.sql.Timestamp dateFechaActual = new java.sql.Timestamp(System.currentTimeMillis());			
				stmt.setTimestamp(8, dateFechaActual);
				stmt.setInt(9, dispositivo.getIdDispositivo());
				registros = stmt.executeUpdate(); // Altera el estado de la BDD
				System.out.println("[DispositivoDaoJDBC - actualizar] Registros actualizados: "+registros);

			} catch (SQLException e) {
				e.printStackTrace(System.out);
			} catch (ClassNotFoundException ex) {
				ex.printStackTrace(System.out);	
			} finally {
				Conexion.close(stmt);
				Conexion.close(conn);
			}
		} else {
			try {
				conn = Conexion.getConnection();
				stmt = conn.prepareStatement(SQL_UPDATE);
				stmt.setString(1, dispositivo.getNombre());
				stmt.setString(2, dispositivo.getDescripcion());
				stmt.setString(3, dispositivo.getFotografia());
				stmt.setString(4, dispositivo.getHash());
				stmt.setString(5, dispositivo.getEstaCifrado());
				stmt.setString(6, dispositivo.getTipo());
				stmt.setString(7, dispositivo.getMarca());
				stmt.setString(8, dispositivo.getEsClonadoForense());
				java.sql.Timestamp dateFechaActual = new java.sql.Timestamp(System.currentTimeMillis());			
				stmt.setTimestamp(9, dateFechaActual);
				stmt.setInt(10, dispositivo.getIdDispositivo());
				registros = stmt.executeUpdate(); // Altera el estado de la BDD
				System.out.println("[DispositivoDaoJDBC - actualizar] Registros actualizados: "+registros);

			} catch (SQLException e) {
				e.printStackTrace(System.out);
			} catch (ClassNotFoundException ex) {
				ex.printStackTrace(System.out);	
			} finally {
				Conexion.close(stmt);
				Conexion.close(conn);
			}	
		}
		
		return registros;
	}
	
	public List<Dispositivo> buscarDispositivo(int idDispositivo, String nombreDispositivo, String inicioFechaCreacion, String finFechaCreacion, String inicioFechaUltimaModif, String finFechaUltimaModif) {
		
		// TODO en la pantalla hacer obligatorio informar mínimo 1 campo para buscar
		
		List<Dispositivo> listaDispositivosEncontrados = new ArrayList<Dispositivo>();
		
		Connection conn = null;
		
		// ********** campos individuales (idDispositivo, nombreDispositivo, fechas) **********
		List<Integer> listaIdDispositivosCamposIndividuales = new ArrayList<Integer>();
		if (idDispositivo != 0 || !nombreDispositivo.isEmpty() || !inicioFechaCreacion.isEmpty() || !finFechaCreacion.isEmpty() || !inicioFechaUltimaModif.isEmpty() || !finFechaUltimaModif.isEmpty()) {
			listaIdDispositivosCamposIndividuales = buscarIdDispositivosPorCamposIndividuales(conn, idDispositivo, nombreDispositivo, inicioFechaCreacion, finFechaCreacion, inicioFechaUltimaModif, finFechaUltimaModif);
		}

		System.out.println("[DispositivoDaoJDBC - buscarDispositivo] Dispositivo/s a devolver: ");
		for (Integer idDispositivoEncontrado: listaIdDispositivosCamposIndividuales) {
			System.out.println("\t"+idDispositivoEncontrado);
			Dispositivo dispositivoAencontrar = new Dispositivo(idDispositivoEncontrado);
			Dispositivo dispositivoEncontrado = encontrar(dispositivoAencontrar);
			listaDispositivosEncontrados.add(dispositivoEncontrado);
		}

		return listaDispositivosEncontrados;
	
	}
	
	public int eliminar(Dispositivo dispositivo) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmtNumDispositivosCaso = null;
		PreparedStatement stmtEvidenciasCaso = null;
		int registros = 0;
		int registrosUpdateNumDispositivosCaso = 0;
		int registrosEvidencias = 0;
		
		try {
			conn = Conexion.getConnection();
			stmt = conn.prepareStatement(SQL_DELETE);
			stmt.setInt(1, dispositivo.getIdDispositivo());
			registros = stmt.executeUpdate(); // Altera el estado de la BDD
			System.out.println("[DispositivoDaoJDBC - eliminar] SQL_DELETE: "+stmt);
			System.out.println("[DispositivoDaoJDBC - eliminar] Registros eliminados: "+registros);

            // Modificar la tabla de casos para decrementar el número de dispositivos            
			stmtNumDispositivosCaso = conn.prepareStatement(SQL_UPDATE_NUM_DISPOSITIVOS_CASO_ELIMINAR);
            stmtNumDispositivosCaso.setInt(1, dispositivo.getIdCaso());
            registrosUpdateNumDispositivosCaso = stmtNumDispositivosCaso.executeUpdate(); // Altera el estado de la BDD
            System.out.println("[DispositivoDaoJDBC - eliminar] SQL_UPDATE_NUM_DISPOSITIVOS_CASO_ELIMINAR: "+stmtNumDispositivosCaso);
			System.out.println("[DispositivoDaoJDBC - eliminar] Registros actualizados en el update de numDispositivos del caso: "+registrosUpdateNumDispositivosCaso);
			
			// Eliminar evidencias asociadas al dispositivo
			stmtEvidenciasCaso = conn.prepareStatement(SQL_DELETE_EVIDENCIAS);
			stmtEvidenciasCaso.setInt(1, dispositivo.getIdDispositivo());
			registrosEvidencias = stmtEvidenciasCaso.executeUpdate(); // Altera el estado de la BDD
            System.out.println("[DispositivoDaoJDBC - eliminar] SQL_DELETE_EVIDENCIAS: "+stmtEvidenciasCaso);			
			System.out.println("[DispositivoDaoJDBC - eliminar] Registros de evidencias eliminadas: "+registrosEvidencias);
			
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace(System.out);	
		} finally {
			Conexion.close(stmt);
			Conexion.close(stmtNumDispositivosCaso);
			Conexion.close(stmtEvidenciasCaso);
			Conexion.close(conn);
		}
		return registros;
	}
	
	public List<Dispositivo> listarMisDispositivos(int idUsuario) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		PreparedStatement stmtEvidencias = null;
		ResultSet rsEvidencias = null;
		Dispositivo dispositivo = null;
		Evidencia evidencia = null;
		List<Dispositivo> listaMisDispositivos = new ArrayList<Dispositivo>();
		List<Evidencia> listaEvidencias = new ArrayList<Evidencia>();
		int idDispositivo = 0;
		
		try {
			conn = Conexion.getConnection();			
			stmt = conn.prepareStatement(SQL_SELECT_MIS_DISPOSITIVOS);
			stmt.setInt(1, idUsuario);		
			rs = stmt.executeQuery(); // Consulta
			
			while (rs.next()) {
				listaEvidencias = new ArrayList<Evidencia>();
				int idCaso = rs.getInt("id_caso");
				idDispositivo = rs.getInt("id_dispositivo");
				String nombre = rs.getString("nombre");
				String descripcion = rs.getString("descripcion");
				String fotografia = rs.getString("fotografia");
				String hash = rs.getString("hash");
				String cifrado = rs.getString("cifrado");
				int idCadenaCustodia = rs.getInt("id_cadena_custodia");
				int idCicloVida = rs.getInt("id_ciclo_vida");
				String tipo = rs.getString("tipo");
				String marca = rs.getString("marca");
				int numEvidencias = rs.getInt("num_evidencias");
				String esClonadoForense = rs.getString("es_clonado_forense");				
				Date fechaCreacionReg = rs.getTimestamp("fecha_creacion_reg");
				Date fechaUltimaModif = rs.getTimestamp("fecha_ultima_modif");
				dispositivo = new Dispositivo(idDispositivo, idCaso, nombre, descripcion, fotografia, hash, cifrado, tipo, marca, esClonadoForense, idCadenaCustodia, idCicloVida, numEvidencias, fechaCreacionReg, fechaUltimaModif);
				listaMisDispositivos.add(dispositivo);
				
				try {
					// Consultar todas las evidencias digitales de este dispositivo			
					stmtEvidencias = conn.prepareStatement(SQL_SELECT_EVIDENCIAS_DISPOSITIVO);
					stmtEvidencias.setInt(1, idDispositivo);		
					rsEvidencias = stmtEvidencias.executeQuery(); // Consulta
								
					while (rsEvidencias.next()) {
						int idCasoEvidencia = rsEvidencias.getInt("id_caso");
						int idEvidenciaDigital = rsEvidencias.getInt("id_evidencia_digital");
						int idDispositivoEvidencia = rsEvidencias.getInt("id_dispositivo");			
						String nombreEvidencia = rsEvidencias.getString("nombre");
						String descripcionEvidencia = rsEvidencias.getString("descripcion");
						String tipoEvidencia = rsEvidencias.getString("tipo");
						//Double tamano = rsEvidencias.getDouble("tamano");
						Date fechaCreacionRegEvidencia = rsEvidencias.getTimestamp("fecha_creacion_reg");
						Date fechaUltimaModifEvidencia = rsEvidencias.getTimestamp("fecha_ultima_modif");
						evidencia = new Evidencia(idEvidenciaDigital, idCasoEvidencia, idDispositivoEvidencia, nombreEvidencia, descripcionEvidencia, tipoEvidencia, fechaCreacionRegEvidencia, fechaUltimaModifEvidencia);
						listaEvidencias.add(evidencia);
						dispositivo.setListaEvidenciasDigitales(listaEvidencias);
					}
				} catch (SQLException ex) {
					ex.printStackTrace(System.out);	
				} finally {
					Conexion.close(rsEvidencias);
					Conexion.close(stmtEvidencias);
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
		
		return listaMisDispositivos;
	}
	
	public List<Dispositivo> listarMisDispositivosSinCadenaCustodia(int idUsuario) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		PreparedStatement stmtEvidencias = null;
		ResultSet rsEvidencias = null;
		Dispositivo dispositivo = null;
		Evidencia evidencia = null;
		List<Dispositivo> listaMisDispositivosSinCadenaCustodia = new ArrayList<Dispositivo>();
		List<Evidencia> listaEvidencias = new ArrayList<Evidencia>();
		int idDispositivo = 0;
		
		try {
			conn = Conexion.getConnection();			
			stmt = conn.prepareStatement(SQL_SELECT_MIS_DISPOSITIVOS_SIN_CADENA_CUSTODIA);
			stmt.setInt(1, idUsuario);		
			System.out.println("SQL_SELECT_MIS_DISPOSITIVOS_SIN_CADENA_CUSTODIA: "+stmt);
			rs = stmt.executeQuery(); // Consulta
			
			while (rs.next()) {
				listaEvidencias = new ArrayList<Evidencia>();
				int idCaso = rs.getInt("id_caso");
				idDispositivo = rs.getInt("id_dispositivo");
				String nombre = rs.getString("nombre");
				String descripcion = rs.getString("descripcion");
				String fotografia = rs.getString("fotografia");
				String hash = rs.getString("hash");
				String cifrado = rs.getString("cifrado");
				int idCadenaCustodia = rs.getInt("id_cadena_custodia");
				int idCicloVida = rs.getInt("id_ciclo_vida");
				int numEvidencias = rs.getInt("num_evidencias");
				String tipo = rs.getString("tipo");
				String marca = rs.getString("marca");
				String esClonadoForense = rs.getString("es_clonado_forense");				
				Date fechaCreacionReg = rs.getTimestamp("fecha_creacion_reg");
				Date fechaUltimaModif = rs.getTimestamp("fecha_ultima_modif");
				dispositivo = new Dispositivo(idDispositivo, idCaso, nombre, descripcion, fotografia, hash, cifrado, tipo, marca, esClonadoForense, idCadenaCustodia, idCicloVida, numEvidencias, fechaCreacionReg, fechaUltimaModif);
				listaMisDispositivosSinCadenaCustodia.add(dispositivo);
				
				try {
					// Consultar todas las evidencias digitales de este dispositivo			
					stmtEvidencias = conn.prepareStatement(SQL_SELECT_EVIDENCIAS_DISPOSITIVO);
					stmtEvidencias.setInt(1, idDispositivo);		
					rsEvidencias = stmtEvidencias.executeQuery(); // Consulta
								
					while (rsEvidencias.next()) {
						int idCasoEvidencia = rsEvidencias.getInt("id_caso");
						int idEvidenciaDigital = rsEvidencias.getInt("id_evidencia_digital");
						int idDispositivoEvidencia = rsEvidencias.getInt("id_dispositivo");			
						String nombreEvidencia = rsEvidencias.getString("nombre");
						String descripcionEvidencia = rsEvidencias.getString("descripcion");
						String tipoEvidencia = rsEvidencias.getString("tipo");
						//Double tamano = rsEvidencias.getDouble("tamano");
						Date fechaCreacionRegEvidencia = rsEvidencias.getTimestamp("fecha_creacion_reg");
						Date fechaUltimaModifEvidencia = rsEvidencias.getTimestamp("fecha_ultima_modif");
						evidencia = new Evidencia(idEvidenciaDigital, idCasoEvidencia, idDispositivoEvidencia, nombreEvidencia, descripcionEvidencia, tipoEvidencia, fechaCreacionRegEvidencia, fechaUltimaModifEvidencia);
						listaEvidencias.add(evidencia);
						dispositivo.setListaEvidenciasDigitales(listaEvidencias);
					}
				} catch (SQLException ex) {
					ex.printStackTrace(System.out);	
				} finally {
					Conexion.close(rsEvidencias);
					Conexion.close(stmtEvidencias);
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
		
		return listaMisDispositivosSinCadenaCustodia;
	}
	
	public Dispositivo encontrar(Dispositivo dispositivo) {
		
		Connection conn = null;
		
		PreparedStatement stmtSelectDispositivo = null;
		ResultSet rsSelectDispositivo = null;
		
		PreparedStatement stmtSelectEvidencias = null;
		ResultSet rsSelectEvidencias = null;
		
		try {
			conn = Conexion.getConnection();
			
			// Obtener información básica del dispositivo
			
			// 	SELECT id_dispositivo, nombre, descripcion, fotografia, hash, cifrado, id_caso, id_cadena_custodia, id_ciclo_vida, tipo, 
			// es_clonado_forense, fecha_creacion_reg, fecha_ultima_modif FROM caso WHERE id_dispositivo = ?

			stmtSelectDispositivo = conn.prepareStatement(SQL_SELECT_BY_ID);
			stmtSelectDispositivo.setInt(1, dispositivo.getIdDispositivo());
			rsSelectDispositivo = stmtSelectDispositivo.executeQuery(); // Consulta
			
			if (rsSelectDispositivo.next()) {
				dispositivo.setNombre(rsSelectDispositivo.getString("nombre"));
				dispositivo.setDescripcion(rsSelectDispositivo.getString("descripcion"));
				dispositivo.setFotografia(rsSelectDispositivo.getString("fotografia"));
				dispositivo.setHash(rsSelectDispositivo.getString("hash"));
				dispositivo.setEstaCifrado(rsSelectDispositivo.getString("cifrado"));
				dispositivo.setIdCaso(rsSelectDispositivo.getInt("id_caso"));
				dispositivo.setIdCadenaCustodia(rsSelectDispositivo.getInt("id_cadena_custodia"));
				dispositivo.setIdCicloVida(rsSelectDispositivo.getInt("id_ciclo_vida"));
				dispositivo.setTipo(rsSelectDispositivo.getString("tipo"));
				dispositivo.setMarca(rsSelectDispositivo.getString("marca"));
				dispositivo.setEsClonadoForense(rsSelectDispositivo.getString("es_clonado_forense"));
				dispositivo.setFechaCreacionReg(rsSelectDispositivo.getTimestamp("fecha_creacion_reg"));
				dispositivo.setFechaUltimaModif(rsSelectDispositivo.getTimestamp("fecha_ultima_modif"));
			}
			
			// Obtener evidencias del dispositivo
			
			stmtSelectEvidencias = conn.prepareStatement(SQL_SELECT_EVIDENCIAS_DISPOSITIVO);
			stmtSelectEvidencias.setInt(1, dispositivo.getIdDispositivo());
			rsSelectEvidencias = stmtSelectEvidencias.executeQuery(); // Consulta
			
			List<Evidencia> listaEvidencias = new ArrayList<Evidencia>();
			
			System.out.println("[DispositivoDaoJDBC - encontrar] Lista evidencias: ");
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
				listaEvidencias.add(evidencia);	
				System.out.println("\t"+evidencia.toString());
			}
			dispositivo.setListaEvidenciasDigitales(listaEvidencias);

		} catch (SQLException ex) {
			ex.printStackTrace(System.out);	
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace(System.out);	
		} finally {
			Conexion.close(rsSelectDispositivo);
			Conexion.close(stmtSelectDispositivo);
			Conexion.close(rsSelectEvidencias);
			Conexion.close(stmtSelectEvidencias);
			Conexion.close(conn);
		}
		
		return dispositivo;
	}
	
	public List<Integer> buscarIdDispositivosPorCamposIndividuales(Connection conn, int idDispositivo, String nombreDispositivo, String inicioFechaCreacion, String finFechaCreacion, String inicioFechaUltimaModif, String finFechaUltimaModif) {
		
		List<Integer> listaIdDispositivosEncontrados = new ArrayList<Integer>();
		boolean existePrimerWhere = false;
		String sqlQuery = "SELECT id_dispositivo FROM dispositivo WHERE ";
		
		// ********** idDispositivo **********
		if (idDispositivo != 0) {
			existePrimerWhere = true;
			sqlQuery = sqlQuery+"id_dispositivo = "+idDispositivo+" ";
		}
		
		// ********** nombreDispositivo **********
		if (!nombreDispositivo.isEmpty()) {			
			if (existePrimerWhere) {				
				sqlQuery = sqlQuery+"AND nombre LIKE \'%%"+nombreDispositivo+"%%\' ";				
			} else {			
				existePrimerWhere = true;				
				sqlQuery = sqlQuery+"nombre LIKE \'%%"+nombreDispositivo+"%%\' ";				
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
		
		PreparedStatement stmtSelectDispositivo = null;
		ResultSet rsSelectDispositivo = null;
				
		if (existePrimerWhere) {
			try {
				System.out.println("[DispositivoDaoJDBC - buscarIdDispositivosPorCamposIndividuales] sqlQuery: "+sqlQuery);
				
				conn = Conexion.getConnection();	
				stmtSelectDispositivo = conn.prepareStatement(sqlQuery);
				rsSelectDispositivo = stmtSelectDispositivo.executeQuery(); // Consulta
				
				while (rsSelectDispositivo.next()) {

					int idDispositivoEncontrado = rsSelectDispositivo.getInt("id_dispositivo");
					listaIdDispositivosEncontrados.add(idDispositivoEncontrado);
				}

			} catch (SQLException ex) {
				ex.printStackTrace(System.out);	
			} catch (ClassNotFoundException ex) {
				ex.printStackTrace(System.out);	
			} finally {
				Conexion.close(rsSelectDispositivo);
				Conexion.close(stmtSelectDispositivo);
				Conexion.close(conn);
			}
			
			if (!listaIdDispositivosEncontrados.isEmpty()) {
				System.out.println("[DispositivoDaoJDBC - buscarIdDispositivosPorCamposIndividuales] ID dispositivos encontrados:");
				for (Integer cas: listaIdDispositivosEncontrados) {
					System.out.println("\t"+cas);
				}
			}
		}
		return listaIdDispositivosEncontrados;
	}
	public int insertar(Dispositivo dispositivo) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmtNumDispositivosCaso = null;
		int registros = 0;
		int registrosUpdateNumDispositivosCaso = 0;
		int lastId = 0;
		
		try {
			conn = Conexion.getConnection();
			stmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, dispositivo.getIdCaso());
			stmt.setString(2, dispositivo.getNombre());
			stmt.setString(3, dispositivo.getDescripcion());			
			stmt.setString(4, dispositivo.getFotografia());	
			stmt.setString(5, dispositivo.getHash());	
			if (dispositivo.getEstaCifrado().equalsIgnoreCase("si")) {
				stmt.setString(6, "S");
			} else if (dispositivo.getEstaCifrado().equalsIgnoreCase("no")) {
				stmt.setString(6, "N");
			}
			stmt.setString(7, dispositivo.getTipo());	
			stmt.setString(8,  dispositivo.getMarca());
			stmt.setString(9, dispositivo.getEsClonadoForense());
			java.sql.Timestamp dateFechaActual = new java.sql.Timestamp(System.currentTimeMillis());
			stmt.setTimestamp(10, dateFechaActual);
			stmt.setTimestamp(11, dateFechaActual);
			registros = stmt.executeUpdate(); // Altera el estado de la BDD
			System.out.println("[DispositivoDaoJDBC - insertar] Registros insertados: "+registros);
			
            ResultSet rs= stmt.getGeneratedKeys();
            if (rs.next()) {
            	lastId = rs.getInt(1);         	
            }
            
            // Modificar la tabla de casos para incrementar el número de dispositivos            
            stmtNumDispositivosCaso = conn.prepareStatement(SQL_UPDATE_NUM_DISPOSITIVOS_CASO);
            stmtNumDispositivosCaso.setInt(1, dispositivo.getIdCaso());
			registrosUpdateNumDispositivosCaso = stmtNumDispositivosCaso.executeUpdate(); // Altera el estado de la BDD
			System.out.println("[DispositivoDaoJDBC - insertar] Registros actualizados en el update de numDispositivos del caso: "+registrosUpdateNumDispositivosCaso);
 
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace(System.out);	
		} finally {
			System.out.println("[DispositivoDaoJDBC - insertar] Last Inserted ID = "+lastId);
			Conexion.close(stmtNumDispositivosCaso);
			Conexion.close(stmt);
			Conexion.close(conn);
		}

		return lastId;
	}
	
}
