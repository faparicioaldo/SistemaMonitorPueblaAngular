app.controller("alertasBtnPanicoCtrl", ['$scope','FuncionesService', '$location', '$rootScope','MonitorService', function($scope, FuncionesService, $location, $rootScope, MonitorService) {
	
	$rootScope.numberTab = 0;
	
	$rootScope.listaAlertasEnviadasSemovi = [];

	$scope.init = function() {
		console.log("init alertasBtnPanicoCtrl")
		getPannicButtonAlerts();
	}
	
	MonitorService.receive().then(
	function(res){
		console.log("primer funcion");
	},
	function(res){
		console.log("segunda funcion");
	}, 
	function(message) {
		console.log("tercera funcion");
    	//$scope.messages.push(message);
  	});
  
	function getPannicButtonAlerts() {
		console.log('Cargando alertas enviadas a semovi....');

        FuncionesService.POST("/MonitorAlertasPuebla/getPannicButtonAlerts").then(
            function(respuesta) {
				//console.log("datos alertas enviadas semovi respuesta: " + JSON.stringify(respuesta, null, '\t'));
				
                if (respuesta) {
                	$rootScope.listaAlertasEnviadasSemovi = respuesta.listaAlertasEnviadasSemovi;                	
                }
            }
        );
    };
    
	$scope.enviarAlertaSemovi = function(idAlerta){
		console.log("Enviando alerta: " + idAlerta);
		FuncionesService.POST("/MonitorAlertasPuebla/enviarAlertaSemovi", idAlerta).then(
            function(respuesta) {
				console.log("respuesta envio de alerta: " + JSON.stringify(respuesta, null, '\t'));
				
				getPannicButtonAlerts();
				
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
				
				getPannicButtonAlerts();
				
                if (respuesta) {
                }
            }
        );
	}	
}]);