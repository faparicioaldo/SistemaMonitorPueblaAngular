app.controller("monitorCtrl", ['$scope','FuncionesService', '$location', '$rootScope', '$websocket', function($scope, FuncionesService, $location, $rootScope, $websocket) {
	//$websocket
	
	$rootScope.numberTab = 2;
	
	$scope.listaAlertasEnviadasSemovi = [];
	$scope.mensaje = "";
	
	$scope.init = function() {
	}
	
	
	// path to the end point is actually /app/uc/status/request
    // unable to add end point path when connecting
    // and don't know how to subscribe
    
    var ws = $websocket('ws://localhost:8080/MonitorAlertasPuebla/ws/websocket');

    ws.onMessage(function(event) { 
        console.log('message: ', event);
    });

    ws.onError(function(event) {
        console.log("error");
    });

    ws.onClose(function(event) { 
        console.log('connection close');
    });

    ws.onOpen(function(event) { 
        console.log('connection open');
    });

	
	
	
	$scope.enviarMensaje = function() {
		console.log('Enviando mensaje websocket....');

		// nothing happens when this call is made
	    ws.send("message content", "/app/send/message");
	    ws.send("/app/send/message", "message content");
	    ws.send("message content");

		console.log('Enviado.');

    };
    
	$scope.enviarAlertaSemovi = function(idAlerta){
		console.log("Enviando alerta: " + idAlerta);
		FuncionesService.POST("/MonitorAlertasPuebla/enviarAlertaSemovi", idAlerta).then(
            function(respuesta) {
				console.log("respuesta envio de alerta: " + JSON.stringify(respuesta, null, '\t'));
				
                if (respuesta) {
                }
            }
        );
	}

	$scope.descartarAlertaCeiba = function(idAlerta){
		console.log("Descartando alerta: " + idAlerta);
		FuncionesService.POST("/MonitorAlertasPuebla/descartarAlarma", idAlerta).then(
            function(respuesta) {
				console.log("respuesta descartar alerta: " + JSON.stringify(respuesta, null, '\t'));
				
                if (respuesta) {
                }
            }
        );
	}
	/*
	
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