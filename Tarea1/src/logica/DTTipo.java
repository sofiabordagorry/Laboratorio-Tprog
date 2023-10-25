package logica;

import java.time.LocalDate;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;


@XmlAccessorType(XmlAccessType.FIELD)
public class DTTipo {
	private String nombre;
	private String descripcion;
	private int exposicion;
	private int duracion;
	private float costo;
	private LocalDate fechaDeAlta;
	
	//CONSTRUCTORES
	public DTTipo() {
		
	}
	
	public DTTipo(String nombre, String descripcion, int exposicion, int duracion, float costo, LocalDate fechaDeAlta) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.exposicion = exposicion;
		this.duracion = duracion;
		this.costo = costo;
		this.fechaDeAlta = fechaDeAlta;
	}
	//GETTERS
	public String getNombre() {
		return this.nombre;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public int getExposicion() {
		return this.exposicion;
	}
	
	public int getDuracion() {
		return this.duracion;
	}
	
	public float getCosto() {
		return this.costo;
	}
	
	public LocalDate getFechaDeAlta() {
		return this.fechaDeAlta;
	}
	//SETTERS
	public void setNombre(String nombre) {
	    this.nombre = nombre;
	}

	public void setDescripcion(String descripcion) {
	    this.descripcion = descripcion;
	}

	public void setExposicion(int exposicion) {
	    this.exposicion = exposicion;
	}

	public void setDuracion(int duracion) {
	    this.duracion = duracion;
	}

	public void setCosto(float costo) {
	    this.costo = costo;
	}

	public void setFechaDeAlta(LocalDate fechaDeAlta) {
	    this.fechaDeAlta = fechaDeAlta;
	}
	//OTRAS FUNCIONES
    public LocalDate calcularVencimiento(LocalDate fecha) {
    	return fecha.plusDays(this.duracion);
    }
    
	public String toString() {
		return this.nombre;
	}
}
