package logica;

import java.time.LocalDate;

public class DTPostulacion {
	private LocalDate fecha;
	private String CVReducido;
	private String motivacion;
	private DTPostulante postulante;
	private DTOfertaLaboral ofertaLaboral;
	
	public DTPostulacion(LocalDate fecha, String CVReducido, String motivacion, DTPostulante postulante, DTOfertaLaboral ofertaLaboral) {
		this.fecha = fecha;
		this.CVReducido = CVReducido;
		this.motivacion = motivacion;
		this.postulante = postulante;
		this.ofertaLaboral = ofertaLaboral;
	}
	
	public LocalDate getFecha() {
		return this.fecha;
	}
	
	public String getCVReducido() {
		return this.CVReducido;
	}
	
	public String getMotivacion() {
		return this.motivacion;
	}
	
	public DTPostulante getPostulante() {
		return this.postulante;
	}
	
	public DTOfertaLaboral getOfertaLaboral() {
		return this.ofertaLaboral;
	}
	
}
