package logica;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTCompra {
	private String fechaCompra;
	private String fechaVencimiento;
	private DTPaquete paqComprado;
	private List<DTOfertaLaboral> ofertasLaborales;
	
	public DTCompra() {
		fechaCompra = null;
	}
	
	// ---------------- Constructores ------------------
	
	public DTCompra(String fComp, String fVenc, DTPaquete paq, List<DTOfertaLaboral> ofLab) {
		fechaCompra = fComp;
		fechaVencimiento = fVenc;
		paqComprado = paq;
		ofertasLaborales = ofLab;
	}
	
	// ------------ Getters --------------
	
	public String getFechaCompra() {
		return fechaCompra;
	}
	
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}
	
	public DTPaquete getPaqueteComprado() {
		return paqComprado;
	}
	
	public List<DTOfertaLaboral> getOfertasLaboralesCompradas(){
		return ofertasLaborales;
	}
	
	// ------------ Setters --------------
	public void setFechaCompra(String fechaCompra) {
	    this.fechaCompra = fechaCompra;
	}

	public void setFechaVencimiento(String fechaVencimiento) {
	    this.fechaVencimiento = fechaVencimiento;
	}

	public void setPaqueteComprado(DTPaquete paqueteComprado) {
	    this.paqComprado = paqueteComprado;
	}

	public void setOfertasLaboralesCompradas(List<DTOfertaLaboral> ofertasLaborales) {
	    this.ofertasLaborales = ofertasLaborales;
	}
}
