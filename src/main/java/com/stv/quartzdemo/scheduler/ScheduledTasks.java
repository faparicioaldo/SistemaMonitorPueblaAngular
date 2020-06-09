package com.stv.quartzdemo.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.stv.quartzdemo.service.APISistemaVideoVigilanciaService;
import com.stv.quartzdemo.service.EnviarAlarmaGobiernoService;

/*
 * referencia
 * https://www.callicoder.com/spring-boot-task-scheduling-with-scheduled-annotation/
 * */

@Component
public class ScheduledTasks {

	@Autowired
	APISistemaVideoVigilanciaService apiSistemaVideoVigilanciaService;

	@Autowired
	private EnviarAlarmaGobiernoService enviarAlarmasSemovi;
	
	@Scheduled(fixedRate = 2000)
	public void scheduleTaskWithFixedRate() {
		
		enviarAlarmasSemovi.enviarAlarmaGobierno();
		
	}

	public void scheduleTaskWithFixedDelay() {
	}

	public void scheduleTaskWithInitialDelay() {
	}

	public void scheduleTaskWithCronExpression() {
	}
}