package com.puebla.monitoralertas.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.auth.BasicAuthRequestInterceptor;

@Configuration
public class FeignClientConfig {
	
	@Value("${semovi.username}")
	String semoviUsername;
	
	@Value("${semovi.password}")
	String semoviPassword;
	
	@Bean
	public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
		return new BasicAuthRequestInterceptor(semoviUsername, semoviPassword);
	}
}
