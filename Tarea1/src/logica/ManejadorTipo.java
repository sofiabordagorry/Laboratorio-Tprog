package logica;

import java.util.Map;
import java.util.Collection;
import java.util.HashMap;

public class ManejadorTipo {
	private static ManejadorTipo instancia = null;

	private Map<String, Tipo> mapTipos; //Hay que cambiarle el nombre
	private Map<String, Paquete> mapPaquetes; //a este tambien
	
	private ManejadorTipo() {//CONSTRUCTOR
		mapTipos = new HashMap<>();
		mapPaquetes = new HashMap<>();
	}
	
	public static ManejadorTipo getInstancia() {//OBTENER INSTANCIA 
		if (instancia == null) {
			instancia = new ManejadorTipo();
		}
		return instancia;
	}
	
	public void agregarTipo(Tipo tip) {//AGREGAR UN TIPO A LA COLECCION
		mapTipos.put(tip.getNombre(), tip);
	}
	
	public void agregarPaquete(Paquete paq) {//AGREAGR UN PAQUETE A LA COLECCION
		mapPaquetes.put(paq.getNombre(), paq);
	}
	
	public Tipo[] getTipos(){//OBTENER COLEECION DE TIPOS
		if (this.mapTipos.isEmpty())
            return null;
        else {
            Collection<Tipo> tips = this.mapTipos.values();
            Object[] obj = tips.toArray();
            Tipo[] tipos = new Tipo[obj.length];
            for (int i = 0; i < obj.length; i++) {
                tipos[i] = (Tipo) obj[i];
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
            Object[] obj = paquetes.toArray();
            Paquete[] paq = new Paquete[obj.length];
            for (int i = 0; i < obj.length; i++) {
                paq[i] = (Paquete) obj[i];
            }

            return paq;
        }
	}
	
	public Tipo buscarTipo(String tipo) {//BUSCAR TIPO
		Tipo tip = mapTipos.get(tipo);
		return tip;
	}
	
	public Paquete buscarPaquete(String paquete) {//BUSCAR PAQUETE
		Paquete paq = mapPaquetes.get(paquete);
		return paq;
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
	
	public void limpiar() {
		mapTipos.clear();
		mapPaquetes.clear();
	}

	public Map<String, Tipo> getMapTipos() {
		return mapTipos;
	}
	
}
