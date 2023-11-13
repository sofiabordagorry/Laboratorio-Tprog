<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="publicar.DtUsuario" %>
<%@ page import="publicar.DtEmpresa" %>
<%@ page import="publicar.DtOfertaLaboral" %>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<jsp:include page="../template/head.jsp" />
		<link rel="stylesheet" href="media/styles/busqueda.css">
		<title>Trabajo.uy</title>
	</head>
	<body>
		<%
		List<DtUsuario> usuariosFiltrada = (List<DtUsuario>) request.getAttribute("usuariosFiltrados");
		List<DtOfertaLaboral> ofertasFiltradas = (List<DtOfertaLaboral>) request.getAttribute("ofertasFiltradas");
		%>
		<jsp:include page="../template/navbar.jsp" />
		<div class="container-fluid" id="web-content">
			<div class="row justify-content-md-center">
				<jsp:include page="../template/sidebar.jsp" />
				<div class="col col-md-9 col-sm-12" id="main-content">		
					<div class="row">
						<div class="col">
							<h1>Empresas</h1>
							<%
							if (usuariosFiltrada != null) {
								for (DtUsuario user: usuariosFiltrada) {
									DtEmpresa usuario = (DtEmpresa) user;
									%>
									<div class="card mb-3">
										<div class="empresa-res card-body container-fluid">
											<p class="card-title"><b><%= usuario.getNickname() %></b></p>
											<%
												if (usuario.getDescripcion().length() >= 150) {
											%>											
											<p class="card-text"><%= usuario.getDescripcion().substring(0, 150) %>...</p>
											<%
												} else {
											%>
											<p class="card-text"><%= usuario.getDescripcion() %></p>
											<% } %>
											<button class="btn mx-auto d-block">
												<a href="ConsultaUsuario?usuarioConsultado=<%=usuario.getNickname()%>">Ver perfil</a>
											</button>
										</div>									
									</div>
									<%
								}
							}
							%>
						</div>
						
						<div class="col">
							<h1>Ofertas Laborales</h1>
							<%
							if (ofertasFiltradas != null) {
								for (DtOfertaLaboral of: ofertasFiltradas) {
									%>
									<div class="card mb-3">
										<div class="oferta-res card-body container-fluid">
											<p class="card-title"><b><%= of.getNombre() %></b></p>
											<%
												if (of.getDescripcion().length() >= 150) {
											%>
											<p class="card-text"><%= of.getDescripcion().substring(0, 150) %>...</p>
											<%
												} else {
											%>
											<p class="card-text"><%= of.getDescripcion() %></p>
											<% } %>
											<button class="btn mx-auto d-block">
												<a href="ConsultaOfertaLaboral?oferta_consultada=<%=of.getNombre()%>">Ver oferta</a>
											</button>
										</div>									
									</div>
									<% 
								}
							}
							%>
						</div>
					</div>	
				</div>
			</div>
		</div>
	
		<jsp:include page="../template/footer.jsp" />
	</body>
</html>