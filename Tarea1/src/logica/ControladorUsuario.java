package logica;


import excepciones.ExisteUnUsuarioYaRegistradoException;
import excepciones.EmpresasNoExistenException;

public class ControladorUsuario implements IUsuario {
	public ControladorUsuario() {
	}
	
	public void ingresarDatosPostulante(DTPostulante post) throws ExisteUnUsuarioYaRegistradoException {
		ManejadorUsuario m = ManejadorUsuario.getInstancia();
		Usuario user = m.buscarUsuario(post.getNickname());
		if (user == null) {
			Postulante new_user = new Postulante(post.getNickname(), post.getNombre(), post.getApellido(), post.getCorreo(), post.getFechaDeNacimiento(), post.getNacionalidad());
			m.agregarPostulante(new_user);
		} else {
			throw new ExisteUnUsuarioYaRegistradoException("Existe un usuario ya registrado con ese nickname");
		}
	}
	
	public void ingresarDatosEmpresa(DTEmpresa emp) throws ExisteUnUsuarioYaRegistradoException {
		ManejadorUsuario m = ManejadorUsuario.getInstancia();
		Usuario user = m.buscarUsuario(emp.getNickname());
		if (user == null) {
			Empresa new_user = new Empresa(emp.getNickname(), emp.getNombre(), emp.getApellido(), emp.getCorreo(), emp.getNombreDeEmpresa(), emp.getDescripcion(), emp.getLink());
			m.agregarEmpresa(new_user);
		} else {
			throw new ExisteUnUsuarioYaRegistradoException("Existe un usuario ya registrado con ese nickname");
		}
	}
	
	public DTEmpresa[] listarEmpresasAOL() throws EmpresasNoExistenException {
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		Empresa[] empresas = mu.getEmpresas();
		
		if (empresas != null) {
			DTEmpresa[] de = new DTEmpresa[empresas.length];
			Empresa empresa;
			
			for(int i = 0; i < empresas.length; i++) {
				empresa = empresas[i];
				de[i] = empresa.getDataEmpresaALO();
			}
			
			return de;
		} else 
			throw new EmpresasNoExistenException("No existen Empresas registradas");
	}
	
}
