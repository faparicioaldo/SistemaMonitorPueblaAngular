package com.stv.quartzdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stv.quartzdemo.config.GlobanSession;

@RestController
public class MostrarCadenasController {

	@Autowired
	private GlobanSession session;

	@GetMapping("/mostrarCadenasLog/{valor}")
	public @ResponseBody String login(@PathVariable String valor) {

		String response = "";
		try {
			session.setMostrarCadenasAlertas(valor.equals("si")?true:false);
			response = "Operacion exitosa";
		}catch(Exception e) {
			response = "Error " + e.getMessage();
			
		}
		return response;
	}
}