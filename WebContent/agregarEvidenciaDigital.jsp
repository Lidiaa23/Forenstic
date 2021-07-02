<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"
	integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">
<link href="tail.select-bootstrap4.css" rel="stylesheet">
<script src="https://kit.fontawesome.com/c819e18a95.js"
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
	integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
	integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
	crossorigin="anonymous"></script>
<!-- Bootstrap JavaScript -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-growl/1.0.0/jquery.bootstrap-growl.min.js"></script>
	
<script src="tail.select-full.min.js"></script>
	
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<title>Agregar dispositivo</title>
</head>
		
<body>

		
	<!-- Cabecero -->
	<jsp:include page="/WEB-INF/paginas/comunes/cabecero.jsp" />

	<!-- Botones de navegación -->
	<!--<jsp:include page="/WEB-INF/paginas/comunes/botonesNavegacionConsulta.jsp" />-->
	<br />
	<br />
	<section id="details">
		<div class="container-fluid" style="max-width: 1650px">
			<div class="row">
				<div class="col">
					<div class="card mx-auto">

						<div class="card-header">
							<h4>
								<i class="fas fa-file"></i> Agregar evidencia digital
							</h4>
						</div>

						<div class="card-body">
						
						<c:choose>
							<c:when test="${fn:length(listaMisCasos) > 0}">

								<form id="agregarEvidenciaForm"
									action="${pageContext.request.contextPath}/ServletControlador?accion=insertarEvidenciaConCasoSeleccionado"
									method="POST" enctype="multipart/form-data">
	
									<div class="row justify-content-md-center">
										<div class="col-7">
											<label for="#" class="form-label">Selecciona el caso al que añadirle esta evidencia digital</label>   
											<select name="idCaso" id="selectCaso" class="form-control" data-live-search="true">
												
												<c:forEach var="caso" items="${listaMisCasos}" varStatus="status">										
													<option value="${caso.idCaso}">${caso.idCaso} - ${caso.nombre}</option>										
												</c:forEach>
	
											</select>
											<small id="casoHelp" class="form-text text-muted">Solo se muestran los casos que tienes asignados.</small>							
										</div>
									</div>
									
									<br />
									<button type="submit" class="btn btn-primary">Agregar</button>
	
								</form>
							</c:when>
							<c:otherwise>
								<dd class="col-sm-9">Actualmente aún no dispones de casos asignados, por lo que no es posible añadir evidencias digitales.</dd>
							</c:otherwise>
						</c:choose>
	
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<!-- Pie de página -->
	<jsp:include page="/WEB-INF/paginas/comunes/piePagina.jsp" />

</body>
</html>