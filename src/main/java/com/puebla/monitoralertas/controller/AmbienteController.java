package com.puebla.monitoralertas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.puebla.monitoralertas.config.GlobalSession;
import com.puebla.monitoralertas.dto.SemoviPropertiesDTO;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class AmbienteController {

	@Autowired
	private GlobalSession session;

	@PostMapping("/cambioAmbienteSemovi")
	public @ResponseBody String cambioAmbienteSemovi(@RequestBody SemoviPropertiesDTO request) {
		String response;
		try {
			session.setSemoviUrl(request.getSemoviUrl());
			session.setSemoviUsername(request.getSemoviUsername());
			session.setSemoviPassword(request.getSemoviPassword());
			response = "{\"\"msj\"\":\"\"Actualizacion correcta\"\", \"\"datos\"\":\"\"ok\"\"}";
		}catch(Exception e) {
			log.error("Error al cambiar parametros de ambiente: ", e);
			response = "Error al cambiar datos: " + e.getMessage();	
		}
		return response;
	}
}