package com.trabajouy.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import publicar.DtKeywordWS;
import publicar.DtOfertaLaboralWS;
import publicar.OfertasLaboralesNoExistenNingunaException_Exception;

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
    
    public static void aplicarFiltros(HttpServletRequest request) {
		publicar.WebServicesService service = new publicar.WebServicesService();
		publicar.WebServices port = service.getWebServicesPort();
    	
    	String keywordFiltro = request.getParameter("filtro");
    	
		DtKeywordWS keys = port.getDTKeyword();
		request.setAttribute("keywords", keys.getKeys());
		
    	if (keywordFiltro != null) {
    		request.setAttribute("filtro", keywordFiltro);
    	} else {
    		request.setAttribute("filtro", null);
    	}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		aplicarFiltros(request);
		publicar.WebServicesService service = new publicar.WebServicesService();
		publicar.WebServices port = service.getWebServicesPort();
		
		try {
			DtOfertaLaboralWS dtols = port.getDTOfertasLaborales();
			request.getSession().setAttribute("listaOfertasLaborales", dtols.getOfs());
		} catch (OfertasLaboralesNoExistenNingunaException_Exception e) {
			
		}

		request.getSession().setAttribute("filterType", request.getParameter("filterType"));
		request.getRequestDispatcher("/WEB-INF/desktop/template/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
