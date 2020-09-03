<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!-- index.jsp -->
<html>
<head>
	<title>Portal de Afiliaci&oacute;n de Centros de Trabajo</title>
	<link href="favicon.ico" rel="shortcut icon">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
 	<style type="text/css">
  		.cursorManita {  
  			cursor: pointer  
  		}  
	</style>
	
	<link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/styles.css">
	<link href="<%=request.getContextPath()%>/css/adicionales.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
	
	<script src="<%=request.getContextPath()%>/js/rsaGenerator.js" type="application/javascript"></script>
	<script type="application/javascript" src="<%=request.getContextPath()%>/js/scriptsInternet.js"	type="application/javascript"></script>
	<script type="application/javascript" src="<%=request.getContextPath()%>/js/polyfill.js" type="application/javascript"></script>
	
	<!-- CSS -->
	<link href="/favicon.ico" rel="shortcut icon">
	
	<!-- Angular Material requires Angular.js Libraries -->
	<script	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.js"></script>
	<script	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular-route.js"></script>
	<script	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular-sanitize.js"></script>
	<script	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular-animate.js"></script>
	<script	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular-aria.js"></script>
	<script	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular-messages.js"></script>
	
	<!-- Angular Material Library -->
	<script	src="https://ajax.googleapis.com/ajax/libs/angular_material/1.1.8/angular-material.js"></script>


	<script type="text/javascript">

	/*
	 * INICIA FUNCIONALIDAD DE CLIENTE WEB SOCKET
	 */
		
		'use strict';
		
		var stompClient = null;
		
		function connect() {
	        var socket = new SockJS('/MonitorAlertasPuebla/ws');
	        stompClient = Stomp.over(socket);

	        stompClient.connect({}, onConnected, onError);
		}
		
		function onConnected() {
		    stompClient.subscribe('/topic/public', onMessageReceived);
		}

		function onError(error) {
			alert("No se ha podido conectart al servidor de websocket. Por favor, recargue la pagina para intentar nuevamente!");
		}

		function onMessageReceived(payload) {
	 	    var message = JSON.parse(payload.body);
	 	    alert(message.content + " Se encontraron alertas nuevas!!");
// 		    location.reload();
		    
		}
		
		/*
		 * TERMINA FUNCIONALIDAD DE CLIENTE WEB SOCKET
		 */
	
	</script>

</head>
<body onload="connect();" ng-app="fonacotApp" ng-controller="appController" ng-init="init()" ng-strict-di>

	<br/><br/>
	
	<div class="container">

	<div class="container">
		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<form id="logoutForm" method="POST"	action="<%=request.getContextPath()%>/logout">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</form>
			<h6 class="pull-right">
				Bienvenido ${pageContext.request.userPrincipal.name} | 
				<a onclick="document.forms['logoutForm'].submit()">SALIR</a>
			</h6>
		</c:if>
	</div>

		<div class="container">
<!-- 			<div class="row"> -->
<!-- 				<div class="col"> -->
					<ol class="breadcrumb">
						<li>
							<a href="https://www.gob.mx/">
								<i class="icon icon-home"></i>
							</a>
						</li>
						<li>
							<a ng-click="redirect('preafiliacionesPorValidar')">Inicio</a>
						</li>
						<li class="active" ng-bind="$root.tabs[$root.numberTab]"></li>
					</ol>
<!-- 				</div>	 -->
<!-- 				<div class="row"> -->
<!-- 					<div class="col"> -->
<!-- 						<span class="float-right">Fecha Actual: {{fechaActual |	date:'dd-MM-yyyy'}}</span> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</div> -->
		</div>
		<div class="container">

			<ul class="nav nav-tabs container cursorManita">

				<li ng-class="isTabActive(0)">
					<a
						ng-click="redirect('monitorAlarmas')"
						style="font-weight: bold; align-content: center">
						{{tabs[0]}}
					</a>
				</li>
				<li ng-class="isTabActive(1)">
					<a
						ng-click="redirect('vehiculosRegistrados')"
						style="font-weight: bold; align-content: center">
						{{tabs[1]}}
					</a>
				</li>
				<li ng-class="isTabActive(2)">
					<a
						ng-click="redirect('domicilioTrabajador')"
						style="font-weight: bold; align-content: center">
						{{tabs[2]}}
					</a>
				</li>					
				<li ng-class="isTabActive(3)">
					<a
						ng-click="redirect('vehiculosRegistradosCeiba')"
						style="font-weight: bold; align-content: center">
						{{tabs[3]}}
					</a>
				</li>												
			</ul>

			<div class="tab-content">
				<div ng-view></div>
			</div>

		</div>
		<!-- container -->
	</div>

	<script type="application/javascript">
		function mostrarModalBuscadorCT() {
			$('#modalBuscadorCT').modal('show');
		}
	
		function mostrarModalBuscadorCP() {
			$('#modalBuscadorCP').modal('show');
		}
		function mostrarModalEditarVehiculo() {
			$('#modalEditarVehiculo').modal('show');
		}		
	</script>

	<modal-editar-vehiculo></modal-editar-vehiculo>
	<modal-buscadorct></modal-buscadorct>
	<modal-buscadorcp cp="datosPreafiliacion.codPostal"></modal-buscadorcp>
	<loading></loading>
	<modal-mensaje></modal-mensaje>
	<modal-mensajepdf></modal-mensajepdf>
	<modal-terminar-preafiliacion></modal-terminar-preafiliacion>


	<!-- JS -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
  	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
	
<!-- 	<script src="https://framework-gb.cdn.gob.mx/gobmx.js"></script> -->
<!-- 	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script> -->
<!-- 	<script src="https://code.jquery.com/jquery-1.10.2.js"></script> -->
	
<%-- 	<script type="application/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script> --%>
	<script type="application/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.12.1.min.js"></script>
<!-- 	<script	src="https://framework-gb.cdn.gob.mx/assets/scripts/jquery-ui-datepicker.js"></script> -->

<!-- 	<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-fileinput/5.0.1/css/fileinput.min.css" media="all" rel="stylesheet" type="text/css" /> -->
<!-- 	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-fileinput/5.0.1/js/fileinput.min.js"></script> -->

	<script type="application/javascript" src="<%= request.getContextPath() %>/js/dirPagination.js"></script>

	<script type="application/javascript" src="<%=request.getContextPath()%>/js/app.js"></script>
	<script type="application/javascript" src="<%=request.getContextPath()%>/js/directives.js"></script>
	<script type="application/javascript" src="<%=request.getContextPath()%>/controllers/components.js"></script>
	<script type="application/javascript" src="<%=request.getContextPath()%>/controllers/CommonsCtrl.js"></script>

	<script type="application/javascript" src="<%=request.getContextPath()%>/controllers/cliente/datosGeneralesTrabajadorCtrl.js"></script>
	<script type="application/javascript" src="<%=request.getContextPath()%>/controllers/cliente/datosLaboralesCtrl.js"></script>
	<script type="application/javascript" src="<%=request.getContextPath()%>/controllers/cliente/domicilioTrabajadorCtrl.js"></script>
	<script type="application/javascript" src="<%=request.getContextPath()%>/controllers/cliente/otrosDatosCtrl.js"></script>
	<script type="application/javascript" src="<%=request.getContextPath()%>/controllers/cliente/datosConyugeCtrl.js"></script>
	<script type="application/javascript" src="<%=request.getContextPath()%>/controllers/cliente/datosReferencia1Ctrl.js"></script>
	<script type="application/javascript" src="<%=request.getContextPath()%>/controllers/cliente/datosReferencia2Ctrl.js"></script>
	<script type="application/javascript" src="<%=request.getContextPath()%>/controllers/cliente/documentosGeneralesTrabajadorCtrl.js"></script>

	<script type="application/javascript" src="<%=request.getContextPath()%>/controllers/monitorAlarmasCtrl.js"></script>
	<script type="application/javascript" src="<%=request.getContextPath()%>/controllers/vehiculosRegistradosCtrl.js"></script>

</body>
</html>