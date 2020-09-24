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
	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/styles.css">
	<link href="<%=request.getContextPath()%>/css/adicionales.css" rel="stylesheet" type="text/css">
	<link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<!-- 	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous"> -->
	
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
	<script	src="https://cdn.rawgit.com/AngularClass/angular-websocket/v2.0.0/dist/angular-websocket.js"></script>

	<!-- Angular Material Library -->
	<script	src="https://ajax.googleapis.com/ajax/libs/angular_material/1.1.8/angular-material.js"></script>

</head>
<body ng-app="fonacotApp" ng-controller="appController" ng-init="init()" ng-strict-di>
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
				<label>MENSAJE ALERTS: {{message.alerts}}</label>
				<label>MENSAJE VEHICLES: {{message.vehicles}}</label>
				<label>MENSAJE GPSS: {{message.gpss}}</label>
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
						ng-click="redirect('alertasBtnPanico')"
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
						ng-click="redirect('monitor')"
						style="font-weight: bold; align-content: center">
						{{tabs[2]}}
					</a>
				</li>					
				<li ng-class="isTabActive(3)">
					<a
						ng-click="redirect('historicoAlertasBtnPanico')"
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
			console.log("mostrando modalEditarVehiculo...");
			$('#modalEditarVehiculo').modal('show');
		}		
	</script>

	<modal-editar-vehiculo></modal-editar-vehiculo>
	<loading></loading>
	<modal-mensaje></modal-mensaje>


	<!-- JS -->
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<!--   	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script> -->
<!--   	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script> -->
  	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
  	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<!-- 	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script> -->
	
<!-- 	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script> -->
<!-- 	<script src="https://code.jquery.com/jquery-1.10.2.js"></script> -->
	
<%-- 	<script type="application/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script> --%>
	<script type="application/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.12.1.min.js"></script>

	<script type="application/javascript" src="<%= request.getContextPath() %>/js/dirPagination.js"></script>

	<script type="application/javascript" src="<%=request.getContextPath()%>/js/app.js"></script>
	<script type="application/javascript" src="<%=request.getContextPath()%>/js/directives.js"></script>
	<script type="application/javascript" src="<%=request.getContextPath()%>/controllers/components.js"></script>
	<script type="application/javascript" src="<%=request.getContextPath()%>/controllers/CommonsCtrl.js"></script>

	<script type="application/javascript" src="<%=request.getContextPath()%>/controllers/alertasBtnPanicoCtrl.js"></script>
	<script type="application/javascript" src="<%=request.getContextPath()%>/controllers/vehiculosRegistradosCtrl.js"></script>
	<script type="application/javascript" src="<%=request.getContextPath()%>/controllers/monitorCtrl.js"></script>
	<script type="application/javascript" src="<%=request.getContextPath()%>/controllers/historicoAlertasBtnPanicoCtrl.js"></script>

</body>
</html>