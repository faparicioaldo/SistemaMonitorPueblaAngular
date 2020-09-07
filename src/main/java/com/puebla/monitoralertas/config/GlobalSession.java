package com.puebla.monitoralertas.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.puebla.monitoralertas.dto.GpsCoordinatesDTO;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class GlobalSession {

	private Integer contadorEnviarGPSs = 0;
	private Integer ciclosContadorGPS = 8;
	private String keyCeiba2;
	private Map<String,GpsCoordinatesDTO> mapLastGps = new HashMap<>();
	private Map<String,Set<String>> mapGpsStatus = new HashMap<>();

	
}
