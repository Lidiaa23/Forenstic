<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<section id="dispositivos">
	<div class="container-fluid" style="max-width:2000px">
		<div class="row">
			<div class="col">
				<div class="card mx-auto">
					<div class="card-header">
						<h4>Lista de evidencias digitales</h4>
					</div>
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
							<!-- Iteramos cada elemento de la lista de evidencias -->
							<c:forEach var="evidencia" items="${listaEvidencias}" varStatus="status">
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
				</div>
			</div>
		</div>
	</div>

</section>