package logica;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;
import java.time.LocalDate;

public class Paquete {
	private String nombre;
	private String descripcion;
	private int periodoDeValidez;
	private float descuento;
//	private LocalDate fecha;
	private float costoAsociado;
	private List<PaqueteTipo> paquetesTipos;
	private LocalDate fechaDeAlta;
	
	public Paquete(String nombre, String descripcion, int periodoDeValidez, float descuento, float costoAsociado, LocalDate fechaDeAlta) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.periodoDeValidez = periodoDeValidez;
		this.descuento = descuento;
		this.costoAsociado = costoAsociado;
		this.paquetesTipos = new ArrayList<>();
		this.fechaDeAlta = fechaDeAlta;
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
    
//    public LocalDate getFecha() {
//   	return this.fecha;
 //   }
    
    public float getCostoAsociado() {
        return this.costoAsociado;
    }
    
    public List<PaqueteTipo> getPaquetesTipos() {
        return this.paquetesTipos;
    }
    
    public LocalDate getFechaDeAlta() {
    	return this.fechaDeAlta;
    }

    public void agregarPaqueteTipo(PaqueteTipo paqueteTipo) {
    	this.paquetesTipos.add(paqueteTipo);
    }
    

    public DTPaquete getDataPaquete() {
    	PaqueteTipo paq;
    	PaqueteTipo[] paqarr = this.paquetesTipos.toArray(new PaqueteTipo[0]);
    	DTPaqueteTipo[] dtpaq = new DTPaqueteTipo[this.paquetesTipos.size()];
    	for(int i = 0; i < this.paquetesTipos.size(); i++) {
			paq = paqarr[i];
			dtpaq[i] = paq.getDTPaqueteTipo();
		}
    	return new DTPaquete(this.getNombre(), this.getDescripcion(), this.getPeriodoDeValidez(), 
				this.getDescuento(), this.getCostoAsociado(), dtpaq, this.fechaDeAlta);
    } 	


    
    public boolean agregarTipo(int cantidad, Tipo t) {
    	List<PaqueteTipo> paqT = this.getPaquetesTipos();
    	PaqueteTipo pt;
    	for (int i = 0; i < paqT.size(); i++) {
    		pt = paqT.get(i);
    		if(pt.getTipo().getNombre() == t.getNombre())
    			return false;
    	}
    	//No hay link entre Paquete y Tipo
    	pt = new PaqueteTipo(cantidad, t);
    	this.agregarPaqueteTipo(pt);
    	return true;
    }
}
