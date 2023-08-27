package logica;

import java.time.LocalDate;

public class Postulacion {
	private LocalDate fecha;
	private String CVReducido;
	private String motivacion;
	private Postulante postulante;
	private OfertaLaboral ofertaLaboral;
	
	public Postulacion(LocalDate fecha, String CVReducido, String motivacion, Postulante postulante, OfertaLaboral ofertaLaboral) {
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

	public void setMotivacion(String motivacion) {
		this.motivacion = motivacion;

	}
	
	public void setPostulante(Postulante postulante) {
		this.postulante = postulante;
	}
	
	public void setOfertaLaboral(OfertaLaboral ofertaLaboral) {
		this.ofertaLaboral = ofertaLaboral;
	}
	
	public Boolean verificarOfertaLaboral(String oferta) {
		String nombre = this.ofertaLaboral.getNombre();
		return oferta==nombre;
	}

	public DTPostulacion getDataPostulacion() {
		DTPostulacion dtP = new DTPostulacion(this.getFecha(), this.getCVReducido(), this.getMotivacion(), this.getPostulante().getNickname(), this.getOfertaLaboral().getNombre());
		return dtP;
	}
}