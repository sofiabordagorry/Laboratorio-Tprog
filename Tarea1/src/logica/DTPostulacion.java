package logica;

import java.time.LocalDate;

public class Postulacion {
	private LocalDate fecha;
	private String CVReducido;
	private String descripcion;
	private DTPostulante postulante;
	private DTOfertaLaboral ofertaLaboral;
	
	public Postulacion(LocalDate fecha, String CVReducido, String descripcion, DTPostulante postulante, DTOfertaLaboral ofertaLaboral) {
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
	
	public DTPostulante getPostulante() {
		return this.postulante;
	}
	
	public DTOfertaLaboral getOfertaLaboral() {
		return this.ofertaLaboral;
	}
	
