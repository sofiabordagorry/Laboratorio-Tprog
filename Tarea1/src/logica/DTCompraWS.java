package logica;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTCompraWS {
	private DTCompra[] compras;
	
	public DTCompraWS() {
	}
	
	public DTCompraWS(DTCompra[] c) {
		this.compras = c;
	}
	
	public DTCompra[] getDTCompras() {
		return this.compras;
	}
	
	public void setDTCompras(DTCompra[] c) {
		this.compras = c;
	}
}
