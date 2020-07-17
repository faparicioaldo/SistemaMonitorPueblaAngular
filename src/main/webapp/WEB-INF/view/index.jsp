<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- index.jsp -->
<html>
<head>
	<title>Portal de Afiliaci&oacute;n de Centros de Trabajo</title>
	<link href="favicon.ico" rel="shortcut icon">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<style type="text/css">
		body {
			cursor: pointer
		}
	</style>
	
	<link href="<%=request.getContextPath()%>/css/adicionales.css" rel="stylesheet" type="text/css">
	<script src="<%=request.getContextPath()%>/js/rsaGenerator.js" type="application/javascript"></script>
	<script type="application/javascript" src="<%=request.getContextPath()%>/js/scriptsInternet.js"	type="application/javascript"></script>
	<script type="application/javascript" src="<%=request.getContextPath()%>/js/polyfill.js" type="application/javascript"></script>
	
	<!-- CSS -->
	<link href="/favicon.ico" rel="shortcut icon">
	<link href="https://framework-gb.cdn.gob.mx/assets/styles/main.css" rel="stylesheet">
	<script src="https://framework-gb.cdn.gob.mx/gobmx.js"></script>
	
	<!-- Angular Material requires Angular.js Libraries -->
	<script	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
	<script	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular-route.js"></script>
	<script	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular-sanitize.js"></script>
	<script	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular-animate.min.js"></script>
	<script	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular-aria.min.js"></script>
	<script	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular-messages.min.js"></script>
	
	<!-- Angular Material Library -->
	<script	src="https://ajax.googleapis.com/ajax/libs/angular_material/1.1.8/angular-material.min.js"></script>

</head>
<body ng-app="fonacotApp" ng-controller="appController" ng-init="init()" ng-strict-di onload="scopeHolderCommons()">

	<br>
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

	<img ng-src="{{$root.pathUDM}}" height="1" width="1" alt="*" ng-show="false"></img>
	<div class="ns_ form-horizontal">
		<div class="container">
			<div class="row">
				<div class="col-md-6 col-sm-6 col-xs-6">
					<img src="<%=request.getContextPath()%>/imgs/logoSTPS2.jpg"	style="width: 120px;" />
				</div>
				<div class="col-md-6 col-sm-6 col-xs-6">
					<img src="<%=request.getContextPath()%>/imgs/infonacotlogdocrgb2.jpg" style="width: 120px;" class="pull-right" />
				</div>
			</div>
			<div class="row">&nbsp;</div>
		</div>

		<div class="container">
			<ol class="breadcrumb">
				<li><a href="https://www.gob.mx/"
					onclick="uid_call('fonacotCTGob.index.ligaGobernacion','clickout')"><i
						class="icon icon-home"></i></a></li>
				<li><a ng-click="redirect('preafiliacionesPorValidar')">Inicio</a></li>
				<li class="active" ng-bind="$root.tabs[$root.numberTab]"></li>
				<p class="pull-right">Fecha Actual: {{fechaActual |	date:'dd-MM-yyyy'}}</p>
			</ol>
		</div>
		<div class="container">

			<ul class="nav nav-tabs container">

				<li ng-class="isTabActive(0)">
					<a
						ng-click="redirect('datosGeneralesTrabajador')"
						style="font-weight: bold; align-content: center">
						Datos Generales <br> del Trabajador
					</a>
				</li>
				<li ng-class="isTabActive(1)">
					<a
						ng-click="validaCurp()"
						style="font-weight: bold; align-content: center">
						Datos <br> Laborales
					</a>
				</li>
				<li ng-class="isTabActive(2)">
					<a
						ng-click="redirect('domicilioTrabajador')"
						style="font-weight: bold; align-content: center">
						Domicilio Del <br> Trabajador(Cliente)
					</a>
				</li>
				<li ng-class="isTabActive(3)">
					<a
						ng-click="redirect('otrosDatos')"
						style="font-weight: bold; align-content: center">
						Otros <br> Datos
					</a>
				</li>
				<li ng-class="isTabActive(4)" ng-if="tieneConyuge()">
					<a
						ng-click="redirect('datosConyuge')"
						style="font-weight: bold; align-content: center">
						Datos del <br> Conyuge
					</a>
				</li>
				<li ng-class="isTabActive(5)">
					<a
						ng-click="redirect('datosReferencia1')"
						style="font-weight: bold; align-content: center">
						Datos <br> Referencia 1
					</a>
				</li>
				<li ng-class="isTabActive(6)">
					<a
						ng-click="redirect('datosReferencia2')"
						style="font-weight: bold; align-content: center">
						Datos <br> Referencia 2
					</a>
				</li>
				<li ng-class="isTabActive(7)">
					<a
						ng-click="redirect('documentosGeneralesTrabajador')"
						style="font-weight: bold; align-content: center">
						Documentos Generales <br> del Trabajador
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
	</script>

	<modal-buscadorct></modal-buscadorct>
	<modal-buscadorcp cp="datosPreafiliacion.codPostal"></modal-buscadorcp>
	<loading></loading>
	<modal-mensaje></modal-mensaje>
	<modal-mensajepdf></modal-mensajepdf>
	<modal-terminar-preafiliacion></modal-terminar-preafiliacion>
	<script	src="https://framework-gb.cdn.gob.mx/data/encuesta_v1.0/encuestas.js"></script>

	<!-- JS -->
	<script src="https://framework-gb.cdn.gob.mx/gobmx.js"></script>
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script type="application/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
	<script type="application/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.12.1.min.js"></script>
	<script	src="https://framework-gb.cdn.gob.mx/assets/scripts/jquery-ui-datepicker.js"></script>

<!-- 	<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-fileinput/5.0.1/css/fileinput.min.css" media="all" rel="stylesheet" type="text/css" /> -->
<!-- 	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-fileinput/5.0.1/js/fileinput.min.js"></script> -->

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

</body>
</html>