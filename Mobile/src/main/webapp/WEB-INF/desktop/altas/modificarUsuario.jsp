<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<jsp:include page="../template/head.jsp" />
	<link rel="stylesheet" href="media/styles/altaDeUsuario.css">
	<title>Trabajo.uy</title>
</head>
<body>
	<jsp:include page="../template/navbar.jsp" />
	<div class="container-fluid" id="web-content">
		<div class="row justify-content-md-center">
			<div class="col-xs-12 col-md-10">
				<div id="registrarContainer" class="d-flex flex-column min-vh-100 justify-content-center">
					
					<!-- Formulario -->
					<form action="ModificarUsuario" method="POST" class="formulario" id="formulario" enctype="multipart/form-data">
						<div class="row">
	                    	<div class="col-md-3" id="empresaPefilInfo">
								    <img src="data:image/jpg;base64," alt="Uploaded Image">
                            </div>
                            <div class="col">
                                <h2></h2>
                                <p><i class="" style="color: #000000;"></i> <b>Nombre:</b></p>
                                <p><i class=""></i> <b>Apellido:</b></p>
                                <p><i class=""></i> <b>Correo: </b></p>
                                <p><i class=""></i> <b>Descripcion: </b></p>
                                <p><i class=""></i> <b>Link: </b></p>
							</div>
	                    </div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>