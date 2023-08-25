package logica;
import java.util.Map;
import java.util.HashMap;

public class ManejadorUsuario {
	private static ManejadorUsuario instancia;
	private Map<String, Empresa> mapEmpresas;
	private Map<String, Postulante> mapPostulantes;
	
	private ManejadorUsuario() {//CONSTRUCTOR
		mapEmpresas = new HashMap<>();
		mapPostulantes = new HashMap<>();
	}
	
	public static ManejadorUsuario getInstancia() {//OBTENER INSTANCIA
		if(instancia == null) {
			instancia = new ManejadorUsuario();
		}
		return instancia;
	}
	
	public void agregarEmpresa(Empresa e) {//AGREAR UNA EMPRESA A LA COLECCION
		mapEmpresas.put(e.getNickname(), e);
	}
	
	public void agregarPostulante(Postulante p) {//AGREGAR UN POSTULANTE A LA COLECCION
		mapPostulantes.put(p.getNickname(), p);
	}
	
	public Map<String, Empresa> getEmpresas(){//OBTENER COLECCIONES DE EMPRESAS
		return mapEmpresas;
	}
	
	public Map<String, Postulante> getPostulantes(){//OBTENER COLECCION DE POSTULANTES
		return mapPostulantes;
	}
	
	public Usuario buscarUsuario(String nickname) {//BUSCAR UN USUARIO
		Usuario u = mapEmpresas.get(nickname);
		if(u != null) {
			return u;
		}
		u = mapPostulantes.get(nickname);
		return u;
	}
	
	public boolean existeEmpresa(String nickname) {//VERIFICAR EXISTENCIA DE EMPRESA
		return mapEmpresas.containsKey(nickname);
	}
	
	public void existePostulante(String nickname) {//VERIFICAR EXISTENCIA DE POSTULANTE
		mapPostulantes.remove(nickname);
	}
	
	public Postulante buscarPostulante(String nickname) {//BUSCAR POSTULANTE
		Postulante p = mapPostulantes.get(nickname);
		return p;
	}
	
	public Empresa buscarEmpresa(String nickname) {//BUSCAR EMPRESA
		Empresa e = mapEmpresas.get(nickname);
		return e;
	}
	
	public void eliminarEmpresa(String nickname) {//ELIMINAR EMPRESA DE LA COLECION
		mapEmpresas.remove(nickname);
	}
	
	public void eliminarPostulante(String nickname) {//ELIMINAR POSTULANTE DE LA COLECCCION
		mapPostulantes.remove(nickname);
	}
}
