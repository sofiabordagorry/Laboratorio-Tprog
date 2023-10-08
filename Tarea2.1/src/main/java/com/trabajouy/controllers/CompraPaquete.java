package com.trabajouy.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import logica.ManejadorTipo;
import logica.Paquete;
import logica.Usuario;
import logica.IOfertaLaboral;
import logica.Factory;
import logica.DTPaquete;

import excepciones.PaqueteYaCompradoException;

/**
 * Servlet implementation class CompraPaquete
 */
public class CompraPaquete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public CompraPaquete() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

    	Factory fac = Factory.getInstance();
    	IOfertaLaboral col = fac.getIOfertaLaboral();
    	ManejadorTipo mtip = ManejadorTipo.getInstancia();
    	
		HttpSession session = request.getSession();
     	Usuario user = (Usuario) session.getAttribute("usuario_logueado");
     	
     	//ManejadorUsuario murs = ManejadorUsuario.getInstancia();
     	
     	request.setAttribute("paqComprado", true);
     	
     	//Empresa emp = murs.buscarEmpresa(user.getNickname());
     	String nombrePaq = request.getParameter("paquete");
     	
     	try {
     		col.comprarPaquete(nombrePaq,user.getNickname());
     		request.setAttribute("compraExitosa", true);
     	}catch(PaqueteYaCompradoException e) {
     		request.setAttribute("compraExitosa", false);
     	}
     	
     	Paquete paq = mtip.buscarPaquete(nombrePaq);
        DTPaquete paquete = paq.getDataPaquete();
        request.setAttribute("dataPaquete", paquete);
        request.getRequestDispatcher("/WEB-INF/consultas/consultarPaquete.jsp").forward(request, response);
	}

}
