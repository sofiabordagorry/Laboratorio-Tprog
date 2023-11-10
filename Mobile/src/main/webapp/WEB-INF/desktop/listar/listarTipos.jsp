<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <jsp:include page="../template/head.jsp" />
    <!--Bootstrap, NO BORRAR-->
    <link rel="stylesheet" href="media/styles/stylesListar.css">
    <title>Trabajo.uy</title>
</head>
<body>
    <!-- Barra de navegación -->
	<jsp:include page="../template/navbar.jsp" />
    <div class="container-fluid" id="web-content">
        <div class="row justify-content-md-center">
            <!-- Barra de navegación de la izquierda / keywords -->
			<jsp:include page="../template/sidebar.jsp" />
			<div class="col col-md-9 col-sm-12" id="main-content">
	            <!--ACÁ VA TODO EL CONTENIDO ESPECÍFICO DE LA PÁGINA 
	            EJEMPLO:-->
	            <div id="listarContainer" class="form-row align-items-center justify-content-center contenedor">
   					<%
						boolean noTypes = (boolean) request.getAttribute("NoTypesInSystem_Error");
						if(!noTypes){
							String[] types = (String[]) request.getAttribute("systemTypes");
					%>
					<h1 class="text-center">Busqueda de Tipos</h1>

					<div id="barraBusqueda" class="mx-auto">
						<div class="input-group mb-3">
	            			<input type="text" id="buscador" class="form-control" placeholder="Buscar tipo">
	       				</div>
					</div>
					<ul class="list-group" id="lista-elementos">
					<%
						for(int i = 0; i < types.length; i++){
					%>
						<li class="list-group-item text-center"><a href="ConsultaTipo?tipo_consultado=<%=types[i]%>"><%=types[i]%></a></li>
					<%
						}
					%>
					</ul>
					<%
						}else{
					%>
               			<h1>Ooops, en este momento no hay Tipos en el sistema</h1>
					<%
						}
					%>
                </div>
	        </div>
        </div>
    </div>
    <jsp:include page="../template/footer.jsp" />
    <script src="media/javaScript/listadosBusquedas.js"></script>
  </body>
</html>