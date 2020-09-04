package com.puebla.monitoralertas.config;

import org.springframework.stereotype.Component;

@Component
public class GlobalSession {

	private static Integer contadorEnviarGPSs = 0;
	private static Integer ciclosContadorGPS = 8;
	private static String keyCeiba2;
	
    public String getKeyCeiba2() {
		return keyCeiba2;
	}
    public void setKeyCeiba2(String key) {
    	GlobalSession.keyCeiba2 = key;
	}

	public Integer getContadorEnviarGPSs() {
		return contadorEnviarGPSs;
	}

	public void setContadorEnviarGPSs(Integer contadorEnviarGPSs) {
		GlobalSession.contadorEnviarGPSs = contadorEnviarGPSs;
	}

	public Integer getCiclosContadorGPS() {
		return ciclosContadorGPS;
	}

	public void setCiclosContadorGPS(Integer ciclosContadorGPS) {
		GlobalSession.ciclosContadorGPS = ciclosContadorGPS;
	}
	
}
