package com.puebla.monitoralertas.config;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Configuration
@PropertySource("classpath:semovi.properties")
@Getter
@Setter
@Component
public class CargaSemoviProperties { 
	
    @Value("${semovi.webmaps.service.url}")
    @NotBlank
    private String url;

    @Value("${semovi.webmaps.service.username}")
    @NotBlank
    private String username;

    @Value("${semovi.webmaps.service.password}")
    @NotBlank
    private String password;
}