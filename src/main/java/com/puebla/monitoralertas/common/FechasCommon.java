package com.puebla.monitoralertas.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class FechasCommon {
	
	/**
	 * @return fecha en formato yyyy-MM-dd HH:mm:ss
	 * 
	 * */
	public String dateToString(Date fecha) {
		String fechaFormated = null;		
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			fechaFormated = formatter.format(fecha);
		}catch(Exception e) {
			log.error("No se pudo convertir date con fecha a cadena", e);
		}
		return fechaFormated;
	}
	
	/**
	 * @param fecha en formato:
	 * 		yyyy-MM-dd HH:mm:ss
	 * */
	public Date stringToDate(String fechaString) {
		Date fechaDate = null;
		try {
			SimpleDateFormat formater=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			fechaDate = formater.parse(fechaString);
		}catch(Exception e) {
			log.error("No se pudo convertir cadena fecha a date", e);
		}
		return fechaDate; 
    }
}
