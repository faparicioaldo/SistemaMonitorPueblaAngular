package com.stv.quartzdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.stv.quartzdemo.entity.AlarmaEntity;
import com.stv.quartzdemo.entity.DatosVehiculoEntity;
import com.stv.quartzdemo.repository.AlarmaRepository;
import com.stv.quartzdemo.repository.FolioAlarmaRepository;
import com.stv.quartzdemo.service.DatosVehiculoService;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class DatosVehiculoController {

	@Autowired
	private DatosVehiculoService datosVehiculoService;

	@Autowired
	FolioAlarmaRepository folioAlarmaRepository;

	@Autowired
	AlarmaRepository alarmaRepository;

	@RequestMapping("/datosVehiculos")
	public String monitorAlarmas(String name, Model model) {

		List<DatosVehiculoEntity> datosVehiculos = datosVehiculoService.obtenerDatosVehiculos();
		model.addAttribute("datosVehiculos", datosVehiculos);

		return "datosVehiculos";
	}

	@RequestMapping(value = "/agregarVehiculo/{idDispositivo}", method = RequestMethod.GET)
	public String agregarAlarma(@PathVariable String idDispositivo, Model model) {
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
	public String agregarVehiculo(@PathVariable Integer alarmaid, @PathVariable String deviceid, Model model) {
		AlarmaEntity datosAlarma = null;

		datosAlarma = alarmaRepository.findById(alarmaid).get();

		if (datosAlarma == null)
			model.addAttribute("datosAlarma", new DatosVehiculoEntity());
		else
			model.addAttribute("datosAlarma", datosAlarma);

		return "agregarDatosAlarma";
	}

	@RequestMapping(value = "/guardarDatosVehiculo", method = RequestMethod.POST)
	public String submit(@ModelAttribute("datosVehiculo") DatosVehiculoEntity datosVehiculo) {
		datosVehiculoService.guardaDatosVehiculo(datosVehiculo);
		return "datosVehiculos";
	}

	@RequestMapping(value = "/guardarDatosAlarma", method = RequestMethod.POST)
	public String guardarDatosAlarma(@ModelAttribute("datosAlarma") AlarmaEntity datosAlarma) {
		alarmaRepository.updateAlarmaDatos(datosAlarma.getAlarmaid(), datosAlarma.getPlaca(), datosAlarma.getImei(), datosAlarma.getIpdispositivo(), datosAlarma.getRuta(), datosAlarma.getEmpresa(),datosAlarma.getEconomico());
		return "agregarDatosAlarma";

	}

}
