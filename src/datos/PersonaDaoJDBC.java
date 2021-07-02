package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import domain.Caso;
import domain.Evidencia;
import domain.Persona;

public class PersonaDaoJDBC {
	
	private static final String SQL_INSERT = "INSERT INTO persona(nombre, usuario, password, correo, last_session, telefono) VALUES(?, ?, ?, ?, ? , ?)";
	private static final String SQL_SELECT_BY_ID = "SELECT id_persona, nombre, usuario, password, correo, last_session, telefono FROM persona WHERE id_persona = ?";
	private static final String SQL_SELECT_CASOS_PERSONA = "SELECT id_caso, id_persona FROM caso_persona WHERE id_persona = ?";
	private static final String SQL_SELECT_BY_USUARIO = "SELECT id_persona, usuario, password, nombre, correo, telefono, last_session FROM persona WHERE usuario = ?";
	private static final String SQL_UPDATE_LAST_SESSION = "UPDATE persona SET last_session = ? WHERE usuario = ?";
	private static final String SQL_SELECT = "SELECT id_persona, nombre, usuario, correo, telefono, last_session FROM persona";
	private static final String SQL_UPDATE_CON_CONTRASENYA = "UPDATE persona SET nombre = ?, usuario = ?, password = ?, correo = ?, telefono = ? WHERE id_persona = ?";
	private static final String SQL_UPDATE_SIN_CONTRASENYA = "UPDATE persona SET nombre = ?, usuario = ?, correo = ?, telefono = ? WHERE id_persona = ?";
	private static final String SQL_SELECT_PERSONAS_PARA_CASO_1 = "SELECT id_persona FROM persona";
	private static final String SQL_SELECT_PERSONAS_PARA_CASO_2 = "SELECT id_persona FROM caso_persona WHERE id_caso = ?";
	private static final String SQL_SELECT_PERSONAS_PARA_CASO_3 = "SELECT id_persona, nombre, usuario, correo, telefono, last_session FROM persona WHERE id_persona = ?";
	private static final String SQL_AGREGAR_RESPONSABLE = "INSERT INTO caso_persona (id_caso, id_persona) VALUES(?,?)";
	
	public PersonaDaoJDBC() {	
	}
	
	
	public boolean agregarResponsable(int idCaso, int idPersona) {
		
		boolean acabaOK = false;
		Connection conn = null;
		PreparedStatement stmt = null;
		int registros = 0;
		
		try {
			conn = Conexion.getConnection();
			stmt = conn.prepareStatement(SQL_AGREGAR_RESPONSABLE);
			stmt.setInt(1, idCaso);
			stmt.setInt(2, idPersona);
			registros = stmt.executeUpdate(); // Altera el estado de la BDD
		} catch (Exception e) {
			e.printStackTrace(System.out);	
		} finally {
			Conexion.close(stmt);
			Conexion.close(conn);
		}
		
		if (registros == 1) {
			acabaOK = true;
		}
		
		return acabaOK;
		
	}
	
	public List<Persona> listarPersonasParaAsignarUnCaso(int idCaso) {
		
		List<Persona> personas = new ArrayList<Persona>();
		Connection conn = null;
		PreparedStatement stmt1 = null;
		ResultSet rs1 = null;
		PreparedStatement stmt2 = null;
		ResultSet rs2 = null;

		try {
			conn = Conexion.getConnection();		
			
			// 1. Seleccionar todas las personas disponibles
			stmt1 = conn.prepareStatement(SQL_SELECT_PERSONAS_PARA_CASO_1);
			rs1 = stmt1.executeQuery(); // Consulta
			System.out.println("[PersonaDaoJDBC - listarPersonasParaAsignarUnCaso] 1. Seleccionar todas las personas disponibles:");
			while (rs1.next()) {		
				Persona persona = new Persona(rs1.getInt("id_persona"));
				System.out.println("\t"+rs1.getInt("id_persona"));
				personas.add(persona);
			}
			
			try {
				// 2. Seleccionar las personas que tienen asignado este caso
				stmt2 = conn.prepareStatement(SQL_SELECT_PERSONAS_PARA_CASO_2);
				stmt2.setInt(1, idCaso);
				rs2 = stmt2.executeQuery(); // Consulta
				System.out.println("[PersonaDaoJDBC - listarPersonasParaAsignarUnCaso] 2. Seleccionar las personas que tienen asignado este caso:");	
				System.out.println("[PersonaDaoJDBC - listarPersonasParaAsignarUnCaso] 3. Quitar de la lista 1 las personas de la lista 2:");
				while (rs2.next()) {		
					System.out.println("\t"+rs2.getInt("id_persona"));			
					// 3. Quitar de la lista 1 las personas de la lista 2
					ListIterator<Persona> iter = personas.listIterator();				
					while (iter.hasNext()) {
						if (iter.next().getIdPersona() == rs2.getInt("id_persona")) {						
							iter.remove();
							System.out.println("\tSe ha eliminado esta persona de la lista: "+rs2.getInt("id_persona"));						
						}					
					}	
				}
			} catch (Exception e) {
				e.printStackTrace(System.out);	
			} finally {
				Conexion.close(rs2);
				Conexion.close(stmt2);
			}

			// 4. Seleccionar info de las personas
			
			PreparedStatement stmt3 = null;
			ResultSet rs3 = null;

			for (Persona persona: personas) {
				
				try {
					stmt3 = conn.prepareStatement(SQL_SELECT_PERSONAS_PARA_CASO_3);
					stmt3.setInt(1, persona.getIdPersona());
					rs3 = stmt3.executeQuery(); // Consulta
					while (rs3.next()) {	
						if (persona.getIdPersona() == rs3.getInt("id_persona")) {
							persona.setCorreo(rs3.getString("correo"));
							persona.setNombre(rs3.getString("nombre"));
							persona.setTelefono(rs3.getString("telefono"));
							persona.setUltimaSesion(rs3.getTimestamp("last_session"));
							persona.setUsuario(rs3.getString("usuario"));	
						}
					}
				} catch (Exception e) {
					e.printStackTrace(System.out);	
				} finally {
					Conexion.close(rs3);
					Conexion.close(stmt3);
				}	
			}
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);	
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace(System.out);	
		} finally {
			Conexion.close(rs1);
			Conexion.close(stmt1);
			Conexion.close(conn);
		}
		return personas;
	}
	
	public int actualizar(Persona persona) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		int registros = 0;
		try {
			conn = Conexion.getConnection();
			
			if (persona.getContrasena().isEmpty()) {
				stmt = conn.prepareStatement(SQL_UPDATE_SIN_CONTRASENYA);
				stmt.setString(1, persona.getNombre());
				stmt.setString(2, persona.getUsuario());
				stmt.setString(3, persona.getCorreo());
				stmt.setString(4, persona.getTelefono());
				stmt.setInt(5, persona.getIdPersona());
			} else {
				stmt = conn.prepareStatement(SQL_UPDATE_CON_CONTRASENYA);
				stmt.setString(1, persona.getNombre());
				stmt.setString(2, persona.getUsuario());
				stmt.setString(3, persona.getContrasena());
				stmt.setString(4, persona.getCorreo());
				stmt.setString(5, persona.getTelefono());
				stmt.setInt(6, persona.getIdPersona());
			}
			registros = stmt.executeUpdate(); // Altera el estado de la BDD

			System.out.println("[PersonaDaoJDBC - actualizar] Registros actualizados: "+registros);

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
	
	public List<Persona> listar() {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Persona persona = null;
		List<Persona> listaPersonas = new ArrayList<Persona>();
		
		try {
			conn = Conexion.getConnection();			
			stmt = conn.prepareStatement(SQL_SELECT);
			rs = stmt.executeQuery(); // Consulta
			while (rs.next()) {
				int idPersona = rs.getInt("id_persona");
				String nombre = rs.getString("nombre");
				String usuario = rs.getString("usuario");
				String correo = rs.getString("correo");
				String telefono = rs.getString("telefono");
				Date fechaUltimaSesion = rs.getTimestamp("last_session");
				persona = new Persona(idPersona, nombre, usuario, correo, telefono, fechaUltimaSesion);
				listaPersonas.add(persona);
				
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
		
		return listaPersonas;
	}
	
	public List<Persona> buscarPersona(int idUsuario, String nombreReal, String nombreUsuario, String correo, String telefono, String inicioFechaSesion, String finFechaSesion) {
		
		// TODO en la pantalla hacer obligatorio informar mínimo 1 campo para buscar
		
		List<Persona> listaPersonasEncontradas = new ArrayList<Persona>();
		
		Connection conn = null;
		
		// ********** campos individuales (idUsuario, nombreReal, nombreUsuario, correo, telefono, inicioFechaSesion, finFechaSesion) **********
		List<Integer> listaIdUsuariosCamposIndividuales = new ArrayList<Integer>();
		if (idUsuario != 0 || !nombreReal.isEmpty() || !nombreUsuario.isEmpty() || !correo.isEmpty() || !telefono.isEmpty() || !inicioFechaSesion.isEmpty() || !finFechaSesion.isEmpty()) {
			listaIdUsuariosCamposIndividuales = buscarIdPersonasPorCamposIndividuales(conn, idUsuario, nombreReal, nombreUsuario, correo, telefono, inicioFechaSesion, finFechaSesion);
		}

		System.out.println("[PersonaDaoJDBC - buscarPersona] Persona/s a devolver: ");
		for (Integer idPersonaEncontrada: listaIdUsuariosCamposIndividuales) {
			System.out.println("\t"+idPersonaEncontrada);
			Persona personaAencontrar = new Persona(idPersonaEncontrada);
			Persona personaEncontrada = encontrar(personaAencontrar);
			listaPersonasEncontradas.add(personaEncontrada);
		}

		return listaPersonasEncontradas;
	
	}
	
	public List<Integer> buscarIdPersonasPorCamposIndividuales(Connection conn, int idUsuario, String nombreReal, String nombreUsuario, String correo, String telefono, String inicioFechaSesion, String finFechaSesion) {
		
		List<Integer> listaIdPersonasEncontradas = new ArrayList<Integer>();
		boolean existePrimerWhere = false;
		String sqlQuery = "SELECT id_persona FROM persona WHERE ";
		
		// ********** idUsuario **********
		if (idUsuario != 0) {	
			existePrimerWhere = true;			
			sqlQuery = sqlQuery+"id_persona = "+idUsuario+" ";		
		}
		
		// ********** nombreReal **********
		if (!nombreReal.isEmpty()) {			
			if (existePrimerWhere) {				
				sqlQuery = sqlQuery+"AND nombre LIKE \'%%"+nombreReal+"%%\' ";				
			} else {			
				existePrimerWhere = true;				
				sqlQuery = sqlQuery+"nombre LIKE \'%%"+nombreReal+"%%\' ";				
			}	
		}

		// ********** nombreUsuario **********
		if (!nombreUsuario.isEmpty()) {			
			if (existePrimerWhere) {				
				sqlQuery = sqlQuery+"AND usuario = \'"+nombreUsuario+"\' ";				
			} else {			
				existePrimerWhere = true;				
				sqlQuery = sqlQuery+"usuario = \'"+nombreUsuario+"\' ";				
			}
		}

		// ********** correo **********
		if (!correo.isEmpty()) {			
			if (existePrimerWhere) {				
				sqlQuery = sqlQuery+"AND correo LIKE \'%%"+correo+"%%\' ";				
			} else {			
				existePrimerWhere = true;				
				sqlQuery = sqlQuery+"correo LIKE \'%%"+correo+"%%\' ";				
			}
		}
		
		// ********** telefono **********
		if (!telefono.isEmpty()) {			
			if (existePrimerWhere) {				
				sqlQuery = sqlQuery+"AND telefono = \'"+telefono+"\' ";				
			} else {			
				existePrimerWhere = true;				
				sqlQuery = sqlQuery+"telefono = \'"+telefono+"\' ";				
			}
		}	
		
		
		// ********** inicioFechaSesion **********
		if (!inicioFechaSesion.isEmpty()) {
			if (existePrimerWhere) {				
				sqlQuery = sqlQuery+"AND last_session >= \'"+inicioFechaSesion+" 00:00:00\' ";				
			} else {			
				existePrimerWhere = true;				
				sqlQuery = sqlQuery+"last_session >= \'"+inicioFechaSesion+" 00:00:00\' ";				
			}	
		}
		
		// ********** finFechaUltimaModif **********
		if (!finFechaSesion.isEmpty()) {
			if (existePrimerWhere) {				
				sqlQuery = sqlQuery+"AND last_session <= \'"+finFechaSesion+" 23:59:59\' ";				
			} else {			
				existePrimerWhere = true;				
				sqlQuery = sqlQuery+"last_session <= \'"+finFechaSesion+" 23:59:59\' ";				
			}	
		}
		
		// Buscar en la tabla de personas con esta información
		
		PreparedStatement stmtSelectPersona = null;
		ResultSet rsSelectPersona = null;
				
		if (existePrimerWhere) {
			try {
				System.out.println("[PersonaDaoJDBC - buscarIdPersonasPorCamposIndividuales] sqlQuery: "+sqlQuery);
				
				conn = Conexion.getConnection();	
				stmtSelectPersona = conn.prepareStatement(sqlQuery);
				rsSelectPersona = stmtSelectPersona.executeQuery(); // Consulta
				
				while (rsSelectPersona.next()) {

					int idPersonaEncontrada = rsSelectPersona.getInt("id_persona");
					listaIdPersonasEncontradas.add(idPersonaEncontrada);
				}

			} catch (SQLException ex) {
				ex.printStackTrace(System.out);	
			} catch (ClassNotFoundException ex) {
				ex.printStackTrace(System.out);	
			} finally {
				Conexion.close(rsSelectPersona);
				Conexion.close(stmtSelectPersona);
				Conexion.close(conn);
			}
			
			if (!listaIdPersonasEncontradas.isEmpty()) {
				System.out.println("[PersonaDaoJDBC - buscarIdPersonasPorCamposIndividuales] ID personas encontradas:");
				for (Integer pers: listaIdPersonasEncontradas) {
					System.out.println("\t"+pers);
				}
			}
		}
		return listaIdPersonasEncontradas;
	}
	
	
	public int insertar(Persona persona) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		int registros = 0;
		int lastId = 0;
		
		try {
			conn = Conexion.getConnection();
			stmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, persona.getNombre());
			stmt.setString(2, persona.getUsuario());			
			stmt.setString(3, persona.getContrasena());
			stmt.setString(4, persona.getCorreo());	
			java.sql.Timestamp dateFechaActual = new java.sql.Timestamp(System.currentTimeMillis());
			stmt.setTimestamp(5, dateFechaActual);	
			stmt.setString(6, persona.getTelefono());
			registros = stmt.executeUpdate(); // Altera el estado de la BDD
			System.out.println("Registros insertados: "+registros);
			
            ResultSet rs= stmt.getGeneratedKeys();
            if (rs.next()) {
            	lastId = rs.getInt(1);         	
            }
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace(System.out);	
		} finally {
			System.out.println("Last Inserted ID = "+lastId);
			Conexion.close(stmt);
			Conexion.close(conn);
		}

		return lastId;
	}
	
	public boolean login(Persona persona) {
		
		Connection conn = null;
		PreparedStatement stmtSelectPersona = null;
		ResultSet rsSelectPersona = null;
		PreparedStatement stmtLastSession = null;
		//ResultSet rsUpdateLastSession = null;
		int registros = 0;
		
		try {
			conn = Conexion.getConnection();
			stmtSelectPersona = conn.prepareStatement(SQL_SELECT_BY_USUARIO);
			stmtSelectPersona.setString(1, persona.getUsuario());
			rsSelectPersona = stmtSelectPersona.executeQuery(); // Consulta
			if (rsSelectPersona.next()) {
				if (persona.getContrasena().equals(rsSelectPersona.getString("password"))) {
					// Actualizamos last_session de la BDD
					stmtLastSession = conn.prepareStatement(SQL_UPDATE_LAST_SESSION);
					java.sql.Timestamp dateFechaActual = new java.sql.Timestamp(System.currentTimeMillis());
					stmtLastSession.setTimestamp(1, dateFechaActual);						
					stmtLastSession.setString(2, persona.getUsuario());					
					registros = stmtLastSession.executeUpdate(); // Altera el estado de la BDD
					System.out.println("Registros actualizados: "+registros);	
					return true;
				} else {
					return false;
				}			
			}
			return false;
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
			return false;
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace(System.out);	
			return false;
		} finally {
			Conexion.close(rsSelectPersona);
			Conexion.close(stmtSelectPersona);
			Conexion.close(conn);
		}
	}
	
	public Persona encontrarPorUsuario(Persona persona) {
		
		Connection conn = null;
		
		PreparedStatement stmtSelectPersona = null;
		ResultSet rsSelectPersona = null;
		
		PreparedStatement stmtSelectCasos = null;
		ResultSet rsSelectCasos = null;
		
		try {
			conn = Conexion.getConnection();
			
			// Obtener información básica del caso
			
			stmtSelectPersona = conn.prepareStatement(SQL_SELECT_BY_USUARIO);
			stmtSelectPersona.setString(1, persona.getUsuario());
			rsSelectPersona = stmtSelectPersona.executeQuery(); // Consulta
			
			if (rsSelectPersona.next()) {
				persona.setIdPersona(rsSelectPersona.getInt("id_persona"));
				persona.setNombre(rsSelectPersona.getString("nombre"));
				persona.setUsuario(rsSelectPersona.getString("usuario"));
				persona.setContrasena(rsSelectPersona.getString("password"));
				persona.setCorreo(rsSelectPersona.getString("correo"));
				Date fechaLastSession = rsSelectPersona.getDate("last_session");
				persona.setUltimaSesion(fechaLastSession);
				persona.setTelefono(rsSelectPersona.getString("telefono"));
			}
			
			// Obtener casos de la persona
			
			System.out.println("PRUEBAAAAAA: "+persona);
			
			stmtSelectCasos = conn.prepareStatement(SQL_SELECT_CASOS_PERSONA);
			stmtSelectCasos.setInt(1, persona.getIdPersona());
			rsSelectCasos = stmtSelectCasos.executeQuery(); // Consulta
			
			List<Caso> listaCasos = new ArrayList<Caso>();
			List<Integer> listaIdCasos = new ArrayList<Integer>();
			
			System.out.println("Lista casos: ");
			while (rsSelectCasos.next()) {
				listaIdCasos.add(rsSelectCasos.getInt("id_caso"));
				System.out.println("\t"+rsSelectCasos.getInt("id_caso"));
			}
			
			for (Integer idCaso: listaIdCasos) {
				Caso caso = new Caso(idCaso);
				CasoDaoJDBC casoDaoJDBC = new CasoDaoJDBC();
				Caso casoEncontrado = casoDaoJDBC.encontrar(caso);
				listaCasos.add(casoEncontrado);			
			}
			persona.setListaCasos(listaCasos);

		} catch (SQLException ex) {
			ex.printStackTrace(System.out);	
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace(System.out);	
		} finally {
			Conexion.close(rsSelectPersona);
			Conexion.close(stmtSelectPersona);
			Conexion.close(rsSelectCasos);
			Conexion.close(stmtSelectCasos);
			Conexion.close(conn);
		}
		
		return persona;
		
	}

	
	public Persona encontrar(Persona persona) {
		
		Connection conn = null;
		
		PreparedStatement stmtSelectPersona = null;
		ResultSet rsSelectPersona = null;
		PreparedStatement stmtSelectCasos = null;
		ResultSet rsSelectCasos = null;
		
		try {
			conn = Conexion.getConnection();
			
			// Obtener información básica del caso
			
			stmtSelectPersona = conn.prepareStatement(SQL_SELECT_BY_ID);
			stmtSelectPersona.setInt(1, persona.getIdPersona());
			rsSelectPersona = stmtSelectPersona.executeQuery(); // Consulta
			
			if (rsSelectPersona.next()) {
				persona.setNombre(rsSelectPersona.getString("nombre"));
				persona.setUsuario(rsSelectPersona.getString("usuario"));
				persona.setContrasena(rsSelectPersona.getString("password"));
				persona.setCorreo(rsSelectPersona.getString("correo"));
				persona.setUltimaSesion(rsSelectPersona.getDate("last_session"));
				persona.setTelefono(rsSelectPersona.getString("telefono"));
			}
			
			// Obtener casos de la persona
			
			stmtSelectCasos = conn.prepareStatement(SQL_SELECT_CASOS_PERSONA);
			stmtSelectCasos.setInt(1, persona.getIdPersona());
			rsSelectCasos = stmtSelectCasos.executeQuery(); // Consulta
			
			List<Caso> listaCasos = new ArrayList<Caso>();
			List<Integer> listaIdCasos = new ArrayList<Integer>();
			
			System.out.println("Lista casos: ");
			while (rsSelectCasos.next()) {
				listaIdCasos.add(rsSelectCasos.getInt("id_caso"));
				System.out.println("\t"+rsSelectCasos.getInt("id_caso"));
			}
			
			for (Integer idCaso: listaIdCasos) {
				Caso caso = new Caso(idCaso);
				CasoDaoJDBC casoDaoJDBC = new CasoDaoJDBC();
				Caso casoEncontrado = casoDaoJDBC.encontrar(caso);
				listaCasos.add(casoEncontrado);			
			}
			persona.setListaCasos(listaCasos);

		} catch (SQLException ex) {
			ex.printStackTrace(System.out);	
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace(System.out);	
		} finally {
			Conexion.close(rsSelectPersona);
			Conexion.close(stmtSelectPersona);
			Conexion.close(rsSelectCasos);
			Conexion.close(stmtSelectCasos);
			Conexion.close(conn);
		}
		
		return persona;
		
	}

}
