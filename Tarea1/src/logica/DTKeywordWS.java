package logica;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTKeywordWS {
	private DTKeyword[] keys;
	
	public DTKeywordWS() {
	}
	
	public DTKeywordWS(DTKeyword[] k) {
		this.keys = k;
	}
	
	public DTKeyword[] getDTKeyword() {
		return this.keys;
	}
	
	public void setDTKeyword(DTKeyword[] k) {
		this.keys = k;
	}
}
