package com.trabajouy.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import publicar.DtEmpresa;
import publicar.DtKeywordWS;
import publicar.DtOfertaLaboralWS;
import publicar.DtUsuario;
import publicar.DtUsuarioWS;
import publicar.OfertasLaboralesNoExistenNingunaException_Exception;
import publicar.UsuariosNoExistenException_Exception;

/**
 * Servlet implementation class Ofertas
 */
public class Ofertas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ofertas() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public static void aplicarFiltros(HttpServletRequest request) throws UsuariosNoExistenException_Exception {
		publicar.WebServicesService service = new publicar.WebServicesService();
		publicar.WebServices port = service.getWebServicesPort();
    	
    	String keywordFiltro = request.getParameter("filtro");
    	String empresaFiltro = request.getParameter("empresaFiltro");
    	
		DtKeywordWS keys = port.getDTKeyword();
		DtUsuarioWS usuarios = port.listarUsuarios();
		List<DtEmpresa> emp = new ArrayList<>();
		for (DtUsuario user: usuarios.getUsers()) {
			if (user instanceof DtEmpresa) {
				DtEmpresa e = (DtEmpresa) user;
				emp.add(e);
			}
		}
		request.setAttribute("keywords", keys.getKeys());
		request.setAttribute("empresas", emp);		
    	if (keywordFiltro != null) {
    		request.setAttribute("filtro", keywordFiltro);
    	} else {
    		request.setAttribute("filtro", null);
    	}
    	
    	if (empresaFiltro != null) {
    		request.setAttribute("empresaFiltro", empresaFiltro);
    	} else {
    		request.setAttribute("empresaFiltro", empresaFiltro);
    	}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			aplicarFiltros(request);
		} catch (UsuariosNoExistenException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		publicar.WebServicesService service = new publicar.WebServicesService();
		publicar.WebServices port = service.getWebServicesPort();
		
		try {
			DtOfertaLaboralWS dtols = port.getDTOfertasLaborales();
			request.getSession().setAttribute("listaOfertasLaborales", dtols.getOfs());
		} catch (OfertasLaboralesNoExistenNingunaException_Exception e) {
			
		}

		request.getSession().setAttribute("filterType", request.getParameter("filterType"));
		request.getRequestDispatcher("/WEB-INF/mobile/template/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
