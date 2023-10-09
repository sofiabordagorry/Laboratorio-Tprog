package logica;



import excepciones.ExisteUnUsuarioYaRegistradoException;

import java.time.LocalDate;
import java.util.List;

import excepciones.EmpresaSinOfertasException;
import excepciones.EmpresasNoExistenException;
import excepciones.PostulantesNoExistenException;
import excepciones.UsuarioSinPostulacionesException;
import excepciones.YaSePostuloException;

import java.util.Map;

import excepciones.NoExistenEmpresasOfertasLaboralesException;
import excepciones.OfertasLaboralesNoExistenException;
import excepciones.UsuariosNoExistenException;


public class ControladorUsuario implements IUsuario {
	public ControladorUsuario() {
	}
	
	private boolean existeMail(String mail) {
		ManejadorUsuario musr = ManejadorUsuario.getInstancia();
		Postulante[] pos = musr.getPostulantes();
		Empresa[] emp = musr.getEmpresas();
		boolean existemail = false;
		if (pos != null) {
			for (int i = 0; i < pos.length; i++) {
				existemail = existemail || (pos[i].getCorreo().equals(mail));
			}
		}
		if (emp != null) {
			if (existemail) {
				return existemail;
			}else {
				for (int j = 0; j < emp.length; j++) {
					existemail = existemail || (emp[j].getCorreo().equals(mail));
				}
			}
		}
		return existemail;
	}
	
	public void ingresarDatosPostulante(DTPostulante post) throws ExisteUnUsuarioYaRegistradoException{
		ManejadorUsuario musr = ManejadorUsuario.getInstancia();
		Usuario user = musr.buscarUsuario(post.getNickname());
		if (user == null) {
			if (this.existeMail(post.getCorreo()) == false) {
				Postulante new_user = new Postulante(post.getNickname(), post.getNombre(), post.getApellido(), post.getCorreo(), post.getFechaDeNacimiento(), post.getNacionalidad(), post.getContrasenia(), post.getImagen());
				musr.agregarPostulante(new_user);
			} else {
				throw new ExisteUnUsuarioYaRegistradoException("Existe un usuario ya registrado con ese mail");
			}
		} else {
			throw new ExisteUnUsuarioYaRegistradoException("Existe un usuario ya registrado con ese nickname");
		}
	}
	
	public void ingresarDatosEmpresa(DTEmpresa emp) throws ExisteUnUsuarioYaRegistradoException {
		ManejadorUsuario musr = ManejadorUsuario.getInstancia();
		Usuario user = musr.buscarUsuario(emp.getNickname());
		if (user == null) {
			if (this.existeMail(emp.getCorreo()) == false) {
				Empresa new_user = new Empresa(emp.getNickname(), emp.getNombre(), emp.getApellido(), emp.getCorreo(), emp.getDescripcion(), emp.getLink(), emp.getContrasenia(), emp.getImagen());
				musr.agregarEmpresa(new_user);
			} else {
				throw new ExisteUnUsuarioYaRegistradoException("Existe un usuario ya registrado con ese mail");
			}
		} else {
			throw new ExisteUnUsuarioYaRegistradoException("Existe un usuario ya registrado con ese nickname");
		}
	}
	
	public DTEmpresa[] listarEmpresasAOL() throws EmpresasNoExistenException {
		ManejadorUsuario musr = ManejadorUsuario.getInstancia();
		Empresa[] empresas = musr.getEmpresas();
		
		if (empresas != null) {
			DTEmpresa[] dtemp = new DTEmpresa[empresas.length];
			Empresa empresa;
			 
			for (int i = 0; i < empresas.length; i++) {
				empresa = empresas[i];
				dtemp[i] = empresa.getDataEmpresaALO();
			}
			
			return dtemp;
		} else 
			throw new EmpresasNoExistenException("No existen Empresas registradas");
	}
    
    public DTPostulante[] listarPostulantes() throws PostulantesNoExistenException{
        ManejadorUsuario musr = ManejadorUsuario.getInstancia();
        Postulante[] postulantes = musr.getPostulantes();

        if (postulantes != null) {
            DTPostulante [] dtpost = new DTPostulante[postulantes.length];
            Postulante postulante;

            for (int i = 0; i < postulantes.length; i++) {
                postulante = postulantes[i];
                dtpost[i] = new DTPostulante(postulante.getNickname(), postulante.getNombre(), postulante.getApellido(), postulante.getCorreo(), postulante.getFechaNacimiento(), postulante.getNacionalidad());
            }
            return dtpost; 
        }  else throw new PostulantesNoExistenException("No existen postulantes");
    }
    
    public  DTOfertaLaboral[] listarOfertasLaboralesVigentes(String empresa) throws EmpresaSinOfertasException{
    ManejadorUsuario musr = ManejadorUsuario.getInstancia();
        Empresa emp = musr.buscarEmpresa(empresa);
        List<DTOfertaLaboral> ofertas = emp.obtenerOfertasVigentes();
      DTOfertaLaboral[] dtofertas;
        if (ofertas != null) {
        	 dtofertas = ofertas.toArray(new DTOfertaLaboral[ofertas.size()]);
        } else {
        	dtofertas = null;
        }
        if (dtofertas != null) { 
        	return  dtofertas;
        }
        else {
        	throw new EmpresaSinOfertasException("La empresa seleccionada no tiene ofertas vigentes");
        	}
    }
    
   
    public  DTOfertaLaboral[] listarOfertasLaboralesIngresadas(String empresa) throws EmpresaSinOfertasException{
        ManejadorUsuario musr = ManejadorUsuario.getInstancia();
            Empresa emp = musr.buscarEmpresa(empresa);
            DTOfertaLaboral[] ofertas = emp.obtenerOfertasIngresadas();
            if (ofertas != null){
            	return  ofertas;
            	}
            else {
            	throw new EmpresaSinOfertasException("La empresa seleccionada no tiene ofertas ingresadas");
            	}
        }
    
    public void ingresarPostulacion(String CVReducido, String motivacion, LocalDate fecha, String empresa, String oferta, String postulante) throws YaSePostuloException {
        ManejadorUsuario musr = ManejadorUsuario.getInstancia();
        Postulante pos = musr.buscarPostulante(postulante);
        Boolean existe =  pos.existePostulacion(oferta);
        Empresa emp = musr.buscarEmpresa(empresa);
        OfertaLaboral ofl = emp.buscarOferta(oferta);
        
        if (!existe) {
        	Postulacion postu = new Postulacion(fecha, CVReducido, motivacion, pos, ofl);
        	pos.agregarPostulacion(postu);
        	ofl.agregarPostulacion(postu);	
        }
        else  {
        	throw new YaSePostuloException("El postulante seleccionado ya se ha postulado a esta oferta laboral");
        	}
        		
    }

	public DTUsuario[] listarUsuarios() throws UsuariosNoExistenException{
		 ManejadorUsuario iMU =  ManejadorUsuario.getInstancia();
		 Empresa[] empresas = iMU.getEmpresas();
		 Postulante[] postulantes = iMU.getPostulantes();
		 if (empresas != null || postulantes != null) {
			 DTUsuario[] arrUsu;
			 if (empresas == null) {
				 arrUsu = new DTUsuario[postulantes.length];
				 for (int j = 0; j < postulantes.length; j++) {
					 arrUsu[j] = postulantes[j].getDataPostulante();
				 }
			 }else if (postulantes == null) {
				 arrUsu = new DTUsuario[empresas.length];
				 for (int i = 0; i < empresas.length; i++) {
					 arrUsu[i] = empresas[i].getDataEmpresa();
				 }
			 }else {
				 arrUsu = new DTUsuario[postulantes.length + empresas.length];
				 for (int i = 0; i < empresas.length; i++) {
					 arrUsu[i] = empresas[i].getDataEmpresa();
				 }
				 
				 for (int j = 0; j < postulantes.length; j++) {
					 arrUsu[empresas.length + j] = postulantes[j].getDataPostulante();
				 }
			 }
			 return arrUsu;
		 }else {
			 throw new UsuariosNoExistenException("No existen Usuarios registradas");
		 }
	}

	public DTUsuario mostrarInformacionUsuario(String nickname) {
		ManejadorUsuario iMU = ManejadorUsuario.getInstancia();
		Usuario usr = iMU.buscarUsuario(nickname);
		DTUsuario dtU = usr.getDataUsuario(); 
		return dtU;
	}
	
	public DTEmpresa[] listarEmpresas() throws EmpresasNoExistenException, NoExistenEmpresasOfertasLaboralesException {

		 ManejadorUsuario iMU =  ManejadorUsuario.getInstancia();
		 Empresa[] empresas = iMU.getEmpresas();
		 boolean hayOL = false;

		 if (empresas != null ) {
			 for (int i = 0; i < empresas.length; i++) {
				 hayOL = hayOL || empresas[i].getOfertasLaborales().size() != 0;
			 }
			 if (hayOL) {
				 DTEmpresa[] arrEmp = new DTEmpresa[empresas.length];
				 for (int i = 0; i < empresas.length; i++) {
					 arrEmp[i] = empresas[i].getDataEmpresa();
				 }
				return arrEmp;
			 }else {
				 throw new NoExistenEmpresasOfertasLaboralesException("No hay empresas con Ofertas Laborales");
			 }
		 } else {
			 throw new EmpresasNoExistenException("No existen Empresas registradas");
		 }

	}

	public DTOfertaLaboral[] listarOfertasLaborales(String nickEmpresa) throws OfertasLaboralesNoExistenException {
		ManejadorUsuario iMU = ManejadorUsuario.getInstancia();

		Empresa emp = iMU.buscarEmpresa(nickEmpresa);
		Map<String, OfertaLaboral> ofLab = emp.getOfertasLaborales();
		if (ofLab.size() != 0) {
			DTOfertaLaboral[] ofertasRes = new DTOfertaLaboral[ofLab.size()];
			int iter = 0;
			for (Map.Entry<String, OfertaLaboral> entry : ofLab.entrySet()) {
				ofertasRes[iter] = entry.getValue().getDataOfertaLaboral();
				iter++;
			}
			return ofertasRes;
		} else {
			throw new OfertasLaboralesNoExistenException("La empresa seleccionada no tiene Ofertas Laborales");
		}
	}

	public void modificarEmpresa(String nick, String nom, String ape, String desc, String link) {
		ManejadorUsuario iMU = ManejadorUsuario.getInstancia();
		Empresa emp = iMU.buscarEmpresa(nick);
		emp.modificarDatos(nom, ape, desc, link);
	}

	public void modificarPostulante(String nick, String nom, String ape, LocalDate fecha, String nac) {
		ManejadorUsuario iMU = ManejadorUsuario.getInstancia();
		Postulante emp = iMU.buscarPostulante(nick);
		emp.modificarDatos(nom, ape, fecha, nac);
	}

	public DTOfertaLaboral[] listarOfertasPostulado(String nickname) throws UsuarioSinPostulacionesException {
		ManejadorUsuario musr = ManejadorUsuario.getInstancia();
		Postulante usr = musr.buscarPostulante(nickname);
		List<Postulacion> postulaciones = usr.getPostulaciones();
		if (postulaciones != null) {
			DTOfertaLaboral[] ofertasPostulado = new DTOfertaLaboral[postulaciones.size()];
			int iter = 0;
			for (Postulacion post : postulaciones) {
				ofertasPostulado[iter] = post.getOfertaLaboral().getDataOfertaLaboral();
				iter++;
			}
			
			return ofertasPostulado;
		} else {
			throw new UsuarioSinPostulacionesException("No existen postulaciones."); 
		}
	}
}
