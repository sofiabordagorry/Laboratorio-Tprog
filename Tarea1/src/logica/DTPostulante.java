package logica;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

public class DTPostulante extends DTUsuario {
	private LocalDate fechaDeNacimiento;
	private String nacionalidad;
	
	public DTPostulante(String nickname, String nombre, String apellido, String correo, Map<String, DTOfertaLaboral> ofertasLaborales, LocalDate fechaDeNacimiento, String nacionalidad) {
		super(nickname, nombre, apellido, correo, ofertasLaborales);
		this.fechaDeNacimiento = fechaDeNacimiento;
		this.nacionalidad = nacionalidad;
	}
	
	public LocalDate getFechaDeNacimiento() {
		return this.fechaDeNacimiento;
	}
	
	public String getNacionalidad() {
		return this.nacionalidad;
	}
}
