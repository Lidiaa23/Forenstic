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
		<title>Mis evidencias digitales</title>
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
								<h4><i class="fas fa-file-video"></i> Mis evidencias digitales</h4>
							</div>
							
							<div class="card-body">
							
							<%
							Object messageEliminado = request.getAttribute("evidenciaEliminadaOK");
							
							if (messageEliminado != null) {%>
								<div class="alert alert-success alert-dismissible fade show" role="alert">
								<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span></button>
								La evidencia digital se ha eliminado correctamente.
							</div>	
							<% }%>								

								<c:choose>
									<c:when test="${fn:length(listaMisEvidencias) > 0}">
									<table class="table table-striped">
										<thead class="thead-dark">
											<tr>
												<th>ID</th>
												<th>Nombre</th>
												<th>Caso</th>
												<th>Dispositivo</th>
												<th>Descripción</th>
												<th>Tipo</th>
												<th>Fecha creación</th>
												<th>Fecha última modificación</th>	
												<th>Acciones</th>											
											</tr>
										</thead>
										<tbody>
											<!-- Iteramos cada elemento de la lista de evidencias -->
											<c:forEach var="evidencia" items="${listaMisEvidencias}" varStatus="status">
												<tr>
													<!-- <td>${status.count}</td> -->
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

													<td>
											      		<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownButtonAccionesEvidencias" role="button" data-toggle="dropdown" aria-expanded="false">Seleccionar</button>
											       		 <ul class="dropdown-menu" aria-labelledby="dropdownButtonAccionesEvidencias">												        	
															<li><a class="dropdown-item" href="${pageContext.request.contextPath}/ServletControlador?accion=editarEvidencia&idEvidencia=${evidencia.idEvidencia}">Editar</a></li>
															<li><a class="dropdown-item"  href="#" data-href="${pageContext.request.contextPath}/ServletControlador?accion=eliminarEvidenciaDesdeMisEvidencias&idEvidencia=${evidencia.idEvidencia}" data-toggle="modal" data-target="#exampleModal">Eliminar</a></li>
															
														</ul>
												 	</td>	
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</c:when>
								<c:otherwise>
									<dd class="col-sm-9">Actualmente aún no dispones de evidencias digitales asignadas.</dd>
								</c:otherwise>
							</c:choose>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
		
		<!-- Modal -->
		<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel">Ventana de confirmación</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
		        <p>¿Estás seguro de querer eliminar esta evidencia digital?</p>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
		        <a class="btn btn-danger btn-ok">Eliminar</a>
		      </div>
		    </div>
		  </div>
		</div>			
	</div>
	
	<script>
	
	$('#exampleModal').on('show.bs.modal', function(e) {
	    $(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));
	});
	
	</script>
				
		<!-- Pie de página -->
		<jsp:include page="/WEB-INF/paginas/comunes/piePagina.jsp" />

	</body>
</html>