package com.trabajouy.model;

import java.time.LocalDate;

public class Tipo {
	private String nombre;
	private String descripcion;
	private int exposicion;
	private int duracion;
	private float costo;
	private LocalDate fechaDeAlta;
	
	public Tipo(String nombre, String descripcion, int exposicion, int duracion, float costo, LocalDate fechaDeAlta) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.exposicion = exposicion;
		this.duracion = duracion;
		this.costo = costo;
		this.fechaDeAlta = fechaDeAlta;
	}
	
	// Getters
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
    
    public DTTipo getDataTipo() {
    	return new DTTipo(this.getNombre(), this.getDescripcion(), this.getExposicion(), 
										this.getDuracion(), this.getCosto(), this.getFechaDeAlta());
    } 
    
    public LocalDate calcularVencimiento(LocalDate fecha) {
    	return fecha.plusDays(this.duracion);
    }
}
