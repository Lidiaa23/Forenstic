package domain;

import java.util.Date;
import java.util.List;

public class Dispositivo {
	
	private int idDispositivo;
	private int idCadenaCustodia;
	private int idCicloVida;
	private int idCaso;
	private String nombre;
	private String nombreCaso;
	private String descripcion;
	private String tipo;
	private String fotografia;
	private String esClonadoForense;
	private String hash;
	private String estaCifrado;
	private String marca;
	private Date fechaCreacionReg;
	private Date fechaUltimaModif;
	private int numEvidenciasDigitales;
	private List<Evidencia> listaEvidenciasDigitales;
	
	public Dispositivo() {
	}
	
	public Dispositivo(int idDispositivo) {
		this.idDispositivo = idDispositivo;
	}
	
	public Dispositivo(int idDispositivo, int idCaso) {
		this.idDispositivo = idDispositivo;
		this.idCaso = idCaso;
	}
	
	public Dispositivo(int idCaso, String nombre, String descripcion, String fotografia, String hash, String estaCifrado, String tipo, String marca, String esClonadoForense) {
		this.idCaso = idCaso;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fotografia = fotografia;
		this.hash = hash;
		this.estaCifrado = estaCifrado;
		this.tipo = tipo;
		this.marca = marca;
		this.esClonadoForense = esClonadoForense;
	}
	
	public Dispositivo(int idDispositivo, int idCaso, String nombre, String descripcion, String fotografia, String hash, String estaCifrado, String tipo, String marca, String esClonadoForense, int idCadenaCustodia, int idCicloVida, int numEvidenciasDigitales, Date fechaCreacionReg, Date fechaUltimaModif) {
		this.idDispositivo = idDispositivo;
		this.idCaso = idCaso;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fotografia = fotografia;
		this.hash = hash;
		this.estaCifrado = estaCifrado;
		this.tipo = tipo;
		this.marca = marca;
		this.esClonadoForense = esClonadoForense;
		this.idCadenaCustodia = idCadenaCustodia;
		this.idCicloVida = idCicloVida;
		this.numEvidenciasDigitales = numEvidenciasDigitales;
		this.fechaCreacionReg = fechaCreacionReg;
		this.fechaUltimaModif = fechaUltimaModif;
	}
	
	public Dispositivo(int idDispositivo, int idCaso, String nombre, String descripcion, String fotografia, String hash, String estaCifrado, String tipo, String marca, String esClonadoForense) {
		this.idDispositivo = idDispositivo;
		this.idCaso = idCaso;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fotografia = fotografia;
		this.hash = hash;
		this.estaCifrado = estaCifrado;
		this.tipo = tipo;
		this.marca = marca;
		this.esClonadoForense = esClonadoForense;
	}

	public int getIdDispositivo() {
		return idDispositivo;
	}

	public void setIdDispositivo(int idDispositivo) {
		this.idDispositivo = idDispositivo;
	}

	public int getIdCadenaCustodia() {
		return idCadenaCustodia;
	}

	public void setIdCadenaCustodia(int idCadenaCustodia) {
		this.idCadenaCustodia = idCadenaCustodia;
	}

	public int getIdCicloVida() {
		return idCicloVida;
	}

	public void setIdCicloVida(int idCicloVida) {
		this.idCicloVida = idCicloVida;
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

	public String getFotografia() {
		return fotografia;
	}

	public void setFotografia(String fotografia) {
		this.fotografia = fotografia;
	}

	public String getEsClonadoForense() {
		return esClonadoForense;
	}

	public void setEsClonadoForense(String esClonadoForense) {
		this.esClonadoForense = esClonadoForense;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getEstaCifrado() {
		return estaCifrado;
	}

	public void setEstaCifrado(String estaCifrado) {
		this.estaCifrado = estaCifrado;
	}

	public Date getFechaCreacionReg() {
		return fechaCreacionReg;
	}

	public void setFechaCreacionReg(Date fechaCreacionReg) {
		this.fechaCreacionReg = fechaCreacionReg;
	}

	public Date getFechaUltimaModif() {
		return fechaUltimaModif;
	}

	public void setFechaUltimaModif(Date fechaUltimaModif) {
		this.fechaUltimaModif = fechaUltimaModif;
	}

	public List<Evidencia> getListaEvidenciasDigitales() {
		return listaEvidenciasDigitales;
	}

	public void setListaEvidenciasDigitales(List<Evidencia> listaEvidenciasDigitales) {
		this.listaEvidenciasDigitales = listaEvidenciasDigitales;
	}

	public int getNumEvidenciasDigitales() {
		return numEvidenciasDigitales;
	}

	public void setNumEvidenciasDigitales(int numEvidenciasDigitales) {
		this.numEvidenciasDigitales = numEvidenciasDigitales;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	public String getNombreCaso() {
		return nombreCaso;
	}

	public void setNombreCaso(String nombreCaso) {
		this.nombreCaso = nombreCaso;
	}

	@Override
	public String toString() {
		return "Dispositivo [idDispositivo=" + idDispositivo + ", idCadenaCustodia=" + idCadenaCustodia
				+ ", idCicloVida=" + idCicloVida + ", idCaso=" + idCaso + ", nombre=" + nombre + ", descripcion="
				+ descripcion + ", tipo=" + tipo + ", fotografia=" + fotografia
				+ ", esClonadoForense=" + esClonadoForense + ", nombreCaso=" + nombreCaso +", hash=" + hash + ", estaCifrado=" + estaCifrado
				+ ", marca=" + marca + ", fechaCreacionReg=" + fechaCreacionReg + ", fechaUltimaModif="
				+ fechaUltimaModif + ", numEvidenciasDigitales=" + numEvidenciasDigitales
				+ ", listaEvidenciasDigitales=" + listaEvidenciasDigitales + "]";
	}

}
