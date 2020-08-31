app.controller("appController", ['$scope', '$location', '$rootScope', function($scope, $location, $rootScope) {
	$scope.redirect = function(ruta) {
		console.log(ruta);
		$location.path(ruta);
	}
	$rootScope.tieneConyuge = function() {
		return false;
	};

}]);