package com.puebla.monitoralertas.feign.client;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.puebla.monitoralertas.dto.SemoviResponseDTO;
import com.puebla.monitoralertas.dto.SemoviSendRequestDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SemoviSendFeignClientTest {

	@Autowired
	private SemoviSendFeignClient vehicleSendFeignClient;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void sendVehicleTest() {

		SemoviSendRequestDTO newAlert = null;

		try {

			newAlert = new SemoviSendRequestDTO("260583", "-98.099181", "19.157860", "", "", "", "2020-02-04 17:59:00",
					"1", "1");

			String response = vehicleSendFeignClient.send(newAlert);

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
