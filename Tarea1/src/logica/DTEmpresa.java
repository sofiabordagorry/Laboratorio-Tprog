package logica;

import java.util.Map;

public class DTEmpresa extends DTUsuario {
	private String descripcion;
	private String link;
	
	public DTEmpresa(String nickname, String nombre, String apellido, String correo, Map<String, DTOfertaLaboral> ofertasLaborales, String descripcion, String link) {
		super(nickname, nombre, apellido, correo, ofertasLaborales);
		this.descripcion = descripcion;
		this.link = link;
	}

	public DTEmpresa(String nickname, String nombre, String apellido, String correo, String descripcion, String link) {
		super(nickname, nombre, apellido, correo);
		this.descripcion = descripcion;
		this.link = link;
	}
	
	public DTEmpresa(String nickname, String nombre, String apellido, String correo, Map<String, DTOfertaLaboral> ofertasLaborales, String descripcion, String link, String contrasenia) {
		super(nickname, nombre, apellido, correo, ofertasLaborales, contrasenia);
		this.descripcion = descripcion;
		this.link = link;
	}
	
	public DTEmpresa(String nickname, String nombre, String apellido, String correo, Map<String, DTOfertaLaboral> ofertasLaborales, String descripcion, String link, String contrasenia, byte[] image) {
		super(nickname, nombre, apellido, correo, ofertasLaborales, contrasenia, image);
		this.descripcion = descripcion;
		this.link = link;
	}
	
	public String toString() {
		return this.getNombre() + this.getApellido() + "(" + this.getCorreo() + ")";
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public String getLink() {
		return this.link;
	}
}
