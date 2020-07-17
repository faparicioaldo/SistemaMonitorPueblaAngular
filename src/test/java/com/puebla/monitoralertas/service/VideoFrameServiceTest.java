package com.puebla.monitoralertas.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VideoFrameServiceTest {

	@Autowired
	private VideoFrameService videoFrameService;

	/**
	 * Valida armado de URL donde se muestra cuatro video en una sola ventana en monitor de alertas
	 * */
	@Test
	public void serviceGetUrlLiveVideoUriTest() {		
		String vehicleId = "1";
		
		String url = videoFrameService.getUrlFrameLiveVideo(vehicleId);
		System.out.println("TEST > URL CEIBA2 SERVICE GET LIVE VIDEO");
		System.out.println("TEST > URL: " + url);
		assertEquals("http://3.14.24.174:8084/video/"+vehicleId, url);

	}

}
