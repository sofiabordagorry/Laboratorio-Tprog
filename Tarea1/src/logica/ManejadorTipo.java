package logica;
import java.util.Map;
import java.util.HashMap;

public class ManejadorTipo {
	private static ManejadorTipo instancia;
	//private Map<String, Tipo> mapTipos;//Hay que cambiarle el nombre
	private Map<String, Paquete> mapPaquetes;//a este tambien
	
	public ManejadorTipo() {
		mapTipos = new HashMap<>();
	}
	
	public ManejadorTipo getInstancia() {
		if(instancia == null) {
			instancia = new ManejadorTipo();
		}
		return instancia;
	}
	
	public void agregarTipo(Tipo t) {//AGREGAR UN TIPO A LA COLECCION
		mapTipos.put(t.getNombre(), t);
	}
	
	public Map<String, Tipo> getTipos(){//OBTENER COLEECION DE TIPOS
		return mapTipos;
	}
	
	public Map<String, Paqute> getPaquetes(){//OBTENER COLECCION DE PAQUETES
		return mapPaquetes;
	}
	
	public Tipo buscarTipo(String tipo) {//BUSCAR TIPO
		Tipo t = mapTipos.get(tipo);
		return t;
	}
	
	public Tipo buscarPaquete(String paquete) {//BUSCAR PAQUETE
		Tipo p = mapTipos.get(paquete);
		return p;
	}
	
	public boolean existeTipo(String nombre) {
		return mapTipo.containsKey(nombre);
	}
	
}
