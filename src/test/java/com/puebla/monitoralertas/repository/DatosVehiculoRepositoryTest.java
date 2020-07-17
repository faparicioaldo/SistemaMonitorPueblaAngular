package com.puebla.monitoralertas.repository;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.puebla.monitoralertas.entity.DatosVehiculoEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DatosVehiculoRepositoryTest {

	@Autowired
	private DatosVehiculoRepository datosVehiculoRepository;
	
	@Test
	public void getAllDatosVehiculoTest() {

		List<DatosVehiculoEntity> vehiculos = datosVehiculoRepository.findAll();
		assertNotNull(vehiculos);
	}

}
