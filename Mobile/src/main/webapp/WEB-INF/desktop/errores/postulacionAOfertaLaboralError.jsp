<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<jsp:include page="../template/head.jsp" />
		<title>Trabajo.uy</title>
	</head>
	<body>
		<jsp:include page="../template/navbar.jsp" />
		 <div class="container-fluid" id="web-content">
		<br>
        <div class="row justify-content-md-center">
			<div class="col-xs-12 col-md-10">
                <div id="errorPosContainer" class="min-vh-100 justify-content-center">
											<br>
						<h2>¡Oops!</h2>
						<h3>Parece que ya te postulaste a esta oferta laboral, ¿Qué deseas hacer?</h3>
						<br>
						<a href="ConsultaPostulacion" class="btn btn-secondary">Ver mis postulaciones</a> 
						<a href="Home" class="btn btn-secondary">Volver al inicio</a>            	
						</div>				
			</div>
        </div>
    </div>
		
		<jsp:include page="../template/footer.jsp" /> 
	</body>
</html>
