package com.puebla.monitoralertas.rest.client;

import java.nio.charset.Charset;
import java.util.Arrays;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.puebla.monitoralertas.config.GlobalSession;
import com.puebla.monitoralertas.dto.SemoviRequestDTO;
import com.puebla.monitoralertas.dto.SemoviResponseDTO;
import com.puebla.monitoralertas.dto.SemoviResponseWrapperDTO;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ClienteSemoviPuebla {

	@Autowired
	private GlobalSession session;
		
	public SemoviResponseWrapperDTO enviarMensajeSemovi(SemoviRequestDTO datos ) {

		SemoviResponseWrapperDTO responseWrapper = new SemoviResponseWrapperDTO(); 
		
		RestTemplate api = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();

		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		SemoviResponseDTO response = null;
		
		ObjectMapper mapper = new ObjectMapper();
		String result = null;
		SemoviResponseDTO res = null;
		HttpEntity<SemoviRequestDTO> entityRequest = null;
		try {
			entityRequest = new HttpEntity(datos, createHeaders(session.getSemoviUsername(), session.getSemoviPassword()));
			System.err.println("Semovi URL: " + session.getSemoviUrl());
			System.err.println("Semovi Username: " + session.getSemoviUsername());
			System.err.println("Semovi Password: " + session.getSemoviPassword());
			result = api.exchange(session.getSemoviUrl(), HttpMethod.POST, entityRequest, String.class).getBody();
			log.info("RESPUESTA SEMOVI: " + result);
			response = mapper.readValue(result, SemoviResponseDTO.class);
			responseWrapper.setSemoviResponse(response);
			responseWrapper.setSemoviResponseJson(result);
		} catch (Exception e) {
			log.error("Error fallo al enviar datos a semovi jsonResult: " + result, e);
		}

		return responseWrapper;
	}

	private HttpHeaders createHeaders(String username, String password) {
		return new HttpHeaders() {
			{
				String auth = username + ":" + password;
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);

				set("Authorization",authHeader);
				setContentType(MediaType.APPLICATION_JSON);
			}
		};
	}
}
