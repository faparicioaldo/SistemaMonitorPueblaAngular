package com.stv.quartzdemo.config;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Configuration
@PropertySource("classpath:ceiba2server.properties")
@Getter
@Setter
@ToString
@Component
public class CargaCeiba2ServerProperties { 

    @Value("${ceiba2.server.service.application.context}")
    @NotBlank
    private String ceiba2ServiceAppContext;

    @Value("${ceiba2.server.service.username}")
    @NotBlank
    private String ceiba2ServiceUsername;

    @Value("${ceiba2.server.service.password}")
    @NotBlank
    private String ceiba2ServicePassword;

	@Value("${ceiba2.server.service.ip}")
    @NotBlank
    private String ceiba2ServiceIp;

    @Value("${ceiba2.server.service.port}")
    @NotBlank
    private String ceiba2ServicePort;

    @Value("${ceiba2.server.service.video.port}")
    @NotBlank
    private String ceiba2ServiceVideoPort;

    @Value("${ceiba2.server.service.protocol}")
    @NotBlank
    private String ceiba2ServiceProtocol;

    @Value("${ceiba2.server.service.key}")
    @NotBlank
    private String key;

    @Value("${ceiba2.server.service.devices}")
    @NotBlank
    private String devices;

    @Value("${ceiba2.server.service.live.video.url}")
    @NotBlank
    private String liveVideoUrl;

    @Value("${ceiba2.server.service.live.video.direct.uri}")
    @NotBlank
    private String liveVideoDirectUri;

    @Value("${ceiba2.server.service.post.gps.last}")
    @NotBlank
    private String deviceGpsLast;

    @Value("${ceiba2.server.service.device.online.now}")
    @NotBlank
    private String deviceOnlineNow;

    @Value("${ceiba2.server.service.alarm.detail.info}")
    @NotBlank
    private String deviceAlarmInfo;

    
}
