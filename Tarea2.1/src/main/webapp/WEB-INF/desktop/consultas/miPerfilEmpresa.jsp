<%@page import="publicar.DtUsuario"%>
<%@page import="publicar.DtEmpresa"%>
<%@page import="publicar.DtCompra"%>
<%@page import="publicar.DtOfertaLaboral"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.util.List" %>
<%@page import="java.util.Base64" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
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
	            <jsp:include page="../template/sidebar.jsp" />
	            <div class="col col-md-9 col-sm-12" id="main-content">
	                <div class="container-fluid" id="main-container">
	                	<div class="row">
	                	
		                	<%
		                		DtUsuario userInfo = (DtUsuario) request.getAttribute("userData");
		                		DtEmpresa eInfo = null;
		                		eInfo = (DtEmpresa) userInfo;
		                		List<DtOfertaLaboral> offers = eInfo.getOfertasLaborales();
		                	%>
	                    	<div class="col-md-3" id="empresaPefilInfo">
                        		<%-- Assuming 'imageBytes' is the byte array containing the image data --%>
								    <img class="card-img" src="media/imagenes/NoImageUser.png" style="max-width: 550px; max-height: 550px;"/>
                            </div>
                            <div class="col">
                                <h2><%=eInfo.getNickname() %></h2>
                                <p><i class="" style="color: #000000;"></i> <b>Nombre:</b><%=eInfo.getNombre() %></p>
                                <p><i class=""></i> <b>Apellido:</b><%=eInfo.getApellido() %></p>
                                <p><i class=""></i> <b>Correo: </b><%=eInfo.getCorreo() %></p>
                                <p><i class=""></i> <b>Descripcion: </b><%=eInfo.getDescripcion() %></p>
                                <p><i class=""></i> <b>Link: </b> <a href="<%=eInfo.getLink() %>"><%=eInfo.getLink() %></a></p>
							</div>
	                    </div>
	                    <div class ="row" style="margin-top: 1%;">
							<div class = "col-md-3 text-center">
		                        <div class="row container-fluid">
		                    	<div class="" id="Siguiendo">
		                    		<div class="table-container">
		                    			<table class="table text-center" id="tablaSiguiendo">
		                    				<thead>
		                    					<tr>
		                    						<th>Siguiendo</th>
		                    					</tr>
		                    				</thead>
		                    				<tbody>
               					        	<%
		                    					String[] seguidos = (String[]) request.getAttribute("User_Following");
               					        		String[] seguidores = (String[]) request.getAttribute("User_Followers");
               					        		for(int i = 0; i < seguidos.length;i++){
		                    				%>
		                    					<tr>
		                    						<td><a href="ConsultaUsuario?usuarioConsultado=<%=seguidos[i]%>"><%=seguidos[i]%></a></td>
		                    					</tr>
		                    				<%
               					        		}
		                    				%>
		                    				</tbody>
		                    			</table>
		                    		</div>
		                    	</div>
		                    	<div class="" id="Seguidores">
	         	                	<div class="table-container">
	       	                	    	<table class="table text-center" id="tablaSeguidores">
		                    				<thead>
		                    					<tr>
		                    						<th>Seguidores</th>
		                    					</tr>
		                    				</thead>
		                    				<tbody>
		                    				<%
		                    					for(int i = 0; i < seguidores.length; i++){
		                    				%>
		                    					<tr>
		                    						<td><a href="ConsultaUsuario?usuarioConsultado=<%=seguidores[i]%>"><%=seguidores[i] %></a></td>
		                    					</tr>
		                    				<%
		                    					}
		                    				%>
		                    				</tbody>
		                    			</table>
		                    		</div>
		                    	</div>
		            		</div>
							</div>
							<div class = "col-md-9 text-center">
							<div class="mb-5 ">
		                        <h3>Compras de Paquetes</h3>
		                        <table class="table" id="tablaPrincipal">
									<thead>
									    <tr>
									      <th>Nombre de Paquete</th>
									      <th>Precio</th>
									      <th>Fecha de Vencimiento</th>
									    </tr>
								  	</thead>
									<tbody>
										<%
											List<DtCompra> paquetesComprados = eInfo.getPaqComprados();
											for(DtCompra compra : paquetesComprados){
										  	//ACA TENDRIA QUE PONER LA LOGICA DE LOS PAQUETES COMPRADOS
										%>
									    <tr>
									    	<td><a href="ConsultaPaquete?paqueteConsultado=<%=compra.getPaqComprado().getNombre()%>"><%=compra.getPaqComprado().getNombre()%></a></td>
											<td><%=compra.getPaqComprado().getCostoAsociado()%></td>
											<td><%=compra.getFechaVencimiento() %></td>
									    </tr>
										<%
											}
										%>
									</tbody>
								</table>
								</div>
								<h3>Ofertas Laborales</h3>
		                        <ul class="list-group" id="ofertas-laborales">
			                        <%
			                        	for(DtOfertaLaboral entry : offers){
			                        %>
			                        <li class="list-group-item text-center"><a href="ConsultaOfertaLaboral?oferta_consultada=<%=entry.getNombre()%>"><%=entry.getNombre() + "(" + entry.getEstado() + ")" %></a></li>
									<%
										}
									%>
								</ul>
							</div>

	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	    <jsp:include page="../template/footer.jsp" />
		<script src="media/javaScripts/consultaEmpresa.js"></script>
	</body>
</html>