package logica;

import java.util.List;
import java.util.ArrayList;

public class Paquete {
	private String nombre;
	private String descripcion;
	private int periodoDeValidez;
	private float descuento;
	private float costoAsociado;
	private List<PaqueteTipo> paquetesTipos;
	
	public Paquete(String nombre, String descripcion, int periodoDeValidez, float descuento, float costoAsociado) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.periodoDeValidez = periodoDeValidez;
		this.descuento = descuento;
		this.costoAsociado = costoAsociado;
		this.paquetesTipos = new ArrayList<>();
	}
	
    // Getters
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
    
    public List<PaqueteTipo> getPaquetesTipos() {
        return this.paquetesTipos;
    }

    public void agregarPaqueteTipo(PaqueteTipo paqueteTipo) {
    	this.paquetesTipos.add(paqueteTipo);
    }
}
