package logica;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTTipoWS {
	private DTTipo[] tipos;
	
	public DTTipoWS() {
	}
	
	public DTTipoWS(DTTipo[] tipos) {
		this.tipos = tipos;
	}
	
	public DTTipo[] getDTTipo() {
		return this.tipos;
	}
	
	public void setDTTipo(DTTipo[] tipos) {
		this.tipos = tipos;
	}
}