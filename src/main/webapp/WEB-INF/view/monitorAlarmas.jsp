<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Monitor de Alarmas</title>
<link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="<c:url value = "/resources/styles.css"/>">
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script type="application/javascript">

/*
 * INICIA FUNCIONALIDAD DE CLIENTE WEB SOCKET
 */
	'use strict';
	
	var stompClient = null;
	
	function connect() {
        var socket = new SockJS('/ws');
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
	    location.reload();
	    
	}
	/*
	 * TERMINA FUNCIONALIDAD DE CLIENTE WEB SOCKET
	 */
	
	function enviarAlarmaPOST(idAlarma, deviceid){
		var formData = {
	        idAlarma : idAlarma,
	        deviceid : deviceid
	    }
	    alert("Enviando alarma....");
	    $.ajax({
		    type : "POST",
		    contentType : "application/json",
		    url : "/enviarAlarma",
		    data : JSON.stringify(formData),
		    dataType : 'json',
		    success : function(result) {
		    	if(result.idAlarma == -1 )
			        alert('Atencion: No ha capturado datos del vehiculo, presione la opcion Ir a Datos del Vehiculo.');
		    	else
		    		alert('Alarma ' + result.idAlarma + ' enviada. RESPUESTA: ' + result.deviceid);
	    		location.reload();
		    }
	  	});
	}
	function descartarAlarmaPOST(idAlarma, deviceid){
		var formData = {
		        idAlarma : idAlarma, 
		        deviceid : deviceid
	    }
		      
	    $.ajax({
		    type : "POST",
		    contentType : "application/json",
		    url : "/descartarAlarma",
		    data : JSON.stringify(formData),
		    dataType : 'json',
		    success : function(result) {
		        alert('Alarma ' + result.idAlarma + ' descartada.');
		    }
	  	});
	}
</script>
<style>
	h1 {
		text-align: center
	}
	
	.centrar {
		text-align: center
	}
	
</style>
</head>
<body onload="connect();">

	<h1>MONITOR DE ALARMAS (BOTON DE PANICO)</h1>
	
	<br>
	  <div class="container">
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <h2>Welcome ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a></h2>
    </c:if>
  </div>
  
  <br>
	<table class="blueTable">
		<!-- here should go some titles... -->
		<tr>
			<th>#ALARMA</th>
			<th>ID_CEIBA</th>
			<th>DISPOSITIVO_ID</th>
			<th>PLACA</th>
			<th>ECONOMICO</th>
			<th>FECHA</th>
			<th>ESTATUS SEMOVI</th>
			<th>EMPRESA</th>
			<th>RUTA</th>
			<th>PUERTO</th>
			<th></th>
			<th></th>
			<th></th>
		</tr>
		<c:forEach varStatus="loopCounter" items="${alarmas}" var="alarma">
			<tr>
				<td><c:out value="${alarma.idAlerta}" /></td>
				<td><c:out value="${alarma.ceibaAlarmid}" /></td>
				<td><c:out value="${alarma.idDispositivo}" /></td>
				<td><c:out value="${alarma.plate}" /></td>
				<td><c:out value="${alarma.eco}" /></td>
				<td><c:out value="${alarma.ceibaGpsTime}" /></td>
				<td><c:out value="${alarma.semoviMensaje}" /></td>
				<td><c:out value="${alarma.empresa}" /></td>
				<td><c:out value="${alarma.route}" /></td>
				<td><c:out value="${alarma.ceibaType}" /></td>				
				<c:if test="${alarma.semoviEstatus == 'Cargada'}">
					<td><input type="button" value="Enviar" id="nuevo"
						name="nuevo" onclick="enviarAlarmaPOST(${alarma.idAlerta},${alarma.idDispositivo})" /></td>
					<td><input type="button" value="Descartar" id="nuevo"
						name="nuevo" onclick="descartarAlarmaPOST(${alarma.idAlerta},${alarma.idDispositivo})" />
					</td>
				</c:if>
				<c:if test="${alarma.semoviEstatus != 'Cargada'}">
					<td></td>
					<td></td>
				</c:if>
				<td>
					<input type="button" value="Agregar Datos" id="add" name="add" onclick="window.location='/agregarDatosAlarma/'+${alarma.idAlerta}+'/'+${alarma.idDispositivo}"/>
				</td>

			</tr>
		</c:forEach>
	</table>

	<div class="centrar">
		<a href="/monitorFoliosAlarmas">Ir a Monitor de Folios</a>
	
		<br>
	
		<a href="/datosVehiculos">Agregar Vehiculo</a>
	</div>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
  <script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</body>
</html>