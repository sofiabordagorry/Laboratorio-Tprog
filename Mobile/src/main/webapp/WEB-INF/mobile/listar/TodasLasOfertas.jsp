<%@page import="java.util.Map"%>
<%@ page import="publicar.DtOfertaLaboral"%>
<%@ page import="publicar.DtUsuario"%>
<%@ page import="publicar.EstadoOL"%>
<%@ page import="publicar.DtKeyword"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<div class="col col-md-9 col-sm-12" id="main-content">
	<div class="col ofertaLaboral">
		<h2>Todas las ofertas</h2>
		<%
			DtUsuario user = (DtUsuario) request.getSession().getAttribute("usuario_logueado");
			List<DtOfertaLaboral> ols = (List<DtOfertaLaboral>) request.getSession().getAttribute("listaOfertasLaborales");
			String keywordFiltro = (String) request.getAttribute("filtro");
			String empresaFiltro = (String) request.getAttribute("empresaFiltro");
			if(ols != null){
			for(int i = 0; i < ols.size(); i++) {
					if(ols.get(i).getEstado().equals(EstadoOL.CONFIRMADA)) {
						boolean keywordFound = false;
						boolean empresaFound = false;
						if (keywordFiltro != null) {
				            for (DtKeyword keyword : ols.get(i).getDataKeywords()) {
				                if (keyword.getNombre().equals(keywordFiltro)) {
				                    keywordFound = true;
				                    break; // Keyword found, exit the loop
				                }
				            }
						} else if (empresaFiltro != null) {
							
								if (ols.get(i).getDataEmpresa().equals(empresaFiltro)) {
									empresaFound = true;
								}
						}
						if ((keywordFiltro == null && empresaFiltro == null) || (keywordFound || empresaFound)) {	
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
			}else{
		%>
			<h3>Lo sentimos, en este momento no hay ofertas laborales disponibles</h3>
		<%
			}
		%>
	</div>
</div>