package logica;

import java.util.Date;
import java.util.Map;

public class DTPostulante extends DTUsuario {
	private Date fechaDeNacimiento;
	private String nacionalidad;
	
	public DTPostulante(String nickname, String nombre, String apellido, String correo, Map<String, DTOfertaLaboral> ofertasLaborales, Date fechaDeNacimiento, String nacionalidad) {
		super(nickname, nombre, apellido, correo, ofertasLaborales);
		this.fechaDeNacimiento = fechaDeNacimiento;
		this.nacionalidad = nacionalidad;
	}
	
	public Date getFechaDeNacimiento() {
		return this.fechaDeNacimiento;
	}
	
	public String getNacionalidad() {
		return this.nacionalidad;
	}
}
