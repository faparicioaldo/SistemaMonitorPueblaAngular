package com.puebla.monitoralertas.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CeibaVehiculoServiceTest {

	@Autowired
	private CeibaVehiculoService ceibaVehiculoService;

	@Test
	public void actualizarVehiculosCeibaInMonitorTest() {
		try {
			ceibaVehiculoService.actualizarVehiculosCeibaInMonitor();
		}catch(Exception e) {
			fail("Ocurrio un problema al actualizar vehiculos ceiba en base de monitor");
		}
	}

}
