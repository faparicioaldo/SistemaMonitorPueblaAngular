app.controller("datosConyugeCtrl", ['$scope', '$location', '$rootScope', function($scope, $location, $rootScope) {
	$rootScope.numberTab = 4;

	$scope.redirect = function(ruta) {
		console.log(ruta);
		$location.path(ruta);
	}
}]);