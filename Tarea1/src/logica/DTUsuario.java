package logica;

import java.util.Map;

public class DTUsuario {
	private String nickname;
	private String nombre;
	private String apellido;
	private String correo;
	private String contrasenia;
	private byte[] imagen;
	private Map<String, DTOfertaLaboral> ofertasLaborales;
	
	public DTUsuario(String nickname, String nombre, String apellido, String correo, Map<String, DTOfertaLaboral> ofertasLaborales) {
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
	
	public DTUsuario(String nickname, String nombre, String apellido, String correo,String contrasenia) {
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
	}
	
	public DTUsuario(String nickname, String nombre, String apellido, String correo, Map<String, DTOfertaLaboral> ofertasLaborales, String contrasenia, byte[] image) {
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
	
	public Map<String, DTOfertaLaboral> getDTOfertasLaborales() {
		return this.ofertasLaborales;
	}
	
	public String toString() {
		return this.nickname;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public byte[] getImagen() {
		return imagen;
	}
}
