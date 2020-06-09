package com.stv.quartzdemo.borrar.controller;
//package com.stv.quartzdemo.controller;
//
//import java.io.IOException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import com.fasterxml.jackson.core.JsonParseException;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.stv.quartzdemo.json.pojo.LoginPojo;
//import com.stv.quartzdemo.json.pojo.UserVehiclePojo;
//import com.stv.quartzdemo.service.APISistemaVideoVigilanciaService;
//import com.stv.quartzdemo.socket.Cliente;
//
//import lombok.extern.log4j.Log4j2;
//
//@Controller
//@Log4j2
//public class ConsultaAlarmasController {
//
//	@Autowired
//	APISistemaVideoVigilanciaService apiSVV;
//
//    @GetMapping("/consultaAlarmas")
//    public String consultaAlarmas() {
//		String respuesta = null;
//    	String loginResponse = null;
//    	LoginPojo login = null;
//    	String jsonVehiclesList = null;
//    	UserVehiclePojo listaVehiculos  = null;
//		String GPSVehicle = null;
//		StringBuilder gpsList = null;
//		Cliente clienteSocket = null;
//
//    	try {
//        	loginResponse = apiSVV.login();
//			login = new ObjectMapper().readValue(loginResponse, LoginPojo.class);
//	    	log.info("LOGIN: " + login.toString());
//		} catch (JsonParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (JsonMappingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//        return "Hello and Welcome to the EasyNotes application. You can create a new Note by making a POST request to /api/notes endpoint.";
//    }
//
//}
