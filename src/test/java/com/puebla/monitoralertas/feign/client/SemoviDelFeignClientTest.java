package com.puebla.monitoralertas.feign.client;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.puebla.monitoralertas.dto.SemoviDelRequestDTO;
import com.puebla.monitoralertas.dto.SemoviResponseDTO;
import com.puebla.monitoralertas.dto.SemoviSendRequestDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SemoviDelFeignClientTest {

	@Autowired
	private SemoviDelFeignClient vehicleDelFeignClient;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void DelVehicleTest() {

		SemoviDelRequestDTO idVehicle = null;

		try {

			idVehicle = new SemoviDelRequestDTO("1");

			String response = vehicleDelFeignClient.del(idVehicle);

			SemoviResponseDTO convertido = objectMapper.readValue(response, SemoviResponseDTO.class);

			System.err.println(convertido.toString());
			assertNotNull(convertido.getStatus());
			assertNotNull(convertido.getMsg());

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
