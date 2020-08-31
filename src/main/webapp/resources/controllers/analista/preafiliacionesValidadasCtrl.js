app.controller("preafiliacionesValidadasCtrl", ['$scope', 'FuncionesService', '$location', '$rootScope', function($scope, FuncionesService, $location, $rootScope) {
	$rootScope.numberTab = 9;

	$scope.init = function() {
//		console.log('init');
		$rootScope.datosCentroTrabajo = {};
		var idPreafiliacion = 1;
		cargaCombos();
		cargaDatosPreafiliacion(idPreafiliacion);
		cargaPreafiliacionesValidadas();
	};
	
	$scope.sortKey = "";
	$scope.reverse = false;
	 $scope.sort = function(keyname){
	        $scope.sortKey = keyname;   //set the sortKey to the param passed
	        $scope.reverse = !$scope.reverse; //if true make it false and vice versa
	    };

	function cargaPreafiliacionesValidadas() {
		console.log('Cargando preafiliaciones asignadas a analista....');

        FuncionesService.POST("/PortalAnalistasAfiliacion/consultaPreafiliacionesAnalistaValidadas").then(
            function(respuesta) {
        		console.log('combos respuesta: ' + respuesta);		

                if (respuesta) {
                	$scope.preafiliacionesAnalistaValidadas = respuesta.preafiliaciones;

                }
            }
        );
	};
	
	function cargaCombos() {
		console.log('Cargando combos....');		

        FuncionesService.POST("/PortalAnalistasAfiliacion/consultaEstados", $scope.consulta).then(
            function(respuesta) {
        		console.log('combos respuesta: ' + respuesta);		

                if (respuesta) {
                	$scope.estados = respuesta.estados;
                }
            }
        );
    };
	
	function cargaDatosPreafiliacion(idPreafiliacion) {
		console.log('Cargando datos preafiliacion: ' + idPreafiliacion);		

        FuncionesService.POST("/PortalAnalistasAfiliacion/consultaDatosPreafiliacion", idPreafiliacion).then(
            function(respuesta) {
				console.log("datos preafiliacion respuesta: " + JSON.stringify(respuesta, null, '\t'));

                if (respuesta) {

                	$rootScope.datosCentroTrabajo.solicitudId = respuesta.solicitudId;
                	$rootScope.datosCentroTrabajo.tzLock = respuesta.tzLock;
                	$rootScope.datosCentroTrabajo.nombre = respuesta.nombre;
                	$rootScope.datosCentroTrabajo.segundoNombre = respuesta.segundoNombre;
                	$rootScope.datosCentroTrabajo.apPaterno = respuesta.apPaterno;
                	$rootScope.datosCentroTrabajo.apMaterno = respuesta.apMaterno;
                	$rootScope.datosCentroTrabajo.sexo = respuesta.sexo;
                	$rootScope.datosCentroTrabajo.nomCalle = respuesta.nomCalle;
                	$rootScope.datosCentroTrabajo.numExt = respuesta.numExt;
                	$rootScope.datosCentroTrabajo.numInt = respuesta.numInt;
                	$rootScope.datosCentroTrabajo.refUbica = respuesta.refUbica;
                	$rootScope.datosCentroTrabajo.claveColonia = respuesta.claveColonia;
                	$rootScope.datosCentroTrabajo.codPostal = respuesta.codPostal;
                	$rootScope.datosCentroTrabajo.aniosHabitada = respuesta.aniosHabitada;
                	$rootScope.datosCentroTrabajo.mesesHabitada = respuesta.mesesHabitada;
                	$rootScope.datosCentroTrabajo.tipoVivienda = respuesta.tipoVivienda;
                	$rootScope.datosCentroTrabajo.fechaNacimiento = respuesta.fechaNacimiento;
                	$rootScope.datosCentroTrabajo.rfc = respuesta.rfc;
                	$rootScope.datosCentroTrabajo.curp = respuesta.curp;
                	$rootScope.datosCentroTrabajo.numeroImss = respuesta.numeroImss;
                	$rootScope.datosCentroTrabajo.emailCliente = respuesta.emailCliente;
                	$rootScope.datosCentroTrabajo.estadoCivil = respuesta.estadoCivil;
                	$rootScope.datosCentroTrabajo.numPersDepend = respuesta.numPersDepend;
                	$rootScope.datosCentroTrabajo.nombreConyuge = respuesta.nombreConyuge;
                	$rootScope.datosCentroTrabajo.nomCalleCony = respuesta.nomCalleCony;
                	$rootScope.datosCentroTrabajo.numExtCony = respuesta.numExtCony;
                	$rootScope.datosCentroTrabajo.numIntCony = respuesta.numIntCony;
                	$rootScope.datosCentroTrabajo.refUbicacCony = respuesta.refUbicacCony;
                	$rootScope.datosCentroTrabajo.claveColoCony = respuesta.claveColoCony;
                	$rootScope.datosCentroTrabajo.codPostalCony = respuesta.codPostalCony;
                	$rootScope.datosCentroTrabajo.trabajaConyuge = respuesta.trabajaConyuge;
                	$rootScope.datosCentroTrabajo.nombreRef1 = respuesta.nombreRef1;
                	$rootScope.datosCentroTrabajo.nomCtRef1 = respuesta.nomCtRef1;
                	$rootScope.datosCentroTrabajo.nombreRef2 = respuesta.nombreRef2;
                	$rootScope.datosCentroTrabajo.NOM_CT_REF2 = respuesta.NOM_CT_REF2;
                	$rootScope.datosCentroTrabajo.otrosCreditos = respuesta.otrosCreditos;
                	$rootScope.datosCentroTrabajo.otrosIngresos = respuesta.otrosIngresos;
                	$rootScope.datosCentroTrabajo.montoOtrosIng = respuesta.montoOtrosIng;
                	$rootScope.datosCentroTrabajo.NUM_PER_TRABAJ = respuesta.NUM_PER_TRABAJ;
                	$rootScope.datosCentroTrabajo.comisionista = respuesta.comisionista;
                	$rootScope.datosCentroTrabajo.importeSalario = respuesta.importeSalario;
                	$rootScope.datosCentroTrabajo.importeDescuen = respuesta.importeDescuen;
                	$rootScope.datosCentroTrabajo.salarioNeto = respuesta.salarioNeto;
                	$rootScope.datosCentroTrabajo.idClienteCt = respuesta.idClienteCt;
                	$rootScope.datosCentroTrabajo.numRegCt = respuesta.numRegCt;
                	$rootScope.datosCentroTrabajo.fcIngresoCt = respuesta.fcIngresoCt;
                	$rootScope.datosCentroTrabajo.comoSeEntero = respuesta.comoSeEntero;
                	$rootScope.datosCentroTrabajo.userAlta = respuesta.userAlta;
                	$rootScope.datosCentroTrabajo.numDocumento = respuesta.numDocumento;
                	$rootScope.datosCentroTrabajo.tramita = respuesta.tramita;
                	$rootScope.datosCentroTrabajo.fechaSolicitud = respuesta.fechaSolicitud;
                	$rootScope.datosCentroTrabajo.telefono = respuesta.telefono;
                	$rootScope.datosCentroTrabajo.telefonoPais = respuesta.telefonoPais;
                	$rootScope.datosCentroTrabajo.telefonoArea = respuesta.telefonoArea;
                	$rootScope.datosCentroTrabajo.telefonoExt = respuesta.telefonoExt;
                	$rootScope.datosCentroTrabajo.telefono2 = respuesta.telefono2;
                	$rootScope.datosCentroTrabajo.telefonoPais2 = respuesta.telefonoPais2;
                	$rootScope.datosCentroTrabajo.telefonoArea2 = respuesta.telefonoArea2;
                	$rootScope.datosCentroTrabajo.telefonoExt2 = respuesta.telefonoExt2;
                	$rootScope.datosCentroTrabajo.parentescoRef1 = respuesta.parentescoRef1;
                	$rootScope.datosCentroTrabajo.parentescoRef2 = respuesta.parentescoRef2;
                	$rootScope.datosCentroTrabajo.tipoDocumento = respuesta.tipoDocumento;
                	$rootScope.datosCentroTrabajo.puestoTrabajador = respuesta.puestoTrabajador;
                	$rootScope.datosCentroTrabajo.tipoSeguridadSocial = respuesta.tipoSeguridadSocial;
                	$rootScope.datosCentroTrabajo.salarioBase = respuesta.salarioBase;
                	$rootScope.datosCentroTrabajo.nomCalleRef1 = respuesta.nomCalleRef1;
                	$rootScope.datosCentroTrabajo.numExtRef1 = respuesta.numExtRef1;
                	$rootScope.datosCentroTrabajo.numIntRef1 = respuesta.numIntRef1;
                	$rootScope.datosCentroTrabajo.refUbicaRef1 = respuesta.refUbicaRef1;
                	$rootScope.datosCentroTrabajo.claveColoRef1 = respuesta.claveColoRef1;
                	$rootScope.datosCentroTrabajo.codPostalRef1 = respuesta.codPostalRef1;
                	$rootScope.datosCentroTrabajo.nomCalleRef2 = respuesta.nomCalleRef2;
                	$rootScope.datosCentroTrabajo.numExtRef2 = respuesta.numExtRef2;
                	$rootScope.datosCentroTrabajo.numIntRef2 = respuesta.numIntRef2;
                	$rootScope.datosCentroTrabajo.refUbicaRef2 = respuesta.refUbicaRef2;
                	$rootScope.datosCentroTrabajo.claveColoRef2 = respuesta.claveColoRef2;
                	$rootScope.datosCentroTrabajo.codPostalRef2 = respuesta.codPostalRef2;
                	$rootScope.datosCentroTrabajo.nomCtroTrabCony = respuesta.nomCtroTrabCony;
                	$rootScope.datosCentroTrabajo.telCelNum = respuesta.telCelNum;
                	$rootScope.datosCentroTrabajo.trabajaRef1 = respuesta.trabajaRef1;
                	$rootScope.datosCentroTrabajo.trabajaRef2 = respuesta.trabajaRef2;
                	$rootScope.datosCentroTrabajo.telefonoRef1 = respuesta.telefonoRef1;
                	$rootScope.datosCentroTrabajo.telefonoPaisRef1 = respuesta.telefonoPaisRef1;
                	$rootScope.datosCentroTrabajo.telefonoAreaRef1 = respuesta.telefonoAreaRef1;
                	$rootScope.datosCentroTrabajo.telefonoExtRef1 = respuesta.telefonoExtRef1;
                	$rootScope.datosCentroTrabajo.telefonoRef2 = respuesta.telefonoRef2;
                	$rootScope.datosCentroTrabajo.telefonoPaisRef2 = respuesta.telefonoPaisRef2;
                	$rootScope.datosCentroTrabajo.telefonoAreaRef2 = respuesta.telefonoAreaRef2;
                	$rootScope.datosCentroTrabajo.telefonoExtRef2 = respuesta.telefonoExtRef2;
                	$rootScope.datosCentroTrabajo.estadoAfiliacion = respuesta.estadoAfiliacion;
                	$rootScope.datosCentroTrabajo.telefonoExtCony = respuesta.telefonoExtCony;
                	$rootScope.datosCentroTrabajo.telefonoCony = respuesta.telefonoCony;
                	$rootScope.datosCentroTrabajo.telefonoAreaCony = respuesta.telefonoAreaCony;
                	$rootScope.datosCentroTrabajo.telefonoPaisCony = respuesta.telefonoPaisCony;
                	$rootScope.datosCentroTrabajo.emailCony = respuesta.emailCony;
                	$rootScope.datosCentroTrabajo.telCelNumRef1 = respuesta.telCelNumRef1;
                	$rootScope.datosCentroTrabajo.telCelNumRef2 = respuesta.telCelNumRef2;
                	$rootScope.datosCentroTrabajo.emailRef1 = respuesta.emailRef1;
                	$rootScope.datosCentroTrabajo.emailRef2 = respuesta.emailRef2;
                	$rootScope.datosCentroTrabajo.nomCalleCtRef1 = respuesta.nomCalleCtRef1;
                	$rootScope.datosCentroTrabajo.numExtCtRef1 = respuesta.numExtCtRef1;
                	$rootScope.datosCentroTrabajo.numIntCtRef1 = respuesta.numIntCtRef1;
                	$rootScope.datosCentroTrabajo.refUbicaCtRef1 = respuesta.refUbicaCtRef1;
                	$rootScope.datosCentroTrabajo.claveColoCtRef1 = respuesta.claveColoCtRef1;
                	$rootScope.datosCentroTrabajo.codPostalCtRef1 = respuesta.codPostalCtRef1;
                	$rootScope.datosCentroTrabajo.telefonoCtRef1 = respuesta.telefonoCtRef1;
                	$rootScope.datosCentroTrabajo.telefonoPaisCtRef1 = respuesta.telefonoPaisCtRef1;
                	$rootScope.datosCentroTrabajo.telefonoAreaCtRef1 = respuesta.telefonoAreaCtRef1;
                	$rootScope.datosCentroTrabajo.telefonoExtCtRef1 = respuesta.telefonoExtCtRef1;
                	$rootScope.datosCentroTrabajo.emailCtRef1 = respuesta.emailCtRef1;
                	$rootScope.datosCentroTrabajo.nomCalleCtRef2 = respuesta.nomCalleCtRef2;
                	$rootScope.datosCentroTrabajo.numExtCtRef2 = respuesta.numExtCtRef2;
                	$rootScope.datosCentroTrabajo.numIntCtRef2 = respuesta.numIntCtRef2;
                	$rootScope.datosCentroTrabajo.refUbicaCtRef2 = respuesta.refUbicaCtRef2;
                	$rootScope.datosCentroTrabajo.claveColoCtRef2 = respuesta.claveColoCtRef2;
                	$rootScope.datosCentroTrabajo.codPostalCtRef2 = respuesta.codPostalCtRef2;
                	$rootScope.datosCentroTrabajo.telefonoCtRef2 = respuesta.telefonoCtRef2;
                	$rootScope.datosCentroTrabajo.telefonoPaisCtRef2 = respuesta.telefonoPaisCtRef2;
                	$rootScope.datosCentroTrabajo.telefonoAreaCtRef2 = respuesta.telefonoAreaCtRef2;
                	$rootScope.datosCentroTrabajo.telefonoExtCtRef2 = respuesta.telefonoExtCtRef2;
                	$rootScope.datosCentroTrabajo.emailCtRef2 = respuesta.emailCtRef2;
                	$rootScope.datosCentroTrabajo.idCanal = respuesta.idCanal;
                	$rootScope.datosCentroTrabajo.enviado = respuesta.enviado;
                	$rootScope.datosCentroTrabajo.sucursalCt = respuesta.sucursalCt;
                	$rootScope.datosCentroTrabajo.identificadorTramitante = respuesta.identificadorTramitante;
                	$rootScope.datosCentroTrabajo.archElectrCt = respuesta.archElectrCt;
                	$rootScope.datosCentroTrabajo.nombreEstado = respuesta.nombreEstado;
                	$rootScope.datosCentroTrabajo.nombreMunicipio = respuesta.nombreMunicipio;
                	$rootScope.datosCentroTrabajo.nombreColonia = respuesta.nombreColonia;
                	$rootScope.datosCentroTrabajo.tipoDireccion = respuesta.tipoDireccion;
                	$rootScope.datosCentroTrabajo.nombreCompleto = respuesta.nombreCompleto;
                	$rootScope.datosCentroTrabajo.telefono2Cony = respuesta.telefono2Cony;
                	$rootScope.datosCentroTrabajo.telefono2PaisCony = respuesta.telefono2PaisCony;
                	$rootScope.datosCentroTrabajo.telefono2AreaCony = respuesta.telefono2AreaCony;
                	$rootScope.datosCentroTrabajo.telefono2ExtCony = respuesta.telefono2ExtCony;
                	$rootScope.datosCentroTrabajo.telCelNumCony = respuesta.telCelNumCony;
                	$rootScope.datosCentroTrabajo.lugarNacimiento = respuesta.lugarNacimiento;
                	$rootScope.datosCentroTrabajo.nacionalidad = respuesta.nacionalidad;
                	$rootScope.datosCentroTrabajo.nivelEstudios = respuesta.nivelEstudios;
                	$rootScope.datosCentroTrabajo.vigencia = respuesta.vigencia;
                	$rootScope.datosCentroTrabajo.bancoId = respuesta.bancoId;
                	$rootScope.datosCentroTrabajo.capitalAutorizado = respuesta.capitalAutorizado;
                	$rootScope.datosCentroTrabajo.comisionApertura = respuesta.comisionApertura;
                	$rootScope.datosCentroTrabajo.ctaClabe = respuesta.ctaClabe;
                	$rootScope.datosCentroTrabajo.nivelDescuento = respuesta.nivelDescuento;
                	$rootScope.datosCentroTrabajo.pagoMensual = respuesta.pagoMensual;
                	$rootScope.datosCentroTrabajo.plazoMeses = respuesta.plazoMeses;
                	$rootScope.datosCentroTrabajo.plazoIntInicAut = respuesta.plazoIntInicAut;
                	$rootScope.datosCentroTrabajo.tasaInteres = respuesta.tasaInteres;
                	$rootScope.datosCentroTrabajo.totalCredito = respuesta.totalCredito;
                	$rootScope.datosCentroTrabajo.totalInteres = respuesta.totalInteres;
                	$rootScope.datosCentroTrabajo.capDescMenSol = respuesta.capDescMenSol;
                	$rootScope.datosCentroTrabajo.idConsulta = respuesta.idConsulta;
                	$rootScope.datosCentroTrabajo.plazo = respuesta.plazo;
                	$rootScope.datosCentroTrabajo.sueldoBrutoBase = respuesta.sueldoBrutoBase;
                	$rootScope.datosCentroTrabajo.tarjeta = respuesta.tarjeta;
                	$rootScope.datosCentroTrabajo.idAseguradora = respuesta.idAseguradora;
                	$rootScope.datosCentroTrabajo.montoCreditoCalculado = respuesta.montoCreditoCalculado;
                	$rootScope.datosCentroTrabajo.simulacionAplicada = respuesta.simulacionAplicada;
                	$rootScope.datosCentroTrabajo.primaSeguro = respuesta.primaSeguro;
                	$rootScope.datosCentroTrabajo.factorPrima = respuesta.factorPrima;
                	$rootScope.datosCentroTrabajo.plazoSimulacion = respuesta.plazoSimulacion;
                	$rootScope.datosCentroTrabajo.pagoMensualSimulacion = respuesta.pagoMensualSimulacion;
                	$rootScope.datosCentroTrabajo.clavePromotoria = respuesta.clavePromotoria;
                	$rootScope.datosCentroTrabajo.clavePromotor = respuesta.clavePromotor;
                	$rootScope.datosCentroTrabajo.nombreRef3 = respuesta.nombreRef3;
                	$rootScope.datosCentroTrabajo.telefonoRef3 = respuesta.telefonoRef3;
                	$rootScope.datosCentroTrabajo.telefonoAreaRef3 = respuesta.telefonoAreaRef3;
                	
                	
                	
//                	$scope.estados = respuesta.estados;

					/*AQUI INVOCAR A LA FUNCION QUE OBTENDRA LOS DATOS QUE DEVUELVA GERARDO*/
//					DispacherService.precargaForm($scope);
                }
            }
        );
    };
}]);