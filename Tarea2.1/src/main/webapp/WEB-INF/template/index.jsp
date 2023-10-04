<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<jsp:include page="head.jsp" />
		<title>Trabajo.uy</title>
	</head>
	<body>
		<jsp:include page="navbar.jsp" />
		<div class="container-fluid" id="web-content">
			<div class="row justify-content-md-center">
				<jsp:include page="sidebar.jsp" />
				<jsp:include page="content.jsp" />
			</div>
		</div>
	
		<jsp:include page="footer.jsp" />
	</body>
</html>