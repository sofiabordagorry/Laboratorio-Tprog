package logica;

import java.time.LocalDate;
import java.util.Map;

public class DTCompra {
	private LocalDate fechaCompra;
	private LocalDate fechaVencimiento;
	private DTPaquete paqComprado;
	private Map<String, DTOfertaLaboral> ofertasLaborales;
	
	public DTCompra() {
		fechaCompra = null;
	}
	
	public DTCompra(LocalDate fComp, LocalDate fVenc, DTPaquete paq, Map<String, DTOfertaLaboral> ofLab) {
		fechaCompra = fComp;
		fechaVencimiento = fVenc;
		paqComprado = paq;
		ofertasLaborales = ofLab;
	}
	
	public LocalDate getFechaCompra() {
		return fechaCompra;
	}
	
	public LocalDate getFechaVencimiento() {
		return fechaVencimiento;
	}
	
	public DTPaquete getPaqueteComprado() {
		return paqComprado;
	}
	
	public Map<String, DTOfertaLaboral> getOfertasLaboralesCompradas(){
		return ofertasLaborales;
	}
}
