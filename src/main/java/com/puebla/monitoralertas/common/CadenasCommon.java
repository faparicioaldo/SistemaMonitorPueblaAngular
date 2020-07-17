package com.puebla.monitoralertas.common;

import java.text.MessageFormat;

import org.springframework.stereotype.Component;

@Component
public class CadenasCommon {

	public String format(String cadena,String param) {
		return (MessageFormat.format(cadena.substring(0), param));
	}

	public String format(String cadena, Object[] paramsList) {
		return (MessageFormat.format(cadena.substring(0), paramsList));
	}
}
