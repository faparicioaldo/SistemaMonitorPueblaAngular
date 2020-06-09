package com.stv.quartzdemo.config;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
//@SpringBootTest(properties = "spring.main.web-application-type=reactive")
public class CargaCeiba2ServerPropertiesTest {

	@Autowired
	private CargaCeiba2ServerProperties ceiba2ServerProperties;

	@Test
	public void test() {
	
		System.err.println("Prueba de carga de properties: ceiba2server.properties");
		System.err.println("service key: " + ceiba2ServerProperties.getKey());
		System.err.println("service devices: " + ceiba2ServerProperties.getDevices());
		System.err.println("service video" + ceiba2ServerProperties.getLiveVideoUrl());

		assertNotNull(ceiba2ServerProperties.getKey());
		assertNotNull(ceiba2ServerProperties.getDevices());
		assertNotNull(ceiba2ServerProperties.getLiveVideoUrl());

		assertNotEquals("",ceiba2ServerProperties.getKey().trim());
		assertNotEquals("",ceiba2ServerProperties.getDevices().trim());
		assertNotEquals("",ceiba2ServerProperties.getLiveVideoUrl().trim());

	}

}
