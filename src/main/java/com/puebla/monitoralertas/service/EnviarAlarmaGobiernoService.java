package com.puebla.monitoralertas.service;

public interface EnviarAlarmaGobiernoService {

	public void actualizarConAlertasCeiba();
	public void enviarGPSs();
	public void enviarAlertaSemovi(Integer idAlerta);
	public void descartarAlertaSemovi(Integer idAlerta);
	
}
