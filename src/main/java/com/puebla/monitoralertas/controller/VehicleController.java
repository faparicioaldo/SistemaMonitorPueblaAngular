package com.puebla.monitoralertas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.puebla.monitoralertas.dto.ListaDatosVehiculosRegistradosDTO;
import com.puebla.monitoralertas.dto.RespuestaJSON;
import com.puebla.monitoralertas.entity.DatosVehiculoEntity;
import com.puebla.monitoralertas.service.DatosVehiculoService;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class VehicleController {

	@Autowired
	private DatosVehiculoService datosVehiculoService;

	@RequestMapping(value = "/cargaDatosVehiculos", method = RequestMethod.POST)
	public @ResponseBody ListaDatosVehiculosRegistradosDTO cargaDatosVehiculos() {

		ListaDatosVehiculosRegistradosDTO listaVehiculosRegistrados = new ListaDatosVehiculosRegistradosDTO();
		
		List<DatosVehiculoEntity> datosVehiculos = datosVehiculoService.obtenerDatosVehiculos();
		listaVehiculosRegistrados.setListaVehiculosRegistrados(datosVehiculos);

		return listaVehiculosRegistrados;
	}

	@RequestMapping(value = "/guardarDatosVehiculo", method = RequestMethod.POST)
	public @ResponseBody RespuestaJSON guardarDatosVehiculo(@RequestBody DatosVehiculoEntity datosVehiculo) {
		datosVehiculo.setEstatus("DATOS_COMPLETOS");
		datosVehiculoService.guardaDatosVehiculo(datosVehiculo);
		return new RespuestaJSON();
	}

}
