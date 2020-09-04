package com.puebla.monitoralertas.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.puebla.monitoralertas.config.GlobalSession;
import com.puebla.monitoralertas.service.CeibaVehiculoService;
import com.puebla.monitoralertas.service.EnviarAlarmaGobiernoService;

import lombok.extern.log4j.Log4j2;

/*
 * referencia
 * https://www.callicoder.com/spring-boot-task-scheduling-with-scheduled-annotation/
 * */

@Component
@Log4j2
public class ScheduledTasks {

	@Autowired
	private EnviarAlarmaGobiernoService enviarAlarmasSemovi;
	
	@Autowired
	private CeibaVehiculoService ceibaVehiculoService;
	
	@Autowired
	private GlobalSession session;

	/**
	 * Proceso automatico para enviar alertas y gps's a semovi.
	 * 
	 * @author Aldo Flores
	 * 
	 * */
	@Scheduled(fixedRate = 10000)
	public void scheduleTaskWithFixedRate() {
		log.info("------------------------------------------------------------------");
		log.info("INICIO: scheduler");
		log.info("------------------------------------------------------------------");
		
		//Consulta lista de vehiculos registrados en CEIBA2
//		enviarAlarmasSemovi.obtenerListaVehiculosCeiba2();
		ceibaVehiculoService.actualizarVehiculosCeibaInMonitor();
		
		//Cuando se cumplan N (CiclosContadorGPS definidos por variable ciclosContadorGPS en GlobalSession) 
		//se realiza en envio de mensajes (GPS) de todos los vehiculos en CEIBA2 y en Monitor de Alarmas a SEMOVI 
		session.setContadorEnviarGPSs(session.getContadorEnviarGPSs() + 1);
		if(session.getContadorEnviarGPSs().equals(session.getCiclosContadorGPS())) {
			session.setContadorEnviarGPSs(0);//Reinicia contador
			enviarAlarmasSemovi.enviarGPSs();
		}
		
		log.info("------------------------------------------------------------------");
		log.info("FIN: scheduler");
		log.info("------------------------------------------------------------------");
	}

	public void scheduleTaskWithFixedDelay() {
	}

	public void scheduleTaskWithInitialDelay() {
	}

	public void scheduleTaskWithCronExpression() {
	}
}