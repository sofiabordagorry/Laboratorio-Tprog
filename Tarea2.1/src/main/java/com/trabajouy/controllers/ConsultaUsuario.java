package com.trabajouy.controllers;

import java.io.IOException;

import com.trabajouy.exceptions.ExisteUnUsuarioYaRegistradoException;
import com.trabajouy.exceptions.UsuariosNoExistenException;
import com.trabajouy.model.DTPostulante;
import com.trabajouy.model.DTUsuario;
import com.trabajouy.model.Factory;
import com.trabajouy.model.IUsuario;
import com.trabajouy.model.ManejadorUsuario;
import com.trabajouy.model.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class ConsultaUsuario
 */
public class ConsultaUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ConsultaUsuario() {
    	super();
        // TODO Auto-generated constructor stub
    }
    
    private void processRequest(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
    	// Obtén el nickname del usuario que se consulta de alguna manera
    	ManejadorUsuario murs = ManejadorUsuario.getInstancia();
    	Factory fac = Factory.getInstance();
    	IUsuario iurs = fac.getIUsuario();
    	String nicknameConsultado = request.getParameter("usuarioConsultado");
    	
    	// Obtén el nickname del usuario en sesión (si hay uno)
    	HttpSession session = request.getSession();
    	Usuario user = (Usuario) session.getAttribute("usuario_logueado");
    	String nicknameEnSesion = null;
    	if(user != null) {
    		nicknameEnSesion = user.getNickname();
    	}
    	
    	if (nicknameConsultado == null) {
    	    // No se especificó un nickname para consultar, mostrar la página ListarUsuarios
    		try {
				DTUsuario[] users = iurs.listarUsuarios();
	    		request.setAttribute("SystemUsers", users);
				request.setAttribute("NoUsersInSystem_Error", false);
	    	    request.getRequestDispatcher("/WEB-INF/listar/listarUsuarios.jsp").forward(request, response);
			} catch (UsuariosNoExistenException e) {
				request.setAttribute("NoUsersInSystem_Error", true);
	    		request.getRequestDispatcher("/WEB-INF/listar/listarUsuarios.jsp").forward(request, response);
			}


    	} else {
	        DTUsuario userInfo = iurs.mostrarInformacionUsuario(nicknameConsultado);
	        //request.setAttribute("userInformation", userInfo);
	        request.setAttribute("userData", userInfo);
    	    if (nicknameEnSesion != null && nicknameEnSesion.equals(nicknameConsultado)) {
    	        // El usuario en sesión coincide con el usuario consultado
    	        if (murs.existeEmpresa(nicknameConsultado)) {
    	            // Es una empresa, mostrar MiPerfilEmpresa
    	            request.getRequestDispatcher("/WEB-INF/consultas/miPerfilEmpresa.jsp").forward(request, response);
    	        } else if (murs.existePostulante(nicknameConsultado)) {
    	            // Es un postulante, mostrar MiPerfilPostulante
    	            request.getRequestDispatcher("/WEB-INF/consultas/miPerfilPostulante.jsp").forward(request, response);
    	        }
    	    } else {
    	        if (murs.existeEmpresa(nicknameConsultado)) {
    	            // Es una empresa, mostrar ConsultarEmpresa
    	            request.getRequestDispatcher("/WEB-INF/consultas/consultarEmpresa.jsp").forward(request, response);
    	        } else if (murs.existePostulante(nicknameConsultado)) {
    	            // Es un postulante, mostrar ConsultarPostulante
    	            request.getRequestDispatcher("/WEB-INF/consultas/consultarPostulante.jsp").forward(request, response);
    	        }
    	    }
    	}
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}