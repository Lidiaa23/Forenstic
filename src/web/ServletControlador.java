package web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import datos.CadenaCustodiaDaoJDBC;
import datos.CasoDaoJDBC;
import datos.DispositivoDaoJDBC;
import datos.EvidenciaDaoJDBC;
import datos.PersonaDaoJDBC;
import datos.RegistroCadenaDaoJDBC;
import domain.CadenaCustodia;
import domain.Caso;
import domain.Dispositivo;
import domain.Evidencia;
import domain.Hash;
import domain.Persona;
import domain.RegistroCadenaCustodia;
import utils.Utils;

/**
 * Servlet implementation class HolaServlet
 */
@WebServlet("/ServletControlador")
@MultipartConfig
public class ServletControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletControlador() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String accion = request.getParameter("accion");
		
		System.out.println("Buscando operación GET");
		
		if (accion != null) {			
			switch (accion) {
				case "editar":
					this.editarCaso(request, response);
					break;
				case "editarDispositivo":
					this.editarDispositivo(request, response);
					break;
				case "editarEvidencia":
					this.editarEvidencia(request, response);
					break;
				case "editarCadenaCustodia":
					this.editarCadenaCustodia(request, response);
					break;
				case "editarRegistroCadena":
					this.editarRegistroCadena(request, response);
					break;
				case "editarUsuario":
					this.editarUsuario(request, response);
					break;
				case "insertarCadenaCustodiaConDispositivoSeleccionado":
					this.insertarCadenaCustodiaConDispositivoSeleccionado(request, response);
					break;
				case "eliminarCasoDesdeMisCasos":
					this.eliminarCasoDesdeMisCasos(request, response);
					break;
				case "eliminarDispositivo":
					this.eliminarDispositivo(request, response);
					break;
				case "eliminarDispositivoDesdeConsultarCasos":
					this.eliminarDispositivoDesdeConsultarCasos(request, response);
					break;
				case "eliminarRegistroDesdeConsultarCadena":
					this.eliminarRegistroDesdeConsultarCadena(request, response);
					break;
				case "eliminarEvidenciaDesdeConsultarCasos":
					this.eliminarEvidenciaDesdeConsultarCasos(request, response);
					break;
				case "eliminarRegistroCadenaDesdeConsultarCadena":
					this.eliminarRegistroCadenaDesdeConsultarCadena(request, response);
					break;
				case "eliminarDispositivoDesdeMisDispositivos":
					this.eliminarDispositivoDesdeMisDispositivos(request, response);
					break;
				case "eliminarEvidenciaDesdeConsultarDispositivos":
					this.eliminarEvidenciaDesdeConsultarDispositivos(request, response);
					break;
				case "eliminarEvidenciaDesdeMisEvidencias":
					this.eliminarEvidenciaDesdeMisEvidencias(request, response);
					break;
				case "consultar":
					this.consultarCaso(request, response);
					break;
				case "consultarDispositivo":
					this.consultarDispositivo(request, response);
					break;
				case "consultarEvidenciaDigital":
					this.consultarEvidenciaDigital(request, response);
					break;
				case "consultarCadenaCustodia":
					this.consultarCadenaCustodia(request, response);
					break;
				case "consultarUsuario":
					this.consultarUsuario(request, response);
					break;
				case "misCasos":
					this.misCasos(request, response);
					break;
				case "misDispositivos":
					this.misDispositivos(request, response);
					break;
				case "misEvidencias":
					this.misEvidencias(request, response);
					break;
				case "desconectar":
					this.desconectar(request, response);
					break;
				case "consultarMisCasosParaNuevoDispositivo":
					this.consultarMisCasosParaNuevoDispositivo(request, response);
					break;
				case "consultarMisCasosParaNuevaEvidencia":
					this.consultarMisCasosParaNuevaEvidencia(request, response);
					break;
				case "consultarMisDispositivosParaNuevaCadena":
					this.consultarMisDispositivosParaNuevaCadena(request, response);
					break;					
				case "listarTodosLosDispositivos":
					this.listarTodosLosDispositivos(request, response);
					break;
				case "listarTodasLasEvidencias":
					this.listarTodasLasEvidencias(request, response);
					break;
				case "listarTodosLosUsuarios":
					this.listarTodosLosUsuarios(request, response);
					break;
				case "agregarDispositivoAlCaso":
					this.agregarDispositivoAlCaso(request, response);
					break;
				case "agregarEvidenciaAlCaso":
					this.agregarEvidenciaAlCaso(request, response);
					break;
				case "agregarEvidenciaAlDispositivo":
					this.agregarEvidenciaAlDispositivo(request, response);
					break;
				case "consultarCadenaCustodiaDeDispositivo":
					this.consultarCadenaCustodiaDeDispositivo(request, response);
					break;
				case "insertarRegistroCadenaCustodia":
					this.insertarRegistroCadenaCustodia(request, response);
					break;	
				case "agregarResponsableAlCaso":
					this.agregarResponsableAlCaso(request, response);
					break;	
				default:
					//this.accionDefault(request, response);		
					this.loginUsuario(request, response);
			}
		} else {
			this.accionDefault(request, response);
		}	

	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String accion = request.getParameter("accion");
		System.out.println("Buscando operación POST");
		
		if (accion != null) {			
			switch (accion) {	
				case "insertar":
					this.insertarCaso(request, response);
					break;
				case "insertarDispositivo":
					this.insertarDispositivo(request, response);
					break;
				case "agregarResponsableForm":
					this.agregarResponsableForm(request, response);
					break;
				case "insertarEvidencia":
					this.insertarEvidencia(request, response);
					break;
				case "insertarCadenaCustodia":
					this.insertarCadenaCustodia(request, response);
					break;
				case "insertarEvidenciaConCasoSeleccionado":
					this.insertarEvidenciaConCasoSeleccionado(request, response);
					break;
				case "insertarCadenaCustodiaConDispositivoSeleccionado":
					this.insertarCadenaCustodiaConDispositivoSeleccionado(request, response);
					break;		
				case "insertarRegistroCadenaCustodiaForm":
					this.insertarRegistroCadenaCustodiaForm(request, response);
					break;					
				case "modificar":
					this.modificarCaso(request, response);
					break;
				case "modificarDispositivo":
					this.modificarDispositivo(request, response);
					break;
				case "modificarEvidencia":
					this.modificarEvidencia(request, response);
					break;
				case "modificarCadenaCustodia":
					this.modificarCadenaCustodia(request, response);
					break;
				case "modificarRegistroCadena":
					this.modificarRegistroCadena(request, response);
					break;
				case "modificarUsuario":
					this.modificarUsuario(request, response);
					break;					
				case "buscarCaso":
					this.buscarCaso(request, response);
					break;
				case "buscarDispositivo":
					this.buscarDispositivo(request, response);
					break;
				case "buscarEvidencia":
					this.buscarEvidencia(request, response);
					break;
				case "buscarUsuario":
					this.buscarUsuario(request, response);
					break;
				case "insertarUsuario":
					this.insertarUsuario(request, response);
					break;
				case "loginUsuario":
					this.loginUsuario(request, response);
					break;
				default:
					//this.accionDefault(request, response);
					this.loginUsuario(request, response);
			}
		} else {
			this.accionDefault(request, response);
		}		
	}
	
	private void consultarMisCasosParaNuevoDispositivo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sesion = request.getSession();
		String usuario = (String) sesion.getAttribute("usuario");
		int idUsuario = (int) sesion.getAttribute("idUsuario");
		System.out.println("[ServletControlador - consultarMisCasosParaNuevoDispositivo] USUARIO: "+usuario);
		
		List<Caso> listaMisCasos = new CasoDaoJDBC().listarMisCasos(idUsuario);
		System.out.println("[ServletControlador - consultarMisCasosParaNuevoDispositivo] ListaMisCasos = " + listaMisCasos);
				
		sesion.setAttribute("listaMisCasos", listaMisCasos);
		
		//request.getRequestDispatcher("misCasos.jsp").forward(request, response); //la URL no cambia
		response.sendRedirect("agregarDispositivo.jsp"); // la URL sí cambia, redirige
	}
	
	private void consultarMisCasosParaNuevaEvidencia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sesion = request.getSession();
		String usuario = (String) sesion.getAttribute("usuario");
		int idUsuario = (int) sesion.getAttribute("idUsuario");
		System.out.println("[ServletControlador - consultarMisCasosParaNuevaEvidencia] USUARIO: "+usuario);
		
		List<Caso> listaMisCasos = new CasoDaoJDBC().listarMisCasos(idUsuario);
		System.out.println("[ServletControlador - consultarMisCasosParaNuevaEvidencia] ListaMisCasos = " + listaMisCasos);
				
		sesion.setAttribute("listaMisCasos", listaMisCasos);
		
		//request.getRequestDispatcher("misCasos.jsp").forward(request, response); //la URL no cambia
		response.sendRedirect("agregarEvidenciaDigital.jsp"); // la URL sí cambia, redirige
	}
	
	private void consultarMisDispositivosParaNuevaCadena(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sesion = request.getSession();
		String usuario = (String) sesion.getAttribute("usuario");
		int idUsuario = (int) sesion.getAttribute("idUsuario");
		System.out.println("[ServletControlador - consultarMisDispositivosParaNuevaCadena] USUARIO: "+usuario);
		
		List<Dispositivo> listaMisDispositivosSinCadenaCustodia = new DispositivoDaoJDBC().listarMisDispositivosSinCadenaCustodia(idUsuario);
		System.out.println("[ServletControlador - consultarMisDispositivosParaNuevaCadena] listaMisDispositivosSinCadenaCustodia = " + listaMisDispositivosSinCadenaCustodia);
				
		sesion.setAttribute("listaMisDispositivosSinCadenaCustodia", listaMisDispositivosSinCadenaCustodia);
		
		//request.getRequestDispatcher("misCasos.jsp").forward(request, response); //la URL no cambia
		response.sendRedirect("agregarCadenaCustodia.jsp"); // la URL sí cambia, redirige
	}
	
	
	private void agregarDispositivoAlCaso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sesion = request.getSession();
		String usuario = (String) sesion.getAttribute("usuario");
		int idUsuario = (int) sesion.getAttribute("idUsuario");
		String idCaso = request.getParameter("idCaso");
		String nombreCaso = request.getParameter("nombreCaso");
		request.setAttribute("idCaso", idCaso);	
		request.setAttribute("nombreCaso", nombreCaso);	
		request.getRequestDispatcher("agregarDispositivoAlCaso.jsp").forward(request, response);
	
	}
	
	private void agregarEvidenciaAlCaso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sesion = request.getSession();
		String usuario = (String) sesion.getAttribute("usuario");
		int idUsuario = (int) sesion.getAttribute("idUsuario");
		String idCaso = request.getParameter("idCaso");
		int idCasoInt = 0;
		if (!idCaso.isEmpty()) {
			idCasoInt = Integer.parseInt(idCaso);
		}
		String nombreCaso = request.getParameter("nombreCaso");
		request.setAttribute("idCaso", idCaso);	
		request.setAttribute("nombreCaso", nombreCaso);	

		// Buscar los dispositivos asociados a este caso
		
		List<Dispositivo> listaDispositivosDeCaso = new DispositivoDaoJDBC().listarDispositivosDeUnCaso(idCasoInt);
		System.out.println("[ServletControlador - agregarEvidenciaAlCaso] ListaDispositivosDeCaso = " + listaDispositivosDeCaso);
				
		sesion.setAttribute("listaDispositivosDeCaso", listaDispositivosDeCaso);

		request.getRequestDispatcher("agregarEvidenciaAlCaso.jsp").forward(request, response);
	
	}
	
	private void agregarEvidenciaAlDispositivo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sesion = request.getSession();
		String usuario = (String) sesion.getAttribute("usuario");
		int idUsuario = (int) sesion.getAttribute("idUsuario");
		String idDispositivo = request.getParameter("idDispositivo");
		String nombreDispositivo = request.getParameter("nombreDispositivo");
		String idCaso = request.getParameter("idCaso");
		request.setAttribute("idDispositivo", idDispositivo);	
		request.setAttribute("nombreDispositivo", nombreDispositivo);	
		request.setAttribute("idCaso", idCaso);	
		request.getRequestDispatcher("agregarEvidenciaAlDispositivo.jsp").forward(request, response);
	
	}
	
	
	
	private void consultarCadenaCustodiaDeDispositivo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sesion = request.getSession();
		String usuario = (String) sesion.getAttribute("usuario");
		int idUsuario = (int) sesion.getAttribute("idUsuario");

		// Obtener información del dispositivo
		String idDispositivo = request.getParameter("idDispositivo");
		String nombreDispositivo = request.getParameter("nombreDispositivo");
		String descripcionDispositivo = request.getParameter("descripcionDispositivo");
		String coleccionAdquisicionDispositivo = request.getParameter("coleccionAdquisicionDispositivo");
		String hashDispositivo = request.getParameter("hashDispositivo");

		CadenaCustodia cadenaCustodia = new CadenaCustodia();
		cadenaCustodia.setIdDispositivo(Integer.parseInt(idDispositivo));
		cadenaCustodia.setNombreDispositivo(nombreDispositivo);
		cadenaCustodia.setDescripcionDispositivo(descripcionDispositivo);
		cadenaCustodia.setColeccionAdquisicionDispositivo(coleccionAdquisicionDispositivo);
		cadenaCustodia.setHashDispositivo(hashDispositivo);
		cadenaCustodia = new CadenaCustodiaDaoJDBC().encontrarPorIdDispositivo(cadenaCustodia);
		
		System.out.println("[ServletControlador - consultarCadenaCustodiaDeDispositivo] idDispositivo: "+idDispositivo+", nombreDispositivo: "+nombreDispositivo);
		request.setAttribute("idDispositivo", idDispositivo);	
		request.setAttribute("cadenaCustodia", cadenaCustodia);
		
		// ¿El usuario tiene permisos sobre esta cadena de custodia?
		boolean tienePermisos = new CadenaCustodiaDaoJDBC().permisosUsuario(cadenaCustodia.getIdCadenaCustodia(), cadenaCustodia.getIdDispositivo(), idUsuario);
		
		if (tienePermisos) {
			request.setAttribute("tienePermisos", "si");
		} else {
			request.setAttribute("tienePermisos", "no");
		}

		String jspConsultar = "/WEB-INF/paginas/cadenaCustodia/consultarCadenaCustodia.jsp";
		request.getRequestDispatcher(jspConsultar).forward(request, response);

	}
	
	private void misCasos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sesion = request.getSession();
		String usuario = (String) sesion.getAttribute("usuario");
		int idUsuario = (int) sesion.getAttribute("idUsuario");
		
		List<Caso> listaMisCasos = new CasoDaoJDBC().listarMisCasos(idUsuario);
		System.out.println("[ServletControlador - misCasos] ListaMisCasos = " + listaMisCasos);
				
		sesion.setAttribute("listaMisCasos", listaMisCasos);
		
		//response.sendRedirect("misCasos.jsp"); // la URL sí cambia, redirige
		request.getRequestDispatcher("misCasos.jsp").forward(request, response); //la URL no cambia
	}
	
	private void misDispositivos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sesion = request.getSession();
		String usuario = (String) sesion.getAttribute("usuario");
		int idUsuario = (int) sesion.getAttribute("idUsuario");
		
		List<Dispositivo> listaMisDispositivos = new DispositivoDaoJDBC().listarMisDispositivos(idUsuario);
		System.out.println("[ServletControlador - misDispositivos] ListaMisDispositivos = " + listaMisDispositivos);
				
		sesion.setAttribute("listaMisDispositivos", listaMisDispositivos);

		//response.sendRedirect("misDispositivos.jsp"); // la URL sí cambia, redirige
		request.getRequestDispatcher("misDispositivos.jsp").forward(request, response);
	}
	
	private void misEvidencias(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sesion = request.getSession();
		String usuario = (String) sesion.getAttribute("usuario");
		int idUsuario = (int) sesion.getAttribute("idUsuario");
		
		List<Evidencia> listaMisEvidencias = new EvidenciaDaoJDBC().listarMisEvidencias(idUsuario);
		System.out.println("[ServletControlador - misEvidencias] ListaMisEvidencias = " + listaMisEvidencias);
				
		sesion.setAttribute("listaMisEvidencias", listaMisEvidencias);
		
		request.getRequestDispatcher("misEvidencias.jsp").forward(request, response);
		//response.sendRedirect("misEvidencias.jsp"); // la URL sí cambia, redirige
	}
	
	private void desconectar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sesion = request.getSession();
		sesion.invalidate();
		
		//sesion = request.getSession();
		//sesion.setAttribute("logout", true);
		
		request.setAttribute("logoutOK", "logoutOK");
		
		//request.getRequestDispatcher("misCasos.jsp").forward(request, response); //la URL no cambia
		//response.sendRedirect("login.jsp"); // la URL sí cambia, redirige
		
		// Redirigimos
		request.getRequestDispatcher("login.jsp").forward(request, response);
		
		
	}
	
	private void accionDefault(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Caso> listaCasos = new CasoDaoJDBC().listar();
		System.out.println("[ServletControlador - accionDefault] ListaCasos = " + listaCasos);
		
		HttpSession sesion = request.getSession();
		sesion.setAttribute("listaCasos", listaCasos);
		sesion.setAttribute("totalCasos", listaCasos.size());
		
		//request.getRequestDispatcher("casos.jsp").forward(request, response); la URL no cambia
		response.sendRedirect("casos.jsp"); // la URL sí cambia, redirige
	}
	
	private void listarTodosLosDispositivos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Dispositivo> listaDispositivos = new DispositivoDaoJDBC().listar();
		System.out.println("[ServletControlador - listarTodosLosDispositivos] ListaDispositivos = " + listaDispositivos);
		
		HttpSession sesion = request.getSession();
		
		sesion.setAttribute("listaDispositivos", listaDispositivos);
		sesion.setAttribute("totalDispositivos", listaDispositivos.size());
		
		//request.getRequestDispatcher("casos.jsp").forward(request, response); la URL no cambia
		response.sendRedirect("dispositivos.jsp"); // la URL sí cambia, redirige
		
		// Redirigimos
		//String jspConsultar = "dispositivos.jsp";
		//request.getRequestDispatcher(jspConsultar).forward(request, response);
		
	}
	
	private void listarTodasLasEvidencias(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Evidencia> listaEvidencias = new EvidenciaDaoJDBC().listar();
		System.out.println("[ServletControlador - listarTodasLasEvidencias] ListaEvidencias = " + listaEvidencias);
		
		HttpSession sesion = request.getSession();
		
		sesion.setAttribute("listaEvidencias", listaEvidencias);
		sesion.setAttribute("totalEvidencias", listaEvidencias.size());
		
		//request.getRequestDispatcher("casos.jsp").forward(request, response); la URL no cambia
		response.sendRedirect("evidencias.jsp"); // la URL sí cambia, redirige
		
		// Redirigimos
		//String jspConsultar = "dispositivos.jsp";
		//request.getRequestDispatcher(jspConsultar).forward(request, response);
		
	}
	
	private void listarTodosLosUsuarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Persona> listaPersonas = new PersonaDaoJDBC().listar();
		System.out.println("[ServletControlador - listarTodosLosUsuarios] ListaPersonas = " + listaPersonas);
		
		HttpSession sesion = request.getSession();
		
		sesion.setAttribute("listaPersonas", listaPersonas);
		sesion.setAttribute("totalPersonas", listaPersonas.size());
		
		//request.getRequestDispatcher("casos.jsp").forward(request, response); la URL no cambia
		response.sendRedirect("personas.jsp"); // la URL sí cambia, redirige
		
		// Redirigimos
		//String jspConsultar = "dispositivos.jsp";
		//request.getRequestDispatcher(jspConsultar).forward(request, response);
		
	}	

	
	private void insertarCaso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sesion = request.getSession();
		
		// Recuperar los valores del formulario agregarCaso
		String nombre = (String) request.getParameter("nombre");
		String descripcion = (String) request.getParameter("descripcion");
		
		int idUsuario = (int) sesion.getAttribute("idUsuario");
		Persona persona = new Persona(idUsuario);
		
		// Creamos el objeto de caso (modelo)
		Caso nuevoCaso = new Caso(nombre, descripcion, persona);

		// Insertamos el nuevo objeto en la base de datos
		int lastId =  new CasoDaoJDBC().insertar(nuevoCaso);
		System.out.println("[ServletControlador - insertarCaso] LastId: " + lastId);
		
		// Creamos el objeto de caso (modelo)
		Caso casoConsulta = new Caso(lastId);

		// Insertamos el nuevo objeto en la base de datos
		Caso casoEncontrado =  new CasoDaoJDBC().encontrar(casoConsulta);
		System.out.println("[ServletControlador - insertarCaso] Caso encontrado: " + casoEncontrado);
		request.setAttribute("caso", casoEncontrado);
		request.setAttribute("tienePermisos", "si");
		request.setAttribute("casoAgregadoOK", "casoAgregadoOK");
		
		// Redirigimos
		String jspConsultar = "/WEB-INF/paginas/caso/consultarCaso.jsp";
		request.getRequestDispatcher(jspConsultar).forward(request, response);
	
	}

	private void agregarResponsableForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Recuperar los valores del formulario agregarDispositivo
		int idCaso = Integer.parseInt(request.getParameter("idCaso"));
		int idPersona = Integer.parseInt(request.getParameter("idPersona"));

		System.out.println("[ServletControlador - agregarResponsableForm] INSERTAR RESPONSABLE AL CASO DATOS:");
		System.out.println("\tIDcaso: "+idCaso);
		System.out.println("\tIDpersona: "+idPersona);

		boolean updateOK = new PersonaDaoJDBC().agregarResponsable(idCaso, idPersona);
		System.out.println("[ServletControlador - agregarResponsableForm] UpdateOK: " + updateOK);
		
		request.setAttribute("tienePermisos", "si");
		
		request.setAttribute("responsableAgregadoOK", "responsableAgregadoOK");
		
		this.consultarCaso(request, response);

	}	
	private void insertarDispositivo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Recuperar los valores del formulario agregarDispositivo
		int idCaso = Integer.parseInt(request.getParameter("idCaso"));
		String nombre = (String) request.getParameter("nombre");
		String descripcion = (String) request.getParameter("descripcion");
		
		Part part = request.getPart("fotografia");
		String path = "";
		
		String fileName = part.getSubmittedFileName();
		if (!fileName.isEmpty()) {
			System.out.println("[ServletControlador - insertarDispositivo] FILENAME: "+fileName);
			path = getServletContext().getRealPath("/"+"fotografias"+"/"+File.separator+fileName);		
			InputStream is = part.getInputStream();		
			boolean succs = subirImagen(is, path);		
			if (succs) {
				System.out.println("[ServletControlador - insertarDispositivo] File uploaded to this directory: "+path);
			} else {
				System.out.println("[ServletControlador - insertarDispositivo] Error uploading file");
			}
		}
		
		if (!fileName.isEmpty()) {
			fileName = "\\fotografias\\"+fileName;
		}
		
		String hash = (String) request.getParameter("hash");
		String cifrado = (String) request.getParameter("cifrado");	
		String tipoDispositivo = (String) request.getParameter("tipoDispositivo");
		String marca = (String) request.getParameter("marca");
		String clonadoForense = (String) request.getParameter("clonadoForense");

		System.out.println("[ServletControlador - insertarDispositivo] INSERTAR DISPOSITIVO DATOS:");
		System.out.println("\tIDcaso: "+idCaso);
		System.out.println("\tNombre: "+nombre);
		System.out.println("\tDescripción: "+descripcion);
		System.out.println("\tFotografía: "+path);
		System.out.println("\tHash: "+hash);
		System.out.println("\t¿Se encuentra cifrado?: "+cifrado);
		System.out.println("\tTipo dispositivo: "+tipoDispositivo);
		System.out.println("\tMarca: "+marca);
		System.out.println("\t¿Se trata de un clonado forense?: "+clonadoForense);
		
		// Creamos el objeto de dispositivo (modelo)
		Dispositivo nuevoDispositivo = new Dispositivo(idCaso, nombre, descripcion, fileName, hash, cifrado, tipoDispositivo, marca, clonadoForense);
		
		// Insertamos el nuevo objeto en la base de datos
		int lastId = new DispositivoDaoJDBC().insertar(nuevoDispositivo);
		System.out.println("[ServletControlador - insertarDispositivo] LastId: " + lastId);
		
		// Creamos el objeto de dispositivo (modelo)
		Dispositivo dispositivoConsulta = new Dispositivo(lastId);
		
		// Insertamos el nuevo objeto en la base de datos
		Dispositivo dispositivoEncontrado =  new DispositivoDaoJDBC().encontrar(dispositivoConsulta);
		System.out.println("[ServletControlador - insertarDispositivo] Dispositivo encontrado: " + dispositivoEncontrado);
		
		request.setAttribute("tienePermisos", "si");
		
		request.setAttribute("dispositivoAgregadoOK", "dispositivoAgregadoOK");

		if (!dispositivoEncontrado.getFotografia().isEmpty() && dispositivoEncontrado.getFotografia().length() > 12) {
			
			String imagenDominio2 = dispositivoEncontrado.getFotografia().substring(13); // DiscoDuro_AlmacenamientoExterno.jpg
			
			String imagenDominio1 = "https://forenstic.com/Forenstic/fotografias/"+imagenDominio2; // https://forenstic.com/Forenstic/fotografias/DiscoDuro_AlmacenamientoExterno.jpg
			
			dispositivoEncontrado.setFotografia(imagenDominio1);
			
			request.setAttribute("dispositivo", dispositivoEncontrado);
			
			System.out.println("[ServletControlador - insertarDispositivo] Dispositivo encontrado cambiando ruta imagen: " + dispositivoEncontrado);
			
			System.out.println("FOTOGRAFRIA: "+dispositivoEncontrado.getFotografia());
			
			// Delay intencionado para esperar a que se acabe de subir la imagen al dominio para poder mostrarla en la pantalla de consulta
			try {
				TimeUnit.SECONDS.sleep(7);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			request.setAttribute("dispositivo", dispositivoEncontrado);
		}
		
		// Redirigimos
		String jspConsultar = "/WEB-INF/paginas/dispositivo/consultarDispositivo.jsp";
		request.getRequestDispatcher(jspConsultar).forward(request, response);

	}
	
	private void insertarEvidenciaConCasoSeleccionado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int idCaso = Integer.parseInt(request.getParameter("idCaso"));

		System.out.println("[ServletControlador - insertarEvidenciaConCasoSeleccionado] idCaso: "+idCaso);

		List<Dispositivo> listaMisDispositivos = new DispositivoDaoJDBC().listarDispositivosDeUnCaso(idCaso);
		request.setAttribute("listaMisDispositivos", listaMisDispositivos);
		request.setAttribute("idCaso", idCaso);
		//response.sendRedirect("/WEB-INF/paginas/evidenciaDigital/agregarDispositivoConCasoSeleccionado.jsp"); // la URL sí cambia, redirige

		String jspEditar = "/WEB-INF/paginas/evidenciaDigital/agregarDispositivoConCasoSeleccionado.jsp";
		request.getRequestDispatcher(jspEditar).forward(request, response);
	
	}
	
	private void insertarCadenaCustodiaConDispositivoSeleccionado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int idDispositivo = Integer.parseInt(request.getParameter("idDispositivo"));

		System.out.println("[ServletControlador - insertarCadenaCustodiaConDispositivoSeleccionado] idDispositivo: "+idDispositivo);
		request.setAttribute("idDispositivo", idDispositivo);
		//response.sendRedirect("/WEB-INF/paginas/evidenciaDigital/agregarDispositivoConCasoSeleccionado.jsp"); // la URL sí cambia, redirige

		String jspEditar = "/WEB-INF/paginas/cadenaCustodia/agregarCadenaCustodiaConDispositivoSeleccionado.jsp";
		request.getRequestDispatcher(jspEditar).forward(request, response);
	
	}
	
	
	private void insertarRegistroCadenaCustodia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int idCadenaCustodia = Integer.parseInt(request.getParameter("idCadenaCustodia"));
		String nombreDispositivo = request.getParameter("nombreDispositivo");

		System.out.println("[ServletControlador - insertarRegistroCadenaCustodia] idCadenaCustodia: "+idCadenaCustodia);
		request.setAttribute("idCadenaCustodia", idCadenaCustodia);
		//response.sendRedirect("/WEB-INF/paginas/evidenciaDigital/agregarDispositivoConCasoSeleccionado.jsp"); // la URL sí cambia, redirige
		request.setAttribute("nombreDispositivo", nombreDispositivo);
		
		String jspEditar = "/WEB-INF/paginas/cadenaCustodia/agregarRegistroCadenaCustodia.jsp";
		request.getRequestDispatcher(jspEditar).forward(request, response);
	}
	
	private void agregarResponsableAlCaso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int idCaso = Integer.parseInt(request.getParameter("idCaso"));
		String nombreCaso = request.getParameter("nombreCaso");

		List<Persona> listaPersonasQueNoTienenAsignadoEsteCaso = new PersonaDaoJDBC().listarPersonasParaAsignarUnCaso(idCaso);
		request.setAttribute("listaPersonas", listaPersonasQueNoTienenAsignadoEsteCaso);
		request.setAttribute("idCaso", idCaso);

		String jsp = "/WEB-INF/paginas/persona/agregarResponsableAlCaso.jsp";
		request.getRequestDispatcher(jsp).forward(request, response);
	}

	private void insertarEvidencia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Recuperar los valores del formulario agregarEvidencia
		int idDispositivo = Integer.parseInt(request.getParameter("idDispositivo"));
		int idCaso = Integer.parseInt(request.getParameter("idCaso"));
		
		String nombre = (String) request.getParameter("nombre");
		String descripcion = (String) request.getParameter("descripcion");
		String tipo = (String) request.getParameter("tipo");
		//String tamano = (String) request.getParameter("tamano");
		//Double tamanoDouble = (double) 0;

		System.out.println("[ServletControlador - insertarEvidencia] INSERTAR EVIDENCIA DATOS:");
		System.out.println("\tIDcaso: "+idCaso);
		System.out.println("\tIDdispositivo: "+idDispositivo);
		System.out.println("\tNombre: "+nombre);
		System.out.println("\tDescripción: "+descripcion);
		System.out.println("\tTipo: "+tipo);
		/*System.out.println("\tTamaño: "+tamano);
		
		if (!tamano.isEmpty() && Utils.isNumeric(tamano)) {
			tamanoDouble = Double.parseDouble(tamano);
		}*/
		
		// Creamos el objeto de evidencia (modelo)
		Evidencia nuevaEvidencia = new Evidencia(idCaso, idDispositivo, nombre, descripcion, tipo);
		
		// Insertamos el nuevo objeto en la base de datos
		int lastId = new EvidenciaDaoJDBC().insertar(nuevaEvidencia);
		System.out.println("[ServletControlador - insertarEvidencia] LastId: " + lastId);
		
		// Creamos el objeto de evidencia (modelo)
		Evidencia evidenciaConsulta = new Evidencia(lastId);
		
		// Insertamos el nuevo objeto en la base de datos
		Evidencia evidenciaEncontrada =  new EvidenciaDaoJDBC().encontrar(evidenciaConsulta);
		System.out.println("[ServletControlador - insertarEvidencia] Evidencia encontrada: " + evidenciaEncontrada);
		request.setAttribute("evidencia", evidenciaEncontrada);
		request.setAttribute("tienePermisos", "si");
		
		request.setAttribute("evidenciaAgregadaOK", "evidenciaAgregadaOK");

		// Redirigimos
		String jspConsultar = "/WEB-INF/paginas/evidenciaDigital/consultarEvidenciaDigital.jsp";
		request.getRequestDispatcher(jspConsultar).forward(request, response);
	
	}
	
	private void insertarRegistroCadenaCustodiaForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Recuperar los valores del formulario insertarRegistroCadenaCustodiaForm
		int idCadenaCustodia = Integer.parseInt(request.getParameter("idCadenaCustodia"));
		String fecha = (String) request.getParameter("fecha");
		String hora = (String) request.getParameter("hora");
		String personaEntrega = (String) request.getParameter("personaEntrega");
		String agenciaInstitucionEntrega = (String) request.getParameter("agenciaInstitucionEntrega");
		String personaRecibe = (String) request.getParameter("personaRecibe");
		String agenciaInstitucionRecibe = (String) request.getParameter("agenciaInstitucionRecibe");
		String actividadProposito = (String) request.getParameter("actividadProposito");
		String observaciones = (String) request.getParameter("observaciones");

		System.out.println("[ServletControlador - insertarRegistroCadenaCustodiaForm] INSERTAR REGISTRO CADENA CUSTODIA DATOS:");
		System.out.println("\tIDcadenaCustodia: "+idCadenaCustodia);
		System.out.println("\tFecha: "+fecha);
		System.out.println("\tHora: "+hora);
		System.out.println("\tPersona que entrega: "+personaEntrega);
		System.out.println("\tAgencia/institución que entrega: "+agenciaInstitucionEntrega);
		System.out.println("\tPersona que recibe: "+personaRecibe);
		System.out.println("\tAgencia/institución que recibe: "+agenciaInstitucionRecibe);
		System.out.println("\tActividad/propósito: "+actividadProposito);
		System.out.println("\tObservaciones: "+observaciones);

		// Creamos el objeto de registro (modelo)
		
		// Fecha: 2021-06-09
		// Hora: 09:12
		
		String fechaHoraStr = fecha+" "+hora+":00";
		System.out.println("fechaHoraStr: "+fechaHoraStr);
		
		Date fechaHora = new Date();
		try {
			fechaHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fechaHoraStr);
		} catch (ParseException e) {
			System.out.println(e);		
		}  
		System.out.println("FECHA HORA: "+fechaHora);

		RegistroCadenaCustodia nuevoRegistroCadena = new RegistroCadenaCustodia(idCadenaCustodia, fechaHora, personaEntrega, agenciaInstitucionEntrega, personaRecibe, agenciaInstitucionRecibe, actividadProposito, observaciones);
		
		// Insertamos el nuevo objeto en la base de datos
		int lastId = new RegistroCadenaDaoJDBC().insertar(nuevoRegistroCadena);
		System.out.println("[ServletControlador - insertarRegistroCadenaCustodiaForm] LastId: " + lastId);
		
		// Creamos el objeto de evidencia (modelo)
		RegistroCadenaCustodia registroCadenaConsulta = new RegistroCadenaCustodia(lastId);
		
		// Insertamos el nuevo objeto en la base de datos
		RegistroCadenaCustodia registroCadenaEncontrado =  new RegistroCadenaDaoJDBC().encontrar(registroCadenaConsulta);
		System.out.println("[ServletControlador - insertarRegistroCadenaCustodiaForm] Registro cadena encontrado: " + registroCadenaEncontrado);
		request.setAttribute("registroCadena", registroCadenaEncontrado);
		request.setAttribute("tienePermisos", "si");
		
		// Consultar cadena de custodia
				
		// Creamos el objeto de caso (modelo)
		CadenaCustodia cadenaCustodiaConsulta = new CadenaCustodia(registroCadenaEncontrado.getIdCadenaCustodia());
		CadenaCustodia cadenaCustodiaEncontrada =  new CadenaCustodiaDaoJDBC().encontrar(cadenaCustodiaConsulta);
		System.out.println("[ServletControlador - consultarCadenaCustodia] Cadena custodia encontrada: " + cadenaCustodiaEncontrada);
		request.setAttribute("cadenaCustodia", cadenaCustodiaEncontrada);
		request.setAttribute("nombreDispositivo", (String) request.getParameter("nombreDispositivo"));
		request.setAttribute("idCaso", cadenaCustodiaEncontrada.getIdCaso());		
		request.setAttribute("registroCadenaAgregadoOK", "registroCadenaAgregadoOK");

		// Redirigimos
		String jspConsultar = "/WEB-INF/paginas/cadenaCustodia/consultarCadenaCustodia.jsp";
		request.getRequestDispatcher(jspConsultar).forward(request, response);
	
	}
	
	private void insertarCadenaCustodia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idDispositivo = Integer.parseInt(request.getParameter("idDispositivo"));
		
		String localizacion = (String) request.getParameter("localizacion");
		String fechaStr = (String) request.getParameter("fecha");
		String horaStr = (String) request.getParameter("hora");
		String razon = (String) request.getParameter("razon");
		String agenciaInstitucion = (String) request.getParameter("agenciaInstitucion");
		String persona = (String) request.getParameter("persona");

		
		// Fecha: 2021-06-09
		// Hora: 09:12
		
		String fechaHoraStr = fechaStr+" "+horaStr+":00";
		System.out.println("fechaHoraStr: "+fechaHoraStr);
		
		Date fechaHora = new Date();
		try {
			fechaHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fechaHoraStr);
		} catch (ParseException e) {
			System.out.println(e);		
		}  
		System.out.println("FECHA HORA: "+fechaHora);
		System.out.println("[ServletControlador - insertarCadenaCustodia] INSERTAR CADENA CUSTODIA DATOS:");
		System.out.println("\tID dispositivo: "+idDispositivo);
		System.out.println("\tLocalización: "+localizacion);
		System.out.println("\tFecha y hora: "+fechaHora);
		System.out.println("\tRazón: "+razon);
		System.out.println("\tAgencia / Institución: "+agenciaInstitucion);
		System.out.println("\tPersona: "+persona);

		CadenaCustodia nuevaCadenaCustodia = new CadenaCustodia(idDispositivo, localizacion, fechaHora, razon, agenciaInstitucion, persona);
		
		// Insertamos el nuevo objeto en la base de datos
		int lastId = new CadenaCustodiaDaoJDBC().insertar(nuevaCadenaCustodia);
		System.out.println("[ServletControlador - insertarCadenaCustodia] LastId: " + lastId);
		
		// Creamos el objeto de cadena de custodia (modelo)
		CadenaCustodia cadenaCustodiaConsulta = new CadenaCustodia(lastId);
		
		CadenaCustodia cadenaCustodiaEncontrada =  new CadenaCustodiaDaoJDBC().encontrar(cadenaCustodiaConsulta);
		System.out.println("[ServletControlador - insertarCadenaCustodia] CadenaCustodia encontrada: " + cadenaCustodiaEncontrada);
		request.setAttribute("cadenaCustodia", cadenaCustodiaEncontrada);
		request.setAttribute("tienePermisos", "si");
		
		request.setAttribute("cadenaCustodiaAgregadaOK", "cadenaCustodiaAgregadaOK");

		// Redirigimos
		String jspConsultar = "/WEB-INF/paginas/cadenaCustodia/consultarCadenaCustodia.jsp";
		request.getRequestDispatcher(jspConsultar).forward(request, response);
	
	}
	
	public boolean subirImagen(InputStream is, String path) {	
		boolean test = false;		
		try {
			byte[] byt = new byte[is.available()];
			is.read(byt);
			FileOutputStream fops = new FileOutputStream(path);
			fops.write(byt);
			fops.flush();
			fops.close();
			test = true;
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return test;	
	}
	
	private void insertarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Recuperar los valores del formulario agregarCaso
		String nombre = (String) request.getParameter("nombre");
		String usuario = (String) request.getParameter("usuario");
		String password1 = (String) request.getParameter("contrasena1");
		String password2 = (String) request.getParameter("contrasena2");
		String correoElectronico = (String) request.getParameter("correoElectronico");
		String telefono = (String) request.getParameter("telefono");

		if (password1.equalsIgnoreCase(password2)) {
			
			String nuevoPasswd = Hash.sha1(password1);
			
			// Creamos el objeto de caso (modelo)
			Persona nuevaPersona = new Persona(nombre, usuario, nuevoPasswd, correoElectronico, telefono);

			// Insertamos el nuevo objeto en la base de datos
			int lastId =  new PersonaDaoJDBC().insertar(nuevaPersona);
			System.out.println("[ServletControlador - insertarUsuario] LastId: " + lastId);
			
			// Creamos el objeto de caso (modelo)
			Persona personaConsulta = new Persona(lastId);

			// Insertamos el nuevo objeto en la base de datos
			Persona personaEncontrada =  new PersonaDaoJDBC().encontrar(personaConsulta);
			System.out.println("[ServletControlador - insertarUsuario] Persona encontrada: " + personaEncontrada);
			request.setAttribute("persona", personaEncontrada);
			
			// Redirigimos
			//String jspConsultar = "/WEB-INF/paginas/persona/consultarPersona.jsp";
			String jspConsultar = "login.jsp";
			request.setAttribute("usuarioRegistradoOK", "usuarioRegistradoOK");
			request.getRequestDispatcher(jspConsultar).forward(request, response);
		} else {
			// TODO Las contraseñas no coinciden
		}
	}
	
	private void loginUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Recuperar los valores del formulario agregarCaso
		String usuario = (String) request.getParameter("usuario");
		String password = (String) request.getParameter("contrasena");
		System.out.println("[ServletControlador - loginUsuario] USUARIO: "+usuario);
		System.out.println("[ServletControlador - loginUsuario] PASSWORD: "+password);
		String nuevoPass = Hash.sha1(password);
		
		Persona personaLogin = new Persona(usuario, nuevoPass);

		if (new PersonaDaoJDBC().login(personaLogin)) { // Login OK
			
			System.out.println("[ServletControlador - loginUsuario] Persona login: "+personaLogin);
			
			personaLogin =  new PersonaDaoJDBC().encontrarPorUsuario(personaLogin);
			
			System.out.println("[ServletControlador - loginUsuario] PERSONA LOGIN: "+personaLogin);
			
			HttpSession sesion = request.getSession();
			sesion.setAttribute("usuario", personaLogin.getUsuario());
			sesion.setAttribute("idUsuario", personaLogin.getIdPersona());
			sesion.setAttribute("nombre", personaLogin.getNombre());
			//   HttpSession sesion = request.getSession();
			//   Object usuario = (String) session.getAttribute("usuario");

		} else { // Login NOK
			System.out.println("[ServletControlador - loginUsuario] NO SE HA PODIDO HACER LOGIN");
		}

		request.setAttribute("persona", personaLogin);
		
		// Redirigimos
		String jspConsultar = "/index.jsp";
		request.getRequestDispatcher(jspConsultar).forward(request, response);
	
	}
	
	private void modificarCaso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Recuperar los valores del formulario editarCaso
		int idCaso = Integer.parseInt(request.getParameter("idCaso"));
		String nombre = (String) request.getParameter("nombre");
		String descripcion = (String) request.getParameter("descripcion");
		
		// Creamos el objeto de caso (modelo)
		Caso casoEditar = new Caso(idCaso, nombre, descripcion, null, null); // TODO

		// Insertamos el nuevo objeto en la base de datos
		int registrosModificados =  new CasoDaoJDBC().actualizar(casoEditar);
		System.out.println("[ServletControlador - modificarCaso] Registros modificados: " + registrosModificados);
		
		this.consultarCaso(request, response);

		// Redirigimos hacia accion por default
		//String jspEditar = "/WEB-INF/paginas/caso/consultarCaso.jsp";
		//request.getRequestDispatcher(jspEditar).forward(request, response);
		
	}
	
	private void modificarDispositivo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Recuperar los valores del formulario editarDispositivo
		int idDispositivo = Integer.parseInt(request.getParameter("idDispositivo"));
		int idCaso = Integer.parseInt(request.getParameter("idCaso"));
		String nombre = (String) request.getParameter("nombre");
		String descripcion = (String) request.getParameter("descripcion");
		
		Part part = request.getPart("fotografia");
		String path = "";
		String fotografia = "";
		
		String fileName = part.getSubmittedFileName();
		if (!fileName.isEmpty()) {
			System.out.println("[ServletControlador - modificarDispositivo] FILENAME: "+fileName);		
			path = getServletContext().getRealPath("/"+"fotografias"+"/"+File.separator+fileName);		
			InputStream is = part.getInputStream();		
			boolean succs = subirImagen(is, path);		
			if (succs) {
				System.out.println("[ServletControlador - modificarDispositivo] File uploaded to this directory: "+path);
			} else {
				System.out.println("[ServletControlador - modificarDispositivo] Error uploading file");
			}
			fileName = "\\fotografias\\"+fileName;
		}

		String hash = (String) request.getParameter("hash");
		String cifrado = (String) request.getParameter("cifrado");
		String tipoDispositivo = (String) request.getParameter("tipoDispositivo");
		String marca = (String) request.getParameter("marca");
		String clonadoForense = (String) request.getParameter("clonadoForense");

		// Creamos el objeto de dispositivo (modelo)
		Dispositivo dispositivoEditar = new Dispositivo(idDispositivo, idCaso, nombre, descripcion, fileName, hash, cifrado, tipoDispositivo, marca, clonadoForense);

		System.out.println("[ServletControlador - modificarDispositivo] Dispositivo editado: "+dispositivoEditar.toString());
		
		// Insertamos el nuevo objeto en la base de datos
		int registrosModificados =  new DispositivoDaoJDBC().actualizar(dispositivoEditar);
		System.out.println("[ServletControlador - modificarDispositivo] Registros modificados: " + registrosModificados);
		
		request.setAttribute("dispositivoEditadoOK", "dispositivoEditadoOK");

		// Delay intencionado para esperar a que se acabe de subir la imagen al dominio para poder mostrarla en la pantalla de consulta
		try {
			TimeUnit.SECONDS.sleep(7);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		this.consultarDispositivo(request, response);
		
	}
	
	private void modificarEvidencia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Recuperar los valores del formulario editarEvidencia
		int idEvidencia = Integer.parseInt(request.getParameter("idEvidencia"));
		int idDispositivo = Integer.parseInt(request.getParameter("idDispositivo"));
		int idCaso = Integer.parseInt(request.getParameter("idCaso"));
		String nombre = (String) request.getParameter("nombre");
		String descripcion = (String) request.getParameter("descripcion");
		String tipo = (String) request.getParameter("tipo");
		/*String tamano = (String) request.getParameter("tamano");
		Double tamanoDouble = 0.0;
		if (!tamano.isEmpty()) {
			tamanoDouble = Double.parseDouble(tamano);
		}*/

		// Creamos el objeto de evidencia (modelo)
		Evidencia evidenciaEditar = new Evidencia(idEvidencia, idCaso, idDispositivo, nombre, descripcion, tipo);

		System.out.println("[ServletControlador - modificarEvidencia] Evidencia editada: "+evidenciaEditar.toString());
		
		// Insertamos el nuevo objeto en la base de datos
		int registrosModificados =  new EvidenciaDaoJDBC().actualizar(evidenciaEditar);
		System.out.println("[ServletControlador - modificarEvidencia] Registros modificados: " + registrosModificados);
		
		request.setAttribute("evidenciaEditadaOK", "evidenciaEditadaOK");
		
		this.consultarEvidenciaDigital(request, response);
		
	}
	
	private void modificarCadenaCustodia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Recuperar los valores del formulario editarCadenaCustodia
		int idCadenaCustodia = Integer.parseInt(request.getParameter("idCadenaCustodia"));
		String localizacion = (String) request.getParameter("localizacion");
		String fecha = (String) request.getParameter("fecha");
		String hora = (String) request.getParameter("hora");
		String razon = (String) request.getParameter("razon");
		String agenciaInstitucion = (String) request.getParameter("agenciaInstitucion");
		String persona = (String) request.getParameter("persona");
		

		// Fecha: 2021-06-09
		// Hora: 09:12
		
		String fechaHoraStr = fecha+" "+hora+":00";
		System.out.println("fechaHoraStr: "+fechaHoraStr);
		
		Date fechaHora = new Date();
		try {
			fechaHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fechaHoraStr);
		} catch (ParseException e) {
			System.out.println(e);		
		}  
		System.out.println("FECHA HORA: "+fechaHora);

		// Creamos el objeto de cadenaCustodia (modelo)
		CadenaCustodia cadenaCustodiaEditar = new CadenaCustodia(idCadenaCustodia, localizacion, fechaHora, razon, agenciaInstitucion, persona);

		System.out.println("[ServletControlador - modificarCadenaCustodia] Cadena custodia editada: "+cadenaCustodiaEditar.toString());
		
		// Insertamos el nuevo objeto en la base de datos
		int registrosModificados =  new CadenaCustodiaDaoJDBC().actualizar(cadenaCustodiaEditar);
		System.out.println("[ServletControlador - modificarCadenaCustodia] Registros modificados: " + registrosModificados);
		
		request.setAttribute("cadenaEditadaOK", "cadenaEditadaOK");
		
		this.consultarCadenaCustodia(request, response);
		
	}
	
	private void modificarRegistroCadena(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Recuperar los valores del formulario modificarRegistroCadena
		int idRegistroCadena = Integer.parseInt(request.getParameter("idRegistroCadena"));
		int idCadenaCustodia = Integer.parseInt(request.getParameter("idCadenaCustodia"));
		String fecha = (String) request.getParameter("fecha");
		String hora = (String) request.getParameter("hora");
		String personaEntrega = (String) request.getParameter("personaEntrega");
		String agenciaInstitucionEntrega = (String) request.getParameter("agenciaInstitucionEntrega");
		String personaRecibe = (String) request.getParameter("personaRecibe");
		String agenciaInstitucionRecibe = (String) request.getParameter("agenciaInstitucionRecibe");
		String actividadProposito = (String) request.getParameter("actividadProposito");
		String observaciones = (String) request.getParameter("observaciones");

		System.out.println("[ServletControlador - modificarRegistroCadena] EDITAR REGISTRO CADENA CUSTODIA DATOS:");
		System.out.println("\tIDcadenaCustodia: "+idCadenaCustodia);
		System.out.println("\tFecha: "+fecha);
		System.out.println("\tHora: "+hora);
		System.out.println("\tPersona que entrega: "+personaEntrega);
		System.out.println("\tAgencia/institución que entrega: "+agenciaInstitucionEntrega);
		System.out.println("\tPersona que recibe: "+personaRecibe);
		System.out.println("\tAgencia/institución que recibe: "+agenciaInstitucionRecibe);
		System.out.println("\tActividad/propósito: "+actividadProposito);
		System.out.println("\tObservaciones: "+observaciones);

		// Creamos el objeto de registro (modelo)
		
		// Fecha: 2021-06-09
		// Hora: 09:12
		
		String fechaHoraStr = fecha+" "+hora+":00";
		System.out.println("fechaHoraStr: "+fechaHoraStr);
		
		Date fechaHora = new Date();
		try {
			fechaHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fechaHoraStr);
		} catch (ParseException e) {
			System.out.println(e);		
		}  
		System.out.println("FECHA HORA: "+fechaHora);

		// Creamos el objeto de registro (modelo)
		RegistroCadenaCustodia registroEditar = new RegistroCadenaCustodia(idRegistroCadena, idCadenaCustodia, fechaHora, personaEntrega, agenciaInstitucionEntrega, personaRecibe, agenciaInstitucionRecibe, actividadProposito, observaciones);

		System.out.println("[ServletControlador - modificarRegistroCadena] Registro editado: "+registroEditar.toString());
		
		// Insertamos el nuevo objeto en la base de datos
		int registrosModificados =  new RegistroCadenaDaoJDBC().actualizar(registroEditar);
		System.out.println("[ServletControlador - modificarRegistroCadena] Registros modificados: " + registrosModificados);
		
		request.setAttribute("registroEditadoOK", "registroEditadoOK");
		
		this.consultarCadenaCustodia(request, response);
		
	}
	
	private void modificarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Recuperar los valores del formulario editarUsuario
		int idUsuario = Integer.parseInt(request.getParameter("idPersona"));
		String nombreUsuario = (String) request.getParameter("usuario");	
		String nombreReal = (String) request.getParameter("nombre");
		String correo = (String) request.getParameter("correo");
		String telefono = (String) request.getParameter("telefono");	
		String contrasena1 = (String) request.getParameter("contrasena1");
		String contrasena2 = (String) request.getParameter("contrasena2");
		Persona persona = new Persona();
		
		// Creamos el objeto de persona (modelo)
		if (contrasena1.isEmpty()) {
			// No modificar contraseña
			persona = new Persona(idUsuario, nombreReal, nombreUsuario, contrasena1, correo, telefono);
		} else {
			// Modificar también contraseña				
			if (contrasena1.equalsIgnoreCase(contrasena2)) {
				persona = new Persona(idUsuario, nombreReal, nombreUsuario, correo, telefono);
			} else {
				// TODO las contraseñas no coinciden, mostrar mensaje de error
			}	
		}

		System.out.println("[ServletControlador - modificarUsuario] Persona a editar: "+persona.toString());
		
		// Insertamos el nuevo objeto en la base de datos
		int registrosModificados =  new PersonaDaoJDBC().actualizar(persona);
		System.out.println("[ServletControlador - modificarUsuario] Registros modificados: " + registrosModificados);
		
		request.setAttribute("personaEditadaOK", "personaEditadaOK");
		
		this.consultarUsuario(request, response);
		
	}
	
	private void editarCaso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Recuperar el idCaso
		int idCaso = Integer.parseInt(request.getParameter("idCaso"));

		// Recuperar el objeto caso de la base de datos con ese idCaso	
		Caso caso = new CasoDaoJDBC().encontrar(new Caso(idCaso));
		
		request.setAttribute("caso", caso);
		
		String jspEditar = "/WEB-INF/paginas/caso/editarCaso.jsp";
		request.getRequestDispatcher(jspEditar).forward(request, response);
	
	}
	
	private void editarDispositivo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Recuperar el idDispositivo
		int idDispositivo = Integer.parseInt(request.getParameter("idDispositivo"));

		// Recuperar el objeto dispositivo de la base de datos con ese idDispositivo	
		Dispositivo dispositivo = new DispositivoDaoJDBC().encontrar(new Dispositivo(idDispositivo));
		
		request.setAttribute("dispositivo", dispositivo);
		request.setAttribute("estaCifradoAttr", dispositivo.getEstaCifrado());
		request.setAttribute("tipoAttr", dispositivo.getTipo());
		request.setAttribute("esClonadoForenseAttr", dispositivo.getEsClonadoForense());
		request.setAttribute("fotografiaAttr", dispositivo.getFotografia());
		
		System.out.println("[ServletControlador - editarDispositivo] ¿Cifrado?: "+dispositivo.getEstaCifrado());
		System.out.println("[ServletControlador - editarDispositivo] Tipo: "+dispositivo.getTipo());
		System.out.println("[ServletControlador - editarDispositivo] ¿Es clonado forense?: "+dispositivo.getEsClonadoForense());

		String jspEditar = "/WEB-INF/paginas/dispositivo/editarDispositivo.jsp";
		request.getRequestDispatcher(jspEditar).forward(request, response);
	
	}
	
	private void editarEvidencia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Recuperar el idEvidencia
		int idEvidencia = Integer.parseInt(request.getParameter("idEvidencia"));
		System.out.println("[ServletControlador - editarEvidencia] ID evidencia: "+idEvidencia);
		// Recuperar el objeto evidencia de la base de datos con ese idEvidencia
		Evidencia evidencia = new EvidenciaDaoJDBC().encontrar(new Evidencia(idEvidencia));
		System.out.println("[ServletControlador - editarEvidencia]  Evidencia encontrada: "+evidencia);
		
		request.setAttribute("tipoAttr", evidencia.getTipo());
		request.setAttribute("evidencia", evidencia);
		
		String jspEditar = "/WEB-INF/paginas/evidenciaDigital/editarEvidencia.jsp";
		request.getRequestDispatcher(jspEditar).forward(request, response);
	
	}
	
	private void editarCadenaCustodia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		// Recuperar el idCadenaCustodia
		int idCadenaCustodia = Integer.parseInt(request.getParameter("idCadenaCustodia"));
		System.out.println("[ServletControlador - editarCadenaCustodia] ID CADENA CUSTODIA: "+idCadenaCustodia);
		// Recuperar el objeto cadena de custodia de la base de datos con ese idCadenaCustodia
		CadenaCustodia cadenaCustodia = new CadenaCustodiaDaoJDBC().encontrar(new CadenaCustodia(idCadenaCustodia));
		System.out.println("[ServletControlador - editarCadenaCustodia]  CADENA CUSTODIA ENCONTRADA: "+cadenaCustodia);
		
		request.setAttribute("cadenaCustodia", cadenaCustodia);
		
		String patternFecha = "yyyy-MM-dd";
		DateFormat dfFecha = new SimpleDateFormat(patternFecha);
		String fechaStr = dfFecha.format(cadenaCustodia.getFechaHoraOrigen());

		// Print the result!
		System.out.println("[ServletControlador - editarCadenaCustodia] Fecha: " + fechaStr);
		request.setAttribute("fechaStr", fechaStr);
		
		String patternHora = "HH:mm";
		DateFormat dfHora = new SimpleDateFormat(patternHora);
		String horaStr = dfHora.format(cadenaCustodia.getFechaHoraOrigen());
		System.out.println("[ServletControlador - editarCadenaCustodia] Hora: " + horaStr);
		request.setAttribute("horaStr", horaStr);
		
		String jspEditar = "/WEB-INF/paginas/cadenaCustodia/editarCadenaCustodia.jsp";
		request.getRequestDispatcher(jspEditar).forward(request, response);
	
	}
	
	private void editarRegistroCadena(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Recuperar el idRegistroCadena
		int idRegistroCadena = Integer.parseInt(request.getParameter("idRegistroCadena"));
		System.out.println("[ServletControlador - editarRegistroCadena] ID REGISTRO CADENA: "+idRegistroCadena);
		// Recuperar el objeto registro cadena de la base de datos con ese idRegistroCadena
		RegistroCadenaCustodia registroCadena = new RegistroCadenaDaoJDBC().encontrar(new RegistroCadenaCustodia(idRegistroCadena));
		System.out.println("[ServletControlador - editarRegistroCadena]  REGISTRO CADENA ENCONTRADO: "+registroCadena);
		
		request.setAttribute("registroCadena", registroCadena);

		String patternFecha = "yyyy-MM-dd";
		DateFormat dfFecha = new SimpleDateFormat(patternFecha);
		String fechaStr = dfFecha.format(registroCadena.getFechaHora());

		// Print the result!
		System.out.println("[ServletControlador - editarRegistroCadena] Fecha: " + fechaStr);
		request.setAttribute("fechaStr", fechaStr);
		
		String patternHora = "HH:mm";
		DateFormat dfHora = new SimpleDateFormat(patternHora);
		String horaStr = dfHora.format(registroCadena.getFechaHora());
		System.out.println("[ServletControlador - editarRegistroCadena] Hora: " + horaStr);
		request.setAttribute("horaStr", horaStr);
		
		String jspEditar = "/WEB-INF/paginas/cadenaCustodia/editarRegistroCadena.jsp";
		request.getRequestDispatcher(jspEditar).forward(request, response);
	
	}
	
	private void editarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Recuperar el idUsuario
		int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
		System.out.println("[ServletControlador - editarUsuario] ID USUARIO: "+idUsuario);
		// Recuperar el objeto evidencia de la base de datos con ese idEvidencia
		Persona persona = new PersonaDaoJDBC().encontrar(new Persona(idUsuario));
		System.out.println("[ServletControlador - editarUsuario]  PERSONA ENCONTRADA: "+persona);
		
		//request.setAttribute("tipoAttr", persona.getTipo());
		request.setAttribute("usuario", persona);
		
		String jspEditar = "/WEB-INF/paginas/persona/editarPersona.jsp";
		request.getRequestDispatcher(jspEditar).forward(request, response);
	
	}
	
	/*private void eliminarCaso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Recuperar el idCaso
		int idCaso = Integer.parseInt(request.getParameter("idCaso"));
		Caso casoEliminar = new Caso(idCaso);

		// Recuperar el objeto caso de la base de datos con ese idCaso	
		int registrosEliminados = new CasoDaoJDBC().eliminar(casoEliminar);
		System.out.println("Registros eliminados: " + registrosEliminados);
		
		// Redigirimos hacia acción por default
		this.accionDefault(request, response);
	
	}*/
	
	private void eliminarDispositivo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Recuperar el idDispositivo
		int idDispositivo = Integer.parseInt(request.getParameter("idDispositivo"));
		Dispositivo dispositivoEliminar = new Dispositivo(idDispositivo);

		// Recuperar el objeto caso de la base de datos con ese idCaso	
		int registrosEliminados = new DispositivoDaoJDBC().eliminar(dispositivoEliminar);
		System.out.println("[ServletControlador - eliminarDispositivo] Registros eliminados: " + registrosEliminados);
		
		request.setAttribute("dispositivoEliminadoOK", "dispositivoEliminadoOK");

		// Redigirimos hacia acción por default
		this.misDispositivos(request, response);
	}
	
	private void eliminarDispositivoDesdeConsultarCasos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Recuperar el idDispositivo
		int idDispositivo = Integer.parseInt(request.getParameter("idDispositivo"));
		int idCaso = Integer.parseInt(request.getParameter("idCaso"));
		Dispositivo dispositivoEliminar = new Dispositivo(idDispositivo, idCaso);

		// Recuperar el objeto caso de la base de datos con ese idCaso	
		int registrosEliminados = new DispositivoDaoJDBC().eliminar(dispositivoEliminar);
		System.out.println("[ServletControlador - eliminarDispositivoDesdeConsultarCasos] Registros eliminados: " + registrosEliminados);
		
		request.setAttribute("dispositivoEliminadoOK", "dispositivoEliminadoOK");

		// Redigirimos 
		this.consultarCaso(request, response);
	}
	
	private void eliminarRegistroDesdeConsultarCadena(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Recuperar el idRegistro
		int idRegistro = Integer.parseInt(request.getParameter("idRegistro"));
		int idCadena = Integer.parseInt(request.getParameter("idCadena"));
		RegistroCadenaCustodia registroEliminar = new RegistroCadenaCustodia(idRegistro, idCadena);

		// Recuperar el objeto caso de la base de datos con ese idRegistro	
		int registrosEliminados = new RegistroCadenaDaoJDBC().eliminar(registroEliminar);
		System.out.println("[ServletControlador - eliminarRegistroDesdeConsultarCadena] Registros eliminados: " + registrosEliminados);
		
		request.setAttribute("registroEliminadoOK", "registroEliminadoOK");

		// Redigirimos 
		this.consultarCadenaCustodia(request, response);
	}
	

	private void eliminarDispositivoDesdeMisDispositivos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Recuperar el idDispositivo
		int idDispositivo = Integer.parseInt(request.getParameter("idDispositivo"));
		Dispositivo dispositivoEliminar = new Dispositivo(idDispositivo);

		// Recuperar el objeto caso de la base de datos con ese idCaso	
		int registrosEliminados = new DispositivoDaoJDBC().eliminar(dispositivoEliminar);
		System.out.println("[ServletControlador - eliminarDispositivoDesdeMisDispositivos] Registros eliminados: " + registrosEliminados);
		
		request.setAttribute("dispositivoEliminadoOK", "dispositivoEliminadoOK");

		// Redigirimos 
		this.misDispositivos(request, response);
	}
	
	private void eliminarEvidenciaDesdeConsultarCasos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Recuperar el idEvidencia
		int idEvidencia = Integer.parseInt(request.getParameter("idEvidencia"));
		Evidencia evidenciaEliminar = new Evidencia(idEvidencia);

		// Recuperar el objeto evidencia de la base de datos con ese idEvidencia
		int registrosEliminados = new EvidenciaDaoJDBC().eliminar(evidenciaEliminar);
		System.out.println("[ServletControlador - eliminarEvidenciaDesdeConsultarCasos] Registros eliminados: " + registrosEliminados);
		
		request.setAttribute("evidenciaEliminadaOK", "evidenciaEliminadaOK");

		// Redigirimos 
		this.consultarCaso(request, response);
	}
	
	private void eliminarRegistroCadenaDesdeConsultarCadena(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idRegistroCadena = Integer.parseInt(request.getParameter("idRegistroCadena"));
		RegistroCadenaCustodia registroEliminar = new RegistroCadenaCustodia(idRegistroCadena);

		// Recuperar el objeto registro de la base de datos con ese idRegistroCadena
		int registrosEliminados = new RegistroCadenaDaoJDBC().eliminar(registroEliminar);
		System.out.println("[ServletControlador - eliminarRegistroCadenaDesdeConsultarCadena] Registros eliminados: " + registrosEliminados);
		
		request.setAttribute("registroCadenaEliminadaOK", "registroCadenaEliminadaOK");

		// Redigirimos 
		this.consultarCadenaCustodia(request, response);
	}
	
	private void eliminarEvidenciaDesdeConsultarDispositivos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Recuperar el idEvidencia
		int idEvidencia = Integer.parseInt(request.getParameter("idEvidencia"));
		Evidencia evidenciaEliminar = new Evidencia(idEvidencia);

		// Recuperar el objeto evidencia de la base de datos con ese idEvidencia
		int registrosEliminados = new EvidenciaDaoJDBC().eliminar(evidenciaEliminar);
		System.out.println("[ServletControlador - eliminarEvidenciaDesdeConsultarDispositivos] Registros eliminados: " + registrosEliminados);
		
		request.setAttribute("evidenciaEliminadaOK", "evidenciaEliminadaOK");

		// Redigirimos 
		this.consultarDispositivo(request, response);
	}
	
	private void eliminarEvidenciaDesdeMisEvidencias(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Recuperar el idEvidencia
		int idEvidencia = Integer.parseInt(request.getParameter("idEvidencia"));
		Evidencia evidenciaEliminar = new Evidencia(idEvidencia);

		// Recuperar el objeto evidencia de la base de datos con ese idEvidencia
		int registrosEliminados = new EvidenciaDaoJDBC().eliminar(evidenciaEliminar);
		System.out.println("[ServletControlador - eliminarEvidenciaDesdeMisEvidencias] Registros eliminados: " + registrosEliminados);
		
		request.setAttribute("evidenciaEliminadaOK", "evidenciaEliminadaOK");

		// Redigirimos 
		this.misEvidencias(request, response);
	}
	
	private void eliminarCasoDesdeMisCasos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Recuperar el idCaso
		int idCaso = Integer.parseInt(request.getParameter("idCaso"));
		Caso casoEliminar = new Caso(idCaso);

		// Recuperar el objeto caso de la base de datos con ese idCaso	
		int registrosEliminados = new CasoDaoJDBC().eliminar(casoEliminar);
		System.out.println("[ServletControlador - eliminarCasoDesdeMisCasos] Registros eliminados: " + registrosEliminados);

		request.setAttribute("casoEliminadoOK", "casoEliminadoOK");
		
		this.misCasos(request, response);
	
	}
	
	private void consultarCaso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Recuperar los valores del formulario editarCaso
		int idCaso = Integer.parseInt(request.getParameter("idCaso"));
		
		// Creamos el objeto de caso (modelo)
		Caso casoConsulta = new Caso(idCaso);

		// Insertamos el nuevo objeto en la base de datos
		Caso casoEncontrado =  new CasoDaoJDBC().encontrar(casoConsulta);
		System.out.println("[ServletControlador - consultarCaso] Caso encontrado: " + casoEncontrado);
		request.setAttribute("caso", casoEncontrado);
		
		// ¿El usuario tiene permisos sobre este caso?
		HttpSession sesion = request.getSession();
		int idUsuario = (int) sesion.getAttribute("idUsuario");
		boolean tienePermisos = new CasoDaoJDBC().permisosUsuario(casoEncontrado.getIdCaso(), idUsuario);
		
		if (tienePermisos) {
			request.setAttribute("tienePermisos", "si");
		} else {
			request.setAttribute("tienePermisos", "no");
		}
		
		// Redirigimos
		String jspConsultar = "/WEB-INF/paginas/caso/consultarCaso.jsp";
		request.getRequestDispatcher(jspConsultar).forward(request, response);
		
	}
	
	private void consultarDispositivo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idDispositivo = Integer.parseInt(request.getParameter("idDispositivo"));
		
		Dispositivo dispositivoConsulta = new Dispositivo(idDispositivo);

		// Insertamos el nuevo objeto en la base de datos
		Dispositivo dispositivoEncontrado =  new DispositivoDaoJDBC().encontrar(dispositivoConsulta);
		System.out.println("[ServletControlador - consultarDispositivo] Dispositivo encontrado: " + dispositivoEncontrado); // fotografia=\fotografias\DiscoDuro_AlmacenamientoExterno.jpg
		//request.setAttribute("dispositivo", dispositivoEncontrado);
		
		// ¿El usuario tiene permisos sobre este dispositivo?
		HttpSession sesion = request.getSession();
		int idUsuario = (int) sesion.getAttribute("idUsuario");
		boolean tienePermisos = new CasoDaoJDBC().permisosUsuario(dispositivoEncontrado.getIdCaso(), idUsuario);
		
		if (tienePermisos) {
			request.setAttribute("tienePermisos", "si");
		} else {
			request.setAttribute("tienePermisos", "no");
		}
		
		if (!dispositivoEncontrado.getFotografia().isEmpty() && dispositivoEncontrado.getFotografia().length() > 12) {
			
			String imagenDominio2 = dispositivoEncontrado.getFotografia().substring(13); // DiscoDuro_AlmacenamientoExterno.jpg
			
			String imagenDominio1 = "https://forenstic.com/Forenstic/fotografias/"+imagenDominio2; // https://forenstic.com/Forenstic/fotografias/DiscoDuro_AlmacenamientoExterno.jpg
			
			dispositivoEncontrado.setFotografia(imagenDominio1);
			
			System.out.println("[ServletControlador - consultarDispositivo] Dispositivo encontrado cambiando ruta imagen: " + dispositivoEncontrado);
			request.setAttribute("dispositivo", dispositivoEncontrado);
			
			System.out.println("IMAGEN: "+dispositivoEncontrado.getFotografia());
		
		} else {
			request.setAttribute("dispositivo", dispositivoEncontrado);
		}
		
		// Redirigimos
		String jspConsultar = "/WEB-INF/paginas/dispositivo/consultarDispositivo.jsp";
		request.getRequestDispatcher(jspConsultar).forward(request, response);
		
	}
	
	private void consultarEvidenciaDigital(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idEvidencia = Integer.parseInt(request.getParameter("idEvidencia"));
		
		Evidencia evidenciaConsulta = new Evidencia(idEvidencia);

		// Insertamos el nuevo objeto en la base de datos
		Evidencia evidenciaEncontrada =  new EvidenciaDaoJDBC().encontrar(evidenciaConsulta);
		System.out.println("[ServletControlador - consultarEvidenciaDigital] Evidencia encontrada: " + evidenciaEncontrada);
		request.setAttribute("evidencia", evidenciaEncontrada);
		
		// ¿El usuario tiene permisos sobre este dispositivo?
		HttpSession sesion = request.getSession();
		int idUsuario = (int) sesion.getAttribute("idUsuario");
		boolean tienePermisos = new CasoDaoJDBC().permisosUsuario(evidenciaEncontrada.getIdCaso(), idUsuario);
		
		if (tienePermisos) {
			request.setAttribute("tienePermisos", "si");
		} else {
			request.setAttribute("tienePermisos", "no");
		}
		
		// Redirigimos
		String jspConsultar = "/WEB-INF/paginas/evidenciaDigital/consultarEvidenciaDigital.jsp";
		request.getRequestDispatcher(jspConsultar).forward(request, response);
		
	}
	
	private void consultarCadenaCustodia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Recuperar los valores del formulario editarCaso
		int idCadenaCustodia = Integer.parseInt(request.getParameter("idCadenaCustodia"));
				
		// Creamos el objeto de caso (modelo)
		CadenaCustodia cadenaCustodiaConsulta = new CadenaCustodia(idCadenaCustodia);

		// Insertamos el nuevo objeto en la base de datos
		CadenaCustodia cadenaCustodiaEncontrada =  new CadenaCustodiaDaoJDBC().encontrar(cadenaCustodiaConsulta);
		System.out.println("[ServletControlador - consultarCadenaCustodia] Cadena custodia encontrada: " + cadenaCustodiaEncontrada);
		request.setAttribute("cadenaCustodia", cadenaCustodiaEncontrada);
		request.setAttribute("nombreDispositivo", (String) request.getParameter("nombreDispositivo"));
		request.setAttribute("idCaso", cadenaCustodiaEncontrada.getIdCaso());

		// ¿El usuario tiene permisos sobre esta cadena de custodia?
		HttpSession sesion = request.getSession();
		int idUsuario = (int) sesion.getAttribute("idUsuario");
		System.out.println("[ServletControlador - consultarCadenaCustodia] idUsuario: "+idUsuario);
		boolean tienePermisos = new CadenaCustodiaDaoJDBC().permisosUsuario(cadenaCustodiaConsulta.getIdCadenaCustodia(), cadenaCustodiaConsulta.getIdDispositivo(), idUsuario);
		
		if (tienePermisos) {
			request.setAttribute("tienePermisos", "si");
		} else {
			request.setAttribute("tienePermisos", "no");
		}
		
		// Redirigimos
		String jspConsultar = "/WEB-INF/paginas/cadenaCustodia/consultarCadenaCustodia.jsp";
		request.getRequestDispatcher(jspConsultar).forward(request, response);
		
	}
	
	private void consultarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Recuperar los valores del formulario editarCaso
		int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
				
		// Creamos el objeto de persona (modelo)
		Persona usuarioConsulta = new Persona(idUsuario);

		// Insertamos el nuevo objeto en la base de datos
		Persona personaEncontrada =  new PersonaDaoJDBC().encontrar(usuarioConsulta);
		System.out.println("[ServletControlador - consultarUsuario] Usuario encontrado: " + personaEncontrada);
		request.setAttribute("usuario", personaEncontrada);
		//request.setAttribute("nombreDispositivo", (String) request.getParameter("nombreDispositivo"));
		//request.setAttribute("idCaso", Integer.parseInt(request.getParameter("idCaso")));
		
		// Redirigimos
		String jspConsultar = "/WEB-INF/paginas/persona/consultarPersona.jsp";
		request.getRequestDispatcher(jspConsultar).forward(request, response);
		
	}
	
	private void buscarCaso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// ********** idCaso **********
		
		String idCasoStr = (String) request.getParameter("idCaso"); // TODO antes de la conversión, controlar si es vacío - COMPROBAR SI SE QUEJA
		int idCaso = 0;
		if (Utils.isNumeric(idCasoStr)) {
			idCaso = Integer.parseInt(idCasoStr);
		}
		
		// ********** nombreCaso **********
		
		String nombreCaso = (String) request.getParameter("nombreCaso");
		
		// ********** idEvidencia **********

		/*String idEvidenciaStr = (String) request.getParameter("idEvidencia");
		List<Integer> listaIdEvidenciasInt = new ArrayList<Integer>();
		
		if (!idEvidenciaStr.isEmpty()) {
			
			// Eliminamos los espacios en blanco del string
			idEvidenciaStr = idEvidenciaStr.replace(" ", "");
			
			// Separamos los ids en una lista de strings
			String listaIdEvidenciasStr[] = idEvidenciaStr.split(",");
			List<String> listaIdEvidenciasStrList = new ArrayList<String>();
			listaIdEvidenciasStrList = Arrays.asList(listaIdEvidenciasStr);

			// Comprobamos que los strings son todos numéricos y los pasamos a number en una lista de enteros
			int idEvidenciaInt;
			for (String idEvidenciaString: listaIdEvidenciasStrList) {
				idEvidenciaInt = 0;
				if (Utils.isNumeric(idEvidenciaString)) {
					idEvidenciaInt = Integer.parseInt(idEvidenciaString);
					listaIdEvidenciasInt.add(idEvidenciaInt);
				}
			}
		}*/

		// ********** idDispositivo **********
		
		/*String idDispositivoStr = (String) request.getParameter("idDispositivo");
		List<Integer> listaIdDispositivosInt = new ArrayList<Integer>();
		
		if (!idDispositivoStr.isEmpty()) {	
			
			// Eliminamos los espacios en blanco del string
			idDispositivoStr = idDispositivoStr.replace(" ", "");
			
			// Separamos los ids en una lista de strings
			String listaIdDispositivosStr[] = idDispositivoStr.split(",");
			List<String> listaIdDispositivosStrList = new ArrayList<String>();
			listaIdDispositivosStrList = Arrays.asList(listaIdDispositivosStr);

			// Comprobamos que los strings son todos numéricos y los pasamos a number en una lista de enteros
			int idDispositivoInt;
			for (String idDispositivoString: listaIdDispositivosStrList) {
				idDispositivoInt = 0;
				if (Utils.isNumeric(idDispositivoString)) {
					idDispositivoInt = Integer.parseInt(idDispositivoString);
					listaIdDispositivosInt.add(idDispositivoInt);
				}
			}
		}*/
		
		// ********** idPersona **********
		
		/*String idPersonaStr = (String) request.getParameter("idPersona");
		List<Integer> listaIdPersonasInt = new ArrayList<Integer>();
		
		if (!idPersonaStr.isEmpty()) {	
			
			// Eliminamos los espacios en blanco del string
			idPersonaStr = idPersonaStr.replace(" ", "");
			
			// Separamos los ids en una lista de strings
			String listaIdPersonasStr[] = idPersonaStr.split(",");
			List<String> listaIdPersonasStrList = new ArrayList<String>();
			listaIdPersonasStrList = Arrays.asList(listaIdPersonasStr);

			// Comprobamos que los strings son todos numéricos y los pasamos a number en una lista de enteros
			int idPersonaInt;
			for (String idPersonaString: listaIdPersonasStrList) {
				idPersonaInt = 0;
				if (Utils.isNumeric(idPersonaString)) {
					idPersonaInt = Integer.parseInt(idPersonaString);
					listaIdPersonasInt.add(idPersonaInt);
				}
			}
		}*/
				
		// ********** inicioFechaCreacion ********** 		
		String inicioFechaCreacion = (String) request.getParameter("inicioFechaCreacion"); // arriba 2021-03-01

		// ********** finFechaCreacion **********
		
		String finFechaCreacion = (String) request.getParameter("finFechaCreacion");
			
		// ********** inicioFechaUltimaModif **********	 
		String inicioFechaUltimaModif = (String) request.getParameter("inicioFechaUltimaModif");

		// ********** finFechaUltimaModif **********
		String finFechaUltimaModif = (String) request.getParameter("finFechaUltimaModif");

		System.out.println("[ServletControlador - buscarCaso] CASO A BUSCAR --> idCaso: "+idCaso+", nombreCaso: "+nombreCaso+", inicioFechaCreacion: "+inicioFechaCreacion+", finFechaCreacion: "+finFechaCreacion+", inicioFechaUltimaModif: "+inicioFechaUltimaModif+", finFechaUltimaModif: "+finFechaUltimaModif);

		List<Caso> casosBuscados = new ArrayList<Caso>();
		casosBuscados =  new CasoDaoJDBC().buscarCaso(idCaso, nombreCaso, inicioFechaCreacion, finFechaCreacion, inicioFechaUltimaModif, finFechaUltimaModif);

		HttpSession sesion = request.getSession();		
		sesion.setAttribute("listaCasosEncontrados", casosBuscados);
		sesion.setAttribute("totalCasosEncontrados", casosBuscados.size());
		request.getRequestDispatcher("buscarCaso.jsp").forward(request, response); // la URL no cambia
		//response.sendRedirect("buscarCaso.jsp"); // la URL sí cambia, redirige
		
	}
	
	private void buscarDispositivo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// ********** idDispositivo **********	
		String idDispositivoStr = (String) request.getParameter("idDispositivo"); // TODO antes de la conversión, controlar si es vacío - COMPROBAR SI SE QUEJA
		int idDispositivo = 0;
		if (Utils.isNumeric(idDispositivoStr)) {
			idDispositivo = Integer.parseInt(idDispositivoStr);
		}
		
		// ********** nombreDispositivo **********
		String nombreDispositivo = (String) request.getParameter("nombreDispositivo");
				
		// ********** inicioFechaCreacion ********** 
		String inicioFechaCreacion = (String) request.getParameter("inicioFechaCreacion"); // arriba 2021-03-01

		// ********** finFechaCreacion **********
		String finFechaCreacion = (String) request.getParameter("finFechaCreacion");
		
		// ********** inicioFechaUltimaModif **********
		String inicioFechaUltimaModif = (String) request.getParameter("inicioFechaUltimaModif");

		// ********** finFechaUltimaModif **********
		String finFechaUltimaModif = (String) request.getParameter("finFechaUltimaModif");
		
		System.out.println("[ServletControlador - buscarDispositivo] DISPOSITIVO A BUSCAR --> idDispositivo: "+idDispositivo+", nombreDispositivo: "+nombreDispositivo+", inicioFechaCreacion: "+inicioFechaCreacion+", finFechaCreacion: "+finFechaCreacion+", inicioFechaUltimaModif: "+inicioFechaUltimaModif+", finFechaUltimaModif: "+finFechaUltimaModif);

		List<Dispositivo> dispositivosBuscados = new ArrayList<Dispositivo>();
		dispositivosBuscados =  new DispositivoDaoJDBC().buscarDispositivo(idDispositivo, nombreDispositivo, inicioFechaCreacion, finFechaCreacion, inicioFechaUltimaModif, finFechaUltimaModif);

		HttpSession sesion = request.getSession();		
		sesion.setAttribute("listaDispositivosEncontrados", dispositivosBuscados);
		sesion.setAttribute("totalDispositivosEncontrados", dispositivosBuscados.size());
		request.getRequestDispatcher("buscarDispositivo.jsp").forward(request, response); // la URL no cambia
		//response.sendRedirect("buscarCaso.jsp"); // la URL sí cambia, redirige
		
	}
	
	private void buscarEvidencia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// ********** idEvidencia **********	
		String idEvidenciaStr = (String) request.getParameter("idEvidencia"); // TODO antes de la conversión, controlar si es vacío - COMPROBAR SI SE QUEJA
		int idEvidencia = 0;
		if (Utils.isNumeric(idEvidenciaStr)) {
			idEvidencia = Integer.parseInt(idEvidenciaStr);
		}
		
		// ********** nombreEvidencia **********
		String nombreEvidencia = (String) request.getParameter("nombreEvidencia");
				
		// ********** inicioFechaCreacion ********** 
		String inicioFechaCreacion = (String) request.getParameter("inicioFechaCreacion"); // arriba 2021-03-01

		// ********** finFechaCreacion **********
		String finFechaCreacion = (String) request.getParameter("finFechaCreacion");
		
		// ********** inicioFechaUltimaModif **********
		String inicioFechaUltimaModif = (String) request.getParameter("inicioFechaUltimaModif");

		// ********** finFechaUltimaModif **********
		String finFechaUltimaModif = (String) request.getParameter("finFechaUltimaModif");
		
		System.out.println("[ServletControlador - buscarEvidencia] EVIDENCIA A BUSCAR --> idEvidencia: "+idEvidencia+", nombreEvidencia: "+nombreEvidencia+", inicioFechaCreacion: "+inicioFechaCreacion+", finFechaCreacion: "+finFechaCreacion+", inicioFechaUltimaModif: "+inicioFechaUltimaModif+", finFechaUltimaModif: "+finFechaUltimaModif);

		List<Evidencia> evidenciasBuscadas = new ArrayList<Evidencia>();
		evidenciasBuscadas =  new EvidenciaDaoJDBC().buscarEvidencia(idEvidencia, nombreEvidencia, inicioFechaCreacion, finFechaCreacion, inicioFechaUltimaModif, finFechaUltimaModif);

		HttpSession sesion = request.getSession();		
		sesion.setAttribute("listaEvidenciasEncontradas", evidenciasBuscadas);
		sesion.setAttribute("totalEvidenciasEncontradas", evidenciasBuscadas.size());
		request.getRequestDispatcher("buscarEvidencia.jsp").forward(request, response); // la URL no cambia
		//response.sendRedirect("buscarCaso.jsp"); // la URL sí cambia, redirige
		
	}
	
	private void buscarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// ********** idUsuario **********	
		String idUsuarioStr = (String) request.getParameter("idUsuario"); // TODO antes de la conversión, controlar si es vacío - COMPROBAR SI SE QUEJA
		int idUsuario = 0;
		if (Utils.isNumeric(idUsuarioStr)) {
			idUsuario = Integer.parseInt(idUsuarioStr);
		}
		
		// ********** nombreReal **********
		String nombreReal = (String) request.getParameter("nombreReal");
		
		// ********** nombreUsuario **********
		String nombreUsuario = (String) request.getParameter("nombreUsuario");
		
		// ********** correo **********
		String correo = (String) request.getParameter("correo");		
		
		// ********** telefono **********
		String telefono = (String) request.getParameter("telefono");		
		
		// ********** inicioFechaSesion ********** 
		String inicioFechaSesion = (String) request.getParameter("inicioFechaSesion"); // arriba 2021-03-01

		// ********** finFechaSesion **********
		String finFechaSesion = (String) request.getParameter("finFechaSesion");
		
		System.out.println("[ServletControlador - buscarUsuario] USUARIO A BUSCAR --> idUsuario: "+idUsuario+", nombreReal: "+nombreReal+", nombreUsuario: "+nombreUsuario+", correo: "+correo+", telefono: "+telefono+", inicioFechaSesion: "+inicioFechaSesion+", finFechaSesion: "+finFechaSesion);

		List<Persona> personasBuscadas = new ArrayList<Persona>();
		personasBuscadas =  new PersonaDaoJDBC().buscarPersona(idUsuario, nombreReal, nombreUsuario, correo, telefono, inicioFechaSesion, finFechaSesion);

		HttpSession sesion = request.getSession();		
		sesion.setAttribute("listaPersonasEncontradas", personasBuscadas);
		sesion.setAttribute("totalPersonasEncontradas", personasBuscadas.size());
		request.getRequestDispatcher("buscarUsuario.jsp").forward(request, response); // la URL no cambia
		//response.sendRedirect("buscarCaso.jsp"); // la URL sí cambia, redirige
		
	}
	
	private static final void salir(HttpServletRequest request, HttpServletResponse response) {
		  HttpSession sesion = request.getSession();
		  sesion.invalidate();
		}

}
