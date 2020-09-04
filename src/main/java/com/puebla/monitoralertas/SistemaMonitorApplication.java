package com.puebla.monitoralertas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//@EnableScheduling
@EnableFeignClients
public class SistemaMonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaMonitorApplication.class, args);
	}
}
