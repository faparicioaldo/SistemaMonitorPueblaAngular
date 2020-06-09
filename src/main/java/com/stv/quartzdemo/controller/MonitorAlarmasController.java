package com.stv.quartzdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stv.quartzdemo.config.GlobanSession;
import com.stv.quartzdemo.dto.EnviarAlarmaDTO;
import com.stv.quartzdemo.entity.AlarmaEntity;
import com.stv.quartzdemo.repository.AlarmaRepository;
import com.stv.quartzdemo.repository.FolioAlarmaRepository;
import com.stv.quartzdemo.service.DatosVehiculoService;
import com.stv.quartzdemo.service.EnviarAlarmaGobiernoService;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class MonitorAlarmasController {

	@Autowired
	private GlobanSession session;
	
	@Autowired
	private EnviarAlarmaGobiernoService enviarAlarmaGobiernoService;
	
	@Autowired
	private AlarmaRepository alarmaRepository;

	@Autowired
	FolioAlarmaRepository folioAlarmaRepository;
	
	@Autowired
	private DatosVehiculoService datosVehiculoService;


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
		int numRegistrosActualizados = alarmaRepository.updateAlarmaEstatusByAlarmaid(idAlarma.getIdAlarma(),"Descartada");

		return idAlarma;
	}

}
