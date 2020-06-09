package com.stv.quartzdemo.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stv.quartzdemo.dto.SemoviRequestDTO;
import com.stv.quartzdemo.dto.SemoviResponseDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
//@SpringBootTest(properties = "spring.main.web-application-type=reactive")
public class ClienteSemoviPueblaTest {

	@Autowired
	private ClienteSemoviPuebla clienteSemovi;

	@Test
	public void testNOK() {
		SemoviRequestDTO datos = new SemoviRequestDTO();

		SemoviResponseDTO response = clienteSemovi.enviarMensajeSemovi(datos);

		assertNotNull(response);

		System.err.println("Estatus: " + response.getStatus());
		System.err.println("Mensaje: " + response.getMsg());

		assertEquals("false", response.getStatus());
		assertEquals("Formato de IMEI incorrecto.", response.getMsg());
	}
	
	@Test
	public void testOK() {
		SemoviRequestDTO datos = new SemoviRequestDTO();

		datos.setImei("511760273476623");
		datos.setLongitude("-98.123456");
		datos.setLatitude("18.123456");
		datos.setAddress("Prueba");
		datos.setSpeed("80");
		datos.setCourse("88");
		datos.setDate("2020-11-10");
		datos.setIgnition("1");
		datos.setFix("true");
		datos.setSat("10");
		datos.setPlate("MTV9899");
		datos.setVin("WVWML93C99E535315");
		datos.setEngine("WVWML93C99E535315");
		datos.setYear("2012");
		datos.setColor("Blanco");
		datos.setRoute("Ruta 99");
		datos.setRs("Autotransportes Puebla S.A. de C.V.");
		datos.setEco("82");
		datos.setBranch("Volkswagen");
		datos.setSubbranch("Volksbus");
		datos.setUrl_camera("http:/camaras");
		datos.setPanic_button("false");

		SemoviResponseDTO response = clienteSemovi.enviarMensajeSemovi(datos);

		assertNotNull(response);

		System.err.println("Estatus: " + response.getStatus());
		System.err.println("Mensaje: " + response.getMsg());

		assertEquals("true", response.getStatus());
		assertEquals("OK", response.getMsg());
	}

}
