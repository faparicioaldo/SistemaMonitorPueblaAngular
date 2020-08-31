app.controller("domicilioTrabajadorCtrl", ['$scope', '$location', '$rootScope', function($scope, $location, $rootScope) {
	$rootScope.numberTab = 2;
	$rootScope.mostrarPestaniasAnalista = false;
	$rootScope.mostrarPestaniasCliente = true;

	$scope.redirect = function(ruta) {
		console.log(ruta);
		$location.path(ruta);
	}
}]);