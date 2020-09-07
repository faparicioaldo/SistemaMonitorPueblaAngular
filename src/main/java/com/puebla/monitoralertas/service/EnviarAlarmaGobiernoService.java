package com.puebla.monitoralertas.service;

import com.puebla.monitoralertas.dto.SemoviResponseDTO;

public interface EnviarAlarmaGobiernoService {

	public void actualizarConAlertasCeiba();
	public void enviarGPSs();
	public void pruebaGPS();
	public SemoviResponseDTO enviarAlertaSemovi(Integer idAlerta);
	public void descartarAlertaSemovi(Integer idAlerta);
	
}
