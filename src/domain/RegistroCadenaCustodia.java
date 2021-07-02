package domain;

import java.util.Date;

public class RegistroCadenaCustodia {
	
	// IDs
	private int idRegistroCadenaCustodia;
	private int idCadenaCustodia;
	
	// Otros
	private Date fechaHora;
	private String personaEntrega;
	private String agenciaInstitucionEntrega;
	private String personaRecibe;
	private String agenciaInstitucionRecibe;
	private String actividadProposito;
	private String observaciones;
	
	private int orden;

	
	public RegistroCadenaCustodia() {
	}
	
	public RegistroCadenaCustodia(int idRegistroCadenaCustodia) {
		this.idRegistroCadenaCustodia = idRegistroCadenaCustodia;
	}
	
	public RegistroCadenaCustodia(int idRegistroCadenaCustodia, int idCadenaCustodia) {
		this.idRegistroCadenaCustodia = idRegistroCadenaCustodia;
		this.idCadenaCustodia = idCadenaCustodia;
	}

	public RegistroCadenaCustodia(int idRegistorCadenaCustodia, int idCadenaCustodia, Date fechaHora, String personaEntrega, String agenciaInstitucionEntrega, String personaRecibe, String agenciaInstitucionRecibe, String actividadProposito, String observaciones) {
		this.idRegistroCadenaCustodia = idRegistorCadenaCustodia;
		this.idCadenaCustodia = idCadenaCustodia;
		this.fechaHora = fechaHora;
		this.personaEntrega = personaEntrega;
		this.agenciaInstitucionEntrega = agenciaInstitucionEntrega;
		this.personaRecibe = personaRecibe;
		this.agenciaInstitucionRecibe = agenciaInstitucionRecibe;
		this.actividadProposito = actividadProposito;
		this.observaciones = observaciones;
	}
	
	public RegistroCadenaCustodia(int idCadenaCustodia, Date fechaHora, String personaEntrega, String agenciaInstitucionEntrega, String personaRecibe, String agenciaInstitucionRecibe, String actividadProposito, String observaciones) {
		this.idCadenaCustodia = idCadenaCustodia;
		this.fechaHora = fechaHora;
		this.personaEntrega = personaEntrega;
		this.agenciaInstitucionEntrega = agenciaInstitucionEntrega;
		this.personaRecibe = personaRecibe;
		this.agenciaInstitucionRecibe = agenciaInstitucionRecibe;
		this.actividadProposito = actividadProposito;
		this.observaciones = observaciones;
	}
	
	public int getIdRegistroCadenaCustodia() {
		return idRegistroCadenaCustodia;
	}


	public void setIdRegistroCadenaCustodia(int idRegistroCadenaCustodia) {
		this.idRegistroCadenaCustodia = idRegistroCadenaCustodia;
	}


	public int getIdCadenaCustodia() {
		return idCadenaCustodia;
	}


	public void setIdCadenaCustodia(int idCadenaCustodia) {
		this.idCadenaCustodia = idCadenaCustodia;
	}


	public Date getFechaHora() {
		return fechaHora;
	}


	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}


	public String getPersonaEntrega() {
		return personaEntrega;
	}


	public void setPersonaEntrega(String personaEntrega) {
		this.personaEntrega = personaEntrega;
	}


	public String getAgenciaInstitucionEntrega() {
		return agenciaInstitucionEntrega;
	}


	public void setAgenciaInstitucionEntrega(String agenciaInstitucionEntrega) {
		this.agenciaInstitucionEntrega = agenciaInstitucionEntrega;
	}


	public String getPersonaRecibe() {
		return personaRecibe;
	}


	public void setPersonaRecibe(String personaRecibe) {
		this.personaRecibe = personaRecibe;
	}


	public String getAgenciaInstitucionRecibe() {
		return agenciaInstitucionRecibe;
	}


	public void setAgenciaInstitucionRecibe(String agenciaInstitucionRecibe) {
		this.agenciaInstitucionRecibe = agenciaInstitucionRecibe;
	}


	public String getActividadProposito() {
		return actividadProposito;
	}


	public void setActividadProposito(String actividadProposito) {
		this.actividadProposito = actividadProposito;
	}


	public String getObservaciones() {
		return observaciones;
	}


	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}


	public int getOrden() {
		return orden;
	}


	public void setOrden(int orden) {
		this.orden = orden;
	}


	@Override
	public String toString() {
		return "RegistroCadenaCustodia [idRegistroCadenaCustodia=" + idRegistroCadenaCustodia + ", idCadenaCustodia="
				+ idCadenaCustodia + ", fechaHora=" + fechaHora + ", personaEntrega=" + personaEntrega
				+ ", agenciaInstitucionEntrega=" + agenciaInstitucionEntrega + ", personaRecibe=" + personaRecibe
				+ ", agenciaInstitucionRecibe=" + agenciaInstitucionRecibe + ", actividadProposito="
				+ actividadProposito + ", observaciones=" + observaciones + ", orden=" + orden + "]";
	}
	
}
