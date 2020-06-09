package com.stv.quartzdemo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stv.quartzdemo.dto.EnviarFolioAlarmaDTO;
import com.stv.quartzdemo.entity.FolioAlarmaEntity;
import com.stv.quartzdemo.repository.FolioAlarmaRepository;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class MonitorFoliosAlarmasController {

	@Autowired
	FolioAlarmaRepository folioAlarmaRepository;

	@RequestMapping("/monitorFoliosAlarmas")
	public String monitorFoliosAlarmas(String name, Model model) {

		List<FolioAlarmaEntity> folios = folioAlarmaRepository.findTop15ByOrderByFolioidDesc();
		model.addAttribute("folios", folios);

		return "monitorFoliosAlarmas";
	}

	@PostMapping(
			value = "/enviarFolioAlarma"
            , produces = {"application/json", "application/xml"}
            ,  consumes = {"application/json", "application/xml"}
			)
	public @ResponseBody EnviarFolioAlarmaDTO enviarFolioAlarma(@RequestBody EnviarFolioAlarmaDTO folioAlarmaID) {

		log.error("ENVIAR FIOLIO DE ALARMA: " + folioAlarmaID.getIdFolioAlarma());
		int numRegistrosActualizados = folioAlarmaRepository.updateFolioAlarmaEstatusFechaCierreByFolioid(folioAlarmaID.getIdFolioAlarma(),"Atendido", new Date());
		return folioAlarmaID;
	}

	@PostMapping(
			value = "/descartarFolioAlarma"
            , produces = {"application/json", "application/xml"}
            ,  consumes = {"application/json", "application/xml"}
)
	public @ResponseBody EnviarFolioAlarmaDTO descartarFolioAlarma(@RequestBody EnviarFolioAlarmaDTO folioAlarmaID) {

		log.error("DESCARTAR FOLIO ALARMA: " + folioAlarmaID.getIdFolioAlarma());
		int numRegistrosActualizados = folioAlarmaRepository.updateFolioAlarmaEstatusFechaCierreByFolioid(folioAlarmaID.getIdFolioAlarma(),"Cerrado",new Date());

		return folioAlarmaID;
	}

}
