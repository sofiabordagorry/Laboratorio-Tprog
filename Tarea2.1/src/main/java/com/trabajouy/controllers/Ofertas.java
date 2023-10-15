package com.trabajouy.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import excepciones.OfertasLaboralesNoExistenNingunaException;
import logica.DTOfertaLaboral;
import logica.Factory;
import logica.IOfertaLaboral;
import logica.Usuario;

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
    	String keywordFiltro = request.getParameter("filtro");
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
		Factory fac = Factory.getInstance();
		IOfertaLaboral iol = fac.getIOfertaLaboral();
		try {
			DTOfertaLaboral dtols[] = iol.listarTodasOfertasLaborales();
			String filterType = request.getParameter("filterType");
			request.getSession().setAttribute("filterType", filterType);
			request.getSession().setAttribute("listaOfertasLaborales", dtols);
		} catch (OfertasLaboralesNoExistenNingunaException e) {
			
		}
		request.getRequestDispatcher("/WEB-INF/template/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
