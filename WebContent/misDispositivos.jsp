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

		<title>Mis dispositivos</title>
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
								<h4><i class="fas fa-laptop"></i> Mis dispositivos</h4>
							</div>
							
							<div class="card-body">
							
							<%
							Object messageEliminado = request.getAttribute("dispositivoEliminadoOK");
							
							if (messageEliminado != null) {%>
								<div class="alert alert-success alert-dismissible fade show" role="alert">
								<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span></button>
								El dispositivo se ha eliminado correctamente.
							</div>	
							<% }%>	

								<c:choose>
									<c:when test="${fn:length(listaMisDispositivos) > 0}">
									<table class="table table-striped">
										<thead class="thead-dark">
											<tr>
												<th>ID</th>
												<th>Nombre</th>
												<th>Tipo</th>
												<th>Marca y modelo</th>
												<th>Descripción</th>
												<th>Nº evidencias digitales</th>
												<th>Hash</th>
												<th>¿Se encuentra cifrado?</th>
												<th>¿Se trata del dispositivo original o una copia?</th>
												<th>ID caso</th>
												<th>Fecha creación</th>
												<th>Fecha última modificación</th>
												<th>Acciones</th>											
											</tr>
										</thead>
										<tbody>
											<!-- Iteramos cada elemento de la lista de dispositivos -->
											<c:forEach var="dispositivo" items="${listaMisDispositivos}" varStatus="status">
												<tr>
													<!-- <td>${status.count}</td> -->
													<td><a href="${pageContext.request.contextPath}/ServletControlador?accion=consultarDispositivo&idDispositivo=${dispositivo.idDispositivo}" class="text-dark">${dispositivo.idDispositivo}</a></td>
													<td><a href="${pageContext.request.contextPath}/ServletControlador?accion=consultarDispositivo&idDispositivo=${dispositivo.idDispositivo}" class="text-dark">${dispositivo.nombre}</a></td>

										      		<c:choose>
												      <c:when test="${dispositivo.tipo == '1'}">
												      	<td>CD/DVD</td>
												      </c:when>
												      <c:when test="${dispositivo.tipo == '2'}">
												      	<td>Consola</td>
												      </c:when>
												      <c:when test="${dispositivo.tipo == '3'}">
												      	<td>Cámara de seguridad</td>
												      </c:when>
													  <c:when test="${dispositivo.tipo == '4'}">
												      	<td>Cámara digital</td>
												      </c:when>											      
												      <c:when test="${dispositivo.tipo == '5'}">
												      	<td>Disco duro externo</td>
												      </c:when>												      
												      <c:when test="${dispositivo.tipo == '6'}">
												      	<td>Disco duro interno</td>
												      </c:when>												      
												      <c:when test="${dispositivo.tipo == '7'}">
												      	<td>Fax</td>
												      </c:when>												      
												      <c:when test="${dispositivo.tipo == '8'}">
												      	<td>GPS</td>
												      </c:when>												      
												      <c:when test="${dispositivo.tipo == '9'}">
												      	<td>Impresora</td>
												      </c:when>												      
												      <c:when test="${dispositivo.tipo == '10'}">
												      	<td>MP3/MP4</td>
												      </c:when>												      
												      <c:when test="${dispositivo.tipo == '11'}">
												      	<td>Notebook</td>
												      </c:when>												      
												      <c:when test="${dispositivo.tipo == '12'}">
												      	<td>Ordenador personal</td>
												      </c:when>												      
												      <c:when test="${dispositivo.tipo == '13'}">
												      	<td>Ordenador portátil</td>
												      </c:when>												      
												      <c:when test="${dispositivo.tipo == '14'}">
												      	<td>PDA</td>
												      </c:when>												      
												      <c:when test="${dispositivo.tipo == '15'}">
												      	<td>Router</td>
												      </c:when>												      
												      <c:when test="${dispositivo.tipo == '16'}">
												      	<td>Servidor</td>
												      </c:when>												      
												      <c:when test="${dispositivo.tipo == '17'}">
												      	<td>Smart TV</td>
												      </c:when>												      
												      <c:when test="${dispositivo.tipo == '18'}">
												      	<td>Smartwatch</td>
												      </c:when>												      
												      <c:when test="${dispositivo.tipo == '19'}">
												      	<td>Tablet</td>
												      </c:when>												      
												      <c:when test="${dispositivo.tipo == '20'}">
												      	<td>Tarjeta de memoria</td>
												      </c:when>												      
												      <c:when test="${dispositivo.tipo == '21'}">
												      	<td>Teléfono móvil</td>
												      </c:when>												      
												      <c:when test="${dispositivo.tipo == '22'}">
												      	<td>USB</td>
												      </c:when>												      												      											      
											      </c:choose>

													<td>${dispositivo.marca}</td>										
													<td>${dispositivo.descripcion}</td>
													<td>${dispositivo.numEvidenciasDigitales}</td>
													<td>${dispositivo.hash}</td>
													
												      <c:choose>
												      	  <c:when test="${dispositivo.estaCifrado == 'S'}">
													      	<td>Sí</td>
													      </c:when>	
												      	  <c:when test="${dispositivo.estaCifrado == 'N'}">
													      	<td>No</td>
													      </c:when>												      
												      </c:choose>	

												      <c:choose>
												      	  <c:when test="${dispositivo.esClonadoForense == 'O'}">
													      	<td>Original</td>
													      </c:when>	
												      	  <c:when test="${dispositivo.esClonadoForense == 'C'}">
													      	<td>Clonado forense (copia)</td>
													      </c:when>		
													      <c:when test="${dispositivo.esClonadoForense == 'I'}">
													      	<td>Contiene una imagen forense u otras evidencias digitales (copia)</td>
													      </c:when>											      
												      </c:choose>
								      
													<td>${dispositivo.idCaso}</td>											
													<td><fmt:formatDate value="${dispositivo.fechaCreacionReg}" pattern="dd-MM-yyyy HH:mm:ss" /></td>
													<td><fmt:formatDate value="${dispositivo.fechaUltimaModif}" pattern="dd-MM-yyyy HH:mm:ss" /></td>
													<td>
											      		<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownButtonAccionesDispositivos" role="button" data-toggle="dropdown" aria-expanded="false">Seleccionar</button>
											       		 <ul class="dropdown-menu" aria-labelledby="dropdownButtonAccionesDispositivos">												        	
															<li><a class="dropdown-item" href="${pageContext.request.contextPath}/ServletControlador?accion=editarDispositivo&idDispositivo=${dispositivo.idDispositivo}">Editar</a></li>
															<li><a class="dropdown-item"  href="#" data-href="${pageContext.request.contextPath}/ServletControlador?accion=eliminarDispositivoDesdeMisDispositivos&idDispositivo=${dispositivo.idDispositivo}" data-toggle="modal" data-target="#exampleModal">Eliminar</a></li>
															
														</ul>
												 	</td>	
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</c:when>
								<c:otherwise>
									<dd class="col-sm-9">Actualmente aún no dispones de dispositivos asignados.</dd>
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
		        <p>¿Estás seguro de querer eliminar este dispositivo?</p>
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