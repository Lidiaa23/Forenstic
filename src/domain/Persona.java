package domain;

import java.util.Date;
import java.util.List;

public class Persona {

	private int idPersona;
	private String nombre;
	private String usuario;
	private String contrasena;
	private String correo;
	private Date ultimaSesion;
	private String telefono;
	private List<Caso> listaCasos;
	
	public Persona() {
	}
	
	public Persona(int idPersona, String nombre, String usuario, String contrasena, String correo, String telefono) {
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.correo = correo;
		this.telefono = telefono;
	}
	
	public Persona(int idPersona, String nombre, String usuario, String correo, String telefono) {
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.usuario = usuario;
		this.correo = correo;
		this.telefono = telefono;
	}
	
	public Persona(int idPersona) {
		this.idPersona = idPersona;
	}
	
	public Persona(String usuario) {
		this.usuario = usuario;
	}
	
	public Persona(String nombre, List<Caso> listaCasos) {
		this.nombre = nombre;
		this.listaCasos = listaCasos;
	}
	
	public Persona(String usuario, String contrasena) {
		this.usuario = usuario;
		this.contrasena = contrasena;
	}
	
	public Persona(String nombre, String usuario, String contrasena, String correo, String telefono) {
		this.nombre = nombre;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.correo = correo;
		this.telefono = telefono;
	}
	
	public Persona(int idPersona, String nombre, String usuario, String correo, String telefono, Date ultimaSesion) {
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.usuario = usuario;
		this.correo = correo;
		this.telefono = telefono;
		this.ultimaSesion = ultimaSesion;
	}

	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Caso> getListaCasos() {
		return listaCasos;
	}

	public void setListaCasos(List<Caso> listaCasos) {
		this.listaCasos = listaCasos;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Date getUltimaSesion() {
		return ultimaSesion;
	}

	public void setUltimaSesion(Date ultimaSesion) {
		this.ultimaSesion = ultimaSesion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "Persona [idPersona=" + idPersona + ", nombre=" + nombre + ", usuario=" + usuario + ", contrasena="
				+ contrasena + ", correo=" + correo + ", ultimaSesion=" + ultimaSesion + ", telefono=" + telefono
				+ ", listaCasos=" + listaCasos + "]";
	}

}
