package logica;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

public class Paquete {
	private String nombre;
	private String descripcion;
	private int periodoDeValidez;
	private float descuento;
	private float costoAsociado;
	private List<PaqueteTipo> paquetesTipos;
	private LocalDate fechaDeAlta;
	private byte[] imagen;
	private Compra compra;
	
	public Paquete(String nombre, String descripcion, int periodoDeValidez, float descuento, float costoAsociado, LocalDate fechaDeAlta) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.periodoDeValidez = periodoDeValidez;
		this.descuento = descuento;
		this.costoAsociado = costoAsociado;
		this.paquetesTipos = new ArrayList<>();
		this.fechaDeAlta = fechaDeAlta;
	}
	
	public Paquete(String nombre, String descripcion, int periodoDeValidez, float descuento, float costoAsociado, LocalDate fechaDeAlta, byte[] image) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.periodoDeValidez = periodoDeValidez;
		this.descuento = descuento;
		this.costoAsociado = costoAsociado;
		this.paquetesTipos = new ArrayList<>();
		this.fechaDeAlta = fechaDeAlta;
		this.setImagen(image);
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
    	for (int i = 0; i < this.paquetesTipos.size(); i++) {
			paq = paqarr[i];
			dtpaq[i] = paq.getDTPaqueteTipo();
		}
    	return new DTPaquete(this.getNombre(), this.getDescripcion(), this.getPeriodoDeValidez(), 
				this.getDescuento(), this.getCostoAsociado(), dtpaq, this.fechaDeAlta);
    } 	

    public boolean agregarTipo(int cantidad, Tipo tip) {
        List<PaqueteTipo> paqT = this.getPaquetesTipos();
        PaqueteTipo paqti;
        for (int i = 0; i < paqT.size(); i++) {
            paqti = paqT.get(i);
            if (paqti.getTipo().getNombre() == tip.getNombre())
                return false;
        }
        //No hay link entre Paquete y Tipo
        paqti = new PaqueteTipo(cantidad, tip);
        this.agregarPaqueteTipo(paqti);
        //arrglo del costoAsociado
        float costo = (tip.getCosto() * cantidad) *((100-this.descuento)/100);
        sumarACosto(costo);
        return true;
    }
    
    public void sumarACosto(float cost) {
        this.costoAsociado = this.costoAsociado + cost;
    }

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}
}
