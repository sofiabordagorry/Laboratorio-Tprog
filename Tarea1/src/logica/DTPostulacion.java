package logica;

import java.time.LocalDate;

public class DTPostulacion {
	private LocalDate fecha;
	private String CVReducido;
	private String descripcion;
	private String postulante;

	
	public DTPostulacion(LocalDate fecha, String CVReducido, String descripcion, String postulante) {
		this.fecha = fecha;
		this.CVReducido = CVReducido;
		this.descripcion = descripcion;
		this.postulante = postulante;
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
	
	public String getPostulante() {
		return this.postulante;
	}
	
}
