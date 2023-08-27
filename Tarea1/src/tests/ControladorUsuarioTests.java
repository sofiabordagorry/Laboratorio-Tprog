package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;


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
import excepciones.NoExistenEmpresasOfertasLaboralesException;
import excepciones.OfertasLaboralesNoExistenException;

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
import logica.DTPostulacion;
import logica.Empresa;
import logica.OfertaLaboral;
import logica.Tipo;

class ControladorUsuarioTests {
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

	@Test
	void testRegistrarEmpresaOK() {
		String nicknameTest = "Nickname Test RegUsuOK";
		String nombreTest = "Nombre Test RegUsuOK";
		String apellidoTest = "Apellido Test";
		String correoTest = "Correo Test RegUsuOK";
		Map<String, DTOfertaLaboral> ofertasLaboralesTest = new HashMap<>();
		String nombreEmpresaTest = "Nombre Empresa Test RegUsuOK";
		String descripcionTest = "Descripcion Test RegUsuOK";
		String linkTest = "Link Test RegUsuOK";
		DTEmpresa uEmp = new DTEmpresa(nicknameTest, nombreTest, apellidoTest, correoTest, ofertasLaboralesTest, nombreEmpresaTest, descripcionTest, linkTest);
		try {
			cu.ingresarDatosEmpresa(uEmp);
			DTUsuario du = cu.mostrarInformacionUsuario(nicknameTest);
			DTEmpresa de = (DTEmpresa) du;
			
			assertEquals(de.getNickname(), nicknameTest);
			assertEquals(de.getNombre(), nombreTest);
			assertEquals(de.getApellido(), apellidoTest);
			assertEquals(de.getCorreo(), correoTest);
			assertEquals(de.getDTOfertasLaborales(), ofertasLaboralesTest);
			assertEquals(de.getDescripcion(), descripcionTest);
			assertEquals(de.getNombreDeEmpresa(), nombreEmpresaTest);
			assertEquals(de.getLink(), linkTest);
		}catch(ExisteUnUsuarioYaRegistradoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		};
	}
	
	@Test
	void testRegistrarUsuarioRepetido (){
		String nicknameTest = "Nickname Test RegUsuRepe";
		String nombreTest = "Nombre Test RegUsuRepe";
		String apellidoTest = "Apellido Test RegUsuRepe";
		String correoTest = "Correo Test RegUsuRepe";
		Map<String, DTOfertaLaboral> ofertasLaboralesTest = new HashMap<>();
		String nombreEmpresaTest = "Nombre Empresa Test RegUsuRepe";
		String descripcionTest = "Descripcion Test RegUsuRepe";
		String linkTest = "Link Test RegUsuRepe";
		DTEmpresa uEmp = new DTEmpresa(nicknameTest, nombreTest, apellidoTest, correoTest, ofertasLaboralesTest, nombreEmpresaTest, descripcionTest, linkTest);
		try {
			cu.ingresarDatosEmpresa(uEmp);
		}catch(ExisteUnUsuarioYaRegistradoException e){
			fail(e.getMessage());
			e.printStackTrace();
		};
		assertThrows(ExisteUnUsuarioYaRegistradoException.class, ()->{cu.ingresarDatosEmpresa(uEmp);});
	}
	
	@Test
	void testListarEmpresasOK() {
		String nicknameTest = "Nickname Test ListarEmpresasOK";
		String nombreTest = "Nombre Test ListarEmpresasOK";
		String apellidoTest = "Apellido Test ListarEmpresasOK";
		String correoTest = "Correo Test ListarEmpresasOK";
		Map<String, DTOfertaLaboral> ofertasLaboralesTest = new HashMap<>();
		String nombreEmpresaTest = "Nombre Empresa Test ListarEmpresasOK";
		String descripcionTest = "Descripcion Test ListarEmpresasOK";
		String linkTest = "Link Test ListarEmpresasOK";
		DTEmpresa[] empresas = new DTEmpresa[1];
		DTEmpresa uEmp = new DTEmpresa(nicknameTest, nombreTest, apellidoTest, correoTest, ofertasLaboralesTest, nombreEmpresaTest, descripcionTest, linkTest);
		empresas[0] = uEmp;
		
		String nombreTipo = "Nombre Tipo Test ListarEmpresasOK";
		String descripcionTipo = "Descripcion Tipo Test ListarEmpresasOK";
		int exposicionTipo = 10;
		int duracionTipo = 10;
		float costoTipo = 15.0f;
		Date fechaDeAltaTipo = new Date();
		//LocalDate localDate = fechaDeAltaTipo.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		//DTTipo dtTipo = new DTTipo(nombreTipo, descripcionTipo, exposicionTipo,duracionTipo,costoTipo,localDate);
		
		String nombreOL = "Nombre Oferta Laboral ListarEmpresasOK";
		String descripcionOL = "Descripcion OfertaLaboral ListarEmpresasOK";
		String ciudadOL = "Ciudad Oferta Laboral ListarEmpresasOK";
		String departamentoOL = "Departamento Oferta Laboral ListarEmpresasOK";
		String horarioOL ="Horario Oferta Laboral ListarEmpresasOK";
		float remuneracionOL = 180.7f;
		LocalDate fechaDeAltaOL = LocalDate.of(2023, 8, 25);
		float costoOL = 15.0f;
		Map<String, DTKeyword> keysOL = new HashMap<>();
		//List<DTPostulacion> postulacionesOL = new LinkedList<>();
		DTOfertaLaboral olNueva = new DTOfertaLaboral(nombreOL, descripcionOL,ciudadOL, departamentoOL, horarioOL, remuneracionOL, fechaDeAltaOL, keysOL);
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		try {
			cu.ingresarDatosEmpresa(uEmp);
			col.ingresarTipo(nombreTipo, descripcionTipo, exposicionTipo, duracionTipo, costoTipo, fechaDeAltaTipo);
			col.ingresarDatosOL(nicknameTest,nombreTipo, olNueva);
			int cantEmpresas = mu.getEmpresas().length;
			DTEmpresa[] emps = new DTEmpresa[cantEmpresas];
			emps = cu.listarEmpresas();
			assertEquals(emps[0].getNickname(),empresas[0].getNickname());
			assertEquals(emps[0].getNombre(), empresas[0].getNombre());
			assertEquals(emps[0].getApellido(), empresas[0].getApellido());
			assertEquals(emps[0].getCorreo(), empresas[0].getCorreo());
			
			DTOfertaLaboral dt1 = emps[0].getDTOfertasLaborales().get(nombreOL);
			
			assertEquals(dt1.getCiudad(), ciudadOL);
			assertEquals(dt1.getCostoAsociado(), costoOL);
			assertEquals(dt1.getDepartamento(), departamentoOL);
			assertEquals(dt1.getDescripcion(), descripcionOL);
			assertEquals(dt1.getFechaDeAlta().toString(), fechaDeAltaOL.toString());
			assertEquals(dt1.getHorario(), horarioOL);
			assertEquals(dt1.getNombre(), nombreOL);
			assertEquals(dt1.getRemuneracion(), remuneracionOL);

			assertEquals(emps[0].getDescripcion(), empresas[0].getDescripcion());
			assertEquals(emps[0].getNombreDeEmpresa(), empresas[0].getNombreDeEmpresa());
			assertEquals(emps[0].getLink(), empresas[0].getLink());
		}catch(ExisteUnUsuarioYaRegistradoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}catch(EmpresasNoExistenException e){
			fail(e.getMessage());
			e.printStackTrace();
		}catch(NoExistenEmpresasOfertasLaboralesException e){
			fail(e.getMessage());
			e.printStackTrace();
		}catch (TipoRepetidoException e){
			fail(e.getMessage());
			e.printStackTrace();
		}catch(OfertaLaboralRepetidaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		};
	}

	@Test
	void TestListarEmpresasNoHayEmpresa() {
		assertThrows(EmpresasNoExistenException.class, ()->{cu.listarEmpresas();});
	}
	
	@Test
	void TestListarEmpresasNoHayOfertasLaborales() {
		String nicknameTest = "Nickname Test ListarEmpresasOK";
		String nombreTest = "Nombre Test ListarEmpresasOK";
		String apellidoTest = "Apellido Test ListarEmpresasOK";
		String correoTest = "Correo Test ListarEmpresasOK";
		Map<String, DTOfertaLaboral> ofertasLaboralesTest = new HashMap<>();
		String nombreEmpresaTest = "Nombre Empresa Test ListarEmpresasOK";
		String descripcionTest = "Descripcion Test ListarEmpresasOK";
		String linkTest = "Link Test ListarEmpresasOK";
		DTEmpresa uEmp = new DTEmpresa(nicknameTest, nombreTest, apellidoTest, correoTest, ofertasLaboralesTest, nombreEmpresaTest, descripcionTest, linkTest);
		//ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		try {
			//int cantEmpresas = mu.getEmpresas().length;
			cu.ingresarDatosEmpresa(uEmp);
		}catch(ExisteUnUsuarioYaRegistradoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}

			assertThrows(NoExistenEmpresasOfertasLaboralesException.class, ()->{cu.listarEmpresas();});
	}
	
	@Test
	void listarOfertasLaboralesOK() {
		String nicknameTest = "Nickname Test ListarEmpresasOK";
		String nombreTest = "Nombre Test ListarEmpresasOK";
		String apellidoTest = "Apellido Test ListarEmpresasOK";
		String correoTest = "Correo Test ListarEmpresasOK";
		Map<String, DTOfertaLaboral> ofertasLaboralesTest = new HashMap<>();
		String nombreEmpresaTest = "Nombre Empresa Test ListarEmpresasOK";
		String descripcionTest = "Descripcion Test ListarEmpresasOK";
		String linkTest = "Link Test ListarEmpresasOK";
		DTEmpresa uEmp = new DTEmpresa(nicknameTest, nombreTest, apellidoTest, correoTest, ofertasLaboralesTest, nombreEmpresaTest, descripcionTest, linkTest);
		
		String nombreTipo = "Nombre Tipo Test ListarEmpresasOK";
		String descripcionTipo = "Descripcion Tipo Test ListarEmpresasOK";
		int exposicionTipo = 10;
		int duracionTipo = 10;
		float costoTipo = 15.0f;
		Date fechaDeAltaTipo = new Date();
		
		String nombreOL = "Nombre Oferta Laboral ListarEmpresasOK";
		String descripcionOL = "Descripcion OfertaLaboral ListarEmpresasOK";
		String ciudadOL = "Ciudad Oferta Laboral ListarEmpresasOK";
		String departamentoOL = "Departamento Oferta Laboral ListarEmpresasOK";
		String horarioOL ="Horario Oferta Laboral ListarEmpresasOK";
		float remuneracionOL = 180.7f;
		LocalDate fechaDeAltaOL = LocalDate.of(2023, 8, 25);
		float costoOL = 15.0f;
		Map<String, DTKeyword> keysOL = new HashMap<>();
		DTOfertaLaboral olNueva = new DTOfertaLaboral(nombreOL, descripcionOL,ciudadOL, departamentoOL, horarioOL, remuneracionOL, fechaDeAltaOL, keysOL);
		
		try {
			cu.ingresarDatosEmpresa(uEmp);
			col.ingresarTipo(nombreTipo, descripcionTipo, exposicionTipo, duracionTipo, costoTipo, fechaDeAltaTipo);
			col.ingresarDatosOL(nicknameTest,nombreTipo, olNueva);
		}catch(ExisteUnUsuarioYaRegistradoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}catch(OfertaLaboralRepetidaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}catch(TipoRepetidoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		};
		try {
			DTOfertaLaboral[] dtOL = new DTOfertaLaboral[1];
 			dtOL = cu.listarOfertasLaborales(nicknameTest);
 			assertEquals(dtOL[0].getNombre(), nombreOL);
 			assertEquals(dtOL[0].getDescripcion(), descripcionOL);
 			assertEquals(dtOL[0].getCiudad(), ciudadOL);
 			assertEquals(dtOL[0].getDepartamento(), departamentoOL);
 			assertEquals(dtOL[0].getHorario(), horarioOL);
 			assertEquals(dtOL[0].getRemuneracion(),remuneracionOL);
 			assertEquals(dtOL[0].getFechaDeAlta().toString(), fechaDeAltaOL.toString());
 			assertEquals(dtOL[0].getCostoAsociado(), costoOL);
		}catch(OfertasLaboralesNoExistenException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test 
	void listarOfertasLaboralesNoHayOfertas(){
		String nicknameTest = "Nickname Test ListarEmpresasOK";
		String nombreTest = "Nombre Test ListarEmpresasOK";
		String apellidoTest = "Apellido Test ListarEmpresasOK";
		String correoTest = "Correo Test ListarEmpresasOK";
		Map<String, DTOfertaLaboral> ofertasLaboralesTest = new HashMap<>();
		String nombreEmpresaTest = "Nombre Empresa Test ListarEmpresasOK";
		String descripcionTest = "Descripcion Test ListarEmpresasOK";
		String linkTest = "Link Test ListarEmpresasOK";
		DTEmpresa uEmp = new DTEmpresa(nicknameTest, nombreTest, apellidoTest, correoTest, ofertasLaboralesTest, nombreEmpresaTest, descripcionTest, linkTest);
		

		try {
			cu.ingresarDatosEmpresa(uEmp);
		}catch(ExisteUnUsuarioYaRegistradoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		assertThrows(OfertasLaboralesNoExistenException.class, ()->{cu.listarOfertasLaborales(nicknameTest);});
	}
	
	@Test
	void modificarEmpresaOK() {
		String nicknameViejo = "NicknameViejo";
		String nombreViejo = "NombreViejo";
		String apellidoViejo = "ApellidoViejo";
		String correoViejo = "CorreoViejo";
		Map<String, DTOfertaLaboral> ofertasLaboralesViejo = new HashMap<>();
		String nombreEmpresaViejo = "NombreEmpresaViejo";
		String descripcionViejo = "DescripcionVieja";
		String linkViejo = "LinkViejo";
		//DTEmpresa[] empresas = new DTEmpresa[1];
		DTEmpresa uEmp = new DTEmpresa(nicknameViejo, nombreViejo, apellidoViejo, correoViejo, ofertasLaboralesViejo, nombreEmpresaViejo, descripcionViejo, linkViejo);
		
		String nombreNuevo = "NombreNuevo";
		String apellidoNuevo = "ApellidoNuevo";
		String nombreEmpresaNuevo = "NombreEmpresaNuevo";
		String descripcionNuevo = "DescripcionNuevo";
		String linkNuevo = "LinkNuevo";
		
		try {
			cu.ingresarDatosEmpresa(uEmp);
		}catch(ExisteUnUsuarioYaRegistradoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		cu.modificarEmpresa(nicknameViejo, nombreNuevo, apellidoNuevo, nombreEmpresaNuevo, descripcionNuevo, linkNuevo);
		
		DTUsuario du = cu.mostrarInformacionUsuario(nicknameViejo);
		DTEmpresa de = (DTEmpresa) du;
		assertEquals(de.getNombre(), nombreNuevo);
		assertEquals(de.getApellido(), apellidoNuevo);
		assertEquals(de.getNombreDeEmpresa(), nombreEmpresaNuevo);
		assertEquals(de.getDescripcion(), descripcionNuevo);
		assertEquals(de.getLink(), linkNuevo);
	}
}
