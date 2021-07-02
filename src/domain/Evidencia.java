package domain;

import java.util.Date;

public class Evidencia {

	private int idEvidencia;
	private int idCaso;
	private int idDispositivo;
	private String nombre;
	private String nombreDispositivo;
	private String nombreCaso;
	private String descripcion;
	private String tipo;
	//private Double tamano;
	private Date fechaCreacionReg;
	private Date fechaUltimaModif;

	public Evidencia() {
	}
	
	// En el caso de eliminar
	public Evidencia(int idEvidencia) {
		this.idEvidencia = idEvidencia;
	}
	
	// En el caso de insertar
	public Evidencia(int idCaso, int idDispositivo, String nombre, String descripcion, String tipo) {
		this.idCaso = idCaso;
		this.idDispositivo = idDispositivo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tipo = tipo;
		//this.tamano = tamano;
		Date dateFechaCreacionReg = new Date(System.currentTimeMillis());
		this.fechaCreacionReg = dateFechaCreacionReg;
		this.fechaUltimaModif = dateFechaCreacionReg;
	}
	
	// En el caso de consultar
	public Evidencia(int idEvidencia, int idCaso, int idDispositivo, String nombre, String descripcion, String tipo, Date fechaCreacionReg, Date fechaUltimaModif) {
		this.idEvidencia = idEvidencia;
		this.idCaso = idCaso;
		this.idDispositivo = idDispositivo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tipo = tipo;
		//this.tamano = tamano;
		this.fechaCreacionReg = fechaCreacionReg;
		this.fechaUltimaModif = fechaUltimaModif;
	}
	
	// En el caso de editar
	public Evidencia(int idEvidencia, int idCaso, int idDispositivo, String nombre, String descripcion, String tipo) {
		this.idEvidencia = idEvidencia;
		this.idCaso = idCaso;
		this.idDispositivo = idDispositivo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tipo = tipo;
		//this.tamano = tamano;
	}
	
	// En el caso de la modificación
	public Evidencia(int idEvidencia, int idCaso, String nombre, String descripcion, String tipo, int idDispositivo) {
		this.idEvidencia = idEvidencia;
		this.idCaso = idCaso;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.idDispositivo = idDispositivo;
		//this.tamano = tamano;
		// FechaCreacionReg no se puede cambiar
		Date dateFechaCreacionReg = new Date(System.currentTimeMillis());
		this.fechaUltimaModif = dateFechaCreacionReg;
	}

	public int getIdEvidencia() {
		return idEvidencia;
	}

	public void setIdEvidencia(int idEvidencia) {
		this.idEvidencia = idEvidencia;
	}

	public int getIdCaso() {
		return idCaso;
	}

	public void setIdCaso(int idCaso) {
		this.idCaso = idCaso;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public int getIdDispositivo() {
		return this.idDispositivo;
	}
	
	public void setIdDispositivo(int idDispositivo) {
		this.idDispositivo = idDispositivo;
	}
	
	/*public Double getTamano() {
		return this.tamano;
	}
	
	public void setTamano(Double tamano) {
		this.tamano = tamano;
	}*/
	
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

	public String getNombreDispositivo() {
		return nombreDispositivo;
	}

	public void setNombreDispositivo(String nombreDispositivo) {
		this.nombreDispositivo = nombreDispositivo;
	}
	
	public String getNombreCaso() {
		return nombreCaso;
	}

	public void setNombreCaso(String nombreCaso) {
		this.nombreCaso = nombreCaso;
	}

	@Override
	public String toString() {
		return "Evidencia [idEvidencia=" + idEvidencia + ", idCaso=" + idCaso + ", idDispositivo=" + idDispositivo
				+ ", nombre=" + nombre + ", nombreDispositivo=" + nombreDispositivo + ", descripcion=" + descripcion
				+ ", tipo=" + tipo + ", nombreCaso=" + nombreCaso + ", fechaCreacionReg=" + fechaCreacionReg
				+ ", fechaUltimaModif=" + fechaUltimaModif + "]";
	}

}
