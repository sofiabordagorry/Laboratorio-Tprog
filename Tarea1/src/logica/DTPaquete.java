package logica;

import java.util.ArrayList;
import java.util.List;

public class DTPaquete {
	private String nombre;
	private String descripcion;
	private int periodoDeValidez;
	private float descuento;
	private float costoAsociado;
	private DTPaqueteTipo[] paquetesTipos;
	
	public DTPaquete(String nombre, String descripcion, int periodoDeValidez, float descuento, float costoAsociado, DTPaqueteTipo[] dtpaq) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.periodoDeValidez = periodoDeValidez;
		this.descuento = descuento;
		this.costoAsociado = costoAsociado;
		this.paquetesTipos = dtpaq;
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
	
	public float getCostoAsociado() {
		return this.costoAsociado;
	}
	
	public String toString() {
		return this.nombre;
	}
	
	public DTPaqueteTipo[] getPaqueteTipos() {
		return this.paquetesTipos;
	}
}
