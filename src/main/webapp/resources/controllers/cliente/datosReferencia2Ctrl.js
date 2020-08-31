app.controller("datosReferencia2Ctrl", ['$scope', '$location', '$rootScope', function($scope, $location, $rootScope) {
	$rootScope.numberTab = 6;
	$rootScope.mostrarPestaniasAnalista = false;
	$rootScope.mostrarPestaniasCliente = true;

	$scope.init = function() {
		console.log('init ref2');
//		$location.path(ruta);
	};

	$scope.redirect = function(ruta) {
		console.log(ruta);
		$location.path(ruta);
	}
}]);