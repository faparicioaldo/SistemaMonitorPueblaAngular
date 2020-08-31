app.controller("datosLaboralesCtrl", ['$scope', '$location', '$rootScope', function($scope, $location, $rootScope) {
	$rootScope.numberTab = 1;
	$rootScope.mostrarPestaniasAnalista = false;
	$rootScope.mostrarPestaniasCliente = true;

	$scope.redirect = function(ruta) {
		console.log(ruta);
		$location.path(ruta);
	}
}]);