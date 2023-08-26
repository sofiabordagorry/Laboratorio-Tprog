package logica;

import java.time.LocalDate;

public class Postulacion {
	private LocalDate fecha;
	private String CVReducido;
	private String descripcion;
	private Postulante postulante;
	private OfertaLaboral ofertaLaboral;
	
	public Postulacion(LocalDate fecha, String CVReducido, String descripcion, Postulante postulante, OfertaLaboral ofertaLaboral) {
		this.fecha = fecha;
		this.CVReducido = CVReducido;
		this.descripcion = descripcion;
		this.postulante = postulante;
		this.ofertaLaboral = ofertaLaboral;
	}
	
	public LocalDate getFecha() {
		return this.fecha;
	}
	
	public String getCVReducido() {
		return this.CVReducido;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public Postulante getPostulante() {
		return this.postulante;
	}
	
	public OfertaLaboral getOfertaLaboral() {
		return this.ofertaLaboral;
	}
	
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	public void setCVReducido(String CVReducido) {
		this.CVReducido = CVReducido;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public void setPostulante(Postulante postulante) {
		this.postulante = postulante;
	}
	
	public void setOfertaLaboral(OfertaLaboral ofertaLaboral) {
		this.ofertaLaboral = ofertaLaboral;
	}
}