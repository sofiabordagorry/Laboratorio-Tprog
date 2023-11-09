package logica;

import java.time.LocalDate;


public class DTTipo {
	private String nombre;
	private String descripcion;
	private int exposicion;
	private int duracion;
	private float costo;
	private String fechaDeAlta;
	
	public DTTipo() {}
	
	public DTTipo(String nombre, String descripcion, int exposicion, int duracion, float costo, String fechaDeAlta) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.exposicion = exposicion;
		this.duracion = duracion;
		this.costo = costo;
		this.fechaDeAlta = fechaDeAlta;
	}
	
	public String toString() {
		return this.nombre;
	}

    // Getter para nombre
    public String getNombre() {
        return nombre;
    }

    // Setter para nombre
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter para descripcion
    public String getDescripcion() {
        return descripcion;
    }

    // Setter para descripcion
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Getter para exposicion
    public int getExposicion() {
        return exposicion;
    }

    // Setter para exposicion
    public void setExposicion(int exposicion) {
        this.exposicion = exposicion;
    }

    // Getter para duracion
    public int getDuracion() {
        return duracion;
    }

    // Setter para duracion
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    // Getter para costo
    public float getCosto() {
        return costo;
    }

    // Setter para costo
    public void setCosto(float costo) {
        this.costo = costo;
    }

    // Getter para fechaDeAlta
    public String getFechaDeAlta() {
        return this.fechaDeAlta;
    }

    // Setter para fechaDeAlta
    public void setFechaDeAlta(String fechaDeAlta) {
        this.fechaDeAlta = fechaDeAlta;
    }

    public LocalDate calcularVencimiento(LocalDate fecha) {
    	return fecha.plusDays(this.duracion);
    }
}
