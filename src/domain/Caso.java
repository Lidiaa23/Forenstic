package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Caso {
	
	private int idCaso;
	private String nombre;
	private String descripcion;
	private List<Evidencia> listaEvidencias;
	private List<Persona> listaPersonas;
	private List<Dispositivo> listaDispositivos;
	private Date fechaCreacionReg;
	private Date fechaUltimaModif;
	private int numDispositivos;
	private int numEvidencias;
	
	public Caso() {
	}
	
	// En el caso de eliminar
	public Caso(int idCaso) {
		this.idCaso = idCaso;
	}
	
	// En el caso de insertar
	public Caso(String nombre, String descripcion, List<Evidencia> listaEvidencias, List<Persona> listaPersonas, List<Dispositivo> listaDispositivos) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.listaEvidencias = listaEvidencias;
		this.listaPersonas = listaPersonas;
		this.listaDispositivos = listaDispositivos;
		Date dateFechaActual = new Date(System.currentTimeMillis());
		this.fechaCreacionReg = dateFechaActual;
		this.fechaUltimaModif = dateFechaActual;	
	}
	
	// En el caso de insertar
	public Caso(String nombre, String descripcion, Persona persona) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.listaPersonas = new ArrayList<Persona>();
		this.listaPersonas.add(persona);
		Date dateFechaActual = new Date(System.currentTimeMillis());
		this.fechaCreacionReg = dateFechaActual;
		this.fechaUltimaModif = dateFechaActual;	
	}
	
	// En el caso de la modificación o consulta para listarCasos
	public Caso(int idCaso, String nombre, String descripcion, int numDispositivos, int numEvidencias, List<Evidencia> listaEvidencias, List<Persona> listaPersonas, List<Dispositivo> listaDispositivos, Date fechaCreacionReg, Date fechaUltimaModif) {
		this.idCaso = idCaso;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.numDispositivos = numDispositivos;
		this.numEvidencias = numEvidencias;
		this.listaEvidencias = listaEvidencias;
		this.listaDispositivos = listaDispositivos;
		this.listaPersonas = listaPersonas;
		// TODO ¿FechaCreacion no se va a modificar?
		this.fechaCreacionReg = fechaCreacionReg;
		this.fechaUltimaModif = fechaUltimaModif;
	}
	
	// En el caso de la modificación
	public Caso(int idCaso, String nombre, String descripcion, Date fechaCreacionReg, Date fechaUltimaModif) {
		this.idCaso = idCaso;
		this.nombre = nombre;
		this.descripcion = descripcion;
		// TODO ¿FechaCreacion no se va a modificar?
		this.fechaCreacionReg = fechaCreacionReg;
		this.fechaUltimaModif = fechaUltimaModif;
	}
	
	public int getIdCaso() {
		return this.idCaso;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public void setIdCaso(int idCaso) {
		this.idCaso = idCaso;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public List<Evidencia> getListaEvidencias() {
		return this.listaEvidencias;
	}
	
	public void setListaEvidencias(List<Evidencia> listaEvidencias) {
		this.listaEvidencias = listaEvidencias;
	}

	public List<Persona> getListaPersonas() {
		return listaPersonas;
	}

	public void setListaPersonas(List<Persona> listaPersonas) {
		this.listaPersonas = listaPersonas;
	}
	
	public List<Dispositivo> getListaDispositivos() {
		return this.listaDispositivos;
	}
	
	public void setListaDispositivos(List<Dispositivo> listaDispositivos) {
		this.listaDispositivos = listaDispositivos;
	}
	
	public Date getFechaCreacionReg() {
		return this.fechaCreacionReg;
	}
	
	public void setFechaCreacionReg(Date fechaCreacionReg) {
		this.fechaCreacionReg = fechaCreacionReg;
	}
	
	public Date getFechaUltimaModif() {
		return this.fechaUltimaModif;
	}
	
	public void setFechaUltimaModif(Date fechaUltimaModif) {
		this.fechaUltimaModif = fechaUltimaModif;
	}
	
	

	public int getNumDispositivos() {
		return numDispositivos;
	}

	public void setNumDispositivos(int numDispositivos) {
		this.numDispositivos = numDispositivos;
	}

	public int getNumEvidencias() {
		return numEvidencias;
	}

	public void setNumEvidencias(int numEvidencias) {
		this.numEvidencias = numEvidencias;
	}

	@Override
	public String toString() {
		return "Caso [idCaso=" + idCaso + ", nombre=" + nombre + ", descripcion=" + descripcion + ", listaEvidencias="
				+ listaEvidencias + ", listaPersonas=" + listaPersonas + ", listaDispositivos=" + listaDispositivos
				+ ", fechaCreacionReg=" + fechaCreacionReg + ", fechaUltimaModif=" + fechaUltimaModif
				+ ", numDispositivos=" + numDispositivos + ", numEvidencias=" + numEvidencias + "]";
	}

}
