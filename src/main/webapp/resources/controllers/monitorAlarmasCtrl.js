app.controller("monitorAlarmasCtrl", ['$scope','FuncionesService', '$location', '$rootScope', function($scope, FuncionesService, $location, $rootScope) {
	$rootScope.numberTab = 0;
	
	$scope.listaAlertasEnviadasSemovi = [];

	$scope.init = function() {
		console.log("init monitorAlarmasCtrl")
		cargaAlertasEnviadasSemovi();
	}
	
	function cargaAlertasEnviadasSemovi() {
		console.log('Cargando alertas enviadas a semovi....');

        FuncionesService.POST("/MonitorAlertasPuebla/cargaAlertasEnviadasSemovi").then(
            function(respuesta) {
				//console.log("datos alertas enviadas semovi respuesta: " + JSON.stringify(respuesta, null, '\t'));
				
                if (respuesta) {
                	$scope.listaAlertasEnviadasSemovi = respuesta.listaAlertasEnviadasSemovi;                	
                }
            }
        );
    };
    
	/*
	$scope.enviarAlarmaPOST = function(idAlarma, deviceid){
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
	$scope.descartarAlarmaPOST = function(idAlarma, deviceid){
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
	*/
	/*
 * INICIA FUNCIONALIDAD DE CLIENTE WEB SOCKET
 */
	/*
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
	*/
	/*
	 * TERMINA FUNCIONALIDAD DE CLIENTE WEB SOCKET
	 */
	
}]);