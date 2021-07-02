<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<!-- Required meta tags -->
   		<meta charset="utf-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    	<!-- Bootstrap CSS -->
    	  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
    	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
		<script src="https://kit.fontawesome.com/c819e18a95.js" crossorigin="anonymous"></script>
		<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
	    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
		<!-- Bootstrap JavaScript -->
		<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-growl/1.0.0/jquery.bootstrap-growl.min.js"></script>
		<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
		<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
		<title>Buscar usuario</title>
	</head>
	<body>
	
		<!-- Cabecero -->
		<jsp:include page="/WEB-INF/paginas/comunes/cabecero.jsp" />
	
		<!-- Botones de navegación -->
		<!--<jsp:include page="/WEB-INF/paginas/comunes/botonesNavegacionConsulta.jsp" />-->
		<br/><br/>
		<section id="details">
			<div class="container-fluid" style="max-width:2000px">
				<div class="row">
					<div class="col">
						<div class="card mx-auto">
						
							<div class="card-header">
								<h4><i class="fas fa-user-friends"></i> Buscar usuario</h4>
							</div>
							
							<div class="card-body">

								<form id="buscarEvidenciaForm" action="${pageContext.request.contextPath}/ServletControlador?accion=buscarUsuario" method="POST">
								
									<div class="row">									
										<div class="col">										
											<div class="form-group">
											    <label for="idUsuario" class="form-label">ID usuario</label>
											    <input type="text" class="form-control" id="idUsuario" name="idUsuario">
											</div>
										</div>
										
										<div class="col">
											<div class="form-group">
											    <label for="nombreReal" class="form-label">Nombre real</label>
											    <input type="text" class="form-control" id="nombreReal" name="nombreReal">
											</div>
										</div>		
										
										<div class="col">
											<div class="form-group">
											    <label for="nombreUsuario" class="form-label">Nombre de usuario</label>
											    <input type="text" class="form-control" id="nombreUsuario" name="nombreUsuario">
											</div>
										</div>											
												
										<div class="col">
											<div class="form-group">
											    <label for="correo" class="form-label">Correo del usuario</label>
											    <input type="text" class="form-control" id="correo" name="correo">
											</div>
										</div>		
										
										<div class="col">
											<div class="form-group">
											    <label for="telefono" class="form-label">Teléfono del usuario</label>
											    <input type="text" class="form-control" id="telefono" name="telefono">
											</div>
										</div>																						
																													
									</div>		
																
									<br/>		
												
									<div class="row">	
										<div class="col">
											<h6>Fecha última sesión</h6>	
										</div>
									</div>									
									<div class="row">																		
										<div class="col">										
											<div class="form-group">
											    <label for="inicioFechaSesion" class="form-label">Desde</label>
											    <input type="date" class="form-control" id="inicioFechaSesion" name="inicioFechaSesion">
											</div>
										</div>
										<div class="col">										
											<div class="form-group">
											    <label for="finFechaSesion" class="form-label">Hasta</label>
											    <input type="date" class="form-control" id="finFechaSesion" name="finFechaSesion">
											</div>
										</div>			
																			
									</div>
												  
									<button type="submit" class="btn btn-primary">Buscar</button>

								</form> 

								<br/>
								<c:choose>
									<c:when test="${fn:length(listaPersonasEncontradas) > 0}">
									<table class="table table-striped">
										<thead class="thead-dark">
											<tr>
												<th>#</th>
												<th>ID</th>
												<th>Nombre real</th>
												<th>Nombre usuario</th>
												<th>Correo electrónico</th>
												<th>Teléfono de contacto</th>
												<th>Fecha última sesión</th>										
											</tr>
										</thead>
										<tbody>
											<!-- Iteramos cada elemento de la lista de personas -->
											<c:forEach var="persona" items="${listaPersonasEncontradas}" varStatus="status">
												<tr>
													<td>${status.count}</td>
													<td><a href="${pageContext.request.contextPath}/ServletControlador?accion=consultarUsuario&idUsuario=${persona.idPersona}" class="text-dark">${persona.idPersona}</a></td>
													<td><a href="${pageContext.request.contextPath}/ServletControlador?accion=consultarUsuario&idUsuario=${persona.idPersona}" class="text-dark">${persona.nombre}</a></td>			
													<td>${persona.usuario}</td>													
													<td>${persona.correo}</td>
													<td>${persona.telefono}</td>						
													<td><fmt:formatDate value="${persona.ultimaSesion}" pattern="dd-MM-yyyy HH:mm:ss" /></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</c:when>
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