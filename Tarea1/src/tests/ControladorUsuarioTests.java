package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import excepciones.ExisteUnUsuarioYaRegistradoException;
import excepciones.OfertaLaboralRepetidaException;
import excepciones.PostulantesNoExistenException;
import excepciones.EmpresasNoExistenException;
import excepciones.EmpresaSinOfertasException;
import excepciones.TipoRepetidoException;
import excepciones.UsuariosNoExistenException;

import logica.ManejadorTipo;
import logica.ManejadorOfertaLaboral;
import logica.DTEmpresa;
import logica.ManejadorUsuario;
import logica.Factory;
import logica.DTPostulante;
import logica.IOfertaLaboral;
import logica.IUsuario;
import logica.DTTipo;
import logica.DTOfertaLaboral;
import logica.DTKeyword;
import logica.DTUsuario;
import logica.Postulante;

class ControladorUsuarioTests {
	
	private static IUsuario cu;
	private static IOfertaLaboral col;
	private ManejadorUsuario mu;
	private ManejadorOfertaLaboral mol;
	private ManejadorTipo tp;
	
	@BeforeAll
	public static void iniciar() {
		Factory fac = Factory.getInstance();
		col = fac.getIOfertaLaboral();
		cu = fac.getIUsuario();
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
	void testRegistrarPostulanteOK() {
		//Data postulante 
		String nickname = "NicknamePrueba";
		String nombre = "NombrePrueba";
		String apellido = "ApellidoPrueba";
		String correo = "correoPrueba@gmail.com";
		LocalDate fechaDeNacimiento = LocalDate.of(2023, 1, 1);
		String nacionalidad = "Uruguay";
		DTPostulante dataP = new DTPostulante(nickname, nombre, apellido, correo, fechaDeNacimiento, nacionalidad);
		
		try {
			cu.ingresarDatosPostulante(dataP);
			DTPostulante dataU =(DTPostulante) cu.mostrarInformacionUsuario(nickname);
			
			assertEquals(dataU.getNickname(), nickname);
			assertEquals(dataU.getNombre(), nombre);
			assertEquals(dataU.getApellido(), apellido);
			assertEquals(dataU.getCorreo(), correo);
			assertEquals(dataU.getFechaDeNacimiento(), fechaDeNacimiento);
			assertEquals(dataU.getNacionalidad(), nacionalidad);
		} catch (ExisteUnUsuarioYaRegistradoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test 
	void testRegistrarPostulanteRepetido() {
		//Data postulante 
		String nickname = "NicknamePrueba2";
		String nombre = "NombrePrueba";
		String apellido = "ApellidoPrueba";
		String correo = "correoPrueba2@gmail.com";
		LocalDate fechaDeNacimiento = LocalDate.of(2023, 1, 1);
		String nacionalidad = "Uruguay";
		DTPostulante dataP = new DTPostulante(nickname, nombre, apellido, correo, fechaDeNacimiento, nacionalidad);
		
		try {
			cu.ingresarDatosPostulante(dataP);
		} catch(ExisteUnUsuarioYaRegistradoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		assertThrows(ExisteUnUsuarioYaRegistradoException.class, ()->{cu.ingresarDatosPostulante(dataP);});
	}
	
	@Test
	void testListarPostulanteOK() {
		String nickname = "NicknamePrueba3";
		String nombre = "NombrePrueba";
		String apellido = "ApellidoPrueba";
		String correo = "correoPrueba3@gmail.com";
		LocalDate fechaDeNacimiento = LocalDate.of(2023, 1, 1);
		String nacionalidad = "Uruguay";
		DTPostulante dataP = new DTPostulante(nickname, nombre, apellido, correo, fechaDeNacimiento, nacionalidad);
		try {
			cu.ingresarDatosPostulante(dataP);
			DTPostulante[] listaP = cu.listarPostulantes();
			assertEquals(listaP[0].getNickname(), nickname);
			assertEquals(listaP[0].getNombre(), nombre);
			assertEquals(listaP[0].getApellido(), apellido);
			assertEquals(listaP[0].getCorreo(), correo);
			assertEquals(listaP[0].getFechaDeNacimiento(), fechaDeNacimiento);
			assertEquals(listaP[0].getNacionalidad(), nacionalidad);
		} catch (ExisteUnUsuarioYaRegistradoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (PostulantesNoExistenException ep) {
			fail(ep.getMessage());
			ep.printStackTrace();
		}
	}
	
	@Test
	void testListarPostulanteNoExisten() {
		assertThrows(PostulantesNoExistenException.class, ()->{cu.listarPostulantes();});
	}
	
	@Test 
	void listarEmpresasAOLOK() {
		String nickname = "NicknamePrueba3";
		String nombre = "NombrePrueba";
		String apellido = "ApellidoPrueba";
		String correo = "correoPrueba3@gmail.com";
		String nombreEmpresa = "NombreEmpresaPrueba";
		String descripcion = "DescripcionPrueba";
		String link = "LinkPrueba";
		DTEmpresa emp = new DTEmpresa(nickname, nombre, apellido, correo, nombreEmpresa, descripcion, link);
		try {
			cu.ingresarDatosEmpresa(emp);
			DTEmpresa[] listaE = cu.listarEmpresasAOL();
			assertEquals(listaE[0].getNombre(), nombre);
			assertEquals(listaE[0].getNickname(), nickname);
			assertEquals(listaE[0].getApellido(), apellido);
			assertEquals(listaE[0].getCorreo(), correo);
			assertEquals(listaE[0].getNombreDeEmpresa(), nombreEmpresa);
			assertEquals(listaE[0].getDescripcion(), descripcion);
			assertEquals(listaE[0].getLink(), link);
		} catch (ExisteUnUsuarioYaRegistradoException e) {
			fail(e.getMessage());
		} catch (EmpresasNoExistenException ee) {
			fail(ee.getMessage());
		}
	}
	
	@Test
	void listarEmpresasAOLNoExisten() {
		assertThrows(EmpresasNoExistenException.class, ()->{cu.listarEmpresasAOL();});
	}
	
	@Test 
	void listarOfertasLaboralesViegentesOK() {
		//Datos Empresa
		String nickname = "NicknamePrueba3";
		String nombre = "NombrePrueba";
		String apellido = "ApellidoPrueba";
		String correo = "correoPrueba3@gmail.com";
		String nombreEmpresa = "NombreEmpresaPrueba";
		String descripcion = "DescripcionPrueba";
		String link = "LinkPrueba";
		DTEmpresa emp = new DTEmpresa(nickname, nombre, apellido, correo, nombreEmpresa, descripcion, link);
		
		//Datos de tipo
		String nombreTipo = "Nombre Tipo Test ListarEmpresasOK";
		String descripcionTipo = "Descripcion Tipo Test ListarEmpresasOK";
        int exposicionTipo = 10;
        int duracionTipo = 100000;
        float costoTipo = 15.0f;
        Date fechaDeAltaTipo = new Date();
       	LocalDate localDate = fechaDeAltaTipo.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
       	DTTipo dtTipo = new DTTipo(nombreTipo, descripcionTipo, exposicionTipo,duracionTipo,costoTipo,localDate);
       	
       	//Datos de Keyword 
       	String nombreK = "NombreKey";
       	DTKeyword dtk = new DTKeyword(nombreK);
       	Map<String, DTKeyword> mapdtk = new HashMap<>();
       	mapdtk.put(nombreK, dtk);
       	
       	//Datos de Oferta Laboral
       	String nombreOL = "NombreOL";
       	String descripcionOL = "DescripcionOL";
       	String ciudadOL = "CiudadOL";
       	String departamentoOL = "DepartamentoOL";
       	String horario = "HorarioOL";
       	float remuneracion = 123;
       	LocalDate dateOL = LocalDate.of(2023, 8, 1);
       	DTOfertaLaboral dtol = new DTOfertaLaboral(nombreOL, descripcionOL, ciudadOL, departamentoOL, horario, remuneracion, dateOL, mapdtk);
       	
		try {
			cu.ingresarDatosEmpresa(emp);
			col.ingresarTipo(nombreTipo, descripcionTipo, exposicionTipo, duracionTipo, costoTipo, fechaDeAltaTipo);
			col.ingresarDatosOL(emp.getNickname(), nombreTipo, dtol);
			DTOfertaLaboral[] o = cu.listarOfertasLaboralesVigentes(emp.getNickname());
			assertEquals(o[0].getNombre(), nombreOL);
			assertEquals(o[0].getDescripcion(), descripcionOL);
			assertEquals(o[0].getCiudad(), ciudadOL);
			assertEquals(o[0].getDepartamento(), departamentoOL);
			assertEquals(o[0].getHorario(), horario);
			assertEquals(o[0].getRemuneracion(), remuneracion);
			assertEquals(o[0].getFechaDeAlta(), dateOL);
		} catch (EmpresaSinOfertasException e) {
			fail(e.getMessage());
		} catch (ExisteUnUsuarioYaRegistradoException ee) {
			fail(ee.getMessage());
		} catch (TipoRepetidoException eee) {
			fail(eee.getMessage());
		} catch (OfertaLaboralRepetidaException eeee) {
			fail(eeee.getMessage());
		}
	}
	
	@Test 
	void listarOfertasLaboralesViegentesNoExisten() {
		//Datos Empresa
		String nickname = "NicknamePrueba3";
		String nombre = "NombrePrueba";
		String apellido = "ApellidoPrueba";
		String correo = "correoPrueba3@gmail.com";
		String nombreEmpresa = "NombreEmpresaPrueba";
		String descripcion = "DescripcionPrueba";
		String link = "LinkPrueba";
		DTEmpresa emp = new DTEmpresa(nickname, nombre, apellido, correo, nombreEmpresa, descripcion, link);
		
       	try {
			cu.ingresarDatosEmpresa(emp);
		} catch (ExisteUnUsuarioYaRegistradoException ee) {
			fail(ee.getMessage());
		} 
       	assertThrows(EmpresaSinOfertasException.class, ()->{cu.listarOfertasLaboralesVigentes(emp.getNickname());});
	}
	
	@Test
	void listarUsuariosOKEmpresa() {
		//Datos Empresa
		String nickname = "NicknamePrueba3";
		String nombre = "NombrePrueba";
		String apellido = "ApellidoPrueba";
		String correo = "correoPrueba3@gmail.com";
		String nombreEmpresa = "NombreEmpresaPrueba";
		String descripcion = "DescripcionPrueba";
		String link = "LinkPrueba";
		DTEmpresa emp = new DTEmpresa(nickname, nombre, apellido, correo, nombreEmpresa, descripcion, link);
		try {
			cu.ingresarDatosEmpresa(emp);
			DTUsuario[] usu = new DTEmpresa[1];
			usu = cu.listarUsuarios();
			assertEquals(usu[0].getNickname(), nickname);
			assertEquals(usu[0].getNombre(), nombre);
			assertEquals(usu[0].getApellido(), apellido);
			assertEquals(usu[0].getCorreo(), correo);
		} catch (ExisteUnUsuarioYaRegistradoException ee) {
			fail(ee.getMessage());
		} catch(UsuariosNoExistenException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	void listarUsuariosOKPostulante() {
		//Data postulante 
		String nickname = "NicknamePrueba";
		String nombre = "NombrePrueba";
		String apellido = "ApellidoPrueba";
		String correo = "correoPrueba@gmail.com";
		LocalDate fechaDeNacimiento = LocalDate.of(2023, 1, 1);
		String nacionalidad = "Uruguay";
		DTPostulante dataP = new DTPostulante(nickname, nombre, apellido, correo, fechaDeNacimiento, nacionalidad);
		try {
			cu.ingresarDatosPostulante(dataP);
			DTUsuario[] usu = cu.listarUsuarios();
			assertEquals(usu[0].getNickname(), nickname);
			assertEquals(usu[0].getNombre(), nombre);
			assertEquals(usu[0].getApellido(), apellido);
			assertEquals(usu[0].getCorreo(), correo);
		} catch (ExisteUnUsuarioYaRegistradoException ee) {
			fail(ee.getMessage());
		} catch(UsuariosNoExistenException e) {
			fail(e.getMessage());
		}
	}
	
	@Test 
	void listarUsuariosOKPostEmp() {
		//Data postulante 
		String nickname = "NicknamePrueba";
		String nombre = "NombrePrueba";
		String apellido = "ApellidoPrueba";
		String correo = "correoPrueba@gmail.com";
		LocalDate fechaDeNacimiento = LocalDate.of(2023, 1, 1);
		String nacionalidad = "Uruguay";
		DTPostulante dataP = new DTPostulante(nickname, nombre, apellido, correo, fechaDeNacimiento, nacionalidad);
		
		//Datos Empresa
		String nicknameE = "NicknamePrueba3";
		String nombreE = "NombrePrueba";
		String apellidoE = "ApellidoPrueba";
		String correoE = "correoPrueba3@gmail.com";
		String nombreEmpresa = "NombreEmpresaPrueba";
		String descripcion = "DescripcionPrueba";
		String link = "LinkPrueba";
		DTEmpresa emp = new DTEmpresa(nicknameE, nombreE, apellidoE, correoE, nombreEmpresa, descripcion, link);
		try {
			cu.ingresarDatosEmpresa(emp);
			cu.ingresarDatosPostulante(dataP);
			DTUsuario[] usu = cu.listarUsuarios();
			assertEquals(usu[0].getNickname(), nicknameE);
			assertEquals(usu[0].getNombre(), nombreE);
			assertEquals(usu[0].getApellido(), apellidoE);
			assertEquals(usu[0].getCorreo(), correoE);
			assertEquals(usu[1].getNickname(), nickname);
			assertEquals(usu[1].getNombre(), nombre);
			assertEquals(usu[1].getApellido(), apellido);
			assertEquals(usu[1].getCorreo(), correo);
		} catch (ExisteUnUsuarioYaRegistradoException ee) {
			fail(ee.getMessage());
		} catch (UsuariosNoExistenException e) {
			fail(e.getMessage());
		}
	}
	
	@Test 
	void listarUsuariosNoExistentes() {
		assertThrows(UsuariosNoExistenException.class, ()->{cu.listarUsuarios();});
	}
	
	@Test 
	public void modificarPostulante() {
		//Data postulante 
		String nickname = "NicknamePrueba";
		String nombre = "NombrePrueba";
		String apellido = "ApellidoPrueba";
		String correo = "correoPrueba@gmail.com";
		LocalDate fechaDeNacimiento = LocalDate.of(2023, 1, 1);
		String nacionalidad = "Uruguay";
		DTPostulante dataP = new DTPostulante(nickname, nombre, apellido, correo, fechaDeNacimiento, nacionalidad);
		try {
			cu.ingresarDatosPostulante(dataP);
		} catch(ExisteUnUsuarioYaRegistradoException ee) {
			fail(ee.getMessage());
		}
		cu.modificarPostulante(nickname, "nom", "ape", fechaDeNacimiento, "China");
		DTPostulante dtp = (DTPostulante) cu.mostrarInformacionUsuario(nickname);
		assertEquals(dtp.getNombre(), "nom");
		assertEquals(dtp.getApellido(), "ape");
		assertEquals(dtp.getFechaDeNacimiento(), fechaDeNacimiento);
		assertEquals(dtp.getNacionalidad(), "China");
	}
}

