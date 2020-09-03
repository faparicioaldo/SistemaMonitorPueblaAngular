package com.puebla.monitoralertas.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.puebla.monitoralertas.config.FeignClientConfig;
import com.puebla.monitoralertas.dto.SemoviDelRequestDTO;

@FeignClient(url = "${feign.url.semovi.del}", name = "data3",configuration = FeignClientConfig.class)
public interface SemoviDelFeignClient {

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String del(@RequestBody SemoviDelRequestDTO request);
	
}