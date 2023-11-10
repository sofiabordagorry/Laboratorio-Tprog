<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="publicar.DtUsuario"%>
<%@page import="publicar.DtEmpresa"%>
<%@page import="publicar.DtOfertaLaboral"%>
<%@page import="publicar.EstadoOL"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Base64" %>
<!DOCTYPE html>
<html lang="en">
	<head>
	    <meta charset="UTF-8">
	  	<jsp:include page="../template/head.jsp" />
	    <link rel="stylesheet" href="media/styles/stylesConsultas.css" />
	    <title>Trabajo.uy</title>
	</head>
	<body>
    	<jsp:include page="../template/navbar.jsp" />
    	<div class="container-fluid" id="web-content">
        	<div class="row justify-content-md-center">
				<div class="col col-md-9 col-sm-12" id="main-content">
	            	<div class="container-fluid" id="main-container">
                		<div class="row">
                			<div class="col-md-3" id="empresaConsultaInfo">
                    			<%-- Assuming 'imageBytes' is the byte array containing the image data --%>
								<%
	                        	DtUsuario userInfo = (DtUsuario) request.getAttribute("userData");
	                        	DtEmpresa eInfo = null;
	                        	eInfo = (DtEmpresa) userInfo;
	                        	List<DtOfertaLaboral> offers = eInfo.getOfertasLaborales();

	                        		
								%>
								<img class="card-img" src="media/imagenes/NoImageUser.png" style="max-width: 550px; max-height: 550px;"/>
                        	</div>
	                        <div class="col">
	                            <h2><%= eInfo.getNickname()%></h2>
	                            <p><i class="" style="color: #000000;"></i> <b>Nombre:</b><%=eInfo.getNombre() %></p>
	                            <p><i class=""></i> <b>Apellido:</b><%=eInfo.getApellido() %></p>
	                            <p><i class=""></i> <b>Correo: </b><%=eInfo.getCorreo() %></p>
	                            <p><i class=""></i> <b>Descripcion: </b><%=eInfo.getDescripcion() %></p>
	                            <p><i class=""></i> <b>Link: </b> <a href="https://www.ecotechinnovations.com/"><%=eInfo.getLink() %></a></p>
	
	                        </div>
                    	</div>
                    	<div class ="row" style="margin-top: 1%;">
							<div class = "col-md-6 col-md-12 text-center">
                        		<h3>Ofertas Laborales</h3>
                        		<div class="table-container">
	                        		<table class="table" id="ofertas-laborales-consulta-empresa">
										<thead>
											<th>Nombre</th>
											<th>Departamento</th>
										</thead>
										<tbody>
											<%	
											
												for(DtOfertaLaboral entry : offers){
													if (entry.getEstado() == EstadoOL.CONFIRMADA) {
											%>
											<tr>
												<td><a href="ConsultaOfertaLaboral?oferta_consultada=<%=entry.getNombre() %>"><%=entry.getNombre() %></a></td>
												<td><%=entry.getDepartamento() %></td>
											</tr>
											<%
													}
												}
											%>
										</tbody>
									</table>
                        		</div>
                        	<% if ((boolean) request.getAttribute("logueado")){
                        		Boolean siguiendo = (Boolean) request.getAttribute("siguiendo");
                            	if (siguiendo != null && siguiendo){%>
	                            		
                            		<form action="ConsultaUsuario" method="POST">
								        <input type="hidden" name="seguidor" value="<%=eInfo.getNickname() %>">
								        <button type="submit" class="btn btn-primary">Dejar de seguir</button>
								    </form>
	                            		
	                            <%} else{%>
	                            
	                            	<form action="ConsultaUsuario" method="POST">
								        <input type="hidden" name="seguidor" value="<%=eInfo.getNickname() %>">
								        <button type="submit" class="btn btn-primary">Seguir</button>
								    </form>
	                            	
	                            	
	                            <%}
	                        }%>
							</div>
                    	</div>
                	</div>
	        	</div>
        	</div>
        </div>
    	<jsp:include page="../template/footer.jsp" />
	</body>
</html>