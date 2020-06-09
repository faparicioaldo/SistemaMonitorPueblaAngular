package com.stv.quartzdemo.helper;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stv.quartzdemo.dto.SemoviRequestDTO;
import com.stv.quartzdemo.json.pojo.Ceiba2DevicesPojo;
import com.stv.quartzdemo.json.pojo.Ceiba2KeyPojo;
import com.stv.quartzdemo.json.pojo.DataDevicePojo;
import com.stv.quartzdemo.service.APISistemaVideoVigilanciaService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GeneraCadenaPeticionHelperTest {

	@Autowired
	GeneraCadenaPeticionHelper generaCadenaPeticion;

	@Autowired
	private APISistemaVideoVigilanciaService apiCeiba2;

	@Test
	public void generaCadenaPeticionTest() {
		String key = null;
		
		/*
		 * Solicita key necesario para hacer peticiones a CEIBA 2
		 * */
		Ceiba2KeyPojo keyPojo = apiCeiba2.login();
		key = keyPojo.getData().getKey();
		
		/*
		 * Obtiene lista de vehiculos del CEIBA 2
		 * */
		Ceiba2DevicesPojo vehicles = apiCeiba2.getAllVehicles(key);
		List<String> terids = obtenVehicleList(vehicles);
	
		List<SemoviRequestDTO> mensajesSemovi = generaCadenaPeticion.generaCadenaPeticion(terids, key, "false");
		assertNotNull(mensajesSemovi);
		assertTrue(mensajesSemovi.size()>0);
		System.err.println("PRUEBA DE MENSAJES GPS PARA ENVIO A SEMOVI");
		System.err.println("Numero de Mensajes: " + mensajesSemovi.size());
		System.err.println("Primer Mensaje: " + mensajesSemovi.get(0).toString());
	}

	private List<String> obtenVehicleList(Ceiba2DevicesPojo vehicles) {
		List<String> deviceIdList = new ArrayList<>();
		for (DataDevicePojo vehicle : vehicles.getData()) {
			deviceIdList.add(vehicle.getDeviceid());
		}
		return deviceIdList;
	}
}
