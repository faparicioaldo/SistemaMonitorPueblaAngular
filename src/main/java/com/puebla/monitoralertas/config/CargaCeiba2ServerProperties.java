package com.puebla.monitoralertas.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Configuration
@PropertySource("classpath:ceiba2server.properties")
@Getter
@Setter
@Component
public class CargaCeiba2ServerProperties { 

    @Value("${ceiba2.server.service.application.context}")
    
    private String ceiba2ServiceAppContext;

    @Value("${ceiba2.server.service.username}")
    
    private String ceiba2ServiceUsername;

    @Value("${ceiba2.server.service.password}")
    
    private String ceiba2ServicePassword;

	@Value("${ceiba2.server.service.ip}")
    
    private String ceiba2ServiceIp;

    @Value("${ceiba2.server.service.port}")
    
    private String ceiba2ServicePort;

    @Value("${ceiba2.server.service.video.port}")
    
    private String ceiba2ServiceVideoPort;

    @Value("${ceiba2.server.service.protocol}")
    
    private String ceiba2ServiceProtocol;

    @Value("${ceiba2.server.service.key}")
    
    private String key;

    @Value("${ceiba2.server.service.devices}")
    
    private String devices;

    @Value("${ceiba2.server.service.live.video.url}")
    
    private String liveVideoUrl;

    @Value("${ceiba2.server.service.live.video.direct.uri}")
    
    private String liveVideoDirectUri;

    @Value("${ceiba2.server.service.post.gps.last}")
    
    private String deviceGpsLast;

    @Value("${ceiba2.server.service.device.online.now}")
    
    private String deviceOnlineNow;

    @Value("${ceiba2.server.service.alarm.detail.info}")
    
    private String deviceAlarmInfo;

    
}
