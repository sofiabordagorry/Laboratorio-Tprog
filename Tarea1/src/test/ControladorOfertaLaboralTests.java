package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import excepciones.NoHayPaquetesException;
import excepciones.NoHayTiposException;
import excepciones.PaqueteRepetidoException;
import excepciones.TipoRepetidoException;
import excepciones.TipoYaAgragadoException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import logica.Factory;
import logica.IOfertaLaboral;
import logica.Keyword;
import logica.Tipo;
import logica.Paquete;
import logica.PaqueteTipo;
import logica.Postulacion;
import logica.DTOfertaLaboral;
import logica.DTPaquete;
import logica.DTPaqueteTipo;
import logica.Empresa;
import logica.ManejadorOfertaLaboral;
import logica.ManejadorTipo;
import logica.OfertaLaboral;

class ControladorOfertaLaboralTests{

	private static IOfertaLaboral controladorOfertaLaboral;
	private ManejadorOfertaLaboral mol;
	private ManejadorTipo mt;

	
	@BeforeAll
	public static void iniciar(){
		Factory fabrica = Factory.getInstance();
		controladorOfertaLaboral = fabrica.getIOfertaLaboral();
	}
	
	@AfterEach
	public void borrar(){
        mol = ManejadorOfertaLaboral.getInstance();
        mol.limpiar();
        mt = ManejadorTipo.getInstancia();
        mt.limpiar();
	}
	
	@Test
	void testIngresarKeyword(){
		String nombre = "nombreKeyword";
			controladorOfertaLaboral.ingresarKeyword(nombre);
			mol = ManejadorOfertaLaboral.getInstance();
			Keyword k = mol.buscarKeyword(nombre);
			//if (k == null) {
			//	fail("No existe la keyword");
		//	}
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
			
		DTPaquete 	dtp = new DTPaquete(nombre, descripcion, periodoDeValidez, descuento, costoAsociado, dtpaq, fechaDeAlta);
		
		try {
			controladorOfertaLaboral.ingresarDatosPaquete(dtp);
			mt = ManejadorTipo.getInstancia();
			Paquete p = mt.buscarPaquete(nombre);
			assertEquals(p.getNombre(), nombre);
			assertEquals(p.getDescripcion(), descripcion);
			assertEquals(p.getDescuento(), descuento);
			assertEquals(p.getPaquetesTipos(), paquetesTipos);
			assertEquals(p.getCostoAsociado(), costoAsociado);
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
		List<PaqueteTipo> paquetesTipos = new ArrayList<>();//lista vacia para comparar
		DTPaquete 	dtp = new DTPaquete(nombre, descripcion, periodoDeValidez, descuento, costoAsociado, dtpaq, fechaDeAlta);
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
	void listarNomTiposVacioTest() {
		try {
			String[] res = controladorOfertaLaboral.listarNomTipos();
		} catch (NoHayTiposException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
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
	void listarNomPaquetesVacioTest() {
		try {
			String[] noms = controladorOfertaLaboral.listarNomPaquetes();
		} catch (NoHayPaquetesException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
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
		Empresa emp = new Empresa("a", "b", "c", "d", "e", "f", "g");
		OfertaLaboral of = new OfertaLaboral("OF1", "DESC", "CIUDAD", "DEPARTAMENTO", "HORARIO", 50f, LocalDate.now(), 50f, t, keywords, emp);
		mt.agregarTipo(t);
		mof.agregarOfertaLaboral(of);
		DTOfertaLaboral datos = controladorOfertaLaboral.mostrarDatosOfertaLaboral(of.getNombre());
		assertEquals(of.getNombre(), datos.getNombre());
		assertEquals(of.getDescripcion(), datos.getDescripcion());
		assertEquals(of.getCiudad(), datos.getCiudad());
		assertEquals(of.getDepartamento(), datos.getDepartamento());
		assertEquals(of.getHorario(), datos.getHorario());
		assertEquals(of.getRemuneracion(), datos.getRemuneracion());
		assertEquals(of.getFechaDeAlta(), datos.getFechaDeAlta());
		assertEquals(of.getCostoAsociado(), datos.getCostoAsociado());
		assertEquals(of.getTipoOL().getNombre(), datos.getTipo().getNombre());
		assertTrue(of.getKeywords().size() == 0 && datos.getKeywords().size() == 0);
		assertTrue(of.getPostulaciones().size() == 0 && datos.getPostulaciones().size() == 0);
		assertEquals(of.getEmpresaCreadora(), datos.getDTEmpresa());
	}
}
