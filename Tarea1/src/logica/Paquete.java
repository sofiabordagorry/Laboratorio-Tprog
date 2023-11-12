package logica;

import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
	private List<Compra> compras = new ArrayList<>();
	
	public Paquete(String nombre, String descripcion, int periodoDeValidez, float descuento, float costoAsociado, LocalDate fechaDeAlta) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.periodoDeValidez = periodoDeValidez;
		this.descuento = descuento;
		this.costoAsociado = costoAsociado;
		this.paquetesTipos = new ArrayList<>();
		this.fechaDeAlta = fechaDeAlta;
		this.compras = new ArrayList<>();
	}
	
	public Paquete(String nombre, String descripcion, int periodoDeValidez, float descuento, float costoAsociado, LocalDate fechaDeAlta, byte[] image) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.periodoDeValidez = periodoDeValidez;
		this.descuento = descuento;
		this.costoAsociado = costoAsociado;
		this.paquetesTipos = new ArrayList<>();
		this.compras = new ArrayList<>();
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

    	List<PaqueteTipo> paquetes = this.paquetesTipos;
    	DTPaqueteTipo[] dtpaq = new DTPaqueteTipo[paquetes.size()];
    	for (int i = 0; i < paquetes.size(); i++) {
			dtpaq[i] = paquetes.get(i).getDTPaqueteTipo();
		}
    	
    	LocalDate fechaDeAlta = this.fechaDeAlta;
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    	String fechaDeAltaString = fechaDeAlta.format(formatter);
    	
    	return new DTPaquete(this.getNombre(), this.getDescripcion(), this.getPeriodoDeValidez(), 
				this.getDescuento(), this.getCostoAsociado(), dtpaq, fechaDeAltaString);
    } 	

    public boolean agregarTipo(int cantidad, Tipo tip) {
    	if (this.getCompra() != null && this.getCompra().size() > 0) {
    		return false;
    	} else {
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

	public List<Compra> getCompra() {
		return compras;
	}

	public void setCompra(List<Compra> compra) {
		this.compras = compra;
	}
	
	public void agregarCompra(Compra compra) {
		this.compras.add(compra);
	}
}
