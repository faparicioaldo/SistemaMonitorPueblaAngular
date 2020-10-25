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
                	$rootScope.alertasNoVistas = respuesta.alertasNoVistas;
                	$rootScope.alertasNoVistasCount = respuesta.alertasNoVistasCount;
                }
            }
        );
    };

	$scope.markAlertAsSeen = function(ceibaAlertId){

		//Si alerta ya esta marcada como vista, no se realizan acciones
		if(!$rootScope.alertasNoVistas.find(element => element === ceibaAlertId))
			return;
		
		const NUM_ITEMS_TO_DELETE = 1;
		$rootScope.alertasNoVistas.splice($rootScope.alertasNoVistas.indexOf(ceibaAlertId),NUM_ITEMS_TO_DELETE);
		
		FuncionesService.POST("/MonitorAlertasPuebla/markAlertAsSeen",ceibaAlertId).then(
            function(respuesta) {
                if (respuesta) {
                }
            }
        );		
	};

	$scope.marcarComoNoVisto = function(idAlerta){
		var estiloNoVisto = '';
		
		if(idAlerta != undefined && $rootScope.alertasNoVistas.includes(idAlerta)){
			estiloNoVisto = 'estiloNoVistoRows';
		}
		
		return estiloNoVisto;
	};
	
	$rootScope.indicaAlertasNuevas = function(){
		var estiloNoVisto = '';
		
		if($rootScope.alertasNoVistas.length > 0){
			estiloNoVisto = 'estiloNoVisto';
		}
		
		return estiloNoVisto;
	};
	    
	$scope.enviarAlertaSemovi = function(idAlerta){
		console.log("Enviando alerta: " + idAlerta);
		FuncionesService.POST("/MonitorAlertasPuebla/enviarAlertaSemovi", idAlerta).then(
            function(respuesta) {
				//console.log("respuesta envio de alerta: " + JSON.stringify(respuesta, null, '\t'));
				
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
				//console.log("respuesta descartar alerta: " + JSON.stringify(respuesta, null, '\t'));
				
				getPannicButtonAlerts();
				
                if (respuesta) {
                }
            }
        );
	}	
}]);