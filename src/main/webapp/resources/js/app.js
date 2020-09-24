
function upperCase(value) {
	if (value.length > 0) {
		value = trimAccents(trim(value.toUpperCase()));
	}
	return value;
}

function desactivaAutocompletar() {
	for (i = 0; i < document.forms.length; i++) {
		document.forms[i].setAttribute("AutoComplete", "off");
	}
}

'use strict'
var app = angular.module("fonacotApp", [ "ngRoute", "ngSanitize", "ngMaterial",
		"ngMessages", "ui.mask", "angularUtils.directives.dirPagination"]);

app.config([ '$routeProvider', '$compileProvider',
	function($routeProvider, $compileProvider) {
			
			$compileProvider.debugInfoEnabled(true);
    		$compileProvider.aHrefSanitizationWhitelist(/^\s*(https?|ftp|mailto|javascript|local|data|chrome-extension):/);
			$compileProvider.commentDirectivesEnabled(false);
			$compileProvider.cssClassDirectivesEnabled(false);
		
			$routeProvider.when("/", {
				templateUrl : "views/indexView.jsp",
				controller : "indexCtrl"

			}).when("/alertasBtnPanico", {
				templateUrl : "/MonitorAlertasPuebla/views.spa/alertasBtnPanicoView.jsp",
				controller : "alertasBtnPanicoCtrl"
			}).when("/vehiculosRegistrados", {
				templateUrl : "/MonitorAlertasPuebla/views.spa/vehiculosRegistrados.jsp",
				controller : "vehiculosRegistradosCtrl"
			}).when("/monitor", {
				templateUrl : "/MonitorAlertasPuebla/views.spa/monitorView.jsp",
				controller : "monitorCtrl"
			}).when("/historicoAlertasBtnPanico", {
				templateUrl : "/MonitorAlertasPuebla/views.spa/historicoAlertasBtnPanicoView.jsp",
				controller : "historicoAlertasBtnPanicoCtrl"
			});
	}]);

app.run([
	'$rootScope',
	'$q',
	'$interval',
	'$sce',
	'$timeout',
	'$location',
	'FuncionesService',
	function($rootScope, $q, $interval, $sce, $timeout, $location, FuncionesService) {
		$rootScope.fechaActual = new Date();		
        
        $rootScope.message = {};
        $rootScope.message.alerts = 0;
        $rootScope.message.vehicles = 0;
        $rootScope.message.gpss = 0;
        
		$rootScope.datosVehiculoEditar = [];
		$rootScope.listaVehiculosRegistrados = [];

		$location.path('/alertasBtnPanico');
			
		$rootScope.tabs = [ 
				"Alertas Panico",
				"Vehiculos Registrados",
				"Monitor",
				"Historico Alertas Panico"
				];

		$rootScope.isTabActive = function(number) {
			return ($rootScope.numberTab == number) ? "active" : "";
		};				

		$rootScope.disableTab = function(number) {
			if ($rootScope.numberTab == number) {
				return true;			
			}else{
				return false;				
			}
		};				
		
		$rootScope.existJQuery = function() {
			var defered = $q.defer();
			var promise = defered.promise;
	
			var intervalo = $interval(
				function() {
					if (typeof $ !== 'undefined' && typeof $('[data-toggle="tooltip"]').tooltip !== 'undefined') {
						$interval.cancel(intervalo);
						defered.resolve($);
					}
				}, 100
			);
	
			return promise;
		};
		$rootScope.validaMensajeFatal = function(respuesta) {
			if (respuesta.respuestaCode != 0) {
				if (respuesta.descripcionCode.length > 0) {
					$rootScope
						.existJQuery()
						.then(
							function(data) {
								$rootScope.errores = respuesta;
								if (typeof respuesta.ruta != 'undefined') {
									$("#btnModalMensaje").attr("href","#!" + respuesta.ruta)
								} else {
									$("#btnModalMensaje").removeAttr("href");
								}
								$("#mensaje").modal('toggle');
							});
					return false;
				}
			}
			return true;
		};
		
		$rootScope.servicioNoDisponible = function(mensaje) {
			var respuesta = {};
			respuesta.respuestaCode = -1;
			respuesta.descripcionCode = typeof mensaje !== 'undefined' ? mensaje : 'Servicio no disponible';
			$rootScope.validaMensajeFatal(respuesta);
		};
		$rootScope.resetTooltip = function() {
			$rootScope.existJQuery().then(function(data) {
				$('[data-toggle="tooltip"]').tooltip();
			})
		};
		$rootScope.$on(
			"$locationChangeStart",
			function(event, next, current) {
				var path = next.split("/#!/")[1];
				path = angular.isUndefined(path) ? "" : path;
				$rootScope.errores = null;
				$rootScope.resetScroll(path.length == 0 ? 0 : 190);
				$rootScope.pathUDM = 'https://b.scorecardresearch.com/b?c1=2&c2=17183199&ns_site=gobmx&name=fonacotCTGob.'
						+ (path.length == 0 ? 'index' : path);
				window.removeEventListener(
						'beforeunload',
						$rootScope.eventoBeforeunload);
				if (path != 'infoCentroTrabajo') {
					$timeout(
							function() {
								desactivaAutocompletar();
								$rootScope
										.resetTooltip();
							}, 2000);
				}
			}
		);
		$rootScope.resetScroll = function(pos) {
			var position = pos ? pos : 0;
			$rootScope.existJQuery().then(function(data) {
				$(document).scrollTop(position);
			});
		};
	}
]);

app.service('DispacherService',[
'$location',
'FuncionesService',
'$rootScope',
function($location, FuncionesService, $rootScope) {
	var centroTrabajo = {};
	var datos = {};
	this.esConcluido = function(ct) {
		centroTrabajo = ct;
		if (ct.idEstatus == 2) {
			$location.path("/infoCentroTrabajo");
		} else {
			var respuesta = {};
			respuesta.respuestaCode = -1;
			respuesta.descripcionCode = 'Usted ya se encuentra en proceso de validaci&oacute;n. Para mayor informaci&oacute;n comun&iacute;quese al tel. 01 800 FONACOT (3662268) o acuda a nuestras oficinas.';
			$rootScope.validaMensajeFatal(respuesta);
			$location.path("/consultarAfiliacion");
		}
	};
	
	this.insertaValores = function(data) {
		data.rfcCT = centroTrabajo.ctRFC;
		data.registroPatronal = centroTrabajo.registroPatronal;
		centroTrabajo = {};
	};

	this.mantieneDatos = function(registroPatronal) {
		datos.registroPatronal = registroPatronal.toUpperCase();
	};
	this.obtieneDatos = function() {
		return datos;
	};
}]);
	
//https://dimitr.im/spring-angular-sockjs	
app.service('MonitorService',['$rootScope', '$q', '$timeout', function($rootScope, $q, $timeout) {

	var service = {}; 

	var socket = { client: null, stomp: null };
    var messageIds = [];
    var listener = $q.defer();
    
    service.RECONNECT_TIMEOUT = 30000;
    service.SOCKET_URL = "/MonitorAlertasPuebla/ws";

    service.VEHICLE_TOPIC = "/topic/vehicle";
    service.ALERT_TOPIC = "/topic/alert";
    service.GPS_TOPIC = "/topic/gps";
    
    service.receive = function() {
      return listener.promise;
    };
    
    var reconnect = function() {
   	  console.log('reconexion a websocket....');
      $timeout(function() {
        initialize();
      }, this.RECONNECT_TIMEOUT);
    };
    
    var getMessage = function(data) {    
	  var message = JSON.parse(data), out = {};
      out.message = message.content;
      out.time = new Date(message.time);
      return out;
    };
    
    var startListener = function() {
      socket.stomp.subscribe(service.ALERT_TOPIC, function(data) {
    	 $rootScope.message.alerts += 1;
       	 listener.notify("hola");
      });      
      socket.stomp.subscribe(service.VEHICLE_TOPIC, function(data) {
      	 var newVehicles = JSON.parse(data.body);
      	 console.log("NUEVOS VEHICULOS ENCONTRADOS: " + JSON.stringify(newVehicles, null, '\t'));
      	 
		 $rootScope.listaVehiculosRegistrados.unshift(...newVehicles);
		 
       	 $rootScope.message.vehicles += 1;
       	 listener.notify("hola");
      });
      socket.stomp.subscribe(service.GPS_TOPIC, function(data) {
    	 $rootScope.message.gpss += 1;
    	 listener.notify("hola");
    	 
      });      
    };

    var errorConexion = function() {
   	  console.log('error conexion de websocket....');
	  alert("Hubo un problema al conectar con el socket");
    };

    var initialize = function() {
   	  console.log('inicializando conecion a websocket....');
      socket.client = new SockJS(service.SOCKET_URL);
      socket.stomp = Stomp.over(socket.client);
      socket.stomp.connect({}, startListener, errorConexion);
      socket.stomp.onclose = reconnect;
    };
    
    initialize();
    return service;
}]);
		
app.service('FuncionesService',['$http','$q','$rootScope', function($http, $q, $rootScope) {
	// Detecta si el navegador es Internet Explorer
	this.isIE = function() {
		var ua = window.navigator.userAgent;
		var msie = ua.indexOf("MSIE ");
		return msie > 0 || !!ua.match(/Trident.*rv\:11\./);
	};
	// Detecta si el navegador esta en un dispositivo movil
	this.isMobile = function() {
		if (window.navigator.userAgent.match(/Mobile/i)
				|| window.navigator.userAgent
						.match(/Tablet/i)
				|| window.navigator.userAgent
						.match(/iPhone/i)
				|| window.navigator.userAgent
						.match(/iPod/i)
				|| window.navigator.userAgent
						.match(/iPad/i)
				|| window.navigator.userAgent
						.match(/IEMobile/i)
				|| window.navigator.userAgent
						.match(/Windows Phone/i)
				|| window.navigator.userAgent
						.match(/Android/i)
				|| window.navigator.userAgent
						.match(/BlackBerry/i)
				|| window.navigator.userAgent
						.match(/webOS/i)
				|| window.navigator.userAgent
						.match(/Nexus 7/i)
				|| window.navigator.userAgent
						.match(/Nexus 10/i)
				|| window.navigator.userAgent
						.match(/KFAPWI/i)) {
			return true;
		} else {
			return false;
		}
	};
	this.cifrarTexto = function(dato) {
		// var rsa = new RSAKey();
		// rsa.setPublic('A9E55538FF49DB1AA4A1A7B2F0DA09CB87006A004645372A2A33808A5FD37F4211688B189D7529CFBA29B1EB59356137CA9DF6C62E6BF113AE226EC303246B67',
		// '10001');
		// return hex2b64(rsa.encrypt("" + dato));
		return dato;
	};
	this.POST = function(url, datos) {
		var defered = $q.defer();
		var promise = defered.promise;
		$rootScope.loading = true;
		$http.post(url, datos)
		.then(
			function(response) {
				$rootScope.errores = null;
				$rootScope.loading = false;
				var respuesta = response.data;

				//console.log("RESPONSE: " + JSON.stringify(response, null, '\t'));
				console.log("Peticion POST....");

				if ($rootScope.validaMensajeFatal(respuesta)) {
					defered.resolve(respuesta);
				} else {
					if (datos) {
						datos.captcha = null;
					}
					defered.resolve(respuesta.respuestaCode < 0 ? respuesta: null);
				}
			},
			function(error) {
				if (datos) {
					datos.captcha = null;
				}
				$rootScope.loading = false;
				$rootScope.servicioNoDisponible();
				defered.resolve(null);
			}
		);
		return promise;
	}

	this.windowPost = function(url, datos, target,propiedades) {
		var form = document.createElement('form');
		form.setAttribute('method', 'post');
		form.setAttribute('action', url);
		form.setAttribute('target', target);

		Object
			.keys(datos)
			.forEach(
				function(k) {
					var hiddenField = document.createElement("input");
					hiddenField.setAttribute("type", "hidden");
					hiddenField.setAttribute("name", k);
					hiddenField.setAttribute("value", datos[k]);
					form.appendChild(hiddenField);
				}
			);

		document.body.appendChild(form);

		var win = window.open('',target,'width=800,height=650,left=70,top=70,menubar=yes,scrollbars=yes,status=no,location=no,toolbar="0"');
		win.focus();

		form.submit();
	};

	this.tabPost = function(url, datos, target,
			propiedades) {
		var form = document.createElement('form');
		form.setAttribute('method', 'post');
		form.setAttribute('action', url);
		form.setAttribute('target', target);

		Object
				.keys(datos)
				.forEach(
						function(k) {
							var hiddenField = document
									.createElement("input");
							hiddenField.setAttribute(
									"type", "hidden");
							hiddenField.setAttribute(
									"name", k);
							hiddenField.setAttribute(
									"value", datos[k]);
							form
									.appendChild(hiddenField);
						});

		document.body.appendChild(form);

		form.submit();
	};

	this.push = function(array, item, value) {
		if (angular.isUndefined(array[item])) {
			array[item] = [ value ];
		} else {
			array[item].push(value);
		}
	};
} ]);

app.factory('utilityService', ['$http','$q','$rootScope', function($http, $q, $rootScope) {
	  return {
			mensaje: 'jeje...',
	  		getIdDispositivo: function() {
				return this.mensaje;
			},
			setIdDispositivo: function(msj) {
				this.mensaje = msj;
			},
			mostrarMensaje: function(msj) {
				$rootScope.errores = {};
				$rootScope.errores.descripcionCode = msj;
				
				$("#mensaje").modal('toggle');

			}
		};
}]);
