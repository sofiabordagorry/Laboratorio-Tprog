package logica;

import excepciones.ExisteUnUsuarioYaRegistradoException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
	
	public boolean existeMail(String mail) {
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
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				String fechaDeNacimiento = post.getFechaDeNacimiento();
				LocalDate fechaNacimientoLocalDate = LocalDate.parse(fechaDeNacimiento, formatter);
				Postulante new_user = new Postulante(post.getNickname(), post.getNombre(), post.getApellido(), post.getCorreo(), fechaNacimientoLocalDate, post.getNacionalidad(), post.getContrasenia(), post.getImagen());
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
                LocalDate fechaNacimiento = postulantes[i].getFechaNacimiento();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String fechaNacimientoString = fechaNacimiento.format(formatter);
                dtpost[i] = new DTPostulante(postulante.getNickname(), postulante.getNombre(), postulante.getApellido(), postulante.getCorreo(), postulante.getContrasenia(), fechaNacimientoString, postulante.getNacionalidad(), null, null, postulante.getImage());
            }
            return dtpost; 
        }  else throw new PostulantesNoExistenException("No existen postulantes");
    }
    
    public  DTOfertaLaboral[] listarOfertasLaboralesVigentes(String empresa) throws EmpresaSinOfertasException{
    ManejadorUsuario musr = ManejadorUsuario.getInstancia();
        Empresa emp = musr.buscarEmpresa(empresa);
        List<DTOfertaLaboral> ofertas = emp.obtenerOfertasVigentesyConfirmadas();
      DTOfertaLaboral[] dtofertas;
        if (ofertas != null && ofertas.size() != 0) {
        	 return dtofertas = ofertas.toArray(new DTOfertaLaboral[ofertas.size()]);
        } else {

        	throw new EmpresaSinOfertasException("La empresa seleccionada no tiene ofertas vigentes y confirmadas");
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
    
    public void ingresarPostulacion(String CVReducido, String motivacion, LocalDate fecha, String empresa, String oferta, String postulante, String video) throws YaSePostuloException {
        ManejadorUsuario musr = ManejadorUsuario.getInstancia();
        Postulante pos = musr.buscarPostulante(postulante);
        Boolean existe =  pos.existePostulacion(oferta);
        Empresa emp = musr.buscarEmpresa(empresa);
        OfertaLaboral ofl = emp.buscarOferta(oferta);
        
        if (!existe) {
        	Postulacion postu = new Postulacion(fecha, CVReducido, motivacion, pos, ofl, video);
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

	public DTEmpresa buscarEmpresa(String nickname) {
		ManejadorUsuario musr = ManejadorUsuario.getInstancia();
		Empresa emp = musr.buscarEmpresa(nickname);
		if (emp != null)
			return emp.getDataEmpresa();
		else 
			return new DTEmpresa();
	}
	
	public DTUsuario buscarDTUsuario(String nickname) {
		Empresa usr = ManejadorUsuario.getInstancia().buscarEmpresa(nickname);
		if (usr != null) {
			DTEmpresa emp = usr.getDataEmpresa();
			return emp;
		}
		Postulante usrp = ManejadorUsuario.getInstancia().buscarPostulante(nickname);
		if (usrp != null) {
			DTPostulante dtp = usrp.getDataPostulante();
			return dtp;
		}
		return new DTUsuario();
		//return new DTUsuario("h", "sadf", "asdf", "asdf", "cd",new byte[0]);
	}
	
	public DTUsuario buscarDTUsuarioPorMail(String nickname) {
		ManejadorUsuario musr = ManejadorUsuario.getInstancia();
		Usuario usr = musr.buscarUsuarioPorMail(nickname);
		if (usr != null)
			return usr.getDataUsuario();
		else
			return new DTUsuario();
	}
	
	public boolean verificacionDePostulantePostulacion(String nicknameConsultado, String nombreOferta, String nombreEmp) {
    	ManejadorUsuario musr = ManejadorUsuario.getInstancia();
    	Postulante usr = musr.buscarPostulante(nicknameConsultado);
    	List<Postulacion> postulaciones = usr.getPostulaciones();
    	boolean correcto = false;
    	for(Postulacion post : postulaciones) {
    		if(post.getOfertaLaboral().getNombre().equals(nombreOferta) && 
    				post.getOfertaLaboral().getEmpresaCreadora().equals(nombreEmp))
    			correcto = true;
    	}
    	
    	return correcto;
    }
	
	public DTPostulante dataPostulante(String nickname) {
		ManejadorUsuario cusr = ManejadorUsuario.getInstancia();
		Postulante post = cusr.buscarPostulante(nickname);
		DTPostulante dtpost = post.getDataPostulante();
		return dtpost;
	}
	
	public boolean verificacionCompraPaq(String emp, String nombrePaq) {
		ManejadorUsuario cusr = ManejadorUsuario.getInstancia();
		Empresa empresa = cusr.buscarEmpresa(emp);
		return empresa.verificarCompra(nombrePaq);
	}
	

	public boolean estaSiguiendo(String seguidor, String seguido) {
		ManejadorUsuario musr = ManejadorUsuario.getInstancia();
		Usuario usr = musr.buscarUsuario(seguidor);
		return usr.sigue(seguido);
	}
	
	public void seguirUsuario(String seguidor, String seguido) {
		ManejadorUsuario musr = ManejadorUsuario.getInstancia();
		Usuario usr1 = musr.buscarUsuario(seguidor);
		Usuario usr2 = musr.buscarUsuario(seguido);
		usr1.seguirUsuario(usr2);
	}
	
	
	public void dejarSeguimiento(String seguidor, String seguido) {
		ManejadorUsuario musr = ManejadorUsuario.getInstancia();
		Usuario usr1 = musr.buscarUsuario(seguidor);
		Usuario usr2 = musr.buscarUsuario(seguido);
		usr1.dejarSeguir(usr2);
	}
	
	public DTPostulante crearDTPostulante(String nickname, String nom, String apellido, String correo, String contrasenia, String nacionalidad, 
			String fechaDeNacimiento, byte[] image) {
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");	
//		LocalDate fechaNacimientoLocalDate = LocalDate.parse(fechaDeNacimiento, formatter);
		DTPostulante dtpost = new DTPostulante(nickname, nom, apellido, correo, contrasenia, fechaDeNacimiento, nacionalidad, null, null, image);
		return dtpost;
	}
	
	public DTEmpresa crearDTEmpresa(String nickname, String nom, String apellido, String correo, String contrasenia, 
			String descripcion, String link, byte[] image) {
	DTEmpresa dtemp = new DTEmpresa(nickname, nom, apellido, correo, contrasenia, null, descripcion, link, null, image);
	return dtemp;
	}
	
	public String[] obtenerSeguidores(String nickname) {
		ManejadorUsuario musr = ManejadorUsuario.getInstancia();
		Usuario user = musr.buscarUsuario(nickname);
		List<Usuario> listSeguidores = user.getSeguidores();
		String[] seguidores = new String[listSeguidores.size()];
		int i = 0;
		for(Usuario u: listSeguidores) {
			seguidores[i] = u.getNickname();
			i++;
		}
		return seguidores;
	}
	
	public String[] obtenerSeguidos(String nickname) {
		ManejadorUsuario musr = ManejadorUsuario.getInstancia();
		Usuario user = musr.buscarUsuario(nickname);
		List<Usuario> listSeguidos = user.getUsuariosSeguidos();
		String[] seguidos = new String[listSeguidos.size()];
		int i = 0;
		for(Usuario u: listSeguidos) {
			seguidos[i] = u.getNickname();
			i++;
		}
		return seguidos;
	}

	public boolean esFavorito(String nickname, String oferta) {
		ManejadorUsuario musr = ManejadorUsuario.getInstancia();
		ManejadorOfertaLaboral mol = ManejadorOfertaLaboral.getInstance();
		OfertaLaboral ofta = mol.buscarOfertaLaboral(oferta);
		Postulante pos = musr.buscarPostulante(nickname);
		return pos.esFavorito(ofta);
	}
	
	public void quitarOfertaFav(String nickname, String oferta) {
		ManejadorUsuario musr = ManejadorUsuario.getInstancia();
		ManejadorOfertaLaboral mol = ManejadorOfertaLaboral.getInstance();
		OfertaLaboral ofta = mol.buscarOfertaLaboral(oferta);
		Postulante pos = musr.buscarPostulante(nickname);
		pos.quitarOfertaFav(ofta);
		ofta.quitarFavorito();
	}
	
	public void agregarOfertaFav(String nickname, String oferta) {
		ManejadorUsuario musr = ManejadorUsuario.getInstancia();
		ManejadorOfertaLaboral mol = ManejadorOfertaLaboral.getInstance();
		OfertaLaboral ofta = mol.buscarOfertaLaboral(oferta);
		Postulante pos = musr.buscarPostulante(nickname);
		pos.agregarOfertaFav(ofta);
		ofta.agregarFavorito();
	}

	public boolean existePostulante(String nickname) {
    	ManejadorUsuario murs = ManejadorUsuario.getInstancia();
    	return murs.existePostulante(nickname);
	}
	
	public boolean existeEmpresa(String nickname) {
    	ManejadorUsuario murs = ManejadorUsuario.getInstancia();
    	return murs.existeEmpresa(nickname);
	}

 }
