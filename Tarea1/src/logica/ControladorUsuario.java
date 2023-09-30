package logica;



import excepciones.ExisteUnUsuarioYaRegistradoException;

import java.time.LocalDate;
import java.util.ArrayList;

import excepciones.EmpresaSinOfertasException;
import excepciones.EmpresasNoExistenException;
import excepciones.PostulantesNoExistenException;
import excepciones.YaSePostuloException;

import java.util.Map;

import excepciones.NoExistenEmpresasOfertasLaboralesException;
import excepciones.OfertasLaboralesNoExistenException;
import excepciones.UsuariosNoExistenException;


public class ControladorUsuario implements IUsuario {
	public ControladorUsuario() {
	}
	
	private boolean existeMail(String mail) {
		ManejadorUsuario m = ManejadorUsuario.getInstancia();
		Postulante[] pos = m.getPostulantes();
		Empresa[] emp = m.getEmpresas();
		boolean existemail = false;
		if(pos != null) {
			for (int i = 0; i < pos.length;i++) {
				existemail = existemail || (pos[i].getCorreo().equals(mail));
			}
		}
		if(emp != null) {
			if(existemail) {
				return existemail;
			}else{
				for (int j = 0; j < emp.length;j++) {
					existemail = existemail || (emp[j].getCorreo().equals(mail));
				}
			}
		}
		return existemail;
	}
	
	public void ingresarDatosPostulante(DTPostulante post) throws ExisteUnUsuarioYaRegistradoException{
		ManejadorUsuario m = ManejadorUsuario.getInstancia();
		Usuario user = m.buscarUsuario(post.getNickname());
		if (user == null) {
			if(this.existeMail(post.getCorreo()) == false) {
				Postulante new_user = new Postulante(post.getNickname(), post.getNombre(), post.getApellido(), post.getCorreo(), post.getFechaDeNacimiento(), post.getNacionalidad());
				m.agregarPostulante(new_user);
			} else {
				throw new ExisteUnUsuarioYaRegistradoException("Existe un usuario ya registrado con ese mail");
			}
		} else {
			throw new ExisteUnUsuarioYaRegistradoException("Existe un usuario ya registrado con ese nickname");
		}
	}
	
	public void ingresarDatosEmpresa(DTEmpresa emp) throws ExisteUnUsuarioYaRegistradoException {
		ManejadorUsuario m = ManejadorUsuario.getInstancia();
		Usuario user = m.buscarUsuario(emp.getNickname());
		if (user == null) {
			if(this.existeMail(emp.getCorreo()) == false) {
				Empresa new_user = new Empresa(emp.getNickname(), emp.getNombre(), emp.getApellido(), emp.getCorreo(), emp.getDescripcion(), emp.getLink());
				m.agregarEmpresa(new_user);
			} else {
				throw new ExisteUnUsuarioYaRegistradoException("Existe un usuario ya registrado con ese mail");
			}
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
    
    public DTPostulante[] listarPostulantes() throws PostulantesNoExistenException{
        ManejadorUsuario mu = ManejadorUsuario.getInstancia();
        Postulante[] postulantes = mu.getPostulantes();

        if (postulantes != null) {
            DTPostulante [] dp = new DTPostulante[postulantes.length];
            Postulante postulante;

            for (int i = 0; i < postulantes.length; i++) {
                postulante = postulantes[i];
                dp[i] = new DTPostulante(postulante.getNickname(), postulante.getNombre(), postulante.getApellido(), postulante.getCorreo(), postulante.getFechaNacimiento(), postulante.getNacionalidad());
            }
            return dp; 
        }  else throw new PostulantesNoExistenException("No existen postulantes");
    }
    
    public  DTOfertaLaboral[] listarOfertasLaboralesVigentes(String empresa) throws EmpresaSinOfertasException{
    ManejadorUsuario mu = ManejadorUsuario.getInstancia();
        Empresa emp = mu.buscarEmpresa(empresa);
        ArrayList<DTOfertaLaboral> ofertas = emp.obtenerOfertasVigentes();
      DTOfertaLaboral[] dtofertas;
        if (ofertas != null) {
        	 dtofertas = ofertas.toArray(new DTOfertaLaboral[ofertas.size()]);
        } else {
        	dtofertas = null;
        }
        if (dtofertas != null) { return  dtofertas;}
        else { throw new EmpresaSinOfertasException("La empresa seleccionada no tiene ofertas vigentes");}
    }
    
    
    public  DTOfertaLaboral[] listarOfertasLaboralesIngresadas(String empresa) throws EmpresaSinOfertasException{
        ManejadorUsuario mu = ManejadorUsuario.getInstancia();
            Empresa emp = mu.buscarEmpresa(empresa);
            DTOfertaLaboral[] ofertas = emp.obtenerOfertasIngresadas();
            if (ofertas != null){ return  ofertas;}
            else { throw new EmpresaSinOfertasException("La empresa seleccionada no tiene ofertas ingresadas");}
        }
    
    public void ingresarPostulacion(String CVReducido, String motivacion, LocalDate fecha, String empresa, String oferta, String postulante) throws YaSePostuloException {
        ManejadorUsuario mu = ManejadorUsuario.getInstancia();
        Postulante pos = mu.buscarPostulante(postulante);
        Boolean existe =  pos.existePostulacion(oferta);
        Empresa emp = mu.buscarEmpresa(empresa);
        OfertaLaboral of = emp.buscarOferta(oferta);
        
        if (!existe) {
        	Postulacion postu = new Postulacion(fecha, CVReducido, motivacion, pos, of);
        	pos.agregarPostulacion(postu);
        	of.agregarPostulacion(postu);	
        }
        else  { throw new YaSePostuloException("El postulante seleccionado ya se ha postulado a esta oferta laboral");}
        		
    }

	public DTUsuario[] listarUsuarios() throws UsuariosNoExistenException{
		 ManejadorUsuario iMU =  ManejadorUsuario.getInstancia();
		 Empresa[] empresas = iMU.getEmpresas();
		 Postulante[] postulantes = iMU.getPostulantes();
		 if(empresas != null || postulantes != null) {
			 DTUsuario[] arrUsu;
			 if(empresas == null) {
				 arrUsu = new DTUsuario[postulantes.length];
				 for(int j = 0; j < postulantes.length; j++) {
					 arrUsu[j] = postulantes[j].getDataPostulante();
				 }
			 }else if(postulantes == null) {
				 arrUsu = new DTUsuario[empresas.length];
				 for(int i = 0; i < empresas.length; i++) {
					 arrUsu[i] = empresas[i].getDataEmpresa();
				 }
			 }else {
				 arrUsu = new DTUsuario[postulantes.length + empresas.length];
				 for(int i = 0; i < empresas.length; i++) {
					 arrUsu[i] = empresas[i].getDataEmpresa();
				 }
				 
				 for(int j = 0; j < postulantes.length; j++) {
					 arrUsu[empresas.length + j] = postulantes[j].getDataPostulante();
				 }
			 }
			 return arrUsu;
		 }else{
			 throw new UsuariosNoExistenException("No existen Usuarios registradas");
		 }
	}

	public DTUsuario mostrarInformacionUsuario(String nickname) {
		ManejadorUsuario iMU = ManejadorUsuario.getInstancia();
		Usuario u = iMU.buscarUsuario(nickname);
		DTUsuario dtU = u.getDataUsuario(); 
		return dtU;
	}
	
	public DTEmpresa[] listarEmpresas() throws EmpresasNoExistenException,NoExistenEmpresasOfertasLaboralesException {

		 ManejadorUsuario iMU =  ManejadorUsuario.getInstancia();
		 Empresa[] empresas = iMU.getEmpresas();
		 boolean hayOL = false;

		 if (empresas != null ) {
			 for(int i = 0; i < empresas.length; i++) {
				 hayOL = hayOL || (empresas[i].getOfertasLaborales().size() != 0);
			 }
			 if(hayOL) {
				 DTEmpresa[] arrEmp = new DTEmpresa[empresas.length];
				 for(int i = 0; i < empresas.length; i++) {
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

		Empresa e = iMU.buscarEmpresa(nickEmpresa);
		Map<String, OfertaLaboral> ofLab = e.getOfertasLaborales();
		if(ofLab.size() != 0) {
			DTOfertaLaboral[] ofertasRes = new DTOfertaLaboral[ofLab.size()];
			int j = 0;
			for(Map.Entry<String, OfertaLaboral> entry : ofLab.entrySet()) {
				ofertasRes[j] = entry.getValue().getDataOfertaLaboral();
				j++;
			}
			return ofertasRes;
		} else {
			throw new OfertasLaboralesNoExistenException("La empresa seleccionada no tiene Ofertas Laborales");
		}
	}

	public void modificarEmpresa(String nick, String nom, String ap, String desc, String l) {
		ManejadorUsuario iMU = ManejadorUsuario.getInstancia();
		Empresa e = iMU.buscarEmpresa(nick);
		e.modificarDatos(nom, ap, desc, l);
	}

	public void modificarPostulante(String nick, String nom, String ap, LocalDate f, String nac) {
		ManejadorUsuario iMU = ManejadorUsuario.getInstancia();
		Postulante e = iMU.buscarPostulante(nick);
		e.modificarDatos(nom, ap, f, nac);
	}
}
