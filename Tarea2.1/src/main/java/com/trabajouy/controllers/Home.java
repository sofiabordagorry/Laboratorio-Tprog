package com.trabajouy.controllers;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import com.trabajouy.exceptions.OfertasLaboralesNoExistenNingunaException;
import com.trabajouy.model.*;

@WebServlet ("/home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Home() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public static void init(HttpServletRequest request) {
    	HttpSession misesion = request.getSession();
    	if (misesion.getAttribute("loginEstado") == null) {
    		misesion.setAttribute("loginEstado", LoginEstado.NO_LOGIN);
    		
    		Factory fac = Factory.getInstance();
    		ManejadorOfertaLaboral mol = ManejadorOfertaLaboral.getInstance();
    		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
    		LocalDate ldate = LocalDate.now();
    		Empresa emp = new Empresa("nick", "Rodolfo", "Gutierrez", "hola@gmail.com", "Desc", "link", "123", new byte[0]);
    		Postulante post = new Postulante("jokin", "joaco", "corbo", "correo@c.com", ldate, "uru", "123", new byte[0]);
    		mu.agregarEmpresa(emp);
    		mu.agregarPostulante(post);
    		Keyword k = new Keyword("pato");
    		Map<String, Keyword> keyss = new HashMap<>();
    		keyss.put("pato", k);
    		Tipo t = new Tipo("clean", "tipo clean", 1, 1000, 200000, ldate);
    		OfertaLaboral ol = new OfertaLaboral("Jugador de CS2 Profesional", "Que pegue unos buenos tiros", "Punta Carretas", "Montevideo", "09:20", 20000, ldate, 20, t, keyss, emp);
    		ol.setEstado(EstadoOL.Aceptada);
    		mol.agregarOfertaLaboral(ol);
    		Postulacion postu = new Postulacion(ldate, "Me gusta el fortnite", "Tengo monitor", post, ol);
    		post.agregarPostulacion(postu);
    		ol.agregarPostulacion(postu);
    		IOfertaLaboral iol = fac.getIOfertaLaboral();
    		try {
    			DTOfertaLaboral dtols[] = iol.listarTodasOfertasLaborales();
    			request.getSession().setAttribute("listaOfertasLaborales", dtols);
    		} catch (OfertasLaboralesNoExistenNingunaException e) {
    			e.printStackTrace();
    		}
    		
    		
    		mol.agregarKeyword(k);
    		Keyword[] keys = mol.getKeywords();
    		ServletContext context = request.getServletContext();
    		context.setAttribute("keywords", keys);
    	} 
    }
    
    public static LoginEstado getLoginEstado(HttpServletRequest request) {
    	return (LoginEstado) request.getSession().getAttribute("loginEstado");
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	init(request);
    	
    	switch (getLoginEstado(request)) {
    		case NO_LOGIN:
    			request.getRequestDispatcher("/WEB-INF/template/index.jsp").forward(request, response);
    		case LOGIN_INCORRECTO:
    			break;
    		case LOGIN_CORRECTO:
    			request.getRequestDispatcher("/WEB-INF/template/index.jsp").forward(request, response);;
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
