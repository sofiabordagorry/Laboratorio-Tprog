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
<div class="row" id="sidebar">
	<div class="list-group m-2">
		<a href="#">
			<div class="header d-flex" data-toggle="collapse" aria-controls="keywordList" href="#keywordList">
				<h2 class="list-group-item-heading mr-auto p-2">Keywords</h2>
				<img class="p-2" width="50" height="50" src="media/imagenes/collapse.png" />
			</div>
		</a>
		<div id="keywordList" class="collapse">
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
	</div>
</div>
