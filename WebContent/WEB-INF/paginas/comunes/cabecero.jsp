<!-- Cabecero -->
<header id="main-header" class="py-2 bg-dark text-white">
	
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-6">					
				<h1><i class="fas fa-angle-double-right"></i><a href="${pageContext.request.contextPath}/ServletControlador?" class="text-light"> Forenstic</a></h1>
			</div>
		</div>
	</div>
	
	<div class="container">
	
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		
		    <!-- <a class="navbar-brand" href="#">Forenstic</a>-->
		    
		    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
		      <span class="navbar-toggler-icon"></span>
		    </button>
		
		    <div class="collapse navbar-collapse" id="navbarSupportedContent">
		    
		      <ul class="navbar-nav mr-auto">
		        
		        <li class="nav-item dropdown">
		          <a class="nav-link dropdown-toggle" id="navbarDropdownCasos" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="far fa-folder-open"></i> Casos</a>
		          <div class="dropdown-menu" aria-labelledby="navbarDropdown">
		            <a class="dropdown-item" href="${pageContext.request.contextPath}/buscarCaso.jsp">Buscar caso</a>
		            <a class="dropdown-item" href="${pageContext.request.contextPath}/ServletControlador?accion=misCasos">Mis casos</a>
		            <a class="dropdown-item" href="${pageContext.request.contextPath}/agregarCaso.jsp">Nuevo caso</a>
		            <a class="dropdown-item" href="${pageContext.request.contextPath}/ServletControlador">Todos los casos</a>
		          </div>
		        </li>
		        
		        <li class="nav-item dropdown">
		          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownDispositivos" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fas fa-laptop"></i> Dispositivos</a>
		          <div class="dropdown-menu" aria-labelledby="navbarDropdown">
		            <a class="dropdown-item" href="${pageContext.request.contextPath}/buscarDispositivo.jsp">Buscar dispositivo</a>
		            <a class="dropdown-item" href="${pageContext.request.contextPath}/ServletControlador?accion=misDispositivos">Mis dispositivos</a>
		            <a class="dropdown-item" href="${pageContext.request.contextPath}/ServletControlador?accion=consultarMisCasosParaNuevoDispositivo">Nuevo dispositivo</a>
		        	<a class="dropdown-item" href="${pageContext.request.contextPath}/ServletControlador?accion=listarTodosLosDispositivos">Todos los dispositivos</a>
		          </div>
		        </li>
		        
		       <li class="nav-item dropdown">
		          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownEvidenciasDigitales" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fas fa-file-video"></i> Evidencias digitales</a>
		          <div class="dropdown-menu" aria-labelledby="navbarDropdown">
		            <a class="dropdown-item" href="${pageContext.request.contextPath}/buscarEvidencia.jsp">Buscar evidencia digital</a>
		            <a class="dropdown-item" href="${pageContext.request.contextPath}/ServletControlador?accion=misEvidencias">Mis evidencias digitales</a>
		            <a class="dropdown-item" href="${pageContext.request.contextPath}/ServletControlador?accion=consultarMisCasosParaNuevaEvidencia">Nueva evidencia digital</a>
		            <a class="dropdown-item" href="${pageContext.request.contextPath}/ServletControlador?accion=listarTodasLasEvidencias">Todas las evidencias digitales</a>
		          </div>
		        </li>
		        
		        <li class="nav-item dropdown">
		          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownCdenasDeCustodia" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fas fa-link"></i> Cadenas de custodia</a>
		          <div class="dropdown-menu" aria-labelledby="navbarDropdown">
		            <a class="dropdown-item" href="${pageContext.request.contextPath}/ServletControlador?accion=consultarMisDispositivosParaNuevaCadena">Nueva cadena de custodia</a>
		          </div>
		        </li>

				<c:choose>				
					<c:when test="${sessionScope.nombre != null}">
				       <li class="nav-item dropdown">
				          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownUsuarios" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fas fa-user-friends"></i> Usuarios</a>
				          <div class="dropdown-menu" aria-labelledby="navbarDropdown">
				            <a class="dropdown-item" href="${pageContext.request.contextPath}/buscarUsuario.jsp">Buscar usuario</a>
				            <a class="dropdown-item" href="${pageContext.request.contextPath}/ServletControlador?accion=listarTodosLosUsuarios">Todos los usuarios</a>
				          </div>
				        </li>
					</c:when>
					<c:otherwise>
						<li class="nav-item dropdown">
				          <a class="nav-link" href="${pageContext.request.contextPath}/login.jsp" id="navbarDropdownIniciarSesion" role="button" aria-haspopup="true" aria-expanded="false"><i class="fas fa-user-friends"></i> Iniciar sesión</a>
				        </li>   
					</c:otherwise>
				</c:choose>

     			<c:choose>
     				<c:when test="${sessionScope.nombre != null}">
						<li class="nav-item dropdown">
				          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownConfiguracion" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fas fa-cog"></i> Mi cuenta (${sessionScope.nombre})</a>
				          <div class="dropdown-menu" aria-labelledby="navbarDropdown">
				            <a class="dropdown-item" href="${pageContext.request.contextPath}/ServletControlador?accion=consultarUsuario&idUsuario=${sessionScope.idUsuario}">Ver mi perfil</a>
				            <a class="dropdown-item" href="${pageContext.request.contextPath}/ServletControlador?accion=editarUsuario&idUsuario=${sessionScope.idUsuario}">Editar mi perfil</a>
				            <a class="dropdown-item" href="${pageContext.request.contextPath}/ServletControlador?accion=desconectar">Desconectar</a>
				          </div>
				        </li>  
     				</c:when>
     			</c:choose>

		        <li class="nav-item">
		          <a class="nav-link" href="#" id="navbarAyuda" role="button" aria-haspopup="true" aria-expanded="false"><i class="fas fa-question-circle"></i> Ayuda</a>
		       </li> 
		        	        
		      </ul>
		     <!--  <form class="form-inline my-2 my-lg-0">
		        <input class="form-control mr-sm-2" type="search" placeholder="Buscar" aria-label="Search">
		        <button class="btn btn-outline-light my-2 my-sm-0" type="submit">Buscar</button>
		      </form> -->
		      
		    </div>
		    
		  </nav>
		
	</div>

</header>