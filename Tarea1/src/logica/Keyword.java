package logica;

public class Keyword {
	
	private String nombre;
	
	public Keyword(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public DTKeyword getDataKeyWord() {
		DTKeyword dtKW = new DTKeyword(this.getNombre());
		return dtKW;
	}
}
