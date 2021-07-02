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
<title>Agregar cadena de custodia</title>
</head>
<body>

	<!-- Cabecero -->
	<jsp:include page="/WEB-INF/paginas/comunes/cabecero.jsp" />

	<!-- Botones de navegaci�n -->
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
								<i class="fas fa-link"></i> Agregar cadena de custodia
							</h4>
						</div>

						<div class="card-body">

							
						
						<c:choose>
							<c:when test="${fn:length(listaMisDispositivosSinCadenaCustodia) > 0}">
	
								<form id="agregarCadenaCustodiaForm"
									action="${pageContext.request.contextPath}/ServletControlador?accion=insertarCadenaCustodiaConDispositivoSeleccionado"
									method="POST" enctype="multipart/form-data">
	
									<div class="row justify-content-md-center">
										<div class="col-7">
											<label for="#" class="form-label">Selecciona el dispositivo al que a�adirle esta cadena de custodia</label>   
											<select name="idDispositivo" class="form-control" data-live-search="true">
												
												<c:forEach var="dispositivo" items="${listaMisDispositivosSinCadenaCustodia}" varStatus="status">										
													<option value="${dispositivo.idDispositivo}">${dispositivo.idDispositivo} - ${dispositivo.nombre}</option>										
												</c:forEach>
	
											</select>
											<small id="dispositivoHelp" class="form-text text-muted">Solo se muestran los dispositivos que tienes asignados y que no tienen ninguna cadena de custodia asignada.</small>							
										</div>
									</div>
									
									<br/>

									<button type="submit" class="btn btn-primary">Agregar</button>
	
								</form>
							</c:when>
							<c:otherwise>
								<dd class="col-sm-9">Actualmente a�n no dispones de dispositivos asignados sin cadena de custodia, por lo que no es posible a�adir una.</dd>
							</c:otherwise>
						</c:choose>
						
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<!-- Pie de p�gina -->
	<jsp:include page="/WEB-INF/paginas/comunes/piePagina.jsp" />

</body>
</html>