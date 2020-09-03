package com.puebla.monitoralertas.feign.client;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.puebla.monitoralertas.dto.SemoviListResponseDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SemoviListFeignClientTest {

	@Autowired
	private SemoviListFeignClient vehicleListFeignClient;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@Ignore
	public void ListVehiclesTest() {

		try {

			String response = vehicleListFeignClient.list();

			SemoviListResponseDTO convertido = objectMapper.readValue(response, SemoviListResponseDTO.class);

			System.err.println(convertido.toString());
			assertNotNull(convertido.getData());
			assertNotNull(convertido.getStatus());
			assertNotNull(convertido.getMsg());

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
