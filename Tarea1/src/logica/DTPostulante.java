package logica;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTPostulante extends DTUsuario {
	private String fechaDeNacimiento;
	private String nacionalidad;
	private List<DTPostulacion> postulaciones = new ArrayList<>();
	private List<DTOfertaLaboral> ofertasFavoritas = new ArrayList<>();
	
	public DTPostulante() {
		super();
	}
	
	public DTPostulante(String nickname, String nombre, String apellido, String correo, String contrasenia, String fechaDeNacimiento, String nacionalidad, DTOfertaLaboral[] ofLab, List<DTPostulacion> posts, byte[] image) {
		super(nickname, nombre, apellido, correo, ofLab, contrasenia, image);
		this.fechaDeNacimiento = fechaDeNacimiento;
		this.nacionalidad = nacionalidad;
		this.postulaciones = posts;
	}

	// -------- Getters -----------
	public String getFechaDeNacimiento() {
		return this.fechaDeNacimiento;
	}
	
	public String getNacionalidad() {
		return this.nacionalidad;
	}
	
	public String toString() {
		return this.getNombre() + "(" + this.getCorreo() + ")";
	}
	
	public List<DTPostulacion> getPostulaciones() {
		return postulaciones;
	}
	
	// -------- Setters -----------
	public void setFechaDeNacimiento(String fechaDeNacimiento) {
	    this.fechaDeNacimiento = fechaDeNacimiento;
	}
	

	public void setNacionalidad(String nacionalidad) {
	    this.nacionalidad = nacionalidad;
	}

	public void setPostulaciones(List<DTPostulacion> postulaciones) {
	    this.postulaciones = postulaciones;
	}
	
	public boolean esFavorito(DTOfertaLaboral oferta) {
		return this.ofertasFavoritas.contains(oferta);
	}
	
	public void agregarOfertaFav(DTOfertaLaboral oferta) {
		this.ofertasFavoritas.add(oferta);
	}
	
	public void quitarOfertaFav(DTOfertaLaboral oferta) {
		this.ofertasFavoritas.remove(oferta);
	}
}
