/**
 * 
 */
package publicar;

import logica.*;

import excepciones.ExisteUnUsuarioYaRegistradoException;
import excepciones.NoExistenOfertasSeleccionarPostulanteException;
import excepciones.OfertasLaboralesNoExistenNingunaException;
import excepciones.UsuarioSinPostulacionesException;
import excepciones.NoHayTiposException;
import excepciones.UsuariosNoExistenException;
import excepciones.YaSePostuloException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;
import java.time.format.DateTimeFormatter;

import excepciones.NoHayPaquetesException;
import excepciones.OfertaLaboralRepetidaException;
import excepciones.PaqueteYaCompradoException;
import excepciones.TipoPubNoExistenException;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class WebServices {

    private Endpoint endpoint = null;
    Properties prop = null;
    //Constructor
    public WebServices(){
    	prop = new Properties();
    	String userHome = System.getProperty("user.home");
    	String propertyFilePath = userHome+"/trabajoUy/conf.properties";
    	
    	try {
    		InputStream inputStream = new FileInputStream(propertyFilePath);
    		prop.load(inputStream);
    		
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    }

    //Operaciones las cuales quiero publicar

    @WebMethod(exclude = true)
    public void publicar(){
         //endpoint = Endpoint.publish("http://localhost:9127/webservices", this);
    	endpoint =  Endpoint.publish("http://"+prop.getProperty("host")+":"+prop.getProperty("port")+"/webservices", this);
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
            return endpoint;
    }
    
    @WebMethod 
    public void cambiarEstado(LoginEstado est) {
    	
    }
    
    @WebMethod
    public DTUsuario buscarUsuarioPorMail(String mail) {
    	Factory fab = Factory.getInstance();
    	IUsuario ius = fab.getIUsuario();
    	return ius.buscarDTUsuarioPorMail(mail);
    }
    
    @WebMethod
    public DTUsuario buscarUsuario(String nickname) {
    	Factory fab = Factory.getInstance();
    	IUsuario ius = fab.getIUsuario();
    	return ius.buscarDTUsuario(nickname);
    }
    
    @WebMethod
    public DTEmpresa buscarEmpresa(String nickname) {
    	Factory fab = Factory.getInstance();
    	IUsuario ius = fab.getIUsuario();
    	return ius.buscarEmpresa(nickname);
    }
    
    @WebMethod
    public DTPostulante buscarPostulante(String nickname) {
    	Factory fab = Factory.getInstance();
    	IUsuario ius = fab.getIUsuario();
    	return ius.dataPostulante(nickname);
    }
    
    @WebMethod
    public DTPaquete buscarPaquete(String nombrePaq) {
    	Factory fac = Factory.getInstance();
		IOfertaLaboral col = fac.getIOfertaLaboral();
    	return col.buscarPaquete(nombrePaq);
    }
    
    @WebMethod
    public DTKeywordWS getDTKeyword() {
    	Factory fac = Factory.getInstance();
		IOfertaLaboral col = fac.getIOfertaLaboral();
		DTKeyword[] keys;
		if (col.getDTKeywords() == null) {
			keys = new DTKeyword[0];
		} else {
			keys = col.getDTKeywords();
		}
		DTKeywordWS dtkey = new DTKeywordWS(keys);

		return dtkey;
    }
    
    @WebMethod
    public DTOfertaLaboralWS getDTOfertasLaborales() throws OfertasLaboralesNoExistenNingunaException {
    	Factory fac = Factory.getInstance();
		IOfertaLaboral col = fac.getIOfertaLaboral();
		DTOfertaLaboral[] ofertas = col.listarTodasOfertasLaborales();
		DTOfertaLaboralWS ofsWS = new DTOfertaLaboralWS(ofertas);
		return ofsWS;
    }
    
    @WebMethod
    public void ingresarDatosEmpresa(DTEmpresa dtemp) throws ExisteUnUsuarioYaRegistradoException {
    	Factory fac = Factory.getInstance();
    	IUsuario cusr = fac.getIUsuario();
    	cusr.ingresarDatosEmpresa(dtemp);
    }
    
    @WebMethod
    public void ingresarDatosPostulante(DTPostulante dtpost) throws ExisteUnUsuarioYaRegistradoException {
    	Factory fac = Factory.getInstance();
    	IUsuario cusr = fac.getIUsuario();
    	cusr.ingresarDatosPostulante(dtpost);
    }
    
    @WebMethod
    public DTPostulante crearDTPostulante(String nickname, String nom, String apellido, String correo, String contrasenia, String nacionalidad, 
											String fechaDeNacimiento, byte[] image) {
    	Factory fac = Factory.getInstance();
    	IUsuario cusr = fac.getIUsuario();
    	return cusr.crearDTPostulante(nickname, nom, apellido, correo, contrasenia, nacionalidad, fechaDeNacimiento, image);
    }
    
    @WebMethod
    public DTEmpresa crearDTEmpresa(String nickname, String nom, String apellido, String correo, String contrasenia, 
										String descripcion, String link,byte[] image) {
    	Factory fac = Factory.getInstance();
    	IUsuario cusr = fac.getIUsuario();
    	return cusr.crearDTEmpresa(nickname, nom, apellido, correo, contrasenia, descripcion, link, image);
    }
    
    @WebMethod
    public DTOfertaLaboralMisPostulacionesWS listarOfertasPostulado(String nickname) throws UsuarioSinPostulacionesException {
    	Factory fac = Factory.getInstance();
    	IUsuario cusr = fac.getIUsuario();
    	DTOfertaLaboral[] ofertas = cusr.listarOfertasPostulado(nickname);
    	DTOfertaLaboralMisPostulacionesWS misPostulaciones = new DTOfertaLaboralMisPostulacionesWS(ofertas);
    	return misPostulaciones;
    }
    
    @WebMethod
    public DTPostulacion dataPostulacion(String nicknameConsultado, String nomOferta) {
    	Factory fac = Factory.getInstance();
    	IOfertaLaboral col = fac.getIOfertaLaboral();
    	DTPostulacion dataPostulacion = col.dataPostulacion(nicknameConsultado, nomOferta);
    	return dataPostulacion;
    }
    
    @WebMethod
    public DTPostulante dataPostulante(String nickname) {
    	Factory fac = Factory.getInstance();
    	IUsuario cusr = fac.getIUsuario();
    	return cusr.dataPostulante(nickname);
    }
    
    @WebMethod
    public String[] listarNomTipos() throws NoHayTiposException{
    	Factory fac = Factory.getInstance();
		IOfertaLaboral col = fac.getIOfertaLaboral();
		String[] tipos = col.listarNomTipos();
		return tipos;
    }
    
    @WebMethod
    public DTTipo buscarTipo(String type) {
    	Factory fac = Factory.getInstance();
		IOfertaLaboral col = fac.getIOfertaLaboral();
		return col.buscarTipo(type);
    }
    
    @WebMethod
    public boolean existeEmpresa(String nickname) {
    	Factory fac = Factory.getInstance();
    	IUsuario iusr = fac.getIUsuario();
    	return iusr.existeEmpresa(nickname);
    }
    
    @WebMethod
    public boolean existePostulante(String nickname) {
    	Factory fac = Factory.getInstance();
    	IUsuario iusr = fac.getIUsuario();
    	return iusr.existePostulante(nickname);
    }
    
    @WebMethod
    public DTUsuarioWS listarUsuarios() throws UsuariosNoExistenException{
    	Factory fac = Factory.getInstance();
    	IUsuario iusr = fac.getIUsuario();
    	DTUsuarioWS users = new DTUsuarioWS(iusr.listarUsuarios());
    	return users;
    }
    
    @WebMethod
    public DTUsuario mostrarInformacionUsuario(String nickname) {
    	Factory fac = Factory.getInstance();
    	IUsuario iusr = fac.getIUsuario();
    	return iusr.mostrarInformacionUsuario(nickname);
    }

    
    @WebMethod
    public String[] listarTipoPublicacionOfertaLaboral() {
    	Factory fac = Factory.getInstance();
		IOfertaLaboral col = fac.getIOfertaLaboral();
		String[] tipos = new String[0];

		try {
			DTTipo[] tipo = col.listarTipoPublicacionOfertaLaboral();
			tipos = new String[tipo.length];
			for (int i = 0; i < tipo.length; i++) {
                tipos[i] =  tipo[i].getNombre();
            }
			return tipos;
				
		}catch(TipoPubNoExistenException e) {
			
			return tipos;
		}
    }
    
    @WebMethod
    public DTOfertaLaboral crearDTOfertaLaboral(String nombre, String descripcion, String ciudad, String departamento, String horario, float remuneracion, String[] keyss) {
    	LocalDate fechaDeAlta =  LocalDate.now();
  
    	DTKeyword[] keywords = new DTKeyword[keyss.length];
		for (int j = 0; j < keyss.length; j++) {
			keywords[j] =  new DTKeyword(keyss[j]);
        }
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String fechaDeAltaString = fechaDeAlta.format(formatter);
    	DTOfertaLaboral oferta = new DTOfertaLaboral(nombre, descripcion, ciudad, departamento, horario, remuneracion, fechaDeAltaString, keywords);
    	
		return oferta;
    	
    }
    
    @WebMethod
    public boolean ingresarDatosOL(String nickname, String tipo, DTOfertaLaboral ofLab) {
    	Factory fac = Factory.getInstance();
		IOfertaLaboral col = fac.getIOfertaLaboral();
    	try {
    		col.ingresarDatosOL(nickname, tipo, ofLab);
    		return true;
    		
    	}catch (OfertaLaboralRepetidaException e) {
    		return false;
    	}
    }
    
    @WebMethod
    public boolean comprarPaquete(String nickname, String nombrePaq) {
    	Factory fac = Factory.getInstance();
		IOfertaLaboral col = fac.getIOfertaLaboral();
		try {
	 		col.comprarPaquete(nickname,nombrePaq);
	 		return true;
	 	}catch (PaqueteYaCompradoException e) {
	 		return false;
	 	}
    }
    
    @WebMethod
    public boolean verificacionCompraPaq(String nicknameEnSesion, String nombrePaq) {
    	Factory fab = Factory.getInstance();
    	IUsuario ius = fab.getIUsuario();
    	return ius.verificacionCompraPaq(nicknameEnSesion, nombrePaq);
    }
    
    @WebMethod
    public String[] listarNomPaquetes() {
    	Factory fac = Factory.getInstance();
		IOfertaLaboral col = fac.getIOfertaLaboral();
		String[] paqs = new String[0];
	    try {
			paqs = col.listarNomPaquetes();
			return paqs;
		} catch (NoHayPaquetesException e) {
			return paqs;
		}
    }
    
    @WebMethod
    public DTOfertaLaboral obtenerDTOfertaLaboral(String nombre) {
    	Factory fac = Factory.getInstance();
        IOfertaLaboral col = fac.getIOfertaLaboral();
        DTOfertaLaboral oferta = col.obtenerDTOfertaLaboral(nombre);
        return oferta;
    }
    
    @WebMethod
    public DTCompraWS getPaqComprados(String nombreEmpresa) {
    	Factory fac = Factory.getInstance();
        IUsuario cu = fac.getIUsuario();
    	DTEmpresa emp = cu.buscarEmpresa(nombreEmpresa);
    	List<DTCompra> comps = emp.getPaqComprados();
    	DTCompra[] compsarr = comps.toArray(new DTCompra[comps.size()]);
    	DTCompraWS compraws = new DTCompraWS(compsarr);
    	return compraws;
    }
    
    @WebMethod
    public Boolean existeOfertaEnLista(DTOfertaLaboral[] lista, String nombreOf) {
    	for (DTOfertaLaboral of: lista) {
    		if (of.getNombre().equals(nombreOf)) {
    			return true;
    		}
    	}
    	return false;
    }
    
    @WebMethod
    public boolean verificacionDePostulantePostulacion(String nickname, String oferta, String empresa) {
    	Factory fac = Factory.getInstance();
        IUsuario curs = fac.getIUsuario();
        return curs.verificacionDePostulantePostulacion(nickname, oferta, empresa);
    }
    
    @WebMethod
    public void ingresarPostulacion(String CVReducido, String motivacion, String fecha, String empresa, String oferta, String postulante, String video) throws YaSePostuloException {
     	Factory fab = Factory.getInstance();
    	IUsuario iusu = fab.getIUsuario();
    	
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");		
		LocalDate fechaLD = LocalDate.parse(fecha, formatter);
		
    	iusu.ingresarPostulacion(CVReducido, motivacion, fechaLD, empresa, oferta, postulante, video);
    }
   
    @WebMethod
    public DTOfertaLaboralWS getOfertasSeleccionarPosutlante(String nickname) throws NoExistenOfertasSeleccionarPostulanteException {
    	Factory fac = Factory.getInstance();
    	IOfertaLaboral col = fac.getIOfertaLaboral();
    	DTOfertaLaboral[] ols = col.getOfertasSeleccionarPosutlante(nickname);
    	DTOfertaLaboralWS dtols = new DTOfertaLaboralWS(ols);
    	return dtols;
    }

    @WebMethod
    public boolean estaSiguiendo(String nicknameEnSesion, String nicknameConsultado) {
    	Factory fac = Factory.getInstance();
        IUsuario curs = fac.getIUsuario();
        return curs.estaSiguiendo(nicknameEnSesion, nicknameConsultado);
    }
    
    @WebMethod
    public void dejarSeguimiento(String nicknameEnSesion, String nicknameConsultado) {
    	Factory fac = Factory.getInstance();
        IUsuario curs = fac.getIUsuario();
        curs.dejarSeguimiento(nicknameEnSesion, nicknameConsultado);
    }
    
    @WebMethod
    public void seguirUsuario(String nicknameEnSesion, String nicknameConsultado) {
    	Factory fac = Factory.getInstance();
        IUsuario curs = fac.getIUsuario();
        curs.seguirUsuario(nicknameEnSesion, nicknameConsultado);
    }
    
    @WebMethod
    public void realizarSeleccion(String nombreOL, String[] rankings) {
    	Factory fac = Factory.getInstance();
    	IOfertaLaboral col = fac.getIOfertaLaboral();
    	col.realizarSeleccion(nombreOL, rankings);
    }
    	
//    @WebMethod
//    public void agregarVisualizacion(String nombreOferta) {
//    	ManejadorOfertaLaboral mol = ManejadorOfertaLaboral.getInstance();
//    	OfertaLaboral oferta = mol.buscarOfertaLaboral(nombreOferta);
//    	oferta.agregarVisualizacion();
//    }
    
    @WebMethod
    public DTOfertaLaboral buscarOfertaLaboral(String nombre) {
    	Factory fac = Factory.getInstance();
    	IOfertaLaboral col = fac.getIOfertaLaboral();
    	return col.buscarOfertaLaboral(nombre);
    }
    
    @WebMethod
    public Boolean existeMail(String mail) {
    	Factory fac = Factory.getInstance();
    	IUsuario iu = fac.getIUsuario();
    	return iu.existeMail(mail);
    }
    
    @WebMethod
    public String[] obtenerSeguidores(String nickname) {
    	Factory fac = Factory.getInstance();
    	IUsuario iusr = fac.getIUsuario();
    	String[] seguidores = iusr.obtenerSeguidores(nickname);
    	return seguidores;
    }
    
    @WebMethod
    public String[] obtenerSeguidos(String nickname) {
    	Factory fac = Factory.getInstance();
    	IUsuario iusr = fac.getIUsuario();
    	String[] seguidos = iusr.obtenerSeguidos(nickname);
    	return seguidos;
    }
    
    @WebMethod
    public void cambiarEstadoOferta(EstadoOL estado, String oferta) {
    	Factory fac = Factory.getInstance();
    	IOfertaLaboral iofl = fac.getIOfertaLaboral();
    	iofl.acepRechOL(estado, oferta);
    }

    
    public boolean esFavorito(String nickname, String oferta) {
    	Factory fac = Factory.getInstance();
    	IUsuario iusr = fac.getIUsuario();
    	return iusr.esFavorito(nickname, oferta);
    }
    public void quitarOfertaFav(String nickname, String oferta) {
    	Factory fac = Factory.getInstance();
    	IUsuario iusr = fac.getIUsuario();
    	iusr.quitarOfertaFav(nickname, oferta);
    }
    public void agregarOfertaFav(String nickname, String oferta) {
    	Factory fac = Factory.getInstance();
    	IUsuario iusr = fac.getIUsuario();
    	iusr.agregarOfertaFav(nickname, oferta);
    }

    @WebMethod
    public boolean estaVigenteOferta(String nombreOferta) {
    	Factory fac = Factory.getInstance();
    	IOfertaLaboral iofl = fac.getIOfertaLaboral();
    	return iofl.estaVigenteOferta(nombreOferta);
    }
    
    @WebMethod
    public String  obtenerVideoPostulacion(String nickname, String oferta) {
    	Factory fac = Factory.getInstance();
    	IOfertaLaboral iofl = fac.getIOfertaLaboral();
    	String vid = iofl.obtenerVideoPostulacion(nickname, oferta);
    	if (vid != null) {
    		return vid;
    	}
    	else {
    		return "";
    	}
    }

}