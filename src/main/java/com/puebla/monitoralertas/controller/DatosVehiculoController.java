package com.puebla.monitoralertas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.puebla.monitoralertas.dto.ListaDatosVehiculosRegistradosDTO;
import com.puebla.monitoralertas.dto.RespuestaJSON;
import com.puebla.monitoralertas.entity.AlarmaEntity;
import com.puebla.monitoralertas.entity.DatosVehiculoEntity;
import com.puebla.monitoralertas.repository.AlarmaRepository;
import com.puebla.monitoralertas.service.DatosVehiculoService;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class DatosVehiculoController {

	@Autowired
	private DatosVehiculoService datosVehiculoService;

	@Autowired
	AlarmaRepository alarmaRepository;

	@RequestMapping(value = "/cargaDatosVehiculos", method = RequestMethod.POST)
	public @ResponseBody ListaDatosVehiculosRegistradosDTO cargaDatosVehiculos() {

		ListaDatosVehiculosRegistradosDTO listaVehiculosRegistrados = new ListaDatosVehiculosRegistradosDTO();
		
		List<DatosVehiculoEntity> datosVehiculos = datosVehiculoService.obtenerDatosVehiculos();
		listaVehiculosRegistrados.setListaVehiculosRegistrados(datosVehiculos);

		return listaVehiculosRegistrados;
	}

	@RequestMapping(value = "/agregarVehiculo/{idDispositivo}", method = RequestMethod.GET)
	public String agregarVehiculo(@PathVariable String idDispositivo, Model model) {
		DatosVehiculoEntity datosVehiculo = null;

		if (!idDispositivo.equals("1"))
			datosVehiculo = datosVehiculoService.obtenerDatosVehiculo(idDispositivo);

		if (datosVehiculo == null)
			model.addAttribute("datosVehiculo", new DatosVehiculoEntity());
		else
			model.addAttribute("datosVehiculo", datosVehiculo);

		return "agregarVehiculo";
	}

	@RequestMapping(value = "/agregarDatosAlarma/{alarmaid}/{deviceid}", method = RequestMethod.GET)
	public String agregarDatosAlarma(@PathVariable Integer alarmaid, @PathVariable String deviceid, Model model) {
		AlarmaEntity datosAlarma = null;

		datosAlarma = alarmaRepository.findById(alarmaid).get();

		if (datosAlarma == null)
			model.addAttribute("datosAlarma", new DatosVehiculoEntity());
		else
			model.addAttribute("datosAlarma", datosAlarma);

		return "agregarDatosAlarma";
	}

	@RequestMapping(value = "/guardarDatosVehiculo", method = RequestMethod.POST)
	public @ResponseBody RespuestaJSON guardarDatosVehiculo(@RequestBody DatosVehiculoEntity datosVehiculo) {
		datosVehiculoService.guardaDatosVehiculo(datosVehiculo);
		return new RespuestaJSON();
	}

	@RequestMapping(value = "/guardarDatosAlarma", method = RequestMethod.POST)
	public String guardarDatosAlarma(@ModelAttribute("datosAlarma") AlarmaEntity datosAlarma) {
		alarmaRepository.updateAlarmaDatos(datosAlarma.getAlarmaid(), datosAlarma.getPlaca(), datosAlarma.getImei(), datosAlarma.getIpdispositivo(), datosAlarma.getRuta(), datosAlarma.getEmpresa(),datosAlarma.getEconomico());
		return "agregarDatosAlarma";

	}

}
