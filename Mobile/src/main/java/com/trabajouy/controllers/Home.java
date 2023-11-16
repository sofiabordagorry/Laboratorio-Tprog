package com.trabajouy.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import publicar.DtEmpresa;
import publicar.DtKeywordWS;
import publicar.DtOfertaLaboralWS;
import publicar.DtUsuario;
import publicar.DtUsuarioWS;
import publicar.LoginEstado;
import publicar.OfertasLaboralesNoExistenNingunaException_Exception;
import publicar.UsuariosNoExistenException_Exception;

@WebServlet ("/home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Home() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public static void init(HttpServletRequest request) throws UsuariosNoExistenException_Exception {
    	HttpSession misesion = request.getSession();
    	if (misesion.getAttribute("estado_sesion") == null) {
    		misesion.setAttribute("estado_sesion", LoginEstado.NO_LOGIN);
    		misesion.setAttribute("filtro", null);
    	}
		
		publicar.WebServicesService service = new publicar.WebServicesService();
		publicar.WebServices port = service.getWebServicesPort();
    	
    	// Factory fac = Factory.getInstance();
    	// IOfertaLaboral iol = fac.getIOfertaLaboral();
    	
		DtKeywordWS keys = port.getDTKeyword();
		request.setAttribute("keywords", keys.getKeys());
		DtUsuarioWS usuarios = port.listarUsuarios();
		List<DtEmpresa> emp = new ArrayList<>();
		for (DtUsuario user: usuarios.getUsers()) {
			if (user instanceof DtEmpresa) {
				DtEmpresa e = (DtEmpresa) user;
				emp.add(e);
			}
		}
		request.setAttribute("empresas", emp);
    	//request.getSession().setAttribute("filterType", "AllOffers");
		
		try {
			DtOfertaLaboralWS dtols = port.getDTOfertasLaborales();
			request.getSession().setAttribute("listaOfertasLaborales", dtols.getOfs());
		} catch (OfertasLaboralesNoExistenNingunaException_Exception e) {
			e.printStackTrace();
		}
    }
    
    public static LoginEstado getLoginEstado(HttpServletRequest request) {
    	return (LoginEstado) request.getSession().getAttribute("estado_sesion");
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	try {
			init(request);
		} catch (UsuariosNoExistenException_Exception e) {
			e.printStackTrace();
		}

		switch (getLoginEstado(request)) {
		case NO_LOGIN:
			request.getRequestDispatcher("/WEB-INF/mobile/home/login.jsp").forward(request, response);
		case LOGIN_INCORRECTO:
			break;
		case LOGIN_CORRECTO:
			request.getRequestDispatcher("/WEB-INF/mobile/template/index.jsp").forward(request, response);;
		}
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}
