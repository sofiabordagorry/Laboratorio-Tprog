package logica;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class Compra {
	private LocalDate fechaCompra;
	private LocalDate fechaVencimiento;
	private Paquete paqComprado;
	private Empresa empresa;
	private List<CompraTipo> comprasTipos;
	private Map<String, OfertaLaboral> ofertas;
	
	public Compra() {
		fechaCompra = null;
		fechaVencimiento = null;
		paqComprado = null;
		empresa= null;
		comprasTipos = null;
	}

	public Compra(LocalDate fComp, LocalDate fVenc, Paquete paq, Empresa empre, List<CompraTipo> comprasTipou) {
		fechaCompra = fComp;
		fechaVencimiento = fVenc;
		paqComprado = paq;
		empresa=empre;
		comprasTipos=comprasTipou;
	}
	
	public void agregarOferta(OfertaLaboral Oferta) {
		if(this.ofertas == null) {
			this.ofertas = new HashMap<>();
		}
		this.ofertas.put(Oferta.getNombre(), Oferta);
	}
	
	public LocalDate getFechaCompra() {
		return fechaCompra;
	}
	
	public LocalDate getFechaVencimiento() {
		return fechaVencimiento;
	}
	
	public Paquete getPaqueteComprado() {
		return paqComprado;
	}

	public Empresa getEmpresa() {
		return empresa;
	}
	
	
	public List<CompraTipo> getCompraTipo(){
		return comprasTipos;
	}
	
	public Map<String, OfertaLaboral> getOfertas(){
		return this.ofertas;
	}

	public DTCompra getDataCompra() {
		DTPaquete paqueteCompra = this.paqComprado.getDataPaquete();
		LocalDate fechaCompra = this.fechaCompra;
		LocalDate fechaVencimiento = this.fechaVencimiento;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String fechaCompraString = fechaCompra.format(formatter);
		String fechaVencimientoString = fechaVencimiento.format(formatter);
		DTCompra compra = new DTCompra(fechaCompraString, fechaVencimientoString, paqueteCompra, null);
		return compra;
	}
}
