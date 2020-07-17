package com.puebla.monitoralertas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.puebla.monitoralertas.config.GlobalSession;

@RestController
public class CambiarLosController {

	@Autowired
	private GlobalSession session;

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