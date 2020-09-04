package com.puebla.monitoralertas.helper;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.puebla.monitoralertas.json.pojo.Ceiba2DevicesPojo;
import com.puebla.monitoralertas.json.pojo.Ceiba2KeyPojo;
import com.puebla.monitoralertas.json.pojo.DataDevicePojo;
import com.puebla.monitoralertas.service.APISistemaVideoVigilanciaService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GeneraCadenaPeticionHelperTest {

	@Autowired
	private APISistemaVideoVigilanciaService apiCeiba2;

	@Test
	public void generaCadenaPeticionTest() {
		String key = null;
		
		/*
		 * Solicita key necesario para hacer peticiones a CEIBA 2
		 * */
		Ceiba2KeyPojo keyPojo = apiCeiba2.getCeibaToken();
		key = keyPojo.getData().getKey();
		
		/*
		 * Obtiene lista de vehiculos del CEIBA 2
		 * */
		Ceiba2DevicesPojo vehicles = apiCeiba2.getAllVehicles(key);
		List<String> terids = obtenVehicleList(vehicles);
	
//		generaCadenaPeticion.obtieneDatosNecesarios(terids, key);
//		List<SemoviRequestDTO> mensajesSemovi = generaCadenaPeticion.generaCadenasSemovi("true");
//
//		assertNotNull(mensajesSemovi);
//		assertTrue(mensajesSemovi.size()>0);
//		System.err.println("PRUEBA DE MENSAJES GPS PARA ENVIO A SEMOVI");
//		System.err.println("Numero de Mensajes: " + mensajesSemovi.size());
//		System.err.println("Primer Mensaje: " + mensajesSemovi.get(0).toString());
	}

	private List<String> obtenVehicleList(Ceiba2DevicesPojo vehicles) {
		List<String> deviceIdList = new ArrayList<>();
		for (DataDevicePojo vehicle : vehicles.getData()) {
			deviceIdList.add(vehicle.getDeviceid());
		}
		return deviceIdList;
	}
}
