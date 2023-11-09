package logica;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTUsuario {
	private String nickname;
	private String nombre;
	private String apellido;
	private String correo;
	private String contrasenia;
	private byte[] imagen;
	private DTOfertaLaboral[] ofertasLaborales = null;
	
	public DTUsuario() {}
	
	/* public DTUsuario(String nickname, String nombre, String apellido, String correo, Map<String, DTOfertaLaboral> ofertasLaborales) {
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.ofertasLaborales = ofertasLaborales;
	}
	
	public DTUsuario(String nickname, String nombre, String apellido, String correo) {
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
	}
	
	public DTUsuario(String nickname, String nombre, String apellido, String correo, String contrasenia) {
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.contrasenia = contrasenia;
	}
	
	public DTUsuario(String nickname, String nombre, String apellido, String correo, Map<String, DTOfertaLaboral> ofertasLaborales, String contrasenia) {
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.ofertasLaborales = ofertasLaborales;
		this.contrasenia = contrasenia;
	} */
	
	public DTUsuario(String nickname, String nombre, String apellido, String correo, DTOfertaLaboral[] ofertasLaborales, String contrasenia, byte[] image) {
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.ofertasLaborales = ofertasLaborales;
		this.contrasenia = contrasenia;
		this.imagen = image;
	}
	
	public String getNickname() {
		return this.nickname;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getApellido() {
		return this.apellido;
	}
	
	public String getCorreo() {
		return this.correo;
	}
	
	public DTOfertaLaboral[] getDTOfertasLaborales() {
		return this.ofertasLaborales;
	}
	
	public String toString() {
		return this.nickname;
	}

	public String getContrasenia() {
		return this.contrasenia;
	}

	public byte[] getImagen() {
		return this.imagen;
	}
	
	//Setters
	
	public void setNickname(String nickname) {
	    this.nickname = nickname;
	}

	public void setNombre(String nombre) {
	    this.nombre = nombre;
	}

	public void setApellido(String apellido) {
	    this.apellido = apellido;
	}

	public void setCorreo(String correo) {
	    this.correo = correo;
	}

	public void setContrasenia(String contrasenia) {
	    this.contrasenia = contrasenia;
	}

	public void setImagen(byte[] imagen) {
	    this.imagen = imagen;
	}

	public void setOfertasLaborales(DTOfertaLaboral[] ofertasLaborales) {
	    this.ofertasLaborales = ofertasLaborales;
	}
	
}
