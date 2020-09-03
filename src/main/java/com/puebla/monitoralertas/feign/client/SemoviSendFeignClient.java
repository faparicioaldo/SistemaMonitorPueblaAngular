package com.puebla.monitoralertas.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.puebla.monitoralertas.config.FeignClientConfig;
import com.puebla.monitoralertas.dto.SemoviSendRequestDTO;

@FeignClient(url = "${feign.url.semovi.send}", name = "data1",configuration = FeignClientConfig.class)
public interface SemoviSendFeignClient {

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String send(@RequestBody SemoviSendRequestDTO request);
	
}