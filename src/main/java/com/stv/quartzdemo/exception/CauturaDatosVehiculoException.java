package com.stv.quartzdemo.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CauturaDatosVehiculoException extends Exception {

	private static final long serialVersionUID = -9037606384122128563L;

	private Integer codigo;
	private String mensaje;	
	
	public CauturaDatosVehiculoException(Integer codigo, String mensaje) {
		this.codigo = codigo;
		this.mensaje = mensaje;
	}

	public CauturaDatosVehiculoException(String mensaje) {
		super(mensaje);
	}

	public CauturaDatosVehiculoException(String mensaje, Throwable cause) {
		super(mensaje, cause);
	}
}
