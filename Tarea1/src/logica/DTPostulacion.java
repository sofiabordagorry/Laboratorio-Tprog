package logica;

import java.time.LocalDate;

public class DTPostulacion {
	private LocalDate fecha;
	private String CVReducido;
	private String motivacion;
	private String postulante;

	
	public DTPostulacion(LocalDate fecha, String CVReducido, String motivacion, String postulante) {
		this.fecha = fecha;
		this.CVReducido = CVReducido;
		this.motivacion = motivacion;
		this.postulante = postulante;
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
	
	public String getPostulante() {
		return this.postulante;
	}
	
}
