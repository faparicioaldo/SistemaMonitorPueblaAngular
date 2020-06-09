package com.stv.quartzdemo.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsultaBuroException extends Exception {

	private static final long serialVersionUID = -9037606384122128563L;

	private Integer codigo;
	private String mensaje;	
	
	public ConsultaBuroException(Integer codigo, String mensaje) {
		this.codigo = codigo;
		this.mensaje = mensaje;
	}

	public ConsultaBuroException(String mensaje) {
		super(mensaje);
	}

	public ConsultaBuroException(String mensaje, Throwable cause) {
		super(mensaje, cause);
	}
}
