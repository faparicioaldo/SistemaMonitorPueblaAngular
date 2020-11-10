package com.puebla.monitoralertas.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.puebla.monitoralertas.service.CeibaVehiculoService;
import com.puebla.monitoralertas.service.EnviarAlarmaGobiernoService;
import com.puebla.monitoralertas.service.EnviarGPSGobiernoService;

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
	private EnviarGPSGobiernoService enviarGpsSemovi;

	@Autowired
	private CeibaVehiculoService ceibaVehiculoService;
	
	/**
	 * Proceso automatico para enviar gps's a webmaps.
	 * 
	 * */
	@Scheduled(fixedRate = 5000)
	public void scheduleTaskGpsWithFixedRate() {
		log.info("------------------------------------------");
		log.info("SCHEDULER 1: Enviar GPS's (los que cambiaron) a WebMaps");
		log.info("------------------------------------------");
		enviarGpsSemovi.enviarGPSs();
	}

	/**
	 * Proceso automatico para sincronizar alertas con CEIBA
	 * 
	 * */
	@Scheduled(fixedRate = 7000)
	public void scheduleTaskAlertsWithFixedRate() {
		log.info("------------------------------------------");
		log.info("SCHEDULER 2: Sincronizar alertas con CEIBA");
		log.info("------------------------------------------");
		enviarAlarmasSemovi.actualizarConAlertasCeiba();
	}

	/**
	 * Proceso automatico para sincronizar con nuevos vehiculos registrados en CEIBA cada 4 horas a partir de la media noche.
	 * 
	 * */
	@Scheduled(cron = "0 0 0/4 ? * * *")
	public void scheduleTaskVehicleWithCronExpression() {
		log.info("------------------------------------------------------------------");
		log.info("SCHEDULER 3: Sincronizar vehiculos con CEIBA");
		log.info("------------------------------------------------------------------");
		ceibaVehiculoService.actualizarVehiculosCeibaInMonitor();				
	}
}