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
		<title>Consultar caso</title>
	</head>
	<body>
	
		<!-- Cabecero -->
		<jsp:include page="/WEB-INF/paginas/comunes/cabecero.jsp" />

		<!-- Botones de navegación -->
		<jsp:include page="/WEB-INF/paginas/comunes/botonesNavegacionConsulta.jsp" />
		
		<section id="details">
			<div class="container-fluid" style="max-width:2000px">
				<div class="row">
					<div class="col">
						<div class="card mx-auto">
							<div class="card-header">
								<h4><i class="far fa-folder-open"></i> Consultar caso</h4>
							</div>
							<div class="card-body">
							
							
							<%
							Object messageResponsable = request.getAttribute("responsableAgregadoOK");
							
							if (messageResponsable != null) {%>
							<div class="alert alert-success alert-dismissible fade show" role="alert">
								<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span></button>
								Se ha agregado correctamente el nuevo responsable.
							</div>	
							<% }%>
													
							<%
							Object message = request.getAttribute("casoAgregadoOK");
							
							if (message != null) {%>
							<div class="alert alert-success alert-dismissible fade show" role="alert">
								<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span></button>
								<strong>El caso se ha creado correctamente</strong>. Ahora puede proceder a agregar dispositivos, evidencias digitales y responsables a este caso.
							</div>	
							<% }%>
							
							<%
							Object messageDispositivoEliminado = request.getAttribute("dispositivoEliminadoOK");
							
							if (messageDispositivoEliminado != null) {%>
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
								  <dd class="col-sm-9">${caso.idCaso}</dd>
								
								  <dt class="col-sm-3">Nombre:</dt>
								  <dd class="col-sm-9">${caso.nombre}</dd>
								
								  <dt class="col-sm-3">Descripción:</dt>
								  <dd class="col-sm-9">${caso.descripcion}</dd>
								  
								  <dt class="col-sm-3">Dispositivos:</dt>
								  								  
								  <c:choose>
								  	<c:when test="${fn:length(caso.listaDispositivos) > 0}">
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
										      <th scope="col">Nº evidencias digitales</th>								      
										      <th scope="col">Hash</th>
										      <th scope="col">¿Está cifrado?</th>
										      <th scope="col">¿Se trata del dispositivo original o una copia?</th>
										      <!-- <th scope="col">¿Fotografía?</th>
										      <th scope="col">Cadena de custodia</th>
										      <th scope="col">Ciclo de vida</th> -->
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
										  	<c:forEach var="dispositivo" items="${caso.listaDispositivos}" varStatus="status">
											    <tr>
											      <th scope="row">${status.index + 1}</th>
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
												      	<td>Contiene imagen forense u otras evidencias digitales (copia)</td>
												      </c:when>		
												      <c:otherwise>
												     	 <td>Sin información</td> 
												      </c:otherwise>									      									      
											      </c:choose>											      

											      <!--<c:choose>
												      <c:when test="${dispositivo.fotografia != ''}">
												     	 <td>Disponible</td>
												      </c:when>
												     <c:otherwise>
												      	<td>No disponible</td>
												      </c:otherwise>
											      </c:choose>
											      <td><a href="${pageContext.request.contextPath}/ServletControlador?accion=consultarCadenaCustodia&idCadenaCustodia=${dispositivo.idCadenaCustodia}&nombreDispositivo=${dispositivo.nombre}&idCaso=${dispositivo.idCaso}" class="text-dark">${dispositivo.idCadenaCustodia}</a></td>
											      <td>${dispositivo.idCicloVida}</td>	-->										      
											      <td><fmt:formatDate value="${dispositivo.fechaCreacionReg}" pattern="dd-MM-yyyy HH:mm:ss" /></td>
											      <td><fmt:formatDate value="${dispositivo.fechaUltimaModif}" pattern="dd-MM-yyyy HH:mm:ss" /></td>

													<%										
													if (!tienePermisosStr.isEmpty() && tienePermisosStr.equalsIgnoreCase("si")) {%>
													<td>
														<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownButtonAccionesDispositivos" role="button" data-toggle="dropdown" aria-expanded="false">Seleccionar</button>
												        <ul class="dropdown-menu" aria-labelledby="dropdownButtonAccionesDispositivos">
															<li><a class="dropdown-item" href="${pageContext.request.contextPath}/ServletControlador?accion=editarDispositivo&idDispositivo=${dispositivo.idDispositivo}">Editar</a></li>
															<li><a class="dropdown-item" href="#" data-href="${pageContext.request.contextPath}/ServletControlador?accion=eliminarDispositivoDesdeConsultarCasos&idDispositivo=${dispositivo.idDispositivo}&idCaso=${dispositivo.idCaso}" data-toggle="modal" data-target="#exampleModal">Eliminar</a></li>
														</ul>
														 </td>
													<% }  %>
											    </tr>
											</c:forEach>
										  </tbody>
										</table>
										<br/>							  
								  		<br/>
									</c:when>
									<c:otherwise>
										<dd class="col-sm-9">No existen dispositivos.</dd>
									</c:otherwise>
								  </c:choose>							

								  <dt class="col-sm-3">Evidencias digitales:</dt>
								  <c:choose>
								  	<c:when test="${fn:length(caso.listaEvidencias) > 0}">
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
										      <th scope="col">Dispositivo asociado</th>
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
										  	<c:forEach var="evidencia" items="${caso.listaEvidencias}" varStatus="status">
											    <tr>
											      <th scope="row">${status.index + 1}</th>											      
											      <td><a href="${pageContext.request.contextPath}/ServletControlador?accion=consultarEvidenciaDigital&idEvidencia=${evidencia.idEvidencia}" class="text-dark">${evidencia.idEvidencia}</a></td>
											      <td><a href="${pageContext.request.contextPath}/ServletControlador?accion=consultarEvidenciaDigital&idEvidencia=${evidencia.idEvidencia}" class="text-dark">${evidencia.nombre}</a></td>
											      
											      <c:choose>
											      	<c:when test="${evidencia.tipo == 1}">
											      	 <td>Archivo cifrado</td>
											      	</c:when>
											      	<c:when test="${evidencia.tipo == 2}">
											      	 <td>Archivo comprimido</td>
											      	</c:when>
											      	<c:when test="${evidencia.tipo == 3}">
											      	 <td>Archivo de texto</td>
											      	</c:when>											      												      	
											      	<c:when test="${evidencia.tipo == 4}">
											      	 <td>Audio</td>
											      	</c:when>											      
											      	<c:when test="${evidencia.tipo == 5}">
											      	 <td>Base de datos</td>
											      	</c:when>
											      	<c:when test="${evidencia.tipo == 6}">
											      	 <td>Documento ofimático</td>
											      	</c:when>											      	
											      	<c:when test="${evidencia.tipo == 7}">
											      	 <td>Historial de navegación</td>
											      	</c:when>											      	
											      	<c:when test="${evidencia.tipo == 8}">
											      	 <td>Imagen forense</td>
											      	</c:when>
												    <c:when test="${evidencia.tipo == 9}">
											      	 <td>Imagen</td>
											      	</c:when>										      	
												    <c:when test="${evidencia.tipo == 10}">
											      	 <td>Memoria RAM</td>
											      	</c:when>										      	
											      	<c:when test="${evidencia.tipo == 11}">
											      	 <td>Mensaje/s de correo</td>
											      	</c:when>											      	
											      	<c:when test="${evidencia.tipo == 12}">
											      	 <td>Metadato/s de archivo/s</td>
											      	</c:when>											      	
											      	<c:when test="${evidencia.tipo == 13}">
											      	 <td>Otros</td>
											      	</c:when>											      	
											      	<c:when test="${evidencia.tipo == 14}">
											      	 <td>Redes Wi-Fi detectadas</td>
											      	</c:when>											      	
											      	<c:when test="${evidencia.tipo == 15}">
											      	 <td>Registro de llamadas</td>
											      	</c:when>											      	
											      	<c:when test="${evidencia.tipo == 16}">
											      	 <td>Registro/s de Windows</td>
											      	</c:when>											      												      	
												    <c:when test="${evidencia.tipo == 17}">
											      	 <td>SMS</td>
											      	</c:when>										      	
											      	<c:when test="${evidencia.tipo == 18}">
											      	 <td>Tráfico de red</td>
											      	</c:when>											      	
											      	<c:when test="${evidencia.tipo == 19}">
											      	 <td>Vídeo</td>
											      	</c:when>											      												      												      
											      </c:choose>

											      <td>${evidencia.descripcion}</td>
											     <c:choose>
											      	 <c:when test="${evidencia.idDispositivo == '-1'}">
											      		<td>Sin dispositivo asociado</td> 
											      	</c:when>
											      	<c:otherwise>
											      		<td>[${evidencia.idDispositivo}] ${evidencia.nombreDispositivo}</td>
											      	</c:otherwise>
											      </c:choose>
											      
											      <td><fmt:formatDate value="${evidencia.fechaCreacionReg}" pattern="dd-MM-yyyy HH:mm:ss" /></td>
											      <td><fmt:formatDate value="${evidencia.fechaUltimaModif}" pattern="dd-MM-yyyy HH:mm:ss" /></td>
													<%										
													if (!tienePermisosStr.isEmpty() && tienePermisosStr.equalsIgnoreCase("si")) {%>
													<td>
														<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownButtonAccionesEvidencias" role="button" data-toggle="dropdown" aria-expanded="false">Seleccionar</button>
												        <ul class="dropdown-menu" aria-labelledby="dropdownButtonAccionesEvidencias">
															<li><a class="dropdown-item" href="${pageContext.request.contextPath}/ServletControlador?accion=editarEvidencia&idEvidencia=${evidencia.idEvidencia}">Editar</a></li>
															<li><a class="dropdown-item" href="#" data-href="${pageContext.request.contextPath}/ServletControlador?accion=eliminarEvidenciaDesdeConsultarCasos&idDispositivo=${evidencia.idDispositivo}&idCaso=${evidencia.idCaso}&idEvidencia=${evidencia.idEvidencia}" data-toggle="modal" data-target="#modalEliminarEvidencia">Eliminar</a></li>
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
														  								  
								  <dt class="col-sm-3">Responsables:</dt>	
								  	<c:choose>
								  	<c:when test="${fn:length(caso.listaPersonas) > 0}">							  
									  
									  	<c:choose>
									  		<c:when test="${fn:length(caso.listaPersonas) > 1}">	
									  		<dd class="col-sm-9"> 
										  		<c:forEach var="responsable" items="${caso.listaPersonas}" varStatus="status">					  		
													<!-- <p>${responsable.nombre}</p> -->												
													<p><a href="${pageContext.request.contextPath}/ServletControlador?accion=consultarUsuario&idUsuario=${responsable.idPersona}" class="text-dark">${responsable.nombre}</a></p>
												</c:forEach>
												</dd>
										  	</c:when>
										  	<c:otherwise>
										  		<c:forEach var="responsable" items="${caso.listaPersonas}" varStatus="status">
													<!--<dd class="col-sm-9">${responsable.nombre}</dd>  -->
													<dd class="col-sm-9"><a href="${pageContext.request.contextPath}/ServletControlador?accion=consultarUsuario&idUsuario=${responsable.idPersona}" class="text-dark">${responsable.nombre}</a></dd>												
												</c:forEach>
										  	</c:otherwise>
									  	</c:choose>
									  
									 </c:when>
									 <c:otherwise>
									 	<dd class="col-sm-9">No existen responsables.</dd>
									 </c:otherwise>
								  </c:choose>
								  <dt class="col-sm-3">Fecha creación registro:</dt>
								  <dd class="col-sm-9">
								  	<fmt:formatDate value="${caso.fechaCreacionReg}" pattern="dd-MM-yyyy HH:mm:ss" />
								  </dd>
								  <dt class="col-sm-3">Fecha última modificación:</dt>
								  <dd class="col-sm-9">
								  	<fmt:formatDate value="${caso.fechaUltimaModif}" pattern="dd-MM-yyyy HH:mm:ss" />
								  </dd>								  

								</dl>
							<br/>
							
							<div class="container-fluid">
								<%Object tienePermisos = request.getAttribute("tienePermisos");
								String tienePermisosStr = (String) tienePermisos;
								
								if (!tienePermisosStr.isEmpty() && tienePermisosStr.equalsIgnoreCase("si")) {%>
									<a class="btn btn-secondary" href="${pageContext.request.contextPath}/ServletControlador?accion=agregarDispositivoAlCaso&idCaso=${caso.idCaso}&nombreCaso=${caso.nombre}" role="button"><i class="fas fa-laptop"></i> <i class="fas fa-plus"></i> Agregar dispositivo</a>
									<a class="btn btn-secondary" href="${pageContext.request.contextPath}/ServletControlador?accion=agregarEvidenciaAlCaso&idCaso=${caso.idCaso}&nombreCaso=${caso.nombre}" role="button"><i class="fas fa-file-video"></i> <i class="fas fa-plus"></i> Agregar evidencia digital</a>
									<a class="btn btn-secondary" href="${pageContext.request.contextPath}/ServletControlador?accion=agregarResponsableAlCaso&idCaso=${caso.idCaso}&nombreCaso=${caso.nombre}" role="button"><i class="fas fa-user-friends"></i> <i class="fas fa-plus"></i> Agregar responsable</a>
								<% } else { %>
									<div class="alert alert-secondary" role="alert">
									  No dispones de <strong>permisos</strong> para <strong>editar</strong> el caso ni para <strong>añadir dispositivos ni evidencias digitales</strong> a este caso.
									</div>
									<a class="btn btn-secondary disabled" disabled href="#" role="button" aria-disabled="true"><i class="fas fa-laptop"></i> <i class="fas fa-plus"></i> Agregar dispositivo</a>
									<a class="btn btn-secondary disabled" disabled href="#" role="button" aria-disabled="true"><i class="fas fa-file-video"></i> <i class="fas fa-plus"></i> Agregar evidencia digital</a>
									<a class="btn btn-secondary disabled" disabled href="#" role="button" aria-disabled="true"><i class="fas fa-user-friends"></i> <i class="fas fa-plus"></i> Agregar responsable</a>						
								<% } %>
							</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<!-- Modal Eliminar Dispositivo -->
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
		
		$('#exampleModal').on('show.bs.modal', function(e) {
		    $(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));
		});
		
		$('#modalEliminarEvidencia').on('show.bs.modal', function(e) {
		    $(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));
		});


		</script>
			
			
		</section>
				
		<!-- Pie de página -->
		<jsp:include page="/WEB-INF/paginas/comunes/piePagina.jsp" />

	</body>
</html>