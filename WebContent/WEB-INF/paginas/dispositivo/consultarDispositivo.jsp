<!-- <!DOCTYPE html>
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
		<title>Consultar dispositivo</title>
	</head>
	<body>
	
		<!-- Cabecero -->
		<jsp:include page="/WEB-INF/paginas/comunes/cabecero.jsp" />

		<!-- Botones de navegación -->
		<jsp:include page="/WEB-INF/paginas/comunes/botonesNavegacionConsultaDispositivo.jsp" />
		
		<section id="details">
			<div class="container-fluid" style="max-width:1650px">
				<nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
				  <ol class="breadcrumb">
				    <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/ServletControlador">Inicio</a></li>
				    <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/ServletControlador?accion=consultar&idCaso=${dispositivo.idCaso}">Caso</a></li>
				    <li class="breadcrumb-item active" aria-current="page">Dispositivo</li>
				  </ol>
				</nav>
											
				<div class="row">
					<div class="col">
						<div class="card mx-auto">
							<div class="card-header">
								<h4><i class="fas fa-laptop"></i> Consultar dispositivo</h4>
							</div>
							<div class="card-body">
													
							<%
							Object message = request.getAttribute("dispositivoAgregadoOK");
							
							if (message != null) {%>
								<div class="alert alert-success alert-dismissible fade show" role="alert">
								<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span></button>
								<strong>El dispositivo se ha creado correctamente</strong>. Ahora puede proceder a agregar evidencias digitales asociadas a este dispositivo.	
							</div>	
							<% }%>
							
							<%
							Object messageEditado = request.getAttribute("dispositivoEditadoOK");
							
							if (messageEditado != null) {%>
								<div class="alert alert-success alert-dismissible fade show" role="alert">
								<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span></button>
								El dispositivo se ha editado correctamente.
							</div>	
							<% }%>	
							
														<%
							Object messageEliminado = request.getAttribute("dispositivoEliminadoOK");
							
							if (messageEliminado != null) {%>
								<div class="alert alert-success alert-dismissible fade show" role="alert">
								<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span></button>
								El dispositivo se ha eliminado correctamente.
							</div>	
							<% }%>	
							
							<%
							Object messageEvidenciaEliminada = request.getAttribute("evidenciaEliminadaOK");
							
							if (messageEvidenciaEliminada != null) {%>
							<div class="alert alert-success alert-dismissible fade show" role="alert">
								<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span></button>
								La evidencia digital se ha eliminado correctamente.
							</div>	
							<% }%>					

								<dl class="row">
								
								<dt class="col-sm-3">ID del caso:</dt>
								  <dd class="col-sm-9">${dispositivo.idCaso}</dd>
								  
								  <dt class="col-sm-3">ID del dispositivo:</dt>
								  <dd class="col-sm-9">${dispositivo.idDispositivo}</dd>
								
								  <dt class="col-sm-3">Nombre:</dt>
								  <dd class="col-sm-9">${dispositivo.nombre}</dd>
								
								  <dt class="col-sm-3">Descripción:</dt>
								  <dd class="col-sm-9">${dispositivo.descripcion}</dd>
								  
								  <dt class="col-sm-3">Fotografía:</dt>						  
								  <c:choose>
									  <c:when test="${dispositivo.fotografia == ''}">
									  	<dd class="col-sm-9">Fotografía no disponible.</dd>
									  </c:when>
									  <c:otherwise>
									  	<dd class="col-sm-9">
									  		<!-- <img height="400" width="400" src="${pageContext.request.contextPath}${dispositivo.fotografia}" class="img-fluid" /> -->
									  		<img height="400" width="400" src="${dispositivo.fotografia}" class="img-fluid" />
									  	</dd>									  	
									  </c:otherwise>								  
								  </c:choose>
								  
								  <dt class="col-sm-3">Hash:</dt>
								  <dd class="col-sm-9">${dispositivo.hash}</dd>
								  
								  <dt class="col-sm-3">¿Se encuentra cifrado?:</dt>
								  <c:choose>
								  	<c:when test="${dispositivo.estaCifrado == 'S'}">
									  	<dd class="col-sm-9">Sí</dd>
									  </c:when>
									  <c:when test="${dispositivo.estaCifrado == 'N'}">
									  	<dd class="col-sm-9">No</dd>
									  </c:when>
								  </c:choose>

								  <dt class="col-sm-3">Tipo de dispositivo:</dt>							
								  
								  <c:choose>
									  <c:when test="${dispositivo.tipo == '1'}">
									  	<dd class="col-sm-9">CD/DVD</dd>
									  </c:when>
									  <c:when test="${dispositivo.tipo == '2'}">
									  	<dd class="col-sm-9">Consola</dd>
									  </c:when>
									  <c:when test="${dispositivo.tipo == '3'}">
									  	<dd class="col-sm-9">Cámara de seguridad</dd>
									  </c:when>									  
									  <c:when test="${dispositivo.tipo == '4'}">
									  	<dd class="col-sm-9">Cámara digital</dd>
									  </c:when>									  
									  <c:when test="${dispositivo.tipo == '5'}">
									  	<dd class="col-sm-9">Disco duro externo</dd>
									  </c:when>									  
									  <c:when test="${dispositivo.tipo == '6'}">
									  	<dd class="col-sm-9">Disco duro interno</dd>
									  </c:when>									  
									  <c:when test="${dispositivo.tipo == '7'}">
									  	<dd class="col-sm-9">Fax</dd>
									  </c:when>									  
									  <c:when test="${dispositivo.tipo == '8'}">
									  	<dd class="col-sm-9">GPS</dd>
									  </c:when>									  
									  <c:when test="${dispositivo.tipo == '9'}">
									  	<dd class="col-sm-9">Impresora</dd>
									  </c:when>									  
									  <c:when test="${dispositivo.tipo == '10'}">
									  	<dd class="col-sm-9">MP3/MP4</dd>
									  </c:when>									  
									  <c:when test="${dispositivo.tipo == '11'}">
									  	<dd class="col-sm-9">Notebook</dd>
									  </c:when>									  
									  <c:when test="${dispositivo.tipo == '12'}">
									  	<dd class="col-sm-9">Ordenador personal</dd>
									  </c:when>									  
									  <c:when test="${dispositivo.tipo == '13'}">
									  	<dd class="col-sm-9">Ordenador portátil</dd>
									  </c:when>									  
									  <c:when test="${dispositivo.tipo == '14'}">
									  	<dd class="col-sm-9">PDA</dd>
									  </c:when>		
									  <c:when test="${dispositivo.tipo == '15'}">
									  	<dd class="col-sm-9">Router</dd>
									  </c:when>									  
									  <c:when test="${dispositivo.tipo == '16'}">
									  	<dd class="col-sm-9">Servidor</dd>
									  </c:when>									  
									  <c:when test="${dispositivo.tipo == '17'}">
									  	<dd class="col-sm-9">Smart TV</dd>
									  </c:when>									  
									  <c:when test="${dispositivo.tipo == '18'}">
									  	<dd class="col-sm-9">Smartwatch</dd>
									  </c:when>									  
									  <c:when test="${dispositivo.tipo == '19'}">
									  	<dd class="col-sm-9">Tablet</dd>
									  </c:when>									  
									  <c:when test="${dispositivo.tipo == '20'}">
									  	<dd class="col-sm-9">Tarjeta de memoria</dd>
									  </c:when>									  
									  <c:when test="${dispositivo.tipo == '21'}">
									  	<dd class="col-sm-9">Teléfono móvil</dd>
									  </c:when>								  
									  <c:when test="${dispositivo.tipo == '22'}">
									  	<dd class="col-sm-9">USB</dd>
									  </c:when>									  							  
								  </c:choose>
									  
								  <dt class="col-sm-3">Marca y/o modelo:</dt>
								  <dd class="col-sm-9">${dispositivo.marca}</dd>
								  
								  <dt class="col-sm-3">¿Se trata del dispositivo original o de una adquisición?:</dt>
								  <c:choose>
								  	 <c:when test="${dispositivo.esClonadoForense == 'O'}">
									  	<dd class="col-sm-9">Original</dd>
									  </c:when>	
								  	 <c:when test="${dispositivo.esClonadoForense == 'C'}">
									  	<dd class="col-sm-9">Clonado forense (copia)</dd>
									  </c:when>	
								  	 <c:when test="${dispositivo.esClonadoForense == 'I'}">
									  	<dd class="col-sm-9">Contiene imagen forense u otras evidencias digitales (copia)</dd>
									  </c:when>										  									 
								  </c:choose>				
								  
								  <br/>				  
								  
								  <dt class="col-sm-3">Evidencias digitales que almacena el dispositivo:</dt>						

								  <c:choose>
								  	<c:when test="${fn:length(dispositivo.listaEvidenciasDigitales) > 0}">
								 	<br/> 
									 <br/>
									  <table class="table table-striped table-bordered">
										  <thead>
										    <tr>
										      <th scope="col">#</th>
										      <th scope="col">ID</th>
										      <th scope="col">Nombre</th>
										      <th scope="col">Tipo</th>
										      <th scope="col">Descripción</th>									      
										      <th scope="col">Fecha creación reg.</th>
										      <th scope="col">Fecha última modif.</th>
										     <%Object tienePermisos = request.getAttribute("tienePermisos");
												String tienePermisosStr = (String) tienePermisos;											
												if (!tienePermisosStr.isEmpty() && tienePermisosStr.equalsIgnoreCase("si")) {%>											
													<th scope="col">Acciones</th>												
												<% } %>										   
										 </tr>
										  </thead>
										  <tbody>										  
										  	<c:forEach var="evidencia" items="${dispositivo.listaEvidenciasDigitales}" varStatus="status">
											    <tr>
											      <th scope="row">${status.index + 1}</th>
											      <td><a href="${pageContext.request.contextPath}/ServletControlador?accion=consultarEvidenciaDigital&idEvidencia=${evidencia.idEvidencia}" class="text-dark">${evidencia.idEvidencia}</a></td>
											      <td><a href="${pageContext.request.contextPath}/ServletControlador?accion=consultarEvidenciaDigital&idEvidencia=${evidencia.idEvidencia}" class="text-dark">${evidencia.nombre}</a></td>

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
											      		<td>Mensaje/s de correo</td>
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
	
											      <td>${evidencia.descripcion}</td>
											      <td><fmt:formatDate value="${evidencia.fechaCreacionReg}" pattern="dd-MM-yyyy HH:mm:ss" /></td>
											      <td><fmt:formatDate value="${evidencia.fechaUltimaModif}" pattern="dd-MM-yyyy HH:mm:ss" /></td>
													<%										
													if (!tienePermisosStr.isEmpty() && tienePermisosStr.equalsIgnoreCase("si")) {%>
													<td>
														<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownButtonAccionesEvidencias" role="button" data-toggle="dropdown" aria-expanded="false">Seleccionar</button>
												        <ul class="dropdown-menu" aria-labelledby="dropdownButtonAccionesEvidencias">
															<li><a class="dropdown-item" href="${pageContext.request.contextPath}/ServletControlador?accion=editarEvidencia&idEvidencia=${evidencia.idEvidencia}">Editar</a></li>
															<li><a class="dropdown-item" href="#" data-href="${pageContext.request.contextPath}/ServletControlador?accion=eliminarEvidenciaDesdeConsultarDispositivos&idDispositivo=${evidencia.idDispositivo}&idCaso=${evidencia.idCaso}&idEvidencia=${evidencia.idEvidencia}" data-toggle="modal" data-target="#modalEliminarEvidencia">Eliminar</a></li>
														</ul>
														 </td>
													<% }  %>
											    </tr>
											</c:forEach>
										  </tbody>
										</table>
										<br/>	
										</c:when>
										<c:otherwise>
											 <dd class="col-sm-9">No existen evidencias.</dd>
										</c:otherwise>									
									</c:choose>


								  <dt class="col-sm-3">Fecha creación registro:</dt>
								  <dd class="col-sm-9">
								  	<fmt:formatDate value="${dispositivo.fechaCreacionReg}" pattern="dd-MM-yyyy HH:mm:ss" />
								  </dd>
								  <dt class="col-sm-3">Fecha última modificación:</dt>
								  <dd class="col-sm-9">
								  	<fmt:formatDate value="${dispositivo.fechaUltimaModif}" pattern="dd-MM-yyyy HH:mm:ss" />
								  </dd>								  

								</dl>
								<br/>
								<%Object tienePermisos = request.getAttribute("tienePermisos");
								String tienePermisosStr = (String) tienePermisos;
								
								if (!tienePermisosStr.isEmpty() && tienePermisosStr.equalsIgnoreCase("si")) {%>
									<a class="btn btn-secondary" href="${pageContext.request.contextPath}/ServletControlador?accion=agregarEvidenciaAlDispositivo&idDispositivo=${dispositivo.idDispositivo}&nombreDispositivo=${dispositivo.nombre}&idCaso=${dispositivo.idCaso}" role="button"><i class="fas fa-file-video"></i> <i class="fas fa-plus"></i> Agregar evidencia digital</a>
								<% } else { %>
									<div class="alert alert-secondary" role="alert">
									  No dispones de <strong>permisos</strong> para <strong>editar</strong> el dispositivo ni para <strong>añadir evidencias digitales</strong> a este dispositivo.
									</div>
									<button class="btn btn-secondary disabled" disabled type="submit" aria-disabled="true"><i class="fas fa-file-video"></i> <i class="fas fa-plus"></i> Agregar evidencia digital</button>
								<% } %>
							
								<c:choose>
									<c:when test="${dispositivo.idCadenaCustodia == 0}">	
										<% if (!tienePermisosStr.isEmpty() && tienePermisosStr.equalsIgnoreCase("si")) {%>							
											<a class="btn btn-secondary" href="${pageContext.request.contextPath}/ServletControlador?accion=insertarCadenaCustodiaConDispositivoSeleccionado&idDispositivo=${dispositivo.idDispositivo}" role="button"><i class="fas fa-link"><i class="fas fa-plus"></i></i> Agregar cadena de custodia</a>																		
										<% } else { %>
											<a class="btn btn-secondary disabled" href="${pageContext.request.contextPath}/ServletControlador?accion=insertarCadenaCustodiaConDispositivoSeleccionado&idDispositivo=${dispositivo.idDispositivo}" role="button"><i class="fas fa-link"><i class="fas fa-plus"></i></i> Agregar cadena de custodia</a>																		
										<% } %>
									</c:when>
									<c:otherwise>
										<a class="btn btn-secondary" href="${pageContext.request.contextPath}/ServletControlador?accion=consultarCadenaCustodiaDeDispositivo&idDispositivo=${dispositivo.idDispositivo}&nombreDispositivo=${dispositivo.nombre}&descripcionDispositivo=${dispositivo.descripcion}&hashDispositivo=${dispositivo.hash}" role="button"><i class="fas fa-link"></i> Consultar cadena de custodia</a>																		
									</c:otherwise>							
								</c:choose>
								
								
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<!-- Modal Eliminar Evidencia -->
			<div class="modal fade" id="modalEliminarEvidencia" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
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

				$('#modalEliminarEvidencia').on('show.bs.modal', function(e) {
				    $(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));
				});
		
	
			</script>
					
		</section>
				
		<!-- Pie de página -->
		<jsp:include page="/WEB-INF/paginas/comunes/piePagina.jsp" />

	</body>
</html>