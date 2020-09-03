package com.puebla.monitoralertas.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Configuration
@PropertySource("classpath:sistemaMonitor.properties")
@Getter
@Setter
@Component
public class SistemaMonitorProperties { 
	
    @Value("${monitor.alertas.service.ip}")
    
    private String ip;

    @Value("${monitor.alertas.service.port}")
    
    private String port;

    @Value("${monitor.alertas.service.protocol}")
    
    private String protocol;
    
    @Value("${monitor.alertas.service.application.context}")
    
    private String context;
    
    @Value("${monitor.alertas.service.video}")
    
    private String videoFrame;
}
