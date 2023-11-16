<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="publicar.DtUsuario"%>
<%@ page import="publicar.DtEmpresa"%>
<%@ page import="publicar.DtKeyword"%>
<%@ page import="jakarta.servlet.ServletContext" %>
<%@ page import="jakarta.servlet.ServletException" %>
<%@ page import="jakarta.servlet.http.HttpServletRequest" %>
<%@ page import="jakarta.servlet.http.HttpServletResponse" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.util.List" %>
<div id="sidebar">
	<div class="list-group m-2">
		<a href="#">
			<div class="header d-flex" data-toggle="collapse" aria-controls="keywordList" href="#keywordList">
				<h2 class="list-group-item-heading mr-auto p-2">Filtros</h2>
				<img class="p-2" width="50" height="50" src="media/imagenes/collapse.png" />
			</div>
		</a>
		<div id="keywordList" class="collapse row">
			<div class="col-6">
			<%
				List<DtKeyword> keys = (List<DtKeyword>) request.getAttribute("keywords");
				if (keys != null) {
				for(int i = 0; i < keys.size(); i++) {
			%>
			<a class="list-group-item list-group-item-action" href="Ofertas?filtro=<%= keys.get(i).getNombre() %>&filterType=<%= request.getSession().getAttribute("filterType") %>"><%= keys.get(i).getNombre() %></a>
			<%
					}
				}
			%>
			</div>
			<div class="col-6">
			<%
				List<DtEmpresa> empresas = (List<DtEmpresa>) request.getAttribute("empresas");
				if (empresas != null) {
					for(int i = 0; i < empresas.size(); i++) {
			%>
			<a class="list-group-item list-group-item-action" href="Ofertas?empresaFiltro=<%= empresas.get(i).getNickname() %>&filterType=<%= request.getSession().getAttribute("filterType") %>"><%= empresas.get(i).getNickname() %></a>
			<%
					}
				}
			%>
		</div>
	</div>
</div>
