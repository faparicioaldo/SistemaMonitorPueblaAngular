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
import com.puebla.monitoralertas.dto.EnviarAlarmaDTO;
import com.puebla.monitoralertas.dto.ListaDatosAlertaEnviadasSemoviDTO;
import com.puebla.monitoralertas.repository.AlarmaRepository;
import com.puebla.monitoralertas.repository.AlertaSemoviRepository;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class MonitorAlarmasController {
	
	@Autowired
	private AlarmaRepository alarmaRepository;

	@Autowired
	private AlertaSemoviRepository alertaSemoviRepository;	
	
	@RequestMapping(value = "/cargaAlertasEnviadasSemovi", method = RequestMethod.POST)
	public @ResponseBody ListaDatosAlertaEnviadasSemoviDTO cargaAlertasEnviadasSemovi() {

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
