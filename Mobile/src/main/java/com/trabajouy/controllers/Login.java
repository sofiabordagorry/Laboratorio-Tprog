package com.trabajouy.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import publicar.DtEmpresa;
import publicar.DtKeyword;
import publicar.DtKeywordWS;
import publicar.DtUsuario;
import publicar.DtUsuarioWS;
import publicar.LoginEstado;
import publicar.UsuariosNoExistenException_Exception;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private void processRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
				req.getRequestDispatcher("/WEB-INF/mobile/home/login.jsp").
				forward(req, resp);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.processRequest(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession objSesion = request.getSession();
		
		publicar.WebServicesService service = new publicar.WebServicesService();
		publicar.WebServices port = service.getWebServicesPort();
		
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		LoginEstado nuevoEstado = LoginEstado.NO_LOGIN;
		RequestDispatcher dispatcher;
		
		// chequea contraseña
		DtUsuario usr = port.buscarUsuario(login);
		if (usr.getNickname() == null) {
			usr = port.buscarUsuarioPorMail(login);
		}
		
		if (usr.getNickname() != null && !usr.getContrasenia().equals(password)) {
			nuevoEstado = LoginEstado.LOGIN_INCORRECTO;
			dispatcher = request.getRequestDispatcher("/WEB-INF/mobile/home/login.jsp");
		} else if (usr.getNickname() != null && usr.getContrasenia().equals(password) && !(usr instanceof DtEmpresa)) {
			nuevoEstado = LoginEstado.LOGIN_CORRECTO;
			// setea el usuario logueado
			request.getSession().setAttribute("usuario_logueado", usr);
			dispatcher = request.getRequestDispatcher("/WEB-INF/mobile/template/index.jsp");
		} else {
			nuevoEstado = LoginEstado.LOGIN_INCORRECTO;
			dispatcher = request.getRequestDispatcher("/WEB-INF/mobile/home/login.jsp");
		}
		
		DtKeywordWS k = port.getDTKeyword();
		List<DtKeyword> dtk = k.getKeys();
		request.setAttribute("keywords", dtk);
		DtUsuarioWS usuarios;
		List<DtEmpresa> emp = null;
		try {
			usuarios = port.listarUsuarios();
			emp = new ArrayList<>();
			for (DtUsuario user: usuarios.getUsers()) {
				if (user instanceof DtEmpresa) {
					DtEmpresa e = (DtEmpresa) user;
					emp.add(e);
				}
			}
		} catch (UsuariosNoExistenException_Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("empresas", emp);
		
		objSesion.setAttribute("estado_sesion", nuevoEstado);
		dispatcher.forward(request, response);
	}
}
