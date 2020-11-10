package com.puebla.monitoralertas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.puebla.monitoralertas.dto.ListaDatosVehiculosRegistradosDTO;
import com.puebla.monitoralertas.dto.RespuestaJSON;
import com.puebla.monitoralertas.entity.DatosVehiculoEntity;
import com.puebla.monitoralertas.service.DatosVehiculoService;

@RestController
public class VehicleController {

	@Autowired
	private DatosVehiculoService datosVehiculoService;

	@PostMapping("/cargaDatosVehiculos")
	public @ResponseBody ListaDatosVehiculosRegistradosDTO cargaDatosVehiculos() {

		ListaDatosVehiculosRegistradosDTO listaVehiculosRegistrados = new ListaDatosVehiculosRegistradosDTO();
		
		List<DatosVehiculoEntity> datosVehiculos = datosVehiculoService.obtenerDatosVehiculos();
		listaVehiculosRegistrados.setListaVehiculosRegistrados(datosVehiculos);

		return listaVehiculosRegistrados;
	}

	@PostMapping("/guardarDatosVehiculo")
	public @ResponseBody RespuestaJSON guardarDatosVehiculo(@RequestBody DatosVehiculoEntity datosVehiculo) {
		datosVehiculo.setEstatus("DATOS_COMPLETOS");
		String estatusMsg = datosVehiculoService.guardaDatosVehiculo(datosVehiculo);
		RespuestaJSON response = new RespuestaJSON();
		response.setDescripcionCode(estatusMsg);
		return response;
	}
	
	@PostMapping("/eliminarVehiculo")
	public @ResponseBody RespuestaJSON eliminarVehiculo(@RequestBody String iddispositivo) {
		RespuestaJSON response = new RespuestaJSON();
		datosVehiculoService.eliminarVehiculo(iddispositivo);
		response.setDescripcionCode("Vehiculo eliminado en semovi");
		return response;
	}

}
