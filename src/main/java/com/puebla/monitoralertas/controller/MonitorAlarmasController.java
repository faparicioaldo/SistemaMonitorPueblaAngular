package com.puebla.monitoralertas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.puebla.monitoralertas.dto.EnviarAlarmaDTO;
import com.puebla.monitoralertas.entity.AlarmaEntity;
import com.puebla.monitoralertas.repository.AlarmaRepository;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class MonitorAlarmasController {
	
	@Autowired
	private AlarmaRepository alarmaRepository;
	
	@RequestMapping("/monitorAlarmas")
	public String monitorAlarmas(String name, Model model) {

		List<AlarmaEntity> alarmas = alarmaRepository.findTop15ByOrderByAlarmaidDesc();
		model.addAttribute("alarmas", alarmas);

		return "monitorAlarmas";
	}

	@PostMapping(
			value = "/descartarAlarma"
            , produces = {"application/json", "application/xml"}
            ,  consumes = {"application/json", "application/xml"}
)
	public @ResponseBody EnviarAlarmaDTO descartarAlarma(@RequestBody EnviarAlarmaDTO idAlarma) {

		log.info("DESCARTAR ALARMA: " + idAlarma.getIdAlarma());
		alarmaRepository.updateAlarmaEstatusByAlarmaid(idAlarma.getIdAlarma(),"Descartada");

		return idAlarma;
	}

}
