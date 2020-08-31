app.controller("datosGeneralesTrabajadorCtrl", ['$scope', '$routeParams','FuncionesService', '$location', '$rootScope', function($scope, $routeParams, FuncionesService, $location, $rootScope) {
	$rootScope.numberTab = 0;
	
	$scope.init = function() {
		console.log('init generales');
		$rootScope.datosCentroTrabajo = {};
		$rootScope.datosDocumentos = {};
		cargaCombos();
		cargaDatosPreafiliacion($rootScope.preafiliacionSelecionada);
	};
	
	function cargaCombos() {
		console.log('Cargando combos....');		

        FuncionesService.POST("/PortalAnalistasAfiliacion/consultaEstados").then(
            function(respuesta) {
        		console.log('combos respuesta: ' + respuesta);		

                if (respuesta) {
                	$scope.estados = respuesta.estados;

					/*AQUI INVOCAR A LA FUNCION QUE OBTENDRA LOS DATOS QUE DEVUELVA GERARDO*/
//					DispacherService.precargaForm($scope);
                }
            }
        );
    };
	
	function cargaDatosPreafiliacion(idPreafiliacion) {
		console.log('Cargando datos preafiliacion: ' + idPreafiliacion);		

        FuncionesService.POST("/PortalAnalistasAfiliacion/consultaDatosPreafiliacion", idPreafiliacion).then(
            function(respuesta) {
				console.log("datos generales trabajdor respuesta: " + JSON.stringify(respuesta, null, '\t'));

                if (respuesta) {

                	$rootScope.datosDocumentos.rutaComprobanteDomicilio = respuesta.rutaComprobanteDomicilio;
                	$rootScope.datosDocumentos.rutaIdentificacionOficial = respuesta.rutaIdentificacionOficial;
                	$rootScope.datosDocumentos.rutaReciboNomina1 = respuesta.rutaReciboNomina1;
                	$rootScope.datosDocumentos.rutaReciboNomina2 = respuesta.rutaReciboNomina2;
                	$rootScope.datosDocumentos.rutaReciboNomina3 = respuesta.rutaReciboNomina3;
                	$rootScope.datosDocumentos.idSucursal = respuesta.idSucursal;                	

                	/*
                	 * DATOS GENERALES DEL TRABAJADOR
                	 * */
                	$rootScope.datosCentroTrabajo.apPaterno = respuesta.datosPreafiliacion.apPaterno;
                	$rootScope.datosCentroTrabajo.apMaterno = respuesta.datosPreafiliacion.apMaterno;
                	$rootScope.datosCentroTrabajo.nombre = respuesta.datosPreafiliacion.nombre;
                	$rootScope.datosCentroTrabajo.segundoNombre = respuesta.datosPreafiliacion.segundoNombre;
                	$rootScope.datosCentroTrabajo.fechaNacimiento = respuesta.datosPreafiliacion.fechaNacimiento;
                	$rootScope.datosCentroTrabajo.lugarNacimiento = respuesta.datosPreafiliacion.lugarNacimiento;
                	$rootScope.datosCentroTrabajo.nacionalidad = respuesta.datosPreafiliacion.nacionalidad;
                	$rootScope.datosCentroTrabajo.sexo = respuesta.datosPreafiliacion.sexo;
                	$rootScope.datosCentroTrabajo.rfc = respuesta.datosPreafiliacion.rfc;
                	$rootScope.datosCentroTrabajo.curp = respuesta.datosPreafiliacion.curp;
                	$rootScope.datosCentroTrabajo.tipoSeguridadSocial = respuesta.datosPreafiliacion.tipoSeguridadSocial;
                	$rootScope.datosCentroTrabajo.numeroImss = respuesta.datosPreafiliacion.numeroImss;
                	$rootScope.datosCentroTrabajo.tipoDocumento = respuesta.datosPreafiliacion.tipoDocumento;
                	$rootScope.datosCentroTrabajo.numDocumento = respuesta.datosPreafiliacion.numDocumento;

                	/*
                	 * DATOS LABORALES
                	 * */
                	$rootScope.datosCentroTrabajo.idClienteCt = respuesta.datosPreafiliacion.idClienteCt;
                	$rootScope.datosCentroTrabajo.fcIngresoCt = respuesta.datosPreafiliacion.fcIngresoCt;
                	$rootScope.datosCentroTrabajo.comisionista = respuesta.datosPreafiliacion.comisionista;
                	$rootScope.datosCentroTrabajo.numRegCt = respuesta.datosPreafiliacion.numRegCt;
                	$rootScope.datosCentroTrabajo.puestoTrabajador = respuesta.datosPreafiliacion.puestoTrabajador;
                	$rootScope.datosCentroTrabajo.salarioNeto = respuesta.datosPreafiliacion.salarioNeto;
                	$rootScope.datosCentroTrabajo.importeDescuen = respuesta.datosPreafiliacion.importeDescuen;
                	$rootScope.datosCentroTrabajo.importeSalario = respuesta.datosPreafiliacion.importeSalario;

                	/*
                	 * DOMICILIO DEL TRABAJADOR
                	 * */
                	$rootScope.datosCentroTrabajo.nomCalle = respuesta.datosPreafiliacion.nomCalle;
                	$rootScope.datosCentroTrabajo.numExt = respuesta.datosPreafiliacion.numExt;
                	$rootScope.datosCentroTrabajo.numInt = respuesta.datosPreafiliacion.numInt;
                	$rootScope.datosCentroTrabajo.refUbica = respuesta.datosPreafiliacion.refUbica;
                	$rootScope.datosCentroTrabajo.codPostal = respuesta.datosPreafiliacion.codPostal;
                	$rootScope.datosCentroTrabajo.telefonoArea = respuesta.datosPreafiliacion.telefonoArea;
                	$rootScope.datosCentroTrabajo.telefono = respuesta.datosPreafiliacion.telefono;
                	$rootScope.datosCentroTrabajo.telefonoArea2 = respuesta.datosPreafiliacion.telefonoArea2;
                	$rootScope.datosCentroTrabajo.telefono2 = respuesta.datosPreafiliacion.telefono2;
                	$rootScope.datosCentroTrabajo.telefonoExt2 = respuesta.datosPreafiliacion.telefonoExt2;
                	$rootScope.datosCentroTrabajo.telCelNum = respuesta.datosPreafiliacion.telCelNum;
                	$rootScope.datosCentroTrabajo.emailCliente = respuesta.datosPreafiliacion.emailCliente;

                	$rootScope.datosCentroTrabajo.codPostal = completeLeftZeroes($rootScope.datosCentroTrabajo.codPostal, 5);
                	
                	/*
                	 * OTROS DATOS
                	 * */
                	$rootScope.datosCentroTrabajo.estadoCivil = respuesta.datosPreafiliacion.estadoCivil;
                	$rootScope.datosCentroTrabajo.tipoVivienda = respuesta.datosPreafiliacion.tipoVivienda;
                	$rootScope.datosCentroTrabajo.aniosHabitada = respuesta.datosPreafiliacion.aniosHabitada;
                	$rootScope.datosCentroTrabajo.mesesHabitada = respuesta.datosPreafiliacion.mesesHabitada;
                	$rootScope.datosCentroTrabajo.numPersDepend = respuesta.datosPreafiliacion.numPersDepend;
                	$rootScope.datosCentroTrabajo.numPerTrabaj = respuesta.datosPreafiliacion.numPerTrabaj;
                	$rootScope.datosCentroTrabajo.nivelEstudios = respuesta.datosPreafiliacion.nivelEstudios;
                	$rootScope.datosCentroTrabajo.otrosIngresos = respuesta.datosPreafiliacion.otrosIngresos;
                	$rootScope.datosCentroTrabajo.montoOtrosIng = respuesta.datosPreafiliacion.montoOtrosIng;
                	$rootScope.datosCentroTrabajo.otrosCreditos = respuesta.datosPreafiliacion.otrosCreditos;
                	$rootScope.datosCentroTrabajo.comoSeEntero = respuesta.datosPreafiliacion.comoSeEntero;

                	/*
                	 * DATOS DEL CONYUGE
                	 * */
                	$rootScope.datosCentroTrabajo.nombreConyuge = respuesta.datosPreafiliacion.nombreConyuge;

                	/*
                	 * DATOS REFERENCIA 1
                	 * */
                	$rootScope.datosCentroTrabajo.parentescoRef1 = respuesta.datosPreafiliacion.parentescoRef1;
                	$rootScope.datosCentroTrabajo.nombreRef1 = respuesta.datosPreafiliacion.nombreRef1;
                	$rootScope.datosCentroTrabajo.telefonoCtRef1 = respuesta.datosPreafiliacion.telefonoCtRef1;
                	$rootScope.datosCentroTrabajo.telCelNumRef1 = respuesta.datosPreafiliacion.telCelNumRef1;

                	/*
                	 * DATOS REFERENCIA 2
                	 * */
                	$rootScope.datosCentroTrabajo.parentescoRef2 = respuesta.datosPreafiliacion.parentescoRef2;
                	$rootScope.datosCentroTrabajo.nombreRef2 = respuesta.datosPreafiliacion.nombreRef2;
                	$rootScope.datosCentroTrabajo.telefonoRef2 = respuesta.datosPreafiliacion.telefonoRef2;
                	$rootScope.datosCentroTrabajo.telCelNumRef2 = respuesta.datosPreafiliacion.telCelNumRef2;

                	/*
                	 * DATOS NO UTILIZADOS
                	 * */                	
                	$rootScope.datosCentroTrabajo.solicitudId = respuesta.datosPreafiliacion.solicitudId;            
                	$rootScope.datosCentroTrabajo.telefonoCtRef2 = respuesta.datosPreafiliacion.telefonoCtRef2;
                	$rootScope.datosCentroTrabajo.telefonoRef1 = respuesta.datosPreafiliacion.telefonoRef1;
                	$rootScope.datosCentroTrabajo.numIntRef1 = respuesta.datosPreafiliacion.numIntRef1;
                	$rootScope.datosCentroTrabajo.numIntRef2 = respuesta.datosPreafiliacion.numIntRef2;
                	$rootScope.datosCentroTrabajo.nivelDescuento = respuesta.datosPreafiliacion.nivelDescuento;
                	$rootScope.datosCentroTrabajo.emailRef1 = respuesta.datosPreafiliacion.emailRef1;
                	$rootScope.datosCentroTrabajo.emailRef2 = respuesta.datosPreafiliacion.emailRef2;
                	$rootScope.datosCentroTrabajo.fechaSolicitud = respuesta.datosPreafiliacion.fechaSolicitud;
                	$rootScope.datosCentroTrabajo.totalcredito = respuesta.datosPreafiliacion.totalcredito;
                	$rootScope.datosCentroTrabajo.totalinteres = respuesta.datosPreafiliacion.totalinteres;
                	$rootScope.datosCentroTrabajo.nomCtRef2 = respuesta.datosPreafiliacion.nomCtRef2;
                	$rootScope.datosCentroTrabajo.archElectrCt = respuesta.datosPreafiliacion.archElectrCt;
                	$rootScope.datosCentroTrabajo.bancoId = respuesta.datosPreafiliacion.bancoId;
                	$rootScope.datosCentroTrabajo.capDescMenSol = respuesta.datosPreafiliacion.capDescMenSol;
                	$rootScope.datosCentroTrabajo.capitalautorizado = respuesta.datosPreafiliacion.capitalautorizado;
                	$rootScope.datosCentroTrabajo.claveColoCony = respuesta.datosPreafiliacion.claveColoCony;
                	$rootScope.datosCentroTrabajo.claveColoCtRef1 = respuesta.datosPreafiliacion.claveColoCtRef1;
                	$rootScope.datosCentroTrabajo.claveColoCtRef2 = respuesta.datosPreafiliacion.claveColoCtRef2;
                	$rootScope.datosCentroTrabajo.claveColoRef1 = respuesta.datosPreafiliacion.claveColoRef1;
                	$rootScope.datosCentroTrabajo.claveColoRef2 = respuesta.datosPreafiliacion.claveColoRef2;
                	$rootScope.datosCentroTrabajo.claveColonia = respuesta.datosPreafiliacion.claveColonia;
                	$rootScope.datosCentroTrabajo.clavePromotor = respuesta.datosPreafiliacion.clavePromotor;
                	$rootScope.datosCentroTrabajo.clavePromotoria = respuesta.datosPreafiliacion.clavePromotoria;
                	$rootScope.datosCentroTrabajo.codPostalCony = respuesta.datosPreafiliacion.codPostalCony;
                	$rootScope.datosCentroTrabajo.codPostalCtRef1 = respuesta.datosPreafiliacion.codPostalCtRef1;
                	$rootScope.datosCentroTrabajo.codPostalCtRef2 = respuesta.datosPreafiliacion.codPostalCtRef2;
                	$rootScope.datosCentroTrabajo.codPostalRef1 = respuesta.datosPreafiliacion.codPostalRef1;
                	$rootScope.datosCentroTrabajo.codPostalRef2 = respuesta.datosPreafiliacion.codPostalRef2;
                	$rootScope.datosCentroTrabajo.comisionapertura = respuesta.datosPreafiliacion.comisionapertura;
                	$rootScope.datosCentroTrabajo.ctaClabe = respuesta.datosPreafiliacion.ctaClabe;
                	$rootScope.datosCentroTrabajo.emailCony = respuesta.datosPreafiliacion.emailCony;
                	$rootScope.datosCentroTrabajo.emailCtRef1 = respuesta.datosPreafiliacion.emailCtRef1;
                	$rootScope.datosCentroTrabajo.emailCtRef2 = respuesta.datosPreafiliacion.emailCtRef2;
                	$rootScope.datosCentroTrabajo.enviado = respuesta.datosPreafiliacion.enviado;
                	$rootScope.datosCentroTrabajo.estadoAfiliacion = respuesta.datosPreafiliacion.estadoAfiliacion;
                	$rootScope.datosCentroTrabajo.factorPrima = respuesta.datosPreafiliacion.factorPrima;
                	$rootScope.datosCentroTrabajo.idAseguradora = respuesta.datosPreafiliacion.idAseguradora;
                	$rootScope.datosCentroTrabajo.idCanal = respuesta.datosPreafiliacion.idCanal;
                	$rootScope.datosCentroTrabajo.idConsulta = respuesta.datosPreafiliacion.idConsulta;
                	$rootScope.datosCentroTrabajo.identificadorTramitante = respuesta.datosPreafiliacion.identificadorTramitante;
                	$rootScope.datosCentroTrabajo.montoCreditoCalculado = respuesta.datosPreafiliacion.montoCreditoCalculado;
                	$rootScope.datosCentroTrabajo.nomCalleCony = respuesta.datosPreafiliacion.nomCalleCony;
                	$rootScope.datosCentroTrabajo.nomCalleCtRef1 = respuesta.datosPreafiliacion.nomCalleCtRef1;
                	$rootScope.datosCentroTrabajo.nomCalleCtRef2 = respuesta.datosPreafiliacion.nomCalleCtRef2;
                	$rootScope.datosCentroTrabajo.nomCalleRef1 = respuesta.datosPreafiliacion.nomCalleRef1;
                	$rootScope.datosCentroTrabajo.nomCalleRef2 = respuesta.datosPreafiliacion.nomCalleRef2;
                	$rootScope.datosCentroTrabajo.nomCtRef1 = respuesta.datosPreafiliacion.nomCtRef1;
                	$rootScope.datosCentroTrabajo.nomCtroTrabCony = respuesta.datosPreafiliacion.nomCtroTrabCony;
                	$rootScope.datosCentroTrabajo.nombreColonia = respuesta.datosPreafiliacion.nombreColonia;
                	$rootScope.datosCentroTrabajo.nombreCompleto = respuesta.datosPreafiliacion.nombreCompleto;
                	$rootScope.datosCentroTrabajo.nombreEstado = respuesta.datosPreafiliacion.nombreEstado;
                	$rootScope.datosCentroTrabajo.nombreMunicipio = respuesta.datosPreafiliacion.nombreMunicipio;
                	$rootScope.datosCentroTrabajo.nombreRef3 = respuesta.datosPreafiliacion.nombreRef3;
                	$rootScope.datosCentroTrabajo.numExtCony = respuesta.datosPreafiliacion.numExtCony;
                	$rootScope.datosCentroTrabajo.numExtCtRef1 = respuesta.datosPreafiliacion.numExtCtRef1;
                	$rootScope.datosCentroTrabajo.numExtCtRef2 = respuesta.datosPreafiliacion.numExtCtRef2;
                	$rootScope.datosCentroTrabajo.numExtRef1 = respuesta.datosPreafiliacion.numExtRef1;
                	$rootScope.datosCentroTrabajo.numExtRef2 = respuesta.datosPreafiliacion.numExtRef2;
                	$rootScope.datosCentroTrabajo.numIntCony = respuesta.datosPreafiliacion.numIntCony;
                	$rootScope.datosCentroTrabajo.numIntCtRef1 = respuesta.datosPreafiliacion.numIntCtRef1;
                	$rootScope.datosCentroTrabajo.numIntCtRef2 = respuesta.datosPreafiliacion.numIntCtRef2;
                	$rootScope.datosCentroTrabajo.pagomensual = respuesta.datosPreafiliacion.pagomensual;
                	$rootScope.datosCentroTrabajo.pagoMensualSimulacion = respuesta.datosPreafiliacion.pagoMensualSimulacion;
                	$rootScope.datosCentroTrabajo.plazo = respuesta.datosPreafiliacion.plazo;
                	$rootScope.datosCentroTrabajo.plazoIntInicAut = respuesta.datosPreafiliacion.plazoIntInicAut;
                	$rootScope.datosCentroTrabajo.plazomeses = respuesta.datosPreafiliacion.plazomeses;
                	$rootScope.datosCentroTrabajo.plazoSimulacion = respuesta.datosPreafiliacion.plazoSimulacion;
                	$rootScope.datosCentroTrabajo.primaSeguro = respuesta.datosPreafiliacion.primaSeguro;
                	$rootScope.datosCentroTrabajo.refUbicaCtRef1 = respuesta.datosPreafiliacion.refUbicaCtRef1;
                	$rootScope.datosCentroTrabajo.refUbicaCtRef2 = respuesta.datosPreafiliacion.refUbicaCtRef2;
                	$rootScope.datosCentroTrabajo.refUbicaRef1 = respuesta.datosPreafiliacion.refUbicaRef1;
                	$rootScope.datosCentroTrabajo.refUbicaRef2 = respuesta.datosPreafiliacion.refUbicaRef2;
                	$rootScope.datosCentroTrabajo.refUbicacCony = respuesta.datosPreafiliacion.refUbicacCony;
                	$rootScope.datosCentroTrabajo.salarioBase = respuesta.datosPreafiliacion.salarioBase;
                	$rootScope.datosCentroTrabajo.simulacionAplicada = respuesta.datosPreafiliacion.simulacionAplicada;
                	$rootScope.datosCentroTrabajo.sucursalCt = respuesta.datosPreafiliacion.sucursalCt;
                	$rootScope.datosCentroTrabajo.sueldoBrutoBase = respuesta.datosPreafiliacion.sueldoBrutoBase;
                	$rootScope.datosCentroTrabajo.tarjeta = respuesta.datosPreafiliacion.tarjeta;
                	$rootScope.datosCentroTrabajo.tasainteres = respuesta.datosPreafiliacion.tasainteres;
                	$rootScope.datosCentroTrabajo.telCelNumCony = respuesta.datosPreafiliacion.telCelNumCony;
                	$rootScope.datosCentroTrabajo.telefono2AreaCony = respuesta.datosPreafiliacion.telefono2AreaCony;
                	$rootScope.datosCentroTrabajo.telefono2Cony = respuesta.datosPreafiliacion.telefono2Cony;
                	$rootScope.datosCentroTrabajo.telefono2ExtCony = respuesta.datosPreafiliacion.telefono2ExtCony;
                	$rootScope.datosCentroTrabajo.telefono2PaisCony = respuesta.datosPreafiliacion.telefono2PaisCony;
                	$rootScope.datosCentroTrabajo.telefonoAreaCony = respuesta.datosPreafiliacion.telefonoAreaCony;
                	$rootScope.datosCentroTrabajo.telefonoAreaCtRef1 = respuesta.datosPreafiliacion.telefonoAreaCtRef1;
                	$rootScope.datosCentroTrabajo.telefonoAreaCtRef2 = respuesta.datosPreafiliacion.telefonoAreaCtRef2;
                	$rootScope.datosCentroTrabajo.telefonoAreaRef1 = respuesta.datosPreafiliacion.telefonoAreaRef1;
                	$rootScope.datosCentroTrabajo.telefonoAreaRef2 = respuesta.datosPreafiliacion.telefonoAreaRef2;
                	$rootScope.datosCentroTrabajo.telefonoAreaRef3 = respuesta.datosPreafiliacion.telefonoAreaRef3;
                	$rootScope.datosCentroTrabajo.telefonoCony = respuesta.datosPreafiliacion.telefonoCony;
                	$rootScope.datosCentroTrabajo.telefonoExt = respuesta.datosPreafiliacion.telefonoExt;
                	$rootScope.datosCentroTrabajo.telefonoExtCony = respuesta.datosPreafiliacion.telefonoExtCony;
                	$rootScope.datosCentroTrabajo.telefonoExtCtRef1 = respuesta.datosPreafiliacion.telefonoExtCtRef1;
                	$rootScope.datosCentroTrabajo.telefonoExtCtRef2 = respuesta.datosPreafiliacion.telefonoExtCtRef2;
                	$rootScope.datosCentroTrabajo.telefonoExtRef1 = respuesta.datosPreafiliacion.telefonoExtRef1;
                	$rootScope.datosCentroTrabajo.telefonoExtRef2 = respuesta.datosPreafiliacion.telefonoExtRef2;
                	$rootScope.datosCentroTrabajo.telefonoPais = respuesta.datosPreafiliacion.telefonoPais;
                	$rootScope.datosCentroTrabajo.telefonoPais2 = respuesta.datosPreafiliacion.telefonoPais2;
                	$rootScope.datosCentroTrabajo.telefonoPaisCony = respuesta.datosPreafiliacion.telefonoPaisCony;
                	$rootScope.datosCentroTrabajo.telefonoPaisCtRef1 = respuesta.datosPreafiliacion.telefonoPaisCtRef1;
                	$rootScope.datosCentroTrabajo.telefonoPaisCtRef2 = respuesta.datosPreafiliacion.telefonoPaisCtRef2;
                	$rootScope.datosCentroTrabajo.telefonoPaisRef1 = respuesta.datosPreafiliacion.telefonoPaisRef1;
                	$rootScope.datosCentroTrabajo.telefonoPaisRef2 = respuesta.datosPreafiliacion.telefonoPaisRef2;
                	$rootScope.datosCentroTrabajo.telefonoRef3 = respuesta.datosPreafiliacion.telefonoRef3;
                	$rootScope.datosCentroTrabajo.tipoDireccion = respuesta.datosPreafiliacion.tipoDireccion;
                	$rootScope.datosCentroTrabajo.trabajaConyuge = respuesta.datosPreafiliacion.trabajaConyuge;
                	$rootScope.datosCentroTrabajo.trabajaRef1 = respuesta.datosPreafiliacion.trabajaRef1;
                	$rootScope.datosCentroTrabajo.trabajaRef2 = respuesta.datosPreafiliacion.trabajaRef2;
                	$rootScope.datosCentroTrabajo.tramita = respuesta.datosPreafiliacion.tramita;
                	$rootScope.datosCentroTrabajo.tzLock = respuesta.datosPreafiliacion.tzLock;
                	$rootScope.datosCentroTrabajo.userAlta = respuesta.datosPreafiliacion.userAlta;
                	$rootScope.datosCentroTrabajo.vigencia = respuesta.datosPreafiliacion.vigencia;

//                	$scope.estados = respuesta.estados;

					/*AQUI INVOCAR A LA FUNCION QUE OBTENDRA LOS DATOS QUE DEVUELVA GERARDO*/
//					DispacherService.precargaForm($scope);
                }
            }
        );
    };
    
}]);