package logica;

import java.time.LocalDate;

public class DTTipo {
	private String nombre;
	private String descripcion;
	private int exposicion;
	private int duracion;
	private float costo;
	private LocalDate fechaDeAlta;
	
	public DTTipo(String nombre, String descripcion, int exposicion, int duracion, float costo, LocalDate fechaDeAlta) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.exposicion = exposicion;
		this.duracion = duracion;
		this.costo = costo;
		this.fechaDeAlta = fechaDeAlta;
	}
	
	public String toString() {
		return this.nombre + this.exposicion;
	}
	
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
	
}
