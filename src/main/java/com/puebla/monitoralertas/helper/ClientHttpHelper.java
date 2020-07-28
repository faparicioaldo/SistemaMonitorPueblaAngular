package com.puebla.monitoralertas.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class ClientHttpHelper {
	
	@Autowired
	private RestTemplate restTemplate;
	
	public String peticionHttpGet(String urlParaVisitar) {
		StringBuilder resultado = null;
		URL url = null;
		HttpURLConnection conexion = null;
		BufferedReader rd = null;
		String linea = null;
		try {
			resultado = new StringBuilder();
			url = new URL(urlParaVisitar);
			
			conexion = (HttpURLConnection) url.openConnection();
			conexion.setRequestMethod("GET");
			rd = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
			
			while ((linea = rd.readLine()) != null) {
				resultado.append(linea);
			}
			rd.close();
		
		} catch (MalformedURLException e) {
			log.error("MalformedURLException: ", e.getMessage());
			log.debug(e);
		} catch (IOException e) {
			log.error("IOException: ", e.getMessage());
			log.debug(e);
		}

		return resultado.toString();
	}

	public String peticionHttpPost(String url, Object objectRequest) {
	    String  result = restTemplate.postForEntity(url, objectRequest, String.class).getBody();
		return result;
	}

}
