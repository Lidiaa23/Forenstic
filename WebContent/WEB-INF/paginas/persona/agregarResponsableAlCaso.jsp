<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<!-- Required meta tags -->
   		<meta charset="utf-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    	<!-- Bootstrap CSS -->
    	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
		<script src="https://kit.fontawesome.com/c819e18a95.js" crossorigin="anonymous"></script>
		<style>			
		</style>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
		<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
		<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
		<title>Agregar responsable al caso</title>
	</head>
	<body>
	
		<!-- Cabecero -->
		<jsp:include page="/WEB-INF/paginas/comunes/cabecero.jsp" />
	
			<section id="details">
				<div class="container">
					<div class="row">
						<div class="col">
							<div class="card">
								<div class="card-header">
									<h4>Agregar responsable al caso</h4>
								</div>
								<div class="card-body">
								
									<c:choose>
										<c:when test="${fn:length(listaPersonas) > 0}">
				
											<form id="agregarResponsableForm"
												action="${pageContext.request.contextPath}/ServletControlador?accion=agregarResponsableForm"
												method="POST" enctype="multipart/form-data">
												
												<input type="hidden" name="idCaso" value="${idCaso}">
				
												<div class="row justify-content-md-center">
													<div class="col-7">
														<label for="#" class="form-label">Selecciona a la persona a quien asignarle el caso.</label>   
														<select name="idPersona" class="form-control" data-live-search="true">
															
															<c:forEach var="persona" items="${listaPersonas}" varStatus="status">										
																<option value="${persona.idPersona}">${persona.idPersona} - ${persona.nombre} (${persona.usuario})</option>										
															</c:forEach>
				
														</select>
														<small id="casoHelp" class="form-text text-muted">Solo se muestran las personas que no tienen asignado este caso.</small>							
													</div>
												</div>
			
												<br />
												<button type="submit" class="btn btn-primary">Agregar</button>
				
											</form>
										</c:when>
										<c:otherwise>
											<dd class="col-sm-9">Actualmente no existen personas a quien asignarle el caso.</dd>
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
		
	    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
	    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
	    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
		
	</body>
</html>