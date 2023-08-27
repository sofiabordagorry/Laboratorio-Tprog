package logica;

import java.util.Map;
import java.util.Collection;
import java.util.HashMap;

public class ManejadorTipo {
	private static ManejadorTipo instancia = null;

	private Map<String, Tipo> mapTipos;//Hay que cambiarle el nombre
	private Map<String, Paquete> mapPaquetes;//a este tambien
	
	private ManejadorTipo() {//CONSTRUCTOR
		mapTipos = new HashMap<>();
		mapPaquetes = new HashMap<>();
	}
	
	public static ManejadorTipo getInstancia() {//OBTENER INSTANCIA 
		if(instancia == null) {
			instancia = new ManejadorTipo();
		}
		return instancia;
	}
	
	public void agregarTipo(Tipo t) {//AGREGAR UN TIPO A LA COLECCION
		mapTipos.put(t.getNombre(), t);
	}
	
	public void agregarPaquete(Paquete p) {//AGREAGR UN PAQUETE A LA COLECCION
		mapPaquetes.put(p.getNombre(), p);
	}
	
	public Tipo[] getTipos(){//OBTENER COLEECION DE TIPOS
		if (this.mapTipos.isEmpty())
            return null;
        else {
            Collection<Tipo> tips = this.mapTipos.values();
            Object[] o = tips.toArray();
            Tipo[] tipos = new Tipo[o.length];
            for (int i = 0; i < o.length; i++) {
                tipos[i] = (Tipo) o[i];
            }

            return tipos;
        }
	}
	
	public Map<String, Tipo> getMapTipo(){
		return this.mapTipos;
	}
	
	public Map<String, Paquete> getMapPaquete(){
		return this.mapPaquetes;
	}
	
	public Paquete[] getPaquetes(){//OBTENER COLECCION DE PAQUETES
		if (this.mapPaquetes.isEmpty())
            return null;
        else {
            Collection<Paquete> paquetes = this.mapPaquetes.values();
            Object[] o = paquetes.toArray();
            Paquete[] paq = new Paquete[o.length];
            for (int i = 0; i < o.length; i++) {
                paq[i] = (Paquete) o[i];
            }

            return paq;
        }
	}
	
	public Tipo buscarTipo(String tipo) {//BUSCAR TIPO
		Tipo t = mapTipos.get(tipo);
		return t;
	}
	
	public Paquete buscarPaquete(String paquete) {//BUSCAR PAQUETE
		Paquete p = mapPaquetes.get(paquete);
		return p;
	}
	
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
	}
	
	public void borrarTipos() {
		this.mapTipos.clear();
	}
	
	public void borrarPaquetes() {
		this.mapPaquetes.clear();
	}
	
}
