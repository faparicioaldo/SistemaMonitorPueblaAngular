package com.puebla.monitoralertas.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.puebla.monitoralertas.entity.DatosVehiculoEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DatosVehiculoRepositoryTest {

	@Autowired
	private DatosVehiculoRepository datosVehiculoRepository;
	
	@Test
	@Ignore
	public void getAllDatosVehiculoTest() {
		List<DatosVehiculoEntity> vehiculos = datosVehiculoRepository.findAll();
		assertNotNull(vehiculos);			
	}

	@Test
	@Transactional
	@Rollback(true)
	@Ignore
	public void insertTest() {

		String idDispositivo = "TEST_DISP_1";
		
		DatosVehiculoEntity newVehiculo = new DatosVehiculoEntity();
		newVehiculo.setIddispositivo(idDispositivo);
		DatosVehiculoEntity res = null;
		try {
			res = datosVehiculoRepository.save(newVehiculo);
			System.err.println(res.getIddispositivo());
		}catch(Exception e ){
			e.printStackTrace();
		}
		DatosVehiculoEntity result = datosVehiculoRepository.findById(idDispositivo).get();
		assertEquals(idDispositivo, result.getIddispositivo());
	}
	
//	@Test
//	public void vehicleAlertsRelationShipOneToMany() {
//		DatosVehiculoEntity vehiculos = datosVehiculoRepository.findById("007C008648").get();
//		vehiculos.getAlertas().get(0).getCeibaalarmid();
//		assertNotNull(vehiculos);
//	}

}
