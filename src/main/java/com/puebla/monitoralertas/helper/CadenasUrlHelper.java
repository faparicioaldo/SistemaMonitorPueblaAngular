package com.puebla.monitoralertas.helper;

import org.springframework.stereotype.Component;

@Component
public class CadenasUrlHelper {

	public String armaUrl(String protocol, String ip, String port, String context) {
		StringBuilder url = new StringBuilder();
		
		url.append(protocol)
		   .append("://")
		   		.append(ip)
		   .append(":")
		   		.append(port)
		   .append("")
		   		.append(context.trim().equals("/")?"":context);
		
		return url.toString();
	}
}
