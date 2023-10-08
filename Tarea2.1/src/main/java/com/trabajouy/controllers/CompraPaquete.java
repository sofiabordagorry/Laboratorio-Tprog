package com.trabajouy.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.trabajouy.model.Empresa;
import com.trabajouy.model.Factory;
import com.trabajouy.model.IOfertaLaboral;
import com.trabajouy.model.ManejadorTipo;
import com.trabajouy.model.ManejadorUsuario;
import com.trabajouy.model.Paquete;
import com.trabajouy.model.Usuario;
import com.trabajouy.model.Compra;
import com.trabajouy.model.DTPaquete;

/**
 * Servlet implementation class CompraPaquete
 */
public class CompraPaquete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompraPaquete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

    	Factory fac = Factory.getInstance();
    	IOfertaLaboral col = fac.getIOfertaLaboral();
    	ManejadorTipo mtip = ManejadorTipo.getInstancia();
    	
		HttpSession session = request.getSession();
     	Usuario user = (Usuario) session.getAttribute("usuario_logueado");
     	
     	ManejadorUsuario murs = ManejadorUsuario.getInstancia();
     	
     	request.setAttribute("paqComprado", true);
     	
     	Empresa emp = murs.buscarEmpresa(user.getNickname());
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
     	
     	
     	
		doGet(request, response);
	}

}
