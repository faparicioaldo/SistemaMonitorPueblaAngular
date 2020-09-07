package com.puebla.monitoralertas.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.puebla.monitoralertas.dto.DatosAlertaEnviadasDTO;
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
		List<DatosAlertaEnviadasDTO> alertasEnviadas = new ArrayList<>(); 
		
		List<Object[]> alarmas = alertaSemoviRepository.consultaAlertasEnviadasSemovi();
		
		for(Object[] alarma : alarmas) {
			DatosAlertaEnviadasDTO datosAlerta=new DatosAlertaEnviadasDTO();
			datosAlerta.setIdAlerta(alarma[0] + "");
			datosAlerta.setCeibaAlarmid(alarma[1] + "");
			datosAlerta.setIdDispositivo(alarma[2] + "");
			datosAlerta.setPlate(alarma[3] + "");
			datosAlerta.setEco(alarma[4] + "");
			datosAlerta.setCeibaGpsTime(alarma[5] + "");
			datosAlerta.setSemoviEstatus(alarma[6] + "");
			datosAlerta.setEmpresa(alarma[7] + "");
			datosAlerta.setRoute(alarma[8] + "");
			datosAlerta.setSemoviMensaje(alarma[9]+"");
			datosAlerta.setCeibaType(alarma[10]+"");
			
			alertasEnviadas.add(datosAlerta);
		}

		listaAlertasEnviadas.setListaAlertasEnviadasSemovi(alertasEnviadas);
		
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
