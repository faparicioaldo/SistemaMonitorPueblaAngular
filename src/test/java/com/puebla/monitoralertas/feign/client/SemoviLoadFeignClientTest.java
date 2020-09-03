package com.puebla.monitoralertas.feign.client;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.puebla.monitoralertas.dto.SemoviLoadRequestDTO;
import com.puebla.monitoralertas.dto.SemoviResponseDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SemoviLoadFeignClientTest {

	@Autowired
	private SemoviLoadFeignClient vehicleLoadFeignClient;
	
	@Autowired
    private ObjectMapper objectMapper;
	
	@Test
	public void loadVehicleTest() {
		
		SemoviLoadRequestDTO newVehicle = null;
		
		try {
			
			newVehicle = new SemoviLoadRequestDTO(
					"260583",
					"905WKY",
					"AAAAAAAAAAAAAAAAA",
					"BBBBBBBB",
					"2000",
					"Rojo",
					"Ruta WM",
					"Web Maps",
					"100",
					"GM",
					"Chevi 1",
					"",
					"NA",
					"http://3.17.128.211:8084/video/0099003BA9"
					);
			
			String response = vehicleLoadFeignClient.load(newVehicle);
			
			SemoviResponseDTO convertido = objectMapper.readValue(response, SemoviResponseDTO.class);
			
			System.err.println(convertido.toString());
			assertNotNull(convertido.getStatus());
			assertNotNull(convertido.getMsg());
		}catch(Exception e) {
			e.printStackTrace();
			fail();
		}
	}

}
