<%@page import="java.util.Map"%>
<%@page import="java.util.LinkedList"%>
<%@page import="java.util.Base64"%>
<%@page import="com.trabajouy.model.*"%>
<%@page import="jakarta.servlet.http.HttpSession"%>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<div class="col col-md-9 col-sm-12" id="main-content">
	<div class="col ofertaLaboral">
		<!--  <h2>Mis Postulaciones</h2>
		<div class="card mb-3" style="max-width: 650px;">
			<div class="row no-gutters">
			
			<%
				Map<String, DTOfertaLaboral> postulaciones = (Map) request.getSession().getAttribute("misPostulaciones");
				for(Map.Entry<String, DTOfertaLaboral> entry : postulaciones.entrySet()) {
					DTOfertaLaboral dtol = entry.getValue();
			%>
			
				<div class="col-md-4">
					<img src="data:image/jpeg;base64, <%= new String(Base64.getEncoder().encode(dtol.getImagen())) %> class="card-img" alt="...">
				</div>
				<div class="col-md-8">
					<div class="card-body">
						<h5 class="card-title"><%=dtol.getNombre()%></h5>
						<p class="card-text"><%=dtol.getDescripcion()%></p>
						<button class="btn"><a href="consultaOfertaLaboral">Ver oferta/postulacion</a></button>
					</div>
				</div>
				
			<% 
				}
			%>
			</div>
		</div>-->
	</div>
</div>