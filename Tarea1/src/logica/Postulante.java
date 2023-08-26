package logica;

import java.time.LocalDate;
import java.util.LinkedList;

public class Postulante extends Usuario {
	
	private LocalDate fechaNacimiento;
	private String nacionalidad;
	private LinkedList<Postulacion> postulaciones;

	public Postulante(String nickname, String nombre, String apellido, String correo, LocalDate fechaNacimiento, String nacionalidad) {
		super(nickname, nombre, apellido, correo);
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
	}
	
	public LocalDate getFechaNacimiento() {
		return this.fechaNacimiento;
	}
	
	public String getNacionalidad() {
		return this.nacionalidad;
	}
	
	public Boolean existePostulacion(String oferta) {
		LinkedList<Postulacion> postulaciones = this.postulaciones;
		Boolean existe=false;
        for (int i = 0; i < postulaciones.size(); i++) {
        	existe=postulaciones.get(i).verificarOfertaLaboral(oferta);
        }
        return existe;
	}
	
	public void agregarPostulacion(Postulacion postulacion) {
		this.postulaciones.add(postulacion);
	}
}
