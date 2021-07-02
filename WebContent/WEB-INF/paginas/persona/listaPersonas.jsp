<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<section id="dispositivos">
	<div class="container-fluid" style="max-width:2000px">
		<div class="row">
			<div class="col">
				<div class="card mx-auto">
					<div class="card-header">
						<h4>Lista de usuarios</h4>
					</div>

						<c:choose>
							<c:when test="${fn:length(listaPersonas) > 0}">
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
									<!-- Iteramos cada elemento de la lista de casos -->
									<c:forEach var="persona" items="${listaPersonas}" varStatus="status">
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

</section>