<%@page import="logica.DTOfertaLaboral"%>
<%@page import="java.util.Map"%>
<%@page import="logica.Usuario"%>
<%@page import="logica.EstadoOL"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<div class="col col-md-9 col-sm-12" id="main-content">
	<div class="col ofertaLaboral">
		<%
			String of = (String) request.getAttribute("filterType");
			if(of != null) {
				if(of.equals("MyOffers")) {
		%>
		<h2>Mis Ofertas</h2>
		<%
				} else {
		%>
		<h2>Todas las ofertas</h2>
		<%
				}
			} else {	
		%>
		<h2>Todas las ofertas</h2>
		<%
			}
			Usuario user = (Usuario) request.getSession().getAttribute("usuario_logueado");
			DTOfertaLaboral[] ols = (DTOfertaLaboral[]) request.getSession().getAttribute("listaOfertasLaborales");
			String keywordFiltro = (String) request.getSession().getAttribute("filtro");
			String misOfertasBool = (String) request.getSession().getAttribute("filterType");
			for(int i = 0; i < ols.length; i++) {
				if(!misOfertasBool.equals("MyOffers")) {
					if(ols[i].getEstado() == EstadoOL.Confirmada) {
						if (keywordFiltro == null || (keywordFiltro != null && ols[i].getKeywords().containsKey(keywordFiltro))) {	
		%>
		<div class="card mb-3" style="max-width: 650px;">
			<div class="row no-gutters">
				<div class="col-md-4">
					<img src="media/imagenes/ofertaSinFoto.jpg" class="card-img" alt="...">
				</div>
				<div class="col-md-8">
					<div class="card-body">
						<h5 class="card-title"><%= ols[i].getNombre() %></h5>
						<p class="card-text"><%= ols[i].getDescripcion() %></p>
						<button class="btn"><a href="ConsultaOfertaLaboral?oferta_consultada=<%=ols[i].getNombre()%>">Ver oferta</a></button>
					</div>
				</div>
			</div>
		</div>
		<%
						}
					}	
				} else {
					if (keywordFiltro == null || (keywordFiltro != null && ols[i].getKeywords().containsKey(keywordFiltro))) {
		%>
		<div class="card mb-3" style="max-width: 650px;">
			<div class="row no-gutters">
				<div class="col-md-4">
					<img src="media/imagenes/ofertaSinFoto.jpg" class="card-img" alt="...">
				</div>
				<div class="col-md-8">
					<div class="card-body">
						<h5 class="card-title"><%= ols[i].getNombre() %></h5>
						<p class="card-text"><%= ols[i].getDescripcion() %></p>
						<button class="btn"><a href="ConsultaOfertaLaboral?oferta_consultada=<%=ols[i].getNombre()%>">Ver oferta</a></button>
					</div>
				</div>
			</div>
		</div>
		<%
					}
				}
			}
		%>
	</div>
</div>