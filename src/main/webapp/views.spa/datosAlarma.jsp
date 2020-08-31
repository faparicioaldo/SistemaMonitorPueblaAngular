<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value = "/resources/styles.css"/>">
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script type="application/javascript">
	function enviarAlarmaPOST(idAlarma){
		var formData = {
	        idAlarma : idAlarma 
	    }
	      
	    $.ajax({
		    type : "POST",
		    contentType : "application/json",
		    url : "/enviarAlarma",
		    data : JSON.stringify(formData),
		    dataType : 'json',
		    success : function(result) {
		        alert('Alarma ' + result.idAlarma + ' enviada.');
		    }
	  	});
	}
	function descartarAlarmaPOST(idAlarma){
		var formData = {
		        idAlarma : idAlarma 
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
</head>
<body>
	<center>
		<h1>EDITOR DE DATOS DE ALARMAS (BOTON DE PANICO)</h1>
	</center>
	<table class="blueTable">
		<!-- here should go some titles... -->
		<tr>
			<th>#ALARMA</th>
			<th>PLACA</th>
			<th>ECONOMICO</th>
			<th>ESTATUS</th>
			<th>EMPRESA</th>
			<th>SOCIO</th>
			<th>FECHA</th>
			<th>IP</th>
			<th>HORA</th>
			<th>ID DISPOSITIVO</th>
			<th>LATITUD</th>
			<th>LONGITUD</th>
			<th>VELOCIDAD</th>
			<th>DIRECCION</th>
			<th>ALARMA</th>
			<th>RUTA</th>
		</tr>
		<c:forEach varStatus="loopCounter" items="${datosVehiculos}" var="vehiculo">
			<tr>
				<td><c:out value="${vehiculo.alarmaId}" /></td>
				<td><c:out value="${vehiculo.placa}" /></td>
				<td><c:out value="${vehiculo.economico}" /></td>
				<td><c:out value="${vehiculo.estatus}" /></td>
				<td><c:out value="${vehiculo.empresa}" /></td>
				<td><c:out value="${vehiculo.socio}" /></td>
				<td><c:out value="${vehiculo.fecha}" /></td>
				<td><c:out value="${vehiculo.imei}" /></td>
				<td><c:out value="${vehiculo.ipDispositivo}" /></td>
				<td><c:out value="${vehiculo.hora_utc}" /></td>
				<td><c:out value="${vehiculo.deviceId}" /></td>
				<td><c:out value="${vehiculo.latitud}" /></td>
				<td><c:out value="${vehiculo.longitud}" /></td>
				<td><c:out value="${vehiculo.velocidad}" /></td>
				<td><c:out value="${vehiculo.direccion}" /></td>
				<td><c:out value="${vehiculo.alarma}" /></td>
				<td><c:out value="${vehiculo.ruta}" /></td>
				<td><input type="button" value="Editar" id="editar"
						name="nuevo" onclick="window.location='/agregarVehiculo/${vehiculo.idDispositivo}';" /></td>

			</tr>
		</c:forEach>
	</table>

	<center>
		<a href="/agregarVehiculo/1">Agregar vehiculo</a>
	</center>
	
	<br>
	
		<center>
		<a href="/monitorAlarmas">Ir a Monitor de Alarmas</a>
	</center>
	
</body>
</html>