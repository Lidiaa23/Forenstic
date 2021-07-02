<section id="actions" class="py-4 mb-4 bg-light">
	
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<a href="#" onclick="history.back()" class="btn btn-light btn-block">
					<i class="fas fa-arrow-left"></i> Volver atrás
				</a>	
			</div>
			<div class="col-md-3">

			<%Object tienePermisos = request.getAttribute("tienePermisos");
			String tienePermisosStr = (String) tienePermisos;
			
			if (!tienePermisosStr.isEmpty() && tienePermisosStr.equalsIgnoreCase("si")) {%>
				<a href="${pageContext.request.contextPath}/ServletControlador?accion=editarDispositivo&idDispositivo=${dispositivo.idDispositivo}" class="btn btn-secondary btn-block">
					<i class="fas fa-laptop"></i> Editar dispositivo
				</a>
			<% }  %>

			</div>
		</div>
	</div>
</section>