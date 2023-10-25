package logica;

import java.time.LocalDate;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;


@XmlAccessorType(XmlAccessType.FIELD)
public class DTPostulacion {
	private LocalDate fecha;
	private String cVReducido;
	private String motivacion;
	private String postulante;
	private String oferta;

	//CONSTRUCTORES
	public DTPostulacion() {
		
	}
	
	public DTPostulacion(LocalDate fecha, String CVReducido, String motivacion, String postulante, String oferta) {
		this.fecha = fecha;
		this.cVReducido = CVReducido;
		this.motivacion = motivacion;
		this.postulante = postulante;
		this.oferta=oferta;
	}
	
	//GETTERS
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
	
	//SETTERS
	public void setFecha(LocalDate fecha) {
	    this.fecha = fecha;
	}

	public void setCVReducido(String cVReducido) {
	    this.cVReducido = cVReducido;
	}

	public void setMotivacion(String motivacion) {
	    this.motivacion = motivacion;
	}

	public void setPostulante(String postulante) {
	    this.postulante = postulante;
	}

	public void setOferta(String oferta) {
	    this.oferta = oferta;
	}
}
