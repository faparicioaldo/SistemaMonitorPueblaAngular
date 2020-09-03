package com.puebla.monitoralertas.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.puebla.monitoralertas.config.FeignClientConfig;

@FeignClient(url = "${feign.url.semovi.list}", name = "data2",configuration = FeignClientConfig.class)
public interface SemoviListFeignClient {

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String list();
	
}