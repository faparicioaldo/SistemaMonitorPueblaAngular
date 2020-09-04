package com.puebla.monitoralertas.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.puebla.monitoralertas.config.SemoviFeignClientConfig;
import com.puebla.monitoralertas.dto.SemoviLoadRequestDTO;

@FeignClient(url = "${feign.url.semovi.load}", name = "data4", configuration = SemoviFeignClientConfig.class)
public interface SemoviLoadFeignClient {

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String load(@RequestBody SemoviLoadRequestDTO request);

}