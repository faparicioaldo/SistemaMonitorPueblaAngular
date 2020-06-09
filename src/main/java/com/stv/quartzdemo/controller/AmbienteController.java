package com.stv.quartzdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stv.quartzdemo.config.GlobanSession;
import com.stv.quartzdemo.dto.SemoviPropertiesDTO;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class AmbienteController {


	@Autowired
	private GlobanSession session;

	@PostMapping("/cambioAmbienteSemovi")
	public @ResponseBody String login(@RequestBody SemoviPropertiesDTO request) {
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