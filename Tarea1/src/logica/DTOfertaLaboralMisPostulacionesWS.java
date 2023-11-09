package logica;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTOfertaLaboralMisPostulacionesWS {
	private DTOfertaLaboral[] ofertas;
	
	public DTOfertaLaboralMisPostulacionesWS() {}
	
	public DTOfertaLaboralMisPostulacionesWS(DTOfertaLaboral[] ofertas) {
		this.ofertas = ofertas;
	}
	
	public DTOfertaLaboral[] getOfertas() {
		return this.ofertas;
	}
	
	public void setOfertas(DTOfertaLaboral[] ofertas) {
		this.ofertas = ofertas;
	}
}
