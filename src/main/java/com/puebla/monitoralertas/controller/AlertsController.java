package com.puebla.monitoralertas.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.puebla.monitoralertas.config.GlobalSession;
import com.puebla.monitoralertas.dto.IDatosAlertaEnviadasDTO;
import com.puebla.monitoralertas.dto.ListaDatosAlertaEnviadasSemoviDTO;
import com.puebla.monitoralertas.dto.RespuestaJSON;
import com.puebla.monitoralertas.dto.SemoviResponseDTO;
import com.puebla.monitoralertas.repository.AlertaSemoviRepository;
import com.puebla.monitoralertas.service.EnviarAlarmaGobiernoService;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class AlertsController {
	
	@Autowired
	private EnviarAlarmaGobiernoService enviarAlarmaGobiernoService; 
	
	@Autowired
	private AlertaSemoviRepository alertaSemoviRepository;	
	
	@Autowired
	private GlobalSession session;
	
	@PostMapping("/getPannicButtonAlerts")
	public @ResponseBody ListaDatosAlertaEnviadasSemoviDTO getPannicButtonAlerts() {

		ListaDatosAlertaEnviadasSemoviDTO listaAlertasEnviadas = new ListaDatosAlertaEnviadasSemoviDTO();
		
		List<IDatosAlertaEnviadasDTO> alarmas = alertaSemoviRepository.consultaAlertasEnviadasSemovi();
		
		listaAlertasEnviadas.setListaAlertasEnviadasSemovi(alarmas);
		listaAlertasEnviadas.setAlertasNoVistas(session.getAlertasNoVistas());
		
		return listaAlertasEnviadas;
	}

	@GetMapping("/addPannic/{idceibaalert}")
	public @ResponseBody String getPannicButtonAlerts(@PathVariable("idceibaalert") String idceibaalert) {

		String result = "error "; 
		try {
		session.getAlertasNoVistas().add(idceibaalert);
		result = "ok";
		}catch(Exception e) {
			result += (": " + e.getCause()); 
		}
		return result;
	}

	@GetMapping("/getPannic")
	public @ResponseBody Set<String> getPannicAlerts() {

		Set<String> result = null; 
		try {
			result = session.getAlertasNoVistas();
		}catch(Exception e) {
			log.error("eroorrrr", e);
		}
		return result;
	}

	@PostMapping("/markAlertAsSeen")
	public @ResponseBody RespuestaJSON markAlertAsSeen(@RequestBody String CeibaAlertId) {
		RespuestaJSON response = new RespuestaJSON();
		session.getAlertasNoVistas().remove(CeibaAlertId);
		log.info("Alert mark as seen: " + CeibaAlertId);
		return response;
	}	
	
	@PostMapping("/descartarAlarma")
	public @ResponseBody RespuestaJSON descartarAlarma(@RequestBody Integer idAlerta) {

		log.info("DESCARTAR ALARMA: " + idAlerta);
		RespuestaJSON response = new RespuestaJSON();
		
		enviarAlarmaGobiernoService.descartarAlertaSemovi(idAlerta);
		
		response.setRespuestaCode(200);
		response.setDescripcionCode("Se descarto alerta correctamente");
		
		return response;
	}

	@PostMapping("/enviarAlertaSemovi")
	public @ResponseBody RespuestaJSON enviarAlertaSemovi(@RequestBody Integer idAlerta) {

		log.info("DESCARTAR ALARMA: " + idAlerta);
		RespuestaJSON response = new RespuestaJSON();
		
		SemoviResponseDTO semoviResponse = enviarAlarmaGobiernoService.enviarAlertaSemovi(idAlerta);
		
		if(semoviResponse.getStatus() && semoviResponse.getMsg().equals("OK")) {
			response.setRespuestaCode(200);
			response.setDescripcionCode("El envio de la alerta "+ idAlerta +" a semovi exitoso");			
		}else{
			response.setRespuestaCode(500);
			response.setDescripcionCode("El envio de la alerta "+ idAlerta +" a semovi fallo, semovi dice: " + semoviResponse.getMsg());			
		}
		
		
		
		return response;
	}

}
