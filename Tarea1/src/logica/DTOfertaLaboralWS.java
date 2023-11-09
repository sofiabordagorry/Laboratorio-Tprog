package logica;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTOfertaLaboralWS {
	private DTOfertaLaboral[] ofs;
	
	public DTOfertaLaboralWS() {
	}
	
	public DTOfertaLaboralWS(DTOfertaLaboral[] of) {
		this.ofs = of;
	}
	
	public DTOfertaLaboral[] getDTOfertaLaboral() {
		return this.ofs;
	}
	
	public void setDTOfertaLaboral(DTOfertaLaboral[] of) {
		this.ofs = of;
	}
}
