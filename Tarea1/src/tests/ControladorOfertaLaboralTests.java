package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;
import java.util.ArrayList;

import excepciones.NoHayPaquetesException;
import excepciones.NoHayTiposException;
import excepciones.PaqueteRepetidoException;
import excepciones.PaqueteYaCompradoException;
import excepciones.TipoRepetidoException;
import excepciones.TipoYaAgragadoException;
import excepciones.YaSePostuloException;
import excepciones.ExisteUnUsuarioYaRegistradoException;
import excepciones.OfertaLaboralRepetidaException;
import excepciones.OfertasLaboralesNoExistenNingunaException;
import excepciones.KeywordsNoExistenException;
import excepciones.NoExistenOfertasSeleccionarPostulanteException;
import excepciones.TipoPubNoExistenException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import logica.Factory;
import logica.IOfertaLaboral;
import logica.IUsuario;
import logica.Keyword;
import logica.Tipo;
import logica.Paquete;
import logica.PaqueteTipo;

import logica.Postulacion;
import logica.Postulante;
import logica.OfertaLaboral;
import logica.DTOfertaLaboral;
import logica.DTPaquete;
import logica.DTPostulante;
import logica.DTPaqueteTipo;
import logica.DTPostulacion;
import logica.DTTipo;
import logica.Empresa;
import logica.EstadoOL;
import logica.Compra;
import logica.DTCompra;
import logica.DTEmpresa;
import logica.DTKeyword;
import logica.ManejadorOfertaLaboral;
import logica.ManejadorTipo;
import logica.ManejadorUsuario;

class ControladorOfertaLaboralTests{

	private static IOfertaLaboral controladorOfertaLaboral;
	private static IUsuario controladorUsuario;
	private ManejadorOfertaLaboral mol;
	private ManejadorTipo mt;
	private ManejadorUsuario mu;

	
	@BeforeAll
	public static void iniciar(){
		Factory fabrica = Factory.getInstance();
		controladorOfertaLaboral = fabrica.getIOfertaLaboral();
		controladorUsuario =fabrica.getIUsuario();
	}

	@AfterEach
	public void borrar(){
		mol = ManejadorOfertaLaboral.getInstance();
		mol.limpiar();
		mt = ManejadorTipo.getInstancia();
		mt.limpiar();
		mu = ManejadorUsuario.getInstancia();
		mu.borrarUsuarios();
	}
	
	@Test
	void testIngresarKeyword(){
		String nombre = "nombreKeyword";
			controladorOfertaLaboral.ingresarKeyword(nombre);
			mol = ManejadorOfertaLaboral.getInstance();
			Keyword k = mol.buscarKeyword(nombre);
			assertEquals(k.getNombre(), nombre);
	}
	
	@Test
	void testIngresarTipo(){
		String nombre = "nombreT1";
		String descripcion = "descripcionT1";
		 int exposicion = 1;
		 int duracion = 1;
		 float costo = 1;
		 Date fechaAlta = new Date(1597891200000L); // 20 de agosto 2020
		try {
			controladorOfertaLaboral.ingresarTipo(nombre, descripcion, exposicion, duracion, costo, fechaAlta);
			mt = ManejadorTipo.getInstancia();
			Tipo t = mt.buscarTipo(nombre); 
			
			assertEquals(t.getNombre(), nombre);
			assertEquals(t.getDescripcion(), descripcion);
			assertEquals(t.getExposicion(), exposicion);
			assertEquals(t.getDuracion(), duracion);
			assertEquals(t.getCosto(), costo);
			assertEquals(t.getFechaDeAlta(), fechaAlta.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()); //se pasa la date a localDate para comparar
			
		}catch (TipoRepetidoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	void testTipoRepetido() {
		String nombre = "nombreT2";
		String descripcion = "descripcionT2";
		 int exposicion = 2;
		 int duracion = 2;
		 float costo = 2;
		 Date fechaAlta = new Date(1597891200000L); // 20 de agosto 2020
		 try {
				controladorOfertaLaboral.ingresarTipo(nombre, descripcion, exposicion, duracion, costo, fechaAlta);
		 }catch (TipoRepetidoException e) {
				fail(e.getMessage());
				e.printStackTrace();
			}
		 
		 assertThrows(TipoRepetidoException.class, ()->{controladorOfertaLaboral.ingresarTipo(nombre, descripcion, exposicion, duracion, costo, fechaAlta);});
	}
	
	@Test
	void testIngresarDatosPaquete() {
		String nombre ="nombreP1";
		String descripcion = "descripcionP1";
		int periodoDeValidez = 1;
		float descuento= 1;
		float costoAsociado = 1;
		DTPaqueteTipo[] dtpaq = new DTPaqueteTipo[1];//arreglo vacio para hacer el DT que no se usa despues
		LocalDate fechaDeAlta  = LocalDate.of(2020, 8, 20);
		List<PaqueteTipo> paquetesTipos = new ArrayList<>();//lista vacia para comparar
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String dateString = formatter.format(fechaDeAlta);
		DTPaquete 	dtp = new DTPaquete(nombre, descripcion, periodoDeValidez, descuento, costoAsociado, dtpaq, dateString);
		
		try {
			controladorOfertaLaboral.ingresarDatosPaquete(dtp);
			mt = ManejadorTipo.getInstancia();
			Paquete p = mt.buscarPaquete(nombre);
			assertEquals(p.getNombre(), nombre);
			assertEquals(p.getDescripcion(), descripcion);
			assertEquals(p.getDescuento(), descuento);
			assertEquals(p.getPaquetesTipos(), paquetesTipos);
			//assertEquals(p.getCostoAsociado(), costoAsociado);
			assertEquals(p.getFechaDeAlta(), fechaDeAlta);
			
		}catch(PaqueteRepetidoException	e) {
			fail(e.getMessage());
			e.printStackTrace();
		}

	}
	
	@Test
	void testIngregarPaqueteRepetido() {
		String nombre ="nombreP2";
		String descripcion = "descripcionP2";
		int periodoDeValidez = 2;
		float descuento= 2;
		float costoAsociado = 2;
		DTPaqueteTipo[] dtpaq = new DTPaqueteTipo[1];//arreglo vacio para hacer el DT que no se usa despues
		LocalDate fechaDeAlta  = LocalDate.of(2020, 8, 20);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String dateString = formatter.format(fechaDeAlta);
		
		DTPaquete 	dtp = new DTPaquete(nombre, descripcion, periodoDeValidez, descuento, costoAsociado, dtpaq, dateString);
		try {
			controladorOfertaLaboral.ingresarDatosPaquete(dtp);
		}catch(PaqueteRepetidoException	e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		assertThrows(PaqueteRepetidoException.class, ()->{controladorOfertaLaboral.ingresarDatosPaquete(dtp);});
		
	}
	
	@Test
	void listarNomTiposTest() {
		mt = ManejadorTipo.getInstancia();
		String[] nomtipos = new String[1];
		String nombre = "nombreT1";
		String descripcion = "descripcionT1";
		int exposicion = 1;
		int duracion = 1;
		float costo = 1;
		LocalDate fechaAlta = LocalDate.now();
		Tipo tipo = new Tipo(nombre, descripcion, exposicion, duracion, costo, fechaAlta);
		mt.agregarTipo(tipo);
		nomtipos[0] = nombre;
		try {
			String[] res = controladorOfertaLaboral.listarNomTipos();
			assertEquals(nomtipos[0], res[0]);
		} catch (NoHayTiposException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	void testListarPaquetes() {
		String nombre ="nombreP3";
		String descripcion = "descripcionP3";
		int periodoDeValidez = 3;
		float descuento= 3;
		float costoAsociado = 3;
		DTPaqueteTipo[] dtpaq = new DTPaqueteTipo[1];//arreglo vacio para hacer el DT que no se usa despues
		LocalDate fechaDeAlta  = LocalDate.of(2020, 8, 20);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String dateString = formatter.format(fechaDeAlta);
		DTPaquete 	dtp = new DTPaquete(nombre, descripcion, periodoDeValidez, descuento, costoAsociado, dtpaq, dateString);
		try {
			controladorOfertaLaboral.ingresarDatosPaquete(dtp);
			DTPaquete[] p = controladorOfertaLaboral.listarPaquetes();
			assertEquals(p[0].getNombre(), nombre);
			assertEquals(p[0].getDescripcion(), descripcion);
			assertEquals(p[0].getDescuento(), descuento);
			assertEquals(p[0].getFechaDeAlta(), dateString);
		}catch(PaqueteRepetidoException	e) {
			fail(e.getMessage());
			e.printStackTrace();
		}catch (NoHayPaquetesException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	void listarNomTiposVacioTest() {
		assertThrows(NoHayTiposException.class, ()->{controladorOfertaLaboral.listarNomTipos();});
	}
	
	@Test
	void listarKeywordsVaciasTest() {
		assertThrows(KeywordsNoExistenException.class, ()->{controladorOfertaLaboral.listarKeywords();});
	}
	
	@Test
	void listarNomPaquetesTest() {
		mt = ManejadorTipo.getInstancia();
		String nombrep ="nombreP1";
		String descripcionp = "descripcionP1";
		int periodoDeValidez = 1;
		float descuento= 1;
		float costoAsociado = 1;
		LocalDate fechaDeAlta  = LocalDate.of(2020, 8, 20);
		Paquete paq = new Paquete(nombrep, descripcionp, periodoDeValidez, descuento, costoAsociado, fechaDeAlta);
		mt.agregarPaquete(paq);
		try {
			String[] paqs = controladorOfertaLaboral.listarNomPaquetes();
			for (String p : paqs) {
				Paquete pf = mt.buscarPaquete(p);
				assertEquals(pf.getNombre(), nombrep);
				assertEquals(pf.getDescripcion(), descripcionp);
				assertEquals(pf.getPeriodoDeValidez(), periodoDeValidez);
				assertEquals(pf.getDescuento(), descuento);
				assertEquals(pf.getCostoAsociado(), costoAsociado);
				assertEquals(pf.getFechaDeAlta(), fechaDeAlta);
			}
		} catch (NoHayPaquetesException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	void testListarPaquetesVacio() {
		assertThrows(NoHayPaquetesException.class, ()->{controladorOfertaLaboral.listarPaquetes();});
	}
	
	@Test
	void testDatosPaqueteAMostrar() {
		String nombre ="nombreP4";
		String descripcion = "descripcionP4";
		int periodoDeValidez = 4;
		float descuento= 4;
		float costoAsociado = 4;
		DTPaqueteTipo[] dtpaq = new DTPaqueteTipo[1];//arreglo vacio para hacer el DT que no se usa despues
		LocalDate fechaDeAlta  = LocalDate.of(2020, 8, 20);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateString = formatter.format(fechaDeAlta);
		DTPaquete 	dtp = new DTPaquete(nombre, descripcion, periodoDeValidez, descuento, costoAsociado, dtpaq, dateString);
		
		String datos = controladorOfertaLaboral.datosPaqueteAMostrar(dtp);
		String respuestaOK = "Nombre: " + nombre + "\nDescripcion: " + descripcion + "\nPeriodo de validez: " + periodoDeValidez + " días\nDescuento: " + descuento + "%\nCosto: $" + costoAsociado;
		assertEquals(datos, respuestaOK);
	}
	
	@Test
	void testIngresarDatosOL() {
		String nombreK = "nombreKeyword";
		
		controladorOfertaLaboral.ingresarKeyword(nombreK);
		//datos tipo
		String nombreT = "nombreT3";
		String descripcionT = "descripcionT3";
		 int exposicionT = 3;
		 int duracionT = 300000;
		 float costoT = 3;
		 Date fechaAltaT = new Date(1597891200000L); // 20 de agosto 2020
		 
		 //datos empresa
		 String nicknameE ="nicke1";
		 String nombreE ="nome1";
		 String apellido= "ape1";
		 String correo= "coe1";
		 String descripcion = "dese1";
		 String link = "linke1";
		 
		 String empresa = nicknameE;
			String nombreTipo = nombreT;
			//datos oferta
			String nombre= "nombreOL1";
			String descripcionOL= "descOL1";
			String ciudad = "ciudOL1";
			String departamento = "depOL1";
			String horario = "hoeOL1";
			float remuneracion =1;
			LocalDate fechaDeAlta = LocalDate.of(2020, 8, 20);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        String dateString = formatter.format(fechaDeAlta);
			float costoAsociado = costoT;

	        // Convierte el objeto Date a una cadena 
	        String dateStringOL = formatter.format(fechaDeAlta);
			DTTipo dataTipo = new DTTipo(nombreT, descripcionT, exposicionT, duracionT, costoT, dateString);
			DTKeyword[] dataKeywords = new DTKeyword[1];
			DTKeyword dtk = new DTKeyword(nombreK);
			dataKeywords[0] = dtk;
		 
		 DTEmpresa dte = new DTEmpresa(nicknameE, nombreE, apellido, correo, "contra", null, descripcion, link, null, null);

		 
		try {
			
			controladorOfertaLaboral.ingresarTipo(nombreT, descripcionT, exposicionT, duracionT, costoT, fechaAltaT);
			
			controladorUsuario.ingresarDatosEmpresa(dte); 
			mt = ManejadorTipo.getInstancia();
			Tipo tOL = mt.buscarTipo(nombreTipo);
			DTOfertaLaboral ol = new DTOfertaLaboral(nombre, descripcionOL, ciudad, departamento, horario, remuneracion, dateStringOL, dateStringOL, 3, costoAsociado, dataTipo, dataKeywords, null, empresa, EstadoOL.Confirmada, false);
			controladorOfertaLaboral.ingresarDatosOL(empresa,  nombreTipo, ol);
			mol = ManejadorOfertaLaboral.getInstance();
			OfertaLaboral Ol = mol.buscarOfertaLaboral(nombre);
			assertEquals(Ol.getNombre(), nombre);
			assertEquals(Ol.getCiudad(), ciudad);
			assertEquals(Ol.getDepartamento(), departamento);
			assertEquals(Ol.getDescripcion(), descripcionOL);
			assertEquals(Ol.getFechaDeAlta(), fechaDeAlta);
			assertEquals(Ol.getCostoAsociado(), costoAsociado);
			assertTrue(Ol.getTipoOL().equals(tOL));
			assertEquals(Ol.getKeywords().keySet().iterator().next(), nombreK);
			
		}catch (TipoRepetidoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}catch (ExisteUnUsuarioYaRegistradoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}catch (OfertaLaboralRepetidaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	void listarNomPaquetesVacioTest() {
		assertThrows(NoHayPaquetesException.class, ()->{controladorOfertaLaboral.listarNomPaquetes();});
	}
	
	@Test
	void agregarTipoAPaqueteTest() {
		mt = ManejadorTipo.getInstancia();
		String nombre = "nombreT1";
		String descripcion = "descripcionT1";
		int exposicion = 1;
		int duracion = 1;
		float costo = 1;
		LocalDate fechaAlta = LocalDate.now();
		Tipo t = new Tipo(nombre, descripcion, exposicion, duracion, costo, fechaAlta);
		mt.agregarTipo(t);
		String nombrep ="nombreP1";
		String descripcionp = "descripcionP1";
		int periodoDeValidez = 1;
		float descuento= 1;
		float costoAsociado = 1;
		LocalDate fechaDeAlta  = LocalDate.of(2020, 8, 20);
		Paquete paq = new Paquete(nombrep, descripcionp, periodoDeValidez, descuento, costoAsociado, fechaDeAlta);
		mt.agregarPaquete(paq);
		try {
			controladorOfertaLaboral.agregarTipoAPaquete(5, "nombreP1", "nombreT1");
			Paquete pqb = mt.buscarPaquete("nombreP1");
			Tipo tb = mt.buscarTipo("nombreT1");
			List<PaqueteTipo> paqtips = pqb.getPaquetesTipos();
			for (PaqueteTipo paqtip : paqtips) {
				assertEquals(tb.getNombre(), paqtip.getTipo().getNombre());
				assertEquals(tb.getDescripcion(), paqtip.getTipo().getDescripcion());
				assertEquals(tb.getExposicion(), paqtip.getTipo().getExposicion());
				assertEquals(tb.getDuracion(), paqtip.getTipo().getDuracion());
				assertEquals(tb.getCosto(), paqtip.getTipo().getCosto());
				assertEquals(tb.getFechaDeAlta(), paqtip.getTipo().getFechaDeAlta());
			}
		} catch(TipoYaAgragadoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	void testIngresarDatosOLRepetido() {
		String nombreK = "nombreKeyword";
		
		controladorOfertaLaboral.ingresarKeyword(nombreK);
		//datos tipo
		String nombreT = "nombreT3";
		String descripcionT = "descripcionT3";
		 int exposicionT = 3;
		 int duracionT = 300000;
		 float costoT = 3;
		 Date fechaAltaT = new Date(1597891200000L); // 20 de agosto 2020
		 
		 //datos empresa
		 String nicknameE ="nicke2";
		 String nombreE ="nome2";
		 String apellido= "ape1";
		 String correo= "coe1";
		 String descripcion = "dese1";
		 String link = "linke1";
		 
		 String empresa = nicknameE;
			String nombreTipo = nombreT;
			//datos oferta
			String nombre= "nombreOL1";
			String descripcionOL= "descOL1";
			String ciudad = "ciudOL1";
			String departamento = "depOL1";
			String horario = "hoeOL1";
			float remuneracion =1;
			LocalDate fechaDeAlta = LocalDate.of(2020, 8, 20);
			float costoAsociado = costoT;
			DTTipo dataTipo = new DTTipo(nombreT, descripcionT, exposicionT, duracionT, costoT, "20-08-2022");
			DTKeyword[] dataKeywords = new DTKeyword[1];
			DTKeyword dtk = new DTKeyword(nombreK);
			dataKeywords[0] = dtk;
		 
		 DTEmpresa dte = new DTEmpresa(nicknameE, nombreE, apellido, correo, "contra", null, descripcion, link, null, null);
		 
		try {
			
			controladorOfertaLaboral.ingresarTipo(nombreT, descripcionT, exposicionT, duracionT, costoT, fechaAltaT);
			
			controladorUsuario.ingresarDatosEmpresa(dte); 
			mt = ManejadorTipo.getInstancia();
			Tipo tOL = mt.buscarTipo(nombreTipo);
			DTOfertaLaboral ol = new DTOfertaLaboral(nombre, descripcionOL, ciudad, departamento, horario, remuneracion, "2020-08-20", "2020-08-22", 3, costoAsociado, dataTipo, dataKeywords, null, empresa, EstadoOL.Confirmada, false);
			controladorOfertaLaboral.ingresarDatosOL(empresa,  nombreTipo, ol);
			mol = ManejadorOfertaLaboral.getInstance();
			OfertaLaboral Ol = mol.buscarOfertaLaboral(nombre);
			assertEquals(Ol.getNombre(), nombre);
			assertEquals(Ol.getCiudad(), ciudad);
			assertEquals(Ol.getDepartamento(), departamento);
			assertEquals(Ol.getDescripcion(), descripcionOL);
			assertEquals(Ol.getFechaDeAlta(), fechaDeAlta);
			assertEquals(Ol.getCostoAsociado(), costoAsociado);
			assertTrue(Ol.getTipoOL().equals(tOL));
			assertEquals(Ol.getKeywords().keySet().iterator().next(), nombreK);
			
		}catch (TipoRepetidoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}catch (ExisteUnUsuarioYaRegistradoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}catch (OfertaLaboralRepetidaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		DTOfertaLaboral ol = new DTOfertaLaboral(nombre, descripcionOL, ciudad, departamento, horario, remuneracion, "2020-08-22", "2020-08-22", 3, costoAsociado, dataTipo, dataKeywords, null, empresa, EstadoOL.Confirmada, false);
		assertThrows(OfertaLaboralRepetidaException.class, ()->{controladorOfertaLaboral.ingresarDatosOL(empresa,  nombreTipo, ol);});
	}
	
	@Test
	void testListarKeywords() {
		String nombre = "nombreKeyword2";
		controladorOfertaLaboral.ingresarKeyword(nombre);
		try {
		String[] ke = controladorOfertaLaboral.listarKeywords();
		assertEquals(ke[0], nombre);
		}catch (KeywordsNoExistenException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	void agregarTipoRepetidoAPaqueteTest() {
		mt = ManejadorTipo.getInstancia();
		String nombre = "nombreT1";
		String descripcion = "descripcionT1";
		int exposicion = 1;
		int duracion = 1;
		float costo = 1;
		LocalDate fechaAlta = LocalDate.now();
		Tipo t = new Tipo(nombre, descripcion, exposicion, duracion, costo, fechaAlta);
		mt.agregarTipo(t);
		String nombrep ="nombreP1";
		String descripcionp = "descripcionP1";
		int periodoDeValidez = 1;
		float descuento= 1;
		float costoAsociado = 1;
		LocalDate fechaDeAlta  = LocalDate.of(2020, 8, 20);
		Paquete paq = new Paquete(nombrep, descripcionp, periodoDeValidez, descuento, costoAsociado, fechaDeAlta);
		mt.agregarPaquete(paq);
		try {
			controladorOfertaLaboral.agregarTipoAPaquete(5, "nombreP1", "nombreT1");
		} catch(TipoYaAgragadoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		assertThrows(TipoYaAgragadoException.class, ()->{controladorOfertaLaboral.agregarTipoAPaquete(5, "nombreP1", "nombreT1");});
	}
	
	@Test
	void mostrarDatosOfertaLaboralTest() {
		mt = ManejadorTipo.getInstancia();
		ManejadorOfertaLaboral mof = ManejadorOfertaLaboral.getInstance();
		String nombre = "nombreT1";
		String descripcion = "descripcionT1";
		int exposicion = 1;
		int duracion = 1;
		float costo = 1;
		LocalDate fechaAlta = LocalDate.now();
		Tipo t = new Tipo(nombre, descripcion, exposicion, duracion, costo, fechaAlta);
		Map<String, Keyword> keywords = new HashMap<>();
		Empresa emp = new Empresa("a", "b", "c", "d", "e", "f");
		OfertaLaboral of = new OfertaLaboral("OF1", "DESC", "CIUDAD", "DEPARTAMENTO", "HORARIO", 50f, LocalDate.now(), 50f, t, keywords, emp);
		mt.agregarTipo(t);
		mof.agregarOfertaLaboral(of);
		DTOfertaLaboral datos = controladorOfertaLaboral.mostrarDatosOfertaLaboral(of.getNombre());
		String dateString = datos.getFechaDeAlta();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateLocalDate = LocalDate.parse(dateString, formatter);
		assertEquals(of.getNombre(), datos.getNombre());
		assertEquals(of.getDescripcion(), datos.getDescripcion());
		assertEquals(of.getCiudad(), datos.getCiudad());
		assertEquals(of.getDepartamento(), datos.getDepartamento());
		assertEquals(of.getHorario(), datos.getHorario());
		assertEquals(of.getRemuneracion(), datos.getRemuneracion());
		assertEquals(of.getFechaDeAlta(), dateLocalDate);
		assertEquals(of.getCostoAsociado(), datos.getCostoAsociado());
		assertEquals(of.getTipoOL().getNombre(), datos.getTipo().getNombre());
		assertEquals(of.getKeywords().size(), datos.getKeywords().length);
		assertEquals(of.getPostulaciones().size(), datos.getPostulaciones().size());
		assertEquals(of.getEmpresaCreadora(), datos.getDTEmpresa());
	}
	
	void testVacioListarKeywords() {
		assertThrows(KeywordsNoExistenException.class, ()->{controladorOfertaLaboral.listarKeywords();});
	}
	
	@Test
	void testListarTipoPublicacionOfertaLaboral() {
		String nombre = "nombreT1";
		String descripcion = "descripcionT1";
		 int exposicion = 1;
		 int duracion = 1;
		 float costo = 1;
		 Date fechaAlta = new Date(1597891200000L); // 20 de agosto 2020
		try {
			controladorOfertaLaboral.ingresarTipo(nombre, descripcion, exposicion, duracion, costo, fechaAlta);
			DTTipo[] t = controladorOfertaLaboral.listarTipoPublicacionOfertaLaboral();
			
			assertEquals(t[0].getNombre(), nombre);
			assertEquals(t[0].getDescripcion(), descripcion);
			assertEquals(t[0].getExposicion(), exposicion);
			assertEquals(t[0].getDuracion(), duracion);
			assertEquals(t[0].getCosto(), costo);
			//assertEquals(t[0].getFechaDeAlta(), ); //se pasa la date a localDate para comparar
			
		}catch (TipoRepetidoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}catch (TipoPubNoExistenException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	@Test
	void testVacioListarTipoPublicacionOfertaLaboral() {
		assertThrows(TipoPubNoExistenException.class, ()->{controladorOfertaLaboral.listarTipoPublicacionOfertaLaboral();});
	}
	

	@Test 
	void testAceptarOL() {
		String nombreK = "nombreKeyword";
		
		controladorOfertaLaboral.ingresarKeyword(nombreK);
		//datos tipo
		String nombreT = "nombreT3";
		String descripcionT = "descripcionT3";
		 int exposicionT = 3;
		 int duracionT = 300000;
		 float costoT = 3;
		 Date fechaAltaT = new Date(1597891200000L); // 20 de agosto 2020
		 
		 //datos empresa
		 String nicknameE ="nicke1";
		 String nombreE ="nome1";
		 String apellido= "ape1";
		 String correo= "coe1";
		 String descripcion = "dese1";
		 String link = "linke1";
		 
		 String empresa = nicknameE;
			String nombreTipo = nombreT;
			//datos oferta
			String nombre= "nombreOL1";
			String descripcionOL= "descOL1";
			String ciudad = "ciudOL1";
			String departamento = "depOL1";
			String horario = "hoeOL1";
			float remuneracion =1;
			LocalDate fechaDeAlta = LocalDate.of(2020, 8, 20);
			float costoAsociado = costoT;
			DTTipo dataTipo = new DTTipo(nombreT, descripcionT, exposicionT, duracionT, costoT, "1999-03-04");
			DTKeyword[] dataKeywords = new DTKeyword[1];
			DTKeyword dtk = new DTKeyword(nombreK);
			dataKeywords[0] = dtk;
		 
		 DTEmpresa dte = new DTEmpresa(nicknameE, nombreE, apellido, correo, "contra", null, descripcion, link, null, null);

		 
		try {
			
			controladorOfertaLaboral.ingresarTipo(nombreT, descripcionT, exposicionT, duracionT, costoT, fechaAltaT);
			
			controladorUsuario.ingresarDatosEmpresa(dte); 
			mt = ManejadorTipo.getInstancia();
			Tipo tOL = mt.buscarTipo(nombreTipo);
			DTOfertaLaboral ol = new DTOfertaLaboral(nombre, descripcionOL, ciudad, departamento, horario, remuneracion, "2020-08-20", "2020-08-22", 3, costoAsociado, dataTipo, dataKeywords, null, empresa, EstadoOL.Confirmada, false);
			controladorOfertaLaboral.ingresarDatosOL(empresa,  nombreTipo, ol);
			mol = ManejadorOfertaLaboral.getInstance();
			OfertaLaboral Ol = mol.buscarOfertaLaboral(nombre);
			controladorOfertaLaboral.acepRechOL(EstadoOL.Confirmada, nombre);
			assertEquals(Ol.getNombre(), nombre);
			assertEquals(Ol.getCiudad(), ciudad);
			assertEquals(Ol.getDepartamento(), departamento);
			assertEquals(Ol.getDescripcion(), descripcionOL);
			assertEquals(Ol.getFechaDeAlta(), fechaDeAlta);
			assertEquals(Ol.getCostoAsociado(), costoAsociado);
			assertEquals(Ol.getEstado(), EstadoOL.Confirmada);
			assertTrue(Ol.getTipoOL().equals(tOL));
			assertEquals(Ol.getKeywords().keySet().iterator().next(), nombreK);
			
		}catch (TipoRepetidoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}catch (ExisteUnUsuarioYaRegistradoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}catch (OfertaLaboralRepetidaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test 
	void testRechazarOL() {
		String nombreK = "nombreKeyword";
		
		controladorOfertaLaboral.ingresarKeyword(nombreK);
		//datos tipo
		String nombreT = "nombreT3";
		String descripcionT = "descripcionT3";
		 int exposicionT = 3;
		 int duracionT = 300000;
		 float costoT = 3;
		 Date fechaAltaT = new Date(1597891200000L); // 20 de agosto 2020
		 
		 //datos empresa
		 String nicknameE ="nicke1";
		 String nombreE ="nome1";
		 String apellido= "ape1";
		 String correo= "coe1";
		 String descripcion = "dese1";
		 String link = "linke1";
		 
		 String empresa = nicknameE;
			String nombreTipo = nombreT;
			//datos oferta
			String nombre= "nombreOL1";
			String descripcionOL= "descOL1";
			String ciudad = "ciudOL1";
			String departamento = "depOL1";
			String horario = "hoeOL1";
			float remuneracion =1;
			LocalDate fechaDeAlta = LocalDate.of(2020, 8, 20);
			float costoAsociado = costoT;
			DTTipo dataTipo = new DTTipo(nombreT, descripcionT, exposicionT, duracionT, costoT, "2020-08-22");
			DTKeyword[] dataKeywords = new DTKeyword[1];
			DTKeyword dtk = new DTKeyword(nombreK);
			dataKeywords[0] = dtk;
		 
		 DTEmpresa dte = new DTEmpresa(nicknameE, nombreE, apellido, correo, "contra", null, descripcion, link, null, null);

		 
		try {
			
			controladorOfertaLaboral.ingresarTipo(nombreT, descripcionT, exposicionT, duracionT, costoT, fechaAltaT);
			
			controladorUsuario.ingresarDatosEmpresa(dte); 
			mt = ManejadorTipo.getInstancia();
			Tipo tOL = mt.buscarTipo(nombreTipo);
			DTOfertaLaboral ol = new DTOfertaLaboral(nombre, descripcionOL, ciudad, departamento, horario, remuneracion, "2020-08-20", "2020-08-22", 3, costoAsociado, dataTipo, dataKeywords, null, empresa, EstadoOL.Confirmada, false);
			controladorOfertaLaboral.ingresarDatosOL(empresa,  nombreTipo, ol);
			mol = ManejadorOfertaLaboral.getInstance();
			OfertaLaboral Ol = mol.buscarOfertaLaboral(nombre);
			controladorOfertaLaboral.acepRechOL(EstadoOL.Rechazada, nombre);
			assertEquals(Ol.getNombre(), nombre);
			assertEquals(Ol.getCiudad(), ciudad);
			assertEquals(Ol.getDepartamento(), departamento);
			assertEquals(Ol.getDescripcion(), descripcionOL);
			assertEquals(Ol.getFechaDeAlta(), fechaDeAlta);
			assertEquals(Ol.getCostoAsociado(), costoAsociado);
			assertEquals(Ol.getEstado(), EstadoOL.Rechazada);
			assertTrue(Ol.getTipoOL().equals(tOL));
			assertEquals(Ol.getKeywords().keySet().iterator().next(), nombreK);
			
		}catch (TipoRepetidoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}catch (ExisteUnUsuarioYaRegistradoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}catch (OfertaLaboralRepetidaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	void testListarTodasOfertasLaboralesOK() {
String nombreK = "nombreKeyword";
		
		controladorOfertaLaboral.ingresarKeyword(nombreK);
		//datos tipo
		String nombreT = "nombreT3";
		String descripcionT = "descripcionT3";
		 int exposicionT = 3;
		 int duracionT = 300000;
		 float costoT = 3;
		 Date fechaAltaT = new Date(1597891200000L); // 20 de agosto 2020
		 
		 //datos empresa
		 String nicknameE ="nicke1";
		 String nombreE ="nome1";
		 String apellido= "ape1";
		 String correo= "coe1";
		 String descripcion = "dese1";
		 String link = "linke1";
		 
		 String empresa = nicknameE;
			String nombreTipo = nombreT;
			//datos oferta
			String nombre= "nombreOL1";
			String descripcionOL= "descOL1";
			String ciudad = "ciudOL1";
			String departamento = "depOL1";
			String horario = "hoeOL1";
			float remuneracion =1;
			float costoAsociado = costoT;
			DTTipo dataTipo = new DTTipo(nombreT, descripcionT, exposicionT, duracionT, costoT, "2020-08-22");
			DTKeyword[] dataKeywords = new DTKeyword[1];
			DTKeyword dtk = new DTKeyword(nombreK);
			dataKeywords[0] = dtk;
		 
		 DTEmpresa dte = new DTEmpresa(nicknameE, nombreE, apellido, correo, "contra", null, descripcion, link, null, null);

		 
		try {
			
			controladorOfertaLaboral.ingresarTipo(nombreT, descripcionT, exposicionT, duracionT, costoT, fechaAltaT);
			
			controladorUsuario.ingresarDatosEmpresa(dte); 
			mt = ManejadorTipo.getInstancia();
			DTOfertaLaboral ol = new DTOfertaLaboral(nombre, descripcionOL, ciudad, departamento, horario, remuneracion, "2020-08-20", "2020-08-22", 3, costoAsociado, dataTipo, dataKeywords, null, empresa, EstadoOL.Confirmada, false);
			controladorOfertaLaboral.ingresarDatosOL(empresa,  nombreTipo, ol);
			mol = ManejadorOfertaLaboral.getInstance();
			DTOfertaLaboral[] ols = controladorOfertaLaboral.listarTodasOfertasLaborales();
			assertEquals(ols[0].getNombre(), nombre);
			assertEquals(ols[0].getCiudad(), ciudad);
			assertEquals(ols[0].getDepartamento(), departamento);
			assertEquals(ols[0].getDescripcion(), descripcionOL);
			assertEquals(ols[0].getFechaDeAlta(), "2020-08-20");
			assertEquals(ols[0].getCostoAsociado(), costoAsociado);
			assertEquals(ols[0].getKeywords()[0].getNombre(), nombreK);
			
		}catch (TipoRepetidoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}catch (ExisteUnUsuarioYaRegistradoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}catch (OfertaLaboralRepetidaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch(OfertasLaboralesNoExistenNingunaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	void testListarTodasOfertasLaboralesNiguna() {
		assertThrows(OfertasLaboralesNoExistenNingunaException.class, ()->{controladorOfertaLaboral.listarTodasOfertasLaborales();});
	}
	
	@Test 
	void testComprarPaquetes() {
		//Datos tipo
		String nombreT = "nombreT3";
		String descripcionT = "descripcionT3";
		 int exposicionT = 3;
		 int duracionT = 300000;
		 float costoT = 3;
		 Tipo tip = new Tipo(nombreT, descripcionT, exposicionT, duracionT, costoT, LocalDate.now());
		 
		 //datos empresa
		 String nicknameE ="nicke1";
		 String nombreE ="nome1";
		 String apellido= "ape1";
		 String correo= "coe1";
		 String descripcion = "dese1";
		 String link = "linke1";
	
		//Datos paquetes
		Paquete paq = new Paquete("paq", "desc", 100, 25, 200, LocalDate.now());
		Paquete paq1 = new Paquete("paqe", "desc", 100, 25, 200, LocalDate.now());
		mt = ManejadorTipo.getInstancia();
		mu = ManejadorUsuario.getInstancia();
		mt.agregarPaquete(paq);
		mt.agregarPaquete(paq1);
		mt.agregarTipo(tip);
		Empresa emp = new Empresa(nicknameE, nombreE, apellido, correo, descripcion, link);
		mu.agregarEmpresa(emp);
		PaqueteTipo paqTip = new PaqueteTipo(3, tip);
		paq.agregarPaqueteTipo(paqTip);
		paq1.agregarPaqueteTipo(paqTip);
		try {
			controladorOfertaLaboral.comprarPaquete(nicknameE, "paq");
		} catch (PaqueteYaCompradoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		assertThrows(PaqueteYaCompradoException.class, ()->{controladorOfertaLaboral.comprarPaquete(nicknameE, "paq");});
		
	}
	
	@Test 
	void testComprarPaquetesRepetido() {
		//Datos tipo
		String nombreT = "nombreT3";
		String descripcionT = "descripcionT3";
		 int exposicionT = 3;
		 int duracionT = 300000;
		 float costoT = 3;
		 Tipo tip = new Tipo(nombreT, descripcionT, exposicionT, duracionT, costoT, LocalDate.now());
		 
		 //datos empresa
		 String nicknameE ="nicke1";
		 String nombreE ="nome1";
		 String apellido= "ape1";
		 String correo= "coe1";
		 String descripcion = "dese1";
		 String link = "linke1";
	
		//Datos paquetes
		Paquete paq = new Paquete("paq", "desc", 100, 25, 200, LocalDate.now());
		Paquete paq1 = new Paquete("paqe", "desc", 100, 25, 200, LocalDate.now());
		mt = ManejadorTipo.getInstancia();
		mu = ManejadorUsuario.getInstancia();
		mt.agregarPaquete(paq);
		mt.agregarPaquete(paq1);
		mt.agregarTipo(tip);
		Empresa emp = new Empresa(nicknameE, nombreE, apellido, correo, descripcion, link);
		mu.agregarEmpresa(emp);
		PaqueteTipo paqTip = new PaqueteTipo(3, tip);
		paq.agregarPaqueteTipo(paqTip);
		paq1.agregarPaqueteTipo(paqTip);
		try {
			controladorOfertaLaboral.comprarPaquete(nicknameE, "paq");
		} catch (PaqueteYaCompradoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testDataPostulacion() {
String nombreK = "nombreKeyword";
		
		controladorOfertaLaboral.ingresarKeyword(nombreK);
		//datos tipo
		String nombreT = "nombreT3";
		String descripcionT = "descripcionT3";
		 int exposicionT = 3;
		 int duracionT = 300000;
		 float costoT = 3;
		 Date fechaAltaT = new Date(1597891200000L); // 20 de agosto 2020
		 //datos postulante

		 String nicknameP ="nickp";
		 String nombreP ="nomp";
		 String apellidoP= "apep";
		 String correoP= "cop";
		 String nacionalidadP = "NacP";
		 
		 DTPostulante dtPost = new DTPostulante(nicknameP, nombreP, apellidoP, correoP,"contra", "2020-08-22", nacionalidadP, null, null, null);
		 //datos empresa
		 String nicknameE ="nicke1";
		 String nombreE ="nome1";
		 String apellido= "ape1";
		 String correo= "coe1";
		 String descripcion = "dese1";
		 String link = "linke1";
		 
		 String empresa = nicknameE;
			String nombreTipo = nombreT;
			//datos oferta
			String nombre= "nombreOL1";
			String descripcionOL= "descOL1";
			String ciudad = "ciudOL1";
			String departamento = "depOL1";
			String horario = "hoeOL1";
			float remuneracion =1;
			float costoAsociado = costoT;
			DTTipo dataTipo = new DTTipo(nombreT, descripcionT, exposicionT, duracionT, costoT, "2020-08-22");
			DTKeyword[] dataKeywords = new DTKeyword[1];
			DTKeyword dtk = new DTKeyword(nombreK);
			dataKeywords[0] = dtk;
		 
		 DTEmpresa dte = new DTEmpresa(nicknameE, nombreE, apellido, correo, "contra", null, descripcion, link, null, null);

		 
		try {
			controladorOfertaLaboral.ingresarTipo(nombreT, descripcionT, exposicionT, duracionT, costoT, fechaAltaT);
			controladorUsuario.ingresarDatosPostulante(dtPost);
			controladorUsuario.ingresarDatosEmpresa(dte); 
			mt = ManejadorTipo.getInstancia();
			DTOfertaLaboral ol = new DTOfertaLaboral(nombre, descripcionOL, ciudad, departamento, horario, remuneracion, "2020-08-22", "2020-08-22", 3, costoAsociado, dataTipo, dataKeywords, null, empresa, EstadoOL.Confirmada, false);
			controladorOfertaLaboral.ingresarDatosOL(empresa,  nombreTipo, ol);
			mol = ManejadorOfertaLaboral.getInstance();
			OfertaLaboral Ol = mol.buscarOfertaLaboral(nombre);
			mu = ManejadorUsuario.getInstancia();
			Postulante postu = mu.buscarPostulante(nicknameP);
			LocalDate local = LocalDate.now();
			Postulacion pos = new Postulacion(local, "cvReducido","motivacion", postu, Ol, "");
			
			postu.agregarPostulacion(pos);
		
			
			List<Postulacion> posts = postu.getPostulaciones();
			assertEquals(posts.get(0).getCVReducido(),  "cvReducido");
			assertEquals(posts.get(0).getMotivacion(),  "motivacion");
			assertEquals(posts.get(0).getPostulante().getNickname(),  postu.getNickname());
			assertEquals(posts.get(0).getOfertaLaboral().getNombre(),  Ol.getNombre());
			
			
		}catch (TipoRepetidoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}catch (ExisteUnUsuarioYaRegistradoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}catch (OfertaLaboralRepetidaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	void testDataCompra() {
        //Datos tipo
        String nombreT = "nombreT3";
        String descripcionT = "descripcionT3";
         int exposicionT = 3;
         int duracionT = 300000;
         float costoT = 3;
         Tipo tip = new Tipo(nombreT, descripcionT, exposicionT, duracionT, costoT, LocalDate.now());

         //datos empresa
         String nicknameE ="nicke1";
         String nombreE ="nome1";
         String apellido= "ape1";
         String correo= "coe1";
         String descripcion = "dese1";
         String link = "linke1";

        //Datos paquetes
        Paquete paq = new Paquete("paq", "desc", 100, 25, 200, LocalDate.now());
        Paquete paq1 = new Paquete("paqe", "desc", 100, 25, 200, LocalDate.now());
        mt = ManejadorTipo.getInstancia();
        mu = ManejadorUsuario.getInstancia();
        mt.agregarPaquete(paq);
        mt.agregarPaquete(paq1);
        mt.agregarTipo(tip);
        Empresa emp = new Empresa(nicknameE, nombreE, apellido, correo, descripcion, link);
        mu.agregarEmpresa(emp);
        PaqueteTipo paqTip = new PaqueteTipo(3, tip);
        paq.agregarPaqueteTipo(paqTip);
        paq1.agregarPaqueteTipo(paqTip);
        try {
            controladorOfertaLaboral.comprarPaquete(nicknameE, "paq");
            Compra comp = emp.getCompras().get(0);
            DTCompra dtc = comp.getDataCompra();
            String dateString = dtc.getFechaCompra();
            String vencimientoString = dtc.getFechaVencimiento();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dateLocalDate = LocalDate.parse(dateString, formatter);
            LocalDate vencimentoLocalDate = LocalDate.parse(vencimientoString, formatter);
            assertEquals(comp.getFechaCompra(), dateLocalDate);
            assertEquals(comp.getFechaVencimiento(), vencimentoLocalDate);

        } catch (PaqueteYaCompradoException e) {
            fail(e.getMessage());
            e.printStackTrace();
        }
    }
	
	@Test
	  void testRealizarSeleccion(){
	    String nombreK = "nombreKeyword";
	    
	    controladorOfertaLaboral.ingresarKeyword(nombreK);
	    //datos tipo
	    String nombreT = "nombreT3";
	    String descripcionT = "descripcionT3";
	     int exposicionT = 3;
	     int duracionT = 300000;
	     float costoT = 3;
	     Date fechaAltaT = new Date(1597891200000L); // 20 de agosto 2020
	     //datos postulante

	     String nicknameP ="nickp";
	     String nombreP ="nomp";
	     String apellidoP= "apep";
	     String correoP= "cop";
	     String nacionalidadP = "NacP";
	     
	     DTPostulante dtPost = new DTPostulante(nicknameP, nombreP, apellidoP, correoP,"contra", "2020-08-22", nacionalidadP, null, null, null);
	     //datos empresa
	     String nicknameE ="nicke1";
	     String nombreE ="nome1";
	     String apellido= "ape1";
	     String correo= "coe1";
	     String descripcion = "dese1";
	     String link = "linke1";
	     
	     String empresa = nicknameE;
	      String nombreTipo = nombreT;
	      //datos oferta
	      String nombre= "nombreOL1";
	      String descripcionOL= "descOL1";
	      String ciudad = "ciudOL1";
	      String departamento = "depOL1";
	      String horario = "hoeOL1";
	      float remuneracion =1;
	      float costoAsociado = costoT;
	      DTTipo dataTipo = new DTTipo(nombreT, descripcionT, exposicionT, duracionT, costoT, "2020-08-22");
	      DTKeyword[] dataKeywords = new DTKeyword[1];
	      DTKeyword dtk = new DTKeyword(nombreK);
	      dataKeywords[0] = dtk;
	     
	     DTEmpresa dte = new DTEmpresa(nicknameE, nombreE, apellido, correo, "contra", null, descripcion, link, null, null);

	     
	    try {
	      controladorOfertaLaboral.ingresarTipo(nombreT, descripcionT, exposicionT, duracionT, costoT, fechaAltaT);
	      controladorUsuario.ingresarDatosPostulante(dtPost);
	      controladorUsuario.ingresarDatosEmpresa(dte); 
	      mt = ManejadorTipo.getInstancia();
	      DTOfertaLaboral ol = new DTOfertaLaboral(nombre, descripcionOL, ciudad, departamento, horario, remuneracion, "2020-08-22", "2020-08-22", 3, costoAsociado, dataTipo, dataKeywords, null, empresa, EstadoOL.Confirmada, false);
	      controladorOfertaLaboral.ingresarDatosOL(empresa,  nombreTipo, ol);
	      mol = ManejadorOfertaLaboral.getInstance();
	      OfertaLaboral Ol = mol.buscarOfertaLaboral(nombre);
	      mu = ManejadorUsuario.getInstancia();
	      Postulante postu = mu.buscarPostulante(nicknameP);
	      LocalDate local = LocalDate.now();
	      Postulacion pos = new Postulacion(local, "cvReducido","motivacion", postu, Ol, "https://www.youtube.com/watch?v=xZ27YDDYjqY");
	      controladorUsuario.ingresarPostulacion("CV", "Motivacion", local, nicknameE, nombre, nicknameP, "video");

	      String[] seleccion = new String[1];
	      seleccion[0] = nicknameP;
	      controladorOfertaLaboral.realizarSeleccion(nombre, seleccion);
	      Ol = mol.buscarOfertaLaboral(nombre);
	      assertEquals(Ol.getRankeada(), true);


	    }catch (TipoRepetidoException e) {
	      fail(e.getMessage());
	      e.printStackTrace();
	    }catch (ExisteUnUsuarioYaRegistradoException e) {
	      fail(e.getMessage());
	      e.printStackTrace();
	    }catch (OfertaLaboralRepetidaException e) {
	      fail(e.getMessage());
	      e.printStackTrace();
	    }catch (YaSePostuloException e) {
	    	fail(e.getMessage());
	    	e.printStackTrace();
	    }
	  }

	  @Test
	  void testBuscarTipo(){
	    //datos tipo
	    String nombreT = "nombreT3";
	    String descripcionT = "descripcionT3";
	     int exposicionT = 3;
	     int duracionT = 300000;
	     float costoT = 3;
	     Date fechaAltaT = new Date(1597891200000L); // 20 de agosto 2020

	    try {
	      controladorOfertaLaboral.ingresarTipo(nombreT, descripcionT, exposicionT, duracionT, costoT, fechaAltaT);
	      DTTipo tipo = controladorOfertaLaboral.buscarTipo(nombreT);

	      assertEquals(tipo.getCosto(), costoT);
	      assertEquals(tipo.getNombre(), nombreT);
	      assertEquals(tipo.getDescripcion(), descripcionT);
	      assertEquals(tipo.getDuracion(), duracionT);

	    }catch (TipoRepetidoException e) {
	      fail(e.getMessage());
	      e.printStackTrace();
	    }
	  }
	  
	  @Test
	  void testObtenerVideoPostulacion(){
	    String nombreK = "nombreKeyword";
	    
	    controladorOfertaLaboral.ingresarKeyword(nombreK);
	    //datos tipo
	    String nombreT = "nombreT3";
	    String descripcionT = "descripcionT3";
	     int exposicionT = 3;
	     int duracionT = 300000;
	     float costoT = 3;
	     Date fechaAltaT = new Date(1597891200000L); // 20 de agosto 2020
	     //datos postulante

	     String nicknameP ="nickp";
	     String nombreP ="nomp";
	     String apellidoP= "apep";
	     String correoP= "cop";
	     String nacionalidadP = "NacP";
	     
	     DTPostulante dtPost = new DTPostulante(nicknameP, nombreP, apellidoP, correoP,"contra", "2020-08-22", nacionalidadP, null, null, null);
	     //datos empresa
	     String nicknameE ="nicke1";
	     String nombreE ="nome1";
	     String apellido= "ape1";
	     String correo= "coe1";
	     String descripcion = "dese1";
	     String link = "linke1";
	     
	     String empresa = nicknameE;
	      String nombreTipo = nombreT;
	      //datos oferta
	      String nombre= "nombreOL1";
	      String descripcionOL= "descOL1";
	      String ciudad = "ciudOL1";
	      String departamento = "depOL1";
	      String horario = "hoeOL1";
	      float remuneracion =1;
	      float costoAsociado = costoT;
	      DTTipo dataTipo = new DTTipo(nombreT, descripcionT, exposicionT, duracionT, costoT, "2020-08-22");
	      DTKeyword[] dataKeywords = new DTKeyword[1];
	      DTKeyword dtk = new DTKeyword(nombreK);
	      dataKeywords[0] = dtk;
	     
	     DTEmpresa dte = new DTEmpresa(nicknameE, nombreE, apellido, correo, "contra", null, descripcion, link, null, null);

	     
	    try {
	      controladorOfertaLaboral.ingresarTipo(nombreT, descripcionT, exposicionT, duracionT, costoT, fechaAltaT);
	      controladorUsuario.ingresarDatosPostulante(dtPost);
	      controladorUsuario.ingresarDatosEmpresa(dte); 
	      mt = ManejadorTipo.getInstancia();
	      DTOfertaLaboral ol = new DTOfertaLaboral(nombre, descripcionOL, ciudad, departamento, horario, remuneracion, "2020-08-22", "2020-08-22", 3, costoAsociado, dataTipo, dataKeywords, null, empresa, EstadoOL.Confirmada, false);
	      controladorOfertaLaboral.ingresarDatosOL(empresa,  nombreTipo, ol);
	      mol = ManejadorOfertaLaboral.getInstance();
	      OfertaLaboral Ol = mol.buscarOfertaLaboral(nombre);
	      mu = ManejadorUsuario.getInstancia();
	      Postulante postu = mu.buscarPostulante(nicknameP);
	      LocalDate local = LocalDate.now();
	      Postulacion pos = new Postulacion(local, "cvReducido","motivacion", postu, Ol, "https://www.youtube.com/watch?v=xZ27YDDYjqY");
	      
	      postu.agregarPostulacion(pos);

	      String video = controladorOfertaLaboral.obtenerVideoPostulacion(nicknameP, nombre);


	    }catch (TipoRepetidoException e) {
	      fail(e.getMessage());
	      e.printStackTrace();
	    }catch (ExisteUnUsuarioYaRegistradoException e) {
	      fail(e.getMessage());
	      e.printStackTrace();
	    }catch (OfertaLaboralRepetidaException e) {
	      fail(e.getMessage());
	      e.printStackTrace();
	    }
	  }
	  
	  @Test
		void testEstaVigente(){

			String nombreK = "nombreKeyword";
			controladorOfertaLaboral.ingresarKeyword(nombreK);

			//datos tipo
			String nombreT = "nombreT3";
			String descripcionT = "descripcionT3";
			int exposicionT = 3;
			int duracionT = 3;
			float costoT = 3;
			Date fechaAltaT = new Date(1597891200000L); // 20 de agosto 2020

			//datos empresa
			String nicknameE ="nicke1";
			String nombreE ="nome1";
			String apellido= "ape1";
			String correo= "coe1";
			String descripcion = "dese1";
			String link = "linke1";
			String empresa = nicknameE;
			String nombreTipo = nombreT;

			//datos oferta
			String nombre= "nombreOL1";
			String descripcionOL= "descOL1";
			String ciudad = "ciudOL1";
			String departamento = "depOL1";
			String horario = "hoeOL1";
			float remuneracion =1;
			float costoAsociado = costoT;
			DTTipo dataTipo = new DTTipo(nombreT, descripcionT, exposicionT, duracionT, costoT, "2020-08-22");
			DTKeyword[] dataKeywords = new DTKeyword[1];
			DTKeyword dtk = new DTKeyword(nombreK);
			dataKeywords[0] = dtk;
			DTEmpresa dte = new DTEmpresa(nicknameE, nombreE, apellido, correo, "contra", null, descripcion, link, null, null);

			 
			try {
				controladorOfertaLaboral.ingresarTipo(nombreT, descripcionT, exposicionT, duracionT, costoT, fechaAltaT);
				controladorUsuario.ingresarDatosEmpresa(dte); 
				mt = ManejadorTipo.getInstancia();
				DTOfertaLaboral ol = new DTOfertaLaboral(nombre, descripcionOL, ciudad, departamento, horario, remuneracion, "2020-08-22", "2020-08-22", 3, costoAsociado, dataTipo, dataKeywords, null, empresa, EstadoOL.Confirmada, false);
				controladorOfertaLaboral.ingresarDatosOL(empresa,  nombreTipo, ol);

				boolean vigente = controladorOfertaLaboral.estaVigenteOferta(nombre);
				assertEquals(vigente, false);

			}catch (TipoRepetidoException e) {
				fail(e.getMessage());
				e.printStackTrace();
			}catch (ExisteUnUsuarioYaRegistradoException e) {
				fail(e.getMessage());
				e.printStackTrace();
			}catch (OfertaLaboralRepetidaException e) {
				fail(e.getMessage());
				e.printStackTrace();
			}
		}
	  @Test
		void testBuscarOfertaLaboral(){

			String nombreK = "nombreKeyword";
			controladorOfertaLaboral.ingresarKeyword(nombreK);

			//datos tipo
			String nombreT = "nombreT3";
			String descripcionT = "descripcionT3";
			int exposicionT = 3;
			int duracionT = 300000;
			float costoT = 3;
			Date fechaAltaT = new Date(1597891200000L); // 20 de agosto 2020

			//datos empresa
			String nicknameE ="nicke1";
			String nombreE ="nome1";
			String apellido= "ape1";
			String correo= "coe1";
			String descripcion = "dese1";
			String link = "linke1";
			String empresa = nicknameE;
			String nombreTipo = nombreT;

			//datos oferta
			String nombre= "nombreOL1";
			String descripcionOL= "descOL1";
			String ciudad = "ciudOL1";
			String departamento = "depOL1";
			String horario = "hoeOL1";
			float remuneracion =1;
			float costoAsociado = costoT;
			DTTipo dataTipo = new DTTipo(nombreT, descripcionT, exposicionT, duracionT, costoT, "2020-08-22");
			DTKeyword[] dataKeywords = new DTKeyword[1];
			DTKeyword dtk = new DTKeyword(nombreK);
			dataKeywords[0] = dtk;
			DTEmpresa dte = new DTEmpresa(nicknameE, nombreE, apellido, correo, "contra", null, descripcion, link, null, null);

			 
			try {
				controladorOfertaLaboral.ingresarTipo(nombreT, descripcionT, exposicionT, duracionT, costoT, fechaAltaT);
				controladorUsuario.ingresarDatosEmpresa(dte); 
				mt = ManejadorTipo.getInstancia();
				DTOfertaLaboral ol = new DTOfertaLaboral(nombre, descripcionOL, ciudad, departamento, horario, remuneracion, "2020-08-22", "2020-08-22", 3, costoAsociado, dataTipo, dataKeywords, null, empresa, EstadoOL.Confirmada, false);
				controladorOfertaLaboral.ingresarDatosOL(empresa,  nombreTipo, ol);

				DTOfertaLaboral dtO = controladorOfertaLaboral.buscarOfertaLaboral(nombre);

				assertEquals(dtO.getNombre(), nombre);
				assertEquals(dtO.getCiudad(), ciudad);
				assertEquals(dtO.getDepartamento(), departamento);
				assertEquals(dtO.getDescripcion(), descripcionOL);
				assertEquals(dtO.getFechaDeAlta(), "2020-08-22");
				assertEquals(dtO.getCostoAsociado(), costoAsociado);
				assertEquals(dtO.getTipo().getNombre(),nombreT);

			}catch (TipoRepetidoException e) {
				fail(e.getMessage());
				e.printStackTrace();
			}catch (ExisteUnUsuarioYaRegistradoException e) {
				fail(e.getMessage());
				e.printStackTrace();
			}catch (OfertaLaboralRepetidaException e) {
				fail(e.getMessage());
				e.printStackTrace();
			}
		}
	  
	  @Test
	void testGetOfertasSeleccionarPosutlante(){

		String nombreK = "nombreKeyword";
		controladorOfertaLaboral.ingresarKeyword(nombreK);

		//datos tipo
		String nombreT = "nombreT3";
		String descripcionT = "descripcionT3";
		int exposicionT = 3;
		int duracionT = 300;
		float costoT = 3;
		Date fechaAltaT = new Date(1597891200000L); // 20 de agosto 2020

		//datos postulante

		String nicknameP ="nickp";
		String nombreP ="nomp";
		String apellidoP= "apep";
		String correoP= "cop";
		String nacionalidadP = "NacP";
		
		DTPostulante dtPost = new DTPostulante(nicknameP, nombreP, apellidoP, correoP,"contra", "2020-08-22", nacionalidadP, null, null, null);
		
		//datos empresa
		String nicknameE ="nicke1";
		String nombreE ="nome1";
		String apellido= "ape1";
		String correo= "coe1";
		String descripcion = "dese1";
		String link = "linke1";
		
		String empresa = nicknameE;
		String nombreTipo = nombreT;
		
		//datos oferta
		String nombre= "nombreOL1";
		String descripcionOL= "descOL1";
		String ciudad = "ciudOL1";
		String departamento = "depOL1";
		String horario = "hoeOL1";
		float remuneracion =1;
		float costoAsociado = costoT;
		DTTipo dataTipo = new DTTipo(nombreT, descripcionT, exposicionT, duracionT, costoT, "2020-08-22");
		DTKeyword[] dataKeywords = new DTKeyword[1];
		DTKeyword dtk = new DTKeyword(nombreK);
		dataKeywords[0] = dtk;
		 
		DTEmpresa dte = new DTEmpresa(nicknameE, nombreE, apellido, correo, "contra", null, descripcion, link, null, null);

		 
		try {
			controladorOfertaLaboral.ingresarTipo(nombreT, descripcionT, exposicionT, duracionT, costoT, fechaAltaT);
			controladorUsuario.ingresarDatosPostulante(dtPost);
			controladorUsuario.ingresarDatosEmpresa(dte); 
			mt = ManejadorTipo.getInstancia();
			DTOfertaLaboral ol = new DTOfertaLaboral(nombre, descripcionOL, ciudad, departamento, horario, remuneracion, "2020-08-22", "2020-08-22", 3, costoAsociado, dataTipo, dataKeywords, null, empresa, EstadoOL.Confirmada, false);
			controladorOfertaLaboral.ingresarDatosOL(empresa,  nombreTipo, ol);
			mol = ManejadorOfertaLaboral.getInstance();
			OfertaLaboral Ol = mol.buscarOfertaLaboral(nombre);
			Ol.setEstado(EstadoOL.Confirmada);
			mu = ManejadorUsuario.getInstancia();
			Postulante postu = mu.buscarPostulante(nicknameP);
			LocalDate local = LocalDate.now();
			controladorUsuario.ingresarPostulacion("CVReducido", "motivacion", local, nicknameE, nombre, nicknameP, "");
	
			DTOfertaLaboral[] ofts = controladorOfertaLaboral.getOfertasSeleccionarPosutlante(nicknameE);

			assertEquals(ofts[0].getNombre(), nombre);


		}catch (TipoRepetidoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}catch (ExisteUnUsuarioYaRegistradoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}catch (OfertaLaboralRepetidaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}catch (YaSePostuloException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}catch (NoExistenOfertasSeleccionarPostulanteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	void testGetOfertasSeleccionarPosutlanteError(){
		//datos empresa
		String nicknameE ="nicke1";
		String nombreE ="nome1";
		String apellido= "ape1";
		String correo= "coe1";
		String descripcion = "dese1";
		String link = "linke1";
		String empresa = nicknameE;
	
		
		DTEmpresa dte = new DTEmpresa(nicknameE, nombreE, apellido, correo, "contra", null, descripcion, link, null, null);

		 
		try {
			controladorUsuario.ingresarDatosEmpresa(dte); 

		}catch (ExisteUnUsuarioYaRegistradoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}

		assertThrows(NoExistenOfertasSeleccionarPostulanteException.class, ()->{controladorOfertaLaboral.getOfertasSeleccionarPosutlante(nicknameE);});
		
	}
	
	@Test
    void testGetDTKeywords(){

        String nombreK = "nombreKeyword";
        controladorOfertaLaboral.ingresarKeyword(nombreK);
        DTKeyword[] keys = controladorOfertaLaboral.getDTKeywords();

        assertEquals(keys[0].getNombre(), nombreK);
    }
	
	@Test
	void testObtenerDTOfertaLaboral(){

		String nombreK = "nombreKeyword";
		controladorOfertaLaboral.ingresarKeyword(nombreK);

		//datos tipo
		String nombreT = "nombreT3";
		String descripcionT = "descripcionT3";
		int exposicionT = 3;
		int duracionT = 300000;
		float costoT = 3;
		Date fechaAltaT = new Date(1597891200000L); // 20 de agosto 2020

		//datos empresa
		String nicknameE ="nicke1";
		String nombreE ="nome1";
		String apellido= "ape1";
		String correo= "coe1";
		String descripcion = "dese1";
		String link = "linke1";
		
		String empresa = nicknameE;
		String nombreTipo = nombreT;
		
		//datos oferta
		String nombre= "nombreOL1";
		String descripcionOL= "descOL1";
		String ciudad = "ciudOL1";
		String departamento = "depOL1";
		String horario = "hoeOL1";
		float remuneracion =1;
		float costoAsociado = costoT;
		DTTipo dataTipo = new DTTipo(nombreT, descripcionT, exposicionT, duracionT, costoT, "2020-08-22");
		DTKeyword[] dataKeywords = new DTKeyword[1];
		DTKeyword dtk = new DTKeyword(nombreK);
		dataKeywords[0] = dtk;
		 
		DTEmpresa dte = new DTEmpresa(nicknameE, nombreE, apellido, correo, "contra", null, descripcion, link, null, null);

		 
		try {
			controladorOfertaLaboral.ingresarTipo(nombreT, descripcionT, exposicionT, duracionT, costoT, fechaAltaT);
			controladorUsuario.ingresarDatosEmpresa(dte); 
			mt = ManejadorTipo.getInstancia();
			DTOfertaLaboral Ol = new DTOfertaLaboral(nombre, descripcionOL, ciudad, departamento, horario, remuneracion, "2020-08-22", "2020-08-22", 3, costoAsociado, dataTipo, dataKeywords, null, empresa, EstadoOL.Confirmada, false);
			controladorOfertaLaboral.ingresarDatosOL(empresa,  nombreTipo, Ol);
			Ol =  controladorOfertaLaboral.obtenerDTOfertaLaboral(nombre);

			assertEquals(Ol.getNombre(), nombre);
			assertEquals(Ol.getCiudad(), ciudad);
			assertEquals(Ol.getDepartamento(), departamento);
			assertEquals(Ol.getDescripcion(), descripcionOL);
			assertEquals(Ol.getFechaDeAlta(), "2020-08-22");
			assertEquals(Ol.getCostoAsociado(), costoAsociado);


		}catch (TipoRepetidoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}catch (ExisteUnUsuarioYaRegistradoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}catch (OfertaLaboralRepetidaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	  void testBuscarPaquete(){
	    //Tipo
	    String nombreTipo = "nombreTipo";
	    String descripcionTipo = "descTipo";
	    int exposicionTipo = 3;
	    int duracionTipo = 15;
	    Float costoTipo = 330f;
	    LocalDate fechaAlta = LocalDate.now();
	    Date fechaAltaTipo = Date.from(fechaAlta.atStartOfDay(ZoneId.systemDefault()).toInstant());
	    Tipo t = new Tipo(nombreTipo, descripcionTipo, exposicionTipo, duracionTipo, costoTipo, fechaAlta);
	    try {
	      controladorOfertaLaboral.ingresarTipo(nombreTipo, descripcionTipo, exposicionTipo, duracionTipo, costoTipo, fechaAltaTipo);
	    } catch (TipoRepetidoException e) {
	      e.printStackTrace();
	    }
	    
	    //PaqueteTipo
	    int cantidadPaqTip = 3;
	    DTPaqueteTipo paqTip = new DTPaqueteTipo(cantidadPaqTip, t.getDataTipo());
	    
	    //Paquete
	    String nombrePaq = "nombrePaq";
	    String descripcionPaq = "descripcionPaq";
	    int periodoDeValidezPaq = 3;
	    float descuentoPaq = 20;
	    float costoAsociadoPaq = 20;
	    String fechaDeAlta = "20-03-2023";
	    DTPaqueteTipo[] paqTipos = new DTPaqueteTipo[1];
	    paqTipos[0] = paqTip;
	    DTPaquete paquete = new DTPaquete(nombrePaq, descripcionPaq, periodoDeValidezPaq, descuentoPaq, costoAsociadoPaq, paqTipos, fechaDeAlta);
	    
	    try {
	      controladorOfertaLaboral.ingresarDatosPaquete(paquete);
	      DTPaquete dtPaq = controladorOfertaLaboral.buscarPaquete(nombrePaq);
	      assertEquals(dtPaq.getNombre() , nombrePaq);
	      assertEquals(dtPaq.getDescripcion() , descripcionPaq);
	      assertEquals(dtPaq.getPeriodoDeValidez() , periodoDeValidezPaq);
	      assertEquals(dtPaq.getDescuento() , descuentoPaq);
	      assertEquals(dtPaq.getFechaDeAlta() , fechaDeAlta); 
	    } catch (PaqueteRepetidoException e) {
	      fail(e.getMessage());
	      e.printStackTrace();
	    } 

	  }
}