package presentacion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logica.Empresa;
import logica.Keyword;
import logica.ManejadorOfertaLaboral;
import logica.ManejadorTipo;
import logica.ManejadorUsuario;
import logica.OfertaLaboral;
import logica.Paquete;
import logica.Postulacion;
import logica.Postulante;
import logica.Tipo;
import logica.Usuario;

public class CargarDatos {
	
	public CargarDatos() {
	}
	
	public static void ingresarPaquetes() {
		ManejadorTipo m = ManejadorTipo.getInstancia();
		String csvFilePath = "./src/CSV/Paquetes.csv";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yy");
		
		try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
			String line;
			boolean isFirstLine = true;
			
			while ((line = br.readLine()) != null) {
				if (isFirstLine) {
					isFirstLine = false;
					continue;
				}
				
				String[] parts = line.split(";");
				Paquete paq = new Paquete(parts[1], parts[2], Integer.parseInt(parts[3]), Float.parseFloat(parts[4]), 0, LocalDate.parse(parts[5], formatter));
				m.agregarPaquete(paq);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void ingresarTipos() {
		ManejadorTipo m = ManejadorTipo.getInstancia();
		String csvFilePath = "./src/CSV/TipoPublicacion.csv";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		
		try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
			String line;
			boolean isFirstLine = true;
			
			while ((line = br.readLine()) != null) {
				if (isFirstLine) {
					isFirstLine = false;
					continue;
				}
				
				String[] parts = line.split(";");
				Tipo tp = new Tipo(parts[1], parts[2], Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), Float.parseFloat(parts[5]), LocalDate.parse(parts[6], formatter));
				m.agregarTipo(tp);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void ingresarPaqueteTipos() {
		ManejadorTipo m = ManejadorTipo.getInstancia();
		String csvFilePath = "./src/CSV/TiposPublicacionPaquetes.csv";
		
		Map<String, Paquete> paqs = m.getMapPaquete();
		Map<String, Tipo> tipos = m.getMapTipo();
		
		try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
			String line;
			boolean isFirstLine = true;
			
			while ((line = br.readLine()) != null) {
				if (isFirstLine) {
					isFirstLine = false;
					continue;
				}
				
				String[] parts = line.split(";");
				paqs.get(parts[1]).agregarTipo(Integer.parseInt(parts[3]), tipos.get(parts[2]));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static List<Usuario> cargarUsuarios() {
		String csvFilePath = "./src/CSV/Usuarios.csv";
		List<Usuario> usuarios = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
			String line;
			boolean isFirstLine = true;
			
			while ((line = br.readLine()) != null) {
				if (isFirstLine) {
					isFirstLine = false;
					continue;
				}
				
				String[] parts = line.split(";");
				Usuario user = new Usuario(parts[2], parts[3], parts[4], parts[5]);
				usuarios.add(user);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return usuarios;
	}
	
	public static List<Postulante> cargarPostulantes() {
		String csvFilePath = "./src/CSV/Usuarios-Postulantes.csv";
		List<Postulante> postulantes = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		
		List<Usuario> users = cargarUsuarios();
		Usuario[] usersArr = users.toArray(new Usuario[users.size()]);
		int i = 0;
		
		try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
			String line;
			boolean isFirstLine = true;
			
			while ((line = br.readLine()) != null) {
				if (isFirstLine) {
					isFirstLine = false;
					continue;
				}
				
				String[] parts = line.split(";");
				Postulante user = new Postulante(usersArr[i].getNickname(), usersArr[i].getNombre(), usersArr[i].getApellido(), usersArr[i].getCorreo(), LocalDate.parse(parts[1], formatter), parts[2]);
				postulantes.add(user);
				i++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return postulantes;
	}
	
	public static List<Empresa> cargarEmpresas() {
		String csvFilePath = "./src/CSV/Usuarios-Empresas.csv";
		List<Empresa> empresas = new ArrayList<>();
		
		List<Usuario> users = cargarUsuarios();
		Usuario[] usersArr = users.toArray(new Usuario[users.size()]);
		int i = 10;
		
		try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
			String line;
			boolean isFirstLine = true;
			
			while ((line = br.readLine()) != null) {
				if (isFirstLine) {
					isFirstLine = false;
					continue;
				}
				
				String[] parts = line.split(";");
				Empresa user = new Empresa(usersArr[i].getNickname(), usersArr[i].getNombre(), usersArr[i].getApellido(), usersArr[i].getCorreo(), parts[1], parts[2], parts[3]);
				empresas.add(user);
				i++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return empresas;
	}
	
	public static void ingresarUsuarios() {
		List<Empresa> empresas = cargarEmpresas();
		List<Postulante> postulantes = cargarPostulantes();
		
		ManejadorUsuario m = ManejadorUsuario.getInstancia();
		
		for (Empresa item : empresas) {
			m.agregarEmpresa(item);
		}
		for (Postulante item: postulantes) {
			m.agregarPostulante(item);
		}
	}
	
	public static void ingresarKeywords() {
		ManejadorOfertaLaboral m = ManejadorOfertaLaboral.getInstance();
		String csvFilePath = "./src/CSV/Keywords.csv";
		
		try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
			String line;
			boolean isFirstLine = true;
			
			while ((line = br.readLine()) != null) {
				if (isFirstLine) {
					isFirstLine = false;
					continue;
				}
				
				String[] parts = line.split(";");
				Keyword keyword = new Keyword(parts[1]);
				m.agregarKeyword(keyword);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void ingresarOfertasLaborales() {
		ManejadorTipo m = ManejadorTipo.getInstancia();
		ManejadorOfertaLaboral mof = ManejadorOfertaLaboral.getInstance();
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yy");
		
		String csvFilePath = "./src/CSV/OfertasLaborales.csv";
		
		try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
			String line;
			boolean isFirstLine = true;
			
			while ((line = br.readLine()) != null) {
				if (isFirstLine) {
					isFirstLine = false;
					continue;
				}
				
				String[] parts = line.split(";");
				Map<String, Empresa> empresas = mu.getMapEmpresas();
				Map<String, Tipo> tipos = m.getMapTipos();
				Map<String, Keyword> keywords = new HashMap<>();
				
				OfertaLaboral of = new OfertaLaboral(parts[1], parts[2], parts[4], parts[3], parts[5], Float.parseFloat(parts[6]), LocalDate.parse(parts[9], formatter), 0, tipos.get(parts[8]), keywords, empresas.get(parts[7]));
				mof.agregarOfertaLaboral(of);
				empresas.get(parts[7]).agregarOfertaLaboral(of);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void ingresarKeywordsOfertas() {
		ManejadorOfertaLaboral mof = ManejadorOfertaLaboral.getInstance();
		Keyword[] keywords = mof.getKeywords();
		
		Map<String,OfertaLaboral> ofertasLaborales = mof.getOfertasLaborales();
		ofertasLaborales.get("Desarrollador Frontend").agregarKeyword(keywords[0]);
		ofertasLaborales.get("Desarrollador Frontend").agregarKeyword(keywords[1]);
		ofertasLaborales.get("Desarrollador Frontend").agregarKeyword(keywords[2]);
		ofertasLaborales.get("Desarrollador Frontend").agregarKeyword(keywords[3]);
		ofertasLaborales.get("Desarrollador Frontend").agregarKeyword(keywords[4]);
		ofertasLaborales.get("Desarrollador Frontend").agregarKeyword(keywords[5]);
		ofertasLaborales.get("Estratega de Negocios").agregarKeyword(keywords[4]);
		ofertasLaborales.get("Diseñador UX/UI").agregarKeyword(keywords[1]);
		ofertasLaborales.get("Diseñador UX/UI").agregarKeyword(keywords[2]);
		ofertasLaborales.get("Diseñador UX/UI").agregarKeyword(keywords[5]);
		ofertasLaborales.get("Analista de Datos").agregarKeyword(keywords[1]);
		ofertasLaborales.get("Content Manager").agregarKeyword(keywords[3]);
		ofertasLaborales.get("Soporte Técnico").agregarKeyword(keywords[0]);
	}
	
	public static void ingresarPostulaciones() {
		ManejadorOfertaLaboral mof = ManejadorOfertaLaboral.getInstance();
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yy");
		
		String csvFilePath = "./src/CSV/Postulaciones.csv";
		
		try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
			String line;
			boolean isFirstLine = true;
			
			while ((line = br.readLine()) != null) {
				if (isFirstLine) {
					isFirstLine = false;
					continue;
				}
				
				String[] parts = line.split(";");
				OfertaLaboral lof = mof.buscarOfertaLaboral(parts[5]);
				Postulante upost = mu.buscarPostulante(parts[1]);
				
				Postulacion post = new Postulacion(LocalDate.parse(parts[4], formatter), parts[2], parts[3], upost, lof);
				upost.agregarPostulacion(post);
				lof.agregarPostulacion(post);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
