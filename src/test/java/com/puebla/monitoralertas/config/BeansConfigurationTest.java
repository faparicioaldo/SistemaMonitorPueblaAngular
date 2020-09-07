package com.puebla.monitoralertas.config;

import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.puebla.monitoralertas.json.pojo.Ceiba2DevicesPojo;
import com.puebla.monitoralertas.json.pojo.Ceiba2KeyPojo;
import com.puebla.monitoralertas.service.APISistemaVideoVigilanciaService;

import lombok.extern.log4j.Log4j2;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
class BeansConfigurationTest {

	@Resource(name="ceiba2Key")
	private String ceiba2Key;
	
	@Autowired
	private GlobalSession session;
	
	@Autowired
	private APISistemaVideoVigilanciaService apiCeiba2;

	@Test
	@Before
	void Ceiba2KeyTest1() {
		log.info("Iniciando prueba 1...");
		Ceiba2DevicesPojo response = apiCeiba2.getAllVehicles(session.getKeyCeiba2());
		if(response.getErrorcode() != 200) {
			log.info("prueba 1: key invalido, solicitando nuevo...");
			Ceiba2KeyPojo ceiba2Token = apiCeiba2.getCeibaToken();
			session.setKeyCeiba2(ceiba2Token.getData().getKey());
		}else {
			log.info("prueba 1: key valido.");
		}
		log.info("Termina prueba 1");
	}

	@Test
	@After
	void Ceiba2KeyTest2() {
		
		log.info("Iniciando prueba 2...");
		Ceiba2DevicesPojo response = apiCeiba2.getAllVehicles(session.getKeyCeiba2());
		if(response.getErrorcode() != 200) {
			log.info("prueba 2: key invalido, solicitando nuevo...");
			fail("No se tomo la llave de la session");
		}else {
			log.info("prueba 2: key valido.");
		}
		log.info("Termina prueba 2");
	}

	
//	@Test
//	@Before
//	void Ceiba2KeyTest1() {
//		log.info("Iniciando prueba 1...");
//		Ceiba2DevicesPojo response = apiCeiba2.getAllVehicles(ceiba2Key);
//		if(response.getErrorcode() != 200) {
//			log.info("prueba 1: key invalido, solicitando nuevo...");
//			Ceiba2KeyPojo ceiba2Token = apiCeiba2.getCeibaToken();
//			ceiba2Key = ceiba2Token.getData().getKey();
//		}else {
//			log.info("prueba 1: key valido.");
//		}
//		log.info("Termina prueba 1");
//	}
//
//	@Test
//	@After
//	void Ceiba2KeyTest2() {
//		
//		log.info("Iniciando prueba 2...");
//		Ceiba2DevicesPojo response = apiCeiba2.getAllVehicles(ceiba2Key);
//		if(response.getErrorcode() != 200) {
//			log.info("prueba 2: key invalido, solicitando nuevo...");
//			fail("No se tomo la llave de la session");
//		}else {
//			log.info("prueba 2: key valido.");
//		}
//		log.info("Termina prueba 2");
//	}

}
