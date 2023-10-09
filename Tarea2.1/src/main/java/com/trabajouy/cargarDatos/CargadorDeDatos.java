package com.trabajouy.cargarDatos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import logica.*;

import jakarta.servlet.ServletContext;
import logica.Empresa;

public class CargadorDeDatos {
	
	public CargadorDeDatos() {
	}
	
	public static void ingresarPaquetes(ServletContext context) {
		ManejadorTipo m = ManejadorTipo.getInstancia();
		String csvFilePath = "/CSV/Paquetes.csv";
		InputStream inputStream = context.getResourceAsStream(csvFilePath);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
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
	
	public static void ingresarTipos(ServletContext context) {
		ManejadorTipo m = ManejadorTipo.getInstancia();
		String csvFilePath = "/CSV/TipoPublicacion.csv";
		InputStream inputStream = context.getResourceAsStream(csvFilePath);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
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
	
	public static void ingresarPaqueteTipos(ServletContext context) {
		ManejadorTipo m = ManejadorTipo.getInstancia();
		String csvFilePath = "/CSV/TiposPublicacionPaquetes.csv";
		InputStream inputStream = context.getResourceAsStream(csvFilePath);
		
		Map<String, Paquete> paqs = m.getMapPaquete();
		Map<String, Tipo> tipos = m.getMapTipo();
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
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
	
	public static List<Usuario> cargarUsuarios(ServletContext context) {
		String csvFilePath = "/CSV/Usuarios.csv";
		InputStream inputStream = context.getResourceAsStream(csvFilePath);
		List<Usuario> usuarios = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
			String line;
			boolean isFirstLine = true;
			
			while ((line = br.readLine()) != null) {
				if (isFirstLine) {
					isFirstLine = false;
					continue;
				}
				
				String[] parts = line.split(";");
				Usuario user = new Usuario(parts[2], parts[3], parts[4], parts[5], parts[6]);
				usuarios.add(user);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return usuarios;
	}
	
	public static List<Postulante> cargarPostulantes(ServletContext context) {
		String csvFilePath = "/CSV/Usuarios-Postulantes.csv";
		InputStream inputStream = context.getResourceAsStream(csvFilePath);
		List<Postulante> postulantes = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		List<Usuario> users = cargarUsuarios(context);
		Usuario[] usersArr = users.toArray(new Usuario[users.size()]);
		int i = 0;
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
			String line;
			boolean isFirstLine = true;
			
			while ((line = br.readLine()) != null) {
				if (isFirstLine) {
					isFirstLine = false;
					continue;
				}
				
				String[] parts = line.split(";");
				Postulante user = new Postulante(usersArr[i].getNickname(), usersArr[i].getNombre(), usersArr[i].getApellido(), usersArr[i].getCorreo(), LocalDate.parse(parts[1], formatter), parts[2], usersArr[i].getContrasenia());
				postulantes.add(user);
				i++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return postulantes;
	}
	
	public static List<Empresa> cargarEmpresas(ServletContext context) {
		String csvFilePath = "/CSV/Usuarios-Empresas.csv";
		InputStream inputStream = context.getResourceAsStream(csvFilePath);
		List<Empresa> empresas = new ArrayList<>();
		
		List<Usuario> users = cargarUsuarios(context);
		Usuario[] usersArr = users.toArray(new Usuario[users.size()]);
		int i = 10;
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
			String line;
			boolean isFirstLine = true;
			
			while ((line = br.readLine()) != null) {
				if (isFirstLine) {
					isFirstLine = false;
					continue;
				}
				
				String[] parts = line.split(";");
				Empresa user = new Empresa(usersArr[i].getNickname(), usersArr[i].getNombre(), usersArr[i].getApellido(), usersArr[i].getCorreo(), parts[1], parts[2], usersArr[i].getContrasenia());
				empresas.add(user);
				i++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return empresas;
	}
	
	public static void ingresarUsuarios(ServletContext context) {
		List<Empresa> empresas = cargarEmpresas(context);
		List<Postulante> postulantes = cargarPostulantes(context);
		
		ManejadorUsuario m = ManejadorUsuario.getInstancia();
		
		for (Empresa item : empresas) {
			m.agregarEmpresa(item);
		}
		for (Postulante item: postulantes) {
			m.agregarPostulante(item);
		}
	}
	
	public static void ingresarKeywords(ServletContext context) {
		ManejadorOfertaLaboral m = ManejadorOfertaLaboral.getInstance();
		String csvFilePath = "/CSV/Keywords.csv";
		InputStream inputStream = context.getResourceAsStream(csvFilePath);
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
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
	
	public static void ingresarOfertasLaborales(ServletContext context) {
		ManejadorTipo m = ManejadorTipo.getInstancia();
		ManejadorOfertaLaboral mof = ManejadorOfertaLaboral.getInstance();
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
		String csvFilePath = "/CSV/OfertasLaborales.csv";
		InputStream inputStream = context.getResourceAsStream(csvFilePath);
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
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
				EstadoOL estado = EstadoOL.valueOf(parts[10]);
				
				OfertaLaboral of = new OfertaLaboral(parts[1], parts[2], parts[4], parts[3], parts[5], Float.parseFloat(parts[6]), LocalDate.parse(parts[9], formatter), 0, tipos.get(parts[8]), keywords, estado, empresas.get(parts[7]), null);
				mof.agregarOfertaLaboral(of);
				empresas.get(parts[7]).agregarOfertaLaboral(of);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void ingresarKeywordsOfertas(ServletContext context) {
		ManejadorOfertaLaboral mof = ManejadorOfertaLaboral.getInstance();
		Keyword[] keywords = mof.getKeywords();
		
		Map<String,OfertaLaboral> ofertasLaborales = mof.getOfertasLaborales();
		for (Map.Entry<String, OfertaLaboral> entry : ofertasLaborales.entrySet()) {
            String key = entry.getKey();
            OfertaLaboral value = entry.getValue();
            
            // Now you can work with the key and value as needed
        }
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
		ofertasLaborales.get("A. de Marketing Digital").agregarKeyword(keywords[3]);
		ofertasLaborales.get("Contador Senior").agregarKeyword(keywords[0]);
		ofertasLaborales.get("Técnico/a Básico Red").agregarKeyword(keywords[4]);
		ofertasLaborales.get("Desarrollador de Software Senior").agregarKeyword(keywords[0]);
		ofertasLaborales.get("Desarrollador de Software Senior").agregarKeyword(keywords[5]);
		ofertasLaborales.get("Desarrollador de Software Senior").agregarKeyword(keywords[8]);
		ofertasLaborales.get("Desarrollador de Software Full Stack").agregarKeyword(keywords[2]);
		ofertasLaborales.get("Gerente de Proyecto").agregarKeyword(keywords[2]);
		ofertasLaborales.get("Gerente de Proyecto").agregarKeyword(keywords[5]);
		ofertasLaborales.get("Ingeniero de Calidad de Software").agregarKeyword(keywords[0]);
		ofertasLaborales.get("Ingeniero de Calidad de Software").agregarKeyword(keywords[9]);
	}
	
	public static void ingresarPostulaciones(ServletContext context) {
		ManejadorOfertaLaboral mof = ManejadorOfertaLaboral.getInstance();
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String csvFilePath = "/CSV/Postulaciones.csv";
		InputStream inputStream = context.getResourceAsStream(csvFilePath);
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
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
	
	public static void ingresarComprasPaquetes(ServletContext context) {
		ManejadorTipo m = ManejadorTipo.getInstancia();
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		String csvFilePath = "/CSV/PaquetesCompras.csv";
		InputStream inputStream = context.getResourceAsStream(csvFilePath);
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
			String line;
			boolean isFirstLine = true;
			
			while ((line = br.readLine()) != null) {
				if (isFirstLine) {
					isFirstLine = false;
					continue;
				}
				
				String[] parts = line.split(";");
				Empresa emp = mu.buscarEmpresa(parts[1]);
				Paquete paq = m.buscarPaquete(parts[2]);
				int periodoValidezPaq = paq.getPeriodoDeValidez();
				LocalDate fecha = LocalDate.parse(parts[3], formatter);
				LocalDate vencimiento = fecha.plusDays(periodoValidezPaq);
				
				List<PaqueteTipo> paqTip = paq.getPaquetesTipos();
				List<CompraTipo>  compTip = new LinkedList<>();
				for(PaqueteTipo p : paqTip) {
					CompraTipo ct = new CompraTipo(p.getCantidad() , p.getTipo());
					compTip.add(ct);
				}
				Compra compra = new Compra(fecha, vencimiento, paq, emp, compTip);
				emp.agregarCompra(compra);
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
