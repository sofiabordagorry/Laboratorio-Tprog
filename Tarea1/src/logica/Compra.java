package logica;

import java.time.LocalDate;
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
		Map<String, OfertaLaboral> ofertas = new HashMap<>();
	}

	public Compra(LocalDate fComp, LocalDate fVenc, Paquete paq, Empresa empre, List<CompraTipo> comprasTipou) {
		fechaCompra = fComp;
		fechaVencimiento = fVenc;
		paqComprado = paq;
		empresa=empre;
		comprasTipos=comprasTipou;
		Map<String, OfertaLaboral> ofertas = new HashMap<>();
	}
	
	public void agregarOferta(OfertaLaboral Oferta) {
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
}
