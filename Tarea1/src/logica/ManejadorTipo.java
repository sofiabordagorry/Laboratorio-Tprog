package logica;
import java.util.Map;
import java.util.HashMap;

public class ManejadorTipo {
	private static ManejadorTipo instancia;
<<<<<<< HEAD
	//private Map<String, Tipo> mapTipos;//Hay que cambiarle el nombre
	private Map<String, Paquete> mapPaquetes;//a este tambien
	
	public ManejadorTipo() {
		mapTipos = new HashMap<>();
	}
	
	public ManejadorTipo getInstancia() {
=======
	private Map<String, Tipo> mapTipos;//Hay que cambiarle el nombre
	private Map<String, Paquete> mapPaquetes;//a este tambien
	
	public ManejadorTipo() {//CONSTRUCTOR
		mapTipos = new HashMap<>();
	}
	
	public ManejadorTipo getInstancia() {//OBTENER INSTANCIA 
>>>>>>> 82d1aafc7128267398ffa94bd861ab7110cf7946
		if(instancia == null) {
			instancia = new ManejadorTipo();
		}
		return instancia;
	}
	
	public void agregarTipo(Tipo t) {//AGREGAR UN TIPO A LA COLECCION
		mapTipos.put(t.getNombre(), t);
	}
	
<<<<<<< HEAD
=======
	public void agregarPaquete(Paquete p) {//AGREAGR UN PAQUETE A LA COLECCION
		mapPaquetes.put(p.getNombre(), p);
	}
	
>>>>>>> 82d1aafc7128267398ffa94bd861ab7110cf7946
	public Map<String, Tipo> getTipos(){//OBTENER COLEECION DE TIPOS
		return mapTipos;
	}
	
<<<<<<< HEAD
	public Map<String, Paqute> getPaquetes(){//OBTENER COLECCION DE PAQUETES
=======
	public Map<String, Paquete> getPaquetes(){//OBTENER COLECCION DE PAQUETES
>>>>>>> 82d1aafc7128267398ffa94bd861ab7110cf7946
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
	
<<<<<<< HEAD
	public boolean existeTipo(String nombre) {
		return mapTipo.containsKey(nombre);
=======
	public boolean existeTipo(String nombre) {//VERIFICAR EXISTENCIA DE TIPO
		return mapTipos.containsKey(nombre);
	}
	
	public boolean existePaquete(String nombre) {//VERIFICAR EXISTENCIA DE PAQUETE
		return mapPaquetes.containsKey(nombre);
	}
	
	public void eliminarPaquete(String nombre) {//ELIMINAR PAQUETE DE LA COLECCION
		mapPaquetes.remove(nombre);
	}
	
	public void eliminarTipo(String nombre) {//ELIMINAR TIPO DE LA COLECCION
		mapTipos.remove(nombre);
>>>>>>> 82d1aafc7128267398ffa94bd861ab7110cf7946
	}
	
}
