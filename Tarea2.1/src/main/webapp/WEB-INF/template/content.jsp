<%@page import="com.trabajouy.model.*"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<div class="col col-md-9 col-sm-12" id="main-content">
	<div class="col ofertaLaboral">
		<h2>Todas las ofertas</h2>
		<div class="card mb-3" style="max-width: 650px;">
		
		<%
			DTOfertaLaboral[] ols = (DTOfertaLaboral[]) request.getSession().getAttribute("listaOfertasLaborales");
			for(int i = 0; i < ols.length; i++) {
				
		%>
		
			<div class="row no-gutters">
				<div class="col-md-4">
					<img src="https://kinsta.com/es/wp-content/uploads/sites/8/2021/12/front-end-developer-1024x512.png" class="card-img" alt="...">
				</div>
				<div class="col-md-8">
					<div class="card-body">
						<h5 class="card-title"><%= ols[i].getNombre() %></h5>
						<p class="card-text"><%= ols[i].getDescripcion() %></p>
						<button class="btn"><a href="ConsultaOfertaLaboral?oferta_consultada=<%=ols[i].getNombre()%>">Ver oferta</a></button>
					</div>
				</div>
			</div>
		<%
			}
		%>
		</div>
	</div>
</div>