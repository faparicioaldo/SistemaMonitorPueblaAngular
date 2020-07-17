package com.puebla.monitoralertas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
//@EnableScheduling
public class SistemaMonitorApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SistemaMonitorApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SistemaMonitorApplication.class, args);
	}
}