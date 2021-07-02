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
		<title>Buscar evidencia digital</title>
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
								<h4><i class="fas fa-file-video"></i> Buscar evidencia digital</h4>
							</div>
							
							<div class="card-body">

								<form id="buscarEvidenciaForm" action="${pageContext.request.contextPath}/ServletControlador?accion=buscarEvidencia" method="POST">
								
									<div class="row">									
										<div class="col">										
											<div class="form-group">
											    <label for="idEvidencia" class="form-label">ID evidencia</label>
											    <input type="text" class="form-control" id="idEvidencia" name="idEvidencia">
											</div>
										</div>
										
										<div class="col">
											<div class="form-group">
											    <label for="nombreEvidencia" class="form-label">Nombre evidencia</label>
											    <input type="text" class="form-control" id="nombreEvidencia" name="nombreEvidencia">
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
									<c:when test="${fn:length(listaEvidenciasEncontradas) > 0}">
									<table class="table table-striped">
										<thead class="thead-dark">
											<tr>
												<th>#</th>
												<th>ID</th>
												<th>Nombre</th>
												<th>Caso</th>
												<th>Dispositivo</th>
												<th>Descripción</th>
												<th>Tipo</th>
												<th>Fecha creación</th>
												<th>Fecha última modificación</th>										
											</tr>
										</thead>
										<tbody>
											<!-- Iteramos cada elemento de la lista de casos -->
											<c:forEach var="evidencia" items="${listaEvidenciasEncontradas}" varStatus="status">
												<tr>
													<td>${status.count}</td>
													<td><a href="${pageContext.request.contextPath}/ServletControlador?accion=consultarEvidenciaDigital&idEvidencia=${evidencia.idEvidencia}" class="text-dark">${evidencia.idEvidencia}</a></td>
													<td><a href="${pageContext.request.contextPath}/ServletControlador?accion=consultarEvidenciaDigital&idEvidencia=${evidencia.idEvidencia}" class="text-dark">${evidencia.nombre}</a></td>
				
													<td>[${evidencia.idCaso}] ${evidencia.nombreCaso}</td>		
													<c:choose>
														<c:when test="${evidencia.idDispositivo == '-1'}">
															<td>Sin dispositivo asignado</td>
														</c:when>
														<c:otherwise>
															<td>[${evidencia.idDispositivo}] ${evidencia.nombreDispositivo}</td>
														</c:otherwise>
													</c:choose>
																				
																								
													<td>${evidencia.descripcion}</td>
																					
										      		<c:choose>
												      <c:when test="${evidencia.tipo == '1'}">
												      	<td>Archivo cifrado</td>
												      </c:when>
												      <c:when test="${evidencia.tipo == '2'}">
												      	<td>Archivo comprimido</td>
												      </c:when>
												      <c:when test="${evidencia.tipo == '3'}">
												      	<td>Archivo de texto</td>
												      </c:when>
													  <c:when test="${evidencia.tipo == '4'}">
												      	<td>Audio</td>
												      </c:when>											      
												      <c:when test="${evidencia.tipo == '5'}">
												      	<td>Base de datos</td>
												      </c:when>												      
												      <c:when test="${evidencia.tipo == '6'}">
												      	<td>Documento ofimático</td>
												      </c:when>												      
												      <c:when test="${evidencia.tipo == '7'}">
												      	<td>Historial de navegación</td>
												      </c:when>												      
												      <c:when test="${evidencia.tipo == '8'}">
												      	<td>Imagen forense</td>
												      </c:when>												      
												      <c:when test="${evidencia.tipo == '9'}">
												      	<td>Imagen</td>
												      </c:when>												      
												      <c:when test="${evidencia.tipo == '10'}">
												      	<td>Memoria RAM</td>
												      </c:when>												      
												      <c:when test="${evidencia.tipo == '11'}">
												      	<td>Mensaje/s de correo/s</td>
												      </c:when>												      
												      <c:when test="${evidencia.tipo == '12'}">
												      	<td>Metadato/s de archivo/s</td>
												      </c:when>												      
												      <c:when test="${evidencia.tipo == '13'}">
												      	<td>Otros</td>
												      </c:when>												      
												      <c:when test="${evidencia.tipo == '14'}">
												      	<td>Redes Wi-Fi detectadas</td>
												      </c:when>												      
												      <c:when test="${evidencia.tipo == '15'}">
												      	<td>Registro de llamadas</td>
												      </c:when>												      
												      <c:when test="${evidencia.tipo == '16'}">
												      	<td>Registro/s de Windows</td>
												      </c:when>												      
												      <c:when test="${evidencia.tipo == '17'}">
												      	<td>SMS</td>
												      </c:when>												      
												      <c:when test="${evidencia.tipo == '18'}">
												      	<td>Tráfico de red</td>
												      </c:when>			
												      <c:when test="${evidencia.tipo == '19'}">
												      	<td>Vídeo</td>
												      </c:when>													      									      											      												      											      
											      </c:choose>
																			
													<td><fmt:formatDate value="${evidencia.fechaCreacionReg}" pattern="dd-MM-yyyy HH:mm:ss" /></td>
													<td><fmt:formatDate value="${evidencia.fechaUltimaModif}" pattern="dd-MM-yyyy HH:mm:ss" /></td>
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