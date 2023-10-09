package com.trabajouy.controllers;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.trabajouy.cargarDatos.*;

/**
 * Servlet implementation class cargarDatos
 */
public class CargarDatos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CargarDatos() {
        super();
        // TODO Auto-generated constructor stub
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
    			ServletContext servletContext = getServletContext();
    			CargadorDeDatos.cargarUsuarios(servletContext);
    			CargadorDeDatos.cargarPostulantes(servletContext);
    			CargadorDeDatos.cargarEmpresas(servletContext);
    			CargadorDeDatos.ingresarUsuarios(servletContext);
    			CargadorDeDatos.ingresarKeywords(servletContext);
    			CargadorDeDatos.ingresarTipos(servletContext);
    			CargadorDeDatos.ingresarPaquetes(servletContext);
    			CargadorDeDatos.ingresarPaqueteTipos(servletContext);
    			CargadorDeDatos.ingresarComprasPaquetes(servletContext);
    			CargadorDeDatos.ingresarOfertasLaborales(servletContext);
    			CargadorDeDatos.ingresarKeywordsOfertas(servletContext);
    			CargadorDeDatos.ingresarPostulaciones(servletContext);
				req.getRequestDispatcher("Home").
				forward(req, resp);
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}
