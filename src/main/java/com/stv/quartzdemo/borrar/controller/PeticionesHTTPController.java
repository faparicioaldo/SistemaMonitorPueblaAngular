package com.stv.quartzdemo.borrar.controller;
//package com.stv.quartzdemo.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.stv.quartzdemo.json.pojo.DispositivoGPSPojo;
//import com.stv.quartzdemo.json.pojo.LoginPojo;
//import com.stv.quartzdemo.json.pojo.UserVehiclePojo;
//import com.stv.quartzdemo.json.pojo.VehiclePojo;
//import com.stv.quartzdemo.service.APISistemaVideoVigilanciaService;
//import com.stv.quartzdemo.socket.Cliente;
//
//import lombok.extern.log4j.Log4j2;
//
//@Controller
//@Log4j2
//public class PeticionesHTTPController {
//
//	@Autowired
//	private APISistemaVideoVigilanciaService apiSVV;
//
//	@GetMapping("/peticionHTTP")
//	public String peticionHTTP(Model model) {
//		model.addAttribute("name", "ALDO");
//
//		String respuesta = null;
//		String loginResponse = null;
//		LoginPojo login = null;
//		String jsonVehiclesList = null;
//		UserVehiclePojo listaVehiculos = null;
//		String GPSVehicle = null;
//		StringBuilder gpsList = null;
//		Cliente clienteSocket = null;
//
//		try {
//			loginResponse = apiSVV.login();
//			login = new ObjectMapper().readValue(loginResponse, LoginPojo.class);
//			log.info("LOGIN: " + login.toString());
//
//			jsonVehiclesList = apiSVV.getAllVehicles(login.getJSESSIONID());
//			listaVehiculos = new ObjectMapper().readValue(jsonVehiclesList, UserVehiclePojo.class);
//			log.info("LISTA VEHICULOS CON SESSIONID => (" + login.getJSESSIONID() + ") : " + listaVehiculos.toString());
//
//			// Map<String, String> gpsList = new HashMap<>();
//			gpsList = new StringBuilder("{");
//			for (VehiclePojo vehicle : listaVehiculos.getVehicles()) {
//				// GPSVehicle = getEstatusVehicle(login.getJSESSIONID(),
//				// vehicle.getDl().get(0).getId());
//				GPSVehicle = apiSVV.getGPSVehicle(login.getJSESSIONID(), vehicle.getDl().get(0).getId());
//				DispositivoGPSPojo gpsDispositivo = new ObjectMapper().readValue(GPSVehicle, DispositivoGPSPojo.class);
//				// gpsList.put(vehicle.getNm(), GPSVehicle);
//				gpsList.append(vehicle.getNm()).append(",").append(GPSVehicle);
//				log.info("VEHICULO: " + vehicle.getDl().get(0).getId() + " | GPS => LAT: "
//						+ gpsDispositivo.getStatus().get(0).getLat() + ", LNG: "
//						+ gpsDispositivo.getStatus().get(0).getLng());
//			}
//			gpsList.append("}");
//
//			// log.info("JSON GPS's: " + gpsList.toString());
//
//			clienteSocket = new Cliente();
//			clienteSocket.setMensaje("holaaaaaaa");
//			clienteSocket.startClient();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return respuesta;
//	}
//}
