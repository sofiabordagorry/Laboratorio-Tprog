package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import excepciones.EmpresasNoExistenException;
import excepciones.ExisteUnUsuarioYaRegistradoException;
import excepciones.NoExistenEmpresasOfertasLaboralesException;
import excepciones.OfertaLaboralRepetidaException;
import excepciones.OfertasLaboralesNoExistenException;
import excepciones.TipoRepetidoException;
import logica.DTEmpresa;
import logica.DTPostulacion;
import logica.DTKeyword;
import logica.DTOfertaLaboral;
import logica.DTTipo;
import logica.DTUsuario;
import logica.Empresa;
import logica.Factory;
import logica.IOfertaLaboral;
import logica.IUsuario;
import logica.ManejadorOfertaLaboral;
import logica.ManejadorTipo;
import logica.ManejadorUsuario;
import logica.OfertaLaboral;
import logica.Tipo;

class ControladorUsuarioTests {
	private static IUsuario contUsuario;
	private static IOfertaLaboral contOL;
	private ManejadorUsuario mu;
	private ManejadorTipo mt;
	private ManejadorOfertaLaboral mol;
	
	@BeforeAll
	public static void iniciar(){
		Factory fab = Factory.getInstance();
		contUsuario = fab.getIUsuario();
		contOL = fab.getIOfertaLaboral();
	}
	
	@AfterEach
	public void borrarUsuarios() {
		mu = ManejadorUsuario.getInstancia();
		mt = ManejadorTipo.getInstancia();
		mol = ManejadorOfertaLaboral.getInstance();
		mt.borrarPaquetes();
		mt.borrarTipos();
		mol.borrarKeywords();
		mol.borrarOfertasLaborales();
		mu.borrarUsuarios();
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
			contUsuario.ingresarDatosEmpresa(uEmp);
			DTUsuario du = contUsuario.mostrarInformacionUsuario(nicknameTest);
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
			contUsuario.ingresarDatosEmpresa(uEmp);
		}catch(ExisteUnUsuarioYaRegistradoException e){
			fail(e.getMessage());
			e.printStackTrace();
		};
		assertThrows(ExisteUnUsuarioYaRegistradoException.class, ()->{contUsuario.ingresarDatosEmpresa(uEmp);});
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
			contUsuario.ingresarDatosEmpresa(uEmp);
			contOL.ingresarTipo(nombreTipo, descripcionTipo, exposicionTipo, duracionTipo, costoTipo, fechaDeAltaTipo);
			contOL.ingresarDatosOL(nicknameTest,nombreTipo, olNueva);
			int cantEmpresas = mu.getEmpresas().length;
			DTEmpresa[] emps = new DTEmpresa[cantEmpresas];
			emps = contUsuario.listarEmpresas();
			assertEquals(emps[0].getNickname(),empresas[0].getNickname());
			assertEquals(emps[0].getNombre(), empresas[0].getNombre());
			assertEquals(emps[0].getApellido(), empresas[0].getApellido());
			assertEquals(emps[0].getCorreo(), empresas[0].getCorreo());
			
			DTOfertaLaboral dt1 = emps[0].getDTOfertasLaborales().get(nombreOL);
			
			assertEquals(dt1.getCiudad(), ciudadOL);
			assertEquals(dt1.getCostoAsociado(), costoOL);
			assertEquals(dt1.getDepartamente(), departamentoOL);
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
		assertThrows(EmpresasNoExistenException.class, ()->{contUsuario.listarEmpresas();});
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
			contUsuario.ingresarDatosEmpresa(uEmp);
		}catch(ExisteUnUsuarioYaRegistradoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}

			assertThrows(NoExistenEmpresasOfertasLaboralesException.class, ()->{contUsuario.listarEmpresas();});
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
			contUsuario.ingresarDatosEmpresa(uEmp);
			contOL.ingresarTipo(nombreTipo, descripcionTipo, exposicionTipo, duracionTipo, costoTipo, fechaDeAltaTipo);
			contOL.ingresarDatosOL(nicknameTest,nombreTipo, olNueva);
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
 			dtOL = contUsuario.listarOfertasLaborales(nombreEmpresaTest);
 			assertEquals(dtOL[0].getNombre(), nombreOL);
 			assertEquals(dtOL[0].getDescripcion(), descripcionOL);
 			assertEquals(dtOL[0].getCiudad(), ciudadOL);
 			assertEquals(dtOL[0].getDepartamente(), departamentoOL);
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
			contUsuario.ingresarDatosEmpresa(uEmp);
		}catch(ExisteUnUsuarioYaRegistradoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		assertThrows(OfertasLaboralesNoExistenException.class, ()->{contUsuario.listarOfertasLaborales(nombreEmpresaTest);});
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
			contUsuario.ingresarDatosEmpresa(uEmp);
		}catch(ExisteUnUsuarioYaRegistradoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		contUsuario.modificarEmpresa(nicknameViejo, nombreNuevo, apellidoNuevo, nombreEmpresaNuevo, descripcionNuevo, linkNuevo);
		
		DTUsuario du = contUsuario.mostrarInformacionUsuario(nicknameViejo);
		DTEmpresa de = (DTEmpresa) du;
		assertEquals(de.getNombre(), nombreNuevo);
		assertEquals(de.getApellido(), apellidoNuevo);
		assertEquals(de.getNombreDeEmpresa(), nombreEmpresaNuevo);
		assertEquals(de.getDescripcion(), descripcionNuevo);
		assertEquals(de.getLink(), linkNuevo);
	}
}
