package com.puebla.monitoralertas.config;

import org.springframework.stereotype.Component;

@Component
public class GlobalSession {

	private static String SESSION;
	private static String cadenaRespuesta;
	private static Integer tipoEnviaSocket = 1;
	private static Integer contadorEnviarGPSs = 0;
	private static Integer ciclosContadorGPS = 8;
	private static String fechaUltimaConsultaAlarma;
	private static String ultimaAlarmaId; 
	private static String semoviUrl = "http://semovi-puebladev.webmaps.com.mx/api/1.0/send";
    private static String semoviUsername = "sinergia";
    private static String semoviPassword = "ZWZjZGI3MzMzZTM0YTY5MGQ3NzkwMzNhMDBhNjZlZTI5ZjJlZjkyNWJhZDFmNmFjYmZjNjNlZjIyYTY0ZTk4NQ==";
	private static boolean mostrarCadenasAlertas = false;
	private static String keyCeiba2;
	
    public String getKeyCeiba2() {
		return keyCeiba2;
	}
    public void setKeyCeiba2(String key) {
    	GlobalSession.keyCeiba2 = key;
	}

	public String getSemoviUrl() {
		return semoviUrl;
	}

	public void setSemoviUrl(String semoviUrl) {
		GlobalSession.semoviUrl = semoviUrl;
	}

	public String getSemoviUsername() {
		return semoviUsername;
	}

	public void setSemoviUsername(String semoviUsername) {
		GlobalSession.semoviUsername = semoviUsername;
	}

	public String getSemoviPassword() {
		return semoviPassword;
	}

	public void setSemoviPassword(String semoviPassword) {
		GlobalSession.semoviPassword = semoviPassword;
	}
	
	public boolean isMostrarCadenasAlertas() {
		return mostrarCadenasAlertas;
	}

	public void setMostrarCadenasAlertas(boolean mostrarCadenasAlertas) {
		GlobalSession.mostrarCadenasAlertas = mostrarCadenasAlertas;
	}

	public String getUltimaAlarmaId() {
		return ultimaAlarmaId;
	}

	public void setUltimaAlarmaId(String ultimaAlarmaId) {
		GlobalSession.ultimaAlarmaId = ultimaAlarmaId;
	}

	public String getFechaUltimaConsultaAlarma() {
		return fechaUltimaConsultaAlarma;
	}

	public void setFechaUltimaConsultaAlarma(String fechaUltimaConsultaAlarma) {
		GlobalSession.fechaUltimaConsultaAlarma = fechaUltimaConsultaAlarma;
	}

	public String getCadenaRespuesta() {
		return cadenaRespuesta;
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

	public void setCadenaRespuesta(String cadena) {
		cadenaRespuesta = cadena;
	}
	
	public String getSESSION() {
		return SESSION;
	}

	public void setSESSION(String session) {
		SESSION = session;
	}

	public Integer getTipoEnviaSocket() {
		return tipoEnviaSocket;
	}

	public void setTipoEnviaSocket(Integer tipoEnviaSocket) {
		GlobalSession.tipoEnviaSocket = tipoEnviaSocket;
	}
	
	
		

}
