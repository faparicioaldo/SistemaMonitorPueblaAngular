package com.puebla.monitoralertas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
	
	@PostMapping("/getPannicButtonAlerts")
	public @ResponseBody ListaDatosAlertaEnviadasSemoviDTO getPannicButtonAlerts() {

		ListaDatosAlertaEnviadasSemoviDTO listaAlertasEnviadas = new ListaDatosAlertaEnviadasSemoviDTO();
		
		List<IDatosAlertaEnviadasDTO> alarmas = alertaSemoviRepository.consultaAlertasEnviadasSemovi();
		
		listaAlertasEnviadas.setListaAlertasEnviadasSemovi(alarmas);
		
		return listaAlertasEnviadas;
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
