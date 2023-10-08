package com.trabajouy.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;

import com.trabajouy.exceptions.ExisteUnUsuarioYaRegistradoException;
import com.trabajouy.exceptions.YaSePostuloException;
import com.trabajouy.model.DTUsuario;
import com.trabajouy.model.Empresa;
import com.trabajouy.model.Factory;
import com.trabajouy.model.IUsuario;
import com.trabajouy.model.LoginEstado;
import com.trabajouy.model.ManejadorOfertaLaboral;
import com.trabajouy.model.ManejadorUsuario;
import com.trabajouy.model.OfertaLaboral;
import com.trabajouy.model.Postulante;
import com.trabajouy.model.Usuario;

@WebServlet("/PostularAOfertaLaboral")
public class PostulacionAOfertaLaboral extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public PostulacionAOfertaLaboral() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Verificar el estado de login
        // Obtén el nickname del usuario en sesión (si hay uno)
        HttpSession session = request.getSession();
        Usuario user = (Usuario) session.getAttribute("usuario_logueado");
		boolean seCumpleExcepcion = false;

        if (user == null) {
            response.sendError(403);
        } else {
            if (user instanceof Empresa) {
                response.sendError(403);
            } else if (user instanceof Postulante) {
                Factory fac = Factory.getInstance();
                IUsuario icu = fac.getIUsuario();
                ManejadorOfertaLaboral manejadorOf = ManejadorOfertaLaboral.getInstance();
                ManejadorUsuario manejadorUsu = ManejadorUsuario.getInstancia();

                String CVReducido, motivacion;
                CVReducido = request.getParameter("CVReducido");
                motivacion = request.getParameter("motivacion");
                String ofertaL = request.getParameter("oferta");
                OfertaLaboral ofertaBuscada = manejadorOf.buscarOfertaLaboral(ofertaL);
                String postulante = user.getNickname();
                
    			try {
                    // Ingresar los datos de la postulación
                    icu.ingresarPostulacion(CVReducido, motivacion, LocalDate.now(), ofertaBuscada.getEmpresaCreadora(), ofertaL, postulante);
    
                    session.setAttribute("yaSePostulo", true);
                } catch (YaSePostuloException e) {
                    request.setAttribute("postulacionError", e.getMessage());
    				seCumpleExcepcion = true;
    			}
    		}
    		
    		
    		if(seCumpleExcepcion) {
                request.getRequestDispatcher("/WEB-INF/errores/postulacionAOfertaLaboralError.jsp").forward(request, response);
    		} else {
                request.getRequestDispatcher("/WEB-INF/altas/postulacionAOfertaLaboral.jsp").forward(request, response);
    		}

                
        }
    }

    public static LoginEstado getLoginEstado(HttpServletRequest request) {
    	System.out.println(request.getSession().getAttribute("loginEstado"));
        return (LoginEstado) request.getSession().getAttribute("loginEstado");
    }

    public static Usuario getUsuarioLogueado(HttpServletRequest request) {
        return (Usuario) request.getSession().getAttribute("usuario_logueado");
    }
}

