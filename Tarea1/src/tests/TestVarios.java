package tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import logica.DTCompra;
import logica.DTCompraWS;
import logica.DTTipoWS;
import logica.DTUsuarioWS;
import logica.EstadoOL;
import logica.DTPostulante;
import logica.DTTipo;
import logica.DTUsuario;
import logica.DTPaquete;
import logica.DTPostulacion;
import logica.DTKeyword;
import logica.DTKeywordWS;
import logica.DTOfertaLaboral;
import logica.DTOfertaLaboralMisPostulacionesWS;
import logica.DTOfertaLaboralWS;
import logica.Factory;
import logica.IOfertaLaboral;
import logica.IUsuario;
import logica.ManejadorOfertaLaboral;
import logica.ManejadorTipo;
import logica.ManejadorUsuario;
import logica.OfertaLaboral;
import logica.Tipo;

class TestVarios {
	private static IUsuario cu;
	private static IOfertaLaboral col;
	private ManejadorUsuario mu;
	private ManejadorTipo tp;
	private ManejadorOfertaLaboral mol;
	
	@BeforeAll
	public static void iniciar(){
		Factory fab = Factory.getInstance();
		cu = fab.getIUsuario(); 
		col = fab.getIOfertaLaboral();
	}
	
	@AfterEach
	public void borrarUsuarios() {
		mu = ManejadorUsuario.getInstancia();
		mol = ManejadorOfertaLaboral.getInstance();
		tp = ManejadorTipo.getInstancia();
		mu.borrarUsuarios();
		mol.limpiar();
		tp.limpiar();
	}
	
	@Test
	void DTOfertaLaboral() {
		String nombreOferta = "nombreOferta";
		String descripcionOferta = "descripcionOferta";
		String ciudadOferta = "ciudadOferta";
		String departamentoOferta = "departamentoOferta";
		String horarioOferta = "horarioOferta";
		float remuneracionOferta = 1;
		String fechaDeAltaOferta = "2023-12-12";
		String nomKey = "key";
		DTKeyword dtk = new DTKeyword(nomKey);
		DTKeyword[] dtkeys = new DTKeyword[1];
		dtkeys[0] = dtk;
		new DTOfertaLaboral();
		DTOfertaLaboral dtol1 = new DTOfertaLaboral(nombreOferta, descripcionOferta, ciudadOferta, departamentoOferta, horarioOferta, remuneracionOferta, fechaDeAltaOferta, dtkeys);
		assertEquals(dtol1.getNombre(), nombreOferta);
		assertEquals(dtol1.getDescripcion(), descripcionOferta);
		assertEquals(dtol1.getCiudad(), ciudadOferta);
		assertEquals(dtol1.getDepartamento(), departamentoOferta);
		assertEquals(dtol1.getHorario(), horarioOferta);
		assertEquals(dtol1.getRemuneracion(), remuneracionOferta);
		assertEquals(dtol1.getFechaDeAlta(), fechaDeAltaOferta);
		assertEquals(dtol1.getKeywords()[0].getNombre(), nomKey);
		assertEquals(dtol1.getRankeada(), false);
		
		new DTOfertaLaboral(nombreOferta, descripcionOferta, ciudadOferta, departamentoOferta, horarioOferta, remuneracionOferta, fechaDeAltaOferta, dtkeys, null);
		
		dtol1.setNombre(nombreOferta);
		dtol1.setDescripcion(descripcionOferta);
		dtol1.setCiudad(ciudadOferta);
		dtol1.setDepartamento(departamentoOferta);
		dtol1.setHorario(horarioOferta);
		dtol1.setRemuneracion(remuneracionOferta);
		dtol1.setFechaDeAlta(fechaDeAltaOferta);
		dtol1.setDataKeywords(dtkeys);
		dtol1.setImagen(new byte[0]);
		dtol1.setFavoritos(2);
	}
	
	@Test
	void DTPaquete() {
		String nombreP = "nombrep";
		String descripcionP = "descp";
		int periodoDeValidezP = 4;
		float descuentoP = 3;
		String fechaDeAltaP = "2023-12-12";
		new DTPaquete();
		DTPaquete paq = new DTPaquete(nombreP, descripcionP, periodoDeValidezP, descuentoP, null, fechaDeAltaP);
		new DTPaquete(nombreP, descripcionP, periodoDeValidezP, descuentoP, null, fechaDeAltaP, null);
		paq.setCostoAsociado(4);
		paq.setDescripcion(descripcionP);
		paq.setNombre(nombreP);
		paq.setFechaDeAlta(fechaDeAltaP);
		paq.setFecha(fechaDeAltaP);
		paq.setPeriodoDeValidez(periodoDeValidezP);
		paq.setDescuento(descuentoP);
	}
	
	@Test
    void testSettersDTUsuario(){
        DTUsuario usr = new DTUsuario();

        String nickname ="nickname";
        String nombre ="nombre";
        String apellido= "apellido";
        String correo= "correo";
        String contrasenia = "contra";
        byte[] imagen = null;
        DTOfertaLaboral[] ofertasLaborales = null;

        usr.setNickname(nickname);
        usr.setNombre(nombre);
        usr.setApellido(apellido);
        usr.setCorreo(correo);
        usr.setContrasenia(contrasenia);
        usr.setImagen(imagen);
        usr.setOfertasLaborales(ofertasLaborales);
    }

	@Test
    void testSettersDTPostulante(){
        DTPostulante post = new DTPostulante();

        String nickname ="nickname";
        String nombre ="nombre";
        String apellido= "apellido";
        String correo= "correo";
        String contrasenia = "contra";
        byte[] imagen = null;
        DTOfertaLaboral[] ofertasLaborales = null;
        String fechaDeNacimiento = "";
        String nacionalidad = "nacionalidad";
        List<DTPostulacion> postulaciones = new ArrayList<>();

        post.setNickname(nickname);
        post.setNombre(nombre);
        post.setApellido(apellido);
        post.setCorreo(correo);
        post.setContrasenia(contrasenia);
        post.setImagen(imagen);
        post.setOfertasLaborales(ofertasLaborales);
        post.setFechaDeNacimiento(fechaDeNacimiento);
        post.setNacionalidad(nacionalidad);
        post.setPostulaciones(postulaciones);
    }
	
	@Test
	void DTUsuarioWS() {
		new DTUsuarioWS();
		
		DTUsuario[] dtUsers = new DTUsuario[0];
		DTUsuarioWS dtUsersWs = new DTUsuarioWS(dtUsers);
		dtUsersWs.getUsers();
		dtUsersWs.setUsers(dtUsers);
	}
	
	@Test 
	void DTTipoWS() {
		new DTTipoWS();
		
		DTTipo[] dtTipos = new DTTipo[0];
		DTTipoWS dtTiposWs = new DTTipoWS(dtTipos);
		dtTiposWs.getDTTipo();
		dtTiposWs.setDTTipo(dtTipos);
	}
	
	@Test
	void DTCompraWS() {
		new DTCompraWS();
		
		DTCompra[] dtCompra = new DTCompra[0];
		DTCompraWS dtCompraWs = new DTCompraWS(dtCompra);
		dtCompraWs.getDTCompras();
		dtCompraWs.setDTCompras(dtCompra);
	}
	
	@Test
    void testDTOfertaLaboralWS(){
        DTOfertaLaboral[] ofertasLaborales = null;

        DTOfertaLaboralWS oftsWS = new DTOfertaLaboralWS();
        DTOfertaLaboralWS oftsWS2 = new DTOfertaLaboralWS(ofertasLaborales);

        oftsWS.setDTOfertaLaboral(ofertasLaborales);

        assertEquals(oftsWS.getDTOfertaLaboral(), oftsWS2.getDTOfertaLaboral());
    }

    @Test
    void testDTOfertaLaboralMisPostulacionesWS(){
        DTOfertaLaboral[] ofertasLaborales = null;

        DTOfertaLaboralMisPostulacionesWS ofts = new DTOfertaLaboralMisPostulacionesWS();
        DTOfertaLaboralMisPostulacionesWS ofts2 = new DTOfertaLaboralMisPostulacionesWS(ofertasLaborales);

        ofts.setOfertas(ofertasLaborales);

        assertEquals(ofts.getOfertas(), ofts2.getOfertas());
    }
    
    @Test
    void testDTKeywordsWS(){
        DTKeyword[] DTKeywords = null;

        DTKeywordWS keysWS = new DTKeywordWS();
        DTKeywordWS keysWS2 = new DTKeywordWS(DTKeywords);

        keysWS.setDTKeyword(DTKeywords);

        assertEquals(keysWS.getDTKeyword(), keysWS2.getDTKeyword());
    }
    
    @Test
    void testOfertaLaboral() {
    	String nombreOferta = "nombreOferta";
		String descripcionOferta = "descripcionOferta";
		String ciudadOferta = "ciudadOferta";
		String departamentoOferta = "departamentoOferta";
		String horarioOferta = "horarioOferta";
		float remuneracionOferta = 1;
		LocalDate date = LocalDate.now();
		
		//Tipo
		String nombreTipo = "nombreTipo";
		int exposicionTipo = 3;
		int duracionTipo = 15;
		Float costoTipo = 330f;
		Tipo t = new Tipo(nombreTipo, "des", exposicionTipo, duracionTipo, costoTipo, date);
		
		new OfertaLaboral(nombreOferta, descripcionOferta, ciudadOferta, departamentoOferta, horarioOferta, remuneracionOferta, date, 45, t, null, EstadoOL.Confirmada, null, null);
		new OfertaLaboral(nombreOferta, descripcionOferta, ciudadOferta, departamentoOferta, horarioOferta, remuneracionOferta, date, 45, t, null, EstadoOL.Confirmada, null, null, 3);
		new OfertaLaboral(nombreOferta, descripcionOferta, ciudadOferta, departamentoOferta, horarioOferta, remuneracionOferta, date, 45, t, null, null, null, 3);
    }
 }
