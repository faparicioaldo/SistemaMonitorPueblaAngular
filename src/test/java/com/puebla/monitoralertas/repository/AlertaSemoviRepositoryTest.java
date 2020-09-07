package com.puebla.monitoralertas.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.puebla.monitoralertas.entity.AlertaSemoviEntity;

import lombok.extern.log4j.Log4j2;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Log4j2
public class AlertaSemoviRepositoryTest {

	@Autowired
	private AlertaSemoviRepository alertaSemoviRepository;
	
	@Test
	@Ignore
	@Rollback(true)
	public void test() {
		AlertaSemoviEntity entity = new AlertaSemoviEntity();
		List<AlertaSemoviEntity> entitySave = null;
		
		String idDispositivo = "id_de_prueba";
		entity.setIddispositivo(idDispositivo);
		
		alertaSemoviRepository.save(entity);
				
		entitySave = alertaSemoviRepository.findByIddispositivo(idDispositivo);
		
		assertEquals(entitySave.get(0).getIddispositivo(), entity.getIddispositivo());
	}
	
	@Test
	@Ignore
	public void consultaAlertasEnviadasSemoviTest() {
		
		try {
			List<Object[]> alertasEnviadas = alertaSemoviRepository.consultaAlertasEnviadasSemovi();
			
			assertNotNull(alertasEnviadas);
			System.out.println("ALERTA ENVIADA: "+alertasEnviadas.get(0)[0]);
			System.out.println("ALERTA ENVIADA: "+alertasEnviadas.get(0)[1]);
			System.out.println("ALERTA ENVIADA: "+alertasEnviadas.get(0)[2]);
			System.out.println("ALERTA ENVIADA: "+alertasEnviadas.get(0)[3]);
			System.out.println("ALERTA ENVIADA: "+alertasEnviadas.get(0)[4]);
		}catch(Exception e) {
			log.error("Ocuarrio un probklema: ", e);
		}

	}

	@Test
//	@Ignore
	@Rollback(false)
	public void updateAlarmaEstatusByAlarmaidTest() {
		
		try {
			Integer alarmid = 1;
			String newStatus = "ESTATUS_PRUEBA";
			
			alertaSemoviRepository.updateAlarmaEstatusByAlarmaid(alarmid, newStatus, "descripcion mensaje");
			List<String> result = alertaSemoviRepository.consultaSemoviEstatusByCeibaAlarmid(alarmid);
			
			assertEquals(newStatus, result.get(0));
			
		}catch(Exception e) {
			log.error("Ocuarrio un probklema: ", e);
			fail();
		}

	}

}
