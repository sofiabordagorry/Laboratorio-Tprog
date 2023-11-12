<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@ page import="publicar.DtOfertaLaboral"%>
<%@ page import="publicar.DtUsuario"%>
<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<div class="col col-md-9 col-sm-12" id="main-content">
	<div class="col ofertaLaboral">
		<h2>Mis Ofertas</h2>
		<%
			String of = (String) request.getAttribute("filterType");
			DtUsuario user = (DtUsuario) request.getSession().getAttribute("usuario_logueado");
			List<DtOfertaLaboral> ols = (List<DtOfertaLaboral>) request.getSession().getAttribute("listaOfertasLaborales");
			String keywordFiltro = (String) request.getSession().getAttribute("filtro");
			String misOfertasBool = (String) request.getSession().getAttribute("filterType");
			if(ols != null){
			for(int i = 0; i < ols.size(); i++) {
				if(misOfertasBool.equals("MyOffers")) {
					if (ols.get(i).getDataEmpresa().equals(user.getNickname())) {
						if (keywordFiltro == null || (keywordFiltro != null /*&& ols[i].getKeywords().containsKey(keywordFiltro)*/)) {	
		%>
		<div class="card mb-3" style="max-width: 650px;">
			<div class="row no-gutters">
				<div class="col-md-4">
					<img src="media/imagenes/ofertaSinFoto.jpg" class="card-img" alt="...">
				</div>
				<div class="col-md-8">
					<div class="card-body">
						<h5 class="card-title"><%= ols.get(i).getNombre() %></h5>
						<p class="card-text"><%= ols.get(i).getDescripcion() %></p>
						<button class="btn"><a href="ConsultaOfertaLaboral?oferta_consultada=<%=ols.get(i).getNombre()%>">Ver oferta</a></button>
					</div>
				</div>
			</div>
		</div>
		<%
						}
					}
				}
			}
			}else{
		%>
			<h3>Lo sentimos, no ha creado ninguna oferta laboral</h3>
		<%
			}
		%>
	</div>
</div>