package com.puebla.monitoralertas.dto;

import org.apache.logging.log4j.message.MessageFormatMessage;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RespuestaJSON {
	protected int respuestaCode = 0;
	protected String descripcionCode = "";

	public void generaError(int respuestaCode, String descripcionCode) {
		this.respuestaCode = respuestaCode;
		this.descripcionCode = descripcionCode;
	}

	public void generaError(String descripcionCode) {
		this.respuestaCode = 1;
		this.descripcionCode = descripcionCode;
	}

	public void generaError(Errors errores) {
		this.respuestaCode = 1;
		for (FieldError error : errores.getFieldErrors()) {
			this.descripcionCode += new MessageFormatMessage(error.getDefaultMessage(), error.getArguments())
					.getFormattedMessage() + "; ";
		}
	}
}
