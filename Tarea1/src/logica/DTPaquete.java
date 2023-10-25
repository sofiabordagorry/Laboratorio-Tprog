package logica;

import java.time.LocalDate;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)

public class DTPaquete {
	private String nombre;
	private String descripcion;
	private int periodoDeValidez;
	private float descuento;
	private LocalDate fecha;
	private float costoAsociado;
	private DTPaqueteTipo[] paquetesTipos;
	private LocalDate fechaDeAlta;
	private byte[] imagen;
	
	public DTPaquete() {
		
	}
	
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
	
	public DTPaquete(String nombre, String descripcion, int periodoDeValidez, float descuento, DTPaqueteTipo[] dtpaq, LocalDate fechaDeAlta, byte[] image) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.periodoDeValidez = periodoDeValidez;
		this.descuento = descuento;
		this.paquetesTipos = dtpaq;
		this.fechaDeAlta = fechaDeAlta;
		this.imagen = image;
	}
	
	// --- getters ---
	
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

	public byte[] getImagen() {
		return imagen;
	}
	
	// --- setters ---
	
	public void setNombre(String nombre) {
	    this.nombre = nombre;
	}

	public void setDescripcion(String descripcion) {
	    this.descripcion = descripcion;
	}

	public void setPeriodoDeValidez(int periodoDeValidez) {
	    this.periodoDeValidez = periodoDeValidez;
	}

	public void setDescuento(float descuento) {
	    this.descuento = descuento;
	}

	public void setFecha(LocalDate fecha) {
	    this.fecha = fecha;
	}

	public void setCostoAsociado(float costoAsociado) {
	    this.costoAsociado = costoAsociado;
	}

	public void setPaquetesTipos(DTPaqueteTipo[] paquetesTipos) {
	    this.paquetesTipos = paquetesTipos;
	}

	public void setFechaDeAlta(LocalDate fechaDeAlta) {
	    this.fechaDeAlta = fechaDeAlta;
	}

	public void setImagen(byte[] imagen) {
	    this.imagen = imagen;
	}

	
}
