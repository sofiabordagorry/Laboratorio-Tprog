package logica;

import java.time.LocalDate;

public class DTPostulacion {
	private LocalDate fecha;
	private String cVReducido;
	private String motivacion;
	private String postulante;
	private String oferta;

	
	public DTPostulacion(LocalDate fecha, String CVReducido, String motivacion, String postulante, String oferta) {
		this.fecha = fecha;
		this.cVReducido = CVReducido;
		this.motivacion = motivacion;
		this.postulante = postulante;
		this.oferta=oferta;
	}
	
	public LocalDate getFecha() {
		return this.fecha;
	}
	
	public String getCVReducido() {
		return this.cVReducido;
	}
	
	public String getMotivacion() {
		return this.motivacion;
	}
	
	public String getPostulante() {
		return this.postulante;
	}
	
	public String getOferta() {
		return this.oferta;
	}
	
}
