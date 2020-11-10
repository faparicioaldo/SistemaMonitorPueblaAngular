app.controller("monitorCtrl", 
['$scope','FuncionesService', '$location', '$rootScope', 'MonitorService', 
function($scope, FuncionesService, $location, $rootScope, MonitorService) {
	$rootScope.numberTab = 2;

	$scope.listaAlertasEnviadasSemovi = [];
	$scope.mensaje = "";
	
	$scope.init = function() {
		console.log("init...");
	}
	
}]);