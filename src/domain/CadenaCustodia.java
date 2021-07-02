package domain;

import java.util.Date;
import java.util.List;

public class CadenaCustodia {
	
	// IDs
	private int idCadenaCustodia;
	private int idCaso;
	private int idDispositivo;
	private int idCicloVida;
	private List<RegistroCadenaCustodia> listaRegistros;
	
	// Otros
	private String localizacionOrigen;
	private Date fechaHoraOrigen;
	private String razon;
	private String agenciaInstitucion;
	private String persona;

	// Coger de la tabla 'dispositivo' en la BDD
	private String nombreDispositivo;
	private String marcaModeloDispositivo;
	private String descripcionDispositivo;
	private String coleccionAdquisicionDispositivo;
	private String hashDispositivo;
	
	
	public CadenaCustodia() {
	}
	
	public CadenaCustodia(int idDispositivo, String localizacionOrigen, Date fechaHoraOrigen, String razon, String agenciaInstitucion, String persona) {
		this.idDispositivo = idDispositivo;
		this.localizacionOrigen = localizacionOrigen;
		this.fechaHoraOrigen = fechaHoraOrigen;
		this.razon = razon;
		this.agenciaInstitucion = agenciaInstitucion;
		this.persona = persona;
	}
	
	public CadenaCustodia(int idCadenaCustodia) {
		this.idCadenaCustodia = idCadenaCustodia;
	}

	public int getIdCadenaCustodia() {
		return idCadenaCustodia;
	}

	public void setIdCadenaCustodia(int idCadenaCustodia) {
		this.idCadenaCustodia = idCadenaCustodia;
	}

	public int getIdCaso() {
		return idCaso;
	}

	public void setIdCaso(int idCaso) {
		this.idCaso = idCaso;
	}

	public int getIdDispositivo() {
		return idDispositivo;
	}

	public void setIdDispositivo(int idDispositivo) {
		this.idDispositivo = idDispositivo;
	}

	public int getIdCicloVida() {
		return idCicloVida;
	}

	public void setIdCicloVida(int idCicloVida) {
		this.idCicloVida = idCicloVida;
	}

	public List<RegistroCadenaCustodia> getListaRegistros() {
		return listaRegistros;
	}

	public void setListaRegistros(List<RegistroCadenaCustodia> listaRegistros) {
		this.listaRegistros = listaRegistros;
	}

	public String getLocalizacionOrigen() {
		return localizacionOrigen;
	}

	public void setLocalizacionOrigen(String localizacionOrigen) {
		this.localizacionOrigen = localizacionOrigen;
	}

	public Date getFechaHoraOrigen() {
		return fechaHoraOrigen;
	}

	public void setFechaHoraOrigen(Date fechaHoraOrigen) {
		this.fechaHoraOrigen = fechaHoraOrigen;
	}

	public String getRazon() {
		return razon;
	}

	public void setRazon(String razon) {
		this.razon = razon;
	}

	public String getAgenciaInstitucion() {
		return agenciaInstitucion;
	}

	public void setAgenciaInstitucion(String agenciaInstitucion) {
		this.agenciaInstitucion = agenciaInstitucion;
	}

	public String getPersona() {
		return persona;
	}

	public void setPersona(String persona) {
		this.persona = persona;
	}

	public String getNombreDispositivo() {
		return nombreDispositivo;
	}

	public void setNombreDispositivo(String nombreDispositivo) {
		this.nombreDispositivo = nombreDispositivo;
	}

	public String getMarcaModeloDispositivo() {
		return marcaModeloDispositivo;
	}

	public void setMarcaModeloDispositivo(String marcaModeloDispositivo) {
		this.marcaModeloDispositivo = marcaModeloDispositivo;
	}

	public String getDescripcionDispositivo() {
		return descripcionDispositivo;
	}

	public void setDescripcionDispositivo(String descripcionDispositivo) {
		this.descripcionDispositivo = descripcionDispositivo;
	}

	public String getColeccionAdquisicionDispositivo() {
		return coleccionAdquisicionDispositivo;
	}

	public void setColeccionAdquisicionDispositivo(String coleccionAdquisicionDispositivo) {
		this.coleccionAdquisicionDispositivo = coleccionAdquisicionDispositivo;
	}

	public String getHashDispositivo() {
		return hashDispositivo;
	}

	public void setHashDispositivo(String hashDispositivo) {
		this.hashDispositivo = hashDispositivo;
	}

	@Override
	public String toString() {
		return "CadenaCustodia [idCadenaCustodia=" + idCadenaCustodia + ", idCaso=" + idCaso + ", idDispositivo="
				+ idDispositivo + ", idCicloVida=" + idCicloVida + ", listaRegistros=" + listaRegistros
				+ ", localizacionOrigen=" + localizacionOrigen + ", fechaHoraOrigen=" + fechaHoraOrigen + ", razon="
				+ razon + ", agenciaInstitucion=" + agenciaInstitucion + ", persona=" + persona + ", nombreDispositivo="
				+ nombreDispositivo + ", marcaModeloDispositivo=" + marcaModeloDispositivo + ", descripcionDispositivo="
				+ descripcionDispositivo + ", coleccionAdquisicionDispositivo=" + coleccionAdquisicionDispositivo
				+ ", hashDispositivo=" + hashDispositivo + "]";
	}

	

}
