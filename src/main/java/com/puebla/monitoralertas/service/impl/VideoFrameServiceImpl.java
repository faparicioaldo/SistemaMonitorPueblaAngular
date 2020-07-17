package com.puebla.monitoralertas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.puebla.monitoralertas.common.CadenasCommon;
import com.puebla.monitoralertas.config.SistemaMonitorProperties;
import com.puebla.monitoralertas.helper.CadenasUrlHelper;
import com.puebla.monitoralertas.service.VideoFrameService;

@Service
public class VideoFrameServiceImpl implements VideoFrameService {

	@Autowired
	private SistemaMonitorProperties sistemaMonitorProperties;

	@Autowired
	private CadenasUrlHelper cadenasUrlHelper;
	
	@Autowired
	private CadenasCommon cadenasHelper;
	
	@Override
	public String getUrlFrameLiveVideo(String vehicleId) {
		String rutaServicio = cadenasHelper.format(sistemaMonitorProperties.getVideoFrame(),vehicleId);
		String url = 
				cadenasUrlHelper.armaUrl(
						sistemaMonitorProperties.getProtocol(), 
						sistemaMonitorProperties.getIp(), 
						sistemaMonitorProperties.getPort(),
						sistemaMonitorProperties.getContext()) + rutaServicio;
		return url;
	}
}