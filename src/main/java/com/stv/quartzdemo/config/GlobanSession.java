package com.stv.quartzdemo.config;

import org.springframework.stereotype.Component;

@Component
public class GlobanSession {

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
    	GlobanSession.keyCeiba2 = key;
	}

	public String getSemoviUrl() {
		return semoviUrl;
	}

	public void setSemoviUrl(String semoviUrl) {
		GlobanSession.semoviUrl = semoviUrl;
	}

	public String getSemoviUsername() {
		return semoviUsername;
	}

	public void setSemoviUsername(String semoviUsername) {
		GlobanSession.semoviUsername = semoviUsername;
	}

	public String getSemoviPassword() {
		return semoviPassword;
	}

	public void setSemoviPassword(String semoviPassword) {
		GlobanSession.semoviPassword = semoviPassword;
	}
	
	public boolean isMostrarCadenasAlertas() {
		return mostrarCadenasAlertas;
	}

	public void setMostrarCadenasAlertas(boolean mostrarCadenasAlertas) {
		GlobanSession.mostrarCadenasAlertas = mostrarCadenasAlertas;
	}

	public static String getUltimaAlarmaId() {
		return ultimaAlarmaId;
	}

	public static void setUltimaAlarmaId(String ultimaAlarmaId) {
		GlobanSession.ultimaAlarmaId = ultimaAlarmaId;
	}

	public static String getFechaUltimaConsultaAlarma() {
		return fechaUltimaConsultaAlarma;
	}

	public static void setFechaUltimaConsultaAlarma(String fechaUltimaConsultaAlarma) {
		GlobanSession.fechaUltimaConsultaAlarma = fechaUltimaConsultaAlarma;
	}

	public String getCadenaRespuesta() {
		return cadenaRespuesta;
	}

	public static Integer getContadorEnviarGPSs() {
		return contadorEnviarGPSs;
	}

	public static void setContadorEnviarGPSs(Integer contadorEnviarGPSs) {
		GlobanSession.contadorEnviarGPSs = contadorEnviarGPSs;
	}

	public static Integer getCiclosContadorGPS() {
		return ciclosContadorGPS;
	}

	public static void setCiclosContadorGPS(Integer ciclosContadorGPS) {
		GlobanSession.ciclosContadorGPS = ciclosContadorGPS;
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
		GlobanSession.tipoEnviaSocket = tipoEnviaSocket;
	}
	
	
		

}
