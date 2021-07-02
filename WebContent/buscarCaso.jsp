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
		<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
		<title>Buscar caso</title>
	</head>
	<body>
	
		<!-- Cabecero -->
		<jsp:include page="/WEB-INF/paginas/comunes/cabecero.jsp" />
	
		<!-- Botones de navegación -->
		<!--<jsp:include page="/WEB-INF/paginas/comunes/botonesNavegacionConsulta.jsp" />-->
		<br/><br/>
		<section id="details">
			<div class="container-fluid" style="max-width:1650px">
				<div class="row">
					<div class="col">
						<div class="card mx-auto">
						
							<div class="card-header">
								<h4><i class="far fa-folder-open"></i> Buscar caso</h4>
							</div>
							
							<div class="card-body">

								<form id="buscarCasoForm" action="${pageContext.request.contextPath}/ServletControlador?accion=buscarCaso" method="POST">
								
									<div class="row">									
										<div class="col">										
											<div class="form-group">
											    <label for="idCaso" class="form-label">ID caso</label>
											    <input type="text" class="form-control" id="idCaso" name="idCaso">
											</div>
										</div>
										
										<div class="col">
											<div class="form-group">
											    <label for="nombreCaso" class="form-label">Nombre caso</label>
											    <input type="text" class="form-control" id="nombreCaso" name="nombreCaso">
											</div>
										</div>										
									</div>		
																
									<br/>		
												
									<div class="row">	
										<div class="col">
											<h6>Fecha creación</h6>	
										</div>
										<div class="col">
											<h6>Fecha última modificación</h6>	
										</div>
									</div>									
									<div class="row">																		
										<div class="col">										
											<div class="form-group">
											    <label for="inicioFechaCreacion" class="form-label">Desde</label>
											    <input type="date" class="form-control" id="inicioFechaCreacion" name="inicioFechaCreacion">
											</div>
										</div>
										<div class="col">										
											<div class="form-group">
											    <label for="finFechaCreacion" class="form-label">Hasta</label>
											    <input type="date" class="form-control" id="finFechaCreacion" name="finFechaCreacion">
											</div>
										</div>			
	
										<div class="col">										
											<div class="form-group">
											    <label for="inicioFechaUltimaModif" class="form-label">Desde</label>
											    <input type="date" class="form-control" id="inicioFechaUltimaModif" name="inicioFechaUltimaModif">
											</div>
										</div>	
										<div class="col">
											<div class="form-group">
											    <label for="finFechaUltimaModif" class="form-label">Hasta</label>
											    <input type="date" class="form-control" id="finFechaUltimaModif" name="finFechaUltimaModif">
											</div>
										</div>																				
									</div>
												  
									<button type="submit" class="btn btn-primary">Buscar</button>

								</form> 

								<br/>
								
								<c:choose>
									<c:when test="${fn:length(listaCasosEncontrados) > 0}">
										<table class="table table-striped">
											<thead class="thead-dark">
												<tr>
													<!-- <th>#</th> -->
													<th>ID</th>
													<th>Nombre</th>
													<th>Descripción</th>
													<th>Nº dispositivos</th>
													<th>Nº evidencias digitales</th>
													<th>Fecha creación</th>
													<th>Fecha última modificación</th>
												</tr>
											</thead>
											<tbody>
												<!-- Iteramos cada elemento de la lista de casos -->
												<c:forEach var="caso" items="${listaCasosEncontrados}" varStatus="status">
													<tr>
														<!-- <td>${status.count}</td> -->
														<td><a href="${pageContext.request.contextPath}/ServletControlador?accion=consultar&idCaso=${caso.idCaso}" class="text-dark">${caso.idCaso}</a></td>
														<td><a href="${pageContext.request.contextPath}/ServletControlador?accion=consultar&idCaso=${caso.idCaso}" class="text-dark">${caso.nombre}</a></td>
														<td>${caso.descripcion}</td>
														<td>${caso.numDispositivos}</td>
														<td>${caso.numEvidencias}</td>
														<td><fmt:formatDate value="${caso.fechaCreacionReg}" pattern="dd-MM-yyyy HH:mm:ss" /></td>
														<td><fmt:formatDate value="${caso.fechaUltimaModif}" pattern="dd-MM-yyyy HH:mm:ss" /></td>
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