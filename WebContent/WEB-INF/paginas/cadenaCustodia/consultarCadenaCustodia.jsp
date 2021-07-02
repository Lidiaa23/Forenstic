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
		<!-- <style>			
			.footer {
			  position: relative;
			  margin-top: -150px; /* negative value of footer height */
			  height: 150px;
			  clear:both;
			  padding-top:20px;
			} 
		</style>-->
		<title>Consultar cadena de custodia</title>
	</head>
	<body>
	
		<!-- Cabecero -->
		<jsp:include page="/WEB-INF/paginas/comunes/cabecero.jsp" />

		<!-- Botones de navegación -->
		<jsp:include page="/WEB-INF/paginas/comunes/botonesNavegacionConsultaCadenaCustodia.jsp" />
		
		<section id="details">
			<div class="container-fluid" style="max-width:1650px">
			
				<nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
				  <ol class="breadcrumb">
				    <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/ServletControlador">Inicio</a></li>
				    <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/ServletControlador?accion=consultar&idCaso=${cadenaCustodia.idCaso}">Caso</a></li>
				    <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/ServletControlador?accion=consultarDispositivo&idDispositivo=${cadenaCustodia.idDispositivo}">Dispositivo</a></li>			    
				    <li class="breadcrumb-item active" aria-current="page">Cadena de custodia</li>
				  </ol>
				</nav>			
			
				<div class="row">
					<div class="col">
						<div class="card mx-auto">
							<div class="card-header">
								<h4><i class="fas fa-link"></i> Consultar cadena de custodia del dispositivo: [${cadenaCustodia.idDispositivo}] ${cadenaCustodia.nombreDispositivo}</h4>
							</div>
							<div class="card-body">
							
							<%
							Object message = request.getAttribute("cadenaCustodiaAgregadaOK");
							
							if (message != null) {%>
								<div class="alert alert-success alert-dismissible fade show" role="alert">
								<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span></button>
								La cadena de custodia se ha creado correctamente.
							</div>	
							<% }%>		

							<%
							Object messageCadenaEditada = request.getAttribute("cadenaEditadaOK");
							
							if (messageCadenaEditada != null) {%>
								<div class="alert alert-success alert-dismissible fade show" role="alert">
								<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span></button>
								La cadena de custodia se ha editado correctamente.
							</div>	
							<% }%>
							
							<%
							Object messageRegistroEditado = request.getAttribute("registroEditadoOK");
							
							if (messageRegistroEditado != null) {%>
								<div class="alert alert-success alert-dismissible fade show" role="alert">
								<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span></button>
								El registro de la cadena de custodia se ha editado correctamente.
							</div>	
							<% }%>

							<%
							Object messageEliminado = request.getAttribute("registroCadenaEliminadaOK");
							
							if (messageEliminado != null) {%>
								<div class="alert alert-success alert-dismissible fade show" role="alert">
								<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span></button>
								El registro de la cadena de custodia se ha eliminado correctamente.
							</div>	
							<% }%>								
							
							<%
							Object messageRegistro = request.getAttribute("registroCadenaAgregadoOK");
							
							if (messageRegistro != null) {%>
								<div class="alert alert-success alert-dismissible fade show" role="alert">
								<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span></button>
								El nuevo registro de la cadena de custodia se ha creado correctamente.
							</div>	
							<% }%>			
							
							<%
							Object messageRegistroEliminado = request.getAttribute("registroEliminadoOK");
							
							if (messageRegistroEliminado != null) {%>
								<div class="alert alert-success alert-dismissible fade show" role="alert">
								<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span></button>
								El registro de la cadena de custodia se ha elimina correctamente.
							</div>	
							<% }%>											

								<dl class="row">
								  
								  <dt class="col-sm-3">ID caso:</dt>
								  <dd class="col-sm-9">${cadenaCustodia.idCaso}</dd>
								
								  <dt class="col-sm-3">ID dispositivo:</dt>
								  <dd class="col-sm-9">${cadenaCustodia.idDispositivo}</dd>
								  
								  <dt class="col-sm-3">Localización origen:</dt>
								  <dd class="col-sm-9">${cadenaCustodia.localizacionOrigen}</dd>
								  
								  <dt class="col-sm-3">Fecha y hora origen:</dt>
								  <dd class="col-sm-9"><fmt:formatDate value="${cadenaCustodia.fechaHoraOrigen}" pattern="dd-MM-yyyy HH:mm:ss" /></dd>

								  <dt class="col-sm-3">Razón:</dt>
								  <dd class="col-sm-9">${cadenaCustodia.razon}</dd>
								  
								  <dt class="col-sm-3">Agencia / Institución:</dt>
								  <dd class="col-sm-9">${cadenaCustodia.agenciaInstitucion}</dd>		
								  
								  <dt class="col-sm-3">Persona:</dt>
								  <dd class="col-sm-9">${cadenaCustodia.persona}</dd>	
								  
								  <dt class="col-sm-3">Nombre dispositivo:</dt>
								  <dd class="col-sm-9">${cadenaCustodia.nombreDispositivo}</dd>
								
								  <dt class="col-sm-3">Descripción dispositivo:</dt>
								  <dd class="col-sm-9">${cadenaCustodia.descripcionDispositivo}</dd>
								  								  
								  <dt class="col-sm-3">¿Se trata del dispositivo original o de una adquisición?:</dt>
								  <c:choose>
								  	 <c:when test="${cadenaCustodia.coleccionAdquisicionDispositivo == 'O'}">
									  	<dd class="col-sm-9">Original</dd>
									  </c:when>	
								  	 <c:when test="${cadenaCustodia.coleccionAdquisicionDispositivo == 'C'}">
									  	<dd class="col-sm-9">Clonado forense (copia)</dd>
									  </c:when>	
								  	 <c:when test="${cadenaCustodia.coleccionAdquisicionDispositivo == 'I'}">
									  	<dd class="col-sm-9">Contiene imagen forense u otras evidencias digitales (copia)</dd>
									  </c:when>										  									 
								  </c:choose>									  
								  <dt class="col-sm-3">Hash dispositivo:</dt>
								  <dd class="col-sm-9">${cadenaCustodia.hashDispositivo}</dd>

								  <!-- TODO Si no hay registros, no mostrar tabla, solo un mensaje -->
								  <dt class="col-sm-3">Registros de la cadena de custodia:</dt>
								  <br/><br/>
								  
						<c:choose>
							<c:when test="${fn:length(cadenaCustodia.listaRegistros) > 0}">
									  
							  		<table class="table table-striped table-bordered">
									  <thead>
									    <tr>
									      <th scope="col">#</th>
									      <th scope="col">ID</th>
									      <th scope="col">Fecha y hora</th>
									      <th scope="col">Persona que entrega</th>
									      <th scope="col">Agencia / Institución que entrega</th>
									      <th scope="col">Persona que recibe</th>									      
									      <th scope="col">Agencia / Institución que recibe</th>
									      <th scope="col">Actividad / Propósito</th>
									      <th scope="col">Observaciones</th>
									      <!-- <th scope="col">Orden</th> -->
									      <th scope="col">Acciones</th>
									    </tr>
									  </thead>
									  <tbody>										  
									  	<c:forEach var="registro" items="${cadenaCustodia.listaRegistros}" varStatus="status">
										    <tr>
										      <th scope="row">${status.index + 1}</th>
										      <td>${registro.idRegistroCadenaCustodia}</td>
										      <td><fmt:formatDate value="${registro.fechaHora}" pattern="dd-MM-yyyy HH:mm:ss" /></td>
										      <td>${registro.personaEntrega}</td>
										      <td>${registro.agenciaInstitucionEntrega}</td>
										      <td>${registro.personaRecibe}</td>
										      <td>${registro.agenciaInstitucionRecibe}</td>
										      <td>${registro.actividadProposito}</td>	
										      <td>${registro.observaciones}</td>	
										      <!--<td>${registro.orden}</td>		  -->
										     	 <td>
											      	<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownButtonAccionesEvidencias" role="button" data-toggle="dropdown" aria-expanded="false">Seleccionar</button>
											        <ul class="dropdown-menu" aria-labelledby="dropdownButtonAccionesEvidencias">
														<li><a class="dropdown-item" href="${pageContext.request.contextPath}/ServletControlador?accion=editarRegistroCadena&idRegistroCadena=${registro.idRegistroCadenaCustodia}">Editar</a></li>
														<li><a class="dropdown-item" href="#" data-href="${pageContext.request.contextPath}/ServletControlador?accion=eliminarRegistroCadenaDesdeConsultarCadena&idRegistroCadena=${registro.idRegistroCadenaCustodia}&idCadenaCustodia=${cadenaCustodia.idCadenaCustodia}&nombreDispositivo=${cadenaCustodia.nombreDispositivo}&idCaso=${cadenaCustodia.idCaso}" data-toggle="modal" data-target="#exampleModal">Eliminar</a></li>
													</ul>
												</td>
										    </tr>
										</c:forEach>
									  </tbody>
									</table>
									
									</c:when>
									<c:otherwise>
										<dd class="col-sm-9">Actualmente aún no dispone de registros.</dd>									
									</c:otherwise>
								  	
								  	</c:choose>		
								</dl>
								
									<%Object tienePermisos = request.getAttribute("tienePermisos");
									String tienePermisosStr = (String) tienePermisos;
						
									
									if (!tienePermisosStr.isEmpty() && tienePermisosStr.equalsIgnoreCase("si")) {%>							
										<a class="btn btn-secondary" href="${pageContext.request.contextPath}/ServletControlador?accion=insertarRegistroCadenaCustodia&idCadenaCustodia=${cadenaCustodia.idCadenaCustodia}&nombreDispositivo=${cadenaCustodia.nombreDispositivo}" role="button"><i class="fas fa-link"><i class="fas fa-plus"></i></i> Agregar nuevo registro en la cadena de custodia</a>																		
									<% } else { %>
										<a class="btn btn-secondary disabled" href="${pageContext.request.contextPath}/ServletControlador?accion=insertarRegistroCadenaCustodia&idCadenaCustodia=${cadenaCustodia.idCadenaCustodia}" role="button"><i class="fas fa-link"><i class="fas fa-plus"></i></i> Agregar nuevo registro en la cadena de custodia</a>																		
									<% } %>									
								
							</div>						
						</div>
					</div>
				</div>
			</div>
			
			<!-- Modal Eliminar registro de cadena de custodia -->
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
			        <p>¿Estás seguro de querer eliminar este registro de cadena de custodia?</p>
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
			
			
		</section>
				
		<!-- Pie de página -->
		<jsp:include page="/WEB-INF/paginas/comunes/piePagina.jsp" />

	</body>
</html>