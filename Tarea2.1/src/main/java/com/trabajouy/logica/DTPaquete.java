package com.trabajouy.logica;

import java.time.LocalDate;

public class DTPaquete {
	private String nombre;
	private String descripcion;
	private int periodoDeValidez;
	private float descuento;
	private LocalDate fecha;
	private float costoAsociado;
	private DTPaqueteTipo[] paquetesTipos;
	private LocalDate fechaDeAlta;
	
	
	public DTPaquete(String nombre, String descripcion, int periodoDeValidez, float descuento, float costoAsociado, DTPaqueteTipo[] dtpaq, LocalDate fechaDeAlta) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.periodoDeValidez = periodoDeValidez;
		this.descuento = descuento;
		this.costoAsociado = costoAsociado;
		this.paquetesTipos = dtpaq;
		this.fechaDeAlta = fechaDeAlta;

	}
	
	public DTPaquete(String nombre, String descripcion, int periodoDeValidez, float descuento, DTPaqueteTipo[] dtpaq, LocalDate fechaDeAlta) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.periodoDeValidez = periodoDeValidez;
		this.descuento = descuento;
		this.paquetesTipos = dtpaq;
		this.fechaDeAlta = fechaDeAlta;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public int getPeriodoDeValidez() {
		return this.periodoDeValidez;
	}
	
	public float getDescuento() {
		return this.descuento;
	}
	
	public LocalDate getFecha() {
		return this.fecha;
	}
	
	public float getCostoAsociado() {
		return this.costoAsociado;
	}
	
	public String toString() {
		return this.nombre;
	}
	
	public DTPaqueteTipo[] getPaqueteTipos() {
		return this.paquetesTipos;
	}
	
	public LocalDate getFechaDeAlta() {
		return this.fechaDeAlta;
	}
}

