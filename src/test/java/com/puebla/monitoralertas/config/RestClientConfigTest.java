package com.puebla.monitoralertas.config;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestClientConfigTest {

	@Autowired
	private RestTemplate restTemplate; 

	/**
	 * Valida que el objeto restTamplate inyecte correctamente 
	 * lanza una peticion GET a lap pagina de google
	 * 
	 * */
	@Test
	public void peticionGoogleTest() {
		assertNotNull(restTemplate);		
		ResponseEntity<String> result = restTemplate.getForEntity("https://www.google.com/", String.class);
		assertNotNull(result);
	}

}
