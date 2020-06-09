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
	function enviarAlarmaPOST(idFolioAlarma){
		var formData = {
	        idFolioAlarma : idFolioAlarma 
	    }
	      
	    $.ajax({
		    type : "POST",
		    contentType : "application/json",
		    url : "/enviarFolioAlarma",
		    data : JSON.stringify(formData),
		    dataType : 'json',
		    success : function(result) {
		        alert('Folio ' + result.idFolioFolioAlarma + ' enviado.');
		    }
	  	});
	}
	function descartarAlarmaPOST(idFolioAlarma){
		var formData = {
		        idFolioAlarma : idFolioAlarma 
	    }
		      
	    $.ajax({
		    type : "POST",
		    contentType : "application/json",
		    url : "/descartarFolioAlarma",
		    data : JSON.stringify(formData),
		    dataType : 'json',
		    success : function(result) {
		        alert('Folio ' + result.idFolioAlarma + ' descartado.');
		    }
	  	});
	}
</script>
</head>
<body>
	<center>
		<h1>FOLIOS DE ALARMAS ENVIADAS A GOBIERNO</h1>
	</center>
	<table class="blueTable">
		<!-- here should go some titles... -->
		<tr>
			<th>#FOLIO</th>
			<th>FOLIO</th>
			<th>ECONOMICO</th>
			<th>FECHA GENERACION FOLIO</th>
			
		</tr>
		<c:forEach varStatus="loopCounter" items="${folios}" var="folio">
			<tr>
				<td><c:out value="${folio.folioid}" /></td>
				<td><c:out value="${folio.folio}" /></td>
				<td><c:out value="${folio.ipdispositivo}" /></td>
				<td><c:out value="${folio.fechagenerado}" /></td>
				

			</tr>
		</c:forEach>
	</table>

	<center>
		<a href="monitorAlarmas">Ir a Monitor de Alarmas</a>
	</center>

</body>
</html>