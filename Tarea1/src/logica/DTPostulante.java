package logica;

import java.time.LocalDate;
import java.util.Map;

public class DTPostulante extends DTUsuario {
	private LocalDate fechaDeNacimiento;
	private String nacionalidad;
	
	public DTPostulante(String nickname, String nombre, String apellido, String correo, LocalDate fechaDeNacimiento, String nacionalidad) {
		super(nickname, nombre, apellido, correo);
		this.fechaDeNacimiento = fechaDeNacimiento;
		this.nacionalidad = nacionalidad;
	}
	
	public DTPostulante(String nickname, String nombre, String apellido, String correo, LocalDate fechaDeNacimiento, String nacionalidad, Map<String, DTOfertaLaboral> ofLab) {
		super(nickname, nombre, apellido, correo, ofLab);
		this.fechaDeNacimiento = fechaDeNacimiento;
		this.nacionalidad = nacionalidad;
	}
	
	public DTPostulante(String nickname, String nombre, String apellido, String correo, LocalDate fechaDeNacimiento, String nacionalidad,String contrasenia) {
		super(nickname, nombre, apellido, correo, contrasenia);
		this.fechaDeNacimiento = fechaDeNacimiento;
		this.nacionalidad = nacionalidad;
	}
	
	public DTPostulante(String nickname, String nombre, String apellido, String correo, LocalDate fechaDeNacimiento, String nacionalidad,String contrasenia, byte[] image) {
		super(nickname, nombre, apellido, correo,null, contrasenia, image);
		this.fechaDeNacimiento = fechaDeNacimiento;
		this.nacionalidad = nacionalidad;
	}
	

	public LocalDate getFechaDeNacimiento() {
		return this.fechaDeNacimiento;
	}
	
	public String getNacionalidad() {
		return this.nacionalidad;
	}
	
	public String toString() {
		return this.getNombre() + "(" + this.getCorreo() + ")";
	}
}
